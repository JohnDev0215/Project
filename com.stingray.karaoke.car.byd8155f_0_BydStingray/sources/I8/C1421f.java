package i8;

import java.util.HashMap;
import o8.C2057b;
import o8.C2058c;

/* JADX INFO: renamed from: i8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1421f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final boolean f15792i = "true".equals(System.getProperty("kotlin.ignore.old.metadata"));

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final HashMap f15793j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f15794a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f15795b;
    public int c;
    public String[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String[] f15796e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String[] f15797f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public EnumC1416a f15798g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String[] f15799h;

    static {
        HashMap map = new HashMap();
        f15793j = map;
        map.put(C2057b.j(new C2058c("kotlin.jvm.internal.KotlinClass")), EnumC1416a.CLASS);
        map.put(C2057b.j(new C2058c("kotlin.jvm.internal.KotlinFileFacade")), EnumC1416a.FILE_FACADE);
        map.put(C2057b.j(new C2058c("kotlin.jvm.internal.KotlinMultifileClass")), EnumC1416a.MULTIFILE_CLASS);
        map.put(C2057b.j(new C2058c("kotlin.jvm.internal.KotlinMultifileClassPart")), EnumC1416a.MULTIFILE_CLASS_PART);
        map.put(C2057b.j(new C2058c("kotlin.jvm.internal.KotlinSyntheticClass")), EnumC1416a.SYNTHETIC_CLASS);
    }
}
