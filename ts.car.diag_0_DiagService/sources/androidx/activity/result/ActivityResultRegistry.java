package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityResultRegistry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicInteger f83a = new AtomicInteger(65535);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Integer, String> f84b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, Integer> f85c = new HashMap();
    private final transient Map<String, c<?>> d = new HashMap();
    private final Bundle e = new Bundle();

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* JADX INFO: compiled from: caradapter-bug */
    class a<I> extends androidx.activity.result.b<I> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f88a;

        a(int i, androidx.activity.result.d.a aVar, String str) {
            this.f88a = str;
        }

        @Override // androidx.activity.result.b
        public void a() {
            ActivityResultRegistry.this.i(this.f88a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* JADX INFO: compiled from: caradapter-bug */
    class b<I> extends androidx.activity.result.b<I> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f90a;

        b(int i, androidx.activity.result.d.a aVar, String str) {
            this.f90a = str;
        }

        @Override // androidx.activity.result.b
        public void a() {
            ActivityResultRegistry.this.i(this.f90a);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class c<O> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final androidx.activity.result.a<O> f92a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.activity.result.d.a<?, O> f93b;

        c(androidx.activity.result.a<O> aVar, androidx.activity.result.d.a<?, O> aVar2) {
            this.f92a = aVar;
            this.f93b = aVar2;
        }
    }

    private void a(int i, String str) {
        this.f84b.put(Integer.valueOf(i), str);
        this.f85c.put(str, Integer.valueOf(i));
    }

    private <O> void c(String str, int i, @Nullable Intent intent, @Nullable c<O> cVar) {
        androidx.activity.result.a<O> aVar;
        if (cVar == null || (aVar = cVar.f92a) == null) {
            this.e.putParcelable(str, new ActivityResult(i, intent));
        } else {
            aVar.a(cVar.f93b.a(i, intent));
        }
    }

    private int h(String str) {
        Integer num = this.f85c.get(str);
        if (num != null) {
            return num.intValue();
        }
        int andIncrement = this.f83a.getAndIncrement();
        a(andIncrement, str);
        return andIncrement;
    }

    @MainThread
    public final boolean b(int i, int i2, @Nullable Intent intent) {
        String str = this.f84b.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        c(str, i2, intent, this.d.get(str));
        return true;
    }

    public final void d(@Nullable Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        int size = stringArrayList.size();
        for (int i = 0; i < size; i++) {
            a(integerArrayList.get(i).intValue(), stringArrayList.get(i));
        }
        this.f83a.set(size);
        this.e.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
    }

    public final void e(@NonNull Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.f84b.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.f84b.values()));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", this.e);
    }

    @NonNull
    public final <I, O> androidx.activity.result.b<I> f(@NonNull String str, @NonNull androidx.activity.result.d.a<I, O> aVar, @NonNull androidx.activity.result.a<O> aVar2) {
        int iH = h(str);
        this.d.put(str, new c<>(aVar2, aVar));
        ActivityResult activityResult = (ActivityResult) this.e.getParcelable(str);
        if (activityResult != null) {
            this.e.remove(str);
            aVar2.a(aVar.a(activityResult.b(), activityResult.a()));
        }
        return new b(iH, aVar, str);
    }

    @NonNull
    public final <I, O> androidx.activity.result.b<I> g(@NonNull final String str, @NonNull f fVar, @NonNull final androidx.activity.result.d.a<I, O> aVar, @NonNull final androidx.activity.result.a<O> aVar2) {
        int iH = h(str);
        this.d.put(str, new c<>(aVar2, aVar));
        Lifecycle lifecycleA = fVar.a();
        final ActivityResult activityResult = (ActivityResult) this.e.getParcelable(str);
        if (activityResult != null) {
            this.e.remove(str);
            if (lifecycleA.b().isAtLeast(Lifecycle.State.STARTED)) {
                aVar2.a(aVar.a(activityResult.b(), activityResult.a()));
            } else {
                lifecycleA.a(new d(this) { // from class: androidx.activity.result.ActivityResultRegistry.1
                    @Override // androidx.lifecycle.d
                    public void f(@NonNull f fVar2, @NonNull Lifecycle.Event event) {
                        if (Lifecycle.Event.ON_START.equals(event)) {
                            aVar2.a(aVar.a(activityResult.b(), activityResult.a()));
                        }
                    }
                });
            }
        }
        lifecycleA.a(new d() { // from class: androidx.activity.result.ActivityResultRegistry.2
            @Override // androidx.lifecycle.d
            public void f(@NonNull f fVar2, @NonNull Lifecycle.Event event) {
                if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                    ActivityResultRegistry.this.i(str);
                }
            }
        });
        return new a(iH, aVar, str);
    }

    @MainThread
    final void i(@NonNull String str) {
        Integer numRemove = this.f85c.remove(str);
        if (numRemove != null) {
            this.f84b.remove(numRemove);
        }
        this.d.remove(str);
        if (this.e.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.e.getParcelable(str));
            this.e.remove(str);
        }
    }
}
