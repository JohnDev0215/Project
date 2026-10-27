package com.android.providers.contacts.debug;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.providers.contacts.R;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ContactsDumpActivity extends Activity implements View.OnClickListener {
    private static String TAG = "ContactsDumpActivity";
    private Button mCancelButton;
    private Button mConfirmButton;
    private Button mDeleteButton;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(3);
        setContentView(R.layout.contact_dump_activity);
        getWindow().setFeatureDrawableResource(3, android.R.drawable.ic_dialog_alert);
        this.mConfirmButton = (Button) findViewById(R.id.confirm);
        this.mCancelButton = (Button) findViewById(R.id.cancel);
        this.mDeleteButton = (Button) findViewById(R.id.delete);
        updateDeleteButton();
    }

    private void updateDeleteButton() {
        this.mDeleteButton.setEnabled(DataExporter.dumpFileExists(this));
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        getWindow().addSystemFlags(524288);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel /* 2130771968 */:
                finish();
                break;
            case R.id.confirm /* 2130771969 */:
                this.mConfirmButton.setEnabled(false);
                this.mCancelButton.setEnabled(false);
                new DumpDbTask().execute(new Void[0]);
                break;
            case R.id.delete /* 2130771970 */:
                cleanup();
                updateDeleteButton();
                break;
        }
    }

    private void cleanup() {
        DataExporter.removeDumpFiles(this);
    }

    private class DumpDbTask extends AsyncTask<Void, Void, Uri> {
        private DumpDbTask() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ContactsDumpActivity.this.setProgressBarIndeterminateVisibility(true);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Uri doInBackground(Void... voidArr) {
            try {
                return DataExporter.exportData(ContactsDumpActivity.this.getApplicationContext());
            } catch (IOException e) {
                Log.e(ContactsDumpActivity.TAG, "Failed to export", e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Uri uri) {
            if (uri != null) {
                ContactsDumpActivity.this.emailFile(uri);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emailFile(Uri uri) {
        Log.i(TAG, "Drafting email");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.debug_dump_email_subject));
        intent.putExtra("android.intent.extra.TEXT", getString(R.string.debug_dump_email_body));
        intent.setType("application/zip");
        intent.putExtra("android.intent.extra.STREAM", uri);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.debug_dump_email_sender_picker)), 0);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        updateDeleteButton();
        this.mConfirmButton.setEnabled(true);
        this.mCancelButton.setEnabled(true);
    }
}
