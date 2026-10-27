package com.byd.profile.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.byd.profile.R;
import com.byd.profile.dialog.BaseWindowDialog;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseDialog extends Dialog implements View.OnClickListener, ProfileDialog {
    protected static final int HEIGHT = 1;
    protected static final int WIDTH = 0;
    protected Button mBtnOk;
    protected Context mContext;
    protected int mGravity;
    protected int mHeight;
    protected int mLocationX;
    protected int mLocationY;
    protected BaseWindowDialog.OnDialogClickListener mOnDialogClickListener;
    protected int mWidth;

    public interface OnDialogClickListener {
        void onConfirmClick();
    }

    protected abstract void execute();

    protected abstract void initContent(View view);

    protected abstract void initData();

    protected abstract int initLayoutRes();

    protected abstract int initTitle();

    protected abstract void onViewClick(View v);

    protected abstract void reset();

    protected abstract void setWidthAndHeight(int[] size);

    public BaseDialog(Context context) {
        super(context, R.style.profile_dialog_style);
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_base);
        setAttributes();
        addFlag();
        setCanceledOnTouchOutside(true);
        initData();
        initView();
    }

    private void addFlag() {
        getWindow().addFlags(402653440);
    }

    private void setAttributes() {
        initSize();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        int i = this.mWidth;
        if (i != 0 && this.mHeight != 0) {
            attributes.width = i;
            attributes.height = this.mHeight;
        } else {
            attributes.width = -2;
            attributes.height = -2;
        }
        int i2 = this.mLocationX;
        if (i2 != 0) {
            attributes.x = i2;
        }
        int i3 = this.mLocationY;
        if (i3 != 0) {
            attributes.y = i3;
        }
        attributes.gravity = this.mGravity;
        attributes.format = -3;
        attributes.dimAmount = 0.5f;
        if (Build.VERSION.SDK_INT >= 26) {
            attributes.type = 2024;
        } else {
            attributes.type = 2003;
        }
        attributes.packageName = this.mContext.getPackageName();
        getWindow().addFlags(131072);
        getWindow().setAttributes(attributes);
    }

    private void initSize() {
        int[] iArr = new int[2];
        setWidthAndHeight(iArr);
        this.mWidth = iArr[0] == 0 ? -2 : this.mContext.getResources().getDimensionPixelSize(iArr[0]);
        this.mHeight = iArr[1] != 0 ? this.mContext.getResources().getDimensionPixelSize(iArr[1]) : -2;
    }

    private void initView() {
        int iInitTitle = initTitle();
        if (iInitTitle != 0) {
            ((TextView) findViewById(R.id.tv_title)).setText(iInitTitle);
        }
        initButtons();
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.ly_content);
        LayoutInflater.from(getContext()).inflate(initLayoutRes(), viewGroup, true);
        initContent(viewGroup);
    }

    protected void initButtons() {
        Button button = (Button) findViewById(R.id.btn_ok);
        this.mBtnOk = button;
        button.setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_ok) {
            execute();
            BaseWindowDialog.OnDialogClickListener onDialogClickListener = this.mOnDialogClickListener;
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

    public void setOnDialogButtonClickListener(BaseWindowDialog.OnDialogClickListener onDialogClickListener) {
        this.mOnDialogClickListener = onDialogClickListener;
    }
}
