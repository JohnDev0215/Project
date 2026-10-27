package p;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import com.spotify.automotive.playback.ErrorState;
import com.spotify.automotive.ubi.LoggerPlaybackError;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class di1 implements ph1 {
    public final /* synthetic */ ei1 a;
    public final /* synthetic */ ErrorState b;

    public di1(ei1 ei1Var, ErrorState errorState) {
        this.a = ei1Var;
        this.b = errorState;
    }

    @Override // p.ph1
    public final void a(bi1 bi1Var) {
        long j;
        float f;
        int i;
        long jElapsedRealtime;
        v03.j(bi1Var, "mediaSessionCompat");
        ei1 ei1Var = this.a;
        ei1Var.getClass();
        Map map = fi1.a;
        ErrorState errorState = this.b;
        Object obj = map.get(errorState);
        if (obj == null) {
            obj = null;
        }
        LoggerPlaybackError loggerPlaybackError = (LoggerPlaybackError) obj;
        if (loggerPlaybackError != null) {
            ei1Var.c.e(loggerPlaybackError);
        }
        kk0 kk0Var = bi1Var.b;
        Bundle bundle = ((eh1) kk0Var.e).a().s;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("com.spotify.music.extra.ERROR_CODE", errorState.a());
        PlaybackStateCompat playbackStateCompatA = ((eh1) kk0Var.e).a();
        ArrayList arrayList = new ArrayList();
        int i2 = playbackStateCompatA.c;
        long j2 = playbackStateCompatA.k;
        long j3 = playbackStateCompatA.m;
        ArrayList arrayList2 = playbackStateCompatA.q;
        if (arrayList2 != null) {
            arrayList.addAll(arrayList2);
        }
        long j4 = playbackStateCompatA.r;
        int iB = errorState.b();
        String string = ei1Var.b.getString(errorState.c());
        if (errorState == ErrorState.VOICE_FAILURE || errorState == ErrorState.PREMIUM_IS_REQUIRED || errorState == ErrorState.PLAYBACK_TEMPORARY_ERROR || errorState == ErrorState.NETWORK_ERROR) {
            j = playbackStateCompatA.e;
            f = playbackStateCompatA.l;
            i = i2;
            jElapsedRealtime = playbackStateCompatA.f2p;
        } else {
            jElapsedRealtime = SystemClock.elapsedRealtime();
            j = -1;
            f = 0.0f;
            i = 7;
        }
        bi1Var.e(new PlaybackStateCompat(i, j, j2, f, j3, iB, string, jElapsedRealtime, arrayList, j4, bundle));
    }
}
