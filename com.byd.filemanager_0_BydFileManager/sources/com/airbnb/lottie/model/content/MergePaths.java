package com.airbnb.lottie.model.content;

import com.airbnb.lottie.r.b.l;

/* JADX INFO: loaded from: classes.dex */
public class MergePaths implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2534a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final MergePathsMode f2535b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f2536c;

    public enum MergePathsMode {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static MergePathsMode forId(int i) {
            return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? MERGE : EXCLUDE_INTERSECTIONS : INTERSECT : SUBTRACT : ADD : MERGE;
        }
    }

    public MergePaths(String str, MergePathsMode mergePathsMode, boolean z) {
        this.f2534a = str;
        this.f2535b = mergePathsMode;
        this.f2536c = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        if (fVar.k()) {
            return new l(this);
        }
        com.airbnb.lottie.u.d.c("Animation contains merge paths but they are disabled.");
        return null;
    }

    public MergePathsMode b() {
        return this.f2535b;
    }

    public String c() {
        return this.f2534a;
    }

    public boolean d() {
        return this.f2536c;
    }

    public String toString() {
        return "MergePaths{mode=" + this.f2535b + '}';
    }
}
