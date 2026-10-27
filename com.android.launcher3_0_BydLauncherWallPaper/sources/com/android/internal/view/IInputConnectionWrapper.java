package com.android.internal.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import com.android.internal.inputmethod.ICharSequenceResultCallback;
import com.android.internal.inputmethod.IExtractedTextResultCallback;
import com.android.internal.inputmethod.IIntResultCallback;
import com.android.internal.os.SomeArgs;
import com.android.internal.view.IInputContext;

/* JADX INFO: loaded from: classes4.dex */
public abstract class IInputConnectionWrapper extends IInputContext.Stub {
    private static final boolean DEBUG = false;
    private static final int DO_BEGIN_BATCH_EDIT = 90;
    private static final int DO_CLEAR_META_KEY_STATES = 130;
    private static final int DO_CLOSE_CONNECTION = 150;
    private static final int DO_COMMIT_COMPLETION = 55;
    private static final int DO_COMMIT_CONTENT = 160;
    private static final int DO_COMMIT_CORRECTION = 56;
    private static final int DO_COMMIT_TEXT = 50;
    private static final int DO_DELETE_SURROUNDING_TEXT = 80;
    private static final int DO_DELETE_SURROUNDING_TEXT_IN_CODE_POINTS = 81;
    private static final int DO_END_BATCH_EDIT = 95;
    private static final int DO_FINISH_COMPOSING_TEXT = 65;
    private static final int DO_GET_CURSOR_CAPS_MODE = 30;
    private static final int DO_GET_EXTRACTED_TEXT = 40;
    private static final int DO_GET_SELECTED_TEXT = 25;
    private static final int DO_GET_TEXT_AFTER_CURSOR = 10;
    private static final int DO_GET_TEXT_BEFORE_CURSOR = 20;
    private static final int DO_PERFORM_CONTEXT_MENU_ACTION = 59;
    private static final int DO_PERFORM_EDITOR_ACTION = 58;
    private static final int DO_PERFORM_PRIVATE_COMMAND = 120;
    private static final int DO_REQUEST_UPDATE_CURSOR_ANCHOR_INFO = 140;
    private static final int DO_SEND_KEY_EVENT = 70;
    private static final int DO_SET_COMPOSING_REGION = 63;
    private static final int DO_SET_COMPOSING_TEXT = 60;
    private static final int DO_SET_SELECTION = 57;
    private static final String TAG = "IInputConnectionWrapper";
    private Handler mH;
    private InputConnection mInputConnection;
    private Looper mMainLooper;
    private Object mLock = new Object();
    private boolean mFinished = false;

    protected abstract boolean isActive();

