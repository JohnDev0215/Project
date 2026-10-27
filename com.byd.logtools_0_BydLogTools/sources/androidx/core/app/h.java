package androidx.core.app;

import android.app.RemoteInput;

/* JADX INFO: compiled from: RemoteInput.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    static RemoteInput a(h hVar) {
        throw null;
    }

    static RemoteInput[] b(h[] hVarArr) {
        if (hVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[hVarArr.length];
        for (int i = 0; i < hVarArr.length; i++) {
            remoteInputArr[i] = a(hVarArr[i]);
        }
        return remoteInputArr;
    }
}
