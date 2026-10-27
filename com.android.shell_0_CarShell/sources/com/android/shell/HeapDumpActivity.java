package com.android.shell;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DebugUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class HeapDumpActivity extends Activity {
    private AlertDialog mDialog;
    private Uri mDumpUri;
    private boolean mHandled = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("com.android.internal.extra.heap_dump.PROCESS_NAME");
        long longExtra = getIntent().getLongExtra("com.android.internal.extra.heap_dump.SIZE_BYTES", 0L);
        boolean booleanExtra = getIntent().getBooleanExtra("com.android.internal.extra.heap_dump.IS_USER_INITIATED", false);
        boolean z = getIntent().getIntExtra("android.intent.extra.UID", 0) == 1000;
        this.mDumpUri = HeapDumpProvider.makeUri(stringExtra);
        if (z) {
            stringExtra = getString(android.R.string.accessibility_system_action_back_label);
        }
        final Intent intent = new Intent();
        ClipData clipDataNewUri = ClipData.newUri(getContentResolver(), "Heap Dump", this.mDumpUri);
        intent.setClipData(clipDataNewUri);
        intent.addFlags(1);
        intent.setType(clipDataNewUri.getDescription().getMimeType(0));
        intent.putExtra("android.intent.extra.STREAM", this.mDumpUri);
        String stringExtra2 = getIntent().getStringExtra("com.android.internal.extra.heap_dump.REPORT_PACKAGE");
        if (stringExtra2 != null) {
            intent.setAction("android.app.action.REPORT_HEAP_LIMIT");
            intent.setPackage(stringExtra2);
            try {
                startActivity(intent);
                this.mHandled = true;
                finish();
                return;
            } catch (ActivityNotFoundException e) {
                Log.e("HeapDumpActivity", "Unable to direct launch to " + stringExtra2, e);
            }
        }
        this.mDialog = new AlertDialog.Builder(this, android.R.style.Theme.Material.Light.Dialog.Alert).setTitle(android.R.string.config_screenshotServiceComponent).setMessage(getString(booleanExtra ? android.R.string.config_satellite_test_with_esp_replies_intent_action : z ? android.R.string.config_screenshotAppClipsServiceComponent : android.R.string.config_screenshotErrorReceiverComponent, new Object[]{stringExtra, DebugUtils.sizeValueToString(longExtra, null)})).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.shell.-$$Lambda$HeapDumpActivity$wIGs5VHSQAMImnIAt7aOt8UUZaE
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$0$HeapDumpActivity(dialogInterface, i);
            }
        }).setNeutralButton(android.R.string.config_pointing_ui_package, new DialogInterface.OnClickListener() { // from class: com.android.shell.-$$Lambda$HeapDumpActivity$sEcBYSDi1_fvVdvGaSgb_g2wF2Y
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$1$HeapDumpActivity(dialogInterface, i);
            }
        }).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.shell.-$$Lambda$HeapDumpActivity$ZLUFlYa_TUxtxZJswi1uoKtc3hs
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$2$HeapDumpActivity(intent, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$HeapDumpActivity(DialogInterface dialogInterface, int i) {
        this.mHandled = true;
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$1$HeapDumpActivity(DialogInterface dialogInterface, int i) {
        this.mHandled = true;
        Intent intent = new Intent("com.android.shell.action.DELETE_HEAP_DUMP");
        intent.setClass(getApplicationContext(), HeapDumpReceiver.class);
        intent.putExtra("uri", this.mDumpUri.toString());
        sendBroadcast(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$2$HeapDumpActivity(Intent intent, DialogInterface dialogInterface, int i) {
        this.mHandled = true;
        intent.setAction("android.intent.action.SEND");
        intent.setPackage(null);
        startActivity(Intent.createChooser(intent, getText(android.R.string.config_screenshotServiceComponent)));
        finish();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (isChangingConfigurations() || this.mHandled) {
            return;
        }
        Intent intent = new Intent("com.android.shell.action.DELETE_HEAP_DUMP");
        intent.setClass(getApplicationContext(), HeapDumpReceiver.class);
        intent.putExtra("uri", this.mDumpUri.toString());
        sendBroadcast(intent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
