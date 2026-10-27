package com.android.internal.view;

import android.inputmethodservice.AbstractInputMethodService;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionInspector;
import android.view.inputmethod.InputContentInfo;
import com.android.internal.inputmethod.CancellationGroup;
import com.android.internal.inputmethod.ResultCallbacks;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class InputConnectionWrapper implements InputConnection {
    private static final int MAX_WAIT_TIME_MILLIS = 2000;
    private static final String TAG = "InputConnectionWrapper";
    private final CancellationGroup mCancellationGroup;
    private final IInputContext mIInputContext;
    private final WeakReference<AbstractInputMethodService> mInputMethodService;
    private final int mMissingMethods;

    @Override // android.view.inputmethod.InputConnection
    public void closeConnection() {
    }

    @Override // android.view.inputmethod.InputConnection
    public Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean reportFullscreenMode(boolean z) {
        return false;
    }

    public InputConnectionWrapper(WeakReference<AbstractInputMethodService> weakReference, IInputContext iInputContext, int i, CancellationGroup cancellationGroup) {
        this.mInputMethodService = weakReference;
        this.mIInputContext = iInputContext;
        this.mMissingMethods = i;
        this.mCancellationGroup = cancellationGroup;
    }

    private static void logInternal(String str, boolean z, Object obj) {
        if (z) {
            Log.w(TAG, str + " didn't respond in 2000 msec. Returning default: " + obj);
        } else {
            Log.w(TAG, str + " was canceled before complete. Returning default: " + obj);
        }
    }

    private static int getResultOrZero(CancellationGroup.Completable.Int r2, String str) {
        boolean zAwait = r2.await(2000, TimeUnit.MILLISECONDS);
        if (r2.hasValue()) {
            return r2.getValue();
        }
        logInternal(str, zAwait, 0);
        return 0;
    }

    private static <T> T getResultOrNull(CancellationGroup.Completable.Values<T> values, String str) {
        boolean zAwait = values.await(2000, TimeUnit.MILLISECONDS);
        if (values.hasValue()) {
            return values.getValue();
        }
        logInternal(str, zAwait, null);
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextAfterCursor(int i, int i2) {
        if (this.mCancellationGroup.isCanceled()) {
            return null;
        }
        CancellationGroup.Completable.CharSequence charSequenceCreateCompletableCharSequence = this.mCancellationGroup.createCompletableCharSequence();
        try {
            this.mIInputContext.getTextAfterCursor(i, i2, ResultCallbacks.of(charSequenceCreateCompletableCharSequence));
            return (CharSequence) getResultOrNull(charSequenceCreateCompletableCharSequence, "getTextAfterCursor()");
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int i, int i2) {
        if (this.mCancellationGroup.isCanceled()) {
            return null;
        }
        CancellationGroup.Completable.CharSequence charSequenceCreateCompletableCharSequence = this.mCancellationGroup.createCompletableCharSequence();
        try {
            this.mIInputContext.getTextBeforeCursor(i, i2, ResultCallbacks.of(charSequenceCreateCompletableCharSequence));
            return (CharSequence) getResultOrNull(charSequenceCreateCompletableCharSequence, "getTextBeforeCursor()");
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getSelectedText(int i) {
        if (this.mCancellationGroup.isCanceled() || isMethodMissing(1)) {
            return null;
        }
        CancellationGroup.Completable.CharSequence charSequenceCreateCompletableCharSequence = this.mCancellationGroup.createCompletableCharSequence();
        try {
            this.mIInputContext.getSelectedText(i, ResultCallbacks.of(charSequenceCreateCompletableCharSequence));
            return (CharSequence) getResultOrNull(charSequenceCreateCompletableCharSequence, "getSelectedText()");
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public int getCursorCapsMode(int i) {
        if (this.mCancellationGroup.isCanceled()) {
            return 0;
        }
        CancellationGroup.Completable.Int intCreateCompletableInt = this.mCancellationGroup.createCompletableInt();
        try {
            this.mIInputContext.getCursorCapsMode(i, ResultCallbacks.of(intCreateCompletableInt));
            return getResultOrZero(intCreateCompletableInt, "getCursorCapsMode()");
        } catch (RemoteException unused) {
            return 0;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        if (this.mCancellationGroup.isCanceled()) {
            return null;
        }
        CancellationGroup.Completable.ExtractedText extractedTextCreateCompletableExtractedText = this.mCancellationGroup.createCompletableExtractedText();
        try {
            this.mIInputContext.getExtractedText(extractedTextRequest, i, ResultCallbacks.of(extractedTextCreateCompletableExtractedText));
            return (ExtractedText) getResultOrNull(extractedTextCreateCompletableExtractedText, "getExtractedText()");
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i) {
        try {
            this.mIInputContext.commitText(charSequence, i);
            notifyUserActionIfNecessary();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    private void notifyUserActionIfNecessary() {
        AbstractInputMethodService abstractInputMethodService = this.mInputMethodService.get();
        if (abstractInputMethodService == null) {
            return;
        }
        abstractInputMethodService.notifyUserActionIfNecessary();
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCompletion(CompletionInfo completionInfo) {
        if (isMethodMissing(4)) {
            return false;
        }
        try {
            this.mIInputContext.commitCompletion(completionInfo);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        try {
            this.mIInputContext.commitCorrection(correctionInfo);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setSelection(int i, int i2) {
        try {
            this.mIInputContext.setSelection(i, i2);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performEditorAction(int i) {
        try {
            this.mIInputContext.performEditorAction(i);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int i) {
        try {
            this.mIInputContext.performContextMenuAction(i);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int i, int i2) {
        if (isMethodMissing(2)) {
            return false;
        }
        try {
            this.mIInputContext.setComposingRegion(i, i2);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i) {
        try {
            this.mIInputContext.setComposingText(charSequence, i);
            notifyUserActionIfNecessary();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        try {
            this.mIInputContext.finishComposingText();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        try {
            this.mIInputContext.beginBatchEdit();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        try {
            this.mIInputContext.endBatchEdit();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent keyEvent) {
        try {
            this.mIInputContext.sendKeyEvent(keyEvent);
            notifyUserActionIfNecessary();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int i) {
        try {
            this.mIInputContext.clearMetaKeyStates(i);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int i, int i2) {
        try {
            this.mIInputContext.deleteSurroundingText(i, i2);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        if (isMethodMissing(16)) {
            return false;
        }
        try {
            this.mIInputContext.deleteSurroundingTextInCodePoints(i, i2);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String str, Bundle bundle) {
        try {
            this.mIInputContext.performPrivateCommand(str, bundle);
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int i) {
        if (this.mCancellationGroup.isCanceled() || isMethodMissing(8)) {
            return false;
        }
        CancellationGroup.Completable.Int intCreateCompletableInt = this.mCancellationGroup.createCompletableInt();
        try {
            this.mIInputContext.requestUpdateCursorAnchorInfo(i, ResultCallbacks.of(intCreateCompletableInt));
            return getResultOrZero(intCreateCompletableInt, "requestUpdateCursorAnchorInfo()") != 0;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        if (this.mCancellationGroup.isCanceled() || isMethodMissing(128)) {
            return false;
        }
        if ((i & 1) != 0) {
            AbstractInputMethodService abstractInputMethodService = this.mInputMethodService.get();
            if (abstractInputMethodService == null) {
                return false;
            }
            abstractInputMethodService.exposeContent(inputContentInfo, this);
        }
        CancellationGroup.Completable.Int intCreateCompletableInt = this.mCancellationGroup.createCompletableInt();
        try {
            this.mIInputContext.commitContent(inputContentInfo, i, bundle, ResultCallbacks.of(intCreateCompletableInt));
            return getResultOrZero(intCreateCompletableInt, "commitContent()") != 0;
        } catch (RemoteException unused) {
            return false;
        }
    }

    private boolean isMethodMissing(int i) {
        return (this.mMissingMethods & i) == i;
    }

    public String toString() {
        return "InputConnectionWrapper{idHash=#" + Integer.toHexString(System.identityHashCode(this)) + " mMissingMethods=" + InputConnectionInspector.getMissingMethodFlagsAsString(this.mMissingMethods) + "}";
    }
}
