package defpackage;

import android.app.RemoteInput;

/* JADX INFO: compiled from: RemoteInput.java */
/* JADX INFO: loaded from: classes.dex */
public final class b7 {
    public static RemoteInput a(b7 b7Var) {
        throw null;
    }

    public static RemoteInput[] b(b7[] b7VarArr) {
        if (b7VarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[b7VarArr.length];
        for (int i = 0; i < b7VarArr.length; i++) {
            remoteInputArr[i] = a(b7VarArr[i]);
        }
        return remoteInputArr;
    }
}
