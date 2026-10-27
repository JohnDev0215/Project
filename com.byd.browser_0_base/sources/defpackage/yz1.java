package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PendingDialogContainer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yz1 {
    public final HashMap<Integer, List<PropertyModel>> a = new HashMap<>();

    /* JADX INFO: compiled from: PendingDialogContainer.java */
    public class a {
        public final PropertyModel a;
        public final int b;
        public final int c;

        public a(PropertyModel propertyModel, int i, int i2) {
            this.a = propertyModel;
            this.b = i;
            this.c = i2;
        }
    }

    public final void a(int i, int i2, PropertyModel propertyModel, boolean z) {
        Integer numValueOf = Integer.valueOf((i * 10) + i2);
        HashMap<Integer, List<PropertyModel>> map = this.a;
        List<PropertyModel> arrayList = map.get(numValueOf);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            map.put(numValueOf, arrayList);
        }
        arrayList.add(z ? 0 : arrayList.size(), propertyModel);
    }
}
