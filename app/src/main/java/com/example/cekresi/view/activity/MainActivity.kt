package com.example.cekresi.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cekresi.R
import com.example.cekresi.base.BaseActivity
import com.example.cekresi.base.NetworkResult
import com.example.cekresi.databinding.ActivityMainBinding
import com.example.cekresi.helper.convertDate
import com.example.cekresi.helper.utils.API_KEY
import com.example.cekresi.network.request.ResiRequest
import com.example.cekresi.network.response.Detail
import com.example.cekresi.network.response.HistoryItem
import com.example.cekresi.network.response.Summary
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    var awb: String? = null

    override val setLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initialization() {
        onclickListener()
    }

    override fun observeViewModel() {

        viewModel.resi.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.data
                    if (!data.toString().isNullOrEmpty()) {
                        setupView(data?.summary, data?.detail)
                        setupAdapter(data?.history)
                        binding.layoutHasil.visibility = VISIBLE
                        binding.progressBar.visibility = GONE
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        Log.e("Error", "observeViewModel: $it")
                    }
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = VISIBLE
                }
            }
        }

    }

    private fun setupView(summary: Summary?, detail: Detail?) {
        binding.progressBar.visibility = GONE
        binding.layoutDetail.apply {
            txtEkspedisi.text = "Ekspedisi ${summary?.courier}"
            txtResi.text = summary?.awb
            txtService.text = summary?.service
            txtDate.text = convertDate(summary?.date, "dd/MMMM/yyyy" + "," + "HH:mm")
            txtShipper.text = "${detail?.shipper}\n ${detail?.origin}"
            txtReceiver.text = "${detail?.receiver}\n ${detail?.destination}"
            txtStatus.text = summary?.status
        }


    }

    private fun onclickListener() {


        binding.btnCekResi.setOnClickListener {
            binding.layoutButton.root.visibility = VISIBLE
            awb = binding.edtResi.text.toString()
        }

        binding.layoutButton.apply {

            btnJNT.setOnClickListener {
                viewModel.getResi(
                    API_KEY,
                    "jnt",
                    awb
                )
            }

            btnSicepat.setOnClickListener {
                viewModel.getResi(
                    API_KEY,
                    "sicepat",
                    awb
                )
            }
            btnJNE.setOnClickListener {
                viewModel.getResi(
                    API_KEY,
                    "jne",
                    awb
                )
            }
            btnPOS.setOnClickListener {
                viewModel.getResi(
                    API_KEY,
                    "pos",
                    awb
                )
            }
            btnTiki.setOnClickListener {
                viewModel.getResi(
                    API_KEY,
                    "tiki",
                    awb
                )
            }
            btnAnterAja.setOnClickListener {
                viewModel.getResi(
                    API_KEY,
                    "anteraja",
                    awb
                )
            }
            btnJETExpress.setOnClickListener {
                viewModel.getResi(
                    API_KEY,
                    "jet",
                    awb
                )
            }
            btnShopeeExpress.setOnClickListener {
                viewModel.getResi(
                    API_KEY,
                    "spx",
                    awb
                )
            }

        }

        binding.layoutHasil.apply {
            with(binding) {
                txtDetail.setOnClickListener {
                    binding.layoutDetail.root.visibility = VISIBLE
                }

                txtHistory.setOnClickListener {
                    binding.rvHistory.visibility = VISIBLE
                }
            }
        }

    }


    private fun setupAdapter(list: ArrayList<HistoryItem>?) {
        val mainAdapter = MainAdapter().apply {
            setNewInstance(list?.toMutableList())
        }
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mainAdapter
        }
    }

}