package com.android.car.pm;

import android.content.ComponentName;
import android.content.Intent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
class VendorServiceInfo {
    private static final int BIND = 0;
    private static final String KEY_BIND = "bind";
    private static final String KEY_TRIGGER = "trigger";
    private static final String KEY_USER_SCOPE = "user";
    private static final int START = 1;
    private static final int START_FOREGROUND = 2;
    private static final int TRIGGER_ASAP = 0;
    private static final int TRIGGER_UNLOCKED = 1;
    private static final int USER_SCOPE_ALL = 0;
    private static final int USER_SCOPE_FOREGROUND = 2;
    private static final int USER_SCOPE_SYSTEM = 1;
    private final int mBind;
    private final ComponentName mComponentName;
    private final int mTrigger;
    private final int mUserScope;

    @Retention(RetentionPolicy.SOURCE)
    @interface Bind {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface Trigger {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface UserScope {
    }

    private VendorServiceInfo(ComponentName componentName, int bind, int userScope, int trigger) {
        this.mComponentName = componentName;
        this.mUserScope = userScope;
        this.mTrigger = trigger;
        this.mBind = bind;
    }

    boolean isSystemUserService() {
        int i = this.mUserScope;
        return i == 0 || i == 1;
    }

    boolean isForegroundUserService() {
        int i = this.mUserScope;
        return i == 0 || i == 2;
    }

    boolean shouldStartOnUnlock() {
        return this.mTrigger == 1;
    }

    boolean shouldStartAsap() {
        return this.mTrigger == 0;
    }

    boolean shouldBeBound() {
        return this.mBind == 0;
    }

    boolean shouldBeStartedInForeground() {
        return this.mBind == 2;
    }

    Intent getIntent() {
        Intent intent = new Intent();
        intent.setComponent(this.mComponentName);
        return intent;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.android.car.pm.VendorServiceInfo parse(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.pm.VendorServiceInfo.parse(java.lang.String):com.android.car.pm.VendorServiceInfo");
    }

    public String toString() {
        return "VendorService{component=" + this.mComponentName + ", bind=" + this.mBind + ", trigger=" + this.mTrigger + ", user=" + this.mUserScope + '}';
    }

    String toShortString() {
        ComponentName componentName = this.mComponentName;
        return componentName != null ? componentName.toShortString() : "";
    }
}
