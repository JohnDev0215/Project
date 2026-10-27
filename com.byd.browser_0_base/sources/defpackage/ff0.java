package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* JADX INFO: compiled from: DynamicResourceSnapshot.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ff0 implements pb2 {
    public final Bitmap a;
    public final boolean b;
    public final Rect c;
    public final long d;

    public ff0(Bitmap bitmap, boolean z, Rect rect, long j) {
        this.a = bitmap;
        this.b = z;
        this.c = rect;
        this.d = j;
    }

    @Override // defpackage.pb2
    public final boolean a() {
        return this.b;
    }

    @Override // defpackage.pb2
    public final Bitmap b() {
        return this.a;
    }

    @Override // defpackage.pb2
    public final long c() {
        return this.d;
    }

    @Override // defpackage.pb2
    public final Rect d() {
        return this.c;
    }

    @Override // defpackage.pb2
    public final l50 e() {
        return null;
    }
}
