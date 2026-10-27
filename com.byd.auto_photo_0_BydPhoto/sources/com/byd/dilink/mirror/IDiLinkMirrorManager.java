package com.byd.dilink.mirror;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkMirrorManager {
    IDiLinkMirrorState getMirrorState();

    void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener);

    void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener, Looper looper);

    void unregisterListener(IDiLinkMirrorListener iDiLinkMirrorListener);
}