    class MyHandler extends Handler {
        MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            IInputConnectionWrapper.this.executeMessage(message);
        }
    }

    public IInputConnectionWrapper(Looper looper, InputConnection inputConnection) {
        this.mInputConnection = inputConnection;
        this.mMainLooper = looper;
        this.mH = new MyHandler(this.mMainLooper);
    }

    public InputConnection getInputConnection() {
        InputConnection inputConnection;
        synchronized (this.mLock) {
            inputConnection = this.mInputConnection;
        }
        return inputConnection;
    }

    protected boolean isFinished() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mFinished;
        }
        return z;
    }

    @Override // com.android.internal.view.IInputContext
    public void getTextAfterCursor(int i, int i2, ICharSequenceResultCallback iCharSequenceResultCallback) {
        dispatchMessage(this.mH.obtainMessage(10, i, i2, iCharSequenceResultCallback));
    }

    @Override // com.android.internal.view.IInputContext
    public void getTextBeforeCursor(int i, int i2, ICharSequenceResultCallback iCharSequenceResultCallback) {
        dispatchMessage(this.mH.obtainMessage(20, i, i2, iCharSequenceResultCallback));
    }

    @Override // com.android.internal.view.IInputContext
    public void getSelectedText(int i, ICharSequenceResultCallback iCharSequenceResultCallback) {
        dispatchMessage(this.mH.obtainMessage(25, i, 0, iCharSequenceResultCallback));
    }

    @Override // com.android.internal.view.IInputContext
    public void getCursorCapsMode(int i, IIntResultCallback iIntResultCallback) {
        dispatchMessage(this.mH.obtainMessage(30, i, 0, iIntResultCallback));
    }

    @Override // com.android.internal.view.IInputContext
    public void getExtractedText(ExtractedTextRequest extractedTextRequest, int i, IExtractedTextResultCallback iExtractedTextResultCallback) {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = extractedTextRequest;
        someArgsObtain.arg2 = iExtractedTextResultCallback;
        dispatchMessage(this.mH.obtainMessage(40, i, 0, someArgsObtain));
    }

    @Override // com.android.internal.view.IInputContext
    public void commitText(CharSequence charSequence, int i) {
        dispatchMessage(obtainMessageIO(50, i, charSequence));
    }

    @Override // com.android.internal.view.IInputContext
    public void commitCompletion(CompletionInfo completionInfo) {
        dispatchMessage(obtainMessageO(55, completionInfo));
    }

    @Override // com.android.internal.view.IInputContext
    public void commitCorrection(CorrectionInfo correctionInfo) {
        dispatchMessage(obtainMessageO(56, correctionInfo));
    }

    @Override // com.android.internal.view.IInputContext
    public void setSelection(int i, int i2) {
        dispatchMessage(obtainMessageII(57, i, i2));
    }

    @Override // com.android.internal.view.IInputContext
    public void performEditorAction(int i) {
        dispatchMessage(obtainMessageII(58, i, 0));
    }

    @Override // com.android.internal.view.IInputContext
    public void performContextMenuAction(int i) {
        dispatchMessage(obtainMessageII(59, i, 0));
    }

    @Override // com.android.internal.view.IInputContext
    public void setComposingRegion(int i, int i2) {
        dispatchMessage(obtainMessageII(63, i, i2));
    }

    @Override // com.android.internal.view.IInputContext
    public void setComposingText(CharSequence charSequence, int i) {
        dispatchMessage(obtainMessageIO(60, i, charSequence));
    }

    @Override // com.android.internal.view.IInputContext
    public void finishComposingText() {
        dispatchMessage(obtainMessage(65));
    }

    @Override // com.android.internal.view.IInputContext
    public void sendKeyEvent(KeyEvent keyEvent) {
        dispatchMessage(obtainMessageO(70, keyEvent));
    }

    @Override // com.android.internal.view.IInputContext
    public void clearMetaKeyStates(int i) {
        dispatchMessage(obtainMessageII(130, i, 0));
    }

    @Override // com.android.internal.view.IInputContext
    public void deleteSurroundingText(int i, int i2) {
        dispatchMessage(obtainMessageII(80, i, i2));
    }

    @Override // com.android.internal.view.IInputContext
    public void deleteSurroundingTextInCodePoints(int i, int i2) {
        dispatchMessage(obtainMessageII(81, i, i2));
    }

    @Override // com.android.internal.view.IInputContext
    public void beginBatchEdit() {
        dispatchMessage(obtainMessage(90));
    }

    @Override // com.android.internal.view.IInputContext
    public void endBatchEdit() {
        dispatchMessage(obtainMessage(95));
    }

    @Override // com.android.internal.view.IInputContext
    public void performPrivateCommand(String str, Bundle bundle) {
        dispatchMessage(obtainMessageOO(120, str, bundle));
    }

    @Override // com.android.internal.view.IInputContext
    public void requestUpdateCursorAnchorInfo(int i, IIntResultCallback iIntResultCallback) {
        dispatchMessage(this.mH.obtainMessage(140, i, 0, iIntResultCallback));
    }

    public void closeConnection() {
        dispatchMessage(obtainMessage(150));
    }

    @Override // com.android.internal.view.IInputContext
    public void commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle, IIntResultCallback iIntResultCallback) {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = inputContentInfo;
        someArgsObtain.arg2 = bundle;
        someArgsObtain.arg3 = iIntResultCallback;
        dispatchMessage(this.mH.obtainMessage(160, i, 0, someArgsObtain));
    }

    void dispatchMessage(Message message) {
        if (Looper.myLooper() == this.mMainLooper) {
            executeMessage(message);
            message.recycle();
        } else {
            this.mH.sendMessage(message);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:313|66|(1:78)(3:71|(6:76|(1:82)|326|83|87|88)|77)|79|(0)|326|83|87|88) */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0134, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0135, code lost:
    
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to commitContent(). result=" + r6, r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void executeMessage(android.os.Message r7) {
        /*
            Method dump skipped, instruction units count: 1178
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.view.IInputConnectionWrapper.executeMessage(android.os.Message):void");
    }

    Message obtainMessage(int i) {
        return this.mH.obtainMessage(i);
    }

    Message obtainMessageII(int i, int i2, int i3) {
        return this.mH.obtainMessage(i, i2, i3);
    }

    Message obtainMessageO(int i, Object obj) {
        return this.mH.obtainMessage(i, 0, 0, obj);
    }

    Message obtainMessageIO(int i, int i2, Object obj) {
        return this.mH.obtainMessage(i, i2, 0, obj);
    }

    Message obtainMessageOO(int i, Object obj, Object obj2) {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = obj;
        someArgsObtain.arg2 = obj2;
        return this.mH.obtainMessage(i, 0, 0, someArgsObtain);
    }
}
