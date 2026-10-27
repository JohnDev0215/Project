package com.amazon.mrn.v1;

import com.amazon.mrn.MediaResourceName;
import com.amazon.mrn.model.MrnVersion;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaResourceNameV1.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BK\b\u0000\u0012\b\b\u0002\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001d\u001a\u00020\u0002\u0012\u0016\b\u0002\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001f¢\u0006\u0004\b$\u0010%J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR%\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/amazon/mrn/v1/MediaResourceNameV1;", "Lcom/amazon/mrn/MediaResourceName;", "", "toString", "", "hashCode", "", "other", "", "equals", "version", "Ljava/lang/String;", "getVersion", "()Ljava/lang/String;", "Lcom/amazon/mrn/v1/MrnAuthority;", "authority", "Lcom/amazon/mrn/v1/MrnAuthority;", "getAuthority", "()Lcom/amazon/mrn/v1/MrnAuthority;", "Lcom/amazon/mrn/v1/MrnMediaType;", "mediaType", "Lcom/amazon/mrn/v1/MrnMediaType;", "getMediaType", "()Lcom/amazon/mrn/v1/MrnMediaType;", "Lcom/amazon/mrn/v1/MrnIdentifierType;", "mediaIdentifierType", "Lcom/amazon/mrn/v1/MrnIdentifierType;", "getMediaIdentifierType", "()Lcom/amazon/mrn/v1/MrnIdentifierType;", "identifier", "getIdentifier", "", "attributes", "Ljava/util/Map;", "getAttributes", "()Ljava/util/Map;", "<init>", "(Ljava/lang/String;Lcom/amazon/mrn/v1/MrnAuthority;Lcom/amazon/mrn/v1/MrnMediaType;Lcom/amazon/mrn/v1/MrnIdentifierType;Ljava/lang/String;Ljava/util/Map;)V", "AmazonMusicMediaResourceNameKotlin"}, k = 1, mv = {1, 7, 1})
public final /* data */ class MediaResourceNameV1 implements MediaResourceName {
    private final Map<String, String> attributes;
    private final MrnAuthority authority;
    private final String identifier;
    private final MrnIdentifierType mediaIdentifierType;
    private final MrnMediaType mediaType;
    private final String version;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaResourceNameV1)) {
            return false;
        }
        MediaResourceNameV1 mediaResourceNameV1 = (MediaResourceNameV1) other;
        return Intrinsics.areEqual(getVersion(), mediaResourceNameV1.getVersion()) && this.authority == mediaResourceNameV1.authority && this.mediaType == mediaResourceNameV1.mediaType && this.mediaIdentifierType == mediaResourceNameV1.mediaIdentifierType && Intrinsics.areEqual(this.identifier, mediaResourceNameV1.identifier) && Intrinsics.areEqual(this.attributes, mediaResourceNameV1.attributes);
    }

    public int hashCode() {
        int iHashCode = ((((((((getVersion().hashCode() * 31) + this.authority.hashCode()) * 31) + this.mediaType.hashCode()) * 31) + this.mediaIdentifierType.hashCode()) * 31) + this.identifier.hashCode()) * 31;
        Map<String, String> map = this.attributes;
        return iHashCode + (map == null ? 0 : map.hashCode());
    }

    public MediaResourceNameV1(String version, MrnAuthority authority, MrnMediaType mediaType, MrnIdentifierType mediaIdentifierType, String identifier, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(authority, "authority");
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        Intrinsics.checkNotNullParameter(mediaIdentifierType, "mediaIdentifierType");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        this.version = version;
        this.authority = authority;
        this.mediaType = mediaType;
        this.mediaIdentifierType = mediaIdentifierType;
        this.identifier = identifier;
        this.attributes = map;
    }

    public /* synthetic */ MediaResourceNameV1(String str, MrnAuthority mrnAuthority, MrnMediaType mrnMediaType, MrnIdentifierType mrnIdentifierType, String str2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? MrnVersion.V1.getValue() : str, mrnAuthority, mrnMediaType, mrnIdentifierType, str2, (i & 32) != 0 ? null : map);
    }

    public String getVersion() {
        return this.version;
    }

    public final MrnAuthority getAuthority() {
        return this.authority;
    }

    public final MrnMediaType getMediaType() {
        return this.mediaType;
    }

    public final MrnIdentifierType getMediaIdentifierType() {
        return this.mediaIdentifierType;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final Map<String, String> getAttributes() {
        return this.attributes;
    }

    public String toString() {
        return MediaResourceNameProvider.INSTANCE.toMrnString(this);
    }
}
