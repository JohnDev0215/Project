package com.amazon.identity.platform.metric.csm;

import com.amazon.identity.auth.device.ga;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class MAPCSMTransitionFactoryProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public MAPCSMTransitionFactory f712a;
    public Class b;

    /* JADX INFO: compiled from: DCP */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final MAPCSMTransitionFactoryProvider f713a = new MAPCSMTransitionFactoryProvider();
    }

    public static MAPCSMTransitionFactoryProvider getInstance() {
        return b.f713a;
    }

    private void getMAPCSMTransitionFactoryImpl() {
        try {
            Class<?> cls = Class.forName("com.amazon.csm.map.MAPCSMTransitionFactoryImpl", false, MAPCSMTransitionFactoryProvider.class.getClassLoader());
            this.b = cls;
            if (cls != null) {
                this.f712a = (MAPCSMTransitionFactory) cls.newInstance();
                ga.a("MAPCSMTransitionFactoryProvider");
            }
        } catch (ClassNotFoundException unused) {
            ga.a("MAPCSMTransitionFactoryProvider");
        } catch (IllegalAccessException unused2) {
            ga.a("MAPCSMTransitionFactoryProvider");
        } catch (InstantiationException unused3) {
            ga.a("MAPCSMTransitionFactoryProvider");
        }
    }

    public MAPCSMTransitionFactory getMAPCSMTransitionFactory() {
        return this.f712a;
    }

    private MAPCSMTransitionFactoryProvider() {
        getMAPCSMTransitionFactoryImpl();
    }
}
