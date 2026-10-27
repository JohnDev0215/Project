package defpackage;

import com.android.webview.R;

/* JADX INFO: renamed from: Jm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0174Jm {
    public static final AbstractC0102Fm a;

    static {
        a = (!(AbstractC0066Dm.h && AbstractC0066Dm.g) || AbstractC0369Uk.a()) ? new C0120Gm() : new C0156Im();
    }

    public static int a(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static int b(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        int i4 = -1;
        if (i3 == 0) {
            if (b > -12) {
                b = -1;
            }
            return b;
        }
        if (i3 != 1) {
            if (i3 == 2) {
                return f(b, bArr[i], bArr[i + 1]);
            }
            throw new AssertionError();
        }
        byte b2 = bArr[i];
        if (b <= -12 && b2 <= -65) {
            i4 = b ^ (b2 << 8);
        }
        return i4;
    }

    public static int c(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    public static int d(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return a.b(charSequence, bArr, i, i2);
    }

    public static int e(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt < 2048) {
                i3 += (127 - cCharAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char cCharAt2 = charSequence.charAt(i2);
                    if (cCharAt2 < 2048) {
                        i += (127 - cCharAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < R.anim.abc_fade_in) {
                                throw new C0138Hm(i2, length2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sbF = Z7.f("UTF-8 length does not fit in int: ");
        sbF.append(((long) i3) + 4294967296L);
        throw new IllegalArgumentException(sbF.toString());
    }

    public static int f(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }
}
