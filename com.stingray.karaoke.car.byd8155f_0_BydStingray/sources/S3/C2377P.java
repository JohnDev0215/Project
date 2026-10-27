package s3;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s3.P, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2377P {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f22202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22203b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22204e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f22205f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f22206g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f22207h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f22208i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f22209j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f22210k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f22211m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22212n;

    public final void a(int i7) {
        if ((this.d & i7) != 0) {
            return;
        }
        throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i7) + " but it is " + Integer.toBinaryString(this.d));
    }

    public final int b() {
        return this.f22206g ? this.f22203b - this.c : this.f22204e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("State{mTargetPosition=");
        sb.append(this.f22202a);
        sb.append(", mData=null, mItemCount=");
        sb.append(this.f22204e);
        sb.append(", mIsMeasuring=");
        sb.append(this.f22208i);
        sb.append(", mPreviousLayoutItemCount=");
        sb.append(this.f22203b);
        sb.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
        sb.append(this.c);
        sb.append(", mStructureChanged=");
        sb.append(this.f22205f);
        sb.append(", mInPreLayout=");
        sb.append(this.f22206g);
        sb.append(", mRunSimpleAnimations=");
        sb.append(this.f22209j);
        sb.append(", mRunPredictiveAnimations=");
        return AbstractC1347d.i(sb, this.f22210k, '}');
    }
}
