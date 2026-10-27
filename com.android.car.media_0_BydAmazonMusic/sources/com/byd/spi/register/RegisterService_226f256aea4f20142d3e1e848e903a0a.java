package com.byd.spi.register;

import com.byd.car.IAppFocusManager;
import com.byd.car.internal.appfocus.AppFocusManager;
import com.byd.spi.Spi;

/* JADX INFO: loaded from: classes2.dex */
public class RegisterService_226f256aea4f20142d3e1e848e903a0a {
    public static void register() {
        Spi.addService(IAppFocusManager.class, AppFocusManager.class);
    }
}
