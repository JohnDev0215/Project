package com.android.inputmethod.latin.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.LruCache;
import com.android.inputmethod.compat.AppWorkaroundsUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class TargetPackageInfoGetterTask extends AsyncTask<String, Void, PackageInfo> {
    private static final int MAX_CACHE_ENTRIES = 64;
    private static final LruCache<String, PackageInfo> sCache = new LruCache<>(64);
    private Context mContext;
    private final AsyncResultHolder<AppWorkaroundsUtils> mResult;

    public static PackageInfo getCachedPackageInfo(final String packageName) {
        if (packageName == null) {
            return null;
        }
        return sCache.get(packageName);
    }

    public static void removeCachedPackageInfo(final String packageName) {
        sCache.remove(packageName);
    }

    public TargetPackageInfoGetterTask(final Context context, final AsyncResultHolder<AppWorkaroundsUtils> result) {
        this.mContext = context;
        this.mResult = result;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public PackageInfo doInBackground(final String... packageName) {
        PackageManager packageManager = this.mContext.getPackageManager();
        this.mContext = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName[0], 0);
            sCache.put(packageName[0], packageInfo);
            return packageInfo;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(final PackageInfo info) {
        this.mResult.set(new AppWorkaroundsUtils(info));
    }
}
