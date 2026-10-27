package org.chromium.chrome.browser.compositor.layouts.content;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Size;
import defpackage.b32;
import defpackage.d73;
import defpackage.iv;
import defpackage.oj2;
import defpackage.pq2;
import defpackage.ry;
import defpackage.sh;
import defpackage.sy2;
import defpackage.uy2;
import defpackage.y13;
import defpackage.ya0;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: loaded from: classes.dex */
public class TabContentManager {
    public static final b32 j = new b32();
    public static final sh k = new sh("TabGridLayoutAndroid", "allow_to_refetch", true);
    public final float b;
    public final int c;
    public final ry d;
    public long e;
    public final boolean g;
    public final a h;
    public final Context i;
    public final HashSet a = new HashSet();
    public final ArrayList<b> f = new ArrayList<>();

    public interface a {
    }

    public interface b {
        void a();
    }

    public TabContentManager(Context context, ry ryVar, boolean z, pq2 pq2Var) {
        this.i = context;
        this.d = ryVar;
        this.h = pq2Var;
        this.g = z;
        this.c = c(R$integer.default_thumbnail_cache_size, context, "thumbnails");
        float f = ya0.a(context).d;
        float f2 = 1.0f;
        if (DeviceFormFactor.a(context)) {
            f2 = 1.0f / f;
        } else if (f > 1.5f) {
            f2 = 1.5f / f;
        }
        this.b = f2;
    }

    public static int c(int i, Context context, String str) {
        int integer;
        if (oj2.z(context)) {
            integer = i == R$integer.default_thumbnail_cache_size ? 2 : -1;
            if (i == R$integer.default_approximation_thumbnail_cache_size) {
                integer = 8;
            }
        } else {
            integer = context.getResources().getInteger(i);
        }
        String strF = iv.e().f(str);
        return strF != null ? Integer.parseInt(strF) : integer;
    }

    public final void a(Tab tab) {
        if (this.e == 0 || !this.g) {
            return;
        }
        b(tab, true, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(org.chromium.chrome.browser.tab.Tab r27, boolean r28, defpackage.ty2 r29) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.layouts.content.TabContentManager.b(org.chromium.chrome.browser.tab.Tab, boolean, ty2):void");
    }

    public final double d() {
        return TabUtils.c(this.i);
    }

    public final void e(int i, Size size, Callback<Bitmap> callback, boolean z, boolean z2) {
        if (this.g) {
            if (!z) {
                TraceEvent.I0(i, "GetTabThumbnailFromDisk");
                PostTask.c(d73.i, new uy2(this, i, size, callback, 0));
            } else {
                if (this.e == 0) {
                    return;
                }
                sy2 sy2Var = new sy2(this, callback, i, z2);
                TraceEvent.I0(i, "GetTabThumbnailFromDisk");
                PostTask.c(d73.i, new uy2(this, i, size, sy2Var, 0));
            }
        }
    }

    public final void f(int i) {
        long j2 = this.e;
        if (j2 != 0) {
            N.MZeSR4YP(j2, i);
        }
    }

    public final void g(int i, List list) {
        if (this.e != 0) {
            int iMin = Math.min(this.c, list.size());
            int[] iArr = new int[iMin];
            for (int i2 = 0; i2 < iMin; i2++) {
                iArr[i2] = ((Integer) list.get(i2)).intValue();
            }
            N.MZoWkzRr(this.e, iArr, i);
        }
    }

    @CalledByNative
    public final long getNativePtr() {
        return this.e;
    }

    @CalledByNative
    public final Tab getTabById(int i) {
        a aVar = this.h;
        if (aVar == null) {
            return null;
        }
        return ((y13) ((pq2) aVar).f).s(i);
    }

    @CalledByNative
    public void notifyListenersOfThumbnailChange(int i) {
        Iterator<b> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }
}
