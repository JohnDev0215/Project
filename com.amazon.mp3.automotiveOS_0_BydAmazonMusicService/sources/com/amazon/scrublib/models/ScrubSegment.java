package com.amazon.scrublib.models;

import com.amazon.scrublib.utils.AtomicBool;
import com.amazon.scrublib.utils.AtomicInt;
import com.amazon.scrublib.utils.PlatformUtilsKt;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScrubSegment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001Be\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b0\u00101J=\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nR\u001f\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001cR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010#\"\u0004\b$\u0010%R$\u0010\u0003\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u0010\u0004\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010'\"\u0004\b+\u0010)R$\u0010\b\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u00062"}, d2 = {"Lcom/amazon/scrublib/models/ScrubSegment;", "", "", "startTime", "endTime", "", "isProcessed", "Lcom/amazon/scrublib/models/ContentType;", "contentType", "clone", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Lcom/amazon/scrublib/models/ContentType;)Lcom/amazon/scrublib/models/ScrubSegment;", "", "Lcom/amazon/scrublib/models/Signature;", "audioSignature", "Ljava/util/List;", "getAudioSignature", "()Ljava/util/List;", "", "audioType", "Ljava/lang/String;", "getAudioType", "()Ljava/lang/String;", MediaTrack.ROLE_CAPTION, "getCaption", "speaker", "getSpeaker", "Lcom/amazon/scrublib/utils/AtomicInt;", "_startTime", "Lcom/amazon/scrublib/utils/AtomicInt;", "_endTime", "_contentType", "Lcom/amazon/scrublib/utils/AtomicBool;", "_isProcessed", "Lcom/amazon/scrublib/utils/AtomicBool;", "value", "()Z", "setProcessed", "(Z)V", "getStartTime", "()I", "setStartTime", "(I)V", "getEndTime", "setEndTime", "getContentType", "()Lcom/amazon/scrublib/models/ContentType;", "setContentType", "(Lcom/amazon/scrublib/models/ContentType;)V", "<init>", "(IILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/scrublib/models/ContentType;)V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class ScrubSegment {
    private final AtomicInt _contentType;
    private final AtomicInt _endTime;
    private final AtomicBool _isProcessed;
    private final AtomicInt _startTime;
    private final List<Signature> audioSignature;
    private final String audioType;
    private final String caption;
    private final String speaker;

    public ScrubSegment() {
        this(0, 0, null, null, null, null, false, null, 255, null);
    }

    public ScrubSegment(int i, int i2, List<Signature> list, String str, String str2, String str3, boolean z, ContentType contentType) {
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        this.audioSignature = list;
        this.audioType = str;
        this.caption = str2;
        this.speaker = str3;
        this._isProcessed = new AtomicBool(z);
        this._startTime = new AtomicInt(i);
        this._endTime = new AtomicInt(i2);
        this._contentType = new AtomicInt(contentType.getId());
        PlatformUtilsKt.freeze(this);
    }

    public final List<Signature> getAudioSignature() {
        return this.audioSignature;
    }

    public final String getCaption() {
        return this.caption;
    }

    public /* synthetic */ ScrubSegment(int i, int i2, List list, String str, String str2, String str3, boolean z, ContentType contentType, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? null : list, (i3 & 8) != 0 ? null : str, (i3 & 16) != 0 ? null : str2, (i3 & 32) == 0 ? str3 : null, (i3 & 64) == 0 ? z : false, (i3 & 128) != 0 ? ContentType.TRANSCRIBED_CONTENT : contentType);
    }

    public final boolean isProcessed() {
        return this._isProcessed.get();
    }

    public final void setProcessed(boolean z) {
        this._isProcessed.set(z);
    }

    public final int getStartTime() {
        return this._startTime.get();
    }

    public final void setStartTime(int i) {
        this._startTime.set(i);
    }

    public final int getEndTime() {
        return this._endTime.get();
    }

    public final void setEndTime(int i) {
        this._endTime.set(i);
    }

    public final ContentType getContentType() {
        return ContentType.INSTANCE.fromInt(this._contentType.get());
    }

    public final void setContentType(ContentType value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this._contentType.set(value.getId());
    }

    public static /* synthetic */ ScrubSegment clone$default(ScrubSegment scrubSegment, Integer num, Integer num2, Boolean bool, ContentType contentType, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            bool = null;
        }
        if ((i & 8) != 0) {
            contentType = null;
        }
        return scrubSegment.clone(num, num2, bool, contentType);
    }

    public final ScrubSegment clone(Integer startTime, Integer endTime, Boolean isProcessed, ContentType contentType) {
        int iIntValue = startTime != null ? startTime.intValue() : getStartTime();
        int iIntValue2 = endTime != null ? endTime.intValue() : getEndTime();
        List<Signature> list = this.audioSignature;
        String str = this.audioType;
        String str2 = this.caption;
        String str3 = this.speaker;
        boolean zBooleanValue = isProcessed != null ? isProcessed.booleanValue() : isProcessed();
        if (contentType == null) {
            contentType = getContentType();
        }
        return new ScrubSegment(iIntValue, iIntValue2, list, str, str2, str3, zBooleanValue, contentType);
    }
}
