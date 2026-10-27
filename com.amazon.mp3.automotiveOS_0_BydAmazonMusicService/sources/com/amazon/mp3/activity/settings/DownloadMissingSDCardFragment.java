package com.amazon.mp3.activity.settings;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.generator.ItemGenerator;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.externalstorage.DeviceStorageConfiguration;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.externalstorage.StorageLocationPreference;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadMissingSDCardFragment extends DialogFragment {
    public static final String TAG;
    private static final String TOTAL_DURATION;
    private static final String TOTAL_NUM_MISSING_MUSIC;
    long mTotalDurations = 0;
    int mTotalNumMissingMusic = 0;
    private final DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.settings.DownloadMissingSDCardFragment.3
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int which) {
            DownloadMissingSDCardFragment.this.dismiss();
        }
    };
    private final DialogInterface.OnClickListener downloadListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.settings.DownloadMissingSDCardFragment.4
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int which) {
            AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.settings.DownloadMissingSDCardFragment.4.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DownloadMissingSDCardFragment.this.getActivity() != null) {
                        ((SettingsActivity) DownloadMissingSDCardFragment.this.getActivity()).setIsRedownloadRemoveAllEnabled(false);
                        DownloadMissingSDCardFragment downloadMissingSDCardFragment = DownloadMissingSDCardFragment.this;
                        downloadMissingSDCardFragment.downloadMissingContent(downloadMissingSDCardFragment.getActivity());
                    }
                }
            });
            dialog.cancel();
        }
    };

    static {
        String simpleName = DownloadMissingSDCardFragment.class.getSimpleName();
        TAG = simpleName;
        TOTAL_DURATION = simpleName + "_TOTAL_DURATION";
        TOTAL_NUM_MISSING_MUSIC = simpleName + "_TOTAL_NUM_MISSING_MUSIC";
    }

    public static void show(final Activity activity) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.Theme_Dialog_windowNoTitle_progressbar) { // from class: com.amazon.mp3.activity.settings.DownloadMissingSDCardFragment.1
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
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.settings.DownloadMissingSDCardFragment.2
            @Override // java.lang.Runnable
            public void run() {
                DownloadMissingSDCardFragment downloadMissingSDCardFragment = new DownloadMissingSDCardFragment();
                List missingTracks = DownloadMissingSDCardFragment.getMissingTracks(activity, "cirrus");
                List<CatalogPlaylist> primePlaylistsWithMissingTracks = PrimePlaylistUtil.getPrimePlaylistsWithMissingTracks(activity);
                int numberOfMissingPrimePlaylistTracks = PrimePlaylistUtil.getNumberOfMissingPrimePlaylistTracks(activity);
                downloadMissingSDCardFragment.setTotalDuration(DownloadMissingSDCardFragment.getTotalDurations(missingTracks, primePlaylistsWithMissingTracks));
                downloadMissingSDCardFragment.setTotalNumMissingMusic(missingTracks.size() + numberOfMissingPrimePlaylistTracks);
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null) {
                    progressDialog2.dismiss();
                }
                activity.getFragmentManager().beginTransaction().add(downloadMissingSDCardFragment, DownloadMissingSDCardFragment.TAG).commitAllowingStateLoss();
            }
        });
    }

    public void setTotalDuration(final long totalDuration) {
        this.mTotalDurations = totalDuration;
    }

    public void setTotalNumMissingMusic(final int totalNumMissingMusic) {
        this.mTotalNumMissingMusic = totalNumMissingMusic;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mTotalDurations = savedInstanceState.getLong(TOTAL_DURATION, 0L);
            this.mTotalNumMissingMusic = savedInstanceState.getInt(TOTAL_NUM_MISSING_MUSIC, 0);
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(TOTAL_DURATION, this.mTotalDurations);
        outState.putInt(TOTAL_NUM_MISSING_MUSIC, this.mTotalNumMissingMusic);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(getActivity());
        bauhausDialogBuilder.setCancelable(true);
        String str = TAG;
        Log.info(str, "creating dialog to re-download missing music");
        Activity activity = getActivity();
        int iRound = (int) Math.round(this.mTotalDurations * 0.0325d);
        int iRound2 = Math.round(getSpaceAvailable(activity));
        Log.info(str, "found " + this.mTotalNumMissingMusic + " missing mMissingLibraryTracks");
        bauhausDialogBuilder.setTitle(R.string.dmusic_setting_storage_download_missing_songs_confirm_title_pref);
        bauhausDialogBuilder.setMessage(getString(R.string.dmusic_setting_storage_download_missing_songs_confirm_body_pref, Integer.valueOf(this.mTotalNumMissingMusic), Integer.valueOf(iRound2), Integer.valueOf(iRound)));
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, this.downloadListener);
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_cancel, this.cancelListener);
        return bauhausDialogBuilder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getTotalDurations(final List<MusicTrack> missingLibraryTracks, final List<CatalogPlaylist> missingPrimePlaylists) {
        long durationSeconds = 0;
        if (missingLibraryTracks != null && missingPrimePlaylists != null) {
            Iterator<MusicTrack> it = missingLibraryTracks.iterator();
            while (it.hasNext()) {
                durationSeconds += it.next().getDuration();
            }
            Iterator<CatalogPlaylist> it2 = missingPrimePlaylists.iterator();
            while (it2.hasNext()) {
                Iterator<CatalogPlaylistTrack> it3 = it2.next().getTracks().iterator();
                while (it3.hasNext()) {
                    durationSeconds += (long) it3.next().getDurationSeconds();
                }
            }
        }
        return durationSeconds;
    }

    private long getSpaceAvailable(Context context) {
        if (context == null) {
            return 0L;
        }
        if (StorageLocation.DEVICE.equals(new StorageLocationPreference(context).getPreferredStorageLocation())) {
            return StorageInfo.getInternalStorageMBAvailable();
        }
        return StorageInfo.getExternalStorageMBAvailable(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadMissingContent(final Context context) {
        if (context == null) {
            return;
        }
        List<MusicTrack> missingTracks = getMissingTracks(context, "cirrus");
        List<CatalogPlaylist> primePlaylistsWithMissingTracks = PrimePlaylistUtil.getPrimePlaylistsWithMissingTracks(context);
        if (missingTracks.size() > 0) {
            Group.Builder builder = new Group.Builder();
            ItemGenerator itemGenerator = new ItemGenerator(context, StorageLocationFileManager.create(context, new DeviceStorageConfiguration(context, Environment.DIRECTORY_MUSIC), Environment.DIRECTORY_MUSIC));
            for (MusicTrack musicTrack : missingTracks) {
                Log.debug(TAG, "redownloading missing track:  " + musicTrack);
                Item itemGenerate = itemGenerator.generate(musicTrack.getContentUri("cirrus"), false, new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name()), 5);
                if (itemGenerate != null) {
                    builder.addItem(musicTrack.getAsin(), itemGenerate);
                }
            }
            builder.setNotificationInfo(new NotificationInfo(context.getString(R.string.dmusic_library_source_local_header), context.getResources().getQuantityString(R.plurals.dmusic_download_str_to_download, missingTracks.size(), Integer.valueOf(missingTracks.size())), ""));
            MusicDownloader.getInstance(context).download(TAG, builder.build());
        }
        for (CatalogPlaylist catalogPlaylist : primePlaylistsWithMissingTracks) {
            Log.debug(TAG, "redownloading missing playlist:  " + catalogPlaylist.toString());
            NotificationInfo notificationInfo = new NotificationInfo(catalogPlaylist.getTitle(), catalogPlaylist.getDescription(), ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), catalogPlaylist.getAsin()).toString());
            PrimePlaylistUtil.deleteOfflinePrimePlaylist(context, catalogPlaylist.getLuid());
            Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", catalogPlaylist.getAsin());
            MusicDownloader.getInstance(context).download(contentUri.toString(), contentUri, notificationInfo, false, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<MusicTrack> getMissingTracks(final Context context, final String source) {
        return TrackUtil.getMissingSDCardTracks(context, source);
    }
}
