package android.hardware.bydauto.test.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import com.ts.lib.settings.appconfig.OnAppConfigChangedListener;
import com.ts.lib.settings.appconfig.OnCarTypeChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestSettingsManagerImpl {
    private static final int INSTRUNENT_COMMAND_SUCCESS = 0;
    private static final int SETTING_COMMAND_BUSY = -2147482647;
    private static final int SETTING_COMMAND_FAILED = -2147482648;
    private static final int SETTING_COMMAND_INVALID = -2147482645;
    private static final int SETTING_COMMAND_TIMEOUT = -2147482646;
    private static final String TAG = "TestSettingsManagerImpl";
    private static volatile TestSettingsManagerImpl sInstance;
    private Context mContext;
    private SettingsManagerImpl mSettingsManagerImpl;
    private final List<AbsBYDAutoTestListener> mListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;

    private TestSettingsManagerImpl(Context con) {
        this.mContext = con;
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(con);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setAppConfigChangedListener(new OnAppConfigChangedListener() { // from class: android.hardware.bydauto.test.tsimpl.TestSettingsManagerImpl.1
            public void onChangedConfigArea(int state) {
                synchronized (TestSettingsManagerImpl.this.mListeners) {
                    if (TestSettingsManagerImpl.this.mListeners != null && TestSettingsManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoTestListener listener : TestSettingsManagerImpl.this.mListeners) {
                            listener.onChangedConfigArea(state);
                        }
                    }
                }
            }

            public void onChangedCpaa(int state) {
                synchronized (TestSettingsManagerImpl.this.mListeners) {
                    if (TestSettingsManagerImpl.this.mListeners != null && TestSettingsManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoTestListener listener : TestSettingsManagerImpl.this.mListeners) {
                            listener.onChangedCpaa(state);
                        }
                    }
                }
            }
        });
        this.mSettingsManagerImpl.setCarTypeChangedListener(new OnCarTypeChangedListener() { // from class: android.hardware.bydauto.test.tsimpl.TestSettingsManagerImpl.2
            public void onStatusChangeCarType(boolean status) {
                synchronized (TestSettingsManagerImpl.this.mListeners) {
                    if (TestSettingsManagerImpl.this.mListeners != null && TestSettingsManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoTestListener listener : TestSettingsManagerImpl.this.mListeners) {
                            listener.onStatusChangeCarType(status);
                        }
                    }
                }
            }
        });
    }

    public static TestSettingsManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (TestSettingsManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new TestSettingsManagerImpl(con);
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

    public void registerListener(AbsBYDAutoTestListener listener) {
        LogUtil.info(TAG, "registerListener is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (listener == null) {
            LogUtil.info(TAG, "listener is null");
        } else if (!this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
        }
    }

    public void unregisterListener(AbsBYDAutoTestListener listener) {
        LogUtil.info(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
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

    public int setConfigArea(String code) {
        LogUtil.info(TAG, "setConfigArea : " + code);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setConfigArea(code) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public int setCpaaInstall(boolean state) {
        LogUtil.info(TAG, "setCpaaInstall : " + state);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setCpaaInstall(state) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public int setCpaaInstall(int type, boolean state) {
        LogUtil.info(TAG, "setCpaaInstall type :" + type + " state : " + state);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setCpaaInstallType(type, state) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public int setCarType(int code) {
        LogUtil.info(TAG, "setCarType : " + code);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setCarType(code) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public void setDefaultLanguage(int countryCode) {
        LogUtil.warning(TAG, "setDefaultLanguage : " + countryCode);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() == null) {
            LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        } else {
            this.mSettingsManagerImpl.getAppConfigSettingsManger().setDefaultLanguages(countryCode);
        }
    }
}
