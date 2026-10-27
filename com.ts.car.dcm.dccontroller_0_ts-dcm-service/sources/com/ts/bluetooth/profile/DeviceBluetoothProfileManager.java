package com.ts.bluetooth.profile;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DeviceBluetoothProfileManager {
    private Context context;
    private Map<Profile, IBluetoothProfile> profileMap = new HashMap();

    public DeviceBluetoothProfileManager(Context context) {
        this.context = context;
        initProfiles(context);
    }

    private void initProfiles(Context context) {
        this.profileMap.put(Profile.A2dp, new BluetoothA2dpProfile(context));
        this.profileMap.put(Profile.Headset, new BluetoothHeadsetProfile(context));
    }

    public List<Integer> checkInitProfile(BluetoothDevice device) {
        List<Integer> list = new LinkedList<>();
        MyBluetoothClass myBluetoothClass = new MyBluetoothClass(device.getBluetoothClass().hashCode());
        for (int p : MyBluetoothClass.PROFILES) {
            boolean b1 = myBluetoothClass.doesClassMatch(p);
            if (b1) {
                list.add(Integer.valueOf(p));
            }
        }
        return list;
    }

    public void setProfiles(Profile... profiles) {
        this.profileMap.clear();
        for (Profile profile : profiles) {
            this.profileMap.put(profile, profile.create(this.context));
        }
    }

    public boolean addProfile(Profile profile) {
        return this.profileMap.containsKey(profile) || this.profileMap.put(profile, profile.create(this.context)) != null;
    }

    public boolean removeProfile(Profile profile) {
        return this.profileMap.remove(profile) != null;
    }

    public void connect(BluetoothDevice device) {
        for (IBluetoothProfile profile : this.profileMap.values()) {
            profile.connect(device);
        }
    }

    public boolean connect(BluetoothDevice device, Profile profile) {
        if (!this.profileMap.containsKey(profile)) {
            this.profileMap.put(profile, profile.create(this.context));
        }
        IBluetoothProfile bluetoothProfile = this.profileMap.get(profile);
        if (bluetoothProfile == null) {
            return false;
        }
        return bluetoothProfile.connect(device);
    }

    public boolean disconnect(BluetoothDevice device) {
        boolean b1 = true;
        for (IBluetoothProfile profile : this.profileMap.values()) {
            if (!profile.disconnect(device)) {
                b1 = false;
            }
        }
        return b1;
    }

    public boolean disconnect(BluetoothDevice device, Profile profile) {
        return this.profileMap.containsKey(profile) && this.profileMap.get(profile) != null && this.profileMap.get(profile).disconnect(device);
    }

    public void close() {
        for (IBluetoothProfile profile : this.profileMap.values()) {
            profile.close();
        }
        this.profileMap.clear();
        this.profileMap = null;
        this.context = null;
    }
}
