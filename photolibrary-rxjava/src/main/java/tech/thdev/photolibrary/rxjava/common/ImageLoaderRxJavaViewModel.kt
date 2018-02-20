package tech.thdev.photolibrary.rxjava.common

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import tech.thdev.photolibrary.base.ImageLoaderNavigator
import tech.thdev.photolibrary.base.adapter.control.ImageLoaderAdapterNavigator
import tech.thdev.photolibrary.rxjava.data.source.image.ImageLoaderManagerRxJavaDataSorcue

/**
 * Created by Taehwan on 20/02/2018.
 */
class ImageLoaderRxJavaViewModel<out ADAPTER_VIEW_MODE : ImageLoaderAdapterNavigator>(
        application: Application,
        private val repository: ImageLoaderManagerRxJavaDataSorcue,
        private val adapterViewModel: ADAPTER_VIEW_MODE) : AndroidViewModel(application), ImageLoaderNavigator, LifecycleObserver {

    override lateinit var showEmptyImageList: () -> Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun loadImage() {
        repository.loadImage(getApplication())
                .observeOn(Schedulers.io())
                .filter {
                    if (it.isEmpty()) {
                        Observable.just(it.isEmpty())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    showEmptyImageList()
                                }
                    }
                    it.isNotEmpty()
                }
                .concatMapIterable { it }
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    adapterViewModel.addItem(it).takeIf { it > -1 }?.let {
                        adapterViewModel.notifyItemChanged(it)
                    }
                }
                .subscribe({
                    // TODO Success.
                }, {
                    // TODO Error.
                })
    }
}