package p;

import android.support.v4.media.MediaBrowserCompat$MediaItem;
import com.spotify.base.java.logging.Logger;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wn implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ ry0 e;

    public /* synthetic */ wn(int i, ry0 ry0Var) {
        this.c = i;
        this.e = ry0Var;
    }

    public final void a(Throwable th) {
        int i = this.c;
        ry0 ry0Var = this.e;
        switch (i) {
            case 1:
                v03.j(th, "throwable");
                Logger.c(th, "Couldn't resolve children.", new Object[0]);
                ry0Var.invoke(EmptyList.c);
                break;
            default:
                v03.j(th, "throwable");
                Logger.c(th, "Couldn't resolve item.", new Object[0]);
                ry0Var.invoke(null);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        ry0 ry0Var = this.e;
        switch (i) {
            case 0:
                List list = (List) obj;
                v03.j(list, "p0");
                ry0Var.invoke(list);
                break;
            case 1:
                a((Throwable) obj);
                break;
            case 2:
                ry0Var.invoke((MediaBrowserCompat$MediaItem) obj);
                break;
            default:
                a((Throwable) obj);
                break;
        }
    }
}
