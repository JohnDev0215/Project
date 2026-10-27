package com.spotify.esperanto.esperanto;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceDescriptorRegistry {
    public static final Companion Companion = new Companion(null);
    private static final ConcurrentHashMap<String, ServiceDescriptor> descriptors = new ConcurrentHashMap<>();

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ServiceDescriptor find(String str) {
            v03.j(str, "serviceID");
            return (ServiceDescriptor) ServiceDescriptorRegistry.descriptors.get(str);
        }

        public final void register(ServiceDescriptor serviceDescriptor) {
            v03.j(serviceDescriptor, "desc");
            ServiceDescriptorRegistry.descriptors.put(serviceDescriptor.getServiceID(), serviceDescriptor);
        }
    }
}
