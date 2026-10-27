package p;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qv implements sp1 {
    public final /* synthetic */ int a;
    public final /* synthetic */ androidx.activity.a b;

    public /* synthetic */ qv(zw0 zw0Var, int i) {
        this.a = i;
        this.b = zw0Var;
    }

    @Override // p.sp1
    public final void a() {
        int i = this.a;
        androidx.activity.a aVar = this.b;
        switch (i) {
            case 0:
                Bundle bundleA = aVar.m.b.a("android:support:activity-result");
                if (bundleA != null) {
                    sv svVar = aVar.r;
                    svVar.getClass();
                    ArrayList<Integer> integerArrayList = bundleA.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
                    ArrayList<String> stringArrayList = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
                    if (stringArrayList != null && integerArrayList != null) {
                        svVar.e = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                        svVar.a = (Random) bundleA.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
                        Bundle bundle = bundleA.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
                        Bundle bundle2 = svVar.h;
                        bundle2.putAll(bundle);
                        for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
                            String str = stringArrayList.get(i2);
                            HashMap map = svVar.c;
                            boolean zContainsKey = map.containsKey(str);
                            HashMap map2 = svVar.b;
                            if (zContainsKey) {
                                Integer num = (Integer) map.remove(str);
                                if (!bundle2.containsKey(str)) {
                                    map2.remove(num);
                                }
                            }
                            int iIntValue = integerArrayList.get(i2).intValue();
                            String str2 = stringArrayList.get(i2);
                            map2.put(Integer.valueOf(iIntValue), str2);
                            map.put(str2, Integer.valueOf(iIntValue));
                        }
                        break;
                    }
                }
                break;
            default:
                yw0 yw0Var = (yw0) ((zw0) aVar).z.e;
                yw0Var.t.b(yw0Var, yw0Var, null);
                break;
        }
    }
}
