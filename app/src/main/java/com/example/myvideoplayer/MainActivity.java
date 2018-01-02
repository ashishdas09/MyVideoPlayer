package com.example.myvideoplayer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

	private static final int REQUEST_TAKE_GALLERY_VIDEO = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				EditText editText = (EditText) findViewById(R.id.et_uri);
				playVideo(editText.getText().toString());
			}
		});

		findViewById(R.id.btn_video_file).setOnClickListener(new View.OnClickListener()
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
	}


	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK)
		{
			if (requestCode == REQUEST_TAKE_GALLERY_VIDEO)
			{
				Uri selectedVideoUri = data.getData();

				playVideo(selectedVideoUri.toString()+"");
			}
		}
	}

	// UPDATED!
	public String getPath(Uri uri)
	{
		String[] projection = {MediaStore.Video.Media.DATA};
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		if (cursor != null)
		{
			// HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
			// THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		}
		else
		{
			return null;
		}
	}

	private void playVideo(String uri)
	{
		Intent intent = new Intent(this, VideoPlayerActivity.class);
		intent.putExtra("uri", uri);
		startActivity(intent);
	}
}
