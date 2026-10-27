package p;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.impl.background.systemjob.SystemJobService;

/* JADX INFO: loaded from: classes.dex */
public final class ki2 {
    public final ComponentName a;
    public final zb0 b;

    static {
        vc1.d("SystemJobInfoConverter");
    }

    public ki2(Context context, zb0 zb0Var) {
        this.b = zb0Var;
        this.a = new ComponentName(context.getApplicationContext(), (Class<?>) SystemJobService.class);
    }
}
