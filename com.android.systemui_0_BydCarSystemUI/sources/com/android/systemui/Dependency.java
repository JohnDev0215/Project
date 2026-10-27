package com.android.systemui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.ServiceManager;
import android.util.ArrayMap;
import android.util.Log;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.Preconditions;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.PluginManagerImpl;
import com.android.systemui.qs.tileimpl.QSFactoryImpl;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.stackdivider.SplitScreenController;
import com.android.systemui.stackdivider.view.IndexWindowManager;
import com.android.systemui.statusbar.phone.BlurViewController;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.phone.StatusBarWindowManager;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl;
import com.android.systemui.statusbar.policy.BrightnessController;
import com.android.systemui.statusbar.policy.BrightnessControllerImpl;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.statusbar.policy.CarControllerImpl;
import com.android.systemui.statusbar.policy.ClockController;
import com.android.systemui.statusbar.policy.ClockControllerImpl;
import com.android.systemui.statusbar.policy.HvacController;
import com.android.systemui.statusbar.policy.HvacControllerImpl;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.NetworkControllerImpl;
import com.android.systemui.statusbar.policy.UsbController;
import com.android.systemui.statusbar.policy.UsbControllerImpl;
import com.android.systemui.statusbar.policy.VolumeController;
import com.android.systemui.statusbar.policy.VolumeControllerImpl;
import com.android.systemui.statusbar.wm.AirPanelController;
import com.android.systemui.statusbar.wm.GestureControl;
import com.android.systemui.util.leak.LeakDetector;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class Dependency extends SystemUI {
    private static final String TAG = "Dependency";
    private static Dependency sDependency;
    private final ArrayMap<Object, Object> mDependencies = new ArrayMap<>();
    private final ArrayMap<Object, DependencyProvider> mProviders = new ArrayMap<>();
    public static final String BG_LOOPER_NAME = "background_looper";
    public static final DependencyKey<Looper> BG_LOOPER = new DependencyKey<>(BG_LOOPER_NAME);
    public static final DependencyKey<Handler> TIME_TICK_HANDLER = new DependencyKey<>("time_tick_handler");
    public static final String MAIN_HANDLER_NAME = "main_handler";
    public static final DependencyKey<Handler> MAIN_HANDLER = new DependencyKey<>(MAIN_HANDLER_NAME);
    public static final DependencyKey<String> LEAK_REPORT_EMAIL = new DependencyKey<>("leak_report_email");

    public interface DependencyProvider<T> {
        T createDependency();
    }

    static /* synthetic */ Object lambda$start$3() {
        return null;
    }

    /* JADX INFO: renamed from: lambda$xzGuYK767r-qPLSqqTFwvjd1zLk, reason: not valid java name */
    public static /* synthetic */ MetricsLogger m7lambda$xzGuYK767rqPLSqqTFwvjd1zLk() {
        return new MetricsLogger();
    }

    @Override // com.android.systemui.SystemUI
    public void start() {
        this.mProviders.put(TIME_TICK_HANDLER, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$43jHYB0tmkZW9gOp8fWQ0jsQtT8
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return Dependency.lambda$start$0();
            }
        });
        this.mProviders.put(BG_LOOPER, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$bM-h7HZK68YYa_3q_pwxRSJC4Yw
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return Dependency.lambda$start$1();
            }
        });
        this.mProviders.put(MAIN_HANDLER, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$kqXnJQTBVaI7uHsQu09pQCi6iP8
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return Dependency.lambda$start$2();
            }
        });
        this.mProviders.put(LEAK_REPORT_EMAIL, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$ZPpW65CZse75xWbiSOFc4qbukZQ
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return Dependency.lambda$start$3();
            }
        });
        this.mProviders.put(HvacController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$WQBBXgDOjLvfQxuuSCjALEUY_wU
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$4$Dependency();
            }
        });
        this.mProviders.put(StatusBarIconController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$QUC0lkMz_7if4fzDmsd-jYj9Xb8
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$5$Dependency();
            }
        });
        this.mProviders.put(StatusBarWindowManager.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$UYJ74bi_ob6-JeNwaTVRDRYDBgs
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$6$Dependency();
            }
        });
        this.mProviders.put(IWindowManager.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$XRZ6BrbjFf91135wEs63LjjYU6k
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return WindowManagerGlobal.getWindowManagerService();
            }
        });
        this.mProviders.put(FragmentService.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$odKw7cF7Ls4glGvV4fLloQflLtQ
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$7$Dependency();
            }
        });
        this.mProviders.put(AirPanelController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$inIkqdSkkvRyfhLhHRc0mbXHlVg
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$8$Dependency();
            }
        });
        this.mProviders.put(GestureControl.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$yGSXCFl0_yusojbIlbVkLF9i3ZQ
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$9$Dependency();
            }
        });
        this.mProviders.put(MetricsLogger.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$xzGuYK767r-qPLSqqTFwvjd1zLk
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return Dependency.m7lambda$xzGuYK767rqPLSqqTFwvjd1zLk();
            }
        });
        this.mProviders.put(PluginManager.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$jA3RFU2mePxiVq3Udrd8ENknuMc
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$10$Dependency();
            }
        });
        this.mProviders.put(PluginDependencyProvider.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$UOr6IyXhhK6DFgnT_XHn5efTR1g
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return Dependency.lambda$start$11();
            }
        });
        this.mProviders.put(LeakDetector.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$0nZfKRratosTWauzHk4c6Gaq274
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return LeakDetector.create();
            }
        });
        this.mProviders.put(NetworkController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$TSO2Xq_JvhAqKUk5e1I1j6zQF2c
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$12$Dependency();
            }
        });
        this.mProviders.put(CarController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$ku8TKueyA8_7ImwdX3Db-RXxuUs
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$13$Dependency();
            }
        });
        this.mProviders.put(ClockController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$Qs22LaW-cHM_1Yi4C8q5ysawisc
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$14$Dependency();
            }
        });
        this.mProviders.put(VolumeController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$5X55A0LvJS3YqLfBkn6qUrCR3Fw
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$15$Dependency();
            }
        });
        this.mProviders.put(UsbController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$Xu9xjcimihjNkQEi8DCQLGVsZvA
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$16$Dependency();
            }
        });
        this.mProviders.put(BluetoothController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$nb2t6xCT5q2M0nlbqonOwPR5AmY
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$17$Dependency();
            }
        });
        this.mProviders.put(BrightnessController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$LVh0M0qMbxJcdwEgV96UX1DJk9E
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$18$Dependency();
            }
        });
        this.mProviders.put(QSFactoryImpl.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$mUpUpgpc3gm-gL9WDSjdCqVMQq4
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$19$Dependency();
            }
        });
        this.mProviders.put(BlurViewController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$QDRYSCP0y1D5i2cZtvgmOKtTAAc
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$20$Dependency();
            }
        });
        this.mProviders.put(IStatusBarService.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$EVJ7UhrVRWrcnobJFjy4F5PYJ1o
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
            }
        });
        this.mProviders.put(SplitScreenController.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$KB2adNntBnmoo6TnhpK-5rrRegA
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$22$Dependency();
            }
        });
        this.mProviders.put(IndexWindowManager.class, new DependencyProvider() { // from class: com.android.systemui.-$$Lambda$Dependency$myim_owfKi1l1jvlIg3Z-eJqreg
            @Override // com.android.systemui.Dependency.DependencyProvider
            public final Object createDependency() {
                return this.f$0.lambda$start$23$Dependency();
            }
        });
        sDependency = this;
    }

    static /* synthetic */ Object lambda$start$0() {
        HandlerThread handlerThread = new HandlerThread("TimeTick");
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    static /* synthetic */ Object lambda$start$1() {
        HandlerThread handlerThread = new HandlerThread("SysUiBg", 10);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    static /* synthetic */ Object lambda$start$2() {
        return new Handler(Looper.getMainLooper());
    }

    public /* synthetic */ Object lambda$start$4$Dependency() {
        return new HvacControllerImpl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$5$Dependency() {
        return new StatusBarIconController(this.mContext);
    }

    public /* synthetic */ Object lambda$start$6$Dependency() {
        return new StatusBarWindowManager(this.mContext);
    }

    public /* synthetic */ Object lambda$start$7$Dependency() {
        return new FragmentService(this.mContext);
    }

    public /* synthetic */ Object lambda$start$8$Dependency() {
        return new AirPanelController(this.mContext);
    }

    public /* synthetic */ Object lambda$start$9$Dependency() {
        return new GestureControl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$10$Dependency() {
        return new PluginManagerImpl(this.mContext);
    }

    static /* synthetic */ Object lambda$start$11() {
        return new PluginDependencyProvider((PluginManager) get(PluginManager.class));
    }

    public /* synthetic */ Object lambda$start$12$Dependency() {
        return new NetworkControllerImpl(this.mContext, (Looper) getDependency(BG_LOOPER));
    }

    public /* synthetic */ Object lambda$start$13$Dependency() {
        return new CarControllerImpl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$14$Dependency() {
        return new ClockControllerImpl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$15$Dependency() {
        return new VolumeControllerImpl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$16$Dependency() {
        return new UsbControllerImpl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$17$Dependency() {
        return new BluetoothControllerImpl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$18$Dependency() {
        return new BrightnessControllerImpl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$19$Dependency() {
        return new QSFactoryImpl(this.mContext);
    }

    public /* synthetic */ Object lambda$start$20$Dependency() {
        return new BlurViewController(this.mContext);
    }

    public /* synthetic */ Object lambda$start$22$Dependency() {
        return new SplitScreenController(this.mContext);
    }

    public /* synthetic */ Object lambda$start$23$Dependency() {
        return new IndexWindowManager(this.mContext);
    }

    @Override // com.android.systemui.SystemUI
    protected void onBootCompleted() {
        OverviewProxyService.initOverviewProxyService(this.mContext);
        Log.e(TAG, "onBootCompleted DeviceManager.getInstance().init ");
        DeviceManager.getInstance().init(this.mContext);
    }

    @Override // com.android.systemui.SystemUI
    public synchronized void dump(final FileDescriptor fd, final PrintWriter pw, final String[] args) {
        pw.println("Dumping existing controllers:");
        this.mDependencies.values().stream().filter(new Predicate() { // from class: com.android.systemui.-$$Lambda$Dependency$LRLRIEN2RvbecTR9NABCQbabHEY
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Dependency.lambda$dump$24(obj);
            }
        }).forEach(new Consumer() { // from class: com.android.systemui.-$$Lambda$Dependency$DQJDpDmhwC93xaTu0yM6zz2ullw
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Dumpable) obj).dump(fd, pw, args);
            }
        });
    }

    static /* synthetic */ boolean lambda$dump$24(Object obj) {
        return obj instanceof Dumpable;
    }

    @Override // com.android.systemui.SystemUI
    protected synchronized void onConfigurationChanged(Configuration newConfig) {
    }

    protected final <T> T getDependency(Class<T> cls) {
        return (T) getDependencyInner(cls);
    }

    protected final <T> T getDependency(DependencyKey<T> dependencyKey) {
        return (T) getDependencyInner(dependencyKey);
    }

    private synchronized <T> T getDependencyInner(Object obj) {
        T t;
        t = (T) this.mDependencies.get(obj);
        if (t == null) {
            t = (T) createDependency(obj);
            this.mDependencies.put(obj, t);
        }
        return t;
    }

    protected <T> T createDependency(Object obj) {
        Preconditions.checkArgument((obj instanceof DependencyKey) || (obj instanceof Class));
        DependencyProvider dependencyProvider = this.mProviders.get(obj);
        if (dependencyProvider == null) {
            throw new IllegalArgumentException("Unsupported dependency " + obj + ". " + this.mProviders.size() + " providers known.");
        }
        return (T) dependencyProvider.createDependency();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void destroyDependency(Class<T> cls, Consumer<T> destroy) {
        Object objRemove = this.mDependencies.remove(cls);
        if (objRemove == null || destroy == 0) {
            return;
        }
        destroy.accept(objRemove);
    }

    public static void initDependencies(Context context) {
        if (sDependency != null) {
            return;
        }
        Dependency dependency = new Dependency();
        dependency.mContext = context;
        dependency.mComponents = new HashMap();
        dependency.start();
    }

    public static void clearDependencies() {
        sDependency = null;
    }

    public static <T> void destroy(Class<T> cls, Consumer<T> destroy) {
        sDependency.destroyDependency(cls, destroy);
    }

    public static <T> T get(Class<T> cls) {
        return (T) sDependency.getDependency(cls);
    }

    public static <T> T get(DependencyKey<T> dependencyKey) {
        return (T) sDependency.getDependency(dependencyKey);
    }

    public static final class DependencyKey<V> {
        private final String mDisplayName;

        public DependencyKey(String displayName) {
            this.mDisplayName = displayName;
        }

        public String toString() {
            return this.mDisplayName;
        }

        public int hashCode() {
            return super.hashCode();
        }

        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
