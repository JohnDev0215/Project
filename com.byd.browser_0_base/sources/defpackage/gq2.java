package defpackage;

import android.hardware.bydauto.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Arrays;
import java.util.HashMap;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.n;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gq2 implements n.a {
    public static final /* synthetic */ int[] e = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};

    public static final void a(int i, View view) {
        if (i == 0) {
            throw null;
        }
        int i2 = i - 1;
        if (i2 == 0) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(view);
                return;
            }
            return;
        }
        if (i2 == 1) {
            view.setVisibility(0);
        } else if (i2 == 2) {
            view.setVisibility(8);
        } else {
            if (i2 != 3) {
                return;
            }
            view.setVisibility(4);
        }
    }

    public static /* synthetic */ int b(int i) {
        if (i == 1) {
            return -1;
        }
        if (i == 2) {
            return 0;
        }
        if (i == 3) {
            return 1;
        }
        if (i == 4) {
            return 2;
        }
        throw null;
    }

    public static e70 c(gq0 gq0Var) {
        e70 e70Var = new e70(gq0Var);
        e70Var.b();
        return e70Var;
    }

    public static Integer d(int i, HashMap map, Integer num, int i2) {
        map.put(num, Integer.valueOf(i));
        return Integer.valueOf(i2);
    }

    public static String e(String str, int i) {
        return str + i;
    }

    public static String f(String str, String str2) {
        return str + str2;
    }

    public static String g(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String i(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static void j(String str, int i, String str2) {
        Log.v(str2, str + i);
    }

    public static void k(int[] iArr, StringBuilder sb, String str) {
        sb.append(Arrays.toString(iArr));
        android.util.Log.i(str, sb.toString());
    }

    public static boolean l(String str) {
        return oj2.k(Profile.d()).a(str);
    }

    public static /* synthetic */ int m(int i) {
        if (i != 0) {
            return i - 1;
        }
        throw null;
    }

    public static /* synthetic */ String n(int i) {
        return i == 1 ? "UNKNOWN" : i == 2 ? "HORIZONTAL_DIMENSION" : i == 3 ? "VERTICAL_DIMENSION" : i == 4 ? "LEFT" : i == 5 ? "RIGHT" : i == 6 ? "TOP" : i == 7 ? "BOTTOM" : i == 8 ? "BASELINE" : "null";
    }

    public static /* synthetic */ String o(int i) {
        return i == 1 ? "REMOVED" : i == 2 ? "VISIBLE" : i == 3 ? "GONE" : i == 4 ? "INVISIBLE" : "null";
    }

    public static /* synthetic */ int[] p(int i) {
        int[] iArr = new int[i];
        System.arraycopy(e, 0, iArr, 0, i);
        return iArr;
    }

    @Override // org.chromium.ui.modelutil.n.a
    public void h(Object obj, Object obj2) {
        cx1 cx1Var = (cx1) obj;
        j.a(((xa1) obj2).b, cx1Var.e, cx1Var.y, true);
    }
}
