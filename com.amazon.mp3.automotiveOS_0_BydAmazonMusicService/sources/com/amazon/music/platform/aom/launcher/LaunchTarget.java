package com.amazon.music.platform.aom.launcher;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LaunchTarget.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\"\u0010#J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0011R\u0017\u0010\u0019\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u001a\u0010\u0011R\u0017\u0010\u001b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u0011R\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006$"}, d2 = {"Lcom/amazon/music/platform/aom/launcher/LaunchTarget;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/aom/launcher/CatalogType;", "catalogType", "Lcom/amazon/music/platform/aom/launcher/CatalogType;", "getCatalogType", "()Lcom/amazon/music/platform/aom/launcher/CatalogType;", "identifier", "Ljava/lang/String;", "getIdentifier", "()Ljava/lang/String;", "Lcom/amazon/music/platform/aom/launcher/IdentifierType;", "identifierType", "Lcom/amazon/music/platform/aom/launcher/IdentifierType;", "getIdentifierType", "()Lcom/amazon/music/platform/aom/launcher/IdentifierType;", "catalogId", "getCatalogId", "targetName", "getTargetName", "token", "getToken", "Lcom/amazon/music/platform/aom/launcher/LaunchConfig;", "launchConfig", "Lcom/amazon/music/platform/aom/launcher/LaunchConfig;", "getLaunchConfig", "()Lcom/amazon/music/platform/aom/launcher/LaunchConfig;", "<init>", "(Lcom/amazon/music/platform/aom/launcher/CatalogType;Ljava/lang/String;Lcom/amazon/music/platform/aom/launcher/IdentifierType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/aom/launcher/LaunchConfig;)V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class LaunchTarget {
    private final String catalogId;
    private final CatalogType catalogType;
    private final String identifier;
    private final IdentifierType identifierType;
    private final LaunchConfig launchConfig;
    private final String targetName;
    private final String token;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LaunchTarget)) {
            return false;
        }
        LaunchTarget launchTarget = (LaunchTarget) other;
        return this.catalogType == launchTarget.catalogType && Intrinsics.areEqual(this.identifier, launchTarget.identifier) && this.identifierType == launchTarget.identifierType && Intrinsics.areEqual(this.catalogId, launchTarget.catalogId) && Intrinsics.areEqual(this.targetName, launchTarget.targetName) && Intrinsics.areEqual(this.token, launchTarget.token) && Intrinsics.areEqual(this.launchConfig, launchTarget.launchConfig);
    }

    public int hashCode() {
        int iHashCode = ((((this.catalogType.hashCode() * 31) + this.identifier.hashCode()) * 31) + this.identifierType.hashCode()) * 31;
        String str = this.catalogId;
        int iHashCode2 = (((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.targetName.hashCode()) * 31) + this.token.hashCode()) * 31;
        LaunchConfig launchConfig = this.launchConfig;
        return iHashCode2 + (launchConfig != null ? launchConfig.hashCode() : 0);
    }

    public String toString() {
        return "LaunchTarget(catalogType=" + this.catalogType + ", identifier=" + this.identifier + ", identifierType=" + this.identifierType + ", catalogId=" + this.catalogId + ", targetName=" + this.targetName + ", token=" + this.token + ", launchConfig=" + this.launchConfig + ')';
    }

    public LaunchTarget(CatalogType catalogType, String identifier, IdentifierType identifierType, String str, String targetName, String token, LaunchConfig launchConfig) {
        Intrinsics.checkNotNullParameter(catalogType, "catalogType");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(identifierType, "identifierType");
        Intrinsics.checkNotNullParameter(targetName, "targetName");
        Intrinsics.checkNotNullParameter(token, "token");
        this.catalogType = catalogType;
        this.identifier = identifier;
        this.identifierType = identifierType;
        this.catalogId = str;
        this.targetName = targetName;
        this.token = token;
        this.launchConfig = launchConfig;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final String getToken() {
        return this.token;
    }
}
