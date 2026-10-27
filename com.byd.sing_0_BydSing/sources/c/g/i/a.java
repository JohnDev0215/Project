package c.g.i;

import android.text.SpannableStringBuilder;
import com.android.volley.BuildConfig;
import java.util.Locale;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final d f2232a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2233b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f2234c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final a f2235d;
    static final a e;
    private final boolean f;
    private final int g;
    private final d h;

    /* JADX INFO: renamed from: c.g.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class C0062a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2236a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2237b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f2238c;

        public C0062a() {
            c(a.e(Locale.getDefault()));
        }

        private static a b(boolean z) {
            return z ? a.e : a.f2235d;
        }

        private void c(boolean z) {
            this.f2236a = z;
            this.f2238c = a.f2232a;
            this.f2237b = 2;
        }

        public a a() {
            return (this.f2237b == 2 && this.f2238c == a.f2232a) ? b(this.f2236a) : new a(this.f2236a, this.f2237b, this.f2238c);
        }
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final byte[] f2239a = new byte[1792];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final CharSequence f2240b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f2241c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f2242d;
        private int e;
        private char f;

        static {
            for (int i = 0; i < 1792; i++) {
                f2239a[i] = Character.getDirectionality(i);
            }
        }

        b(CharSequence charSequence, boolean z) {
            this.f2240b = charSequence;
            this.f2241c = z;
            this.f2242d = charSequence.length();
        }

        private static byte c(char c2) {
            return c2 < 1792 ? f2239a[c2] : Character.getDirectionality(c2);
        }

        private byte f() {
            char cCharAt;
            int i = this.e;
            do {
                int i2 = this.e;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f2240b;
                int i3 = i2 - 1;
                this.e = i3;
                cCharAt = charSequence.charAt(i3);
                this.f = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.e = i;
            this.f = ';';
            return (byte) 13;
        }

        private byte g() {
            char cCharAt;
            do {
                int i = this.e;
                if (i >= this.f2242d) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f2240b;
                this.e = i + 1;
                cCharAt = charSequence.charAt(i);
                this.f = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        private byte h() {
            char cCharAt;
            int i = this.e;
            while (true) {
                int i2 = this.e;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f2240b;
                int i3 = i2 - 1;
                this.e = i3;
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
                        int i4 = this.e;
                        if (i4 > 0) {
                            CharSequence charSequence2 = this.f2240b;
                            int i5 = i4 - 1;
                            this.e = i5;
                            cCharAt = charSequence2.charAt(i5);
                            this.f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.e = i;
            this.f = '>';
            return (byte) 13;
        }

        private byte i() {
            char cCharAt;
            int i = this.e;
            while (true) {
                int i2 = this.e;
                if (i2 >= this.f2242d) {
                    this.e = i;
                    this.f = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f2240b;
                this.e = i2 + 1;
                char cCharAt2 = charSequence.charAt(i2);
                this.f = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i3 = this.e;
                        if (i3 < this.f2242d) {
                            CharSequence charSequence2 = this.f2240b;
                            this.e = i3 + 1;
                            cCharAt = charSequence2.charAt(i3);
                            this.f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }

        byte a() {
            char cCharAt = this.f2240b.charAt(this.e - 1);
            this.f = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f2240b, this.e);
                this.e -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.e--;
            byte bC = c(this.f);
            if (!this.f2241c) {
                return bC;
            }
            char c2 = this.f;
            return c2 == '>' ? h() : c2 == ';' ? f() : bC;
        }

        byte b() {
            char cCharAt = this.f2240b.charAt(this.e);
            this.f = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f2240b, this.e);
                this.e += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.e++;
            byte bC = c(this.f);
            if (!this.f2241c) {
                return bC;
            }
            char c2 = this.f;
            return c2 == '<' ? i() : c2 == '&' ? g() : bC;
        }

        int d() {
            this.e = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.e < this.f2242d && i == 0) {
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
            while (this.e > 0) {
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
            this.e = this.f2242d;
            int i = 0;
            while (true) {
                int i2 = i;
                while (this.e > 0) {
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
        d dVar = e.f2258c;
        f2232a = dVar;
        f2233b = Character.toString((char) 8206);
        f2234c = Character.toString((char) 8207);
        f2235d = new a(false, 2, dVar);
        e = new a(true, 2, dVar);
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
        return new C0062a().a();
    }

    static boolean e(Locale locale) {
        return f.b(locale) == 1;
    }

    private String f(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f || !(zA || b(charSequence) == 1)) ? this.f ? (!zA || b(charSequence) == -1) ? f2234c : BuildConfig.FLAVOR : BuildConfig.FLAVOR : f2233b;
    }

    private String g(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f || !(zA || a(charSequence) == 1)) ? this.f ? (!zA || a(charSequence) == -1) ? f2234c : BuildConfig.FLAVOR : BuildConfig.FLAVOR : f2233b;
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
            spannableStringBuilder.append((CharSequence) g(charSequence, zA ? e.f2257b : e.f2256a));
        }
        if (zA != this.f) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) f(charSequence, zA ? e.f2257b : e.f2256a));
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
