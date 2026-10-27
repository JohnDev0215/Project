package android.car.drivingstate;

import android.car.drivingstate.XCarUxRestrictions;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BydBaseCarUxrManagerImpl implements IXCarUxRestrictionsManager {
    public static final int GEAR_BOX_UNKNOWN = -1;
    private static final String TAG = "BydCarUxrManagerImpl";
    public transient boolean haveClass = false;
    private final transient Object mLock = new Object();
    private final transient List<IXCarUxRestrictionsChangeListener> uxrChangeListeners = new ArrayList();

    @Override // android.car.drivingstate.IXCarUxRestrictionsManager
    public final XCarUxRestrictions getCurrentUxRestrictions(int i) {
        return !this.haveClass ? new XCarUxRestrictions.Builder(false, 0, 0L).build() : getGearboxType() == getPDef() ? new XCarUxRestrictions.Builder(false, 0, 0L).build() : new XCarUxRestrictions.Builder(true, 511, 0L).build();
    }

    public abstract int getGearboxType();

    public abstract int getPDef();

    @Override // android.car.drivingstate.IXCarUxRestrictionsManager
    public final String getRestrictionMode() {
        return null;
    }

    @Override // android.car.drivingstate.IXCarUxRestrictionsManager
    public void onCarDisconnected() {
        onCarDisconnectedPlatform();
    }

    public abstract void onCarDisconnectedPlatform();

    @Override // android.car.drivingstate.IXCarUxRestrictionsManager
    public void onGearBoxChangedTest(boolean z) {
        Log.i(TAG, "test onGearboxTypeChanged: " + z);
        XCarUxRestrictions xCarUxRestrictionsBuild = z ? new XCarUxRestrictions.Builder(false, 0, 0L).build() : new XCarUxRestrictions.Builder(true, 511, 0L).build();
        synchronized (this.mLock) {
            Iterator<IXCarUxRestrictionsChangeListener> it = this.uxrChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onUxRestrictionsChanged(xCarUxRestrictionsBuild);
            }
        }
    }

    public final void onGearboxTypeChanged(int i) {
        Log.i(TAG, "onGearboxTypeChanged: " + i);
        XCarUxRestrictions xCarUxRestrictionsBuild = i == getPDef() ? new XCarUxRestrictions.Builder(false, 0, 0L).build() : new XCarUxRestrictions.Builder(true, 511, 0L).build();
        synchronized (this.mLock) {
            Iterator<IXCarUxRestrictionsChangeListener> it = this.uxrChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onUxRestrictionsChanged(xCarUxRestrictionsBuild);
            }
        }
    }

    @Override // android.car.drivingstate.IXCarUxRestrictionsManager
    public final void registerUxRestrictionsChangeListener(IXCarUxRestrictionsChangeListener iXCarUxRestrictionsChangeListener, int i) {
        Log.d(TAG, "BydCarUxrManagerImpl,registerUxRestrictionsChangeListener(" + iXCarUxRestrictionsChangeListener + "," + i + ")");
        if (i < 0 || iXCarUxRestrictionsChangeListener == null) {
            return;
        }
        synchronized (this.mLock) {
            if (this.uxrChangeListeners.contains(iXCarUxRestrictionsChangeListener)) {
                Log.d(TAG, "BydCarUxrManagerImpl,registerUxRestrictionsChangeListener, uxrChangeListeners.contains(listener) == true");
            } else {
                this.uxrChangeListeners.add(iXCarUxRestrictionsChangeListener);
            }
        }
    }

    @Override // android.car.drivingstate.IXCarUxRestrictionsManager
    public final boolean setRestrictionMode(String str) {
        return false;
    }

    @Override // android.car.drivingstate.IXCarUxRestrictionsManager
    public final void unregisterUxRestrictionsChangeListener(IXCarUxRestrictionsChangeListener iXCarUxRestrictionsChangeListener) {
        Log.d(TAG, "BydCarUxrManagerImpl,unregisterUxRestrictionsChangeListener(" + iXCarUxRestrictionsChangeListener + ")");
        if (iXCarUxRestrictionsChangeListener != null) {
            synchronized (this.mLock) {
                this.uxrChangeListeners.remove(iXCarUxRestrictionsChangeListener);
            }
        }
    }
}
