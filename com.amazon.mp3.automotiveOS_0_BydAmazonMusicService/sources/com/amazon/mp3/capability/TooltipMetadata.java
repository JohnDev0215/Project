package com.amazon.mp3.capability;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TooltipConfig.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR:\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000f0\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\n\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\"\u0010\u0019\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000eR\"\u0010\u001c\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\"\u0010\u001f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\n\u001a\u0004\b \u0010\f\"\u0004\b!\u0010\u000eR\"\u0010\"\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006("}, d2 = {"Lcom/amazon/mp3/capability/TooltipMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "tagIdentifier", "Ljava/lang/String;", "getTagIdentifier", "()Ljava/lang/String;", "setTagIdentifier", "(Ljava/lang/String;)V", "", "metadata", "Ljava/util/Map;", "getMetadata", "()Ljava/util/Map;", "setMetadata", "(Ljava/util/Map;)V", "title", "getTitle", "setTitle", "text", "getText", "setText", "buttonText", "getButtonText", "setButtonText", "cacheStateKey", "getCacheStateKey", "setCacheStateKey", "viewIndex", "I", "getViewIndex", "()I", "setViewIndex", "(I)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TooltipMetadata {
    private String buttonText;
    private String cacheStateKey;
    private Map<String, ? extends Map<String, String>> metadata;
    private String tagIdentifier;
    private String text;
    private String title;
    private int viewIndex;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TooltipMetadata)) {
            return false;
        }
        TooltipMetadata tooltipMetadata = (TooltipMetadata) other;
        return Intrinsics.areEqual(this.tagIdentifier, tooltipMetadata.tagIdentifier) && Intrinsics.areEqual(this.metadata, tooltipMetadata.metadata) && Intrinsics.areEqual(this.title, tooltipMetadata.title) && Intrinsics.areEqual(this.text, tooltipMetadata.text) && Intrinsics.areEqual(this.buttonText, tooltipMetadata.buttonText) && Intrinsics.areEqual(this.cacheStateKey, tooltipMetadata.cacheStateKey) && this.viewIndex == tooltipMetadata.viewIndex;
    }

    public int hashCode() {
        return (((((((((((this.tagIdentifier.hashCode() * 31) + this.metadata.hashCode()) * 31) + this.title.hashCode()) * 31) + this.text.hashCode()) * 31) + this.buttonText.hashCode()) * 31) + this.cacheStateKey.hashCode()) * 31) + Integer.hashCode(this.viewIndex);
    }

    public String toString() {
        return "TooltipMetadata(tagIdentifier=" + this.tagIdentifier + ", metadata=" + this.metadata + ", title=" + this.title + ", text=" + this.text + ", buttonText=" + this.buttonText + ", cacheStateKey=" + this.cacheStateKey + ", viewIndex=" + this.viewIndex + ')';
    }

    public final String getTagIdentifier() {
        return this.tagIdentifier;
    }

    public final Map<String, Map<String, String>> getMetadata() {
        return this.metadata;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public final String getButtonText() {
        return this.buttonText;
    }

    public final void setButtonText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.buttonText = str;
    }

    public final String getCacheStateKey() {
        return this.cacheStateKey;
    }

    public final int getViewIndex() {
        return this.viewIndex;
    }

    public final void setViewIndex(int i) {
        this.viewIndex = i;
    }
}
