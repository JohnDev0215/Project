package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IdentityContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "serializedMusicRequestIdentityContext", "Ljava/lang/String;", "getSerializedMusicRequestIdentityContext", "()Ljava/lang/String;", "recognizedSpeakerId", "getRecognizedSpeakerId", "recognizedSpeakerLinkedId", "getRecognizedSpeakerLinkedId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class IdentityContextInput {
    private final String recognizedSpeakerId;
    private final String recognizedSpeakerLinkedId;
    private final String serializedMusicRequestIdentityContext;

    public IdentityContextInput() {
        this(null, null, null, 7, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IdentityContextInput)) {
            return false;
        }
        IdentityContextInput identityContextInput = (IdentityContextInput) other;
        return Intrinsics.areEqual(this.serializedMusicRequestIdentityContext, identityContextInput.serializedMusicRequestIdentityContext) && Intrinsics.areEqual(this.recognizedSpeakerId, identityContextInput.recognizedSpeakerId) && Intrinsics.areEqual(this.recognizedSpeakerLinkedId, identityContextInput.recognizedSpeakerLinkedId);
    }

    public int hashCode() {
        String str = this.serializedMusicRequestIdentityContext;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.recognizedSpeakerId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.recognizedSpeakerLinkedId;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "IdentityContextInput(serializedMusicRequestIdentityContext=" + this.serializedMusicRequestIdentityContext + ", recognizedSpeakerId=" + this.recognizedSpeakerId + ", recognizedSpeakerLinkedId=" + this.recognizedSpeakerLinkedId + ')';
    }

    public IdentityContextInput(String str, String str2, String str3) {
        this.serializedMusicRequestIdentityContext = str;
        this.recognizedSpeakerId = str2;
        this.recognizedSpeakerLinkedId = str3;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ IdentityContextInput(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }
}
