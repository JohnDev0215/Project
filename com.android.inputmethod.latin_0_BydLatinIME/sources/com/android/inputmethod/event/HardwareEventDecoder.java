package com.android.inputmethod.event;

import android.view.KeyEvent;

/* JADX INFO: loaded from: classes2.dex */
public interface HardwareEventDecoder extends EventDecoder {
    Event decodeHardwareKey(final KeyEvent keyEvent);
}
