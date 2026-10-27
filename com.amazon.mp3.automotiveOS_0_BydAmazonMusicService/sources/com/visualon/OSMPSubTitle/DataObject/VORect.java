package com.visualon.OSMPSubTitle.DataObject;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes7.dex */
public class VORect {
    public Rect rect = new Rect();
    public int edgeType = 0;
    public int edgeColor = 0;
    public int color = 0;
    public int zOrder = 0;
    public VOSubtitleDisPlayEffect rectDisplayEffect = new VOSubtitleDisPlayEffect();

    private void setColor(int i) {
        this.color = i;
    }
}
