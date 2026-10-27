package com.amazon.mp3.playlist.service;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.library.util.EditPlaylistMetricsLogger;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistRequest;
import com.amazon.musicplaylist.model.CatalogTrack;
import com.amazon.musicplaylist.model.LibraryTrack;
import com.amazon.musicplaylist.model.TrackId;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Playlists {
    private static final String TAG = "Playlists";

    public static class AppendTracksToPlaylistBuilder {
        private final Context mContext;
        private String mId;
        private List<String> mTrackAsins;

        private AppendTracksToPlaylistBuilder(final Context context) {
            this.mContext = context;
        }

        public AppendTracksToPlaylistBuilder withId(@NonNull final String id) {
            this.mId = id;
            return this;
        }

        public AppendTracksToPlaylistBuilder withTrackAsins(@NonNull final List<String> trackAsins) {
            this.mTrackAsins = trackAsins;
            return this;
        }

        public void submit() throws PlaylistException {
            TrackId trackIdBuild;
            String homeMarketPlaceId = AccountDetailUtil.get(this.mContext).getHomeMarketPlaceId();
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.mContext);
            PlaylistService playlistServiceCreate = PlaylistServiceFactory.create(this.mContext);
            Log.debug(Playlists.TAG, "Adding %d tracks to playlist %s", Integer.valueOf(this.mTrackAsins.size()), this.mId);
            ArrayList arrayList = new ArrayList(this.mTrackAsins.size());
            for (String str : this.mTrackAsins) {
                if (PrimePlaylistUtil.isAsin(str)) {
                    trackIdBuild = CatalogTrack.builder().withAsin(str).withMarketplaceId(homeMarketPlaceId).build();
                } else {
                    trackIdBuild = LibraryTrack.builder().withObjectId(str).build();
                }
                arrayList.add(trackIdBuild);
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("SUPPORT_MIXED_ID_TYPES");
            try {
                playlistServiceCreate.appendTracksToPlaylists(AppendTracksToPlaylistRequest.builder().withPlaylistId(this.mId).withDeviceId(accountCredentialStorage.getDeviceId()).withDeviceType(accountCredentialStorage.getDeviceType()).withFeatureSet(arrayList2).withTracks(arrayList).withApiRevision(1).withVersion("1").withRejectDuplicateTracks(true).build());
                new EditPlaylistMetricsLogger(this.mContext).sendOnlinePlaylistEdited();
            } catch (VolleyError e) {
                throw new PlaylistException(e);
            }
        }
    }

    public static AppendTracksToPlaylistBuilder append(final Context context) {
        return new AppendTracksToPlaylistBuilder(context);
    }
}
