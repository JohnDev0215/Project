package com.android.providers.media.scan;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class NullMediaScanner implements MediaScanner {
    private static final String TAG = "NullMediaScanner";
    private final Context mContext;

    @Override // com.android.providers.media.scan.MediaScanner
    public void onDetachVolume(String str) {
    }

    public NullMediaScanner(Context context) {
        this.mContext = context;
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Context getContext() {
        return this.mContext;
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public void scanDirectory(File file, int i) {
        Log.w(TAG, "Ignoring scan request for " + file);
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Uri scanFile(File file, int i) {
        Log.w(TAG, "Ignoring scan request for " + file);
        return null;
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Uri scanFile(File file, int i, String str) {
        Log.w(TAG, "Ignoring scan request for " + file);
        return null;
    }
}
