package project.viewmodel.pertemuan5

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import project.viewmodel.pertemuan5.data.dataform

class cobaviewmodel : ViewModel() {
    var namaUsr: String by mutableStateOf("")
        private set
    var noTelp: String by mutableStateOf("")
        private set
    var namaAlmt: String by mutableStateOf("")
        private set
    var jenisKl: String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(dataform())
    val uiState: StateFlow<dataform> = _uiState.asStateFlow()

    fun insertData(nm: String, tlp: String, almt: String, jk: String) {
        namaUsr = nm;
        noTelp = tlp;
        namaAlmt = almt;
        jenisKl = jk;
    }

    fun setJenis(pilihJK: String){
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }

}