package defpackage;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import kotlinx.coroutines.android.AndroidExceptionPreHandler;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: b, reason: case insensitive filesystem */
/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0175b {
    public static int a(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 9;
            case 9:
                return 10;
            case 10:
                return 11;
            case 11:
                return 12;
            case 12:
                return 13;
            case 13:
                return 14;
            case 14:
                return 15;
            default:
                switch (i) {
                    case 100:
                        return 16;
                    case 101:
                        return 17;
                    case 102:
                        return 18;
                    case 103:
                        return 19;
                    case 104:
                        return 20;
                    default:
                        return 0;
                }
        }
    }

    public static /* synthetic */ int b(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 3;
        }
        throw null;
    }

    public static /* synthetic */ int c(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 12;
        }
        throw null;
    }

    public static /* synthetic */ int d(int i) {
        if (i == 1) {
            return -1;
        }
        if (i == 2) {
            return 0;
        }
        throw null;
    }

    public static int e(int i, int i2, int i3, int i4) {
        return ((i * i2) + i3) * i4;
    }

    public static int f(int i, int i2, int i3, qh0 qh0Var, String str, boolean z, int i4, int i5) {
        qh0Var.m(str, (i * i2) + i3, z);
        return i4 + i5;
    }

    public static String g(int i, int i2, int i3, e70 e70Var, boolean z) {
        return e70Var.w((i * i2) + i3, z);
    }

    public static String h(RecyclerView recyclerView, StringBuilder sb) {
        sb.append(recyclerView.z());
        return sb.toString();
    }

    public static String i(String str, Fragment fragment, String str2) {
        return str + fragment + str2;
    }

    public static String j(String str, String str2) {
        return str + str2;
    }

    public static String k(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static StringBuilder l(int i, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(str);
        return sb;
    }

    public static StringBuilder m(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(i);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder n(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static /* synthetic */ Iterator o() {
        try {
            return Arrays.asList(new AndroidExceptionPreHandler()).iterator();
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    public static PropertyModel p(HashMap map, PropertyModel.l lVar, PropertyModel.g gVar, HashMap map2) {
        map.put(lVar, gVar);
        return new PropertyModel(map2);
    }

    public static /* synthetic */ void q(int i, String str) {
        if (i != 0) {
            return;
        }
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        NullPointerException nullPointerException = new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
        i21.f(i21.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static /* synthetic */ void r(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static void s(String str, int i, String str2) {
        Log.w(str2, str + i);
    }
}
