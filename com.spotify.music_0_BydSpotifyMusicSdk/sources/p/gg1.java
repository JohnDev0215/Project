package p;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class gg1 extends vg1 {
    public final /* synthetic */ int e;
    public final /* synthetic */ b52 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ gg1(String str, b52 b52Var, int i) {
        super(str);
        this.e = i;
        this.f = b52Var;
    }

    @Override // p.vg1
    public final void c() {
        switch (this.e) {
            case 2:
                this.f.b(-1, null);
                return;
            default:
                super.c();
                throw null;
        }
    }

    @Override // p.vg1
    public final void d(Bundle bundle) {
        switch (this.e) {
            case 2:
                this.f.b(1, bundle);
                return;
            default:
                super.d(bundle);
                throw null;
        }
    }

    @Override // p.vg1
    public final void e(Object obj) {
        int i = this.e;
        b52 b52Var = this.f;
        switch (i) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putParcelable("media_item", (MediaBrowserCompat$MediaItem) obj);
                b52Var.b(0, bundle);
                break;
            case 1:
                List list = (List) obj;
                if (list != null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat$MediaItem[0]));
                    b52Var.b(0, bundle2);
                } else {
                    b52Var.b(-1, null);
                }
                break;
            default:
                b52Var.b(0, (Bundle) obj);
                break;
        }
    }
}
