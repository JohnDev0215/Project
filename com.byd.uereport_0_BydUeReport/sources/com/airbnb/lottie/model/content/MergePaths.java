package com.airbnb.lottie.model.content;

import a2.d;
import o1.g;
import q1.c;
import q1.l;
import v1.b;
import w1.a;

/* JADX INFO: loaded from: classes.dex */
public class MergePaths implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2461a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MergePathsMode f2462b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f2463c;

    public enum MergePathsMode {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static MergePathsMode forId(int i8) {
            return i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? i8 != 5 ? MERGE : EXCLUDE_INTERSECTIONS : INTERSECT : SUBTRACT : ADD : MERGE;
        }
    }

    public MergePaths(String str, MergePathsMode mergePathsMode, boolean z7) {
        this.f2461a = str;
        this.f2462b = mergePathsMode;
        this.f2463c = z7;
    }

    @Override // v1.b
    public c a(g gVar, a aVar) {
        if (gVar.k()) {
            return new l(this);
        }
        d.c("Animation contains merge paths but they are disabled.");
        return null;
    }

    public MergePathsMode b() {
        return this.f2462b;
    }

    public String c() {
        return this.f2461a;
    }

    public boolean d() {
        return this.f2463c;
    }

    public String toString() {
        return "MergePaths{mode=" + this.f2462b + '}';
    }
}
