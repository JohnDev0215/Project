package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class NativePageRootFrameLayout extends FrameLayout {
    public NativePageRootFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.imeOptions = 33554432;
        return super.onCreateInputConnection(editorInfo);
    }
}
