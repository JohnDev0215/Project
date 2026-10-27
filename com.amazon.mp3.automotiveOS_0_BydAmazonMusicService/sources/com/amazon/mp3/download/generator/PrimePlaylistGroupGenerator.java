package com.amazon.mp3.download.generator;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.download.generator.CannotGenerateGroupException;
import com.amazon.mp3.download.helper.RedownloadStateResolver;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistGroupGenerator {
    private static final String SELECTION = DeluxeContentUtil.getNonDeluxeSelection();
    private static final String[] SELECTION_ARGS = DeluxeContentUtil.getNonDeluxeSelectionArgs();
    private static final String TAG = "PrimePlaylistGroupGenerator";
    private final Context mContext;
    private final String mDescription;
    private final String mImageUri;
    private final StorageLocationFileManager mStorageLocationFileManager;
    private final String mTitle;
    private final Uri tracksUri;

    public PrimePlaylistGroupGenerator(final Uri tracksUri, final Context context, final String groupTitle, final String groupDescription, final String imageUri, final StorageLocationFileManager storageLocationFileManager) {
        this.tracksUri = tracksUri;
        this.mContext = context.getApplicationContext();
        this.mTitle = groupTitle;
        this.mDescription = groupDescription;
        this.mImageUri = imageUri;
        this.mStorageLocationFileManager = storageLocationFileManager;
    }

    public Group generate(final PriorityInfo priorityInfo, final Integer originalDownloadState) throws CannotGenerateGroupException {
        return generate(this.mContext.getContentResolver().query(this.tracksUri, PrimePlaylistItemGenerator.PROJECTION, SELECTION, SELECTION_ARGS, null), priorityInfo, originalDownloadState);
    }

    public Group generate(final Cursor cursor, final PriorityInfo priorityInfo, final Integer originalDownloadState) throws CannotGenerateGroupException {
        Group.Builder builder;
        int i;
        int i2;
        int i3;
        Group.Builder builder2;
        Cursor cursor2 = cursor;
        try {
            if (cursor2 == null) {
                throw new CannotGenerateGroupException(CannotGenerateGroupException.Reason.NoTracks);
            }
            Group.Builder builder3 = new Group.Builder();
            int columnIndex = cursor2.getColumnIndex("asin");
            int columnIndex2 = cursor2.getColumnIndex("title");
            int columnIndex3 = cursor2.getColumnIndex("download_state");
            int columnIndex4 = cursor2.getColumnIndex("artist");
            int columnIndex5 = cursor2.getColumnIndex("album");
            int columnIndex6 = cursor2.getColumnIndex("album_artist");
            int columnIndex7 = cursor2.getColumnIndex("track_num");
            int columnIndex8 = cursor2.getColumnIndex("catalog_status");
            int columnIndex9 = cursor2.getColumnIndex("content_encoding");
            RedownloadStateResolver redownloadStateResolver = new RedownloadStateResolver(this.mContext);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (true) {
                builder = builder3;
                if (!cursor.moveToNext()) {
                    break;
                }
                i6++;
                String string = cursor2.getString(columnIndex);
                String string2 = cursor2.getString(columnIndex2);
                String string3 = cursor2.getString(columnIndex4);
                String string4 = cursor2.getString(columnIndex6);
                String string5 = cursor2.getString(columnIndex5);
                String string6 = cursor2.getString(columnIndex7);
                int i7 = cursor2.getInt(columnIndex3);
                int i8 = columnIndex;
                ContentCatalogStatus contentCatalogStatusFromValue = ContentCatalogStatus.fromValue(cursor2.getInt(columnIndex8));
                List<ContentEncoding> listStringtoContentEncoding = ContentEncodingDecoder.stringtoContentEncoding(cursor2.getString(columnIndex9));
                if (i7 == 0) {
                    i = columnIndex2;
                    if (!redownloadStateResolver.shouldRedownloadTrack(string, true, listStringtoContentEncoding)) {
                        i4++;
                        cursor2 = cursor;
                        builder3 = builder;
                        columnIndex = i8;
                        columnIndex2 = i;
                    }
                } else {
                    i = columnIndex2;
                }
                if (contentCatalogStatusFromValue.isPreviouslyCatalog() || ContentAccessUtil.getCatalogContentStatusUnavailableReason(contentCatalogStatusFromValue, ContentAccessUtil.ContentAccessOperation.DOWNLOAD) != null) {
                    i2 = columnIndex3;
                    i3 = columnIndex4;
                } else {
                    i2 = columnIndex3;
                    i3 = columnIndex4;
                    Item itemGenerate = new PrimePlaylistItemGenerator(this.mContext, this.mTitle, this.mStorageLocationFileManager).generate(string, string2, string5, string4, string3, string6, contentCatalogStatusFromValue, priorityInfo, originalDownloadState);
                    if (itemGenerate != null) {
                        i5++;
                        builder2 = builder;
                        builder2.addItem("primeplaylist:" + string, itemGenerate);
                        builder2.setUri(this.tracksUri);
                        Log.verbose(TAG, "Track added to the playlist / asin: %s, title: %s", string, string2);
                        cursor2 = cursor;
                        builder3 = builder2;
                        columnIndex = i8;
                        columnIndex2 = i;
                        columnIndex3 = i2;
                        columnIndex4 = i3;
                    }
                }
                builder2 = builder;
                cursor2 = cursor;
                builder3 = builder2;
                columnIndex = i8;
                columnIndex2 = i;
                columnIndex3 = i2;
                columnIndex4 = i3;
            }
            if (i5 == 0) {
                Log.debug(TAG, "0 tracks to download for PrimePlaylist tracksUri %s out of total track count %s", this.tracksUri, Integer.valueOf(i6));
                if (i6 == 0) {
                    throw new CannotGenerateGroupException(CannotGenerateGroupException.Reason.NoTracks);
                }
                if (i4 == i6) {
                    throw new CannotGenerateGroupException(CannotGenerateGroupException.Reason.AlreadyDownloaded);
                }
                throw new CannotGenerateGroupException(CannotGenerateGroupException.Reason.AccessDenied);
            }
            Log.verbose(TAG, "%d tracks have been added to Download Group for PrimePlaylist tracksUri %s out of total track count %s", Integer.valueOf(i5), this.tracksUri, Integer.valueOf(i6));
            builder.setNotificationInfo(new NotificationInfo(this.mTitle, this.mDescription, this.mImageUri));
            return builder.build();
        } finally {
            DbUtil.closeCursor(cursor);
        }
    }
}
