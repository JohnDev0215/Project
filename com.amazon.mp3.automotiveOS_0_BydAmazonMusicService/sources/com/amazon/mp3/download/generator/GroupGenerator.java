package com.amazon.mp3.download.generator;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.helper.RedownloadStateResolver;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GroupGenerator {
    private static final String SELECTION = DeluxeContentUtil.getNonDeluxeSelection();
    private static final String[] SELECTION_ARGS = DeluxeContentUtil.getNonDeluxeSelectionArgs();
    private static final String SORT_ORDER = CirrusMediaSource.getAlbumTrackSortOrder();
    private static final String TAG = "GroupGenerator";
    private final Context mContext;
    private final String mDescription;
    private final String mImageUri;
    private final StorageLocationFileManager mStorageLocationFileManager;
    private final String mTitle;
    private final Uri mTracksUri;

    public GroupGenerator(Context context, Uri tracksUri, String groupTitle, String groupDescription, String imageUri, final StorageLocationFileManager storageLocationFileManager) {
        this.mContext = context.getApplicationContext();
        this.mTracksUri = tracksUri;
        this.mTitle = groupTitle;
        this.mDescription = groupDescription;
        this.mImageUri = imageUri;
        this.mStorageLocationFileManager = storageLocationFileManager;
        Log.verbose(TAG, "Group Generator Created for: tracksUri = %s, title = %s, description = %s, imageUri = %s", tracksUri, groupTitle, groupDescription, imageUri);
    }

    public Group generate(boolean downloadNonAddedPrimeTracks, final PriorityInfo priorityInfo, final Integer originalDownloadState) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(this.mTracksUri, ItemGenerator.PROJECTION, SELECTION, SELECTION_ARGS, SORT_ORDER);
        if (cursorQuery == null) {
            Log.error(TAG, "Cannot retrieve cursor for parameters: trackUri = %s, downloadNonAddedPrimeTracks = %s, priorityInfo.priority = %s, priorityInfo.reason = %s", this.mTracksUri, Boolean.valueOf(downloadNonAddedPrimeTracks), priorityInfo.getPriority(), priorityInfo.getReason());
        }
        return generate(cursorQuery, downloadNonAddedPrimeTracks, priorityInfo, originalDownloadState);
    }

    public Group generate(final Cursor cursor, final boolean downloadNonAddedPrimeTracks, final PriorityInfo priorityInfo, final Integer originalDownloadState) throws Throwable {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        RedownloadStateResolver redownloadStateResolver;
        Item itemGenerate;
        Group.Builder builder;
        Cursor cursor2 = cursor;
        if (cursor2 != null) {
            try {
                Group.Builder builder2 = new Group.Builder();
                int columnIndex = cursor2.getColumnIndex("luid");
                int columnIndex2 = cursor2.getColumnIndex("asin");
                int columnIndex3 = cursor2.getColumnIndex("title");
                int columnIndex4 = cursor2.getColumnIndex("download_state");
                int columnIndex5 = cursor2.getColumnIndex("prime_status");
                int columnIndex6 = cursor2.getColumnIndex("ownership_status");
                int columnIndex7 = cursor2.getColumnIndex("extension");
                int columnIndex8 = cursor2.getColumnIndex("artist");
                int columnIndex9 = cursor2.getColumnIndex("album");
                int columnIndex10 = cursor2.getColumnIndex("album_asin");
                int columnIndex11 = cursor2.getColumnIndex("album_artist");
                int columnIndex12 = cursor2.getColumnIndex("disc_num");
                int columnIndex13 = cursor2.getColumnIndex("track_num");
                Group.Builder builder3 = builder2;
                cursor2.getColumnIndex("playlist_track_status");
                int columnIndex14 = cursor2.getColumnIndex("purchased");
                int columnIndex15 = cursor2.getColumnIndex("uploaded");
                int columnIndex16 = cursor2.getColumnIndex("content_encoding");
                int i6 = columnIndex6;
                int i7 = columnIndex5;
                try {
                    RedownloadStateResolver redownloadStateResolver2 = new RedownloadStateResolver(this.mContext);
                    int i8 = columnIndex16;
                    Log.verbose(TAG, "Number of rows returned by Cursor = %d", Integer.valueOf(cursor.getCount()));
                    boolean z = UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
                    int i9 = 0;
                    while (cursor.moveToNext()) {
                        String string = cursor2.getString(columnIndex);
                        String string2 = cursor2.getString(columnIndex2);
                        String string3 = cursor2.getString(columnIndex3);
                        String string4 = cursor2.getString(columnIndex7);
                        String string5 = cursor2.getString(columnIndex8);
                        String string6 = cursor2.getString(columnIndex11);
                        String string7 = cursor2.getString(columnIndex9);
                        String string8 = cursor2.getString(columnIndex10);
                        String string9 = cursor2.getString(columnIndex12);
                        String string10 = cursor2.getString(columnIndex13);
                        int i10 = cursor2.getInt(columnIndex4);
                        int i11 = columnIndex13;
                        int i12 = i8;
                        List<ContentEncoding> listStringtoContentEncoding = ContentEncodingDecoder.stringtoContentEncoding(cursor2.getString(i12));
                        int i13 = columnIndex;
                        int i14 = i7;
                        ContentCatalogStatus contentCatalogStatusFromValue = ContentCatalogStatus.fromValue(cursor2.getInt(i14));
                        int i15 = i6;
                        ContentOwnershipStatus contentOwnershipStatusFromValue = ContentOwnershipStatus.fromValue(cursor2.getInt(i15));
                        int i16 = columnIndex2;
                        int i17 = columnIndex14;
                        if (cursor2.isNull(i17)) {
                            i = i17;
                            i3 = columnIndex3;
                            i4 = columnIndex15;
                            i2 = 0;
                        } else {
                            i = i17;
                            i2 = cursor2.getInt(i17);
                            i3 = columnIndex3;
                            i4 = columnIndex15;
                        }
                        int i18 = cursor2.isNull(i4) ? 0 : cursor2.getInt(i4);
                        columnIndex15 = i4;
                        if (i10 == 0) {
                            RedownloadStateResolver redownloadStateResolver3 = redownloadStateResolver2;
                            i5 = columnIndex4;
                            redownloadStateResolver = redownloadStateResolver3;
                            if (!redownloadStateResolver.shouldRedownloadTrack(string2, (i2 == 1 || i18 == 1) ? false : true, listStringtoContentEncoding)) {
                            }
                            cursor2 = cursor;
                            columnIndex3 = i3;
                            columnIndex2 = i16;
                            columnIndex = i13;
                            columnIndex13 = i11;
                            i8 = i12;
                            i7 = i14;
                            i6 = i15;
                            columnIndex14 = i;
                            int i19 = i5;
                            redownloadStateResolver2 = redownloadStateResolver;
                            columnIndex4 = i19;
                        } else {
                            RedownloadStateResolver redownloadStateResolver4 = redownloadStateResolver2;
                            i5 = columnIndex4;
                            redownloadStateResolver = redownloadStateResolver4;
                        }
                        if (!z || contentOwnershipStatusFromValue.equals(ContentOwnershipStatus.OWNED) || i2 == 1) {
                            boolean z2 = z;
                            try {
                                RedownloadStateResolver redownloadStateResolver5 = redownloadStateResolver;
                                ItemGenerator itemGenerator = new ItemGenerator(this.mContext, this.mStorageLocationFileManager);
                                int i20 = columnIndex7;
                                int i21 = columnIndex8;
                                PrimePlaylistItemGenerator primePlaylistItemGenerator = new PrimePlaylistItemGenerator(this.mContext, this.mTitle, this.mStorageLocationFileManager);
                                if (PrimePlaylistUtil.isAsin(string)) {
                                    itemGenerate = primePlaylistItemGenerator.generate(string2, string3, string7, string6, string5, string10, contentCatalogStatusFromValue, priorityInfo, originalDownloadState);
                                } else {
                                    itemGenerate = itemGenerator.generate(string2, string, string3, string7, string8, string6, string5, string9, string10, string4, contentCatalogStatusFromValue, contentOwnershipStatusFromValue, downloadNonAddedPrimeTracks, i2 == 1 || i18 == 1 || contentOwnershipStatusFromValue == ContentOwnershipStatus.OWNED, priorityInfo, originalDownloadState);
                                }
                                if (itemGenerate != null) {
                                    if (TextUtils.isEmpty(string2)) {
                                        string2 = string;
                                    }
                                    builder = builder3;
                                    builder.addItem(string2, itemGenerate);
                                    i9++;
                                    Log.verbose(TAG, "Track added to the album / luid: %s, title: %s", string, string3);
                                } else {
                                    builder = builder3;
                                    String str = TAG;
                                    Object[] objArr = new Object[14];
                                    objArr[0] = string;
                                    objArr[1] = string3;
                                    objArr[2] = string7;
                                    objArr[3] = string6;
                                    objArr[4] = string5;
                                    objArr[5] = string9;
                                    objArr[6] = string10;
                                    objArr[7] = string4;
                                    objArr[8] = contentCatalogStatusFromValue;
                                    objArr[9] = contentOwnershipStatusFromValue;
                                    objArr[10] = Boolean.valueOf(downloadNonAddedPrimeTracks);
                                    objArr[11] = Boolean.valueOf(i2 == 1);
                                    objArr[12] = Boolean.valueOf(i18 == 1);
                                    objArr[13] = originalDownloadState;
                                    Log.error(str, "Could not find track to be added to the album / luid: %s, title: %s, album = %s, albumArtist = %s, artist = %s, discNum = %s, trackNum = %s, extension = %s, catalogStatus = %s, ownershipStatus = %s, downloadNonAddedPrimeTracks = %s, isPurchased = %s, isUploaded = %s, originalDownloadState = %s", objArr);
                                }
                                cursor2 = cursor;
                                builder3 = builder;
                                columnIndex3 = i3;
                                columnIndex2 = i16;
                                columnIndex4 = i5;
                                columnIndex = i13;
                                columnIndex13 = i11;
                                i8 = i12;
                                i7 = i14;
                                i6 = i15;
                                columnIndex14 = i;
                                z = z2;
                                redownloadStateResolver2 = redownloadStateResolver5;
                                columnIndex7 = i20;
                                columnIndex8 = i21;
                            } catch (Throwable th) {
                                th = th;
                                DbUtil.closeCursor(cursor);
                                throw th;
                            }
                        } else {
                            cursor2 = cursor;
                            columnIndex3 = i3;
                            columnIndex2 = i16;
                            columnIndex = i13;
                            columnIndex13 = i11;
                            i8 = i12;
                            i7 = i14;
                            i6 = i15;
                            columnIndex14 = i;
                            int i192 = i5;
                            redownloadStateResolver2 = redownloadStateResolver;
                            columnIndex4 = i192;
                        }
                    }
                    Group.Builder builder4 = builder3;
                    Log.verbose(TAG, "%d tracks have been added", Integer.valueOf(i9));
                    if (i9 != 0) {
                        builder4.setUri(this.mTracksUri);
                        builder4.setNotificationInfo(new NotificationInfo(this.mTitle, this.mDescription, this.mImageUri));
                        Group groupBuild = builder4.build();
                        DbUtil.closeCursor(cursor);
                        return groupBuild;
                    }
                    DbUtil.closeCursor(cursor);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            DbUtil.closeCursor(cursor);
            return null;
        }
    }
}
