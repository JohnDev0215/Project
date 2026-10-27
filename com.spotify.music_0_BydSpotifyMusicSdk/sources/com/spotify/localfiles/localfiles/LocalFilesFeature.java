package com.spotify.localfiles.localfiles;

import android.net.Uri;
import io.reactivex.rxjava3.core.Completable;
import p.jb2;
import p.ww0;
import p.zw0;

/* JADX INFO: loaded from: classes.dex */
public interface LocalFilesFeature {
    Completable addPermenentFiles(jb2 jb2Var);

    void addTemporaryFile(Uri uri);

    boolean isEnabled();

    boolean isPermanentFile(Uri uri);

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    Completable removePermanentFile(Uri uri);

    Completable setEnabled(boolean z);

    Completable setEnabledWithPermission(ww0 ww0Var, boolean z);

    Completable setEnabledWithPermission(zw0 zw0Var, boolean z);
}
