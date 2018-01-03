package com.example.myvideoplayer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myvideoplayer.videoplayer.MyVideoPlayer;
import com.example.myvideoplayer.videoplayer.MyVideoPlayerListener;

public class VideoPlayerFragment extends Fragment implements MyVideoPlayerListener
{
	private final static String LOG_TAG = "VideoPlayerFragment";

	private final static String ARG_PARAM_URI = "param_uri";

	private Context mContext;
	private String mParamURI;
	private MyVideoPlayer mVideoPlayer;

	public VideoPlayerFragment()
	{
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param uri Video file uri string.
	 * @return A new instance of fragment VideoPlayerFragment.
	 */
	public static VideoPlayerFragment newInstance(String uri)
	{
		VideoPlayerFragment fragment = new VideoPlayerFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM_URI, uri);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
			mParamURI = getArguments().getString(ARG_PARAM_URI);
		}
		setVideoSource();
		mContext = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_video_player, container, false);

		mVideoPlayer = (MyVideoPlayer) view.findViewById(R.id.player);
		assert mVideoPlayer != null;
		mVideoPlayer.setPlayerListener(this);
		mVideoPlayer.setAutoPlay(true);
		setVideoSource();
		return view;
	}

	private void setVideoSource()
	{
		if (mVideoPlayer != null && mParamURI != null)
		{
			mVideoPlayer.setSource(Uri.parse(mParamURI));
		}
	}

	@Override
	public void onPause()
	{
		super.onPause();
		if (mVideoPlayer != null)
		{
			mVideoPlayer.pause();
		}
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
		Toast.makeText(mContext, "onError(): " + e.getMessage(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCompletion(MyVideoPlayer player)
	{
		Log.d(LOG_TAG, "onCompletion()");
	}

	@Override
	public void onRetry(MyVideoPlayer player, Uri source)
	{
		Toast.makeText(mContext, "Retry", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSubmit(MyVideoPlayer player, Uri source)
	{
		Toast.makeText(mContext, "Submit", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClickVideoFrame(MyVideoPlayer player)
	{
		Toast.makeText(mContext, "Click video frame.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onControlVisibility(final boolean visible)
	{
		ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
		if (visible)
		{
			actionBar.show();
		}
		else
		{
			actionBar.hide();
		}
	}
}
