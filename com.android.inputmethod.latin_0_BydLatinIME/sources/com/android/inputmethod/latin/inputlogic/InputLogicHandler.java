package com.android.inputmethod.latin.inputlogic;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.android.inputmethod.compat.LooperCompatUtils;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.Suggest;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.InputPointers;

/* JADX INFO: loaded from: classes2.dex */
class InputLogicHandler implements Handler.Callback {
    private static final int MSG_GET_SUGGESTED_WORDS = 1;
    public static final InputLogicHandler NULL_HANDLER = new InputLogicHandler() { // from class: com.android.inputmethod.latin.inputlogic.InputLogicHandler.1
        @Override // com.android.inputmethod.latin.inputlogic.InputLogicHandler
        public void getSuggestedWords(final int sessionId, final int sequenceNumber, final Suggest.OnGetSuggestedWordsCallback callback) {
        }

        @Override // com.android.inputmethod.latin.inputlogic.InputLogicHandler, android.os.Handler.Callback
        public boolean handleMessage(final Message msg) {
            return true;
        }

        @Override // com.android.inputmethod.latin.inputlogic.InputLogicHandler
        public void onCancelBatchInput() {
        }

        @Override // com.android.inputmethod.latin.inputlogic.InputLogicHandler
        public void onStartBatchInput() {
        }

        @Override // com.android.inputmethod.latin.inputlogic.InputLogicHandler
        public void onUpdateBatchInput(final InputPointers batchPointers, final int sequenceNumber) {
        }

        @Override // com.android.inputmethod.latin.inputlogic.InputLogicHandler
        public void reset() {
        }

        @Override // com.android.inputmethod.latin.inputlogic.InputLogicHandler
        public void updateTailBatchInput(final InputPointers batchPointers, final int sequenceNumber) {
        }
    };
    private boolean mInBatchInput;
    final InputLogic mInputLogic;
    final LatinIME mLatinIME;
    private final Object mLock;
    final Handler mNonUIThreadHandler;

    InputLogicHandler() {
        this.mLock = new Object();
        this.mNonUIThreadHandler = null;
        this.mLatinIME = null;
        this.mInputLogic = null;
    }

    public InputLogicHandler(final LatinIME latinIME, final InputLogic inputLogic) {
        this.mLock = new Object();
        HandlerThread handlerThread = new HandlerThread(InputLogicHandler.class.getSimpleName());
        handlerThread.start();
        this.mNonUIThreadHandler = new Handler(handlerThread.getLooper(), this);
        this.mLatinIME = latinIME;
        this.mInputLogic = inputLogic;
    }

    public void reset() {
        this.mNonUIThreadHandler.removeCallbacksAndMessages(null);
    }

    public void destroy() {
        LooperCompatUtils.quitSafely(this.mNonUIThreadHandler.getLooper());
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(final Message msg) {
        if (msg.what == 1) {
            this.mLatinIME.getSuggestedWords(msg.arg1, msg.arg2, (Suggest.OnGetSuggestedWordsCallback) msg.obj);
        }
        return true;
    }

    public void onStartBatchInput() {
        synchronized (this.mLock) {
            this.mInBatchInput = true;
        }
    }

    public boolean isInBatchInput() {
        return this.mInBatchInput;
    }

    private void updateBatchInput(final InputPointers batchPointers, final int sequenceNumber, final boolean isTailBatchInput) {
        synchronized (this.mLock) {
            if (this.mInBatchInput) {
                this.mInputLogic.mWordComposer.setBatchInputPointers(batchPointers);
                getSuggestedWords(isTailBatchInput ? 3 : 2, sequenceNumber, new Suggest.OnGetSuggestedWordsCallback() { // from class: com.android.inputmethod.latin.inputlogic.InputLogicHandler.2
                    @Override // com.android.inputmethod.latin.Suggest.OnGetSuggestedWordsCallback
                    public void onGetSuggestedWords(final SuggestedWords suggestedWords) {
                        InputLogicHandler.this.showGestureSuggestionsWithPreviewVisuals(suggestedWords, isTailBatchInput);
                    }
                });
            }
        }
    }

    void showGestureSuggestionsWithPreviewVisuals(final SuggestedWords suggestedWordsForBatchInput, final boolean isTailBatchInput) {
        if (suggestedWordsForBatchInput.isEmpty()) {
            suggestedWordsForBatchInput = this.mInputLogic.mSuggestedWords;
        }
        this.mLatinIME.mHandler.showGesturePreviewAndSuggestionStrip(suggestedWordsForBatchInput, isTailBatchInput);
        if (isTailBatchInput) {
            this.mInBatchInput = false;
            this.mLatinIME.mHandler.showTailBatchInputResult(suggestedWordsForBatchInput);
        }
    }

    public void onUpdateBatchInput(final InputPointers batchPointers, final int sequenceNumber) {
        updateBatchInput(batchPointers, sequenceNumber, false);
    }

    public void onCancelBatchInput() {
        synchronized (this.mLock) {
            this.mInBatchInput = false;
        }
    }

    public void updateTailBatchInput(final InputPointers batchPointers, final int sequenceNumber) {
        updateBatchInput(batchPointers, sequenceNumber, true);
    }

    public void getSuggestedWords(final int inputStyle, final int sequenceNumber, final Suggest.OnGetSuggestedWordsCallback callback) {
        this.mNonUIThreadHandler.obtainMessage(1, inputStyle, sequenceNumber, callback).sendToTarget();
    }
}
