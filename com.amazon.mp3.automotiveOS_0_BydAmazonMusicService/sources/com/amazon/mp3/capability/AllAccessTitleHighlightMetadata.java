package com.amazon.mp3.capability;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AllAccessTitleHighlight.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010 \n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR:\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000f0\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R4\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00170\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0013\"\u0004\b\u001a\u0010\u0015¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/capability/AllAccessTitleHighlightMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "tagIdentifier", "Ljava/lang/String;", "getTagIdentifier", "()Ljava/lang/String;", "setTagIdentifier", "(Ljava/lang/String;)V", "", "metadata", "Ljava/util/Map;", "getMetadata", "()Ljava/util/Map;", "setMetadata", "(Ljava/util/Map;)V", "", "", "highlightMapping", "getHighlightMapping", "setHighlightMapping", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AllAccessTitleHighlightMetadata {
    private Map<String, List<String>> highlightMapping;
    private Map<String, ? extends Map<String, String>> metadata;
    private String tagIdentifier;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AllAccessTitleHighlightMetadata)) {
            return false;
        }
        AllAccessTitleHighlightMetadata allAccessTitleHighlightMetadata = (AllAccessTitleHighlightMetadata) other;
        return Intrinsics.areEqual(this.tagIdentifier, allAccessTitleHighlightMetadata.tagIdentifier) && Intrinsics.areEqual(this.metadata, allAccessTitleHighlightMetadata.metadata) && Intrinsics.areEqual(this.highlightMapping, allAccessTitleHighlightMetadata.highlightMapping);
    }

    public int hashCode() {
        return (((this.tagIdentifier.hashCode() * 31) + this.metadata.hashCode()) * 31) + this.highlightMapping.hashCode();
    }

    public String toString() {
        return "AllAccessTitleHighlightMetadata(tagIdentifier=" + this.tagIdentifier + ", metadata=" + this.metadata + ", highlightMapping=" + this.highlightMapping + ')';
    }

    public final String getTagIdentifier() {
        return this.tagIdentifier;
    }

    public final Map<String, Map<String, String>> getMetadata() {
        return this.metadata;
    }

    public final Map<String, List<String>> getHighlightMapping() {
        return this.highlightMapping;
    }

    public final void setHighlightMapping(Map<String, List<String>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.highlightMapping = map;
    }
}
