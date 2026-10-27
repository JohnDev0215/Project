package com.airbnb.lottie.model.content;

import android.graphics.Paint;
import java.util.List;
import o1.g;
import q1.c;
import q1.r;
import u1.d;
import v1.b;

/* JADX INFO: loaded from: classes.dex */
public class ShapeStroke implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u1.b f2475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<u1.b> f2476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u1.a f2477d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f2478e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u1.b f2479f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final LineCapType f2480g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final LineJoinType f2481h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f2482i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f2483j;

    public enum LineCapType {
        BUTT,
        ROUND,
        UNKNOWN;

        public Paint.Cap toPaintCap() {
            int i8 = a.f2484a[ordinal()];
            return i8 != 1 ? i8 != 2 ? Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }
    }

    public enum LineJoinType {
        MITER,
        ROUND,
        BEVEL;

        public Paint.Join toPaintJoin() {
            int i8 = a.f2485b[ordinal()];
            if (i8 == 1) {
                return Paint.Join.BEVEL;
            }
            if (i8 == 2) {
                return Paint.Join.MITER;
            }
            if (i8 != 3) {
                return null;
            }
            return Paint.Join.ROUND;
        }
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2484a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f2485b;

        static {
            int[] iArr = new int[LineJoinType.values().length];
            f2485b = iArr;
            try {
                iArr[LineJoinType.BEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2485b[LineJoinType.MITER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2485b[LineJoinType.ROUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[LineCapType.values().length];
            f2484a = iArr2;
            try {
                iArr2[LineCapType.BUTT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2484a[LineCapType.ROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2484a[LineCapType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public ShapeStroke(String str, u1.b bVar, List<u1.b> list, u1.a aVar, d dVar, u1.b bVar2, LineCapType lineCapType, LineJoinType lineJoinType, float f8, boolean z7) {
        this.f2474a = str;
        this.f2475b = bVar;
        this.f2476c = list;
        this.f2477d = aVar;
        this.f2478e = dVar;
        this.f2479f = bVar2;
        this.f2480g = lineCapType;
        this.f2481h = lineJoinType;
        this.f2482i = f8;
        this.f2483j = z7;
    }

    @Override // v1.b
    public c a(g gVar, w1.a aVar) {
        return new r(gVar, aVar, this);
    }

    public LineCapType b() {
        return this.f2480g;
    }

    public u1.a c() {
        return this.f2477d;
    }

    public u1.b d() {
        return this.f2475b;
    }

    public LineJoinType e() {
        return this.f2481h;
    }

    public List<u1.b> f() {
        return this.f2476c;
    }

    public float g() {
        return this.f2482i;
    }

    public String h() {
        return this.f2474a;
    }

    public d i() {
        return this.f2478e;
    }

    public u1.b j() {
        return this.f2479f;
    }

    public boolean k() {
        return this.f2483j;
    }
}
