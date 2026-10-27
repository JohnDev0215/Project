package b.b.g.g;

import androidx.core.app.NotificationCompat;
import b.b.h.f.b;
import com.byd.carsettings.db.model.MaintainanceRecord;
import java.util.List;

/* JADX INFO: compiled from: MaintainanceRecordDbHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends b.b.g.g.a<MaintainanceRecord> {

    /* JADX INFO: compiled from: MaintainanceRecordDbHelper.java */
    public class a implements b.InterfaceC0077b {
        public a() {
        }

        @Override // b.b.h.f.b.InterfaceC0077b
        public void a(b.b.h.f.b bVar, int i, int i2) {
            b.b.m.c.a.a("MaintainanceRecordDbHelper", "---createDb---onUpgrade---db = " + bVar + ", oldVersion = " + i + ", newVersion = " + i2);
        }
    }

    /* JADX INFO: compiled from: MaintainanceRecordDbHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static e f798a = new e(null);

        public static e a() {
            return f798a;
        }
    }

    public /* synthetic */ e(a aVar) {
        this();
    }

    public void m() {
        if (this.f762d) {
            return;
        }
        b.b.h.d dVar = new b.b.h.d();
        dVar.h(MaintainanceRecord.class);
        dVar.i("MaintainanceRecord");
        dVar.j(1);
        dVar.l(new a());
        super.b(dVar);
    }

    public List<MaintainanceRecord> n(int i) {
        return j(NotificationCompat.MessagingStyle.Message.KEY_DATA_MIME_TYPE, "" + i, "date", true, 10);
    }

    public void o(MaintainanceRecord maintainanceRecord) {
        l(maintainanceRecord, null);
    }

    public e() {
    }
}
