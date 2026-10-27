package org.chromium.content.browser.input;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ThreadedInputConnection extends BaseInputConnection implements ChromiumBaseInputConnection {
    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_Ime";
    private static final TextInputState UNBLOCKER = new TextInputState("", new Range(0, 0), new Range(-1, -1), false, false) { // from class: org.chromium.content.browser.input.ThreadedInputConnection.1
        @Override // org.chromium.content.browser.input.TextInputState
        public boolean shouldUnblock() {
            return true;
        }
    };
    private final Runnable mFinishComposingTextRunnable;
    private final Handler mHandler;
    private final ImeAdapter mImeAdapter;
    private final Runnable mMoveCursorSelectionEndRunnable;
    private final Runnable mNotifyUserActionRunnable;
    private int mNumNestedBatchEdits;
    private int mPendingAccent;
    private final Runnable mProcessPendingInputStatesRunnable;
    private final BlockingQueue<TextInputState> mQueue;
    private final Runnable mRequestTextInputStateUpdate;

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public void closeConnection() {
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        return false;
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public void onRestartInputOnUiThread() {
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean reportFullscreenMode(boolean z) {
        return false;
    }

    ThreadedInputConnection(View view, ImeAdapter imeAdapter, Handler handler) {
        super(view, true);
        this.mProcessPendingInputStatesRunnable = new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.2
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.processPendingInputStates();
            }
        };
        this.mMoveCursorSelectionEndRunnable = new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.3
            @Override // java.lang.Runnable
            public void run() {
                TextInputState textInputStateRequestAndWaitForTextInputState = ThreadedInputConnection.this.requestAndWaitForTextInputState();
                if (textInputStateRequestAndWaitForTextInputState == null) {
                    return;
                }
                Range rangeSelection = textInputStateRequestAndWaitForTextInputState.selection();
                ThreadedInputConnection.this.setSelection(rangeSelection.end(), rangeSelection.end());
            }
        };
        this.mRequestTextInputStateUpdate = new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.4
            @Override // java.lang.Runnable
            public void run() {
                if (ThreadedInputConnection.this.mImeAdapter.requestTextInputStateUpdate()) {
                    return;
                }
                ThreadedInputConnection.this.unblockOnUiThread();
            }
        };
        this.mNotifyUserActionRunnable = new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.5
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.notifyUserAction();
            }
        };
        this.mFinishComposingTextRunnable = new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.6
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.finishComposingText();
            }
        };
        this.mQueue = new LinkedBlockingQueue();
        ImeUtils.checkOnUiThread();
        this.mImeAdapter = imeAdapter;
        this.mHandler = handler;
    }

    void resetOnUiThread() {
        ImeUtils.checkOnUiThread();
        this.mNumNestedBatchEdits = 0;
        this.mPendingAccent = 0;
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public void updateStateOnUiThread(String str, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        ImeUtils.checkOnUiThread();
        addToQueueOnUiThread(new TextInputState(str, new Range(i, i2), new Range(i3, i4), z, !z2));
        if (z2) {
            this.mHandler.post(this.mProcessPendingInputStatesRunnable);
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection, org.chromium.content.browser.input.ChromiumBaseInputConnection
    public Handler getHandler() {
        return this.mHandler;
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public boolean sendKeyEventOnUiThread(final KeyEvent keyEvent) {
        ImeUtils.checkOnUiThread();
        this.mHandler.post(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.7
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.sendKeyEvent(keyEvent);
            }
        });
        return true;
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public void moveCursorToSelectionEndOnUiThread() {
        this.mHandler.post(this.mMoveCursorSelectionEndRunnable);
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection
    public void unblockOnUiThread() {
        ImeUtils.checkOnUiThread();
        addToQueueOnUiThread(UNBLOCKER);
        this.mHandler.post(this.mProcessPendingInputStatesRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPendingInputStates() {
        assertOnImeThread();
        while (true) {
            TextInputState textInputStatePoll = this.mQueue.poll();
            if (textInputStatePoll == null) {
                return;
            }
            if (!textInputStatePoll.shouldUnblock()) {
                updateSelection(textInputStatePoll);
            }
        }
    }

    private void updateSelection(TextInputState textInputState) {
        if (textInputState == null) {
            return;
        }
        assertOnImeThread();
        if (this.mNumNestedBatchEdits != 0) {
            return;
        }
        Range rangeSelection = textInputState.selection();
        Range rangeComposition = textInputState.composition();
        this.mImeAdapter.updateSelection(rangeSelection.start(), rangeSelection.end(), rangeComposition.start(), rangeComposition.end());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TextInputState requestAndWaitForTextInputState() {
        ThreadUtils.postOnUiThread(this.mRequestTextInputStateUpdate);
        return blockAndGetStateUpdate();
    }

    private void addToQueueOnUiThread(TextInputState textInputState) {
        ImeUtils.checkOnUiThread();
        try {
            this.mQueue.put(textInputState);
        } catch (InterruptedException e) {
            Log.e(TAG, "addToQueueOnUiThread interrupted", e);
        }
    }

    BlockingQueue<TextInputState> getQueueForTest() {
        return this.mQueue;
    }

    private void assertOnImeThread() {
        ImeUtils.checkCondition(this.mHandler.getLooper() == Looper.myLooper());
    }

    private TextInputState blockAndGetStateUpdate() {
        assertOnImeThread();
        boolean z = false;
        while (true) {
            try {
                TextInputState textInputStateTake = this.mQueue.take();
                if (textInputStateTake.shouldUnblock()) {
                    return null;
                }
                if (textInputStateTake.fromIme()) {
                    if (z) {
                        updateSelection(textInputStateTake);
                    }
                    return textInputStateTake;
                }
                z = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                ImeUtils.checkCondition(false);
                return null;
            }
        }
    }

    private void notifyUserAction() {
        ThreadUtils.postOnUiThread(this.mNotifyUserActionRunnable);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i) {
        return updateComposingText(charSequence, i, false);
    }

    public boolean updateComposingText(final CharSequence charSequence, final int i, boolean z) {
        final int i2 = z ? this.mPendingAccent | Integer.MIN_VALUE : 0;
        assertOnImeThread();
        cancelCombiningAccent();
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.8
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.sendCompositionToNative(charSequence, i, false, i2);
            }
        });
        notifyUserAction();
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitText(final CharSequence charSequence, final int i) {
        assertOnImeThread();
        cancelCombiningAccent();
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.9
            @Override // java.lang.Runnable
            public void run() {
                ImeAdapter imeAdapter = ThreadedInputConnection.this.mImeAdapter;
                CharSequence charSequence2 = charSequence;
                imeAdapter.sendCompositionToNative(charSequence2, i, charSequence2.length() > 0, 0);
            }
        });
        notifyUserAction();
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performEditorAction(final int i) {
        assertOnImeThread();
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.10
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.performEditorAction(i);
            }
        });
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(final int i) {
        assertOnImeThread();
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.11
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.performContextMenuAction(i);
            }
        });
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        assertOnImeThread();
        TextInputState textInputStateRequestAndWaitForTextInputState = requestAndWaitForTextInputState();
        if (textInputStateRequestAndWaitForTextInputState == null) {
            return null;
        }
        ExtractedText extractedText = new ExtractedText();
        extractedText.text = textInputStateRequestAndWaitForTextInputState.text();
        extractedText.partialEndOffset = textInputStateRequestAndWaitForTextInputState.text().length();
        extractedText.selectionStart = textInputStateRequestAndWaitForTextInputState.selection().start();
        extractedText.selectionEnd = textInputStateRequestAndWaitForTextInputState.selection().end();
        extractedText.flags = textInputStateRequestAndWaitForTextInputState.singleLine() ? 1 : 0;
        return extractedText;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        assertOnImeThread();
        this.mNumNestedBatchEdits++;
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        assertOnImeThread();
        int i = this.mNumNestedBatchEdits;
        if (i == 0) {
            return false;
        }
        int i2 = i - 1;
        this.mNumNestedBatchEdits = i2;
        if (i2 == 0) {
            updateSelection(requestAndWaitForTextInputState());
        }
        return this.mNumNestedBatchEdits != 0;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(final int i, final int i2) {
        assertOnImeThread();
        if (this.mPendingAccent != 0) {
            finishComposingText();
        }
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.12
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.deleteSurroundingText(i, i2);
            }
        });
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(final KeyEvent keyEvent) {
        assertOnImeThread();
        if (handleCombiningAccent(keyEvent)) {
            return true;
        }
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.13
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.sendKeyEvent(keyEvent);
            }
        });
        notifyUserAction();
        return true;
    }

    private boolean handleCombiningAccent(KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        int unicodeChar = keyEvent.getUnicodeChar();
        if (action != 0) {
            return false;
        }
        if ((Integer.MIN_VALUE & unicodeChar) != 0) {
            int i = unicodeChar & Integer.MAX_VALUE;
            StringBuilder sb = new StringBuilder();
            sb.appendCodePoint(i);
            updateComposingText(sb.toString(), 1, true);
            setCombiningAccent(i);
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
        return false;
    }

    public void setCombiningAccent(int i) {
        this.mPendingAccent = i;
    }

    private void cancelCombiningAccent() {
        this.mPendingAccent = 0;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        cancelCombiningAccent();
        ThreadUtils.postOnUiThread(this.mFinishComposingTextRunnable);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setSelection(final int i, final int i2) {
        assertOnImeThread();
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.14
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.setEditableSelectionOffsets(i, i2);
            }
        });
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingRegion(final int i, final int i2) {
        assertOnImeThread();
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.15
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.setComposingRegion(i, i2);
            }
        });
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int i, int i2) {
        assertOnImeThread();
        TextInputState textInputStateRequestAndWaitForTextInputState = requestAndWaitForTextInputState();
        if (textInputStateRequestAndWaitForTextInputState == null) {
            return null;
        }
        return textInputStateRequestAndWaitForTextInputState.getTextBeforeSelection(i);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public CharSequence getTextAfterCursor(int i, int i2) {
        assertOnImeThread();
        TextInputState textInputStateRequestAndWaitForTextInputState = requestAndWaitForTextInputState();
        if (textInputStateRequestAndWaitForTextInputState == null) {
            return null;
        }
        return textInputStateRequestAndWaitForTextInputState.getTextAfterSelection(i);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public CharSequence getSelectedText(int i) {
        assertOnImeThread();
        TextInputState textInputStateRequestAndWaitForTextInputState = requestAndWaitForTextInputState();
        if (textInputStateRequestAndWaitForTextInputState == null) {
            return null;
        }
        return textInputStateRequestAndWaitForTextInputState.getSelectedText();
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public int getCursorCapsMode(int i) {
        assertOnImeThread();
        return 0;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitCompletion(CompletionInfo completionInfo) {
        assertOnImeThread();
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        assertOnImeThread();
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int i) {
        assertOnImeThread();
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String str, Bundle bundle) {
        assertOnImeThread();
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(final int i) {
        assertOnImeThread();
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnection.16
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.onRequestCursorUpdates(i);
            }
        });
        return true;
    }
}
