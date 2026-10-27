package org.chromium.components.browser_ui.photo_picker;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.LruCache;
import defpackage.af;
import defpackage.i9;
import defpackage.oj2;
import java.util.ArrayList;
import org.chromium.components.browser_ui.photo_picker.PickerCategoryView;

/* JADX INFO: compiled from: BitmapScalerTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends i9<Bitmap> {
    public final LruCache<String, PickerCategoryView.b> h;
    public final String i;
    public final int j;
    public final Bitmap k;
    public final String l;
    public final float m;

    public a(LruCache<String, PickerCategoryView.b> lruCache, Bitmap bitmap, String str, String str2, int i, float f) {
        this.h = lruCache;
        this.i = str;
        this.j = i;
        this.k = bitmap;
        this.l = str2;
        this.m = f;
    }

    @Override // defpackage.i9
    public final Bitmap b() {
        if (i()) {
            return null;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Bitmap bitmapD = af.d(this.k, this.j);
        oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "Android.PhotoPicker.BitmapScalerTask");
        return bitmapD;
    }

    @Override // defpackage.i9
    public final void l(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (i()) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(bitmap2);
        this.h.put(this.i, new PickerCategoryView.b(arrayList, this.l, this.m));
    }
}
