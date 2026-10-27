package com.ts.appservice.caradapter.a;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

/* JADX INFO: compiled from: SettingsRemoteCallbackList.java */
/* JADX INFO: loaded from: classes.dex */
public class k<E extends IInterface> extends RemoteCallbackList<E> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f18a;

    /* JADX INFO: compiled from: SettingsRemoteCallbackList.java */
    public interface a<E extends IInterface> {
        void a(E e);

        void b(int i);
    }

    public void a(a aVar) {
        this.f18a = aVar;
    }

    @Override // android.os.RemoteCallbackList
    public void onCallbackDied(E e, Object obj) {
        onCallbackDied(e);
    }

    @Override // android.os.RemoteCallbackList
    public void onCallbackDied(E e) {
        super.onCallbackDied(e);
        a aVar = this.f18a;
        if (aVar != null) {
            try {
                aVar.b(getRegisteredCallbackCount());
                this.f18a.a(e);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }
}
