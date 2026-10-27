package org.chromium.ui.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Callback;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.VSyncMonitor;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("ui")
public class WindowAndroid {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int START_INTENT_FAILURE = -1;
    private static final String TAG = "WindowAndroid";
    static final String WINDOW_CALLBACK_ERRORS = "window_callback_errors";
    private final AccessibilityManager mAccessibilityManager;
    private View mAnimationPlaceholderView;
    protected Context mApplicationContext;
    private WeakReference<Context> mContextRef;
    protected HashMap<Integer, String> mIntentErrors;
    private boolean mIsTouchExplorationEnabled;
    private ViewGroup mKeyboardAccessoryView;
    protected SparseArray<IntentCallback> mOutstandingIntents;
    private AndroidPermissionDelegate mPermissionDelegate;
    private TouchExplorationMonitor mTouchExplorationMonitor;
    private final VSyncMonitor.Listener mVSyncListener;
    private final VSyncMonitor mVSyncMonitor;
    private long mNativeWindowAndroid = 0;
    private HashSet<Animator> mAnimationsOverContent = new HashSet<>();
    protected boolean mIsKeyboardShowing = false;
    private LinkedList<KeyboardVisibilityListener> mKeyboardVisibilityListeners = new LinkedList<>();

    public interface IntentCallback {
        void onIntentCompleted(WindowAndroid windowAndroid, int i, ContentResolver contentResolver, Intent intent);
    }

    public interface KeyboardVisibilityListener {
        void keyboardVisibilityChanged(boolean z);
    }

    public interface PermissionCallback {
        void onRequestPermissionsResult(String[] strArr, int[] iArr);
    }

    private native void nativeDestroy(long j);

    private native long nativeInit();

    private native void nativeOnActivityStarted(long j);

    private native void nativeOnActivityStopped(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnVSync(long j, long j2, long j3);

    private native void nativeOnVisibilityChanged(long j, boolean z);

    protected void registerKeyboardVisibilityCallbacks() {
    }

    protected void unregisterKeyboardVisibilityCallbacks() {
    }

    private class TouchExplorationMonitor {
        private AccessibilityManager.TouchExplorationStateChangeListener mTouchExplorationListener;

        TouchExplorationMonitor() {
            this.mTouchExplorationListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: org.chromium.ui.base.WindowAndroid.TouchExplorationMonitor.1
                @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
                public void onTouchExplorationStateChanged(boolean z) {
                    WindowAndroid.this.mIsTouchExplorationEnabled = WindowAndroid.this.mAccessibilityManager.isTouchExplorationEnabled();
                    WindowAndroid.this.refreshWillNotDraw();
                }
            };
            WindowAndroid.this.mAccessibilityManager.addTouchExplorationStateChangeListener(this.mTouchExplorationListener);
        }

        void destroy() {
            WindowAndroid.this.mAccessibilityManager.removeTouchExplorationStateChangeListener(this.mTouchExplorationListener);
        }
    }

