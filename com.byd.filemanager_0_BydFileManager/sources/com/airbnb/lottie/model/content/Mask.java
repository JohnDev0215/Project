package com.airbnb.lottie.model.content;

/* JADX INFO: loaded from: classes.dex */
public class Mask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MaskMode f2529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.h f2530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.d f2531c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f2532d;

    public enum MaskMode {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT,
        MASK_MODE_NONE
    }

    public Mask(MaskMode maskMode, com.airbnb.lottie.model.i.h hVar, com.airbnb.lottie.model.i.d dVar, boolean z) {
        this.f2529a = maskMode;
        this.f2530b = hVar;
        this.f2531c = dVar;
        this.f2532d = z;
    }

    public MaskMode a() {
        return this.f2529a;
    }

    public com.airbnb.lottie.model.i.h b() {
        return this.f2530b;
    }

    public com.airbnb.lottie.model.i.d c() {
        return this.f2531c;
    }

    public boolean d() {
        return this.f2532d;
    }
}
