package com.android.inputmethod.keyboard;

import com.android.inputmethod.latin.common.InputPointers;

/* JADX INFO: loaded from: classes2.dex */
public interface KeyboardActionListener {
    public static final KeyboardActionListener EMPTY_LISTENER = new Adapter();

    public static class Adapter implements KeyboardActionListener {
        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onCancelBatchInput() {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onCancelInput() {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onCodeInput(int primaryCode, int x, int y, boolean isKeyRepeat) {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public boolean onCustomRequest(int requestCode) {
            return false;
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onEndBatchInput(InputPointers batchPointers) {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onFinishSlidingInput() {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onPressKey(int primaryCode, int repeatCount, boolean isSinglePointer) {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onReleaseKey(int primaryCode, boolean withSliding) {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onStartBatchInput() {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onTextInput(String text) {
        }

        @Override // com.android.inputmethod.keyboard.KeyboardActionListener
        public void onUpdateBatchInput(InputPointers batchPointers) {
        }
    }

    void onCancelBatchInput();

    void onCancelInput();

    void onCodeInput(int primaryCode, int x, int y, boolean isKeyRepeat);

    boolean onCustomRequest(int requestCode);

    void onEndBatchInput(InputPointers batchPointers);

    void onFinishSlidingInput();

    void onPressKey(int primaryCode, int repeatCount, boolean isSinglePointer);

    void onReleaseKey(int primaryCode, boolean withSliding);

    void onStartBatchInput();

    void onTextInput(String text);

    void onUpdateBatchInput(InputPointers batchPointers);
}
