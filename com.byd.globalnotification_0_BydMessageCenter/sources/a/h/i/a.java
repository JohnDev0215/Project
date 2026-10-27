package a.h.i;

import a.h.i.d;
import android.text.SpannableStringBuilder;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c f775d = d.f793c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f776e = Character.toString(8206);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f777f = Character.toString(8207);
    public static final a g = new a(false, 2, f775d);
    public static final a h = new a(true, 2, f775d);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f778a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f779b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f780c;

    /* JADX INFO: renamed from: a.h.i.a$a, reason: collision with other inner class name */
    public static class C0013a {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final byte[] f781f = new byte[1792];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CharSequence f782a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f783b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f784c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f785d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public char f786e;

        static {
            for (int i = 0; i < 1792; i++) {
                f781f[i] = Character.getDirectionality(i);
            }
        }

        public C0013a(CharSequence charSequence, boolean z) {
            this.f782a = charSequence;
            this.f783b = z;
            this.f784c = charSequence.length();
        }

        public byte a() {
            char cCharAt;
            char cCharAt2;
            char cCharAt3 = this.f782a.charAt(this.f785d - 1);
            this.f786e = cCharAt3;
            if (Character.isLowSurrogate(cCharAt3)) {
                int iCodePointBefore = Character.codePointBefore(this.f782a, this.f785d);
                this.f785d -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.f785d--;
            char c2 = this.f786e;
            byte directionality = c2 < 1792 ? f781f[c2] : Character.getDirectionality(c2);
            if (!this.f783b) {
                return directionality;
            }
            char c3 = this.f786e;
            if (c3 != '>') {
                if (c3 != ';') {
                    return directionality;
                }
                int i = this.f785d;
                do {
                    int i2 = this.f785d;
                    if (i2 <= 0) {
                        break;
                    }
                    CharSequence charSequence = this.f782a;
                    int i3 = i2 - 1;
                    this.f785d = i3;
                    cCharAt = charSequence.charAt(i3);
                    this.f786e = cCharAt;
                    if (cCharAt == '&') {
                        return (byte) 12;
                    }
                } while (cCharAt != ';');
                this.f785d = i;
                this.f786e = ';';
                return (byte) 13;
            }
            int i4 = this.f785d;
            while (true) {
                int i5 = this.f785d;
                if (i5 <= 0) {
                    break;
                }
                CharSequence charSequence2 = this.f782a;
                int i6 = i5 - 1;
                this.f785d = i6;
                char cCharAt4 = charSequence2.charAt(i6);
                this.f786e = cCharAt4;
                if (cCharAt4 == '<') {
                    break;
                }
                if (cCharAt4 == '>') {
                    break;
                }
                if (cCharAt4 == '\"' || cCharAt4 == '\'') {
                    char c4 = this.f786e;
                    do {
                        int i7 = this.f785d;
                        if (i7 > 0) {
                            CharSequence charSequence3 = this.f782a;
                            int i8 = i7 - 1;
                            this.f785d = i8;
                            cCharAt2 = charSequence3.charAt(i8);
                            this.f786e = cCharAt2;
                        }
                    } while (cCharAt2 != c4);
                }
            }
            this.f785d = i4;
            this.f786e = '>';
            return (byte) 13;
        }
    }

    public a(boolean z, int i, c cVar) {
        this.f778a = z;
        this.f779b = i;
        this.f780c = cVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:114:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:?, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:?, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00dc, code lost:
    
        if (r3 != 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00df, code lost:
    
        if (r4 == 0) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00e5, code lost:
    
        if (r0.f785d <= 0) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00eb, code lost:
    
        switch(r0.a()) {
            case 14: goto L106;
            case 15: goto L106;
            case 16: goto L105;
            case 17: goto L105;
            case 18: goto L104;
            default: goto L111;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00ef, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00f2, code lost:
    
        if (r3 != r5) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f6, code lost:
    
        if (r3 != r5) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00fa, code lost:
    
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00fd, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(java.lang.CharSequence r13) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.i.a.a(java.lang.CharSequence):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0041, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int b(java.lang.CharSequence r6) {
        /*
            a.h.i.a$a r0 = new a.h.i.a$a
            r1 = 0
            r0.<init>(r6, r1)
            int r6 = r0.f784c
            r0.f785d = r6
            r6 = r1
        Lb:
            r2 = r6
        Lc:
            int r3 = r0.f785d
            r4 = 1
            if (r3 <= 0) goto L41
            byte r3 = r0.a()
            if (r3 == 0) goto L39
            if (r3 == r4) goto L32
            r5 = 2
            if (r3 == r5) goto L32
            r5 = 9
            if (r3 == r5) goto Lc
            switch(r3) {
                case 14: goto L2c;
                case 15: goto L2c;
                case 16: goto L29;
                case 17: goto L29;
                case 18: goto L26;
                default: goto L23;
            }
        L23:
            if (r6 != 0) goto Lc
            goto L3f
        L26:
            int r2 = r2 + 1
            goto Lc
        L29:
            if (r6 != r2) goto L2f
            goto L34
        L2c:
            if (r6 != r2) goto L2f
            goto L3b
        L2f:
            int r2 = r2 + (-1)
            goto Lc
        L32:
            if (r2 != 0) goto L36
        L34:
            r1 = r4
            goto L41
        L36:
            if (r6 != 0) goto Lc
            goto L3f
        L39:
            if (r2 != 0) goto L3d
        L3b:
            r1 = -1
            goto L41
        L3d:
            if (r6 != 0) goto Lc
        L3f:
            r6 = r2
            goto Lb
        L41:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.i.a.b(java.lang.CharSequence):int");
    }

    public static a c() {
        boolean z = e.a(Locale.getDefault()) == 1;
        c cVar = f775d;
        return cVar == f775d ? z ? h : g : new a(z, 2, cVar);
    }

    public CharSequence d(CharSequence charSequence, c cVar, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean zB = ((d.c) cVar).b(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String str = "";
        if (((this.f779b & 2) != 0) && z) {
            boolean zB2 = ((d.c) (zB ? d.f792b : d.f791a)).b(charSequence, 0, charSequence.length());
            spannableStringBuilder.append((CharSequence) ((this.f778a || !(zB2 || a(charSequence) == 1)) ? (!this.f778a || (zB2 && a(charSequence) != -1)) ? "" : f777f : f776e));
        }
        if (zB != this.f778a) {
            spannableStringBuilder.append(zB ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            boolean zB3 = ((d.c) (zB ? d.f792b : d.f791a)).b(charSequence, 0, charSequence.length());
            if (!this.f778a && (zB3 || b(charSequence) == 1)) {
                str = f776e;
            } else if (this.f778a && (!zB3 || b(charSequence) == -1)) {
                str = f777f;
            }
            spannableStringBuilder.append((CharSequence) str);
        }
        return spannableStringBuilder;
    }
}
