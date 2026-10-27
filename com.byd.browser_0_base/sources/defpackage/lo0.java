package defpackage;

import J.N;
import android.content.Context;
import defpackage.io0;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: FollowManagementMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class lo0 {
    public final ya1 a;
    public final a b;
    public final Context c;
    public final xq3 d;

    /* JADX INFO: compiled from: FollowManagementMediator.java */
    public interface a {
    }

    public lo0(Context context, ya1 ya1Var, io0.a aVar, xq3 xq3Var) {
        this.a = ya1Var;
        this.b = aVar;
        this.c = context;
        this.d = xq3Var;
        ya1Var.t(new xa1(2, new PropertyModel(new v62[0])));
        N.MKcAcTO8(new ko0(0, this));
    }

    public final void a(int i) {
        a aVar = this.b;
        if (i == 2) {
            Toast.c(io0.this.a, R$string.feed_follow_no_connection_error, 1).e();
        } else if (i != 1) {
            Toast.c(io0.this.a, R$string.feed_follow_unknown_error, 1).e();
        }
    }
}
