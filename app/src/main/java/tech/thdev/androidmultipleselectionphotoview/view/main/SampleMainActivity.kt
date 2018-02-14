package tech.thdev.androidmultipleselectionphotoview.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sample_main.*
import tech.thdev.androidmultipleselectionphotoview.R
import tech.thdev.androidmultipleselectionphotoview.data.PhotoItem
import tech.thdev.androidmultipleselectionphotoview.view.main.adapter.SampleAdapter
import java.util.*

/**
 * Created by Taehwan on 12/02/2018.
 */
class SampleMainActivity : AppCompatActivity() {

    private val imageList = listOf(
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_01.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_02.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_03.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_04.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_05.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_06.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_07.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_08.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_09.png?raw=true",
            "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_10.png?raw=true")

    private val sampleAdapter: SampleAdapter by lazy {
        SampleAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)

        recycler_view.run {
            layoutManager = GridLayoutManager(this@SampleMainActivity, 3)
            adapter = sampleAdapter
        }

        sampleAdapter.run {
            (0..30).forEach {
                itemList.add(PhotoItem(imageList[(0..9).random()]))
            }
            notifyDataSetChanged()

            selectLimitCount = 3

            onClickItem = {
                if (sampleAdapter.updateSelectItem(it)) {
                    sampleAdapter.notifyItemChanged(it)
                    tv_selected_count.text = sampleAdapter.selectList.size.toString()
                }
            }

            onSelectedLimit = {
                Toast.makeText(this@SampleMainActivity, "Select limit item!!! $it", Toast.LENGTH_SHORT).show()
            }
        }

        tv_total_count.text = sampleAdapter.selectLimitCount.toString()
        tv_selected_count.text = sampleAdapter.selectList.size.toString()
    }

    fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + 1
}