package project.viewmodel.pertemuan5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import project.viewmodel.pertemuan5.data.dataform
import project.viewmodel.pertemuan5.data.datasource.jenis
import project.viewmodel.pertemuan5.data.datasource.status
import project.viewmodel.pertemuan5.ui.theme.ViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        options.forEach{item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

@Composable
fun SelectStatus(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        options.forEach{item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}


@Composable
fun TextHasil(namanya: String, telponnya: String, emailnya: String, alamatnya: String, jenisnya: String, statusnya: String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Status : " + statusnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Email : " + emailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun TampilForm(cobaviewmodel: cobaviewmodel = viewModel()) {

    var textNama by remember { mutableStateOf("") }
    var textTlp by remember { mutableStateOf("") }
    var textemail by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }

    val context = LocalContext.current
    val dataForm: dataform
    val uiState by cobaviewmodel.uiState.collectAsState()
    dataForm = uiState

    Text(
        text = "Create Your Account",
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )

    OutlinedTextField(
        value = textNama,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Username")},
        onValueChange = {
            textNama = it
        }
    )
    OutlinedTextField(
        value = textTlp,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Telepon")},
        onValueChange = {
            textTlp = it
        }
    )
    OutlinedTextField(
        value = textemail,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Email")},
        onValueChange = {
            textemail = it
        }
    )
    SelectJK(
        options = jenis.map { id -> context.resources.getString(id) },
        onSelectionChanged = { cobaviewmodel.setJenis(it) })
    SelectStatus(
        options = status.map { id -> context.resources.getString(id) },
        onSelectionChanged = { cobaviewmodel.setStatus(it) })
    OutlinedTextField(
        value = textAlamat,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Alamat")},
        onValueChange = {
            textAlamat = it
        }
    )
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            cobaviewmodel.insertData(textNama, textTlp, textemail, textAlamat, dataForm.sex, dataForm.stts)
        }
    ){
        Text(
            text = stringResource(R.string.submit),
            fontSize = 16.sp
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    TextHasil(
        jenisnya = cobaviewmodel.jenisKl,
        statusnya = cobaviewmodel.SelectStatus,
        alamatnya = cobaviewmodel.namaAlmt,
        emailnya = cobaviewmodel.namaemail,
        namanya = cobaviewmodel.namaUsr,
        telponnya = cobaviewmodel.noTelp,
    )
}

@Composable
fun TampilLayout(
    modifier: Modifier = Modifier
){
    val cardElevation = 1.dp

    Card (
        modifier = modifier,
        elevation = cardElevation
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ){
            TampilForm()
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelTheme {
        TampilLayout()
    }
}