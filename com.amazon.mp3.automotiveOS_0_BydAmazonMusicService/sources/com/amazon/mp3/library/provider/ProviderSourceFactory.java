package com.amazon.mp3.library.provider;

import android.app.Application;
import android.content.Context;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.local.LocalMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingSource;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class ProviderSourceFactory {
    private static ProviderSourceFactory sInstance;
    private Application mContext;
    private HashMap<String, ProviderSource> mSources = new HashMap<>();

    public static ProviderSourceFactory getInstance(Context context) {
        synchronized (ProviderSourceFactory.class) {
            if (sInstance == null) {
                sInstance = new ProviderSourceFactory((Application) context.getApplicationContext());
            }
        }
        return sInstance;
    }

    public synchronized ProviderSource getSource(String id) {
        return this.mSources.get(id);
    }

    private ProviderSourceFactory(Application context) {
        this.mContext = context;
        CirrusMediaSource cirrusMediaSource = new CirrusMediaSource();
        registerSource("cirrus", cirrusMediaSource);
        registerSource("cirrus-local", cirrusMediaSource);
        registerSource("merged", new MergedProviderSource());
        registerSource("recentitems", new RecentItemsProviderSource());
        registerSource("nowplaying", new NowPlayingSource());
        registerSource(ImagesContract.LOCAL, new LocalMediaSource());
    }

    private void registerSource(String id, ProviderSource source) {
        if (this.mSources.containsKey(id)) {
            throw new IllegalArgumentException("Source with ID: " + id + " already registered");
        }
        this.mSources.put(id, source);
        source.onRegistered(this.mContext);
    }
}
