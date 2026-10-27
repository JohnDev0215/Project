package com.amazon.music.platform.aom.hints.guiContext;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GUIContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00020\u000e¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR#\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/platform/aom/hints/guiContext/GUIContext;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/aom/hints/guiContext/GUIContextPageType;", "pageType", "Lcom/amazon/music/platform/aom/hints/guiContext/GUIContextPageType;", "getPageType", "()Lcom/amazon/music/platform/aom/hints/guiContext/GUIContextPageType;", "", "Lcom/amazon/music/platform/aom/hints/guiContext/GUIContextMetadataKey;", "metadata", "Ljava/util/Map;", "getMetadata", "()Ljava/util/Map;", "<init>", "(Lcom/amazon/music/platform/aom/hints/guiContext/GUIContextPageType;Ljava/util/Map;)V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class GUIContext {
    private final Map<GUIContextMetadataKey, String> metadata;
    private final GUIContextPageType pageType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GUIContext)) {
            return false;
        }
        GUIContext gUIContext = (GUIContext) other;
        return this.pageType == gUIContext.pageType && Intrinsics.areEqual(this.metadata, gUIContext.metadata);
    }

    public int hashCode() {
        return (this.pageType.hashCode() * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "GUIContext(pageType=" + this.pageType + ", metadata=" + this.metadata + ')';
    }

    public GUIContext(GUIContextPageType pageType, Map<GUIContextMetadataKey, String> metadata) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.pageType = pageType;
        this.metadata = metadata;
    }

    public final GUIContextPageType getPageType() {
        return this.pageType;
    }

    public final Map<GUIContextMetadataKey, String> getMetadata() {
        return this.metadata;
    }
}
