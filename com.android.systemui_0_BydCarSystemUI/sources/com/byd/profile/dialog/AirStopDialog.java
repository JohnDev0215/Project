package com.byd.profile.dialog;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.profile.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class AirStopDialog extends FloatingWindow implements View.OnClickListener {
    private static final int WHAT_UPDATE = 19;
    private static final int WINDOW_STATE_VENTILATE = 5;
    private int count;
    protected Button mBtnOk;
    private Handler mHandler;
    protected View mRoot;

    @Override // com.byd.profile.dialog.FloatingWindow
    protected boolean isFullScreen() {
        return true;
    }

    static /* synthetic */ int access$010(AirStopDialog airStopDialog) {
        int i = airStopDialog.count;
        airStopDialog.count = i - 1;
        return i;
    }

    public AirStopDialog(Context context) {
        super(context);
        this.count = 8;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.profile.dialog.AirStopDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 19) {
                    if (AirStopDialog.this.count <= 0) {
                        AirStopDialog.this.openWindow();
                        AirStopDialog.this.dismiss();
                    } else {
                        AirStopDialog.this.mBtnOk.setText(AirStopDialog.this.mContext.getString(R.string.btn_i_known, Integer.valueOf(AirStopDialog.access$010(AirStopDialog.this))));
                        AirStopDialog.this.mHandler.sendEmptyMessageDelayed(19, 1000L);
                    }
                }
            }
        };
    }

    @Override // com.byd.profile.dialog.FloatingWindow
    protected void setWidthAndHeight(int[] size) {
        size[0] = R.dimen.dp_560;
        size[1] = R.dimen.dp_300;
    }

    @Override // com.byd.profile.dialog.FloatingWindow
    public void initView() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.dialog_fullscreen, (ViewGroup) null);
        this.mRoot = viewInflate;
        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate.findViewById(R.id.content);
        LayoutInflater.from(this.mContext).inflate(R.layout.dialog_air_stop, (ViewGroup) constraintLayout, true);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
        layoutParams.width = this.mWidth;
        layoutParams.height = this.mHeight;
        constraintLayout.setLayoutParams(layoutParams);
        Button button = (Button) this.mRoot.findViewById(R.id.btn_ok);
        this.mBtnOk = button;
        button.setOnClickListener(this);
        this.mBtnOk.setText(this.mContext.getString(R.string.btn_i_known, Integer.valueOf(this.count)));
        this.mHandler.sendEmptyMessage(19);
    }

    public void show() {
        if (this.mRoot == null) {
            initView();
        }
        View view = this.mRoot;
        if (view != null) {
            show(view);
        }
    }

    public void dismiss() {
        this.mHandler.removeMessages(19);
        View view = this.mRoot;
        if (view != null) {
            dismiss(view);
        }
        this.mRoot = null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok) {
            openWindow();
            dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openWindow() {
        BydLogHelper.logD("openWindow");
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
        bYDAutoBodyworkDevice.setWindowControlState(1, 5);
        bYDAutoBodyworkDevice.setWindowControlState(2, 5);
    }
}
