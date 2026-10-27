package com.ts.lib.settings.key;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.view.KeyEvent;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.key.IKeySettingsCallback;
import com.ts.lib.settings.key.IKeySettingsEventCallback;
import com.ts.lib.settings.key.IKeySettingsLongPressEventCallback;
import com.ts.lib.settings.key.IKeySettingsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class KeySettingsManager implements SettingsAdapterBase {
    public static final int CLICK_LONG = 2;
    public static final int CLICK_SHORT = 1;
    private static final String TAG = KeySettingsManager.class.getSimpleName();
    private IKeySettingsService mKeySettingsService;
    private List<OnKeyListener> mKeyListenerList = new ArrayList();
    private Map<Integer, List<OnSceneKeyListener>> mOnSceneKeyListenerMap = new ArrayMap();
    private final IKeySettingsCallback mKeySettingsCallback = new IKeySettingsCallback.Stub() { // from class: com.ts.lib.settings.key.KeySettingsManager.1
        @Override // com.ts.lib.settings.key.IKeySettingsCallback
        public void onIdlerWheel(int value) {
            KeySettingsManager.this.callbackIdlerWheel(value);
        }

        @Override // com.ts.lib.settings.key.IKeySettingsCallback
        public void onKeyClick(int keyValue, int click) {
            KeySettingsManager.this.callbackKeyClick(keyValue, click);
        }
    };
    private final IKeySettingsEventCallback mSceneKeyEventListener = new IKeySettingsEventCallback.Stub() { // from class: com.ts.lib.settings.key.KeySettingsManager.2
        @Override // com.ts.lib.settings.key.IKeySettingsEventCallback
        public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
            int keyCode = event.getKeyCode();
            int action = event.getAction();
            LogUtil.debug(KeySettingsManager.TAG, " mSceneKeyEventListener keyCode :" + keyCode + ", action : " + action);
            if (action == 1) {
                synchronized (KeySettingsManager.this.mOnSceneKeyListenerMap) {
                    if (KeySettingsManager.this.mOnSceneKeyListenerMap.containsKey(Integer.valueOf(sceneType))) {
                        for (OnSceneKeyListener onSceneKeyListener : (List) KeySettingsManager.this.mOnSceneKeyListenerMap.get(Integer.valueOf(sceneType))) {
                            onSceneKeyListener.onKeyClick(keyCode, 1);
                        }
                    }
                }
            }
        }
    };
    private final IKeySettingsLongPressEventCallback mSceneKeyLongPressEventListener = new IKeySettingsLongPressEventCallback.Stub() { // from class: com.ts.lib.settings.key.KeySettingsManager.3
        @Override // com.ts.lib.settings.key.IKeySettingsLongPressEventCallback
        public void onKeyLongPressed(KeyEvent event, int sceneType, int targetDisplay) {
            int keyCode = event.getKeyCode();
            int action = event.getAction();
            LogUtil.debug(KeySettingsManager.TAG, " mSceneKeyLongPressEventListener keyCode :" + keyCode + ", action : " + action);
            if (action == 0) {
                synchronized (KeySettingsManager.this.mOnSceneKeyListenerMap) {
                    if (KeySettingsManager.this.mOnSceneKeyListenerMap.containsKey(Integer.valueOf(sceneType))) {
                        for (OnSceneKeyListener onSceneKeyListener : (List) KeySettingsManager.this.mOnSceneKeyListenerMap.get(Integer.valueOf(sceneType))) {
                            onSceneKeyListener.onKeyClick(keyCode, 2);
                        }
                    }
                }
            }
        }
    };

    public KeySettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init KeySettingsManager");
        this.mKeySettingsService = IKeySettingsService.Stub.asInterface(binder);
        subscribeKeyCallback();
        subscribeSceneKeyCallback();
        subscribeSceneLongPressKeyCallback();
    }

    public void subscribeKeyCallback(OnKeyListener listener) {
        synchronized (this.mKeyListenerList) {
            if (!this.mKeyListenerList.contains(listener) && listener != null) {
                this.mKeyListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeKeyCallback ok --> listener size: " + this.mKeyListenerList.size());
            }
        }
    }

    public void unSubscribeKeyCallback(OnKeyListener listener) {
        synchronized (this.mKeyListenerList) {
            if (this.mKeyListenerList != null && this.mKeyListenerList.contains(listener)) {
                this.mKeyListenerList.remove(listener);
                LogUtil.debug(TAG, "unSubscribeKeyCallback ok --> listener size: " + this.mKeyListenerList.size());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackIdlerWheel(int value) {
        synchronized (this.mKeyListenerList) {
            if (this.mKeyListenerList != null && this.mKeyListenerList.size() > 0) {
                for (OnKeyListener listener : this.mKeyListenerList) {
                    listener.onIdlerWheel(value);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackKeyClick(int keyValue, int click) {
        for (OnKeyListener listener : this.mKeyListenerList) {
            listener.onKeyClick(keyValue, click);
        }
    }

    public void subscribeKeyCallback() {
        if (this.mKeySettingsService == null) {
            LogUtil.error(TAG, "mKeySettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeKeyCallback");
        try {
            this.mKeySettingsService.subscribeKeyCallback(this.mKeySettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeKeyCallback error ", ex);
        }
    }

    public void unsubscribeKeyCallback() {
        if (this.mKeySettingsService == null) {
            LogUtil.error(TAG, "mKeySettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeKeyCallback");
        try {
            this.mKeySettingsService.unsubscribeKeyCallback(this.mKeySettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeKeyCallback error ", ex);
        }
    }

    public void subscribeSceneKeyCallback() {
        if (this.mKeySettingsService == null) {
            LogUtil.error(TAG, "mKeySettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeSceneKeyCallback");
        try {
            this.mKeySettingsService.subscribeSceneKeyCallback(this.mSceneKeyEventListener);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeSceneKeyCallback error ", ex);
        }
    }

    public void unsubscribeSceneKeyCallback() {
        if (this.mKeySettingsService == null) {
            LogUtil.error(TAG, "mKeySettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeSceneKeyCallback");
        try {
            this.mKeySettingsService.unsubscribeSceneKeyCallback(this.mSceneKeyEventListener);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeSceneKeyCallback error ", ex);
        }
    }

    public void subscribeSceneLongPressKeyCallback() {
        if (this.mKeySettingsService == null) {
            LogUtil.error(TAG, "mKeySettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeSceneLongPressKeyCallback");
        try {
            this.mKeySettingsService.subscribeSceneLongPressKeyCallback(this.mSceneKeyLongPressEventListener);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeSceneLongPressKeyCallback error ", ex);
        }
    }

    public void unsubscribeSceneLongPressKeyCallback() {
        if (this.mKeySettingsService == null) {
            LogUtil.error(TAG, "mKeySettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeSceneLongPressKeyCallback");
        try {
            this.mKeySettingsService.unsubscribeSceneLongPressKeyCallback(this.mSceneKeyLongPressEventListener);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeSceneLongPressKeyCallback error ", ex);
        }
    }

    public void addHighPriorityScene(int sceneId) {
        if (this.mKeySettingsService == null) {
            LogUtil.error(TAG, "mKeySettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "addHighPriorityScene :" + sceneId);
        try {
            this.mKeySettingsService.addHighPriorityScene(sceneId);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "addHighPriorityScene error ", ex);
        }
    }

    public void removeHighPriorityScene(int sceneId) {
        if (this.mKeySettingsService == null) {
            LogUtil.error(TAG, "mKeySettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "removeHighPriorityScene");
        try {
            this.mKeySettingsService.removeHighPriorityScene(sceneId);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "removeHighPriorityScene error ", ex);
        }
    }

    public void registerSceneListener(OnSceneKeyListener listener, int sceneId) {
        LogUtil.debug(TAG, "registerSceneListener");
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mOnSceneKeyListenerMap) {
            if (this.mOnSceneKeyListenerMap.containsKey(Integer.valueOf(sceneId)) && this.mOnSceneKeyListenerMap.get(Integer.valueOf(sceneId)).contains(listener)) {
                LogUtil.debug(TAG, "Scene Listener already exists.");
                return;
            }
            if (this.mOnSceneKeyListenerMap.containsKey(Integer.valueOf(sceneId))) {
                this.mOnSceneKeyListenerMap.get(Integer.valueOf(sceneId)).add(listener);
            } else {
                List<OnSceneKeyListener> list = new ArrayList<>();
                list.add(listener);
                this.mOnSceneKeyListenerMap.put(Integer.valueOf(sceneId), list);
            }
            try {
                addHighPriorityScene(sceneId);
            } catch (Exception ex) {
                LogUtil.error(TAG, "addHighPriorityScene error ", ex);
            }
        }
    }

    public void unregisterSceneListener(OnSceneKeyListener listener, int sceneId) {
        LogUtil.debug(TAG, "unregisterSceneListener: ");
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mOnSceneKeyListenerMap) {
            if (this.mOnSceneKeyListenerMap.containsKey(Integer.valueOf(sceneId)) && this.mOnSceneKeyListenerMap.get(Integer.valueOf(sceneId)).contains(listener)) {
                List<OnSceneKeyListener> list = this.mOnSceneKeyListenerMap.get(Integer.valueOf(sceneId));
                list.remove(listener);
                if (list.size() > 0) {
                    this.mOnSceneKeyListenerMap.put(Integer.valueOf(sceneId), list);
                } else {
                    this.mOnSceneKeyListenerMap.remove(Integer.valueOf(sceneId));
                }
                try {
                    removeHighPriorityScene(sceneId);
                } catch (Exception ex) {
                    LogUtil.error(TAG, "removeHighPriorityScene error ", ex);
                }
                return;
            }
            LogUtil.debug(TAG, "Scene Listener not exists.");
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        unsubscribeKeyCallback();
        unsubscribeSceneKeyCallback();
        unsubscribeSceneLongPressKeyCallback();
    }
}
