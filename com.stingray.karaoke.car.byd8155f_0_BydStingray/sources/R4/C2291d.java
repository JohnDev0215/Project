package r4;

import android.content.Context;
import android.graphics.Bitmap;
import e4.l;
import g4.x;
import java.security.MessageDigest;
import n4.C1958d;

/* JADX INFO: renamed from: r4.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2291d implements l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f21823b;

    public C2291d(l lVar) {
        A4.h.c(lVar, "Argument must not be null");
        this.f21823b = lVar;
    }

    @Override // e4.l
    public final x a(Context context, x xVar, int i7, int i10) {
        C2290c c2290c = (C2290c) xVar.get();
        x c1958d = new C1958d(c2290c.l.f21814a.l, com.bumptech.glide.b.a(context).l);
        l lVar = this.f21823b;
        x xVarA = lVar.a(context, c1958d, i7, i10);
        if (!c1958d.equals(xVarA)) {
            c1958d.e();
        }
        c2290c.l.f21814a.c(lVar, (Bitmap) xVarA.get());
        return xVar;
    }

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        this.f21823b.b(messageDigest);
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (obj instanceof C2291d) {
            return this.f21823b.equals(((C2291d) obj).f21823b);
        }
        return false;
    }

    @Override // e4.e
    public final int hashCode() {
        return this.f21823b.hashCode();
    }
}
