package defpackage;

import android.os.RemoteException;

/* JADX INFO: compiled from: TboxUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class sg {
    public static String a() {
        try {
            return um.p().h();
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }
}
