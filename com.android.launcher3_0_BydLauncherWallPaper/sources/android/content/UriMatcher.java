package android.content;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class UriMatcher {
    private static final int EXACT = 0;
    public static final int NO_MATCH = -1;
    private static final int NUMBER = 1;
    private static final int TEXT = 2;
    private ArrayList<UriMatcher> mChildren;
    private int mCode;
    private final String mText;
    private final int mWhich;

    public UriMatcher(int i) {
        this.mCode = i;
        this.mWhich = -1;
        this.mChildren = new ArrayList<>();
        this.mText = null;
    }

    private UriMatcher(int i, String str) {
        this.mCode = -1;
        this.mWhich = i;
        this.mChildren = new ArrayList<>();
        this.mText = str;
    }

    public void addURI(String str, String str2, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("code " + i + " is invalid: it must be positive");
        }
        String[] strArrSplit = null;
        if (str2 != null) {
            if (str2.length() > 1 && str2.charAt(0) == '/') {
                str2 = str2.substring(1);
            }
            strArrSplit = str2.split("/");
        }
        int length = strArrSplit != null ? strArrSplit.length : 0;
        int i2 = -1;
        while (i2 < length) {
            String str3 = i2 < 0 ? str : strArrSplit[i2];
            ArrayList<UriMatcher> arrayList = this.mChildren;
            int size = arrayList.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    break;
                }
                UriMatcher uriMatcher = arrayList.get(i3);
                if (str3.equals(uriMatcher.mText)) {
                    this = uriMatcher;
                    break;
                }
                i3++;
            }
            if (i3 == size) {
                UriMatcher uriMatcherCreateChild = createChild(str3);
                this.mChildren.add(uriMatcherCreateChild);
                this = uriMatcherCreateChild;
            }
            i2++;
        }
        this.mCode = i;
    }

    private static UriMatcher createChild(String str) {
        str.hashCode();
        if (str.equals("#")) {
            return new UriMatcher(1, "#");
        }
        if (str.equals("*")) {
            return new UriMatcher(2, "*");
        }
        return new UriMatcher(0, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0066 A[LOOP:1: B:17:0x0030->B:38:0x0066, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0069 A[EDGE_INSN: B:47:0x0069->B:39:0x0069 BREAK  A[LOOP:1: B:17:0x0030->B:38:0x0066], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int match(android.net.Uri r15) {
        /*
            r14 = this;
            java.util.List r0 = r15.getPathSegments()
            int r1 = r0.size()
            if (r1 != 0) goto L13
            java.lang.String r2 = r15.getAuthority()
            if (r2 != 0) goto L13
            int r14 = r14.mCode
            return r14
        L13:
            r2 = -1
            r3 = r2
        L15:
            if (r3 >= r1) goto L6f
            if (r3 >= 0) goto L1e
            java.lang.String r4 = r15.getAuthority()
            goto L24
        L1e:
            java.lang.Object r4 = r0.get(r3)
            java.lang.String r4 = (java.lang.String) r4
        L24:
            java.util.ArrayList<android.content.UriMatcher> r5 = r14.mChildren
            if (r5 != 0) goto L29
            goto L6f
        L29:
            r14 = 0
            int r6 = r5.size()
            r7 = 0
            r8 = r7
        L30:
            if (r8 >= r6) goto L69
            java.lang.Object r9 = r5.get(r8)
            android.content.UriMatcher r9 = (android.content.UriMatcher) r9
            int r10 = r9.mWhich
            if (r10 == 0) goto L5a
            r11 = 1
            if (r10 == r11) goto L43
            r11 = 2
            if (r10 == r11) goto L62
            goto L63
        L43:
            int r10 = r4.length()
            r11 = r7
        L48:
            if (r11 >= r10) goto L62
            char r12 = r4.charAt(r11)
            r13 = 48
            if (r12 < r13) goto L63
            r13 = 57
            if (r12 <= r13) goto L57
            goto L63
        L57:
            int r11 = r11 + 1
            goto L48
        L5a:
            java.lang.String r10 = r9.mText
            boolean r10 = r10.equals(r4)
            if (r10 == 0) goto L63
        L62:
            r14 = r9
        L63:
            if (r14 == 0) goto L66
            goto L69
        L66:
            int r8 = r8 + 1
            goto L30
        L69:
            if (r14 != 0) goto L6c
            return r2
        L6c:
            int r3 = r3 + 1
            goto L15
        L6f:
            int r14 = r14.mCode
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.UriMatcher.match(android.net.Uri):int");
    }
}
