package androidx.leanback.app;

import android.view.View;
import androidx.leanback.media.PlaybackGlueHost;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.OnActionClickedListener;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.PlaybackRowPresenter;
import androidx.leanback.widget.PlaybackSeekUi;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class PlaybackFragmentGlueHost extends PlaybackGlueHost implements PlaybackSeekUi {
    final PlaybackFragment mFragment;
    final PlaybackGlueHost.PlayerCallback mPlayerCallback = new PlaybackGlueHost.PlayerCallback() { // from class: androidx.leanback.app.PlaybackFragmentGlueHost.2
        @Override // androidx.leanback.media.PlaybackGlueHost.PlayerCallback
        public void onBufferingStateChanged(boolean start) {
            PlaybackFragmentGlueHost.this.mFragment.onBufferingStateChanged(start);
        }

        @Override // androidx.leanback.media.PlaybackGlueHost.PlayerCallback
        public void onError(int errorCode, CharSequence errorMessage) {
            PlaybackFragmentGlueHost.this.mFragment.onError(errorCode, errorMessage);
        }

        @Override // androidx.leanback.media.PlaybackGlueHost.PlayerCallback
        public void onVideoSizeChanged(int videoWidth, int videoHeight) {
            PlaybackFragmentGlueHost.this.mFragment.onVideoSizeChanged(videoWidth, videoHeight);
        }
    };

    public PlaybackFragmentGlueHost(PlaybackFragment fragment) {
        this.mFragment = fragment;
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void setControlsOverlayAutoHideEnabled(boolean enabled) {
        this.mFragment.setControlsOverlayAutoHideEnabled(enabled);
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public boolean isControlsOverlayAutoHideEnabled() {
        return this.mFragment.isControlsOverlayAutoHideEnabled();
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void setOnKeyInterceptListener(View.OnKeyListener onKeyListener) {
        this.mFragment.setOnKeyInterceptListener(onKeyListener);
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void setOnActionClickedListener(final OnActionClickedListener listener) {
        if (listener == null) {
            this.mFragment.setOnPlaybackItemViewClickedListener(null);
        } else {
            this.mFragment.setOnPlaybackItemViewClickedListener(new OnItemViewClickedListener() { // from class: androidx.leanback.app.PlaybackFragmentGlueHost.1
                @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
                public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                    if (item instanceof Action) {
                        listener.onActionClicked((Action) item);
                    }
                }
            });
        }
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void setHostCallback(PlaybackGlueHost.HostCallback callback) {
        this.mFragment.setHostCallback(callback);
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void notifyPlaybackRowChanged() {
        this.mFragment.notifyPlaybackRowChanged();
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void setPlaybackRowPresenter(PlaybackRowPresenter presenter) {
        this.mFragment.setPlaybackRowPresenter(presenter);
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void setPlaybackRow(Row row) {
        this.mFragment.setPlaybackRow(row);
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void fadeOut() {
        this.mFragment.fadeOut();
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public boolean isControlsOverlayVisible() {
        return this.mFragment.isControlsOverlayVisible();
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void hideControlsOverlay(boolean runAnimation) {
        this.mFragment.hideControlsOverlay(runAnimation);
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public void showControlsOverlay(boolean runAnimation) {
        this.mFragment.showControlsOverlay(runAnimation);
    }

    @Override // androidx.leanback.widget.PlaybackSeekUi
    public void setPlaybackSeekUiClient(PlaybackSeekUi.Client client) {
        this.mFragment.setPlaybackSeekUiClient(client);
    }

    @Override // androidx.leanback.media.PlaybackGlueHost
    public PlaybackGlueHost.PlayerCallback getPlayerCallback() {
        return this.mPlayerCallback;
    }
}
