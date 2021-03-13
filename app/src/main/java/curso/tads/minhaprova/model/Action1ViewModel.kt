package curso.tads.minhaprova.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Action1ViewModel: ViewModel() {
    var texto = MutableLiveData<String>("")
}