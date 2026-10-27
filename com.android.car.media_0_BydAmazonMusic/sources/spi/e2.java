package spi;

import android.car.XCar;
import android.car.drivingstate.XCarUxRestrictions;
import android.car.drivingstate.XCarUxRestrictionsManager;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: CarUxRestrictionsUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e2 {
    public static e2 d;

    @NonNull
    public XCarUxRestrictions a = new XCarUxRestrictions.Builder(true, 511, 0).build();
    public final Set<a> b = Collections.newSetFromMap(new WeakHashMap());
    public final q6 c;

    /* JADX INFO: compiled from: CarUxRestrictionsUtil.java */
    public interface a {
        void a(@NonNull XCarUxRestrictions xCarUxRestrictions);
    }

    private e2(Context context) {
        q6 q6Var = new q6(this);
        this.c = q6Var;
        try {
            try {
                XCarUxRestrictionsManager xCarUxRestrictionsManager = (XCarUxRestrictionsManager) XCar.createCar(context).getCarManager(XCar.CAR_UX_RESTRICTION_SERVICE);
                xCarUxRestrictionsManager.registerListener(q6Var);
                q6Var.onUxRestrictionsChanged(xCarUxRestrictionsManager.getCurrentCarUxRestrictions());
            } catch (Exception e) {
                Log.e("CarUxRestrictionsUtil", "Car not connected", e);
            }
        } catch (RuntimeException e2) {
            Log.w("CarUxRestrictionsUtil", "Unable to connect to car service, assuming unrestricted", e2);
            this.c.onUxRestrictionsChanged(new XCarUxRestrictions.Builder(false, 0, 0L).build());
        }
    }

    @NonNull
    public static e2 a(Context context) {
        if (d == null) {
            d = new e2(context);
        }
        return d;
    }

    public static boolean b(int i, @Nullable XCarUxRestrictions xCarUxRestrictions) {
        return xCarUxRestrictions == null || (i & xCarUxRestrictions.getActiveRestrictions()) != 0;
    }

    public void register(a aVar) {
        this.b.add(aVar);
        aVar.a(this.a);
    }

    public void unregister(a aVar) {
        this.b.remove(aVar);
    }
}
