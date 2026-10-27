package androidx.databinding;

import a.k.c;
import a.k.i;
import a.k.n;
import a.k.o;
import a.k.q;
import a.n.g;
import a.n.k;
import a.n.l;
import a.n.r;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseIntArray;
import android.view.Choreographer;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import e.a.a.c;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends a.k.a {
    public static int n;
    public static final boolean o;
    public static final f p;
    public static final f q;
    public static final c.a<o, ViewDataBinding, Void> r;
    public static final ReferenceQueue<ViewDataBinding> s;
    public static final View.OnAttachStateChangeListener t;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Runnable f1541b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1542c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1543d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i[] f1544e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View f1545f;
    public a.k.c<o, ViewDataBinding, Void> g;
    public boolean h;
    public Choreographer i;
    public final Choreographer.FrameCallback j;
    public Handler k;
    public k l;
    public OnStartListener m;

    public static class OnStartListener implements a.n.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<ViewDataBinding> f1546a;

        public OnStartListener(ViewDataBinding viewDataBinding, a aVar) {
            this.f1546a = new WeakReference<>(viewDataBinding);
        }

        @r(g.a.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f1546a.get();
            if (viewDataBinding != null) {
                viewDataBinding.l();
            }
        }
    }

    public static class a implements f {
        @Override // androidx.databinding.ViewDataBinding.f
        public i a(ViewDataBinding viewDataBinding, int i) {
            return new j(viewDataBinding, i).f1552a;
        }
    }

    public static class b implements f {
        @Override // androidx.databinding.ViewDataBinding.f
        public i a(ViewDataBinding viewDataBinding, int i) {
            return new h(viewDataBinding, i).f1548a;
        }
    }

    public static class c extends c.a<o, ViewDataBinding, Void> {
        @Override // a.k.c.a
        public void a(o oVar, ViewDataBinding viewDataBinding, int i, Void r4) {
            int iE;
            o oVar2 = oVar;
            ViewDataBinding viewDataBinding2 = viewDataBinding;
            if (i == 1) {
                RecyclerView recyclerView = e.a.a.c.this.i;
                if (recyclerView != null && recyclerView.P()) {
                    return;
                }
                viewDataBinding2.f1543d = true;
                return;
            }
            if (i != 2) {
                if (i == 3 && oVar2 == null) {
                    throw null;
                }
                return;
            }
            c.a aVar = (c.a) oVar2;
            RecyclerView recyclerView2 = e.a.a.c.this.i;
            if (recyclerView2 == null || recyclerView2.P() || (iE = aVar.f2523a.e()) == -1) {
                return;
            }
            try {
                e.a.a.c.this.f1636a.d(iE, 1, e.a.a.c.k);
            } catch (IllegalStateException unused) {
            }
        }
    }

    public static class d implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        @TargetApi(19)
        public void onViewAttachedToWindow(View view) {
            ViewDataBinding.m(view).f1541b.run();
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                ViewDataBinding.this.f1542c = false;
            }
            while (true) {
                Reference<? extends ViewDataBinding> referencePoll = ViewDataBinding.s.poll();
                if (referencePoll == null) {
                    break;
                } else if (referencePoll instanceof i) {
                    ((i) referencePoll).b();
                }
            }
            if (ViewDataBinding.this.f1545f.isAttachedToWindow()) {
                ViewDataBinding.this.l();
            } else {
                ViewDataBinding.this.f1545f.removeOnAttachStateChangeListener(ViewDataBinding.t);
                ViewDataBinding.this.f1545f.addOnAttachStateChangeListener(ViewDataBinding.t);
            }
        }
    }

    public interface f {
        i a(ViewDataBinding viewDataBinding, int i);
    }

    public interface g<T> {
        void a(T t);

        void b(k kVar);

        void c(T t);
    }

    public static class h extends n.a implements g<n> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final i<n> f1548a;

        public h(ViewDataBinding viewDataBinding, int i) {
            this.f1548a = new i<>(viewDataBinding, i, this);
        }

        @Override // androidx.databinding.ViewDataBinding.g
        public void a(n nVar) {
            nVar.b(this);
        }

        @Override // androidx.databinding.ViewDataBinding.g
        public void b(k kVar) {
        }

        @Override // androidx.databinding.ViewDataBinding.g
        public void c(n nVar) {
            nVar.a(this);
        }

        @Override // a.k.n.a
        public void d(n nVar) {
            i<n> iVar;
            n nVar2;
            ViewDataBinding viewDataBindingA = this.f1548a.a();
            if (viewDataBindingA != null && (nVar2 = (iVar = this.f1548a).f1551c) == nVar) {
                ViewDataBinding.j(viewDataBindingA, iVar.f1550b, nVar2, 0);
            }
        }

        @Override // a.k.n.a
        public void e(n nVar, int i, int i2) {
            d(nVar);
        }

        @Override // a.k.n.a
        public void f(n nVar, int i, int i2) {
            d(nVar);
        }

        @Override // a.k.n.a
        public void g(n nVar, int i, int i2, int i3) {
            d(nVar);
        }

        @Override // a.k.n.a
        public void h(n nVar, int i, int i2) {
            d(nVar);
        }
    }

    public static class i<T> extends WeakReference<ViewDataBinding> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final g<T> f1549a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1550b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public T f1551c;

        public i(ViewDataBinding viewDataBinding, int i, g<T> gVar) {
            super(viewDataBinding, ViewDataBinding.s);
            this.f1550b = i;
            this.f1549a = gVar;
        }

        public ViewDataBinding a() {
            ViewDataBinding viewDataBinding = (ViewDataBinding) get();
            if (viewDataBinding == null) {
                b();
            }
            return viewDataBinding;
        }

        public boolean b() {
            boolean z;
            T t = this.f1551c;
            if (t != null) {
                this.f1549a.c(t);
                z = true;
            } else {
                z = false;
            }
            this.f1551c = null;
            return z;
        }
    }

    public static class j extends i.a implements g<a.k.i> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final i<a.k.i> f1552a;

        public j(ViewDataBinding viewDataBinding, int i) {
            this.f1552a = new i<>(viewDataBinding, i, this);
        }

        @Override // androidx.databinding.ViewDataBinding.g
        public void a(a.k.i iVar) {
            iVar.b(this);
        }

        @Override // androidx.databinding.ViewDataBinding.g
        public void b(k kVar) {
        }

        @Override // androidx.databinding.ViewDataBinding.g
        public void c(a.k.i iVar) {
            iVar.c(this);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        n = i2;
        o = i2 >= 16;
        p = new a();
        q = new b();
        r = new c();
        s = new ReferenceQueue<>();
        t = new d();
    }

    public ViewDataBinding(Object obj, View view, int i2) {
        if (obj != null) {
            if (!(obj instanceof a.k.f)) {
                throw new IllegalArgumentException("The provided bindingComponent parameter must be an instance of DataBindingComponent. See  https://issuetracker.google.com/issues/116541301 for details of why this parameter is not defined as DataBindingComponent");
            }
        }
        this.f1541b = new e();
        this.f1542c = false;
        this.f1543d = false;
        this.f1544e = new i[i2];
        this.f1545f = view;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        }
        if (o) {
            this.i = Choreographer.getInstance();
            this.j = new q(this);
        } else {
            this.j = null;
            this.k = new Handler(Looper.myLooper());
        }
    }

    public static void j(ViewDataBinding viewDataBinding, int i2, Object obj, int i3) {
        if (viewDataBinding.r(i2, obj, i3)) {
            viewDataBinding.u();
        }
    }

    public static ViewDataBinding m(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(a.k.r.a.dataBinding);
        }
        return null;
    }

    public static boolean o(String str, int i2) {
        int length = str.length();
        if (length == i2) {
            return false;
        }
        while (i2 < length) {
            if (!Character.isDigit(str.charAt(i2))) {
                return false;
            }
            i2++;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void p(a.k.f r4, android.view.View r5, java.lang.Object[] r6, android.util.SparseIntArray r7, boolean r8) {
        /*
            androidx.databinding.ViewDataBinding r0 = m(r5)
            if (r0 == 0) goto L7
            return
        L7:
            java.lang.Object r0 = r5.getTag()
            boolean r1 = r0 instanceof java.lang.String
            if (r1 == 0) goto L12
            java.lang.String r0 = (java.lang.String) r0
            goto L13
        L12:
            r0 = 0
        L13:
            r1 = 0
            r2 = 1
            if (r8 == 0) goto L3b
            if (r0 == 0) goto L3b
            java.lang.String r8 = "layout"
            boolean r8 = r0.startsWith(r8)
            if (r8 == 0) goto L3b
            r8 = 95
            int r8 = r0.lastIndexOf(r8)
            if (r8 <= 0) goto L52
            int r8 = r8 + r2
            boolean r3 = o(r0, r8)
            if (r3 == 0) goto L52
            int r8 = s(r0, r8)
            r0 = r6[r8]
            if (r0 != 0) goto L53
            r6[r8] = r5
            goto L53
        L3b:
            if (r0 == 0) goto L52
            java.lang.String r8 = "binding_"
            boolean r8 = r0.startsWith(r8)
            if (r8 == 0) goto L52
            r8 = 8
            int r8 = s(r0, r8)
            r0 = r6[r8]
            if (r0 != 0) goto L53
            r6[r8] = r5
            goto L53
        L52:
            r2 = r1
        L53:
            if (r2 != 0) goto L6a
            int r8 = r5.getId()
            if (r8 <= 0) goto L6a
            if (r7 == 0) goto L6a
            r0 = -1
            int r8 = r7.get(r8, r0)
            if (r8 < 0) goto L6a
            r0 = r6[r8]
            if (r0 != 0) goto L6a
            r6[r8] = r5
        L6a:
            boolean r8 = r5 instanceof android.view.ViewGroup
            if (r8 == 0) goto L81
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            int r8 = r5.getChildCount()
            r0 = r1
        L75:
            if (r0 >= r8) goto L81
            android.view.View r2 = r5.getChildAt(r0)
            p(r4, r2, r6, r7, r1)
            int r0 = r0 + 1
            goto L75
        L81:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.databinding.ViewDataBinding.p(a.k.f, android.view.View, java.lang.Object[], android.util.SparseIntArray, boolean):void");
    }

    public static Object[] q(a.k.f fVar, View view, int i2, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i2];
        p(fVar, view, objArr, sparseIntArray, true);
        return objArr;
    }

    public static int s(String str, int i2) {
        int iCharAt = 0;
        while (i2 < str.length()) {
            iCharAt = (iCharAt * 10) + (str.charAt(i2) - '0');
            i2++;
        }
        return iCharAt;
    }

    public abstract void k();

    public void l() {
        if (this.h) {
            u();
            return;
        }
        if (n()) {
            this.h = true;
            this.f1543d = false;
            a.k.c<o, ViewDataBinding, Void> cVar = this.g;
            if (cVar != null) {
                cVar.c(this, 1, null);
                if (this.f1543d) {
                    this.g.c(this, 2, null);
                }
            }
            if (!this.f1543d) {
                k();
                a.k.c<o, ViewDataBinding, Void> cVar2 = this.g;
                if (cVar2 != null) {
                    cVar2.c(this, 3, null);
                }
            }
            this.h = false;
        }
    }

    public abstract boolean n();

    public abstract boolean r(int i2, Object obj, int i3);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void t(int i2, Object obj, f fVar) {
        i iVarA = this.f1544e[i2];
        if (iVarA == null) {
            iVarA = fVar.a(this, i2);
            this.f1544e[i2] = iVarA;
            k kVar = this.l;
            if (kVar != null) {
                iVarA.f1549a.b(kVar);
            }
        }
        iVarA.b();
        iVarA.f1551c = obj;
        iVarA.f1549a.a(obj);
    }

    public void u() {
        k kVar = this.l;
        if (kVar != null) {
            if (!(((l) kVar.a()).f1094b.compareTo(g.b.STARTED) >= 0)) {
                return;
            }
        }
        synchronized (this) {
            if (this.f1542c) {
                return;
            }
            this.f1542c = true;
            if (o) {
                this.i.postFrameCallback(this.j);
            } else {
                this.k.post(this.f1541b);
            }
        }
    }

    public abstract boolean v(int i2, Object obj);

    public boolean w(int i2, a.k.i iVar) {
        return x(i2, iVar, p);
    }

    public final boolean x(int i2, Object obj, f fVar) {
        if (obj == null) {
            i iVar = this.f1544e[i2];
            if (iVar != null) {
                return iVar.b();
            }
            return false;
        }
        i[] iVarArr = this.f1544e;
        i iVar2 = iVarArr[i2];
        if (iVar2 == null) {
            t(i2, obj, fVar);
            return true;
        }
        if (iVar2.f1551c == obj) {
            return false;
        }
        i iVar3 = iVarArr[i2];
        if (iVar3 != null) {
            iVar3.b();
        }
        t(i2, obj, fVar);
        return true;
    }
}
