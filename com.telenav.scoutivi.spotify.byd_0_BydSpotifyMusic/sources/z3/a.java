package z3;

/* JADX INFO: compiled from: FormatUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(int i7) {
        Object objValueOf;
        Object objValueOf2;
        Object objValueOf3;
        if (i7 <= 0) {
            return "0:00";
        }
        int i8 = i7 / 1000;
        int i9 = i8 / 60;
        int i10 = i9 / 60;
        int i11 = i9 % 60;
        int i12 = i8 % 60;
        if (i10 <= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(i11);
            sb.append(":");
            if (i12 < 10) {
                objValueOf = "0" + i12;
            } else {
                objValueOf = Integer.valueOf(i12);
            }
            sb.append(objValueOf);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i10);
        sb2.append(":");
        if (i11 < 10) {
            objValueOf2 = "0" + i11;
        } else {
            objValueOf2 = Integer.valueOf(i11);
        }
        sb2.append(objValueOf2);
        sb2.append(":");
        if (i12 < 10) {
            objValueOf3 = "0" + i12;
        } else {
            objValueOf3 = Integer.valueOf(i12);
        }
        sb2.append(objValueOf3);
        return sb2.toString();
    }
}
