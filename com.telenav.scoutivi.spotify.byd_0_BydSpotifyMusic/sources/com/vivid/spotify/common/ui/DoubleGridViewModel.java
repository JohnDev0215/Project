package com.vivid.spotify.common.ui;

import android.app.Application;
import android.support.v4.media.MediaBrowserCompat;
import android.text.BidiFormatter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.t;
import com.vivid.spotify.p;
import f4.m;
import h3.i;
import java.util.List;
import q4.g;

/* JADX INFO: loaded from: classes.dex */
public class DoubleGridViewModel extends MediaCoverItemViewModel {
    private static final String TAG = "com.vivid.spotify.common.ui.DoubleGridViewModel";
    private final t<Integer> mBgReference;
    private final t<List<MediaBrowserCompat.MediaItem>> mLikedSongInfo;

    class a implements g<List<MediaBrowserCompat.MediaItem>> {
        a() {
        }

        @Override // q4.g
        public void a(Throwable th) {
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            DoubleGridViewModel.this.mLikedSongInfo.j(list);
            DoubleGridViewModel.this.subTitle.r(BidiFormatter.getInstance().unicodeWrap(i3.c.f(DoubleGridViewModel.this.mMediaItem)));
            DoubleGridViewModel doubleGridViewModel = DoubleGridViewModel.this;
            doubleGridViewModel.isPlayable.r((!doubleGridViewModel.mMediaItem.m() || list == null || list.isEmpty()) ? false : true);
        }
    }

    public DoubleGridViewModel(Application application, m mVar, i3.m mVar2) {
        super(application, mVar, mVar2, null);
        this.mLikedSongInfo = new t<>();
        this.mBgReference = new t<>();
    }

    private void load() {
        this.rxExecutor.c(this.mMediaManager.s(this.mMediaItem), new a());
    }

    public LiveData<List<MediaBrowserCompat.MediaItem>> observeLikedSong() {
        return this.mLikedSongInfo;
    }

    public LiveData<Integer> observerBg() {
        return this.mBgReference;
    }

    public void setMediaItem(MediaBrowserCompat.MediaItem mediaItem, MediaBrowserCompat.MediaItem mediaItem2) {
        this.mParentMediaItem = mediaItem;
        this.mMediaItem = mediaItem2;
        if (mediaItem2 != null) {
            this.title.r(BidiFormatter.getInstance().unicodeWrap(i3.c.g(this.mMediaItem)));
            String strD = i3.c.d(this.mMediaItem);
            if (strD.endsWith("collection")) {
                this.mBgReference.j(Integer.valueOf(p.rv_item_liked_song_bg));
            } else if (strD.endsWith("your-episodes")) {
                this.mBgReference.j(Integer.valueOf(p.rv_item_liked_pods_bg));
            } else {
                i.b(TAG).d("unknown type", new Object[0]);
            }
        }
        onMediaItemUpdate();
        load();
    }

    @Override // com.vivid.spotify.common.ui.MediaCoverItemViewModel, com.vivid.spotify.common.base.BaseViewModel
    protected boolean supportRxJava() {
        return true;
    }
}
