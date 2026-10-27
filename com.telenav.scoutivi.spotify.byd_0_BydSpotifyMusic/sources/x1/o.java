package x1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.security.MessageDigest;

/* JADX INFO: compiled from: DrawableTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public class o implements n1.m<Drawable> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n1.m<Bitmap> f9789b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f9790c;

    public o(n1.m<Bitmap> mVar, boolean z6) {
        this.f9789b = mVar;
        this.f9790c = z6;
    }

    private q1.v<Drawable> d(Context context, q1.v<Bitmap> vVar) {
        return t.f(context.getResources(), vVar);
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        this.f9789b.a(messageDigest);
    }

    @Override // n1.m
    public q1.v<Drawable> b(Context context, q1.v<Drawable> vVar, int i7, int i8) {
        r1.d dVarF = com.bumptech.glide.c.c(context).f();
        Drawable drawable = vVar.get();
        q1.v<Bitmap> vVarA = n.a(dVarF, drawable, i7, i8);
        if (vVarA != null) {
            q1.v<Bitmap> vVarB = this.f9789b.b(context, vVarA, i7, i8);
            if (!vVarB.equals(vVarA)) {
                return d(context, vVarB);
            }
            vVarB.a();
            return vVar;
        }
        if (!this.f9790c) {
            return vVar;
        }
        throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
    }

    public n1.m<BitmapDrawable> c() {
        return this;
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (obj instanceof o) {
            return this.f9789b.equals(((o) obj).f9789b);
        }
        return false;
    }

    @Override // n1.f
    public int hashCode() {
        return this.f9789b.hashCode();
    }
}
