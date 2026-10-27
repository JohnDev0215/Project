package com.amazon.mp3.library.item;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public enum ItemWrapper$IdType {
    URI,
    CID,
    LCID,
    MERGED_ID,
    ASIN;

    private static final String LOGTAG = ItemWrapper$IdType.class.getSimpleName();

    public Uri getUri(final ItemWrapper$ItemType itemType, final String itemId) {
        int i = ItemWrapper$1.$SwitchMap$com$amazon$mp3$library$item$ItemWrapper$IdType[ordinal()];
        if (i == 1) {
            return Uri.parse(itemId);
        }
        if (i == 2) {
            return itemType.getUriForCid(itemId);
        }
        if (i == 3) {
            return itemType.getUriForLcid(itemId);
        }
        if (i != 4) {
            if (i == 5) {
                return AmazonApplication.getLibraryItemFactory().getUriForAsin(itemId);
            }
            Log.error(LOGTAG, "Unsupported id type");
        } else if (itemType == ItemWrapper$ItemType.ALBUM || itemType == ItemWrapper$ItemType.ARTIST || itemType == ItemWrapper$ItemType.SONG || itemType == ItemWrapper$ItemType.GENRE) {
            return itemType.getUriForId("merged", itemId);
        }
        return null;
    }
}
