package b.d.a.a.b0;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class a extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Typeface f1801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0036a f1802b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1803c;

    /* JADX INFO: renamed from: b.d.a.a.b0.a$a, reason: collision with other inner class name */
    public interface InterfaceC0036a {
    }

    public a(InterfaceC0036a interfaceC0036a, Typeface typeface) {
        this.f1801a = typeface;
        this.f1802b = interfaceC0036a;
    }

    @Override // b.d.a.a.b0.d
    public void a(int i) {
        c(this.f1801a);
    }

    @Override // b.d.a.a.b0.d
    public void b(Typeface typeface, boolean z) {
        c(typeface);
    }

    public final void c(Typeface typeface) {
        if (this.f1803c) {
            return;
        }
        b.d.a.a.z.c cVar = ((b.d.a.a.z.b) this.f1802b).f2161a;
        a aVar = cVar.w;
        boolean z = true;
        if (aVar != null) {
            aVar.f1803c = true;
        }
        if (cVar.t != typeface) {
            cVar.t = typeface;
        } else {
            z = false;
        }
        if (z) {
            cVar.k();
        }
    }
}
