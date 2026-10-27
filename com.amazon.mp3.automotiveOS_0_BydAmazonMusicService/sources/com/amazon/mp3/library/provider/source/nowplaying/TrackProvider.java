package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.library.item.MusicTrack;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface TrackProvider {
    void cancel();

    void close();

    void deregisterListener(TrackProviderListener listener);

    String getCollectionName();

    int getCount();

    MusicTrack getTrack(int position);

    Uri getUri();

    boolean isLoading();

    void loadCollection(LoadCookie cookie);

    void loadCollectionName();

    void populateState(JSONObject blob) throws JSONException;

    void refresh(LoadCookie cookie);

    void registerListener(TrackProviderListener listener);

    void restore(Context context, JSONObject object) throws JSONException;

    boolean supportsRepeatMode();

    boolean supportsShuffle();
}
