package android.view.inputmethod;

import android.Manifest;
import android.app.ActivityThread;
import android.app.Application;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.text.style.SuggestionSpan;
import android.util.Log;
import android.util.Pools;
import android.util.PrintWriterPrinter;
import android.util.SparseArray;
import android.view.ImeFocusController;
import android.view.ImeInsetsSourceConsumer;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventSender;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.autofill.AutofillManager;
import com.android.internal.inputmethod.InputMethodDebug;
import com.android.internal.inputmethod.InputMethodPrivilegedOperationsRegistry;
import com.android.internal.os.SomeArgs;
import com.android.internal.view.IInputConnectionWrapper;
import com.android.internal.view.IInputContext;
import com.android.internal.view.IInputMethodClient;
import com.android.internal.view.IInputMethodManager;
import com.android.internal.view.IInputMethodSession;
import com.android.internal.view.InputBindResult;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
public final class InputMethodManager {
    static final boolean DEBUG = false;
    public static final int DISPATCH_HANDLED = 1;
    public static final int DISPATCH_IN_PROGRESS = -1;
    public static final int DISPATCH_NOT_HANDLED = 0;
    public static final int HIDE_IMPLICIT_ONLY = 1;
    public static final int HIDE_NOT_ALWAYS = 2;
    static final long INPUT_METHOD_NOT_RESPONDING_TIMEOUT = 2500;
    static final int MSG_APPLY_IME_VISIBILITY = 20;
    static final int MSG_BIND = 2;
    static final int MSG_DUMP = 1;
    static final int MSG_FLUSH_INPUT_EVENT = 7;
    static final int MSG_REPORT_FULLSCREEN_MODE = 10;
    static final int MSG_REPORT_PRE_RENDERED = 15;
    static final int MSG_SEND_INPUT_EVENT = 5;
    static final int MSG_SET_ACTIVE = 4;
    static final int MSG_TIMEOUT_INPUT_EVENT = 6;
    static final int MSG_UNBIND = 3;
    static final int MSG_UPDATE_ACTIVITY_VIEW_TO_SCREEN_MATRIX = 30;
    private static final int NOT_A_SUBTYPE_ID = -1;
    static final String PENDING_EVENT_COUNTER = "aq:imm";
    private static final int REQUEST_UPDATE_CURSOR_ANCHOR_INFO_NONE = 0;
    public static final int RESULT_HIDDEN = 3;
    public static final int RESULT_SHOWN = 2;
    public static final int RESULT_UNCHANGED_HIDDEN = 1;
    public static final int RESULT_UNCHANGED_SHOWN = 0;
    public static final int SHOW_FORCED = 2;
    public static final int SHOW_IMPLICIT = 1;
    public static final int SHOW_IM_PICKER_MODE_AUTO = 0;
    public static final int SHOW_IM_PICKER_MODE_EXCLUDE_AUXILIARY_SUBTYPES = 2;
    public static final int SHOW_IM_PICKER_MODE_INCLUDE_AUXILIARY_SUBTYPES = 1;
    private static final String SUBTYPE_MODE_VOICE = "voice";
    static final String TAG = "InputMethodManager";

