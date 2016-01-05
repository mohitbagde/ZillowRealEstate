package com.example.webtechfinal;

import com.facebook.AppEventsLogger;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class ResultActivity extends FragmentActivity {
	private UiLifecycleHelper uiHelper;
	
	static int i = 1;
	ImageView imageview1;
	ImageView imageview2;
	ImageView imageview3;
	Uri uri1,uri2,uri3;
	static String charts = "null";
	TextView chart;
	TextView row;
	TextView myaddr;
	static String myhome = "null";
	static String mycap = "Property Information from Zillow.com";
	static String mydesc = "null";
	static String sign = "null";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(this, null);
	    uiHelper.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_result);
		String raddr = getIntent().getExtras().getString("myaddr");
	    String hd = getIntent().getExtras().getString("homedetails");
	    myhome = hd;
	    TextView home = (TextView)findViewById(R.id.addresslink);
	    home.setText(Html.fromHtml(String.format("<a href=\"%s\">%s</a> ",hd, raddr)));
	    home.setMovementMethod(LinkMovementMethod.getInstance());
	    home.setClickable(true);
	    
	    TextView footer = (TextView) findViewById(R.id.footer);
	    footer.setText(Html.fromHtml("<center> &copy; Zilow Inc. 2006 - 2014.<br>Use is subject to <a href = 'http://www.zillow.com/corp/Terms.htm'>Terms of Use</a><br><a href = 'http://www.zillow.com/wikipages/What-is-a-Zestimate/'>What's a Zestimate?</a></center>"));
	    TextView footer2 = (TextView) findViewById(R.id.footer2);
	    footer2.setText(Html.fromHtml("<center> &copy; Zilow Inc. 2006 - 2014.<br>Use is subject to <a href = 'http://www.zillow.com/corp/Terms.htm'>Terms of Use</a><br><a href = 'http://www.zillow.com/wikipages/What-is-a-Zestimate/'>What's a Zestimate?</a></center>"));
	    
	    String prop = getIntent().getExtras().getString("property");
	    TextView prop1 = (TextView)findViewById(R.id.property);
	    prop1.setText(prop);
	    
	    String yb = getIntent().getExtras().getString("yearbuilt");
	    TextView yb1 = (TextView) findViewById(R.id.yearbuilt);
	    yb1.setText(yb);
	    
	    String ls = getIntent().getExtras().getString("lotsize");
	    TextView ls1 = (TextView)findViewById(R.id.lotsize);
	    ls1.setText(ls);
	    
	    String fa = getIntent().getExtras().getString("finarea");
	    TextView fa1 = (TextView)findViewById(R.id.finarea);
	    fa1.setText(fa);
	    
	    String nba = getIntent().getExtras().getString("nbath");
	    TextView nba1 = (TextView)findViewById(R.id.nbath);
	    nba1.setText(nba);
	    
	    String nbe = getIntent().getExtras().getString("nbed");
	    TextView nbe1 = (TextView)findViewById(R.id.nbed);
	    nbe1.setText(nbe);
	    
	    String ty = getIntent().getExtras().getString("taxyear");
	    TextView ty1 = (TextView)findViewById(R.id.taxyear);
	    ty1.setText(ty);
	    
	    String ta = getIntent().getExtras().getString("taxamt");
	    TextView ta1 = (TextView)findViewById(R.id.taxamt);
	    ta1.setText(ta);
	    
	    String lsp = getIntent().getExtras().getString("lastsellprice");
	    TextView lsp1 = (TextView)findViewById(R.id.lsp);
	    lsp1.setText(lsp);
	    
	    String lsd = getIntent().getExtras().getString("lastselldate");
	    TextView lsd1 = (TextView)findViewById(R.id.lsd);
	    lsd1.setText(lsd);
	    
	    String zupdt = getIntent().getExtras().getString("zupddate");
	    String zestimatelabel = String.format("Zestimate &reg; Property Estimate as of %s", zupdt);
	    TextView zlabel = (TextView)findViewById(R.id.zestimatelabel);
	    zlabel.setText(zestimatelabel);
	    
	    String zupa = getIntent().getExtras().getString("zupdtamt");
	    TextView zupa1 = (TextView)findViewById(R.id.zestimate);
	    zupa1.setText(zupa);
	   	   
	    String arr = getIntent().getExtras().getString("disparrow");
	    String da = getIntent().getExtras().getString("depreamt");
	    String disparr = "null";
	    
	    if (arr.equals("up"))        {disparr = "http://cs-server.usc.edu:45678/hw/hw6/up_g.gif"; sign = "+";}
	    else if(arr.equals("down")) { disparr = "http://cs-server.usc.edu:45678/hw/hw6/down_r.gif"; sign = "-";}
	    String htmltext = String.format("<img src = '%s'> %s",disparr, da);
	    TextView valuechange = (TextView)findViewById(R.id.valuechange);
	    URLImageParser uip = new URLImageParser(valuechange, this);
	    valuechange.setText(Html.fromHtml(htmltext, uip, null));
	    
	    mydesc = "Last Sold Price: "+lsp+" 30 Days Overall Change: "+sign+da;
	    String range = String.format("%s - %s", getIntent().getExtras().getString("alltimelow"), getIntent().getExtras().getString("alltimehigh"));
	    TextView range1 = (TextView)findViewById(R.id.propchange);
	    range1.setText(range);
	      
	    String rzl = String.format("Rent Zestimate &reg; Valuation as of %s", getIntent().getExtras().getString("rzupddate"));
	    TextView rzl1 = (TextView)findViewById(R.id.rentzestimatelabel);
	    rzl1.setText(rzl);
	    
	    String rz = getIntent().getExtras().getString("rzupdtamt");
	    TextView rz1 = (TextView)findViewById(R.id.rentzestimate);
	    rz1.setText(rz);
	    
	    String rarr = getIntent().getExtras().getString("rdisparrow");
	    String rda = getIntent().getExtras().getString("rdepreamt");
	    String rdisparr = "null";
	    if (rarr.equals("up"))        rdisparr = "http://cs-server.usc.edu:45678/hw/hw6/up_g.gif";
	    else if(arr.equals("down"))  rdisparr = "http://cs-server.usc.edu:45678/hw/hw6/down_r.gif";
	    String rhtmltext = String.format("<img src = '%s'> %s",rdisparr, rda);
	    TextView rvaluechange = (TextView)findViewById(R.id.rentchange);
	    URLImageParser ruip = new URLImageParser(rvaluechange, this);
	    rvaluechange.setText(Html.fromHtml(rhtmltext, ruip, null));
	    
	    String charturl1 = getIntent().getExtras().getString("charturl1");
	    String charturl2 = getIntent().getExtras().getString("charturl5");
	    String charturl3 = getIntent().getExtras().getString("charturl10");
	   final String chart1 = String.format("<img src = '%s'>", charturl1);
	   final String chart2 = String.format("<img src = '%s'>", charturl2);
	   final String chart3 = String.format("<img src = '%s'>", charturl3);

	    row = (TextView)findViewById(R.id.rowlabel);
	    myaddr = (TextView)findViewById(R.id.myaddr);
	  //  imageview1 =(ImageView) findViewById(R.id.imageView1);
	  //imageview2 =(ImageView) findViewById(R.id.imageView2);
	 // imageview3 =(ImageView) findViewById(R.id.imageView3);
	    uri1 = Uri.parse(charturl1);
	   final TextView mytext1 = (TextView) findViewById(R.id.textView1);
	   final TextView mytext2 = (TextView) findViewById(R.id.textView2);
	   final TextView mytext3 = (TextView) findViewById(R.id.textView3);
	    //imageview1.setImageURI(uri1);
	   URLImageParser urlimg1 = new URLImageParser(mytext1, this);
	   URLImageParser urlimg2 = new URLImageParser(mytext1, this);
	   URLImageParser urlimg3 = new URLImageParser(mytext1, this);
		 //   String url1 = String.format(chart1);
	    //final String url2 = String.format(chart2);
	    //final String url3 = String.format(chart3);
	   //  mytext.setText(Html.fromHtml(chart1, urlimg, null));
	   charts = charturl1;
	     mytext1.setText(Html.fromHtml(chart1, urlimg1, null));
	     mytext2.setText(Html.fromHtml(chart2, urlimg2, null));
	     mytext3.setText(Html.fromHtml(chart3, urlimg3, null));
	     myaddr.setText(raddr);
	     mytext2.setVisibility(View.GONE);
         mytext3.setVisibility(View.GONE);
        Button next= (Button) findViewById(R.id.next);
	    Button prev= (Button) findViewById(R.id.prev);
	    Button fb = (Button) findViewById(R.id.fb_button);
	    row.setText("Historical Zestimate for past 1 year");
	    next.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) 
	        {
	            switch(i)
	            {
	            case 1: 
	           	i = 2;
		        row.setText("Historical Zestimate for past 1 year");
	            mytext1.setVisibility(View.VISIBLE);
	            mytext2.setVisibility(View.GONE);
	            mytext3.setVisibility(View.GONE);
	            break;
	            case 2: 
		        i = 3;
		        row.setText("Historical Zestimate for past 5 years");
	            mytext2.setVisibility(View.VISIBLE);// mytext.setText(Html.fromHtml(chart2, urlimg, null));
	            mytext1.setVisibility(View.GONE);
	            mytext3.setVisibility(View.GONE);
	      		break;
	            case 3: 
	            i = 1;	
	            row.setText("Historical Zestimate for past 10 years");
	            mytext3.setVisibility(View.VISIBLE);// mytext.setText(Html.fromHtml(chart3, urlimg, null));
	            mytext1.setVisibility(View.GONE);
	            mytext2.setVisibility(View.GONE);
	  			break;
	            }
	            System.out.println(i);
          }});
	    prev.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) 
	        {
	        	switch(i)
	            {
	            case 1: 
	           	i = 3;
		        row.setText("Historical Zestimate for past 1 year");
	            mytext1.setVisibility(View.VISIBLE);
	            mytext2.setVisibility(View.GONE);
	            mytext3.setVisibility(View.GONE);
	            break;
	            case 2: 
		        i = 1;
		        row.setText("Historical Zestimate for past 5 years");
	            mytext2.setVisibility(View.VISIBLE);// mytext.setText(Html.fromHtml(chart2, urlimg, null));
	            mytext1.setVisibility(View.GONE);
	            mytext3.setVisibility(View.GONE);
	      		break;
	            case 3: 
	            i = 2;	
	            row.setText("Historical Zestimate for past 10 years");
	            mytext3.setVisibility(View.VISIBLE);// mytext.setText(Html.fromHtml(chart3, urlimg, null));
	            mytext1.setVisibility(View.GONE);
	            mytext2.setVisibility(View.GONE);
	  			break;
	            }
	            System.out.println(i);
	            }});
	    	
	        	
	    String atrange = String.format("%s - %s", getIntent().getExtras().getString("ralltimelow"), getIntent().getExtras().getString("ralltimehigh"));
	    TextView atrange1 = (TextView)findViewById(R.id.alltimerentchange);
	    atrange1.setText(atrange); 
	    
		System.out.println("here 3");
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);  
        tabHost.setup();  
        System.out.println("here 4");
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Basic Info").setContent(R.id.tab1));  
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Historical Zestimates").setContent(R.id.tab2));
        System.out.println("here 5");
        //TextView localTextView5 = (TextView)tabHost.getTabWidget().getChildAt(0).findViewById(R.id.tab1);
      //  TextView localTextView6 = (TextView)tabHost.getTabWidget().getChildAt(1).findViewById(R.id.tab2);
        System.out.println("here 6");
      //  localTextView5.setText("BASIC INFO");
      //  localTextView6.setText("HISTORICAL ZESTIMATES");
        System.out.println("here 7");
	       

    }
	
	public void share(View v)
	{
		// setContentView(R.layout.activity_result);
			
		FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
        .setLink(myhome)
        .setCaption(mycap)
        .setDescription(mydesc)
        .setPicture(charts)
        .build();
    	uiHelper.trackPendingDialogCall(shareDialog.present());
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
	        @Override
	        public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
	            Log.e("Activity", String.format("Error: %s", error.toString()));
	        }

	        @Override
	        public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
	            Log.i("Activity", "Success!");
	        }
	    });
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}
	@Override
	protected void onResume() {
	  super.onResume();
	  uiHelper.onResume();
	  // Logs 'install' and 'app activate' App Events.
	  AppEventsLogger.activateApp(this);
	}     
	@Override
	protected void onPause() {
	  super.onPause();
	  uiHelper.onPause();
	
	  // Logs 'app deactivate' App Event.
	  AppEventsLogger.deactivateApp(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
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
}
