package B2;

import h2.AbstractC1343A;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class z {
    public static final Pattern c = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f613a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f614b = -1;

    public final boolean a(String str) {
        Matcher matcher = c.matcher(str);
        if (!matcher.find()) {
            return false;
        }
        try {
            String strGroup = matcher.group(1);
            int i7 = AbstractC1343A.f15195a;
            int i10 = Integer.parseInt(strGroup, 16);
            int i11 = Integer.parseInt(matcher.group(2), 16);
            if (i10 <= 0 && i11 <= 0) {
                return false;
            }
            this.f613a = i10;
            this.f614b = i11;
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public final void b(e2.K k10) {
        int i7 = 0;
        while (true) {
            e2.J[] jArr = k10.l;
            if (i7 >= jArr.length) {
                return;
            }
            e2.J j3 = jArr[i7];
            if (j3 instanceof P2.e) {
                P2.e eVar = (P2.e) j3;
                if ("iTunSMPB".equals(eVar.f5310n) && a(eVar.o)) {
                    return;
                }
            } else if (j3 instanceof P2.k) {
                P2.k kVar = (P2.k) j3;
                if ("com.apple.iTunes".equals(kVar.f5318m) && "iTunSMPB".equals(kVar.f5319n) && a(kVar.o)) {
                    return;
                }
            } else {
                continue;
            }
            i7++;
        }
    }
}
