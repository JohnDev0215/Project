package defpackage;

import J.N;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.components.policy.CombinedPolicyProvider;
import org.chromium.components.policy.PolicyConverter;
import org.json.JSONException;

/* JADX INFO: compiled from: PolicyProvider.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class p22 {
    public CombinedPolicyProvider a;
    public int b = -1;

    public final void a(Bundle bundle) {
        List<Bundle> listAsList;
        Object obj = ThreadUtils.a;
        CombinedPolicyProvider combinedPolicyProvider = this.a;
        int i = this.b;
        if (combinedPolicyProvider.a == 0) {
            return;
        }
        if (combinedPolicyProvider.c != null) {
            listAsList = Arrays.asList(bundle);
        } else {
            ArrayList arrayList = combinedPolicyProvider.e;
            arrayList.set(i, bundle);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((Bundle) it.next()) == null) {
                    return;
                }
            }
            listAsList = arrayList;
        }
        for (Bundle bundle2 : listAsList) {
            for (String str : bundle2.keySet()) {
                PolicyConverter policyConverter = combinedPolicyProvider.b;
                Object obj2 = bundle2.get(str);
                policyConverter.getClass();
                if (obj2 instanceof Boolean) {
                    N.MNcVehwq(policyConverter.a, policyConverter, str, ((Boolean) obj2).booleanValue());
                } else if (obj2 instanceof String) {
                    N.Mu_Zn154(policyConverter.a, policyConverter, str, (String) obj2);
                } else if (obj2 instanceof Integer) {
                    N.MX$FOTbg(policyConverter.a, policyConverter, str, ((Integer) obj2).intValue());
                } else if (obj2 instanceof String[]) {
                    N.MLFU3fY7(policyConverter.a, policyConverter, str, (String[]) obj2);
                } else if (obj2 instanceof Bundle) {
                    Bundle bundle3 = (Bundle) obj2;
                    try {
                        N.Mu_Zn154(policyConverter.a, policyConverter, str, PolicyConverter.b(bundle3).toString());
                    } catch (JSONException unused) {
                        Log.w("cr_PolicyConverter", "Invalid bundle in app restrictions " + bundle3.toString() + " for key " + str);
                    }
                } else if (obj2 instanceof Bundle[]) {
                    Bundle[] bundleArr = (Bundle[]) obj2;
                    try {
                        N.Mu_Zn154(policyConverter.a, policyConverter, str, PolicyConverter.a(bundleArr).toString());
                    } catch (JSONException unused2) {
                        Log.w("cr_PolicyConverter", "Invalid bundle array in app restrictions " + Arrays.toString(bundleArr) + " for key " + str);
                    }
                }
            }
        }
        N.M81oD3lB(combinedPolicyProvider.a, CombinedPolicyProvider.a());
    }

    public abstract void b();
}
