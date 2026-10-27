package i8;

import java.util.LinkedHashMap;
import n7.z;

/* JADX INFO: renamed from: i8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1416a {
    UNKNOWN(0),
    CLASS(1),
    FILE_FACADE(2),
    SYNTHETIC_CLASS(3),
    MULTIFILE_CLASS(4),
    MULTIFILE_CLASS_PART(5);


    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final LinkedHashMap f15776m;
    public final int l;

    static {
        EnumC1416a[] enumC1416aArrValues = values();
        int iQ = z.q(enumC1416aArrValues.length);
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ < 16 ? 16 : iQ);
        for (EnumC1416a enumC1416a : enumC1416aArrValues) {
            linkedHashMap.put(Integer.valueOf(enumC1416a.l), enumC1416a);
        }
        f15776m = linkedHashMap;
    }

    EnumC1416a(int i7) {
        this.l = i7;
    }
}
