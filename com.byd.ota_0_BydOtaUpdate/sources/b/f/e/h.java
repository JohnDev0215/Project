package b.f.e;

import android.app.RemoteInput;

/* JADX INFO: compiled from: RemoteInput.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static RemoteInput a(h hVar) {
        throw null;
    }

    public static RemoteInput[] b(h[] hVarArr) {
        if (hVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[hVarArr.length];
        for (int i2 = 0; i2 < hVarArr.length; i2++) {
            remoteInputArr[i2] = a(hVarArr[i2]);
        }
        return remoteInputArr;
    }
}
