package b.h.d;

import android.app.RemoteInput;

/* JADX INFO: compiled from: RemoteInput.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static RemoteInput a(j jVar) {
        throw null;
    }

    public static RemoteInput[] b(j[] jVarArr) {
        if (jVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[jVarArr.length];
        for (int i2 = 0; i2 < jVarArr.length; i2++) {
            remoteInputArr[i2] = a(jVarArr[i2]);
        }
        return remoteInputArr;
    }
}
