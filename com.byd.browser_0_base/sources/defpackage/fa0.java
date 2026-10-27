package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.directwriting.IDirectWritingService;
import java.util.Objects;

/* JADX INFO: compiled from: DirectWritingServiceBinder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fa0 {
    public IDirectWritingService a;
    public String b;
    public final a c = new a();
    public b d;
    public Context e;

    /* JADX INFO: compiled from: DirectWritingServiceBinder.java */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            fa0 fa0Var = fa0.this;
            String str = fa0Var.b;
            Objects.toString(componentName);
            fa0Var.a = IDirectWritingService.Stub.asInterface(iBinder);
            fa0Var.b();
            if (fa0Var.a()) {
                try {
                    Bundle bundle = new Bundle();
                    fa0Var.a.getConfiguration(bundle);
                    ((ja0) fa0Var.d).a(bundle);
                } catch (DeadObjectException e) {
                    Log.e("cr_DWServiceBinder", "updateConfiguration failed due to DeadObjectException.", e);
                    fa0Var.c();
                } catch (Exception e2) {
                    Log.e("cr_DWServiceBinder", "updateConfiguration failed.", e2);
                }
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            fa0 fa0Var = fa0.this;
            String str = fa0Var.b;
            Objects.toString(componentName);
            fa0Var.d(fa0Var.e);
        }
    }

    /* JADX INFO: compiled from: DirectWritingServiceBinder.java */
    public interface b {
    }

    public final boolean a() {
        return this.a != null;
    }

    public final void b() {
        if (a()) {
            ga0 ga0Var = ((ja0) this.d).a.m;
            try {
                this.a.registerCallback(ga0Var, this.b + IDirectWritingService.VALUE_SERVICE_HOST_SOURCE_WEBVIEW);
            } catch (DeadObjectException e) {
                Log.e("cr_DWServiceBinder", "registerCallback failed due to DeadObjectException.", e);
                c();
            } catch (Exception e2) {
                Log.e("cr_DWServiceBinder", "registerCallback failed.", e2);
            }
        }
    }

    public final void c() {
        this.a = null;
        this.b = "";
    }

    public final void d(Context context) {
        if (a()) {
            if (a()) {
                try {
                    this.a.unregisterCallback(((ja0) this.d).a.m);
                } catch (DeadObjectException e) {
                    Log.e("cr_DWServiceBinder", "unregisterCallback failed due to DeadObjectException.", e);
                    c();
                } catch (Exception e2) {
                    Log.e("cr_DWServiceBinder", "unregisterCallback failed.", e2);
                }
            }
            try {
                context.unbindService(this.c);
            } catch (IllegalArgumentException e3) {
                Log.e("cr_DWServiceBinder", "unbindService failed : " + e3);
            }
            c();
        }
    }

    public final void e(View view, Rect rect) {
        if (a()) {
            try {
                this.a.onBoundedEditTextChanged(a8.m(view, rect));
            } catch (DeadObjectException e) {
                Log.e("cr_DWServiceBinder", "updateEditableBounds failed due to DeadObjectException.", e);
                c();
            } catch (Exception e2) {
                Log.e("cr_DWServiceBinder", "updateEditableBounds failed.", e2);
            }
        }
    }
}
