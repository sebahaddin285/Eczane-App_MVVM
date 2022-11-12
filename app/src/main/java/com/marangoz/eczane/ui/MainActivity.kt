package com.marangoz.eczane.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marangoz.eczane.R
import com.marangoz.eczane.databinding.ActivityMainBinding
import com.marangoz.eczane.model.District
import com.marangoz.eczane.model.Objetc
import com.marangoz.eczane.model.Province
import com.marangoz.eczane.model.Result
import com.marangoz.eczane.repository.Repository
import com.marangoz.eczane.room.EczaneDao
import com.marangoz.eczane.room.EczaneDataBase
import java.lang.Exception


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
private lateinit var viewModel: MainActivityViewModel
private lateinit var list: List<District>
private lateinit var provincelist: List<Province>

class MainActivity : AppCompatActivity() {
    private val adapter: com.marangoz.eczane.adapter.Adapter by lazy {
        com.marangoz.eczane.adapter.Adapter(
            this
        )
    }
    private val db: EczaneDataBase by lazy { EczaneDataBase.accsessDatabase(this)!! }
    private val eDao: EczaneDao by lazy { db.getEczaneDao() }
    private val repo: Repository by lazy { Repository(eDao) }
    val viewModelFactory by lazy { MainActivityViewModelFactory(repo) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        //send repository to viewmodel
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MainActivityViewModel::class.java)

        viewModel.allProvince()

        viewModel.sehirId.observe(this) {
            viewModel.getDistrict(it)
        }

        viewModel.districtList.observe(this) {
            list = it
        }

        viewModel.provinceList.observe(this) {
            provincelist = it
        }

        viewModel.eczaneList.observe(this) {
            if (it.body()?.success == true){
                val list = it.body()?.result
                if (list != null) {
                    adapter.setList(list)
                }
            }


        }


        binding.sonucButton.setOnClickListener() {
            binding.swipeRefleshLayout.isRefreshing = true
            sonucGetir()


        }

        binding.ilcelistButton.setOnClickListener() {
            if (binding.sehirText.text == "Şehir") {
                Toast.makeText(this, "Lütfen Şehir Seçiniz", Toast.LENGTH_SHORT).show()
            } else {
                ilceGetir()
            }

        }

        binding.sehirlistButton.setOnClickListener() {
            binding.ilceText.text = "İlçe"
            sehirGetir()
        }


        //screen reflesh
        binding.swipeRefleshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                sonucGetir()
                val timer = object : CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        binding.swipeRefleshLayout.isRefreshing = false
                    }
                }
                timer.start()
            }
        })





    }

    fun sonucGetir() {


        if (binding.sehirText.text == "Şehir") {
            Toast.makeText(this, "Lütfen Şehir Seçiniz", Toast.LENGTH_SHORT).show()

        } else {
            val timer = object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    binding.swipeRefleshLayout.isRefreshing = false
                }
            }
            timer.start()
            var ilce: String? = null
            if (binding.ilceText.text == "İlçe") {
                ilce = null
            } else {
                ilce = binding.ilceText.text.toString()
            }
            val il = binding.sehirText.text.toString()
            try {
                viewModel.getCustomEczane(ilce, il)
            }catch (e:Exception){
                Toast.makeText(this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show()
            }

        }


    }

    fun sehirGetir() {
        val array = Array(81) { "" }
        var sayac = 0
        provincelist.forEach {
            array[sayac] = it.sehiradi
            sayac++
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sehir Seçiniz")
            .setItems(
                array
            ) { dialog, i ->
                binding.sehirText.text = array[i]
                viewModel.sehirId.value = provincelist[i].id
            }
        builder.create()
        builder.show()

    }

    fun ilceGetir() {
        val array = Array(list.size) { "" }
        var sayac = 0
        list.forEach {
            array[sayac] = it.ilceadi
            sayac++
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("İlçe Seçiniz")
            .setItems(
                array
            ) { dialog, i ->
                binding.ilceText.text = array[i]
            }
        builder.create()
        builder.show()


    }


}