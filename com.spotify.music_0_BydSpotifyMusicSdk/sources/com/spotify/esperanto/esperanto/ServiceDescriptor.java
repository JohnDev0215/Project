package com.spotify.esperanto.esperanto;

import java.util.ArrayList;
import java.util.HashMap;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class ServiceDescriptor {
    private final HashMap<String, MethodDescriptor> mMethodsMap = new HashMap<>();

    public final void addMethod(String str, MethodDescriptor methodDescriptor) {
        v03.j(str, "methodName");
        v03.j(methodDescriptor, "methodDescriptor");
        this.mMethodsMap.put(str, methodDescriptor);
    }

    public final ArrayList<MethodDescriptor> findAllDescriptors() {
        return new ArrayList<>(this.mMethodsMap.values());
    }

    public final MethodDescriptor findDescriptor(String str) {
        v03.j(str, "methodName");
        return this.mMethodsMap.get(str);
    }

    public abstract String getServiceID();

    public abstract String getServiceName();
}
