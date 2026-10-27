package coil.size;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Precision {
    public static final Precision c;
    public static final Precision e;
    public static final /* synthetic */ Precision[] k;

    static {
        Precision precision = new Precision("EXACT", 0);
        Precision precision2 = new Precision("INEXACT", 1);
        c = precision2;
        Precision precision3 = new Precision("AUTOMATIC", 2);
        e = precision3;
        Precision[] precisionArr = {precision, precision2, precision3};
        k = precisionArr;
        kotlin.enums.a.a(precisionArr);
    }

    public static Precision valueOf(String str) {
        return (Precision) Enum.valueOf(Precision.class, str);
    }

    public static Precision[] values() {
        return (Precision[]) k.clone();
    }
}
