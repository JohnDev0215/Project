package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;

/* JADX INFO: compiled from: ChromeCachedFlags.java */
/* JADX INFO: loaded from: classes.dex */
public final class wp {
    public static final wp b = new wp();
    public static final List<rk> c;
    public boolean a;

    static {
        Object[] objArr = {CustomTabActivity.t1};
        ArrayList arrayList = new ArrayList(1);
        Object obj = objArr[0];
        Objects.requireNonNull(obj);
        arrayList.add(obj);
        c = Collections.unmodifiableList(arrayList);
    }
}
