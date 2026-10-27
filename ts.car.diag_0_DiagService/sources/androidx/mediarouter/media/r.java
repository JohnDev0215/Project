package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Bundle f991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final List<p> f992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f993c;

    r(List<p> list, boolean z) {
        this.f992b = list == null ? Collections.emptyList() : list;
        this.f993c = z;
    }

    public static r b(Bundle bundle) {
        ArrayList arrayList = null;
        if (bundle == null) {
            return null;
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("routes");
        if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
            int size = parcelableArrayList.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList2.add(p.e((Bundle) parcelableArrayList.get(i)));
            }
            arrayList = arrayList2;
        }
        return new r(arrayList, bundle.getBoolean("supportsDynamicGroupRoute", false));
    }

    public Bundle a() {
        Bundle bundle = this.f991a;
        if (bundle != null) {
            return bundle;
        }
        this.f991a = new Bundle();
        List<p> list = this.f992b;
        if (list != null && !list.isEmpty()) {
            int size = this.f992b.size();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(this.f992b.get(i).a());
            }
            this.f991a.putParcelableArrayList("routes", arrayList);
        }
        this.f991a.putBoolean("supportsDynamicGroupRoute", this.f993c);
        return this.f991a;
    }

    @NonNull
    public List<p> c() {
        return this.f992b;
    }

    public boolean d() {
        int size = c().size();
        for (int i = 0; i < size; i++) {
            p pVar = this.f992b.get(i);
            if (pVar == null || !pVar.y()) {
                return false;
            }
        }
        return true;
    }

    public boolean e() {
        return this.f993c;
    }

    public String toString() {
        return "MediaRouteProviderDescriptor{ routes=" + Arrays.toString(c().toArray()) + ", isValid=" + d() + " }";
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private List<p> f994a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f995b;

        public a() {
            this.f995b = false;
        }

        public a a(p pVar) {
            if (pVar == null) {
                throw new IllegalArgumentException("route must not be null");
            }
            List<p> list = this.f994a;
            if (list == null) {
                this.f994a = new ArrayList();
            } else if (list.contains(pVar)) {
                throw new IllegalArgumentException("route descriptor already added");
            }
            this.f994a.add(pVar);
            return this;
        }

        public a b(Collection<p> collection) {
            if (collection == null) {
                throw new IllegalArgumentException("routes must not be null");
            }
            if (!collection.isEmpty()) {
                Iterator<p> it = collection.iterator();
                while (it.hasNext()) {
                    a(it.next());
                }
            }
            return this;
        }

        public r c() {
            return new r(this.f994a, this.f995b);
        }

        a d(Collection<p> collection) {
            if (collection == null || collection.isEmpty()) {
                this.f994a = null;
            } else {
                this.f994a = new ArrayList(collection);
            }
            return this;
        }

        public a e(boolean z) {
            this.f995b = z;
            return this;
        }

        public a(r rVar) {
            this.f995b = false;
            if (rVar != null) {
                this.f994a = rVar.f992b;
                this.f995b = rVar.f993c;
                return;
            }
            throw new IllegalArgumentException("descriptor must not be null");
        }
    }
}
