package p;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class iy extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    static {
        vc1.d("ConstraintProxy");
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        vc1 vc1VarC = vc1.c();
        Objects.toString(intent);
        vc1VarC.getClass();
        String str = tu.n;
        Intent intent2 = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent2.setAction("ACTION_CONSTRAINTS_CHANGED");
        context.startService(intent2);
    }
}
