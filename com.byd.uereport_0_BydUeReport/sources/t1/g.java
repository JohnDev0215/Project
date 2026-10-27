package t1;

/* JADX INFO: compiled from: Marker.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f8012a = "\r";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f8013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f8014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f8015d;

    public g(String str, float f8, float f9) {
        this.f8013b = str;
        this.f8015d = f9;
        this.f8014c = f8;
    }

    public boolean a(String str) {
        if (this.f8013b.equalsIgnoreCase(str)) {
            return true;
        }
        if (this.f8013b.endsWith(f8012a)) {
            String str2 = this.f8013b;
            if (str2.substring(0, str2.length() - 1).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
