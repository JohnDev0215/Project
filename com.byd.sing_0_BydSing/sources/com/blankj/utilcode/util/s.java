package com.blankj.utilcode.util;

import android.graphics.Point;
import android.view.WindowManager;

/* JADX INFO: compiled from: ScreenUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class s {
    public static int a() {
        WindowManager windowManager = (WindowManager) w.a().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }
}
