package ts.car.diag.d;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class a {
    public static ArrayList<Byte> a(String str) {
        return b((byte) 1, (byte) 1, str);
    }

    public static ArrayList<Byte> b(byte b2, byte b3, String str) {
        ArrayList<Byte> arrayList = new ArrayList<>();
        arrayList.add(Byte.valueOf(b2));
        arrayList.add(Byte.valueOf(b3));
        if (str == null) {
            str = "                 ";
        }
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        for (byte b4 : bytes) {
            arrayList.add(Byte.valueOf(b4));
        }
        return arrayList;
    }
}
