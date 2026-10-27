package b.g.i;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final d f2250a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2251b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f2252c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final a f2253d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final a f2254e;
    private final boolean f;
    private final int g;
    private final d h;

    /* JADX INFO: renamed from: b.g.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class C0049a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2255a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2256b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f2257c;

        public C0049a() {
            c(a.e(Locale.getDefault()));
        }

        private static a b(boolean z) {
            return z ? a.f2254e : a.f2253d;
        }

        private void c(boolean z) {
            this.f2255a = z;
            this.f2257c = a.f2250a;
            this.f2256b = 2;
        }

        public a a() {
            return (this.f2256b == 2 && this.f2257c == a.f2250a) ? b(this.f2255a) : new a(this.f2255a, this.f2256b, this.f2257c);
        }
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final byte[] f2258a = new byte[1792];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final CharSequence f2259b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f2260c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f2261d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f2262e;
        private char f;

        static {
            for (int i = 0; i < 1792; i++) {
                f2258a[i] = Character.getDirectionality(i);
            }
        }

        b(CharSequence charSequence, boolean z) {
            this.f2259b = charSequence;
            this.f2260c = z;
            this.f2261d = charSequence.length();
        }

        private static byte c(char c2) {
            return c2 < 1792 ? f2258a[c2] : Character.getDirectionality(c2);
        }

        private byte f() {
            char cCharAt;
            int i = this.f2262e;
            do {
                int i2 = this.f2262e;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f2259b;
                int i3 = i2 - 1;
                this.f2262e = i3;
                cCharAt = charSequence.charAt(i3);
                this.f = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.f2262e = i;
            this.f = ';';
            return (byte) 13;
        }

        private byte g() {
            char cCharAt;
            do {
                int i = this.f2262e;
                if (i >= this.f2261d) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f2259b;
                this.f2262e = i + 1;
                cCharAt = charSequence.charAt(i);
                this.f = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        private byte h() {
            char cCharAt;
            int i = this.f2262e;
            while (true) {
                int i2 = this.f2262e;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f2259b;
                int i3 = i2 - 1;
                this.f2262e = i3;
                char cCharAt2 = charSequence.charAt(i3);
                this.f = cCharAt2;
                if (cCharAt2 == '<') {
                    return (byte) 12;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i4 = this.f2262e;
                        if (i4 > 0) {
                            CharSequence charSequence2 = this.f2259b;
                            int i5 = i4 - 1;
                            this.f2262e = i5;
                            cCharAt = charSequence2.charAt(i5);
                            this.f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.f2262e = i;
            this.f = '>';
            return (byte) 13;
        }

        private byte i() {
            char cCharAt;
            int i = this.f2262e;
            while (true) {
                int i2 = this.f2262e;
                if (i2 >= this.f2261d) {
                    this.f2262e = i;
                    this.f = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f2259b;
                this.f2262e = i2 + 1;
                char cCharAt2 = charSequence.charAt(i2);
                this.f = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i3 = this.f2262e;
                        if (i3 < this.f2261d) {
                            CharSequence charSequence2 = this.f2259b;
                            this.f2262e = i3 + 1;
                            cCharAt = charSequence2.charAt(i3);
                            this.f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }

        byte a() {
            char cCharAt = this.f2259b.charAt(this.f2262e - 1);
            this.f = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f2259b, this.f2262e);
                this.f2262e -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.f2262e--;
            byte bC = c(this.f);
            if (!this.f2260c) {
                return bC;
            }
            char c2 = this.f;
            return c2 == '>' ? h() : c2 == ';' ? f() : bC;
        }

        byte b() {
            char cCharAt = this.f2259b.charAt(this.f2262e);
            this.f = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f2259b, this.f2262e);
                this.f2262e += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.f2262e++;
            byte bC = c(this.f);
            if (!this.f2260c) {
                return bC;
            }
            char c2 = this.f;
            return c2 == '<' ? i() : c2 == '&' ? g() : bC;
        }

        int d() {
            this.f2262e = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.f2262e < this.f2261d && i == 0) {
                byte b2 = b();
                if (b2 != 0) {
                    if (b2 == 1 || b2 == 2) {
                        if (i3 == 0) {
                            return 1;
                        }
                    } else if (b2 != 9) {
                        switch (b2) {
                            case 14:
                            case 15:
                                i3++;
                                i2 = -1;
                                continue;
                            case 16:
                            case 17:
                                i3++;
                                i2 = 1;
                                continue;
                            case 18:
                                i3--;
                                i2 = 0;
                                continue;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
                i = i3;
            }
            if (i == 0) {
                return 0;
            }
            if (i2 != 0) {
                return i2;
            }
            while (this.f2262e > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i == i3) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i == i3) {
                            return 1;
                        }
                        break;
                    case 18:
                        i3++;
                        continue;
                }
                i3--;
            }
            return 0;
        }

        int e() {
            this.f2262e = this.f2261d;
            int i = 0;
            while (true) {
                int i2 = i;
                while (this.f2262e > 0) {
                    byte bA = a();
                    if (bA == 0) {
                        if (i == 0) {
                            return -1;
                        }
                        if (i2 == 0) {
                            break;
                        }
                    } else if (bA == 1 || bA == 2) {
                        if (i == 0) {
                            return 1;
                        }
                        if (i2 == 0) {
                            break;
                        }
                    } else if (bA != 9) {
                        switch (bA) {
                            case 14:
                            case 15:
                                if (i2 == i) {
                                    return -1;
                                }
                                i--;
                                break;
                            case 16:
                            case 17:
                                if (i2 == i) {
                                    return 1;
                                }
                                i--;
                                break;
                            case 18:
                                i++;
                                break;
                            default:
                                if (i2 != 0) {
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
    }

    static {
        d dVar = e.f2280c;
        f2250a = dVar;
        f2251b = Character.toString((char) 8206);
        f2252c = Character.toString((char) 8207);
        f2253d = new a(false, 2, dVar);
        f2254e = new a(true, 2, dVar);
    }

    a(boolean z, int i, d dVar) {
        this.f = z;
        this.g = i;
        this.h = dVar;
    }

    private static int a(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    private static int b(CharSequence charSequence) {
        return new b(charSequence, false).e();
    }

    public static a c() {
        return new C0049a().a();
    }

    static boolean e(Locale locale) {
        return f.b(locale) == 1;
    }

    private String f(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f || !(zA || b(charSequence) == 1)) ? this.f ? (!zA || b(charSequence) == -1) ? f2252c : "" : "" : f2251b;
    }

    private String g(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f || !(zA || a(charSequence) == 1)) ? this.f ? (!zA || a(charSequence) == -1) ? f2252c : "" : "" : f2251b;
    }

    public boolean d() {
        return (this.g & 2) != 0;
    }

    public CharSequence h(CharSequence charSequence) {
        return i(charSequence, this.h, true);
    }

    public CharSequence i(CharSequence charSequence, d dVar, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (d() && z) {
            spannableStringBuilder.append((CharSequence) g(charSequence, zA ? e.f2279b : e.f2278a));
        }
        if (zA != this.f) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) f(charSequence, zA ? e.f2279b : e.f2278a));
        }
        return spannableStringBuilder;
    }

    public String j(String str) {
        return k(str, this.h, true);
    }

    public String k(String str, d dVar, boolean z) {
        if (str == null) {
            return null;
        }
        return i(str, dVar, z).toString();
    }
}
