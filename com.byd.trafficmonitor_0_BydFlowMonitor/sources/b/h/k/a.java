package b.h.k;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f2530a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2531b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f2532c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f2533d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f2534e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2535f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2536g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f2537h;

    /* JADX INFO: renamed from: b.h.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class C0039a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2538a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2539b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f2540c;

        public C0039a() {
            c(a.e(Locale.getDefault()));
        }

        public static a b(boolean z) {
            return z ? a.f2534e : a.f2533d;
        }

        public a a() {
            return (this.f2539b == 2 && this.f2540c == a.f2530a) ? b(this.f2538a) : new a(this.f2538a, this.f2539b, this.f2540c);
        }

        public final void c(boolean z) {
            this.f2538a = z;
            this.f2540c = a.f2530a;
            this.f2539b = 2;
        }
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final byte[] f2541a = new byte[1792];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final CharSequence f2542b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f2543c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f2544d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2545e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public char f2546f;

        static {
            for (int i2 = 0; i2 < 1792; i2++) {
                f2541a[i2] = Character.getDirectionality(i2);
            }
        }

        public b(CharSequence charSequence, boolean z) {
            this.f2542b = charSequence;
            this.f2543c = z;
            this.f2544d = charSequence.length();
        }

        public static byte c(char c2) {
            return c2 < 1792 ? f2541a[c2] : Character.getDirectionality(c2);
        }

        public byte a() {
            char cCharAt = this.f2542b.charAt(this.f2545e - 1);
            this.f2546f = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f2542b, this.f2545e);
                this.f2545e -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.f2545e--;
            byte bC = c(this.f2546f);
            if (!this.f2543c) {
                return bC;
            }
            char c2 = this.f2546f;
            return c2 == '>' ? h() : c2 == ';' ? f() : bC;
        }

        public byte b() {
            char cCharAt = this.f2542b.charAt(this.f2545e);
            this.f2546f = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f2542b, this.f2545e);
                this.f2545e += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.f2545e++;
            byte bC = c(this.f2546f);
            if (!this.f2543c) {
                return bC;
            }
            char c2 = this.f2546f;
            return c2 == '<' ? i() : c2 == '&' ? g() : bC;
        }

        public int d() {
            this.f2545e = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (this.f2545e < this.f2544d && i2 == 0) {
                byte b2 = b();
                if (b2 != 0) {
                    if (b2 == 1 || b2 == 2) {
                        if (i4 == 0) {
                            return 1;
                        }
                    } else if (b2 != 9) {
                        switch (b2) {
                            case 14:
                            case 15:
                                i4++;
                                i3 = -1;
                                continue;
                            case 16:
                            case 17:
                                i4++;
                                i3 = 1;
                                continue;
                            case 18:
                                i4--;
                                i3 = 0;
                                continue;
                        }
                    }
                } else if (i4 == 0) {
                    return -1;
                }
                i2 = i4;
            }
            if (i2 == 0) {
                return 0;
            }
            if (i3 != 0) {
                return i3;
            }
            while (this.f2545e > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i2 == i4) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i2 == i4) {
                            return 1;
                        }
                        break;
                    case 18:
                        i4++;
                        continue;
                }
                i4--;
            }
            return 0;
        }

        public int e() {
            this.f2545e = this.f2544d;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                while (this.f2545e > 0) {
                    byte bA = a();
                    if (bA == 0) {
                        if (i2 == 0) {
                            return -1;
                        }
                        if (i3 == 0) {
                            break;
                        }
                    } else if (bA == 1 || bA == 2) {
                        if (i2 == 0) {
                            return 1;
                        }
                        if (i3 == 0) {
                            break;
                        }
                    } else if (bA != 9) {
                        switch (bA) {
                            case 14:
                            case 15:
                                if (i3 == i2) {
                                    return -1;
                                }
                                i2--;
                                break;
                            case 16:
                            case 17:
                                if (i3 == i2) {
                                    return 1;
                                }
                                i2--;
                                break;
                            case 18:
                                i2++;
                                break;
                            default:
                                if (i3 != 0) {
                                }
                                break;
                        }
                    } else {
                        continue;
                    }
                }
                return 0;
            }
        }

        public final byte f() {
            char cCharAt;
            int i2 = this.f2545e;
            do {
                int i3 = this.f2545e;
                if (i3 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f2542b;
                int i4 = i3 - 1;
                this.f2545e = i4;
                cCharAt = charSequence.charAt(i4);
                this.f2546f = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.f2545e = i2;
            this.f2546f = ';';
            return (byte) 13;
        }

        public final byte g() {
            char cCharAt;
            do {
                int i2 = this.f2545e;
                if (i2 >= this.f2544d) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f2542b;
                this.f2545e = i2 + 1;
                cCharAt = charSequence.charAt(i2);
                this.f2546f = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        public final byte h() {
            char cCharAt;
            int i2 = this.f2545e;
            while (true) {
                int i3 = this.f2545e;
                if (i3 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f2542b;
                int i4 = i3 - 1;
                this.f2545e = i4;
                char cCharAt2 = charSequence.charAt(i4);
                this.f2546f = cCharAt2;
                if (cCharAt2 == '<') {
                    return (byte) 12;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i5 = this.f2545e;
                        if (i5 > 0) {
                            CharSequence charSequence2 = this.f2542b;
                            int i6 = i5 - 1;
                            this.f2545e = i6;
                            cCharAt = charSequence2.charAt(i6);
                            this.f2546f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.f2545e = i2;
            this.f2546f = '>';
            return (byte) 13;
        }

        public final byte i() {
            char cCharAt;
            int i2 = this.f2545e;
            while (true) {
                int i3 = this.f2545e;
                if (i3 >= this.f2544d) {
                    this.f2545e = i2;
                    this.f2546f = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f2542b;
                this.f2545e = i3 + 1;
                char cCharAt2 = charSequence.charAt(i3);
                this.f2546f = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i4 = this.f2545e;
                        if (i4 < this.f2544d) {
                            CharSequence charSequence2 = this.f2542b;
                            this.f2545e = i4 + 1;
                            cCharAt = charSequence2.charAt(i4);
                            this.f2546f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }
    }

    static {
        d dVar = e.f2564c;
        f2530a = dVar;
        f2531b = Character.toString((char) 8206);
        f2532c = Character.toString((char) 8207);
        f2533d = new a(false, 2, dVar);
        f2534e = new a(true, 2, dVar);
    }

    public a(boolean z, int i2, d dVar) {
        this.f2535f = z;
        this.f2536g = i2;
        this.f2537h = dVar;
    }

    public static int a(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    public static int b(CharSequence charSequence) {
        return new b(charSequence, false).e();
    }

    public static a c() {
        return new C0039a().a();
    }

    public static boolean e(Locale locale) {
        return f.b(locale) == 1;
    }

    public boolean d() {
        return (this.f2536g & 2) != 0;
    }

    public final String f(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f2535f || !(zA || b(charSequence) == 1)) ? this.f2535f ? (!zA || b(charSequence) == -1) ? f2532c : "" : "" : f2531b;
    }

    public final String g(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f2535f || !(zA || a(charSequence) == 1)) ? this.f2535f ? (!zA || a(charSequence) == -1) ? f2532c : "" : "" : f2531b;
    }

    public CharSequence h(CharSequence charSequence) {
        return i(charSequence, this.f2537h, true);
    }

    public CharSequence i(CharSequence charSequence, d dVar, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (d() && z) {
            spannableStringBuilder.append((CharSequence) g(charSequence, zA ? e.f2563b : e.f2562a));
        }
        if (zA != this.f2535f) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) f(charSequence, zA ? e.f2563b : e.f2562a));
        }
        return spannableStringBuilder;
    }

    public String j(String str) {
        return k(str, this.f2537h, true);
    }

    public String k(String str, d dVar, boolean z) {
        if (str == null) {
            return null;
        }
        return i(str, dVar, z).toString();
    }
}
