package r1;

/* JADX INFO: compiled from: IntegerArrayAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements a<int[]> {
    @Override // r1.a
    public String a() {
        return "IntegerArrayPool";
    }

    @Override // r1.a
    public int c() {
        return 4;
    }

    @Override // r1.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int b(int[] iArr) {
        return iArr.length;
    }

    @Override // r1.a
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public int[] newArray(int i7) {
        return new int[i7];
    }
}
