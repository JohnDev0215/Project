package defpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.SparseArray;
import java.util.ArrayList;

/* JADX INFO: compiled from: SkinCompatManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class no2 extends ph1 {
    public static volatile no2 o;
    public final Object f;
    public final Context g;
    public boolean h;
    public final ArrayList i;
    public final ArrayList j;
    public final ArrayList k;
    public final SparseArray<b> l;
    public final boolean m;
    public boolean n;

    /* JADX INFO: compiled from: SkinCompatManager.java */
    public interface b {
        String a(int i, Context context, String str);

        String b(Context context, String str);

        void c();

        void d();

        void e();

        int getType();
    }

    public no2(Context context) {
        super(6);
        this.f = new Object();
        this.h = false;
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        SparseArray<b> sparseArray = new SparseArray<>();
        this.l = sparseArray;
        this.m = true;
        this.n = true;
        this.g = context.getApplicationContext();
        sparseArray.put(-1, new dp2());
        sparseArray.put(0, new do2());
        sparseArray.put(1, new eo2());
        sparseArray.put(2, new gp2());
    }

    public final void f(bp2 bp2Var) {
        if (bp2Var instanceof lp2) {
            this.i.add((lp2) bp2Var);
        }
        this.j.add(bp2Var);
    }

    public final AsyncTask h(String str, int i) {
        b bVar = this.l.get(i);
        if (bVar == null) {
            return null;
        }
        return new a(bVar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, str);
    }

    /* JADX INFO: compiled from: SkinCompatManager.java */
    public class a extends AsyncTask<String, Void, String> {
        public final b a;

        public a(b bVar) {
            this.a = bVar;
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x007c, code lost:
        
            r5 = defpackage.po2.d();
            r5.getClass();
            r5.f(defpackage.no2.o.l.get(-1));
            r5 = null;
         */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String doInBackground(java.lang.String[] r6) {
            /*
                r5 = this;
                java.lang.String[] r6 = (java.lang.String[]) r6
                no2 r0 = defpackage.no2.this
                java.lang.Object r0 = r0.f
                monitor-enter(r0)
            L7:
                no2 r1 = defpackage.no2.this     // Catch: java.lang.Throwable -> L7d
                boolean r2 = r1.h     // Catch: java.lang.Throwable -> L7d
                if (r2 == 0) goto L2b
                java.lang.Object r1 = r1.f     // Catch: java.lang.InterruptedException -> L13 java.lang.Throwable -> L7d
                r1.wait()     // Catch: java.lang.InterruptedException -> L13 java.lang.Throwable -> L7d
                goto L7
            L13:
                r1 = move-exception
                java.lang.String r2 = "SkinCompatManager"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7d
                r3.<init>()     // Catch: java.lang.Throwable -> L7d
                java.lang.String r4 = "doInBackground1: "
                r3.append(r4)     // Catch: java.lang.Throwable -> L7d
                r3.append(r1)     // Catch: java.lang.Throwable -> L7d
                java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L7d
                android.util.Log.e(r2, r1)     // Catch: java.lang.Throwable -> L7d
                goto L7
            L2b:
                r2 = 1
                r1.h = r2     // Catch: java.lang.Throwable -> L7d
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L7d
                int r0 = r6.length     // Catch: java.lang.Exception -> L52
                if (r0 != r2) goto L66
                no2$b r0 = r5.a     // Catch: java.lang.Exception -> L52
                android.content.Context r1 = r1.g     // Catch: java.lang.Exception -> L52
                r2 = 0
                r3 = r6[r2]     // Catch: java.lang.Exception -> L52
                java.lang.String r0 = r0.b(r1, r3)     // Catch: java.lang.Exception -> L52
                boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L52
                if (r0 == 0) goto L4f
                po2 r6 = defpackage.po2.d()     // Catch: java.lang.Exception -> L52
                no2$b r5 = r5.a     // Catch: java.lang.Exception -> L52
                r6.f(r5)     // Catch: java.lang.Exception -> L52
                java.lang.String r5 = ""
                goto L7c
            L4f:
                r5 = r6[r2]     // Catch: java.lang.Exception -> L52
                goto L7c
            L52:
                r5 = move-exception
                java.lang.String r6 = "SkinCompatManager"
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "doInBackground2: "
                r0.<init>(r1)
                r0.append(r5)
                java.lang.String r5 = r0.toString()
                android.util.Log.e(r6, r5)
            L66:
                po2 r5 = defpackage.po2.d()
                r5.getClass()
                no2 r6 = defpackage.no2.o
                android.util.SparseArray<no2$b> r6 = r6.l
                r0 = -1
                java.lang.Object r6 = r6.get(r0)
                no2$b r6 = (no2.b) r6
                r5.f(r6)
                r5 = 0
            L7c:
                return r5
            L7d:
                r5 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L7d
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: no2.a.doInBackground(java.lang.Object[]):java.lang.Object");
        }

        @Override // android.os.AsyncTask
        public final void onPostExecute(String str) {
            ep2[] ep2VarArr;
            String str2 = str;
            synchronized (no2.this.f) {
                if (str2 != null) {
                    fp2 fp2Var = fp2.c;
                    fp2Var.b.putString("skin-name", str2);
                    fp2Var.b.putInt("skin-strategy", this.a.getType());
                    fp2Var.b.apply();
                    no2 no2Var = no2.this;
                    synchronized (no2Var) {
                        Object obj = no2Var.e;
                        ep2VarArr = (ep2[]) ((ArrayList) obj).toArray(new ep2[((ArrayList) obj).size()]);
                    }
                    int length = ep2VarArr.length;
                    while (true) {
                        length--;
                        if (length < 0) {
                            break;
                        } else {
                            ep2VarArr[length].a();
                        }
                    }
                } else {
                    fp2 fp2Var2 = fp2.c;
                    fp2Var2.b.putString("skin-name", "");
                    fp2Var2.b.putInt("skin-strategy", -1);
                    fp2Var2.b.apply();
                }
                no2 no2Var2 = no2.this;
                no2Var2.h = false;
                no2Var2.f.notifyAll();
            }
        }

        @Override // android.os.AsyncTask
        public final void onPreExecute() {
        }
    }
}
