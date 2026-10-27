package com.android.systemui.toast;

import android.app.INotificationManager;
import android.app.ITransientNotificationCallback;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.IAccessibilityManager;
import android.widget.TextView;
import android.widget.ToastPresenter;
import com.android.systemui.R;
import com.android.systemui.SystemUI;
import com.android.systemui.statusbar.CommandQueue;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes.dex */
@Singleton
public class ToastUI extends SystemUI implements CommandQueue.Callbacks {
    private static final String TAG = "ToastUI";
    private IAccessibilityManager mAccessibilityManager;
    private ITransientNotificationCallback mCallback;
    private CommandQueue mCommandQueue;
    private int mGravity;
    private INotificationManager mNotificationManager;
    private ToastPresenter mPresenter;
    private int mY;
    private int mYPort;

    @Override // com.android.systemui.SystemUI
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    @Override // com.android.systemui.SystemUI
    protected void onBootCompleted() {
    }

    @Override // com.android.systemui.SystemUI
    protected void onConfigurationChanged(Configuration newConfig) {
    }

    @Inject
    public ToastUI(Context context, CommandQueue commandQueue) {
        this(context, commandQueue, INotificationManager.Stub.asInterface(ServiceManager.getService("notification")), IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility")));
    }

    ToastUI(Context context, CommandQueue commandQueue, INotificationManager notificationManager, IAccessibilityManager accessibilityManager) {
        this.mCommandQueue = commandQueue;
        this.mNotificationManager = notificationManager;
        this.mAccessibilityManager = accessibilityManager;
        Resources resources = this.mContext.getResources();
        this.mGravity = 48;
        this.mY = resources.getDimensionPixelSize(R.dimen.dp_72);
        this.mYPort = resources.getDimensionPixelSize(R.dimen.dp_190);
    }

    public ToastUI() {
    }

    @Override // com.android.systemui.SystemUI
    public void start() {
        this.mCommandQueue = (CommandQueue) getComponent(CommandQueue.class);
        this.mNotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        this.mAccessibilityManager = IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility"));
        Resources resources = this.mContext.getResources();
        this.mGravity = 48;
        this.mY = resources.getDimensionPixelSize(R.dimen.dp_72);
        this.mYPort = resources.getDimensionPixelSize(R.dimen.dp_190);
        this.mCommandQueue.addCallback(this);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void showToast(int uid, String packageName, IBinder token, CharSequence text, IBinder windowToken, int duration, ITransientNotificationCallback callback) {
        if (this.mPresenter != null) {
            hideCurrentToast();
        }
        CharSequence charSequence = text;
        BydLogHelper.logD("text:" + ((Object) charSequence) + " , packageName:" + packageName);
        Context contextCreateContextAsUser = this.mContext.createContextAsUser(UserHandle.getUserHandleForUid(uid), 0);
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.view_custom_toast, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_content);
        if (TextUtils.isEmpty(text)) {
            charSequence = "";
        }
        textView.setText(charSequence);
        this.mCallback = callback;
        this.mPresenter = new ToastPresenter(contextCreateContextAsUser, this.mAccessibilityManager, this.mNotificationManager, packageName);
        if (isLand(this.mContext)) {
            this.mPresenter.show(viewInflate, token, windowToken, duration, this.mGravity, 0, this.mY, 0.0f, 0.0f, this.mCallback);
        } else {
            this.mPresenter.show(viewInflate, token, windowToken, duration, this.mGravity, 0, this.mYPort, 0.0f, 0.0f, this.mCallback);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void hideToast(String packageName, IBinder token) {
        ToastPresenter toastPresenter = this.mPresenter;
        if (toastPresenter == null || !Objects.equals(toastPresenter.getPackageName(), packageName) || !Objects.equals(this.mPresenter.getToken(), token)) {
            Log.w(TAG, "Attempt to hide non-current toast from package " + packageName);
        } else {
            hideCurrentToast();
        }
    }

    private void hideCurrentToast() {
        ToastPresenter toastPresenter = this.mPresenter;
        if (toastPresenter != null) {
            toastPresenter.hide(this.mCallback);
            this.mPresenter = null;
        }
    }

    public boolean isLand(Context context) {
        return context.getResources().getConfiguration().orientation != 1;
    }
}
