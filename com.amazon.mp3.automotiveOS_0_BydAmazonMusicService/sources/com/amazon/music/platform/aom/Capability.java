package com.amazon.music.platform.aom;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Capability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB+\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0005\u001a\u00020\u0002HÆ\u0003J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\b\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\u0013\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/platform/aom/Capability;", "", "", "component1", "component2", "component3", "Lcom/amazon/music/platform/aom/Configuration;", "component4", "toString", "", "hashCode", "other", "", "equals", "capabilityInterface", "Ljava/lang/String;", "getCapabilityInterface", "()Ljava/lang/String;", "capabilityType", "getCapabilityType", "capabilityVersion", "getCapabilityVersion", "configuration", "Lcom/amazon/music/platform/aom/Configuration;", "getConfiguration", "()Lcom/amazon/music/platform/aom/Configuration;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/aom/Configuration;)V", "Companion", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Capability {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String capabilityInterface;
    private final String capabilityType;
    private final String capabilityVersion;
    private final Configuration configuration;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCapabilityInterface() {
        return this.capabilityInterface;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCapabilityType() {
        return this.capabilityType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCapabilityVersion() {
        return this.capabilityVersion;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Configuration getConfiguration() {
        return this.configuration;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Capability)) {
            return false;
        }
        Capability capability = (Capability) other;
        return Intrinsics.areEqual(this.capabilityInterface, capability.capabilityInterface) && Intrinsics.areEqual(this.capabilityType, capability.capabilityType) && Intrinsics.areEqual(this.capabilityVersion, capability.capabilityVersion) && Intrinsics.areEqual(this.configuration, capability.configuration);
    }

    public int hashCode() {
        int iHashCode = ((((this.capabilityInterface.hashCode() * 31) + this.capabilityType.hashCode()) * 31) + this.capabilityVersion.hashCode()) * 31;
        Configuration configuration = this.configuration;
        return iHashCode + (configuration == null ? 0 : configuration.hashCode());
    }

    public String toString() {
        return "Capability(capabilityInterface=" + this.capabilityInterface + ", capabilityType=" + this.capabilityType + ", capabilityVersion=" + this.capabilityVersion + ", configuration=" + this.configuration + ')';
    }

    public Capability(String capabilityInterface, String capabilityType, String capabilityVersion, Configuration configuration) {
        Intrinsics.checkNotNullParameter(capabilityInterface, "capabilityInterface");
        Intrinsics.checkNotNullParameter(capabilityType, "capabilityType");
        Intrinsics.checkNotNullParameter(capabilityVersion, "capabilityVersion");
        this.capabilityInterface = capabilityInterface;
        this.capabilityType = capabilityType;
        this.capabilityVersion = capabilityVersion;
        this.configuration = configuration;
        PlatformProviderUtilKt.freeze(this);
    }

    /* JADX INFO: compiled from: Capability.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/platform/aom/Capability$Companion;", "", "()V", "create", "Lcom/amazon/music/platform/aom/Capability;", "capabilityInterface", "", "capabilityType", "version", "configuration", "Lcom/amazon/music/platform/aom/Configuration;", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Capability create(String capabilityInterface, String capabilityType, String version) {
            Intrinsics.checkNotNullParameter(capabilityInterface, "capabilityInterface");
            Intrinsics.checkNotNullParameter(capabilityType, "capabilityType");
            Intrinsics.checkNotNullParameter(version, "version");
            return create(capabilityInterface, capabilityType, version, null);
        }

        public final Capability create(String capabilityInterface, String capabilityType, String version, Configuration configuration) {
            Intrinsics.checkNotNullParameter(capabilityInterface, "capabilityInterface");
            Intrinsics.checkNotNullParameter(capabilityType, "capabilityType");
            Intrinsics.checkNotNullParameter(version, "version");
            return new Capability(capabilityInterface, capabilityType, version, configuration);
        }
    }
}
