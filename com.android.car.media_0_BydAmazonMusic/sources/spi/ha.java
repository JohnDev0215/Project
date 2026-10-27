package spi;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: SqlDateTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ha extends nb<Date> {
    public static final a b = new a();
    public final SimpleDateFormat a = new SimpleDateFormat("MMM d, yyyy");

    /* JADX INFO: compiled from: SqlDateTypeAdapter.java */
    public static class a implements ob {
        @Override // spi.ob
        public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
            if (vbVar.a == Date.class) {
                return new ha();
            }
            return null;
        }
    }

    @Override // spi.nb
    public final Date a(o5 o5Var) {
        Date date;
        synchronized (this) {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                date = null;
            } else {
                try {
                    date = new Date(this.a.parse(o5Var.t()).getTime());
                } catch (ParseException e) {
                    throw new r5(e);
                }
            }
        }
        return date;
    }

    @Override // spi.nb
    public final void b(v5 v5Var, Date date) {
        Date date2 = date;
        synchronized (this) {
            v5Var.q(date2 == null ? null : this.a.format((java.util.Date) date2));
        }
    }
}
