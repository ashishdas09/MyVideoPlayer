package com.example.myvideoplayer.videoplayer;

import android.net.Uri;

public interface MyVideoPlayerListener
{
	void onStarted(MyVideoPlayer player);

	void onPaused(MyVideoPlayer player);

	void onPreparing(MyVideoPlayer player);

	void onPrepared(MyVideoPlayer player);

	void onBuffering(int percent);

	void onError(MyVideoPlayer player, Exception e);

	void onCompletion(MyVideoPlayer player);

	void onRetry(MyVideoPlayer player, Uri source);

	void onSubmit(MyVideoPlayer player, Uri source);

	void onClickVideoFrame(MyVideoPlayer player);
}
