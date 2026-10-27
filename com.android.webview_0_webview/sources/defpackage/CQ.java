package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.weblayer_private.ProfileImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CQ {
    public final Map a = new HashMap();

    public ProfileImpl a(final String str) {
        if (str == null) {
            throw new IllegalArgumentException("Name shouldn't be null");
        }
        ProfileImpl profileImpl = (ProfileImpl) this.a.get(str);
        if (profileImpl != null) {
            return profileImpl;
        }
        ProfileImpl profileImpl2 = new ProfileImpl(str, new Runnable(this, str) { // from class: BQ
            public final CQ s;
            public final String t;

            {
                this.s = this;
                this.t = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                CQ cq = this.s;
                cq.a.remove(this.t);
            }
        });
        this.a.put(str, profileImpl2);
        return profileImpl2;
    }
}
