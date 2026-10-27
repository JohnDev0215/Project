package com.android.bluetooth.opp;

import android.R;
import android.bluetooth.AlertActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vcard.VCardConfig;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppBtEnableActivity extends AlertActivity implements DialogInterface.OnClickListener {
    private BluetoothOppManager mOppManager;

    @Override // android.bluetooth.AlertActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addPrivateFlags(524288);
        BluetoothOppManager bluetoothOppManager = BluetoothOppManager.getInstance(this);
        this.mOppManager = bluetoothOppManager;
        bluetoothOppManager.mSendingFlag = false;
        this.mAlertBuilder.setIconAttribute(R.attr.alertDialogIcon);
        this.mAlertBuilder.setTitle(getString(com.android.bluetooth.R.string.bt_enable_title));
        this.mAlertBuilder.setView(createView());
        this.mAlertBuilder.setPositiveButton(com.android.bluetooth.R.string.bt_enable_ok, this);
        this.mAlertBuilder.setNegativeButton(com.android.bluetooth.R.string.bt_enable_cancel, this);
        setupAlert();
    }

    private View createView() {
        View view = getLayoutInflater().inflate(com.android.bluetooth.R.layout.confirm_dialog, (ViewGroup) null);
        TextView contentView = (TextView) view.findViewById(com.android.bluetooth.R.id.content);
        contentView.setText(getString(com.android.bluetooth.R.string.bt_enable_line1) + "\n\n" + getString(com.android.bluetooth.R.string.bt_enable_line2) + "\n");
        return view;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which == -2) {
            finish();
            return;
        }
        if (which == -1) {
            this.mOppManager.enableBluetooth();
            this.mOppManager.mSendingFlag = true;
            Toast.makeText(this, getString(com.android.bluetooth.R.string.enabling_progress_content), 0).show();
            Intent in = new Intent(this, (Class<?>) BluetoothOppBtEnablingActivity.class);
            in.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            startActivity(in);
            finish();
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        if (!this.mOppManager.mSendingFlag) {
            this.mOppManager.cleanUpSendingFileInfo();
        }
    }
}
