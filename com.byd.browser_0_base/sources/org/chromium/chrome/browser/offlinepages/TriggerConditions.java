package org.chromium.chrome.browser.offlinepages;

/* JADX INFO: loaded from: classes2.dex */
public final class TriggerConditions {
    public final boolean a;
    public final int b;
    public final boolean c;

    public TriggerConditions(int i, boolean z, boolean z2) {
        this.a = z;
        this.b = i;
        this.c = z2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TriggerConditions)) {
            return false;
        }
        TriggerConditions triggerConditions = (TriggerConditions) obj;
        return this.a == triggerConditions.a && this.b == triggerConditions.b && this.c == triggerConditions.c;
    }

    public final int hashCode() {
        return ((((403 + (this.a ? 1 : 0)) * 31) + this.b) * 31) + (this.c ? 1 : 0);
    }
}
