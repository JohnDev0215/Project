package com.airbnb.lottie.model.content;

import android.graphics.Paint;
import com.airbnb.lottie.r.b.r;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShapeStroke implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2544a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2545b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<com.airbnb.lottie.model.i.b> f2546c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.a f2547d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.d f2548e;
    private final com.airbnb.lottie.model.i.b f;
    private final LineCapType g;
    private final LineJoinType h;
    private final float i;
    private final boolean j;

    public enum LineCapType {
        BUTT,
        ROUND,
        UNKNOWN;

        public Paint.Cap toPaintCap() {
            int i = a.f2551a[ordinal()];
            return i != 1 ? i != 2 ? Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }
    }

    public enum LineJoinType {
        MITER,
        ROUND,
        BEVEL;

        public Paint.Join toPaintJoin() {
            int i = a.f2552b[ordinal()];
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

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2551a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2552b;

        static {
            int[] iArr = new int[LineJoinType.values().length];
            f2552b = iArr;
            try {
                iArr[LineJoinType.BEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2552b[LineJoinType.MITER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2552b[LineJoinType.ROUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[LineCapType.values().length];
            f2551a = iArr2;
            try {
                iArr2[LineCapType.BUTT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2551a[LineCapType.ROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2551a[LineCapType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public ShapeStroke(String str, com.airbnb.lottie.model.i.b bVar, List<com.airbnb.lottie.model.i.b> list, com.airbnb.lottie.model.i.a aVar, com.airbnb.lottie.model.i.d dVar, com.airbnb.lottie.model.i.b bVar2, LineCapType lineCapType, LineJoinType lineJoinType, float f, boolean z) {
        this.f2544a = str;
        this.f2545b = bVar;
        this.f2546c = list;
        this.f2547d = aVar;
        this.f2548e = dVar;
        this.f = bVar2;
        this.g = lineCapType;
        this.h = lineJoinType;
        this.i = f;
        this.j = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new r(fVar, aVar, this);
    }

    public LineCapType b() {
        return this.g;
    }

    public com.airbnb.lottie.model.i.a c() {
        return this.f2547d;
    }

    public com.airbnb.lottie.model.i.b d() {
        return this.f2545b;
    }

    public LineJoinType e() {
        return this.h;
    }

    public List<com.airbnb.lottie.model.i.b> f() {
        return this.f2546c;
    }

    public float g() {
        return this.i;
    }

    public String h() {
        return this.f2544a;
    }

    public com.airbnb.lottie.model.i.d i() {
        return this.f2548e;
    }

    public com.airbnb.lottie.model.i.b j() {
        return this.f;
    }

    public boolean k() {
        return this.j;
    }
}
