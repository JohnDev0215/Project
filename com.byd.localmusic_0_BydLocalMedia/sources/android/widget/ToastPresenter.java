package android.widget;

import android.app.INotificationManager;
import android.app.ITransientNotificationCallback;
import android.content.Context;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.IAccessibilityManager;
import com.android.internal.R;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public class ToastPresenter {
    private static final long LONG_DURATION_TIMEOUT = 7000;
    private static final long SHORT_DURATION_TIMEOUT = 4000;
    private static final String TAG = "ToastPresenter";
    public static final int TEXT_TOAST_LAYOUT = 17367342;
    private static final String WINDOW_TITLE = "Toast";
    private final AccessibilityManager mAccessibilityManager;
    private final Context mContext;
    private final INotificationManager mNotificationManager;
    private final String mPackageName;
    private final WindowManager.LayoutParams mParams = createLayoutParams();
    private final Resources mResources;
    private IBinder mToken;
    private View mView;
    private final WindowManager mWindowManager;

    public static View getTextToastView(Context context, CharSequence charSequence) {
        View viewInflate = LayoutInflater.from(context).inflate(17367342, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(16908299)).setText(charSequence);
        return viewInflate;
    }

    public ToastPresenter(Context context, IAccessibilityManager iAccessibilityManager, INotificationManager iNotificationManager, String str) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mNotificationManager = iNotificationManager;
        this.mPackageName = str;
        this.mAccessibilityManager = new AccessibilityManager(context, iAccessibilityManager, context.getUserId());
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return this.mParams;
    }

    public View getView() {
        return this.mView;
    }

    public IBinder getToken() {
        return this.mToken;
    }

    private WindowManager.LayoutParams createLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 16973828;
        layoutParams.type = 2005;
        layoutParams.setFitInsetsIgnoringVisibility(true);
        layoutParams.setTitle(WINDOW_TITLE);
        layoutParams.flags = 152;
        setShowForAllUsersIfApplicable(layoutParams, this.mPackageName);
        return layoutParams;
    }

    private void adjustLayoutParams(WindowManager.LayoutParams layoutParams, IBinder iBinder, int i, int i2, int i3, int i4, float f, float f2) {
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, this.mResources.getConfiguration().getLayoutDirection());
        layoutParams.gravity = absoluteGravity;
        if ((absoluteGravity & 7) == 7) {
            layoutParams.horizontalWeight = 1.0f;
        }
        if ((absoluteGravity & 112) == 112) {
            layoutParams.verticalWeight = 1.0f;
        }
        layoutParams.x = i3;
        layoutParams.y = i4;
        layoutParams.horizontalMargin = f;
        layoutParams.verticalMargin = f2;
        layoutParams.packageName = this.mContext.getPackageName();
        layoutParams.hideTimeoutMilliseconds = i == 1 ? LONG_DURATION_TIMEOUT : SHORT_DURATION_TIMEOUT;
        layoutParams.token = iBinder;
    }

    private void setShowForAllUsersIfApplicable(WindowManager.LayoutParams layoutParams, String str) {
        if (isCrossUserPackage(str)) {
            layoutParams.privateFlags = 16;
        }
    }

    private boolean isCrossUserPackage(String str) {
        return ArrayUtils.contains(this.mResources.getStringArray(R.array.config_toastCrossUserPackages), str);
    }

    public void show(View view, IBinder iBinder, IBinder iBinder2, int i, int i2, int i3, int i4, float f, float f2, ITransientNotificationCallback iTransientNotificationCallback) {
        Preconditions.checkState(this.mView == null, "Only one toast at a time is allowed, call hide() first.");
        this.mView = view;
        this.mToken = iBinder;
        adjustLayoutParams(this.mParams, iBinder2, i, i2, i3, i4, f, f2);
        if (this.mView.getParent() != null) {
            this.mWindowManager.removeView(this.mView);
        }
        try {
            this.mWindowManager.addView(this.mView, this.mParams);
            trySendAccessibilityEvent(this.mView, this.mPackageName);
            if (iTransientNotificationCallback != null) {
                try {
                    iTransientNotificationCallback.onToastShown();
                } catch (RemoteException e) {
                    Log.w(TAG, "Error calling back " + this.mPackageName + " to notify onToastShow()", e);
                }
            }
        } catch (WindowManager.BadTokenException e2) {
            Log.w(TAG, "Error while attempting to show toast from " + this.mPackageName, e2);
        }
    }

    public void hide(ITransientNotificationCallback iTransientNotificationCallback) {
        Preconditions.checkState(this.mView != null, "No toast to hide.");
        if (this.mView.getParent() != null) {
            this.mWindowManager.removeViewImmediate(this.mView);
        }
        try {
            this.mNotificationManager.finishToken(this.mPackageName, this.mToken);
        } catch (RemoteException e) {
            Log.w(TAG, "Error finishing toast window token from package " + this.mPackageName, e);
        }
        if (iTransientNotificationCallback != null) {
            try {
                iTransientNotificationCallback.onToastHidden();
            } catch (RemoteException e2) {
                Log.w(TAG, "Error calling back " + this.mPackageName + " to notify onToastHide()", e2);
            }
        }
        this.mView = null;
        this.mToken = null;
    }

    public void trySendAccessibilityEvent(View view, String str) {
        if (this.mAccessibilityManager.isEnabled()) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(64);
            accessibilityEventObtain.setClassName(Toast.class.getName());
            accessibilityEventObtain.setPackageName(str);
            view.dispatchPopulateAccessibilityEvent(accessibilityEventObtain);
            this.mAccessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
        }
    }
}
