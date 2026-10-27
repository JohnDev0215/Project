package l3;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import java.lang.ref.WeakReference;
import java.util.List;
import k3.C1646h0;
import u.C2465e;

/* JADX INFO: renamed from: l3.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1767x extends MediaController.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f18617a;

    public C1767x(C1646h0 c1646h0) {
        this.f18617a = new WeakReference(c1646h0);
    }

    @Override // android.media.session.MediaController.Callback
    public final void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
        C1646h0 c1646h0 = (C1646h0) this.f18617a.get();
        if (c1646h0 == null || playbackInfo == null) {
            return;
        }
        c1646h0.a(new C1737B(playbackInfo.getPlaybackType(), new C1746b(new C1745a(playbackInfo.getAudioAttributes())), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onExtrasChanged(Bundle bundle) {
        P.o(bundle);
        C1646h0 c1646h0 = (C1646h0) this.f18617a.get();
        if (c1646h0 != null) {
            c1646h0.c(bundle);
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onMetadataChanged(MediaMetadata mediaMetadata) {
        C1741F c1741fCreateFromParcel;
        C1646h0 c1646h0 = (C1646h0) this.f18617a.get();
        if (c1646h0 != null) {
            C2465e c2465e = C1741F.f18520n;
            if (mediaMetadata != null) {
                Parcel parcelObtain = Parcel.obtain();
                mediaMetadata.writeToParcel(parcelObtain, 0);
                parcelObtain.setDataPosition(0);
                c1741fCreateFromParcel = C1741F.CREATOR.createFromParcel(parcelObtain);
                parcelObtain.recycle();
                c1741fCreateFromParcel.f18521m = mediaMetadata;
            } else {
                c1741fCreateFromParcel = null;
            }
            c1646h0.d(c1741fCreateFromParcel);
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onPlaybackStateChanged(PlaybackState playbackState) {
        C1646h0 c1646h0 = (C1646h0) this.f18617a.get();
        if (c1646h0 == null || c1646h0.c != null) {
            return;
        }
        c1646h0.e(X.a(playbackState));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onQueueChanged(List list) {
        C1646h0 c1646h0 = (C1646h0) this.f18617a.get();
        if (c1646h0 != null) {
            c1646h0.f(M.a(list));
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onQueueTitleChanged(CharSequence charSequence) {
        C1646h0 c1646h0 = (C1646h0) this.f18617a.get();
        if (c1646h0 != null) {
            c1646h0.g(charSequence);
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionDestroyed() {
        C1646h0 c1646h0 = (C1646h0) this.f18617a.get();
        if (c1646h0 != null) {
            c1646h0.f17965e.f18000b.a();
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionEvent(String str, Bundle bundle) {
        P.o(bundle);
        C1646h0 c1646h0 = (C1646h0) this.f18617a.get();
        if (c1646h0 != null) {
            c1646h0.h(str, bundle);
        }
    }
}
