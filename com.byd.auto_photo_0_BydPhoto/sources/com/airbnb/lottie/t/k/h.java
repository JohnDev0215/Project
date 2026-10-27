package com.airbnb.lottie.t.k;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class h implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f833a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f834b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f835c;

    public enum a {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static a a(int i) {
            return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? MERGE : EXCLUDE_INTERSECTIONS : INTERSECT : SUBTRACT : ADD : MERGE;
        }
    }

    public h(String str, a aVar, boolean z) {
        this.f833a = str;
        this.f834b = aVar;
        this.f835c = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    @Nullable
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        if (fVar.h()) {
            return new com.airbnb.lottie.r.b.l(this);
        }
        com.airbnb.lottie.w.d.b("Animation contains merge paths but they are disabled.");
        return null;
    }

    public a b() {
        return this.f834b;
    }

    public String c() {
        return this.f833a;
    }

    public boolean d() {
        return this.f835c;
    }

    public String toString() {
        return "MergePaths{mode=" + this.f834b + '}';
    }
}
