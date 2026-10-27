package p;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class wv0 {
    public static final ae1 a = new ae1(16);
    public static final ThreadPoolExecutor b;
    public static final Object c;
    public static final kd2 d;

    static {
        q32 q32Var = new q32();
        q32Var.c = "fonts-androidx";
        q32Var.e = 10;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, WebgateAuthorizer.COSMOS_TIMEOUT_MS, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), q32Var);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        b = threadPoolExecutor;
        c = new Object();
        d = new kd2(0);
    }

    public static vv0 a(String str, Context context, pv0 pv0Var, int i) throws Throwable {
        ae1 ae1Var = a;
        Typeface typeface = (Typeface) ae1Var.b(str);
        if (typeface != null) {
            return new vv0(typeface);
        }
        try {
            j6 j6VarL = ux2.l(context, pv0Var);
            int i2 = j6VarL.c;
            Object obj = j6VarL.e;
            int i3 = 1;
            if (i2 != 0) {
                i3 = i2 != 1 ? -3 : -2;
            } else {
                cw0[] cw0VarArr = (cw0[]) obj;
                if (cw0VarArr != null && cw0VarArr.length != 0) {
                    int length = cw0VarArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length) {
                            i3 = 0;
                            break;
                        }
                        int i5 = cw0VarArr[i4].e;
                        if (i5 == 0) {
                            i4++;
                        } else if (i5 >= 0) {
                            i3 = i5;
                        }
                    }
                }
            }
            if (i3 != 0) {
                return new vv0(i3);
            }
            Typeface typefaceP = ro2.a.p(context, (cw0[]) obj, i);
            if (typefaceP == null) {
                return new vv0(-3);
            }
            ae1Var.c(str, typefaceP);
            return new vv0(typefaceP);
        } catch (PackageManager.NameNotFoundException unused) {
            return new vv0(-1);
        }
    }
}
