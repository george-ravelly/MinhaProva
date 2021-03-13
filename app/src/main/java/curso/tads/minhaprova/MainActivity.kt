package curso.tads.minhaprova

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import curso.tads.minhaprova.databinding.ActivityMainBinding
import curso.tads.minhaprova.model.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: MainViewModel
    var requestCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        msgBoasVindas()
        manterTexto()
        binding.apply {
            button1.setOnClickListener {
                val i = Intent(applicationContext, Acao1Activity::class.java)
                startActivityForResult(i, requestCode)
            }
            button2.setOnClickListener {
                val dialog = SendMessageDialogFragment()
                dialog.isCancelable = false
                dialog.show(supportFragmentManager,"Dialog")
            }
            button3.setOnClickListener {
                val i = Intent(applicationContext, Acao2Activity::class.java)
                requestCode = 1
                startActivityForResult(i, requestCode)
            }
            button4.setOnClickListener {
                val i = Intent(applicationContext, Acao3Activity::class.java)
                startActivity(i)
            }
        }

    }

    fun manterTexto(){
        binding.textView3.text = viewmodel.texto1
        binding.textView4.text = viewmodel.texto2
    }

    private fun msgBoasVindas() {
        val pref: SharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        if (pref.getBoolean("boasVindas", true)) {
            Toast.makeText(this, R.string.msgBoasVindas, Toast.LENGTH_SHORT).show()
            pref.edit().putBoolean("boasVindas", false).apply()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            0 -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        viewmodel.texto1 = data?.getStringExtra("texto1").toString()
                        binding.textView3.text = viewmodel.texto1
                    }
                    Activity.RESULT_CANCELED -> {
                        Snackbar.make(binding.mainlayout, "Cancelado", Snackbar.LENGTH_LONG)
                            .setAction("cancelar") {
                                Toast.makeText(this, R.string.cancelar, Toast.LENGTH_SHORT).show()
                            }.show()
                    }
                }
            }1 -> {
                when (resultCode) {
                        Activity.RESULT_OK -> {
                        viewmodel.texto2 = "Cadastrado!"
                        binding.textView4.text = viewmodel.texto2
                    }
                        Activity.RESULT_CANCELED -> {
                        Snackbar.make(binding.mainlayout, "Cancelado", Snackbar.LENGTH_LONG)
                            .setAction("cancelar") {
                                Toast.makeText(this, R.string.cancelar, Toast.LENGTH_SHORT).show()
                            }.show()
                    }
                }
            }
        }
    }
}