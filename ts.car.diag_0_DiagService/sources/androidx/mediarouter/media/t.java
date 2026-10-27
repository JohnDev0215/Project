package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final t f996c = new t(new Bundle(), null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Bundle f997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<String> f998b;

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ArrayList<String> f999a;

        public a() {
        }

        @NonNull
        public a a(@NonNull Collection<String> collection) {
            if (collection == null) {
                throw new IllegalArgumentException("categories must not be null");
            }
            if (!collection.isEmpty()) {
                Iterator<String> it = collection.iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
            }
            return this;
        }

        @NonNull
        public a b(@NonNull String str) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            if (this.f999a == null) {
                this.f999a = new ArrayList<>();
            }
            if (!this.f999a.contains(str)) {
                this.f999a.add(str);
            }
            return this;
        }

        @NonNull
        public a c(@NonNull t tVar) {
            if (tVar == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            a(tVar.e());
            return this;
        }

        @NonNull
        public t d() {
            if (this.f999a == null) {
                return t.f996c;
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("controlCategories", this.f999a);
            return new t(bundle, this.f999a);
        }

        public a(@NonNull t tVar) {
            if (tVar == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            tVar.c();
            if (tVar.f998b.isEmpty()) {
                return;
            }
            this.f999a = new ArrayList<>(tVar.f998b);
        }
    }

    t(Bundle bundle, List<String> list) {
        this.f997a = bundle;
        this.f998b = list;
    }

    public static t d(@Nullable Bundle bundle) {
        if (bundle != null) {
            return new t(bundle, null);
        }
        return null;
    }

    public Bundle a() {
        return this.f997a;
    }

    public boolean b(t tVar) {
        if (tVar == null) {
            return false;
        }
        c();
        tVar.c();
        return this.f998b.containsAll(tVar.f998b);
    }

    void c() {
        if (this.f998b == null) {
            ArrayList<String> stringArrayList = this.f997a.getStringArrayList("controlCategories");
            this.f998b = stringArrayList;
            if (stringArrayList == null || stringArrayList.isEmpty()) {
                this.f998b = Collections.emptyList();
            }
        }
    }

    public List<String> e() {
        c();
        return this.f998b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        c();
        tVar.c();
        return this.f998b.equals(tVar.f998b);
    }

    public boolean f() {
        c();
        return this.f998b.isEmpty();
    }

    public boolean g() {
        c();
        return !this.f998b.contains(null);
    }

    public boolean h(List<IntentFilter> list) {
        if (list != null) {
            c();
            int size = this.f998b.size();
            if (size != 0) {
                int size2 = list.size();
                for (int i = 0; i < size2; i++) {
                    IntentFilter intentFilter = list.get(i);
                    if (intentFilter != null) {
                        for (int i2 = 0; i2 < size; i2++) {
                            if (intentFilter.hasCategory(this.f998b.get(i2))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        c();
        return this.f998b.hashCode();
    }

    public String toString() {
        return "MediaRouteSelector{ controlCategories=" + Arrays.toString(e().toArray()) + " }";
    }
}
