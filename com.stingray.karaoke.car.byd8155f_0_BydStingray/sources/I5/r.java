package I5;

import S8.InterfaceC0725w;
import V8.f0;
import android.content.Intent;
import android.content.IntentFilter;
import com.stingray.companion.CompanionManager;
import com.stingray.companion.model.CompanionAppMode;
import com.stingray.companion.model.HostConfig;
import com.stingray.drivemodedetector.DriveModeDetector;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.Locale;
import k.C1587g;
import k6.C1699g;
import k6.C1704l;
import k6.C1705m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ MainActivity l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(MainActivity mainActivity, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = mainActivity;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new r(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        r rVar = (r) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        rVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        int i7 = MainActivity.f12974j0;
        MainActivity mainActivity = this.l;
        if (mainActivity.t().e() == LoadingInterface$LoadingState.Done && !mainActivity.f12987R) {
            mainActivity.f12987R = true;
            C6.b bVar = mainActivity.f12993X;
            if (bVar == null) {
                A7.m.j("navigationLogger");
                throw null;
            }
            bVar.a("Homepage", null);
            C1704l c1704l = mainActivity.f12992W;
            if (c1704l == null) {
                A7.m.j("driveModeManager");
                throw null;
            }
            C1587g c1587g = mainActivity.f13004i0;
            A7.m.f(c1587g, "activityResultLauncher");
            DriveModeDetector driveModeDetector = c1704l.f18273b;
            if (driveModeDetector.checkForPermissions(mainActivity)) {
                c1704l.a();
            } else {
                driveModeDetector.askForPermissions(c1587g);
            }
            C1705m c1705m = mainActivity.f12997b0;
            if (c1705m == null) {
                A7.m.j("languageManager");
                throw null;
            }
            c1705m.c.l(Locale.getDefault().toLanguageTag());
            if (c1705m.f18281b == null) {
                c1705m.f18281b = new h2.r(c1705m, 2);
                IntentFilter intentFilter = new IntentFilter("android.intent.action.LOCALE_CHANGED");
                c1705m.f18280a.registerReceiver(c1705m.f18281b, intentFilter);
            }
            ArrayList arrayList = new ArrayList();
            CompanionManager companionManager = mainActivity.f12994Y;
            if (companionManager == null) {
                A7.m.j("companionManager");
                throw null;
            }
            C1699g c1699g = mainActivity.f12996a0;
            if (c1699g == null) {
                A7.m.j("deviceManager");
                throw null;
            }
            String strA = c1699g.a();
            int i10 = Integer.parseInt("1287");
            Integer num = new Integer(100);
            CompanionAppMode companionAppMode = CompanionAppMode.FULL;
            k6.r rVar = mainActivity.f12999d0;
            if (rVar == null) {
                A7.m.j("parentalControlsManager");
                throw null;
            }
            companionManager.connect(strA, i10, "karaoke_android_car_byd", new HostConfig(num, companionAppMode, (Boolean) ((f0) rVar.f18294e.l).getValue(), null, arrayList, 8, null));
            mainActivity.q().i(false);
            Intent intent = mainActivity.getIntent();
            if (intent != null) {
                mainActivity.o().e(intent);
            }
        }
        return m7.q.f19267a;
    }
}
