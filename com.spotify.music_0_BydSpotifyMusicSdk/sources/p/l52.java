package p;

/* JADX INFO: loaded from: classes.dex */
public final class l52 extends j52 {
    public a60 b;
    public final j52 c;
    public final String d;
    public final String e;

    public l52(a60 a60Var, nx2 nx2Var, String str, String str2) {
        super(nx2Var.a);
        this.b = a60Var;
        this.c = nx2Var;
        this.d = str;
        this.e = str2;
    }

    @Override // p.j52
    public final void c(androidx.sqlite.db.framework.b bVar) {
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0017  */
    @Override // p.j52
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(androidx.sqlite.db.framework.b r5) {
        /*
            r4 = this;
            java.lang.String r0 = "SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'"
            android.database.Cursor r0 = r5.c(r0)
            boolean r1 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L15
            r2 = 0
            if (r1 == 0) goto L17
            int r1 = r0.getInt(r2)     // Catch: java.lang.Throwable -> L15
            if (r1 != 0) goto L17
            r1 = 1
            goto L18
        L15:
            r5 = move-exception
            goto L85
        L17:
            r1 = r2
        L18:
            r3 = 0
            p.ux2.e(r0, r3)
            p.j52 r0 = r4.c
            r0.a(r5)
            if (r1 != 0) goto L44
            p.k52 r1 = r0.h(r5)
            boolean r3 = r1.a
            if (r3 == 0) goto L2c
            goto L44
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Pre-packaged database has an invalid schema: "
            r0.<init>(r2)
            java.lang.Object r1 = r1.b
            java.lang.String r1 = (java.lang.String) r1
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L44:
            r4.i(r5)
            p.nx2 r0 = (p.nx2) r0
            int r5 = r0.b
            p.i52 r0 = r0.c
            switch(r5) {
                case 0: goto L6a;
                default: goto L50;
            }
        L50:
            com.spotify.eventsender.eventsender.EventSenderDatabase_Impl r0 = (com.spotify.eventsender.eventsender.EventSenderDatabase_Impl) r0
            java.util.List r5 = r0.f
            if (r5 == 0) goto L84
            int r5 = r5.size()
        L5a:
            if (r2 >= r5) goto L84
            java.util.List r1 = r0.f
            java.lang.Object r1 = r1.get(r2)
            p.zs r1 = (p.zs) r1
            r1.getClass()
            int r2 = r2 + 1
            goto L5a
        L6a:
            androidx.work.impl.WorkDatabase_Impl r0 = (androidx.work.impl.WorkDatabase_Impl) r0
            java.util.List r5 = r0.f
            if (r5 == 0) goto L84
            int r5 = r5.size()
        L74:
            if (r2 >= r5) goto L84
            java.util.List r1 = r0.f
            java.lang.Object r1 = r1.get(r2)
            p.zs r1 = (p.zs) r1
            r1.getClass()
            int r2 = r2 + 1
            goto L74
        L84:
            return
        L85:
            throw r5     // Catch: java.lang.Throwable -> L86
        L86:
            r1 = move-exception
            p.ux2.e(r0, r5)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.l52.d(androidx.sqlite.db.framework.b):void");
    }

    @Override // p.j52
    public final void e(androidx.sqlite.db.framework.b bVar, int i, int i2) {
        g(bVar, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0018  */
    @Override // p.j52
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(androidx.sqlite.db.framework.b r6) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.l52.f(androidx.sqlite.db.framework.b):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x003b A[EDGE_INSN: B:77:0x003b->B:18:0x003b BREAK  A[LOOP:1: B:12:0x0025->B:80:?], SYNTHETIC] */
    @Override // p.j52
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(androidx.sqlite.db.framework.b r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.l52.g(androidx.sqlite.db.framework.b, int, int):void");
    }

    public final void i(androidx.sqlite.db.framework.b bVar) {
        bVar.w("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        String str = this.d;
        v03.j(str, "hash");
        bVar.w("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '" + str + "')");
    }
}
