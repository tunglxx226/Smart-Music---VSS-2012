package com.vinova.smart.music;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomeAcitivity extends Activity implements OnClickListener 
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialization(savedInstanceState);
    }
    
    public void initialization(Bundle savedInstanceState)
    {
    	ImageButton songsMap = (ImageButton) findViewById(R.id.songsmap);
        songsMap.setOnClickListener(this);
        ImageButton albumsMap = (ImageButton) findViewById(R.id.albumsmap);
        albumsMap.setOnClickListener(this);
        ImageButton artistsMap = (ImageButton) findViewById(R.id.artistsmap);
        artistsMap.setOnClickListener(this);
        ImageButton genresMap = (ImageButton) findViewById(R.id.genresmap);
        genresMap.setOnClickListener(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
