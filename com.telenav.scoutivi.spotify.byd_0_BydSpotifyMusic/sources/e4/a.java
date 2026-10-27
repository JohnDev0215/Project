package e4;

import android.view.View;

/* JADX INFO: compiled from: OnClickListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final InterfaceC0071a f6297e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final int f6298g;

    /* JADX INFO: renamed from: e4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OnClickListener.java */
    public interface InterfaceC0071a {
        void c(int i7, View view);
    }

    public a(InterfaceC0071a interfaceC0071a, int i7) {
        this.f6297e = interfaceC0071a;
        this.f6298g = i7;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f6297e.c(this.f6298g, view);
    }
}
