package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Shader;

/* JADX INFO: compiled from: ComplexColorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class vv {
    public final Shader a;
    public final ColorStateList b;
    public int c;

    public vv(Shader shader, ColorStateList colorStateList, int i) {
        this.a = shader;
        this.b = colorStateList;
        this.c = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0196, code lost:
    
        if (r5.size() <= 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0198, code lost:
    
        r1 = new defpackage.it0(r5, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x019e, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x019f, code lost:
    
        if (r1 == null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01a2, code lost:
    
        if (r18 == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01a4, code lost:
    
        r1 = new defpackage.it0(r6, r10, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01aa, code lost:
    
        r1 = new defpackage.it0(r6, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01b0, code lost:
    
        if (r9 == 1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01b3, code lost:
    
        if (r9 == 2) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01b5, code lost:
    
        r5 = r1.a;
        r1 = r1.b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01bb, code lost:
    
        if (r0 == 1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01bd, code lost:
    
        if (r0 == 2) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01bf, code lost:
    
        r0 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01c2, code lost:
    
        r0 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01c5, code lost:
    
        r0 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01c7, code lost:
    
        r4 = new android.graphics.LinearGradient(r12, r26, r25, r15, r5, r1, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01d8, code lost:
    
        r4 = new android.graphics.SweepGradient(r7, r8, r1.a, r1.b);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ed, code lost:
    
        if (r5 <= 0.0f) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01ef, code lost:
    
        r5 = r1.a;
        r1 = r1.b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01f6, code lost:
    
        if (r0 == 1) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01f9, code lost:
    
        if (r0 == 2) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01fb, code lost:
    
        r0 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01fe, code lost:
    
        r0 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0201, code lost:
    
        r0 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0203, code lost:
    
        r4 = new android.graphics.RadialGradient(r7, r8, r5, r5, r1, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x021b, code lost:
    
        return new defpackage.vv(r4, null, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0223, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.vv a(android.content.res.Resources r28, int r29, android.content.res.Resources.Theme r30) {
        /*
            Method dump skipped, instruction units count: 586
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vv.a(android.content.res.Resources, int, android.content.res.Resources$Theme):vv");
    }

    public final boolean b() {
        ColorStateList colorStateList;
        return this.a == null && (colorStateList = this.b) != null && colorStateList.isStateful();
    }
}
