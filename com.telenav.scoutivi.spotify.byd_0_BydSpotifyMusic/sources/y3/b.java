package y3;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

/* JADX INFO: compiled from: ToastUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected Toast f9964a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final Application f9965b;

    /* JADX INFO: compiled from: ToastUtil.java */
    class a implements View.OnAttachStateChangeListener {
        a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            Toast toast = b.this.f9964a;
            if (toast == null || toast.getView() != view) {
                return;
            }
            view.removeOnAttachStateChangeListener(this);
            b.this.f9964a = null;
        }
    }

    public b(Application application) {
        this.f9965b = application;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, int i7) {
        Toast toast = this.f9964a;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(this.f9965b, str, i7);
        this.f9964a = toastMakeText;
        if (toastMakeText.getView() != null) {
            this.f9964a.getView().addOnAttachStateChangeListener(new a());
        }
        this.f9964a.show();
    }

    public void c(String str) {
        d(str, 0);
    }

    public void d(final String str, final int i7) {
        s4.a.a().b(new Runnable() { // from class: y3.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f9961e.b(str, i7);
            }
        });
    }
}
