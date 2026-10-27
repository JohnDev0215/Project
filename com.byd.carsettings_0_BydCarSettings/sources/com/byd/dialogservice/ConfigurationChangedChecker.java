package com.byd.dialogservice;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import b.b.g.g.b;
import b.b.g.r.f;
import b.b.g.r.i;
import b.b.i.c;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class ConfigurationChangedChecker {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static UiModeManager f3497a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f3498b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Locale f3499c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f3500d;

    public enum ConfigurationChangedMode {
        UNKNOW,
        SCREEN_ORIENTATION_CHANGED,
        DARK_LIGHT_CHANGED,
        LANGUAGE_CHANGED
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3502a;

        static {
            int[] iArr = new int[ConfigurationChangedMode.values().length];
            f3502a = iArr;
            try {
                iArr[ConfigurationChangedMode.SCREEN_ORIENTATION_CHANGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3502a[ConfigurationChangedMode.DARK_LIGHT_CHANGED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3502a[ConfigurationChangedMode.LANGUAGE_CHANGED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void a(Context context, ConfigurationChangedMode configurationChangedMode) {
        b.b.m.c.a.g("ConfigurationChangedChecker", "---doOnConfigurationChanged---mode: " + configurationChangedMode);
        if (configurationChangedMode != null && a.f3502a[configurationChangedMode.ordinal()] == 3) {
            b.f(context, null);
            b.b.g.p.m.a.h(context);
            f.e(context);
            i.f(context);
        }
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        f3498b = context.getApplicationContext().getResources().getConfiguration().orientation;
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        f3497a = uiModeManager;
        f3500d = uiModeManager.getNightMode();
        f3499c = context.getResources().getConfiguration().getLocales().get(0);
        f.c(context);
        b.b.m.c.a.a("ConfigurationChangedChecker", "init", ", curOrientation: " + f3498b);
    }

    public static void c(Context context, Configuration configuration) {
        b.b.m.c.a.a("ConfigurationChangedChecker", "onConfigurationChanged", ", newConfig: " + configuration);
        if (configuration == null) {
            return;
        }
        int i = configuration.orientation;
        ConfigurationChangedMode configurationChangedMode = i != f3498b ? ConfigurationChangedMode.SCREEN_ORIENTATION_CHANGED : null;
        f3498b = i;
        int nightMode = f3497a.getNightMode();
        if (nightMode != f3500d) {
            configurationChangedMode = ConfigurationChangedMode.DARK_LIGHT_CHANGED;
        }
        f3500d = nightMode;
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        if (!locale.toString().equals(f3499c.toString())) {
            configurationChangedMode = ConfigurationChangedMode.LANGUAGE_CHANGED;
        }
        f3499c = locale;
        b.b.m.c.a.g("ConfigurationChangedChecker", "onConfigurationChanged", ", locale: " + locale.toString());
        b.b.m.c.a.g("ConfigurationChangedChecker", "onConfigurationChanged", ", mode: " + configurationChangedMode);
        if (configurationChangedMode == null) {
            configurationChangedMode = ConfigurationChangedMode.UNKNOW;
        }
        if (configurationChangedMode != ConfigurationChangedMode.UNKNOW) {
            c.j(context, configuration);
        }
        a(context, configurationChangedMode);
    }
}
