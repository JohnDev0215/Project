package com.android.car.audio;

import android.car.media.ICarVolumeCallback;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.car.BinderInterfaceContainer;
import com.android.car.CarLog;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
class CarVolumeCallbackHandler {
    private final BinderInterfaceContainer<ICarVolumeCallback> mVolumeCallbackContainer = new BinderInterfaceContainer<>();

    CarVolumeCallbackHandler() {
    }

    void release() {
        this.mVolumeCallbackContainer.clear();
    }

    void onVolumeGroupChange(int zoneId, int groupId, int flags) {
        Iterator it = this.mVolumeCallbackContainer.getInterfaces().iterator();
        while (it.hasNext()) {
            BinderInterfaceContainer.BinderInterface<ICarVolumeCallback> callback = (BinderInterfaceContainer.BinderInterface) it.next();
            try {
                callback.binderInterface.onGroupVolumeChanged(zoneId, groupId, flags);
            } catch (RemoteException e) {
                Log.e(CarLog.TAG_AUDIO, "Failed to callback onGroupVolumeChanged", e);
            }
        }
    }

    void onMasterMuteChanged(int zoneId, int flags) {
        Iterator it = this.mVolumeCallbackContainer.getInterfaces().iterator();
        while (it.hasNext()) {
            BinderInterfaceContainer.BinderInterface<ICarVolumeCallback> callback = (BinderInterfaceContainer.BinderInterface) it.next();
            try {
                callback.binderInterface.onMasterMuteChanged(zoneId, flags);
            } catch (RemoteException e) {
                Log.e(CarLog.TAG_AUDIO, "Failed to callback onMasterMuteChanged", e);
            }
        }
    }

    public void registerCallback(IBinder binder) {
        this.mVolumeCallbackContainer.addBinder(ICarVolumeCallback.Stub.asInterface(binder));
    }

    public void unregisterCallback(IBinder binder) {
        this.mVolumeCallbackContainer.removeBinder(ICarVolumeCallback.Stub.asInterface(binder));
    }
}
