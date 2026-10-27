package com.byd.systemsettings.system;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import com.byd.systemsettings.system.utils.BydDateFormat;
import com.byd.systemsettings.ui.dialog.ServiceDialogHolder;
import java.util.Locale;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public final class ConfigurationChangedChecker {
    private static final String TAG = "ConfigurationChangedChecker";
    private static Locale curLocale;
    public static int curOrientation;
    private static int curUIMode;
    private static UiModeManager um;

    /* JADX INFO: renamed from: com.byd.systemsettings.system.ConfigurationChangedChecker$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$systemsettings$system$ConfigurationChangedChecker$ConfigurationChangedMode;

        static {
            int[] iArr = new int[ConfigurationChangedMode.values().length];
            $SwitchMap$com$byd$systemsettings$system$ConfigurationChangedChecker$ConfigurationChangedMode = iArr;
            try {
                iArr[ConfigurationChangedMode.SCREEN_ORIENTATION_CHANGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$system$ConfigurationChangedChecker$ConfigurationChangedMode[ConfigurationChangedMode.DARK_LIGHT_CHANGED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$system$ConfigurationChangedChecker$ConfigurationChangedMode[ConfigurationChangedMode.LANGUAGE_CHANGED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum ConfigurationChangedMode {
        UNKNOW,
        SCREEN_ORIENTATION_CHANGED,
        DARK_LIGHT_CHANGED,
        LANGUAGE_CHANGED
    }

    private static void doOnConfigurationChanged(Context context, ConfigurationChangedMode configurationChangedMode) {
        a.e(TAG, "---doOnConfigurationChanged---mode: " + configurationChangedMode);
        if (configurationChangedMode != null && AnonymousClass1.$SwitchMap$com$byd$systemsettings$system$ConfigurationChangedChecker$ConfigurationChangedMode[configurationChangedMode.ordinal()] == 3) {
            BydDateFormat.onLanguageChanged(context);
        }
    }

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        curOrientation = context.getApplicationContext().getResources().getConfiguration().orientation;
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        um = uiModeManager;
        curUIMode = uiModeManager.getNightMode();
        curLocale = context.getResources().getConfiguration().getLocales().get(0);
        BydDateFormat.init(context);
        a.a(TAG, "init", ", curOrientation: " + curOrientation);
    }

    public static void onConfigurationChanged(Context context, Configuration configuration) {
        a.a(TAG, "onConfigurationChanged", ", newConfig: " + configuration);
        if (configuration == null) {
            return;
        }
        int i8 = configuration.orientation;
        ConfigurationChangedMode configurationChangedMode = i8 != curOrientation ? ConfigurationChangedMode.SCREEN_ORIENTATION_CHANGED : null;
        curOrientation = i8;
        int nightMode = um.getNightMode();
        if (nightMode != curUIMode) {
            configurationChangedMode = ConfigurationChangedMode.DARK_LIGHT_CHANGED;
        }
        curUIMode = nightMode;
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        if (!locale.toString().equals(curLocale.toString())) {
            configurationChangedMode = ConfigurationChangedMode.LANGUAGE_CHANGED;
        }
        curLocale = locale;
        a.e(TAG, "onConfigurationChanged", ", locale: " + locale.toString());
        a.e(TAG, "onConfigurationChanged", ", mode: " + configurationChangedMode);
        if (configurationChangedMode == null) {
            configurationChangedMode = ConfigurationChangedMode.UNKNOW;
        }
        if (configurationChangedMode != ConfigurationChangedMode.UNKNOW) {
            ServiceDialogHolder.onConfigurationChanged(context, configuration);
        }
        doOnConfigurationChanged(context, configurationChangedMode);
    }
}
