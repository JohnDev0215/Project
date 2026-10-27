package b.b.g.n;

import android.content.Context;
import b.b.g.p.h;
import b.b.m.e.g;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.carsettings.ui.activity.AppListActivity;
import java.util.List;

/* JADX INFO: compiled from: AppListPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends g<AppListActivity> {

    /* JADX INFO: renamed from: b.b.g.n.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppListPresenter.java */
    public class RunnableC0049a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f934a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f935b;

        public RunnableC0049a(int i, int i2) {
            this.f934a = i;
            this.f935b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<BydAppInfo> listL = h.l((Context) a.this.getV(), true);
            if (a.this.getV() != null) {
                ((AppListActivity) a.this.getV()).F(this.f934a, this.f935b, listL);
            }
        }
    }

    public void f(int i, int i2) {
        b.b.m.g.b.a(new RunnableC0049a(i, i2));
    }
}
