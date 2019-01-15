# Deep-Link-Ad
1.	Create an account on the www.branch.io 
2.	Going to the «Link settings», choose Android, write into Android URI Scheme (example: deeplink://) + enable App Links
SHA256 Cert Fingerprints
MD5:  
SHA1:  
You can create it just following next steps – click on the right panel “Gradle” – choose :app – Tasks – Android – signing Report and waiting for respond  MD5 & SHA1. 
3.	If you wanna activate function - track event (means collecting all users info and sending on server on AppsFlyer.com) 
4.	Set up the AndroidManifest.xml 
Step 1:
	Connect some permissions
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

Step 2:
	
	Insert in .MainActivity, where last 2 links were created on branch.io 
<!-- Branch URI Scheme -->
<intent-filter>
    <data android:scheme="androidexample" />
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />
</intent-filter>

<!-- Branch App Links (optional) -->
<intent-filter android:autoVerify="true">
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />
    <data android:scheme="https" android:host="deeplink0001.app.link" />
    <data android:scheme="https" android:host="deeplink0001-alternate.app.link" />
</intent-filter>

Step 3:
 	Connecting to branch.io with a key. 
<!-- Branch init -->
<meta-data android:name="io.branch.sdk.BranchKey" android:value="key_live_alLiQanbG5VtPI0LsKLt4bmbvrjS0RKI" />
<meta-data android:name="io.branch.sdk.BranchKey.test" android:value="key_test_hlxrWC5Zx16DkYmWu4AHiimdqugRYMr" />
<meta-data android:name="io.branch.sdk.TestMode" android:value="false" /> <!-- Set to true to use Branch_Test_Key -->

Step 4:
 <!-- Branch install referrer tracking (optional) -->
<receiver android:name="io.branch.referral.InstallListener" android:exported="true">
    <intent-filter>
        <action android:name="com.android.vending.INSTALL_REFERRER" />
    </intent-filter>
</receiver>

Step 5:
	Enable deeplink access(activity) 
<activity android:name=".DeepLink2">    
<intent-filter android:autoVerify="true"> 
    <action android:name="android.intent.action.VIEW" />

    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />

    <data
        android:host="go.onelink.me"
        android:path="/app/deeplink1"
        android:scheme="https" />
</intent-filter>

Step 6:
	If you wanna enable AppsFlyer 
<receiver
    android:name="com.appsflyer.SingleInstallBroadcastReceiver"
    android:exported="true">
        
    <intent-filter>
                 
        <action android:name="com.android.vending.INSTALL_REFERRER" />
             
    </intent-filter>
</receiver>

Step 7: 
	if you have done with step 6, than in the fil MainActivity.java (lines 176 – 192) copy into void OnCreate  
Step 8:
 Connect app to the facebook (this is optional option) 
https://developers.facebook.com/docs/app-ads/deep-linking

https://developers.facebook.com/docs/applinks

Step 9:
	Rest description – in the code. 
