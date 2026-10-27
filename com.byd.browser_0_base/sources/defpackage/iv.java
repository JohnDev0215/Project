package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: CommandLine.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class iv {
    public static final AtomicReference<iv> a = new AtomicReference<>();

    /* JADX INFO: compiled from: CommandLine.java */
    public static class b extends iv {
        public b(String[] strArr) {
            N.MDkrKi31(strArr);
        }

        @Override // defpackage.iv
        public final void a(String str) {
            N.M5K_ewhl(str);
        }

        @Override // defpackage.iv
        public final void b(String str, String str2) {
            if (str2 == null) {
                str2 = "";
            }
            N.MUoYiNbY(str, str2);
        }

        @Override // defpackage.iv
        public final void c() {
            throw new IllegalStateException("Can't destroy native command line after startup");
        }

        @Override // defpackage.iv
        public final String[] d() {
            return null;
        }

        @Override // defpackage.iv
        public final String f(String str) {
            return N.MZJ2lrZY(str);
        }

        @Override // defpackage.iv
        public final boolean g(String str) {
            return N.MsCvypjU(str);
        }
    }

    public static iv e() {
        return a.get();
    }

    public static void h(String[] strArr) {
        iv andSet = a.getAndSet(new a(strArr));
        if (andSet != null) {
            andSet.c();
        }
    }

    public abstract void a(String str);

    public abstract void b(String str, String str2);

    public abstract void c();

    public abstract String[] d();

    public abstract String f(String str);

    public abstract boolean g(String str);

    /* JADX INFO: compiled from: CommandLine.java */
    public static class a extends iv {
        public final HashMap<String, String> b = new HashMap<>();
        public final ArrayList<String> c;
        public int d;

        public a(String[] strArr) {
            String str;
            ArrayList<String> arrayList = new ArrayList<>();
            this.c = arrayList;
            this.d = 1;
            if (strArr == null || strArr.length == 0 || (str = strArr[0]) == null) {
                arrayList.add("");
                return;
            }
            arrayList.add(str);
            int i = 1;
            boolean z = true;
            for (String str2 : strArr) {
                if (i > 0) {
                    i--;
                } else {
                    z = str2.equals("--") ? false : z;
                    if (z && str2.startsWith("--")) {
                        String[] strArrSplit = str2.split("=", 2);
                        b(strArrSplit[0].substring(2), strArrSplit.length > 1 ? strArrSplit[1] : null);
                    } else {
                        this.c.add(str2);
                    }
                }
            }
        }

        @Override // defpackage.iv
        public final void a(String str) {
            b(str, null);
        }

        @Override // defpackage.iv
        public final void b(String str, String str2) {
            this.b.put(str, str2 == null ? "" : str2);
            String strK = "--" + str;
            if (str2 != null && !str2.isEmpty()) {
                strK = C0175b.k(strK, "=", str2);
            }
            int i = this.d;
            this.d = i + 1;
            this.c.add(i, strK);
        }

        @Override // defpackage.iv
        public final String[] d() {
            ArrayList<String> arrayList = this.c;
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        @Override // defpackage.iv
        public final String f(String str) {
            String str2 = this.b.get(str);
            if (str2 == null || str2.isEmpty()) {
                return null;
            }
            return str2;
        }

        @Override // defpackage.iv
        public final boolean g(String str) {
            return this.b.containsKey(str);
        }

        @Override // defpackage.iv
        public final void c() {
        }
    }
}
