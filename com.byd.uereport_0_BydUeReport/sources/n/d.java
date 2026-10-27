package n;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import n.b;

/* JADX INFO: compiled from: StateListDrawable.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class d extends b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public a f6502p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6503q;

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
            int i8 = i();
            for (int i9 = 0; i9 < i8; i9++) {
                if (StateSet.stateSetMatches(iArr2[i9], iArr)) {
                    return i9;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new d(this, null);
        }

        @Override // n.b.c
        public void p(int i8, int i9) {
            super.p(i8, i9);
            int[][] iArr = new int[i9][];
            System.arraycopy(this.J, 0, iArr, 0, i8);
            this.J = iArr;
        }

        @Override // n.b.c
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

    @Override // n.b, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // n.b
    public void clearMutated() {
        super.clearMutated();
        this.f6503q = false;
    }

    @Override // n.b
    public void g(b.c cVar) {
        super.g(cVar);
        if (cVar instanceof a) {
            this.f6502p = (a) cVar;
        }
    }

    @Override // n.b
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public a b() {
        return new a(this.f6502p, this, null);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    public int[] j(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i8 = 0;
        for (int i9 = 0; i9 < attributeCount; i9++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i9);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i10 = i8 + 1;
                if (!attributeSet.getAttributeBooleanValue(i9, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i8] = attributeNameResource;
                i8 = i10;
            }
        }
        return StateSet.trimStateSet(iArr, i8);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f6503q && super.mutate() == this) {
            this.f6502p.s();
            this.f6503q = true;
        }
        return this;
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        int iB = this.f6502p.B(iArr);
        if (iB < 0) {
            iB = this.f6502p.B(StateSet.WILD_CARD);
        }
        return f(iB) || zOnStateChange;
    }

    public d(a aVar) {
        if (aVar != null) {
            g(aVar);
        }
    }
}
