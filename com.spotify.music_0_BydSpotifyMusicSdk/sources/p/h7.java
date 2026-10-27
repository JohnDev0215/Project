package p;

import android.app.Application;

/* JADX INFO: loaded from: classes.dex */
public final class h7 {
    public static final h7 a = new h7();

    public final String a() {
        String processName = Application.getProcessName();
        v03.i(processName, "getProcessName()");
        return processName;
    }
}
