package android.hardware.bydauto.dms.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.dms.IDMSListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.dms.OnDmsChangedListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DmsManagerImpl {
    private static final String TAG = DmsManagerImpl.class.getSimpleName();
    private static volatile DmsManagerImpl sInstance;
    private Context mContext;
    private final List<IDMSListener> mListeners = new ArrayList();
    private SettingsManagerImpl mSettingsManagerImpl;

    private DmsManagerImpl(Context con) {
        this.mContext = con;
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(con);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setDmsChangedListenerListener(new OnDmsChangedListener() { // from class: android.hardware.bydauto.dms.tsimpl.DmsManagerImpl.1
            public void onDmsSwitchChanged(int state) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onDmsSwitchChanged(state);
                        }
                    }
                }
            }

            public void onFatigueSwitchChanged(int state) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onFatigueSwitchChanged(state);
                        }
                    }
                }
            }

            public void onAttentionSwitchChanged(int state) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onAttentionSwitchChanged(state);
                        }
                    }
                }
            }

            public void onFatigueDetectChanged(int state) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onFatigueDetectChanged(state);
                        }
                    }
                }
            }

            public void onAttentionDetectChanged(int state) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onAttentionDetectChanged(state);
                        }
                    }
                }
            }

            public void onBlockStatusChanged(int state) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onBlockStatusChanged(state);
                        }
                    }
                }
            }

            public void onDriverNotResponse() {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onDriverNotResponse();
                        }
                    }
                }
            }

            public void onRestoreFatigueDetect() {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onRestoreFatigueDetect();
                        }
                    }
                }
            }

            public void onNotifyDmsServerState(int state) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onNotifyDmsServerState(state);
                        }
                    }
                }
            }

            public void onDmsModeChanged(int mode) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        for (IDMSListener listener : DmsManagerImpl.this.mListeners) {
                            listener.onDmsModeChanged(mode);
                        }
                    }
                }
            }
        });
    }

    public static synchronized DmsManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (DmsManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new DmsManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    public void queryDmsServiceStatus() {
        LogUtil.debug(TAG, "queryDmsServiceStatus is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().queryDmsServiceStatus();
        }
    }

    public void setDmsSwtichStatus(int state) {
        LogUtil.debug(TAG, "setDmsSwtichStatus is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setDmsSwtichStatus(state);
        }
    }

    public void setPauseFatigueDetect() {
        LogUtil.debug(TAG, "setPauseFatigueDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setPauseFatigueDetect();
        }
    }

    public void setFatigueDetect(int state) {
        LogUtil.debug(TAG, "setFatigueDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setFatigueDetect(state);
        }
    }

    public void setAttentionDetect(int state) {
        LogUtil.debug(TAG, "setAttentionDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setAttentionDetect(state);
        }
    }

    public void getDmsSwitchChanged() {
        LogUtil.debug(TAG, "getDmsSwitchChanged is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().getDmsSwitchChanged();
        }
    }

    public void queryFatigueDetect() {
        LogUtil.debug(TAG, "queryFatigueDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().queryFatigueDetect();
        }
    }

    public void queryAttentionDetect() {
        LogUtil.debug(TAG, "queryAttentionDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().queryAttentionDetect();
        }
    }

    public void setDmsMode(int mode) {
        LogUtil.debug(TAG, "setDmsMode is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setDmsMode(mode);
        }
    }

    public void queryDmsMode() {
        LogUtil.debug(TAG, "queryDmsMode is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().queryDmsMode();
        }
    }

    public void registerListener(IDMSListener listener) {
        LogUtil.debug(TAG, "registerListener is execute");
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
        } else if (!this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
        }
    }

    public void unregisterListener(IDMSListener listener) {
        LogUtil.debug(TAG, "unregisterListener is execute");
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(listener)) {
                this.mListeners.remove(listener);
            }
        }
    }
}
