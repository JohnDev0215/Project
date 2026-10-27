package defpackage;

import android.os.IBinder;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: jS, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC0998jS extends PR {
    public final Object a;

    public BinderC0998jS(Object obj) {
        this.a = obj;
    }

    public static Object d(QR qr, Class cls) {
        if (qr == null) {
            return null;
        }
        if (qr instanceof BinderC0998jS) {
            return ((BinderC0998jS) qr).a;
        }
        IBinder iBinderAsBinder = qr.asBinder();
        Field[] declaredFields = iBinderAsBinder.getClass().getDeclaredFields();
        int length = declaredFields.length;
        int i = 0;
        Field field = null;
        while (true) {
            if (i >= length) {
                break;
            }
            Field field2 = declaredFields[i];
            if (!field2.isSynthetic()) {
                if (field != null) {
                    field = null;
                    break;
                }
                field = field2;
            }
            i++;
        }
        if (field == null || field.isAccessible()) {
            throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly *one* declared *private* field for the wrapped object.  Preferably, this is an instance of the ObjectWrapper<T> class.");
        }
        field.setAccessible(true);
        try {
            Object obj = field.get(iBinderAsBinder);
            if (obj == null) {
                return null;
            }
            if (cls.isInstance(obj)) {
                return cls.cast(obj);
            }
            throw new IllegalArgumentException("remoteBinder is the wrong class.");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Could not access the field in remoteBinder.", e);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("remoteBinder is the wrong class.", e2);
        } catch (NullPointerException e3) {
            throw new IllegalArgumentException("Binder object is null.", e3);
        }
    }
}
