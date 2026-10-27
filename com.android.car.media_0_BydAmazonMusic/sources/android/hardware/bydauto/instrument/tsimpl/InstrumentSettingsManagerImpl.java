package android.hardware.bydauto.instrument.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import com.ts.lib.settings.cluster.OnClusterChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import spi.c;

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

    private InstrumentSettingsManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(context);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setOnClusterSettingChangedListener(new OnClusterChangedListener() { // from class: android.hardware.bydauto.instrument.tsimpl.InstrumentSettingsManagerImpl.1
            public void onDayNightModeChanged(int i) {
                synchronized (InstrumentSettingsManagerImpl.this.mListeners) {
                    if (InstrumentSettingsManagerImpl.this.mListeners != null && InstrumentSettingsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = InstrumentSettingsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoInstrumentListener) it.next()).onDayNightModeChanged(i);
                        }
                    }
                }
            }

            public void onINSAdasCenterDisplayChanged(int i) {
                synchronized (InstrumentSettingsManagerImpl.this.mListeners) {
                    if (InstrumentSettingsManagerImpl.this.mListeners != null && InstrumentSettingsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = InstrumentSettingsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoInstrumentListener) it.next()).onINSAdasCenterDisplayChanged(i);
                        }
                    }
                }
            }

            public void onINSCenterDisplay(int i) {
                synchronized (InstrumentSettingsManagerImpl.this.mListeners) {
                    if (InstrumentSettingsManagerImpl.this.mListeners != null && InstrumentSettingsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = InstrumentSettingsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoInstrumentListener) it.next()).onINSCenterDisplay(i);
                        }
                    }
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(c.f("requires permission ", str));
        }
    }

    public static InstrumentSettingsManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (InstrumentMediaManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new InstrumentSettingsManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getDayNightMode() {
        LogUtil.info(TAG, "getDayNightMode");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().getDayNightMode();
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -1;
    }

    public int getINSAdasCenterDisplay() {
        LogUtil.info(TAG, "getINSNavDisplayMode");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().getINSAdasCenterDisplay();
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -1;
    }

    public int getINSCenterDisplay() {
        LogUtil.info(TAG, "getINSCenterDisplay");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().getINSCenterDisplay();
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -1;
    }

    public void registerListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        LogUtil.info(TAG, "registerListener is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (absBYDAutoInstrumentListener == null) {
            LogUtil.info(TAG, "listener is null");
        } else {
            if (this.mListeners.contains(absBYDAutoInstrumentListener)) {
                return;
            }
            this.mListeners.add(absBYDAutoInstrumentListener);
        }
    }

    public int setDayNightMode(int i) {
        LogUtil.info(TAG, c.d("setDayNightMode : ", i));
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setDayNightMode(i) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public int setINSAdasCenterDisplay(int i) {
        LogUtil.info(TAG, c.d("setINSAdasCenterDisplay : ", i));
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setINSAdasCenterDisplay(i) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public int setINSCenterDisplay(int i) {
        LogUtil.info(TAG, c.d("setINSCenterDisplay : ", i));
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setINSCenterDisplay(i) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public int setSpeedUpCountState(int i) {
        LogUtil.info(TAG, c.d("setSpeedUpCountState : ", i));
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setSpeedUpCountState(i) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public int setTimeFormat(int i) {
        LogUtil.info(TAG, c.d("setTimeFormat : ", i));
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setTimeFormat(i) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        LogUtil.info(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (absBYDAutoInstrumentListener == null) {
            LogUtil.info(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(absBYDAutoInstrumentListener)) {
                this.mListeners.remove(absBYDAutoInstrumentListener);
            }
        }
    }
}
