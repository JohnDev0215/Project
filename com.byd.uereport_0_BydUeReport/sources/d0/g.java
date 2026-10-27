package d0;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.graphics.drawable.IconCompat;
import d0.e;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatJellybean.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f4329b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f4330c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f4328a = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f4331d = new Object();

    public static SparseArray<Bundle> a(List<Bundle> list) {
        int size = list.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i8 = 0; i8 < size; i8++) {
            Bundle bundle = list.get(i8);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                sparseArray.put(i8, bundle);
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
        synchronized (f4328a) {
            if (f4330c) {
                return null;
            }
            try {
                if (f4329b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        f4330c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    f4329b = declaredField;
                }
                Bundle bundle = (Bundle) f4329b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    f4329b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e8) {
                Log.e("NotificationCompat", "Unable to access notification extras", e8);
                f4330c = true;
                return null;
            } catch (NoSuchFieldException e9) {
                Log.e("NotificationCompat", "Unable to access notification extras", e9);
                f4330c = true;
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
        for (int i8 = 0; i8 < hVarArr.length; i8++) {
            bundleArr[i8] = d(hVarArr[i8]);
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
