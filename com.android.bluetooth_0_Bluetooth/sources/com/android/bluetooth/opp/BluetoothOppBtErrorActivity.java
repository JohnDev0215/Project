package com.android.bluetooth.opp;

import android.R;
import android.bluetooth.AlertActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppBtErrorActivity extends AlertActivity implements DialogInterface.OnClickListener {
    @Override // android.bluetooth.AlertActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addPrivateFlags(524288);
        Intent intent = getIntent();
        String errorTitle = intent.getStringExtra("title");
        String errorContent = intent.getStringExtra("content");
        this.mAlertBuilder.setIconAttribute(R.attr.alertDialogIcon);
        this.mAlertBuilder.setTitle(errorTitle);
        this.mAlertBuilder.setView(createView(errorContent));
        this.mAlertBuilder.setPositiveButton(com.android.bluetooth.R.string.bt_error_btn_ok, this);
        setupAlert();
    }

    private View createView(String errorContent) {
        View view = getLayoutInflater().inflate(com.android.bluetooth.R.layout.confirm_dialog, (ViewGroup) null);
        TextView contentView = (TextView) view.findViewById(com.android.bluetooth.R.id.content);
        contentView.setText(errorContent);
        return view;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
    }
}
