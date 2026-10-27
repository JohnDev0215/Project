package g3;

import e2.M;
import h2.AbstractC1343A;
import h2.t;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {
    static {
        Pattern.compile("^NOTE([ \t].*)?$");
    }

    public static float a(String str) {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }

    public static long b(String str) {
        int i7 = AbstractC1343A.f15195a;
        String[] strArrSplit = str.split("\\.", 2);
        long j3 = 0;
        for (String str2 : strArrSplit[0].split(":", -1)) {
            j3 = (j3 * 60) + Long.parseLong(str2);
        }
        long j10 = j3 * 1000;
        if (strArrSplit.length == 2) {
            j10 += Long.parseLong(strArrSplit[1]);
        }
        return j10 * 1000;
    }

    public static void c(t tVar) throws M {
        int i7 = tVar.f15242b;
        Charset charset = StandardCharsets.UTF_8;
        String strI = tVar.i(charset);
        if (strI == null || !strI.startsWith("WEBVTT")) {
            tVar.H(i7);
            throw M.a(null, "Expected WEBVTT. Got " + tVar.i(charset));
        }
    }
}
