package com.example.takephoto;

import java.io.ByteArrayOutputStream;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;

public class MainActivity extends ActionBarActivity {
	
	private static final int REQUEST_CODE_TAKE_PHOTO = 1;
	
	public static ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Parse.initialize(this, "oaa1iymryUrEgA6wDnfdVtCy9rmeuEL6cMnluXHG", "UQb8tQRuP4gxMrp4L54Xe7YhC4UXh3DBQi6jsZeE");
        
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_take_photo) {
			Log.d("debug", "action take photo");
			
			Intent intent = new Intent();
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
		}
        return super.onOptionsItemSelected(item);
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);

		if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
			Log.d("debug", "onActivityResult, requestCode=" + requestCode
					+ ", resultCode=" + resultCode);
			Toast.makeText(this, "from camera", Toast.LENGTH_SHORT).show();
			

			if (resultCode == RESULT_OK) {
 				Bitmap bitmap = intent.getParcelableExtra("data");
 				saveToParse(bitmap);
 				imageView.setImageBitmap(bitmap);
 			}
		}
	}
    

	private void saveToParse(Bitmap bitmap) {
 		ByteArrayOutputStream baos = new ByteArrayOutputStream();
 		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
 		byte[] bytes = baos.toByteArray();
 		final ParseFile file = new ParseFile("photo.png", bytes);
 		file.saveInBackground(new SaveCallback() {
 			
 			@Override
 			public void done(ParseException e) {
 				Log.d("debug", file.getUrl());
 			}
 		});
 	}
	

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            imageView = (ImageView) rootView.findViewById(R.id.imageView1);
            
            return rootView;
        }
    }

}
