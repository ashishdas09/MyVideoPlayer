package com.example.myvideoplayer;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements ChoiceVideoURIFragment.OnChoiceVideoURIListener
{
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mFragmentManager = getSupportFragmentManager();
		showFragment(ChoiceVideoURIFragment.newInstance());
	}

	@Override
	public void onChoiceVideoURI(final String uri)
	{
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		showFragment(VideoPlayerFragment.newInstance(uri));
	}

	@Override
	public void onBackPressed()
	{
		if (mFragmentManager.getBackStackEntryCount() == 1)
		{
			finish();
			return;
		}

		if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
		{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		super.onBackPressed();
	}

	private void showFragment(Fragment fragment)
	{
		FragmentTransaction ft = mFragmentManager.beginTransaction();
		ft.replace(R.id.fl_container, fragment);
		ft.addToBackStack(null);
		ft.commit();
	}
}
