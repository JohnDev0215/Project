package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
public class MAPUtils {
    private final DebugProperties debugProperties;
    private final boolean isMAPAvailable;
    private final MAPAccountManagerFactory mapAccountManagerFactory;
    private AccountManagerWrapper mapAcctMgr;

    public MAPUtils() {
        this(new ReflectionUtils(), DebugProperties.getInstance(), new MAPAccountManagerFactory());
    }

    MAPUtils(ReflectionUtils reflectionUtils, DebugProperties debugProperties, MAPAccountManagerFactory mAPAccountManagerFactory) {
        boolean zIsClassAvailable = reflectionUtils.isClassAvailable("com.amazon.identity.auth.device.api.MAPAccountManager");
        this.isMAPAvailable = zIsClassAvailable;
        this.debugProperties = debugProperties;
        this.mapAccountManagerFactory = mAPAccountManagerFactory;
        if (zIsClassAvailable) {
            this.mapAcctMgr = mAPAccountManagerFactory.getMapAccountManager();
        }
    }

    public boolean isMAPAvailable() {
        return this.isMAPAvailable;
    }
}
