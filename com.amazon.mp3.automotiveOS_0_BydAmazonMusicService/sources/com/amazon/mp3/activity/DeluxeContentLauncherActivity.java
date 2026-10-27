package com.amazon.mp3.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.job.DeluxeContentJob;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.JobSessionActivity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.EmailUtil;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MediaPlaybackUtil;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class DeluxeContentLauncherActivity extends JobSessionActivity {
    private static final String TAG = "DeluxeContentLauncherActivity";
    private String mAsin;
    private long mJobId = -1;
    private ProgressDialog mLoadingDialog;
    private String mLuid;
    private Uri mUri;

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(this));
            finish();
            return;
        }
        setContentView(R.layout.blank_loading_activity);
        Bundle extras = getIntent().getExtras();
        String str = DeluxeContentUtil.EXTRA_URI_KEY;
        if (!extras.containsKey(str)) {
            Log.warning(TAG, "URI wasn't passed in, cannot proceed with launching deluxe content.");
            finish();
        }
        this.mUri = Uri.parse(extras.getString(str));
        this.mAsin = extras.getString(DeluxeContentUtil.EXTRA_ASIN);
        this.mLuid = extras.getString(DeluxeContentUtil.EXTRA_LUID);
        int i = AnonymousClass5.$SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[((DeluxeContentUtil.ContentType) getIntent().getSerializableExtra(DeluxeContentUtil.EXTRA_REQUEST_KEY)).ordinal()];
        if (i != 1) {
            if (i == 2) {
                this.mJobId = addJob(new DeluxeContentJob(DeluxeContentUtil.ContentType.VIDEO, this.mUri));
                return;
            } else {
                Log.warning(TAG, "Unknown launcher request type.");
                finish();
                return;
            }
        }
        String str2 = DeluxeContentUtil.EXTRA_TITLE_KEY;
        String str3 = DeluxeContentUtil.TEMP_BOOKLET_DIR + (FileUtil.replaceInvalidPathChars(extras.getString(str2)) + '.' + extras.getString(DeluxeContentUtil.EXTRA_EXTENSION_KEY));
        if (!new File(str3).exists()) {
            showLoadingDialog(extras.getString(str2));
            this.mJobId = addJob(new DeluxeContentJob(DeluxeContentUtil.ContentType.DOCUMENT, this.mUri, str3));
        } else {
            launchBookletIntent(str3);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.DeluxeContentLauncherActivity$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType;

        static {
            int[] iArr = new int[DeluxeContentUtil.ContentType.values().length];
            $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType = iArr;
            try {
                iArr[DeluxeContentUtil.ContentType.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[DeluxeContentUtil.ContentType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onJobFinished(long jobId, Job job, int resultCode, final Bundle resultBundle) {
        if (jobId != this.mJobId) {
            super.onJobFinished(jobId, job, resultCode, resultBundle);
            return;
        }
        this.mJobId = -1L;
        if (resultCode == 1) {
            new Handler().post(new Runnable() { // from class: com.amazon.mp3.activity.DeluxeContentLauncherActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    int i = AnonymousClass5.$SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[((DeluxeContentUtil.ContentType) resultBundle.getSerializable(DeluxeContentUtil.EXTRA_REQUEST_KEY)).ordinal()];
                    if (i == 1) {
                        DeluxeContentLauncherActivity.this.launchBookletIntent(resultBundle.getString(DeluxeContentUtil.EXTRA_PATH_KEY));
                        DeluxeContentLauncherActivity.this.hideLoadingDialog();
                    } else {
                        if (i == 2) {
                            Intent playVideoIntent = MediaPlaybackUtil.getPlayVideoIntent(resultBundle.getString(DeluxeContentUtil.EXTRA_URI_KEY));
                            MetricsLogger.deluxeVideoStreamingInitiated(DeluxeContentLauncherActivity.this.mAsin, DeluxeContentLauncherActivity.this.mLuid);
                            try {
                                DeluxeContentLauncherActivity.this.startActivity(playVideoIntent);
                                DeluxeContentLauncherActivity.this.finish();
                                return;
                            } catch (ActivityNotFoundException unused) {
                                Log.info(DeluxeContentLauncherActivity.TAG, "Tried to play deluxe video, but no application supports it.");
                                NoAppFoundDialogFragment.newInstance(DeluxeContentUtil.ContentType.VIDEO, "").show(DeluxeContentLauncherActivity.this.getSupportFragmentManager(), (String) null);
                                return;
                            }
                        }
                        DeluxeContentLauncherActivity.this.finish();
                    }
                }
            });
            return;
        }
        if (resultCode == 2) {
            finish();
        } else if (resultCode == 3) {
            buildUnexpectedErrorDialog().show();
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        Intent intent = new Intent();
        intent.putExtras(getIntent());
        setResult(-1, intent);
        super.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchBookletIntent(String path) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(FileProvider.getUriForFile(this, "com.amazon.mp3.logs.fileprovider", new File(path)), "application/pdf");
        MetricsLogger.deluxeBookletReadingInitiated(this.mAsin, this.mLuid);
        try {
            startActivity(intent);
            finish();
        } catch (ActivityNotFoundException unused) {
            Log.info(TAG, "Tried to open a booklet, but no application supports it.");
            NoAppFoundDialogFragment.newInstance(DeluxeContentUtil.ContentType.DOCUMENT, getIntent().getStringExtra(DeluxeContentUtil.EXTRA_TITLE_KEY)).show(getSupportFragmentManager(), (String) null);
        }
    }

    private Dialog buildUnexpectedErrorDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_deluxe_desc_unknown_title);
        bauhausDialogBuilder.setMessage(R.string.dmusic_deluxe_desc_unknown);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.DeluxeContentLauncherActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                DeluxeContentLauncherActivity.this.finish();
            }
        });
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_contact_us, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.DeluxeContentLauncherActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                EmailUtil.contactCustomerSupport(DeluxeContentLauncherActivity.this);
                DeluxeContentLauncherActivity.this.finish();
            }
        });
        return bauhausDialogBuilder.create();
    }

    private void showLoadingDialog(String title) {
        if (this.mLoadingDialog != null) {
            return;
        }
        findViewById(R.id.BlankLoadingSpinner).setVisibility(8);
        createLoadingDialog(title);
        this.mLoadingDialog.show();
        TextView textView = (TextView) this.mLoadingDialog.findViewById(android.R.id.message);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoadingDialog() {
        ProgressDialog progressDialog = this.mLoadingDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mLoadingDialog = null;
        }
    }

    private void createLoadingDialog(String title) {
        this.mLoadingDialog = new ProgressDialog(this);
        String string = getString(R.string.dmusic_deluxe_loading_dialog_default);
        if (title != null) {
            string = getString(R.string.dmusic_deluxe_loading_dialog, title);
        }
        this.mLoadingDialog.setMessage(string);
        this.mLoadingDialog.setCancelable(true);
        this.mLoadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.activity.DeluxeContentLauncherActivity.4
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialog) {
                DeluxeContentLauncherActivity deluxeContentLauncherActivity = DeluxeContentLauncherActivity.this;
                deluxeContentLauncherActivity.cancelJob(deluxeContentLauncherActivity.mJobId);
            }
        });
    }

    public static class NoAppFoundDialogFragment extends DialogFragment {
        public static NoAppFoundDialogFragment newInstance(DeluxeContentUtil.ContentType contentType, String contentName) {
            Bundle bundle = new Bundle();
            bundle.putString("ARG_CONTENT_NAME", contentName);
            bundle.putSerializable("ARG_CONTENT_TYPE", contentType);
            NoAppFoundDialogFragment noAppFoundDialogFragment = new NoAppFoundDialogFragment();
            noAppFoundDialogFragment.setArguments(bundle);
            return noAppFoundDialogFragment;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(final Bundle savedInstanceState) {
            String string;
            String string2 = getArguments().getString("ARG_CONTENT_NAME");
            DeluxeContentUtil.ContentType contentType = (DeluxeContentUtil.ContentType) getArguments().getSerializable("ARG_CONTENT_TYPE");
            BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(getActivity());
            String string3 = "";
            if (contentType == DeluxeContentUtil.ContentType.DOCUMENT) {
                String string4 = getString(R.string.dmusic_deluxe_no_document_app, string2);
                string3 = getString(R.string.dmusic_deluxe_no_document_app_title);
                string = string4;
            } else if (contentType == DeluxeContentUtil.ContentType.VIDEO) {
                string3 = getString(R.string.dmusic_deluxe_no_video_app_title);
                string = getString(R.string.dmusic_deluxe_no_video_app);
            } else {
                string = "";
            }
            bauhausDialogBuilder.setTitle(string3);
            bauhausDialogBuilder.setMessage(string);
            bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
            return bauhausDialogBuilder.create();
        }

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
        public void onDismiss(final DialogInterface dialog) {
            super.onDismiss(dialog);
            if (getActivity() != null) {
                getActivity().finish();
            }
        }
    }
}
