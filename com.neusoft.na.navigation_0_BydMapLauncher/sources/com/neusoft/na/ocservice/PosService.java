package com.neusoft.na.ocservice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.na.common.IOptionalComponent;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.Tool;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.na.onecore.PositioningProvider;
import com.neusoft.na.pos.Positioning;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: PosService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0014J\"\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/neusoft/na/ocservice/PosService;", "Lcom/neusoft/na/common/ServiceBase;", "()V", "mComponentPositioning", "Lcom/neusoft/na/common/IOptionalComponent;", "mPositioning", "Lcom/neusoft/na/pos/Positioning;", "init", "", "onCreate", "onDestroy", "onStart", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "startPos", "", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PosService extends ServiceBase {
    public static final String BYD_8155F_POSITIONING_CLASSNAME = "com.neusoft.na.byd.Byd8155fPositioning";
    public static final String BYD_POSITIONING_CLASSNAME = "com.neusoft.na.byd.BydPositioning";
    public static final String CHANGANPOSITIONING_CLASSNAME = "com.neusoft.na.changan.ChanganPositioning";
    public static final String ECARXPOSITIONING_CLASSNAME = "com.neusoft.na.ecarx.EcarxPositioning";
    public static final String GREATWALLA01POSITIONING_CLASSNAME = "com.neusoft.na.greatwalla01.GreatWallA01Positioning";
    public static final String GREATWALLB01POSITIONING_CLASSNAME = "com.neusoft.na.greatwallb01.GreatWallB01Positioning";
    public static final String GREATWALLP3POSITIONING_CLASSNAME = "com.neusoft.na.greatwallp3.GreatWallP3Positioning";
    public static final String GREATWALLV61POSITIONING_CLASSNAME = "com.neusoft.na.greatwallv61.GreatWallV61Positioning";
    public static final String GREATWALLVENDOROFFLINESTATUS_CLASSNAME = "com.neusoft.na.greatwallv61.GreatWallVendorOffLineStatus";
    public static final String PROTONMC2POSITIONING_CLASSNAME = "com.neusoft.na.protonmc2.ProtonMC2Positioning";
    public static final String PROTON_X11_POSITIONING_SYSTEM_CLASSNAME = "com.neusoft.protonx11mobile.ProtonX11MobilePositioning";
    public static final String SERES_POSITIONING_CLASSNAME = "com.neusoft.na.seres.SeresPositioning";
    public static final String SMART_POSITIONING_CLASSNAME = "com.neusoft.na.smart.SmartPositioning";
    public static final String STANDARDPOSITIONING_CLASSNAME = "com.neusoft.na.standard.StandardPositioning";
    public static final String WEYFORPRODUCTV3_POSITIONING_CLASSNAME = "com.neusoft.weyforproductv3.Weyforproductv3Positioning";
    public static final String X70POSITIONING_CLASSNAME = "com.neusoft.na.protonx70.X70Positioning";
    public static final String ZEEKRBX1E_POSITIONING_CLASSNAME = "com.neusoft.na.zeekrbx1e.ZeekrPositioning";
    public static final String ZEEKRDC1E_POSITIONING_CLASSNAME = "com.neusoft.na.zeekrdc1e.ZeekrPositioning";
    private IOptionalComponent mComponentPositioning;
    private Positioning mPositioning;

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.PosService$onCreate$10, reason: invalid class name */
    /* JADX INFO: compiled from: PosService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass10 extends Lambda implements Function0<Object> {
        public static final AnonymousClass10 INSTANCE = new AnonymousClass10();

        AnonymousClass10() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to create instance";
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.PosService$onCreate$11, reason: invalid class name */
    /* JADX INFO: compiled from: PosService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass11 extends Lambda implements Function0<Object> {
        public static final AnonymousClass11 INSTANCE = new AnonymousClass11();

        AnonymousClass11() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to find class 'com.neusoft.na.protonx70.X70Positioning'";
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.PosService$onCreate$12, reason: invalid class name */
    /* JADX INFO: compiled from: PosService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass12 extends Lambda implements Function0<Object> {
        public static final AnonymousClass12 INSTANCE = new AnonymousClass12();

        AnonymousClass12() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to load class 'com.neusoft.na.protonx70.X70Positioning'";
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.PosService$onCreate$9, reason: invalid class name */
    /* JADX INFO: compiled from: PosService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass9 extends Lambda implements Function0<Object> {
        public static final AnonymousClass9 INSTANCE = new AnonymousClass9();

        AnonymousClass9() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "instance does not implement IOptionalComponent";
        }
    }

    public PosService() {
        super("pos");
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceBase.INSTANCE.getLogger().debug("PosService.onStartCommand()");
        int iOnStartCommand = super.onStartCommand(intent, flags, startId);
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.UNINITIALIZED) {
            init();
        }
        return iOnStartCommand;
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        ServiceBase.INSTANCE.getLogger().debug("PosService.onCreate()");
        super.onCreate();
        if (CheckBoardKt.isZeekr()) {
            final String str = ZEEKRDC1E_POSITIONING_CLASSNAME;
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                Intrinsics.checkNotNull(classLoader);
                Class<?> clsLoadClass = classLoader.loadClass(ZEEKRDC1E_POSITIONING_CLASSNAME);
                if (clsLoadClass != null) {
                    Object objNewInstance = clsLoadClass.getConstructor(Context.class).newInstance(this);
                    if (objNewInstance != null) {
                        if (objNewInstance instanceof IOptionalComponent) {
                            this.mComponentPositioning = (IOptionalComponent) objNewInstance;
                        } else {
                            ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.37
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return "instance does not implement IOptionalComponent";
                                }
                            });
                        }
                    } else {
                        ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.38
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "failed to create instance";
                            }
                        });
                    }
                } else {
                    ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.39
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "failed to find class '" + str + '\'';
                        }
                    });
                }
            } catch (RuntimeException unused) {
                ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.40
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "failed to load class '" + str + '\'';
                    }
                });
            }
        }
        if (CheckBoardKt.isGreatwallV61()) {
            try {
                ClassLoader classLoader2 = getClass().getClassLoader();
                Intrinsics.checkNotNull(classLoader2);
                Class<?> clsLoadClass2 = classLoader2.loadClass(GREATWALLV61POSITIONING_CLASSNAME);
                if (clsLoadClass2 != null) {
                    Object objNewInstance2 = clsLoadClass2.getConstructor(Context.class).newInstance(this);
                    if (objNewInstance2 != null) {
                        if (objNewInstance2 instanceof IOptionalComponent) {
                            this.mComponentPositioning = (IOptionalComponent) objNewInstance2;
                            Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass success.");
                        } else {
                            ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.41
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return "instance does not implement IOptionalComponent";
                                }
                            });
                            Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error1");
                        }
                    } else {
                        ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.42
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "failed to create instance";
                            }
                        });
                        Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error2");
                    }
                } else {
                    ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.43
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "failed to find class 'com.neusoft.na.greatwallv61.GreatWallV61Positioning'";
                        }
                    });
                    Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error3");
                }
            } catch (RuntimeException unused2) {
                ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.44
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "failed to load class 'com.neusoft.na.greatwallv61.GreatWallV61Positioning'";
                    }
                });
                Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error4");
            }
        }
        if (CheckBoardKt.isByd()) {
            try {
                ClassLoader classLoader3 = getClass().getClassLoader();
                Intrinsics.checkNotNull(classLoader3);
                Object objNewInstance3 = classLoader3.loadClass(BYD_POSITIONING_CLASSNAME).getConstructor(Context.class).newInstance(this);
                if (objNewInstance3 != null) {
                    if (objNewInstance3 instanceof IOptionalComponent) {
                        this.mComponentPositioning = (IOptionalComponent) objNewInstance3;
                        Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass success.");
                    } else {
                        ServiceBase.INSTANCE.getLogger().error("instance does not implement IOptionalComponent");
                        Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error1");
                    }
                } else {
                    ServiceBase.INSTANCE.getLogger().error("failed to create instance");
                    Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error2");
                }
            } catch (Exception e) {
                ServiceBase.INSTANCE.getLogger().error("failed to load class 'com.neusoft.na.byd.BydPositioning'");
                Log.i(ServiceBase.INSTANCE.getTAG(), "failed to load class 'com.neusoft.na.byd.BydPositioning'");
                e.printStackTrace();
            }
        }
        if (CheckBoardKt.isByd8155f()) {
            try {
                ClassLoader classLoader4 = getClass().getClassLoader();
                Intrinsics.checkNotNull(classLoader4);
                Object objNewInstance4 = classLoader4.loadClass(BYD_8155F_POSITIONING_CLASSNAME).getConstructor(Context.class).newInstance(this);
                if (objNewInstance4 != null) {
                    if (objNewInstance4 instanceof IOptionalComponent) {
                        this.mComponentPositioning = (IOptionalComponent) objNewInstance4;
                        Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass success.");
                    } else {
                        ServiceBase.INSTANCE.getLogger().error("instance does not implement IOptionalComponent");
                        Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error1");
                    }
                } else {
                    ServiceBase.INSTANCE.getLogger().error("failed to create instance");
                    Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error2");
                }
            } catch (Exception e2) {
                ServiceBase.INSTANCE.getLogger().error("failed to load class 'com.neusoft.na.byd.Byd8155fPositioning'");
                Log.i(ServiceBase.INSTANCE.getTAG(), "failed to load class 'com.neusoft.na.byd.Byd8155fPositioning'");
                e2.printStackTrace();
            }
        }
        if (CheckBoardKt.isWeyforproductv3()) {
            try {
                ClassLoader classLoader5 = getClass().getClassLoader();
                Intrinsics.checkNotNull(classLoader5);
                Class<?> clsLoadClass3 = classLoader5.loadClass(WEYFORPRODUCTV3_POSITIONING_CLASSNAME);
                if (clsLoadClass3 != null) {
                    Object objNewInstance5 = clsLoadClass3.getConstructor(Context.class).newInstance(this);
                    if (objNewInstance5 != null) {
                        if (objNewInstance5 instanceof IOptionalComponent) {
                            this.mComponentPositioning = (IOptionalComponent) objNewInstance5;
                            Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass success.");
                        } else {
                            ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.45
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return "instance does not implement IOptionalComponent";
                                }
                            });
                            Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error1");
                        }
                    } else {
                        ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.46
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "failed to create instance";
                            }
                        });
                        Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error2");
                    }
                } else {
                    ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.47
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "failed to find class 'com.neusoft.na.greatwallv61.GreatWallV61Positioning'";
                        }
                    });
                    Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error3");
                }
            } catch (RuntimeException unused3) {
                ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.48
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "failed to load class 'com.neusoft.na.greatwallv61.GreatWallV61Positioning'";
                    }
                });
                Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error4");
            }
        }
        if (CheckBoardKt.isProtonX11Mobile()) {
            try {
                ClassLoader classLoader6 = getClass().getClassLoader();
                Intrinsics.checkNotNull(classLoader6);
                Class<?> clsLoadClass4 = classLoader6.loadClass(PROTON_X11_POSITIONING_SYSTEM_CLASSNAME);
                if (clsLoadClass4 != null) {
                    Object objNewInstance6 = clsLoadClass4.getConstructor(Context.class).newInstance(this);
                    if (objNewInstance6 != null) {
                        if (objNewInstance6 instanceof IOptionalComponent) {
                            this.mComponentPositioning = (IOptionalComponent) objNewInstance6;
                            Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass success.");
                        } else {
                            ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.49
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return "instance does not implement IOptionalComponent";
                                }
                            });
                            Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error1");
                        }
                    } else {
                        ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.50
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "failed to create instance";
                            }
                        });
                        Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error2");
                    }
                } else {
                    ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.51
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "failed to find class 'com.neusoft.na.greatwallv61.GreatWallV61Positioning'";
                        }
                    });
                    Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error3");
                }
            } catch (RuntimeException unused4) {
                ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.52
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "failed to load class 'com.neusoft.na.greatwallv61.GreatWallV61Positioning'";
                    }
                });
                Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error4");
            }
        }
        if (CheckBoardKt.isStandard()) {
            try {
                ClassLoader classLoader7 = getClass().getClassLoader();
                Intrinsics.checkNotNull(classLoader7);
                Class<?> clsLoadClass5 = classLoader7.loadClass(STANDARDPOSITIONING_CLASSNAME);
                if (clsLoadClass5 != null) {
                    Object objNewInstance7 = clsLoadClass5.getConstructor(Context.class).newInstance(this);
                    if (objNewInstance7 != null) {
                        if (objNewInstance7 instanceof IOptionalComponent) {
                            this.mComponentPositioning = (IOptionalComponent) objNewInstance7;
                            Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass success.");
                        } else {
                            ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.53
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return "instance does not implement IOptionalComponent";
                                }
                            });
                            Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error1");
                        }
                    } else {
                        ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.54
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "failed to create instance";
                            }
                        });
                        Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error2");
                    }
                } else {
                    ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.55
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "failed to find class 'com.neusoft.na.standard.StandardPositioning'";
                        }
                    });
                    Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error3");
                }
            } catch (RuntimeException unused5) {
                ServiceBase.INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.ocservice.PosService.onCreate.56
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "failed to load class 'com.neusoft.na.standard.StandardPositioning'";
                    }
                });
                Log.i(ServiceBase.INSTANCE.getTAG(), "loadClass error4");
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        ServiceBase.INSTANCE.getLogger().debug("PosService.onDestroy()");
        Positioning positioning = this.mPositioning;
        if (positioning != null) {
            positioning.stop();
        }
        IOptionalComponent iOptionalComponent = this.mComponentPositioning;
        if (iOptionalComponent != null) {
            iOptionalComponent.stop();
        }
        super.onDestroy();
    }

    private final synchronized void init() {
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZING, null, 2, null));
        getPrivateTempDir().mkdirs();
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZED, null, 2, null));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0120  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean startPos() throws java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instruction units count: 758
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.PosService.startPos():boolean");
    }

    @Override // com.neusoft.na.common.ServiceBase
    protected synchronized void onStart() {
        if (this.mPositioning == null) {
            this.mPositioning = PositioningProvider.INSTANCE.getInstance();
        }
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.INITIALIZED) {
            updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.STARTING, null, 2, null));
            initPrivateTempDir();
            File privateCacheDir = getPrivateCacheDir();
            if (!privateCacheDir.isDirectory()) {
                privateCacheDir.mkdirs();
            }
            startPos();
            IOptionalComponent iOptionalComponent = this.mComponentPositioning;
            if (iOptionalComponent != null) {
                iOptionalComponent.start();
            }
            updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.RUNNING, null, 2, null));
            Tool.getSelfMaps("POS");
        }
    }
}
