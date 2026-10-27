package defpackage;

import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: renamed from: Oe, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0255Oe {
    public Object a;
    public boolean b = false;
    public final /* synthetic */ BaseGmsClient c;

    public AbstractC0255Oe(BaseGmsClient baseGmsClient, Object obj) {
        this.c = baseGmsClient;
        this.a = obj;
    }

    public final void a() {
        synchronized (this) {
            this.a = null;
        }
        synchronized (this.c.o) {
            this.c.o.remove(this);
        }
    }

    public abstract void b(Object obj);
}
