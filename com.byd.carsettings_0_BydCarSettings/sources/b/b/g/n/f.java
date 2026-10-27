package b.b.g.n;

import android.content.Context;
import b.b.g.p.a;
import b.b.m.e.g;
import com.byd.carsettings.ui.activity.StartupAppManageActivity;
import java.util.List;

/* JADX INFO: compiled from: StartupAppManagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends g<StartupAppManageActivity> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.p.a f995a;

    public void c(Context context, b.b.g.p.k.a aVar, boolean z) {
        this.f995a.a(context, aVar, z);
    }

    public List<b.b.g.p.k.a> d() {
        return this.f995a.c(getV().getApplicationContext());
    }

    public void e() {
        b.b.g.p.a aVarA = a.b.a();
        this.f995a = aVarA;
        aVarA.e(getV().getApplicationContext());
    }
}
