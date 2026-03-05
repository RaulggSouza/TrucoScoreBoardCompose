package br.edu.ifsp.scl.sc3045153.trucoscoreboardcompose.ui.composables

import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScoreButtons(
    modifier: Modifier = Modifier,
    teamPoints: Int,
    enabled: Boolean,
    teamName: String,
    incrementTruco: (Boolean)-> Unit,
    scoreToWin: Int,
    context: Context) {
    Column {
        Text(
            text = teamName,
            textAlign = TextAlign.Center,
            modifier = modifier,
            fontSize = 40.sp
        )

        if (!enabled) {
            Button(onClick = {
                incrementTruco(false)
            }, modifier = modifier) {
                Text(text = "+1", fontSize = 20.sp)
            }

            if (teamPoints < 11) {
                Button(onClick = {
                    incrementTruco(true)
                }, modifier = modifier) {
                    Text(text = "+3", fontSize = 20.sp)
                }
            } else {
                Toast.makeText(
                    context,
                    "Mão de onze de $teamName",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                context,
                "Parabéns! Time $teamName ganhou!",
                Toast.LENGTH_SHORT
            ).show()
        }
        Text(
            text = teamPoints.toString(),
            textAlign = TextAlign.Center,
            modifier = modifier,
            fontSize = 40.sp,
            color = if (teamPoints == 11) {
                Color.Red
            } else if (teamPoints >= scoreToWin) {
                Color.Green
            } else {
                MaterialTheme.colorScheme.onBackground
            }
        )
    }
}

@Preview
@Composable
fun ScoreButtonsPreview() {
    Column {
        ScoreButtons(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 5.dp),
            teamPoints = 0,
            incrementTruco = {},
            scoreToWin = 12,
            context = LocalContext.current,
            enabled = true,
            teamName = "teste"
        )
    }
}