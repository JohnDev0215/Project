package android.hardware.bydauto.key.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.key.AbsBYDAutoKeyListener;
import android.hardware.bydauto.key.AbsBYDAutoSceneKeyListener;
import android.util.ArrayMap;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.key.OnKeyListener;
import com.ts.lib.settings.key.OnSceneKeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class KeyManagerImpl {
    private static final String TAG = "KeyManagerImpl";
    private static volatile KeyManagerImpl sInstance;
    private final List<AbsBYDAutoKeyListener> mAbsBYDAutoKeyListener = new ArrayList();
    private final Map<Integer, Map<AbsBYDAutoSceneKeyListener, OnSceneKeyListener>> mAbsBYDAutoSceneKeyListenerMap = new ArrayMap();
    private SettingsManagerImpl mSettingsManagerImpl;

    private KeyManagerImpl(Context context) {
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(context);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setOnKeyListener(new OnKeyListener() { // from class: android.hardware.bydauto.key.tsimpl.KeyManagerImpl.1
            public void onIdlerWheel(int value) {
                synchronized (KeyManagerImpl.this.mAbsBYDAutoKeyListener) {
                    if (KeyManagerImpl.this.mAbsBYDAutoKeyListener != null && KeyManagerImpl.this.mAbsBYDAutoKeyListener.size() > 0) {
                        for (AbsBYDAutoKeyListener listener : KeyManagerImpl.this.mAbsBYDAutoKeyListener) {
                            listener.onIdlerWheel(value);
                        }
                    }
                }
            }

            public void onKeyClick(int keyValue, int click) {
                synchronized (KeyManagerImpl.this.mAbsBYDAutoKeyListener) {
                    if (KeyManagerImpl.this.mAbsBYDAutoKeyListener != null && KeyManagerImpl.this.mAbsBYDAutoKeyListener.size() > 0) {
                        for (AbsBYDAutoKeyListener listener : KeyManagerImpl.this.mAbsBYDAutoKeyListener) {
                            listener.onKeyClick(keyValue, click);
                        }
                    }
                }
            }
        });
    }

    public static KeyManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (KeyManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new KeyManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getDevicetype() {
        LogUtil.verbose(TAG, "getDevicetype() is execute");
        return -1;
    }

    public String getSetPermission() {
        LogUtil.verbose(TAG, "getSetPermission() is execute");
        return "";
    }

    public String getGetPermission() {
        LogUtil.verbose(TAG, "getGetPermission() is execute");
        return "";
    }

    public void registerListener(AbsBYDAutoKeyListener listener) {
        LogUtil.debug(TAG, "registerListener is execute");
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
        } else if (!this.mAbsBYDAutoKeyListener.contains(listener)) {
            this.mAbsBYDAutoKeyListener.add(listener);
        }
    }

    public void unregisterListener(AbsBYDAutoKeyListener listener) {
        LogUtil.debug(TAG, "unregisterListener is execute");
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mAbsBYDAutoKeyListener) {
            if (this.mAbsBYDAutoKeyListener.contains(listener)) {
                this.mAbsBYDAutoKeyListener.remove(listener);
            }
        }
    }

    public void registerSceneListener(final AbsBYDAutoSceneKeyListener listener, int sceneId) {
        LogUtil.debug(TAG, "registerSceneListener");
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mAbsBYDAutoSceneKeyListenerMap) {
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(sceneId)) && this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(sceneId)).containsKey(listener)) {
                LogUtil.debug(TAG, "AbsBYDAutoSceneKeyListener is exists.");
                return;
            }
            OnSceneKeyListener onSceneKeyListener = new OnSceneKeyListener() { // from class: android.hardware.bydauto.key.tsimpl.KeyManagerImpl.2
                public void onIdlerWheel(int value) {
                    listener.onIdlerWheel(value);
                }

                public void onKeyClick(int keyValue, int click) {
                    listener.onKeyClick(keyValue, click);
                }
            };
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(sceneId))) {
                this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(sceneId)).put(listener, onSceneKeyListener);
            } else {
                Map<AbsBYDAutoSceneKeyListener, OnSceneKeyListener> map = new ArrayMap<>();
                map.put(listener, onSceneKeyListener);
                this.mAbsBYDAutoSceneKeyListenerMap.put(Integer.valueOf(sceneId), map);
            }
            if (this.mSettingsManagerImpl.getKeySettingsManager() == null) {
                LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            } else {
                this.mSettingsManagerImpl.getKeySettingsManager().registerSceneListener(onSceneKeyListener, sceneId);
            }
        }
    }

    public void unregisterSceneListener(AbsBYDAutoSceneKeyListener listener, int sceneId) {
        LogUtil.debug(TAG, "unregisterSceneListener: ");
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mAbsBYDAutoSceneKeyListenerMap) {
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(sceneId)) && this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(sceneId)).containsKey(listener)) {
                Map<AbsBYDAutoSceneKeyListener, OnSceneKeyListener> map = this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(sceneId));
                OnSceneKeyListener onSceneKeyListener = map.get(listener);
                map.remove(listener, map.get(listener));
                if (map.size() > 0) {
                    this.mAbsBYDAutoSceneKeyListenerMap.put(Integer.valueOf(sceneId), map);
                } else {
                    this.mAbsBYDAutoSceneKeyListenerMap.remove(Integer.valueOf(sceneId));
                }
                if (this.mSettingsManagerImpl.getKeySettingsManager() == null) {
                    LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
                    return;
                } else {
                    this.mSettingsManagerImpl.getKeySettingsManager().unregisterSceneListener(onSceneKeyListener, sceneId);
                    return;
                }
            }
            LogUtil.debug(TAG, "AbsBYDAutoSceneKeyListener not exists.");
        }
    }
}
