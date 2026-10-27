package defpackage;

import android.content.Context;
import android.text.SpannableStringBuilder;
import java.util.List;
import org.chromium.components.omnibox.SuggestionAnswer;

/* JADX INFO: compiled from: AnswerTextNewLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b6 extends a6 {
    public final boolean g;
    public final int h;
    public final boolean i;

    public b6(Context context, int i, SuggestionAnswer.ImageLine imageLine, boolean z, boolean z2) {
        super(context);
        this.g = z;
        this.h = i;
        this.i = z2;
        List<SuggestionAnswer.TextField> list = imageLine.a;
        int i2 = 0;
        while (true) {
            if (i2 >= list.size()) {
                break;
            }
            a(list.get(i2).b, c(list.get(i2).a));
            if (list.get(i2).d != -1) {
                this.f = Math.max(this.f, Math.min(3, list.get(i2).d));
            }
            i2++;
        }
        SuggestionAnswer.TextField textField = imageLine.b;
        boolean z3 = textField != null;
        SpannableStringBuilder spannableStringBuilder = this.c;
        if (z3) {
            spannableStringBuilder.append((CharSequence) "  ");
            a(textField.b, c(textField.a));
        }
        SuggestionAnswer.TextField textField2 = imageLine.c;
        if (textField2 != null) {
            spannableStringBuilder.append((CharSequence) "  ");
            a(textField2.b, c(textField2.a));
        }
        this.d = spannableStringBuilder.toString();
    }

    @Override // defpackage.a6
    public final String b(String str) {
        int iIndexOf;
        return (this.g && this.h == 10 && (iIndexOf = str.indexOf(" = ")) > 0) ? str.substring(iIndexOf + 3) : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.text.style.MetricAffectingSpan[] c(int r6) {
        /*
            r5 = this;
            boolean r0 = r5.g
            r1 = 0
            android.content.Context r2 = r5.a
            r3 = 1
            if (r0 == 0) goto L54
            int r0 = r5.h
            if (r0 == r3) goto L1b
            r4 = 2
            if (r0 == r4) goto L1b
            android.text.style.TextAppearanceSpan[] r5 = new android.text.style.TextAppearanceSpan[r3]
            android.text.style.TextAppearanceSpan r6 = new android.text.style.TextAppearanceSpan
            int r0 = org.chromium.chrome.R$style.TextAppearance_TextLarge_Primary
            r6.<init>(r2, r0)
            r5[r1] = r6
            goto L5f
        L1b:
            r0 = 3
            if (r6 == r0) goto L47
            r0 = 8
            if (r6 == r0) goto L47
            r0 = 13
            if (r6 == r0) goto L47
            r0 = 5
            boolean r5 = r5.i
            if (r6 == r0) goto L3f
            r0 = 6
            if (r6 == r0) goto L37
            switch(r6) {
                case 17: goto L47;
                case 18: goto L47;
                case 19: goto L47;
                case 20: goto L34;
                default: goto L31;
            }
        L31:
            int r5 = org.chromium.chrome.R$style.TextAppearance_TextLarge_Primary
            goto L49
        L34:
            int r5 = org.chromium.chrome.R$style.TextAppearance_TextSmall_Secondary
            goto L49
        L37:
            if (r5 == 0) goto L3c
            int r5 = org.chromium.chrome.R$style.TextAppearance_OmniboxAnswerDescriptionNegativeSmall
            goto L49
        L3c:
            int r5 = org.chromium.chrome.R$style.TextAppearance_OmniboxAnswerDescriptionPositiveSmall
            goto L49
        L3f:
            if (r5 == 0) goto L44
            int r5 = org.chromium.chrome.R$style.TextAppearance_OmniboxAnswerDescriptionPositiveSmall
            goto L49
        L44:
            int r5 = org.chromium.chrome.R$style.TextAppearance_OmniboxAnswerDescriptionNegativeSmall
            goto L49
        L47:
            int r5 = org.chromium.chrome.R$style.TextAppearance_TextLarge_Primary
        L49:
            android.text.style.TextAppearanceSpan[] r6 = new android.text.style.TextAppearanceSpan[r3]
            android.text.style.TextAppearanceSpan r0 = new android.text.style.TextAppearanceSpan
            r0.<init>(r2, r5)
            r6[r1] = r0
            r5 = r6
            goto L5f
        L54:
            android.text.style.TextAppearanceSpan[] r5 = new android.text.style.TextAppearanceSpan[r3]
            android.text.style.TextAppearanceSpan r6 = new android.text.style.TextAppearanceSpan
            int r0 = org.chromium.chrome.R$style.TextAppearance_TextMedium_Secondary
            r6.<init>(r2, r0)
            r5[r1] = r6
        L5f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.b6.c(int):android.text.style.MetricAffectingSpan[]");
    }

    public b6(Context context, String str, boolean z) {
        super(context);
        this.g = z;
        this.h = 0;
        this.i = false;
        a(str, c(8));
    }
}
