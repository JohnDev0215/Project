package b2;

import android.content.Context;
import android.graphics.Bitmap;
import java.security.MessageDigest;
import n1.m;
import q1.v;

/* JADX INFO: compiled from: GifDrawableTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements m<c> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<Bitmap> f3496b;

    public f(m<Bitmap> mVar) {
        this.f3496b = (m) k2.j.d(mVar);
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        this.f3496b.a(messageDigest);
    }

    @Override // n1.m
    public v<c> b(Context context, v<c> vVar, int i7, int i8) {
        c cVar = vVar.get();
        v<Bitmap> eVar = new x1.e(cVar.e(), com.bumptech.glide.c.c(context).f());
        v<Bitmap> vVarB = this.f3496b.b(context, eVar, i7, i8);
        if (!eVar.equals(vVarB)) {
            eVar.a();
        }
        cVar.m(this.f3496b, vVarB.get());
        return vVar;
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (obj instanceof f) {
            return this.f3496b.equals(((f) obj).f3496b);
        }
        return false;
    }

    @Override // n1.f
    public int hashCode() {
        return this.f3496b.hashCode();
    }
}
