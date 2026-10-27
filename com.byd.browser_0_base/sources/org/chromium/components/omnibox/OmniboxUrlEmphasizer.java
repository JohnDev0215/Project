package org.chromium.components.omnibox;

import J.N;
import android.content.Context;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class OmniboxUrlEmphasizer {

    public static class UrlEmphasisColorSpan extends ForegroundColorSpan implements b {
        public final int e;

        public UrlEmphasisColorSpan(int i) {
            super(i);
            this.e = i;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof UrlEmphasisColorSpan) && ((UrlEmphasisColorSpan) obj).e == this.e;
        }

        @Override // android.text.style.ForegroundColorSpan
        public final String toString() {
            return getClass().getName() + ", color: " + this.e;
        }
    }

    public static class UrlEmphasisSecurityErrorSpan extends StrikethroughSpan implements b {
        public final boolean equals(Object obj) {
            return obj instanceof UrlEmphasisSecurityErrorSpan;
        }
    }

    public static class a {
        public final int a;
        public final int b;
        public final int c;
        public final int d;

        public a(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public final String a(String str) {
            int i = this.b;
            if (!(i > 0)) {
                return "";
            }
            int i2 = this.a;
            return str.subSequence(i2, i + i2).toString().toLowerCase(Locale.US);
        }
    }

    public interface b {
    }

    public static void a(Spannable spannable, Context context, AutocompleteSchemeClassifier autocompleteSchemeClassifier, int i, boolean z, boolean z2) {
        b(spannable, autocompleteSchemeClassifier, i, z2, context.getColor(z ? R$color.url_emphasis_non_emphasized_text : R$color.url_emphasis_light_non_emphasized_text), context.getColor(z ? R$color.url_emphasis_emphasized_text : R$color.url_emphasis_light_emphasized_text), context.getColor(z ? R$color.default_red_dark : R$color.default_red_light), context.getColor(z ? R$color.default_green_dark : R$color.default_green_light));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(android.text.Spannable r16, org.chromium.components.omnibox.AutocompleteSchemeClassifier r17, int r18, boolean r19, int r20, int r21, int r22, int r23) {
        /*
            r0 = r16
            r1 = r18
            r2 = r20
            java.lang.String r3 = r16.toString()
            r4 = r17
            org.chromium.components.omnibox.OmniboxUrlEmphasizer$a r4 = c(r3, r4)
            int r5 = r4.a
            int r6 = r4.b
            int r7 = r5 + r6
            int r8 = r4.d
            int r9 = r4.c
            int r10 = r8 + r9
            java.lang.String r11 = r4.a(r3)
            java.util.HashSet<java.lang.String> r12 = defpackage.bk3.d
            boolean r11 = r12.contains(r11)
            r12 = 1
            r13 = 0
            if (r6 <= 0) goto L2c
            r6 = r12
            goto L2d
        L2c:
            r6 = r13
        L2d:
            java.lang.String r14 = "data"
            r15 = 33
            if (r6 == 0) goto L80
            if (r11 != 0) goto L68
            if (r1 == 0) goto L4e
            r6 = 3
            if (r1 == r6) goto L49
            r6 = 5
            if (r1 == r6) goto L41
            r6 = 6
            if (r1 == r6) goto L4e
            goto L5b
        L41:
            if (r19 == 0) goto L46
            r1 = r22
            goto L47
        L46:
            r1 = r2
        L47:
            r6 = r12
            goto L5d
        L49:
            if (r19 == 0) goto L5b
            r1 = r23
            goto L5c
        L4e:
            java.lang.String r1 = r4.a(r3)
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L5b
            r1 = r21
            goto L5c
        L5b:
            r1 = r2
        L5c:
            r6 = r13
        L5d:
            if (r6 == 0) goto L69
            org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisSecurityErrorSpan r6 = new org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisSecurityErrorSpan
            r6.<init>()
            r0.setSpan(r6, r5, r7, r15)
            goto L69
        L68:
            r1 = r2
        L69:
            org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan r6 = new org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan
            r6.<init>(r1)
            r0.setSpan(r6, r5, r7, r15)
            if (r8 <= 0) goto L75
            r1 = r12
            goto L76
        L75:
            r1 = r13
        L76:
            if (r1 == 0) goto L80
            org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan r1 = new org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan
            r1.<init>(r2)
            r0.setSpan(r1, r7, r9, r15)
        L80:
            if (r8 <= 0) goto L83
            goto L84
        L83:
            r12 = r13
        L84:
            if (r12 == 0) goto La3
            org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan r1 = new org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan
            r4 = r21
            r1.<init>(r4)
            r0.setSpan(r1, r9, r10, r15)
            int r1 = r3.length()
            if (r10 >= r1) goto Lb9
            org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan r1 = new org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan
            r1.<init>(r2)
            int r2 = r3.length()
            r0.setSpan(r1, r10, r2, r15)
            goto Lb9
        La3:
            java.lang.String r1 = r4.a(r3)
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto Lb9
            org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan r1 = new org.chromium.components.omnibox.OmniboxUrlEmphasizer$UrlEmphasisColorSpan
            r1.<init>(r2)
            int r2 = r3.length()
            r0.setSpan(r1, r7, r2, r15)
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.omnibox.OmniboxUrlEmphasizer.b(android.text.Spannable, org.chromium.components.omnibox.AutocompleteSchemeClassifier, int, boolean, int, int, int, int):void");
    }

    public static a c(String str, AutocompleteSchemeClassifier autocompleteSchemeClassifier) {
        int[] iArrM8aH4n_l = N.M8aH4n_l(str, autocompleteSchemeClassifier);
        return new a(iArrM8aH4n_l[0], iArrM8aH4n_l[1], iArrM8aH4n_l[2], iArrM8aH4n_l[3]);
    }
}
