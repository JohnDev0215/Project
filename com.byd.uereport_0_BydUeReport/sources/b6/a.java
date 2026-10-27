package b6;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Typeface f2102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0007a f2103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2104c;

    /* JADX INFO: renamed from: b6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0007a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0007a interfaceC0007a, Typeface typeface) {
        this.f2102a = typeface;
        this.f2103b = interfaceC0007a;
    }

    @Override // b6.f
    public void a(int i8) {
        d(this.f2102a);
    }

    @Override // b6.f
    public void b(Typeface typeface, boolean z7) {
        d(typeface);
    }

    public void c() {
        this.f2104c = true;
    }

    public final void d(Typeface typeface) {
        if (this.f2104c) {
            return;
        }
        this.f2103b.a(typeface);
    }
}
