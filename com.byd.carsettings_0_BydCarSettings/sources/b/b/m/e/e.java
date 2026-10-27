package b.b.m.e;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import b.b.m.e.g;
import com.byd.mvp.model.BydData;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: XPageCard.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class e<P extends g> implements h<P>, View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public WeakReference<Activity> f2101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f2102c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public P f2103d;
    public a g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2100a = getClass().getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile boolean f2104e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f2105f = true;

    /* JADX INFO: compiled from: XPageCard.java */
    public static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<e> f2106a;

        public a(e eVar) {
            this.f2106a = new WeakReference<>(eVar);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            WeakReference<e> weakReference = this.f2106a;
            if (weakReference == null || weakReference.get() == null || 33 != message.what) {
                return;
            }
            this.f2106a.get().m(message);
        }
    }

    public e(Activity activity) {
        this.f2101b = new WeakReference<>(activity);
    }

    public View c() {
        return this.f2102c;
    }

    public abstract void e(Context context);

    public boolean f() {
        return false;
    }

    public Context getContext() {
        View view = this.f2102c;
        if (view != null) {
            return view.getContext();
        }
        return null;
    }

    @Override // b.b.m.e.h
    public P getRefP() {
        return this.f2103d;
    }

    public void h(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        b.b.m.c.a.a("XPageCard", getClass().getName());
        this.f2104e = false;
        if (f()) {
            this.g = new a(this);
        }
        if (getLayoutId() > 0) {
            View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
            this.f2102c = viewInflate;
            viewInflate.setOnClickListener(this);
            k(this.f2102c.getContext());
            e(this.f2102c.getContext());
        }
        this.f2104e = true;
        this.f2105f = false;
    }

    public abstract void k(Context context);

    public void l() {
        b.b.m.c.a.a("XPageCard", "----onDestroy---" + getClass().getName());
        this.f2104e = false;
        if (mo7getP() != null) {
            mo7getP().unbindV();
        }
    }

    public void m(Message message) {
        BydLogHelper.logV(this.f2100a + "----onHandlerUpdateUI");
    }

    public void n(BydData bydData, int i) {
        b.b.m.c.a.a(this.f2100a, getClass().getName() + ", data = " + bydData);
    }

    @Override // b.b.m.e.h
    public void setRefP(P p) {
        this.f2103d = p;
    }
}
