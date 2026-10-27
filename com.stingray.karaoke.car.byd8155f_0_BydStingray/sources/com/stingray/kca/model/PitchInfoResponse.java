package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/kca/model/PitchInfoResponse;", "", "Lcom/stingray/kca/model/SignedFile;", "midi", "<init>", "(Lcom/stingray/kca/model/SignedFile;)V", "copy", "(Lcom/stingray/kca/model/SignedFile;)Lcom/stingray/kca/model/PitchInfoResponse;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class PitchInfoResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SignedFile f13188a;

    /* JADX WARN: Multi-variable type inference failed */
    public PitchInfoResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final PitchInfoResponse copy(@o(name = "midi") SignedFile midi) {
        return new PitchInfoResponse(midi);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PitchInfoResponse) && m.a(this.f13188a, ((PitchInfoResponse) obj).f13188a);
    }

    public final int hashCode() {
        SignedFile signedFile = this.f13188a;
        if (signedFile == null) {
            return 0;
        }
        return signedFile.hashCode();
    }

    public final String toString() {
        return "PitchInfoResponse(midi=" + this.f13188a + ")";
    }

    public PitchInfoResponse(@o(name = "midi") SignedFile signedFile) {
        this.f13188a = signedFile;
    }

    public /* synthetic */ PitchInfoResponse(SignedFile signedFile, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : signedFile);
    }
}
