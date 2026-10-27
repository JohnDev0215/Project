package com.byd.card.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import com.byd.card.drag.ApplicationInfo;
import com.byd.card.view.activity.MoreAppActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseDialog extends Dialog implements DialogInterface.OnDismissListener, DialogInterface.OnCancelListener {
    public MoreAppActivity activity;
    public ApplicationInfo appInfo;

    public abstract void finishInitView(Dialog view);

    public abstract void onViewClick(View view);

    public BaseDialog(Context context) {
        super(context);
    }

    protected BaseDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public BaseDialog(MoreAppActivity activity, int resId, ApplicationInfo applicationInfo) {
        super(activity);
        if (activity == null) {
            return;
        }
        this.activity = activity;
        this.appInfo = applicationInfo;
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        initView(resId);
        setCanceledOnTouchOutside(false);
        activity.startAD();
        setListener();
    }

    private void initView(int resId) {
        setContentView(resId);
        finishInitView(this);
    }

    private void setListener() {
        setOnDismissListener(this);
        setOnCancelListener(this);
    }

    public void setOnClickListener(View view) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.view.dialog.BaseDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BaseDialog.this.onViewClick(v);
                if (BaseDialog.this.activity != null) {
                    BaseDialog.this.activity.startAD();
                }
            }
        });
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ev.getAction();
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        MoreAppActivity moreAppActivity = this.activity;
        if (moreAppActivity != null) {
            moreAppActivity.startAD();
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        MoreAppActivity moreAppActivity = this.activity;
        if (moreAppActivity != null) {
            moreAppActivity.startAD();
        }
    }
}
