package com.android.packageinstaller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.android.internal.app.AlertActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class InstallStaging extends AlertActivity {
    private static final String LOG_TAG = InstallStaging.class.getSimpleName();
    private static final String STAGED_FILE = "STAGED_FILE";
    private File mStagedFile;
    private StagingAsyncTask mStagingTask;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlert.setIcon(R.drawable.ic_file_download);
        this.mAlert.setTitle(getString(R.string.app_name_unknown));
        this.mAlert.setView(R.layout.install_content_view);
        this.mAlert.setButton(-2, getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$InstallStaging$-n_J6648NTcOllT9pIbsLwncgvU
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$0$InstallStaging(dialogInterface, i);
            }
        }, (Message) null);
        setupAlert();
        requireViewById(R.id.staging).setVisibility(0);
        if (savedInstanceState != null) {
            File file = new File(savedInstanceState.getString(STAGED_FILE));
            this.mStagedFile = file;
            if (!file.exists()) {
                this.mStagedFile = null;
            }
        }
    }

    public /* synthetic */ void lambda$onCreate$0$InstallStaging(DialogInterface ignored, int ignored2) {
        StagingAsyncTask stagingAsyncTask = this.mStagingTask;
        if (stagingAsyncTask != null) {
            stagingAsyncTask.cancel(true);
        }
        setResult(0);
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onResume() {
        super.onResume();
        if (this.mStagingTask == null) {
            if (this.mStagedFile == null) {
                try {
                    this.mStagedFile = TemporaryFileManager.getStagedFile(this);
                } catch (IOException e) {
                    showError();
                    return;
                }
            }
            StagingAsyncTask stagingAsyncTask = new StagingAsyncTask();
            this.mStagingTask = stagingAsyncTask;
            stagingAsyncTask.execute(getIntent().getData());
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STAGED_FILE, this.mStagedFile.getPath());
    }

    protected void onDestroy() {
        StagingAsyncTask stagingAsyncTask = this.mStagingTask;
        if (stagingAsyncTask != null) {
            stagingAsyncTask.cancel(true);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showError() {
        new ErrorDialog().showAllowingStateLoss(getFragmentManager(), "error");
        Intent result = new Intent();
        result.putExtra("android.intent.extra.INSTALL_RESULT", -2);
        setResult(1, result);
    }

    public static class ErrorDialog extends DialogFragment {
        private Activity mActivity;

        @Override // android.app.DialogFragment, android.app.Fragment
        public void onAttach(Context context) {
            super.onAttach(context);
            this.mActivity = (Activity) context;
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog alertDialog = new AlertDialog.Builder(this.mActivity).setMessage(R.string.Parse_error_dlg_text).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$InstallStaging$ErrorDialog$z6Uttpne_gXZCqllYbnRjQ_S0Kk
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$0$InstallStaging$ErrorDialog(dialogInterface, i);
                }
            }).create();
            alertDialog.setCanceledOnTouchOutside(false);
            return alertDialog;
        }

        public /* synthetic */ void lambda$onCreateDialog$0$InstallStaging$ErrorDialog(DialogInterface dialog, int which) {
            this.mActivity.finish();
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            super.onCancel(dialog);
            this.mActivity.finish();
        }
    }

    private final class StagingAsyncTask extends AsyncTask<Uri, Void, Boolean> {
        private StagingAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Uri... params) {
            if (params == null || params.length <= 0) {
                return false;
            }
            Uri packageUri = params[0];
            try {
                InputStream in = InstallStaging.this.getContentResolver().openInputStream(packageUri);
                if (in == null) {
                    if (in != null) {
                        in.close();
                    }
                    return false;
                }
                try {
                    OutputStream out = new FileOutputStream(InstallStaging.this.mStagedFile);
                    try {
                        byte[] buffer = new byte[1048576];
                        while (true) {
                            int bytesRead = in.read(buffer);
                            if (bytesRead < 0) {
                                out.close();
                                if (in != null) {
                                    in.close();
                                }
                                return true;
                            }
                            if (isCancelled()) {
                                out.close();
                                if (in != null) {
                                    in.close();
                                }
                                return false;
                            }
                            out.write(buffer, 0, bytesRead);
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException | IllegalStateException | SecurityException e) {
                Log.w(InstallStaging.LOG_TAG, "Error staging apk from content URI", e);
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean success) {
            if (!success.booleanValue()) {
                InstallStaging.this.showError();
                return;
            }
            Intent intent = new Intent(InstallStaging.this.getIntent());
            intent.setClass(InstallStaging.this, DeleteStagedFileOnResult.class);
            intent.setData(Uri.fromFile(InstallStaging.this.mStagedFile));
            if (intent.getBooleanExtra("android.intent.extra.RETURN_RESULT", false)) {
                intent.addFlags(33554432);
            }
            intent.addFlags(65536);
            InstallStaging.this.startActivity(intent);
            InstallStaging.this.finish();
        }
    }
}
