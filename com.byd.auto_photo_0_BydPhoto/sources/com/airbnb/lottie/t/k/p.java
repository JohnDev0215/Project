package com.airbnb.lottie.t.k;

import android.graphics.Paint;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.b.r;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class p implements com.airbnb.lottie.t.k.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f863a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private final com.airbnb.lottie.t.j.b f864b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<com.airbnb.lottie.t.j.b> f865c;
    private final com.airbnb.lottie.t.j.a d;
    private final com.airbnb.lottie.t.j.d e;
    private final com.airbnb.lottie.t.j.b f;
    private final b g;
    private final c h;
    private final float i;
    private final boolean j;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f866a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f867b;

        static {
            int[] iArr = new int[c.values().length];
            f867b = iArr;
            try {
                iArr[c.BEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f867b[c.MITER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f867b[c.ROUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[b.values().length];
            f866a = iArr2;
            try {
                iArr2[b.BUTT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f866a[b.ROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f866a[b.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum b {
        BUTT,
        ROUND,
        UNKNOWN;

        public Paint.Cap a() {
            int i = a.f866a[ordinal()];
            return i != 1 ? i != 2 ? Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }
    }

    public enum c {
        MITER,
        ROUND,
        BEVEL;

        public Paint.Join a() {
            int i = a.f867b[ordinal()];
            if (i == 1) {
                return Paint.Join.BEVEL;
            }
            if (i == 2) {
                return Paint.Join.MITER;
            }
            if (i != 3) {
                return null;
            }
            return Paint.Join.ROUND;
        }
    }

    public p(String str, @Nullable com.airbnb.lottie.t.j.b bVar, List<com.airbnb.lottie.t.j.b> list, com.airbnb.lottie.t.j.a aVar, com.airbnb.lottie.t.j.d dVar, com.airbnb.lottie.t.j.b bVar2, b bVar3, c cVar, float f, boolean z) {
        this.f863a = str;
        this.f864b = bVar;
        this.f865c = list;
        this.d = aVar;
        this.e = dVar;
        this.f = bVar2;
        this.g = bVar3;
        this.h = cVar;
        this.i = f;
        this.j = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new r(fVar, aVar, this);
    }

    public b b() {
        return this.g;
    }

    public com.airbnb.lottie.t.j.a c() {
        return this.d;
    }

    public com.airbnb.lottie.t.j.b d() {
        return this.f864b;
    }

    public c e() {
        return this.h;
    }

    public List<com.airbnb.lottie.t.j.b> f() {
        return this.f865c;
    }

    public float g() {
        return this.i;
    }

    public String h() {
        return this.f863a;
    }

    public com.airbnb.lottie.t.j.d i() {
        return this.e;
    }

    public com.airbnb.lottie.t.j.b j() {
        return this.f;
    }

    public boolean k() {
        return this.j;
    }
}
