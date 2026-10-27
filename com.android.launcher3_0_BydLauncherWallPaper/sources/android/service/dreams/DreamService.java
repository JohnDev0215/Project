package android.service.dreams;

import android.app.Activity;
import android.app.ActivityTaskManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.service.dreams.IDreamManager;
import android.service.dreams.IDreamService;
import android.util.Log;
import android.util.MathUtils;
import android.util.Slog;
import android.view.ActionMode;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.contentcapture.MainContentCaptureSession;
import com.android.internal.util.DumpUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public class DreamService extends Service implements Window.Callback {
    public static final String DREAM_META_DATA = "android.service.dream";
    public static final String DREAM_SERVICE = "dreams";
    public static final String SERVICE_INTERFACE = "android.service.dreams.DreamService";
    private Activity mActivity;
    private boolean mCanDoze;
    private Runnable mDispatchAfterOnAttachedToWindow;
    private boolean mDozing;
    private DreamServiceWrapper mDreamServiceWrapper;
    private IBinder mDreamToken;
    private boolean mFinished;
    private boolean mFullscreen;
    private boolean mInteractive;
    private boolean mStarted;
    private boolean mWaking;
    private Window mWindow;
    private boolean mWindowless;
    private final String TAG = DreamService.class.getSimpleName() + "[" + getClass().getSimpleName() + "]";
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mScreenBright = true;
    private int mDozeScreenState = 0;
    private int mDozeScreenBrightness = -1;
    private boolean mDebug = false;
    private final IDreamManager mDreamManager = IDreamManager.Stub.asInterface(ServiceManager.getService(DREAM_SERVICE));

    private int applyFlags(int i, int i2, int i3) {
        return ((~i3) & i) | (i2 & i3);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
    }

    @Override // android.view.Window.Callback
    public void onContentChanged() {
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int i) {
        return null;
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return null;
    }

    public void setDebug(boolean z) {
        this.mDebug = z;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.mInteractive) {
            if (this.mDebug) {
                Slog.v(this.TAG, "Waking up on keyEvent");
            }
            wakeUp();
            return true;
        }
        if (keyEvent.getKeyCode() == 4) {
            if (this.mDebug) {
                Slog.v(this.TAG, "Waking up on back key");
            }
            wakeUp();
            return true;
        }
        return this.mWindow.superDispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (!this.mInteractive) {
            if (this.mDebug) {
                Slog.v(this.TAG, "Waking up on keyShortcutEvent");
            }
            wakeUp();
            return true;
        }
        return this.mWindow.superDispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.mInteractive) {
            if (this.mDebug) {
                Slog.v(this.TAG, "Waking up on touchEvent");
            }
            wakeUp();
            return true;
        }
        return this.mWindow.superDispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        if (!this.mInteractive) {
            if (this.mDebug) {
                Slog.v(this.TAG, "Waking up on trackballEvent");
            }
            wakeUp();
            return true;
        }
        return this.mWindow.superDispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (!this.mInteractive) {
            if (this.mDebug) {
                Slog.v(this.TAG, "Waking up on genericMotionEvent");
            }
            wakeUp();
            return true;
        }
        return this.mWindow.superDispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return onSearchRequested();
    }

    public WindowManager getWindowManager() {
        Window window = this.mWindow;
        if (window != null) {
            return window.getWindowManager();
        }
        return null;
    }

    public Window getWindow() {
        return this.mWindow;
    }

    public void setContentView(int i) {
        getWindow().setContentView(i);
    }

    public void setContentView(View view) {
        getWindow().setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getWindow().setContentView(view, layoutParams);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getWindow().addContentView(view, layoutParams);
    }

    public <T extends View> T findViewById(int i) {
        return (T) getWindow().findViewById(i);
    }

    public final <T extends View> T requireViewById(int i) {
        T t = (T) findViewById(i);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this DreamService");
    }

    public void setInteractive(boolean z) {
        this.mInteractive = z;
    }

    public boolean isInteractive() {
        return this.mInteractive;
    }

    public void setFullscreen(boolean z) {
        if (this.mFullscreen != z) {
            this.mFullscreen = z;
            applyWindowFlags(z ? 1024 : 0, 1024);
        }
    }

    public boolean isFullscreen() {
        return this.mFullscreen;
    }

    public void setScreenBright(boolean z) {
        if (this.mScreenBright != z) {
            this.mScreenBright = z;
            applyWindowFlags(z ? 128 : 0, 128);
        }
    }

    public boolean isScreenBright() {
        return getWindowFlagValue(128, this.mScreenBright);
    }

    public void setWindowless(boolean z) {
        this.mWindowless = z;
    }

    public boolean isWindowless() {
        return this.mWindowless;
    }

    public boolean canDoze() {
        return this.mCanDoze;
    }

    public void startDozing() {
        if (!this.mCanDoze || this.mDozing) {
            return;
        }
        this.mDozing = true;
        updateDoze();
    }

    private void updateDoze() {
        IBinder iBinder = this.mDreamToken;
        if (iBinder == null) {
            Slog.w(this.TAG, "Updating doze without a dream token.");
        } else if (this.mDozing) {
            try {
                this.mDreamManager.startDozing(iBinder, this.mDozeScreenState, this.mDozeScreenBrightness);
            } catch (RemoteException unused) {
            }
        }
    }

    public void stopDozing() {
        if (this.mDozing) {
            this.mDozing = false;
            try {
                this.mDreamManager.stopDozing(this.mDreamToken);
            } catch (RemoteException unused) {
            }
        }
    }

    public boolean isDozing() {
        return this.mDozing;
    }

    public int getDozeScreenState() {
        return this.mDozeScreenState;
    }

    public void setDozeScreenState(int i) {
        if (this.mDozeScreenState != i) {
            this.mDozeScreenState = i;
            updateDoze();
        }
    }

    public int getDozeScreenBrightness() {
        return this.mDozeScreenBrightness;
    }

    public void setDozeScreenBrightness(int i) {
        if (i != -1) {
            i = clampAbsoluteBrightness(i);
        }
        if (this.mDozeScreenBrightness != i) {
            this.mDozeScreenBrightness = i;
            updateDoze();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        if (this.mDebug) {
            Slog.v(this.TAG, "onCreate()");
        }
        super.onCreate();
    }

    public void onDreamingStarted() {
        if (this.mDebug) {
            Slog.v(this.TAG, "onDreamingStarted()");
        }
    }

    public void onDreamingStopped() {
        if (this.mDebug) {
            Slog.v(this.TAG, "onDreamingStopped()");
        }
    }

    public void onWakeUp() {
        finish();
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (this.mDebug) {
            Slog.v(this.TAG, "onBind() intent = " + intent);
        }
        DreamServiceWrapper dreamServiceWrapper = new DreamServiceWrapper();
        this.mDreamServiceWrapper = dreamServiceWrapper;
        return dreamServiceWrapper;
    }

    public final void finish() {
        if (this.mDebug) {
            Slog.v(this.TAG, "finish(): mFinished=" + this.mFinished);
        }
        Activity activity = this.mActivity;
        if (activity != null) {
            if (activity.isFinishing()) {
                return;
            }
            activity.finishAndRemoveTask();
        } else {
            if (this.mFinished) {
                return;
            }
            this.mFinished = true;
            IBinder iBinder = this.mDreamToken;
            if (iBinder == null) {
                Slog.w(this.TAG, "Finish was called before the dream was attached.");
                stopSelf();
            } else {
                try {
                    this.mDreamManager.finishSelf(iBinder, true);
                } catch (RemoteException unused) {
                }
            }
        }
    }

    public final void wakeUp() {
        wakeUp(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wakeUp(boolean z) {
        if (this.mDebug) {
            Slog.v(this.TAG, "wakeUp(): fromSystem=" + z + ", mWaking=" + this.mWaking + ", mFinished=" + this.mFinished);
        }
        if (this.mWaking || this.mFinished) {
            return;
        }
        this.mWaking = true;
        onWakeUp();
        if (z || this.mFinished) {
            return;
        }
        if (this.mActivity == null) {
            Slog.w(this.TAG, "WakeUp was called before the dream was attached.");
        } else {
            try {
                this.mDreamManager.finishSelf(this.mDreamToken, false);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (this.mDebug) {
            Slog.v(this.TAG, "onDestroy()");
        }
        detach();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void detach() {
        if (this.mStarted) {
            if (this.mDebug) {
                Slog.v(this.TAG, "detach(): Calling onDreamingStopped()");
            }
            this.mStarted = false;
            onDreamingStopped();
        }
        Activity activity = this.mActivity;
        if (activity != null && !activity.isFinishing()) {
            this.mActivity.finishAndRemoveTask();
        } else {
            finish();
        }
        this.mDreamToken = null;
        this.mCanDoze = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attach(IBinder iBinder, boolean z, final IRemoteCallback iRemoteCallback) {
        if (this.mDreamToken != null) {
            Slog.e(this.TAG, "attach() called when dream with token=" + this.mDreamToken + " already attached");
            return;
        }
        if (this.mFinished || this.mWaking) {
            Slog.w(this.TAG, "attach() called after dream already finished");
            try {
                this.mDreamManager.finishSelf(iBinder, true);
                return;
            } catch (RemoteException unused) {
                return;
            }
        }
        this.mDreamToken = iBinder;
        this.mCanDoze = z;
        if (this.mWindowless && !z) {
            throw new IllegalStateException("Only doze dreams can be windowless");
        }
        Runnable runnable = new Runnable() { // from class: android.service.dreams.-$$Lambda$DreamService$_jW8-zmRjzxmzNEP5nyb5cmnMvU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$attach$0$DreamService(iRemoteCallback);
            }
        };
        this.mDispatchAfterOnAttachedToWindow = runnable;
        if (!this.mWindowless) {
            Intent intent = new Intent(this, (Class<?>) DreamActivity.class);
            intent.setPackage(getApplicationContext().getPackageName());
            intent.setFlags(268435456);
            intent.putExtra(MainContentCaptureSession.EXTRA_BINDER, this.mDreamServiceWrapper);
            try {
                if (ActivityTaskManager.getService().startDreamActivity(intent)) {
                    return;
                }
                detach();
                return;
            } catch (RemoteException e) {
                Log.w(this.TAG, "Could not connect to activity task manager to start dream activity");
                e.rethrowFromSystemServer();
                return;
            }
        }
        runnable.run();
    }

    public /* synthetic */ void lambda$attach$0$DreamService(IRemoteCallback iRemoteCallback) {
        if (this.mWindow != null || this.mWindowless) {
            this.mStarted = true;
            try {
                onDreamingStarted();
                try {
                    iRemoteCallback.sendResult(null);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                try {
                    iRemoteCallback.sendResult(null);
                    throw th;
                } catch (RemoteException e2) {
                    throw e2.rethrowFromSystemServer();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWindowCreated(Window window) {
        this.mWindow = window;
        window.setCallback(this);
        this.mWindow.requestFeature(1);
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        attributes.flags |= (this.mFullscreen ? 1024 : 0) | 21561601 | (this.mScreenBright ? 128 : 0);
        this.mWindow.setAttributes(attributes);
        this.mWindow.clearFlags(Integer.MIN_VALUE);
        this.mWindow.getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: android.service.dreams.DreamService.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                DreamService.this.mDispatchAfterOnAttachedToWindow.run();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                if (DreamService.this.mActivity == null || !DreamService.this.mActivity.isChangingConfigurations()) {
                    DreamService.this.mActivity = null;
                    DreamService.this.finish();
                }
            }
        });
    }

    private boolean getWindowFlagValue(int i, boolean z) {
        Window window = this.mWindow;
        return window == null ? z : (window.getAttributes().flags & i) != 0;
    }

    private void applyWindowFlags(int i, int i2) {
        Window window = this.mWindow;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags = applyFlags(attributes.flags, i, i2);
            this.mWindow.setAttributes(attributes);
            this.mWindow.getWindowManager().updateViewLayout(this.mWindow.getDecorView(), attributes);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Service
    public void dump(final FileDescriptor fileDescriptor, PrintWriter printWriter, final String[] strArr) {
        DumpUtils.dumpAsync(this.mHandler, new DumpUtils.Dump() { // from class: android.service.dreams.DreamService.2
            @Override // com.android.internal.util.DumpUtils.Dump
            public void dump(PrintWriter printWriter2, String str) {
                DreamService.this.dumpOnHandler(fileDescriptor, printWriter2, strArr);
            }
        }, printWriter, "", 1000L);
    }

    protected void dumpOnHandler(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(this.TAG + ": ");
        if (this.mFinished) {
            printWriter.println("stopped");
        } else {
            printWriter.println("running (dreamToken=" + this.mDreamToken + ")");
        }
        printWriter.println("  window: " + this.mWindow);
        printWriter.print("  flags:");
        if (isInteractive()) {
            printWriter.print(" interactive");
        }
        if (isFullscreen()) {
            printWriter.print(" fullscreen");
        }
        if (isScreenBright()) {
            printWriter.print(" bright");
        }
        if (isWindowless()) {
            printWriter.print(" windowless");
        }
        if (isDozing()) {
            printWriter.print(" dozing");
        } else if (canDoze()) {
            printWriter.print(" candoze");
        }
        printWriter.println();
        if (canDoze()) {
            printWriter.println("  doze screen state: " + Display.stateToString(this.mDozeScreenState));
            printWriter.println("  doze screen brightness: " + this.mDozeScreenBrightness);
        }
    }

    private static int clampAbsoluteBrightness(int i) {
        return MathUtils.constrain(i, 0, 255);
    }

    final class DreamServiceWrapper extends IDreamService.Stub {
        DreamServiceWrapper() {
        }

        @Override // android.service.dreams.IDreamService
        public void attach(final IBinder iBinder, final boolean z, final IRemoteCallback iRemoteCallback) {
            DreamService.this.mHandler.post(new Runnable() { // from class: android.service.dreams.-$$Lambda$DreamService$DreamServiceWrapper$Wyuh5Ty8luK9-wY2eekdWhcWB4U
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$attach$0$DreamService$DreamServiceWrapper(iBinder, z, iRemoteCallback);
                }
            });
        }

        public /* synthetic */ void lambda$attach$0$DreamService$DreamServiceWrapper(IBinder iBinder, boolean z, IRemoteCallback iRemoteCallback) {
            DreamService.this.attach(iBinder, z, iRemoteCallback);
        }

        @Override // android.service.dreams.IDreamService
        public void detach() {
            Handler handler = DreamService.this.mHandler;
            final DreamService dreamService = DreamService.this;
            handler.post(new Runnable() { // from class: android.service.dreams.-$$Lambda$DreamService$DreamServiceWrapper$M4flqk8vG5Q4Nq_Jj7JRFr1pbT8
                @Override // java.lang.Runnable
                public final void run() {
                    dreamService.detach();
                }
            });
        }

        public /* synthetic */ void lambda$wakeUp$2$DreamService$DreamServiceWrapper() {
            DreamService.this.wakeUp(true);
        }

        @Override // android.service.dreams.IDreamService
        public void wakeUp() {
            DreamService.this.mHandler.post(new Runnable() { // from class: android.service.dreams.-$$Lambda$DreamService$DreamServiceWrapper$QYmUpvv6LG49p-q5G_I91exFqKM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$wakeUp$2$DreamService$DreamServiceWrapper();
                }
            });
        }

        void onActivityCreated(DreamActivity dreamActivity) {
            DreamService.this.mActivity = dreamActivity;
            DreamService.this.onWindowCreated(dreamActivity.getWindow());
        }
    }
}
