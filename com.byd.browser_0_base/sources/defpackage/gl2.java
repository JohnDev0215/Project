package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ShareTarget.java */
/* JADX INFO: loaded from: classes.dex */
public final class gl2 {
    public final String a;
    public final String b;
    public final String c;
    public final b d;

    /* JADX INFO: compiled from: ShareTarget.java */
    public static final class a {
        public final String a;
        public final List<String> b;

        public a(String str, ArrayList arrayList) {
            this.a = str;
            this.b = Collections.unmodifiableList(arrayList);
        }
    }

    /* JADX INFO: compiled from: ShareTarget.java */
    public static class b {
        public final String a;
        public final String b;
        public final List<a> c;

        public b(String str, String str2, ArrayList arrayList) {
            this.a = str;
            this.b = str2;
            this.c = arrayList;
        }
    }

    public gl2(String str, String str2, String str3, b bVar) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = bVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.gl2 a(android.os.Bundle r9) {
        /*
            java.lang.String r0 = "androidx.browser.trusted.sharing.KEY_ACTION"
            java.lang.String r0 = r9.getString(r0)
            java.lang.String r1 = "androidx.browser.trusted.sharing.KEY_METHOD"
            java.lang.String r1 = r9.getString(r1)
            java.lang.String r2 = "androidx.browser.trusted.sharing.KEY_ENCTYPE"
            java.lang.String r2 = r9.getString(r2)
            java.lang.String r3 = "androidx.browser.trusted.sharing.KEY_PARAMS"
            android.os.Bundle r9 = r9.getBundle(r3)
            r3 = 0
            if (r9 != 0) goto L1d
            r4 = r3
            goto L6b
        L1d:
            java.lang.String r4 = "androidx.browser.trusted.sharing.KEY_FILES"
            java.util.ArrayList r4 = r9.getParcelableArrayList(r4)
            if (r4 == 0) goto L59
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r4 = r4.iterator()
        L2e:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L5a
            java.lang.Object r6 = r4.next()
            android.os.Bundle r6 = (android.os.Bundle) r6
            if (r6 != 0) goto L3d
            goto L54
        L3d:
            java.lang.String r7 = "androidx.browser.trusted.sharing.KEY_FILE_NAME"
            java.lang.String r7 = r6.getString(r7)
            java.lang.String r8 = "androidx.browser.trusted.sharing.KEY_ACCEPTED_TYPES"
            java.util.ArrayList r6 = r6.getStringArrayList(r8)
            if (r7 == 0) goto L54
            if (r6 != 0) goto L4e
            goto L54
        L4e:
            gl2$a r8 = new gl2$a
            r8.<init>(r7, r6)
            goto L55
        L54:
            r8 = r3
        L55:
            r5.add(r8)
            goto L2e
        L59:
            r5 = r3
        L5a:
            gl2$b r4 = new gl2$b
            java.lang.String r6 = "androidx.browser.trusted.sharing.KEY_TITLE"
            java.lang.String r6 = r9.getString(r6)
            java.lang.String r7 = "androidx.browser.trusted.sharing.KEY_TEXT"
            java.lang.String r9 = r9.getString(r7)
            r4.<init>(r6, r9, r5)
        L6b:
            if (r0 == 0) goto L76
            if (r4 != 0) goto L70
            goto L76
        L70:
            gl2 r9 = new gl2
            r9.<init>(r0, r1, r2, r4)
            return r9
        L76:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gl2.a(android.os.Bundle):gl2");
    }
}
