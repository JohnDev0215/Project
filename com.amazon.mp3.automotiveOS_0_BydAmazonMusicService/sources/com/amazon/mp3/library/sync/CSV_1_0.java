package com.amazon.mp3.library.sync;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.configuration.IntlConfiguration;

/* JADX INFO: loaded from: classes3.dex */
public enum CSV_1_0 {
    OBJECT_ID(new String[]{"objectId"}),
    FILE_NAME(new String[]{"fileName", "name"}),
    FILE_EXTENSION(new String[]{"fileExtension", "extension"}),
    FILE_SIZE(new String[]{"fileSize", "size"}),
    CREATION_DATE(new String[]{"creationDate"}),
    LAST_UPDATE_DATE(new String[]{"lastUpdatedDate"}),
    MARKET(new String[]{IntlConfiguration.MARKETPLACE}),
    ASIN(new String[]{"asin"}),
    MD5(new String[]{"md5"}),
    ORDER_ID(new String[]{"orderId"}),
    PHYSICAL_ORDER_ID(new String[]{"physicalOrderId"}),
    PURCHASE_DATE(new String[]{"purchaseDate"}),
    AUDIO_UPGRADE_DATE(new String[]{"audioUpgradeDate"}),
    PURCHASED(new String[]{"purchased"}),
    UPLOADED(new String[]{"uploaded"}),
    INSTANT_IMPORT(new String[]{"instantImport"}),
    TITLE(new String[]{"title"}),
    RATING(new String[]{"rating"}),
    TRACK_ASSET_TYPE(new String[]{"assetType"}),
    TRACK_ARTIST_NAME(new String[]{"artistName"}),
    TRACK_ARTIST_ASIN(new String[]{"artistAsin"}),
    TRACK_CONTRIBUTORS(new String[]{"contributors"}),
    TRACK_NUM(new String[]{"trackNum"}),
    TRACK_DISC_NUM(new String[]{"discNum"}),
    TRACK_PRIMARY_GENRE(new String[]{"primaryGenre"}),
    TRACK_DURATION(new String[]{"duration"}),
    BITRATE(new String[]{"bitrate"}),
    TRACK_COMPOSER(new String[]{"composer"}),
    TRACK_SONG_WRITER(new String[]{"songWriter"}),
    TRACK_PERFORMER(new String[]{"performer"}),
    TRACK_LYRICIST(new String[]{"lyricist"}),
    TRACK_PUBLISHER(new String[]{"publisher"}),
    STATUS(new String[]{NotificationCompat.CATEGORY_STATUS}),
    ERROR_CODE(new String[]{"errorCode", "trackStatus"}),
    PRIME_STATUS(new String[]{"primeStatus"}),
    HAWKFIRE_STATUS(new String[]{"isMusicSubscription"}),
    ALBUM_NAME(new String[]{"albumName"}),
    ALBUM_ASIN(new String[]{"albumAsin"}),
    ALBUM_ARTIST_NAME(new String[]{"albumArtistName"}),
    ALBUM_ARTIST_ASIN(new String[]{"albumArtistAsin"}),
    ALBUM_CONTRIBUTORS(new String[]{"albumContributors"}),
    ALBUM_RATING(new String[]{"albumRating"}),
    ALBUM_PRIMARY_GENRE(new String[]{"albumPrimaryGenre"}),
    ALBUM_RELEASE_DATE(new String[]{"albumReleaseDate"}),
    SORT_TITLE(new String[]{"sortTitle"}),
    SORT_TRACK_ARTIST_NAME(new String[]{"sortArtistName"}),
    SORT_ALBUM_NAME(new String[]{"sortAlbumName"}),
    SORT_ALBUM_ARTIST_NAME(new String[]{"sortAlbumArtistName"}),
    PARENTAL_CONTROLS(new String[]{"parentalControls"}),
    ASSET_ELIGIBILITY(new String[]{"assetEligibility"}),
    INTERNAL_TAGS(new String[]{"internalTags"}),
    ELIGIBILITY(new String[]{"eligibility"});

    private Integer mColumnIndex;
    private final String[] mColumnNames;

    CSV_1_0(String[] columnNames) {
        this.mColumnNames = columnNames;
    }

    public Integer getColumnIndex() {
        return this.mColumnIndex;
    }

    private void findColumnIndex(String[] header) {
        Integer numValueOf = null;
        for (int i = 0; i < header.length; i++) {
            String[] strArr = this.mColumnNames;
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    if (header[i].equals(strArr[i2])) {
                        numValueOf = Integer.valueOf(i);
                        break;
                    }
                    i2++;
                }
            }
        }
        this.mColumnIndex = numValueOf;
    }

    public static void rebuildIndex(String[] header) {
        for (CSV_1_0 csv_1_0 : values()) {
            csv_1_0.findColumnIndex(header);
        }
    }
}
