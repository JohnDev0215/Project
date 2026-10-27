package coil.decode;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ExifOrientationPolicy {
    public static final ExifOrientationPolicy c;
    public static final /* synthetic */ ExifOrientationPolicy[] e;

    static {
        ExifOrientationPolicy exifOrientationPolicy = new ExifOrientationPolicy("IGNORE", 0);
        ExifOrientationPolicy exifOrientationPolicy2 = new ExifOrientationPolicy("RESPECT_PERFORMANCE", 1);
        c = exifOrientationPolicy2;
        ExifOrientationPolicy[] exifOrientationPolicyArr = {exifOrientationPolicy, exifOrientationPolicy2, new ExifOrientationPolicy("RESPECT_ALL", 2)};
        e = exifOrientationPolicyArr;
        kotlin.enums.a.a(exifOrientationPolicyArr);
    }

    public static ExifOrientationPolicy valueOf(String str) {
        return (ExifOrientationPolicy) Enum.valueOf(ExifOrientationPolicy.class, str);
    }

    public static ExifOrientationPolicy[] values() {
        return (ExifOrientationPolicy[]) e.clone();
    }
}
