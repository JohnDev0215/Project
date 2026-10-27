package F0;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: F0.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0233v0 implements t3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2286a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2287b;

    public /* synthetic */ C0233v0(Object obj, int i7) {
        this.f2286a = i7;
        this.f2287b = obj;
    }

    @Override // t3.d
    public final Bundle a() {
        switch (this.f2286a) {
            case 0:
                Map mapD = ((d0.l) this.f2287b).d();
                Bundle bundle = new Bundle();
                for (Map.Entry entry : ((LinkedHashMap) mapD).entrySet()) {
                    String str = (String) entry.getKey();
                    List list = (List) entry.getValue();
                    bundle.putParcelableArrayList(str, list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
                }
                return bundle;
            case 1:
                return androidx.lifecycle.X.a((androidx.lifecycle.X) this.f2287b);
            default:
                h.n nVar = (h.n) this.f2287b;
                Bundle bundle2 = new Bundle();
                h.l lVar = nVar.f15122t;
                lVar.getClass();
                LinkedHashMap linkedHashMap = lVar.f15105b;
                bundle2.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(linkedHashMap.values()));
                bundle2.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(linkedHashMap.keySet()));
                bundle2.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(lVar.d));
                bundle2.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", new Bundle(lVar.f15108g));
                return bundle2;
        }
    }
}
