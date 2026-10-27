package androidx.constraintlayout.core.widgets.analyzer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class DependencyNode$Type {
    public static final DependencyNode$Type c;
    public static final DependencyNode$Type e;
    public static final DependencyNode$Type k;
    public static final DependencyNode$Type l;
    public static final DependencyNode$Type m;
    public static final DependencyNode$Type n;
    public static final DependencyNode$Type o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final DependencyNode$Type f13p;
    public static final /* synthetic */ DependencyNode$Type[] q;

    static {
        DependencyNode$Type dependencyNode$Type = new DependencyNode$Type("UNKNOWN", 0);
        c = dependencyNode$Type;
        DependencyNode$Type dependencyNode$Type2 = new DependencyNode$Type("HORIZONTAL_DIMENSION", 1);
        e = dependencyNode$Type2;
        DependencyNode$Type dependencyNode$Type3 = new DependencyNode$Type("VERTICAL_DIMENSION", 2);
        k = dependencyNode$Type3;
        DependencyNode$Type dependencyNode$Type4 = new DependencyNode$Type("LEFT", 3);
        l = dependencyNode$Type4;
        DependencyNode$Type dependencyNode$Type5 = new DependencyNode$Type("RIGHT", 4);
        m = dependencyNode$Type5;
        DependencyNode$Type dependencyNode$Type6 = new DependencyNode$Type("TOP", 5);
        n = dependencyNode$Type6;
        DependencyNode$Type dependencyNode$Type7 = new DependencyNode$Type("BOTTOM", 6);
        o = dependencyNode$Type7;
        DependencyNode$Type dependencyNode$Type8 = new DependencyNode$Type("BASELINE", 7);
        f13p = dependencyNode$Type8;
        q = new DependencyNode$Type[]{dependencyNode$Type, dependencyNode$Type2, dependencyNode$Type3, dependencyNode$Type4, dependencyNode$Type5, dependencyNode$Type6, dependencyNode$Type7, dependencyNode$Type8};
    }

    public static DependencyNode$Type valueOf(String str) {
        return (DependencyNode$Type) Enum.valueOf(DependencyNode$Type.class, str);
    }

    public static DependencyNode$Type[] values() {
        return (DependencyNode$Type[]) q.clone();
    }
}
