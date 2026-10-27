package k;

import h.l;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: renamed from: k.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1587g extends AbstractC1582b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f17608a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f17609b;
    public final /* synthetic */ Y4.e c;

    public C1587g(l lVar, String str, Y4.e eVar) {
        this.f17608a = lVar;
        this.f17609b = str;
        this.c = eVar;
    }

    @Override // k.AbstractC1582b
    public final void a() throws Exception {
        l lVar = this.f17608a;
        LinkedHashMap linkedHashMap = lVar.f15105b;
        String str = this.f17609b;
        Object obj = linkedHashMap.get(str);
        Y4.e eVar = this.c;
        if (obj == null) {
            throw new IllegalStateException(("Attempting to launch an unregistered ActivityResultLauncher with contract " + eVar + " and input android.permission.BYDAUTO_GEARBOX_COMMON. You must ensure the ActivityResultLauncher is registered before calling launch().").toString());
        }
        int iIntValue = ((Number) obj).intValue();
        ArrayList arrayList = lVar.d;
        arrayList.add(str);
        try {
            lVar.b(iIntValue, eVar);
        } catch (Exception e10) {
            arrayList.remove(str);
            throw e10;
        }
    }
}
