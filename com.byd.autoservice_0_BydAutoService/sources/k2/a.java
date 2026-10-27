package k2;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Typeface f3584a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0031a f3585b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3586c;

    /* JADX INFO: renamed from: k2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0031a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0031a interfaceC0031a, Typeface typeface) {
        this.f3584a = typeface;
        this.f3585b = interfaceC0031a;
    }

    @Override // k2.f
    public void a(int i3) {
        d(this.f3584a);
    }

    @Override // k2.f
    public void b(Typeface typeface, boolean z3) {
        d(typeface);
    }

    public void c() {
        this.f3586c = true;
    }

    public final void d(Typeface typeface) {
        if (this.f3586c) {
            return;
        }
        this.f3585b.a(typeface);
    }
}
