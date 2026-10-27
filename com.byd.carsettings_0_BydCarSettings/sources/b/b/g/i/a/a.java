package b.b.g.i.a;

import android.view.View;

/* JADX INFO: compiled from: OnClickListener.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0046a f803a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f804b;

    /* JADX INFO: renamed from: b.b.g.i.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OnClickListener.java */
    public interface InterfaceC0046a {
        void a(int i, View view);
    }

    public a(InterfaceC0046a interfaceC0046a, int i) {
        this.f803a = interfaceC0046a;
        this.f804b = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f803a.a(this.f804b, view);
    }
}
