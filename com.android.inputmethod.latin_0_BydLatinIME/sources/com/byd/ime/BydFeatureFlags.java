package com.byd.ime;

import android.content.Context;
import android.os.SystemProperties;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BydFeatureFlags.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/byd/ime/BydFeatureFlags;", "", "()V", "Companion", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class BydFeatureFlags {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final boolean DONE_ACTION_TO_ICON = true;
    public static final boolean DRAW_SPACE_LABEL = true;
    public static final boolean HIDE_IME_KEY_BUTTON_SHOW = true;
    public static final boolean HIDE_VOICE = true;
    public static final int HINT_LABEL_OFFSET = 5;
    public static final float HINT_LETTER_SIZE_ADJUST_MULTIPLE = 1.5f;
    public static final boolean KEY_SOUND_ADAPTER_SYSTEM = true;
    public static final boolean REMOVE_ADDITIONAL_SUBTYPE = true;
    public static final boolean REMOVE_EDIT_PERSONAL_DICTIONARY = true;
    public static final boolean REMOVE_LANGUAGE_DIALOG_SWITCH = true;
    public static final boolean REMOVE_PREF_CUSTOM_STYLE = true;
    public static final boolean REMOVE_PREF_ENABLE_EMOJI_ALT_PHYSICAL = true;
    public static final boolean REMOVE_PREF_ENABLE_SPLIT_KEYBOARD = true;
    public static final boolean REMOVE_PREF_GESTURE_TYPE = true;
    public static final boolean REMOVE_PREF_SHOW_SETUP_WIZARD_ICON = true;
    public static final boolean REMOVE_PREF_THEME = true;
    public static final boolean REMOVE_PREF_USE_CONTACTS_DICT = true;
    public static final boolean REMOVE_SETTINGS_KEY_PRESS = true;
    public static final boolean REMOVE_SPACE_POPUP_HINT = true;
    public static final boolean REMOVE_SWITCH_LANGUAGE_HINT = true;
    public static final boolean REMOVE_USE_PERSONALIZED_DICTS = true;
    public static final boolean SPACE_TOGGLE_MULTI_SUBTYPE = false;

    @JvmStatic
    public static final boolean isLandscape(Context context) {
        return INSTANCE.isLandscape(context);
    }

    @JvmStatic
    public static final boolean isRTL(Context context) {
        return INSTANCE.isRTL(context);
    }

    @JvmStatic
    public static final boolean isRightRudderCar() {
        return INSTANCE.isRightRudderCar();
    }

    /* JADX INFO: compiled from: BydFeatureFlags.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\b\u0010 \u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/byd/ime/BydFeatureFlags$Companion;", "", "()V", "DONE_ACTION_TO_ICON", "", "DRAW_SPACE_LABEL", "HIDE_IME_KEY_BUTTON_SHOW", "HIDE_VOICE", "HINT_LABEL_OFFSET", "", "HINT_LETTER_SIZE_ADJUST_MULTIPLE", "", "KEY_SOUND_ADAPTER_SYSTEM", "REMOVE_ADDITIONAL_SUBTYPE", "REMOVE_EDIT_PERSONAL_DICTIONARY", "REMOVE_LANGUAGE_DIALOG_SWITCH", "REMOVE_PREF_CUSTOM_STYLE", "REMOVE_PREF_ENABLE_EMOJI_ALT_PHYSICAL", "REMOVE_PREF_ENABLE_SPLIT_KEYBOARD", "REMOVE_PREF_GESTURE_TYPE", "REMOVE_PREF_SHOW_SETUP_WIZARD_ICON", "REMOVE_PREF_THEME", "REMOVE_PREF_USE_CONTACTS_DICT", "REMOVE_SETTINGS_KEY_PRESS", "REMOVE_SPACE_POPUP_HINT", "REMOVE_SWITCH_LANGUAGE_HINT", "REMOVE_USE_PERSONALIZED_DICTS", "SPACE_TOGGLE_MULTI_SUBTYPE", "isLandscape", "context", "Landroid/content/Context;", "isRTL", "isRightRudderCar", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean isLandscape(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getResources().getConfiguration().orientation == 2;
        }

        @JvmStatic
        public final boolean isRightRudderCar() {
            return SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", 0) > 0;
        }

        @JvmStatic
        public final boolean isRTL(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getResources().getConfiguration().getLayoutDirection() == 1;
        }
    }
}
