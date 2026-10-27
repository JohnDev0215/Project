package org.chromium.content.browser.input;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import kotlin.text.Typography;
import org.chromium.content.browser.input.ChromiumBaseInputConnection;

/* JADX INFO: loaded from: classes3.dex */
public class ReplicaInputConnection extends BaseInputConnection implements ChromiumBaseInputConnection {
    private static final boolean DEBUG_LOGS = false;
    public static final int INVALID_COMPOSITION = -1;
    public static final int INVALID_SELECTION = -1;
    private static final String TAG = "cr_Ime";
    private final Editable mEditable;
    private final Handler mHandler;
    private final ImeAdapter mImeAdapter;
    private int mNumNestedBatchEdits;
    private int mPendingAccent;
    private boolean mSingleLine;

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public void unblockOnUiThread() {
    }

    static class Factory implements ChromiumBaseInputConnection.Factory {
        private final Editable mEditable;
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
        public void onViewAttachedToWindow() {
        }

        @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
        public void onViewDetachedFromWindow() {
        }

        @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
        public void onViewFocusChanged(boolean z) {
        }

        @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
        public void onWindowFocusChanged(boolean z) {
        }

        Factory() {
            Editable editableNewEditable = Editable.Factory.getInstance().newEditable("");
            this.mEditable = editableNewEditable;
            Selection.setSelection(editableNewEditable, 0);
        }

        @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
        public ReplicaInputConnection initializeAndGet(View view, ImeAdapter imeAdapter, int i, int i2, int i3, int i4, EditorInfo editorInfo) {
            new InputMethodUma().recordProxyViewReplicaInputConnection();
            return new ReplicaInputConnection(view, imeAdapter, this.mHandler, this.mEditable, i, i2, editorInfo);
        }

        @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
        public Handler getHandler() {
            return this.mHandler;
        }
    }

