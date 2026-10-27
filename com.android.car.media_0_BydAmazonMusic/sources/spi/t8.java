package spi;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.arch.core.util.Function;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.playback.PlaybackViewModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class t8 implements Function {
    public static final /* synthetic */ t8 b = new t8(0);
    public static final /* synthetic */ t8 c = new t8(1);
    public static final /* synthetic */ t8 d = new t8(2);
    public static final /* synthetic */ t8 e = new t8(3);
    public static final /* synthetic */ t8 f = new t8(4);
    public final /* synthetic */ int a;

    public /* synthetic */ t8(int i) {
        this.a = i;
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return ((b7) obj).b;
            case 1:
                return ((b7) obj).c();
            case 2:
                return ((MediaItemMetadata) obj).f();
            case 3:
                return ((MediaItemMetadata) obj).f;
            default:
                PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                PlaybackViewModel[] playbackViewModelArr = PlaybackViewModel.n;
                if (eVar == null) {
                    return e6.a(new u8(0L, 0L));
                }
                PlaybackStateCompat playbackStateCompat = eVar.c;
                MediaMetadataCompat mediaMetadataCompat = eVar.b;
                return new l9(playbackStateCompat, mediaMetadataCompat != null ? mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION) : 0L);
        }
    }
}
