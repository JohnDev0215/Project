package org.chromium.content.browser;

import org.chromium.base.annotations.JNINamespace;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ServiceRegistry {
    private final Core mCore;
    private long mNativeServiceRegistryAndroid;

    public interface ImplementationFactory<I extends Interface> {
        I createImpl();
    }

    private native void nativeAddService(long j, Interface.Manager manager, ImplementationFactory implementationFactory, String str);

    private native void nativeConnectToRemoteService(long j, String str, int i);

    private native void nativeRemoveService(long j, String str);

    public <I extends Interface, P extends Interface.Proxy> void addService(Interface.Manager<I, P> manager, ImplementationFactory<I> implementationFactory) {
        nativeAddService(this.mNativeServiceRegistryAndroid, manager, implementationFactory, manager.getName());
    }

    <I extends Interface, P extends Interface.Proxy> void removeService(Interface.Manager<I, P> manager) {
        nativeRemoveService(this.mNativeServiceRegistryAndroid, manager.getName());
    }

    <I extends Interface, P extends Interface.Proxy> void connectToRemoteService(Interface.Manager<I, P> manager, InterfaceRequest<I> interfaceRequest) {
        nativeConnectToRemoteService(this.mNativeServiceRegistryAndroid, manager.getName(), interfaceRequest.passHandle().releaseNativeHandle());
    }

    private ServiceRegistry(long j, Core core) {
        this.mNativeServiceRegistryAndroid = j;
        this.mCore = core;
    }

    private static ServiceRegistry create(long j) {
        return new ServiceRegistry(j, CoreImpl.getInstance());
    }

    private void destroy() {
        this.mNativeServiceRegistryAndroid = 0L;
    }

    private void createImplAndAttach(int i, Interface.Manager manager, ImplementationFactory implementationFactory) {
        manager.bind(implementationFactory.createImpl(), this.mCore.acquireNativeHandle(i).toMessagePipeHandle());
    }
}
