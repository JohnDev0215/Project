package a0;

import a0.d;
import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.graphics.drawable.IconCompat;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatJellybean.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f66b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f67c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f65a = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f68d = new Object();

    public static SparseArray<Bundle> a(List<Bundle> list) {
        int size = list.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i3 = 0; i3 < size; i3++) {
            Bundle bundle = list.get(i3);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                sparseArray.put(i3, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle b(d.a aVar) {
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
        synchronized (f65a) {
            if (f67c) {
                return null;
            }
            try {
                if (f66b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        f67c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    f66b = declaredField;
                }
                Bundle bundle = (Bundle) f66b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    f66b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e3) {
                Log.e("NotificationCompat", "Unable to access notification extras", e3);
                f67c = true;
                return null;
            } catch (NoSuchFieldException e4) {
                Log.e("NotificationCompat", "Unable to access notification extras", e4);
                f67c = true;
                return null;
            }
        }
    }

    public static Bundle d(g gVar) {
        new Bundle();
        throw null;
    }

    public static Bundle[] e(g[] gVarArr) {
        if (gVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[gVarArr.length];
        for (int i3 = 0; i3 < gVarArr.length; i3++) {
            bundleArr[i3] = d(gVarArr[i3]);
        }
        return bundleArr;
    }

    public static Bundle f(Notification.Builder builder, d.a aVar) {
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
