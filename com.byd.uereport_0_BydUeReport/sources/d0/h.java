package d0;

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
        for (int i8 = 0; i8 < hVarArr.length; i8++) {
            remoteInputArr[i8] = a(hVarArr[i8]);
        }
        return remoteInputArr;
    }
}
