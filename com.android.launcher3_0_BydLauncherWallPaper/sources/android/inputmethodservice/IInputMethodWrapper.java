package android.inputmethodservice;

import android.Manifest;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.InputChannel;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodSession;
import android.view.inputmethod.InputMethodSubtype;
import com.android.internal.inputmethod.CancellationGroup;
import com.android.internal.inputmethod.IInputMethodPrivilegedOperations;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import com.android.internal.view.IInlineSuggestionsRequestCallback;
import com.android.internal.view.IInputContext;
import com.android.internal.view.IInputMethod;
import com.android.internal.view.IInputMethodSession;
import com.android.internal.view.IInputSessionCallback;
import com.android.internal.view.InlineSuggestionsRequestInfo;
import com.android.internal.view.InputConnectionWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
class IInputMethodWrapper extends IInputMethod.Stub implements HandlerCaller.Callback {
    private static final int DO_CHANGE_INPUTMETHOD_SUBTYPE = 80;
    private static final int DO_CREATE_INLINE_SUGGESTIONS_REQUEST = 90;
    private static final int DO_CREATE_SESSION = 40;
    private static final int DO_DUMP = 1;
    private static final int DO_HIDE_SOFT_INPUT = 70;
    private static final int DO_INITIALIZE_INTERNAL = 10;
    private static final int DO_REVOKE_SESSION = 50;
    private static final int DO_SET_INPUT_CONTEXT = 20;
    private static final int DO_SET_SESSION_ENABLED = 45;
    private static final int DO_SHOW_SOFT_INPUT = 60;
    private static final int DO_START_INPUT = 32;
    private static final int DO_UNSET_INPUT_CONTEXT = 30;
    private static final String TAG = "InputMethodWrapper";
    final HandlerCaller mCaller;
    CancellationGroup mCancellationGroup = null;
    final Context mContext;
    final WeakReference<InputMethod> mInputMethod;
    final WeakReference<AbstractInputMethodService> mTarget;
    final int mTargetSdkVersion;

    static final class InputMethodSessionCallbackWrapper implements InputMethod.SessionCallback {
        final IInputSessionCallback mCb;
        final InputChannel mChannel;
        final Context mContext;

        InputMethodSessionCallbackWrapper(Context context, InputChannel inputChannel, IInputSessionCallback iInputSessionCallback) {
            this.mContext = context;
            this.mChannel = inputChannel;
            this.mCb = iInputSessionCallback;
        }

        @Override // android.view.inputmethod.InputMethod.SessionCallback
        public void sessionCreated(InputMethodSession inputMethodSession) {
            try {
                if (inputMethodSession != null) {
                    this.mCb.sessionCreated(new IInputMethodSessionWrapper(this.mContext, inputMethodSession, this.mChannel));
                } else {
                    InputChannel inputChannel = this.mChannel;
                    if (inputChannel != null) {
                        inputChannel.dispose();
                    }
                    this.mCb.sessionCreated(null);
                }
            } catch (RemoteException unused) {
            }
        }
    }

    public IInputMethodWrapper(AbstractInputMethodService abstractInputMethodService, InputMethod inputMethod) {
        this.mTarget = new WeakReference<>(abstractInputMethodService);
        Context applicationContext = abstractInputMethodService.getApplicationContext();
        this.mContext = applicationContext;
        this.mCaller = new HandlerCaller(applicationContext, null, this, true);
        this.mInputMethod = new WeakReference<>(inputMethod);
        this.mTargetSdkVersion = abstractInputMethodService.getApplicationInfo().targetSdkVersion;
    }

