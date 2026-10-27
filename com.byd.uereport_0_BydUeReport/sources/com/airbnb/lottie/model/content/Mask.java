package com.airbnb.lottie.model.content;

import u1.d;
import u1.h;

/* JADX INFO: loaded from: classes.dex */
public class Mask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MaskMode f2457a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h f2458b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f2459c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f2460d;

    public enum MaskMode {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT,
        MASK_MODE_NONE
    }

    public Mask(MaskMode maskMode, h hVar, d dVar, boolean z7) {
        this.f2457a = maskMode;
        this.f2458b = hVar;
        this.f2459c = dVar;
        this.f2460d = z7;
    }

    public MaskMode a() {
        return this.f2457a;
    }

    public h b() {
        return this.f2458b;
    }

    public d c() {
        return this.f2459c;
    }

    public boolean d() {
        return this.f2460d;
    }
}
