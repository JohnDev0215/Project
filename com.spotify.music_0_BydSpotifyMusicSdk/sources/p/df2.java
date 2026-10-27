package p;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.SpecialEffectsController$Operation$LifecycleImpact;
import androidx.fragment.app.SpecialEffectsController$Operation$State;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class df2 {
    public final ViewGroup a;
    public final ArrayList b;
    public final ArrayList c;
    public boolean d;
    public boolean e;

    public df2(ViewGroup viewGroup) {
        v03.j(viewGroup, "container");
        this.a = viewGroup;
        this.b = new ArrayList();
        this.c = new ArrayList();
    }

    public static final df2 f(ViewGroup viewGroup, ox0 ox0Var) {
        v03.j(viewGroup, "container");
        v03.j(ox0Var, "fragmentManager");
        v03.i(ox0Var.D(), "fragmentManager.specialEffectsControllerFactory");
        Object tag = viewGroup.getTag(R.id.special_effects_controller_view_tag);
        if (tag instanceof df2) {
            return (df2) tag;
        }
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(viewGroup);
        viewGroup.setTag(R.id.special_effects_controller_view_tag, aVar);
        return aVar;
    }

    public final void a(SpecialEffectsController$Operation$State specialEffectsController$Operation$State, SpecialEffectsController$Operation$LifecycleImpact specialEffectsController$Operation$LifecycleImpact, androidx.fragment.app.b bVar) {
        synchronized (this.b) {
            cq cqVar = new cq();
            ww0 ww0Var = bVar.c;
            v03.i(ww0Var, "fragmentStateManager.fragment");
            bf2 bf2VarD = d(ww0Var);
            if (bf2VarD != null) {
                bf2VarD.c(specialEffectsController$Operation$State, specialEffectsController$Operation$LifecycleImpact);
                return;
            }
            final ze2 ze2Var = new ze2(specialEffectsController$Operation$State, specialEffectsController$Operation$LifecycleImpact, bVar, cqVar);
            this.b.add(ze2Var);
            final int i = 0;
            ze2Var.d.add(new Runnable(this) { // from class: p.ye2
                public final /* synthetic */ df2 e;

                {
                    this.e = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i2 = i;
                    ze2 ze2Var2 = ze2Var;
                    df2 df2Var = this.e;
                    switch (i2) {
                        case 0:
                            v03.j(df2Var, "this$0");
                            v03.j(ze2Var2, "$operation");
                            if (df2Var.b.contains(ze2Var2)) {
                                SpecialEffectsController$Operation$State specialEffectsController$Operation$State2 = ze2Var2.a;
                                View view = ze2Var2.c.M;
                                v03.i(view, "operation.fragment.mView");
                                specialEffectsController$Operation$State2.a(view);
                            }
                            break;
                        default:
                            v03.j(df2Var, "this$0");
                            v03.j(ze2Var2, "$operation");
                            df2Var.b.remove(ze2Var2);
                            df2Var.c.remove(ze2Var2);
                            break;
                    }
                }
            });
            final int i2 = 1;
            ze2Var.d.add(new Runnable(this) { // from class: p.ye2
                public final /* synthetic */ df2 e;

                {
                    this.e = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i22 = i2;
                    ze2 ze2Var2 = ze2Var;
                    df2 df2Var = this.e;
                    switch (i22) {
                        case 0:
                            v03.j(df2Var, "this$0");
                            v03.j(ze2Var2, "$operation");
                            if (df2Var.b.contains(ze2Var2)) {
                                SpecialEffectsController$Operation$State specialEffectsController$Operation$State2 = ze2Var2.a;
                                View view = ze2Var2.c.M;
                                v03.i(view, "operation.fragment.mView");
                                specialEffectsController$Operation$State2.a(view);
                            }
                            break;
                        default:
                            v03.j(df2Var, "this$0");
                            v03.j(ze2Var2, "$operation");
                            df2Var.b.remove(ze2Var2);
                            df2Var.c.remove(ze2Var2);
                            break;
                    }
                }
            });
        }
    }

    public abstract void b(ArrayList arrayList, boolean z);

    public final void c() {
        if (this.e) {
            return;
        }
        ViewGroup viewGroup = this.a;
        WeakHashMap weakHashMap = zt2.a;
        if (!lt2.b(viewGroup)) {
            e();
            this.d = false;
            return;
        }
        synchronized (this.b) {
            try {
                if (!this.b.isEmpty()) {
                    ArrayList<bf2> arrayListO0 = ou.o0(this.c);
                    this.c.clear();
                    for (bf2 bf2Var : arrayListO0) {
                        if (Log.isLoggable("FragmentManager", 2)) {
                            Objects.toString(bf2Var);
                        }
                        bf2Var.a();
                        if (!bf2Var.g) {
                            this.c.add(bf2Var);
                        }
                    }
                    h();
                    ArrayList arrayListO02 = ou.o0(this.b);
                    this.b.clear();
                    this.c.addAll(arrayListO02);
                    Log.isLoggable("FragmentManager", 2);
                    Iterator it = arrayListO02.iterator();
                    while (it.hasNext()) {
                        ((bf2) it.next()).d();
                    }
                    b(arrayListO02, this.d);
                    this.d = false;
                    Log.isLoggable("FragmentManager", 2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final bf2 d(ww0 ww0Var) {
        Object next;
        Iterator it = this.b.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            bf2 bf2Var = (bf2) next;
            if (v03.b(bf2Var.c, ww0Var) && !bf2Var.f) {
                break;
            }
        }
        return (bf2) next;
    }

    public final void e() {
        Log.isLoggable("FragmentManager", 2);
        ViewGroup viewGroup = this.a;
        WeakHashMap weakHashMap = zt2.a;
        boolean zB = lt2.b(viewGroup);
        synchronized (this.b) {
            try {
                h();
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    ((bf2) it.next()).d();
                }
                for (bf2 bf2Var : ou.o0(this.c)) {
                    if (Log.isLoggable("FragmentManager", 2)) {
                        if (!zB) {
                            Objects.toString(this.a);
                        }
                        Objects.toString(bf2Var);
                    }
                    bf2Var.a();
                }
                for (bf2 bf2Var2 : ou.o0(this.b)) {
                    if (Log.isLoggable("FragmentManager", 2)) {
                        if (!zB) {
                            Objects.toString(this.a);
                        }
                        Objects.toString(bf2Var2);
                    }
                    bf2Var2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void g() {
        Object objPrevious;
        synchronized (this.b) {
            try {
                h();
                ArrayList arrayList = this.b;
                ListIterator listIterator = arrayList.listIterator(arrayList.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        objPrevious = null;
                        break;
                    }
                    objPrevious = listIterator.previous();
                    bf2 bf2Var = (bf2) objPrevious;
                    View view = bf2Var.c.M;
                    v03.i(view, "operation.fragment.mView");
                    SpecialEffectsController$Operation$State specialEffectsController$Operation$StateA = p02.a(view);
                    SpecialEffectsController$Operation$State specialEffectsController$Operation$State = bf2Var.a;
                    SpecialEffectsController$Operation$State specialEffectsController$Operation$State2 = SpecialEffectsController$Operation$State.e;
                    if (specialEffectsController$Operation$State == specialEffectsController$Operation$State2 && specialEffectsController$Operation$StateA != specialEffectsController$Operation$State2) {
                        break;
                    }
                }
                this.e = false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void h() {
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State;
        for (bf2 bf2Var : this.b) {
            if (bf2Var.b == SpecialEffectsController$Operation$LifecycleImpact.e) {
                int visibility = bf2Var.c.C().getVisibility();
                if (visibility == 0) {
                    specialEffectsController$Operation$State = SpecialEffectsController$Operation$State.e;
                } else if (visibility == 4) {
                    specialEffectsController$Operation$State = SpecialEffectsController$Operation$State.l;
                } else {
                    if (visibility != 8) {
                        throw new IllegalArgumentException(u4.e("Unknown visibility ", visibility));
                    }
                    specialEffectsController$Operation$State = SpecialEffectsController$Operation$State.k;
                }
                bf2Var.c(specialEffectsController$Operation$State, SpecialEffectsController$Operation$LifecycleImpact.c);
            }
        }
    }
}
