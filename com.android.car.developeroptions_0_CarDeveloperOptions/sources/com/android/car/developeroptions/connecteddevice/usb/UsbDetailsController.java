package com.android.car.developeroptions.connecteddevice.usb;

import android.content.Context;
import android.os.Handler;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public abstract class UsbDetailsController extends AbstractPreferenceController implements PreferenceControllerMixin {
    protected final Context mContext;
    protected final UsbDetailsFragment mFragment;
    Handler mHandler;
    protected final UsbBackend mUsbBackend;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    protected abstract void refresh(boolean z, long j, int i, int i2);

    public UsbDetailsController(Context context, UsbDetailsFragment usbDetailsFragment, UsbBackend usbBackend) {
        super(context);
        this.mContext = context;
        this.mFragment = usbDetailsFragment;
        this.mUsbBackend = usbBackend;
        this.mHandler = new Handler(context.getMainLooper());
    }
}
