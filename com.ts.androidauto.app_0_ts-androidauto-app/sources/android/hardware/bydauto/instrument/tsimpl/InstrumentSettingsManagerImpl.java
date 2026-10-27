package android.hardware.bydauto.instrument.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import com.ts.lib.settings.cluster.OnClusterChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class InstrumentSettingsManagerImpl {
    private static final int INSTRUNENT_COMMAND_SUCCESS = 0;
    private static final int SETTING_COMMAND_BUSY = -2147482647;
    private static final int SETTING_COMMAND_FAILED = -2147482648;
    private static final int SETTING_COMMAND_INVALID = -2147482645;
    private static final int SETTING_COMMAND_TIMEOUT = -2147482646;
    private static final String TAG = "InstrumentSettingsManagerImpl";
    private static volatile InstrumentSettingsManagerImpl sInstance;
    private Context mContext;
    private SettingsManagerImpl mSettingsManagerImpl;
    private final List<AbsBYDAutoInstrumentListener> mListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;

    private InstrumentSettingsManagerImpl(Context con) {
        this.mContext = con;
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(con);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setOnClusterSettingChangedListener(new OnClusterChangedListener() { // from class: android.hardware.bydauto.instrument.tsimpl.InstrumentSettingsManagerImpl.1
            @Override // com.ts.lib.settings.cluster.OnClusterChangedListener
            public void onINSAdasCenterDisplayChanged(int value) {
                synchronized (InstrumentSettingsManagerImpl.this.mListeners) {
                    if (InstrumentSettingsManagerImpl.this.mListeners != null && InstrumentSettingsManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoInstrumentListener listener : InstrumentSettingsManagerImpl.this.mListeners) {
                            listener.onINSAdasCenterDisplayChanged(value);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.cluster.OnClusterChangedListener
            public void onINSCenterDisplay(int value) {
                synchronized (InstrumentSettingsManagerImpl.this.mListeners) {
                    if (InstrumentSettingsManagerImpl.this.mListeners != null && InstrumentSettingsManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoInstrumentListener listener : InstrumentSettingsManagerImpl.this.mListeners) {
                            listener.onINSCenterDisplay(value);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.cluster.OnClusterChangedListener
            public void onDayNightModeChanged(int mode) {
                synchronized (InstrumentSettingsManagerImpl.this.mListeners) {
                    if (InstrumentSettingsManagerImpl.this.mListeners != null && InstrumentSettingsManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoInstrumentListener listener : InstrumentSettingsManagerImpl.this.mListeners) {
                            listener.onDayNightModeChanged(mode);
                        }
                    }
                }
            }
        });
    }

    public static InstrumentSettingsManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (InstrumentMediaManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new InstrumentSettingsManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public void registerListener(AbsBYDAutoInstrumentListener listener) {
        LogUtil.info(TAG, "registerListener is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (listener == null) {
            LogUtil.info(TAG, "listener is null");
        } else if (!this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
        }
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener listener) {
        LogUtil.info(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (listener == null) {
            LogUtil.info(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(listener)) {
                this.mListeners.remove(listener);
            }
        }
    }

    public int setINSAdasCenterDisplay(int value) {
        LogUtil.info(TAG, "setINSAdasCenterDisplay : " + value);
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setINSAdasCenterDisplay(value) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public int getINSAdasCenterDisplay() {
        LogUtil.info(TAG, "getINSNavDisplayMode");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() == null) {
            LogUtil.error(TAG, "mClusterSettingsManager is null");
            return -1;
        }
        return this.mSettingsManagerImpl.getClusterSettingsManager().getINSAdasCenterDisplay();
    }

    public int setINSCenterDisplay(int value) {
        LogUtil.info(TAG, "setINSCenterDisplay : " + value);
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setINSCenterDisplay(value) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public int getINSCenterDisplay() {
        LogUtil.info(TAG, "getINSCenterDisplay");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() == null) {
            LogUtil.error(TAG, "mClusterSettingsManager is null");
            return -1;
        }
        return this.mSettingsManagerImpl.getClusterSettingsManager().getINSCenterDisplay();
    }

    public int setDayNightMode(int mode) {
        LogUtil.info(TAG, "setDayNightMode : " + mode);
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setDayNightMode(mode) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public int getDayNightMode() {
        LogUtil.info(TAG, "getDayNightMode");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() == null) {
            LogUtil.error(TAG, "mClusterSettingsManager is null");
            return -1;
        }
        return this.mSettingsManagerImpl.getClusterSettingsManager().getDayNightMode();
    }

    public int setTimeFormat(int value) {
        LogUtil.info(TAG, "setTimeFormat : " + value);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setTimeFormat(value) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public int setSpeedUpCountState(int state) {
        LogUtil.info(TAG, "setSpeedUpCountState : " + state);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setSpeedUpCountState(state) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }
}
