package com.bumptech.glide;

/* JADX INFO: compiled from: MemoryCategory.java */
/* JADX INFO: loaded from: classes.dex */
public enum f {
    LOW(0.5f),
    NORMAL(1.0f),
    HIGH(1.5f);


    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final float f3981e;

    f(float f7) {
        this.f3981e = f7;
    }

    public float getMultiplier() {
        return this.f3981e;
    }
}
