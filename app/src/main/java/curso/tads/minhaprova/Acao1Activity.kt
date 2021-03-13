package curso.tads.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import curso.tads.minhaprova.databinding.ActivityAcao1Binding
import curso.tads.minhaprova.model.Action1ViewModel

class Acao1Activity : AppCompatActivity() {
    lateinit var binding: ActivityAcao1Binding
    lateinit var viewmodel:Action1ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)
        viewmodel = ViewModelProvider(this).get(Action1ViewModel::class.java)
        viewmodel.texto
        binding.apply {
            ok.setOnClickListener {
                val i = Intent(applicationContext, MainActivity::class.java)
                if(!editText1.text.isEmpty()){
                    i.putExtra("texto1", editText1.text.toString())
                    setResult(Activity.RESULT_OK, i)
                    finish()
                }else{
                    Toast.makeText(applicationContext, R.string.invalido, Toast.LENGTH_SHORT).show()
                }
            }

            cancelar.setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }
}