package android.inputmethodservice;

import android.graphics.Rect;
import android.inputmethodservice.MultiClientInputMethodClientCallbackAdaptor;
import android.inputmethodservice.MultiClientInputMethodServiceDelegate;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import com.android.internal.inputmethod.CancellationGroup;
import com.android.internal.inputmethod.IMultiClientInputMethodSession;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import com.android.internal.view.IInputContext;
import com.android.internal.view.IInputMethodSession;
import com.android.internal.view.InputConnectionWrapper;
import java.lang.ref.WeakReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
final class MultiClientInputMethodClientCallbackAdaptor {
    static final boolean DEBUG = false;
    static final String TAG = "MultiClientInputMethodClientCallbackAdaptor";
    CallbackImpl mCallbackImpl;
    private final CancellationGroup mCancellationGroup;
    KeyEvent.DispatcherState mDispatcherState;
    Handler mHandler;
    InputEventReceiver mInputEventReceiver;
    InputChannel mReadChannel;
    private final Object mSessionLock;

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportNotSupported() {
    }

    IInputMethodSession.Stub createIInputMethodSession() {
        InputMethodSessionImpl inputMethodSessionImpl;
        synchronized (this.mSessionLock) {
            inputMethodSessionImpl = new InputMethodSessionImpl(this.mSessionLock, this.mCallbackImpl, this.mHandler, this.mCancellationGroup);
        }
        return inputMethodSessionImpl;
    }

    IMultiClientInputMethodSession.Stub createIMultiClientInputMethodSession() {
        MultiClientInputMethodSessionImpl multiClientInputMethodSessionImpl;
        synchronized (this.mSessionLock) {
            multiClientInputMethodSessionImpl = new MultiClientInputMethodSessionImpl(this.mSessionLock, this.mCallbackImpl, this.mHandler, this.mCancellationGroup);
        }
        return multiClientInputMethodSessionImpl;
    }

    MultiClientInputMethodClientCallbackAdaptor(MultiClientInputMethodServiceDelegate.ClientCallback clientCallback, Looper looper, KeyEvent.DispatcherState dispatcherState, InputChannel inputChannel) {
        Object obj = new Object();
        this.mSessionLock = obj;
        CancellationGroup cancellationGroup = new CancellationGroup();
        this.mCancellationGroup = cancellationGroup;
        synchronized (obj) {
            this.mCallbackImpl = new CallbackImpl(this, clientCallback);
            this.mDispatcherState = dispatcherState;
            this.mHandler = new Handler(looper, null, true);
            this.mReadChannel = inputChannel;
            this.mInputEventReceiver = new ImeInputEventReceiver(this.mReadChannel, this.mHandler.getLooper(), cancellationGroup, this.mDispatcherState, this.mCallbackImpl.mOriginalCallback);
        }
    }

    private static final class KeyEventCallbackAdaptor implements KeyEvent.Callback {
        private final MultiClientInputMethodServiceDelegate.ClientCallback mLocalCallback;

        KeyEventCallbackAdaptor(MultiClientInputMethodServiceDelegate.ClientCallback clientCallback) {
            this.mLocalCallback = clientCallback;
        }

        @Override // android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return this.mLocalCallback.onKeyDown(i, keyEvent);
        }

