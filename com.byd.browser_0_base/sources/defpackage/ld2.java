package defpackage;

import java.util.HashMap;
import org.chromium.chrome.browser.safety_check.SafetyCheckCoordinator;
import org.chromium.chrome.browser.safety_check.SafetyCheckSettingsFragment;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: SafetyCheckCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ld2 implements bq1<b71> {
    public final /* synthetic */ SettingsLauncher a;
    public final /* synthetic */ a8 b;
    public final /* synthetic */ zp1 c;
    public final /* synthetic */ SafetyCheckCoordinator d;

    public ld2(SafetyCheckCoordinator safetyCheckCoordinator, n3 n3Var, a8 a8Var, aq1 aq1Var) {
        this.d = safetyCheckCoordinator;
        this.a = n3Var;
        this.b = a8Var;
        this.c = aq1Var;
    }

    @Override // defpackage.bq1
    public final void a(b71 b71Var) {
        b71 b71Var2 = b71Var;
        if (b71Var2 == null) {
            return;
        }
        SafetyCheckCoordinator safetyCheckCoordinator = this.d;
        if (safetyCheckCoordinator.g == null) {
            b71Var2.j1().a(safetyCheckCoordinator);
            SafetyCheckSettingsFragment safetyCheckSettingsFragment = safetyCheckCoordinator.e;
            HashMap mapE = PropertyModel.e(pd2.j);
            PropertyModel.p pVar = pd2.a;
            int i = zj.a;
            PropertyModel.d dVar = new PropertyModel.d();
            dVar.a = 0;
            mapE.put(pVar, dVar);
            PropertyModel.p pVar2 = pd2.b;
            PropertyModel.d dVar2 = new PropertyModel.d();
            dVar2.a = 0;
            mapE.put(pVar2, dVar2);
            PropertyModel.p pVar3 = pd2.c;
            PropertyModel.d dVar3 = new PropertyModel.d();
            dVar3.a = 0;
            mapE.put(pVar3, dVar3);
            PropertyModel.p pVar4 = pd2.d;
            PropertyModel.d dVar4 = new PropertyModel.d();
            dVar4.a = 0;
            mapE.put(pVar4, dVar4);
            PropertyModel.e eVar = new PropertyModel.e();
            eVar.a = 0L;
            mapE.put(pd2.i, eVar);
            PropertyModel propertyModel = new PropertyModel(mapE);
            j.b(propertyModel, safetyCheckSettingsFragment, new bg(18));
            safetyCheckCoordinator.g = new od2(propertyModel, safetyCheckCoordinator.f, this.a, this.b, this.c);
        }
    }
}
