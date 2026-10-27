package d;

import android.content.Context;
import android.content.Intent;
import d.a;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ActivityResultContracts.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a<String[], Map<String, Boolean>> {
    static Intent e(String[] strArr) {
        return new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr);
    }

    @Override // d.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Intent a(Context context, String[] strArr) {
        return e(strArr);
    }

    @Override // d.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public a.C0061a<Map<String, Boolean>> b(Context context, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new a.C0061a<>(Collections.emptyMap());
        }
        n.a aVar = new n.a();
        boolean z6 = true;
        for (String str : strArr) {
            boolean z7 = x.a.a(context, str) == 0;
            aVar.put(str, Boolean.valueOf(z7));
            if (!z7) {
                z6 = false;
            }
        }
        if (z6) {
            return new a.C0061a<>(aVar);
        }
        return null;
    }

    @Override // d.a
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public Map<String, Boolean> c(int i7, Intent intent) {
        if (i7 != -1) {
            return Collections.emptyMap();
        }
        if (intent == null) {
            return Collections.emptyMap();
        }
        String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        if (intArrayExtra == null || stringArrayExtra == null) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        int length = stringArrayExtra.length;
        for (int i8 = 0; i8 < length; i8++) {
            map.put(stringArrayExtra[i8], Boolean.valueOf(intArrayExtra[i8] == 0));
        }
        return map;
    }
}
