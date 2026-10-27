package com.android.inputmethod.accessibility;

import android.content.Context;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import com.android.inputmethod.accessibility.AccessibilityLongPressTimer;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyDetector;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.MainKeyboardView;
import com.android.inputmethod.keyboard.PointerTracker;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class MainKeyboardAccessibilityDelegate extends KeyboardAccessibilityDelegate<MainKeyboardView> implements AccessibilityLongPressTimer.LongPressTimerCallback {
    private static final int KEYBOARD_IS_HIDDEN = -1;
    private static final SparseIntArray KEYBOARD_MODE_RES_IDS;
    private static final String TAG = "MainKeyboardAccessibilityDelegate";
    private final Rect mBoundsToIgnoreHoverEvent;
    private int mLastKeyboardMode;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        KEYBOARD_MODE_RES_IDS = sparseIntArray;
        sparseIntArray.put(6, R.string.keyboard_mode_date);
        sparseIntArray.put(8, R.string.keyboard_mode_date_time);
        sparseIntArray.put(2, R.string.keyboard_mode_email);
        sparseIntArray.put(3, R.string.keyboard_mode_im);
        sparseIntArray.put(5, R.string.keyboard_mode_number);
        sparseIntArray.put(4, R.string.keyboard_mode_phone);
        sparseIntArray.put(0, R.string.keyboard_mode_text);
        sparseIntArray.put(7, R.string.keyboard_mode_time);
        sparseIntArray.put(1, R.string.keyboard_mode_url);
    }

    public MainKeyboardAccessibilityDelegate(final MainKeyboardView mainKeyboardView, final KeyDetector keyDetector) {
        super(mainKeyboardView, keyDetector);
        this.mLastKeyboardMode = -1;
        this.mBoundsToIgnoreHoverEvent = new Rect();
    }

    @Override // com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate
    public void setKeyboard(final Keyboard keyboard) {
        if (keyboard == null) {
            return;
        }
        Keyboard keyboard2 = getKeyboard();
        super.setKeyboard(keyboard);
        int i = this.mLastKeyboardMode;
        this.mLastKeyboardMode = keyboard.mId.mMode;
        if (AccessibilityUtils.getInstance().isAccessibilityEnabled()) {
            if (keyboard2 == null || !keyboard.mId.mSubtype.equals(keyboard2.mId.mSubtype)) {
                announceKeyboardLanguage(keyboard);
            } else if (keyboard.mId.mMode != i) {
                announceKeyboardMode(keyboard);
            } else if (keyboard.mId.mElementId != keyboard2.mId.mElementId) {
                announceKeyboardType(keyboard, keyboard2);
            }
        }
    }

    public void onHideWindow() {
        if (this.mLastKeyboardMode != -1) {
            announceKeyboardHidden();
        }
        this.mLastKeyboardMode = -1;
    }

    private void announceKeyboardLanguage(final Keyboard keyboard) {
        sendWindowStateChanged(SubtypeLocaleUtils.getSubtypeDisplayNameInSystemLocale(keyboard.mId.mSubtype.getRawSubtype()));
    }

    private void announceKeyboardMode(final Keyboard keyboard) {
        Context context = ((MainKeyboardView) this.mKeyboardView).getContext();
        int i = KEYBOARD_MODE_RES_IDS.get(keyboard.mId.mMode);
        if (i == 0) {
            return;
        }
        sendWindowStateChanged(context.getString(R.string.announce_keyboard_mode, context.getString(i)));
    }

    private void announceKeyboardType(final Keyboard keyboard, final Keyboard lastKeyboard) {
        int i = lastKeyboard.mId.mElementId;
        int i2 = keyboard.mId.mElementId;
        int i3 = R.string.spoken_description_shiftmode_locked;
        switch (i2) {
            case 0:
            case 2:
                if (i == 0 || i == 2) {
                    return;
                } else {
                    i3 = R.string.spoken_description_mode_alpha;
                }
                break;
            case 1:
                if (i == 2) {
                    return;
                } else {
                    i3 = R.string.spoken_description_shiftmode_on;
                }
                break;
            case 3:
                break;
            case 4:
                if (i == 3) {
                    return;
                }
                break;
            case 5:
                i3 = R.string.spoken_description_mode_symbol;
                break;
            case 6:
                i3 = R.string.spoken_description_mode_symbol_shift;
                break;
            case 7:
                i3 = R.string.spoken_description_mode_phone;
                break;
            case 8:
                i3 = R.string.spoken_description_mode_phone_shift;
                break;
            default:
                return;
        }
        sendWindowStateChanged(i3);
    }

    private void announceKeyboardHidden() {
        sendWindowStateChanged(R.string.announce_keyboard_hidden);
    }

    @Override // com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate
    public void performClickOn(final Key key) {
        if (this.mBoundsToIgnoreHoverEvent.contains(key.getHitBox().centerX(), key.getHitBox().centerY())) {
            this.mBoundsToIgnoreHoverEvent.setEmpty();
        } else {
            super.performClickOn(key);
        }
    }

    @Override // com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate
    protected void onHoverEnterTo(final Key key) {
        if (this.mBoundsToIgnoreHoverEvent.contains(key.getHitBox().centerX(), key.getHitBox().centerY())) {
            return;
        }
        this.mBoundsToIgnoreHoverEvent.setEmpty();
        super.onHoverEnterTo(key);
    }

    @Override // com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate
    protected void onHoverExitFrom(final Key key) {
        key.getHitBox().centerX();
        key.getHitBox().centerY();
        super.onHoverExitFrom(key);
    }

    @Override // com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate, com.android.inputmethod.accessibility.AccessibilityLongPressTimer.LongPressTimerCallback
    public void performLongClickOn(final Key key) {
        PointerTracker pointerTracker = PointerTracker.getPointerTracker(0);
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, key.getHitBox().centerX(), key.getHitBox().centerY(), 0);
        pointerTracker.processMotionEvent(motionEventObtain, this.mKeyDetector);
        motionEventObtain.recycle();
        pointerTracker.onLongPressed();
        if (pointerTracker.isInOperation()) {
            this.mBoundsToIgnoreHoverEvent.setEmpty();
            return;
        }
        this.mBoundsToIgnoreHoverEvent.set(key.getHitBox());
        if (key.hasNoPanelAutoMoreKey()) {
            String descriptionForCodePoint = KeyCodeDescriptionMapper.getInstance().getDescriptionForCodePoint(((MainKeyboardView) this.mKeyboardView).getContext(), key.getMoreKeys()[0].mCode);
            if (descriptionForCodePoint != null) {
                sendWindowStateChanged(descriptionForCodePoint);
            }
        }
    }
}
