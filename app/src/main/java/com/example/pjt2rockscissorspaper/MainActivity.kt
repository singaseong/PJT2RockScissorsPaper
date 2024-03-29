package com.example.pjt2rockscissorspaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pjt2rockscissorspaper.ui.theme.PJT2RockScissorsPaperTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PJT2RockScissorsPaperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PJT2RockScissorsPaper()
                }
            }
        }
    }
}



@Preview(showBackground = false)
@Composable
fun PJT2RockScissorsPaper() {


    var playerChoice by remember { mutableStateOf("") }
    var computerChoice by remember { mutableStateOf("") }
    val rockImage = R.drawable.icons8_rock_hand_48
    val paperImage = R.drawable.icons8_paper_hand_48
    val scissorsImage = R.drawable.icons8_hand_scissors_48
    var chosenImagePlayer : Int by remember { mutableStateOf(0)}
    var chosenImageComputer : Int by remember { mutableStateOf(0)}
    var winner by remember {mutableStateOf("")}
    var winCountPlayer by remember {mutableStateOf(0)}
    var winCountComputer by remember {mutableStateOf(0)}
    var totalGameCount by remember {mutableStateOf(0)}

    //function to randomly choose computer's choice
    fun comChoice() {
        val randomNumber = (1..3).random()

        when (randomNumber) {
            1 -> {
                computerChoice = "Rock"
                chosenImageComputer = rockImage
            }

            2 -> {
                computerChoice = "Paper"
                chosenImageComputer = paperImage
            }

            3 -> {
                computerChoice = "Scissors"
                chosenImageComputer = scissorsImage
            }
        }
    }

    //function to determine winner and update win counts
    fun winnerCheck () {
        winner = when {
            playerChoice == computerChoice -> "Tie"
            playerChoice == "Rock" && computerChoice == "Scissors" -> "Player"
            playerChoice == "Paper" && computerChoice == "Rock" -> "Player"
            playerChoice == "Scissors" && computerChoice == "Paper" -> "Player"
            else -> "Computer"
        }

        if (winner =="Player") {
            winCountPlayer += 1
        } else if (winner =="Computer"){
            winCountComputer += 1
        } else {

        }
    }

    //Composable function to display player's option choice image.
    @Composable
    fun playerChoiceImage (id:Int, choice:String) {
        Image(
            painter = painterResource(id = id),
            contentDescription = choice,
            modifier = Modifier
                .size(48.dp)
                .clickable(
                    onClick = {
                        playerChoice = choice
                        winner = ""
                    })
        )
    }

    //Composable function to display selected choice image with size changeable
    @Composable
    fun selectedImage(id:Int, size:Dp){
        Image(
            painter = painterResource(id =id),
            contentDescription = "",
            modifier = Modifier
                .size(size)
        )
    }
    //Composable function to display player's result & win count
    @Composable
    fun showPlayer() {

            Text(text = "Player",fontSize = 24.sp)
            Text(text = "${playerChoice}",fontSize = 18.sp)
            selectedImage(chosenImagePlayer,48.dp)
            Text(text = "Win : ${winCountPlayer}",fontSize = 18.sp)

    }
    //Composable function to display Computer's result & win count
    @Composable
    fun showComputer(){
            Text(text = "Computer",fontSize = 24.sp)
            Text(text = "${computerChoice}",fontSize = 18.sp)
            selectedImage(chosenImageComputer,48.dp)
            Text(text = "Win : ${winCountComputer}",fontSize = 18.sp)

    }

    //Composable function to display player vs computer result section
    @Composable
    fun showPlayerVSComputer(){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(2.5f)
            ) {
                showPlayer()
            }

            Text(
                text = "VS\nTie\n${totalGameCount - winCountComputer - winCountPlayer}",
                fontSize = 18.sp,
                modifier = Modifier.weight(1.2f),
                textAlign = TextAlign.Center
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(2.5f)
            ) {
                showComputer()
            }


        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //title
        Text(text = "가위바위보 게임", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        //Select Player's Choice
        Text(
            text = "Choose one",
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Rock Paper Scissor selection
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            //player Choice options
            playerChoiceImage(rockImage, "Rock")
            playerChoiceImage(paperImage, "Paper")
            playerChoiceImage(scissorsImage, "Scissors")

        }
        Spacer(modifier = Modifier.height(16.dp))

        //showing Player's Choice
        if (playerChoice !=""){
            Text(
                text = "Player's Choice : ${playerChoice}",
                fontSize = 28.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            when (playerChoice) {
                "Rock" -> {
                    chosenImagePlayer = rockImage
                    selectedImage(chosenImagePlayer, 128.dp)

                }
                "Paper" -> {
                    chosenImagePlayer = paperImage
                    selectedImage(chosenImagePlayer, 128.dp)

                }
                "Scissors" -> {
                    chosenImagePlayer = scissorsImage
                    selectedImage(chosenImagePlayer,128.dp)

                }
            }
        }else {

        }

        Spacer(modifier = Modifier.height(16.dp))

        //Game Start button
        if (playerChoice !="") {
            Button(
                onClick = {
                    comChoice()
                    winnerCheck ()
                    totalGameCount++
                }

            ){
                Text(text = "Game Start", fontSize = 28.sp)
            }
        }else {
            //no show button
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Show the winner
        if(winner !="" && winner !="Tie") {
            Text(text = "Total Game : ${totalGameCount}ea",fontSize = 28.sp)
            Text(text = "Winner : ${winner}",fontSize = 28.sp)
            showPlayerVSComputer()

        }else if(winner =="Tie") {
            Text(text = "Total Game : ${totalGameCount}ea",fontSize = 28.sp)
            Text(text = "Tie",fontSize = 28.sp)
            showPlayerVSComputer()

        }else {
            //nothing
        }

        //RESET
        Spacer(modifier = Modifier.weight(1f))
        Box(contentAlignment = Alignment.BottomCenter) {
            Button(
                onClick = {
                    playerChoice = ""
                    computerChoice = ""
                    winner = ""
                    winCountPlayer =0
                    winCountComputer=0
                    totalGameCount=0
                }){
                Text(text = "RESET", fontSize = 16.sp)
            }
        }

    }
}



