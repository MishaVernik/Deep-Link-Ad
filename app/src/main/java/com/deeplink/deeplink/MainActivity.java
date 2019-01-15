package com.deeplink.deeplink;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;
import com.appsflyer.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Application;
import android.util.Log;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerConversionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import bolts.AppLinks;


import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;


public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();
    private static final String TAG = "MainActivity";
    private static final String AF_DEV_KEY = "";
    private final static String textRemark = "Deep link works!";
    private TextView txtView;
    private Toast toast;
    private static String token = "EAAaFAd0Gx1wBAFSHAIu88YBW1vkeBnmmZAQMR3rc29IDp90mTvAY88yhOZAq1W7Vo6pe546IEsvWuyLtR5m8f6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
 /*
         Intent intent = getIntent();
         String action = intent.getAction();
         Uri data = intent.getData();


        Branch Key
        key_live_alLiQanbG5VtPI0LsKLt4bmbvrjS0RKI

        Branch Secret
        secret_live_lg6o9kfGKpz7FSGN6v0D4XLvjhP1A5g8

        App ID
        612693708072047338

        App Name
        Deep Link pin-up

        Dashboard UID
        612693708759913196

     */
        // Branch init

        Branch.getInstance(getApplicationContext()).initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {

                if (error == null) {
                    Log.i("BRANCH SDK", referringParams.toString());
                    // Retrieve deeplink keys from 'referringParams' and evaluate the values to determine where to route the user
                    // Check '+clicked_branch_link' before deciding whether to use your Branch routing logic
                } else {
                    Log.i("BRANCH SDK", error.getMessage());
                }

            }
        }, this.getIntent().getData(), this);


        // listener (within Main Activity's onStart)
        Branch.getInstance(getApplicationContext()).initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                Log.i("HELLO ", " 7 ");
                if (error == null) {
                    // option 1: log data
                    Log.i("BRANCH SDK", referringParams.toString());

                    // option 2: save data to be used later
                    SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("branchData", referringParams.toString());
                    editor.commit();

                    // option 3: navigate to page
                    try {
                        if (referringParams.getString("value").equals("test_app2") ) {
                            Intent intent = new Intent(MainActivity.this, DeepLink2.class);
                            intent.putExtra("branchData", referringParams.toString());
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        if (referringParams.getString("$marketing_title").equals("deeplinkad")) {
                            Intent intent = new Intent(MainActivity.this, DeepLink2.class);
                            intent.putExtra("branchData", referringParams.toString());
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        if ( referringParams.getString("$marketing_title").equals("Deeplink0002") ) {
                            Intent intent = new Intent(MainActivity.this, DeepLink2.class);
                            intent.putExtra("branchData", referringParams.toString());
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if ( referringParams.getString("value").equals("test_app") ) {
                            Intent intent = new Intent(MainActivity.this, DeepLink2.class);
                            intent.putExtra("branchData", referringParams.toString());
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // option 4: display data
                    Toast.makeText(getApplicationContext(), referringParams.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Log.i("BRANCH SDK", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
       /*
         APP FLYERS.COM - REQUIRED FOR USERs DATA TRACKING!!!!

            SetInstallDataText();


        Button trackEventButton = findViewById(R.id.trackEventButton);
        trackEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Track Events in real time
                Map<String, Object> eventValue = new HashMap<String, Object>();
                eventValue.put(AFInAppEventParameterName.REVENUE, 100);
                eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, "example");
                eventValue.put(AFInAppEventParameterName.CONTENT_ID, "33333");
                eventValue.put(AFInAppEventParameterName.CURRENCY, "USD");
                AppsFlyerLib.getInstance().trackEvent(getApplicationContext(), AFInAppEventType.PURCHASE, eventValue);
            }
        });

        // For local deep links. If you have opened your app by deeplink (myapp://hello=world) it will shows that target not equals null
        Uri target = getIntent().getData();
        if (target != null) {
            // got here via Facebook deep link
            toast = Toast.makeText(getApplicationContext(), textRemark, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // activity was created in a normal fashion
        }
        */


    /*** Ignore - used to display install data ***/
    public void SetInstallDataText(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView installData = findViewById(R.id.installDataText);
                installData.setText(AFApplication.InstallConversionData);
            }
        } , 1000);

    }
}