    @Deprecated
    static InputMethodManager sInstance;
    CompletionInfo[] mCompletions;
    InputChannel mCurChannel;
    String mCurId;
    IInputMethodSession mCurMethod;
    ViewRootImpl mCurRootView;
    ImeInputEventSender mCurSender;
    EditorInfo mCurrentTextBoxAttribute;
    int mCursorCandEnd;
    int mCursorCandStart;
    int mCursorSelEnd;
    int mCursorSelStart;
    private final int mDisplayId;
    boolean mFullscreenMode;
    final H mH;
    final IInputContext mIInputContext;
    private ImeInsetsSourceConsumer mImeInsetsConsumer;
    final Looper mMainLooper;
    boolean mServedConnecting;
    ControlledInputConnectionWrapper mServedInputConnectionWrapper;
    final IInputMethodManager mService;
    private static final Object sLock = new Object();
    private static final SparseArray<InputMethodManager> sInstanceMap = new SparseArray<>();
    boolean mActive = false;
    private boolean mRestartOnNextWindowFocus = true;
    Rect mTmpCursorRect = new Rect();
    Rect mCursorRect = new Rect();
    private CursorAnchorInfo mCursorAnchorInfo = null;
    private Matrix mActivityViewToScreenMatrix = null;
    int mBindSequence = -1;
    private int mRequestUpdateCursorAnchorInfoMonitorMode = 0;
    final Pools.Pool<PendingEvent> mPendingEventPool = new Pools.SimplePool(20);
    final SparseArray<PendingEvent> mPendingEvents = new SparseArray<>(20);
    final DelegateImpl mDelegate = new DelegateImpl();
    final IInputMethodClient.Stub mClient = new IInputMethodClient.Stub() { // from class: android.view.inputmethod.InputMethodManager.1
        @Override // android.os.Binder
        protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = fileDescriptor;
            someArgsObtain.arg2 = printWriter;
            someArgsObtain.arg3 = strArr;
            someArgsObtain.arg4 = countDownLatch;
            InputMethodManager.this.mH.sendMessage(InputMethodManager.this.mH.obtainMessage(1, someArgsObtain));
            try {
                if (countDownLatch.await(5L, TimeUnit.SECONDS)) {
                    return;
                }
                printWriter.println("Timeout waiting for dump");
            } catch (InterruptedException unused) {
                printWriter.println("Interrupted waiting for dump");
            }
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void onBindMethod(InputBindResult inputBindResult) {
            InputMethodManager.this.mH.obtainMessage(2, inputBindResult).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void onUnbindMethod(int i, int i2) {
            InputMethodManager.this.mH.obtainMessage(3, i, i2).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void setActive(boolean z, boolean z2) {
            InputMethodManager.this.mH.obtainMessage(4, z ? 1 : 0, z2 ? 1 : 0).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void scheduleStartInputIfNecessary(boolean z) {
            InputMethodManager.this.mH.obtainMessage(4, 0, z ? 1 : 0).sendToTarget();
            InputMethodManager.this.mH.obtainMessage(4, 1, z ? 1 : 0).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void reportFullscreenMode(boolean z) {
            InputMethodManager.this.mH.obtainMessage(10, z ? 1 : 0, 0).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void reportPreRendered(EditorInfo editorInfo) {
            InputMethodManager.this.mH.obtainMessage(15, 0, 0, editorInfo).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void applyImeVisibility(boolean z) {
            InputMethodManager.this.mH.obtainMessage(20, z ? 1 : 0, 0).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void updateActivityViewToScreenMatrix(int i, float[] fArr) {
            InputMethodManager.this.mH.obtainMessage(30, i, 0, fArr).sendToTarget();
        }
    };
    final InputConnection mDummyInputConnection = new BaseInputConnection(this, false);

    public interface FinishedInputEventCallback {
        void onFinishedInputEvent(Object obj, boolean z);
    }

    private static boolean isInEditMode() {
        return false;
    }

    @Deprecated
    public boolean isWatchingCursor(View view) {
        return false;
    }

    @Deprecated
    public void windowDismissed(IBinder iBinder) {
    }

    public static void ensureDefaultInstanceForDefaultDisplayIfNecessary() {
        forContextInternal(0, Looper.getMainLooper());
    }

    private static boolean isAutofillUIShowing(View view) {
        AutofillManager autofillManager = (AutofillManager) view.getContext().getSystemService(AutofillManager.class);
        return autofillManager != null && autofillManager.isAutofillUiShowing();
    }

    private InputMethodManager getFallbackInputMethodManagerIfNecessary(View view) {
        ViewRootImpl viewRootImpl;
        int displayId;
        if (view == null || (viewRootImpl = view.getViewRootImpl()) == null || (displayId = viewRootImpl.getDisplayId()) == this.mDisplayId) {
            return null;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) viewRootImpl.mContext.getSystemService(InputMethodManager.class);
        if (inputMethodManager == null) {
            Log.v(TAG, "b/117267690: Failed to get non-null fallback IMM. view=" + view);
            return null;
        }
        if (inputMethodManager.mDisplayId != displayId) {
            Log.v(TAG, "b/117267690: Failed to get fallback IMM with expected displayId=" + displayId + " actual IMM#displayId=" + inputMethodManager.mDisplayId + " view=" + view);
            return null;
        }
        Log.v(TAG, "b/117267690: Display ID mismatch found. ViewRootImpl displayId=" + displayId + " InputMethodManager displayId=" + this.mDisplayId + ". Use the right InputMethodManager instance to avoid performance overhead.", new Throwable());
        return inputMethodManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean canStartInput(View view) {
        return view.hasWindowFocus() || isAutofillUIShowing(view);
    }

    public void reportPerceptible(IBinder iBinder, boolean z) {
        try {
            this.mService.reportPerceptible(iBinder, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final class DelegateImpl implements ImeFocusController.InputMethodManagerDelegate {
        private DelegateImpl() {
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public boolean startInput(int i, View view, int i2, int i3, int i4) {
            final View servedViewLocked;
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.mCurrentTextBoxAttribute = null;
                InputMethodManager.this.mCompletions = null;
                InputMethodManager.this.mServedConnecting = true;
                servedViewLocked = InputMethodManager.this.getServedViewLocked();
            }
            if (servedViewLocked != null && servedViewLocked.getHandler() != null) {
                servedViewLocked.getHandler().post(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InputMethodManager$DelegateImpl$r2X8PLo_YIORJTYJGDfinf_IvK4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$startInput$0$InputMethodManager$DelegateImpl(servedViewLocked);
                    }
                });
            }
            return InputMethodManager.this.startInputInner(i, view != null ? view.getWindowToken() : null, i2, i3, i4);
        }

        public /* synthetic */ void lambda$startInput$0$InputMethodManager$DelegateImpl(View view) {
            if (view.onCheckIsTextEditor()) {
                return;
            }
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.maybeCallServedViewChangedLocked(null);
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void finishInput() {
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.finishInputLocked();
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void closeCurrentIme() {
            InputMethodManager.this.closeCurrentInput();
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void startInputAsyncOnWindowFocusGain(View view, int i, int i2, boolean z) {
            boolean z2 = false;
            int startInputFlags = InputMethodManager.this.getStartInputFlags(view, 0) | 8;
            ImeFocusController focusController = InputMethodManager.this.getFocusController();
            if (focusController == null) {
                return;
            }
            if (focusController.checkFocus(z, false) && startInput(1, view, startInputFlags, i, i2)) {
                return;
            }
            synchronized (InputMethodManager.this.mH) {
                try {
                    try {
                        View servedView = focusController.getServedView();
                        if (servedView != null && servedView == view && hasActiveConnection(view)) {
                            z2 = true;
                        }
                        InputMethodManager.this.mService.startInputOrWindowGainedFocus(z2 ? 2 : 3, InputMethodManager.this.mClient, view.getWindowToken(), startInputFlags, i, i2, null, null, 0, InputMethodManager.this.mCurRootView.mContext.getApplicationInfo().targetSdkVersion);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void finishComposingText() {
            if (InputMethodManager.this.mServedInputConnectionWrapper != null) {
                InputMethodManager.this.mServedInputConnectionWrapper.finishComposingText();
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void setCurrentRootView(ViewRootImpl viewRootImpl) {
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.mCurRootView = viewRootImpl;
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public boolean isCurrentRootView(ViewRootImpl viewRootImpl) {
            boolean z;
            synchronized (InputMethodManager.this.mH) {
                z = InputMethodManager.this.mCurRootView == viewRootImpl;
            }
            return z;
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public boolean isRestartOnNextWindowFocus(boolean z) {
            boolean z2 = InputMethodManager.this.mRestartOnNextWindowFocus;
            if (z) {
                InputMethodManager.this.mRestartOnNextWindowFocus = false;
            }
            return z2;
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public boolean hasActiveConnection(View view) {
            synchronized (InputMethodManager.this.mH) {
                boolean z = false;
                if (!InputMethodManager.this.hasServedByInputMethodLocked(view)) {
                    return false;
                }
                if (InputMethodManager.this.mServedInputConnectionWrapper != null && InputMethodManager.this.mServedInputConnectionWrapper.isActive() && InputMethodManager.this.mServedInputConnectionWrapper.mServedView.get() == view) {
                    z = true;
                }
                return z;
            }
        }
    }

    public DelegateImpl getDelegate() {
        return this.mDelegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getServedViewLocked() {
        ViewRootImpl viewRootImpl = this.mCurRootView;
        if (viewRootImpl != null) {
            return viewRootImpl.getImeFocusController().getServedView();
        }
        return null;
    }

    private View getNextServedViewLocked() {
        ViewRootImpl viewRootImpl = this.mCurRootView;
        if (viewRootImpl != null) {
            return viewRootImpl.getImeFocusController().getNextServedView();
        }
        return null;
    }

    private void setServedViewLocked(View view) {
        ViewRootImpl viewRootImpl = this.mCurRootView;
        if (viewRootImpl != null) {
            viewRootImpl.getImeFocusController().setServedView(view);
        }
    }

    private void setNextServedViewLocked(View view) {
        ViewRootImpl viewRootImpl = this.mCurRootView;
        if (viewRootImpl != null) {
            viewRootImpl.getImeFocusController().setNextServedView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImeFocusController getFocusController() {
        synchronized (this.mH) {
            if (this.mCurRootView == null) {
                return null;
            }
            return this.mCurRootView.getImeFocusController();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasServedByInputMethodLocked(View view) {
        View servedViewLocked = getServedViewLocked();
        return servedViewLocked == view || (servedViewLocked != null && servedViewLocked.checkInputConnectionProxy(view));
    }

    class H extends Handler {
        H(Looper looper) {
            super(looper, null, true);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            InputConnection inputConnection;
            int i = message.what;
            if (i == 10) {
                z = message.arg1 != 0;
                synchronized (InputMethodManager.this.mH) {
                    InputMethodManager.this.mFullscreenMode = z;
                    inputConnection = InputMethodManager.this.mServedInputConnectionWrapper != null ? InputMethodManager.this.mServedInputConnectionWrapper.getInputConnection() : null;
                }
                if (inputConnection != null) {
                    inputConnection.reportFullscreenMode(z);
                    return;
                }
                return;
            }
            if (i == 15) {
                synchronized (InputMethodManager.this.mH) {
                    if (InputMethodManager.this.mImeInsetsConsumer != null) {
                        InputMethodManager.this.mImeInsetsConsumer.onPreRendered((EditorInfo) message.obj);
                    }
                }
                return;
            }
            if (i == 20) {
                synchronized (InputMethodManager.this.mH) {
                    if (InputMethodManager.this.mImeInsetsConsumer != null) {
                        ImeInsetsSourceConsumer imeInsetsSourceConsumer = InputMethodManager.this.mImeInsetsConsumer;
                        if (message.arg1 == 0) {
                            z = false;
                        }
                        imeInsetsSourceConsumer.applyImeVisibility(z);
                    }
                }
                return;
            }
            if (i != 30) {
                switch (i) {
                    case 1:
                        SomeArgs someArgs = (SomeArgs) message.obj;
                        try {
                            InputMethodManager.this.doDump((FileDescriptor) someArgs.arg1, (PrintWriter) someArgs.arg2, (String[]) someArgs.arg3);
                            break;
                        } catch (RuntimeException e) {
                            ((PrintWriter) someArgs.arg2).println("Exception: " + e);
                        }
                        synchronized (someArgs.arg4) {
                            ((CountDownLatch) someArgs.arg4).countDown();
                            break;
                        }
                        someArgs.recycle();
                        return;
                    case 2:
                        InputBindResult inputBindResult = (InputBindResult) message.obj;
                        synchronized (InputMethodManager.this.mH) {
                            if (InputMethodManager.this.mBindSequence >= 0 && InputMethodManager.this.mBindSequence == inputBindResult.sequence) {
                                InputMethodManager.this.mRequestUpdateCursorAnchorInfoMonitorMode = 0;
                                InputMethodManager.this.setInputChannelLocked(inputBindResult.channel);
                                InputMethodManager.this.mCurMethod = inputBindResult.method;
                                InputMethodManager.this.mCurId = inputBindResult.id;
                                InputMethodManager.this.mBindSequence = inputBindResult.sequence;
                                InputMethodManager.this.mActivityViewToScreenMatrix = inputBindResult.getActivityViewToScreenMatrix();
                                InputMethodManager.this.startInputInner(6, null, 0, 0, 0);
                                return;
                            }
                            Log.w(InputMethodManager.TAG, "Ignoring onBind: cur seq=" + InputMethodManager.this.mBindSequence + ", given seq=" + inputBindResult.sequence);
                            if (inputBindResult.channel != null && inputBindResult.channel != InputMethodManager.this.mCurChannel) {
                                inputBindResult.channel.dispose();
                            }
                            return;
                        }
                    case 3:
                        int i2 = message.arg1;
                        int i3 = message.arg2;
                        synchronized (InputMethodManager.this.mH) {
                            if (InputMethodManager.this.mBindSequence != i2) {
                                return;
                            }
                            InputMethodManager.this.clearBindingLocked();
                            View servedViewLocked = InputMethodManager.this.getServedViewLocked();
                            if (servedViewLocked != null && servedViewLocked.isFocused()) {
                                InputMethodManager.this.mServedConnecting = true;
                            }
                            boolean z = InputMethodManager.this.mActive;
                            if (z) {
                                InputMethodManager.this.startInputInner(7, null, 0, 0, 0);
                                return;
                            }
                            return;
                        }
                    case 4:
                        boolean z2 = message.arg1 != 0;
                        boolean z3 = message.arg2 != 0;
                        synchronized (InputMethodManager.this.mH) {
                            InputMethodManager.this.mActive = z2;
                            InputMethodManager.this.mFullscreenMode = z3;
                            if (!z2) {
                                InputMethodManager.this.mRestartOnNextWindowFocus = true;
                                try {
                                    InputMethodManager.this.mIInputContext.finishComposingText();
                                    break;
                                } catch (RemoteException unused) {
                                }
                            }
                            View servedViewLocked2 = InputMethodManager.this.getServedViewLocked();
                            if (servedViewLocked2 != null && InputMethodManager.canStartInput(servedViewLocked2) && InputMethodManager.this.mCurRootView != null && InputMethodManager.this.mCurRootView.getImeFocusController().checkFocus(InputMethodManager.this.mRestartOnNextWindowFocus, false)) {
                                InputMethodManager.this.mDelegate.startInput(z2 ? 8 : 9, null, 0, 0, 0);
                            }
                            break;
                        }
                        return;
                    case 5:
                        InputMethodManager.this.sendInputEventAndReportResultOnMainLooper((PendingEvent) message.obj);
                        return;
                    case 6:
                        InputMethodManager.this.finishedInputEvent(message.arg1, false, true);
                        return;
                    case 7:
                        InputMethodManager.this.finishedInputEvent(message.arg1, false, false);
                        return;
                    default:
                        return;
                }
            }
            float[] fArr = (float[]) message.obj;
            int i4 = message.arg1;
            synchronized (InputMethodManager.this.mH) {
                if (InputMethodManager.this.mBindSequence != i4) {
                    return;
                }
                if (fArr != null && InputMethodManager.this.mActivityViewToScreenMatrix != null) {
                    float[] fArr2 = new float[9];
                    InputMethodManager.this.mActivityViewToScreenMatrix.getValues(fArr2);
                    if (Arrays.equals(fArr2, fArr)) {
                        return;
                    }
                    InputMethodManager.this.mActivityViewToScreenMatrix.setValues(fArr);
                    if (InputMethodManager.this.mCursorAnchorInfo != null && InputMethodManager.this.mCurMethod != null && InputMethodManager.this.mServedInputConnectionWrapper != null) {
                        if ((InputMethodManager.this.mRequestUpdateCursorAnchorInfoMonitorMode & 2) == 0) {
                            z = false;
                        }
                        if (z) {
                            try {
                                InputMethodManager.this.mCurMethod.updateCursorAnchorInfo(CursorAnchorInfo.createForAdditionalParentMatrix(InputMethodManager.this.mCursorAnchorInfo, InputMethodManager.this.mActivityViewToScreenMatrix));
                            } catch (RemoteException e2) {
                                Log.w(InputMethodManager.TAG, "IME died: " + InputMethodManager.this.mCurId, e2);
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                InputMethodManager.this.mActivityViewToScreenMatrix = null;
            }
        }
    }

    private static class ControlledInputConnectionWrapper extends IInputConnectionWrapper {
        private final InputMethodManager mParentInputMethodManager;
        private final WeakReference<View> mServedView;

        ControlledInputConnectionWrapper(Looper looper, InputConnection inputConnection, InputMethodManager inputMethodManager, View view) {
            super(looper, inputConnection);
            this.mParentInputMethodManager = inputMethodManager;
            this.mServedView = new WeakReference<>(view);
        }

        @Override // com.android.internal.view.IInputConnectionWrapper
        public boolean isActive() {
            return this.mParentInputMethodManager.mActive && !isFinished();
        }

        void deactivate() {
            if (isFinished()) {
                return;
            }
            closeConnection();
        }

        public String toString() {
            return "ControlledInputConnectionWrapper{connection=" + getInputConnection() + " finished=" + isFinished() + " mParentInputMethodManager.mActive=" + this.mParentInputMethodManager.mActive + " mServedView=" + this.mServedView.get() + "}";
        }
    }

    private static class ImeThreadFactory implements ThreadFactory {
        private final String mThreadName;

        ImeThreadFactory(String str) {
            this.mThreadName = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.mThreadName);
        }
    }

    static void tearDownEditMode() {
        if (!isInEditMode()) {
            throw new UnsupportedOperationException("This method must be called only from layoutlib");
        }
        synchronized (sLock) {
            sInstance = null;
        }
    }

    private static InputMethodManager createInstance(int i, Looper looper) {
        return isInEditMode() ? createStubInstance(i, looper) : createRealInstance(i, looper);
    }

    private static InputMethodManager createRealInstance(int i, Looper looper) {
        try {
            IInputMethodManager iInputMethodManagerAsInterface = IInputMethodManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.INPUT_METHOD_SERVICE));
            InputMethodManager inputMethodManager = new InputMethodManager(iInputMethodManagerAsInterface, i, looper);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                try {
                    iInputMethodManagerAsInterface.addClient(inputMethodManager.mClient, inputMethodManager.mIInputContext, i);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
            return inputMethodManager;
        } catch (ServiceManager.ServiceNotFoundException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private static InputMethodManager createStubInstance(int i, Looper looper) {
        return new InputMethodManager((IInputMethodManager) Proxy.newProxyInstance(IInputMethodManager.class.getClassLoader(), new Class[]{IInputMethodManager.class}, new InvocationHandler() { // from class: android.view.inputmethod.-$$Lambda$InputMethodManager$iDWn3IGSUFqIcs8Py42UhfrshxI
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                return InputMethodManager.lambda$createStubInstance$0(obj, method, objArr);
            }
        }), i, looper);
    }

    static /* synthetic */ Object lambda$createStubInstance$0(Object obj, Method method, Object[] objArr) throws Throwable {
        Class<?> returnType = method.getReturnType();
        if (returnType == Boolean.TYPE) {
            return false;
        }
        if (returnType == Integer.TYPE) {
            return 0;
        }
        if (returnType == Long.TYPE) {
            return 0L;
        }
        if (returnType == Short.TYPE || returnType == Character.TYPE || returnType == Byte.TYPE) {
            return 0;
        }
        if (returnType == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (returnType == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        return null;
    }

    private InputMethodManager(IInputMethodManager iInputMethodManager, int i, Looper looper) {
        this.mService = iInputMethodManager;
        this.mMainLooper = looper;
        this.mH = new H(looper);
        this.mDisplayId = i;
        this.mIInputContext = new ControlledInputConnectionWrapper(looper, this.mDummyInputConnection, this, null);
    }

    public static InputMethodManager forContext(Context context) {
        int displayId = context.getDisplayId();
        return forContextInternal(displayId, displayId == 0 ? Looper.getMainLooper() : context.getMainLooper());
    }

    private static InputMethodManager forContextInternal(int i, Looper looper) {
        boolean z = i == 0;
        synchronized (sLock) {
            InputMethodManager inputMethodManager = sInstanceMap.get(i);
            if (inputMethodManager != null) {
                return inputMethodManager;
            }
            InputMethodManager inputMethodManagerCreateInstance = createInstance(i, looper);
            if (sInstance == null && z) {
                sInstance = inputMethodManagerCreateInstance;
            }
            sInstanceMap.put(i, inputMethodManagerCreateInstance);
            return inputMethodManagerCreateInstance;
        }
    }

    @Deprecated
    public static InputMethodManager getInstance() {
        Log.w(TAG, "InputMethodManager.getInstance() is deprecated because it cannot be compatible with multi-display. Use context.getSystemService(InputMethodManager.class) instead.", new Throwable());
        ensureDefaultInstanceForDefaultDisplayIfNecessary();
        return peekInstance();
    }

    @Deprecated
    public static InputMethodManager peekInstance() {
        InputMethodManager inputMethodManager;
        Log.w(TAG, "InputMethodManager.peekInstance() is deprecated because it cannot be compatible with multi-display. Use context.getSystemService(InputMethodManager.class) instead.", new Throwable());
        synchronized (sLock) {
            inputMethodManager = sInstance;
        }
        return inputMethodManager;
    }

    public IInputMethodClient getClient() {
        return this.mClient;
    }

    public IInputContext getInputContext() {
        return this.mIInputContext;
    }

    public List<InputMethodInfo> getInputMethodList() {
        try {
            return this.mService.getInputMethodList(UserHandle.myUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<InputMethodInfo> getInputMethodListAsUser(int i) {
        try {
            return this.mService.getInputMethodList(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<InputMethodInfo> getEnabledInputMethodList() {
        try {
            return this.mService.getEnabledInputMethodList(UserHandle.myUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<InputMethodInfo> getEnabledInputMethodListAsUser(int i) {
        try {
            return this.mService.getEnabledInputMethodList(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<InputMethodSubtype> getEnabledInputMethodSubtypeList(InputMethodInfo inputMethodInfo, boolean z) {
        try {
            return this.mService.getEnabledInputMethodSubtypeList(inputMethodInfo == null ? null : inputMethodInfo.getId(), z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void showStatusIcon(IBinder iBinder, String str, int i) {
        InputMethodPrivilegedOperationsRegistry.get(iBinder).updateStatusIcon(str, i);
    }

    @Deprecated
    public void hideStatusIcon(IBinder iBinder) {
        InputMethodPrivilegedOperationsRegistry.get(iBinder).updateStatusIcon(null, 0);
    }

    @Deprecated
    public void registerSuggestionSpansForNotification(SuggestionSpan[] suggestionSpanArr) {
        Log.w(TAG, "registerSuggestionSpansForNotification() is deprecated.  Does nothing.");
    }

    @Deprecated
    public void notifySuggestionPicked(SuggestionSpan suggestionSpan, String str, int i) {
        Log.w(TAG, "notifySuggestionPicked() is deprecated.  Does nothing.");
    }

    public boolean isFullscreenMode() {
        boolean z;
        synchronized (this.mH) {
            z = this.mFullscreenMode;
        }
        return z;
    }

    public boolean isActive(View view) {
        boolean z;
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            return fallbackInputMethodManagerIfNecessary.isActive(view);
        }
        checkFocus();
        synchronized (this.mH) {
            z = hasServedByInputMethodLocked(view) && this.mCurrentTextBoxAttribute != null;
        }
        return z;
    }

    public boolean isActive() {
        boolean z;
        checkFocus();
        synchronized (this.mH) {
            z = (getServedViewLocked() == null || this.mCurrentTextBoxAttribute == null) ? false : true;
        }
        return z;
    }

    public boolean isAcceptingText() {
        checkFocus();
        ControlledInputConnectionWrapper controlledInputConnectionWrapper = this.mServedInputConnectionWrapper;
        return (controlledInputConnectionWrapper == null || controlledInputConnectionWrapper.getInputConnection() == null) ? false : true;
    }

    void clearBindingLocked() {
        clearConnectionLocked();
        setInputChannelLocked(null);
        this.mBindSequence = -1;
        this.mCurId = null;
        this.mCurMethod = null;
    }

    void setInputChannelLocked(InputChannel inputChannel) {
        if (this.mCurChannel != inputChannel) {
            if (this.mCurSender != null) {
                flushPendingEventsLocked();
                this.mCurSender.dispose();
                this.mCurSender = null;
            }
            InputChannel inputChannel2 = this.mCurChannel;
            if (inputChannel2 != null) {
                inputChannel2.dispose();
            }
            this.mCurChannel = inputChannel;
        }
    }

    void clearConnectionLocked() {
        this.mCurrentTextBoxAttribute = null;
        ControlledInputConnectionWrapper controlledInputConnectionWrapper = this.mServedInputConnectionWrapper;
        if (controlledInputConnectionWrapper != null) {
            controlledInputConnectionWrapper.deactivate();
            this.mServedInputConnectionWrapper = null;
        }
    }

    void finishInputLocked() {
        this.mActivityViewToScreenMatrix = null;
        setNextServedViewLocked(null);
        if (getServedViewLocked() != null) {
            setServedViewLocked(null);
            this.mCompletions = null;
            this.mServedConnecting = false;
            clearConnectionLocked();
        }
    }

    public void displayCompletions(View view, CompletionInfo[] completionInfoArr) {
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.displayCompletions(view, completionInfoArr);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view)) {
                this.mCompletions = completionInfoArr;
                if (this.mCurMethod != null) {
                    try {
                        this.mCurMethod.displayCompletions(completionInfoArr);
                    } catch (RemoteException unused) {
                    }
                }
            }
        }
    }

    public void updateExtractedText(View view, int i, ExtractedText extractedText) {
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.updateExtractedText(view, i, extractedText);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view)) {
                if (this.mCurMethod != null) {
                    try {
                        this.mCurMethod.updateExtractedText(i, extractedText);
                    } catch (RemoteException unused) {
                    }
                }
            }
        }
    }

    public boolean showSoftInput(View view, int i) {
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            return fallbackInputMethodManagerIfNecessary.showSoftInput(view, i);
        }
        return showSoftInput(view, i, null);
    }

    public boolean showSoftInput(View view, int i, ResultReceiver resultReceiver) {
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            return fallbackInputMethodManagerIfNecessary.showSoftInput(view, i, resultReceiver);
        }
        checkFocus();
        synchronized (this.mH) {
            if (!hasServedByInputMethodLocked(view)) {
                return false;
            }
            try {
                return this.mService.showSoftInput(this.mClient, view.getWindowToken(), i, resultReceiver);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @Deprecated
    public void showSoftInputUnchecked(int i, ResultReceiver resultReceiver) {
        synchronized (this.mH) {
            try {
                try {
                    Log.w(TAG, "showSoftInputUnchecked() is a hidden method, which will be removed soon. If you are using android.support.v7.widget.SearchView, please update to version 26.0 or newer version.");
                    if (this.mCurRootView != null && this.mCurRootView.getView() != null) {
                        this.mService.showSoftInput(this.mClient, this.mCurRootView.getView().getWindowToken(), i, resultReceiver);
                        return;
                    }
                    Log.w(TAG, "No current root view, ignoring showSoftInputUnchecked()");
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean hideSoftInputFromWindow(IBinder iBinder, int i) {
        return hideSoftInputFromWindow(iBinder, i, null);
    }

    public boolean hideSoftInputFromWindow(IBinder iBinder, int i, ResultReceiver resultReceiver) {
        checkFocus();
        synchronized (this.mH) {
            View servedViewLocked = getServedViewLocked();
            if (servedViewLocked == null || servedViewLocked.getWindowToken() != iBinder) {
                return false;
            }
            try {
                return this.mService.hideSoftInput(this.mClient, iBinder, i, resultReceiver);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void toggleSoftInputFromWindow(IBinder iBinder, int i, int i2) {
        synchronized (this.mH) {
            View servedViewLocked = getServedViewLocked();
            if (servedViewLocked != null && servedViewLocked.getWindowToken() == iBinder) {
                if (this.mCurMethod != null) {
                    try {
                        this.mCurMethod.toggleSoftInput(i, i2);
                    } catch (RemoteException unused) {
                    }
                }
            }
        }
    }

    public void toggleSoftInput(int i, int i2) {
        IInputMethodSession iInputMethodSession = this.mCurMethod;
        if (iInputMethodSession != null) {
            try {
                iInputMethodSession.toggleSoftInput(i, i2);
            } catch (RemoteException unused) {
            }
        }
    }

    public void restartInput(View view) {
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.restartInput(view);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view)) {
                this.mServedConnecting = true;
                startInputInner(4, null, 0, 0, 0);
            }
        }
    }

    boolean startInputInner(final int i, IBinder iBinder, int i2, int i3, int i4) throws Throwable {
        int startInputFlags;
        IBinder iBinder2;
        int i5;
        int i6;
        int i7;
        ControlledInputConnectionWrapper controlledInputConnectionWrapper;
        boolean z;
        H h;
        InputBindResult inputBindResultStartInputOrWindowGainedFocus;
        synchronized (this.mH) {
            View servedViewLocked = getServedViewLocked();
            if (servedViewLocked == null) {
                return false;
            }
            if (iBinder == null) {
                IBinder windowToken = servedViewLocked.getWindowToken();
                if (windowToken == null) {
                    Log.e(TAG, "ABORT input: ServedView must be attached to a Window");
                    return false;
                }
                startInputFlags = getStartInputFlags(servedViewLocked, i2);
                iBinder2 = windowToken;
                i5 = servedViewLocked.getViewRootImpl().mWindowAttributes.softInputMode;
                i6 = servedViewLocked.getViewRootImpl().mWindowAttributes.flags;
            } else {
                startInputFlags = i2;
                iBinder2 = iBinder;
                i5 = i3;
                i6 = i4;
            }
            Handler handler = servedViewLocked.getHandler();
            if (handler == null) {
                closeCurrentInput();
                return false;
            }
            if (handler.getLooper() != Looper.myLooper()) {
                handler.post(new Runnable() { // from class: android.view.inputmethod.-$$Lambda$InputMethodManager$dfnCauFoZCf-HfXs1QavrkwWDf0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$startInputInner$1$InputMethodManager(i);
                    }
                });
                return false;
            }
            EditorInfo editorInfo = new EditorInfo();
            editorInfo.packageName = servedViewLocked.getContext().getOpPackageName();
            editorInfo.autofillId = servedViewLocked.getAutofillId();
            editorInfo.fieldId = servedViewLocked.getId();
            InputConnection inputConnectionOnCreateInputConnection = servedViewLocked.onCreateInputConnection(editorInfo);
            H h2 = this.mH;
            synchronized (h2) {
                try {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (getServedViewLocked() == servedViewLocked && this.mServedConnecting) {
                        if (this.mCurrentTextBoxAttribute == null) {
                            startInputFlags |= 4;
                        }
                        this.mCurrentTextBoxAttribute = editorInfo;
                        maybeCallServedViewChangedLocked(editorInfo);
                        this.mServedConnecting = false;
                        Handler handler2 = null;
                        if (this.mServedInputConnectionWrapper != null) {
                            this.mServedInputConnectionWrapper.deactivate();
                            this.mServedInputConnectionWrapper = null;
                        }
                        if (inputConnectionOnCreateInputConnection != null) {
                            this.mCursorSelStart = editorInfo.initialSelStart;
                            this.mCursorSelEnd = editorInfo.initialSelEnd;
                            this.mCursorCandStart = -1;
                            this.mCursorCandEnd = -1;
                            this.mCursorRect.setEmpty();
                            this.mCursorAnchorInfo = null;
                            int missingMethodFlags = InputConnectionInspector.getMissingMethodFlags(inputConnectionOnCreateInputConnection);
                            if ((missingMethodFlags & 32) == 0) {
                                handler2 = inputConnectionOnCreateInputConnection.getHandler();
                            }
                            i7 = missingMethodFlags;
                            controlledInputConnectionWrapper = new ControlledInputConnectionWrapper(handler2 != null ? handler2.getLooper() : handler.getLooper(), inputConnectionOnCreateInputConnection, this, servedViewLocked);
                        } else {
                            i7 = 0;
                            controlledInputConnectionWrapper = null;
                        }
                        this.mServedInputConnectionWrapper = controlledInputConnectionWrapper;
                        try {
                            h = h2;
                            try {
                                inputBindResultStartInputOrWindowGainedFocus = this.mService.startInputOrWindowGainedFocus(i, this.mClient, iBinder2, startInputFlags, i5, i6, editorInfo, controlledInputConnectionWrapper, i7, servedViewLocked.getContext().getApplicationInfo().targetSdkVersion);
                            } catch (RemoteException e) {
                                e = e;
                                z = true;
                            }
                        } catch (RemoteException e2) {
                            e = e2;
                            z = true;
                            h = h2;
                        }
                        if (inputBindResultStartInputOrWindowGainedFocus == null) {
                            Log.wtf(TAG, "startInputOrWindowGainedFocus must not return null. startInputReason=" + InputMethodDebug.startInputReasonToString(i) + " editorInfo=" + editorInfo + " startInputFlags=" + InputMethodDebug.startInputFlagsToString(startInputFlags));
                            return false;
                        }
                        this.mActivityViewToScreenMatrix = inputBindResultStartInputOrWindowGainedFocus.getActivityViewToScreenMatrix();
                        if (inputBindResultStartInputOrWindowGainedFocus.id != null) {
                            setInputChannelLocked(inputBindResultStartInputOrWindowGainedFocus.channel);
                            this.mBindSequence = inputBindResultStartInputOrWindowGainedFocus.sequence;
                            this.mCurMethod = inputBindResultStartInputOrWindowGainedFocus.method;
                            this.mCurId = inputBindResultStartInputOrWindowGainedFocus.id;
                        } else if (inputBindResultStartInputOrWindowGainedFocus.channel != null && inputBindResultStartInputOrWindowGainedFocus.channel != this.mCurChannel) {
                            inputBindResultStartInputOrWindowGainedFocus.channel.dispose();
                        }
                        if (inputBindResultStartInputOrWindowGainedFocus.result != 12) {
                            z = true;
                        } else {
                            z = true;
                            try {
                                this.mRestartOnNextWindowFocus = true;
                            } catch (RemoteException e3) {
                                e = e3;
                                Log.w(TAG, "IME died: " + this.mCurId, e);
                            }
                        }
                        if (this.mCurMethod != null) {
                            if (this.mCompletions != null) {
                                try {
                                    this.mCurMethod.displayCompletions(this.mCompletions);
                                } catch (RemoteException unused) {
                                }
                            }
                        }
                        return z;
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        }
    }

    public /* synthetic */ void lambda$startInputInner$1$InputMethodManager(int i) {
        this.mDelegate.startInput(i, null, 0, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getStartInputFlags(View view, int i) {
        int i2 = i | 1;
        return view.onCheckIsTextEditor() ? i2 | 2 : i2;
    }

    public void checkFocus() {
        ImeFocusController focusController = getFocusController();
        if (focusController != null) {
            focusController.checkFocus(false, true);
        }
    }

    void closeCurrentInput() {
        synchronized (this.mH) {
            if (this.mCurRootView == null || this.mCurRootView.getView() == null) {
                Log.w(TAG, "No current root view, ignoring closeCurrentInput()");
                return;
            }
            try {
                this.mService.hideSoftInput(this.mClient, this.mCurRootView.getView().getWindowToken(), 2, null);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void registerImeConsumer(ImeInsetsSourceConsumer imeInsetsSourceConsumer) {
        if (imeInsetsSourceConsumer == null) {
            throw new IllegalStateException("ImeInsetsSourceConsumer cannot be null.");
        }
        synchronized (this.mH) {
            this.mImeInsetsConsumer = imeInsetsSourceConsumer;
        }
    }

    public void unregisterImeConsumer(ImeInsetsSourceConsumer imeInsetsSourceConsumer) {
        if (imeInsetsSourceConsumer == null) {
            throw new IllegalStateException("ImeInsetsSourceConsumer cannot be null.");
        }
        synchronized (this.mH) {
            if (this.mImeInsetsConsumer == imeInsetsSourceConsumer) {
                this.mImeInsetsConsumer = null;
            }
        }
    }

    public boolean requestImeShow(IBinder iBinder) {
        synchronized (this.mH) {
            View servedViewLocked = getServedViewLocked();
            if (servedViewLocked != null && servedViewLocked.getWindowToken() == iBinder) {
                showSoftInput(servedViewLocked, 0, null);
                return true;
            }
            return false;
        }
    }

    public void notifyImeHidden(IBinder iBinder) {
        synchronized (this.mH) {
            try {
                if (this.mCurMethod != null && this.mCurRootView != null && this.mCurRootView.getWindowToken() == iBinder) {
                    this.mCurMethod.notifyImeHidden();
                }
            } catch (RemoteException unused) {
            }
        }
    }

    public void removeImeSurface(IBinder iBinder) {
        synchronized (this.mH) {
            try {
                try {
                    this.mService.removeImeSurfaceFromWindow(iBinder);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void updateSelection(View view, int i, int i2, int i3, int i4) {
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.updateSelection(view, i, i2, i3, i4);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view) && this.mCurrentTextBoxAttribute != null && this.mCurMethod != null) {
                if (this.mCursorSelStart != i || this.mCursorSelEnd != i2 || this.mCursorCandStart != i3 || this.mCursorCandEnd != i4) {
                    try {
                        int i5 = this.mCursorSelStart;
                        int i6 = this.mCursorSelEnd;
                        this.mCursorSelStart = i;
                        this.mCursorSelEnd = i2;
                        this.mCursorCandStart = i3;
                        this.mCursorCandEnd = i4;
                        this.mCurMethod.updateSelection(i5, i6, i, i2, i3, i4);
                    } catch (RemoteException e) {
                        Log.w(TAG, "IME died: " + this.mCurId, e);
                    }
                }
            }
        }
    }

    @Deprecated
    public void viewClicked(View view) {
        View servedViewLocked;
        View nextServedViewLocked;
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.viewClicked(view);
            return;
        }
        synchronized (this.mH) {
            servedViewLocked = getServedViewLocked();
            nextServedViewLocked = getNextServedViewLocked();
        }
        boolean z = servedViewLocked != nextServedViewLocked;
        checkFocus();
        synchronized (this.mH) {
            if (!hasServedByInputMethodLocked(view) || this.mCurrentTextBoxAttribute == null || this.mCurMethod == null) {
                return;
            }
            try {
                this.mCurMethod.viewClicked(z);
            } catch (RemoteException e) {
                Log.w(TAG, "IME died: " + this.mCurId, e);
            }
        }
    }

    public boolean isCursorAnchorInfoEnabled() {
        boolean z;
        synchronized (this.mH) {
            z = true;
            boolean z2 = (this.mRequestUpdateCursorAnchorInfoMonitorMode & 1) != 0;
            boolean z3 = (this.mRequestUpdateCursorAnchorInfoMonitorMode & 2) != 0;
            if (!z2 && !z3) {
                z = false;
            }
        }
        return z;
    }

    public void setUpdateCursorAnchorInfoMode(int i) {
        synchronized (this.mH) {
            this.mRequestUpdateCursorAnchorInfoMonitorMode = i;
        }
    }

    @Deprecated
    public void updateCursor(View view, int i, int i2, int i3, int i4) {
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.updateCursor(view, i, i2, i3, i4);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view) && this.mCurrentTextBoxAttribute != null && this.mCurMethod != null) {
                this.mTmpCursorRect.set(i, i2, i3, i4);
                if (!this.mCursorRect.equals(this.mTmpCursorRect)) {
                    try {
                        this.mCurMethod.updateCursor(this.mTmpCursorRect);
                        this.mCursorRect.set(this.mTmpCursorRect);
                    } catch (RemoteException e) {
                        Log.w(TAG, "IME died: " + this.mCurId, e);
                    }
                }
            }
        }
    }

    public void updateCursorAnchorInfo(View view, CursorAnchorInfo cursorAnchorInfo) {
        if (view == null || cursorAnchorInfo == null) {
            return;
        }
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.updateCursorAnchorInfo(view, cursorAnchorInfo);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view) && this.mCurrentTextBoxAttribute != null && this.mCurMethod != null) {
                boolean z = true;
                if ((this.mRequestUpdateCursorAnchorInfoMonitorMode & 1) == 0) {
                    z = false;
                }
                if (z || !Objects.equals(this.mCursorAnchorInfo, cursorAnchorInfo)) {
                    try {
                        if (this.mActivityViewToScreenMatrix != null) {
                            this.mCurMethod.updateCursorAnchorInfo(CursorAnchorInfo.createForAdditionalParentMatrix(cursorAnchorInfo, this.mActivityViewToScreenMatrix));
                        } else {
                            this.mCurMethod.updateCursorAnchorInfo(cursorAnchorInfo);
                        }
                        this.mCursorAnchorInfo = cursorAnchorInfo;
                        this.mRequestUpdateCursorAnchorInfoMonitorMode &= -2;
                    } catch (RemoteException e) {
                        Log.w(TAG, "IME died: " + this.mCurId, e);
                    }
                }
            }
        }
    }

    public void sendAppPrivateCommand(View view, String str, Bundle bundle) {
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.sendAppPrivateCommand(view, str, bundle);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (!hasServedByInputMethodLocked(view) || this.mCurrentTextBoxAttribute == null || this.mCurMethod == null) {
                return;
            }
            try {
                this.mCurMethod.appPrivateCommand(str, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "IME died: " + this.mCurId, e);
            }
        }
    }

    @Deprecated
    public void setInputMethod(IBinder iBinder, String str) {
        if (iBinder != null) {
            InputMethodPrivilegedOperationsRegistry.get(iBinder).setInputMethod(str);
            return;
        }
        if (str == null) {
            return;
        }
        if (Process.myUid() == 1000) {
            Log.w(TAG, "System process should not be calling setInputMethod() because almost always it is a bug under multi-user / multi-profile environment. Consider interacting with InputMethodManagerService directly via LocalServices.");
            return;
        }
        Application applicationCurrentApplication = ActivityThread.currentApplication();
        if (applicationCurrentApplication != null && applicationCurrentApplication.checkSelfPermission(Manifest.permission.WRITE_SECURE_SETTINGS) == 0) {
            List<InputMethodInfo> enabledInputMethodList = getEnabledInputMethodList();
            int size = enabledInputMethodList.size();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                if (str.equals(enabledInputMethodList.get(i).getId())) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                Log.e(TAG, "Ignoring setInputMethod(null, " + str + ") because the specified id not found in enabled IMEs.");
                return;
            }
            Log.w(TAG, "The undocumented behavior that setInputMethod() accepts null token when the caller has WRITE_SECURE_SETTINGS is deprecated. This behavior may be completely removed in a future version.  Update secure settings directly instead.");
            ContentResolver contentResolver = applicationCurrentApplication.getContentResolver();
            Settings.Secure.putInt(contentResolver, Settings.Secure.SELECTED_INPUT_METHOD_SUBTYPE, -1);
            Settings.Secure.putString(contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD, str);
        }
    }

    @Deprecated
    public void setInputMethodAndSubtype(IBinder iBinder, String str, InputMethodSubtype inputMethodSubtype) {
        if (iBinder == null) {
            Log.e(TAG, "setInputMethodAndSubtype() does not accept null token on Android Q and later.");
        } else {
            InputMethodPrivilegedOperationsRegistry.get(iBinder).setInputMethodAndSubtype(str, inputMethodSubtype);
        }
    }

    @Deprecated
    public void hideSoftInputFromInputMethod(IBinder iBinder, int i) {
        InputMethodPrivilegedOperationsRegistry.get(iBinder).hideMySoftInput(i);
    }

    @Deprecated
    public void showSoftInputFromInputMethod(IBinder iBinder, int i) {
        InputMethodPrivilegedOperationsRegistry.get(iBinder).showMySoftInput(i);
    }

    public int dispatchInputEvent(InputEvent inputEvent, Object obj, FinishedInputEventCallback finishedInputEventCallback, Handler handler) {
        synchronized (this.mH) {
            if (this.mCurMethod == null) {
                return 0;
            }
            if (inputEvent instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) inputEvent;
                if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 63 && keyEvent.getRepeatCount() == 0) {
                    showInputMethodPickerLocked();
                    return 1;
                }
            }
            PendingEvent pendingEventObtainPendingEventLocked = obtainPendingEventLocked(inputEvent, obj, this.mCurId, finishedInputEventCallback, handler);
            if (this.mMainLooper.isCurrentThread()) {
                return sendInputEventOnMainLooperLocked(pendingEventObtainPendingEventLocked);
            }
            Message messageObtainMessage = this.mH.obtainMessage(5, pendingEventObtainPendingEventLocked);
            messageObtainMessage.setAsynchronous(true);
            this.mH.sendMessage(messageObtainMessage);
            return -1;
        }
    }

    public void dispatchKeyEventFromInputMethod(View view, KeyEvent keyEvent) {
        ViewRootImpl viewRootImpl;
        View servedViewLocked;
        InputMethodManager fallbackInputMethodManagerIfNecessary = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackInputMethodManagerIfNecessary != null) {
            fallbackInputMethodManagerIfNecessary.dispatchKeyEventFromInputMethod(view, keyEvent);
            return;
        }
        synchronized (this.mH) {
            if (view != null) {
                try {
                    viewRootImpl = view.getViewRootImpl();
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                viewRootImpl = null;
            }
            if (viewRootImpl == null && (servedViewLocked = getServedViewLocked()) != null) {
                viewRootImpl = servedViewLocked.getViewRootImpl();
            }
            if (viewRootImpl != null) {
                viewRootImpl.dispatchKeyFromIme(keyEvent);
            }
        }
    }

    void sendInputEventAndReportResultOnMainLooper(PendingEvent pendingEvent) {
        synchronized (this.mH) {
            int iSendInputEventOnMainLooperLocked = sendInputEventOnMainLooperLocked(pendingEvent);
            if (iSendInputEventOnMainLooperLocked == -1) {
                return;
            }
            boolean z = true;
            if (iSendInputEventOnMainLooperLocked != 1) {
                z = false;
            }
            invokeFinishedInputEventCallback(pendingEvent, z);
        }
    }

    int sendInputEventOnMainLooperLocked(PendingEvent pendingEvent) {
        if (this.mCurChannel != null) {
            if (this.mCurSender == null) {
                this.mCurSender = new ImeInputEventSender(this.mCurChannel, this.mH.getLooper());
            }
            InputEvent inputEvent = pendingEvent.mEvent;
            int sequenceNumber = inputEvent.getSequenceNumber();
            if (this.mCurSender.sendInputEvent(sequenceNumber, inputEvent)) {
                this.mPendingEvents.put(sequenceNumber, pendingEvent);
                Trace.traceCounter(4L, PENDING_EVENT_COUNTER, this.mPendingEvents.size());
                Message messageObtainMessage = this.mH.obtainMessage(6, sequenceNumber, 0, pendingEvent);
                messageObtainMessage.setAsynchronous(true);
                this.mH.sendMessageDelayed(messageObtainMessage, INPUT_METHOD_NOT_RESPONDING_TIMEOUT);
                return -1;
            }
            Log.w(TAG, "Unable to send input event to IME: " + this.mCurId + " dropping: " + inputEvent);
        }
        return 0;
    }

    void finishedInputEvent(int i, boolean z, boolean z2) {
        synchronized (this.mH) {
            int iIndexOfKey = this.mPendingEvents.indexOfKey(i);
            if (iIndexOfKey < 0) {
                return;
            }
            PendingEvent pendingEventValueAt = this.mPendingEvents.valueAt(iIndexOfKey);
            this.mPendingEvents.removeAt(iIndexOfKey);
            Trace.traceCounter(4L, PENDING_EVENT_COUNTER, this.mPendingEvents.size());
            if (z2) {
                Log.w(TAG, "Timeout waiting for IME to handle input event after 2500 ms: " + pendingEventValueAt.mInputMethodId);
            } else {
                this.mH.removeMessages(6, pendingEventValueAt);
            }
            invokeFinishedInputEventCallback(pendingEventValueAt, z);
        }
    }

    void invokeFinishedInputEventCallback(PendingEvent pendingEvent, boolean z) {
        pendingEvent.mHandled = z;
        if (pendingEvent.mHandler.getLooper().isCurrentThread()) {
            pendingEvent.run();
            return;
        }
        Message messageObtain = Message.obtain(pendingEvent.mHandler, pendingEvent);
        messageObtain.setAsynchronous(true);
        messageObtain.sendToTarget();
    }

    private void flushPendingEventsLocked() {
        this.mH.removeMessages(7);
        int size = this.mPendingEvents.size();
        for (int i = 0; i < size; i++) {
            Message messageObtainMessage = this.mH.obtainMessage(7, this.mPendingEvents.keyAt(i), 0);
            messageObtainMessage.setAsynchronous(true);
            messageObtainMessage.sendToTarget();
        }
    }

    private PendingEvent obtainPendingEventLocked(InputEvent inputEvent, Object obj, String str, FinishedInputEventCallback finishedInputEventCallback, Handler handler) {
        PendingEvent pendingEventAcquire = this.mPendingEventPool.acquire();
        if (pendingEventAcquire == null) {
            pendingEventAcquire = new PendingEvent();
        }
        pendingEventAcquire.mEvent = inputEvent;
        pendingEventAcquire.mToken = obj;
        pendingEventAcquire.mInputMethodId = str;
        pendingEventAcquire.mCallback = finishedInputEventCallback;
        pendingEventAcquire.mHandler = handler;
        return pendingEventAcquire;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recyclePendingEventLocked(PendingEvent pendingEvent) {
        pendingEvent.recycle();
        this.mPendingEventPool.release(pendingEvent);
    }

    public void showInputMethodPicker() {
        synchronized (this.mH) {
            showInputMethodPickerLocked();
        }
    }

    public void showInputMethodPickerFromSystem(boolean z, int i) {
        try {
            this.mService.showInputMethodPickerFromSystem(this.mClient, z ? 1 : 2, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void showInputMethodPickerLocked() {
        try {
            this.mService.showInputMethodPickerFromClient(this.mClient, 0);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isInputMethodPickerShown() {
        try {
            return this.mService.isInputMethodPickerShownForTest();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void showInputMethodAndSubtypeEnabler(String str) {
        try {
            this.mService.showInputMethodAndSubtypeEnablerFromClient(this.mClient, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public InputMethodSubtype getCurrentInputMethodSubtype() {
        try {
            return this.mService.getCurrentInputMethodSubtype();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean setCurrentInputMethodSubtype(InputMethodSubtype inputMethodSubtype) {
        Application applicationCurrentApplication;
        if (Process.myUid() == 1000) {
            Log.w(TAG, "System process should not call setCurrentInputMethodSubtype() because almost always it is a bug under multi-user / multi-profile environment. Consider directly interacting with InputMethodManagerService via LocalServices.");
            return false;
        }
        if (inputMethodSubtype == null || (applicationCurrentApplication = ActivityThread.currentApplication()) == null || applicationCurrentApplication.checkSelfPermission(Manifest.permission.WRITE_SECURE_SETTINGS) != 0) {
            return false;
        }
        ContentResolver contentResolver = applicationCurrentApplication.getContentResolver();
        String string = Settings.Secure.getString(contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD);
        if (ComponentName.unflattenFromString(string) == null) {
            return false;
        }
        try {
            List<InputMethodSubtype> enabledInputMethodSubtypeList = this.mService.getEnabledInputMethodSubtypeList(string, true);
            int size = enabledInputMethodSubtypeList.size();
            for (int i = 0; i < size; i++) {
                InputMethodSubtype inputMethodSubtype2 = enabledInputMethodSubtypeList.get(i);
                if (inputMethodSubtype2.equals(inputMethodSubtype)) {
                    Settings.Secure.putInt(contentResolver, Settings.Secure.SELECTED_INPUT_METHOD_SUBTYPE, inputMethodSubtype2.hashCode());
                    return true;
                }
            }
        } catch (RemoteException unused) {
        }
        return false;
    }

    @Deprecated
    public void notifyUserAction() {
        Log.w(TAG, "notifyUserAction() is a hidden method, which is now just a stub method that does nothing.  Leave comments in b.android.com/114740982 if your  application still depends on the previous behavior of this method.");
    }

    public Map<InputMethodInfo, List<InputMethodSubtype>> getShortcutInputMethodsAndSubtypes() {
        List<InputMethodInfo> enabledInputMethodList = getEnabledInputMethodList();
        enabledInputMethodList.sort(Comparator.comparingInt(new ToIntFunction() { // from class: android.view.inputmethod.-$$Lambda$InputMethodManager$pvWYFFVbHzZCDCCTiZVM09Xls4w
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return InputMethodManager.lambda$getShortcutInputMethodsAndSubtypes$2((InputMethodInfo) obj);
            }
        }));
        int size = enabledInputMethodList.size();
        for (int i = 0; i < size; i++) {
            InputMethodInfo inputMethodInfo = enabledInputMethodList.get(i);
            int size2 = getEnabledInputMethodSubtypeList(inputMethodInfo, true).size();
            for (int i2 = 0; i2 < size2; i2++) {
                InputMethodSubtype subtypeAt = inputMethodInfo.getSubtypeAt(i2);
                if (SUBTYPE_MODE_VOICE.equals(subtypeAt.getMode())) {
                    return Collections.singletonMap(inputMethodInfo, Collections.singletonList(subtypeAt));
                }
            }
        }
        return Collections.emptyMap();
    }

    static /* synthetic */ int lambda$getShortcutInputMethodsAndSubtypes$2(InputMethodInfo inputMethodInfo) {
        return !inputMethodInfo.isSystem() ? 1 : 0;
    }

    public int getInputMethodWindowVisibleHeight() {
        try {
            return this.mService.getInputMethodWindowVisibleHeight();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void reportActivityView(int i, Matrix matrix) {
        float[] fArr;
        if (matrix == null) {
            fArr = null;
        } else {
            try {
                float[] fArr2 = new float[9];
                matrix.getValues(fArr2);
                fArr = fArr2;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        this.mService.reportActivityView(this.mClient, i, fArr);
    }

    @Deprecated
    public boolean switchToLastInputMethod(IBinder iBinder) {
        return InputMethodPrivilegedOperationsRegistry.get(iBinder).switchToPreviousInputMethod();
    }

    @Deprecated
    public boolean switchToNextInputMethod(IBinder iBinder, boolean z) {
        return InputMethodPrivilegedOperationsRegistry.get(iBinder).switchToNextInputMethod(z);
    }

    @Deprecated
    public boolean shouldOfferSwitchingToNextInputMethod(IBinder iBinder) {
        return InputMethodPrivilegedOperationsRegistry.get(iBinder).shouldOfferSwitchingToNextInputMethod();
    }

    @Deprecated
    public void setAdditionalInputMethodSubtypes(String str, InputMethodSubtype[] inputMethodSubtypeArr) {
        try {
            this.mService.setAdditionalInputMethodSubtypes(str, inputMethodSubtypeArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public InputMethodSubtype getLastInputMethodSubtype() {
        try {
            return this.mService.getLastInputMethodSubtype();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeCallServedViewChangedLocked(EditorInfo editorInfo) {
        ImeInsetsSourceConsumer imeInsetsSourceConsumer = this.mImeInsetsConsumer;
        if (imeInsetsSourceConsumer != null) {
            imeInsetsSourceConsumer.onServedEditorChanged(editorInfo);
        }
    }

    public int getDisplayId() {
        return this.mDisplayId;
    }

    void doDump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        PrintWriterPrinter printWriterPrinter = new PrintWriterPrinter(printWriter);
        printWriterPrinter.println("Input method client state for " + this + SettingsStringUtil.DELIMITER);
        StringBuilder sb = new StringBuilder();
        sb.append("  mService=");
        sb.append(this.mService);
        printWriterPrinter.println(sb.toString());
        printWriterPrinter.println("  mMainLooper=" + this.mMainLooper);
        printWriterPrinter.println("  mIInputContext=" + this.mIInputContext);
        printWriterPrinter.println("  mActive=" + this.mActive + " mRestartOnNextWindowFocus=" + this.mRestartOnNextWindowFocus + " mBindSequence=" + this.mBindSequence + " mCurId=" + this.mCurId);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("  mFullscreenMode=");
        sb2.append(this.mFullscreenMode);
        printWriterPrinter.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("  mCurMethod=");
        sb3.append(this.mCurMethod);
        printWriterPrinter.println(sb3.toString());
        printWriterPrinter.println("  mCurRootView=" + this.mCurRootView);
        printWriterPrinter.println("  mServedView=" + getServedViewLocked());
        printWriterPrinter.println("  mNextServedView=" + getNextServedViewLocked());
        printWriterPrinter.println("  mServedConnecting=" + this.mServedConnecting);
        if (this.mCurrentTextBoxAttribute != null) {
            printWriterPrinter.println("  mCurrentTextBoxAttribute:");
            this.mCurrentTextBoxAttribute.dump(printWriterPrinter, "    ");
        } else {
            printWriterPrinter.println("  mCurrentTextBoxAttribute: null");
        }
        printWriterPrinter.println("  mServedInputConnectionWrapper=" + this.mServedInputConnectionWrapper);
        printWriterPrinter.println("  mCompletions=" + Arrays.toString(this.mCompletions));
        printWriterPrinter.println("  mCursorRect=" + this.mCursorRect);
        printWriterPrinter.println("  mCursorSelStart=" + this.mCursorSelStart + " mCursorSelEnd=" + this.mCursorSelEnd + " mCursorCandStart=" + this.mCursorCandStart + " mCursorCandEnd=" + this.mCursorCandEnd);
    }

    private final class ImeInputEventSender extends InputEventSender {
        public ImeInputEventSender(InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper);
        }

        @Override // android.view.InputEventSender
        public void onInputEventFinished(int i, boolean z) {
            InputMethodManager.this.finishedInputEvent(i, z, false);
        }
    }

    private final class PendingEvent implements Runnable {
        public FinishedInputEventCallback mCallback;
        public InputEvent mEvent;
        public boolean mHandled;
        public Handler mHandler;
        public String mInputMethodId;
        public Object mToken;

        private PendingEvent() {
        }

        public void recycle() {
            this.mEvent = null;
            this.mToken = null;
            this.mInputMethodId = null;
            this.mCallback = null;
            this.mHandler = null;
            this.mHandled = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mCallback.onFinishedInputEvent(this.mToken, this.mHandled);
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.recyclePendingEventLocked(this);
            }
        }
    }

    private static String dumpViewInfo(View view) {
        if (view == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(view);
        sb.append(",focus=" + view.hasFocus());
        sb.append(",windowFocus=" + view.hasWindowFocus());
        sb.append(",autofillUiShowing=" + isAutofillUIShowing(view));
        sb.append(",window=" + view.getWindowToken());
        sb.append(",displayId=" + view.getContext().getDisplayId());
        sb.append(",temporaryDetach=" + view.isTemporarilyDetached());
        sb.append(",hasImeFocus=" + view.hasImeFocus());
        return sb.toString();
    }
}
