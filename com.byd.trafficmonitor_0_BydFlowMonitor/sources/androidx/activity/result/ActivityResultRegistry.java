package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import b.n.e;
import b.n.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityResultRegistry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Random f212a = new Random();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<Integer, String> f213b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<String, Integer> f214c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map<String, d> f215d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<String> f216e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final transient Map<String, c<?>> f217f = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Map<String, Object> f218g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Bundle f219h = new Bundle();

    /* JADX INFO: Add missing generic type declarations: [I] */
    public class a<I> extends b.a.e.b<I> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f224a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f225b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.a.e.d.a f226c;

        public a(String str, int i2, b.a.e.d.a aVar) {
            this.f224a = str;
            this.f225b = i2;
            this.f226c = aVar;
        }

        @Override // b.a.e.b
        public void b(I i2, b.h.d.b bVar) {
            ActivityResultRegistry.this.f216e.add(this.f224a);
            ActivityResultRegistry.this.f(this.f225b, this.f226c, i2, bVar);
        }

        @Override // b.a.e.b
        public void c() {
            ActivityResultRegistry.this.l(this.f224a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    public class b<I> extends b.a.e.b<I> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f228a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f229b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.a.e.d.a f230c;

        public b(String str, int i2, b.a.e.d.a aVar) {
            this.f228a = str;
            this.f229b = i2;
            this.f230c = aVar;
        }

        @Override // b.a.e.b
        public void b(I i2, b.h.d.b bVar) {
            ActivityResultRegistry.this.f216e.add(this.f228a);
            ActivityResultRegistry.this.f(this.f229b, this.f230c, i2, bVar);
        }

        @Override // b.a.e.b
        public void c() {
            ActivityResultRegistry.this.l(this.f228a);
        }
    }

    public static class c<O> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.a.e.a<O> f232a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b.a.e.d.a<?, O> f233b;

        public c(b.a.e.a<O> aVar, b.a.e.d.a<?, O> aVar2) {
            this.f232a = aVar;
            this.f233b = aVar2;
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Lifecycle f234a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList<e> f235b = new ArrayList<>();

        public d(Lifecycle lifecycle) {
            this.f234a = lifecycle;
        }

        public void a(e eVar) {
            this.f234a.a(eVar);
            this.f235b.add(eVar);
        }

        public void b() {
            Iterator<e> it = this.f235b.iterator();
            while (it.hasNext()) {
                this.f234a.c(it.next());
            }
            this.f235b.clear();
        }
    }

    public final void a(int i2, String str) {
        this.f213b.put(Integer.valueOf(i2), str);
        this.f214c.put(str, Integer.valueOf(i2));
    }

    public final boolean b(int i2, int i3, Intent intent) {
        String str = this.f213b.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        this.f216e.remove(str);
        d(str, i3, intent, this.f217f.get(str));
        return true;
    }

    public final <O> boolean c(int i2, @SuppressLint({"UnknownNullness"}) O o) {
        b.a.e.a<?> aVar;
        String str = this.f213b.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        this.f216e.remove(str);
        c<?> cVar = this.f217f.get(str);
        if (cVar != null && (aVar = cVar.f232a) != null) {
            aVar.a(o);
            return true;
        }
        this.f219h.remove(str);
        this.f218g.put(str, o);
        return true;
    }

    public final <O> void d(String str, int i2, Intent intent, c<O> cVar) {
        b.a.e.a<O> aVar;
        if (cVar != null && (aVar = cVar.f232a) != null) {
            aVar.a(cVar.f233b.c(i2, intent));
        } else {
            this.f218g.remove(str);
            this.f219h.putParcelable(str, new ActivityResult(i2, intent));
        }
    }

    public final int e() {
        int iNextInt = this.f212a.nextInt(2147418112);
        while (true) {
            int i2 = iNextInt + 65536;
            if (!this.f213b.containsKey(Integer.valueOf(i2))) {
                return i2;
            }
            iNextInt = this.f212a.nextInt(2147418112);
        }
    }

    public abstract <I, O> void f(int i2, b.a.e.d.a<I, O> aVar, @SuppressLint({"UnknownNullness"}) I i3, b.h.d.b bVar);

    public final void g(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        int size = stringArrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(integerArrayList.get(i2).intValue(), stringArrayList.get(i2));
        }
        this.f216e = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        this.f212a = (Random) bundle.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
        this.f219h.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
    }

    public final void h(Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.f213b.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.f213b.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.f216e));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.f219h.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", this.f212a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <I, O> b.a.e.b<I> i(String str, b.a.e.d.a<I, O> aVar, b.a.e.a<O> aVar2) {
        int iK = k(str);
        this.f217f.put(str, new c<>(aVar2, aVar));
        if (this.f218g.containsKey(str)) {
            Object obj = this.f218g.get(str);
            this.f218g.remove(str);
            aVar2.a(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.f219h.getParcelable(str);
        if (activityResult != null) {
            this.f219h.remove(str);
            aVar2.a(aVar.c(activityResult.k(), activityResult.j()));
        }
        return new b(str, iK, aVar);
    }

    public final <I, O> b.a.e.b<I> j(final String str, g gVar, final b.a.e.d.a<I, O> aVar, final b.a.e.a<O> aVar2) {
        Lifecycle lifecycle = gVar.getLifecycle();
        if (lifecycle.b().isAtLeast(Lifecycle.State.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + gVar + " is attempting to register while current state is " + lifecycle.b() + ". LifecycleOwners must call register before they are STARTED.");
        }
        int iK = k(str);
        d dVar = this.f215d.get(str);
        if (dVar == null) {
            dVar = new d(lifecycle);
        }
        dVar.a(new e() { // from class: androidx.activity.result.ActivityResultRegistry.1
            @Override // b.n.e
            public void d(g gVar2, Lifecycle.Event event) {
                if (!Lifecycle.Event.ON_START.equals(event)) {
                    if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ActivityResultRegistry.this.f217f.remove(str);
                        return;
                    } else {
                        if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                            ActivityResultRegistry.this.l(str);
                            return;
                        }
                        return;
                    }
                }
                ActivityResultRegistry.this.f217f.put(str, new c<>(aVar2, aVar));
                if (ActivityResultRegistry.this.f218g.containsKey(str)) {
                    Object obj = ActivityResultRegistry.this.f218g.get(str);
                    ActivityResultRegistry.this.f218g.remove(str);
                    aVar2.a(obj);
                }
                ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.f219h.getParcelable(str);
                if (activityResult != null) {
                    ActivityResultRegistry.this.f219h.remove(str);
                    aVar2.a(aVar.c(activityResult.k(), activityResult.j()));
                }
            }
        });
        this.f215d.put(str, dVar);
        return new a(str, iK, aVar);
    }

    public final int k(String str) {
        Integer num = this.f214c.get(str);
        if (num != null) {
            return num.intValue();
        }
        int iE = e();
        a(iE, str);
        return iE;
    }

    public final void l(String str) {
        Integer numRemove;
        if (!this.f216e.contains(str) && (numRemove = this.f214c.remove(str)) != null) {
            this.f213b.remove(numRemove);
        }
        this.f217f.remove(str);
        if (this.f218g.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.f218g.get(str));
            this.f218g.remove(str);
        }
        if (this.f219h.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.f219h.getParcelable(str));
            this.f219h.remove(str);
        }
        d dVar = this.f215d.get(str);
        if (dVar != null) {
            dVar.b();
            this.f215d.remove(str);
        }
    }
}
