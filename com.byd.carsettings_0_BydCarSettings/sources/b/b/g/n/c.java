package b.b.g.n;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import b.b.g.h.a;
import b.b.g.p.d;
import b.b.g.p.f;
import b.b.g.r.j;
import b.b.m.e.g;
import com.byd.carsettings.MainActivity;
import com.byd.carsettings.db.model.MaintainMileBean;
import com.byd.carsettings.db.model.MaintainTimeBean;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MainPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends g<MainActivity> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.h.a f976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f977b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f978c;

    /* JADX INFO: compiled from: MainPresenter.java */
    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                c.this.f977b = true;
                c.this.f976a = a.AbstractBinderC0044a.d0(iBinder);
            } catch (Exception e2) {
                BydLogHelper.logE("----", e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            c.this.f977b = false;
        }
    }

    /* JADX INFO: renamed from: b.b.g.n.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MainPresenter.java */
    public class C0051c implements b.b.g.p.j.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<c> f980a;

        public C0051c(c cVar) {
            this.f980a = new WeakReference<>(cVar);
        }

        @Override // b.b.g.p.j.c
        public void onUpdateFlag(boolean z) {
            WeakReference<c> weakReference = this.f980a;
            if (weakReference == null || weakReference.get() == null || this.f980a.get().getV() == null) {
                return;
            }
            ((MainActivity) this.f980a.get().getV()).d0(z);
        }
    }

    public final void g() {
        try {
            this.f978c = new b();
            if (getV() != null) {
                Intent intent = new Intent();
                intent.setAction("com.byd.aidl.dialog");
                intent.setPackage("com.byd.uereport");
                getV().bindService(intent, this.f978c, 1);
            } else {
                BydLogHelper.logE("----", "bindService----getV() == null");
            }
        } catch (Exception e2) {
            BydLogHelper.logE("----", "bindService----" + e2.getMessage());
        }
    }

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public void bindV(MainActivity mainActivity) {
        super.bindV(mainActivity);
        EventBus.getDefault().register(this);
        g();
    }

    public boolean i() {
        return f.c.a().g();
    }

    public void j() {
        f.c.a().h(getV().getApplicationContext());
        f.c.a().l(c.class.getName(), new C0051c(this));
    }

    public final void l() {
        try {
            this.f977b = false;
            if (getV() != null) {
                getV().unbindService(this.f978c);
            } else {
                BydLogHelper.logE("----", "unbindService----getV() == null");
            }
            this.f978c = null;
            this.f976a = null;
        } catch (Exception e2) {
            BydLogHelper.logE("----", "unbindService----" + e2.getMessage());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() != null && aVar.h() == 200009) {
            try {
                if (this.f976a != null) {
                    MaintainTimeBean maintainTimeBean = new MaintainTimeBean();
                    maintainTimeBean.e(90);
                    maintainTimeBean.f(false);
                    maintainTimeBean.g(-1L);
                    this.f976a.A(maintainTimeBean);
                    MaintainMileBean maintainMileBean = new MaintainMileBean();
                    maintainMileBean.e(j.c());
                    maintainMileBean.f(false);
                    maintainMileBean.g(-1.0d);
                    this.f976a.E(maintainMileBean);
                }
            } catch (Exception e2) {
                BydLogHelper.logE("----", e2.getMessage());
            }
        }
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        l();
        super.unbindV();
        EventBus.getDefault().unregister(this);
        d.g.a().j0();
        f.c.a().m();
        f.c.a().p(c.class.getName());
    }
}
