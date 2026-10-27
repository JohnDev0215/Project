package com.amazon.music.platform.data.mediagateway;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.music.platform.data.mediagateway.RecommendForAssistant$Item, reason: from toString */
/* JADX INFO: compiled from: RecommendForAssistant.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"com/amazon/music/platform/data/mediagateway/RecommendForAssistant$Item", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Item {
    private final String id;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Item) && Intrinsics.areEqual(this.id, ((Item) other).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        return "Item(id=" + this.id + ')';
    }

    public Item(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public final String getId() {
        return this.id;
    }
}
