package androidx.core.app;

import android.app.RemoteInput;

/* JADX INFO: compiled from: RemoteInput.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    static RemoteInput a(i iVar) {
        throw null;
    }

    static RemoteInput[] b(i[] iVarArr) {
        if (iVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[iVarArr.length];
        for (int i = 0; i < iVarArr.length; i++) {
            remoteInputArr[i] = a(iVarArr[i]);
        }
        return remoteInputArr;
    }
}
