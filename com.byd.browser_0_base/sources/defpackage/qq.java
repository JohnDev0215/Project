package defpackage;

import android.content.Context;
import androidx.core.app.a;
import org.chromium.chrome.browser.notifications.NotificationIntentInterceptor;

/* JADX INFO: compiled from: ChromeNotificationWrapperCompatBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qq extends np1 {
    public qq(Context context, String str, zm zmVar, o8 o8Var) {
        super(context, str, zmVar, o8Var);
        if (o8Var != null) {
            this.a.A.deleteIntent = NotificationIntentInterceptor.b(2, 0, o8Var, null);
        }
    }

    @Override // defpackage.mp1
    public final mp1 a(o8 o8Var) {
        this.a.A.deleteIntent = NotificationIntentInterceptor.b(2, 0, this.b, o8Var);
        return this;
    }

    @Override // defpackage.mp1
    public final mp1 b(int i, String str, o8 o8Var, int i2) {
        d(i, str, NotificationIntentInterceptor.b(1, i2, this.b, o8Var));
        return this;
    }

    @Override // defpackage.mp1
    public final mp1 c(o8 o8Var) {
        this.a.g = NotificationIntentInterceptor.b(0, 0, this.b, o8Var);
        return this;
    }

    public final mp1 s(a aVar, int i, int i2) {
        aVar.j = NotificationIntentInterceptor.b(1, i2, this.b, new o8(aVar.j, i, 0));
        this.a.b.add(aVar);
        return this;
    }
}
