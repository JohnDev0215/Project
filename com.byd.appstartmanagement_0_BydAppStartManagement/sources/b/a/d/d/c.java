package b.a.d.d;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import b.a.d.d.e;
import com.byd.mvp.model.BydData;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: XPageCard.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c<P extends e> implements f<P>, View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f324b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f325c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public P f326d;
    public volatile boolean e;
    public volatile boolean f;
    public a g;

    /* JADX INFO: compiled from: XPageCard.java */
    public static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<c> f327a;

        public a(c cVar) {
            this.f327a = new WeakReference<>(cVar);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            WeakReference<c> weakReference = this.f327a;
            if (weakReference == null || weakReference.get() == null || 33 != message.what) {
                return;
            }
            this.f327a.get().l(message);
        }
    }

    @Override // b.a.d.d.f
    public void a(P p) {
        this.f326d = p;
    }

    @Override // b.a.d.d.f
    public P b() {
        return this.f326d;
    }

    public View g() {
        return this.f325c;
    }

    public abstract void h(Context context);

    public boolean i() {
        return false;
    }

    public void j(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        b.a.d.b.a.a("XPageCard", c.class.getName());
        this.e = false;
        if (i()) {
            this.g = new a(this);
        }
        if (c() > 0) {
            View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(c(), viewGroup, false);
            this.f325c = viewInflate;
            viewInflate.setOnClickListener(this);
            k(this.f325c.getContext());
            h(this.f325c.getContext());
        }
        this.e = true;
        this.f = false;
    }

    public abstract void k(Context context);

    public void l(Message message) {
        BydLogHelper.logV(this.f324b + "----onHandlerUpdateUI");
    }

    public void m(BydData bydData, int i) {
        b.a.d.b.a.a(this.f324b, c.class.getName() + ", data = " + bydData);
    }
}
