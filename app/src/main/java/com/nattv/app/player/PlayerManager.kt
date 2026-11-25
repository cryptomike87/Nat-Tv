package com.nattv.app.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

/** Owns one ExoPlayer instance. The owner must call release() when its lifecycle ends. */
class PlayerManager(context: Context) {
    private var released = false
    val player: ExoPlayer = ExoPlayer.Builder(context.applicationContext).build()

    fun play(streamUrl: String) {
        checkNotReleased()
        require(streamUrl.isNotBlank()) { "Stream URL must not be blank." }
        player.setMediaItem(MediaItem.fromUri(streamUrl.trim()))
        player.prepare()
        player.playWhenReady = true
    }

    fun pause() {
        checkNotReleased()
        player.pause()
    }

    fun stop() {
        if (released) return
        player.stop()
        player.clearMediaItems()
    }

    fun release() {
        if (released) return
        player.stop()
        player.clearMediaItems()
        player.release()
        released = true
    }

    private fun checkNotReleased() {
        check(!released) { "PlayerManager has already been released." }
    }
}