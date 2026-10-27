package org.chromium.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.base.SysUtils;

/* JADX INFO: loaded from: classes4.dex */
public class Toast {
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;
    private ViewGroup mSWLayout;
    private android.widget.Toast mToast;

    public Toast(Context context) {
        this(context, new android.widget.Toast(context));
    }

    private Toast(Context context, android.widget.Toast toast) {
        this.mToast = toast;
        if (SysUtils.isLowEndDevice() && context.getApplicationInfo().targetSdkVersion >= 21 && isHWAccelerationDisabled(context)) {
            this.mSWLayout = new FrameLayout(new ContextWrapper(context) { // from class: org.chromium.ui.widget.Toast.1
                @Override // android.content.ContextWrapper, android.content.Context
                public ApplicationInfo getApplicationInfo() {
                    ApplicationInfo applicationInfo = new ApplicationInfo(super.getApplicationInfo());
                    applicationInfo.targetSdkVersion = 19;
                    return applicationInfo;
                }
            });
            setView(toast.getView());
        }
    }

    public android.widget.Toast getAndroidToast() {
        return this.mToast;
    }

    public void show() {
        this.mToast.show();
    }

    public void cancel() {
        this.mToast.cancel();
    }

    public void setView(View view) {
        ViewGroup viewGroup = this.mSWLayout;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            if (view != null) {
                this.mSWLayout.addView(view, -2, -2);
                this.mToast.setView(this.mSWLayout);
                return;
            } else {
                this.mToast.setView(null);
                return;
            }
        }
        this.mToast.setView(view);
    }

    public View getView() {
        if (this.mToast.getView() == null) {
            return null;
        }
        ViewGroup viewGroup = this.mSWLayout;
        if (viewGroup != null) {
            return viewGroup.getChildAt(0);
        }
        return this.mToast.getView();
    }

    public void setDuration(int i) {
        this.mToast.setDuration(i);
    }

    public int getDuration() {
        return this.mToast.getDuration();
    }

    public void setMargin(float f, float f2) {
        this.mToast.setMargin(f, f2);
    }

    public float getHorizontalMargin() {
        return this.mToast.getHorizontalMargin();
    }

    public float getVerticalMargin() {
        return this.mToast.getVerticalMargin();
    }

    public void setGravity(int i, int i2, int i3) {
        this.mToast.setGravity(i, i2, i3);
    }

    public int getGravity() {
        return this.mToast.getGravity();
    }

    public int getXOffset() {
        return this.mToast.getXOffset();
    }

    public int getYOffset() {
        return this.mToast.getYOffset();
    }

    public void setText(int i) {
        this.mToast.setText(i);
    }

    public void setText(CharSequence charSequence) {
        this.mToast.setText(charSequence);
    }

    public static Toast makeText(Context context, CharSequence charSequence, int i) {
        return new Toast(context, android.widget.Toast.makeText(context, charSequence, i));
    }

    public static Toast makeText(Context context, int i, int i2) throws Resources.NotFoundException {
        return new Toast(context, android.widget.Toast.makeText(context, i, i2));
    }

    private static Activity getActivity(Context context) {
        while (context != null) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private static boolean isHWAccelerationDisabled(Context context) {
        Activity activity = getActivity(context);
        if (activity == null) {
            return false;
        }
        try {
            return (activity.getPackageManager().getActivityInfo(activity.getComponentName(), 0).flags & 512) == 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
