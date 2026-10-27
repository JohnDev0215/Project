package defpackage;

import java.text.BreakIterator;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: SelectionIndicesConverter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class di2 {
    public static final Pattern f = Pattern.compile("[\\p{javaSpaceChar}\\s]+");
    public String a;
    public int b;
    public String c;
    public int d;
    public int e;

    public final boolean a(int i, int i2, int[] iArr) {
        int i3 = 0;
        iArr[1] = 0;
        iArr[0] = 0;
        int i4 = this.b;
        int i5 = i - i4;
        int i6 = i2 - i4;
        if (i5 >= i6 || i5 < 0 || i5 >= this.a.length() || i6 <= 0 || i6 > this.a.length()) {
            return false;
        }
        int i7 = this.e - this.b;
        BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(this.a);
        if (i5 <= i7) {
            int i8 = 0;
            while (i5 < i7) {
                int iFollowing = wordInstance.following(i5);
                if (!b(i5, iFollowing)) {
                    i8++;
                }
                i5 = iFollowing;
            }
            iArr[0] = -i8;
        } else {
            int i9 = i5;
            int i10 = 0;
            while (i9 > i7) {
                int iPreceding = wordInstance.preceding(i9);
                if (!b(iPreceding, i9)) {
                    i10++;
                }
                i9 = iPreceding;
            }
            iArr[0] = i10;
            if (!wordInstance.isBoundary(i5) && !b(wordInstance.preceding(i5), wordInstance.following(i5))) {
                iArr[0] = iArr[0] - 1;
            }
        }
        if (i6 <= i7) {
            while (i6 < i7) {
                int iFollowing2 = wordInstance.following(i6);
                if (!b(i6, iFollowing2)) {
                    i3++;
                }
                i6 = iFollowing2;
            }
            iArr[1] = -i3;
        } else {
            while (i6 > i7) {
                int iPreceding2 = wordInstance.preceding(i6);
                if (!b(iPreceding2, i6)) {
                    i3++;
                }
                i6 = iPreceding2;
            }
            iArr[1] = i3;
        }
        return true;
    }

    public final boolean b(int i, int i2) {
        return f.matcher(this.a.substring(i, i2)).matches();
    }

    public final boolean c(int i, String str) {
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
            int iMax = Math.max(i2, i);
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
