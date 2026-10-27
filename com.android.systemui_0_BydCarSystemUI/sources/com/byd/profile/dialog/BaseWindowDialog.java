package com.byd.profile.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.profile.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseWindowDialog extends FloatingWindow implements View.OnClickListener, ProfileDialog {
    protected OnDialogClickListener mOnDialogClickListener;
    protected View mRoot;

    public interface OnDialogClickListener {
        void onConfirmClick();
    }

    protected abstract void execute();

    protected abstract void initContent(View view);

    protected abstract void initData();

    protected abstract int initLayoutRes();

    protected abstract int initTitle();

    @Override // com.byd.profile.dialog.FloatingWindow
    protected abstract boolean isFullScreen();

    protected abstract void onViewClick(View v);

    protected abstract void reset();

    public BaseWindowDialog(Context context) {
        super(context);
    }

    @Override // com.byd.profile.dialog.FloatingWindow
    public void initView() {
        initData();
        this.mRoot = LayoutInflater.from(this.mContext).inflate(this.isFullScreen ? R.layout.dialog_fullscreen : R.layout.dialog_base, (ViewGroup) null);
        if (this.isFullScreen) {
            this.mRoot.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() { // from class: com.byd.profile.dialog.BaseWindowDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    BydLogHelper.logD("click outside");
                    BaseWindowDialog.this.dismiss();
                }
            });
            ConstraintLayout constraintLayout = (ConstraintLayout) this.mRoot.findViewById(R.id.content);
            LayoutInflater.from(this.mContext).inflate(R.layout.dialog_base, (ViewGroup) constraintLayout, true);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
            layoutParams.width = this.mWidth;
            layoutParams.height = this.mHeight;
            constraintLayout.setLayoutParams(layoutParams);
        }
        int iInitTitle = initTitle();
        if (iInitTitle != 0) {
            ((TextView) this.mRoot.findViewById(R.id.tv_title)).setText(iInitTitle);
        }
        this.mRoot.findViewById(R.id.btn_reset).setOnClickListener(this);
        this.mRoot.findViewById(R.id.btn_ok).setOnClickListener(this);
        this.mRoot.findViewById(R.id.btn_cancel).setOnClickListener(this);
        LayoutInflater.from(this.mContext).inflate(initLayoutRes(), (ViewGroup) this.mRoot.findViewById(R.id.ly_content), true);
        initContent(this.mRoot);
    }

    @Override // com.byd.profile.dialog.ProfileDialog
    public void show() {
        if (this.mRoot == null) {
            initView();
        }
        View view = this.mRoot;
        if (view != null) {
            show(view);
        }
    }

    @Override // com.byd.profile.dialog.ProfileDialog
    public void dismiss() {
        View view = this.mRoot;
        if (view != null) {
            dismiss(view);
        }
        this.mRoot = null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_ok) {
            execute();
            OnDialogClickListener onDialogClickListener = this.mOnDialogClickListener;
            if (onDialogClickListener != null) {
                onDialogClickListener.onConfirmClick();
            }
            dismiss();
        }
        if (id == R.id.btn_cancel) {
            dismiss();
        }
        if (id == R.id.btn_reset) {
            reset();
        }
        onViewClick(v);
    }

    public void setOnDialogButtonClickListener(OnDialogClickListener onDialogClickListener) {
        this.mOnDialogClickListener = onDialogClickListener;
    }
}
