package com.airbnb.lottie.compose;

import A7.m;
import E0.V;
import S3.k;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Lcom/airbnb/lottie/compose/LottieAnimationSizeElement;", "LE0/V;", "LS3/k;", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class LottieAnimationSizeElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f12662a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f12663b;

    public LottieAnimationSizeElement(int i7, int i10) {
        this.f12662a = i7;
        this.f12663b = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LottieAnimationSizeElement)) {
            return false;
        }
        LottieAnimationSizeElement lottieAnimationSizeElement = (LottieAnimationSizeElement) obj;
        return this.f12662a == lottieAnimationSizeElement.f12662a && this.f12663b == lottieAnimationSizeElement.f12663b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f12663b) + (Integer.hashCode(this.f12662a) * 31);
    }

    @Override // E0.V
    public final n i() {
        k kVar = new k();
        kVar.f7950y = this.f12662a;
        kVar.f7951z = this.f12663b;
        return kVar;
    }

    @Override // E0.V
    public final void l(n nVar) {
        k kVar = (k) nVar;
        m.f(kVar, "node");
        kVar.f7950y = this.f12662a;
        kVar.f7951z = this.f12663b;
    }

    public final String toString() {
        return "LottieAnimationSizeElement(width=" + this.f12662a + ", height=" + this.f12663b + ")";
    }
}
