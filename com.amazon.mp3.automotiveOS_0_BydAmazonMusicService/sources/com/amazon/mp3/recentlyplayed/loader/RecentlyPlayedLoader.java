package com.amazon.mp3.recentlyplayed.loader;

import android.content.Context;
import com.amazon.mp3.api.data.BaseLoader;
import com.amazon.mp3.recentlyplayed.RecentTrack;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedRequestInfo;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedLoader extends BaseLoader<RecentlyPlayedRequestInfo> {
    public RecentlyPlayedLoader(Context context) {
        super(context);
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public RecentlyPlayedRequestInfo loadInBackground() {
        RecentlyPlayedRequestInfo recentlyPlayedRequestInfo = new RecentlyPlayedRequestInfo();
        List<RecentlyPlayedItem> listQueryRecentItems = RecentlyPlayedManager.getInstance(getContext()).queryRecentItems();
        List<RecentTrack> recentTracks = RecentlyPlayedManager.getInstance(getContext()).getRecentTracks();
        recentlyPlayedRequestInfo.setRecentlyPlayed(listQueryRecentItems);
        recentlyPlayedRequestInfo.setRecentTrackList(recentTracks);
        return recentlyPlayedRequestInfo;
    }
}
