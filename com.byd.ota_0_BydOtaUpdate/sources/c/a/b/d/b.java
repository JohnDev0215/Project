package c.a.b.d;

/* JADX INFO: compiled from: AutoTypeUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2331a = "b";

    public static String a(int i2) {
        e.c(f2331a, "carType = " + i2);
        return i2 != 182 ? i2 != 196 ? "undefined" : "STEBU" : "UKE";
    }

    public static boolean b(int i2) {
        e.c(f2331a, "carType = " + i2);
        return i2 == 182 || i2 == 196;
    }

    public static boolean c(int i2) {
        e.c(f2331a, "carType = " + i2);
        switch (i2) {
            case 15:
            case 31:
            case 35:
            case 43:
            case 46:
            case 47:
            case 48:
            case 50:
            case 52:
            case 56:
            case 57:
            case 61:
            case 63:
            case 65:
            case 67:
            case 71:
            case 74:
            case 83:
            case 85:
            case 88:
            case 89:
            case 90:
            case 102:
            case 111:
            case 113:
            case 115:
            case 121:
            case 129:
            case 136:
            case 140:
            case 141:
                return true;
            default:
                return false;
        }
    }
}
