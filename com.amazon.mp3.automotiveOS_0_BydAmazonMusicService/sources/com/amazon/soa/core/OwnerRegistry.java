package com.amazon.soa.core;

import SOACoreInterface.v1_0.Method;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
class OwnerRegistry {
    private final Set<String> ownerRegistry = new HashSet();
    private final Set<String> globalInterfaces = new HashSet();

    OwnerRegistry() {
    }

    public void registerOwner(final String ownerId) {
        this.ownerRegistry.add(ownerId);
    }

    public void unregisterOwner(final String ownerId) {
        this.ownerRegistry.remove(ownerId);
    }

    public boolean canProcessMethod(final String ownerId, final Method method) {
        return this.ownerRegistry.contains(ownerId) || isGlobalMethod(method);
    }

    private boolean isGlobalMethod(final Method method) {
        return this.globalInterfaces.contains(method.getClass().getCanonicalName());
    }
}
