package coil.size;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Scale {
    public static final Scale c;
    public static final Scale e;
    public static final /* synthetic */ Scale[] k;

    static {
        Scale scale = new Scale("FILL", 0);
        c = scale;
        Scale scale2 = new Scale("FIT", 1);
        e = scale2;
        Scale[] scaleArr = {scale, scale2};
        k = scaleArr;
        kotlin.enums.a.a(scaleArr);
    }

    public static Scale valueOf(String str) {
        return (Scale) Enum.valueOf(Scale.class, str);
    }

    public static Scale[] values() {
        return (Scale[]) k.clone();
    }
}
