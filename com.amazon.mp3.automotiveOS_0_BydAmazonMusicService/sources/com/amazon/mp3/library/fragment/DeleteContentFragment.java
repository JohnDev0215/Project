package com.amazon.mp3.library.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.job.DeleteTrackJob;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.BauhausToastUtils;

/* JADX INFO: loaded from: classes3.dex */
public class DeleteContentFragment extends BaseFragment {
    private long mDeleteAlbumJobId = -1;
    private AlbumDeletedHandler mDeletedHandler;

    public void deleteAlbum(final String sourceId, final Uri contentUri, final String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.dmusic_button_yes, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.fragment.DeleteContentFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                BauhausToastUtils.showTextToast(DeleteContentFragment.this.getActivity(), DeleteContentFragment.this.getDeleteContentMessage(sourceId), 1);
                DeleteContentFragment deleteContentFragment = DeleteContentFragment.this;
                deleteContentFragment.mDeleteAlbumJobId = deleteContentFragment.addJob(new DeleteTrackJob(contentUri));
            }
        });
        builder.setNegativeButton(R.string.dmusic_button_no, (DialogInterface.OnClickListener) null);
        builder.setTitle(R.string.dmusic_context_delete_cloud);
        builder.setMessage(getString(R.string.dmusic_album_confirm_delete_cloud_msg, title));
        builder.create().show();
    }

    public void setAlbumDeletedHandler(AlbumDeletedHandler handler) {
        this.mDeletedHandler = handler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDeleteContentMessage(String sourceId) {
        return "cirrus".equals(sourceId) ? R.string.dmusic_album_toast_deleting_track_cloud : R.string.dmusic_album_toast_deleting_track_device;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        if (jobId == this.mDeleteAlbumJobId) {
            this.mDeleteAlbumJobId = -1L;
            AlbumDeletedHandler albumDeletedHandler = this.mDeletedHandler;
            if (albumDeletedHandler != null) {
                albumDeletedHandler.onAlbumDeleted(resultCode);
            }
        }
    }
}