    ReplicaInputConnection(View view, ImeAdapter imeAdapter, Handler handler, Editable editable, int i, int i2, EditorInfo editorInfo) {
        super(view, true);
        this.mNumNestedBatchEdits = 0;
        this.mImeAdapter = imeAdapter;
        this.mEditable = editable;
        this.mHandler = handler;
        ImeUtils.computeEditorInfo(i, i2, Selection.getSelectionStart(editable), Selection.getSelectionEnd(editable), editorInfo);
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public void updateStateOnUiThread(String str, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        this.mSingleLine = z;
        if (z2) {
            String strReplace = str.replace(Typography.nbsp, ' ');
            int iMin = Math.min(i, strReplace.length());
            int iMin2 = Math.min(i2, strReplace.length());
            int iMin3 = Math.min(i3, strReplace.length());
            int iMin4 = Math.min(i4, strReplace.length());
            if (!this.mEditable.toString().equals(strReplace)) {
                Editable editable = this.mEditable;
                editable.replace(0, editable.length(), strReplace);
            }
            Selection.setSelection(this.mEditable, iMin, iMin2);
            if (iMin3 == iMin4) {
                removeComposingSpans(this.mEditable);
            } else {
                super.setComposingRegion(iMin3, iMin4);
            }
            updateSelectionIfRequired();
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection
    public Editable getEditable() {
        return this.mEditable;
    }

    private void updateSelectionIfRequired() {
        if (this.mNumNestedBatchEdits != 0) {
            return;
        }
        this.mImeAdapter.updateSelection(Selection.getSelectionStart(this.mEditable), Selection.getSelectionEnd(this.mEditable), getComposingSpanStart(this.mEditable), getComposingSpanEnd(this.mEditable));
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i) {
        return updateComposingText(charSequence, i, false);
    }

    private boolean updateComposingText(CharSequence charSequence, int i, boolean z) {
        int i2 = z ? this.mPendingAccent : 0;
        this.mPendingAccent = 0;
        super.setComposingText(charSequence, i);
        updateSelectionIfRequired();
        return this.mImeAdapter.sendCompositionToNative(charSequence, i, false, i2);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i) {
        this.mPendingAccent = 0;
        super.commitText(charSequence, i);
        updateSelectionIfRequired();
        return this.mImeAdapter.sendCompositionToNative(charSequence, i, charSequence.length() > 0, 0);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performEditorAction(int i) {
        return this.mImeAdapter.performEditorAction(i);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int i) {
        return this.mImeAdapter.performContextMenuAction(i);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        ExtractedText extractedText = new ExtractedText();
        extractedText.text = this.mEditable.toString();
        extractedText.partialEndOffset = this.mEditable.length();
        extractedText.selectionStart = Selection.getSelectionStart(this.mEditable);
        extractedText.selectionEnd = Selection.getSelectionEnd(this.mEditable);
        extractedText.flags = this.mSingleLine ? 1 : 0;
        return extractedText;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        this.mNumNestedBatchEdits++;
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        int i = this.mNumNestedBatchEdits;
        if (i == 0) {
            return false;
        }
        int i2 = i - 1;
        this.mNumNestedBatchEdits = i2;
        if (i2 == 0) {
            updateSelectionIfRequired();
        }
        return this.mNumNestedBatchEdits != 0;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int i, int i2) {
        return deleteSurroundingTextImpl(i, i2, false);
    }

    static boolean isIndexBetweenUtf16SurrogatePair(CharSequence charSequence, int i) {
        return i > 0 && i < charSequence.length() && Character.isHighSurrogate(charSequence.charAt(i + (-1))) && Character.isLowSurrogate(charSequence.charAt(i));
    }

    private boolean deleteSurroundingTextImpl(int i, int i2, boolean z) {
        if (this.mPendingAccent != 0) {
            finishComposingText();
        }
        int selectionStart = Selection.getSelectionStart(this.mEditable);
        int selectionEnd = Selection.getSelectionEnd(this.mEditable);
        int length = this.mEditable.length() - selectionEnd;
        int iMin = Math.min(i, selectionStart);
        int iMin2 = Math.min(i2, length);
        if (isIndexBetweenUtf16SurrogatePair(this.mEditable, selectionStart - iMin)) {
            iMin++;
        }
        if (isIndexBetweenUtf16SurrogatePair(this.mEditable, selectionEnd + iMin2)) {
            iMin2++;
        }
        super.deleteSurroundingText(iMin, iMin2);
        updateSelectionIfRequired();
        if (z) {
            return true;
        }
        return this.mImeAdapter.deleteSurroundingText(iMin, iMin2);
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public boolean sendKeyEventOnUiThread(KeyEvent keyEvent) {
        return sendKeyEvent(keyEvent);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        int keyCode = keyEvent.getKeyCode();
        int unicodeChar = keyEvent.getUnicodeChar();
        if (action != 0) {
            this.mImeAdapter.sendKeyEvent(keyEvent);
            return true;
        }
        if (keyCode == 67) {
            deleteSurroundingTextImpl(1, 0, true);
        } else if (keyCode == 112) {
            deleteSurroundingTextImpl(0, 1, true);
        } else if (keyCode == 66) {
            finishComposingText();
        } else {
            if ((Integer.MIN_VALUE & unicodeChar) != 0) {
                int i = Integer.MAX_VALUE & unicodeChar;
                StringBuilder sb = new StringBuilder();
                sb.appendCodePoint(i);
                updateComposingText(sb.toString(), 1, true);
                this.mPendingAccent = i;
                return true;
            }
            int i2 = this.mPendingAccent;
            if (i2 != 0 && unicodeChar != 0) {
                int deadChar = KeyEvent.getDeadChar(i2, unicodeChar);
                if (deadChar != 0) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.appendCodePoint(deadChar);
                    commitText(sb2.toString(), 1);
                    return true;
                }
                finishComposingText();
            }
        }
        replaceSelectionWithUnicodeChar(unicodeChar);
        this.mImeAdapter.sendKeyEvent(keyEvent);
        return true;
    }

    private void replaceSelectionWithUnicodeChar(int i) {
        if (i == 0) {
            return;
        }
        int selectionStart = Selection.getSelectionStart(this.mEditable);
        int selectionEnd = Selection.getSelectionEnd(this.mEditable);
        if (selectionStart > selectionEnd) {
            selectionEnd = selectionStart;
            selectionStart = selectionEnd;
        }
        this.mEditable.replace(selectionStart, selectionEnd, Character.toString((char) i));
        updateSelectionIfRequired();
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        this.mPendingAccent = 0;
        if (getComposingSpanStart(this.mEditable) == getComposingSpanEnd(this.mEditable)) {
            return true;
        }
        super.finishComposingText();
        updateSelectionIfRequired();
        this.mImeAdapter.finishComposingText();
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setSelection(int i, int i2) {
        int length = this.mEditable.length();
        if (i < 0 || i2 < 0 || i > length || i2 > length) {
            return true;
        }
        super.setSelection(i, i2);
        updateSelectionIfRequired();
        return this.mImeAdapter.setEditableSelectionOffsets(i, i2);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int i, int i2) {
        int length = this.mEditable.length();
        int iMin = Math.min(i, i2);
        int iMax = Math.max(i, i2);
        if (iMin < 0) {
            iMin = 0;
        }
        if (iMax < 0) {
            iMax = 0;
        }
        if (iMin > length) {
            iMin = length;
        }
        if (iMax <= length) {
            length = iMax;
        }
        if (iMin == length) {
            removeComposingSpans(this.mEditable);
        } else {
            super.setComposingRegion(iMin, length);
        }
        updateSelectionIfRequired();
        return this.mImeAdapter.setComposingRegion(iMin, length);
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public void onRestartInputOnUiThread() {
        this.mNumNestedBatchEdits = 0;
        this.mPendingAccent = 0;
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public void moveCursorToSelectionEndOnUiThread() {
        int selectionEnd = Selection.getSelectionEnd(this.mEditable);
        setSelection(selectionEnd, selectionEnd);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection, org.chromium.content.browser.input.ChromiumBaseInputConnection
    public Handler getHandler() {
        return this.mHandler;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int i) {
        return this.mImeAdapter.onRequestCursorUpdates(i);
    }

    static class ImeState {
        public final int compositionEnd;
        public final int compositionStart;
        public final int selectionEnd;
        public final int selectionStart;
        public final String text;

        public ImeState(String str, int i, int i2, int i3, int i4) {
            this.text = str;
            this.selectionStart = i;
            this.selectionEnd = i2;
            this.compositionStart = i3;
            this.compositionEnd = i4;
        }
    }

    ImeState getImeStateForTesting() {
        return new ImeState(this.mEditable.toString(), Selection.getSelectionStart(this.mEditable), Selection.getSelectionEnd(this.mEditable), getComposingSpanStart(this.mEditable), getComposingSpanEnd(this.mEditable));
    }
}
