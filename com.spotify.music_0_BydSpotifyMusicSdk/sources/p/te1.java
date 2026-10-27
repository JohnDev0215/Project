package p;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class te1 extends ue1 {
    public final Map a;
    public String b = null;

    public te1(LinkedHashMap linkedHashMap) {
        this.a = linkedHashMap;
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Map ("), this.b, ')');
    }
}
