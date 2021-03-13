package curso.tads.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import curso.tads.minhaprova.databinding.ActivityAcao2Binding

class Acao2Activity : AppCompatActivity() {
    lateinit var binding: ActivityAcao2Binding
    val db = LivroBDOpener(this)
    var livro = Livro()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)
        val i = Intent(this, MainActivity::class.java)
        binding.apply {
            salvar.setOnClickListener {
                if(nome.text.isEmpty() || autor.text.isEmpty() || ano.text.isEmpty()){
                    Toast.makeText(applicationContext, R.string.invalido, Toast.LENGTH_SHORT).show()
                }else{
                    livro.nome = nome.text.toString()
                    livro.autor = autor.text.toString()
                    livro.ano = Integer.parseInt(ano.text.toString())
                    livro.nota = ratingBar2.rating
                    db.insert(livro)
                    setResult(Activity.RESULT_OK, i)
                    finish()
                }
            }
            cancelar.setOnClickListener {
                Toast.makeText(applicationContext, R.string.msgCancelar, Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        
    }
}