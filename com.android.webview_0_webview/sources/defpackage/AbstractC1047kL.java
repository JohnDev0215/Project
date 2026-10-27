package defpackage;

import J.N;
import android.os.Bundle;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.policy.CombinedPolicyProvider;
import org.chromium.policy.PolicyConverter;
import org.json.JSONException;

/* JADX INFO: renamed from: kL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1047kL {
    public CombinedPolicyProvider a;
    public int b = -1;

    public void a(Bundle bundle) {
        ThreadUtils.b();
        CombinedPolicyProvider combinedPolicyProvider = this.a;
        combinedPolicyProvider.d.set(this.b, bundle);
        Iterator it = combinedPolicyProvider.d.iterator();
        while (it.hasNext()) {
            if (((Bundle) it.next()) == null) {
                return;
            }
        }
        if (combinedPolicyProvider.a == 0) {
            return;
        }
        for (Bundle bundle2 : combinedPolicyProvider.d) {
            for (String str : bundle2.keySet()) {
                PolicyConverter policyConverter = combinedPolicyProvider.b;
                Object obj = bundle2.get(str);
                Objects.requireNonNull(policyConverter);
                if (obj instanceof Boolean) {
                    N.MpCbKnQi(policyConverter.a, policyConverter, str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof String) {
                    N.MeuOZAup(policyConverter.a, policyConverter, str, (String) obj);
                } else if (obj instanceof Integer) {
                    N.MTuFp9B7(policyConverter.a, policyConverter, str, ((Integer) obj).intValue());
                } else if (obj instanceof String[]) {
                    N.MGY2QXB9(policyConverter.a, policyConverter, str, (String[]) obj);
                } else if (obj instanceof Bundle) {
                    Bundle bundle3 = (Bundle) obj;
                    try {
                        N.MeuOZAup(policyConverter.a, policyConverter, str, policyConverter.b(bundle3).toString());
                    } catch (JSONException unused) {
                        StringBuilder sbF = Z7.f("Invalid bundle in app restrictions ");
                        sbF.append(bundle3.toString());
                        sbF.append(" for key ");
                        sbF.append(str);
                        Ts.f("PolicyConverter", sbF.toString(), new Object[0]);
                    }
                } else if (obj instanceof Bundle[]) {
                    Bundle[] bundleArr = (Bundle[]) obj;
                    try {
                        N.MeuOZAup(policyConverter.a, policyConverter, str, policyConverter.a(bundleArr).toString());
                    } catch (JSONException unused2) {
                        StringBuilder sbF2 = Z7.f("Invalid bundle array in app restrictions ");
                        sbF2.append(Arrays.toString(bundleArr));
                        sbF2.append(" for key ");
                        sbF2.append(str);
                        Ts.f("PolicyConverter", sbF2.toString(), new Object[0]);
                    }
                }
            }
        }
        N.M4QXUjg3(combinedPolicyProvider.a, CombinedPolicyProvider.a());
    }

    public abstract void b();
}
