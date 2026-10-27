package com.android.inputmethod.event;

import android.view.KeyEvent;

/* JADX INFO: loaded from: classes2.dex */
public class HardwareKeyboardEventDecoder implements HardwareEventDecoder {
    final int mDeviceId;

    public HardwareKeyboardEventDecoder(final int deviceId) {
        this.mDeviceId = deviceId;
    }

    @Override // com.android.inputmethod.event.HardwareEventDecoder
    public Event decodeHardwareKey(final KeyEvent keyEvent) {
        int unicodeChar = keyEvent.getUnicodeChar();
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyEvent.getRepeatCount() != 0;
        if (67 == keyCode) {
            return Event.createHardwareKeypressEvent(-1, -5, null, z);
        }
        if (!keyEvent.isPrintingKey() && 62 != keyCode && 66 != keyCode) {
            return Event.createNotHandledEvent();
        }
        if ((Integer.MIN_VALUE & unicodeChar) != 0) {
            return Event.createDeadEvent(Integer.MAX_VALUE & unicodeChar, keyCode, null);
        }
        if (66 == keyCode) {
            if (keyEvent.isShiftPressed()) {
                return Event.createHardwareKeypressEvent(-1, -12, null, z);
            }
            return Event.createHardwareKeypressEvent(10, keyCode, null, z);
        }
        return Event.createHardwareKeypressEvent(unicodeChar, keyCode, null, z);
    }
}
