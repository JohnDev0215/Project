package Q5;

import T.C0735d;
import T.C0761q;
import com.stingray.drivemodedetector.DriveMode;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6289m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6290n;
    public final /* synthetic */ DriveMode o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6291p;

    public /* synthetic */ C1(g0.o oVar, InterfaceC2782k interfaceC2782k, DriveMode driveMode, int i7, int i10) {
        this.l = i10;
        this.f6289m = oVar;
        this.f6290n = interfaceC2782k;
        this.o = driveMode;
        this.f6291p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.f6291p | 1);
                io.sentry.config.a.z(this.f6289m, this.f6290n, this.o, c0761q, iZ);
                break;
            default:
                int iZ2 = C0735d.Z(this.f6291p | 1);
                io.sentry.config.a.A(this.f6289m, this.f6290n, this.o, c0761q, iZ2);
                break;
        }
        return m7.q.f19267a;
    }
}
