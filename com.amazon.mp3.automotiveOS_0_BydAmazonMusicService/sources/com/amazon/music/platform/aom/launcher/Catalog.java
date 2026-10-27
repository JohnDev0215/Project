package com.amazon.music.platform.aom.launcher;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.aom.PlatformProviderUtilKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Catalog.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\u0013\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/platform/aom/launcher/Catalog;", "", "Lcom/amazon/music/platform/aom/launcher/CatalogType;", "component1", "", "Lcom/amazon/music/platform/aom/launcher/IdentifierType;", "component2", "", "toString", "", "hashCode", "other", "", "equals", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/platform/aom/launcher/CatalogType;", "getType", "()Lcom/amazon/music/platform/aom/launcher/CatalogType;", "identifierTypes", "Ljava/util/List;", "getIdentifierTypes", "()Ljava/util/List;", "<init>", "(Lcom/amazon/music/platform/aom/launcher/CatalogType;Ljava/util/List;)V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Catalog {
    private final List<IdentifierType> identifierTypes;
    private final CatalogType type;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CatalogType getType() {
        return this.type;
    }

    public final List<IdentifierType> component2() {
        return this.identifierTypes;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Catalog)) {
            return false;
        }
        Catalog catalog = (Catalog) other;
        return this.type == catalog.type && Intrinsics.areEqual(this.identifierTypes, catalog.identifierTypes);
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + this.identifierTypes.hashCode();
    }

    public String toString() {
        return "Catalog(type=" + this.type + ", identifierTypes=" + this.identifierTypes + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Catalog(CatalogType type, List<? extends IdentifierType> identifierTypes) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(identifierTypes, "identifierTypes");
        this.type = type;
        this.identifierTypes = identifierTypes;
        PlatformProviderUtilKt.freeze(this);
    }
}
