package com.byd.vrsettings.utils;

import android.text.InputFilter;
import android.text.Spanned;

/* JADX INFO: loaded from: classes.dex */
public class EmojiExcludeFilter implements InputFilter {
    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        return filterNickName(charSequence, i, i2, spanned, i3, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0110 A[PHI: r5
  0x0110: PHI (r5v7 java.lang.String) = 
  (r5v5 java.lang.String)
  (r5v5 java.lang.String)
  (r5v5 java.lang.String)
  (r5v5 java.lang.String)
  (r5v6 java.lang.String)
  (r5v6 java.lang.String)
 binds: [B:59:0x010e, B:62:0x0119, B:65:0x0122, B:71:0x0136, B:74:0x0141, B:77:0x014a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String filterNickName(java.lang.CharSequence r8, int r9, int r10, android.text.Spanned r11, int r12, int r13) {
        /*
            Method dump skipped, instruction units count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrsettings.utils.EmojiExcludeFilter.filterNickName(java.lang.CharSequence, int, int, android.text.Spanned, int, int):java.lang.String");
    }
}
