package com.pintu.youtube_sdk_integration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_play_video_screen.*


class PlayVideoScreen : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_play_video_screen)



        val VIDEOS_ID = intent.getStringExtra("video_id")
        val YOUTUBE_API_KEY = intent.getStringExtra("youtube_api_key")

        Log.d("msg--", VIDEOS_ID + "   " + YOUTUBE_API_KEY)

        initUI(VIDEOS_ID!!, YOUTUBE_API_KEY!!)

    }

    private fun initUI(video_id: String, api_key: String) {
        Log.d("msg--", video_id + "   " + api_key)
        ytPlayer.initialize(api_key,
            object : YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    p2: Boolean
                ) {
                    // Start buffering
//                    if(!p2){
                        player?.loadVideo(video_id)
//                    }
                    player?.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                    // player?.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                    //player?.setShowFullscreenButton(true)
                    //player?.setFullscreen(true);
                    //player?.play()


                    player?.setPlayerStateChangeListener(object :
                        YouTubePlayer.PlayerStateChangeListener {
                        override fun onLoading() {}
                        override fun onLoaded(p0: String?) {
                        }

                        override fun onAdStarted() {}
                        override fun onVideoStarted() {}
                        override fun onVideoEnded() {
                            Log.d("msg--", player!!.currentTimeMillis.toString())
                            Log.d("msg--", player!!.durationMillis.toString())
                            player?.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                            startActivity(Intent(this@PlayVideoScreen,MainActivity::class.java))
                            finish()
                            player.seekToMillis(0)
                            player.pause()

                        }

                        override fun onError(p0: YouTubePlayer.ErrorReason?) {}

                    })

                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(this@PlayVideoScreen, "Video player Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            })






    }
//
//    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
//        override fun onAdStarted() {
//            Toast.makeText(this@PlayVideoScreen, "Click Ad now, make the video creator rich!", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onLoading() {
//        }
//
//        override fun onVideoStarted() {
//            Toast.makeText(this@PlayVideoScreen, "Video has started", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onLoaded(p0: String?) {
//        }
//
//        override fun onVideoEnded() {
//            Toast.makeText(this@PlayVideoScreen, "Congratulations! You've completed another video.", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onError(p0: YouTubePlayer.ErrorReason?) {
//        }
//    }
//
//    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
//        override fun onSeekTo(p0: Int) {
//        }
//
//        override fun onBuffering(p0: Boolean) {
//        }
//
//        override fun onPlaying() {
//            Log.d("msg--","playing")
//            Toast.makeText(this@PlayVideoScreen, "Good, video is playing ok", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onStopped() {
//            Log.d("msg--","stop")
//            Toast.makeText(this@PlayVideoScreen, "Video has stopped", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onPaused() {
//            Toast.makeText(this@PlayVideoScreen, "Video has paused", Toast.LENGTH_SHORT).show()
//            Log.d("msg--","paused")
//        }
//    }


}