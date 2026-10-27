package com.android.phone;

import android.app.Application;
import android.os.UserHandle;
import com.android.services.telephony.TelecomAccountRegistry;

/* JADX INFO: loaded from: classes.dex */
public class PhoneApp extends Application {
    PhoneGlobals mPhoneGlobals;

    @Override // android.app.Application
    public void onCreate() {
        if (UserHandle.myUserId() == 0) {
            PhoneGlobals phoneGlobals = new PhoneGlobals(this);
            this.mPhoneGlobals = phoneGlobals;
            phoneGlobals.onCreate();
            TelecomAccountRegistry.getInstance(this).setupOnBoot();
        }
    }
}
