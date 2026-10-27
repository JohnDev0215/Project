package com.vivid.spotify.nowplaying;

import android.app.Application;
import android.net.Uri;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.BidiFormatter;
import android.text.TextUtils;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.t;
import com.vivid.spotify.common.base.BaseViewModel;
import com.vivid.spotify.s;
import g4.f;
import i3.m;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NowPlayingViewModel extends BaseViewModel {
    private static final String TAG = "NowPlayingViewModel";
    public g4.h barBottomControlAction2;
    public g4.h barBottomControlAction3;
    public g4.h barCollectControlAction;
    private final ArrayList<g4.h> barObservableControlActions;
    public g4.h barPlayControlAction;
    public g4.h bottomControlAction2;
    public g4.h bottomControlAction3;
    public g4.h bottomControlAction4;
    public g4.h bottomControlAction5;
    public final ObservableInt bufferingPosition;
    private final MediaControllerCompat.a callback;
    public g4.h collectControlAction;
    private final g4.f controlActionFactory;
    public androidx.databinding.l<Uri> cover;
    private String currentPlayMediaUri;
    public final ObservableInt duration;
    public ObservableBoolean enablePlaylist;
    public final ObservableBoolean enableSeekTo;
    public ObservableBoolean isInvalidSong;
    public final t<Boolean> isMetadataChanged;
    public ObservableBoolean loading;
    private boolean mIsStartTrackingTouch;
    private PlaybackStateCompat mState;
    private final m.e mediaInfoChangeListener;
    private final i3.m mediaPlayController;
    private final g4.g nowPlayingBarControlActionFactory;
    private final ArrayList<g4.h> observableControlActions;
    public final ObservableInt placeHolderResId;
    public g4.h playControlAction;
    public final ObservableInt position;
    private final t<Boolean> showPage;
    public g4.h speedControlAction;
    public androidx.databinding.l<String> subtitle;
    public androidx.databinding.l<String> title;

    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            int iF;
            h3.i.b(NowPlayingViewModel.TAG).b("metadata:" + mediaMetadataCompat, new Object[0]);
            String strH = i3.t.h(mediaMetadataCompat);
            if (NowPlayingViewModel.this.currentPlayMediaUri == null || !TextUtils.equals(NowPlayingViewModel.this.currentPlayMediaUri, strH)) {
                NowPlayingViewModel.this.currentPlayMediaUri = strH;
                NowPlayingViewModel.this.isMetadataChanged.j(Boolean.TRUE);
                if (mediaMetadataCompat != null) {
                    NowPlayingViewModel.this.handleMetadata(mediaMetadataCompat);
                }
            }
            if (mediaMetadataCompat == null || NowPlayingViewModel.this.duration.q() == (iF = i3.t.f(mediaMetadataCompat))) {
                return;
            }
            h3.i.b(NowPlayingViewModel.TAG).b("duration change old = " + NowPlayingViewModel.this.duration.q() + "   new  = " + iF, new Object[0]);
            NowPlayingViewModel.this.duration.r(iF);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            NowPlayingViewModel.this.mState = playbackStateCompat;
            NowPlayingViewModel.this.mIsStartTrackingTouch = false;
            if (playbackStateCompat != null) {
                NowPlayingViewModel.this.handlePlaybackState(playbackStateCompat);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void f(List<MediaSessionCompat.QueueItem> list) {
            super.f(list);
            h3.l lVarB = h3.i.b(NowPlayingViewModel.TAG);
            Object[] objArr = new Object[1];
            objArr[0] = list == null ? null : Integer.valueOf(list.size());
            lVarB.i("onQueueChanged queue:%s", objArr);
            if (NowPlayingViewModel.this.mState != null) {
                NowPlayingViewModel nowPlayingViewModel = NowPlayingViewModel.this;
                nowPlayingViewModel.fillPlaylistButton(nowPlayingViewModel.mState, list);
            }
        }
    }

    class b implements m.e {
        b() {
        }

        @Override // i3.m.e
        public void a(int i7) {
            if (NowPlayingViewModel.this.mIsStartTrackingTouch) {
                return;
            }
            NowPlayingViewModel.this.position.r(i7);
        }
    }

    public NowPlayingViewModel(Application application, i3.m mVar, g4.f fVar, g4.g gVar) {
        super(application);
        this.title = new androidx.databinding.l<>();
        this.subtitle = new androidx.databinding.l<>();
        this.cover = new androidx.databinding.l<>();
        ObservableInt observableInt = new ObservableInt(-1);
        this.placeHolderResId = observableInt;
        this.isInvalidSong = new ObservableBoolean();
        this.loading = new ObservableBoolean();
        this.enablePlaylist = new ObservableBoolean();
        this.enableSeekTo = new ObservableBoolean();
        this.duration = new ObservableInt();
        this.position = new ObservableInt();
        this.bufferingPosition = new ObservableInt();
        this.barPlayControlAction = new g4.h();
        this.barBottomControlAction2 = new g4.h();
        this.barBottomControlAction3 = new g4.h();
        this.barCollectControlAction = new g4.h();
        this.barObservableControlActions = new ArrayList<>();
        this.playControlAction = new g4.h();
        this.collectControlAction = new g4.h();
        this.speedControlAction = new g4.h();
        this.bottomControlAction2 = new g4.h();
        this.bottomControlAction3 = new g4.h();
        this.bottomControlAction4 = new g4.h();
        this.bottomControlAction5 = new g4.h();
        this.observableControlActions = new ArrayList<>();
        Boolean bool = Boolean.FALSE;
        this.showPage = new t<>(bool);
        this.isMetadataChanged = new t<>(bool);
        a aVar = new a();
        this.callback = aVar;
        b bVar = new b();
        this.mediaInfoChangeListener = bVar;
        h3.i.b(TAG).b("NowPlayingViewModel init", new Object[0]);
        initObservableControlActions();
        this.controlActionFactory = fVar;
        this.nowPlayingBarControlActionFactory = gVar;
        this.mediaPlayController = mVar;
        mVar.h(aVar);
        mVar.i(bVar);
        observableInt.r(s.ic_track_placeholder);
    }

    private void fillCollectButton(g4.h hVar, g4.f fVar, PlaybackStateCompat playbackStateCompat) {
        PlaybackStateCompat.CustomAction customActionB = m.b(playbackStateCompat);
        if (customActionB != null) {
            hVar.g(fVar.k(customActionB));
        } else {
            hVar.a();
        }
    }

    private void fillNowPlayingBarButtons(PlaybackStateCompat playbackStateCompat) {
        fillPlayOrPauseButton(this.barPlayControlAction, this.nowPlayingBarControlActionFactory, playbackStateCompat);
        fillCollectButton(this.barCollectControlAction, this.nowPlayingBarControlActionFactory, playbackStateCompat);
        fillOtherButtons(m.f(this.nowPlayingBarControlActionFactory, playbackStateCompat), this.barObservableControlActions);
    }

    private void fillNowPlayingViewButtons(PlaybackStateCompat playbackStateCompat) {
        fillPlayOrPauseButton(this.playControlAction, this.controlActionFactory, playbackStateCompat);
        fillCollectButton(this.collectControlAction, this.controlActionFactory, playbackStateCompat);
        fillSpeedButton(playbackStateCompat);
        fillSeekBar(playbackStateCompat);
        fillPlaylistButton(playbackStateCompat, this.mediaPlayController.r());
        fillOtherButtons(m.g(this.controlActionFactory, playbackStateCompat), this.observableControlActions);
    }

    private void fillOtherButtons(List<g4.a> list, ArrayList<g4.h> arrayList) {
        h3.i.b(TAG).g("start");
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            g4.h hVar = arrayList.get(i7);
            if (i7 < list.size()) {
                g4.a aVar = list.get(i7);
                h3.i.b(TAG).g("controlAction name:" + aVar.d() + ", icon:" + aVar.b());
                hVar.g(aVar);
            } else {
                hVar.a();
            }
        }
        h3.i.b(TAG).g("end");
    }

    private void fillPlayOrPauseButton(g4.h hVar, g4.f fVar, PlaybackStateCompat playbackStateCompat) {
        hVar.g(fVar.l((playbackStateCompat.t() == 3 || playbackStateCompat.t() == 6) ? f.b.Pause : f.b.Play, true));
        this.loading.r(playbackStateCompat.t() == 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fillPlaylistButton(PlaybackStateCompat playbackStateCompat, List<MediaSessionCompat.QueueItem> list) {
        this.enablePlaylist.r(((playbackStateCompat.c() & 4096) == 0 || list == null || list.isEmpty()) ? false : true);
    }

    private void fillSeekBar(PlaybackStateCompat playbackStateCompat) {
        this.enableSeekTo.r((playbackStateCompat.c() & 256) != 0);
    }

    private void fillSpeedButton(PlaybackStateCompat playbackStateCompat) {
        PlaybackStateCompat.CustomAction customActionC = m.c(playbackStateCompat, n.PlaybackSpeed);
        if (customActionC == null) {
            this.speedControlAction.a();
            return;
        }
        this.speedControlAction.g(this.controlActionFactory.k(customActionC));
        int iR = this.controlActionFactory.r(String.valueOf(customActionC.j()));
        if (iR != 0) {
            this.speedControlAction.c().r(getApplication().getDrawable(iR));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMetadata(MediaMetadataCompat mediaMetadataCompat) {
        this.isInvalidSong.r("#media_id_useless#".equals(i3.t.g(mediaMetadataCompat)));
        this.title.r(BidiFormatter.getInstance().unicodeWrap(i3.t.j(mediaMetadataCompat)));
        String strD = i3.t.d(mediaMetadataCompat);
        if (TextUtils.isEmpty(strD)) {
            strD = i3.t.a(mediaMetadataCompat);
        }
        this.subtitle.r(BidiFormatter.getInstance().unicodeWrap(strD));
        String strK = i3.t.k(mediaMetadataCompat);
        if (TextUtils.isEmpty(strK)) {
            this.cover.r(null);
        } else {
            this.cover.r(Uri.parse(strK));
        }
        this.duration.r(i3.t.f(mediaMetadataCompat));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlaybackState(PlaybackStateCompat playbackStateCompat) {
        fillNowPlayingBarButtons(playbackStateCompat);
        fillNowPlayingViewButtons(playbackStateCompat);
    }

    private void initObservableControlActions() {
        this.observableControlActions.add(this.bottomControlAction2);
        this.observableControlActions.add(this.bottomControlAction3);
        this.observableControlActions.add(this.bottomControlAction4);
        this.observableControlActions.add(this.bottomControlAction5);
        this.barObservableControlActions.add(this.barBottomControlAction2);
        this.barObservableControlActions.add(this.barBottomControlAction3);
    }

    public LiveData<Boolean> observeShowPage() {
        return this.showPage;
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, androidx.lifecycle.a0
    protected void onCleared() {
        super.onCleared();
        this.mediaPlayController.M(this.callback);
        this.mediaPlayController.N(this.mediaInfoChangeListener);
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, v3.a
    public void onLanguageChange(String str) {
        super.onLanguageChange(str);
        this.currentPlayMediaUri = null;
        this.callback.d(this.mediaPlayController.n());
        this.callback.e(this.mediaPlayController.q());
    }

    public void onStartTrackingTouch() {
        h3.i.b(TAG).g("2");
        this.mIsStartTrackingTouch = true;
    }

    public void onStopTrackingTouch() {
        h3.i.b(TAG).g("1");
        this.mediaPlayController.O(this.position.q());
    }

    public boolean showPage(boolean z6) {
        this.showPage.j(Boolean.valueOf(z6));
        return z6;
    }
}
