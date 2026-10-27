package k;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import k.b;

/* JADX INFO: compiled from: StateListDrawable.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class d extends b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public a f3575n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f3576o;

    /* JADX INFO: compiled from: StateListDrawable.java */
    public static class a extends b.c {
        public int[][] J;

        public a(a aVar, d dVar, Resources resources) {
            super(aVar, dVar, resources);
            if (aVar != null) {
                this.J = aVar.J;
            } else {
                this.J = new int[g()][];
            }
        }

        public int A(int[] iArr, Drawable drawable) {
            int iA = a(drawable);
            this.J[iA] = iArr;
            return iA;
        }

        public int B(int[] iArr) {
            int[][] iArr2 = this.J;
            int i3 = i();
            for (int i4 = 0; i4 < i3; i4++) {
                if (StateSet.stateSetMatches(iArr2[i4], iArr)) {
                    return i4;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new d(this, null);
        }

        @Override // k.b.c
        public void p(int i3, int i4) {
            super.p(i3, i4);
            int[][] iArr = new int[i4][];
            System.arraycopy(this.J, 0, iArr, 0, i3);
            this.J = iArr;
        }

        @Override // k.b.c
        public void s() {
            int[][] iArr = this.J;
            int[][] iArr2 = new int[iArr.length][];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[][] iArr3 = this.J;
                iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
            }
            this.J = iArr2;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new d(this, resources);
        }
    }

    public d(a aVar, Resources resources) {
        g(new a(aVar, this, resources));
        onStateChange(getState());
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // k.b
    public void clearMutated() {
        super.clearMutated();
        this.f3576o = false;
    }

    @Override // k.b
    public void g(b.c cVar) {
        super.g(cVar);
        if (cVar instanceof a) {
            this.f3575n = (a) cVar;
        }
    }

    @Override // k.b
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public a b() {
        return new a(this.f3575n, this, null);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    public int[] j(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i3 = 0;
        for (int i4 = 0; i4 < attributeCount; i4++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i4);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i5 = i3 + 1;
                if (!attributeSet.getAttributeBooleanValue(i4, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i3] = attributeNameResource;
                i3 = i5;
            }
        }
        return StateSet.trimStateSet(iArr, i3);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f3576o && super.mutate() == this) {
            this.f3575n.s();
            this.f3576o = true;
        }
        return this;
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        int iB = this.f3575n.B(iArr);
        if (iB < 0) {
            iB = this.f3575n.B(StateSet.WILD_CARD);
        }
        return f(iB) || zOnStateChange;
    }

    public d(a aVar) {
        if (aVar != null) {
            g(aVar);
        }
    }
}
