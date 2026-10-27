package p;

import android.net.Uri;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class m31 implements ii0 {
    @Override // p.ii0, p.p31
    public final void c(q31 q31Var, kf0 kf0Var) {
        v03.j(q31Var, "request");
        v03.j(kf0Var, "result");
        Object obj = q31Var.b;
        String string = obj instanceof Uri ? obj.toString() : obj instanceof String ? (String) obj : null;
        if (string != null) {
            Logger.c(kf0Var.c, "Failed to load image with uri: \"%s\".", string);
        }
    }
}
