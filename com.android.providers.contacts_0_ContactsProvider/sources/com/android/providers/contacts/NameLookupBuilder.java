package com.android.providers.contacts;

import com.android.providers.contacts.NameSplitter;
import com.android.providers.contacts.SearchIndexManager;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class NameLookupBuilder {
    private static final int[] KOREAN_JAUM_CONVERT_MAP = {4352, 4353, 0, 4354, 0, 0, 4355, 4356, 4357, 0, 0, 0, 0, 0, 0, 0, 4358, 4359, 4360, 0, 4361, 4362, 4363, 4364, 4365, 4366, 4367, 4368, 4369, 4370};
    private final NameSplitter mSplitter;
    private String[][] mNicknameClusters = new String[4][];
    private StringBuilder mStringBuilder = new StringBuilder();
    private String[] mNames = new String[10];

    protected abstract String[] getCommonNicknameClusters(String str);

    protected abstract void insertNameLookup(long j, long j2, int i, String str);

    public NameLookupBuilder(NameSplitter nameSplitter) {
        this.mSplitter = nameSplitter;
    }

    public void insertNameLookup(long j, long j2, String str, int i) {
        int i2 = this.mSplitter.tokenize(this.mNames, str);
        if (i2 == 0) {
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            String[] strArr = this.mNames;
            strArr[i3] = normalizeName(strArr[i3]);
        }
        int i4 = 4;
        boolean z = i2 > 4;
        if (z) {
            insertNameVariant(j, j2, i2, 0, true);
            Arrays.sort(this.mNames, 0, i2, new Comparator<String>(this) { // from class: com.android.providers.contacts.NameLookupBuilder.1
                @Override // java.util.Comparator
                public int compare(String str2, String str3) {
                    return str3.length() - str2.length();
                }
            });
            String str2 = this.mNames[0];
            for (int i5 = 4; i5 < i2; i5++) {
                String[] strArr2 = this.mNames;
                strArr2[0] = strArr2[i5];
                insertCollationKey(j, j2, 4);
            }
            this.mNames[0] = str2;
        } else {
            i4 = i2;
        }
        for (int i6 = 0; i6 < i4; i6++) {
            this.mNicknameClusters[i6] = getCommonNicknameClusters(this.mNames[i6]);
        }
        int i7 = i4;
        insertNameVariants(j, j2, 0, i7, !z, true);
        insertNicknamePermutations(j, j2, 0, i7);
    }

    public void appendToSearchIndex(SearchIndexManager.IndexBuilder indexBuilder, String str, int i) {
        int i2 = this.mSplitter.tokenize(this.mNames, str);
        if (i2 == 0) {
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            indexBuilder.appendName(this.mNames[i3]);
        }
        appendNameShorthandLookup(indexBuilder, str, i);
        appendNameLookupForLocaleBasedName(indexBuilder, str, i);
    }

    private void appendNameLookupForLocaleBasedName(SearchIndexManager.IndexBuilder indexBuilder, String str, int i) {
        if (i == 5) {
            NameSplitter.Name name = new NameSplitter.Name();
            this.mSplitter.split(name, str, i);
            String str2 = name.givenNames;
            if (str2 != null) {
                indexBuilder.appendName(str2);
                appendKoreanNameConsonantsLookup(indexBuilder, name.givenNames);
            }
            appendKoreanNameConsonantsLookup(indexBuilder, str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0065 A[LOOP:0: B:3:0x000b->B:36:0x0065, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0058 A[EDGE_INSN: B:43:0x0058->B:32:0x0058 BREAK  A[LOOP:0: B:3:0x000b->B:36:0x0065], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void appendKoreanNameConsonantsLookup(com.android.providers.contacts.SearchIndexManager.IndexBuilder r9, java.lang.String r10) {
        /*
            r8 = this;
            int r0 = r10.length()
            java.lang.StringBuilder r1 = r8.mStringBuilder
            r2 = 0
            r1.setLength(r2)
            r1 = r2
        Lb:
            int r3 = r2 + 1
            int r2 = r10.codePointAt(r2)
            r4 = 32
            if (r2 == r4) goto L56
            r4 = 44
            if (r2 == r4) goto L56
            r4 = 46
            if (r2 != r4) goto L1e
            goto L56
        L1e:
            r4 = 4352(0x1100, float:6.098E-42)
            if (r2 < r4) goto L58
            r5 = 4370(0x1112, float:6.124E-42)
            r6 = 12593(0x3131, float:1.7647E-41)
            if (r2 <= r5) goto L2a
            if (r2 < r6) goto L58
        L2a:
            r5 = 12622(0x314e, float:1.7687E-41)
            r7 = 44032(0xac00, float:6.1702E-41)
            if (r2 <= r5) goto L33
            if (r2 < r7) goto L58
        L33:
            r5 = 55203(0xd7a3, float:7.7356E-41)
            if (r2 <= r5) goto L39
            goto L58
        L39:
            if (r2 < r7) goto L40
            int r2 = r2 - r7
            int r2 = r2 / 588
            int r2 = r2 + r4
            goto L4f
        L40:
            if (r2 < r6) goto L4f
            int r2 = r2 + (-12593)
            int[] r4 = com.android.providers.contacts.NameLookupBuilder.KOREAN_JAUM_CONVERT_MAP
            int r5 = r4.length
            if (r2 < r5) goto L4a
            goto L58
        L4a:
            r2 = r4[r2]
            if (r2 != 0) goto L4f
            goto L58
        L4f:
            java.lang.StringBuilder r4 = r8.mStringBuilder
            r4.appendCodePoint(r2)
            int r1 = r1 + 1
        L56:
            if (r3 < r0) goto L65
        L58:
            r10 = 1
            if (r1 <= r10) goto L64
            java.lang.StringBuilder r8 = r8.mStringBuilder
            java.lang.String r8 = r8.toString()
            r9.appendName(r8)
        L64:
            return
        L65:
            r2 = r3
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.NameLookupBuilder.appendKoreanNameConsonantsLookup(com.android.providers.contacts.SearchIndexManager$IndexBuilder, java.lang.String):void");
    }

    protected String normalizeName(String str) {
        return NameNormalizer.normalize(str);
    }

    private void insertNameVariants(long j, long j2, int i, int i2, boolean z, boolean z2) {
        if (i == i2) {
            insertNameVariant(j, j2, i2, !z ? 1 : 0, z2);
            return;
        }
        String str = this.mNames[i];
        int i3 = i;
        while (i3 < i2) {
            String[] strArr = this.mNames;
            strArr[i] = strArr[i3];
            strArr[i3] = str;
            insertNameVariants(j, j2, i + 1, i2, z && i3 == i, z2);
            String[] strArr2 = this.mNames;
            strArr2[i3] = strArr2[i];
            strArr2[i] = str;
            i3++;
        }
    }

    private void insertNameVariant(long j, long j2, int i, int i2, boolean z) {
        this.mStringBuilder.setLength(0);
        for (int i3 = 0; i3 < i; i3++) {
            if (i3 != 0) {
                this.mStringBuilder.append('.');
            }
            this.mStringBuilder.append(this.mNames[i3]);
        }
        insertNameLookup(j, j2, i2, this.mStringBuilder.toString());
        if (z) {
            insertCollationKey(j, j2, i);
        }
    }

    private void insertCollationKey(long j, long j2, int i) {
        this.mStringBuilder.setLength(0);
        for (int i2 = 0; i2 < i; i2++) {
            this.mStringBuilder.append(this.mNames[i2]);
        }
        insertNameLookup(j, j2, 2, this.mStringBuilder.toString());
    }

    private void insertNicknamePermutations(long j, long j2, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            String[] strArr = this.mNicknameClusters[i3];
            if (strArr != null) {
                String str = this.mNames[i3];
                for (String str2 : strArr) {
                    this.mNames[i3] = str2;
                    insertNameVariants(j, j2, 0, i2, false, false);
                    insertNicknamePermutations(j, j2, i3 + 1, i2);
                }
                this.mNames[i3] = str;
            }
        }
    }

    public void appendNameShorthandLookup(SearchIndexManager.IndexBuilder indexBuilder, String str, int i) {
        Iterator<String> nameLookupKeys = ContactLocaleUtils.getInstance().getNameLookupKeys(str, i);
        if (nameLookupKeys != null) {
            while (nameLookupKeys.hasNext()) {
                indexBuilder.appendName(nameLookupKeys.next());
            }
        }
    }
}
