package j0;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f3394a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3395b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3396c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f3397d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f3398e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f3399f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3400g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f3401h;

    /* JADX INFO: renamed from: j0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class C0024a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3402a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3403b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f3404c;

        public C0024a() {
            c(a.e(Locale.getDefault()));
        }

        public static a b(boolean z3) {
            return z3 ? a.f3398e : a.f3397d;
        }

        public a a() {
            return (this.f3403b == 2 && this.f3404c == a.f3394a) ? b(this.f3402a) : new a(this.f3402a, this.f3403b, this.f3404c);
        }

        public final void c(boolean z3) {
            this.f3402a = z3;
            this.f3404c = a.f3394a;
            this.f3403b = 2;
        }
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final byte[] f3405a = new byte[1792];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final CharSequence f3406b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f3407c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f3408d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3409e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public char f3410f;

        static {
            for (int i3 = 0; i3 < 1792; i3++) {
                f3405a[i3] = Character.getDirectionality(i3);
            }
        }

        public b(CharSequence charSequence, boolean z3) {
            this.f3406b = charSequence;
            this.f3407c = z3;
            this.f3408d = charSequence.length();
        }

        public static byte c(char c3) {
            return c3 < 1792 ? f3405a[c3] : Character.getDirectionality(c3);
        }

        public byte a() {
            char cCharAt = this.f3406b.charAt(this.f3409e - 1);
            this.f3410f = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f3406b, this.f3409e);
                this.f3409e -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.f3409e--;
            byte bC = c(this.f3410f);
            if (!this.f3407c) {
                return bC;
            }
            char c3 = this.f3410f;
            return c3 == '>' ? h() : c3 == ';' ? f() : bC;
        }

        public byte b() {
            char cCharAt = this.f3406b.charAt(this.f3409e);
            this.f3410f = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f3406b, this.f3409e);
                this.f3409e += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.f3409e++;
            byte bC = c(this.f3410f);
            if (!this.f3407c) {
                return bC;
            }
            char c3 = this.f3410f;
            return c3 == '<' ? i() : c3 == '&' ? g() : bC;
        }

        public int d() {
            this.f3409e = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (this.f3409e < this.f3408d && i3 == 0) {
                byte b3 = b();
                if (b3 != 0) {
                    if (b3 == 1 || b3 == 2) {
                        if (i5 == 0) {
                            return 1;
                        }
                    } else if (b3 != 9) {
                        switch (b3) {
                            case 14:
                            case 15:
                                i5++;
                                i4 = -1;
                                continue;
                            case 16:
                            case 17:
                                i5++;
                                i4 = 1;
                                continue;
                            case 18:
                                i5--;
                                i4 = 0;
                                continue;
                        }
                    }
                } else if (i5 == 0) {
                    return -1;
                }
                i3 = i5;
            }
            if (i3 == 0) {
                return 0;
            }
            if (i4 != 0) {
                return i4;
            }
            while (this.f3409e > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i3 == i5) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i3 == i5) {
                            return 1;
                        }
                        break;
                    case 18:
                        i5++;
                        continue;
                }
                i5--;
            }
            return 0;
        }

        public int e() {
            this.f3409e = this.f3408d;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                while (this.f3409e > 0) {
                    byte bA = a();
                    if (bA == 0) {
                        if (i3 == 0) {
                            return -1;
                        }
                        if (i4 == 0) {
                            break;
                        }
                    } else if (bA == 1 || bA == 2) {
                        if (i3 == 0) {
                            return 1;
                        }
                        if (i4 == 0) {
                            break;
                        }
                    } else if (bA != 9) {
                        switch (bA) {
                            case 14:
                            case 15:
                                if (i4 == i3) {
                                    return -1;
                                }
                                i3--;
                                break;
                            case 16:
                            case 17:
                                if (i4 == i3) {
                                    return 1;
                                }
                                i3--;
                                break;
                            case 18:
                                i3++;
                                break;
                            default:
                                if (i4 != 0) {
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
            int i3 = this.f3409e;
            do {
                int i4 = this.f3409e;
                if (i4 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f3406b;
                int i5 = i4 - 1;
                this.f3409e = i5;
                cCharAt = charSequence.charAt(i5);
                this.f3410f = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.f3409e = i3;
            this.f3410f = ';';
            return (byte) 13;
        }

        public final byte g() {
            char cCharAt;
            do {
                int i3 = this.f3409e;
                if (i3 >= this.f3408d) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f3406b;
                this.f3409e = i3 + 1;
                cCharAt = charSequence.charAt(i3);
                this.f3410f = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        public final byte h() {
            char cCharAt;
            int i3 = this.f3409e;
            while (true) {
                int i4 = this.f3409e;
                if (i4 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f3406b;
                int i5 = i4 - 1;
                this.f3409e = i5;
                char cCharAt2 = charSequence.charAt(i5);
                this.f3410f = cCharAt2;
                if (cCharAt2 == '<') {
                    return (byte) 12;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i6 = this.f3409e;
                        if (i6 > 0) {
                            CharSequence charSequence2 = this.f3406b;
                            int i7 = i6 - 1;
                            this.f3409e = i7;
                            cCharAt = charSequence2.charAt(i7);
                            this.f3410f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.f3409e = i3;
            this.f3410f = '>';
            return (byte) 13;
        }

        public final byte i() {
            char cCharAt;
            int i3 = this.f3409e;
            while (true) {
                int i4 = this.f3409e;
                if (i4 >= this.f3408d) {
                    this.f3409e = i3;
                    this.f3410f = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f3406b;
                this.f3409e = i4 + 1;
                char cCharAt2 = charSequence.charAt(i4);
                this.f3410f = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i5 = this.f3409e;
                        if (i5 < this.f3408d) {
                            CharSequence charSequence2 = this.f3406b;
                            this.f3409e = i5 + 1;
                            cCharAt = charSequence2.charAt(i5);
                            this.f3410f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }
    }

    static {
        d dVar = e.f3428c;
        f3394a = dVar;
        f3395b = Character.toString((char) 8206);
        f3396c = Character.toString((char) 8207);
        f3397d = new a(false, 2, dVar);
        f3398e = new a(true, 2, dVar);
    }

    public a(boolean z3, int i3, d dVar) {
        this.f3399f = z3;
        this.f3400g = i3;
        this.f3401h = dVar;
    }

    public static int a(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    public static int b(CharSequence charSequence) {
        return new b(charSequence, false).e();
    }

    public static a c() {
        return new C0024a().a();
    }

    public static boolean e(Locale locale) {
        return f.b(locale) == 1;
    }

    public boolean d() {
        return (this.f3400g & 2) != 0;
    }

    public final String f(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f3399f || !(zA || b(charSequence) == 1)) ? this.f3399f ? (!zA || b(charSequence) == -1) ? f3396c : "" : "" : f3395b;
    }

    public final String g(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f3399f || !(zA || a(charSequence) == 1)) ? this.f3399f ? (!zA || a(charSequence) == -1) ? f3396c : "" : "" : f3395b;
    }

    public CharSequence h(CharSequence charSequence) {
        return i(charSequence, this.f3401h, true);
    }

    public CharSequence i(CharSequence charSequence, d dVar, boolean z3) {
        if (charSequence == null) {
            return null;
        }
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (d() && z3) {
            spannableStringBuilder.append((CharSequence) g(charSequence, zA ? e.f3427b : e.f3426a));
        }
        if (zA != this.f3399f) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z3) {
            spannableStringBuilder.append((CharSequence) f(charSequence, zA ? e.f3427b : e.f3426a));
        }
        return spannableStringBuilder;
    }

    public String j(String str) {
        return k(str, this.f3401h, true);
    }

    public String k(String str, d dVar, boolean z3) {
        if (str == null) {
            return null;
        }
        return i(str, dVar, z3).toString();
    }
}
