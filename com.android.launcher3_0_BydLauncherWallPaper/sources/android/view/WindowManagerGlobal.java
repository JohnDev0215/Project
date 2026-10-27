package android.view;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.util.ArraySet;
import android.util.Log;
import android.view.IWindowManager;
import android.view.IWindowSessionCallback;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.util.FastPrintWriter;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class WindowManagerGlobal {
    public static final int ADD_APP_EXITING = -4;
    public static final int ADD_BAD_APP_TOKEN = -1;
    public static final int ADD_BAD_SUBWINDOW_TOKEN = -2;
    public static final int ADD_DUPLICATE_ADD = -5;
    public static final int ADD_FLAG_ALWAYS_CONSUME_SYSTEM_BARS = 4;
    public static final int ADD_FLAG_APP_VISIBLE = 2;
    public static final int ADD_FLAG_IN_TOUCH_MODE = 1;
    public static final int ADD_FLAG_USE_BLAST = 8;
    public static final int ADD_FLAG_USE_TRIPLE_BUFFERING = 4;
    public static final int ADD_INVALID_DISPLAY = -9;
    public static final int ADD_INVALID_TYPE = -10;
    public static final int ADD_INVALID_USER = -11;
    public static final int ADD_MULTIPLE_SINGLETON = -7;
    public static final int ADD_NOT_APP_TOKEN = -3;
    public static final int ADD_OKAY = 0;
    public static final int ADD_PERMISSION_DENIED = -8;
    public static final int ADD_STARTING_NOT_NEEDED = -6;
    public static final int ADD_TOO_MANY_TOKENS = -12;
    public static final int RELAYOUT_DEFER_SURFACE_DESTROY = 2;
    public static final int RELAYOUT_INSETS_PENDING = 1;
    public static final int RELAYOUT_RES_BLAST_SYNC = 128;
    public static final int RELAYOUT_RES_CONSUME_ALWAYS_SYSTEM_BARS = 64;
    public static final int RELAYOUT_RES_DRAG_RESIZING_DOCKED = 8;
    public static final int RELAYOUT_RES_DRAG_RESIZING_FREEFORM = 16;
    public static final int RELAYOUT_RES_FIRST_TIME = 2;
    public static final int RELAYOUT_RES_IN_TOUCH_MODE = 1;
    public static final int RELAYOUT_RES_SURFACE_CHANGED = 4;
    public static final int RELAYOUT_RES_SURFACE_RESIZED = 32;
    private static final String TAG = "WindowManager";
    private static WindowManagerGlobal sDefaultWindowManager = null;
    private static boolean sUseBLASTAdapter = false;
    private static IWindowManager sWindowManagerService;
    private static IWindowSession sWindowSession;
    private Runnable mSystemPropertyUpdater;
    private final Object mLock = new Object();
    private final ArrayList<View> mViews = new ArrayList<>();
    private final ArrayList<ViewRootImpl> mRoots = new ArrayList<>();
    private final ArrayList<WindowManager.LayoutParams> mParams = new ArrayList<>();
    private final ArraySet<View> mDyingViews = new ArraySet<>();

    private WindowManagerGlobal() {
    }

    public static void initialize() {
        getWindowManagerService();
    }

    public static WindowManagerGlobal getInstance() {
        WindowManagerGlobal windowManagerGlobal;
        synchronized (WindowManagerGlobal.class) {
            if (sDefaultWindowManager == null) {
                sDefaultWindowManager = new WindowManagerGlobal();
            }
            windowManagerGlobal = sDefaultWindowManager;
        }
        return windowManagerGlobal;
    }

    public static IWindowManager getWindowManagerService() {
        IWindowManager iWindowManager;
        synchronized (WindowManagerGlobal.class) {
            if (sWindowManagerService == null) {
                IWindowManager iWindowManagerAsInterface = IWindowManager.Stub.asInterface(ServiceManager.getService(Context.WINDOW_SERVICE));
                sWindowManagerService = iWindowManagerAsInterface;
                if (iWindowManagerAsInterface != null) {
                    try {
                        ValueAnimator.setDurationScale(iWindowManagerAsInterface.getCurrentAnimatorScale());
                        sUseBLASTAdapter = sWindowManagerService.useBLAST();
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
            iWindowManager = sWindowManagerService;
        }
        return iWindowManager;
    }

    public static IWindowSession getWindowSession() {
        IWindowSession iWindowSession;
        synchronized (WindowManagerGlobal.class) {
            if (sWindowSession == null) {
                try {
                    InputMethodManager.ensureDefaultInstanceForDefaultDisplayIfNecessary();
                    sWindowSession = getWindowManagerService().openSession(new IWindowSessionCallback.Stub() { // from class: android.view.WindowManagerGlobal.1
                        @Override // android.view.IWindowSessionCallback
                        public void onAnimatorScaleChanged(float f) {
                            ValueAnimator.setDurationScale(f);
                        }
                    });
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            iWindowSession = sWindowSession;
        }
        return iWindowSession;
    }

    public static IWindowSession peekWindowSession() {
        IWindowSession iWindowSession;
        synchronized (WindowManagerGlobal.class) {
            iWindowSession = sWindowSession;
        }
        return iWindowSession;
    }

    public static boolean useBLAST() {
        return sUseBLASTAdapter;
    }

    public String[] getViewRootNames() {
        String[] strArr;
        synchronized (this.mLock) {
            int size = this.mRoots.size();
            strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = getWindowName(this.mRoots.get(i));
            }
        }
        return strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057 A[Catch: all -> 0x0067, TryCatch #0 {, blocks: (B:4:0x0008, B:6:0x0012, B:27:0x0062, B:9:0x001f, B:11:0x0023, B:13:0x0029, B:17:0x0032, B:19:0x004a, B:22:0x0050, B:26:0x0057, B:28:0x0065), top: B:33:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<android.view.ViewRootImpl> getRootViews(android.os.IBinder r11) {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r1 = r10.mLock
            monitor-enter(r1)
            java.util.ArrayList<android.view.ViewRootImpl> r2 = r10.mRoots     // Catch: java.lang.Throwable -> L67
            int r2 = r2.size()     // Catch: java.lang.Throwable -> L67
            r3 = 0
            r4 = r3
        L10:
            if (r4 >= r2) goto L65
            java.util.ArrayList<android.view.WindowManager$LayoutParams> r5 = r10.mParams     // Catch: java.lang.Throwable -> L67
            java.lang.Object r5 = r5.get(r4)     // Catch: java.lang.Throwable -> L67
            android.view.WindowManager$LayoutParams r5 = (android.view.WindowManager.LayoutParams) r5     // Catch: java.lang.Throwable -> L67
            android.os.IBinder r6 = r5.token     // Catch: java.lang.Throwable -> L67
            if (r6 != 0) goto L1f
            goto L62
        L1f:
            android.os.IBinder r6 = r5.token     // Catch: java.lang.Throwable -> L67
            if (r6 == r11) goto L57
            int r6 = r5.type     // Catch: java.lang.Throwable -> L67
            r7 = 1000(0x3e8, float:1.401E-42)
            if (r6 < r7) goto L53
            int r6 = r5.type     // Catch: java.lang.Throwable -> L67
            r7 = 1999(0x7cf, float:2.801E-42)
            if (r6 > r7) goto L53
            r6 = r3
        L30:
            if (r6 >= r2) goto L53
            java.util.ArrayList<android.view.View> r7 = r10.mViews     // Catch: java.lang.Throwable -> L67
            java.lang.Object r7 = r7.get(r6)     // Catch: java.lang.Throwable -> L67
            android.view.View r7 = (android.view.View) r7     // Catch: java.lang.Throwable -> L67
            java.util.ArrayList<android.view.WindowManager$LayoutParams> r8 = r10.mParams     // Catch: java.lang.Throwable -> L67
            java.lang.Object r8 = r8.get(r6)     // Catch: java.lang.Throwable -> L67
            android.view.WindowManager$LayoutParams r8 = (android.view.WindowManager.LayoutParams) r8     // Catch: java.lang.Throwable -> L67
            android.os.IBinder r9 = r5.token     // Catch: java.lang.Throwable -> L67
            android.os.IBinder r7 = r7.getWindowToken()     // Catch: java.lang.Throwable -> L67
            if (r9 != r7) goto L50
            android.os.IBinder r7 = r8.token     // Catch: java.lang.Throwable -> L67
            if (r7 != r11) goto L50
            r5 = 1
            goto L54
        L50:
            int r6 = r6 + 1
            goto L30
        L53:
            r5 = r3
        L54:
            if (r5 != 0) goto L57
            goto L62
        L57:
            java.util.ArrayList<android.view.ViewRootImpl> r5 = r10.mRoots     // Catch: java.lang.Throwable -> L67
            java.lang.Object r5 = r5.get(r4)     // Catch: java.lang.Throwable -> L67
            android.view.ViewRootImpl r5 = (android.view.ViewRootImpl) r5     // Catch: java.lang.Throwable -> L67
            r0.add(r5)     // Catch: java.lang.Throwable -> L67
        L62:
            int r4 = r4 + 1
            goto L10
        L65:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L67
            return r0
        L67:
            r10 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L67
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.WindowManagerGlobal.getRootViews(android.os.IBinder):java.util.ArrayList");
    }

    public ArrayList<View> getWindowViews() {
        ArrayList<View> arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList<>(this.mViews);
        }
        return arrayList;
    }

    public View getWindowView(IBinder iBinder) {
        synchronized (this.mLock) {
            int size = this.mViews.size();
            for (int i = 0; i < size; i++) {
                View view = this.mViews.get(i);
                if (view.getWindowToken() == iBinder) {
                    return view;
                }
            }
            return null;
        }
    }

    public View getRootView(String str) {
        synchronized (this.mLock) {
            for (int size = this.mRoots.size() - 1; size >= 0; size--) {
                ViewRootImpl viewRootImpl = this.mRoots.get(size);
                if (str.equals(getWindowName(viewRootImpl))) {
                    return viewRootImpl.getView();
                }
            }
            return null;
        }
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams, Display display, Window window, int i) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        if (display == null) {
            throw new IllegalArgumentException("display must not be null");
        }
        if (!(layoutParams instanceof WindowManager.LayoutParams)) {
            throw new IllegalArgumentException("Params must be WindowManager.LayoutParams");
        }
        WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
        if (window != null) {
            window.adjustLayoutParamsForSubWindow(layoutParams2);
        } else {
            Context context = view.getContext();
            if (context != null && (context.getApplicationInfo().flags & 536870912) != 0) {
                layoutParams2.flags |= 16777216;
            }
        }
        View view2 = null;
        synchronized (this.mLock) {
            if (this.mSystemPropertyUpdater == null) {
                Runnable runnable = new Runnable() { // from class: android.view.WindowManagerGlobal.2
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (WindowManagerGlobal.this.mLock) {
                            for (int size = WindowManagerGlobal.this.mRoots.size() - 1; size >= 0; size--) {
                                ((ViewRootImpl) WindowManagerGlobal.this.mRoots.get(size)).loadSystemProperties();
                            }
                        }
                    }
                };
                this.mSystemPropertyUpdater = runnable;
                SystemProperties.addChangeCallback(runnable);
            }
            int iFindViewLocked = findViewLocked(view, false);
            if (iFindViewLocked >= 0) {
                if (this.mDyingViews.contains(view)) {
                    this.mRoots.get(iFindViewLocked).doDie();
                } else {
                    throw new IllegalStateException("View " + view + " has already been added to the window manager.");
                }
            }
            if (layoutParams2.type >= 1000 && layoutParams2.type <= 1999) {
                int size = this.mViews.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.mRoots.get(i2).mWindow.asBinder() == layoutParams2.token) {
                        view2 = this.mViews.get(i2);
                    }
                }
            }
            ViewRootImpl viewRootImpl = new ViewRootImpl(view.getContext(), display);
            view.setLayoutParams(layoutParams2);
            this.mViews.add(view);
            this.mRoots.add(viewRootImpl);
            this.mParams.add(layoutParams2);
            try {
                viewRootImpl.setView(view, layoutParams2, view2, i);
            } catch (RuntimeException e) {
                if (iFindViewLocked >= 0) {
                    removeViewLocked(iFindViewLocked, true);
                }
                throw e;
            }
        }
    }

    public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        if (!(layoutParams instanceof WindowManager.LayoutParams)) {
            throw new IllegalArgumentException("Params must be WindowManager.LayoutParams");
        }
        WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
        view.setLayoutParams(layoutParams2);
        synchronized (this.mLock) {
            int iFindViewLocked = findViewLocked(view, true);
            ViewRootImpl viewRootImpl = this.mRoots.get(iFindViewLocked);
            this.mParams.remove(iFindViewLocked);
            this.mParams.add(iFindViewLocked, layoutParams2);
            viewRootImpl.setLayoutParams(layoutParams2, false);
        }
    }

    public void removeView(View view, boolean z) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        synchronized (this.mLock) {
            int iFindViewLocked = findViewLocked(view, true);
            View view2 = this.mRoots.get(iFindViewLocked).getView();
            removeViewLocked(iFindViewLocked, z);
            if (view2 != view) {
                throw new IllegalStateException("Calling with view " + view + " but the ViewAncestor is attached to " + view2);
            }
        }
    }

    public void closeAll(IBinder iBinder, String str, String str2) {
        closeAllExceptView(iBinder, null, str, str2);
    }

    public void closeAllExceptView(IBinder iBinder, View view, String str, String str2) {
        synchronized (this.mLock) {
            int size = this.mViews.size();
            for (int i = 0; i < size; i++) {
                if ((view == null || this.mViews.get(i) != view) && (iBinder == null || this.mParams.get(i).token == iBinder)) {
                    ViewRootImpl viewRootImpl = this.mRoots.get(i);
                    if (str != null) {
                        WindowLeaked windowLeaked = new WindowLeaked(str2 + " " + str + " has leaked window " + viewRootImpl.getView() + " that was originally added here");
                        windowLeaked.setStackTrace(viewRootImpl.getLocation().getStackTrace());
                        Log.e(TAG, "", windowLeaked);
                    }
                    removeViewLocked(i, false);
                }
            }
        }
    }

    private void removeViewLocked(int i, boolean z) {
        ViewRootImpl viewRootImpl = this.mRoots.get(i);
        View view = viewRootImpl.getView();
        if (viewRootImpl != null) {
            viewRootImpl.getImeFocusController().onWindowDismissed();
        }
        boolean zDie = viewRootImpl.die(z);
        if (view != null) {
            view.assignParent(null);
            if (zDie) {
                this.mDyingViews.add(view);
            }
        }
    }

    void doRemoveView(ViewRootImpl viewRootImpl) {
        boolean zIsEmpty;
        synchronized (this.mLock) {
            int iIndexOf = this.mRoots.indexOf(viewRootImpl);
            if (iIndexOf >= 0) {
                this.mRoots.remove(iIndexOf);
                this.mParams.remove(iIndexOf);
                this.mDyingViews.remove(this.mViews.remove(iIndexOf));
            }
            zIsEmpty = this.mRoots.isEmpty();
        }
        if (ThreadedRenderer.sTrimForeground && ThreadedRenderer.isAvailable()) {
            doTrimForeground();
        }
        if (zIsEmpty) {
            InsetsAnimationThread.release();
        }
    }

    private int findViewLocked(View view, boolean z) {
        int iIndexOf = this.mViews.indexOf(view);
        if (!z || iIndexOf >= 0) {
            return iIndexOf;
        }
        throw new IllegalArgumentException("View=" + view + " not attached to window manager");
    }

    public static boolean shouldDestroyEglContext(int i) {
        if (i >= 80) {
            return true;
        }
        return i >= 60 && !ActivityManager.isHighEndGfx();
    }

    public void trimMemory(int i) {
        if (ThreadedRenderer.isAvailable()) {
            if (shouldDestroyEglContext(i)) {
                synchronized (this.mLock) {
                    for (int size = this.mRoots.size() - 1; size >= 0; size--) {
                        this.mRoots.get(size).destroyHardwareResources();
                    }
                }
                i = 80;
            }
            ThreadedRenderer.trimMemory(i);
            if (ThreadedRenderer.sTrimForeground) {
                doTrimForeground();
            }
        }
    }

    public static void trimForeground() {
        if (ThreadedRenderer.sTrimForeground && ThreadedRenderer.isAvailable()) {
            getInstance().doTrimForeground();
        }
    }

    private void doTrimForeground() {
        boolean z;
        synchronized (this.mLock) {
            z = false;
            for (int size = this.mRoots.size() - 1; size >= 0; size--) {
                ViewRootImpl viewRootImpl = this.mRoots.get(size);
                if (viewRootImpl.mView == null || viewRootImpl.getHostVisibility() != 0 || viewRootImpl.mAttachInfo.mThreadedRenderer == null) {
                    viewRootImpl.destroyHardwareResources();
                } else {
                    z = true;
                }
            }
        }
        if (z) {
            return;
        }
        ThreadedRenderer.trimMemory(80);
    }

    public void dumpGfxInfo(FileDescriptor fileDescriptor, String[] strArr) {
        FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(fileDescriptor));
        try {
            synchronized (this.mLock) {
                int size = this.mViews.size();
                fastPrintWriter.println("Profile data in ms:");
                for (int i = 0; i < size; i++) {
                    ViewRootImpl viewRootImpl = this.mRoots.get(i);
                    fastPrintWriter.printf("\n\t%s (visibility=%d)", getWindowName(viewRootImpl), Integer.valueOf(viewRootImpl.getHostVisibility()));
                    ThreadedRenderer threadedRenderer = viewRootImpl.getView().mAttachInfo.mThreadedRenderer;
                    if (threadedRenderer != null) {
                        threadedRenderer.dumpGfxInfo(fastPrintWriter, fileDescriptor, strArr);
                    }
                }
                fastPrintWriter.println("\nView hierarchy:\n");
                ViewRootImpl.GfxInfo gfxInfo = new ViewRootImpl.GfxInfo();
                for (int i2 = 0; i2 < size; i2++) {
                    ViewRootImpl viewRootImpl2 = this.mRoots.get(i2);
                    ViewRootImpl.GfxInfo gfxInfo2 = viewRootImpl2.getGfxInfo();
                    gfxInfo.add(gfxInfo2);
                    fastPrintWriter.printf("  %s\n  %d views, %.2f kB of render nodes", getWindowName(viewRootImpl2), Integer.valueOf(gfxInfo2.viewCount), Float.valueOf(gfxInfo2.renderNodeMemoryUsage / 1024.0f));
                    fastPrintWriter.printf("\n\n", new Object[0]);
                }
                fastPrintWriter.printf("\nTotal %-15s: %d\n", "ViewRootImpl", Integer.valueOf(size));
                fastPrintWriter.printf("Total %-15s: %d\n", "attached Views", Integer.valueOf(gfxInfo.viewCount));
                fastPrintWriter.printf("Total %-15s: %.2f kB (used) / %.2f kB (capacity)\n\n", "RenderNode", Float.valueOf(gfxInfo.renderNodeMemoryUsage / 1024.0f), Float.valueOf(gfxInfo.renderNodeMemoryAllocated / 1024.0f));
            }
        } finally {
            fastPrintWriter.flush();
        }
    }

    private static String getWindowName(ViewRootImpl viewRootImpl) {
        return ((Object) viewRootImpl.mWindowAttributes.getTitle()) + "/" + viewRootImpl.getClass().getName() + '@' + Integer.toHexString(viewRootImpl.hashCode());
    }

    public void setStoppedState(IBinder iBinder, final boolean z) {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = null;
            for (int size = this.mViews.size() - 1; size >= 0; size--) {
                if (iBinder == null || this.mParams.get(size).token == iBinder) {
                    ViewRootImpl viewRootImpl = this.mRoots.get(size);
                    if (viewRootImpl.mThread == Thread.currentThread()) {
                        viewRootImpl.setWindowStopped(z);
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(viewRootImpl);
                    }
                    setStoppedState(viewRootImpl.mAttachInfo.mWindowToken, z);
                }
            }
        }
        if (arrayList != null) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                final ViewRootImpl viewRootImpl2 = (ViewRootImpl) arrayList.get(size2);
                viewRootImpl2.mHandler.runWithScissors(new Runnable() { // from class: android.view.-$$Lambda$WindowManagerGlobal$ghaoSRunGyxCNCOlqFq7hoY-c3o
                    @Override // java.lang.Runnable
                    public final void run() {
                        viewRootImpl2.setWindowStopped(z);
                    }
                }, 0L);
            }
        }
    }

    public void reportNewConfiguration(Configuration configuration) {
        synchronized (this.mLock) {
            int size = this.mViews.size();
            Configuration configuration2 = new Configuration(configuration);
            for (int i = 0; i < size; i++) {
                this.mRoots.get(i).requestUpdateConfiguration(configuration2);
            }
        }
    }

    public void changeCanvasOpacity(IBinder iBinder, boolean z) {
        if (iBinder == null) {
            return;
        }
        synchronized (this.mLock) {
            for (int size = this.mParams.size() - 1; size >= 0; size--) {
                if (this.mParams.get(size).token == iBinder) {
                    this.mRoots.get(size).changeCanvasOpacity(z);
                    return;
                }
            }
        }
    }
}
