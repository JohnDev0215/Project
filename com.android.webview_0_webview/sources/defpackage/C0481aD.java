package defpackage;

import java.text.BreakIterator;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: aD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0481aD {
    public static final Pattern f = Pattern.compile("[\\p{javaSpaceChar}\\s]+");
    public String a;
    public int b;
    public String c;
    public int d;
    public int e;

    public int a(int i, int i2, BreakIterator breakIterator) {
        int i3 = 0;
        while (i > i2) {
            int iPreceding = breakIterator.preceding(i);
            if (!d(iPreceding, i)) {
                i3++;
            }
            i = iPreceding;
        }
        return i3;
    }

    public int b(int i, int i2, BreakIterator breakIterator) {
        int i3 = 0;
        while (i < i2) {
            int iFollowing = breakIterator.following(i);
            if (!d(i, iFollowing)) {
                i3++;
            }
            i = iFollowing;
        }
        return i3;
    }

    public boolean c(int i, int i2, int[] iArr) {
        iArr[1] = 0;
        iArr[0] = 0;
        int i3 = this.b;
        int i4 = i - i3;
        int i5 = i2 - i3;
        if (i4 >= i5 || i4 < 0 || i4 >= this.a.length() || i5 <= 0 || i5 > this.a.length()) {
            return false;
        }
        int i6 = this.e - this.b;
        BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(this.a);
        if (i4 <= i6) {
            iArr[0] = -b(i4, i6, wordInstance);
        } else {
            iArr[0] = a(i4, i6, wordInstance);
            if (!wordInstance.isBoundary(i4) && !d(wordInstance.preceding(i4), wordInstance.following(i4))) {
                iArr[0] = iArr[0] - 1;
            }
        }
        if (i5 <= i6) {
            iArr[1] = -b(i5, i6, wordInstance);
        } else {
            iArr[1] = a(i5, i6, wordInstance);
        }
        return true;
    }

    public boolean d(int i, int i2) {
        return f.matcher(this.a.substring(i, i2)).matches();
    }

    public boolean e(String str, int i) {
        boolean zRegionMatches;
        if (this.a == null) {
            this.c = str;
            this.d = i;
            this.a = str;
            this.b = i;
            return true;
        }
        int length = str.length() + i;
        int length2 = this.c.length() + this.d;
        int i2 = this.d;
        if (i2 > i ? length > i2 : i < length2) {
            int iMax = Math.max(this.d, i);
            zRegionMatches = this.c.regionMatches(iMax - this.d, str, iMax - i, Math.min(length2, length) - iMax);
        } else {
            zRegionMatches = false;
        }
        if (this.d == length || length2 == i) {
            zRegionMatches = true;
        }
        if (!zRegionMatches) {
            this.a = null;
            this.c = null;
            return false;
        }
        this.c = str;
        this.d = i;
        int length3 = str.length() + i;
        int length4 = this.a.length() + this.b;
        if (i < this.b) {
            this.a = str.substring(0, this.b - i) + this.a;
            this.b = i;
        }
        if (length3 > length4) {
            String str2 = this.a + str.substring(length4 - i, str.length());
            int i3 = this.b;
            this.a = str2;
            this.b = i3;
        }
        return true;
    }
}
