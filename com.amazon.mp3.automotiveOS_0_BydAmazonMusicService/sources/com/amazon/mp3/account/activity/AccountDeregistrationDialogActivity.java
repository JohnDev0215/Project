package com.amazon.mp3.account.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.mp3.account.map.AccountDeregistration;
import com.amazon.mp3.account.map.PostAccountDeregister;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class AccountDeregistrationDialogActivity extends DialogActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();

    public static void start(Activity parent) {
        parent.startActivityForResult(new Intent(parent, (Class<?>) AccountDeregistrationDialogActivity.class), REQUEST_CODE);
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountDeregistration.deregister(this, new DeregistrationCallback());
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        Dialog dialog = new Dialog(this, R.style.Theme_Dialog_windowNoTitle_progressbar);
        dialog.setContentView(R.layout.common_progress_spinner);
        dialog.setCancelable(false);
        return dialog;
    }

    private final class DeregistrationCallback implements Callback {
        private DeregistrationCallback() {
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(final Bundle result) {
            AccountDeregistrationDialogActivity.this.setResult(0);
            AccountDeregistrationDialogActivity.this.finish();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(final Bundle result) {
            AccountDeregistrationDialogActivity.this.setResult(-1);
            new PostAccountDeregistrationAsyncTask().execute(new Void[0]);
        }

        private class PostAccountDeregistrationAsyncTask extends AsyncTask<Void, Void, Void> {
            private PostAccountDeregistrationAsyncTask() {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... params) {
                new PostAccountDeregister().onSignOut(AccountDeregistrationDialogActivity.this);
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void params) {
                AccountDeregistrationDialogActivity.this.finish();
            }
        }
    }
}
