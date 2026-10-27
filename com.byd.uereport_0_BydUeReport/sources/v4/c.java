package v4;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import com.byd.trafficmonitor.model.TrafficInfo;
import d1.l;
import d1.o;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TrafficInfoDao_Impl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements v4.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RoomDatabase f8571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d1.c<TrafficInfo> f8572b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d1.c<TrafficInfo> f8573c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d1.b<TrafficInfo> f8574d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d1.b<TrafficInfo> f8575e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final o f8576f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o f8577g;

    /* JADX INFO: compiled from: TrafficInfoDao_Impl.java */
    public class a extends d1.c<TrafficInfo> {
        public a(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "INSERT OR REPLACE INTO `traffic_data` (`package_name`,`app_name`,`uid`,`is_system_app`,`enable_network`,`mobile_rx_byte`,`mobile_tx_byte`) VALUES (?,?,?,?,?,?,?)";
        }

        @Override // d1.c
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(h1.f fVar, TrafficInfo trafficInfo) {
            if (trafficInfo.l() == null) {
                fVar.z(1);
            } else {
                fVar.l(1, trafficInfo.l());
            }
            if (trafficInfo.g() == null) {
                fVar.z(2);
            } else {
                fVar.l(2, trafficInfo.g());
            }
            fVar.U(3, trafficInfo.m());
            fVar.U(4, trafficInfo.i());
            fVar.U(5, trafficInfo.h());
            fVar.U(6, trafficInfo.j());
            fVar.U(7, trafficInfo.k());
        }
    }

    /* JADX INFO: compiled from: TrafficInfoDao_Impl.java */
    public class b extends d1.c<TrafficInfo> {
        public b(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "INSERT OR ABORT INTO `traffic_data` (`package_name`,`app_name`,`uid`,`is_system_app`,`enable_network`,`mobile_rx_byte`,`mobile_tx_byte`) VALUES (?,?,?,?,?,?,?)";
        }

        @Override // d1.c
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(h1.f fVar, TrafficInfo trafficInfo) {
            if (trafficInfo.l() == null) {
                fVar.z(1);
            } else {
                fVar.l(1, trafficInfo.l());
            }
            if (trafficInfo.g() == null) {
                fVar.z(2);
            } else {
                fVar.l(2, trafficInfo.g());
            }
            fVar.U(3, trafficInfo.m());
            fVar.U(4, trafficInfo.i());
            fVar.U(5, trafficInfo.h());
            fVar.U(6, trafficInfo.j());
            fVar.U(7, trafficInfo.k());
        }
    }

    /* JADX INFO: renamed from: v4.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TrafficInfoDao_Impl.java */
    public class C0134c extends d1.b<TrafficInfo> {
        public C0134c(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "DELETE FROM `traffic_data` WHERE `uid` = ?";
        }

        @Override // d1.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(h1.f fVar, TrafficInfo trafficInfo) {
            fVar.U(1, trafficInfo.m());
        }
    }

    /* JADX INFO: compiled from: TrafficInfoDao_Impl.java */
    public class d extends d1.b<TrafficInfo> {
        public d(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "UPDATE OR ABORT `traffic_data` SET `package_name` = ?,`app_name` = ?,`uid` = ?,`is_system_app` = ?,`enable_network` = ?,`mobile_rx_byte` = ?,`mobile_tx_byte` = ? WHERE `uid` = ?";
        }

        @Override // d1.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(h1.f fVar, TrafficInfo trafficInfo) {
            if (trafficInfo.l() == null) {
                fVar.z(1);
            } else {
                fVar.l(1, trafficInfo.l());
            }
            if (trafficInfo.g() == null) {
                fVar.z(2);
            } else {
                fVar.l(2, trafficInfo.g());
            }
            fVar.U(3, trafficInfo.m());
            fVar.U(4, trafficInfo.i());
            fVar.U(5, trafficInfo.h());
            fVar.U(6, trafficInfo.j());
            fVar.U(7, trafficInfo.k());
            fVar.U(8, trafficInfo.m());
        }
    }

    /* JADX INFO: compiled from: TrafficInfoDao_Impl.java */
    public class e extends o {
        public e(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "UPDATE traffic_data SET enable_network = ? WHERE uid = ?";
        }
    }

    /* JADX INFO: compiled from: TrafficInfoDao_Impl.java */
    public class f extends o {
        public f(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "DELETE FROM traffic_data";
        }
    }

    public c(RoomDatabase roomDatabase) {
        this.f8571a = roomDatabase;
        this.f8572b = new a(roomDatabase);
        this.f8573c = new b(roomDatabase);
        this.f8574d = new C0134c(roomDatabase);
        this.f8575e = new d(roomDatabase);
        this.f8576f = new e(roomDatabase);
        this.f8577g = new f(roomDatabase);
    }

    @Override // v4.b
    public int a() {
        this.f8571a.b();
        h1.f fVarA = this.f8577g.a();
        this.f8571a.c();
        try {
            int iS = fVarA.s();
            this.f8571a.r();
            return iS;
        } finally {
            this.f8571a.g();
            this.f8577g.f(fVarA);
        }
    }

    @Override // v4.b
    public void b(int i8, int i9) {
        this.f8571a.b();
        h1.f fVarA = this.f8576f.a();
        fVarA.U(1, i9);
        fVarA.U(2, i8);
        this.f8571a.c();
        try {
            fVarA.s();
            this.f8571a.r();
        } finally {
            this.f8571a.g();
            this.f8576f.f(fVarA);
        }
    }

    @Override // v4.b
    public void c(TrafficInfo trafficInfo) {
        this.f8571a.b();
        this.f8571a.c();
        try {
            this.f8575e.h(trafficInfo);
            this.f8571a.r();
        } finally {
            this.f8571a.g();
        }
    }

    @Override // v4.b
    public List<TrafficInfo> d() {
        l lVarI = l.i("SELECT * FROM traffic_data ORDER BY mobile_rx_byte DESC", 0);
        this.f8571a.b();
        Cursor cursorB = f1.c.b(this.f8571a, lVarI, false, null);
        try {
            int iB = f1.b.b(cursorB, "package_name");
            int iB2 = f1.b.b(cursorB, "app_name");
            int iB3 = f1.b.b(cursorB, "uid");
            int iB4 = f1.b.b(cursorB, "is_system_app");
            int iB5 = f1.b.b(cursorB, "enable_network");
            int iB6 = f1.b.b(cursorB, "mobile_rx_byte");
            int iB7 = f1.b.b(cursorB, "mobile_tx_byte");
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                TrafficInfo trafficInfo = new TrafficInfo();
                trafficInfo.s(cursorB.getString(iB));
                trafficInfo.n(cursorB.getString(iB2));
                trafficInfo.t(cursorB.getInt(iB3));
                trafficInfo.p(cursorB.getInt(iB4));
                trafficInfo.o(cursorB.getInt(iB5));
                trafficInfo.q(cursorB.getLong(iB6));
                trafficInfo.r(cursorB.getLong(iB7));
                arrayList.add(trafficInfo);
            }
            return arrayList;
        } finally {
            cursorB.close();
            lVarI.n();
        }
    }

    @Override // v4.b
    public void e(TrafficInfo trafficInfo) {
        this.f8571a.b();
        this.f8571a.c();
        try {
            this.f8572b.h(trafficInfo);
            this.f8571a.r();
        } finally {
            this.f8571a.g();
        }
    }
}
