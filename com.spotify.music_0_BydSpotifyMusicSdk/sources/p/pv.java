package p;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle$Event;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pv implements u62 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ pv(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // p.u62
    public final Bundle a() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                androidx.activity.a aVar = (androidx.activity.a) obj;
                aVar.getClass();
                Bundle bundle = new Bundle();
                sv svVar = aVar.r;
                svVar.getClass();
                HashMap map = svVar.c;
                bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(map.values()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(map.keySet()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(svVar.e));
                bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) svVar.h.clone());
                bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", svVar.a);
                return bundle;
            case 1:
                zw0 zw0Var = (zw0) obj;
                int i2 = zw0.E;
                while (zw0.t(((yw0) zw0Var.z.e).t)) {
                }
                zw0Var.A.f(Lifecycle$Event.ON_STOP);
                return new Bundle();
            default:
                return ((ox0) obj).R();
        }
    }
}
