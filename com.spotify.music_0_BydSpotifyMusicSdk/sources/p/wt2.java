package p;

import android.view.ContentInfo;
import android.view.OnReceiveContentListener;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class wt2 implements OnReceiveContentListener {
    public final bq1 a;

    public wt2(bq1 bq1Var) {
        this.a = bq1Var;
    }

    public final ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
        jz jzVar = new jz(new p01(contentInfo));
        jz jzVarA = ((gk2) this.a).a(view, jzVar);
        if (jzVarA == null) {
            return null;
        }
        if (jzVarA == jzVar) {
            return contentInfo;
        }
        ContentInfo contentInfoQ = jzVarA.a.q();
        Objects.requireNonNull(contentInfoQ);
        return ez.f(contentInfoQ);
    }
}
