package com.amazon.music.browse.artist;

import com.amazon.music.browse.DataType;
import com.amazon.musicensembleservice.ArtistDetailsMetadataType;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class ArtistDetailsRequestHelper {
    ArtistDetailsRequestHelper() {
    }

    public static ArtistDetailsMetadataType createArtistDetailsMetadataType(DataType dataType, String str, int i, SortBy sortBy) {
        Validate.isTrue(i > 0, "count must be greater than 0", new Object[0]);
        Validate.notNull(dataType, "dataType must not be null", new Object[0]);
        Validate.notNull(sortBy, "sortBy must not be null", new Object[0]);
        ArtistDetailsMetadataType artistDetailsMetadataType = new ArtistDetailsMetadataType();
        artistDetailsMetadataType.setMaxCount(Integer.valueOf(i));
        if (str != null) {
            artistDetailsMetadataType.setNextToken(str);
        } else {
            artistDetailsMetadataType.setNextToken("");
        }
        artistDetailsMetadataType.setType(dataType.getValue());
        artistDetailsMetadataType.setSortBy(sortBy.getValue());
        return artistDetailsMetadataType;
    }
}
