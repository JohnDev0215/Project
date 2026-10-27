package org.chromium.content.browser;

import android.content.Context;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.ServiceRegistry;
import org.chromium.device.battery.BatteryMonitorFactory;
import org.chromium.device.vibration.VibrationManagerImpl;
import org.chromium.mojom.device.BatteryMonitor;
import org.chromium.mojom.device.VibrationManager;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class ServiceRegistrar {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    ServiceRegistrar() {
    }

    private static class BatteryMonitorImplementationFactory implements ServiceRegistry.ImplementationFactory<BatteryMonitor> {
        private final BatteryMonitorFactory mFactory;

        BatteryMonitorImplementationFactory(Context context) {
            this.mFactory = new BatteryMonitorFactory(context);
        }

        @Override // org.chromium.content.browser.ServiceRegistry.ImplementationFactory
        public BatteryMonitor createImpl() {
            return this.mFactory.createMonitor();
        }
    }

    private static class VibrationManagerImplementationFactory implements ServiceRegistry.ImplementationFactory<VibrationManager> {
        private final Context mApplicationContext;

        VibrationManagerImplementationFactory(Context context) {
            this.mApplicationContext = context;
        }

        @Override // org.chromium.content.browser.ServiceRegistry.ImplementationFactory
        public VibrationManager createImpl() {
            return new VibrationManagerImpl(this.mApplicationContext);
        }
    }

    static void registerProcessHostServices(ServiceRegistry serviceRegistry, Context context) {
        serviceRegistry.addService(BatteryMonitor.MANAGER, new BatteryMonitorImplementationFactory(context));
    }

    static void registerFrameHostServices(ServiceRegistry serviceRegistry, Context context) {
        serviceRegistry.addService(VibrationManager.MANAGER, new VibrationManagerImplementationFactory(context));
    }
}
