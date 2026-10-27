package com.byd.uereport.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.widget.CountDownTimerButton;

/* JADX INFO: loaded from: classes2.dex */
public class FreezerTipsDialog extends g implements DialogInterface.OnDismissListener, CountDownTimerButton.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CountDownTimerButton f3226d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f3227e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f3228f;

    public FreezerTipsDialog(Context context) {
        super(context);
    }

    @Override // com.byd.uereport.widget.CountDownTimerButton.c
    public void a() {
        dismiss();
    }

    @Override // com.byd.uereport.widget.CountDownTimerButton.c
    public void b() {
        dismiss();
    }

    public void c(int i8) {
        int i9 = R.string.vehiclesettings_compression_refrigerator_system_check;
        if (i8 != 0) {
            if (i8 == 1) {
                i9 = R.string.vehiclesettings_compression_refrigerator_something_left;
            } else if (i8 == 2) {
                i9 = R.string.vehiclesettings_compression_refrigerator_work_heart_description;
            } else if (i8 == 3) {
                i9 = R.string.vehiclesettings_compression_refrigerator_door_unclose_description;
            } else if (i8 == 4) {
                i9 = R.string.vehiclesettings_compression_refrigerator_before_door_open_description;
            }
        }
        TextView textView = this.f3227e;
        if (textView != null) {
            textView.setText(getContext().getString(i9));
        }
        TextView textView2 = this.f3228f;
        if (textView2 != null) {
            textView2.setText(getContext().getString(R.string.vehiclesettings_title_dialog_hint));
        }
        CountDownTimerButton countDownTimerButton = this.f3226d;
        if (countDownTimerButton != null) {
            countDownTimerButton.setBtnBefforText(getContext().getString(R.string.vehiclesettings_button_confirm));
            this.f3226d.l();
            this.f3226d.i();
        }
    }

    @Override // b5.g
    public void initContent(View view) {
        this.f3226d = (CountDownTimerButton) view.findViewById(R.id.refrigerator_dialog_btn_confirm);
        this.f3228f = (TextView) view.findViewById(R.id.refrigerator_dialog_title_tv);
        this.f3227e = (TextView) view.findViewById(R.id.freezer_dialog_tip_content);
        this.f3226d.setOnBtnCallBackListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_freezer_tips_layout;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_312;
    }
}
