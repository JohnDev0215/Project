package com.amazon.mp3.store.html5.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.store.html5.map.MAPCookiesUtil;
import com.amazon.mp3.store.html5.util.StoreUrls;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class StoreCacheService extends Service {
    private Intent mStartIntent;
    private Handler mTimeoutHandler;
    private final Runnable mTimeoutRunnable = new Runnable() { // from class: com.amazon.mp3.store.html5.service.StoreCacheService.2
        @Override // java.lang.Runnable
        public void run() {
            Log.info(StoreCacheService.TAG, "Store caching timed out");
            StoreCacheService.this.onDoneCaching();
        }
    };
    private static final String TAG = StoreCacheService.class.getSimpleName();
    private static boolean sInitialPrefetchCompleted = false;
    private static boolean sReceiverRegistered = false;
    private static final BroadcastReceiver sConnectivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.store.html5.service.StoreCacheService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            if (intent == null || !(!intent.getBooleanExtra("noConnectivity", false))) {
                return;
            }
            Log.debug(StoreCacheService.TAG, "Connectivity changed, re-attempting store cache");
            StoreCacheService.unregisterConnectivityReceiver();
            StoreCacheService.start(context);
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context context) {
        if (AccountCredentialUtil.get(context).isSignedIn()) {
            ServiceUtil.startService(context, new Intent(context, (Class<?>) StoreCacheService.class));
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.mStartIntent = intent;
        super.onStartCommand(intent, flags, startId);
        return 2;
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int startId) {
        this.mStartIntent = intent;
        super.onStart(intent, startId);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            registerConnectivityReceiver();
            Log.info(TAG, "Waiting for connectivity to cache store");
            stopSelf();
            return;
        }
        MAPCookiesUtil.prefetchOnlyIfNeeded();
        if (StoreWebView.instanceCreated()) {
            Log.debug(TAG, "Store already cached");
            stopSelf();
            return;
        }
        Log.debug(TAG, "Caching store");
        StoreWebView storeWebView = StoreWebView.getInstance(this);
        storeWebView.setMessageHandler(new StoreCacheServiceMessageHandler(this));
        storeWebView.setWebViewClient(new StoreCacheServiceWebViewClient(this));
        Intent intent = this.mStartIntent;
        if (intent != null) {
            StoreUrls.setReftag(intent.getStringExtra("com.amazon.mp3.extra.REFTAG"));
        }
        storeWebView.loadUrl(StoreUrls.getBaseUrl());
        Handler handler = new Handler();
        this.mTimeoutHandler = handler;
        handler.postDelayed(this.mTimeoutRunnable, 30000L);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Handler handler = this.mTimeoutHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mTimeoutRunnable);
        }
        StoreWebView.destroyWebViewIfNotAttached();
        super.onDestroy();
    }

    void onDoneCaching() {
        StoreWebView.getInstance(this).freeMemory();
        Log.verbose(TAG, "Finished caching store");
        stopSelf();
    }

    void onWebViewClientReportedError() {
        onDoneCaching();
        registerConnectivityReceiver();
    }

    private static void registerConnectivityReceiver() {
        if (sReceiverRegistered) {
            return;
        }
        AmazonApplication.getContext().registerReceiver(sConnectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        sReceiverRegistered = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void unregisterConnectivityReceiver() {
        if (sReceiverRegistered) {
            AmazonApplication.getContext().unregisterReceiver(sConnectivityReceiver);
            sReceiverRegistered = false;
        }
    }
}
