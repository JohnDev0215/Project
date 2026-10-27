package com.amazon.mp3.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.casting.CastingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public enum SplashScreen {
    CASTING("com.amazon.music.CASTING_SPLASHSCREEN_DISPLAYED"),
    FTU(""),
    PRIME_UPSELL(""),
    ALEXA_ONBOARDING("alexa_onboarding_splash_screen_shown");

    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) SplashScreen.class);
    private String sharedPrefKey;
    private boolean shownSinceLaunch;

    SplashScreen(final String sharedPrefKey) {
        this.sharedPrefKey = sharedPrefKey;
    }

    public static boolean hasShownAnySinceLaunch() {
        boolean z = false;
        for (SplashScreen splashScreen : values()) {
            if (splashScreen.shownSinceLaunch) {
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.util.SplashScreen$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$util$SplashScreen;

        static {
            int[] iArr = new int[SplashScreen.values().length];
            $SwitchMap$com$amazon$mp3$util$SplashScreen = iArr;
            try {
                iArr[SplashScreen.CASTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$SplashScreen[SplashScreen.ALEXA_ONBOARDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public boolean canShow(final Context context) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$util$SplashScreen[ordinal()];
        if (i == 1) {
            if (!CastingUtil.isAlexaCastingEnabled() || hasShownAnySinceLaunch() || hasBeenShown(context)) {
                return false;
            }
        } else if (i != 2 || !AmazonApplication.getCapabilities().isAlexaEnabled() || hasShownAnySinceLaunch() || hasBeenShown(context)) {
            return false;
        }
        return true;
    }

    public boolean hasBeenShown(final Context context) {
        return this.shownSinceLaunch || !(context == null || this.sharedPrefKey == "" || !SettingsUtil.getPrefs(context).getBoolean(this.sharedPrefKey, false));
    }

    public void setShown(final Context context) {
        this.shownSinceLaunch = true;
        if (context == null || this.sharedPrefKey == "") {
            return;
        }
        LOG.debug("Storing " + this + " splash screen as shown into shared preferences");
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putBoolean(this.sharedPrefKey, true);
        editorEdit.apply();
    }
}
