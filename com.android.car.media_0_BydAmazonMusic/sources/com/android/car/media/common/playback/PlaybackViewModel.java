package com.android.car.media.common.playback;

import android.R;
import android.annotation.SuppressLint;
import android.app.Application;
import android.car.utils.Logs;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.android.car.media.common.source.a;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import spi.b0;
import spi.c7;
import spi.d0;
import spi.e6;
import spi.n7;
import spi.q6;
import spi.t8;
import spi.u8;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackViewModel extends AndroidViewModel {
    public static PlaybackViewModel[] n = new PlaybackViewModel[2];
    public static final MediaMetadata o = new MediaMetadata.Builder().build();
    public final c a;
    public final Observer<a.c> b;
    public final c7.a c;
    public final MutableLiveData<c7> d;
    public final MutableLiveData<MediaItemMetadata> e;
    public final MutableLiveData<List<MediaItemMetadata>> f;
    public final MutableLiveData<Boolean> g;
    public final MutableLiveData<CharSequence> h;
    public final MutableLiveData<d> i;
    public final MutableLiveData<e> j;
    public final MutableLiveData<Boolean> k;
    public final LiveData<u8> l;
    public final b m;

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[a.e.values().length];
            a = iArr;
            try {
                iArr[a.e.DISCONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.e.REJECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[a.e.CONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[a.e.CONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[a.e.SUSPENDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @VisibleForTesting
    public interface b {
    }

    public class c extends MediaControllerCompat.Callback {
        public a.c a;
        public MediaControllerCompat b;
        public MediaMetadataCompat c;
        public PlaybackStateCompat d;

        private c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.android.car.media.common.playback.PlaybackViewModel$a] */
        /* JADX WARN: Type inference failed for: r0v1, types: [android.content.res.TypedArray] */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3, types: [android.content.res.TypedArray] */
        /* JADX WARN: Type inference failed for: r0v4, types: [android.content.res.TypedArray] */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        public final void a(MediaControllerCompat mediaControllerCompat) {
            c7 c7Var;
            ?? r0;
            ?? ObtainStyledAttributes = 0;
            ObtainStyledAttributes = 0;
            ObtainStyledAttributes = 0;
            this.c = null;
            this.d = null;
            this.b = mediaControllerCompat;
            PlaybackViewModel playbackViewModel = PlaybackViewModel.this;
            playbackViewModel.i.setValue(new d(playbackViewModel, mediaControllerCompat, ObtainStyledAttributes));
            MediaControllerCompat mediaControllerCompat2 = this.b;
            if (mediaControllerCompat2 != null) {
                mediaControllerCompat2.registerCallback(this);
                PlaybackViewModel playbackViewModel2 = PlaybackViewModel.this;
                MutableLiveData<c7> mutableLiveData = playbackViewModel2.d;
                c7.a aVar = playbackViewModel2.c;
                String packageName = mediaControllerCompat.getPackageName();
                Objects.requireNonNull(aVar);
                try {
                    if (packageName == null) {
                        c7Var = c7.b;
                    } else {
                        try {
                            ApplicationInfo applicationInfo = aVar.a.getPackageManager().getApplicationInfo(packageName, 128);
                            Context contextCreatePackageContext = aVar.a.createPackageContext(packageName, 0);
                            Bundle bundle = applicationInfo.metaData;
                            int i = bundle != null ? bundle.getInt("com.google.android.gms.car.application.theme") : 0;
                            if (i == 0) {
                                i = applicationInfo.theme;
                            }
                            contextCreatePackageContext.setTheme(i);
                            ObtainStyledAttributes = contextCreatePackageContext.getTheme().obtainStyledAttributes(new int[]{R.attr.colorPrimary, R.attr.colorAccent, R.attr.colorPrimaryDark});
                            c7Var = new c7(ObtainStyledAttributes.getColor(0, 0), ObtainStyledAttributes.getColor(1, 0), ObtainStyledAttributes.getColor(2, 0));
                            r0 = ObtainStyledAttributes;
                        } catch (PackageManager.NameNotFoundException e) {
                            Log.w("MediaSourceColors.Factory", "Unable to update media client package attributes.", e);
                            c7Var = c7.b;
                            r0 = ObtainStyledAttributes;
                            if (ObtainStyledAttributes != 0) {
                            }
                            mutableLiveData.setValue(c7Var);
                            onMetadataChanged(this.b.getMetadata());
                            onPlaybackStateChanged(this.b.getPlaybackState());
                            onQueueChanged(this.b.getQueue());
                            onQueueTitleChanged(this.b.getQueueTitle());
                            b();
                        }
                        r0.recycle();
                    }
                    mutableLiveData.setValue(c7Var);
                    onMetadataChanged(this.b.getMetadata());
                    onPlaybackStateChanged(this.b.getPlaybackState());
                    onQueueChanged(this.b.getQueue());
                    onQueueTitleChanged(this.b.getQueueTitle());
                } catch (Throwable th) {
                    if (ObtainStyledAttributes != 0) {
                        ObtainStyledAttributes.recycle();
                    }
                    throw th;
                }
            } else {
                PlaybackViewModel.this.d.setValue(null);
                onMetadataChanged(null);
                onPlaybackStateChanged(null);
                onQueueChanged(null);
                onQueueTitleChanged(null);
            }
            b();
        }

        public final void b() {
            PlaybackStateCompat playbackStateCompat;
            MediaControllerCompat mediaControllerCompat = this.b;
            if (mediaControllerCompat == null || (playbackStateCompat = this.d) == null) {
                PlaybackViewModel.this.j.setValue(null);
            } else {
                PlaybackViewModel.this.j.setValue(new e(mediaControllerCompat, this.c, playbackStateCompat));
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onMetadataChanged(@Nullable MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat == null || !PlaybackViewModel.o.equals(mediaMetadataCompat.getMediaMetadata())) {
                this.c = mediaMetadataCompat;
            } else {
                this.c = null;
            }
            PlaybackViewModel.this.e.setValue(this.c != null ? new MediaItemMetadata(this.c) : null);
            b();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            this.d = playbackStateCompat;
            b();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onQueueChanged(@Nullable List<MediaSessionCompat.QueueItem> list) {
            StringBuilder sbK = spi.c.k("onQueueChanged queue:");
            sbK.append(list == null ? "null" : Integer.valueOf(list.size()));
            Logs.d("PlaybackViewModel", sbK.toString());
            List<MediaItemMetadata> listEmptyList = list == null ? Collections.emptyList() : (List) list.stream().filter(n7.i).map(d0.i).collect(Collectors.toList());
            PlaybackViewModel.this.f.setValue(listEmptyList);
            PlaybackViewModel.this.g.setValue(Boolean.valueOf(listEmptyList.size() > 1));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onQueueTitleChanged(CharSequence charSequence) {
            PlaybackViewModel.this.h.setValue(charSequence);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onSessionDestroyed() {
            Log.w("PlaybackViewModel", "onSessionDestroyed");
            a(null);
        }

        public /* synthetic */ c(PlaybackViewModel playbackViewModel, a aVar) {
            this();
        }
    }

    public class d {
        public final MediaControllerCompat a;

        public /* synthetic */ d(PlaybackViewModel playbackViewModel, MediaControllerCompat mediaControllerCompat, a aVar) {
            this(mediaControllerCompat);
        }

        public final void a(String str, Bundle bundle) {
            MediaControllerCompat mediaControllerCompat = this.a;
            if (mediaControllerCompat == null) {
                return;
            }
            MediaControllerCompat.TransportControls transportControls = mediaControllerCompat.getTransportControls();
            if (!"com.android.car.media.common.ACTION_SET_RATING".equals(str)) {
                transportControls.sendCustomAction(str, bundle);
                return;
            }
            boolean z = false;
            if (bundle != null && bundle.getBoolean("com.android.car.media.common.EXTRA_SET_HEART", false)) {
                z = true;
            }
            transportControls.setRating(RatingCompat.newHeartRating(z));
        }

        public final void b() {
            StringBuilder sbK = spi.c.k("pause");
            sbK.append(this.a == null ? ",mMediaController is null" : "");
            Logs.d("PlaybackViewModel", sbK.toString());
            MediaControllerCompat mediaControllerCompat = this.a;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.getTransportControls().pause();
            }
        }

        public final void c() {
            StringBuilder sbK = spi.c.k("play");
            sbK.append(this.a == null ? ",mMediaController is null" : "");
            Logs.d("PlaybackViewModel", sbK.toString());
            MediaControllerCompat mediaControllerCompat = this.a;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.getTransportControls().play();
            }
        }

        public final void d() {
            StringBuilder sbK = spi.c.k("prepare,");
            sbK.append(this.a == null ? ",mMediaController is null" : "");
            Logs.d("PlaybackViewModel", sbK.toString());
            MediaControllerCompat mediaControllerCompat = this.a;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.getTransportControls().prepare();
            }
        }

        public final void e(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append("seekTo,");
            sb.append(j);
            sb.append(this.a == null ? ",mMediaController is null" : "");
            Logs.d("PlaybackViewModel", sb.toString());
            MediaControllerCompat mediaControllerCompat = this.a;
            if (mediaControllerCompat != null) {
                PlaybackStateCompat playbackState = mediaControllerCompat.getPlaybackState();
                PlaybackViewModel.this.a.onPlaybackStateChanged(new PlaybackStateCompat.Builder(playbackState).setState(playbackState.getState(), j, playbackState.getPlaybackSpeed()).build());
                this.a.getTransportControls().seekTo(j);
            }
        }

        public final void f() {
            StringBuilder sbK = spi.c.k("skipToNext");
            sbK.append(this.a == null ? ",mMediaController is null" : "");
            Logs.d("PlaybackViewModel", sbK.toString());
            MediaControllerCompat mediaControllerCompat = this.a;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.getTransportControls().skipToNext();
            }
        }

        public final void g() {
            StringBuilder sbK = spi.c.k("skipToPrevious");
            sbK.append(this.a == null ? ",mMediaController is null" : "");
            Logs.d("PlaybackViewModel", sbK.toString());
            MediaControllerCompat mediaControllerCompat = this.a;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.getTransportControls().seekTo(0L);
                this.a.getTransportControls().skipToPrevious();
            }
        }

        public final void h() {
            StringBuilder sbK = spi.c.k("stop");
            sbK.append(this.a == null ? ",mMediaController is null" : "");
            Logs.d("PlaybackViewModel", sbK.toString());
            MediaControllerCompat mediaControllerCompat = this.a;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.getTransportControls().seekTo(0L);
                this.a.getTransportControls().stop();
            }
        }

        private d(@Nullable MediaControllerCompat mediaControllerCompat) {
            this.a = mediaControllerCompat;
        }
    }

    public static final class e {
        public final MediaControllerCompat a;

        @Nullable
        public final MediaMetadataCompat b;
        public final PlaybackStateCompat c;

        public e(@NonNull MediaControllerCompat mediaControllerCompat, @Nullable MediaMetadataCompat mediaMetadataCompat, @NonNull PlaybackStateCompat playbackStateCompat) {
            this.a = mediaControllerCompat;
            this.b = mediaMetadataCompat;
            this.c = playbackStateCompat;
        }

        public final int a() {
            return this.c.getErrorCode();
        }

        @SuppressLint({"WrongConstant"})
        public final int b() {
            long actions = this.c.getActions();
            int i = (514 & actions) != 0 ? 3 : (1 & actions) != 0 ? 2 : 0;
            switch (this.c.getState()) {
                case 0:
                case 1:
                case 2:
                case 7:
                    return (actions & 4) != 0 ? 1 : 0;
                case 3:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 11:
                    return i;
                default:
                    Log.w("PlaybackViewModel", String.format("Unknown PlaybackState: %d", Integer.valueOf(this.c.getState())));
                    return 0;
            }
        }

        public final int c() {
            return this.c.getState();
        }

        public final boolean d() {
            int state = this.c.getState();
            return state == 6 || state == 8 || state == 4 || state == 5 || state == 10 || state == 9 || state == 11;
        }

        public final boolean e() {
            return (this.c.getActions() & 32) != 0;
        }

        public final boolean f() {
            return (this.c.getActions() & 16) != 0;
        }
    }

    public static class f {
        public final int a;

        @Nullable
        public final String b;

        @NonNull
        public final String c;

        @Nullable
        public final Bundle d;

        public f(int i, String str, @NonNull String str2, @Nullable Bundle bundle) {
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = bundle;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || f.class != obj.getClass()) {
                return false;
            }
            f fVar = (f) obj;
            return this.a == fVar.a && Objects.equals(this.b, fVar.b) && Objects.equals(this.c, fVar.c) && Objects.equals(this.d, fVar.d);
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.a), this.b, this.c, this.d);
        }
    }

    private PlaybackViewModel(Application application, int i) {
        this(application, MediaSourceViewModel.a(application, i).d, new q6(application));
    }

    public static PlaybackViewModel a(@NonNull Application application, int i) {
        PlaybackViewModel[] playbackViewModelArr = n;
        if (playbackViewModelArr[i] == null) {
            playbackViewModelArr[i] = new PlaybackViewModel(application, i);
        }
        return playbackViewModelArr[i];
    }

    @VisibleForTesting
    public PlaybackViewModel(Application application, LiveData<a.c> liveData, b bVar) {
        super(application);
        c cVar = new c(this, null);
        this.a = cVar;
        b0 b0Var = new b0(cVar, 5);
        this.b = b0Var;
        this.d = e6.a(null);
        this.e = e6.a(null);
        this.f = e6.a(null);
        this.g = e6.a(null);
        this.h = e6.a(null);
        this.i = e6.a(null);
        MutableLiveData<e> mutableLiveDataA = e6.a(null);
        this.j = mutableLiveDataA;
        this.k = e6.a(null);
        this.l = Transformations.switchMap(mutableLiveDataA, t8.f);
        this.m = bVar;
        this.c = new c7.a(application);
        liveData.observeForever(b0Var);
    }
}