        @Override // android.view.KeyEvent.Callback
        public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
            return this.mLocalCallback.onKeyLongPress(i, keyEvent);
        }

        @Override // android.view.KeyEvent.Callback
        public boolean onKeyUp(int i, KeyEvent keyEvent) {
            return this.mLocalCallback.onKeyUp(i, keyEvent);
        }

        @Override // android.view.KeyEvent.Callback
        public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
            return this.mLocalCallback.onKeyMultiple(i, keyEvent);
        }
    }

    private static final class ImeInputEventReceiver extends InputEventReceiver {
        private final CancellationGroup mCancellationGroupOnFinishSession;
        private final MultiClientInputMethodServiceDelegate.ClientCallback mClientCallback;
        private final KeyEvent.DispatcherState mDispatcherState;
        private final KeyEventCallbackAdaptor mKeyEventCallbackAdaptor;

        ImeInputEventReceiver(InputChannel inputChannel, Looper looper, CancellationGroup cancellationGroup, KeyEvent.DispatcherState dispatcherState, MultiClientInputMethodServiceDelegate.ClientCallback clientCallback) {
            super(inputChannel, looper);
            this.mCancellationGroupOnFinishSession = cancellationGroup;
            this.mDispatcherState = dispatcherState;
            this.mClientCallback = clientCallback;
            this.mKeyEventCallbackAdaptor = new KeyEventCallbackAdaptor(clientCallback);
        }

        @Override // android.view.InputEventReceiver
        public void onInputEvent(InputEvent inputEvent) {
            boolean zOnGenericMotionEvent;
            if (this.mCancellationGroupOnFinishSession.isCanceled()) {
                return;
            }
            try {
                if (inputEvent instanceof KeyEvent) {
                    KeyEventCallbackAdaptor keyEventCallbackAdaptor = this.mKeyEventCallbackAdaptor;
                    zOnGenericMotionEvent = ((KeyEvent) inputEvent).dispatch(keyEventCallbackAdaptor, this.mDispatcherState, keyEventCallbackAdaptor);
                } else {
                    MotionEvent motionEvent = (MotionEvent) inputEvent;
                    if (motionEvent.isFromSource(4)) {
                        zOnGenericMotionEvent = this.mClientCallback.onTrackballEvent(motionEvent);
                    } else {
                        zOnGenericMotionEvent = this.mClientCallback.onGenericMotionEvent(motionEvent);
                    }
                }
                finishInputEvent(inputEvent, zOnGenericMotionEvent);
            } finally {
                finishInputEvent(inputEvent, false);
            }
        }
    }

    private static final class InputMethodSessionImpl extends IInputMethodSession.Stub {
        private CallbackImpl mCallbackImpl;
        private final CancellationGroup mCancellationGroupOnFinishSession;
        private Handler mHandler;
        private final Object mSessionLock;

        InputMethodSessionImpl(Object obj, CallbackImpl callbackImpl, Handler handler, CancellationGroup cancellationGroup) {
            this.mSessionLock = obj;
            this.mCallbackImpl = callbackImpl;
            this.mHandler = handler;
            this.mCancellationGroupOnFinishSession = cancellationGroup;
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void updateExtractedText(int i, ExtractedText extractedText) {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void updateSelection(int i, int i2, int i3, int i4, int i5, int i6) {
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && this.mHandler != null) {
                    SomeArgs someArgsObtain = SomeArgs.obtain();
                    someArgsObtain.argi1 = i;
                    someArgsObtain.argi2 = i2;
                    someArgsObtain.argi3 = i3;
                    someArgsObtain.argi4 = i4;
                    someArgsObtain.argi5 = i5;
                    someArgsObtain.argi6 = i6;
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.inputmethodservice.-$$Lambda$7zvQY_ita5XptgNAIC55l9zpLpw
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).updateSelection((SomeArgs) obj2);
                        }
                    }, this.mCallbackImpl, someArgsObtain));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void viewClicked(boolean z) {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void updateCursor(Rect rect) {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void displayCompletions(CompletionInfo[] completionInfoArr) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && (handler = this.mHandler) != null) {
                    handler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.inputmethodservice.-$$Lambda$g3t6cGPYn-STr6PR4HIkmAub8nQ
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).displayCompletions((CompletionInfo[]) obj2);
                        }
                    }, this.mCallbackImpl, completionInfoArr));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void appPrivateCommand(String str, Bundle bundle) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && (handler = this.mHandler) != null) {
                    handler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.inputmethodservice.-$$Lambda$g8698iemcpOI1iaFYODpsDfF220
                        @Override // com.android.internal.util.function.TriConsumer
                        public final void accept(Object obj, Object obj2, Object obj3) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).appPrivateCommand((String) obj2, (Bundle) obj3);
                        }
                    }, this.mCallbackImpl, str, bundle));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void toggleSoftInput(int i, int i2) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && (handler = this.mHandler) != null) {
                    handler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.inputmethodservice.-$$Lambda$EJ5wtbZkebbCAnw3AeOzQZS7OSw
                        @Override // com.android.internal.util.function.TriConsumer
                        public final void accept(Object obj, Object obj2, Object obj3) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).toggleSoftInput(((Integer) obj2).intValue(), ((Integer) obj3).intValue());
                        }
                    }, this.mCallbackImpl, Integer.valueOf(i), Integer.valueOf(i2)));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void finishSession() {
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && this.mHandler != null) {
                    this.mCancellationGroupOnFinishSession.cancelAll();
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: android.inputmethodservice.-$$Lambda$rgn-8TIKKrlBRz3FpsKQND6MvVs
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).finishSession();
                        }
                    }, this.mCallbackImpl));
                    this.mCallbackImpl = null;
                    this.mHandler = null;
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void updateCursorAnchorInfo(CursorAnchorInfo cursorAnchorInfo) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && (handler = this.mHandler) != null) {
                    handler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.inputmethodservice.-$$Lambda$qZnbA87OwzjQcYKMkVRpJ527ViY
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).updateCursorAnchorInfo((CursorAnchorInfo) obj2);
                        }
                    }, this.mCallbackImpl, cursorAnchorInfo));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public final void notifyImeHidden() {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void removeImeSurface() {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }
    }

    private static final class MultiClientInputMethodSessionImpl extends IMultiClientInputMethodSession.Stub {
        private CallbackImpl mCallbackImpl;
        private final CancellationGroup mCancellationGroupOnFinishSession;
        private Handler mHandler;
        private final Object mSessionLock;

        MultiClientInputMethodSessionImpl(Object obj, CallbackImpl callbackImpl, Handler handler, CancellationGroup cancellationGroup) {
            this.mSessionLock = obj;
            this.mCallbackImpl = callbackImpl;
            this.mHandler = handler;
            this.mCancellationGroupOnFinishSession = cancellationGroup;
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void startInputOrWindowGainedFocus(IInputContext iInputContext, int i, EditorInfo editorInfo, int i2, int i3, int i4) {
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && this.mHandler != null) {
                    SomeArgs someArgsObtain = SomeArgs.obtain();
                    InputConnectionWrapper inputConnectionWrapper = null;
                    WeakReference weakReference = new WeakReference(null);
                    if (iInputContext != null) {
                        inputConnectionWrapper = new InputConnectionWrapper(weakReference, iInputContext, i, this.mCancellationGroupOnFinishSession);
                    }
                    someArgsObtain.arg1 = inputConnectionWrapper;
                    someArgsObtain.arg2 = editorInfo;
                    someArgsObtain.argi1 = i2;
                    someArgsObtain.argi2 = i3;
                    someArgsObtain.argi3 = i4;
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.inputmethodservice.-$$Lambda$eSTmZnvYfdTBYMV1iJEAELPH4QU
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).startInputOrWindowGainedFocus((SomeArgs) obj2);
                        }
                    }, this.mCallbackImpl, someArgsObtain));
                }
            }
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void showSoftInput(int i, ResultReceiver resultReceiver) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && (handler = this.mHandler) != null) {
                    handler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.inputmethodservice.-$$Lambda$0ZsJPNUja-JjNAG6PokZ0FO9MsE
                        @Override // com.android.internal.util.function.TriConsumer
                        public final void accept(Object obj, Object obj2, Object obj3) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).showSoftInput(((Integer) obj2).intValue(), (ResultReceiver) obj3);
                        }
                    }, this.mCallbackImpl, Integer.valueOf(i), resultReceiver));
                }
            }
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void hideSoftInput(int i, ResultReceiver resultReceiver) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (this.mCallbackImpl != null && (handler = this.mHandler) != null) {
                    handler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.inputmethodservice.-$$Lambda$tUMtUY08LV5lNmIGV07VEZTP8h4
                        @Override // com.android.internal.util.function.TriConsumer
                        public final void accept(Object obj, Object obj2, Object obj3) {
                            ((MultiClientInputMethodClientCallbackAdaptor.CallbackImpl) obj).hideSoftInput(((Integer) obj2).intValue(), (ResultReceiver) obj3);
                        }
                    }, this.mCallbackImpl, Integer.valueOf(i), resultReceiver));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class CallbackImpl {
        private final MultiClientInputMethodClientCallbackAdaptor mCallbackAdaptor;
        private boolean mFinished = false;
        private final MultiClientInputMethodServiceDelegate.ClientCallback mOriginalCallback;

        CallbackImpl(MultiClientInputMethodClientCallbackAdaptor multiClientInputMethodClientCallbackAdaptor, MultiClientInputMethodServiceDelegate.ClientCallback clientCallback) {
            this.mCallbackAdaptor = multiClientInputMethodClientCallbackAdaptor;
            this.mOriginalCallback = clientCallback;
        }

        void updateSelection(SomeArgs someArgs) {
            try {
                if (this.mFinished) {
                    return;
                }
                this.mOriginalCallback.onUpdateSelection(someArgs.argi1, someArgs.argi2, someArgs.argi3, someArgs.argi4, someArgs.argi5, someArgs.argi6);
            } finally {
                someArgs.recycle();
            }
        }

        void displayCompletions(CompletionInfo[] completionInfoArr) {
            if (this.mFinished) {
                return;
            }
            this.mOriginalCallback.onDisplayCompletions(completionInfoArr);
        }

        void appPrivateCommand(String str, Bundle bundle) {
            if (this.mFinished) {
                return;
            }
            this.mOriginalCallback.onAppPrivateCommand(str, bundle);
        }

        void toggleSoftInput(int i, int i2) {
            if (this.mFinished) {
                return;
            }
            this.mOriginalCallback.onToggleSoftInput(i, i2);
        }

        void finishSession() {
            if (this.mFinished) {
                return;
            }
            this.mFinished = true;
            this.mOriginalCallback.onFinishSession();
            synchronized (this.mCallbackAdaptor.mSessionLock) {
                this.mCallbackAdaptor.mDispatcherState = null;
                if (this.mCallbackAdaptor.mReadChannel != null) {
                    this.mCallbackAdaptor.mReadChannel.dispose();
                    this.mCallbackAdaptor.mReadChannel = null;
                }
                this.mCallbackAdaptor.mInputEventReceiver = null;
            }
        }

        void updateCursorAnchorInfo(CursorAnchorInfo cursorAnchorInfo) {
            if (this.mFinished) {
                return;
            }
            this.mOriginalCallback.onUpdateCursorAnchorInfo(cursorAnchorInfo);
        }

        void startInputOrWindowGainedFocus(SomeArgs someArgs) {
            try {
                if (this.mFinished) {
                    return;
                }
                this.mOriginalCallback.onStartInputOrWindowGainedFocus((InputConnectionWrapper) someArgs.arg1, (EditorInfo) someArgs.arg2, someArgs.argi1, someArgs.argi2, someArgs.argi3);
            } finally {
                someArgs.recycle();
            }
        }

        void showSoftInput(int i, ResultReceiver resultReceiver) {
            if (this.mFinished) {
                return;
            }
            this.mOriginalCallback.onShowSoftInput(i, resultReceiver);
        }

        void hideSoftInput(int i, ResultReceiver resultReceiver) {
            if (this.mFinished) {
                return;
            }
            this.mOriginalCallback.onHideSoftInput(i, resultReceiver);
        }
    }
}
