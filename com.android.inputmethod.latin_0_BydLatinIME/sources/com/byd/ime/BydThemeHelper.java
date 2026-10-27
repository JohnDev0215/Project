package com.byd.ime;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.inputmethod.keyboard.KeyboardTheme;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BydThemeHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0007H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/byd/ime/BydThemeHelper;", "", "prefs", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "mPrefs", "isValidKeyboardThemeId", "", "themeId", "", "setKeyboardThemePreference", "", "prefKey", "", "updateThemeByNightMode", "context", "Landroid/content/Context;", "updateThemePreference", "isNightNode", "Companion", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class BydThemeHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ILLEGAL_THEME_ID_STRING = "ThisCausesNumberFormatExecption";
    private static final int THEME_ID_ILLEGAL = -3;
    private static final int THEME_ID_NULL = -1;
    private static final int THEME_ID_UNKNOWN = -2;
    private SharedPreferences mPrefs;

    private final boolean isValidKeyboardThemeId(int themeId) {
        return themeId == 0 || themeId == 2 || themeId == 3 || themeId == 4;
    }

    public BydThemeHelper(SharedPreferences prefs) {
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        this.mPrefs = prefs;
    }

    /* JADX INFO: compiled from: BydThemeHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/byd/ime/BydThemeHelper$Companion;", "", "()V", "ILLEGAL_THEME_ID_STRING", "", "THEME_ID_ILLEGAL", "", "THEME_ID_NULL", "THEME_ID_UNKNOWN", "isNightMode", "", "context", "Landroid/content/Context;", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isNightMode(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return (context.getResources().getConfiguration().uiMode & 48) == 32;
        }
    }

    public final void updateThemeByNightMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        updateThemePreference(INSTANCE.isNightMode(context));
    }

    private final void updateThemePreference(boolean isNightNode) {
        if (isNightNode) {
            setKeyboardThemePreference(KeyboardTheme.LXX_KEYBOARD_THEME_KEY, 0);
        } else {
            setKeyboardThemePreference(KeyboardTheme.LXX_KEYBOARD_THEME_KEY, 2);
        }
    }

    private final void setKeyboardThemePreference(String prefKey, int themeId) {
        String string = Integer.toString(themeId);
        Intrinsics.checkNotNullExpressionValue(string, "toString(themeId)");
        if (isValidKeyboardThemeId(themeId) || themeId == -2) {
            SharedPreferences sharedPreferences = this.mPrefs;
            Intrinsics.checkNotNull(sharedPreferences);
            sharedPreferences.edit().putString(prefKey, string).apply();
        } else if (themeId == -1) {
            SharedPreferences sharedPreferences2 = this.mPrefs;
            Intrinsics.checkNotNull(sharedPreferences2);
            sharedPreferences2.edit().remove(prefKey).apply();
        } else {
            SharedPreferences sharedPreferences3 = this.mPrefs;
            Intrinsics.checkNotNull(sharedPreferences3);
            sharedPreferences3.edit().putString(prefKey, ILLEGAL_THEME_ID_STRING).apply();
        }
    }
}
