package com.byd.uereport.dialog.screencast;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byd.cpaa.carplay.CarPlayManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;
import g5.c;
import g5.d;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayNeedDialog extends BydFullScreenDialog {

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CarPlayNeedDialog.this.isShowing()) {
                d.c(CarPlayNeedDialog.this.mContext);
                CarPlayNeedDialog.this.setStartNewUI(true);
                CarPlayNeedDialog.this.dismiss();
            }
        }
    }

    public CarPlayNeedDialog(Context context) {
        super(context);
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_confirm);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_cancel);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_button);
        if (c.e() == 1) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_car_play_need_tip;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.tv_cancel) {
            CarPlayManager.getInstance(this.mContext).setNeedConnectCarPlay(false);
        } else if (id == R.id.tv_confirm) {
            d.c(this.mContext);
            setStartNewUI(true);
            CarPlayManager.getInstance(this.mContext).setNeedConnectCarPlay(true);
        }
        if (view.getId() != R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g, android.app.Dialog
    public void show() {
        super.show();
        new Handler().postDelayed(new a(), 60000L);
    }
}
