package com.ts.car.sdk.input;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ts.car.input.IHardKeyPolicyManager;
import ts.car.input.IKeyEventCallback;
import ts.car.service.input.IHardKeyManager;
import ts.car.service.input.IKeyEventListener;
import ts.car.service.input.IKeyLongPressEventListener;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformHardKeyManager extends SimpleServiceBase implements IHardKeyManager {
    private static final String REMOTE_SERVICE_NAME = "input_policy";
    private static final String SERVICE_COMPONENT = "com.ts.car.input/.InputCoreService";
    private static final String TAG = PlatformHardKeyManager.class.getSimpleName();
    private List<KeyEventListener> mKeyListeners;
    private List<KeyLongPressEventListener> mLongPressListeners;
    private IHardKeyPolicyManager mService;

    @PlatformConstruction
    public PlatformHardKeyManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
        Log.d(TAG, "PlatformHardKeyManager Started.");
        this.mKeyListeners = new ArrayList();
        this.mLongPressListeners = new ArrayList();
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-InputService_1.0.0";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        Log.d(TAG, "onServiceConnected.");
        synchronized (this) {
            this.mService = IHardKeyPolicyManager.Stub.asInterface(iBinder);
        }
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        Log.d(TAG, "onServiceDied.");
        synchronized (this) {
            this.mService = null;
        }
    }

    @Override // ts.car.service.input.IHardKeyManager
    public boolean addKeyEventListener(IKeyEventListener listener, int sceneType) {
        IHardKeyPolicyManager manager;
        if (listener == null || sceneType <= 0) {
            return false;
        }
        synchronized (this) {
            manager = this.mService;
        }
        if (manager == null || findKeyEventListener(listener, sceneType) != null) {
            return false;
        }
        KeyEventListener eventListener = new KeyEventListener(listener, sceneType);
        try {
            manager.addKeyEventCallBack(eventListener, sceneType);
            synchronized (this) {
                this.mKeyListeners.add(eventListener);
            }
            return true;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private KeyEventListener findKeyEventListener(IKeyEventListener listener, int sceneType) {
        synchronized (this) {
            for (KeyEventListener l : this.mKeyListeners) {
                if (l.mListener == listener && l.mSceneType == sceneType) {
                    return l;
                }
            }
            return null;
        }
    }

    @Override // ts.car.service.input.IHardKeyManager
    public boolean removeKeyEventListener(IKeyEventListener listener, int sceneType) {
        if (listener == null || this.mService == null) {
            return false;
        }
        try {
            List<KeyEventListener> findListeners = new ArrayList<>();
            synchronized (this) {
                for (KeyEventListener l : this.mKeyListeners) {
                    if (l.mListener == listener && (sceneType == -1 || l.mSceneType == sceneType)) {
                        findListeners.add(l);
                    }
                }
                Iterator<KeyEventListener> it = findListeners.iterator();
                while (it.hasNext()) {
                    this.mKeyListeners.remove(it.next());
                }
            }
            for (KeyEventListener l2 : findListeners) {
                this.mService.removeKeyEventCallBack(l2, l2.mSceneType);
            }
            return true;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.input.IHardKeyManager
    public boolean addLongPressEventListener(IKeyLongPressEventListener listener, int sceneType) {
        IHardKeyPolicyManager manager;
        if (listener == null || sceneType <= 0) {
            return false;
        }
        synchronized (this) {
            manager = this.mService;
        }
        if (manager == null || findLongPressEventListener(listener, sceneType) != null) {
            return false;
        }
        KeyLongPressEventListener eventListener = new KeyLongPressEventListener(listener, sceneType);
        try {
            manager.addLongPressCallBack(eventListener, sceneType);
            synchronized (this) {
                this.mLongPressListeners.add(eventListener);
            }
            return true;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private KeyLongPressEventListener findLongPressEventListener(IKeyLongPressEventListener listener, int sceneType) {
        synchronized (this) {
            for (KeyLongPressEventListener l : this.mLongPressListeners) {
                if (l.mListener == listener && l.mSceneType == sceneType) {
                    return l;
                }
            }
            return null;
        }
    }

    @Override // ts.car.service.input.IHardKeyManager
    public boolean removeLongPressEventListener(IKeyLongPressEventListener listener, int sceneType) {
        if (listener == null || this.mService == null) {
            return false;
        }
        try {
            List<KeyLongPressEventListener> findListeners = new ArrayList<>();
            synchronized (this) {
                for (KeyLongPressEventListener l : this.mLongPressListeners) {
                    if (l.mListener == listener && (sceneType == -1 || l.mSceneType == sceneType)) {
                        findListeners.add(l);
                    }
                }
                Iterator<KeyLongPressEventListener> it = findListeners.iterator();
                while (it.hasNext()) {
                    this.mLongPressListeners.remove(it.next());
                }
            }
            for (KeyLongPressEventListener l2 : findListeners) {
                this.mService.removeLongPressCallBack(l2, l2.mSceneType);
            }
            return true;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    static class KeyEventListener extends IKeyEventCallback.Stub {
        IKeyEventListener mListener;
        int mSceneType;

        public KeyEventListener(IKeyEventListener listener, int sceneType) {
            this.mListener = listener;
            this.mSceneType = sceneType;
        }

        @Override // ts.car.input.IKeyEventCallback
        public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
            this.mListener.onKeyEvent(event, sceneType, targetDisplay);
        }

        @Override // ts.car.input.IKeyEventCallback
        public void onKeyLongPress(KeyEvent event, int sceneType, int targetDisplay) {
        }
    }

    static class KeyLongPressEventListener extends IKeyEventCallback.Stub {
        IKeyLongPressEventListener mListener;
        int mSceneType;

        public KeyLongPressEventListener(IKeyLongPressEventListener listener, int sceneType) {
            this.mListener = listener;
            this.mSceneType = sceneType;
        }

        @Override // ts.car.input.IKeyEventCallback
        public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
        }

        @Override // ts.car.input.IKeyEventCallback
        public void onKeyLongPress(KeyEvent event, int sceneType, int targetDisplay) {
            this.mListener.onKeyLongPressed(event, sceneType, targetDisplay);
        }
    }
}
