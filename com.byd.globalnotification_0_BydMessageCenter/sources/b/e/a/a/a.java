package b.e.a.a;

import android.os.Looper;
import android.view.View;
import c.b.b;
import c.b.e.c;

/* JADX INFO: loaded from: classes.dex */
public final class a extends c.b.a<d.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f2197a;

    /* JADX INFO: renamed from: b.e.a.a.a$a, reason: collision with other inner class name */
    public static final class ViewOnClickListenerC0046a extends c.b.d.a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f2198b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b<? super d.a> f2199c;

        public ViewOnClickListenerC0046a(View view, b<? super d.a> bVar) {
            if (view == null) {
                d.b.a.a.b("view");
                throw null;
            }
            this.f2198b = view;
            this.f2199c = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == null) {
                d.b.a.a.b("v");
                throw null;
            }
            if (this.f2206a.get()) {
                return;
            }
            this.f2199c.c(d.a.f2514a);
        }
    }

    public a(View view) {
        this.f2197a = view;
    }

    @Override // c.b.a
    public void c(b<? super d.a> bVar) {
        boolean z = false;
        if (!(Looper.myLooper() == null ? Looper.getMainLooper() == null : r0.equals(r1))) {
            Runnable runnable = c.b.h.b.a.f2231a;
            c.b.h.b.b.a(runnable, "run is null");
            bVar.e(new c(runnable));
            StringBuilder sbD = b.a.a.a.a.d("Expected to be called on the main thread but was ");
            Thread threadCurrentThread = Thread.currentThread();
            if (threadCurrentThread == null) {
                IllegalStateException illegalStateException = new IllegalStateException("Thread.currentThread() must not be null");
                d.b.a.a.a(illegalStateException);
                throw illegalStateException;
            }
            sbD.append(threadCurrentThread.getName());
            bVar.b(new IllegalStateException(sbD.toString()));
        } else {
            z = true;
        }
        if (z) {
            ViewOnClickListenerC0046a viewOnClickListenerC0046a = new ViewOnClickListenerC0046a(this.f2197a, bVar);
            bVar.e(viewOnClickListenerC0046a);
            this.f2197a.setOnClickListener(viewOnClickListenerC0046a);
        }
    }
}
