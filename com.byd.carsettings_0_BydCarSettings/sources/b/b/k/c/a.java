package b.b.k.c;

import b.b.k.a.b.a;
import b.b.m.e.g;
import com.byd.base.hilt.Router;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BaseAdasViewPresent.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a<V extends b.b.k.a.b.a> extends g<V> implements b.b.k.b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1952a = getClass().getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1953b;

    public void a() {
        if (this.f1953b) {
            BydLogHelper.logD(this.f1952a, "onCleared unregister eventbus!");
            EventBus.getDefault().unregister(this);
            this.f1953b = false;
        }
    }

    public boolean c(int i) {
        return Router.b().e().j(i);
    }
}
