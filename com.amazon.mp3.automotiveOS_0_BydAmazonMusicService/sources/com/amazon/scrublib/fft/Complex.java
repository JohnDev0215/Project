package com.amazon.scrublib.fft;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Complex.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0086\u0006J\u0006\u0010\u0005\u001a\u00020\u0002R\u0014\u0010\u0006\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/amazon/scrublib/fft/Complex;", "", "", "x", "times", "magnitudeSquared", "re", "D", "im", "exp$delegate", "Lkotlin/Lazy;", "getExp", "()Lcom/amazon/scrublib/fft/Complex;", "exp", "<init>", "(DD)V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class Complex {

    /* JADX INFO: renamed from: exp$delegate, reason: from kotlin metadata */
    private final Lazy exp = LazyKt__LazyJVMKt.lazy(new Function0<Complex>() { // from class: com.amazon.scrublib.fft.Complex$exp$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Complex invoke() {
            return new Complex(Math.cos(this.this$0.im), Math.sin(this.this$0.im)).times(Math.cosh(this.this$0.re) + Math.sinh(this.this$0.re));
        }
    });
    private final double im;
    private final double re;

    public Complex(double d, double d2) {
        this.re = d;
        this.im = d2;
    }

    public final Complex times(double x) {
        return new Complex(this.re * x, this.im * x);
    }

    public final double magnitudeSquared() {
        double d = 2;
        return Math.pow(this.re, d) + Math.pow(this.im, d);
    }
}
