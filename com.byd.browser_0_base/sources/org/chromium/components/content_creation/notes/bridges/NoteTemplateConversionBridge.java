package org.chromium.components.content_creation.notes.bridges;

import defpackage.fq2;
import defpackage.j71;
import defpackage.tw0;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.content_creation.notes.models.Background;
import org.chromium.components.content_creation.notes.models.FooterStyle;
import org.chromium.components.content_creation.notes.models.NoteTemplate;
import org.chromium.components.content_creation.notes.models.TextStyle;

/* JADX INFO: loaded from: classes2.dex */
public class NoteTemplateConversionBridge {
    @CalledByNative
    public static Background createBackground(int i) {
        return new fq2(i);
    }

    @CalledByNative
    public static FooterStyle createFooterStyle(int i, int i2) {
        return new FooterStyle(i, i2);
    }

    @CalledByNative
    public static Background createImageBackground(String str) {
        return new tw0(str);
    }

    @CalledByNative
    public static Background createLinearGradientBackground(int[] iArr, int i) {
        int i2 = 2;
        if (i != 1) {
            if (i != 2) {
                i2 = 4;
                if (i != 3) {
                    i2 = i != 4 ? 1 : 5;
                }
            } else {
                i2 = 3;
            }
        }
        return new j71(iArr, i2);
    }

    @CalledByNative
    public static NoteTemplate createTemplateAndMaybeAddToList(List<NoteTemplate> list, int i, String str, Background background, Background background2, TextStyle textStyle, FooterStyle footerStyle) {
        NoteTemplate noteTemplate = new NoteTemplate(i, str, background, background2, textStyle, footerStyle);
        if (list != null) {
            list.add(noteTemplate);
        }
        return noteTemplate;
    }

    @CalledByNative
    public static List<NoteTemplate> createTemplateList() {
        return new ArrayList();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.components.content_creation.notes.models.TextStyle createTextStyle(java.lang.String r11, int r12, int r13, boolean r14, int r15, int r16, int r17, int r18, int r19) {
        /*
            r0 = r15
            r1 = r19
            org.chromium.components.content_creation.notes.models.TextStyle r10 = new org.chromium.components.content_creation.notes.models.TextStyle
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == r4) goto L15
            if (r0 == r3) goto L13
            if (r0 == r2) goto L10
            r5 = r4
            goto L16
        L10:
            r0 = 4
            r5 = r0
            goto L16
        L13:
            r5 = r2
            goto L16
        L15:
            r5 = r3
        L16:
            if (r1 == 0) goto L21
            if (r1 == r4) goto L1f
            if (r1 == r3) goto L1d
            goto L21
        L1d:
            r9 = r2
            goto L22
        L1f:
            r9 = r3
            goto L22
        L21:
            r9 = r4
        L22:
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r6 = r16
            r7 = r17
            r8 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.content_creation.notes.bridges.NoteTemplateConversionBridge.createTextStyle(java.lang.String, int, int, boolean, int, int, int, int, int):org.chromium.components.content_creation.notes.models.TextStyle");
    }
}
