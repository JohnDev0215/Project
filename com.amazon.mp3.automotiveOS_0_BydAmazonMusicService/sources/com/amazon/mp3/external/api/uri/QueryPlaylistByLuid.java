package com.amazon.mp3.external.api.uri;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.DbUtil;

/* JADX INFO: loaded from: classes3.dex */
public class QueryPlaylistByLuid implements FindUriByAsinJob.QueryEntityById {
    private final Context mContext;

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    @Nullable
    public Uri findCollectionUriByAsin(String asin) {
        return null;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public int findTrackIndexInCollectionByAsin(final Uri collectionUri, final String trackAsin) {
        return 0;
    }

    public QueryPlaylistByLuid(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public Uri convertCollectionUriToTracksUri(Uri collectionUri) {
        return MediaProvider.UdoPlaylists.Tracks.getContentUri(MediaProvider.getSource(collectionUri), MediaProvider.Playlists.getPlaylistId(collectionUri));
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryEntityById
    public int findTrackIndexInCollectionByLuid(final Uri collectionUri, final String trackLuid) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(collectionUri, new String[]{"PlaylistTrack.track_luid"}, null, null, null);
        int position = -1;
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            int columnIndex = cursorQuery.getColumnIndex("track_luid");
            while (true) {
                if (trackLuid.equals(cursorQuery.getString(columnIndex))) {
                    position = cursorQuery.getPosition();
                    break;
                }
                if (!cursorQuery.moveToNext()) {
                    break;
                }
            }
            DbUtil.closeCursor(cursorQuery);
            return position;
        }
        DbUtil.closeCursor(cursorQuery);
        return -1;
    }
}
