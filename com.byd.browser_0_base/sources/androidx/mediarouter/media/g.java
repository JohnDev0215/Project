package androidx.mediarouter.media;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MediaRouteSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final g c = new g(null, new Bundle());
    public final Bundle a;
    public List<String> b;

    /* JADX INFO: compiled from: MediaRouteSelector.java */
    public static final class a {
        public ArrayList<String> a;

        public a() {
        }

        public final void a(ArrayList arrayList) {
            if (arrayList.isEmpty()) {
                return;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str == null) {
                    throw new IllegalArgumentException("category must not be null");
                }
                if (this.a == null) {
                    this.a = new ArrayList<>();
                }
                if (!this.a.contains(str)) {
                    this.a.add(str);
                }
            }
        }

        public final void b(g gVar) {
            if (gVar == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            a(gVar.b());
        }

        public final g c() {
            if (this.a == null) {
                return g.c;
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("controlCategories", this.a);
            return new g(this.a, bundle);
        }

        public a(g gVar) {
            if (gVar == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            gVar.a();
            if (gVar.b.isEmpty()) {
                return;
            }
            this.a = new ArrayList<>(gVar.b);
        }
    }

    public g(ArrayList arrayList, Bundle bundle) {
        this.a = bundle;
        this.b = arrayList;
    }

    public final void a() {
        if (this.b == null) {
            ArrayList<String> stringArrayList = this.a.getStringArrayList("controlCategories");
            this.b = stringArrayList;
            if (stringArrayList == null || stringArrayList.isEmpty()) {
                this.b = Collections.emptyList();
            }
        }
    }

    public final ArrayList b() {
        a();
        return new ArrayList(this.b);
    }

    public final boolean c() {
        a();
        return this.b.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        a();
        gVar.a();
        return this.b.equals(gVar.b);
    }

    public final int hashCode() {
        a();
        return this.b.hashCode();
    }

    public final String toString() {
        return "MediaRouteSelector{ controlCategories=" + Arrays.toString(b().toArray()) + " }";
    }
}
