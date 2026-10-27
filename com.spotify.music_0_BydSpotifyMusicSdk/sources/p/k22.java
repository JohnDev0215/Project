package p;

/* JADX INFO: loaded from: classes.dex */
public final class k22 {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public long m;
    public int n;

    public final void a(int i) {
        if ((this.d & i) != 0) {
            return;
        }
        throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.d));
    }

    public final int b() {
        return this.g ? this.b - this.c : this.e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("State{mTargetPosition=");
        sb.append(this.a);
        sb.append(", mData=null, mItemCount=");
        sb.append(this.e);
        sb.append(", mIsMeasuring=");
        sb.append(this.i);
        sb.append(", mPreviousLayoutItemCount=");
        sb.append(this.b);
        sb.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
        sb.append(this.c);
        sb.append(", mStructureChanged=");
        sb.append(this.f);
        sb.append(", mInPreLayout=");
        sb.append(this.g);
        sb.append(", mRunSimpleAnimations=");
        sb.append(this.j);
        sb.append(", mRunPredictiveAnimations=");
        return u4.n(sb, this.k, '}');
    }
}
