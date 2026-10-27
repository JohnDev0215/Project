package g0;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final c f6424d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f6425e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f6426f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final a f6427g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static final a f6428h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f6429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f6430b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f6431c;

    /* JADX INFO: renamed from: g0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class C0077a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f6432a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f6433b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private c f6434c;

        public C0077a() {
            c(a.e(Locale.getDefault()));
        }

        private static a b(boolean z6) {
            return z6 ? a.f6428h : a.f6427g;
        }

        private void c(boolean z6) {
            this.f6432a = z6;
            this.f6434c = a.f6424d;
            this.f6433b = 2;
        }

        public a a() {
            return (this.f6433b == 2 && this.f6434c == a.f6424d) ? b(this.f6432a) : new a(this.f6432a, this.f6433b, this.f6434c);
        }
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    private static class b {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final byte[] f6435f = new byte[1792];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final CharSequence f6436a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f6437b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f6438c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f6439d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private char f6440e;

        static {
            for (int i7 = 0; i7 < 1792; i7++) {
                f6435f[i7] = Character.getDirectionality(i7);
            }
        }

        b(CharSequence charSequence, boolean z6) {
            this.f6436a = charSequence;
            this.f6437b = z6;
            this.f6438c = charSequence.length();
        }

        private static byte c(char c7) {
            return c7 < 1792 ? f6435f[c7] : Character.getDirectionality(c7);
        }

        private byte f() {
            char cCharAt;
            int i7 = this.f6439d;
            do {
                int i8 = this.f6439d;
                if (i8 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f6436a;
                int i9 = i8 - 1;
                this.f6439d = i9;
                cCharAt = charSequence.charAt(i9);
                this.f6440e = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.f6439d = i7;
            this.f6440e = ';';
            return (byte) 13;
        }

        private byte g() {
            char cCharAt;
            do {
                int i7 = this.f6439d;
                if (i7 >= this.f6438c) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f6436a;
                this.f6439d = i7 + 1;
                cCharAt = charSequence.charAt(i7);
                this.f6440e = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        private byte h() {
            char cCharAt;
            int i7 = this.f6439d;
            while (true) {
                int i8 = this.f6439d;
                if (i8 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f6436a;
                int i9 = i8 - 1;
                this.f6439d = i9;
                char cCharAt2 = charSequence.charAt(i9);
                this.f6440e = cCharAt2;
                if (cCharAt2 == '<') {
                    return (byte) 12;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i10 = this.f6439d;
                        if (i10 > 0) {
                            CharSequence charSequence2 = this.f6436a;
                            int i11 = i10 - 1;
                            this.f6439d = i11;
                            cCharAt = charSequence2.charAt(i11);
                            this.f6440e = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.f6439d = i7;
            this.f6440e = '>';
            return (byte) 13;
        }

        private byte i() {
            char cCharAt;
            int i7 = this.f6439d;
            while (true) {
                int i8 = this.f6439d;
                if (i8 >= this.f6438c) {
                    this.f6439d = i7;
                    this.f6440e = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f6436a;
                this.f6439d = i8 + 1;
                char cCharAt2 = charSequence.charAt(i8);
                this.f6440e = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i9 = this.f6439d;
                        if (i9 < this.f6438c) {
                            CharSequence charSequence2 = this.f6436a;
                            this.f6439d = i9 + 1;
                            cCharAt = charSequence2.charAt(i9);
                            this.f6440e = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }

        byte a() {
            char cCharAt = this.f6436a.charAt(this.f6439d - 1);
            this.f6440e = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f6436a, this.f6439d);
                this.f6439d -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.f6439d--;
            byte bC = c(this.f6440e);
            if (!this.f6437b) {
                return bC;
            }
            char c7 = this.f6440e;
            return c7 == '>' ? h() : c7 == ';' ? f() : bC;
        }

        byte b() {
            char cCharAt = this.f6436a.charAt(this.f6439d);
            this.f6440e = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f6436a, this.f6439d);
                this.f6439d += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.f6439d++;
            byte bC = c(this.f6440e);
            if (!this.f6437b) {
                return bC;
            }
            char c7 = this.f6440e;
            return c7 == '<' ? i() : c7 == '&' ? g() : bC;
        }

        int d() {
            this.f6439d = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (this.f6439d < this.f6438c && i7 == 0) {
                byte b7 = b();
                if (b7 != 0) {
                    if (b7 == 1 || b7 == 2) {
                        if (i9 == 0) {
                            return 1;
                        }
                    } else if (b7 != 9) {
                        switch (b7) {
                            case 14:
                            case 15:
                                i9++;
                                i8 = -1;
                                continue;
                            case 16:
                            case 17:
                                i9++;
                                i8 = 1;
                                continue;
                            case 18:
                                i9--;
                                i8 = 0;
                                continue;
                        }
                    }
                } else if (i9 == 0) {
                    return -1;
                }
                i7 = i9;
            }
            if (i7 == 0) {
                return 0;
            }
            if (i8 != 0) {
                return i8;
            }
            while (this.f6439d > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i7 == i9) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i7 == i9) {
                            return 1;
                        }
                        break;
                    case 18:
                        i9++;
                        continue;
                }
                i9--;
            }
            return 0;
        }

        int e() {
            this.f6439d = this.f6438c;
            int i7 = 0;
            while (true) {
                int i8 = i7;
                while (this.f6439d > 0) {
                    byte bA = a();
                    if (bA == 0) {
                        if (i7 == 0) {
                            return -1;
                        }
                        if (i8 == 0) {
                            break;
                        }
                    } else if (bA == 1 || bA == 2) {
                        if (i7 == 0) {
                            return 1;
                        }
                        if (i8 == 0) {
                            break;
                        }
                    } else if (bA != 9) {
                        switch (bA) {
                            case 14:
                            case 15:
                                if (i8 == i7) {
                                    return -1;
                                }
                                i7--;
                                break;
                            case 16:
                            case 17:
                                if (i8 == i7) {
                                    return 1;
                                }
                                i7--;
                                break;
                            case 18:
                                i7++;
                                break;
                            default:
                                if (i8 != 0) {
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
        c cVar = d.f6452c;
        f6424d = cVar;
        f6425e = Character.toString((char) 8206);
        f6426f = Character.toString((char) 8207);
        f6427g = new a(false, 2, cVar);
        f6428h = new a(true, 2, cVar);
    }

    a(boolean z6, int i7, c cVar) {
        this.f6429a = z6;
        this.f6430b = i7;
        this.f6431c = cVar;
    }

    private static int a(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    private static int b(CharSequence charSequence) {
        return new b(charSequence, false).e();
    }

    public static a c() {
        return new C0077a().a();
    }

    static boolean e(Locale locale) {
        return e.a(locale) == 1;
    }

    private String f(CharSequence charSequence, c cVar) {
        boolean zA = cVar.a(charSequence, 0, charSequence.length());
        return (this.f6429a || !(zA || b(charSequence) == 1)) ? this.f6429a ? (!zA || b(charSequence) == -1) ? f6426f : "" : "" : f6425e;
    }

    private String g(CharSequence charSequence, c cVar) {
        boolean zA = cVar.a(charSequence, 0, charSequence.length());
        return (this.f6429a || !(zA || a(charSequence) == 1)) ? this.f6429a ? (!zA || a(charSequence) == -1) ? f6426f : "" : "" : f6425e;
    }

    public boolean d() {
        return (this.f6430b & 2) != 0;
    }

    public CharSequence h(CharSequence charSequence) {
        return i(charSequence, this.f6431c, true);
    }

    public CharSequence i(CharSequence charSequence, c cVar, boolean z6) {
        if (charSequence == null) {
            return null;
        }
        boolean zA = cVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (d() && z6) {
            spannableStringBuilder.append((CharSequence) g(charSequence, zA ? d.f6451b : d.f6450a));
        }
        if (zA != this.f6429a) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z6) {
            spannableStringBuilder.append((CharSequence) f(charSequence, zA ? d.f6451b : d.f6450a));
        }
        return spannableStringBuilder;
    }

    public String j(String str) {
        return k(str, this.f6431c, true);
    }

    public String k(String str, c cVar, boolean z6) {
        if (str == null) {
            return null;
        }
        return i(str, cVar, z6).toString();
    }
}
