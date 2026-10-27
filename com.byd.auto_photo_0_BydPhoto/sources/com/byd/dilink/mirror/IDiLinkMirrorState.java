package com.byd.dilink.mirror;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkMirrorState {
    int getDisplayId();

    String getMirrorApp();

    String[] getMirrorAppCollection();

    boolean inMirror();

    boolean inMirror(String str);
}
