package com.vinova.smart.music;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;



public class HomeAcitivity extends Activity implements OnClickListener, OnLongClickListener 
{
	//int windowWidth = getWindowManager().getDefaultDisplay().getWidth();
	//int windowHeight = getWindowManager().getDefaultDisplay().getHeight();

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
        songsMap.setOnLongClickListener(this);
        ImageButton albumsMap = (ImageButton) findViewById(R.id.albumsmap);
        albumsMap.setOnClickListener(this);
        albumsMap.setOnLongClickListener(this);
        ImageButton artistsMap = (ImageButton) findViewById(R.id.artistsmap);
        artistsMap.setOnClickListener(this);
        artistsMap.setOnLongClickListener(this);
        ImageButton genresMap = (ImageButton) findViewById(R.id.genresmap);
        genresMap.setOnClickListener(this);
        genresMap.setOnLongClickListener(this);
        myDragEventListener mAlbumDragListener = new myDragEventListener();
        albumsMap.setOnDragListener(mAlbumDragListener);
		myDragEventListener mSongDragListener = new myDragEventListener();
		songsMap.setOnDragListener(mSongDragListener);
		myDragEventListener mArtistDragListener = new myDragEventListener();
		artistsMap.setOnDragListener(mArtistDragListener);
		myDragEventListener mGenreDragListener = new myDragEventListener();
		genresMap.setOnDragListener(mGenreDragListener);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }

	public void onClick(View arg0) 
	{
		
		
	}

	
	public boolean onLongClick(View v) 
	{
		// Drag and drop
		ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
		// this part is different from the android dev:
		String[] mimetype = new String[1];
		mimetype[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;
		
		ClipData dragData = new ClipData((CharSequence) v.getTag(), 
										mimetype, 
										item);
		View.DragShadowBuilder myShadow;
		
		switch(v.getId())
		{
		case R.id.albumsmap:
			myShadow = 
				new MyDragShadowBuilder (v, getResources().getDrawable(R.drawable.ic_launcher));
				
				break;
		case R.id.songsmap:
			myShadow = 
				new MyDragShadowBuilder (v, getResources().getDrawable(R.drawable.ic_launcher));
			
			break;
		case R.id.artistsmap:
			myShadow = 
				new MyDragShadowBuilder (v, getResources().getDrawable(R.drawable.ic_launcher));
			
			break;
		case R.id.genresmap:
			myShadow = 
				new MyDragShadowBuilder (v, getResources().getDrawable(R.drawable.ic_launcher));
			
			break;
		default:
			myShadow = null;
		}
		
		//---------------------------------------------------------------------------
		
		//v.setOnDragListener(mDragListener);
		v.startDrag(dragData,  // the data to be dragged
                myShadow,  // the drag shadow builder
                null,      // no need to use local data
                0          // flags (not currently used, set to 0)
				);		
		return false;
	}
    
}


// To create the shadow for the object
class MyDragShadowBuilder extends View.DragShadowBuilder {

    // The drag shadow image, defined as a drawable thing
    private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v, Drawable drawable) {
        	super(v);
        	shadow = drawable;
        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch)
        {
            // Defines local variables
            int width, height;

            // Sets the width of the shadow to half the width of the original View
        	
            width = getView().getWidth();

            // Sets the height of the shadow to half the height of the original View
            height = getView().getHeight();

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
            // Canvas that the system will provide. As a result, the drag shadow will fill the
            // Canvas.
            shadow.setBounds(0, 0, width, height);

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
            size.set(width, height);

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {

            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas);
        }
    }

class myDragEventListener implements OnDragListener {
    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    public boolean onDrag(View v, DragEvent event) {

        // Defines a variable to store the action type for the incoming event
        final int action = event.getAction();

        // Handles each of the expected events
        CharSequence dragData = null;
		switch(action) {

            case DragEvent.ACTION_DRAG_STARTED:

                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                    // As an example of what your application might do,
                    // applies a blue color tint to the View to indicate that it can accept
                    // data.
                    ((ImageView) v).setColorFilter(Color.BLUE);

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();
                    dragData = (CharSequence) v.getTag();
                    // returns true to indicate that the View can accept the dragged data.
                    return(true);

                    } else {
                    	
                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                    return(false);

                    }
                //break;

            case DragEvent.ACTION_DRAG_ENTERED:

                // Applies a green tint to the View. Return true; the return value is ignored.

                ((ImageView) v).setColorFilter(Color.GREEN);

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();

                return(true);

                //break;

            case DragEvent.ACTION_DRAG_LOCATION:

                // Ignore the event
            	switch(v.getId())
            	{
            	case R.id.albumsmap:
            		((ImageView) v).setColorFilter(Color.RED);
            		break;
            	case R.id.songsmap:
            		((ImageView) v).setColorFilter(Color.YELLOW);
            		break;
            	
            	case R.id.artistsmap:
            		((ImageView) v).setColorFilter(Color.WHITE);
            		break;
            	
            	case R.id.genresmap:
            		((ImageView) v).setColorFilter(Color.BLACK);
            		break;
            	
            	}
                    return(true);

                //break;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Re-sets the color tint to blue. Returns true; the return value is ignored.
                    //((ImageView) v).setColorFilter(Color.BLUE);

                    // Invalidate the view to force a redraw in the new tint
                    //v.invalidate();

                    return(true);

                //break;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    dragData = item.getText();

                    // Displays a message containing the dragged data.
                    //Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG);
                    
                    // Turns off any color tints
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw
                    v.invalidate();
                    
                    //
                    switch(v.getId())
                	{
                	case R.id.albumsmap:
                		((ImageView) v).setColorFilter(Color.RED);
                		if (dragData == "SONG_MAP_BUTTON")
                		{
                			((ImageView) v).setColorFilter(Color.YELLOW);
                			v.invalidate();
                		}
                		break;
                	case R.id.songsmap:
                		((ImageView) v).setColorFilter(Color.YELLOW);
                		break;
                	
                	case R.id.artistsmap:
                		((ImageView) v).setColorFilter(Color.WHITE);
                		break;
                	
                	case R.id.genresmap:
                		((ImageView) v).setColorFilter(Color.BLACK);
                		break;
                	
                	}
                        

                    // Returns true. DragEvent.getResult() will return true.
                    return(true);

                //break;

                case DragEvent.ACTION_DRAG_ENDED:

                    // Turns off any color tinting
                    ((ImageView) v).clearColorFilter();

                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (event.getResult()) {
                        //Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG);

                    } else {
                        //Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG);

                    };

                    // returns true; the value is ignored.
                    return(true);

                //break;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.");

                break;
        };
		return true;
    };

};

