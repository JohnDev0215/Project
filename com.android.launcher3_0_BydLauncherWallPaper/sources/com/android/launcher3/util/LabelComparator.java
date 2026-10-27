package com.android.launcher3.util;

import java.text.Collator;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public class LabelComparator implements Comparator<String> {
    private final Collator mCollator = Collator.getInstance();

    @Override // java.util.Comparator
    public int compare(String titleA, String titleB) {
        boolean z = false;
        boolean z2 = titleA.length() > 0 && Character.isLetterOrDigit(titleA.codePointAt(0));
        if (titleB.length() > 0 && Character.isLetterOrDigit(titleB.codePointAt(0))) {
            z = true;
        }
        if (z2 && !z) {
            return -1;
        }
        if (z2 || !z) {
            return this.mCollator.compare(titleA, titleB);
        }
        return 1;
    }
}
