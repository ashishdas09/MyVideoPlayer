package com.example.myvideoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ChoiceVideoURIFragment extends Fragment
{
	public interface OnChoiceVideoURIListener
	{
		void onChoiceVideoURI(String uri);
	}

	private OnChoiceVideoURIListener mListener;
	private static final int REQUEST_TAKE_GALLERY_VIDEO = 100;

	public static ChoiceVideoURIFragment newInstance()
	{
		ChoiceVideoURIFragment fragment = new ChoiceVideoURIFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		final View rootView = inflater.inflate(R.layout.fragment_choice_video_uri, container, false);
		rootView.findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				EditText editText = (EditText) rootView.findViewById(R.id.et_uri);
				playVideo(editText.getText().toString());
			}
		});

		rootView.findViewById(R.id.btn_video_file).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				Intent intent = new Intent();
				intent.setType("video/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);
			}
		});
		return rootView;
	}

	public void playVideo(String uri)
	{
		if (mListener != null)
		{
			mListener.onChoiceVideoURI(uri);
		}
	}

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
		if (context instanceof OnChoiceVideoURIListener)
		{
			mListener = (OnChoiceVideoURIListener) context;
		}
		else
		{
			throw new RuntimeException(context.toString()
					                           + " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach()
	{
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onActivityResult(final int requestCode, final int resultCode, final Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK)
		{
			if (requestCode == REQUEST_TAKE_GALLERY_VIDEO)
			{
				Uri selectedVideoUri = data.getData();

				playVideo(selectedVideoUri.toString() + "");
			}
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();

		ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
		if(!actionBar.isShowing())
		{
			actionBar.show();
		}
	}
}
