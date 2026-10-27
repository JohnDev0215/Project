package x4;

import android.annotation.SuppressLint;
import android.content.Context;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: NetworkPolicyUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile e f9088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f9089b;

    @SuppressLint({"DiscouragedPrivateApi"})
    public e(Context context) {
        try {
            this.f9089b = Class.forName("android.net.NetworkPolicyManager").getDeclaredMethod("from", Context.class).invoke(null, context);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            e8.printStackTrace();
        }
    }

    public static e c(Context context) {
        if (f9088a == null) {
            synchronized (e.class) {
                if (f9088a == null) {
                    f9088a = new e(context.getApplicationContext());
                }
            }
        }
        return f9088a;
    }

    public boolean a(int i8) {
        try {
            BydLogHelper.logD("disableMobileTraffic value9,uid:" + i8);
            Class<?> cls = this.f9089b.getClass();
            Class<?> cls2 = Integer.TYPE;
            cls.getDeclaredMethod("setUidPolicy", cls2, cls2).invoke(this.f9089b, Integer.valueOf(i8), 9);
            BydLogHelper.logD("disableMobileTraffic success");
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            e8.printStackTrace();
            return false;
        }
    }

    public boolean b(int i8) {
        try {
            BydLogHelper.logD("enableMobileTraffic");
            Class<?> cls = this.f9089b.getClass();
            Class<?> cls2 = Integer.TYPE;
            cls.getDeclaredMethod("setUidPolicy", cls2, cls2).invoke(this.f9089b, Integer.valueOf(i8), 0);
            BydLogHelper.logD("enableMobileTraffic success");
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            e8.printStackTrace();
            return false;
        }
    }

    public boolean d(int i8) {
        try {
            Object objInvoke = this.f9089b.getClass().getDeclaredMethod("getUidPolicy", Integer.TYPE).invoke(this.f9089b, Integer.valueOf(i8));
            if (objInvoke instanceof Integer) {
                return ((Integer) objInvoke).intValue() == 9;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            e8.printStackTrace();
        }
        return false;
    }
}
