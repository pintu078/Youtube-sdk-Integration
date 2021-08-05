package com.pintu.youtube_sdk_integration

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :AppCompatActivity() {

    companion object {
        val VIDEO_ID: String = "GC9tC0Nw07k";
        val YOUTUBE_API_KEY: String = "AIzaSyCk5PA2DfmCCF7nI9YbZ0PfB2I8VyYg4KQ"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        btnPlay.setOnClickListener(View.OnClickListener { v ->
 //          youtubePlayer.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
            intent = Intent(this, PlayVideoScreen::class.java)
            intent.putExtra("video_id", VIDEO_ID)
            intent.putExtra("youtube_api_key",YOUTUBE_API_KEY)
            startActivity(intent)
            finish()
        })
    }
}