    public static Activity activityFromContext(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return activityFromContext(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public boolean isInsideVSync() {
        return this.mVSyncMonitor.isInsideVSync();
    }

    public long getVsyncPeriodInMillis() {
        return this.mVSyncMonitor.getVSyncPeriodInMicroseconds() / 1000;
    }

    public WindowAndroid(Context context) {
        VSyncMonitor.Listener listener = new VSyncMonitor.Listener() { // from class: org.chromium.ui.base.WindowAndroid.1
            @Override // org.chromium.ui.VSyncMonitor.Listener
            public void onVSync(VSyncMonitor vSyncMonitor, long j) {
                if (WindowAndroid.this.mNativeWindowAndroid != 0) {
                    WindowAndroid windowAndroid = WindowAndroid.this;
                    windowAndroid.nativeOnVSync(windowAndroid.mNativeWindowAndroid, j, WindowAndroid.this.mVSyncMonitor.getVSyncPeriodInMicroseconds());
                }
            }
        };
        this.mVSyncListener = listener;
        this.mApplicationContext = context.getApplicationContext();
        this.mContextRef = new WeakReference<>(context);
        this.mOutstandingIntents = new SparseArray<>();
        this.mIntentErrors = new HashMap<>();
        this.mVSyncMonitor = new VSyncMonitor(context, listener);
        this.mAccessibilityManager = (AccessibilityManager) this.mApplicationContext.getSystemService("accessibility");
    }

    private static WindowAndroid createForTesting(Context context) {
        return new WindowAndroid(context);
    }

    public void setAndroidPermissionDelegate(AndroidPermissionDelegate androidPermissionDelegate) {
        this.mPermissionDelegate = androidPermissionDelegate;
    }

    public boolean showIntent(PendingIntent pendingIntent, IntentCallback intentCallback, Integer num) {
        return showCancelableIntent(pendingIntent, intentCallback, num) >= 0;
    }

    public boolean showIntent(Intent intent, IntentCallback intentCallback, Integer num) {
        return showCancelableIntent(intent, intentCallback, num) >= 0;
    }

    public int showCancelableIntent(PendingIntent pendingIntent, IntentCallback intentCallback, Integer num) {
        Log.d(TAG, "Can't show intent as context is not an Activity: " + pendingIntent);
        return -1;
    }

    public int showCancelableIntent(Intent intent, IntentCallback intentCallback, Integer num) {
        Log.d(TAG, "Can't show intent as context is not an Activity: " + intent);
        return -1;
    }

    public int showCancelableIntent(Callback<Integer> callback, IntentCallback intentCallback, Integer num) {
        Log.d(TAG, "Can't show intent as context is not an Activity");
        return -1;
    }

    public void cancelIntent(int i) {
        Log.d(TAG, "Can't cancel intent as context is not an Activity: " + i);
    }

    public boolean removeIntentCallback(IntentCallback intentCallback) {
        int iIndexOfValue = this.mOutstandingIntents.indexOfValue(intentCallback);
        if (iIndexOfValue < 0) {
            return false;
        }
        this.mOutstandingIntents.remove(iIndexOfValue);
        this.mIntentErrors.remove(Integer.valueOf(iIndexOfValue));
        return true;
    }

    public final boolean hasPermission(String str) {
        AndroidPermissionDelegate androidPermissionDelegate = this.mPermissionDelegate;
        return androidPermissionDelegate != null ? androidPermissionDelegate.hasPermission(str) : ApiCompatibilityUtils.checkPermission(this.mApplicationContext, str, Process.myPid(), Process.myUid()) == 0;
    }

    public final boolean canRequestPermission(String str) {
        AndroidPermissionDelegate androidPermissionDelegate = this.mPermissionDelegate;
        if (androidPermissionDelegate != null) {
            return androidPermissionDelegate.canRequestPermission(str);
        }
        Log.w(TAG, "Cannot determine the request permission state as the context is not an Activity");
        return false;
    }

    public final boolean isPermissionRevokedByPolicy(String str) {
        AndroidPermissionDelegate androidPermissionDelegate = this.mPermissionDelegate;
        if (androidPermissionDelegate != null) {
            return androidPermissionDelegate.isPermissionRevokedByPolicy(str);
        }
        Log.w(TAG, "Cannot determine the policy permission state as the context is not an Activity");
        return false;
    }

    public final void requestPermissions(String[] strArr, PermissionCallback permissionCallback) {
        AndroidPermissionDelegate androidPermissionDelegate = this.mPermissionDelegate;
        if (androidPermissionDelegate != null) {
            androidPermissionDelegate.requestPermissions(strArr, permissionCallback);
        } else {
            Log.w(TAG, "Cannot request permissions as the context is not an Activity");
        }
    }

    public void showError(String str) {
        if (str != null) {
            Toast.makeText(this.mApplicationContext, str, 0).show();
        }
    }

    public void showError(int i) {
        showError(this.mApplicationContext.getString(i));
    }

    protected void showCallbackNonExistentError(String str) {
        showError(str);
    }

    public void sendBroadcast(Intent intent) {
        this.mApplicationContext.sendBroadcast(intent);
    }

    public WeakReference<Activity> getActivity() {
        return new WeakReference<>(null);
    }

    public Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public void saveInstanceState(Bundle bundle) {
        bundle.putSerializable(WINDOW_CALLBACK_ERRORS, this.mIntentErrors);
    }

    public void restoreInstanceState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        Serializable serializable = bundle.getSerializable(WINDOW_CALLBACK_ERRORS);
        if (serializable instanceof HashMap) {
            this.mIntentErrors = (HashMap) serializable;
        }
    }

    public void onVisibilityChanged(boolean z) {
        long j = this.mNativeWindowAndroid;
        if (j == 0) {
            return;
        }
        nativeOnVisibilityChanged(j, z);
    }

    protected void onActivityStopped() {
        long j = this.mNativeWindowAndroid;
        if (j == 0) {
            return;
        }
        nativeOnActivityStopped(j);
    }

    protected void onActivityStarted() {
        long j = this.mNativeWindowAndroid;
        if (j == 0) {
            return;
        }
        nativeOnActivityStarted(j);
    }

    private void requestVSyncUpdate() {
        this.mVSyncMonitor.requestUpdate();
    }

    public boolean canResolveActivity(Intent intent) {
        return this.mApplicationContext.getPackageManager().queryIntentActivities(intent, 0).size() > 0;
    }

    public void destroy() {
        long j = this.mNativeWindowAndroid;
        if (j != 0) {
            nativeDestroy(j);
            this.mNativeWindowAndroid = 0L;
        }
        TouchExplorationMonitor touchExplorationMonitor = this.mTouchExplorationMonitor;
        if (touchExplorationMonitor != null) {
            touchExplorationMonitor.destroy();
        }
    }

    public long getNativePointer() {
        if (this.mNativeWindowAndroid == 0) {
            this.mNativeWindowAndroid = nativeInit();
        }
        return this.mNativeWindowAndroid;
    }

    public void setAnimationPlaceholderView(View view) {
        this.mAnimationPlaceholderView = view;
        this.mIsTouchExplorationEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
        refreshWillNotDraw();
        this.mTouchExplorationMonitor = new TouchExplorationMonitor();
    }

    public void setKeyboardAccessoryView(ViewGroup viewGroup) {
        this.mKeyboardAccessoryView = viewGroup;
    }

    public ViewGroup getKeyboardAccessoryView() {
        return this.mKeyboardAccessoryView;
    }

    public void addKeyboardVisibilityListener(KeyboardVisibilityListener keyboardVisibilityListener) {
        if (this.mKeyboardVisibilityListeners.isEmpty()) {
            registerKeyboardVisibilityCallbacks();
        }
        this.mKeyboardVisibilityListeners.add(keyboardVisibilityListener);
    }

    public void removeKeyboardVisibilityListener(KeyboardVisibilityListener keyboardVisibilityListener) {
        this.mKeyboardVisibilityListeners.remove(keyboardVisibilityListener);
        if (this.mKeyboardVisibilityListeners.isEmpty()) {
            unregisterKeyboardVisibilityCallbacks();
        }
    }

    protected void keyboardVisibilityPossiblyChanged(boolean z) {
        if (this.mIsKeyboardShowing == z) {
            return;
        }
        this.mIsKeyboardShowing = z;
        Iterator it = new LinkedList(this.mKeyboardVisibilityListeners).iterator();
        while (it.hasNext()) {
            ((KeyboardVisibilityListener) it.next()).keyboardVisibilityChanged(z);
        }
    }

    public void startAnimationOverContent(Animator animator) {
        if (this.mAnimationPlaceholderView == null) {
            return;
        }
        if (animator.isStarted()) {
            throw new IllegalArgumentException("Already started.");
        }
        if (!this.mAnimationsOverContent.add(animator)) {
            throw new IllegalArgumentException("Already Added.");
        }
        animator.start();
        refreshWillNotDraw();
        animator.addListener(new AnimatorListenerAdapter() { // from class: org.chromium.ui.base.WindowAndroid.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                animator2.removeListener(this);
                WindowAndroid.this.mAnimationsOverContent.remove(animator2);
                WindowAndroid.this.refreshWillNotDraw();
            }
        });
    }

    public WeakReference<Context> getContext() {
        return new WeakReference<>(this.mContextRef.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWillNotDraw() {
        boolean z = !this.mIsTouchExplorationEnabled && this.mAnimationsOverContent.isEmpty();
        if (this.mAnimationPlaceholderView.willNotDraw() != z) {
            this.mAnimationPlaceholderView.setWillNotDraw(z);
        }
    }
}
