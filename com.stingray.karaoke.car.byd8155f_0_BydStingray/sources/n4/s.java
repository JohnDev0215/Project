package n4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import h4.InterfaceC1359a;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class s implements e4.l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e4.l f19963b;
    public final boolean c;

    public s(e4.l lVar, boolean z6) {
        this.f19963b = lVar;
        this.c = z6;
    }

    @Override // e4.l
    public final g4.x a(Context context, g4.x xVar, int i7, int i10) {
        InterfaceC1359a interfaceC1359a = com.bumptech.glide.b.a(context).l;
        Drawable drawable = (Drawable) xVar.get();
        C1958d c1958dA = r.a(interfaceC1359a, drawable, i7, i10);
        if (c1958dA != null) {
            g4.x xVarA = this.f19963b.a(context, c1958dA, i7, i10);
            if (!xVarA.equals(c1958dA)) {
                return new C1958d(context.getResources(), xVarA);
            }
            xVarA.e();
            return xVar;
        }
        if (!this.c) {
            return xVar;
        }
        throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
    }

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        this.f19963b.b(messageDigest);
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (obj instanceof s) {
            return this.f19963b.equals(((s) obj).f19963b);
        }
        return false;
    }

    @Override // e4.e
    public final int hashCode() {
        return this.f19963b.hashCode();
    }
}
