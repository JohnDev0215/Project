package spi;

import android.app.Application;
import android.car.XCarRoute;
import android.car.drivingstate.IXCarUxRestrictionsManager;
import android.car.test.ITestViewControl;
import android.content.Context;
import android.util.Pair;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import java.util.List;

/* JADX INFO: compiled from: TestViewControl.java */
/* JADX INFO: loaded from: classes2.dex */
@Route(path = XCarRoute.CAR_TEST_SETTING)
public final class va implements ITestViewControl {
    public transient ua a = null;
    public transient boolean b = false;
    public final transient IXCarUxRestrictionsManager c = (IXCarUxRestrictionsManager) ARouter.getInstance().build(XCarRoute.CAR_UXR_SERVICE).navigation();

    /* JADX INFO: compiled from: TestViewControl.java */
    public class a {
        public a() {
        }
    }

    public va() {
        new a();
    }

    @Override // android.car.test.ITestViewControl
    public final void hide() {
        ua uaVar = this.a;
        if (uaVar == null || !uaVar.isShowing()) {
            return;
        }
        this.a.dismiss();
        this.a = null;
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public final void init(Context context) {
    }

    @Override // android.car.test.ITestViewControl
    public final Boolean isGearBoxP() {
        return Boolean.valueOf(this.b);
    }

    @Override // android.car.test.ITestViewControl
    public final boolean isLogOpen() {
        Object obj;
        Object obj2;
        Pair<List<String>, List<String>> pairA = da.a("getprop log.tag.BydAmazonMusic");
        return (pairA == null || (obj2 = pairA.second) == null || ((List) obj2).size() <= 0) && pairA != null && (obj = pairA.first) != null && ((List) obj).size() > 0 && ((String) ((List) pairA.first).get(0)).equals("V");
    }

    @Override // android.car.test.ITestViewControl
    public final void onClicked(Context context) {
    }

    @Override // android.car.test.ITestViewControl
    public final void onCreate(Application application) {
    }
}
