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
    private static final String TAG = "DmsManagerImpl";
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
        String str = TAG;
        LogUtil.debug(str, "queryDmsServiceStatus is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().queryDmsServiceStatus();
        }
    }

    public void setDmsSwtichStatus(int i) {
        String str = TAG;
        LogUtil.debug(str, "setDmsSwtichStatus is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setDmsSwtichStatus(i);
        }
    }

    public void setPauseFatigueDetect() {
        String str = TAG;
        LogUtil.debug(str, "setPauseFatigueDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setPauseFatigueDetect();
        }
    }

    public void setFatigueDetect(int i) {
        String str = TAG;
        LogUtil.debug(str, "setFatigueDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setFatigueDetect(i);
        }
    }

    public void setAttentionDetect(int i) {
        String str = TAG;
        LogUtil.debug(str, "setAttentionDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setAttentionDetect(i);
        }
    }

    public void getDmsSwitchChanged() {
        String str = TAG;
        LogUtil.debug(str, "getDmsSwitchChanged is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().getDmsSwitchChanged();
        }
    }

    public void queryFatigueDetect() {
        String str = TAG;
        LogUtil.debug(str, "queryFatigueDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().queryFatigueDetect();
        }
    }

    public void queryAttentionDetect() {
        String str = TAG;
        LogUtil.debug(str, "queryAttentionDetect is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().queryAttentionDetect();
        }
    }

    public void setDmsMode(int i) {
        String str = TAG;
        LogUtil.debug(str, "setDmsMode is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().setDmsMode(i);
        }
    }

    public void queryDmsMode() {
        String str = TAG;
        LogUtil.debug(str, "queryDmsMode is execute");
        if (this.mSettingsManagerImpl.getDmsSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getDmsSettingsManager().queryDmsMode();
        }
    }

    public void registerListener(IDMSListener iDMSListener) {
        String str = TAG;
        LogUtil.debug(str, "registerListener is execute");
        if (iDMSListener == null) {
            LogUtil.error(str, "listener is null");
        } else {
            if (this.mListeners.contains(iDMSListener)) {
                return;
            }
            this.mListeners.add(iDMSListener);
        }
    }

    public void unregisterListener(IDMSListener iDMSListener) {
        String str = TAG;
        LogUtil.debug(str, "unregisterListener is execute");
        if (iDMSListener == null) {
            LogUtil.error(str, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(iDMSListener)) {
                this.mListeners.remove(iDMSListener);
            }
        }
    }
}
