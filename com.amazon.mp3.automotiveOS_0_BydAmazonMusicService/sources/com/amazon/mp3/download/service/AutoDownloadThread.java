package com.amazon.mp3.download.service;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Process;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.notification.NotificationInfo;

/* JADX INFO: loaded from: classes3.dex */
public class AutoDownloadThread extends Thread {
    private static final String TAG = AutoDownloadThread.class.getSimpleName();
    private final Context mContext;
    private final boolean mIsAlbumPurchase;
    private final long mTrackDate;
    private final DownloadRestrictionCriteria mType;

    public enum DownloadRestrictionCriteria {
        EQUAL_LAST_DATE("date_purchased = ?"),
        AFTER_OR_EQUAL_LAST_DATE("date_purchased >= ?");

        final String mRestrictionCriteria;

        DownloadRestrictionCriteria(final String criteria) {
            this.mRestrictionCriteria = criteria;
        }
    }

    public AutoDownloadThread(final Context context, final DownloadRestrictionCriteria type, boolean isAlbumPurchase) {
        this(context, type, -1L, isAlbumPurchase);
    }

    public AutoDownloadThread(final Context context, final DownloadRestrictionCriteria type, final long lastDownloadDate, final boolean isAlbumPurchase) {
        super(TAG);
        this.mContext = context.getApplicationContext();
        this.mTrackDate = lastDownloadDate;
        this.mType = type;
        this.mIsAlbumPurchase = isAlbumPurchase;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        addToDownloadQueue(this.mContext, this.mTrackDate, this.mType, this.mIsAlbumPurchase);
    }

    private static synchronized void addToDownloadQueue(final Context context, final long date, final DownloadRestrictionCriteria type, final boolean isAlbumPurchase) {
        long j;
        if (date <= 0) {
            try {
                j = SettingsUtil.getPrefs(context).getLong(context.getString(R.string.setting_key_auto_download_last_date), -1L);
            } catch (Throwable th) {
                throw th;
            }
        } else {
            j = date;
        }
        if (j < 0) {
            Log.error(TAG, "AutoDownloadThread starting without setting a download date, setting date and punting");
            SettingsUtil.setLastAutoDownloadDateToLastPurchaseDate(context);
            return;
        }
        Cursor cursorQuery = context.getContentResolver().query(MediaProvider.SmartPlaylists.Tracks.getContentUri("cirrus", 0L), new String[]{"luid", "asin", "date_purchased", "title", "album_id", "album"}, DbUtil.applyBinaryOperator(type.mRestrictionCriteria, "AND", "download_state=?"), new String[]{Long.toString(j), Long.toString(5L)}, "date_purchased DESC");
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    final long j2 = Long.parseLong(cursorQuery.getString(cursorQuery.getColumnIndex("date_purchased")));
                    if (isAlbumPurchase) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("album"));
                        long j3 = cursorQuery.getLong(cursorQuery.getColumnIndex("album_id"));
                        Uri contentUri = MediaProvider.Albums.Tracks.getContentUri("cirrus", j3);
                        MusicDownloader.getInstance(context).download(contentUri.toString(), contentUri, new NotificationInfo(string, null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), j3).toString()), false, true, new PriorityInfo(DownloadPriority.BACKFILL.name(), DownloadReason.AUTOMATICDOWNLOAD.name()));
                    } else {
                        do {
                            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("title"));
                            MusicDownloader.getInstance(context).download(cursorQuery.getString(cursorQuery.getColumnIndex("asin")), MediaProvider.Tracks.getContentUri("cirrus", cursorQuery.getString(cursorQuery.getColumnIndex("luid"))), new NotificationInfo(string2, null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), cursorQuery.getLong(cursorQuery.getColumnIndex("album_id"))).toString()), false, true, new PriorityInfo(DownloadPriority.BACKFILL.name(), DownloadReason.AUTOMATICDOWNLOAD.name()));
                        } while (cursorQuery.moveToNext());
                    }
                    AmazonApplication.getDefaultHandler().postDelayed(new Runnable() { // from class: com.amazon.mp3.download.service.AutoDownloadThread.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SettingsUtil.setLastAutoDownloadDate(context, j2);
                        }
                    }, 60000L);
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
    }
}
