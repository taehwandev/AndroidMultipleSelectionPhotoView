package tech.thdev.androidmultipleselectionphotoview.view.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sample_main.*
import tech.thdev.androidmultipleselectionphotoview.R
import tech.thdev.androidmultipleselectionphotoview.view.sample.adapter.SampleAdapter
import tech.thdev.photolibrary.base.adapter.control.ImageLoaderAdapterNavigator
import tech.thdev.photolibrary.base.adapter.control.ImageLoaderAdapterViewModel
import tech.thdev.photolibrary.rxjava.common.ImageLoaderRxJavaViewModel
import tech.thdev.photolibrary.rxjava.data.source.image.ImageLoaderManagerRxJavaRepository

/**
 * Created by Taehwan on 12/02/2018.
 */
class SampleMainActivity : AppCompatActivity() {


    private val sampleAdapter: SampleAdapter by lazy {
        SampleAdapter(ImageLoaderAdapterViewModel(this@SampleMainActivity, 3))
    }

    private val viewModel: ImageLoaderRxJavaViewModel<ImageLoaderAdapterNavigator> by lazy {
        ImageLoaderRxJavaViewModel(application, ImageLoaderManagerRxJavaRepository, sampleAdapter.viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)

        recycler_view.run {
            layoutManager = GridLayoutManager(this@SampleMainActivity, 3)
            adapter = sampleAdapter
        }

        viewModel.loadImage()
        sampleAdapter.viewModel.showAlertSelectedLimit = {
            Toast.makeText(this@SampleMainActivity, "Select limit item!!! $it", Toast.LENGTH_SHORT).show()
        }
        viewModel.showEmptyImageList = {
            Toast.makeText(this@SampleMainActivity, "empty!!!!!", Toast.LENGTH_SHORT).show()
        }

        tv_total_count.text = sampleAdapter.viewModel.selectLimitCount.toString()
        tv_selected_count.text = sampleAdapter.viewModel.selectLimitCount.toString()
    }
}