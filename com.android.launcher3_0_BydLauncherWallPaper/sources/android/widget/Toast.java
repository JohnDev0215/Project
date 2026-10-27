package android.widget;

import android.app.INotificationManager;
import android.app.ITransientNotification;
import android.app.ITransientNotificationCallback;
import android.compat.Compatibility;
import android.content.Context;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.IAccessibilityManager;
import com.android.internal.R;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Toast {
    private static final long CHANGE_TEXT_TOASTS_IN_THE_SYSTEM = 147798919;
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;
    static final String TAG = "Toast";
    private static final int TOAST_LANDSCAPE_Y_OFFSET = 108;
    private static final int TOAST_PORTRAIT_Y_OFFSET = 285;
    static final boolean localLOGV = false;
    private static INotificationManager sService;
    private final List<Callback> mCallbacks;
    private final Context mContext;
    int mDuration;
    private final Handler mHandler;
    private View mNextView;
    final TN mTN;
    private CharSequence mText;
    private final Binder mToken;

    public static abstract class Callback {
        public void onToastHidden() {
        }

        public void onToastShown() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public Toast(Context context) {
        this(context, null);
    }

    public Toast(Context context, Looper looper) {
        this.mContext = context;
        Binder binder = new Binder();
        this.mToken = binder;
        Looper looper2 = getLooper(looper);
        this.mHandler = new Handler(looper2);
        ArrayList arrayList = new ArrayList();
        this.mCallbacks = arrayList;
        TN tn = new TN(context, context.getPackageName(), binder, arrayList, looper2);
        this.mTN = tn;
        if (getOrientation() == 2) {
            tn.mY = 108;
        } else if (getOrientation() == 1) {
            tn.mY = 285;
        }
        tn.mGravity = context.getResources().getInteger(R.integer.config_toastSystemUiGravity);
    }

    private int getOrientation() {
        return this.mContext.getResources().getConfiguration().orientation;
    }

    private Looper getLooper(Looper looper) {
        return looper != null ? looper : (Looper) Preconditions.checkNotNull(Looper.myLooper(), "Can't toast on a thread that has not called Looper.prepare()");
    }

    public void show() {
        if (Compatibility.isChangeEnabled(CHANGE_TEXT_TOASTS_IN_THE_SYSTEM)) {
            Preconditions.checkState((this.mNextView == null && this.mText == null) ? false : true, "You must either set a text or a view");
        } else if (this.mNextView == null) {
            throw new RuntimeException("setView must have been called");
        }
        INotificationManager service = getService();
        String opPackageName = this.mContext.getOpPackageName();
        TN tn = this.mTN;
        tn.mNextView = this.mNextView;
        int displayId = this.mContext.getDisplayId();
        try {
            if (!Compatibility.isChangeEnabled(CHANGE_TEXT_TOASTS_IN_THE_SYSTEM) || this.mNextView != null) {
                service.enqueueToast(opPackageName, this.mToken, tn, this.mDuration, displayId);
            } else {
                service.enqueueTextToast(opPackageName, this.mToken, this.mText, this.mDuration, displayId, new CallbackBinder(this.mCallbacks, this.mHandler));
            }
        } catch (RemoteException unused) {
        }
    }

    public void cancel() {
        if (Compatibility.isChangeEnabled(CHANGE_TEXT_TOASTS_IN_THE_SYSTEM) && this.mNextView == null) {
            try {
                getService().cancelToast(this.mContext.getOpPackageName(), this.mToken);
            } catch (RemoteException unused) {
            }
        } else {
            this.mTN.cancel();
        }
    }

    @Deprecated
    public void setView(View view) {
        this.mNextView = view;
    }

    @Deprecated
    public View getView() {
        return this.mNextView;
    }

    public void setDuration(int i) {
        this.mDuration = i;
        this.mTN.mDuration = i;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setMargin(float f, float f2) {
        if (isSystemRenderedTextToast()) {
            Log.e(TAG, "setMargin() shouldn't be called on text toasts, the values won't be used");
        }
        this.mTN.mHorizontalMargin = f;
        this.mTN.mVerticalMargin = f2;
    }

    public float getHorizontalMargin() {
        if (isSystemRenderedTextToast()) {
            Log.e(TAG, "getHorizontalMargin() shouldn't be called on text toasts, the result may not reflect actual values.");
        }
        return this.mTN.mHorizontalMargin;
    }

    public float getVerticalMargin() {
        if (isSystemRenderedTextToast()) {
            Log.e(TAG, "getVerticalMargin() shouldn't be called on text toasts, the result may not reflect actual values.");
        }
        return this.mTN.mVerticalMargin;
    }

    public void setGravity(int i, int i2, int i3) {
        if (isSystemRenderedTextToast()) {
            Log.e(TAG, "setGravity() shouldn't be called on text toasts, the values won't be used");
        }
        this.mTN.mGravity = i;
        this.mTN.mX = i2;
        this.mTN.mY = i3;
    }

    public int getGravity() {
        if (isSystemRenderedTextToast()) {
            Log.e(TAG, "getGravity() shouldn't be called on text toasts, the result may not reflect actual values.");
        }
        return this.mTN.mGravity;
    }

    public int getXOffset() {
        if (isSystemRenderedTextToast()) {
            Log.e(TAG, "getXOffset() shouldn't be called on text toasts, the result may not reflect actual values.");
        }
        return this.mTN.mX;
    }

    public int getYOffset() {
        if (isSystemRenderedTextToast()) {
            Log.e(TAG, "getYOffset() shouldn't be called on text toasts, the result may not reflect actual values.");
        }
        return this.mTN.mY;
    }

    private boolean isSystemRenderedTextToast() {
        return Compatibility.isChangeEnabled(CHANGE_TEXT_TOASTS_IN_THE_SYSTEM) && this.mNextView == null;
    }

    public void addCallback(Callback callback) {
        Preconditions.checkNotNull(callback);
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(callback);
        }
    }

    public void removeCallback(Callback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(callback);
        }
    }

    public WindowManager.LayoutParams getWindowParams() {
        if (Compatibility.isChangeEnabled(CHANGE_TEXT_TOASTS_IN_THE_SYSTEM) && this.mNextView == null) {
            return null;
        }
        return this.mTN.mParams;
    }

    public static Toast makeText(Context context, CharSequence charSequence, int i) {
        return makeText(context, null, charSequence, i);
    }

    public static Toast makeText(Context context, Looper looper, CharSequence charSequence, int i) {
        if (Compatibility.isChangeEnabled(CHANGE_TEXT_TOASTS_IN_THE_SYSTEM)) {
            Toast toast = new Toast(context, looper);
            toast.mText = charSequence;
            toast.mDuration = i;
            return toast;
        }
        Toast toast2 = new Toast(context, looper);
        toast2.mNextView = ToastPresenter.getTextToastView(context, charSequence);
        toast2.mDuration = i;
        return toast2;
    }

    public static Toast makeText(Context context, int i, int i2) throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(i), i2);
    }

    public void setText(int i) {
        setText(this.mContext.getText(i));
    }

    public void setText(CharSequence charSequence) {
        if (Compatibility.isChangeEnabled(CHANGE_TEXT_TOASTS_IN_THE_SYSTEM)) {
            if (this.mNextView != null) {
                throw new IllegalStateException("Text provided for custom toast, remove previous setView() calls if you want a text toast instead.");
            }
            this.mText = charSequence;
            return;
        }
        View view = this.mNextView;
        if (view == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        TextView textView = (TextView) view.findViewById(16908299);
        if (textView == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        textView.setText(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static INotificationManager getService() {
        INotificationManager iNotificationManager = sService;
        if (iNotificationManager != null) {
            return iNotificationManager;
        }
        INotificationManager iNotificationManagerAsInterface = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        sService = iNotificationManagerAsInterface;
        return iNotificationManagerAsInterface;
    }

    private static class TN extends ITransientNotification.Stub {
        private static final int CANCEL = 2;
        private static final int HIDE = 1;
        private static final int SHOW = 0;
        private final List<Callback> mCallbacks;
        int mDuration;
        int mGravity;
        final Handler mHandler;
        float mHorizontalMargin;
        View mNextView;
        final String mPackageName;
        private final WindowManager.LayoutParams mParams;
        private final ToastPresenter mPresenter;
        final Binder mToken;
        float mVerticalMargin;
        View mView;
        WindowManager mWM;
        int mX;
        int mY;

        TN(Context context, String str, Binder binder, List<Callback> list, Looper looper) {
            ToastPresenter toastPresenter = new ToastPresenter(context, IAccessibilityManager.Stub.asInterface(ServiceManager.getService(Context.ACCESSIBILITY_SERVICE)), Toast.getService(), str);
            this.mPresenter = toastPresenter;
            this.mParams = toastPresenter.getLayoutParams();
            this.mPackageName = str;
            this.mToken = binder;
            this.mCallbacks = list;
            this.mHandler = new Handler(looper, null) { // from class: android.widget.Toast.TN.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i == 0) {
                        TN.this.handleShow((IBinder) message.obj);
                    } else if (i == 1) {
                        TN.this.handleHide();
                        TN.this.mNextView = null;
                    } else {
                        if (i != 2) {
                            return;
                        }
                        TN.this.handleHide();
                        TN.this.mNextView = null;
                        try {
                            Toast.getService().cancelToast(TN.this.mPackageName, TN.this.mToken);
                        } catch (RemoteException unused) {
                        }
                    }
                }
            };
        }

        private List<Callback> getCallbacks() {
            ArrayList arrayList;
            synchronized (this.mCallbacks) {
                arrayList = new ArrayList(this.mCallbacks);
            }
            return arrayList;
        }

        @Override // android.app.ITransientNotification
        public void show(IBinder iBinder) {
            this.mHandler.obtainMessage(0, iBinder).sendToTarget();
        }

        @Override // android.app.ITransientNotification
        public void hide() {
            this.mHandler.obtainMessage(1).sendToTarget();
        }

        public void cancel() {
            this.mHandler.obtainMessage(2).sendToTarget();
        }

        public void handleShow(IBinder iBinder) {
            if (this.mHandler.hasMessages(2) || this.mHandler.hasMessages(1) || this.mView == this.mNextView) {
                return;
            }
            handleHide();
            View view = this.mNextView;
            this.mView = view;
            this.mPresenter.show(view, this.mToken, iBinder, this.mDuration, this.mGravity, this.mX, this.mY, this.mHorizontalMargin, this.mVerticalMargin, new CallbackBinder(getCallbacks(), this.mHandler));
        }

        public void handleHide() {
            View view = this.mView;
            if (view != null) {
                Preconditions.checkState(view == this.mPresenter.getView(), "Trying to hide toast view different than the last one displayed");
                this.mPresenter.hide(new CallbackBinder(getCallbacks(), this.mHandler));
                this.mView = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CallbackBinder extends ITransientNotificationCallback.Stub {
        private final List<Callback> mCallbacks;
        private final Handler mHandler;

        private CallbackBinder(List<Callback> list, Handler handler) {
            this.mCallbacks = list;
            this.mHandler = handler;
        }

        @Override // android.app.ITransientNotificationCallback
        public void onToastShown() {
            this.mHandler.post(new Runnable() { // from class: android.widget.-$$Lambda$Toast$CallbackBinder$_WZIwOgbY1iOPlx8lbYJS9mmHeU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onToastShown$0$Toast$CallbackBinder();
                }
            });
        }

        public /* synthetic */ void lambda$onToastShown$0$Toast$CallbackBinder() {
            Iterator<Callback> it = getCallbacks().iterator();
            while (it.hasNext()) {
                it.next().onToastShown();
            }
        }

        @Override // android.app.ITransientNotificationCallback
        public void onToastHidden() {
            this.mHandler.post(new Runnable() { // from class: android.widget.-$$Lambda$Toast$CallbackBinder$cDR2HpJpfWV3dM0Se2WKGwCszko
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onToastHidden$1$Toast$CallbackBinder();
                }
            });
        }

        public /* synthetic */ void lambda$onToastHidden$1$Toast$CallbackBinder() {
            Iterator<Callback> it = getCallbacks().iterator();
            while (it.hasNext()) {
                it.next().onToastHidden();
            }
        }

        private List<Callback> getCallbacks() {
            ArrayList arrayList;
            synchronized (this.mCallbacks) {
                arrayList = new ArrayList(this.mCallbacks);
            }
            return arrayList;
        }
    }
}
