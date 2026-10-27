package defpackage;

import org.chromium.components.paintpreview.player.b;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g22 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ b.InterfaceC0137b f;

    public /* synthetic */ g22(b.InterfaceC0137b interfaceC0137b, int i) {
        this.e = i;
        this.f = interfaceC0137b;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        b.InterfaceC0137b interfaceC0137b = this.f;
        switch (i) {
            case 0:
                interfaceC0137b.d();
                break;
            default:
                interfaceC0137b.e();
                break;
        }
    }
}
