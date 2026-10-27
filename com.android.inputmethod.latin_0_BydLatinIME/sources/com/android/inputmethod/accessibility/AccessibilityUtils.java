package com.android.inputmethod.accessibility;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.EditorInfo;
import com.android.inputmethod.compat.SettingsSecureCompatUtils;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.utils.InputTypeUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class AccessibilityUtils {
    private static final String CLASS = "com.android.inputmethod.accessibility.AccessibilityUtils";
    private static final boolean ENABLE_ACCESSIBILITY = true;
    private static final String TAG = "AccessibilityUtils";
    private AccessibilityManager mAccessibilityManager;
    private AudioManager mAudioManager;
    private String mAutoCorrectionWord;
    private Context mContext;
    private String mTypedWord;
    private static final String PACKAGE = AccessibilityUtils.class.getPackage().getName();
    private static final AccessibilityUtils sInstance = new AccessibilityUtils();

    public static void init(final Context context) {
        sInstance.initInternal(context);
    }

    public static AccessibilityUtils getInstance() {
        return sInstance;
    }

    private AccessibilityUtils() {
    }

    private void initInternal(final Context context) {
        this.mContext = context;
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public boolean isAccessibilityEnabled() {
        return this.mAccessibilityManager.isEnabled();
    }

    public boolean isTouchExplorationEnabled() {
        return isAccessibilityEnabled() && this.mAccessibilityManager.isTouchExplorationEnabled();
    }

    public static boolean isTouchExplorationEvent(final MotionEvent event) {
        int action = event.getAction();
        return action == 9 || action == 10 || action == 7;
    }

    public boolean shouldObscureInput(final EditorInfo editorInfo) {
        if (editorInfo == null) {
            return false;
        }
        if (SettingsSecureCompatUtils.ACCESSIBILITY_SPEAK_PASSWORD != null) {
            if (Settings.Secure.getInt(this.mContext.getContentResolver(), SettingsSecureCompatUtils.ACCESSIBILITY_SPEAK_PASSWORD, 0) != 0) {
                return false;
            }
        }
        if (this.mAudioManager.isWiredHeadsetOn() || this.mAudioManager.isBluetoothA2dpOn()) {
            return false;
        }
        return InputTypeUtils.isPasswordInputType(editorInfo.inputType);
    }

    public void setAutoCorrection(final SuggestedWords suggestedWords) {
        if (suggestedWords.mWillAutoCorrect) {
            this.mAutoCorrectionWord = suggestedWords.getWord(1);
            SuggestedWords.SuggestedWordInfo suggestedWordInfo = suggestedWords.mTypedWordInfo;
            if (suggestedWordInfo == null) {
                this.mTypedWord = null;
                return;
            } else {
                this.mTypedWord = suggestedWordInfo.mWord;
                return;
            }
        }
        this.mAutoCorrectionWord = null;
        this.mTypedWord = null;
    }

    public String getAutoCorrectionDescription(final String keyCodeDescription, final boolean shouldObscure) {
        return (TextUtils.isEmpty(this.mAutoCorrectionWord) || TextUtils.equals(this.mAutoCorrectionWord, this.mTypedWord)) ? keyCodeDescription : shouldObscure ? this.mContext.getString(R.string.spoken_auto_correct_obscured, keyCodeDescription) : this.mContext.getString(R.string.spoken_auto_correct, keyCodeDescription, this.mTypedWord, this.mAutoCorrectionWord);
    }

    public void announceForAccessibility(final View view, final CharSequence text) {
        if (!this.mAccessibilityManager.isEnabled()) {
            Log.e(TAG, "Attempted to speak when accessibility was disabled!");
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setPackageName(PACKAGE);
        accessibilityEventObtain.setClassName(CLASS);
        accessibilityEventObtain.setEventTime(SystemClock.uptimeMillis());
        accessibilityEventObtain.setEnabled(true);
        accessibilityEventObtain.getText().add(text);
        if (Build.VERSION.SDK_INT >= 16) {
            accessibilityEventObtain.setEventType(16384);
        } else {
            accessibilityEventObtain.setEventType(8);
        }
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            Log.e(TAG, "Failed to obtain ViewParent in announceForAccessibility");
        } else {
            parent.requestSendAccessibilityEvent(view, accessibilityEventObtain);
        }
    }

    public void onStartInputViewInternal(final View view, final EditorInfo editorInfo, final boolean restarting) {
        if (shouldObscureInput(editorInfo)) {
            announceForAccessibility(view, this.mContext.getText(R.string.spoken_use_headphones));
        }
    }

    public void requestSendAccessibilityEvent(final AccessibilityEvent event) {
        if (this.mAccessibilityManager.isEnabled()) {
            this.mAccessibilityManager.sendAccessibilityEvent(event);
        }
    }
}
