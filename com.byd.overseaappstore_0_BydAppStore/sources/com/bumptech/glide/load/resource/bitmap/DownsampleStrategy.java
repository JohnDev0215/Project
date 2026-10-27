package com.bumptech.glide.load.resource.bitmap;

import java.util.Objects;
import spi.jk;

/* JADX INFO: loaded from: classes.dex */
public abstract class DownsampleStrategy {
    public static final e a;
    public static final c b;
    public static final d c;
    public static final f d;
    public static final d e;
    public static final jk<DownsampleStrategy> f;
    public static final boolean g;

    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    public static class a extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final SampleSizeRounding a(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final float b(int i, int i2, int i3, int i4) {
            if (Math.min(i2 / i4, i / i3) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r1);
        }
    }

    public static class b extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final SampleSizeRounding a(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.MEMORY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final float b(int i, int i2, int i3, int i4) {
            int iCeil = (int) Math.ceil(Math.max(i2 / i4, i / i3));
            return 1.0f / (r2 << (Math.max(1, Integer.highestOneBit(iCeil)) >= iCeil ? 0 : 1));
        }
    }

    public static class c extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final SampleSizeRounding a(int i, int i2, int i3, int i4) {
            if (b(i, i2, i3, i4) == 1.0f) {
                return SampleSizeRounding.QUALITY;
            }
            Objects.requireNonNull(DownsampleStrategy.a);
            return DownsampleStrategy.g ? SampleSizeRounding.QUALITY : SampleSizeRounding.MEMORY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final float b(int i, int i2, int i3, int i4) {
            return Math.min(1.0f, DownsampleStrategy.a.b(i, i2, i3, i4));
        }
    }

    public static class d extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final SampleSizeRounding a(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final float b(int i, int i2, int i3, int i4) {
            return Math.max(i3 / i, i4 / i2);
        }
    }

    public static class e extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final SampleSizeRounding a(int i, int i2, int i3, int i4) {
            return DownsampleStrategy.g ? SampleSizeRounding.QUALITY : SampleSizeRounding.MEMORY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final float b(int i, int i2, int i3, int i4) {
            if (DownsampleStrategy.g) {
                return Math.min(i3 / i, i4 / i2);
            }
            if (Math.max(i2 / i4, i / i3) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r2);
        }
    }

    public static class f extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final SampleSizeRounding a(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public final float b(int i, int i2, int i3, int i4) {
            return 1.0f;
        }
    }

    static {
        new a();
        new b();
        a = new e();
        b = new c();
        d dVar = new d();
        c = dVar;
        d = new f();
        e = dVar;
        f = jk.c("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", dVar);
        g = true;
    }

    public abstract SampleSizeRounding a(int i, int i2, int i3, int i4);

    public abstract float b(int i, int i2, int i3, int i4);
}
