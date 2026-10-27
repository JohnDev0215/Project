package com.byd.dilink.app.am;

import android.graphics.Canvas;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkGraphicBuffer {
    void destroy();

    int getFormat();

    int getHeight();

    int getUsage();

    int getWidth();

    boolean isDestroyed();

    Canvas lockCanvas();

    Canvas lockCanvas(Rect rect);

    void unlockCanvasAndPost(Canvas canvas);
}
