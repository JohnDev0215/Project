package defpackage;

import android.content.pm.ApplicationInfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: px, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1354px implements InvocationHandler {
    public Object a;

    public C1354px(Object obj) {
        this.a = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            Object objInvoke = method.invoke(this.a, objArr);
            if (method.getReturnType() == ApplicationInfo.class) {
                ApplicationInfo applicationInfo = (ApplicationInfo) objInvoke;
                if (applicationInfo.metaData != null) {
                    applicationInfo.metaData.remove("preloaded_fonts");
                }
            }
            return objInvoke;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Reflection failed when proxying IPackageManager", e);
        } catch (InvocationTargetException e2) {
            throw e2.getTargetException();
        }
    }
}
