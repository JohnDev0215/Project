package com.telenav.scoutivi.spotify.byd;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

/* JADX INFO: renamed from: com.telenav.scoutivi.spotify.byd.r, reason: case insensitive filesystem */
/* JADX INFO: compiled from: DXToastUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class C0183r extends y3.b {

    /* JADX INFO: renamed from: com.telenav.scoutivi.spotify.byd.r$a */
    /* JADX INFO: compiled from: DXToastUtil.java */
    class a implements View.OnAttachStateChangeListener {
        a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (((y3.b) C0183r.this).f9964a == null || ((y3.b) C0183r.this).f9964a.getView() != view) {
                return;
            }
            view.removeOnAttachStateChangeListener(this);
            ((y3.b) C0183r.this).f9964a = null;
        }
    }

    public C0183r(Application application) {
        super(application);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(String str) {
        Toast toast = this.f9964a;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(this.f9965b, str, 0);
        this.f9964a = toastMakeText;
        toastMakeText.getView().addOnAttachStateChangeListener(new a());
        this.f9964a.show();
    }

    @Override // y3.b
    public void c(final String str) {
        h3.i.b("DXToastUtil").g("show");
        s4.a.a().b(new Runnable() { // from class: com.telenav.scoutivi.spotify.byd.q
            @Override // java.lang.Runnable
            public final void run() {
                this.f5031e.i(str);
            }
        });
    }
}
