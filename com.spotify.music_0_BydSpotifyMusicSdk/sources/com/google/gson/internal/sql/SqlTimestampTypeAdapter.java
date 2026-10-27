package com.google.gson.internal.sql;

import com.google.gson.b;
import java.sql.Timestamp;
import java.util.Date;
import p.lo2;
import p.qo2;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
class SqlTimestampTypeAdapter extends b {
    public static final lo2 b = new lo2() { // from class: com.google.gson.internal.sql.SqlTimestampTypeAdapter.1
        @Override // p.lo2
        public final b a(com.google.gson.a aVar, qo2 qo2Var) {
            if (qo2Var.a != Timestamp.class) {
                return null;
            }
            aVar.getClass();
            return new SqlTimestampTypeAdapter(aVar.d(new qo2(Date.class)));
        }
    };
    public final b a;

    public SqlTimestampTypeAdapter(b bVar) {
        this.a = bVar;
    }

    @Override // com.google.gson.b
    public final Object b(t71 t71Var) {
        Date date = (Date) this.a.b(t71Var);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) {
        this.a.c(z71Var, (Timestamp) obj);
    }
}
