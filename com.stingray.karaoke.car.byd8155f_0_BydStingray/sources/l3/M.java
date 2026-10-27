package l3;

import android.media.session.MediaSession;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class M implements Parcelable {
    public static final Parcelable.Creator<M> CREATOR = new android.support.v4.media.a(17);
    public final C1740E l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f18532m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public MediaSession.QueueItem f18533n;

    public M(MediaSession.QueueItem queueItem, C1740E c1740e, long j3) {
        if (c1740e == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (j3 == -1) {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
        this.l = c1740e;
        this.f18532m = j3;
        this.f18533n = queueItem;
    }

    public static ArrayList a(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MediaSession.QueueItem queueItem = (MediaSession.QueueItem) it.next();
            arrayList.add(new M(queueItem, C1740E.a(queueItem.getDescription()), queueItem.getQueueId()));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MediaSession.QueueItem {Description=");
        sb.append(this.l);
        sb.append(", Id=");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f18532m, " }", sb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        this.l.writeToParcel(parcel, i7);
        parcel.writeLong(this.f18532m);
    }

    public M(Parcel parcel) {
        this.l = C1740E.CREATOR.createFromParcel(parcel);
        this.f18532m = parcel.readLong();
    }
}
