package com.android.systemui.statusbar.policy;

import android.content.Context;
import com.android.systemui.statusbar.policy.UsbController;
import com.byd.profile.storage.BydStorageManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class UsbControllerImpl implements UsbController, BydStorageManager.BydStorageListener {
    private final ArrayList<UsbController.Callback> mCallbacks = new ArrayList<>();
    private Context mContext;

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public UsbControllerImpl(Context context) {
        this.mContext = context;
        BydStorageManager.getInstance(context).setListener(this);
    }

    @Override // com.android.systemui.statusbar.policy.UsbController
    public void getState() {
        BydStorageManager.getInstance(this.mContext).getVolumesStatus();
    }

    @Override // com.android.systemui.statusbar.policy.UsbController
    public boolean isUSBConnected() {
        return BydStorageManager.getInstance(this.mContext).isUsbConnected();
    }

    @Override // com.android.systemui.statusbar.policy.UsbController
    public boolean isSdCardMounted() {
        return BydStorageManager.getInstance(this.mContext).isSdCardMounted();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void addCallback(UsbController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback != null) {
                if (!this.mCallbacks.contains(callback)) {
                    this.mCallbacks.add(callback);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void removeCallback(UsbController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback == null) {
                return;
            }
            this.mCallbacks.remove(callback);
        }
    }

    @Override // com.byd.profile.storage.BydStorageManager.BydStorageListener
    public void usbStateChanged(boolean state) {
        synchronized (this.mCallbacks) {
            Iterator<UsbController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onUsbStateChanged(state);
            }
        }
    }

    @Override // com.byd.profile.storage.BydStorageManager.BydStorageListener
    public void sdcardStateChanged(boolean state) {
        synchronized (this.mCallbacks) {
            Iterator<UsbController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onSdCardStateChanged(state);
            }
        }
    }
}
