package androidx.constraintlayout.solver.widgets;

/* JADX INFO: loaded from: classes.dex */
public class ResolutionDimension extends ResolutionNode {
    float value = 0.0f;

    @Override // androidx.constraintlayout.solver.widgets.ResolutionNode
    public void reset() {
        super.reset();
        this.value = 0.0f;
    }

    public void resolve(int i) {
        int i2 = this.state;
        if (i2 == 0 || this.value != i) {
            this.value = i;
            if (i2 == 1) {
                invalidate();
            }
            didResolve();
        }
    }

    public void remove() {
        this.state = 2;
    }
}
