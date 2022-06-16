package com.example.bizcardapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.bizcardapp.ui.theme.BizCardAppTheme
import com.example.bizcardapp.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DefaultCard()
                }
            }
        }
    }
}

@Composable
fun DefaultCard(){
    val buttonClickState= remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            shape= RoundedCornerShape(corner = CornerSize(10.dp)),
            elevation = 6.dp,

            ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                CreateProfileImage(modifier = Modifier
                    .height(150.dp)
                    .width(150.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Divider()
                Spacer(modifier = Modifier.height(10.dp))
                CreateUserName()
                Spacer(modifier = Modifier.height(10.dp))
                AddSubmitButton {
                  //  Toast.makeText(this,"Data Submited",Toast.LENGTH_LONG).show()
                    Log.d("taggg","Data Submit $it")
                    buttonClickState.value = !buttonClickState.value
                }
                if (buttonClickState.value){
                    Content()
                }else{
                    Box() {

                    }
                }

            }
        }

    }


}


@Composable
fun CreateProfileImage(modifier: Modifier=Modifier){
    Surface(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 5.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "profile img",
            contentScale = ContentScale.Crop,

        )

    }
}


@Composable
fun CreateUserName(){
    Column() {
        Text(
            text = "Alex Hales",
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.h5
        )

        Text(
            text = "International Cricketer",
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle1
        )

        Text(
            text = "alexhales@cricket.com",
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle2
        )
    }

}

@Composable
fun AddSubmitButton(onSubmitClick:(String) -> Unit){

    Button(
        onClick = { onSubmitClick("hhh") }
    ) {
        Text(
            text = "Submit",
            style = MaterialTheme.typography.button
        )
    }
}

@Preview
@Composable
fun Content(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(2.dp,color = Color.LightGray)
        ) {
            ListContent(data= listOf<String>("Match 1","Match 2", "Match3","Match 4","Match 5","Match 6"))

        }
    }
}

@Composable
fun ListContent(data: List<String>) {
     LazyColumn(){
         items(data){ item ->
             CreateItemForList(item)
         }
     }
}

@Composable
fun CreateItemForList(item: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 6.dp,
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateProfileImage(
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
            )
            
            Spacer(modifier = Modifier.width(10.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
              ){
                Text(
                    text = item,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "A match to remember",
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }

    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardAppTheme {
        DefaultCard()
    }
}