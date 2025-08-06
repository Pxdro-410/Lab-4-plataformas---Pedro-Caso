package com.example.laboratorio4_pc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio4_pc.ui.theme.Laboratorio4PCTheme
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio4PCTheme {

                val listaMascotas = listOf(
                    Mascota("Maximus", "Labrador", R.drawable.perro_labrador),
                    Mascota("Scott", "Pug", R.drawable.pug),
                    Mascota("Nebula", "Azul ruso", R.drawable.gatoazulruso),
                    Mascota("Canela", "Tuxedo", R.drawable.gato_tuxedo),
                    Mascota("Orion", "Loro", R.drawable.ave_loro),
                    Mascota("Kiwi", "Canario", R.drawable.ave_canario),
                    Mascota("Nemo", "Payaso", R.drawable.pez_payaso),
                    Mascota("Rainbow", "Guppy", R.drawable.pez_guppy)
                )

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListadoMascotas(
                        mascotas = listaMascotas,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// se utiliza un composable, para emitir elementos en la interfaz
@Composable
fun ListadoMascotas(mascotas: List<Mascota>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(mascotas) {
            // se utiliza un lambda para ahorrar codigo
            mascota -> TarjetaMascota(pet = mascota)
        }
    }
}

// es una funcion composable para la tarjeta individual de la mascota
@Composable
fun TarjetaMascota(pet: Mascota) {
    // Se utiliza remember para el estado del boton
    var adoptado by remember { mutableStateOf(false) }

    // Parametros de la tarjeta de cada mascota
    Card(
        modifier = Modifier
            .padding(12.dp),

    ) {
        // parametros para las filas
        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // parametros para la foto
            Image(
                painter = painterResource(id = pet.foto),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                // se utiliza el ContentScale.Crop para cortar la imagen y llenar la figura
                contentScale = ContentScale.Crop
            )
            // espaciado entre cada componente
            Spacer(modifier = Modifier.width(16.dp))

            // parametro de la columna para ordenar correctamente de forma vertical los componentes
            Column(
                // se utilizo weight para completar los espacios vacios de la columna
                modifier = Modifier.weight(1f)
            ) {
                // primer texto que se muestra, el nombre de la mascota
                Text(
                    text = pet.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp
                )

                // segundo texto que se muestra, la raza en color grisaseo
                Text(
                    text = pet.raza,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            // parametros del boton para realizar la accion de adoptar
            Button(
                // se crea un lambda que cambia el valor de la variable en remember
                onClick = { adoptado = !adoptado },
                // se personaliza el color del boton dependiendo del estado
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (adoptado)
                        Color.Green
                    else
                        Color.Red
                )
            )
            {
                // se muestra el texto que se
                Text(
                    text = if (adoptado) "¡Adoptado!❤️" else "Adoptar")
            }
        }
    }
}