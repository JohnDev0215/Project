package F0;

import F8.AbstractC0287w;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import java.util.Comparator;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class K implements Comparator {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2045m;

    public /* synthetic */ K(Object obj, int i7) {
        this.l = i7;
        this.f2045m = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                int iCompare = ((Comparator) this.f2045m).compare(obj, obj2);
                if (iCompare != 0) {
                    return iCompare;
                }
                return E0.F.f1462V.compare(((L0.o) obj).c, ((L0.o) obj2).c);
            case 1:
                int iCompare2 = ((K) this.f2045m).compare(obj, obj2);
                return iCompare2 != 0 ? iCompare2 : h6.f.b(Integer.valueOf(((L0.o) obj).f3920g), Integer.valueOf(((L0.o) obj2).f3920g));
            case 2:
                AbstractC0287w abstractC0287w = (AbstractC0287w) obj;
                A7.m.e(abstractC0287w, "it");
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f2045m;
                String string = interfaceC2782k.b(abstractC0287w).toString();
                AbstractC0287w abstractC0287w2 = (AbstractC0287w) obj2;
                A7.m.e(abstractC0287w2, "it");
                return h6.f.b(string, interfaceC2782k.b(abstractC0287w2).toString());
            default:
                MaterialButton materialButton = (MaterialButton) obj;
                MaterialButton materialButton2 = (MaterialButton) obj2;
                int iCompareTo = Boolean.valueOf(materialButton.f12806y).compareTo(Boolean.valueOf(materialButton2.f12806y));
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
                int iCompareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) this.f2045m;
                return Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton)).compareTo(Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton2)));
        }
    }

    public K(Comparator comparator) {
        this.l = 0;
        this.f2045m = comparator;
    }
}
