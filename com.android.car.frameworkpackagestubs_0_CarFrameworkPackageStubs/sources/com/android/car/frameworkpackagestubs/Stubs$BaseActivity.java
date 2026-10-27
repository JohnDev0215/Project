package com.android.car.frameworkpackagestubs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
class Stubs$BaseActivity extends Activity {
    private Toast mToast;

    private Stubs$BaseActivity() {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showToast();
        finish();
    }

    protected CharSequence getMessage() {
        return getResources().getString(R.string.message_not_supported);
    }

    private void showToast() {
        cancelToast();
        Toast toastMakeText = Toast.makeText(this, getMessage(), 1);
        this.mToast = toastMakeText;
        toastMakeText.show();
    }

    private void cancelToast() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
    }
}
