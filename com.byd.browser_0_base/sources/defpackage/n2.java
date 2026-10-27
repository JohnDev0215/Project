package defpackage;

import android.text.TextUtils;
import androidx.activity.ComponentActivity;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: compiled from: ActivityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class n2 extends bz {
    public static final /* synthetic */ int b = 0;

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface a {
        void K0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void e(int i, ComponentActivity componentActivity, String[] strArr) {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (TextUtils.isEmpty(strArr[i2])) {
                throw new IllegalArgumentException(gq2.i(new StringBuilder("Permission request for permissions "), Arrays.toString(strArr), " must not contain null or empty values"));
            }
            if (!yj.a() && TextUtils.equals(strArr[i2], "android.permission.POST_NOTIFICATIONS")) {
                hashSet.add(Integer.valueOf(i2));
            }
        }
        int size = hashSet.size();
        String[] strArr2 = size > 0 ? new String[strArr.length - size] : strArr;
        if (size > 0) {
            if (size == strArr.length) {
                return;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < strArr.length; i4++) {
                if (!hashSet.contains(Integer.valueOf(i4))) {
                    strArr2[i3] = strArr[i4];
                    i3++;
                }
            }
        }
        if (componentActivity instanceof a) {
            ((a) componentActivity).K0();
        }
        componentActivity.requestPermissions(strArr, i);
    }
}
