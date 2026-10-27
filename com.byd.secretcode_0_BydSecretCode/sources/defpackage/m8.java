package defpackage;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class m8 {
    public static final p8 a;
    public static final String b;
    public static final String c;
    public static final m8 d;
    public static final m8 e;
    public final boolean f;
    public final int g;
    public final p8 h;

    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class a {
        public boolean a;
        public int b;
        public p8 c;

        public a() {
            c(m8.e(Locale.getDefault()));
        }

        public static m8 b(boolean z) {
            return z ? m8.e : m8.d;
        }

        public m8 a() {
            return (this.b == 2 && this.c == m8.a) ? b(this.a) : new m8(this.a, this.b, this.c);
        }

        public final void c(boolean z) {
            this.a = z;
            this.c = m8.a;
            this.b = 2;
        }
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    public static class b {
        public static final byte[] a = new byte[1792];
        public final CharSequence b;
        public final boolean c;
        public final int d;
        public int e;
        public char f;

        static {
            for (int i = 0; i < 1792; i++) {
                a[i] = Character.getDirectionality(i);
            }
        }

        public b(CharSequence charSequence, boolean z) {
            this.b = charSequence;
            this.c = z;
            this.d = charSequence.length();
        }

        public static byte c(char c) {
            return c < 1792 ? a[c] : Character.getDirectionality(c);
        }

        public byte a() {
            char cCharAt = this.b.charAt(this.e - 1);
            this.f = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.b, this.e);
                this.e -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.e--;
            byte bC = c(this.f);
            if (!this.c) {
                return bC;
            }
            char c = this.f;
            return c == '>' ? h() : c == ';' ? f() : bC;
        }

        public byte b() {
            char cCharAt = this.b.charAt(this.e);
            this.f = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.b, this.e);
                this.e += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.e++;
            byte bC = c(this.f);
            if (!this.c) {
                return bC;
            }
            char c = this.f;
            return c == '<' ? i() : c == '&' ? g() : bC;
        }

        public int d() {
            this.e = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.e < this.d && i == 0) {
                byte b = b();
                if (b != 0) {
                    if (b == 1 || b == 2) {
                        if (i3 == 0) {
                            return 1;
                        }
                    } else if (b != 9) {
                        switch (b) {
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

        public int e() {
            this.e = this.d;
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

        public final byte f() {
            char cCharAt;
            int i = this.e;
            do {
                int i2 = this.e;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.b;
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

        public final byte g() {
            char cCharAt;
            do {
                int i = this.e;
                if (i >= this.d) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.b;
                this.e = i + 1;
                cCharAt = charSequence.charAt(i);
                this.f = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        public final byte h() {
            char cCharAt;
            int i = this.e;
            while (true) {
                int i2 = this.e;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.b;
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
                            CharSequence charSequence2 = this.b;
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

        public final byte i() {
            char cCharAt;
            int i = this.e;
            while (true) {
                int i2 = this.e;
                if (i2 >= this.d) {
                    this.e = i;
                    this.f = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.b;
                this.e = i2 + 1;
                char cCharAt2 = charSequence.charAt(i2);
                this.f = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i3 = this.e;
                        if (i3 < this.d) {
                            CharSequence charSequence2 = this.b;
                            this.e = i3 + 1;
                            cCharAt = charSequence2.charAt(i3);
                            this.f = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }
    }

    static {
        p8 p8Var = q8.c;
        a = p8Var;
        b = Character.toString((char) 8206);
        c = Character.toString((char) 8207);
        d = new m8(false, 2, p8Var);
        e = new m8(true, 2, p8Var);
    }

    public m8(boolean z, int i, p8 p8Var) {
        this.f = z;
        this.g = i;
        this.h = p8Var;
    }

    public static int a(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    public static int b(CharSequence charSequence) {
        return new b(charSequence, false).e();
    }

    public static m8 c() {
        return new a().a();
    }

    public static boolean e(Locale locale) {
        return r8.b(locale) == 1;
    }

    public boolean d() {
        return (this.g & 2) != 0;
    }

    public final String f(CharSequence charSequence, p8 p8Var) {
        boolean zA = p8Var.a(charSequence, 0, charSequence.length());
        return (this.f || !(zA || b(charSequence) == 1)) ? this.f ? (!zA || b(charSequence) == -1) ? c : "" : "" : b;
    }

    public final String g(CharSequence charSequence, p8 p8Var) {
        boolean zA = p8Var.a(charSequence, 0, charSequence.length());
        return (this.f || !(zA || a(charSequence) == 1)) ? this.f ? (!zA || a(charSequence) == -1) ? c : "" : "" : b;
    }

    public CharSequence h(CharSequence charSequence) {
        return i(charSequence, this.h, true);
    }

    public CharSequence i(CharSequence charSequence, p8 p8Var, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean zA = p8Var.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (d() && z) {
            spannableStringBuilder.append((CharSequence) g(charSequence, zA ? q8.b : q8.a));
        }
        if (zA != this.f) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) f(charSequence, zA ? q8.b : q8.a));
        }
        return spannableStringBuilder;
    }

    public String j(String str) {
        return k(str, this.h, true);
    }

    public String k(String str, p8 p8Var, boolean z) {
        if (str == null) {
            return null;
        }
        return i(str, p8Var, z).toString();
    }
}