    @Override // com.android.internal.os.HandlerCaller.Callback
    public void executeMessage(Message message) {
        SomeArgs someArgs;
        InputMethod inputMethod = this.mInputMethod.get();
        if (inputMethod == null && message.what != 1) {
            Log.w(TAG, "Input method reference was null, ignoring message: " + message.what);
            return;
        }
        switch (message.what) {
            case 1:
                AbstractInputMethodService abstractInputMethodService = this.mTarget.get();
                if (abstractInputMethodService == null) {
                    return;
                }
                someArgs = (SomeArgs) message.obj;
                try {
                    abstractInputMethodService.dump((FileDescriptor) someArgs.arg1, (PrintWriter) someArgs.arg2, (String[]) someArgs.arg3);
                    break;
                } catch (RuntimeException e) {
                    ((PrintWriter) someArgs.arg2).println("Exception: " + e);
                }
                synchronized (someArgs.arg4) {
                    ((CountDownLatch) someArgs.arg4).countDown();
                    break;
                }
                return;
            case 10:
                someArgs = (SomeArgs) message.obj;
                try {
                    inputMethod.initializeInternal((IBinder) someArgs.arg1, message.arg1, (IInputMethodPrivilegedOperations) someArgs.arg2);
                    return;
                } finally {
                    someArgs.recycle();
                }
            case 20:
                inputMethod.bindInput((InputBinding) message.obj);
                return;
            case 30:
                inputMethod.unbindInput();
                return;
            case 32:
                SomeArgs someArgs2 = (SomeArgs) message.obj;
                IBinder iBinder = (IBinder) someArgs2.arg1;
                IInputContext iInputContext = (IInputContext) someArgs2.arg2;
                EditorInfo editorInfo = (EditorInfo) someArgs2.arg3;
                CancellationGroup cancellationGroup = (CancellationGroup) someArgs2.arg4;
                someArgs = (SomeArgs) someArgs2.arg5;
                InputConnectionWrapper inputConnectionWrapper = iInputContext != null ? new InputConnectionWrapper(this.mTarget, iInputContext, someArgs.argi3, cancellationGroup) : null;
                editorInfo.makeCompatible(this.mTargetSdkVersion);
                inputMethod.dispatchStartInputWithToken(inputConnectionWrapper, editorInfo, someArgs.argi1 == 1, iBinder, someArgs.argi2 == 1);
                someArgs2.recycle();
                return;
            case 40:
                someArgs = (SomeArgs) message.obj;
                inputMethod.createSession(new InputMethodSessionCallbackWrapper(this.mContext, (InputChannel) someArgs.arg1, (IInputSessionCallback) someArgs.arg2));
                return;
            case 45:
                inputMethod.setSessionEnabled((InputMethodSession) message.obj, message.arg1 != 0);
                return;
            case 50:
                inputMethod.revokeSession((InputMethodSession) message.obj);
                return;
            case 60:
                someArgs = (SomeArgs) message.obj;
                inputMethod.showSoftInputWithToken(message.arg1, (ResultReceiver) someArgs.arg2, (IBinder) someArgs.arg1);
                return;
            case 70:
                someArgs = (SomeArgs) message.obj;
                inputMethod.hideSoftInputWithToken(message.arg1, (ResultReceiver) someArgs.arg2, (IBinder) someArgs.arg1);
                return;
            case 80:
                inputMethod.changeInputMethodSubtype((InputMethodSubtype) message.obj);
                return;
            case 90:
                someArgs = (SomeArgs) message.obj;
                inputMethod.onCreateInlineSuggestionsRequest((InlineSuggestionsRequestInfo) someArgs.arg1, (IInlineSuggestionsRequestCallback) someArgs.arg2);
                return;
            default:
                Log.w(TAG, "Unhandled message code: " + message.what);
                return;
        }
    }

    @Override // android.os.Binder
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        AbstractInputMethodService abstractInputMethodService = this.mTarget.get();
        if (abstractInputMethodService == null) {
            return;
        }
        if (abstractInputMethodService.checkCallingOrSelfPermission(Manifest.permission.DUMP) != 0) {
            printWriter.println("Permission Denial: can't dump InputMethodManager from from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid());
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageOOOO(1, fileDescriptor, printWriter, strArr, countDownLatch));
        try {
            if (countDownLatch.await(5L, TimeUnit.SECONDS)) {
                return;
            }
            printWriter.println("Timeout waiting for dump");
        } catch (InterruptedException unused) {
            printWriter.println("Interrupted waiting for dump");
        }
    }

