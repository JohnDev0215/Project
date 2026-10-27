package p;

import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class qg1 extends vg1 {
    public final /* synthetic */ wg1 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qg1(sg1 sg1Var, Object obj, wg1 wg1Var, Bundle bundle) {
        super(obj);
        this.e = wg1Var;
    }

    @Override // p.vg1
    public final void a() {
        ((MediaBrowserService.Result) this.e.a).detach();
    }

    @Override // p.vg1
    public final void e(Object obj) {
        List<MediaBrowserCompat$MediaItem> list = (List) obj;
        wg1 wg1Var = this.e;
        if (list == null) {
            wg1Var.h(null);
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem : list) {
            Parcel parcelObtain = Parcel.obtain();
            mediaBrowserCompat$MediaItem.writeToParcel(parcelObtain, 0);
            arrayList.add(parcelObtain);
        }
        wg1Var.h(arrayList);
    }
}
