package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageManifest.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/digitalmusicxp/types/ImageManifest;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/digitalmusicxp/types/ImageManifestTuple;", "urls", "Ljava/util/List;", "getUrls", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ImageManifest {
    private final List<ImageManifestTuple> urls;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ImageManifest) && Intrinsics.areEqual(this.urls, ((ImageManifest) other).urls);
    }

    public int hashCode() {
        return this.urls.hashCode();
    }

    public String toString() {
        return "ImageManifest(urls=" + this.urls + ')';
    }

    public ImageManifest(List<ImageManifestTuple> urls) {
        Intrinsics.checkNotNullParameter(urls, "urls");
        this.urls = urls;
        ExpectedFunctionsKt.freeze(this);
    }

    public final List<ImageManifestTuple> getUrls() {
        return this.urls;
    }
}
