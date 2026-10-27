package o3;

import androidx.lifecycle.X;
import androidx.lifecycle.f0;
import d0.InterfaceC1054c;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.UUID;

/* JADX INFO: renamed from: o3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2028a extends f0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f20455b = "SaveableStateHolder_BackStackEntryKey";
    public final UUID c;
    public WeakReference d;

    public C2028a(X x8) {
        Object obj;
        LinkedHashMap linkedHashMap = x8.f11959a;
        try {
            obj = linkedHashMap.get("SaveableStateHolder_BackStackEntryKey");
        } catch (ClassCastException unused) {
            linkedHashMap.remove("SaveableStateHolder_BackStackEntryKey");
            if (x8.c.remove("SaveableStateHolder_BackStackEntryKey") != null) {
                throw new ClassCastException();
            }
            x8.d.remove("SaveableStateHolder_BackStackEntryKey");
            obj = null;
        }
        UUID uuidRandomUUID = (UUID) obj;
        if (uuidRandomUUID == null) {
            uuidRandomUUID = UUID.randomUUID();
            x8.b(uuidRandomUUID, this.f20455b);
        }
        this.c = uuidRandomUUID;
    }

    @Override // androidx.lifecycle.f0
    public final void d() {
        WeakReference weakReference = this.d;
        if (weakReference == null) {
            A7.m.j("saveableStateHolderRef");
            throw null;
        }
        InterfaceC1054c interfaceC1054c = (InterfaceC1054c) weakReference.get();
        if (interfaceC1054c != null) {
            interfaceC1054c.d(this.c);
        }
        WeakReference weakReference2 = this.d;
        if (weakReference2 != null) {
            weakReference2.clear();
        } else {
            A7.m.j("saveableStateHolderRef");
            throw null;
        }
    }
}
