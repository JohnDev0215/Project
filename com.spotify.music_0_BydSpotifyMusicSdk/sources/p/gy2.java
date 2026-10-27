package p;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class gy2 {
    public final UUID a;
    public final ky2 b;
    public final Set c;

    public gy2(UUID uuid, ky2 ky2Var, LinkedHashSet linkedHashSet) {
        v03.j(uuid, "id");
        v03.j(ky2Var, "workSpec");
        v03.j(linkedHashSet, "tags");
        this.a = uuid;
        this.b = ky2Var;
        this.c = linkedHashSet;
    }
}
