package spi;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: TimeTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xa extends nb<Time> {
    public static final a b = new a();
    public final SimpleDateFormat a = new SimpleDateFormat("hh:mm:ss a");

    /* JADX INFO: compiled from: TimeTypeAdapter.java */
    public static class a implements ob {
        @Override // spi.ob
        public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
            if (vbVar.a == Time.class) {
                return new xa();
            }
            return null;
        }
    }

    @Override // spi.nb
    public final Time a(o5 o5Var) {
        synchronized (this) {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            try {
                return new Time(this.a.parse(o5Var.t()).getTime());
            } catch (ParseException e) {
                throw new r5(e);
            }
        }
    }

    @Override // spi.nb
    public final void b(v5 v5Var, Time time) {
        Time time2 = time;
        synchronized (this) {
            v5Var.q(time2 == null ? null : this.a.format((Date) time2));
        }
    }
}
