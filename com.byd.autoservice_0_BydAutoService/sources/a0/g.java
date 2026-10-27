package a0;

import android.app.RemoteInput;

/* JADX INFO: compiled from: RemoteInput.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static RemoteInput a(g gVar) {
        throw null;
    }

    public static RemoteInput[] b(g[] gVarArr) {
        if (gVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[gVarArr.length];
        for (int i3 = 0; i3 < gVarArr.length; i3++) {
            remoteInputArr[i3] = a(gVarArr[i3]);
        }
        return remoteInputArr;
    }
}
