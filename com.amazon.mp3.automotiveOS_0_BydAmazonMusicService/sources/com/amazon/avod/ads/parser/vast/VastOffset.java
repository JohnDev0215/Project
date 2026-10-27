package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastOffset {
    private float mPercent;
    private int mPosition;
    private VastTimeSpan mTimeSpan;
    private final VastOffsetType mType;

    VastOffset(VastOffsetType vastOffsetType) {
        this.mType = vastOffsetType;
    }

    VastOffset(VastTimeSpan vastTimeSpan) {
        this.mType = VastOffsetType.TIME_SPAN;
        this.mTimeSpan = vastTimeSpan;
    }

    VastOffset(float f) {
        this.mType = VastOffsetType.PERCENT;
        this.mPercent = f;
    }

    VastOffset(int i) {
        this.mType = VastOffsetType.POSITION;
        this.mPosition = i;
    }

    public static VastOffset parseXmlString(String str) {
        if (str.endsWith("%")) {
            return new VastOffset(Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f);
        }
        if (str.equals(AsmString.METHOD_FIREBASE_PERF_TRACE_START)) {
            return new VastOffset(VastOffsetType.START);
        }
        if (str.equals("end")) {
            return new VastOffset(VastOffsetType.END);
        }
        if (str.startsWith("#")) {
            return new VastOffset(Integer.parseInt(str.substring(1)));
        }
        return new VastOffset(VastTimeSpan.parseXmlTime(str));
    }

    @Nonnull
    public static VastOffset createVastOffset(@Nonnull VastTimeSpan vastTimeSpan) {
        Preconditions.checkNotNull(vastTimeSpan, "timeSpan");
        return new VastOffset(vastTimeSpan);
    }

    @Nonnull
    public VastOffsetType getOffsetType() {
        return this.mType;
    }

    @Nullable
    public VastTimeSpan getTimeSpan() {
        return this.mTimeSpan;
    }

    public float getPercentage() {
        return this.mPercent;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public String toString() {
        return this.mType.equals(VastOffsetType.START) ? AsmString.METHOD_FIREBASE_PERF_TRACE_START : this.mType.equals(VastOffsetType.END) ? "end" : this.mType.equals(VastOffsetType.POSITION) ? String.format(Locale.US, "#%d", Integer.valueOf(this.mPosition)) : this.mType.equals(VastOffsetType.PERCENT) ? String.format(Locale.US, "%.3f%%", Float.valueOf(this.mPercent * 100.0f)) : this.mTimeSpan.toString();
    }
}
