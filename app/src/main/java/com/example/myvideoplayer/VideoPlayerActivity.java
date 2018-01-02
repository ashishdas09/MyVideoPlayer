package com.example.myvideoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.myvideoplayer.videoplayer.MyVideoPlayer;
import com.example.myvideoplayer.videoplayer.MyVideoPlayerListener;

public class VideoPlayerActivity extends AppCompatActivity implements MyVideoPlayerListener
{
	private final static String LOG_TAG = "VideoPlayerActivity";

	private MyVideoPlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_player);

		player = (MyVideoPlayer) findViewById(R.id.player);
		assert player != null;
		player.setCallback(this);
		player.setAutoPlay(true);


		String url = getIntent().getExtras().getString("uri", "");
		player.setSource(Uri.parse(url));
		// All further configuration is done from the XML layout.
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		player.pause();
	}

	@Override
	public void onStarted(MyVideoPlayer player)
	{
	}

	@Override
	public void onPaused(MyVideoPlayer player)
	{
	}

	@Override
	public void onPreparing(MyVideoPlayer player)
	{
		Log.d(LOG_TAG, "onPreparing()");
	}

	@Override
	public void onPrepared(MyVideoPlayer player)
	{
		Log.d(LOG_TAG, "onPrepared()");
	}

	@Override
	public void onBuffering(int percent)
	{
		Log.d(LOG_TAG, "onBuffering(): " + percent + "%");
	}

	@Override
	public void onError(MyVideoPlayer player, Exception e)
	{
		Log.d(LOG_TAG, "onError(): " + e.getMessage());
		Toast.makeText(this, "onError(): " + e.getMessage(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCompletion(MyVideoPlayer player)
	{
		Log.d(LOG_TAG, "onCompletion()");
	}

	@Override
	public void onRetry(MyVideoPlayer player, Uri source)
	{
		Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSubmit(MyVideoPlayer player, Uri source)
	{
		Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClickVideoFrame(MyVideoPlayer player)
	{
		Toast.makeText(this, "Click video frame.", Toast.LENGTH_SHORT).show();
	}
}
