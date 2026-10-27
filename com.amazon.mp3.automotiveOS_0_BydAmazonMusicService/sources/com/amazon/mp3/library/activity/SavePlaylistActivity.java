package com.amazon.mp3.library.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.dialog.RetryPlaylistJobDialog;
import com.amazon.mp3.library.job.UpdatePlaylistJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.DialogUtil;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class SavePlaylistActivity extends LibraryBaseActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private Uri mPlaylistUri;
    private Dialog mRetryDialog;
    private long mSaveJobId = -1;
    private RetryPlaylistJobDialog.Listener mRetryJobListener = new RetryPlaylistJobDialog.Listener() { // from class: com.amazon.mp3.library.activity.SavePlaylistActivity.1
        @Override // com.amazon.mp3.library.dialog.RetryPlaylistJobDialog.Listener
        public void onRetryJob(Job job) {
            SavePlaylistActivity.this.addJobAndShowDelayedProgress(job);
        }

        @Override // com.amazon.mp3.library.dialog.RetryPlaylistJobDialog.Listener
        public void onCancel() {
            SavePlaylistActivity.this.finish();
        }
    };

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }

    public static Intent getStartIntent(Context context, Uri playlistUri) {
        Intent intent = new Intent(context, (Class<?>) SavePlaylistActivity.class);
        intent.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", playlistUri);
        return intent;
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AmazonMP3_Invisible);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_save_playlist);
        setResult(0);
        this.mPlaylistUri = (Uri) getIntent().getParcelableExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_URI");
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createSessionIfNecessary();
        DialogUtil.hackAroundOtterDialogActivityBug(this);
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity
    protected String getSourceId() {
        return MediaProvider.getSource(this.mPlaylistUri);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onSessionConnected(long sessionId) {
        if (this.mSaveJobId == -1) {
            addJobAndShowDelayedProgress(new UpdatePlaylistJob(this.mPlaylistUri));
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (jobId != this.mSaveJobId) {
            super.onJobFinished(jobId, job, resultCode, resultBundle);
        }
        hideProgressDialog();
        this.mSaveJobId = -1L;
        if (resultCode == 1) {
            setResult(-1);
            PlaylistUtil.clearMutatedFlag(getApplicationContext());
            finish();
            return;
        }
        Dialog dialog = this.mRetryDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        if (!BlueMoonExceptionsUtil.isBlueMoonException()) {
            Dialog dialogCreate = new RetryPlaylistJobDialog(this, this.mRetryJobListener, job).create();
            this.mRetryDialog = dialogCreate;
            dialogCreate.show();
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addJobAndShowDelayedProgress(Job job) {
        showProgressDialog(R.string.dmusic_playlist_progress_updating, 500);
        this.mSaveJobId = addJob(job);
    }
}
