package com.byd.dilink.mirror;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkMirrorService {
    void cancelStartActivityForMirror(String str, Intent intent);

    void startMirrorAppIfNeeded(String str);
}
