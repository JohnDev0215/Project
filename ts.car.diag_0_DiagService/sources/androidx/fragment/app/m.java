package androidx.fragment.app;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.o;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
final class m extends androidx.lifecycle.n {
    private static final o.a i = new a();
    private final boolean e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashMap<String, Fragment> f701b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final HashMap<String, m> f702c = new HashMap<>();
    private final HashMap<String, androidx.lifecycle.p> d = new HashMap<>();
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements o.a {
        a() {
        }

        @Override // androidx.lifecycle.o.a
        @NonNull
        public <T extends androidx.lifecycle.n> T a(@NonNull Class<T> cls) {
            return new m(true);
        }
    }

    m(boolean z) {
        this.e = z;
    }

    @NonNull
    static m i(androidx.lifecycle.p pVar) {
        return (m) new androidx.lifecycle.o(pVar, i).a(m.class);
    }

    @Override // androidx.lifecycle.n
    protected void d() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f = true;
    }

    void e(@NonNull Fragment fragment) {
        if (this.h) {
            if (FragmentManager.D0(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else {
            if (this.f701b.containsKey(fragment.h)) {
                return;
            }
            this.f701b.put(fragment.h, fragment);
            if (FragmentManager.D0(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m.class != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        return this.f701b.equals(mVar.f701b) && this.f702c.equals(mVar.f702c) && this.d.equals(mVar.d);
    }

    void f(@NonNull Fragment fragment) {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        m mVar = this.f702c.get(fragment.h);
        if (mVar != null) {
            mVar.d();
            this.f702c.remove(fragment.h);
        }
        androidx.lifecycle.p pVar = this.d.get(fragment.h);
        if (pVar != null) {
            pVar.a();
            this.d.remove(fragment.h);
        }
    }

    @Nullable
    Fragment g(String str) {
        return this.f701b.get(str);
    }

    @NonNull
    m h(@NonNull Fragment fragment) {
        m mVar = this.f702c.get(fragment.h);
        if (mVar != null) {
            return mVar;
        }
        m mVar2 = new m(this.e);
        this.f702c.put(fragment.h, mVar2);
        return mVar2;
    }

    public int hashCode() {
        return (((this.f701b.hashCode() * 31) + this.f702c.hashCode()) * 31) + this.d.hashCode();
    }

    @NonNull
    Collection<Fragment> j() {
        return new ArrayList(this.f701b.values());
    }

    @NonNull
    androidx.lifecycle.p k(@NonNull Fragment fragment) {
        androidx.lifecycle.p pVar = this.d.get(fragment.h);
        if (pVar != null) {
            return pVar;
        }
        androidx.lifecycle.p pVar2 = new androidx.lifecycle.p();
        this.d.put(fragment.h, pVar2);
        return pVar2;
    }

    boolean l() {
        return this.f;
    }

    void m(@NonNull Fragment fragment) {
        if (this.h) {
            if (FragmentManager.D0(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
                return;
            }
            return;
        }
        if ((this.f701b.remove(fragment.h) != null) && FragmentManager.D0(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    void n(boolean z) {
        this.h = z;
    }

    boolean o(@NonNull Fragment fragment) {
        if (this.f701b.containsKey(fragment.h)) {
            return this.e ? this.f : !this.g;
        }
        return true;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f701b.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f702c.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.d.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
