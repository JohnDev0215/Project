package android.hardware.bydauto.dms.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.dms.IDMSListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.dms.OnDmsChangedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DmsManagerImpl {
    private static final String TAG = DmsManagerImpl.class.getSimpleName();
    private static volatile DmsManagerImpl sInstance;
    private Context mContext;
    private final List<IDMSListener> mListeners = new ArrayList();
    private SettingsManagerImpl mSettingsManagerImpl;

    private DmsManagerImpl(Context context) {
        this.mContext = context;
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(context);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setDmsChangedListenerListener(new OnDmsChangedListener() { // from class: android.hardware.bydauto.dms.tsimpl.DmsManagerImpl.1
            public void onDmsSwitchChanged(int i) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onDmsSwitchChanged(i);
                        }
                    }
                }
            }

            public void onFatigueSwitchChanged(int i) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onFatigueSwitchChanged(i);
                        }
                    }
                }
            }

            public void onAttentionSwitchChanged(int i) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onAttentionSwitchChanged(i);
                        }
                    }
                }
            }

            public void onFatigueDetectChanged(int i) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onFatigueDetectChanged(i);
                        }
                    }
                }
            }

            public void onAttentionDetectChanged(int i) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onAttentionDetectChanged(i);
                        }
                    }
                }
            }

            public void onBlockStatusChanged(int i) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onBlockStatusChanged(i);
                        }
                    }
                }
            }

            public void onDriverNotResponse() {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onDriverNotResponse();
                        }
                    }
                }
            }

            public void onRestoreFatigueDetect() {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onRestoreFatigueDetect();
                        }
                    }
                }
            }

            public void onNotifyDmsServerState(int i) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onNotifyDmsServerState(i);
                        }
                    }
                }
            }

            public void onDmsModeChanged(int i) {
                synchronized (DmsManagerImpl.this.mListeners) {
                    if (DmsManagerImpl.this.mListeners != null && DmsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = DmsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((IDMSListener) it.next()).onDmsModeChanged(i);
                        }
                    }
                }
            }
        });
    }

    public static synchronized DmsManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DmsManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new DmsManagerImpl(context);
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

    public void setDmsSwtichStatus(int i) {
        LogUtil.debug(TAG, "setDmsSwtichStatus is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setDmsSwtichStatus(i);
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

    public void setFatigueDetect(int i) {
        LogUtil.debug(TAG, "setFatigueDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setFatigueDetect(i);
        }
    }

    public void setAttentionDetect(int i) {
        LogUtil.debug(TAG, "setAttentionDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setAttentionDetect(i);
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

    public void setDmsMode(int i) {
        LogUtil.debug(TAG, "setDmsMode is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setDmsMode(i);
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

    public void registerListener(IDMSListener iDMSListener) {
        LogUtil.debug(TAG, "registerListener is execute");
        if (iDMSListener == null) {
            LogUtil.error(TAG, "listener is null");
        } else {
            if (this.mListeners.contains(iDMSListener)) {
                return;
            }
            this.mListeners.add(iDMSListener);
        }
    }

    public void unregisterListener(IDMSListener iDMSListener) {
        LogUtil.debug(TAG, "unregisterListener is execute");
        if (iDMSListener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(iDMSListener)) {
                this.mListeners.remove(iDMSListener);
            }
        }
    }
}
