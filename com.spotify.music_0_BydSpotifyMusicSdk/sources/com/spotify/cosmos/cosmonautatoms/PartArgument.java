package com.spotify.cosmos.cosmonautatoms;

import java.util.Map;
import kotlin.Pair;
import p.he1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class PartArgument {
    private final int index;
    private final String name;

    public PartArgument(String str, int i) {
        v03.j(str, "name");
        this.name = str;
        this.index = i;
    }

    public final Map<String, Object> visit(Object[] objArr) {
        v03.j(objArr, "args");
        return he1.C(new Pair(this.name, objArr[this.index]));
    }
}
