package b.b.g.n.j;

import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.carsettings.model.dilink.PermissionType;
import java.util.List;

/* JADX INFO: compiled from: AppPermissionsPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends b.b.m.e.g<b.b.g.q.f.b.d> {

    /* JADX INFO: compiled from: AppPermissionsPresenter.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1063a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1064b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1065c;

        public a(String str, int i, int i2) {
            this.f1063a = str;
            this.f1064b = i;
            this.f1065c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BydAppInfo bydAppInfoK = b.b.g.p.h.k(((b.b.g.q.f.b.d) b.this.getV()).getContext(), this.f1063a);
            if (b.this.getV() != null) {
                ((b.b.g.q.f.b.d) b.this.getV()).sendMessage(this.f1064b, this.f1065c, bydAppInfoK);
            }
        }
    }

    /* JADX INFO: renamed from: b.b.g.n.j.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppPermissionsPresenter.java */
    public class RunnableC0053b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1067a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1068b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1069c;

        public RunnableC0053b(String str, int i, int i2) {
            this.f1067a = str;
            this.f1068b = i;
            this.f1069c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<b.b.g.m.d.c> listH = b.b.g.p.h.h(((b.b.g.q.f.b.d) b.this.getV()).getContext(), this.f1067a);
            if (b.this.getV() != null) {
                ((b.b.g.q.f.b.d) b.this.getV()).sendMessage(this.f1068b, this.f1069c, listH);
            }
        }
    }

    public void i(String str, int i, int i2) {
        b.b.m.g.b.a(new a(str, i, i2));
    }

    public void j(String str, int i, int i2) {
        b.b.m.g.b.a(new RunnableC0053b(str, i, i2));
    }

    public void l(String str, PermissionType permissionType, List<String> list, boolean z) {
        b.b.g.p.h.v(getV().getContext(), str, permissionType, list, z);
    }
}
