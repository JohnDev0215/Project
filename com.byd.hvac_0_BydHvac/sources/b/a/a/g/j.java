package b.a.a.g;

import b.a.a.f.e1;
import b.a.a.f.f1;
import b.a.a.f.g1;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class j extends b.a.a.b.c<b.a.a.d.g> implements b.a.a.b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g1 f231b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f1 f232c;
    public e1 d;

    @Override // b.a.a.b.c, b.a.a.b.a
    public void a() {
        super.a();
        EventBus.getDefault().unregister(this);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(b.a.a.d.g gVar) {
        super.d(gVar);
        EventBus.getDefault().register(this);
        this.f231b = g1.e();
        this.f232c = f1.l0();
        this.d = e1.a();
    }

    public boolean g() {
        return this.d.b();
    }

    public int h() {
        return this.f231b.a();
    }

    public int i() {
        return this.f232c.c1();
    }

    public int j() {
        return this.f232c.m1();
    }

    public boolean k() {
        return this.f231b.i();
    }

    public int l() {
        return this.f231b.j();
    }

    public int m() {
        return this.f231b.t();
    }

    public int n(int i) {
        return this.f232c.q1(i);
    }

    public void o(int i) {
        this.d.c(i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.a.a.e.a aVar) {
        int iC = aVar.c();
        if (iC == 1068) {
            e().b(((Boolean) aVar.d()).booleanValue());
        } else {
            if (iC != 1073) {
                return;
            }
            e().a(((Boolean) aVar.d()).booleanValue());
        }
    }
}
