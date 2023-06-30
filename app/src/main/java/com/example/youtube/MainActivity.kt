package com.example.youtube

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.youtube.ui.theme.YoutubeTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Youtube",

                                    //style = MaterialTheme.typography.h6,
                                    color = Color.Red // Set the color to red
                                )
                            },
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Sharp.AccountBox,
                                    contentDescription = "YouTube Icon"
                                )
                            }
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            item {
                                Spacer(modifier = Modifier.height(59.dp))
                                YoutubeScreen(videoId = "SYc922ntnKM")
                                Spacer(modifier = Modifier.height(60.dp))
                                YoutubeScreen(videoId = "SqB0lUcqFbA")
                                Spacer(modifier = Modifier.height(60.dp))
                                YoutubeScreen(videoId = "O8gCqSmtkrM")
                                Spacer(modifier = Modifier.height(60.dp))
                                YoutubeScreen(videoId = "iU_iWa9LL_s")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun YoutubeScreen(
    videoId: String
) {
    AndroidView(factory = {
        val view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.pause() // Pause the video by default
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        )
        view
    })
}