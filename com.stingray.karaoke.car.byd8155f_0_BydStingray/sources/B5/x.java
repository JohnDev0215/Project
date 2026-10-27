package B5;

import android.content.Context;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class x extends K {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f738e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(Context context, String str, String str2, int i7) {
        super(context, str, str2);
        this.f738e = i7;
    }

    @Override // B5.K
    public final L a(LinkedHashMap linkedHashMap) {
        switch (this.f738e) {
            case 0:
                return new y(linkedHashMap);
            default:
                return new J(new D5.f(linkedHashMap));
        }
    }
}
