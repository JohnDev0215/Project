package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.database.Cursor;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.prime.ContentOwnershipStatus;

/* JADX INFO: loaded from: classes3.dex */
public class OwnershipStatusQuery {
    private final Context mContext;

    public OwnershipStatusQuery(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public boolean loadOwnershipStatusForInfo(LyricsTrackInfo info) {
        Integer numValueOf;
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).query("Track", new String[]{"ownership_status"}, "asin=? AND (marketplace=? OR marketplace IS NULL)", new String[]{info.getAsin(), info.getMarketplaceId()}, null, null, null);
        if (cursorQuery.moveToNext()) {
            numValueOf = Integer.valueOf(cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status")));
        } else {
            numValueOf = Integer.valueOf(ContentOwnershipStatus.UNKNOWN.getValue());
        }
        cursorQuery.close();
        return numValueOf.intValue() >= ContentOwnershipStatus.OWNED.getValue() && numValueOf.intValue() < 200;
    }
}
