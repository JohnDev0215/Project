package com.google.gson.internal.sql;

import com.google.gson.JsonSyntaxException;
import com.google.gson.b;
import com.google.gson.stream.JsonToken;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import p.lo2;
import p.qo2;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
final class SqlTimeTypeAdapter extends b {
    public static final lo2 b = new lo2() { // from class: com.google.gson.internal.sql.SqlTimeTypeAdapter.1
        @Override // p.lo2
        public final b a(com.google.gson.a aVar, qo2 qo2Var) {
            if (qo2Var.a == Time.class) {
                return new SqlTimeTypeAdapter(0);
            }
            return null;
        }
    };
    public final SimpleDateFormat a;

    public /* synthetic */ SqlTimeTypeAdapter(int i) {
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
                return new Time(this.a.parse(t71Var.u()).getTime());
            } catch (ParseException e) {
                throw new JsonSyntaxException(e);
            }
        }
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) {
        Time time = (Time) obj;
        synchronized (this) {
            z71Var.L0(time == null ? null : this.a.format((Date) time));
        }
    }

    private SqlTimeTypeAdapter() {
        this.a = new SimpleDateFormat("hh:mm:ss a");
    }
}
