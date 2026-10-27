package Q5;

import com.stingray.karaoke.car.utils.ToastType;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: Q5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0553c implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6726m;

    public /* synthetic */ C0553c(int i7, boolean z6) {
        this.l = i7;
        this.f6726m = z6;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                if (this.f6726m) {
                    ToastType toastType = ToastType.DRIVING_DISABLED_WARNING;
                    A7.m.f(toastType, "toastType");
                    h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
                } else {
                    n7.z.b(null, false, AbstractC0644z.f7158b, 15);
                }
                break;
            case 1:
                if (this.f6726m) {
                    ToastType toastType2 = ToastType.DRIVING_DISABLED_WARNING;
                    A7.m.f(toastType2, "toastType");
                    h6.e.c(h6.e.d(toastType2), new b0.b(-1064300120, new C0618s1(toastType2, 2), true));
                } else {
                    n7.z.b(null, false, AbstractC0644z.c, 15);
                }
                break;
            case 2:
                break;
            case 3:
                if (this.f6726m) {
                    ToastType toastType3 = ToastType.DRIVING_DISABLED_WITHOUT_PASSENGER;
                    A7.m.f(toastType3, "toastType");
                    h6.e.c(h6.e.d(toastType3), new b0.b(-1064300120, new C0618s1(toastType3, 2), true));
                }
                break;
            default:
                n7.z.b(null, this.f6726m, J.f6395a, 7);
                break;
        }
        return m7.q.f19267a;
    }
}
