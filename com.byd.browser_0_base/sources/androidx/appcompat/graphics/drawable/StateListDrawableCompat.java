package androidx.appcompat.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import androidx.appcompat.graphics.drawable.DrawableContainerCompat;

/* JADX INFO: loaded from: classes.dex */
public class StateListDrawableCompat extends DrawableContainerCompat {
    public StateListState r;
    public boolean s;

    public static class StateListState extends DrawableContainerCompat.DrawableContainerState {
        public int[][] I;

        public StateListState(StateListState stateListState, StateListDrawableCompat stateListDrawableCompat, Resources resources) {
            super(stateListState, stateListDrawableCompat, resources);
            if (stateListState != null) {
                this.I = stateListState.I;
            } else {
                this.I = new int[this.g.length][];
            }
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat.DrawableContainerState
        public void d() {
            int[][] iArr = this.I;
            int[][] iArr2 = new int[iArr.length][];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[] iArr3 = this.I[length];
                iArr2[length] = iArr3 != null ? (int[]) iArr3.clone() : null;
            }
            this.I = iArr2;
        }

        public final int e(int[] iArr) {
            int[][] iArr2 = this.I;
            int i = this.h;
            for (int i2 = 0; i2 < i; i2++) {
                if (StateSet.stateSetMatches(iArr2[i2], iArr)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new StateListDrawableCompat(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new StateListDrawableCompat(this, resources);
        }
    }

    public StateListDrawableCompat() {
        this(null, null);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public final void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    public void e(DrawableContainerCompat.DrawableContainerState drawableContainerState) {
        super.e(drawableContainerState);
        if (drawableContainerState instanceof StateListState) {
            this.r = (StateListState) drawableContainerState;
        }
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public StateListState b() {
        return new StateListState(this.r, this, null);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.s) {
            super.mutate();
            this.r.d();
            this.s = true;
        }
        return this;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        int iE = this.r.e(iArr);
        if (iE < 0) {
            iE = this.r.e(StateSet.WILD_CARD);
        }
        return d(iE) || zOnStateChange;
    }

    public StateListDrawableCompat(StateListState stateListState, Resources resources) {
        e(new StateListState(stateListState, this, resources));
        onStateChange(getState());
    }

    public StateListDrawableCompat(int i) {
    }
}
