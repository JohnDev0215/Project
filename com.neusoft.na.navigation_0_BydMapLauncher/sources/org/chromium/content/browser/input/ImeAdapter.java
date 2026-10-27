package org.chromium.content.browser.input;

import android.content.res.Configuration;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.UnderlineSpan;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.RenderCoordinates;
import org.chromium.content.browser.input.ChromiumBaseInputConnection;
import org.chromium.content.browser.input.CursorAnchorInfoController;
import org.chromium.content.browser.input.ReplicaInputConnection;
import org.chromium.ui.picker.InputDialogContainer;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ImeAdapter {
    private static final int COMPOSITION_KEY_CODE = 229;
    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_Ime";
    static KeyCharacterMap sKeyCharacterMap;
    static char[] sSingleCharArray = new char[1];
    private Configuration mCurrentConfig;
    private final CursorAnchorInfoController mCursorAnchorInfoController;
    private ChromiumBaseInputConnection mInputConnection;
    private ChromiumBaseInputConnection.Factory mInputConnectionFactory;
    private InputMethodManagerWrapper mInputMethodManagerWrapper;
    private int mLastCompositionEnd;
    private int mLastCompositionStart;
    private int mLastSelectionEnd;
    private int mLastSelectionStart;
    private String mLastText;
    private long mNativeImeAdapterAndroid;
    private int mTextInputFlags;
    private int mTextInputType = 0;
    private final ImeAdapterDelegate mViewEmbedder;

    public interface ImeAdapterDelegate {
        View getAttachedView();

        ResultReceiver getNewShowKeyboardReceiver();

        void onImeEvent();

        void onKeyboardBoundsUnchanged();

        boolean performContextMenuAction(int i);
    }

    private static int getModifiers(int i) {
        int i2 = (i & 1) != 0 ? 1 : 0;
        if ((i & 2) != 0) {
            i2 |= 4;
        }
        if ((i & 4096) != 0) {
            i2 |= 2;
        }
        if ((1048576 & i) != 0) {
            i2 |= 512;
        }
        return (i & 2097152) != 0 ? i2 | 1024 : i2;
    }

    private static native void nativeAppendBackgroundColorSpan(long j, int i, int i2, int i3);

    private static native void nativeAppendUnderlineSpan(long j, int i, int i2);

    private native void nativeAttachImeAdapter(long j);

    private native void nativeCommitText(long j, String str);

    private native void nativeDeleteSurroundingText(long j, int i, int i2);

    private native void nativeFinishComposingText(long j);

    private native boolean nativeIsImeThreadEnabled(long j);

    private native boolean nativeRequestTextInputStateUpdate(long j);

    private native void nativeResetImeAdapter(long j);

    private native boolean nativeSendKeyEvent(long j, KeyEvent keyEvent, int i, int i2, long j2, int i3, int i4, boolean z, int i5);

    private native boolean nativeSendSyntheticKeyEvent(long j, int i, long j2, int i2, int i3, int i4);

    private native void nativeSetComposingRegion(long j, int i, int i2);

    private native void nativeSetComposingText(long j, CharSequence charSequence, String str, int i);

    private native void nativeSetEditableSelectionOffsets(long j, int i, int i2);

    public ImeAdapter(InputMethodManagerWrapper inputMethodManagerWrapper, ImeAdapterDelegate imeAdapterDelegate) {
        this.mInputMethodManagerWrapper = inputMethodManagerWrapper;
        this.mViewEmbedder = imeAdapterDelegate;
        this.mCurrentConfig = new Configuration(imeAdapterDelegate.getAttachedView().getResources().getConfiguration());
        this.mCursorAnchorInfoController = CursorAnchorInfoController.create(inputMethodManagerWrapper, new CursorAnchorInfoController.ComposingTextDelegate() { // from class: org.chromium.content.browser.input.ImeAdapter.1
            @Override // org.chromium.content.browser.input.CursorAnchorInfoController.ComposingTextDelegate
            public CharSequence getText() {
                return ImeAdapter.this.mLastText;
            }

            @Override // org.chromium.content.browser.input.CursorAnchorInfoController.ComposingTextDelegate
            public int getSelectionStart() {
                return ImeAdapter.this.mLastSelectionStart;
            }

            @Override // org.chromium.content.browser.input.CursorAnchorInfoController.ComposingTextDelegate
            public int getSelectionEnd() {
                return ImeAdapter.this.mLastSelectionEnd;
            }

            @Override // org.chromium.content.browser.input.CursorAnchorInfoController.ComposingTextDelegate
            public int getComposingTextStart() {
                return ImeAdapter.this.mLastCompositionStart;
            }

            @Override // org.chromium.content.browser.input.CursorAnchorInfoController.ComposingTextDelegate
            public int getComposingTextEnd() {
                return ImeAdapter.this.mLastCompositionEnd;
            }
        });
    }

    private boolean isImeThreadEnabled() {
        long j = this.mNativeImeAdapterAndroid;
        if (j == 0) {
            return false;
        }
        return nativeIsImeThreadEnabled(j);
    }

    private void createInputConnectionFactory() {
        if (this.mInputConnectionFactory != null) {
            return;
        }
        if (isImeThreadEnabled()) {
            Log.i(TAG, "ImeThread is enabled.", new Object[0]);
            this.mInputConnectionFactory = new ThreadedInputConnectionFactory(this.mInputMethodManagerWrapper);
        } else {
            Log.i(TAG, "ImeThread is not enabled.", new Object[0]);
            this.mInputConnectionFactory = new ReplicaInputConnection.Factory();
        }
    }

    public ChromiumBaseInputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.imeOptions = 301989888;
        if (this.mTextInputType == 0) {
            setInputConnection(null);
            return null;
        }
        ChromiumBaseInputConnection.Factory factory = this.mInputConnectionFactory;
        if (factory == null) {
            return null;
        }
        setInputConnection(factory.initializeAndGet(this.mViewEmbedder.getAttachedView(), this, this.mTextInputType, this.mTextInputFlags, this.mLastSelectionStart, this.mLastSelectionEnd, editorInfo));
        CursorAnchorInfoController cursorAnchorInfoController = this.mCursorAnchorInfoController;
        if (cursorAnchorInfoController != null) {
            cursorAnchorInfoController.resetMonitoringState();
        }
        return this.mInputConnection;
    }

    private void setInputConnection(ChromiumBaseInputConnection chromiumBaseInputConnection) {
        ChromiumBaseInputConnection chromiumBaseInputConnection2 = this.mInputConnection;
        if (chromiumBaseInputConnection2 == chromiumBaseInputConnection) {
            return;
        }
        if (chromiumBaseInputConnection2 != null) {
            chromiumBaseInputConnection2.unblockOnUiThread();
        }
        this.mInputConnection = chromiumBaseInputConnection;
    }

    public void setInputMethodManagerWrapperForTest(InputMethodManagerWrapper inputMethodManagerWrapper) {
        this.mInputMethodManagerWrapper = inputMethodManagerWrapper;
        CursorAnchorInfoController cursorAnchorInfoController = this.mCursorAnchorInfoController;
        if (cursorAnchorInfoController != null) {
            cursorAnchorInfoController.setInputMethodManagerWrapperForTest(inputMethodManagerWrapper);
        }
    }

    void setInputConnectionFactory(ChromiumBaseInputConnection.Factory factory) {
        this.mInputConnectionFactory = factory;
    }

    ChromiumBaseInputConnection.Factory getInputConnectionFactoryForTest() {
        return this.mInputConnectionFactory;
    }

    public ChromiumBaseInputConnection getInputConnectionForTest() {
        return this.mInputConnection;
    }

    public void updateKeyboardVisibility(int i, int i2, boolean z) {
        this.mTextInputFlags = i2;
        if (this.mTextInputType != i) {
            this.mTextInputType = i;
            if (i != 0) {
                restartInput();
            }
        }
        if (i == 0) {
            hideKeyboard();
        } else if (z) {
            showSoftKeyboard();
        }
    }

    public void updateState(String str, int i, int i2, int i3, int i4, boolean z) {
        if (this.mCursorAnchorInfoController != null && (!TextUtils.equals(this.mLastText, str) || this.mLastSelectionStart != i || this.mLastSelectionEnd != i2 || this.mLastCompositionStart != i3 || this.mLastCompositionEnd != i4)) {
            this.mCursorAnchorInfoController.invalidateLastCursorAnchorInfo();
        }
        this.mLastText = str;
        this.mLastSelectionStart = i;
        this.mLastSelectionEnd = i2;
        this.mLastCompositionStart = i3;
        this.mLastCompositionEnd = i4;
        ChromiumBaseInputConnection chromiumBaseInputConnection = this.mInputConnection;
        if (chromiumBaseInputConnection == null) {
            return;
        }
        int i5 = this.mTextInputType;
        chromiumBaseInputConnection.updateStateOnUiThread(str, i, i2, i3, i4, (i5 == 14 || i5 == 15) ? false : true, z);
    }

    public void attach(long j) {
        long j2 = this.mNativeImeAdapterAndroid;
        if (j2 == j) {
            return;
        }
        if (j2 != 0) {
            nativeResetImeAdapter(j2);
        }
        if (j != 0) {
            nativeAttachImeAdapter(j);
        }
        this.mNativeImeAdapterAndroid = j;
        if (j != 0) {
            createInputConnectionFactory();
        }
    }

    private void showSoftKeyboard() {
        this.mInputMethodManagerWrapper.showSoftInput(this.mViewEmbedder.getAttachedView(), 0, this.mViewEmbedder.getNewShowKeyboardReceiver());
        if (this.mViewEmbedder.getAttachedView().getResources().getConfiguration().keyboard != 1) {
            this.mViewEmbedder.onKeyboardBoundsUnchanged();
        }
    }

    private void hideKeyboard() {
        View attachedView = this.mViewEmbedder.getAttachedView();
        if (this.mInputMethodManagerWrapper.isActive(attachedView)) {
            this.mInputMethodManagerWrapper.hideSoftInputFromWindow(attachedView.getWindowToken(), 0, null);
        }
        if (this.mTextInputType != 0 || this.mInputConnection == null) {
            return;
        }
        restartInput();
    }

    public void onKeyboardConfigurationChanged(Configuration configuration) {
        if (this.mCurrentConfig.keyboard == configuration.keyboard && this.mCurrentConfig.keyboardHidden == configuration.keyboardHidden && this.mCurrentConfig.hardKeyboardHidden == configuration.hardKeyboardHidden) {
            return;
        }
        this.mCurrentConfig = new Configuration(configuration);
        if (this.mTextInputType != 0) {
            restartInput();
            showSoftKeyboard();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        ChromiumBaseInputConnection.Factory factory = this.mInputConnectionFactory;
        if (factory != null) {
            factory.onWindowFocusChanged(z);
        }
    }

    public void onViewAttachedToWindow() {
        ChromiumBaseInputConnection.Factory factory = this.mInputConnectionFactory;
        if (factory != null) {
            factory.onViewAttachedToWindow();
        }
    }

    public void onViewDetachedFromWindow() {
        ChromiumBaseInputConnection.Factory factory = this.mInputConnectionFactory;
        if (factory != null) {
            factory.onViewDetachedFromWindow();
        }
    }

    public void onViewFocusChanged(boolean z) {
        if (!z) {
            resetAndHideKeyboard();
        }
        ChromiumBaseInputConnection.Factory factory = this.mInputConnectionFactory;
        if (factory != null) {
            factory.onViewFocusChanged(z);
        }
    }

    public void moveCursorToSelectionEnd() {
        ChromiumBaseInputConnection chromiumBaseInputConnection = this.mInputConnection;
        if (chromiumBaseInputConnection != null) {
            chromiumBaseInputConnection.moveCursorToSelectionEndOnUiThread();
        }
    }

    void setInputTypeForTest(int i) {
        this.mTextInputType = i;
    }

    private static boolean isTextInputType(int i) {
        return (i == 0 || InputDialogContainer.isDialogInputType(i)) ? false : true;
    }

    public boolean hasTextInputType() {
        return isTextInputType(this.mTextInputType);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ChromiumBaseInputConnection chromiumBaseInputConnection = this.mInputConnection;
        return chromiumBaseInputConnection != null ? chromiumBaseInputConnection.sendKeyEventOnUiThread(keyEvent) : sendKeyEvent(keyEvent);
    }

    public void resetAndHideKeyboard() {
        this.mTextInputType = 0;
        this.mTextInputFlags = 0;
        hideKeyboard();
    }

    void updateSelection(int i, int i2, int i3, int i4) {
        this.mInputMethodManagerWrapper.updateSelection(this.mViewEmbedder.getAttachedView(), i, i2, i3, i4);
    }

    void restartInput() {
        this.mInputMethodManagerWrapper.restartInput(this.mViewEmbedder.getAttachedView());
        ChromiumBaseInputConnection chromiumBaseInputConnection = this.mInputConnection;
        if (chromiumBaseInputConnection != null) {
            chromiumBaseInputConnection.onRestartInputOnUiThread();
        }
    }

    boolean performContextMenuAction(int i) {
        return this.mViewEmbedder.performContextMenuAction(i);
    }

    boolean performEditorAction(int i) {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        if (i == 5) {
            sendSyntheticKeyPress(61, 22);
            return true;
        }
        sendSyntheticKeyPress(66, 22);
        return true;
    }

    void notifyUserAction() {
        this.mInputMethodManagerWrapper.notifyUserAction();
    }

    protected void sendSyntheticKeyPress(int i, int i2) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        sendKeyEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i, 0, 0, -1, 0, i2));
        sendKeyEvent(new KeyEvent(SystemClock.uptimeMillis(), jUptimeMillis, 1, i, 0, 0, -1, 0, i2));
    }

    boolean sendCompositionToNative(CharSequence charSequence, int i, boolean z, int i2) {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        if (TextUtils.equals(charSequence, "\n")) {
            sendSyntheticKeyPress(66, 6);
            return true;
        }
        this.mViewEmbedder.onImeEvent();
        long jUptimeMillis = SystemClock.uptimeMillis();
        nativeSendSyntheticKeyEvent(this.mNativeImeAdapterAndroid, 7, jUptimeMillis, 229, 0, i2);
        if (z) {
            nativeCommitText(this.mNativeImeAdapterAndroid, charSequence.toString());
        } else {
            nativeSetComposingText(this.mNativeImeAdapterAndroid, charSequence, charSequence.toString(), i);
        }
        nativeSendSyntheticKeyEvent(this.mNativeImeAdapterAndroid, 9, jUptimeMillis, 229, 0, i2);
        return true;
    }

    boolean finishComposingText() {
        long j = this.mNativeImeAdapterAndroid;
        if (j == 0) {
            return false;
        }
        nativeFinishComposingText(j);
        return true;
    }

    boolean sendKeyEvent(KeyEvent keyEvent) {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        int action = keyEvent.getAction();
        if (action != 0 && action != 1) {
            return false;
        }
        this.mViewEmbedder.onImeEvent();
        return nativeSendKeyEvent(this.mNativeImeAdapterAndroid, keyEvent, keyEvent.getAction(), getModifiers(keyEvent.getMetaState()), keyEvent.getEventTime(), keyEvent.getKeyCode(), keyEvent.getScanCode(), false, keyEvent.getUnicodeChar());
    }

    boolean deleteSurroundingText(int i, int i2) {
        this.mViewEmbedder.onImeEvent();
        long j = this.mNativeImeAdapterAndroid;
        if (j == 0) {
            return false;
        }
        nativeSendSyntheticKeyEvent(j, 7, SystemClock.uptimeMillis(), 229, 0, 0);
        nativeDeleteSurroundingText(this.mNativeImeAdapterAndroid, i, i2);
        nativeSendSyntheticKeyEvent(this.mNativeImeAdapterAndroid, 9, SystemClock.uptimeMillis(), 229, 0, 0);
        return true;
    }

    boolean setEditableSelectionOffsets(int i, int i2) {
        long j = this.mNativeImeAdapterAndroid;
        if (j == 0) {
            return false;
        }
        nativeSetEditableSelectionOffsets(j, i, i2);
        return true;
    }

    boolean setComposingRegion(int i, int i2) {
        long j = this.mNativeImeAdapterAndroid;
        if (j == 0) {
            return false;
        }
        nativeSetComposingRegion(j, i, i2);
        return true;
    }

    private void focusedNodeChanged(boolean z) {
        CursorAnchorInfoController cursorAnchorInfoController = this.mCursorAnchorInfoController;
        if (cursorAnchorInfoController != null) {
            cursorAnchorInfoController.focusedNodeChanged(z);
        }
        if (this.mTextInputType == 0 || this.mInputConnection == null || !z) {
            return;
        }
        restartInput();
    }

    boolean requestTextInputStateUpdate() {
        long j = this.mNativeImeAdapterAndroid;
        if (j == 0 || this.mInputConnection == null) {
            return false;
        }
        return nativeRequestTextInputStateUpdate(j);
    }

    public boolean onRequestCursorUpdates(int i) {
        CursorAnchorInfoController cursorAnchorInfoController = this.mCursorAnchorInfoController;
        if (cursorAnchorInfoController == null) {
            return false;
        }
        return cursorAnchorInfoController.onRequestCursorUpdates(i, this.mViewEmbedder.getAttachedView());
    }

    public void onUpdateFrameInfo(RenderCoordinates renderCoordinates, boolean z, boolean z2, float f, float f2, float f3) {
        CursorAnchorInfoController cursorAnchorInfoController = this.mCursorAnchorInfoController;
        if (cursorAnchorInfoController == null) {
            return;
        }
        cursorAnchorInfoController.onUpdateFrameInfo(renderCoordinates, z, z2, f, f2, f3, this.mViewEmbedder.getAttachedView());
    }

    private void populateUnderlinesFromSpans(CharSequence charSequence, long j) {
        if (charSequence instanceof SpannableString) {
            SpannableString spannableString = (SpannableString) charSequence;
            for (CharacterStyle characterStyle : (CharacterStyle[]) spannableString.getSpans(0, charSequence.length(), CharacterStyle.class)) {
                if (characterStyle instanceof BackgroundColorSpan) {
                    nativeAppendBackgroundColorSpan(j, spannableString.getSpanStart(characterStyle), spannableString.getSpanEnd(characterStyle), ((BackgroundColorSpan) characterStyle).getBackgroundColor());
                } else if (characterStyle instanceof UnderlineSpan) {
                    nativeAppendUnderlineSpan(j, spannableString.getSpanStart(characterStyle), spannableString.getSpanEnd(characterStyle));
                }
            }
        }
    }

    private void cancelComposition() {
        if (this.mInputConnection != null) {
            restartInput();
        }
    }

    private void setCharacterBounds(float[] fArr) {
        CursorAnchorInfoController cursorAnchorInfoController = this.mCursorAnchorInfoController;
        if (cursorAnchorInfoController == null) {
            return;
        }
        cursorAnchorInfoController.setCompositionCharacterBounds(fArr);
    }

    private void detach() {
        this.mNativeImeAdapterAndroid = 0L;
        CursorAnchorInfoController cursorAnchorInfoController = this.mCursorAnchorInfoController;
        if (cursorAnchorInfoController != null) {
            cursorAnchorInfoController.focusedNodeChanged(false);
        }
    }
}
