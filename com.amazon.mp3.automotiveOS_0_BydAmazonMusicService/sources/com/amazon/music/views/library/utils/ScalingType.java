package com.amazon.music.views.library.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ScalingType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \r2\u00020\u0001:\u0003\r\u000e\u000fB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J \u0010\f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/views/library/utils/ScalingType;", "", "value", "", "(I)V", "getValue", "()I", "getHeight", "factor", "", "width", "height", "getWidth", "Companion", "FixedHeight", "FixedWidth", "Lcom/amazon/music/views/library/utils/ScalingType$FixedWidth;", "Lcom/amazon/music/views/library/utils/ScalingType$FixedHeight;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ScalingType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    public /* synthetic */ ScalingType(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public int getHeight(float factor, int width, int height) {
        return height;
    }

    public int getWidth(float factor, int width, int height) {
        return width;
    }

    private ScalingType(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: ScalingType.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/utils/ScalingType$FixedWidth;", "Lcom/amazon/music/views/library/utils/ScalingType;", "()V", "getHeight", "", "factor", "", "width", "height", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class FixedWidth extends ScalingType {
        public static final FixedWidth INSTANCE = new FixedWidth();

        @Override // com.amazon.music.views.library.utils.ScalingType
        public int getHeight(float factor, int width, int height) {
            return (int) (factor * width);
        }

        private FixedWidth() {
            super(0, null);
        }
    }

    /* JADX INFO: compiled from: ScalingType.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/utils/ScalingType$FixedHeight;", "Lcom/amazon/music/views/library/utils/ScalingType;", "()V", "getWidth", "", "factor", "", "width", "height", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class FixedHeight extends ScalingType {
        public static final FixedHeight INSTANCE = new FixedHeight();

        @Override // com.amazon.music.views.library.utils.ScalingType
        public int getWidth(float factor, int width, int height) {
            return (int) (factor * height);
        }

        private FixedHeight() {
            super(1, null);
        }
    }

    /* JADX INFO: compiled from: ScalingType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/utils/ScalingType$Companion;", "", "()V", "getByValue", "Lcom/amazon/music/views/library/utils/ScalingType;", "value", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScalingType getByValue(int value) {
            if (value == 0) {
                return FixedWidth.INSTANCE;
            }
            if (value == 1) {
                return FixedHeight.INSTANCE;
            }
            throw new IllegalArgumentException("The value provided (" + value + ") doesn't map to a scaling type");
        }
    }
}
