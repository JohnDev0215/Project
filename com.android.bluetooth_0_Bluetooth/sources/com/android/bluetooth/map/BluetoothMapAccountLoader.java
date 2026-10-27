package com.android.bluetooth.map;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import com.android.bluetooth.map.BluetoothMapUtils;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapAccountLoader {
    private static final boolean D = false;
    private static final long PROVIDER_ANR_TIMEOUT = 20000;
    private static final String TAG = "BluetoothMapAccountLoader";
    private static final boolean V = false;
    private Context mContext;
    private ContentResolver mResolver;
    private PackageManager mPackageManager = null;
    private int mAccountsEnabledCount = 0;
    private ContentProviderClient mProviderClient = null;

    public BluetoothMapAccountLoader(Context ctx) {
        this.mContext = null;
        this.mContext = ctx;
    }

    public LinkedHashMap<BluetoothMapAccountItem, ArrayList<BluetoothMapAccountItem>> parsePackages(boolean includeIcon) {
        BluetoothMapAccountItem app;
        LinkedHashMap<BluetoothMapAccountItem, ArrayList<BluetoothMapAccountItem>> groups = new LinkedHashMap<>();
        int i = 0;
        boolean z = true;
        Intent[] searchIntents = {new Intent(BluetoothMapContract.PROVIDER_INTERFACE_EMAIL), new Intent(BluetoothMapContract.PROVIDER_INTERFACE_IM)};
        this.mAccountsEnabledCount = 0;
        this.mPackageManager = this.mContext.getPackageManager();
        int length = searchIntents.length;
        int i2 = 0;
        while (i2 < length) {
            Intent searchIntent = searchIntents[i2];
            List<ResolveInfo> resInfos = this.mPackageManager.queryIntentContentProviders(searchIntent, i);
            if (resInfos != null) {
                BluetoothMapUtils.TYPE msgType = Objects.equals(searchIntent.getAction(), BluetoothMapContract.PROVIDER_INTERFACE_EMAIL) ? BluetoothMapUtils.TYPE.EMAIL : BluetoothMapUtils.TYPE.IM;
                for (ResolveInfo rInfo : resInfos) {
                    if ((rInfo.providerInfo.applicationInfo.flags & 2097152) == 0 && (app = createAppItem(rInfo, includeIcon, msgType)) != null) {
                        ArrayList<BluetoothMapAccountItem> accounts = parseAccounts(app);
                        if (accounts.size() > 0) {
                            app.mIsChecked = z;
                            Iterator<BluetoothMapAccountItem> it = accounts.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                BluetoothMapAccountItem acc = it.next();
                                if (acc.mIsChecked) {
                                    i = 0;
                                } else {
                                    i = 0;
                                    app.mIsChecked = false;
                                    break;
                                }
                            }
                            groups.put(app, accounts);
                        }
                    }
                    z = true;
                }
            }
            i2++;
            z = true;
        }
        return groups;
    }

    public BluetoothMapAccountItem createAppItem(ResolveInfo rInfo, boolean includeIcon, BluetoothMapUtils.TYPE type) {
        String provider = rInfo.providerInfo.authority;
        if (provider == null) {
            return null;
        }
        String name = rInfo.loadLabel(this.mPackageManager).toString();
        BluetoothMapAccountItem app = BluetoothMapAccountItem.create("0", name, rInfo.providerInfo.packageName, provider, includeIcon ? rInfo.loadIcon(this.mPackageManager) : null, type);
        return app;
    }

    public ArrayList<BluetoothMapAccountItem> parseAccounts(BluetoothMapAccountItem app) {
        Cursor c;
        ArrayList<BluetoothMapAccountItem> children = new ArrayList<>();
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mResolver = contentResolver;
        try {
            ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(Uri.parse(app.mBase_uri_no_account));
            this.mProviderClient = contentProviderClientAcquireUnstableContentProviderClient;
            if (contentProviderClientAcquireUnstableContentProviderClient == null) {
                throw new RemoteException("Failed to acquire provider for " + app.getPackageName());
            }
            contentProviderClientAcquireUnstableContentProviderClient.setDetectNotResponding(PROVIDER_ANR_TIMEOUT);
            Uri uri = Uri.parse(app.mBase_uri_no_account + "/" + BluetoothMapContract.TABLE_ACCOUNT);
            if (app.getType() == BluetoothMapUtils.TYPE.IM) {
                c = this.mProviderClient.query(uri, BluetoothMapContract.BT_IM_ACCOUNT_PROJECTION, null, null, "_id DESC");
            } else {
                c = this.mProviderClient.query(uri, BluetoothMapContract.BT_ACCOUNT_PROJECTION, null, null, "_id DESC");
            }
            ContentProviderClient contentProviderClient = this.mProviderClient;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
            if (c != null) {
                c.moveToPosition(-1);
                int idIndex = c.getColumnIndex("_id");
                int dispNameIndex = c.getColumnIndex(BluetoothMapContract.AccountColumns.ACCOUNT_DISPLAY_NAME);
                int exposeIndex = c.getColumnIndex(BluetoothMapContract.AccountColumns.FLAG_EXPOSE);
                int uciIndex = c.getColumnIndex(BluetoothMapContract.AccountColumns.ACCOUNT_UCI);
                int uciPreIndex = c.getColumnIndex(BluetoothMapContract.AccountColumns.ACCOUNT_UCI_PREFIX);
                while (c.moveToNext()) {
                    String uci = null;
                    String uciPrefix = null;
                    if (app.getType() == BluetoothMapUtils.TYPE.IM) {
                        uci = c.getString(uciIndex);
                        uciPrefix = c.getString(uciPreIndex);
                    }
                    BluetoothMapAccountItem child = BluetoothMapAccountItem.create(String.valueOf(c.getInt(idIndex)), c.getString(dispNameIndex), app.getPackageName(), app.getProviderAuthority(), null, app.getType(), uci, uciPrefix);
                    child.mIsChecked = c.getInt(exposeIndex) != 0;
                    child.mIsChecked = true;
                    if (child.mIsChecked) {
                        this.mAccountsEnabledCount++;
                    }
                    children.add(child);
                }
                c.close();
            }
            return children;
        } catch (RemoteException e) {
            ContentProviderClient contentProviderClient2 = this.mProviderClient;
            if (contentProviderClient2 != null) {
                contentProviderClient2.close();
            }
            return children;
        } catch (Throwable th) {
            ContentProviderClient contentProviderClient3 = this.mProviderClient;
            if (contentProviderClient3 != null) {
                contentProviderClient3.close();
            }
            throw th;
        }
    }

    public int getAccountsEnabledCount() {
        return this.mAccountsEnabledCount;
    }
}
