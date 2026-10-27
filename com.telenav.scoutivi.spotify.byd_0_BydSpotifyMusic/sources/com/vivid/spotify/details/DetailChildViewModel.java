package com.vivid.spotify.details;

import android.app.Application;
import android.support.v4.media.MediaBrowserCompat;
import com.vivid.spotify.common.base.BaseViewModel;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DetailChildViewModel extends BaseViewModel {
    private static final String TAG = "DetailChildViewModel";
    public final androidx.databinding.k<MediaBrowserCompat.MediaItem> playlistItems;

    public DetailChildViewModel(Application application) {
        super(application);
        this.playlistItems = new androidx.databinding.k<>();
    }

    public void setMediaItem(List<MediaBrowserCompat.MediaItem> list) {
        this.playlistItems.clear();
        if (list == null || list.size() == 0) {
            return;
        }
        this.playlistItems.addAll(list);
    }
}
