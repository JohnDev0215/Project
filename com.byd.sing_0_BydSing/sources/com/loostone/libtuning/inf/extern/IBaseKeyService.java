package com.loostone.libtuning.inf.extern;

import android.hardware.usb.UsbDevice;
import kotlin.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public interface IBaseKeyService {

    @f
    public static final class DefaultImpls {
        public static int getPairState(IBaseKeyService iBaseKeyService, int i) {
            h.d(iBaseKeyService, "this");
            return -1;
        }
    }

    IMicControl getMicControl();

    int getPairState(int i);

    void pause();

    void registerKeyListener(IKeyListener iKeyListener);

    void registerMicInfoListener(IMicInfoListener iMicInfoListener);

    void resume();

    void setDevice(UsbDevice usbDevice);

    void start();

    void stop();

    void unRegisterKeyListener(IKeyListener iKeyListener);

    void unRegisterMicInfoListener(IMicInfoListener iMicInfoListener);
}
