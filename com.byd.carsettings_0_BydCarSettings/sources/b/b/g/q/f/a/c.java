package b.b.g.q.f.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import b.b.m.e.g;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: BaseCommonCar.java */
/* JADX INFO: loaded from: classes2.dex */
public class c<P extends b.b.m.e.g> extends b.b.m.e.e {
    public c(Activity activity) {
        super(activity);
    }

    public void A(int i, b.b.a.c.a aVar) {
    }

    public void B(int i, b.b.a.c.a aVar) {
    }

    public void C(int i, b.b.a.c.a aVar) {
    }

    @Override // b.b.m.e.e
    public void e(Context context) {
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return 0;
    }

    @Override // b.b.m.e.e
    public void k(Context context) {
        EventBus.getDefault().register(this);
    }

    @Override // b.b.m.e.e
    public void l() {
        super.l();
        EventBus.getDefault().unregister(this);
    }

    @Override // b.b.m.e.h
    public b.b.m.e.g newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        int iH = aVar.h();
        if (iH == 70001) {
            b.b.m.c.a.a(this.f2100a, "常用-仪表亮度值变化：" + aVar);
            r(aVar.c(), aVar);
        }
        if (iH == 80004) {
            b.b.m.c.a.a(this.f2100a, "常用-引擎模拟音源变化：" + aVar);
            z(aVar.c(), aVar);
            return;
        }
        if (iH == 150011) {
            b.b.m.c.a.a(this.f2100a, "常用-智能保电/强制保电模式变化：" + aVar);
            B(aVar.c(), aVar);
            return;
        }
        switch (iH) {
            case 20001:
                b.b.m.c.a.a(this.f2100a, "常用-氛围灯点亮区域变化：" + aVar);
                A(aVar.c(), aVar);
                break;
            case 20002:
                t(aVar.d(), aVar);
                b.b.m.c.a.a(this.f2100a, "常用-音乐律动开关状态变化：" + aVar);
                break;
            case 20003:
                b.b.m.c.a.a(this.f2100a, "常用-动态多色彩开关状态变化：" + aVar);
                q(aVar.d(), aVar);
                break;
            case 20004:
                b.b.m.c.a.a(this.f2100a, "常用-氛围灯亮度变化：" + aVar);
                u(aVar.d(), aVar);
                break;
            case 20005:
                b.b.m.c.a.a(this.f2100a, "常用-氛围灯颜色变化：" + aVar);
                v(aVar.d(), aVar);
                break;
            case 20006:
                b.b.m.c.a.a(this.f2100a, "常用-氛围灯音乐律动模式变化：" + aVar);
                s(aVar.d(), aVar);
                break;
            case 20007:
                b.b.m.c.a.a(this.f2100a, "常用-氛围灯多色彩模式变化：" + aVar);
                p(aVar.d(), aVar);
                break;
            default:
                switch (iH) {
                    case 90000:
                        b.b.m.c.a.a(this.f2100a, "常用-主动架悬变化：" + aVar);
                        x(aVar.c(), aVar);
                        break;
                    case 90001:
                        b.b.m.c.a.a(this.f2100a, "常用-转向助力模式变化：" + aVar);
                        w(aVar.c(), aVar);
                        break;
                    default:
                        switch (iH) {
                            case 150015:
                                b.b.m.c.a.a(this.f2100a, "常用-能量回馈模式变化：" + aVar);
                                y(aVar.c(), aVar);
                                break;
                            case 150016:
                                b.b.m.c.a.a(this.f2100a, "常用-SOC设置值变化：" + aVar);
                                C(aVar.c(), aVar);
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public void p(int i, b.b.a.c.a aVar) {
    }

    public void q(int i, b.b.a.c.a aVar) {
    }

    public void r(int i, b.b.a.c.a aVar) {
    }

    public void s(int i, b.b.a.c.a aVar) {
    }

    public void t(int i, b.b.a.c.a aVar) {
    }

    public void u(int i, b.b.a.c.a aVar) {
    }

    public void v(int i, b.b.a.c.a aVar) {
    }

    public void w(int i, b.b.a.c.a aVar) {
    }

    public void x(int i, b.b.a.c.a aVar) {
    }

    public void y(int i, b.b.a.c.a aVar) {
    }

    public void z(int i, b.b.a.c.a aVar) {
    }
}
