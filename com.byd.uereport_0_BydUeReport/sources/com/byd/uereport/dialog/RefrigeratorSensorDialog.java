package com.byd.uereport.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import b5.g;
import com.byd.uereport.R;

/* JADX INFO: loaded from: classes2.dex */
public class RefrigeratorSensorDialog extends g implements DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Button f3286d;

    public RefrigeratorSensorDialog(Context context) {
        super(context);
    }

    @Override // b5.g
    public void initContent(View view) {
        Button button = (Button) view.findViewById(R.id.refrigerator_dialog_btn_confirm);
        this.f3286d = button;
        button.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_refrigerator_sensor_exception;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.refrigerator_dialog_btn_confirm) {
            dismiss();
        }
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
