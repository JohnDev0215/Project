package spi;

import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.ui.widget.CarUiTextViewImpl;
import java.util.Objects;
import java.util.function.Predicate;
import spi.yc;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class n7 implements Predicate {
    public static final /* synthetic */ n7 b = new n7(0);
    public static final /* synthetic */ n7 c = new n7(1);
    public static final /* synthetic */ n7 d = new n7(2);
    public static final /* synthetic */ n7 e = new n7(3);
    public static final /* synthetic */ n7 f = new n7(4);
    public static final /* synthetic */ n7 g = new n7(5);
    public static final /* synthetic */ n7 h = new n7(6);
    public static final /* synthetic */ n7 i = new n7(7);
    public static final /* synthetic */ n7 j = new n7(8);
    public static final /* synthetic */ n7 k = new n7(9);
    public static final /* synthetic */ n7 l = new n7(10);
    public static final /* synthetic */ n7 m = new n7(11);
    public static final /* synthetic */ n7 n = new n7(12);
    public static final /* synthetic */ n7 o = new n7(13);
    public static final /* synthetic */ n7 p = new n7(14);
    public static final /* synthetic */ n7 q = new n7(15);
    public static final /* synthetic */ n7 r = new n7(16);
    public static final /* synthetic */ n7 s = new n7(17);
    public static final /* synthetic */ n7 t = new n7(18);
    public final /* synthetic */ int a;

    public /* synthetic */ n7(int i2) {
        this.a = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.a) {
            case 0:
                return Objects.nonNull((MediaBrowserCompat.MediaItem) obj);
            case 1:
                return Objects.nonNull((MediaItemMetadata) obj);
            case 2:
                return ((MediaItemMetadata) obj).c;
            case 3:
                return ((MediaItemMetadata) obj).c;
            case 4:
                MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                return mediaItemMetadata.d || mediaItemMetadata.c;
            case 5:
                return Objects.nonNull((MediaBrowserCompat.MediaItem) obj);
            case 6:
                return Objects.nonNull((MediaBrowserCompat.MediaItem) obj);
            case 7:
                MediaSessionCompat.QueueItem queueItem = (MediaSessionCompat.QueueItem) obj;
                return (queueItem == null || queueItem.getDescription() == null || queueItem.getDescription().getTitle() == null) ? false : true;
            case 8:
                return !((View) obj).isShown();
            case 9:
                return !((View) obj).isShown();
            case 10:
                return yc.l((View) obj);
            case 11:
                View view = (View) obj;
                if (!view.isShown()) {
                    return true;
                }
                if (view instanceof yc.a) {
                    return true ^ ((yc.a) view).a();
                }
                return false;
            case 12:
                View view2 = (View) obj;
                return yc.m(view2) && yc.b(view2);
            case 13:
                return !((View) obj).isShown();
            case 14:
                return !((View) obj).isShown();
            case 15:
                return !((View) obj).isShown();
            case 16:
                return ((View) obj) instanceof yc.a;
            case 17:
                return !((View) obj).isShown();
            default:
                u1 u1Var = (u1) obj;
                int i2 = CarUiTextViewImpl.d;
                return u1Var.a == Integer.MAX_VALUE && u1Var.b == Integer.MAX_VALUE;
        }
    }
}
