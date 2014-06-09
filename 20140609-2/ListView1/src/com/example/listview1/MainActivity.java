package com.example.listview1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
    	
    	private ListView listView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
			listView = (ListView) rootView.findViewById(R.id.listView1);
			

			// setDataByArrayAdapter();
 			//setDataBySimpleAdapter();
			setDataBySimpleAdapterAndMyLayout();
			
 			return rootView;
 		}
 
		private void setDataBySimpleAdapterAndMyLayout() {
	 
	 			int[] images = new int[] { R.drawable.image1, R.drawable.image2,
	 					R.drawable.image3, R.drawable.image4, R.drawable.image5,
	 					R.drawable.image6 };
	 			String text[] = new String[] { "This is image1: honinbo-shusakus-185th-birthday",
	 					"This is image2: Denmark National Day 2014", 
	 					"This is image3: alejandro-obregons-93rd-birthday",
	 					"This is image4: julija-beniuseviciute-zymantienes-169th-birthday", 
	 					"This is image5: italian-republic-day-2014", 
	 					"This is image6: dragon-boat-festival-2014"
	 					};
	 
	 			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	 
	 			for (int i = 0; i < images.length; i++) {
	 				Map<String, Object> item = new HashMap<String, Object>();
	 				item.put("image", images[i]);
	 				item.put("text", text[i]);
	 				data.add(item);
	 			}
	 
	 			String[] from = new String[] { "image", "text" };
	 			int[] to = new int[] { R.id.imageView1, R.id.textView1 };
	 
	 			SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
	 					R.layout.listview_item, from, to);
	 			listView.setAdapter(adapter);
	 	}
        
        
 		private void setDataBySimpleAdapter() {
 
 			String name[] = new String[] { "kuo", "chen" };
 			String sex[] = new String[] { "M", "M" };
 
 			List<Map<String, String>> data = new ArrayList<Map<String, String>>();
 
 			for (int i = 0; i < name.length; i++) {
 				Map<String, String> item = new HashMap<String, String>();
 				item.put("main", name[i]);
 				item.put("sub", sex[i]);
 				data.add(item);
 			}
 
 			String[] from = new String[] { "main", "sub" };
 			int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
 
 			SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
 					android.R.layout.simple_list_item_2, from, to);
 			listView.setAdapter(adapter);
 		}
 
 		private void setDataByArrayAdapter() {
			String[] data = new String[] { "1", "2", "3", "4", "5", "6", "7",
					"8", "9", "10", "11", "12", "13" };
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_list_item_1, data);
			listView.setAdapter(adapter);
            
        }
    }

}
