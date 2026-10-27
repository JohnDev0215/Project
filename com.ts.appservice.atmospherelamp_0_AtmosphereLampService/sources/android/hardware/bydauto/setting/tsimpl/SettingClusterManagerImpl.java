package android.hardware.bydauto.setting.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto_test.TestSettingManagerImplCallback;
import com.ts.lib.settings.cartips.OnCartipsChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.language.OnLanguageChangedListener;
import com.ts.lib.settings.theme.OnThemeChangedListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingClusterManagerImpl {
    private static final int INSTRUNENT_COMMAND_SUCCESS = 0;
    private static final int SETTING_COMMAND_BUSY = -2147482647;
    private static final int SETTING_COMMAND_FAILED = -2147482648;
    private static final int SETTING_COMMAND_INVALID = -2147482645;
    private static final int SETTING_COMMAND_TIMEOUT = -2147482646;
    private static final String TAG = "SettingClusterManagerImpl";
    private static volatile SettingClusterManagerImpl sInstance;
    private Context mContext;
    private SettingsManagerImpl mSettingsManagerImpl;
    private final List<AbsBYDAutoSettingListener> mListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;

    private SettingClusterManagerImpl(Context con) {
        this.mContext = con;
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(con);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setOnThemeChangedListener(new OnThemeChangedListener() { // from class: android.hardware.bydauto.setting.tsimpl.SettingClusterManagerImpl.1
            public void onThemeChanged(int state) {
                synchronized (SettingClusterManagerImpl.this.mListeners) {
                    if (SettingClusterManagerImpl.this.mListeners != null && SettingClusterManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoSettingListener listener : SettingClusterManagerImpl.this.mListeners) {
                            listener.onINSThemeChanged(state);
                        }
                    }
                }
            }
        });
        this.mSettingsManagerImpl.setOnLanguageChangedListener(new OnLanguageChangedListener() { // from class: android.hardware.bydauto.setting.tsimpl.SettingClusterManagerImpl.2
            public void onLanguageChanged(int value) {
                synchronized (SettingClusterManagerImpl.this.mListeners) {
                    if (SettingClusterManagerImpl.this.mListeners != null && SettingClusterManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoSettingListener listener : SettingClusterManagerImpl.this.mListeners) {
                            listener.onLanguageChanged(value);
                        }
                    }
                }
            }
        });
        this.mSettingsManagerImpl.setCartipsChangedListener(new OnCartipsChangedListener() { // from class: android.hardware.bydauto.setting.tsimpl.SettingClusterManagerImpl.3
            public void onCarTipsSoundTypeChanged(int value) {
                synchronized (SettingClusterManagerImpl.this.mListeners) {
                    if (SettingClusterManagerImpl.this.mListeners != null && SettingClusterManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoSettingListener listener : SettingClusterManagerImpl.this.mListeners) {
                            listener.onCarTipsSoundTypeChanged(value);
                        }
                    }
                }
            }
        });
    }

    public static SettingClusterManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (SettingClusterManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new SettingClusterManagerImpl(con);
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

    public void registerListener(AbsBYDAutoSettingListener listener) {
        LogUtil.debug(TAG, "registerListener is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
        } else if (!this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
        }
    }

    public void unregisterListener(AbsBYDAutoSettingListener listener) {
        LogUtil.debug(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
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

    public int setINSTheme(int value) {
        LogUtil.debug(TAG, "setINSTheme : " + value);
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mSettingsManagerImpl.getThemeSettingsManager() != null) {
            return this.mSettingsManagerImpl.getThemeSettingsManager().setINSTheme(value) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -2147482648;
    }

    public int getINSTheme() {
        LogUtil.debug(TAG, "getINSTheme");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mSettingsManagerImpl.getThemeSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getThemeSettingsManager().getINSTheme();
    }

    public int getLanguage() {
        LogUtil.debug(TAG, "getLanguage");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mSettingsManagerImpl.getLanguageSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getLanguageSettingsManager().getLanguage();
    }

    public int setLanguage(int value) {
        LogUtil.debug(TAG, TestSettingManagerImplCallback.SETLANGUAGE);
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mSettingsManagerImpl.getLanguageSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getLanguageSettingsManager().setLanguage(value);
    }

    public int getCarTipsSoundType() {
        LogUtil.debug(TAG, "getCarTipsSoundType");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mSettingsManagerImpl.getCartipsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getCartipsSettingsManager().getCarTipsSoundType();
    }

    public int setCarTipsSoundType(int value) {
        LogUtil.debug(TAG, TestSettingManagerImplCallback.SETCARTIPSSOUNDTYPE);
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mSettingsManagerImpl.getCartipsSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getCartipsSettingsManager().setCarTipsSoundType(value);
    }
}
