package com.byd.profile.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.byd.profile.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseFullscreenDialog extends FloatingWindow implements ProfileDialog {
    protected OnDialogClickListener mOnDialogClickListener;
    protected View mRoot;

    public interface OnDialogClickListener {
        void onConfirmClick();
    }

    protected abstract void initContent(View view);

    protected abstract void initData();

    protected abstract int initLayoutRes();

    protected abstract int initTitle();

    @Override // com.byd.profile.dialog.FloatingWindow
    protected boolean isFullScreen() {
        return true;
    }

    protected abstract void onViewAttach();

    protected abstract void onViewDetach();

    public BaseFullscreenDialog(Context context) {
        super(context);
    }

    @Override // com.byd.profile.dialog.FloatingWindow
    protected void initView() {
        initData();
        View viewInflate = LayoutInflater.from(this.mContext).inflate(initLayoutRes(), (ViewGroup) null);
        this.mRoot = viewInflate;
        viewInflate.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() { // from class: com.byd.profile.dialog.BaseFullscreenDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BaseFullscreenDialog.this.dismiss();
            }
        });
        int iInitTitle = initTitle();
        if (iInitTitle != 0) {
            ((TextView) this.mRoot.findViewById(R.id.tv_title)).setText(iInitTitle);
        }
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
            onViewAttach();
        }
    }

    @Override // com.byd.profile.dialog.ProfileDialog
    public void dismiss() {
        onViewDetach();
        View view = this.mRoot;
        if (view != null) {
            dismiss(view);
        }
        this.mRoot = null;
    }

    public void setOnDialogButtonClickListener(OnDialogClickListener onDialogClickListener) {
        this.mOnDialogClickListener = onDialogClickListener;
    }
}
