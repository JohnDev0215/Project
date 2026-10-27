package b3;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f3527a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0033a f3528b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3529c;

    /* JADX INFO: renamed from: b3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0033a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0033a interfaceC0033a, Typeface typeface) {
        this.f3527a = typeface;
        this.f3528b = interfaceC0033a;
    }

    private void d(Typeface typeface) {
        if (this.f3529c) {
            return;
        }
        this.f3528b.a(typeface);
    }

    @Override // b3.f
    public void a(int i7) {
        d(this.f3527a);
    }

    @Override // b3.f
    public void b(Typeface typeface, boolean z6) {
        d(typeface);
    }

    public void c() {
        this.f3529c = true;
    }
}
