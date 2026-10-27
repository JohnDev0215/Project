package com.bumptech.glide.load.n.a0;

/* JADX INFO: loaded from: classes.dex */
public final class i implements a<int[]> {
    @Override // com.bumptech.glide.load.n.a0.a
    public String a() {
        return "IntegerArrayPool";
    }

    @Override // com.bumptech.glide.load.n.a0.a
    public int c() {
        return 4;
    }

    @Override // com.bumptech.glide.load.n.a0.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int b(int[] iArr) {
        return iArr.length;
    }

    @Override // com.bumptech.glide.load.n.a0.a
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public int[] newArray(int i) {
        return new int[i];
    }
}
