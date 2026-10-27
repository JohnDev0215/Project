package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageSize.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u001b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/amazon/ui/foundations/styles/ImageSize;", "", "", "toString", "", "hashCode", "other", "", "equals", "size", "I", "getSize", "()I", "cornerRadius", "Ljava/lang/Integer;", "getCornerRadius", "()Ljava/lang/Integer;", "<init>", "(ILjava/lang/Integer;)V", "Companion", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ImageSize {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer cornerRadius;
    private final int size;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageSize)) {
            return false;
        }
        ImageSize imageSize = (ImageSize) other;
        return this.size == imageSize.size && Intrinsics.areEqual(this.cornerRadius, imageSize.cornerRadius);
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.size) * 31;
        Integer num = this.cornerRadius;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "ImageSize(size=" + this.size + ", cornerRadius=" + this.cornerRadius + ')';
    }

    public ImageSize(int i, Integer num) {
        this.size = i;
        this.cornerRadius = num;
    }

    public final int getSize() {
        return this.size;
    }

    public /* synthetic */ ImageSize(int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? 0 : num);
    }

    public final Integer getCornerRadius() {
        return this.cornerRadius;
    }

    /* JADX INFO: compiled from: ImageSize.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/ui/foundations/styles/ImageSize$Companion;", "", "()V", "getRoundImageSize", "Lcom/amazon/ui/foundations/styles/ImageSize;", "size", "", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImageSize getRoundImageSize(int size) {
            return new ImageSize(size, Integer.valueOf(size / 2));
        }
    }
}
