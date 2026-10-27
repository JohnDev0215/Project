package defpackage;

import java.util.BitSet;

/* JADX INFO: compiled from: CustomTabsFeatureUsage.java */
/* JADX INFO: loaded from: classes.dex */
public final class f50 {
    public final BitSet b = new BitSet(52);
    public final boolean a = bq.h.a();

    public final void a(int i) {
        if (this.a) {
            BitSet bitSet = this.b;
            if (!bitSet.get(i)) {
                bitSet.set(i);
                oj2.U(i, 52, "CustomTabs.FeatureUsage");
            }
            if (bitSet.get(0)) {
                return;
            }
            bitSet.set(0);
            oj2.U(0, 52, "CustomTabs.FeatureUsage");
        }
    }
}
