package com.vivid.spotify.common.ui;

import android.app.Application;
import android.net.Uri;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import android.view.View;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.l;
import com.vivid.spotify.common.base.BaseViewModel;
import com.vivid.spotify.common.base.k;
import f4.m;
import f4.o;

/* JADX INFO: loaded from: classes.dex */
public class MediaCoverItemViewModel extends BaseViewModel implements k {
    private static final String TAG = "MediaCoverItemViewModel";
    public final ObservableBoolean gravityCenter;
    public final l<Uri> icon;
    public final ObservableBoolean isPlayable;
    public final ObservableBoolean isPlaying;
    private final o.a listener;
    protected MediaBrowserCompat.MediaItem mMediaItem;
    protected final m mMediaManager;
    protected final i3.m mMediaPlayController;
    protected MediaBrowserCompat.MediaItem mParentMediaItem;
    public final ObservableInt placeHolderResId;
    public final l<String> subTitle;
    public final l<String> title;

    class a implements o.a {
        a() {
        }

        @Override // f4.o.a
        public void a() {
            MediaCoverItemViewModel mediaCoverItemViewModel = MediaCoverItemViewModel.this;
            if (mediaCoverItemViewModel.mMediaItem != null) {
                mediaCoverItemViewModel.isPlaying.r(TextUtils.equals(com.vivid.spotify.nowplaying.b.l(mediaCoverItemViewModel.mMediaPlayController.n()), i3.c.d(MediaCoverItemViewModel.this.mMediaItem)) && MediaCoverItemViewModel.this.mMediaPlayController.x());
            }
        }
    }

    public MediaCoverItemViewModel(Application application, m mVar, i3.m mVar2, MediaBrowserCompat.MediaItem mediaItem) {
        super(application);
        this.title = new l<>();
        this.subTitle = new l<>();
        this.icon = new l<>();
        this.isPlayable = new ObservableBoolean(false);
        this.isPlaying = new ObservableBoolean(false);
        this.placeHolderResId = new ObservableInt(-1);
        this.gravityCenter = new ObservableBoolean(false);
        this.listener = new a();
        this.mMediaManager = mVar;
        this.mParentMediaItem = mediaItem;
        this.mMediaPlayController = mVar2;
    }

    public MediaBrowserCompat.MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel
    protected boolean listenerLanguageChange() {
        return false;
    }

    @Override // com.vivid.spotify.common.base.k
    public void onDestroy() {
    }

    public void onItemClicked(View view) {
        MediaBrowserCompat.MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || view == null) {
            return;
        }
        if (mediaItem.k()) {
            f.a(this.mParentMediaItem, this.mMediaItem, view);
            return;
        }
        if (this.mMediaItem.m()) {
            if (this.isPlaying.q()) {
                this.mMediaPlayController.H();
            } else if (TextUtils.equals(com.vivid.spotify.nowplaying.b.l(this.mMediaPlayController.n()), i3.c.d(this.mMediaItem))) {
                this.mMediaPlayController.I();
            } else {
                this.mMediaManager.r(this.mMediaItem);
            }
        }
    }

    protected void onMediaItemUpdate() {
        this.listener.a();
    }

    @Override // com.vivid.spotify.common.base.k
    public void onPause() {
        ((o) this.mMediaPlayController).e0(this.listener);
    }

    public void onPlayButtonClicked() {
        MediaBrowserCompat.MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && mediaItem.m()) {
            if (this.isPlaying.q()) {
                this.mMediaPlayController.H();
            } else if (TextUtils.equals(com.vivid.spotify.nowplaying.b.l(this.mMediaPlayController.n()), i3.c.d(this.mMediaItem))) {
                this.mMediaPlayController.I();
            } else {
                this.mMediaManager.r(this.mMediaItem);
            }
        }
    }

    @Override // com.vivid.spotify.common.base.k
    public void onResume() {
        ((o) this.mMediaPlayController).Y(this.listener);
    }

    public void onStop() {
    }

    public void setMediaItem(MediaBrowserCompat.MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        String strD = i3.c.d(mediaItem);
        this.placeHolderResId.r(f4.d.m(strD));
        this.title.r(i3.c.g(mediaItem));
        this.subTitle.r(i3.c.f(mediaItem));
        this.isPlayable.r(mediaItem.m() && !strD.startsWith("spotify:artist"));
        if (i3.c.a(mediaItem) != null) {
            this.icon.r(Uri.parse(i3.c.a(mediaItem)));
        }
        this.gravityCenter.r(strD.startsWith("spotify:artist"));
        onMediaItemUpdate();
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel
    protected boolean supportRxJava() {
        return false;
    }
}
