package m0;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f6295a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f6296b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f6297c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f6298d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f6299e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f6300f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f6301g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f6302h;

    /* JADX INFO: renamed from: m0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class C0086a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6303a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f6304b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f6305c;

        public C0086a() {
            c(a.e(Locale.getDefault()));
        }

        public static a b(boolean z7) {
            return z7 ? a.f6299e : a.f6298d;
        }

        public a a() {
            return (this.f6304b == 2 && this.f6305c == a.f6295a) ? b(this.f6303a) : new a(this.f6303a, this.f6304b, this.f6305c);
        }

        public final void c(boolean z7) {
            this.f6303a = z7;
            this.f6305c = a.f6295a;
            this.f6304b = 2;
        }
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final byte[] f6306a = new byte[1792];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final CharSequence f6307b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f6308c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f6309d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6310e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public char f6311f;

        static {
            for (int i8 = 0; i8 < 1792; i8++) {
                f6306a[i8] = Character.getDirectionality(i8);
            }
        }

        public b(CharSequence charSequence, boolean z7) {
            this.f6307b = charSequence;
            this.f6308c = z7;
            this.f6309d = charSequence.length();
        }

        public static byte c(char c8) {
            return c8 < 1792 ? f6306a[c8] : Character.getDirectionality(c8);
        }

        public byte a() {
            char cCharAt = this.f6307b.charAt(this.f6310e - 1);
            this.f6311f = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f6307b, this.f6310e);
                this.f6310e -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.f6310e--;
            byte bC = c(this.f6311f);
            if (!this.f6308c) {
                return bC;
            }
            char c8 = this.f6311f;
            return c8 == '>' ? h() : c8 == ';' ? f() : bC;
        }

        public byte b() {
            char cCharAt = this.f6307b.charAt(this.f6310e);
            this.f6311f = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f6307b, this.f6310e);
                this.f6310e += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.f6310e++;
            byte bC = c(this.f6311f);
            if (!this.f6308c) {
                return bC;
            }
            char c8 = this.f6311f;
            return c8 == '<' ? i() : c8 == '&' ? g() : bC;
        }

        public int d() {
            this.f6310e = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (this.f6310e < this.f6309d && i8 == 0) {
                byte b8 = b();
                if (b8 != 0) {
                    if (b8 == 1 || b8 == 2) {
                        if (i10 == 0) {
                            return 1;
                        }
                    } else if (b8 != 9) {
                        switch (b8) {
                            case 14:
                            case 15:
                                i10++;
                                i9 = -1;
                                continue;
                            case 16:
                            case 17:
                                i10++;
                                i9 = 1;
                                continue;
                            case 18:
                                i10--;
                                i9 = 0;
                                continue;
                        }
                    }
                } else if (i10 == 0) {
                    return -1;
                }
                i8 = i10;
            }
            if (i8 == 0) {
                return 0;
            }
            if (i9 != 0) {
                return i9;
            }
            while (this.f6310e > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i8 == i10) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i8 == i10) {
                            return 1;
                        }
                        break;
                    case 18:
                        i10++;
                        continue;
                }
                i10--;
            }
            return 0;
        }

        public int e() {
            this.f6310e = this.f6309d;
            int i8 = 0;
            while (true) {
                int i9 = i8;
                while (this.f6310e > 0) {
                    byte bA = a();
                    if (bA == 0) {
                        if (i8 == 0) {
                            return -1;
                        }
                        if (i9 == 0) {
                            break;
                        }
                    } else if (bA == 1 || bA == 2) {
                        if (i8 == 0) {
                            return 1;
                        }
                        if (i9 == 0) {
                            break;
                        }
                    } else if (bA != 9) {
                        switch (bA) {
                            case 14:
                            case 15:
                                if (i9 == i8) {
                                    return -1;
                                }
                                i8--;
                                break;
                            case 16:
                            case 17:
                                if (i9 == i8) {
                                    return 1;
                                }
                                i8--;
                                break;
                            case 18:
                                i8++;
                                break;
                            default:
                                if (i9 != 0) {
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
            int i8 = this.f6310e;
            do {
                int i9 = this.f6310e;
                if (i9 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f6307b;
                int i10 = i9 - 1;
                this.f6310e = i10;
                cCharAt = charSequence.charAt(i10);
                this.f6311f = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.f6310e = i8;
            this.f6311f = ';';
            return (byte) 13;
        }

        public final byte g() {
            char cCharAt;
            do {
                int i8 = this.f6310e;
                if (i8 >= this.f6309d) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f6307b;
                this.f6310e = i8 + 1;
                cCharAt = charSequence.charAt(i8);
                this.f6311f = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        public final byte h() {
            char cCharAt;
            int i8 = this.f6310e;
            while (true) {
                int i9 = this.f6310e;
                if (i9 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f6307b;
                int i10 = i9 - 1;
                this.f6310e = i10;
                char cCharAt2 = charSequence.charAt(i10);
                this.f6311f = cCharAt2;
                if (cCharAt2 == '<') {
                    return (byte) 12;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i11 = this.f6310e;
                        if (i11 > 0) {
                            CharSequence charSequence2 = this.f6307b;
                            int i12 = i11 - 1;
                            this.f6310e = i12;
                            cCharAt = charSequence2.charAt(i12);
                            this.f6311f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.f6310e = i8;
            this.f6311f = '>';
            return (byte) 13;
        }

        public final byte i() {
            char cCharAt;
            int i8 = this.f6310e;
            while (true) {
                int i9 = this.f6310e;
                if (i9 >= this.f6309d) {
                    this.f6310e = i8;
                    this.f6311f = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f6307b;
                this.f6310e = i9 + 1;
                char cCharAt2 = charSequence.charAt(i9);
                this.f6311f = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i10 = this.f6310e;
                        if (i10 < this.f6309d) {
                            CharSequence charSequence2 = this.f6307b;
                            this.f6310e = i10 + 1;
                            cCharAt = charSequence2.charAt(i10);
                            this.f6311f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }
    }

    static {
        d dVar = e.f6329c;
        f6295a = dVar;
        f6296b = Character.toString((char) 8206);
        f6297c = Character.toString((char) 8207);
        f6298d = new a(false, 2, dVar);
        f6299e = new a(true, 2, dVar);
    }

    public a(boolean z7, int i8, d dVar) {
        this.f6300f = z7;
        this.f6301g = i8;
        this.f6302h = dVar;
    }

    public static int a(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    public static int b(CharSequence charSequence) {
        return new b(charSequence, false).e();
    }

    public static a c() {
        return new C0086a().a();
    }

    public static boolean e(Locale locale) {
        return f.b(locale) == 1;
    }

    public boolean d() {
        return (this.f6301g & 2) != 0;
    }

    public final String f(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f6300f || !(zA || b(charSequence) == 1)) ? this.f6300f ? (!zA || b(charSequence) == -1) ? f6297c : "" : "" : f6296b;
    }

    public final String g(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f6300f || !(zA || a(charSequence) == 1)) ? this.f6300f ? (!zA || a(charSequence) == -1) ? f6297c : "" : "" : f6296b;
    }

    public CharSequence h(CharSequence charSequence) {
        return i(charSequence, this.f6302h, true);
    }

    public CharSequence i(CharSequence charSequence, d dVar, boolean z7) {
        if (charSequence == null) {
            return null;
        }
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (d() && z7) {
            spannableStringBuilder.append((CharSequence) g(charSequence, zA ? e.f6328b : e.f6327a));
        }
        if (zA != this.f6300f) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z7) {
            spannableStringBuilder.append((CharSequence) f(charSequence, zA ? e.f6328b : e.f6327a));
        }
        return spannableStringBuilder;
    }

    public String j(String str) {
        return k(str, this.f6302h, true);
    }

    public String k(String str, d dVar, boolean z7) {
        if (str == null) {
            return null;
        }
        return i(str, dVar, z7).toString();
    }
}
