package br.edu.ifsp.scl.sc3045153.trucoscoreboardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3045153.trucoscoreboardcompose.ui.composables.ScoreButtons
import br.edu.ifsp.scl.sc3045153.trucoscoreboardcompose.ui.theme.TrucoScoreBoardComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrucoScoreBoardComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TrucoScoreBoardPanel()
                }
            }
        }
    }
}

@Composable
fun TrucoScoreBoardPanel() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
        val modifier = Modifier
            .padding(horizontal = 5.dp)
        val scoreToWin = 12
        var teamAPoints by remember { mutableIntStateOf(0) }
        var teamBPoints by remember { mutableIntStateOf(0) }
        val gameFinished = teamAPoints >= scoreToWin || teamBPoints >= scoreToWin

        Text(
            text = "Truco Score Board",
            fontSize = 40.sp,
            modifier = modifier.padding(vertical = 20.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        val context = LocalContext.current
        Row (
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            val buttonModifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp).defaultMinSize(minWidth = 150.dp)
            ScoreButtons(
                modifier = buttonModifier,
                teamAPoints,
                scoreToWin = scoreToWin,
                enabled = gameFinished,
                teamName = "Nós",
                incrementTruco =
                    { incrementTruco: Boolean ->
                        if (incrementTruco) {
                            teamAPoints += 3
                        } else {
                            teamAPoints++
                        }
                    },
                context = context
            )

            ScoreButtons(
                modifier = buttonModifier,
                teamBPoints,
                scoreToWin = scoreToWin,
                enabled = gameFinished,
                teamName = "Eles",
                incrementTruco = {
                        incrementTruco: Boolean ->
                    if (incrementTruco) {
                        teamBPoints += 3
                    } else {
                        teamBPoints++
                    }
                },
                context = context
            )
        }

        Button(onClick = {
            teamAPoints = 0
            teamBPoints = 0
        }, modifier = modifier.fillMaxWidth()) {
            Text(text = "Reset", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrucoScoreBoardPreview() {
    TrucoScoreBoardPanel()
}