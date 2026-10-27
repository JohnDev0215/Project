package p;

import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class lg1 extends vg1 {
    public final /* synthetic */ int e;
    public final /* synthetic */ wg1 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ lg1(Object obj, wg1 wg1Var, int i) {
        super(obj);
        this.e = i;
        this.f = wg1Var;
    }

    @Override // p.vg1
    public final void a() {
        int i = this.e;
        wg1 wg1Var = this.f;
        switch (i) {
            case 0:
                ((MediaBrowserService.Result) wg1Var.a).detach();
                break;
            default:
                ((MediaBrowserService.Result) wg1Var.a).detach();
                break;
        }
    }

    @Override // p.vg1
    public final void e(Object obj) {
        int i = this.e;
        ArrayList arrayList = null;
        wg1 wg1Var = this.f;
        switch (i) {
            case 0:
                List<MediaBrowserCompat$MediaItem> list = (List) obj;
                if (list != null) {
                    arrayList = new ArrayList(list.size());
                    for (MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem : list) {
                        Parcel parcelObtain = Parcel.obtain();
                        mediaBrowserCompat$MediaItem.writeToParcel(parcelObtain, 0);
                        arrayList.add(parcelObtain);
                    }
                }
                wg1Var.h(arrayList);
                break;
            default:
                MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem2 = (MediaBrowserCompat$MediaItem) obj;
                if (mediaBrowserCompat$MediaItem2 == null) {
                    wg1Var.h(null);
                } else {
                    Parcel parcelObtain2 = Parcel.obtain();
                    mediaBrowserCompat$MediaItem2.writeToParcel(parcelObtain2, 0);
                    wg1Var.h(parcelObtain2);
                }
                break;
        }
    }
}
