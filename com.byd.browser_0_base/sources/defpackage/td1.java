package defpackage;

import android.os.Bundle;
import androidx.mediarouter.media.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: MediaRouteProviderDescriptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class td1 {
    public final List<d> a;
    public final boolean b;

    public td1(List<d> list, boolean z) {
        if (list.isEmpty()) {
            this.a = Collections.emptyList();
        } else {
            this.a = Collections.unmodifiableList(new ArrayList(list));
        }
        this.b = z;
    }

    public static td1 a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("routes");
        if (parcelableArrayList != null) {
            for (int i = 0; i < parcelableArrayList.size(); i++) {
                Bundle bundle2 = (Bundle) parcelableArrayList.get(i);
                arrayList.add(bundle2 != null ? new d(bundle2) : null);
            }
        }
        return new td1(arrayList, bundle.getBoolean("supportsDynamicGroupRoute", false));
    }

    public final boolean b() {
        List<d> list = this.a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            d dVar = list.get(i);
            if (dVar == null || !dVar.i()) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        return "MediaRouteProviderDescriptor{ routes=" + Arrays.toString(this.a.toArray()) + ", isValid=" + b() + " }";
    }
}
