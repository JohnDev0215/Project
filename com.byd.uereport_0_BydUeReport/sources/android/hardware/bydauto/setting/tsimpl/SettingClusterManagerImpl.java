package android.hardware.bydauto.setting.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import com.ts.lib.settings.cartips.OnCartipsChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.language.OnLanguageChangedListener;
import com.ts.lib.settings.theme.OnThemeChangedListener;
import java.util.ArrayList;
import java.util.Iterator;
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

    private SettingClusterManagerImpl(Context context) {
        this.mContext = context;
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(context);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setOnThemeChangedListener(new OnThemeChangedListener() { // from class: android.hardware.bydauto.setting.tsimpl.SettingClusterManagerImpl.1
            public void onThemeChanged(int i8) {
                synchronized (SettingClusterManagerImpl.this.mListeners) {
                    if (SettingClusterManagerImpl.this.mListeners != null && SettingClusterManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = SettingClusterManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoSettingListener) it.next()).onINSThemeChanged(i8);
                        }
                    }
                }
            }
        });
        this.mSettingsManagerImpl.setOnLanguageChangedListener(new OnLanguageChangedListener() { // from class: android.hardware.bydauto.setting.tsimpl.SettingClusterManagerImpl.2
            public void onLanguageChanged(int i8) {
                synchronized (SettingClusterManagerImpl.this.mListeners) {
                    if (SettingClusterManagerImpl.this.mListeners != null && SettingClusterManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = SettingClusterManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoSettingListener) it.next()).onLanguageChanged(i8);
                        }
                    }
                }
            }
        });
        this.mSettingsManagerImpl.setCartipsChangedListener(new OnCartipsChangedListener() { // from class: android.hardware.bydauto.setting.tsimpl.SettingClusterManagerImpl.3
            public void onCarTipsSoundTypeChanged(int i8) {
                synchronized (SettingClusterManagerImpl.this.mListeners) {
                    if (SettingClusterManagerImpl.this.mListeners != null && SettingClusterManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = SettingClusterManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoSettingListener) it.next()).onCarTipsSoundTypeChanged(i8);
                        }
                    }
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static SettingClusterManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SettingClusterManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new SettingClusterManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getCarTipsSoundType() {
        LogUtil.debug(TAG, "getCarTipsSoundType");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mSettingsManagerImpl.getCartipsSettingsManager() != null) {
            return this.mSettingsManagerImpl.getCartipsSettingsManager().getCarTipsSoundType();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int getINSTheme() {
        LogUtil.debug(TAG, "getINSTheme");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mSettingsManagerImpl.getThemeSettingsManager() != null) {
            return this.mSettingsManagerImpl.getThemeSettingsManager().getINSTheme();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int getLanguage() {
        LogUtil.debug(TAG, "getLanguage");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_GET);
        if (this.mSettingsManagerImpl.getLanguageSettingsManager() != null) {
            return this.mSettingsManagerImpl.getLanguageSettingsManager().getLanguage();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public void registerListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        LogUtil.debug(TAG, "registerListener is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (absBYDAutoSettingListener == null) {
            LogUtil.error(TAG, "listener is null");
        } else {
            if (this.mListeners.contains(absBYDAutoSettingListener)) {
                return;
            }
            this.mListeners.add(absBYDAutoSettingListener);
        }
    }

    public int setCarTipsSoundType(int i8) {
        LogUtil.debug(TAG, "setCarTipsSoundType");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mSettingsManagerImpl.getCartipsSettingsManager() != null) {
            return this.mSettingsManagerImpl.getCartipsSettingsManager().setCarTipsSoundType(i8);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int setINSTheme(int i8) {
        LogUtil.debug(TAG, "setINSTheme : " + i8);
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mSettingsManagerImpl.getThemeSettingsManager() != null) {
            return this.mSettingsManagerImpl.getThemeSettingsManager().setINSTheme(i8) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -2147482648;
    }

    public int setLanguage(int i8) {
        LogUtil.debug(TAG, "setLanguage");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_SET);
        if (this.mSettingsManagerImpl.getLanguageSettingsManager() != null) {
            return this.mSettingsManagerImpl.getLanguageSettingsManager().setLanguage(i8);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public void unregisterListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        LogUtil.debug(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        if (absBYDAutoSettingListener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(absBYDAutoSettingListener)) {
                this.mListeners.remove(absBYDAutoSettingListener);
            }
        }
    }
}
