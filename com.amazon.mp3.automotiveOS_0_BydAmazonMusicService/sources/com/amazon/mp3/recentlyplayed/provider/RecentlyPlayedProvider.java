package com.amazon.mp3.recentlyplayed.provider;

import android.os.Bundle;
import androidx.loader.content.Loader;
import com.amazon.mp3.api.data.BaseDataProvider;
import com.amazon.mp3.api.data.DataReceiver;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedRequestInfo;
import com.amazon.mp3.recentlyplayed.loader.RecentlyPlayedLoader;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedProvider extends BaseDataProvider<Receiver, RecentlyPlayedRequestInfo> {

    public interface Receiver extends DataReceiver {
        void onRecentlyPlayedLoaded(RecentlyPlayedRequestInfo data);
    }

    public RecentlyPlayedProvider(Receiver dataReceiver) {
        super(dataReceiver);
    }

    @Override // com.amazon.mp3.api.data.BaseDataProvider
    protected Loader<RecentlyPlayedRequestInfo> onCreateLoader(int type, int key, Bundle params) {
        return new RecentlyPlayedLoader(getReceiver().getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.api.data.BaseDataProvider
    public void onFinished(int type, int key, RecentlyPlayedRequestInfo result) {
        if (getReceiver() == null) {
            return;
        }
        getReceiver().onRecentlyPlayedLoaded(result);
    }

    public int getRecentlyPlayed() {
        return addJob(0, null, 1, 0);
    }
}
