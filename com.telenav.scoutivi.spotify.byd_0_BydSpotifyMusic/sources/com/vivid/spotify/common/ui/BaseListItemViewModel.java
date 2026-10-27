package com.vivid.spotify.common.ui;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.View;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.l;
import com.vivid.spotify.common.base.BaseViewModel;
import com.vivid.spotify.common.base.k;
import com.vivid.spotify.s;
import com.vivid.spotify.w;
import f4.m;
import i3.t;
import i3.v;

/* JADX INFO: loaded from: classes.dex */
public class BaseListItemViewModel<T> extends BaseViewModel implements k {
    private Drawable addToQueueIcon;
    public final l<Drawable> background;
    private final MediaControllerCompat.a callback;
    public final ObservableInt completionPercentage;
    private T content;
    public final l<Drawable> imageDrawable;
    public final ObservableInt imageResId;
    public final l<Uri> imgUri;
    public final ObservableBoolean is19Plus;
    public final ObservableBoolean isCompletionPercentageShow;
    public final ObservableBoolean isExplicit;
    public final ObservableBoolean isSelected;
    private View.OnClickListener mOnClickListener;
    private View.OnClickListener mOnRightButtonClickListener;
    private final m mediaManager;
    private final i3.m mediaPlayController;
    private final MediaBrowserCompat.MediaItem parentMediaItem;
    public final ObservableInt placeHolder;
    private Drawable playingIcon;
    private Drawable removeFromQueueIcon;
    public final l<Drawable> rightImageDrawable;
    public final ObservableBoolean showRightButton;
    public final l<String> subTitle;
    public final l<String> title;

    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            MediaSessionCompat.QueueItem queueItemA;
            super.d(mediaMetadataCompat);
            if (BaseListItemViewModel.this.content instanceof MediaBrowserCompat.MediaItem) {
                String strH = t.h(mediaMetadataCompat);
                String strL = com.vivid.spotify.nowplaying.b.l(BaseListItemViewModel.this.mediaPlayController.n());
                if (BaseListItemViewModel.this.parentMediaItem != null && !TextUtils.isEmpty(strL) && !TextUtils.equals(strL, i3.c.d(BaseListItemViewModel.this.parentMediaItem))) {
                    BaseListItemViewModel.this.isSelected.r(false);
                    return;
                }
                boolean zEquals = TextUtils.equals(i3.c.d((MediaBrowserCompat.MediaItem) BaseListItemViewModel.this.content), strH);
                BaseListItemViewModel.this.isSelected.r(zEquals);
                if (zEquals) {
                    BaseListItemViewModel baseListItemViewModel = BaseListItemViewModel.this;
                    baseListItemViewModel.updateItemPlayingState(baseListItemViewModel.mediaPlayController.q());
                    return;
                }
                return;
            }
            if (!(BaseListItemViewModel.this.content instanceof com.vivid.spotify.queue.k) || (queueItemA = ((com.vivid.spotify.queue.k) BaseListItemViewModel.this.content).a()) == null) {
                return;
            }
            boolean z6 = queueItemA.j() == BaseListItemViewModel.this.mediaPlayController.k();
            BaseListItemViewModel.this.isSelected.r(z6);
            if (!z6) {
                if (BaseListItemViewModel.this.removeFromQueueIcon == null) {
                    BaseListItemViewModel baseListItemViewModel2 = BaseListItemViewModel.this;
                    baseListItemViewModel2.removeFromQueueIcon = y.b.b(baseListItemViewModel2.getApplication().getResources(), s.remove_from_queue, BaseListItemViewModel.this.getApplication().getTheme());
                }
                BaseListItemViewModel baseListItemViewModel3 = BaseListItemViewModel.this;
                baseListItemViewModel3.rightImageDrawable.r(baseListItemViewModel3.removeFromQueueIcon);
                return;
            }
            BaseListItemViewModel baseListItemViewModel4 = BaseListItemViewModel.this;
            baseListItemViewModel4.updateItemPlayingState(baseListItemViewModel4.mediaPlayController.q());
            if (BaseListItemViewModel.this.addToQueueIcon == null) {
                BaseListItemViewModel baseListItemViewModel5 = BaseListItemViewModel.this;
                baseListItemViewModel5.addToQueueIcon = y.b.b(baseListItemViewModel5.getApplication().getResources(), s.add_to_queue, BaseListItemViewModel.this.getApplication().getTheme());
            }
            BaseListItemViewModel baseListItemViewModel6 = BaseListItemViewModel.this;
            baseListItemViewModel6.rightImageDrawable.r(baseListItemViewModel6.addToQueueIcon);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            super.e(playbackStateCompat);
            if (BaseListItemViewModel.this.isSelected.q()) {
                BaseListItemViewModel.this.updateItemPlayingState(playbackStateCompat);
            }
        }
    }

    public BaseListItemViewModel(Application application, m mVar, i3.m mVar2) {
        this(application, mVar, mVar2, null);
    }

    private void onContentUpdate() {
        this.callback.d(this.mediaPlayController.n());
        this.callback.e(this.mediaPlayController.q());
    }

    public T getContent() {
        return this.content;
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel
    protected boolean listenerLanguageChange() {
        return false;
    }

    @Override // com.vivid.spotify.common.base.k
    public void onDestroy() {
        this.mOnClickListener = null;
        this.mOnRightButtonClickListener = null;
    }

    public void onItemClicked() {
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(null);
            return;
        }
        T t6 = this.content;
        if (t6 instanceof MediaBrowserCompat.MediaItem) {
            this.mediaManager.r((MediaBrowserCompat.MediaItem) t6);
        }
    }

    @Override // com.vivid.spotify.common.base.k
    public void onPause() {
        this.mediaPlayController.M(this.callback);
    }

    @Override // com.vivid.spotify.common.base.k
    public void onResume() {
        this.mediaPlayController.h(this.callback);
    }

    public void onRightButtonClicked() {
        View.OnClickListener onClickListener = this.mOnRightButtonClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(null);
        }
    }

    public void onStop() {
    }

    public void setBackground(Drawable drawable) {
        this.background.r(drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setContent(T t6) {
        MediaSessionCompat.QueueItem queueItemA;
        this.content = t6;
        if (t6 instanceof MediaBrowserCompat.MediaItem) {
            this.imgUri.r(Uri.parse(i3.c.a((MediaBrowserCompat.MediaItem) t6)));
        } else if ((t6 instanceof com.vivid.spotify.queue.k) && (queueItemA = ((com.vivid.spotify.queue.k) t6).a()) != null) {
            this.imgUri.r(Uri.parse(v.a(queueItemA)));
        }
        onContentUpdate();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnRightButtonClickListener(View.OnClickListener onClickListener) {
        this.mOnRightButtonClickListener = onClickListener;
    }

    public void setPlaceHolderByUri(String str) {
        this.placeHolder.r(f4.d.m(str));
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel
    protected boolean supportRxJava() {
        return false;
    }

    void updateItemPlayingState(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat != null) {
            if (playbackStateCompat.t() == 3) {
                this.imageResId.r(w.playing_animation);
                this.imageDrawable.r(null);
            } else {
                this.imageResId.r(0);
                if (this.playingIcon == null) {
                    this.playingIcon = y.b.b(getApplication().getResources(), s.icon_playing, getApplication().getTheme());
                }
                this.imageDrawable.r(this.playingIcon);
            }
        }
    }

    public BaseListItemViewModel(Application application, m mVar, i3.m mVar2, MediaBrowserCompat.MediaItem mediaItem) {
        super(application);
        this.isSelected = new ObservableBoolean();
        this.title = new l<>();
        this.subTitle = new l<>();
        this.imgUri = new l<>();
        this.imageDrawable = new l<>();
        this.showRightButton = new ObservableBoolean();
        this.rightImageDrawable = new l<>();
        this.imageResId = new ObservableInt();
        this.placeHolder = new ObservableInt();
        this.isExplicit = new ObservableBoolean();
        this.is19Plus = new ObservableBoolean();
        this.background = new l<>();
        this.completionPercentage = new ObservableInt();
        this.isCompletionPercentageShow = new ObservableBoolean();
        this.callback = new a();
        this.mediaManager = mVar;
        this.mediaPlayController = mVar2;
        this.parentMediaItem = mediaItem;
    }
}
