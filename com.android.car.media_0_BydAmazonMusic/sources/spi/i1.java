package spi;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatDelegate;
import com.android.car.ui.CarUiLayoutInflaterFactory;
import com.android.car.ui.R$attr;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.core.BaseLayoutController;
import com.android.car.ui.core.CarUiInstaller;
import java.lang.reflect.Constructor;
import java.util.HashSet;

/* JADX INFO: compiled from: CarUiInstaller.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i1 implements Application.ActivityLifecycleCallbacks {
    public Insets a = null;
    public boolean b = false;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (b2.i(activity, R$attr.carUiActivity)) {
            ComponentName componentNameCreateRelative = ComponentName.createRelative(activity, activity.getClass().getName());
            StringBuilder sbK = c.k("CarUiInstaller started for ");
            sbK.append(componentNameCreateRelative.flattenToShortString());
            Log.i("CarUiInstaller", sbK.toString());
            HashSet<Application> hashSet = CarUiInstaller.a;
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(activity);
            if (layoutInflaterFrom.getFactory2() == null) {
                layoutInflaterFrom.setFactory2(new CarUiLayoutInflaterFactory());
            } else if (!(layoutInflaterFrom.getFactory2() instanceof CarUiLayoutInflaterFactory) && !(layoutInflaterFrom.getFactory2() instanceof AppCompatDelegate)) {
                throw new AssertionError(layoutInflaterFrom.getFactory2() + " must extend CarUiLayoutInflaterFactory");
            }
            CarUiInstaller.a(activity.getClassLoader(), BaseLayoutController.class, "build", null, new Object[]{activity});
            if (bundle != null) {
                this.a = new Insets(bundle.getInt("CAR_UI_INSET_LEFT"), bundle.getInt("CAR_UI_INSET_TOP"), bundle.getInt("CAR_UI_INSET_RIGHT"), bundle.getInt("CAR_UI_INSET_BOTTOM"));
            }
            this.b = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (b2.i(activity, R$attr.carUiActivity)) {
            CarUiInstaller.a(activity.getClassLoader(), BaseLayoutController.class, "destroy", null, new Object[]{activity});
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostStarted(Activity activity) {
        if (b2.i(activity, R$attr.carUiActivity)) {
            Object objNewInstance = null;
            Object objA = CarUiInstaller.a(activity.getClassLoader(), BaseLayoutController.class, "getBaseLayoutController", null, new Object[]{activity});
            if (this.a == null || objA == null || !this.b) {
                return;
            }
            ClassLoader classLoader = activity.getClassLoader();
            Object[] objArr = new Object[1];
            ClassLoader classLoader2 = activity.getClassLoader();
            Insets insets = this.a;
            if (insets != null) {
                try {
                    Class<?> clsLoadClass = classLoader2.loadClass(Insets.class.getName());
                    Class<?> cls = Integer.TYPE;
                    Constructor<?> declaredConstructor = clsLoadClass.getDeclaredConstructor(cls, cls, cls, cls);
                    declaredConstructor.setAccessible(true);
                    objNewInstance = declaredConstructor.newInstance(Integer.valueOf(insets.getLeft()), Integer.valueOf(insets.getTop()), Integer.valueOf(insets.getRight()), Integer.valueOf(insets.getBottom()));
                } catch (ReflectiveOperationException | SecurityException unused) {
                    throw new RuntimeException();
                }
            }
            objArr[0] = objNewInstance;
            CarUiInstaller.a(classLoader, BaseLayoutController.class, "dispatchNewInsets", objA, objArr);
            this.b = false;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Object objA;
        if (b2.i(activity, R$attr.carUiActivity) && (objA = CarUiInstaller.a(activity.getClassLoader(), BaseLayoutController.class, "getBaseLayoutController", null, new Object[]{activity})) != null) {
            Object objA2 = CarUiInstaller.a(activity.getClassLoader(), BaseLayoutController.class, "getInsets", objA, new Object[0]);
            bundle.putInt("CAR_UI_INSET_LEFT", ((Integer) CarUiInstaller.a(activity.getClassLoader(), Insets.class, "getLeft", objA2, new Object[0])).intValue());
            bundle.putInt("CAR_UI_INSET_TOP", ((Integer) CarUiInstaller.a(activity.getClassLoader(), Insets.class, "getTop", objA2, new Object[0])).intValue());
            bundle.putInt("CAR_UI_INSET_RIGHT", ((Integer) CarUiInstaller.a(activity.getClassLoader(), Insets.class, "getRight", objA2, new Object[0])).intValue());
            bundle.putInt("CAR_UI_INSET_BOTTOM", ((Integer) CarUiInstaller.a(activity.getClassLoader(), Insets.class, "getBottom", objA2, new Object[0])).intValue());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
