package com.google.gson.internal.sql;

import com.google.gson.JsonSyntaxException;
import com.google.gson.b;
import com.google.gson.stream.JsonToken;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import p.lo2;
import p.qo2;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
final class SqlDateTypeAdapter extends b {
    public static final lo2 b = new lo2() { // from class: com.google.gson.internal.sql.SqlDateTypeAdapter.1
        @Override // p.lo2
        public final b a(com.google.gson.a aVar, qo2 qo2Var) {
            if (qo2Var.a == Date.class) {
                return new SqlDateTypeAdapter(0);
            }
            return null;
        }
    };
    public final SimpleDateFormat a;

    public /* synthetic */ SqlDateTypeAdapter(int i) {
        this();
    }

    @Override // com.google.gson.b
    public final Object b(t71 t71Var) {
        synchronized (this) {
            if (t71Var.K0() == JsonToken.q) {
                t71Var.z0();
                return null;
            }
            try {
                return new Date(this.a.parse(t71Var.u()).getTime());
            } catch (ParseException e) {
                throw new JsonSyntaxException(e);
            }
        }
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) {
        Date date = (Date) obj;
        synchronized (this) {
            z71Var.L0(date == null ? null : this.a.format((java.util.Date) date));
        }
    }

    private SqlDateTypeAdapter() {
        this.a = new SimpleDateFormat("MMM d, yyyy");
    }
}
