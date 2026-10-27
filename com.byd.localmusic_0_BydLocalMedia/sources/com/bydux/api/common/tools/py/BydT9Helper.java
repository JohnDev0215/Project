package com.bydux.api.common.tools.py;

import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes5.dex */
public class BydT9Helper {
    private static final String TAG = "BydT9Helper";

    /* JADX WARN: Removed duplicated region for block: B:10:0x0016 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0019 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001c A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:5:0x0007 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x000a A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000d A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0010 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0013 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static char getT9Char(char r0) {
        /*
            switch(r0) {
                case 65: goto L1c;
                case 66: goto L1c;
                case 67: goto L1c;
                case 68: goto L19;
                case 69: goto L19;
                case 70: goto L19;
                case 71: goto L16;
                case 72: goto L16;
                case 73: goto L16;
                case 74: goto L13;
                case 75: goto L13;
                case 76: goto L13;
                case 77: goto L10;
                case 78: goto L10;
                case 79: goto L10;
                case 80: goto Ld;
                case 81: goto Ld;
                case 82: goto Ld;
                case 83: goto Ld;
                case 84: goto La;
                case 85: goto La;
                case 86: goto La;
                case 87: goto L7;
                case 88: goto L7;
                case 89: goto L7;
                case 90: goto L7;
                default: goto L3;
            }
        L3:
            switch(r0) {
                case 97: goto L1c;
                case 98: goto L1c;
                case 99: goto L1c;
                case 100: goto L19;
                case 101: goto L19;
                case 102: goto L19;
                case 103: goto L16;
                case 104: goto L16;
                case 105: goto L16;
                case 106: goto L13;
                case 107: goto L13;
                case 108: goto L13;
                case 109: goto L10;
                case 110: goto L10;
                case 111: goto L10;
                case 112: goto Ld;
                case 113: goto Ld;
                case 114: goto Ld;
                case 115: goto Ld;
                case 116: goto La;
                case 117: goto La;
                case 118: goto La;
                case 119: goto L7;
                case 120: goto L7;
                case 121: goto L7;
                case 122: goto L7;
                default: goto L6;
            }
        L6:
            goto L1e
        L7:
            r0 = 57
            goto L1e
        La:
            r0 = 56
            goto L1e
        Ld:
            r0 = 55
            goto L1e
        L10:
            r0 = 54
            goto L1e
        L13:
            r0 = 53
            goto L1e
        L16:
            r0 = 52
            goto L1e
        L19:
            r0 = 51
            goto L1e
        L1c:
            r0 = 50
        L1e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.tools.py.BydT9Helper.getT9Char(char):char");
    }

    private BydT9Helper() {
    }

    public static String[] getT9Code(String content) {
        String[] t9Code = getT9Code(BydPyHelper.getPinyin(content));
        BydLogHelper.logV(TAG, "getT9Code", "content=" + content + ", result=" + t9Code);
        return t9Code;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] getT9Code(java.lang.String[] r8) {
        /*
            if (r8 == 0) goto L7e
            int r0 = r8.length
            if (r0 != 0) goto L7
            goto L7e
        L7:
            int r0 = r8.length
            java.lang.String[] r0 = new java.lang.String[r0]
            r1 = 0
            r2 = r1
        Lc:
            int r3 = r8.length
            if (r2 >= r3) goto L65
            r3 = r8[r2]
            java.lang.String r4 = "\\|"
            java.lang.String[] r3 = r3.split(r4)
            java.lang.String r4 = "|"
            if (r3 == 0) goto L46
            int r5 = r3.length
            r6 = 1
            if (r5 > r6) goto L20
            goto L46
        L20:
            r5 = r3[r1]
            r0[r2] = r5
        L24:
            int r5 = r3.length
            if (r6 >= r5) goto L62
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r7 = r0[r2]
            r5.append(r7)
            r5.append(r4)
            r7 = r3[r6]
            java.lang.String r7 = getT9Number(r7)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            r0[r2] = r5
            int r6 = r6 + 1
            goto L24
        L46:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r5 = r8[r2]
            r3.append(r5)
            r3.append(r4)
            r4 = r8[r2]
            java.lang.String r4 = getT9Number(r4)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0[r2] = r3
        L62:
            int r2 = r2 + 1
            goto Lc
        L65:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "result="
            r8.append(r1)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            java.lang.String r1 = "BydT9Helper"
            java.lang.String r2 = "getT9Code"
            com.bydux.api.common.tools.log.BydLogHelper.logV(r1, r2, r8)
            return r0
        L7e:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.tools.py.BydT9Helper.getT9Code(java.lang.String[]):java.lang.String[]");
    }

    private static String getT9Number(String py) {
        if (py == null || py.equals("")) {
            return "";
        }
        int length = py.length();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.delete(0, stringBuffer.length());
        for (int i = 0; i < length; i++) {
            char t9Char = getT9Char(py.charAt(i));
            if (t9Char != 0) {
                stringBuffer.append(t9Char);
            }
        }
        String str = new String(stringBuffer.toString());
        stringBuffer.delete(0, stringBuffer.length());
        return str;
    }
}
