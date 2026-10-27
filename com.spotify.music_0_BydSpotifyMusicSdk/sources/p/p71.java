package p;

import com.google.gson.internal.LinkedTreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class p71 extends i71 {
    public final LinkedTreeMap c = new LinkedTreeMap();

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof p71) && ((p71) obj).c.equals(this.c));
    }

    public final int hashCode() {
        return this.c.hashCode();
    }
}
