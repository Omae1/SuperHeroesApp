package com.example.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.model.HeroesRepository.heroes
import com.example.superheroesapp.ui.theme.SuperHeroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroApp()

                }
            }
        }
    }
}

 @Composable
fun HeroApp(){
     Scaffold (
         topBar = {
             SuperHeroesTopBar()
         }
     ){it  ->
         LazyColumn(contentPadding = it) {
             items(heroes) {
                 HeroItems(hero = it,
                     modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp))


             }
         }
     }

}


@Composable
fun HeroItems( hero: Hero, modifier: Modifier = Modifier){

    Card(modifier = modifier) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){
            HeroInformation(
                name = hero.nameRes,
                description = hero.descriptionRes,
                modifier = Modifier
                    .weight(0.5f)


            )
            Spacer(modifier = Modifier.width(16.dp))
            HeroImage(
                heroIcon = hero.imageRes,
                modifier = Modifier
                    .size(72.dp)
            )
        }




    }

}

@Composable
fun HeroInformation(
    @StringRes name: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .size(72.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
        ) {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.displaySmall)

        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.bodyLarge)
    }

}

@Composable
fun HeroImage(
    @DrawableRes heroIcon: Int,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier
        .fillMaxSize()
        ){
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(72.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = heroIcon),
            contentDescription = null,
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesTopBar(modifier: Modifier= Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(topEnd = 48.dp, bottomStart = 48.dp))
                    .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    ,
                horizontalArrangement = Arrangement.Center){
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }

        })
}

@Preview
@Composable
fun HeroesPreview(){
    SuperHeroesAppTheme(darkTheme = false) {
        HeroApp()

    }
}