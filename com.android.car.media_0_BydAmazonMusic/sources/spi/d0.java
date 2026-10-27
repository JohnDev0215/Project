package spi;

import android.app.Activity;
import android.app.Application;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.ui.core.CarUiInstaller;
import com.byd.amazon.music.bean.MusicItem;
import java.util.HashSet;
import java.util.function.Function;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class d0 implements Function {
    public static final /* synthetic */ d0 b = new d0(0);
    public static final /* synthetic */ d0 c = new d0(1);
    public static final /* synthetic */ d0 d = new d0(2);
    public static final /* synthetic */ d0 e = new d0(3);
    public static final /* synthetic */ d0 f = new d0(4);
    public static final /* synthetic */ d0 g = new d0(5);
    public static final /* synthetic */ d0 h = new d0(6);
    public static final /* synthetic */ d0 i = new d0(7);
    public static final /* synthetic */ d0 j = new d0(8);
    public static final /* synthetic */ d0 k = new d0(9);
    public final /* synthetic */ int a;

    public /* synthetic */ d0(int i2) {
        this.a = i2;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return ((MediaItemMetadata) obj).g();
            case 1:
                return new MediaItemMetadata((MediaBrowserCompat.MediaItem) obj);
            case 2:
                return ((MediaItemMetadata) obj).g();
            case 3:
                return ((MediaItemMetadata) obj).g();
            case 4:
                MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                CharSequence charSequenceF = mediaItemMetadata.f();
                return new MusicItem(mediaItemMetadata.b(), charSequenceF == null ? "" : charSequenceF.toString(), "", "", "");
            case 5:
                return new MediaItemMetadata((MediaBrowserCompat.MediaItem) obj);
            case 6:
                return new MediaItemMetadata((MediaBrowserCompat.MediaItem) obj);
            case 7:
                return new MediaItemMetadata((MediaSessionCompat.QueueItem) obj);
            case 8:
                return ((qa) obj).k;
            default:
                HashSet<Application> hashSet = CarUiInstaller.a;
                return obj instanceof Activity ? Activity.class : obj.getClass();
        }
    }
}
