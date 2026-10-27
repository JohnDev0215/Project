package com.android.certinstaller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class CredentialsInstallDialog extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        displayDialog();
    }

    public void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflate = getLayoutInflater().inflate(R.layout.credentials_installed_dialog, (ViewGroup) null);
        builder.setView(viewInflate);
        Bundle extras = getIntent().getExtras();
        int intExtra = getIntent().getIntExtra("install_state", 0);
        TextView textView = (TextView) viewInflate.findViewById(R.id.credential_installed_content);
        if (intExtra == 2) {
            textView.setText(String.format(getResources().getString(R.string.install_done), extras.getString("network_name")));
            builder.setTitle(getResources().getString(R.string.install_done_title));
        } else if (intExtra == 1) {
            textView.setText(getResources().getString(R.string.wifi_installer_fail));
            builder.setTitle(R.string.wifi_installer_fail_title);
        } else if (intExtra == 0) {
            textView.setText(getResources().getString(R.string.wifi_installer_fail_no_wifi));
            builder.setTitle(R.string.wifi_installer_fail_no_wifi_title);
        }
        builder.setPositiveButton(R.string.done_label, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.CredentialsInstallDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                CredentialsInstallDialog.this.finish();
            }
        });
        builder.create().show();
    }
}
