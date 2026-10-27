package com.android.providers.contacts;

/* JADX INFO: loaded from: classes.dex */
public class ContactLookupKey {

    public static class LookupKeySegment implements Comparable<LookupKeySegment> {
        public int accountHashCode;
        public long contactId;
        public String key;
        public int lookupType;
        public String rawContactId;

        @Override // java.lang.Comparable
        public int compareTo(LookupKeySegment lookupKeySegment) {
            long j = this.contactId;
            long j2 = lookupKeySegment.contactId;
            if (j > j2) {
                return -1;
            }
            return j < j2 ? 1 : 0;
        }
    }

    public static int getAccountHashCode(String str, String str2) {
        if (str == null || str2 == null) {
            return 0;
        }
        return (str.hashCode() ^ str2.hashCode()) & 4095;
    }

    public static void appendToLookupKey(StringBuilder sb, String str, String str2, long j, String str3, String str4) {
        if (str4 == null) {
            str4 = "";
        }
        if (sb.length() != 0) {
            sb.append(".");
        }
        sb.append(getAccountHashCode(str, str2));
        if (str3 == null) {
            sb.append('r');
            sb.append(j);
            sb.append('-');
            sb.append(NameNormalizer.normalize(str4));
            return;
        }
        int length = sb.length();
        sb.append('i');
        if (appendEscapedSourceId(sb, str3)) {
            sb.setCharAt(length, 'e');
        }
    }

    private static boolean appendEscapedSourceId(StringBuilder sb, String str) {
        int i = 0;
        boolean z = false;
        while (true) {
            int iIndexOf = str.indexOf(46, i);
            if (iIndexOf == -1) {
                sb.append((CharSequence) str, i, str.length());
                return z;
            }
            sb.append((CharSequence) str, i, iIndexOf);
            sb.append("..");
            i = iIndexOf + 1;
            z = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x00f3, code lost:
    
        r8 = r3;
        r3 = r9.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<com.android.providers.contacts.ContactLookupKey.LookupKeySegment> parse(java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.ContactLookupKey.parse(java.lang.String):java.util.ArrayList");
    }
}
