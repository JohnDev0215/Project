package b.f.e;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.graphics.drawable.IconCompat;
import b.f.e.e;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatJellybean.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f1605b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1606c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f1604a = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f1607d = new Object();

    public static SparseArray<Bundle> a(List<Bundle> list) {
        int size = list.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            Bundle bundle = list.get(i2);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                sparseArray.put(i2, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle b(e.a aVar) {
        Bundle bundle = new Bundle();
        IconCompat iconCompatE = aVar.e();
        bundle.putInt("icon", iconCompatE != null ? iconCompatE.c() : 0);
        bundle.putCharSequence("title", aVar.i());
        bundle.putParcelable("actionIntent", aVar.a());
        Bundle bundle2 = aVar.d() != null ? new Bundle(aVar.d()) : new Bundle();
        bundle2.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        bundle.putBundle("extras", bundle2);
        bundle.putParcelableArray("remoteInputs", e(aVar.f()));
        bundle.putBoolean("showsUserInterface", aVar.h());
        bundle.putInt("semanticAction", aVar.g());
        return bundle;
    }

    public static Bundle c(Notification notification) {
        synchronized (f1604a) {
            if (f1606c) {
                return null;
            }
            try {
                if (f1605b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        f1606c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    f1605b = declaredField;
                }
                Bundle bundle = (Bundle) f1605b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    f1605b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e2) {
                Log.e("NotificationCompat", "Unable to access notification extras", e2);
                f1606c = true;
                return null;
            } catch (NoSuchFieldException e3) {
                Log.e("NotificationCompat", "Unable to access notification extras", e3);
                f1606c = true;
                return null;
            }
        }
    }

    public static Bundle d(h hVar) {
        new Bundle();
        throw null;
    }

    public static Bundle[] e(h[] hVarArr) {
        if (hVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[hVarArr.length];
        for (int i2 = 0; i2 < hVarArr.length; i2++) {
            bundleArr[i2] = d(hVarArr[i2]);
        }
        return bundleArr;
    }

    public static Bundle f(Notification.Builder builder, e.a aVar) {
        IconCompat iconCompatE = aVar.e();
        builder.addAction(iconCompatE != null ? iconCompatE.c() : 0, aVar.i(), aVar.a());
        Bundle bundle = new Bundle(aVar.d());
        if (aVar.f() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", e(aVar.f()));
        }
        if (aVar.c() != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", e(aVar.c()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        return bundle;
    }
}
