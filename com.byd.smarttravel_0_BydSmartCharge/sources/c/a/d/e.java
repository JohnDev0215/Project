package c.a.d;

import android.content.Context;
import android.util.Log;
import com.byd.smarttravel.BydApp;
import com.bydux.api.R;
import java.util.TimeZone;

/* JADX INFO: compiled from: DateUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static byte[] a(long j, long j2) {
        byte[] bArr = new byte[48];
        byte[] bArr2 = new byte[24];
        for (int i = 0; i < 1440; i += 30) {
            if (j < 0 || j2 < 0) {
                bArr[i / 30] = 2;
            } else {
                long j3 = i;
                if (j3 < j || j3 > j2) {
                    bArr[i / 30] = 2;
                } else {
                    bArr[i / 30] = 1;
                }
            }
        }
        for (int i2 = 0; i2 < 48; i2 += 2) {
            bArr2[i2 / 2] = (byte) ((bArr[i2 + 1] << 4) + bArr[i2]);
        }
        return bArr2;
    }

    public static long[] b(long j, long j2) {
        long j3 = ((j2 - j) / 1000) / 60;
        long j4 = (j3 / 60) / 24;
        return new long[]{j4, (j3 - ((j4 * 60) * 24)) / 60, j3 % 60};
    }

    public static String c(int i, int i2) {
        StringBuilder sb;
        StringBuilder sb2;
        int i3 = i2 % 5;
        if (i3 != 0) {
            Log.e("----", i + ":" + i2 + ",startTime:" + i.d(BydApp.a(), "KEY_CHARGE_START_TIME") + ",endTime:" + i.d(BydApp.a(), "KEY_CHARGE_END_TIME") + ",TimeZone:" + TimeZone.getDefault().getID());
            i2 -= i3;
        }
        StringBuilder sb3 = new StringBuilder();
        if (i > 9) {
            sb = new StringBuilder();
            sb.append("");
        } else {
            sb = new StringBuilder();
            sb.append("0");
        }
        sb.append(i);
        sb3.append(sb.toString());
        sb3.append(":");
        if (i2 > 9) {
            sb2 = new StringBuilder();
            sb2.append("");
        } else {
            sb2 = new StringBuilder();
            sb2.append("0");
        }
        sb2.append(i2);
        sb3.append(sb2.toString());
        return sb3.toString();
    }

    public static String d(long j, long j2) {
        StringBuilder sb;
        StringBuilder sb2;
        long j3 = j2 % 5;
        if (j3 != 0) {
            Log.e("----", "getTimeHM is error minute = " + j2);
            j2 -= j3;
        }
        StringBuilder sb3 = new StringBuilder();
        if (j > 9) {
            sb = new StringBuilder();
            sb.append("");
        } else {
            sb = new StringBuilder();
            sb.append("0");
        }
        sb.append(j);
        sb3.append(sb.toString());
        sb3.append(":");
        if (j2 > 9) {
            sb2 = new StringBuilder();
            sb2.append("");
        } else {
            sb2 = new StringBuilder();
            sb2.append("0");
        }
        sb2.append(j2);
        sb3.append(sb2.toString());
        return sb3.toString();
    }

    public static String e(Context context, String str) {
        int[] iArr = {R.string.sunday, R.string.monday, R.string.tuesday, R.string.wednesday, R.string.thursday, R.string.friday, R.string.saturday};
        String[] strArrSplit = str.split("\\|");
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            if ("1".equals(strArrSplit[i2])) {
                i++;
                StringBuilder sb = new StringBuilder();
                sb.append(i == 1 ? "" : "        ");
                sb.append(context.getResources().getString(iArr[i2]));
                stringBuffer.append(sb.toString());
            }
        }
        return i == 0 ? context.getResources().getString(R.string.ont_shot) : i == 7 ? context.getResources().getString(R.string.every_day) : "0|1|1|1|1|1|0".equals(str) ? context.getResources().getString(R.string.mon_to_fri) : "0|1|1|1|1|1|1".equals(str) ? context.getResources().getString(R.string.mon_to_sat) : stringBuffer.toString();
    }

    public static String f(int i) {
        if (i > 9) {
            return i + "";
        }
        return "0" + i;
    }

    public static int g(String str) {
        String[] strArrSplit = str.split("\\|");
        int i = 0;
        for (String str2 : strArrSplit) {
            if ("1".equals(str2)) {
                i++;
            }
        }
        if (i == 0) {
            return 0;
        }
        if (i == 7) {
            return 1;
        }
        if (str.contains("1|1")) {
            return 3;
        }
        return ("1".equals(strArrSplit[0]) && "1".equals(strArrSplit[6])) ? 3 : 2;
    }
}
