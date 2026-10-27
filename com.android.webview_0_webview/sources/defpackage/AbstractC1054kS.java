package defpackage;

import android.os.Parcel;
import android.os.StrictMode;
import android.util.Log;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: kS, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1054kS {
    public static final int a;
    public static final Field b;

    static {
        Field declaredField;
        int iB;
        int i = 0;
        try {
            declaredField = StrictMode.ThreadPolicy.class.getDeclaredField("mask");
            declaredField.setAccessible(true);
            iB = b(declaredField);
        } catch (NoSuchFieldException e) {
            e = e;
            Log.w("StrictModeWorkaround", "StrictMode reflection exception", e);
            declaredField = null;
        } catch (SecurityException e2) {
            e = e2;
            Log.w("StrictModeWorkaround", "StrictMode reflection exception", e);
            declaredField = null;
        } catch (RuntimeException e3) {
            Log.w("StrictModeWorkaround", "StrictMode run time exception", e3);
            declaredField = null;
        }
        try {
            c(declaredField, 0);
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.writeInterfaceToken("StrictModeWorkaround");
            parcelObtain.setDataPosition(0);
            int i2 = parcelObtain.readInt();
            parcelObtain.recycle();
            c(declaredField, iB);
            i = i2;
            b = declaredField;
            a = i;
        } catch (Throwable th) {
            c(declaredField, iB);
            throw th;
        }
    }

    public static void a() {
        Field field = b;
        if (field == null) {
            return;
        }
        try {
            try {
                int i = field.getInt(StrictMode.getThreadPolicy());
                if ((a & i) == 0) {
                    return;
                }
                c(b, i & (~a));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException unused) {
        }
    }

    public static int b(Field field) {
        try {
            return field.getInt(StrictMode.getThreadPolicy());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void c(Field field, int i) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            field.setInt(threadPolicy, i);
            StrictMode.setThreadPolicy(threadPolicy);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
