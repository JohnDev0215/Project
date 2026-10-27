package com.android.bluetooth.map;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.util.Log;
import com.android.bluetooth.map.BluetoothMapUtils;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapAppObserver {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapAppObserver";
    private static final boolean V = false;
    private Context mContext;
    private LinkedHashMap<BluetoothMapAccountItem, ArrayList<BluetoothMapAccountItem>> mFullList;
    BluetoothMapAccountLoader mLoader;
    BluetoothMapService mMapService;
    private BroadcastReceiver mReceiver;
    private ContentResolver mResolver;
    private LinkedHashMap<String, ContentObserver> mObserverMap = new LinkedHashMap<>();
    private PackageManager mPackageManager = null;
    private boolean mRegisteredReceiver = false;

    public BluetoothMapAppObserver(Context context, BluetoothMapService mapService) {
        this.mMapService = null;
        this.mContext = context;
        this.mMapService = mapService;
        this.mResolver = context.getContentResolver();
        BluetoothMapAccountLoader bluetoothMapAccountLoader = new BluetoothMapAccountLoader(this.mContext);
        this.mLoader = bluetoothMapAccountLoader;
        this.mFullList = bluetoothMapAccountLoader.parsePackages(false);
        createReceiver();
        initObservers();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothMapAccountItem getApp(String authoritiesName) {
        for (BluetoothMapAccountItem app : this.mFullList.keySet()) {
            if (app.getProviderAuthority().equals(authoritiesName)) {
                return app;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAccountChanges(String packageNameWithProvider) {
        BluetoothMapAccountItem app = getApp(packageNameWithProvider);
        if (app != null) {
            ArrayList<BluetoothMapAccountItem> newAccountList = this.mLoader.parseAccounts(app);
            ArrayList<BluetoothMapAccountItem> oldAccountList = this.mFullList.get(app);
            ArrayList<BluetoothMapAccountItem> addedAccountList = (ArrayList) newAccountList.clone();
            ArrayList<BluetoothMapAccountItem> removedAccountList = this.mFullList.get(app);
            if (oldAccountList == null) {
                oldAccountList = new ArrayList<>();
            }
            if (removedAccountList == null) {
                removedAccountList = new ArrayList<>();
            }
            this.mFullList.put(app, newAccountList);
            for (BluetoothMapAccountItem newAcc : newAccountList) {
                Iterator<BluetoothMapAccountItem> it = oldAccountList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        BluetoothMapAccountItem oldAcc = it.next();
                        if (Objects.equals(newAcc.getId(), oldAcc.getId())) {
                            removedAccountList.remove(oldAcc);
                            addedAccountList.remove(newAcc);
                            if (!newAcc.getName().equals(oldAcc.getName()) && newAcc.mIsChecked) {
                                this.mMapService.updateMasInstances(2);
                            }
                            if (newAcc.mIsChecked != oldAcc.mIsChecked) {
                                if (newAcc.mIsChecked) {
                                    this.mMapService.updateMasInstances(0);
                                } else {
                                    this.mMapService.updateMasInstances(1);
                                }
                            }
                        }
                    }
                }
            }
            for (BluetoothMapAccountItem bluetoothMapAccountItem : removedAccountList) {
                this.mMapService.updateMasInstances(1);
            }
            for (BluetoothMapAccountItem bluetoothMapAccountItem2 : addedAccountList) {
                this.mMapService.updateMasInstances(0);
            }
            return;
        }
        Log.e(TAG, "Received change notification on package not registered for notifications!");
    }

    public void registerObserver(BluetoothMapAccountItem app) {
        Uri uri = BluetoothMapContract.buildAccountUri(app.getProviderAuthority());
        ContentObserver observer = new ContentObserver(null) { // from class: com.android.bluetooth.map.BluetoothMapAppObserver.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                onChange(selfChange, null);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri2) {
                if (uri2 != null) {
                    BluetoothMapAppObserver.this.handleAccountChanges(uri2.getHost());
                } else {
                    Log.e(BluetoothMapAppObserver.TAG, "Unable to handle change as the URI is NULL!");
                }
            }
        };
        this.mObserverMap.put(uri.toString(), observer);
        this.mResolver.registerContentObserver(uri, false, observer);
    }

    public void unregisterObserver(BluetoothMapAccountItem app) {
        Uri uri = BluetoothMapContract.buildAccountUri(app.getProviderAuthority());
        this.mResolver.unregisterContentObserver(this.mObserverMap.get(uri.toString()));
        this.mObserverMap.remove(uri.toString());
    }

    private void initObservers() {
        for (BluetoothMapAccountItem app : this.mFullList.keySet()) {
            registerObserver(app);
        }
    }

    private void deinitObservers() {
        for (BluetoothMapAccountItem app : this.mFullList.keySet()) {
            unregisterObserver(app);
        }
    }

    private void createReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.map.BluetoothMapAppObserver.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                BluetoothMapAccountItem app;
                String action = intent.getAction();
                if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                    Uri data = intent.getData();
                    String packageName = data.getEncodedSchemeSpecificPart();
                    BluetoothMapUtils.TYPE msgType = BluetoothMapUtils.TYPE.NONE;
                    ResolveInfo resolveInfo = null;
                    int i = 0;
                    Intent[] searchIntents = {new Intent(BluetoothMapContract.PROVIDER_INTERFACE_EMAIL), new Intent(BluetoothMapContract.PROVIDER_INTERFACE_IM)};
                    BluetoothMapAppObserver bluetoothMapAppObserver = BluetoothMapAppObserver.this;
                    bluetoothMapAppObserver.mPackageManager = bluetoothMapAppObserver.mContext.getPackageManager();
                    int length = searchIntents.length;
                    int i2 = 0;
                    while (i2 < length) {
                        Intent searchIntent = searchIntents[i2];
                        List<ResolveInfo> resInfos = BluetoothMapAppObserver.this.mPackageManager.queryIntentContentProviders(searchIntent, i);
                        if (resInfos != null) {
                            Iterator<ResolveInfo> it = resInfos.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                ResolveInfo rInfo = it.next();
                                if (rInfo != null && packageName.equals(rInfo.providerInfo.packageName)) {
                                    resolveInfo = rInfo;
                                    if (Objects.equals(searchIntent.getAction(), BluetoothMapContract.PROVIDER_INTERFACE_EMAIL)) {
                                        msgType = BluetoothMapUtils.TYPE.EMAIL;
                                    } else if (Objects.equals(searchIntent.getAction(), BluetoothMapContract.PROVIDER_INTERFACE_IM)) {
                                        msgType = BluetoothMapUtils.TYPE.IM;
                                    }
                                }
                            }
                        }
                        i2++;
                        i = 0;
                    }
                    if (resolveInfo != null && (app = BluetoothMapAppObserver.this.mLoader.createAppItem(resolveInfo, false, msgType)) != null) {
                        BluetoothMapAppObserver.this.registerObserver(app);
                        ArrayList<BluetoothMapAccountItem> newAccountList = BluetoothMapAppObserver.this.mLoader.parseAccounts(app);
                        BluetoothMapAppObserver.this.mFullList.put(app, newAccountList);
                        return;
                    }
                    return;
                }
                if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                    Uri data2 = intent.getData();
                    String packageName2 = data2.getEncodedSchemeSpecificPart();
                    BluetoothMapAccountItem app2 = BluetoothMapAppObserver.this.getApp(packageName2);
                    if (app2 != null) {
                        BluetoothMapAppObserver.this.unregisterObserver(app2);
                        BluetoothMapAppObserver.this.mFullList.remove(app2);
                    }
                }
            }
        };
        this.mReceiver = broadcastReceiver;
        if (!this.mRegisteredReceiver) {
            try {
                this.mContext.registerReceiver(broadcastReceiver, intentFilter);
                this.mRegisteredReceiver = true;
            } catch (Exception e) {
                Log.e(TAG, "Unable to register MapAppObserver receiver", e);
            }
        }
    }

    private void removeReceiver() {
        if (this.mRegisteredReceiver) {
            try {
                this.mRegisteredReceiver = false;
                this.mContext.unregisterReceiver(this.mReceiver);
            } catch (Exception e) {
                Log.e(TAG, "Unable to unregister mapAppObserver receiver", e);
            }
        }
    }

    public ArrayList<BluetoothMapAccountItem> getEnabledAccountItems() {
        ArrayList<BluetoothMapAccountItem> list = new ArrayList<>();
        for (BluetoothMapAccountItem app : this.mFullList.keySet()) {
            if (app == null) {
                Log.w(TAG, "getEnabledAccountItems() - No Account in App enabled\n");
            } else {
                ArrayList<BluetoothMapAccountItem> accountList = this.mFullList.get(app);
                if (accountList == null) {
                    Log.w(TAG, "getEnabledAccountItems() - No AccountList enabled\n");
                } else {
                    for (BluetoothMapAccountItem acc : accountList) {
                        if (acc.mIsChecked) {
                            list.add(acc);
                        }
                    }
                }
            }
        }
        return list;
    }

    public ArrayList<BluetoothMapAccountItem> getAllAccountItems() {
        ArrayList<BluetoothMapAccountItem> list = new ArrayList<>();
        for (BluetoothMapAccountItem app : this.mFullList.keySet()) {
            ArrayList<BluetoothMapAccountItem> accountList = this.mFullList.get(app);
            list.addAll(accountList);
        }
        return list;
    }

    public void shutdown() {
        deinitObservers();
        removeReceiver();
    }
}
