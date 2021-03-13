package curso.tads.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import curso.tads.minhaprova.databinding.ActivityAcao3Binding

class Acao3Activity : AppCompatActivity() {
    lateinit var binding: ActivityAcao3Binding
    val db = LivroBDOpener(this)
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao3)
        var livros = listarLivros()
        binding.apply {
            nome.text = livros[count].nome
            autor.text = livros[count].autor
            ano.text = livros[count].ano.toString()
            nota.text = livros[count].nota.toString()

            proximo.setOnClickListener {
                if(count+1 > livros.size-1){
                    Toast.makeText(applicationContext, R.string.invalido, Toast.LENGTH_SHORT).show()
                }else{
                    count++
                    nome.text = livros[count].nome
                    autor.text = livros[count].autor
                    ano.text = livros[count].ano.toString()
                    nota.text = livros[count].nota.toString()
                }
            }

            anterior.setOnClickListener {
                if(count == 0){
                    Toast.makeText(applicationContext, R.string.invalido, Toast.LENGTH_SHORT).show()
                }else{
                    count--
                    nome.text = livros[count].nome
                    autor.text = livros[count].autor
                    ano.text = livros[count].ano.toString()
                    nota.text = livros[count].nota.toString()
                }
            }
        }
    }

    fun listarLivros():List<Livro>{
        return db.findAll()
    }
}