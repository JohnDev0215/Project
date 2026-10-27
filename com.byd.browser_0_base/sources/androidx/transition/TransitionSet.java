package androidx.transition;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import defpackage.C0173a;
import defpackage.df3;
import defpackage.fh3;
import defpackage.gq2;
import defpackage.pk;
import defpackage.sv2;
import defpackage.vd1;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class TransitionSet extends Transition {
    public ArrayList<Transition> C;
    public boolean D;
    public int E;
    public boolean F;
    public int G;

    public class a extends g {
        public final /* synthetic */ Transition e;

        public a(Transition transition) {
            this.e = transition;
        }

        @Override // androidx.transition.Transition.d
        public final void d(Transition transition) {
            this.e.F();
            transition.C(this);
        }
    }

    public static class b extends g {
        public final TransitionSet e;

        public b(TransitionSet transitionSet) {
            this.e = transitionSet;
        }

        @Override // androidx.transition.g, androidx.transition.Transition.d
        public final void b(Transition transition) {
            TransitionSet transitionSet = this.e;
            if (transitionSet.F) {
                return;
            }
            transitionSet.O();
            transitionSet.F = true;
        }

        @Override // androidx.transition.Transition.d
        public final void d(Transition transition) {
            TransitionSet transitionSet = this.e;
            int i = transitionSet.E - 1;
            transitionSet.E = i;
            if (i == 0) {
                transitionSet.F = false;
                transitionSet.r();
            }
            transition.C(this);
        }
    }

    public TransitionSet() {
        this.C = new ArrayList<>();
        this.D = true;
        this.F = false;
        this.G = 0;
    }

    @Override // androidx.transition.Transition
    public final void B(View view) {
        super.B(view);
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            this.C.get(i).B(view);
        }
    }

    @Override // androidx.transition.Transition
    public final void C(Transition.d dVar) {
        super.C(dVar);
    }

    @Override // androidx.transition.Transition
    public final void D(View view) {
        for (int i = 0; i < this.C.size(); i++) {
            this.C.get(i).D(view);
        }
        this.j.remove(view);
    }

    @Override // androidx.transition.Transition
    public final void E(ViewGroup viewGroup) {
        super.E(viewGroup);
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            this.C.get(i).E(viewGroup);
        }
    }

    @Override // androidx.transition.Transition
    public final void F() {
        if (this.C.isEmpty()) {
            O();
            r();
            return;
        }
        b bVar = new b(this);
        Iterator<Transition> it = this.C.iterator();
        while (it.hasNext()) {
            it.next().b(bVar);
        }
        this.E = this.C.size();
        if (this.D) {
            Iterator<Transition> it2 = this.C.iterator();
            while (it2.hasNext()) {
                it2.next().F();
            }
            return;
        }
        for (int i = 1; i < this.C.size(); i++) {
            this.C.get(i - 1).b(new a(this.C.get(i)));
        }
        Transition transition = this.C.get(0);
        if (transition != null) {
            transition.F();
        }
    }

    @Override // androidx.transition.Transition
    public final void I(Transition.c cVar) {
        this.x = cVar;
        this.G |= 8;
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            this.C.get(i).I(cVar);
        }
    }

    @Override // androidx.transition.Transition
    public final void K(PathMotion pathMotion) {
        super.K(pathMotion);
        this.G |= 4;
        if (this.C != null) {
            for (int i = 0; i < this.C.size(); i++) {
                this.C.get(i).K(pathMotion);
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void M(vd1 vd1Var) {
        this.w = vd1Var;
        this.G |= 2;
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            this.C.get(i).M(vd1Var);
        }
    }

    @Override // androidx.transition.Transition
    public final void N(long j) {
        this.f = j;
    }

    @Override // androidx.transition.Transition
    public final String P(String str) {
        String strP = super.P(str);
        for (int i = 0; i < this.C.size(); i++) {
            StringBuilder sbK = C0173a.k(strP, "\n");
            sbK.append(this.C.get(i).P(str + "  "));
            strP = sbK.toString();
        }
        return strP;
    }

    public final void Q(Transition transition) {
        this.C.add(transition);
        transition.m = this;
        long j = this.g;
        if (j >= 0) {
            transition.H(j);
        }
        if ((this.G & 1) != 0) {
            transition.J(this.h);
        }
        if ((this.G & 2) != 0) {
            transition.M(this.w);
        }
        if ((this.G & 4) != 0) {
            transition.K(this.y);
        }
        if ((this.G & 8) != 0) {
            transition.I(this.x);
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public final void H(long j) {
        ArrayList<Transition> arrayList;
        this.g = j;
        if (j < 0 || (arrayList = this.C) == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.C.get(i).H(j);
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public final void J(TimeInterpolator timeInterpolator) {
        this.G |= 1;
        ArrayList<Transition> arrayList = this.C;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.C.get(i).J(timeInterpolator);
            }
        }
        this.h = timeInterpolator;
    }

    public final void U(int i) {
        if (i == 0) {
            this.D = true;
        } else {
            if (i != 1) {
                throw new AndroidRuntimeException(gq2.e("Invalid parameter for TransitionSet ordering: ", i));
            }
            this.D = false;
        }
    }

    @Override // androidx.transition.Transition
    public final void b(Transition.d dVar) {
        super.b(dVar);
    }

    @Override // androidx.transition.Transition
    public final void e(View view) {
        for (int i = 0; i < this.C.size(); i++) {
            this.C.get(i).e(view);
        }
        this.j.add(view);
    }

    @Override // androidx.transition.Transition
    public final void h() {
        super.h();
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            this.C.get(i).h();
        }
    }

    @Override // androidx.transition.Transition
    public final void i(df3 df3Var) {
        View view = df3Var.b;
        if (z(view)) {
            for (Transition transition : this.C) {
                if (transition.z(view)) {
                    transition.i(df3Var);
                    df3Var.c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void k(df3 df3Var) {
        super.k(df3Var);
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            this.C.get(i).k(df3Var);
        }
    }

    @Override // androidx.transition.Transition
    public final void l(df3 df3Var) {
        View view = df3Var.b;
        if (z(view)) {
            for (Transition transition : this.C) {
                if (transition.z(view)) {
                    transition.l(df3Var);
                    df3Var.c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: o */
    public final Transition clone() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.C = new ArrayList<>();
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            Transition transitionClone = this.C.get(i).clone();
            transitionSet.C.add(transitionClone);
            transitionClone.m = transitionSet;
        }
        return transitionSet;
    }

    @Override // androidx.transition.Transition
    public final void q(ViewGroup viewGroup, pk pkVar, pk pkVar2, ArrayList<df3> arrayList, ArrayList<df3> arrayList2) {
        long j = this.f;
        int size = this.C.size();
        for (int i = 0; i < size; i++) {
            Transition transition = this.C.get(i);
            if (j > 0 && (this.D || i == 0)) {
                long j2 = transition.f;
                if (j2 > 0) {
                    transition.N(j2 + j);
                } else {
                    transition.N(j);
                }
            }
            transition.q(viewGroup, pkVar, pkVar2, arrayList, arrayList2);
        }
    }

    @SuppressLint({"RestrictedApi"})
    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = new ArrayList<>();
        this.D = true;
        this.F = false;
        this.G = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sv2.g);
        U(fh3.d(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        typedArrayObtainStyledAttributes.recycle();
    }
}
