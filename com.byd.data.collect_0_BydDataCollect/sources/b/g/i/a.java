package b.g.i;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final d f2612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f2614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final a f2615d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final a f2616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f2617f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f2618g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final d f2619h;

    /* JADX INFO: renamed from: b.g.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class C0042a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2620a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2621b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f2622c;

        public C0042a() {
            c(a.e(Locale.getDefault()));
        }

        private static a b(boolean z) {
            return z ? a.f2616e : a.f2615d;
        }

        private void c(boolean z) {
            this.f2620a = z;
            this.f2622c = a.f2612a;
            this.f2621b = 2;
        }

        public a a() {
            return (this.f2621b == 2 && this.f2622c == a.f2612a) ? b(this.f2620a) : new a(this.f2620a, this.f2621b, this.f2622c);
        }
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final byte[] f2623a = new byte[1792];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final CharSequence f2624b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f2625c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f2626d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f2627e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private char f2628f;

        static {
            for (int i2 = 0; i2 < 1792; i2++) {
                f2623a[i2] = Character.getDirectionality(i2);
            }
        }

        b(CharSequence charSequence, boolean z) {
            this.f2624b = charSequence;
            this.f2625c = z;
            this.f2626d = charSequence.length();
        }

        private static byte c(char c2) {
            return c2 < 1792 ? f2623a[c2] : Character.getDirectionality(c2);
        }

        private byte f() {
            char cCharAt;
            int i2 = this.f2627e;
            do {
                int i3 = this.f2627e;
                if (i3 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f2624b;
                int i4 = i3 - 1;
                this.f2627e = i4;
                cCharAt = charSequence.charAt(i4);
                this.f2628f = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.f2627e = i2;
            this.f2628f = ';';
            return (byte) 13;
        }

        private byte g() {
            char cCharAt;
            do {
                int i2 = this.f2627e;
                if (i2 >= this.f2626d) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f2624b;
                this.f2627e = i2 + 1;
                cCharAt = charSequence.charAt(i2);
                this.f2628f = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        private byte h() {
            char cCharAt;
            int i2 = this.f2627e;
            while (true) {
                int i3 = this.f2627e;
                if (i3 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f2624b;
                int i4 = i3 - 1;
                this.f2627e = i4;
                char cCharAt2 = charSequence.charAt(i4);
                this.f2628f = cCharAt2;
                if (cCharAt2 == '<') {
                    return (byte) 12;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i5 = this.f2627e;
                        if (i5 > 0) {
                            CharSequence charSequence2 = this.f2624b;
                            int i6 = i5 - 1;
                            this.f2627e = i6;
                            cCharAt = charSequence2.charAt(i6);
                            this.f2628f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.f2627e = i2;
            this.f2628f = '>';
            return (byte) 13;
        }

        private byte i() {
            char cCharAt;
            int i2 = this.f2627e;
            while (true) {
                int i3 = this.f2627e;
                if (i3 >= this.f2626d) {
                    this.f2627e = i2;
                    this.f2628f = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f2624b;
                this.f2627e = i3 + 1;
                char cCharAt2 = charSequence.charAt(i3);
                this.f2628f = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i4 = this.f2627e;
                        if (i4 < this.f2626d) {
                            CharSequence charSequence2 = this.f2624b;
                            this.f2627e = i4 + 1;
                            cCharAt = charSequence2.charAt(i4);
                            this.f2628f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }

        byte a() {
            char cCharAt = this.f2624b.charAt(this.f2627e - 1);
            this.f2628f = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f2624b, this.f2627e);
                this.f2627e -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.f2627e--;
            byte bC = c(this.f2628f);
            if (!this.f2625c) {
                return bC;
            }
            char c2 = this.f2628f;
            return c2 == '>' ? h() : c2 == ';' ? f() : bC;
        }

        byte b() {
            char cCharAt = this.f2624b.charAt(this.f2627e);
            this.f2628f = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f2624b, this.f2627e);
                this.f2627e += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.f2627e++;
            byte bC = c(this.f2628f);
            if (!this.f2625c) {
                return bC;
            }
            char c2 = this.f2628f;
            return c2 == '<' ? i() : c2 == '&' ? g() : bC;
        }

        int d() {
            this.f2627e = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (this.f2627e < this.f2626d && i2 == 0) {
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
            while (this.f2627e > 0) {
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

        int e() {
            this.f2627e = this.f2626d;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                while (this.f2627e > 0) {
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
    }

    static {
        d dVar = e.f2646c;
        f2612a = dVar;
        f2613b = Character.toString((char) 8206);
        f2614c = Character.toString((char) 8207);
        f2615d = new a(false, 2, dVar);
        f2616e = new a(true, 2, dVar);
    }

    a(boolean z, int i2, d dVar) {
        this.f2617f = z;
        this.f2618g = i2;
        this.f2619h = dVar;
    }

    private static int a(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    private static int b(CharSequence charSequence) {
        return new b(charSequence, false).e();
    }

    public static a c() {
        return new C0042a().a();
    }

    static boolean e(Locale locale) {
        return f.b(locale) == 1;
    }

    private String f(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f2617f || !(zA || b(charSequence) == 1)) ? this.f2617f ? (!zA || b(charSequence) == -1) ? f2614c : "" : "" : f2613b;
    }

    private String g(CharSequence charSequence, d dVar) {
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        return (this.f2617f || !(zA || a(charSequence) == 1)) ? this.f2617f ? (!zA || a(charSequence) == -1) ? f2614c : "" : "" : f2613b;
    }

    public boolean d() {
        return (this.f2618g & 2) != 0;
    }

    public CharSequence h(CharSequence charSequence) {
        return i(charSequence, this.f2619h, true);
    }

    public CharSequence i(CharSequence charSequence, d dVar, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean zA = dVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (d() && z) {
            spannableStringBuilder.append((CharSequence) g(charSequence, zA ? e.f2645b : e.f2644a));
        }
        if (zA != this.f2617f) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) f(charSequence, zA ? e.f2645b : e.f2644a));
        }
        return spannableStringBuilder;
    }

    public String j(String str) {
        return k(str, this.f2619h, true);
    }

    public String k(String str, d dVar, boolean z) {
        if (str == null) {
            return null;
        }
        return i(str, dVar, z).toString();
    }
}
