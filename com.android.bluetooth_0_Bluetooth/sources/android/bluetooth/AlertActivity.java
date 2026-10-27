package android.bluetooth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: loaded from: classes.dex */
public abstract class AlertActivity extends Activity implements DialogInterface.OnDismissListener, DialogInterface.OnCancelListener {
    private AlertDialog mAlert;
    protected AlertDialog.Builder mAlertBuilder;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addPrivateFlags(524288);
        requestWindowFeature(1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        this.mAlertBuilder = builder;
        builder.setOnDismissListener(this);
        this.mAlertBuilder.setOnCancelListener(this);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        if (!isFinishing()) {
            finish();
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        if (!isFinishing()) {
            finish();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return dispatchPopulateAccessibilityEvent(this, event);
    }

    private static boolean dispatchPopulateAccessibilityEvent(Activity act, AccessibilityEvent event) {
        event.setClassName(Dialog.class.getName());
        event.setPackageName(act.getPackageName());
        ViewGroup.LayoutParams params = act.getWindow().getAttributes();
        boolean isFullScreen = params.width == -1 && params.height == -1;
        event.setFullScreen(isFullScreen);
        return false;
    }

    protected void setupAlert() {
        AlertDialog alertDialogCreate = this.mAlertBuilder.create();
        this.mAlert = alertDialogCreate;
        alertDialogCreate.show();
    }

    protected void changeIconAttribute(int attrId) {
        AlertDialog alertDialog = this.mAlert;
        if (alertDialog == null) {
            return;
        }
        alertDialog.setIconAttribute(attrId);
    }

    protected void changeTitle(CharSequence title) {
        AlertDialog alertDialog = this.mAlert;
        if (alertDialog == null) {
            return;
        }
        alertDialog.setTitle(title);
    }

    protected void changeButtonVisibility(int identifier, int visibility) {
        AlertDialog alertDialog = this.mAlert;
        if (alertDialog == null) {
            return;
        }
        alertDialog.getButton(identifier).setVisibility(visibility);
    }

    protected void changeButtonText(int identifier, CharSequence text) {
        AlertDialog alertDialog = this.mAlert;
        if (alertDialog == null) {
            return;
        }
        alertDialog.getButton(identifier).setText(text);
    }

    protected void changeButtonEnabled(int identifier, boolean enable) {
        AlertDialog alertDialog = this.mAlert;
        if (alertDialog == null) {
            return;
        }
        alertDialog.getButton(identifier).setEnabled(enable);
    }
}
