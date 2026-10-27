package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.lifecycle.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityResultRegistry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Random f330a = new Random();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Integer, String> f331b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, Integer> f332c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<String, d> f333d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ArrayList<String> f334e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final transient Map<String, c<?>> f335f = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final Map<String, Object> f336g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Bundle f337h = new Bundle();

    /* JADX INFO: Add missing generic type declarations: [I] */
    class a<I> extends androidx.activity.result.b<I> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f342a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f343b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d.a f344c;

        a(String str, int i7, d.a aVar) {
            this.f342a = str;
            this.f343b = i7;
            this.f344c = aVar;
        }

        @Override // androidx.activity.result.b
        public void b(I i7, w.c cVar) {
            ActivityResultRegistry.this.f334e.add(this.f342a);
            ActivityResultRegistry.this.f(this.f343b, this.f344c, i7, cVar);
        }

        @Override // androidx.activity.result.b
        public void c() {
            ActivityResultRegistry.this.l(this.f342a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    class b<I> extends androidx.activity.result.b<I> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f346a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f347b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d.a f348c;

        b(String str, int i7, d.a aVar) {
            this.f346a = str;
            this.f347b = i7;
            this.f348c = aVar;
        }

        @Override // androidx.activity.result.b
        public void b(I i7, w.c cVar) {
            ActivityResultRegistry.this.f334e.add(this.f346a);
            ActivityResultRegistry.this.f(this.f347b, this.f348c, i7, cVar);
        }

        @Override // androidx.activity.result.b
        public void c() {
            ActivityResultRegistry.this.l(this.f346a);
        }
    }

    private static class c<O> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final androidx.activity.result.a<O> f350a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final d.a<?, O> f351b;

        c(androidx.activity.result.a<O> aVar, d.a<?, O> aVar2) {
            this.f350a = aVar;
            this.f351b = aVar2;
        }
    }

    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final i f352a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ArrayList<m> f353b = new ArrayList<>();

        d(i iVar) {
            this.f352a = iVar;
        }

        void a(m mVar) {
            this.f352a.a(mVar);
            this.f353b.add(mVar);
        }

        void b() {
            Iterator<m> it = this.f353b.iterator();
            while (it.hasNext()) {
                this.f352a.c(it.next());
            }
            this.f353b.clear();
        }
    }

    private void a(int i7, String str) {
        this.f331b.put(Integer.valueOf(i7), str);
        this.f332c.put(str, Integer.valueOf(i7));
    }

    private <O> void d(String str, int i7, Intent intent, c<O> cVar) {
        androidx.activity.result.a<O> aVar;
        if (cVar != null && (aVar = cVar.f350a) != null) {
            aVar.a(cVar.f351b.c(i7, intent));
        } else {
            this.f336g.remove(str);
            this.f337h.putParcelable(str, new ActivityResult(i7, intent));
        }
    }

    private int e() {
        int iNextInt = this.f330a.nextInt(2147418112);
        while (true) {
            int i7 = iNextInt + 65536;
            if (!this.f331b.containsKey(Integer.valueOf(i7))) {
                return i7;
            }
            iNextInt = this.f330a.nextInt(2147418112);
        }
    }

    private int k(String str) {
        Integer num = this.f332c.get(str);
        if (num != null) {
            return num.intValue();
        }
        int iE = e();
        a(iE, str);
        return iE;
    }

    public final boolean b(int i7, int i8, Intent intent) {
        String str = this.f331b.get(Integer.valueOf(i7));
        if (str == null) {
            return false;
        }
        this.f334e.remove(str);
        d(str, i8, intent, this.f335f.get(str));
        return true;
    }

    public final <O> boolean c(int i7, @SuppressLint({"UnknownNullness"}) O o7) {
        androidx.activity.result.a<?> aVar;
        String str = this.f331b.get(Integer.valueOf(i7));
        if (str == null) {
            return false;
        }
        this.f334e.remove(str);
        c<?> cVar = this.f335f.get(str);
        if (cVar != null && (aVar = cVar.f350a) != null) {
            aVar.a(o7);
            return true;
        }
        this.f337h.remove(str);
        this.f336g.put(str, o7);
        return true;
    }

    public abstract <I, O> void f(int i7, d.a<I, O> aVar, @SuppressLint({"UnknownNullness"}) I i8, w.c cVar);

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
        for (int i7 = 0; i7 < size; i7++) {
            a(integerArrayList.get(i7).intValue(), stringArrayList.get(i7));
        }
        this.f334e = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        this.f330a = (Random) bundle.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
        this.f337h.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
    }

    public final void h(Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.f331b.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.f331b.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.f334e));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.f337h.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", this.f330a);
    }

    public final <I, O> androidx.activity.result.b<I> i(final String str, o oVar, final d.a<I, O> aVar, final androidx.activity.result.a<O> aVar2) {
        i iVarA = oVar.a();
        if (iVarA.b().isAtLeast(i.c.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + oVar + " is attempting to register while current state is " + iVarA.b() + ". LifecycleOwners must call register before they are STARTED.");
        }
        int iK = k(str);
        d dVar = this.f333d.get(str);
        if (dVar == null) {
            dVar = new d(iVarA);
        }
        dVar.a(new m() { // from class: androidx.activity.result.ActivityResultRegistry.1
            @Override // androidx.lifecycle.m
            public void d(o oVar2, i.b bVar) {
                if (!i.b.ON_START.equals(bVar)) {
                    if (i.b.ON_STOP.equals(bVar)) {
                        ActivityResultRegistry.this.f335f.remove(str);
                        return;
                    } else {
                        if (i.b.ON_DESTROY.equals(bVar)) {
                            ActivityResultRegistry.this.l(str);
                            return;
                        }
                        return;
                    }
                }
                ActivityResultRegistry.this.f335f.put(str, new c<>(aVar2, aVar));
                if (ActivityResultRegistry.this.f336g.containsKey(str)) {
                    Object obj = ActivityResultRegistry.this.f336g.get(str);
                    ActivityResultRegistry.this.f336g.remove(str);
                    aVar2.a(obj);
                }
                ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.f337h.getParcelable(str);
                if (activityResult != null) {
                    ActivityResultRegistry.this.f337h.remove(str);
                    aVar2.a(aVar.c(activityResult.c(), activityResult.b()));
                }
            }
        });
        this.f333d.put(str, dVar);
        return new a(str, iK, aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <I, O> androidx.activity.result.b<I> j(String str, d.a<I, O> aVar, androidx.activity.result.a<O> aVar2) {
        int iK = k(str);
        this.f335f.put(str, new c<>(aVar2, aVar));
        if (this.f336g.containsKey(str)) {
            Object obj = this.f336g.get(str);
            this.f336g.remove(str);
            aVar2.a(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.f337h.getParcelable(str);
        if (activityResult != null) {
            this.f337h.remove(str);
            aVar2.a(aVar.c(activityResult.c(), activityResult.b()));
        }
        return new b(str, iK, aVar);
    }

    final void l(String str) {
        Integer numRemove;
        if (!this.f334e.contains(str) && (numRemove = this.f332c.remove(str)) != null) {
            this.f331b.remove(numRemove);
        }
        this.f335f.remove(str);
        if (this.f336g.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.f336g.get(str));
            this.f336g.remove(str);
        }
        if (this.f337h.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.f337h.getParcelable(str));
            this.f337h.remove(str);
        }
        d dVar = this.f333d.get(str);
        if (dVar != null) {
            dVar.b();
            this.f333d.remove(str);
        }
    }
}
