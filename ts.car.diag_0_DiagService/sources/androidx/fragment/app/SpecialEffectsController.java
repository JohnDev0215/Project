package androidx.fragment.app;

import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.b;
import androidx.fragment.R$id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
abstract class SpecialEffectsController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewGroup f646a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<Operation> f647b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final HashMap<Fragment, Operation> f648c = new HashMap<>();
    boolean d = false;
    boolean e = false;

    /* JADX INFO: compiled from: caradapter-bug */
    static class Operation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @NonNull
        private final Type f649a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @NonNull
        private final Fragment f650b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @NonNull
        private final androidx.core.os.b f651c;

        @NonNull
        private final List<Runnable> d = new ArrayList();

        /* JADX INFO: compiled from: caradapter-bug */
        enum Type {
            ADD,
            REMOVE,
            SHOW,
            HIDE
        }

        Operation(@NonNull Type type, @NonNull Fragment fragment, @NonNull androidx.core.os.b bVar) {
            this.f649a = type;
            this.f650b = fragment;
            this.f651c = bVar;
        }

        final void a(@NonNull Runnable runnable) {
            this.d.add(runnable);
        }

        @CallSuper
        public void b() {
            Iterator<Runnable> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }

        @NonNull
        public final androidx.core.os.b c() {
            return this.f651c;
        }

        @NonNull
        public final Fragment d() {
            return this.f650b;
        }

        @NonNull
        public final Type e() {
            return this.f649a;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c f652a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ androidx.core.os.b f653b;

        a(c cVar, androidx.core.os.b bVar) {
            this.f652a = cVar;
            this.f653b = bVar;
        }

        @Override // androidx.core.os.b.a
        public void a() {
            synchronized (SpecialEffectsController.this.f647b) {
                SpecialEffectsController.this.f647b.remove(this.f652a);
                SpecialEffectsController.this.f648c.remove(this.f652a.d());
                this.f653b.a();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c f655c;

        b(c cVar) {
            this.f655c = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            SpecialEffectsController.this.f648c.remove(this.f655c.d());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class c extends Operation {

        @NonNull
        private final p e;

        c(@NonNull Operation.Type type, @NonNull p pVar, @NonNull androidx.core.os.b bVar) {
            super(type, pVar.j(), bVar);
            this.e = pVar;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void b() {
            super.b();
            this.e.k();
        }
    }

    SpecialEffectsController(@NonNull ViewGroup viewGroup) {
        this.f646a = viewGroup;
    }

    private void b(@NonNull Operation.Type type, @NonNull p pVar, @NonNull androidx.core.os.b bVar) {
        if (bVar.b()) {
            return;
        }
        synchronized (this.f647b) {
            androidx.core.os.b bVar2 = new androidx.core.os.b();
            c cVar = new c(type, pVar, bVar2);
            this.f647b.add(cVar);
            this.f648c.put(cVar.d(), cVar);
            bVar.c(new a(cVar, bVar2));
            cVar.a(new b(cVar));
        }
    }

    @NonNull
    static SpecialEffectsController l(@NonNull ViewGroup viewGroup, @NonNull FragmentManager fragmentManager) {
        return m(viewGroup, fragmentManager.x0());
    }

    @NonNull
    static SpecialEffectsController m(@NonNull ViewGroup viewGroup, @NonNull x xVar) {
        Object tag = viewGroup.getTag(R$id.special_effects_controller_view_tag);
        if (tag instanceof SpecialEffectsController) {
            return (SpecialEffectsController) tag;
        }
        SpecialEffectsController specialEffectsControllerA = xVar.a(viewGroup);
        viewGroup.setTag(R$id.special_effects_controller_view_tag, specialEffectsControllerA);
        return specialEffectsControllerA;
    }

    void a() {
        synchronized (this.f647b) {
            Iterator<Operation> it = this.f648c.values().iterator();
            while (it.hasNext()) {
                it.next().c().a();
            }
            this.f648c.clear();
            this.f647b.clear();
        }
    }

    void c(@NonNull p pVar, @NonNull androidx.core.os.b bVar) {
        b(Operation.Type.ADD, pVar, bVar);
    }

    void d(@NonNull p pVar, @NonNull androidx.core.os.b bVar) {
        b(Operation.Type.HIDE, pVar, bVar);
    }

    void e(@NonNull p pVar, @NonNull androidx.core.os.b bVar) {
        b(Operation.Type.REMOVE, pVar, bVar);
    }

    void f(@NonNull p pVar, @NonNull androidx.core.os.b bVar) {
        b(Operation.Type.SHOW, pVar, bVar);
    }

    abstract void g(@NonNull List<Operation> list, boolean z);

    void h() {
        if (this.e) {
            return;
        }
        synchronized (this.f647b) {
            g(new ArrayList(this.f647b), this.d);
            this.f647b.clear();
            this.d = false;
        }
    }

    void i() {
        if (this.e) {
            this.e = false;
            h();
        }
    }

    @Nullable
    Operation.Type j(@NonNull p pVar) {
        Operation operation = this.f648c.get(pVar.j());
        if (operation != null) {
            return operation.e();
        }
        return null;
    }

    @NonNull
    public ViewGroup k() {
        return this.f646a;
    }

    void n() {
        synchronized (this.f647b) {
            this.e = false;
            for (int size = this.f647b.size() - 1; size >= 0; size--) {
                Operation operation = this.f647b.get(size);
                if (operation.e() != Operation.Type.ADD && operation.e() != Operation.Type.SHOW) {
                }
                this.e = operation.d().S();
                break;
            }
        }
    }

    void o(boolean z) {
        this.d = z;
    }
}
