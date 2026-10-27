package spi;

import android.content.Context;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class pc {

    public interface a {
        Set<Boolean> d();
    }

    private pc() {
    }

    public static boolean a(Context context) {
        Set<Boolean> setD = ((a) s8.a(context, a.class)).d();
        bl.a(setD.size() <= 1, "Cannot bind the flag @DisableFragmentGetContextFix more than once.", new Object[0]);
        if (setD.isEmpty()) {
            return true;
        }
        return setD.iterator().next().booleanValue();
    }
}
