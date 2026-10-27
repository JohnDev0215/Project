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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.java_websocket.extensions.ExtensionRequestData;

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
            public void onIdlerWheel(int i) {
                synchronized (KeyManagerImpl.this.mAbsBYDAutoKeyListener) {
                    if (KeyManagerImpl.this.mAbsBYDAutoKeyListener != null && KeyManagerImpl.this.mAbsBYDAutoKeyListener.size() > 0) {
                        Iterator it = KeyManagerImpl.this.mAbsBYDAutoKeyListener.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoKeyListener) it.next()).onIdlerWheel(i);
                        }
                    }
                }
            }

            public void onKeyClick(int i, int i2) {
                synchronized (KeyManagerImpl.this.mAbsBYDAutoKeyListener) {
                    if (KeyManagerImpl.this.mAbsBYDAutoKeyListener != null && KeyManagerImpl.this.mAbsBYDAutoKeyListener.size() > 0) {
                        Iterator it = KeyManagerImpl.this.mAbsBYDAutoKeyListener.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoKeyListener) it.next()).onKeyClick(i, i2);
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
        return ExtensionRequestData.EMPTY_VALUE;
    }

    public String getGetPermission() {
        LogUtil.verbose(TAG, "getGetPermission() is execute");
        return ExtensionRequestData.EMPTY_VALUE;
    }

    public void registerListener(AbsBYDAutoKeyListener absBYDAutoKeyListener) {
        LogUtil.debug(TAG, "registerListener is execute");
        if (absBYDAutoKeyListener == null) {
            LogUtil.error(TAG, "listener is null");
        } else {
            if (this.mAbsBYDAutoKeyListener.contains(absBYDAutoKeyListener)) {
                return;
            }
            this.mAbsBYDAutoKeyListener.add(absBYDAutoKeyListener);
        }
    }

    public void unregisterListener(AbsBYDAutoKeyListener absBYDAutoKeyListener) {
        LogUtil.debug(TAG, "unregisterListener is execute");
        if (absBYDAutoKeyListener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mAbsBYDAutoKeyListener) {
            if (this.mAbsBYDAutoKeyListener.contains(absBYDAutoKeyListener)) {
                this.mAbsBYDAutoKeyListener.remove(absBYDAutoKeyListener);
            }
        }
    }

    public void registerSceneListener(final AbsBYDAutoSceneKeyListener absBYDAutoSceneKeyListener, int i) {
        LogUtil.debug(TAG, "registerSceneListener");
        if (absBYDAutoSceneKeyListener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mAbsBYDAutoSceneKeyListenerMap) {
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(i)) && this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(i)).containsKey(absBYDAutoSceneKeyListener)) {
                LogUtil.debug(TAG, "AbsBYDAutoSceneKeyListener is exists.");
                return;
            }
            OnSceneKeyListener onSceneKeyListener = new OnSceneKeyListener() { // from class: android.hardware.bydauto.key.tsimpl.KeyManagerImpl.2
                public void onIdlerWheel(int i2) {
                    absBYDAutoSceneKeyListener.onIdlerWheel(i2);
                }

                public void onKeyClick(int i2, int i3) {
                    absBYDAutoSceneKeyListener.onKeyClick(i2, i3);
                }
            };
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(i))) {
                this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(i)).put(absBYDAutoSceneKeyListener, onSceneKeyListener);
            } else {
                ArrayMap arrayMap = new ArrayMap();
                arrayMap.put(absBYDAutoSceneKeyListener, onSceneKeyListener);
                this.mAbsBYDAutoSceneKeyListenerMap.put(Integer.valueOf(i), arrayMap);
            }
            if (this.mSettingsManagerImpl.getKeySettingsManager() == null) {
                LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            } else {
                this.mSettingsManagerImpl.getKeySettingsManager().registerSceneListener(onSceneKeyListener, i);
            }
        }
    }

    public void unregisterSceneListener(AbsBYDAutoSceneKeyListener absBYDAutoSceneKeyListener, int i) {
        LogUtil.debug(TAG, "unregisterSceneListener: ");
        if (absBYDAutoSceneKeyListener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mAbsBYDAutoSceneKeyListenerMap) {
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(i)) && this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(i)).containsKey(absBYDAutoSceneKeyListener)) {
                Map<AbsBYDAutoSceneKeyListener, OnSceneKeyListener> map = this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(i));
                OnSceneKeyListener onSceneKeyListener = map.get(absBYDAutoSceneKeyListener);
                map.remove(absBYDAutoSceneKeyListener, map.get(absBYDAutoSceneKeyListener));
                if (map.size() > 0) {
                    this.mAbsBYDAutoSceneKeyListenerMap.put(Integer.valueOf(i), map);
                } else {
                    this.mAbsBYDAutoSceneKeyListenerMap.remove(Integer.valueOf(i));
                }
                if (this.mSettingsManagerImpl.getKeySettingsManager() == null) {
                    LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
                    return;
                } else {
                    this.mSettingsManagerImpl.getKeySettingsManager().unregisterSceneListener(onSceneKeyListener, i);
                    return;
                }
            }
            LogUtil.debug(TAG, "AbsBYDAutoSceneKeyListener not exists.");
        }
    }
}
