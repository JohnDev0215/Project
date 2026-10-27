package com.android.providers.media.scan;

import android.content.Context;
import android.net.Uri;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class LegacyMediaScanner implements MediaScanner {
    private final Context mContext;

    public LegacyMediaScanner(Context context) {
        this.mContext = context;
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Context getContext() {
        return this.mContext;
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public void scanDirectory(File file, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Uri scanFile(File file, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Uri scanFile(File file, int i, String str) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public void onDetachVolume(String str) {
        throw new UnsupportedOperationException();
    }
}
