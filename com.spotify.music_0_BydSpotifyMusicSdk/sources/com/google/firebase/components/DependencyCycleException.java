package com.google.firebase.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p.mv;

/* JADX INFO: loaded from: classes.dex */
public class DependencyCycleException extends DependencyException {
    private final List<mv> componentsInCycle;

    public DependencyCycleException(ArrayList arrayList) {
        super("Dependency cycle detected: " + Arrays.toString(arrayList.toArray()));
        this.componentsInCycle = arrayList;
    }
}
