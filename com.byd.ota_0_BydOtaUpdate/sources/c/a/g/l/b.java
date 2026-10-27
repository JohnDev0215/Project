package c.a.g.l;

import android.text.TextUtils;

/* JADX INFO: compiled from: ApiResponseUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str == null ? "status is null" : "status is empty";
        }
        str.hashCode();
        switch (str) {
            case "-1":
                return "Http response exception";
            case "100":
                return "Http response success!";
            case "101":
                return "Http response invalid token!";
            case "102":
                return "Http response can not update!";
            case "103":
                return "Http response params error!";
            case "104":
                return "Http response local version is the latest!";
            case "111":
                return "Http response no available version!";
            case "112":
                return "Http response already register!";
            case "113":
                return "Http response no vin code!";
            case "114":
                return "Http response not register!";
            case "115":
                return "Http response vehicle model does not exist!";
            case "116":
                return "Http response no download address!";
            case "117":
                return "Http response the vin code had bind other car,please unbind it!";
            default:
                return "the response status code is unknow";
        }
    }

    public static boolean b(String str) {
        return "117".equals(str) || "101".equals(str);
    }

    public static boolean c(String str) {
        return "104".equals(str);
    }

    public static boolean d(String str) {
        return "114".equals(str);
    }

    public static boolean e(String str) {
        return "103".equals(str);
    }

    public static boolean f(String str) {
        return "100".equals(str);
    }
}
