package com.android.providers.media.scan;

import android.content.Context;
import android.net.Uri;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public interface MediaScanner {
    public static final int REASON_DEMAND = 2;
    public static final int REASON_IDLE = 3;
    public static final int REASON_MOUNTED = 1;
    public static final int REASON_UNKNOWN = 0;

    Context getContext();

    void onDetachVolume(String str);

    void scanDirectory(File file, int i);

    Uri scanFile(File file, int i);

    Uri scanFile(File file, int i, String str);
}
