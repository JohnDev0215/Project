package com.amazon.mp3.activity.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RemoveMissingSDCardFragment extends DialogFragment {
    public static final String TAG;
    private static final String TOTAL_NUM_MISSING_MUSIC;
    private int mNumMissingMusic = 0;
    private final DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.settings.RemoveMissingSDCardFragment.3
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int which) {
            RemoveMissingSDCardFragment.this.dismiss();
        }
    };
    private final DialogInterface.OnClickListener removeListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.settings.RemoveMissingSDCardFragment.4
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int which) {
            AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.settings.RemoveMissingSDCardFragment.4.1
                @Override // java.lang.Runnable
                public void run() {
                    if (RemoveMissingSDCardFragment.this.getActivity() != null) {
                        ((SettingsActivity) RemoveMissingSDCardFragment.this.getActivity()).setIsRedownloadRemoveAllEnabled(false);
                        RemoveMissingSDCardFragment removeMissingSDCardFragment = RemoveMissingSDCardFragment.this;
                        removeMissingSDCardFragment.removeMissingContent(removeMissingSDCardFragment.getActivity());
                    }
                }
            });
            dialog.cancel();
        }
    };

    static {
        String simpleName = RemoveMissingSDCardFragment.class.getSimpleName();
        TAG = simpleName;
        TOTAL_NUM_MISSING_MUSIC = simpleName + "_TOTAL_NUM_MISSING_MUSIC";
    }

    public static void show(final Activity activity) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.Theme_Dialog_windowNoTitle_progressbar) { // from class: com.amazon.mp3.activity.settings.RemoveMissingSDCardFragment.1
            @Override // android.app.ProgressDialog, android.app.AlertDialog, android.app.Dialog
            public void onCreate(Bundle savedInstanceState) {
                requestWindowFeature(1);
                super.onCreate(savedInstanceState);
                setCancelable(false);
                setCanceledOnTouchOutside(false);
                setContentView(R.layout.common_progress_spinner);
            }
        };
        progressDialog.show();
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.settings.RemoveMissingSDCardFragment.2
            @Override // java.lang.Runnable
            public void run() {
                RemoveMissingSDCardFragment removeMissingSDCardFragment = new RemoveMissingSDCardFragment();
                removeMissingSDCardFragment.setNumMissingMusic(RemoveMissingSDCardFragment.getMissingTracks(activity, "cirrus-local").size() + PrimePlaylistUtil.getNumberOfMissingPrimePlaylistTracks(activity));
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null) {
                    progressDialog2.dismiss();
                }
                activity.getFragmentManager().beginTransaction().add(removeMissingSDCardFragment, RemoveMissingSDCardFragment.TAG).commitAllowingStateLoss();
            }
        });
    }

    public void setNumMissingMusic(final int numMissingMusic) {
        this.mNumMissingMusic = numMissingMusic;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mNumMissingMusic = savedInstanceState.getInt(TOTAL_NUM_MISSING_MUSIC, 0);
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TOTAL_NUM_MISSING_MUSIC, this.mNumMissingMusic);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(R.string.dmusic_setting_storage_remove_missing_songs_confirm_title_pref);
        builder.setMessage(getString(R.string.dmusic_setting_storage_remove_missing_songs_confirm_body_pref, Integer.valueOf(this.mNumMissingMusic)));
        builder.setPositiveButton(R.string.dmusic_button_ok, this.removeListener);
        builder.setNegativeButton(R.string.dmusic_button_cancel, this.cancelListener);
        return builder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMissingContent(final Context context) {
        for (MusicTrack musicTrack : getMissingTracks(context, "cirrus-local")) {
            Log.debug(TAG, "removing missing track:  " + musicTrack);
            DeleteUtil.deleteTracksFromDeviceOnCurrentThread(context, musicTrack.getContentUri());
        }
        for (CatalogPlaylist catalogPlaylist : PrimePlaylistUtil.getPrimePlaylistsWithMissingTracks(context)) {
            Log.debug(TAG, "removing missing prime playlist:  " + catalogPlaylist.toString());
            PrimePlaylistUtil.deleteOfflinePrimePlaylist(context, catalogPlaylist.getLuid());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<MusicTrack> getMissingTracks(Context context, final String source) {
        return TrackUtil.getMissingSDCardTracks(context, source);
    }
}
