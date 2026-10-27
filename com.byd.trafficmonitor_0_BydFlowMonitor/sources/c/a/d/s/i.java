package c.a.d.s;

import android.annotation.SuppressLint;
import android.content.Context;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: NetworkPolicyUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile i f3687a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f3688b;

    @SuppressLint({"DiscouragedPrivateApi"})
    public i(Context context) {
        try {
            this.f3688b = Class.forName("android.net.NetworkPolicyManager").getDeclaredMethod("from", Context.class).invoke(null, context);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }

    public static i c(Context context) {
        if (f3687a == null) {
            synchronized (i.class) {
                if (f3687a == null) {
                    f3687a = new i(context);
                }
            }
        }
        return f3687a;
    }

    public boolean a(int i2) {
        try {
            BydLogHelper.logD("disableMobileTraffic value9,uid:" + i2);
            Class<?> cls = this.f3688b.getClass();
            Class<?> cls2 = Integer.TYPE;
            cls.getDeclaredMethod("setUidPolicy", cls2, cls2).invoke(this.f3688b, Integer.valueOf(i2), 9);
            BydLogHelper.logD("disableMobileTraffic success");
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean b(int i2) {
        try {
            BydLogHelper.logD("enableMobileTraffic");
            Class<?> cls = this.f3688b.getClass();
            Class<?> cls2 = Integer.TYPE;
            cls.getDeclaredMethod("setUidPolicy", cls2, cls2).invoke(this.f3688b, Integer.valueOf(i2), 0);
            BydLogHelper.logD("enableMobileTraffic success");
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean d(int i2) {
        try {
            Object objInvoke = this.f3688b.getClass().getDeclaredMethod("getUidPolicy", Integer.TYPE).invoke(this.f3688b, Integer.valueOf(i2));
            if (objInvoke instanceof Integer) {
                return ((Integer) objInvoke).intValue() == 9;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return false;
    }
}
