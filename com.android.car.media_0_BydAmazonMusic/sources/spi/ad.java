package spi;

import android.car.XCarRoute;
import android.car.media.IXCarMediaControl;
import android.car.utils.Logs;
import android.content.Context;
import android.support.v4.media.session.MediaControllerCompat;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.blankj.utilcode.util.Utils;
import java.util.Objects;

/* JADX INFO: compiled from: XCarMediaControlImpl.java */
/* JADX INFO: loaded from: classes.dex */
@Route(path = XCarRoute.CAR_MEDIA_CONTROL_SERVICE)
public final class ad implements IXCarMediaControl {
    public transient PlaybackViewModel a;

    @Override // android.car.media.IXCarMediaControl
    public final void changeShuffle() {
        PlaybackViewModel playbackViewModel = this.a;
        if (playbackViewModel == null || playbackViewModel.i.getValue() == null) {
            return;
        }
        this.a.i.getValue().a("SHUFFLE_ACTION", null);
    }

    @Override // android.car.media.IXCarMediaControl
    public final int getPlayState() {
        PlaybackViewModel playbackViewModel = this.a;
        if (playbackViewModel == null || playbackViewModel.j.getValue() == null) {
            return 0;
        }
        return this.a.j.getValue().c();
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public final void init(Context context) {
        this.a = PlaybackViewModel.a(Utils.getApp(), 1);
    }

    @Override // android.car.media.IXCarMediaControl
    public final void next() {
        PlaybackViewModel playbackViewModel = this.a;
        if (playbackViewModel == null || playbackViewModel.i.getValue() == null) {
            return;
        }
        this.a.i.getValue().f();
    }

    @Override // android.car.media.IXCarMediaControl
    public final void pause() {
        PlaybackViewModel playbackViewModel = this.a;
        if (playbackViewModel == null || playbackViewModel.i.getValue() == null) {
            return;
        }
        this.a.i.getValue().b();
    }

    @Override // android.car.media.IXCarMediaControl
    public final void play() {
        PlaybackViewModel playbackViewModel = this.a;
        if (playbackViewModel == null || playbackViewModel.i.getValue() == null) {
            return;
        }
        this.a.i.getValue().c();
    }

    @Override // android.car.media.IXCarMediaControl
    public final void previous() {
        PlaybackViewModel playbackViewModel = this.a;
        if (playbackViewModel == null || playbackViewModel.i.getValue() == null) {
            return;
        }
        this.a.i.getValue().g();
    }

    @Override // android.car.media.IXCarMediaControl
    public final void replay() {
        PlaybackViewModel playbackViewModel = this.a;
        if (playbackViewModel == null || playbackViewModel.i.getValue() == null) {
            return;
        }
        PlaybackViewModel.d value = this.a.i.getValue();
        Objects.requireNonNull(value);
        StringBuilder sb = new StringBuilder();
        sb.append("replay,");
        sb.append(value.a == null ? ",mMediaController is null" : "");
        Logs.d("PlaybackViewModel", sb.toString());
        MediaControllerCompat mediaControllerCompat = value.a;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().seekTo(0L);
            value.a.getTransportControls().play();
        }
    }

    @Override // android.car.media.IXCarMediaControl
    public final void stop() {
        PlaybackViewModel playbackViewModel = this.a;
        if (playbackViewModel == null || playbackViewModel.i.getValue() == null) {
            return;
        }
        this.a.i.getValue().h();
    }
}
