package p;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import android.support.v4.media.MediaDescriptionCompat;
import com.google.common.base.Optional;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class tc1 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.automotive.browse.a e;

    public /* synthetic */ tc1(com.spotify.automotive.browse.a aVar, int i) {
        this.c = i;
        this.e = aVar;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        com.spotify.automotive.browse.a aVar = this.e;
        int i = this.c;
        switch (i) {
            case 0:
                List list = (List) obj;
                switch (i) {
                    case 0:
                        v03.j(list, "mediaBrowserItems");
                        return com.spotify.automotive.browse.a.d(aVar, list);
                    default:
                        v03.j(list, "mediaBrowserItems");
                        return com.spotify.automotive.browse.a.d(aVar, list);
                }
            case 1:
                List list2 = (List) obj;
                switch (i) {
                    case 0:
                        v03.j(list2, "mediaBrowserItems");
                        return com.spotify.automotive.browse.a.d(aVar, list2);
                    default:
                        v03.j(list2, "mediaBrowserItems");
                        return com.spotify.automotive.browse.a.d(aVar, list2);
                }
            default:
                dg1 dg1Var = (dg1) ((Optional) obj).h();
                if (dg1Var == null) {
                    return Optional.a();
                }
                aVar.getClass();
                jh1 jh1Var = dg1Var.a;
                return Optional.e(new MediaBrowserCompat$MediaItem(new MediaDescriptionCompat((String) jh1Var.a, (CharSequence) jh1Var.b, (CharSequence) jh1Var.c, (CharSequence) jh1Var.d, (Bitmap) jh1Var.e, (Uri) jh1Var.f, (Bundle) jh1Var.h, (Uri) jh1Var.g), dg1Var.b));
        }
    }
}
