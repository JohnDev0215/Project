package k6;

import S8.AbstractC0727y;
import V8.AbstractC0828q;
import V8.N;
import V8.f0;
import android.content.Context;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.drivemodedetector.DriveModeDetector;
import com.stingray.shared.logs.segment.DrivingStatus;
import com.stingray.shared.logs.segment.PassengerStatus;
import h8.AbstractC1387d;
import java.util.LinkedHashMap;
import timber.log.Timber;

/* JADX INFO: renamed from: k6.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1704l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1696d f18272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DriveModeDetector f18273b;
    public final f0 c;
    public final N d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f0 f18274e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final N f18275f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f0 f18276g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final N f18277h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public DriveMode f18278i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f18279j;

    public C1704l(Context context, C1696d c1696d) {
        A7.m.f(context, "appContext");
        A7.m.f(c1696d, "devSettingsManager");
        this.f18272a = c1696d;
        this.f18273b = new DriveModeDetector(context);
        DriveMode driveMode = DriveMode.PARKED;
        f0 f0VarC = AbstractC0828q.c(driveMode);
        this.c = f0VarC;
        this.d = new N(f0VarC);
        Boolean bool = Boolean.FALSE;
        f0 f0VarC2 = AbstractC0828q.c(bool);
        this.f18274e = f0VarC2;
        this.f18275f = new N(f0VarC2);
        f0 f0VarC3 = AbstractC0828q.c(bool);
        this.f18276g = f0VarC3;
        this.f18277h = new N(f0VarC3);
        this.f18278i = driveMode;
        AbstractC0727y.b();
    }

    public static void b(DriveMode driveMode) {
        LinkedHashMap linkedHashMap = A6.d.f439a;
        int i7 = AbstractC1702j.f18270a[driveMode.ordinal()];
        DrivingStatus drivingStatus = i7 != 1 ? i7 != 2 ? DrivingStatus.UNKNOWN : DrivingStatus.DRIVING : DrivingStatus.PARKED;
        A7.m.f(drivingStatus, "drivingStatus");
        A6.d.f439a.put("driving_status", drivingStatus.name());
    }

    public static void c(Boolean bool) {
        LinkedHashMap linkedHashMap = A6.d.f439a;
        PassengerStatus passengerStatus = bool.equals(Boolean.TRUE) ? PassengerStatus.DETECTED : bool.equals(Boolean.FALSE) ? PassengerStatus.UNDETECTED : PassengerStatus.UNKNOWN;
        A7.m.f(passengerStatus, "passengerStatus");
        A6.d.f439a.put("passenger_status", passengerStatus.name());
    }

    public final void a() throws Throwable {
        DriveModeDetector driveModeDetector = this.f18273b;
        f0 f0Var = this.f18276g;
        if (((Boolean) f0Var.getValue()).booleanValue()) {
            return;
        }
        try {
            driveModeDetector.initialize();
            driveModeDetector.addListener(new C1703k(this));
            DriveMode driveMode = driveModeDetector.getDriveMode();
            this.f18278i = driveMode;
            AbstractC1387d.d("DriveModeManager initialDriveMode: " + driveMode);
            this.c.l(this.f18278i);
            b(this.f18278i);
            boolean zIsPassengerPresent = driveModeDetector.isPassengerPresent();
            this.f18279j = zIsPassengerPresent;
            AbstractC1387d.d("DriveModeManager initialPassengerPresent: " + zIsPassengerPresent);
            d();
            c(Boolean.valueOf(this.f18279j));
            Boolean bool = Boolean.TRUE;
            f0Var.getClass();
            f0Var.m(null, bool);
        } catch (Exception e10) {
            String str = "DriveModeManager init Exception: " + e10.getMessage();
            A7.m.f(str, "error");
            B9.a aVar = Timber.f22577a;
            aVar.e("");
            aVar.b(str, new Object[0]);
        }
    }

    public final void d() {
        Boolean boolValueOf = Boolean.valueOf(this.f18279j);
        f0 f0Var = this.f18274e;
        f0Var.getClass();
        f0Var.m(null, boolValueOf);
    }
}
