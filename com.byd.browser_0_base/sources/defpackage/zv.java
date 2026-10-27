package defpackage;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zv implements ds1 {
    public final /* synthetic */ int a;
    public final /* synthetic */ ComponentActivity b;

    public /* synthetic */ zv(ComponentActivity componentActivity, int i) {
        this.a = i;
        this.b = componentActivity;
    }

    @Override // defpackage.ds1
    public final void a() {
        int i = this.a;
        ComponentActivity componentActivity = this.b;
        switch (i) {
            case 0:
                Bundle bundleA = componentActivity.i.b.a("android:support:activity-result");
                if (bundleA != null) {
                    ComponentActivity.b bVar = componentActivity.n;
                    bVar.getClass();
                    ArrayList<Integer> integerArrayList = bundleA.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
                    ArrayList<String> stringArrayList = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
                    if (stringArrayList != null && integerArrayList != null) {
                        bVar.d = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                        Bundle bundle = bundleA.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
                        Bundle bundle2 = bVar.g;
                        bundle2.putAll(bundle);
                        for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
                            String str = stringArrayList.get(i2);
                            HashMap map = bVar.b;
                            boolean zContainsKey = map.containsKey(str);
                            HashMap map2 = bVar.a;
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
                hp0<?> hp0Var = ((FragmentActivity) componentActivity).w.a;
                hp0Var.n.b(hp0Var, hp0Var, null);
                break;
        }
    }
}
