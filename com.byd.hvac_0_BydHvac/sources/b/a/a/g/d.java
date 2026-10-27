package b.a.a.g;

import b.a.a.f.e1;
import b.a.a.f.f1;
import b.a.a.f.g1;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d extends b.a.a.b.c<b.a.a.d.a> implements b.a.a.b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g1 f219b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f1 f220c;
    public e1 d;

    @Override // b.a.a.b.c, b.a.a.b.a
    public void a() {
        super.a();
        EventBus.getDefault().unregister(this);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(b.a.a.d.a aVar) {
        super.d(aVar);
        EventBus.getDefault().register(this);
        this.f220c = f1.l0();
        this.d = e1.a();
        this.f219b = g1.e();
    }

    public int g() {
        return this.f220c.m1();
    }

    public int h() {
        return this.f219b.m();
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
