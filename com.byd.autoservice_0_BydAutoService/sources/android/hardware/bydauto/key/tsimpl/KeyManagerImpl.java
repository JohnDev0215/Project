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
            public void onIdlerWheel(int i3) {
                synchronized (KeyManagerImpl.this.mAbsBYDAutoKeyListener) {
                    if (KeyManagerImpl.this.mAbsBYDAutoKeyListener != null && KeyManagerImpl.this.mAbsBYDAutoKeyListener.size() > 0) {
                        Iterator it = KeyManagerImpl.this.mAbsBYDAutoKeyListener.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoKeyListener) it.next()).onIdlerWheel(i3);
                        }
                    }
                }
            }

            public void onKeyClick(int i3, int i4) {
                synchronized (KeyManagerImpl.this.mAbsBYDAutoKeyListener) {
                    if (KeyManagerImpl.this.mAbsBYDAutoKeyListener != null && KeyManagerImpl.this.mAbsBYDAutoKeyListener.size() > 0) {
                        Iterator it = KeyManagerImpl.this.mAbsBYDAutoKeyListener.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoKeyListener) it.next()).onKeyClick(i3, i4);
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

    public String getGetPermission() {
        LogUtil.verbose(TAG, "getGetPermission() is execute");
        return "";
    }

    public String getSetPermission() {
        LogUtil.verbose(TAG, "getSetPermission() is execute");
        return "";
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

    public void registerSceneListener(final AbsBYDAutoSceneKeyListener absBYDAutoSceneKeyListener, int i3) {
        LogUtil.debug(TAG, "registerSceneListener");
        if (absBYDAutoSceneKeyListener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mAbsBYDAutoSceneKeyListenerMap) {
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(i3)) && this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(i3)).containsKey(absBYDAutoSceneKeyListener)) {
                LogUtil.debug(TAG, "AbsBYDAutoSceneKeyListener is exists.");
                return;
            }
            OnSceneKeyListener onSceneKeyListener = new OnSceneKeyListener() { // from class: android.hardware.bydauto.key.tsimpl.KeyManagerImpl.2
                public void onIdlerWheel(int i4) {
                    absBYDAutoSceneKeyListener.onIdlerWheel(i4);
                }

                public void onKeyClick(int i4, int i5) {
                    absBYDAutoSceneKeyListener.onKeyClick(i4, i5);
                }
            };
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(i3))) {
                this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(i3)).put(absBYDAutoSceneKeyListener, onSceneKeyListener);
            } else {
                ArrayMap arrayMap = new ArrayMap();
                arrayMap.put(absBYDAutoSceneKeyListener, onSceneKeyListener);
                this.mAbsBYDAutoSceneKeyListenerMap.put(Integer.valueOf(i3), arrayMap);
            }
            if (this.mSettingsManagerImpl.getKeySettingsManager() == null) {
                LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            } else {
                this.mSettingsManagerImpl.getKeySettingsManager().registerSceneListener(onSceneKeyListener, i3);
            }
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

    public void unregisterSceneListener(AbsBYDAutoSceneKeyListener absBYDAutoSceneKeyListener, int i3) {
        LogUtil.debug(TAG, "unregisterSceneListener: ");
        if (absBYDAutoSceneKeyListener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mAbsBYDAutoSceneKeyListenerMap) {
            if (this.mAbsBYDAutoSceneKeyListenerMap.containsKey(Integer.valueOf(i3)) && this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(i3)).containsKey(absBYDAutoSceneKeyListener)) {
                Map<AbsBYDAutoSceneKeyListener, OnSceneKeyListener> map = this.mAbsBYDAutoSceneKeyListenerMap.get(Integer.valueOf(i3));
                OnSceneKeyListener onSceneKeyListener = map.get(absBYDAutoSceneKeyListener);
                map.remove(absBYDAutoSceneKeyListener, map.get(absBYDAutoSceneKeyListener));
                if (map.size() > 0) {
                    this.mAbsBYDAutoSceneKeyListenerMap.put(Integer.valueOf(i3), map);
                } else {
                    this.mAbsBYDAutoSceneKeyListenerMap.remove(Integer.valueOf(i3));
                }
                if (this.mSettingsManagerImpl.getKeySettingsManager() == null) {
                    LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
                    return;
                } else {
                    this.mSettingsManagerImpl.getKeySettingsManager().unregisterSceneListener(onSceneKeyListener, i3);
                    return;
                }
            }
            LogUtil.debug(TAG, "AbsBYDAutoSceneKeyListener not exists.");
        }
    }
}
