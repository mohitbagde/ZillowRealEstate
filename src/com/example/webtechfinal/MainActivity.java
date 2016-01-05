package com.example.webtechfinal;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.webtechfinal.MainActivity;

import android.support.v7.app.ActionBarActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	  public static final String TAG = null;
	  static JSONObject jObj = null;
	  static String json2 = "";
	  String address;
	  String charturl1;
	  EditText city;
	  TextView cityerr;
	  InputStream is = null;
	 // Iterator iter
	  ArrayList<String> keyarr;
	  String mycity;
	  String mystate;
	  String mystreet;
	  ArrayList<String> namearr;
	  TextView resp;
	  String result;
	  String returncode;
	  Spinner state;
	  TextView stateerr;
	  EditText street;
	  TextView streeterr;
	  Button submitbtn;
	  Button zillowbtn;
	  JSONObject json;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 submitbtn = (Button)findViewById(R.id.submitbtn);
		 zillowbtn = (Button)findViewById(R.id.zillowbtn);
		 street = (EditText)findViewById(R.id.address);
		 city = (EditText)findViewById(R.id.city);
		 state = (Spinner)findViewById(R.id.state);
		 streeterr = (TextView)findViewById(R.id.streeterr);
		 cityerr = (TextView)findViewById(R.id.cityerr);
		 stateerr = (TextView)findViewById(R.id.stateerr);
		 resp = (TextView)findViewById(R.id.resp);
		 submitbtn.setOnClickListener(new View.OnClickListener()
		    {
		      public void onClick(View paramView)
		      {
		    	          
		        if (street.getText().toString().length() != 0)
		        {
		          mystreet = street.getText().toString();
		          streeterr.setText("");
		        }
		        else streeterr.setText("This field is required");
		        if (city.getText().toString().length() != 0) 
		        {
			        mycity = city.getText().toString();
			        cityerr.setText("");
			    }
		        else cityerr.setText("This field is required");
		        if (state.getSelectedItem().toString().length() != 0)
		        {
		          mystate = state.getSelectedItem().toString();
		          stateerr.setText("");
		        }
		        else stateerr.setText("This field is required");
		    	
		        if((streeterr.getText().toString() == "") && (stateerr.getText().toString() == "") && (cityerr.getText().toString() == ""))
		        {
		        //	resp.setText("Now we can execture zillow func");
		        	new zillowJsonParse().execute();
		        }
		      //  else resp.setText("");
		       
		         
		         }
		      });
		      zillowbtn.setOnClickListener(new View.OnClickListener()
		      {
		        public void onClick(View v)
		        {
		          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.zillow.com/"));
		          startActivity(localIntent);
		        }
		      });
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
	
	public class zillowJsonParse extends AsyncTask<String, String, String> {
		 
        final String TAG = "AsyncTaskParseJson.java";
 
        // set your json string url here
        String yourJsonStringUrl = "http://demo.codeofaninja.com/tutorials/json-example-with-php/index.php";
 
        // contacts JSONArray
        JSONArray dataJsonArr = null;
 
        @Override
        protected void onPreExecute() {}
 
        @Override
        protected String doInBackground(String... arg0) {
 
            // instantiate our json parser
			JSONParser jParser = new JSONParser();
            String myjsonstr = "null";
			try {
				myjsonstr = "http://mohitbagde.elasticbeanstalk.com?streetInput="+URLEncoder.encode(mystreet,"UTF-8")+"&cityInput="+URLEncoder.encode(mycity,"UTF-8")+"&stateInput="+URLEncoder.encode(mystate,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String myjsonstr = "http://mohitbagde.elasticbeanstalk.com?streetInput=2630+menlo+ave&cityInput=los+angeles&stateInput=CA";
			
			// get json string from url
			json = jParser.getJSONFromUrl(myjsonstr);
            // String nbed = json.getString("nbed");
			return null;
        }
 
        @Override
        protected void onPostExecute(String strFromDoInBg) {
        	Log.v("json",json.toString());
        	try {
        String trc = json.getString("returnCode");
        JSONObject trc1 = new JSONObject(trc);
        String returncode = trc1.getString("0");
        if(returncode.equals("508")) 
        { 
        	Log.v("returncode", "error"); 
        	resp.setText("No exact match found-- Verify that the given address is correct");return;
        	//return returncode;
        }
        else if(returncode.equals("0"))
        {
        String myaddr = json.getString("myaddr");
        
        String charturl1 = json.getString("charturl1");
        String charturl5 = json.getString("charturl5");
        String charturl10 = json.getString("charturl10");
        
        String hd = json.getString("homedetails");
        JSONObject hd1 = new JSONObject(hd);
        String homedetails = hd1.getString("0");
        
        String pt = json.getString("propertyType");
        JSONObject pt1 = new JSONObject(pt);
        String propertyType = pt1.getString("0");
        String yb = json.getString("yearbuilt");
        JSONObject yb1 = new JSONObject(yb);
        String yearbuilt = yb1.getString("0");
        String lsp = json.getString("lastsellprice");
        String ta = json.getString("taxamt");
        String lsd = json.getString("lastselldate");
        String ls = json.getString("lotsize");
        String zupd = json.getString("zupddate");
        String zupa = json.getString("zupdtamt");
        String fa = json.getString("finarea");
        String arrow = json.getString("disparrow");
        String da = json.getString("depreamt");
        String nba = json.getString("nbath");
        String atl = json.getString("alltimelow");
        String ath = json.getString("alltimehigh");
        String nbe = json.getString("nbed");
        String rzupd = json.getString("rzupddate");
        String rzupa = json.getString("rzupdtamt");
        String ty = json.getString("taxyear");
        JSONObject ty1 = new JSONObject(ty);
        String taxyear = ty1.getString("0");
        String rarrow = json.getString("rdisparrow");
        String rda = json.getString("rdepreamt");
        String ratl = json.getString("ralltimelow");
        String rath = json.getString("ralltimehigh");
        Log.v("myaddr", myaddr);
        Log.v("charturl1", charturl1);
        Log.v("charturl5", charturl5);
        Log.v("charturl10", charturl10);
        Log.v("homedetails", homedetails);
        Log.v("property", propertyType);
        Log.v("lastsellprice", lsp);
        Log.v("yearbuilt", yearbuilt);
        Log.v("taxamt", ta);
        Log.v("lastselldate", lsd);
        Log.v("lotsize", ls);
        Log.v("zupddate", zupd);
        Log.v("zupdtamt", zupa);
        Log.v("finarea", fa);
        Log.v("disparrow", arrow);
        Log.v("depreamt", da);
        Log.v("nbath", nba);
        Log.v("alltimelow", atl);
        Log.v("alltimehigh", ath);
        Log.v("nbed", nbe);
        Log.v("rzupddate", rzupd);
        Log.v("rzupdtamt", rzupa);
        Log.v("taxyear", taxyear);
        Log.v("rdisparrow", rarrow);
        Log.v("rdepreamt", rda);
      
        System.out.println("here 1");
        Intent localIntent = new Intent(getBaseContext(), ResultActivity.class);
        localIntent.putExtra("myaddr", myaddr);
        localIntent.putExtra("charturl1", charturl1);
        localIntent.putExtra("charturl5", charturl5);
        localIntent.putExtra("charturl10", charturl10);
        localIntent.putExtra("homedetails", homedetails);
        localIntent.putExtra("property", propertyType);
        localIntent.putExtra("lastsellprice", lsp);
        localIntent.putExtra("yearbuilt", yearbuilt);
        localIntent.putExtra("taxamt", ta);
        localIntent.putExtra("lastselldate", lsd);
        localIntent.putExtra("lotsize", ls);
        localIntent.putExtra("zupddate", zupd);
        localIntent.putExtra("zupdtamt", zupa);
        localIntent.putExtra("finarea", fa);
        localIntent.putExtra("disparrow", arrow);
        localIntent.putExtra("depreamt", da);
        localIntent.putExtra("nbath", nba);
        localIntent.putExtra("alltimelow", atl);
        localIntent.putExtra("alltimehigh", ath);
        localIntent.putExtra("nbed", nbe);
        localIntent.putExtra("rzupddate", rzupd);
        localIntent.putExtra("rzupdtamt", rzupa);
        localIntent.putExtra("taxyear", taxyear);
        localIntent.putExtra("rdisparrow", rarrow);
        localIntent.putExtra("rdepreamt", rda);
        localIntent.putExtra("ralltimelow", ratl);
        localIntent.putExtra("ralltimehigh", rath);
        startActivity(localIntent);
        }
        
        System.out.println("here 2");

      //  }
    }catch (JSONException e) {
        e.printStackTrace();
    }

   return;
    }
	}
}
