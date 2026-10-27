package android.hardware.bydauto.test.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import com.ts.lib.settings.appconfig.OnAppConfigChangedListener;
import com.ts.lib.settings.appconfig.OnCarTypeChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import spi.d;
import spi.e;
import spi.hi;

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

    private TestSettingsManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(context);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setAppConfigChangedListener(new OnAppConfigChangedListener() { // from class: android.hardware.bydauto.test.tsimpl.TestSettingsManagerImpl.1
            public void onChangedConfigArea(int i) {
                synchronized (TestSettingsManagerImpl.this.mListeners) {
                    if (TestSettingsManagerImpl.this.mListeners != null && TestSettingsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = TestSettingsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoTestListener) it.next()).onChangedConfigArea(i);
                        }
                    }
                }
            }

            public void onChangedCpaa(int i) {
                synchronized (TestSettingsManagerImpl.this.mListeners) {
                    if (TestSettingsManagerImpl.this.mListeners != null && TestSettingsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = TestSettingsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoTestListener) it.next()).onChangedCpaa(i);
                        }
                    }
                }
            }
        });
        this.mSettingsManagerImpl.setCarTypeChangedListener(new OnCarTypeChangedListener() { // from class: android.hardware.bydauto.test.tsimpl.TestSettingsManagerImpl.2
            public void onStatusChangeCarType(boolean z) {
                synchronized (TestSettingsManagerImpl.this.mListeners) {
                    if (TestSettingsManagerImpl.this.mListeners != null && TestSettingsManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = TestSettingsManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoTestListener) it.next()).onStatusChangeCarType(z);
                        }
                    }
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
        }
    }

    public static TestSettingsManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (TestSettingsManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new TestSettingsManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public void registerListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        LogUtil.info(TAG, "registerListener is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (absBYDAutoTestListener == null) {
            LogUtil.info(TAG, "listener is null");
        } else {
            if (this.mListeners.contains(absBYDAutoTestListener)) {
                return;
            }
            this.mListeners.add(absBYDAutoTestListener);
        }
    }

    public int setCarType(int i) {
        LogUtil.info(TAG, e.c("setCarType : ", i));
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setCarType(i) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public int setConfigArea(String str) {
        LogUtil.info(TAG, d.f("setConfigArea : ", str));
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setConfigArea(str) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public int setCpaaInstall(int i, boolean z) {
        LogUtil.info(TAG, "setCpaaInstall type :" + i + " state : " + z);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setCpaaInstallType(i, z) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public int setCpaaInstall(boolean z) {
        LogUtil.info(TAG, hi.e("setCpaaInstall : ", z));
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setCpaaInstall(z) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public void setDefaultLanguage(int i) {
        LogUtil.warning(TAG, e.c("setDefaultLanguage : ", i));
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() == null) {
            LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        } else {
            this.mSettingsManagerImpl.getAppConfigSettingsManger().setDefaultLanguages(i);
        }
    }

    public void unregisterListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        LogUtil.info(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (absBYDAutoTestListener == null) {
            LogUtil.info(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(absBYDAutoTestListener)) {
                this.mListeners.remove(absBYDAutoTestListener);
            }
        }
    }
}
