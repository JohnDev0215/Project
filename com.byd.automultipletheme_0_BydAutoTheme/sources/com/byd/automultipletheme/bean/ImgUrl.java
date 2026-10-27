package com.byd.automultipletheme.bean;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImgUrl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/byd/automultipletheme/bean/ImgUrl;", "Ljava/io/Serializable;", "dynasty", "Lcom/byd/automultipletheme/bean/PreviewImage;", "ocean", "denza", "(Lcom/byd/automultipletheme/bean/PreviewImage;Lcom/byd/automultipletheme/bean/PreviewImage;Lcom/byd/automultipletheme/bean/PreviewImage;)V", "getDenza", "()Lcom/byd/automultipletheme/bean/PreviewImage;", "getDynasty", "getOcean", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class ImgUrl implements Serializable {
    private final PreviewImage denza;
    private final PreviewImage dynasty;
    private final PreviewImage ocean;

    public static /* synthetic */ ImgUrl copy$default(ImgUrl imgUrl, PreviewImage previewImage, PreviewImage previewImage2, PreviewImage previewImage3, int i, Object obj) {
        if ((i & 1) != 0) {
            previewImage = imgUrl.dynasty;
        }
        if ((i & 2) != 0) {
            previewImage2 = imgUrl.ocean;
        }
        if ((i & 4) != 0) {
            previewImage3 = imgUrl.denza;
        }
        return imgUrl.copy(previewImage, previewImage2, previewImage3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PreviewImage getDynasty() {
        return this.dynasty;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PreviewImage getOcean() {
        return this.ocean;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final PreviewImage getDenza() {
        return this.denza;
    }

    public final ImgUrl copy(PreviewImage dynasty, PreviewImage ocean, PreviewImage denza) {
        Intrinsics.checkNotNullParameter(dynasty, "dynasty");
        Intrinsics.checkNotNullParameter(ocean, "ocean");
        Intrinsics.checkNotNullParameter(denza, "denza");
        return new ImgUrl(dynasty, ocean, denza);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImgUrl)) {
            return false;
        }
        ImgUrl imgUrl = (ImgUrl) other;
        return Intrinsics.areEqual(this.dynasty, imgUrl.dynasty) && Intrinsics.areEqual(this.ocean, imgUrl.ocean) && Intrinsics.areEqual(this.denza, imgUrl.denza);
    }

    public int hashCode() {
        return (((this.dynasty.hashCode() * 31) + this.ocean.hashCode()) * 31) + this.denza.hashCode();
    }

    public String toString() {
        return "ImgUrl(dynasty=" + this.dynasty + ", ocean=" + this.ocean + ", denza=" + this.denza + ')';
    }

    public ImgUrl(PreviewImage dynasty, PreviewImage ocean, PreviewImage denza) {
        Intrinsics.checkNotNullParameter(dynasty, "dynasty");
        Intrinsics.checkNotNullParameter(ocean, "ocean");
        Intrinsics.checkNotNullParameter(denza, "denza");
        this.dynasty = dynasty;
        this.ocean = ocean;
        this.denza = denza;
    }

    public final PreviewImage getDynasty() {
        return this.dynasty;
    }

    public final PreviewImage getOcean() {
        return this.ocean;
    }

    public final PreviewImage getDenza() {
        return this.denza;
    }
}
