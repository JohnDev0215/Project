package p;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class if0 {
    public final String a;
    public final List b;
    public final List c;
    public final Map d;
    public final Map e;

    public if0(String str, List list, List list2, Map map, LinkedHashMap linkedHashMap) {
        this.a = str;
        this.b = list;
        this.c = list2;
        this.d = map;
        this.e = linkedHashMap;
    }

    public final String toString() {
        return "Error(message = " + this.a + ", locations = " + this.b + ", path=" + this.c + ", extensions = " + this.d + ", nonStandardFields = " + this.e + ')';
    }
}
