package defpackage;

import android.graphics.Bitmap;
import defpackage.mh;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class rh implements LargeIconBridge.LargeIconCallback {
    public final /* synthetic */ mh.a e;
    public final /* synthetic */ mh.d f;

    public rh(mh.d dVar, mh.a aVar) {
        this.f = dVar;
        this.e = aVar;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        Bitmap bitmapCreateScaledBitmap;
        mh.a aVar = this.e;
        mh.d dVar = this.f;
        if (bitmap == null) {
            dVar.e.e.setColor(i);
            bitmapCreateScaledBitmap = dVar.e.c(aVar.b);
        } else {
            int i3 = dVar.g;
            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i3, i3, true);
        }
        aVar.f = bitmapCreateScaledBitmap;
        int i4 = dVar.h - 1;
        dVar.h = i4;
        if (i4 == 0) {
            ((oh) dVar.a).a.add(dVar.b);
            dVar.d.a();
        }
    }
}
