package android.hardware.bydauto.test.tsimpl;

import M5.b;
import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import com.ts.lib.settings.appconfig.OnAppConfigChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
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

    private TestSettingsManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(context);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setAppConfigChangedListener(new OnAppConfigChangedListener() { // from class: android.hardware.bydauto.test.tsimpl.TestSettingsManagerImpl.1
            public void onChangedConfigArea(int i7) {
                synchronized (TestSettingsManagerImpl.this.mListeners) {
                    try {
                        if (TestSettingsManagerImpl.this.mListeners != null && TestSettingsManagerImpl.this.mListeners.size() > 0) {
                            Iterator it = TestSettingsManagerImpl.this.mListeners.iterator();
                            while (it.hasNext()) {
                                ((AbsBYDAutoTestListener) it.next()).onChangedConfigArea(i7);
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            public void onChangedCpaa(int i7) {
                synchronized (TestSettingsManagerImpl.this.mListeners) {
                    try {
                        if (TestSettingsManagerImpl.this.mListeners != null && TestSettingsManagerImpl.this.mListeners.size() > 0) {
                            Iterator it = TestSettingsManagerImpl.this.mListeners.iterator();
                            while (it.hasNext()) {
                                ((AbsBYDAutoTestListener) it.next()).onChangedConfigArea(i7);
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static TestSettingsManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (TestSettingsManagerImpl.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new TestSettingsManagerImpl(context);
                    }
                } finally {
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

    public int setConfigArea(String str) {
        LogUtil.info(TAG, b.g("setConfigArea : ", str));
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setConfigArea(str) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public int setCpaaInstall(boolean z6) {
        LogUtil.info(TAG, "setCpaaInstall : " + z6);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mSettingsManagerImpl.getAppConfigSettingsManger() != null) {
            return this.mSettingsManagerImpl.getAppConfigSettingsManger().setCpaaInstall(z6) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "getAppConfigSettingsManger is null");
        return -2147482648;
    }

    public void unregisterListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        LogUtil.info(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (absBYDAutoTestListener == null) {
            LogUtil.info(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            try {
                if (this.mListeners.contains(absBYDAutoTestListener)) {
                    this.mListeners.remove(absBYDAutoTestListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
