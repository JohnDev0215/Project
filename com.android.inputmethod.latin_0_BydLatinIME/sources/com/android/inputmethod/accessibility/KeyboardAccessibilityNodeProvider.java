package com.android.inputmethod.accessibility;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.KeyboardView;
import com.android.inputmethod.latin.common.CoordinateUtils;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.settings.SettingsValues;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class KeyboardAccessibilityNodeProvider<KV extends KeyboardView> extends AccessibilityNodeProviderCompat {
    private static final String TAG = "KeyboardAccessibilityNodeProvider";
    private static final int UNDEFINED = Integer.MAX_VALUE;
    private final KeyboardAccessibilityDelegate<KV> mDelegate;
    private Keyboard mKeyboard;
    private final KV mKeyboardView;
    private final Rect mTempBoundsInScreen = new Rect();
    private final int[] mParentLocation = CoordinateUtils.newInstance();
    private int mAccessibilityFocusedView = Integer.MAX_VALUE;
    private int mHoveringNodeId = Integer.MAX_VALUE;
    private final KeyCodeDescriptionMapper mKeyCodeDescriptionMapper = KeyCodeDescriptionMapper.getInstance();
    private final AccessibilityUtils mAccessibilityUtils = AccessibilityUtils.getInstance();

    public KeyboardAccessibilityNodeProvider(final KV keyboardView, final KeyboardAccessibilityDelegate<KV> delegate) {
        this.mKeyboardView = keyboardView;
        this.mDelegate = delegate;
        setKeyboard(keyboardView.getKeyboard());
    }

    public void setKeyboard(final Keyboard keyboard) {
        this.mKeyboard = keyboard;
    }

    private Key getKeyOf(final int virtualViewId) {
        Keyboard keyboard = this.mKeyboard;
        if (keyboard == null) {
            return null;
        }
        List<Key> sortedKeys = keyboard.getSortedKeys();
        if (virtualViewId < 0 || virtualViewId >= sortedKeys.size()) {
            return null;
        }
        return sortedKeys.get(virtualViewId);
    }

    private int getVirtualViewIdOf(final Key key) {
        Keyboard keyboard = this.mKeyboard;
        if (keyboard == null) {
            return -1;
        }
        List<Key> sortedKeys = keyboard.getSortedKeys();
        int size = sortedKeys.size();
        for (int i = 0; i < size; i++) {
            if (sortedKeys.get(i) == key) {
                return i;
            }
        }
        return -1;
    }

    public AccessibilityEvent createAccessibilityEvent(final Key key, final int eventType) {
        int virtualViewIdOf = getVirtualViewIdOf(key);
        String keyDescription = getKeyDescription(key);
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(eventType);
        accessibilityEventObtain.setPackageName(this.mKeyboardView.getContext().getPackageName());
        accessibilityEventObtain.setClassName(key.getClass().getName());
        accessibilityEventObtain.setContentDescription(keyDescription);
        accessibilityEventObtain.setEnabled(true);
        AccessibilityEventCompat.asRecord(accessibilityEventObtain).setSource(this.mKeyboardView, virtualViewIdOf);
        return accessibilityEventObtain;
    }

    public void onHoverEnterTo(final Key key) {
        int virtualViewIdOf = getVirtualViewIdOf(key);
        if (virtualViewIdOf == -1) {
            return;
        }
        this.mHoveringNodeId = virtualViewIdOf;
        sendAccessibilityEventForKey(key, 2048);
        sendAccessibilityEventForKey(key, 128);
    }

    public void onHoverExitFrom(final Key key) {
        this.mHoveringNodeId = Integer.MAX_VALUE;
        sendAccessibilityEventForKey(key, 2048);
        sendAccessibilityEventForKey(key, 256);
    }

    @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(final int virtualViewId) {
        if (virtualViewId == Integer.MAX_VALUE) {
            return null;
        }
        if (virtualViewId == -1) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain(this.mKeyboardView);
            ViewCompat.onInitializeAccessibilityNodeInfo(this.mKeyboardView, accessibilityNodeInfoCompatObtain);
            updateParentLocation();
            List<Key> sortedKeys = this.mKeyboard.getSortedKeys();
            int size = sortedKeys.size();
            for (int i = 0; i < size; i++) {
                if (!sortedKeys.get(i).isSpacer()) {
                    accessibilityNodeInfoCompatObtain.addChild(this.mKeyboardView, i);
                }
            }
            return accessibilityNodeInfoCompatObtain;
        }
        Key keyOf = getKeyOf(virtualViewId);
        if (keyOf == null) {
            Log.e(TAG, "Invalid virtual view ID: " + virtualViewId);
            return null;
        }
        String keyDescription = getKeyDescription(keyOf);
        Rect hitBox = keyOf.getHitBox();
        this.mTempBoundsInScreen.set(hitBox);
        this.mTempBoundsInScreen.offset(CoordinateUtils.x(this.mParentLocation), CoordinateUtils.y(this.mParentLocation));
        Rect rect = this.mTempBoundsInScreen;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain2 = AccessibilityNodeInfoCompat.obtain();
        accessibilityNodeInfoCompatObtain2.setPackageName(this.mKeyboardView.getContext().getPackageName());
        accessibilityNodeInfoCompatObtain2.setClassName(keyOf.getClass().getName());
        accessibilityNodeInfoCompatObtain2.setContentDescription(keyDescription);
        accessibilityNodeInfoCompatObtain2.setBoundsInParent(hitBox);
        accessibilityNodeInfoCompatObtain2.setBoundsInScreen(rect);
        accessibilityNodeInfoCompatObtain2.setParent(this.mKeyboardView);
        accessibilityNodeInfoCompatObtain2.setSource(this.mKeyboardView, virtualViewId);
        accessibilityNodeInfoCompatObtain2.setEnabled(keyOf.isEnabled());
        accessibilityNodeInfoCompatObtain2.setVisibleToUser(true);
        accessibilityNodeInfoCompatObtain2.addAction(16);
        if (keyOf.isLongPressEnabled()) {
            accessibilityNodeInfoCompatObtain2.addAction(32);
        }
        if (this.mAccessibilityFocusedView == virtualViewId) {
            accessibilityNodeInfoCompatObtain2.addAction(128);
        } else {
            accessibilityNodeInfoCompatObtain2.addAction(64);
        }
        return accessibilityNodeInfoCompatObtain2;
    }

    @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
    public boolean performAction(final int virtualViewId, final int action, final Bundle arguments) {
        Key keyOf = getKeyOf(virtualViewId);
        if (keyOf == null) {
            return false;
        }
        return performActionForKey(keyOf, action);
    }

    boolean performActionForKey(final Key key, final int action) {
        if (action == 16) {
            sendAccessibilityEventForKey(key, 1);
            this.mDelegate.performClickOn(key);
            return true;
        }
        if (action == 32) {
            sendAccessibilityEventForKey(key, 2);
            this.mDelegate.performLongClickOn(key);
            return true;
        }
        if (action == 64) {
            this.mAccessibilityFocusedView = getVirtualViewIdOf(key);
            sendAccessibilityEventForKey(key, 32768);
            return true;
        }
        if (action != 128) {
            return false;
        }
        this.mAccessibilityFocusedView = Integer.MAX_VALUE;
        sendAccessibilityEventForKey(key, 65536);
        return true;
    }

    void sendAccessibilityEventForKey(final Key key, final int eventType) {
        this.mAccessibilityUtils.requestSendAccessibilityEvent(createAccessibilityEvent(key, eventType));
    }

    private String getKeyDescription(final Key key) {
        boolean zShouldObscureInput = this.mAccessibilityUtils.shouldObscureInput(this.mKeyboard.mId.mEditorInfo);
        SettingsValues current = Settings.getInstance().getCurrent();
        String descriptionForKey = this.mKeyCodeDescriptionMapper.getDescriptionForKey(this.mKeyboardView.getContext(), this.mKeyboard, key, zShouldObscureInput);
        return current.isWordSeparator(key.getCode()) ? this.mAccessibilityUtils.getAutoCorrectionDescription(descriptionForKey, zShouldObscureInput) : descriptionForKey;
    }

    private void updateParentLocation() {
        this.mKeyboardView.getLocationOnScreen(this.mParentLocation);
    }
}
