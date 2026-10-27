package com.amazon.cirrus.libraryservice.v3.type;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.configuration.IntlConfiguration;

/* JADX INFO: loaded from: classes2.dex */
public class SelectableAttribute {
    private static final String[] values = {"albumArtistAsin", "albumArtistName", "albumAsin", "albumContributors", "albumCoverImageFull", "albumCoverImageLarge", "albumCoverImageMedium", "albumCoverImageSmall", "albumCoverImageTiny", "albumCoverImageXL", "albumName", "albumPrimaryGenre", "albumRating", "albumReleaseDate", "artistAsin", "artistName", "asin", "assetType", "assetEligibility", "audioUpgradeDate", "bitrate", "composer", "contributors", "creationDate", "customMeta", "discNum", "dmid", "duration", "eligibility", "fileExtension", "fullAlbumPurchased", "gracenoteId", "instantImport", "isMusicSubscription", "internalTags", "lastUpdatedDate", "localFilePath", "lyricist", IntlConfiguration.MARKETPLACE, "matchType", "matchVersion", "md5", "fileName", "objectId", "orderId", "parentalControls", "performer", "physicalOrderId", "primaryGenre", "primeStatus", "publisher", "purchased", "purchaseDate", "rating", "rogueBackfillDate", "fileSize", "songWriter", "sortAlbumArtistName", "sortAlbumName", "sortArtistName", "sortTitle", NotificationCompat.CATEGORY_STATUS, "storageLocation", "title", "trackNum", "unauthorizedDownloadCount", "errorCode", "uploaded"};

    private SelectableAttribute() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
