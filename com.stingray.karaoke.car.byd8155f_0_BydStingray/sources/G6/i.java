package G6;

import A7.m;
import Q5.C0618s1;
import T.X;
import com.stingray.karaoke.car.utils.ToastType;
import m7.q;
import n7.z;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ X f2703m;

    public /* synthetic */ i(X x8, int i7) {
        this.l = i7;
        this.f2703m = x8;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                this.f2703m.setValue(Boolean.valueOf(!((Boolean) r5.getValue()).booleanValue()));
                break;
            case 1:
                this.f2703m.setValue(Boolean.valueOf(!((Boolean) r5.getValue()).booleanValue()));
                break;
            case 2:
                if (((Boolean) this.f2703m.getValue()).booleanValue()) {
                    ToastType toastType = ToastType.DRIVING_DISABLED_ERROR;
                    m.f(toastType, "toastType");
                    h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
                } else {
                    z.b(null, false, I5.f.f2951b, 15);
                }
                break;
            case 3:
                if (((Boolean) this.f2703m.getValue()).booleanValue()) {
                    ToastType toastType2 = ToastType.DRIVING_DISABLED_ERROR;
                    m.f(toastType2, "toastType");
                    h6.e.c(h6.e.d(toastType2), new b0.b(-1064300120, new C0618s1(toastType2, 2), true));
                } else {
                    z.b(null, false, I5.f.c, 15);
                }
                break;
            case 4:
                this.f2703m.setValue(Boolean.TRUE);
                break;
            case 5:
                this.f2703m.setValue(Boolean.FALSE);
                break;
            case 6:
                ((InterfaceC2772a) this.f2703m.getValue()).a();
                break;
            case 7:
                this.f2703m.setValue(Boolean.valueOf(!((Boolean) r5.getValue()).booleanValue()));
                break;
            case 8:
                this.f2703m.setValue(Boolean.valueOf(!((Boolean) r5.getValue()).booleanValue()));
                break;
            case 9:
                this.f2703m.setValue(Boolean.valueOf(!((Boolean) r5.getValue()).booleanValue()));
                break;
            case 10:
                this.f2703m.setValue(Boolean.TRUE);
                break;
            default:
                this.f2703m.setValue(Boolean.FALSE);
                break;
        }
        return q.f19267a;
    }

    public /* synthetic */ i(InterfaceC2772a interfaceC2772a, X x8) {
        this.l = 9;
        this.f2703m = x8;
    }
}
