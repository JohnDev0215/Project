package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Cs extends Es {
    public HashMap b;
    public ArrayList c;
    public int d;

    public Cs(String[] strArr) {
        super(null);
        this.b = new HashMap();
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.d = 1;
        if (strArr == null || strArr.length == 0 || strArr[0] == null) {
            this.c.add("");
            return;
        }
        arrayList.add(strArr[0]);
        int i = 1;
        boolean z = true;
        for (String str : strArr) {
            if (i > 0) {
                i--;
            } else {
                z = str.equals("--") ? false : z;
                if (z && str.startsWith("--")) {
                    String[] strArrSplit = str.split("=", 2);
                    b(strArrSplit[0].substring(2), strArrSplit.length > 1 ? strArrSplit[1] : null);
                } else {
                    this.c.add(str);
                }
            }
        }
    }

    @Override // defpackage.Es
    public void a(String str) {
        b(str, null);
    }

    @Override // defpackage.Es
    public void b(String str, String str2) {
        this.b.put(str, str2 == null ? "" : str2);
        String strD = "--" + str;
        if (str2 != null && !str2.isEmpty()) {
            strD = Z7.d(strD, "=", str2);
        }
        ArrayList arrayList = this.c;
        int i = this.d;
        this.d = i + 1;
        arrayList.add(i, strD);
    }

    @Override // defpackage.Es
    public String[] d() {
        ArrayList arrayList = this.c;
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // defpackage.Es
    public String f(String str) {
        String str2 = (String) this.b.get(str);
        if (str2 == null || str2.isEmpty()) {
            return null;
        }
        return str2;
    }

    @Override // defpackage.Es
    public Map g() {
        return new HashMap(this.b);
    }

    @Override // defpackage.Es
    public boolean h(String str) {
        return this.b.containsKey(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0049  */
    @Override // defpackage.Es
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void k(java.lang.String r5) {
        /*
            r4 = this;
            java.util.HashMap r0 = r4.b
            r0.remove(r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "--"
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            int r0 = r4.d
            int r0 = r0 + (-1)
        L1a:
            if (r0 <= 0) goto L57
            java.util.ArrayList r1 = r4.c
            java.lang.Object r1 = r1.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L49
            java.util.ArrayList r1 = r4.c
            java.lang.Object r1 = r1.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r5)
            java.lang.String r3 = "="
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            boolean r1 = r1.startsWith(r2)
            if (r1 == 0) goto L54
        L49:
            int r1 = r4.d
            int r1 = r1 + (-1)
            r4.d = r1
            java.util.ArrayList r1 = r4.c
            r1.remove(r0)
        L54:
            int r0 = r0 + (-1)
            goto L1a
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Cs.k(java.lang.String):void");
    }
}
