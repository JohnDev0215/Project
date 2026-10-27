package com.airbnb.lottie.model.content;

/* JADX INFO: loaded from: classes.dex */
public class Mask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MaskMode f2913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.h f2914b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.d f2915c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f2916d;

    public enum MaskMode {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT
    }

    public Mask(MaskMode maskMode, com.airbnb.lottie.model.i.h hVar, com.airbnb.lottie.model.i.d dVar, boolean z) {
        this.f2913a = maskMode;
        this.f2914b = hVar;
        this.f2915c = dVar;
        this.f2916d = z;
    }

    public MaskMode a() {
        return this.f2913a;
    }

    public com.airbnb.lottie.model.i.h b() {
        return this.f2914b;
    }

    public com.airbnb.lottie.model.i.d c() {
        return this.f2915c;
    }

    public boolean d() {
        return this.f2916d;
    }
}
