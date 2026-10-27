package com.amazon.avod.ads.api;

import com.amazon.avod.ads.parser.vast.VastOffset;
import com.amazon.avod.ads.parser.vast.VastOffsetType;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Offset {
    private final Duration mDuration;
    private final float mPercent;
    private final int mPosition;
    private final VastOffsetType mType;

    public Offset(VastOffset vastOffset) {
        this(vastOffset.getOffsetType(), vastOffset.getTimeSpan() != null ? new Duration(vastOffset.getTimeSpan()) : null, vastOffset.getPercentage(), vastOffset.getPosition());
    }

    public Offset(float f) {
        this(VastOffsetType.PERCENT, null, f, 0);
    }

    public Offset(VastOffsetType vastOffsetType) {
        this(vastOffsetType, null, 0.0f, 0);
    }

    private Offset(VastOffsetType vastOffsetType, Duration duration, float f, int i) {
        this.mType = vastOffsetType;
        this.mDuration = duration;
        this.mPercent = f;
        this.mPosition = i;
    }

    public VastOffsetType getType() {
        return this.mType;
    }

    public int getPosition() {
        return this.mPosition;
    }

    /* JADX INFO: renamed from: com.amazon.avod.ads.api.Offset$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$ads$parser$vast$VastOffsetType;

        static {
            int[] iArr = new int[VastOffsetType.values().length];
            $SwitchMap$com$amazon$avod$ads$parser$vast$VastOffsetType = iArr;
            try {
                iArr[VastOffsetType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$parser$vast$VastOffsetType[VastOffsetType.TIME_SPAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$parser$vast$VastOffsetType[VastOffsetType.PERCENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$ads$parser$vast$VastOffsetType[VastOffsetType.END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Nullable
    public Duration getDuration(long j) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$ads$parser$vast$VastOffsetType[getType().ordinal()];
        if (i == 1) {
            return new Duration(0L);
        }
        if (i == 2) {
            return getDuration();
        }
        if (i == 3) {
            return new Duration((long) (j * getPercent()));
        }
        if (i == 4) {
            return new Duration(j);
        }
        throw new IllegalStateException("getDuration() cannot be done for Offset types other than START, TIME_SPAN, PERCENT, and END.");
    }

    @Nullable
    public Duration getDuration() {
        return this.mDuration;
    }

    private double getPercent() {
        return this.mPercent;
    }
}
