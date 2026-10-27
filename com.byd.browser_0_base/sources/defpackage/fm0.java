package defpackage;

import android.text.TextUtils;
import android.util.Pair;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.CollectionUtil;
import org.chromium.base.SysUtils;

/* JADX INFO: compiled from: FeedbackContextFeedbackSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class fm0 implements gm0 {
    public final /* synthetic */ int a;
    public final Serializable b;

    public /* synthetic */ fm0(String str, int i) {
        this.a = i;
        this.b = str;
    }

    @Override // defpackage.gm0
    public final Map c() {
        int i = this.a;
        Serializable serializable = this.b;
        switch (i) {
            case 0:
                String str = (String) serializable;
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                return CollectionUtil.d(Pair.create("Feedback Context", str));
            case 1:
                String str2 = (String) serializable;
                if (TextUtils.isEmpty(str2)) {
                    return null;
                }
                return CollectionUtil.d(Pair.create("URL", str2));
            default:
                return (HashMap) serializable;
        }
    }

    public fm0() {
        this.a = 2;
        HashMap map = new HashMap(1);
        this.b = map;
        map.put("lowmem", Boolean.toString(SysUtils.isLowEndDevice()));
    }
}
