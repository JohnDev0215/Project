package com.ts.bluetooth.profile;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseBluetoothProfileImpl<T extends BluetoothProfile> implements IBluetoothProfile, BluetoothProfile.ServiceListener {
    private Context context;
    private T mProxy;
    private BluetoothProfileReflect mReflect;

    BaseBluetoothProfileImpl(Context context) {
        this.context = context;
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            BluetoothManager btManager = (BluetoothManager) context.getSystemService("bluetooth");
            if (btManager.getAdapter() == null) {
                throw new NullPointerException("bluetoothAdapter is null");
            }
            return;
        }
        bluetoothAdapter.getProfileProxy(context, this, getProfileCode());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceConnected(int profile, BluetoothProfile bluetoothProfile) {
        this.mProxy = bluetoothProfile;
        try {
            this.mReflect = new BluetoothProfileReflect(bluetoothProfile);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceDisconnected(int profile) {
        this.mProxy = null;
    }

    @Override // com.ts.bluetooth.profile.IBluetoothProfile
    public boolean isAvailable() {
        return this.mProxy != null;
    }

    @Override // com.ts.bluetooth.profile.IBluetoothProfile
    public boolean connect(BluetoothDevice device) {
        if (!isEnabled()) {
            return false;
        }
        this.mReflect.setPriority(device, 100);
        return this.mReflect.connect(device);
    }

    @Override // com.ts.bluetooth.profile.IBluetoothProfile
    public boolean disconnect(BluetoothDevice device) {
        if (!isEnabled()) {
            return false;
        }
        this.mReflect.setPriority(device, 0);
        return this.mReflect.disconnect(device);
    }

    @Override // com.ts.bluetooth.profile.IBluetoothProfile
    public int getPriority(BluetoothDevice device) {
        if (!isEnabled()) {
            return 0;
        }
        return this.mReflect.getPriority(device);
    }

    @Override // com.ts.bluetooth.profile.IBluetoothProfile
    public boolean setPriority(BluetoothDevice device, int priority) {
        return isEnabled() && this.mReflect.setPriority(device, priority);
    }

    @Override // com.ts.bluetooth.profile.IBluetoothProfile
    public boolean isPreferred(BluetoothDevice device) {
        return getPriority(device) == 100;
    }

    protected T getProxy() {
        return this.mProxy;
    }

    protected Context getContext() {
        return this.context;
    }

    private boolean isEnabled() {
        return (this.mProxy == null || this.mReflect == null) ? false : true;
    }

    @Override // com.ts.bluetooth.profile.IBluetoothProfile
    public void close() {
        BluetoothAdapter.getDefaultAdapter().closeProfileProxy(getProfileCode(), this.mProxy);
    }

    protected static class BluetoothProfileReflect {
        Class<?> mClass;
        private BluetoothProfile profile;

        protected BluetoothProfileReflect(BluetoothProfile obj) throws ClassNotFoundException {
            setProxy(obj);
        }

        protected final void setProxy(BluetoothProfile obj) throws ClassNotFoundException {
            this.profile = obj;
            this.mClass = Class.forName(obj.getClass().getName());
        }

        protected final BluetoothProfile getProxy() {
            return this.profile;
        }

        protected final boolean connect(BluetoothDevice device) {
            Object object = invoke("connect", new Object[]{device}, BluetoothDevice.class);
            if (object != null) {
                return ((Boolean) object).booleanValue();
            }
            return false;
        }

        protected final boolean disconnect(BluetoothDevice device) {
            Object object = invoke("disconnect", new Object[]{device}, BluetoothDevice.class);
            return object != null && (object instanceof Boolean) && ((Boolean) object).booleanValue();
        }

        protected final boolean setPriority(BluetoothDevice device, int priority) {
            Object object = invoke("setPriority", new Object[]{device, Integer.valueOf(priority)}, BluetoothDevice.class, Integer.TYPE);
            if (object == null || !(object instanceof Boolean)) {
                return false;
            }
            return ((Boolean) object).booleanValue();
        }

        protected final int getPriority(BluetoothDevice device) {
            Object object = invoke("getPriority", new Object[]{device}, BluetoothDevice.class);
            if (object == null || !(object instanceof Integer)) {
                return 0;
            }
            return ((Integer) object).intValue();
        }

        protected final void disconnectAll() {
            List<BluetoothDevice> devices = getConnectDevices();
            if (devices == null) {
                return;
            }
            for (BluetoothDevice device : devices) {
                disconnect(device);
            }
        }

        protected final int getConnectionState(BluetoothDevice device) {
            Object object = invoke("getConnectionState", new Object[]{device}, BluetoothDevice.class);
            if (object == null || !(object instanceof Integer)) {
                return -1;
            }
            return ((Integer) object).intValue();
        }

        protected final List<BluetoothDevice> getConnectDevices() {
            Object object = invoke("getConnectedDevices", null, new Class[0]);
            if (object != null) {
                return (ArrayList) object;
            }
            return new ArrayList();
        }

        private Object invoke(String methodName, Object[] objects, Class<?>... parameterTypes) {
            Class<?> cls = this.mClass;
            if (cls == null || this.profile == null) {
                return null;
            }
            try {
                Method method = cls.getMethod(methodName, parameterTypes);
                if (objects != null && objects.length != 0) {
                    return method.invoke(this.profile, objects);
                }
                return method.invoke(this.profile, new Object[0]);
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }
}
