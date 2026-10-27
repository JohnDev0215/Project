package defpackage;

import android.os.IBinder;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: cg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC0615cg extends AbstractBinderC0503ag {
    public final Object a;

    public BinderC0615cg(Object obj) {
        this.a = obj;
    }

    public static Object w(InterfaceC0559bg interfaceC0559bg) {
        if (interfaceC0559bg instanceof BinderC0615cg) {
            return ((BinderC0615cg) interfaceC0559bg).a;
        }
        IBinder iBinderAsBinder = interfaceC0559bg.asBinder();
        Field[] declaredFields = iBinderAsBinder.getClass().getDeclaredFields();
        Field field = null;
        int i = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i++;
                field = field2;
            }
        }
        if (i != 1) {
            int length = declaredFields.length;
            StringBuilder sb = new StringBuilder(64);
            sb.append("Unexpected number of IObjectWrapper declared fields: ");
            sb.append(length);
            throw new IllegalArgumentException(sb.toString());
        }
        if (field.isAccessible()) {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        field.setAccessible(true);
        try {
            return field.get(iBinderAsBinder);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Could not access the field in remoteBinder.", e);
        } catch (NullPointerException e2) {
            throw new IllegalArgumentException("Binder object is null.", e2);
        }
    }
}
