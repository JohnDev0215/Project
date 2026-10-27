package defpackage;

import android.util.Pair;
import android.view.View;
import java.util.Date;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: ListItem.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f81 {
    public final long a;
    public boolean b;
    public boolean c;

    /* JADX INFO: compiled from: ListItem.java */
    public static class a extends f81 {
        public final Pair<Date, String> d;

        /* JADX WARN: Illegal instructions before constructor call */
        public a(Pair<Date, String> pair, boolean z) {
            int iHashCode = pair.hashCode();
            super(z ? iHashCode : ~iHashCode);
            this.d = pair;
        }
    }

    /* JADX INFO: compiled from: ListItem.java */
    public static class b extends f81 {
        public final int d;

        public b(long j, int i) {
            super(j);
            this.d = i;
        }
    }

    /* JADX INFO: compiled from: ListItem.java */
    public static class c extends a {
        public c(Pair<Date, String> pair) {
            super(pair, false);
        }
    }

    /* JADX INFO: compiled from: ListItem.java */
    public static class d extends a {
        public final String e;

        public d(Pair<Date, String> pair, String str) {
            super(pair, true);
            this.e = str;
        }
    }

    /* JADX INFO: compiled from: ListItem.java */
    public static abstract class e extends f81 {
        public final Date d;

        public e(long j, Date date) {
            super(j);
            this.d = date;
        }
    }

    /* JADX INFO: compiled from: ListItem.java */
    public static class f extends e {
        public OfflineItem e;
        public boolean f;
        public boolean g;

        public f(OfflineItem offlineItem) {
            super((((long) offlineItem.e.hashCode()) << 32) + (offlineItem.q & (-1)), new Date(offlineItem.q));
            this.e = offlineItem;
        }
    }

    /* JADX INFO: compiled from: ListItem.java */
    public static class g extends f81 {
        public g() {
            super(9223372036854775803L);
        }
    }

    /* JADX INFO: compiled from: ListItem.java */
    public static class h extends e {
        public final int e;

        public h(int i, long j) {
            super(i != 0 ? i != 1 ? -1L : 9223372036854775804L : ((long) new Date(j).hashCode()) + 1000, new Date(j));
            this.e = i;
        }
    }

    /* JADX INFO: compiled from: ListItem.java */
    public static class i extends f81 {
        public final View d;

        public i(long j, View view) {
            super(j);
            this.d = view;
        }
    }

    public f81(long j) {
        this.a = j;
    }
}
