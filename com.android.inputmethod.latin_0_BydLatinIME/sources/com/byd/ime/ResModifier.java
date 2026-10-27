package com.byd.ime;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.android.inputmethod.keyboard.internal.KeyDrawParams;
import com.android.inputmethod.latin.R;
import com.byd.ime.subtype.SubtypeManager;
import com.byd.ime.subtype.model.SubtypeConfigData;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResModifier.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/byd/ime/ResModifier;", "", "()V", "Companion", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ResModifier {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final boolean currentSubtypeShouldRTL() {
        return INSTANCE.currentSubtypeShouldRTL();
    }

    @JvmStatic
    public static final int getEmojiTabBackground(Context context) {
        return INSTANCE.getEmojiTabBackground(context);
    }

    @JvmStatic
    public static final Drawable getRTLDeleteDrawable(Context context) {
        return INSTANCE.getRTLDeleteDrawable(context);
    }

    @JvmStatic
    public static final Drawable getShiftDrawable(Context context) {
        return INSTANCE.getShiftDrawable(context);
    }

    @JvmStatic
    public static final String resetLanguageDisplayName(InputMethodSubtype inputMethodSubtype) {
        return INSTANCE.resetLanguageDisplayName(inputMethodSubtype);
    }

    @JvmStatic
    public static final void resetSwitchAlphaSymbolTextSize(Context context, Key key, KeyDrawParams keyDrawParams, Paint paint) {
        INSTANCE.resetSwitchAlphaSymbolTextSize(context, key, keyDrawParams, paint);
    }

    /* JADX INFO: compiled from: ResModifier.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J(\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007¨\u0006\u0018"}, d2 = {"Lcom/byd/ime/ResModifier$Companion;", "", "()V", "currentSubtypeShouldRTL", "", "getEmojiTabBackground", "", "context", "Landroid/content/Context;", "getRTLDeleteDrawable", "Landroid/graphics/drawable/Drawable;", "getShiftDrawable", "resetLanguageDisplayName", "", "mSubtype", "Landroid/view/inputmethod/InputMethodSubtype;", "resetSwitchAlphaSymbolTextSize", "", "key", "Lcom/android/inputmethod/keyboard/Key;", "params", "Lcom/android/inputmethod/keyboard/internal/KeyDrawParams;", "paint", "Landroid/graphics/Paint;", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Drawable getShiftDrawable(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Resources resources = context.getResources();
            boolean zIsNightMode = BydThemeHelper.INSTANCE.isNightMode(context);
            int keyboardShiftMode = KeyboardSwitcher.getInstance().getKeyboardShiftMode();
            if (keyboardShiftMode == 0) {
                if (zIsNightMode) {
                    return resources.getDrawable(R.drawable.byd_dark_key_shift_normal, null);
                }
                return resources.getDrawable(R.drawable.byd_key_shift_normal, null);
            }
            if (keyboardShiftMode == 1) {
                if (zIsNightMode) {
                    return resources.getDrawable(R.drawable.byd_dark_key_shift_manual, null);
                }
                return resources.getDrawable(R.drawable.byd_key_shift_manual, null);
            }
            if (keyboardShiftMode == 3) {
                if (zIsNightMode) {
                    return resources.getDrawable(R.drawable.byd_dark_key_shift_lock, null);
                }
                return resources.getDrawable(R.drawable.byd_key_shift_lock, null);
            }
            if (keyboardShiftMode != 5) {
                return null;
            }
            if (zIsNightMode) {
                return resources.getDrawable(R.drawable.byd_dark_key_shift_manual, null);
            }
            return resources.getDrawable(R.drawable.byd_key_shift_manual, null);
        }

        @JvmStatic
        public final int getEmojiTabBackground(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            boolean zIsNightMode = BydThemeHelper.INSTANCE.isNightMode(context);
            context.getResources();
            return zIsNightMode ? R.drawable.emoji_tab_ics : R.drawable.emoji_tab_klp;
        }

        @JvmStatic
        public final Drawable getRTLDeleteDrawable(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getResources().getDrawable(R.drawable.delete_rtl, null);
        }

        @JvmStatic
        public final void resetSwitchAlphaSymbolTextSize(Context context, Key key, KeyDrawParams params, Paint paint) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(params, "params");
            Intrinsics.checkNotNullParameter(paint, "paint");
            if (key.getCode() == -3 || key.getCode() == -1 || key.getCode() == -14) {
                if (BydFeatureFlags.INSTANCE.isLandscape(context)) {
                    paint.setTextSize(params.mLetterSize);
                } else {
                    paint.setTextSize(context.getResources().getDimension(R.dimen.shift_label_size));
                }
            }
        }

        @JvmStatic
        public final String resetLanguageDisplayName(InputMethodSubtype mSubtype) {
            Intrinsics.checkNotNullParameter(mSubtype, "mSubtype");
            if (Intrinsics.areEqual(mSubtype.getLocale(), SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Spanish_US)) {
                return SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Spanish_US_DISPLAY_NAME;
            }
            if (Intrinsics.areEqual(mSubtype.getLocale(), SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Portuguese_Brazil)) {
                return SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Portuguese_Brazil_DISPLAY_NAME;
            }
            return null;
        }

        @JvmStatic
        public final boolean currentSubtypeShouldRTL() {
            String mCurrentSubtypeLocal = SubtypeManager.INSTANCE.getInstance().getMSubtypeCacheModel().getMCurrentSubtypeLocal();
            return Intrinsics.areEqual(mCurrentSubtypeLocal, SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Arabic) || Intrinsics.areEqual(mCurrentSubtypeLocal, SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Hebrew);
        }
    }
}
