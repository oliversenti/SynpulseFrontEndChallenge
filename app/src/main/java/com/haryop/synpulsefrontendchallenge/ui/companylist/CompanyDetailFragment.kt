package com.haryop.synpulsefrontendchallenge.ui.companylist

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.SystemClock
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import com.google.gson.Gson
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.data.entities.DailyDataEntity
import com.haryop.synpulsefrontendchallenge.data.entities.GlobalQuoeEndpointEntity
import com.haryop.synpulsefrontendchallenge.databinding.FragmentCompanyDetailBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding
import com.haryop.synpulsefrontendchallenge.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import timber.log.Timber


@AndroidEntryPoint
class CompanyDetailFragment : BaseFragmentBinding<FragmentCompanyDetailBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCompanyDetailBinding
        get() = FragmentCompanyDetailBinding::inflate

    lateinit var viewbinding: FragmentCompanyDetailBinding
    override fun setupView(binding: FragmentCompanyDetailBinding) {
        viewbinding = binding
        setupAction(viewbinding.root)

        arguments?.getString("symbol")?.let {
            viewModel.start(it)
            setupObserversCompanyDetail()
            setupObserversDailyData()
        }

    }

    fun setupAction(view: View) = with(viewbinding) {

    }

    var isDailyDataLoaded = false
    var isCompanyDetaialLoaded = false
    private val viewModel: CompanyDetailViewModel by viewModels()
    private fun setupObserversDailyData() {
        isDailyDataLoaded = false
        viewModel.getChartDailyData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    isDailyDataLoaded = true
                    setLoadingGone()
                    if (it.data != null && it.data.time_series_data != null) {

                        var items = ArrayList<DailyDataEntity>()
                        var labels = ArrayList<String>()
                        var _tseriesdata = it.data!!.time_series_data

                        var tseriesdata = JSONObject(_tseriesdata.toString())
                        val keys: Iterator<String> = tseriesdata.keys()

                        Log.e("detail company", "tseriesdata keys.hasNext()= " + keys.hasNext())


                        while (keys.hasNext()) {
                            val key = keys.next()

                            if (tseriesdata.get(key) is JSONObject) {
                                // do something with jsonObject here
                                var gson = Gson()
                                val item: DailyDataEntity = gson.fromJson(
                                    tseriesdata.get(key).toString(),
                                    DailyDataEntity::class.java
                                )
                                items.add(item)
                                labels.add(key)
                            }
                        }

                        Log.e("detail company", "items.size= " + items.size)
                        setUpChart(items, labels)

                    } else {
                        Log.e("detail company", "SUCCESS but NULL")
                        Timber.e("getQuoteEndpoint.observe: SUCCESS tapi null")
                        Toast.makeText(
                            requireContext(),
                            it.message ?: "Request Timeout",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                Resource.Status.ERROR -> {
                    isDailyDataLoaded = true
                    setLoadingGone()

                    Timber.e("getQuoteEndpoint.observe: error")
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    Timber.e("getQuoteEndpoint.observe: LOADING")
                    viewbinding.progressBar.visibility = View.VISIBLE
                }
            }
        })

    }

    fun setUpChart(_items: ArrayList<DailyDataEntity>, _labels: ArrayList<String>) =
        with(viewbinding) {
            var items = ArrayList<DailyDataEntity>()
            var labels = ArrayList<String>()
            for (i in (_labels.size - 1) downTo 0) {
                items.add(_items[i])
                labels.add(_labels[i])
            }


            var dataset_1 = ArrayList<Entry>()
            var i = 0f
            for (item in items) {
                dataset_1.add(Entry(i, item.close.toFloat()))
                i++
            }
            Log.e("detail company", "dataset_1.size= " + dataset_1.size)
            Log.e("detail company", "dataset_1[0]= ${labels[0]} | ${dataset_1[0].y}")
            var set1 = LineDataSet(dataset_1, "close")

            set1.setDrawIcons(false)

            // draw dashed line

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f)

            // black lines and points

            // black lines and points
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)

            // line thickness and point size

            // line thickness and point size
            set1.lineWidth = 1f
            set1.circleRadius = 3f

            // draw points as solid circles

            // draw points as solid circles
            set1.setDrawCircleHole(false)

            // customize legend entry

            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            // text size of values

            // text size of values
            set1.valueTextSize = 9f

            // draw selection line as dashed

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area

            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> lineChart.getAxisLeft().getAxisMinimum() }

            // set color of filled area

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
//            var chart_color = 0
//            if (changePercent.text.toString().toDouble()<0) {
//                chart_color =  R.drawable.fade_red
//            }else{
//                chart_color =  R.drawable.fade_green
//            }
//            val drawable = ContextCompat.getDrawable(requireContext(), chart_color)
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.fade_orange)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }

            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            lineChart.data = data

            lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)

            val downTime: Long = SystemClock.uptimeMillis()
            val eventTime: Long = SystemClock.uptimeMillis() + 100
            val x = 0.0f
            val y = 0.0f
            val metaState = 0
            val motionEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                MotionEvent.ACTION_UP,
                x,
                y,
                metaState
            )
            lineChart.dispatchTouchEvent(motionEvent)
        }

    private fun setupObserversCompanyDetail() {
        isCompanyDetaialLoaded = false
        viewModel.getQuoteEndpoint.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    isCompanyDetaialLoaded = true
                    setLoadingGone()
                    if (it.data != null && it.data.global_quote != null) {
                        var item = it.data!!.global_quote
                        setUpPage(item)

                    } else {
                        Timber.e("getQuoteEndpoint.observe: SUCCESS tapi null")
                        Toast.makeText(
                            requireContext(),
                            it.message ?: "Request Timeout",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                Resource.Status.ERROR -> {
                    isCompanyDetaialLoaded = true
                    setLoadingGone()
                    Timber.e("getQuoteEndpoint.observe: error")
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    Timber.e("getQuoteEndpoint.observe: LOADING")
                    viewbinding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    fun setUpPage(item: GlobalQuoeEndpointEntity) = with(viewbinding) {
        symbol.text = arguments?.getString("symbol")
        name.text = arguments?.getString("name")
        setMarquee(name)
        changePercent.text = item.change_percent

        if (item.change_percent.substring(0, 1).equals("-")) {
            changePercent.setTextColor(resources.getColor(R.color.red))
        }

    }

    fun setLoadingGone() {
        if (isCompanyDetaialLoaded == true && isDailyDataLoaded == true) {
            viewbinding.progressBar.visibility = View.GONE
        }

    }

    fun setMarquee(textView: TextView) {
        textView.ellipsize = TextUtils.TruncateAt.MARQUEE
        textView.isSelected = true
        textView.marqueeRepeatLimit = -1
    }
}