    @Override // com.android.internal.view.IInputMethod
    public void initializeInternal(IBinder iBinder, int i, IInputMethodPrivilegedOperations iInputMethodPrivilegedOperations) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageIOO(10, i, iBinder, iInputMethodPrivilegedOperations));
    }

    @Override // com.android.internal.view.IInputMethod
    public void onCreateInlineSuggestionsRequest(InlineSuggestionsRequestInfo inlineSuggestionsRequestInfo, IInlineSuggestionsRequestCallback iInlineSuggestionsRequestCallback) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageOO(90, inlineSuggestionsRequestInfo, iInlineSuggestionsRequestCallback));
    }

    @Override // com.android.internal.view.IInputMethod
    public void bindInput(InputBinding inputBinding) {
        if (this.mCancellationGroup != null) {
            Log.e(TAG, "bindInput must be paired with unbindInput.");
        }
        this.mCancellationGroup = new CancellationGroup();
        InputBinding inputBinding2 = new InputBinding(new InputConnectionWrapper(this.mTarget, IInputContext.Stub.asInterface(inputBinding.getConnectionToken()), 0, this.mCancellationGroup), inputBinding);
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageO(20, inputBinding2));
    }

    @Override // com.android.internal.view.IInputMethod
    public void unbindInput() {
        CancellationGroup cancellationGroup = this.mCancellationGroup;
        if (cancellationGroup != null) {
            cancellationGroup.cancelAll();
            this.mCancellationGroup = null;
        } else {
            Log.e(TAG, "unbindInput must be paired with bindInput.");
        }
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessage(30));
    }

    @Override // com.android.internal.view.IInputMethod
    public void startInput(IBinder iBinder, IInputContext iInputContext, int i, EditorInfo editorInfo, boolean z, boolean z2) {
        if (this.mCancellationGroup == null) {
            Log.e(TAG, "startInput must be called after bindInput.");
            this.mCancellationGroup = new CancellationGroup();
        }
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.argi1 = z ? 1 : 0;
        someArgsObtain.argi2 = z2 ? 1 : 0;
        someArgsObtain.argi3 = i;
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageOOOOO(32, iBinder, iInputContext, editorInfo, this.mCancellationGroup, someArgsObtain));
    }

    @Override // com.android.internal.view.IInputMethod
    public void createSession(InputChannel inputChannel, IInputSessionCallback iInputSessionCallback) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageOO(40, inputChannel, iInputSessionCallback));
    }

    @Override // com.android.internal.view.IInputMethod
    public void setSessionEnabled(IInputMethodSession iInputMethodSession, boolean z) {
        try {
            InputMethodSession internalInputMethodSession = ((IInputMethodSessionWrapper) iInputMethodSession).getInternalInputMethodSession();
            if (internalInputMethodSession == null) {
                Log.w(TAG, "Session is already finished: " + iInputMethodSession);
            } else {
                HandlerCaller handlerCaller = this.mCaller;
                handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageIO(45, z ? 1 : 0, internalInputMethodSession));
            }
        } catch (ClassCastException e) {
            Log.w(TAG, "Incoming session not of correct type: " + iInputMethodSession, e);
        }
    }

    @Override // com.android.internal.view.IInputMethod
    public void revokeSession(IInputMethodSession iInputMethodSession) {
        try {
            InputMethodSession internalInputMethodSession = ((IInputMethodSessionWrapper) iInputMethodSession).getInternalInputMethodSession();
            if (internalInputMethodSession == null) {
                Log.w(TAG, "Session is already finished: " + iInputMethodSession);
            } else {
                HandlerCaller handlerCaller = this.mCaller;
                handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageO(50, internalInputMethodSession));
            }
        } catch (ClassCastException e) {
            Log.w(TAG, "Incoming session not of correct type: " + iInputMethodSession, e);
        }
    }

    @Override // com.android.internal.view.IInputMethod
    public void showSoftInput(IBinder iBinder, int i, ResultReceiver resultReceiver) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageIOO(60, i, iBinder, resultReceiver));
    }

    @Override // com.android.internal.view.IInputMethod
    public void hideSoftInput(IBinder iBinder, int i, ResultReceiver resultReceiver) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageIOO(70, i, iBinder, resultReceiver));
    }

    @Override // com.android.internal.view.IInputMethod
    public void changeInputMethodSubtype(InputMethodSubtype inputMethodSubtype) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageO(80, inputMethodSubtype));
    }
}
