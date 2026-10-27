package com.ts.bluetooth.profile;

/* JADX INFO: loaded from: classes.dex */
public enum Profile {
    A2dp(BluetoothA2dpProfile.class),
    Headset(BluetoothHeadsetProfile.class),
    Pbap(BluetoothPbapClientProfile.class);

    private Class<? extends IBluetoothProfile> profileClass;

    Profile(Class cls) {
        this.profileClass = cls;
    }

    public IBluetoothProfile create(Object... obj) {
        try {
            return this.profileClass.getConstructor(obj.getClass()).newInstance(obj);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }
}
