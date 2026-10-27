package com.android.launcher3.icons;

import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    static {
        a aVar = new Runnable() { // from class: com.android.launcher3.icons.a
            @Override // java.lang.Runnable
            public final void run() {
                e.b();
            }
        };
    }

    public static int a(Region region) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        int iWidth = 0;
        while (regionIterator.next(rect)) {
            iWidth += rect.width() * rect.height();
        }
        return iWidth;
    }

    static /* synthetic */ void b() {
    }
}
