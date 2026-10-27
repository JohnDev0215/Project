package Q5;

import com.stingray.karaoke.car.utils.ToastType;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class M0 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6437m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6438n;

    public /* synthetic */ M0(InterfaceC2772a interfaceC2772a, boolean z6) {
        this.l = 1;
        this.f6437m = interfaceC2772a;
        this.f6438n = z6;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                if (this.f6438n) {
                    this.f6437m.a();
                }
                break;
            case 1:
                InterfaceC2772a interfaceC2772a = this.f6437m;
                if (interfaceC2772a != null && this.f6438n) {
                    interfaceC2772a.a();
                }
                break;
            default:
                if (this.f6438n) {
                    this.f6437m.a();
                } else {
                    ToastType toastType = ToastType.DRIVING_DISABLED_WARNING;
                    A7.m.f(toastType, "toastType");
                    h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
                }
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ M0(boolean z6, InterfaceC2772a interfaceC2772a, int i7) {
        this.l = i7;
        this.f6438n = z6;
        this.f6437m = interfaceC2772a;
    }
}
