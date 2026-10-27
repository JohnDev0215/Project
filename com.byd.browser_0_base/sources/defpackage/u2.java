package defpackage;

import android.content.Intent;
import androidx.activity.ComponentActivity;
import defpackage.t2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: ActivityResultContracts.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nActivityResultContracts.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ActivityResultContracts.kt\nandroidx/activity/result/contract/ActivityResultContracts$RequestMultiplePermissions\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,959:1\n12541#2,2:960\n8676#2,2:962\n9358#2,4:964\n11365#2:968\n11700#2,3:969\n*S KotlinDebug\n*F\n+ 1 ActivityResultContracts.kt\nandroidx/activity/result/contract/ActivityResultContracts$RequestMultiplePermissions\n*L\n188#1:960,2\n195#1:962,2\n195#1:964,4\n208#1:968\n208#1:969,3\n*E\n"})
public final class u2 extends t2<String[], Map<String, Boolean>> {
    @Override // defpackage.t2
    public final Intent a(ComponentActivity componentActivity, Object obj) {
        String[] strArr = (String[]) obj;
        i21.e(componentActivity, "context");
        i21.e(strArr, "input");
        Intent intentPutExtra = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr);
        i21.d(intentPutExtra, "Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)");
        return intentPutExtra;
    }

    @Override // defpackage.t2
    public final t2.a b(ComponentActivity componentActivity, Object obj) {
        String[] strArr = (String[]) obj;
        i21.e(componentActivity, "context");
        i21.e(strArr, "input");
        boolean z = true;
        if (strArr.length == 0) {
            return new t2.a(hh0.e);
        }
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (!(bz.a(componentActivity, strArr[i]) == 0)) {
                z = false;
                break;
            }
            i++;
        }
        if (!z) {
            return null;
        }
        int iM = oj2.M(strArr.length);
        if (iM < 16) {
            iM = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iM);
        for (String str : strArr) {
            linkedHashMap.put(str, Boolean.TRUE);
        }
        return new t2.a(linkedHashMap);
    }

    @Override // defpackage.t2
    public final Object c(Intent intent, int i) {
        hh0 hh0Var = hh0.e;
        if (i != -1 || intent == null) {
            return hh0Var;
        }
        String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        if (intArrayExtra == null || stringArrayExtra == null) {
            return hh0Var;
        }
        ArrayList arrayList = new ArrayList(intArrayExtra.length);
        for (int i2 : intArrayExtra) {
            arrayList.add(Boolean.valueOf(i2 == 0));
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str : stringArrayExtra) {
            if (str != null) {
                arrayList2.add(str);
            }
        }
        Iterator it = arrayList2.iterator();
        Iterator it2 = arrayList.iterator();
        ArrayList arrayList3 = new ArrayList(Math.min(arrayList2.size(), arrayList.size()));
        while (it.hasNext() && it2.hasNext()) {
            arrayList3.add(new ov1(it.next(), it2.next()));
        }
        return zb1.i0(arrayList3);
    }
}
