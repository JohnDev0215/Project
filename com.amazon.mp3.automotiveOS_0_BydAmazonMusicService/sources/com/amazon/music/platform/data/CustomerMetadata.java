package com.amazon.music.platform.data;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/platform/data/CustomerMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "firstName", "Ljava/lang/String;", "getFirstName", "()Ljava/lang/String;", "lastName", "getLastName", "directedId", "getDirectedId", Splash.PARAMS_SESSION_ID, "getSessionId", Splash.PARAMS_MUSIC_TERRITORY, "getMusicTerritory", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class CustomerMetadata {
    private final String directedId;
    private final String firstName;
    private final String lastName;
    private final String musicTerritory;
    private final String sessionId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerMetadata)) {
            return false;
        }
        CustomerMetadata customerMetadata = (CustomerMetadata) other;
        return Intrinsics.areEqual(this.firstName, customerMetadata.firstName) && Intrinsics.areEqual(this.lastName, customerMetadata.lastName) && Intrinsics.areEqual(this.directedId, customerMetadata.directedId) && Intrinsics.areEqual(this.sessionId, customerMetadata.sessionId) && Intrinsics.areEqual(this.musicTerritory, customerMetadata.musicTerritory);
    }

    public int hashCode() {
        int iHashCode = ((this.firstName.hashCode() * 31) + this.lastName.hashCode()) * 31;
        String str = this.directedId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.sessionId;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.musicTerritory;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "CustomerMetadata(firstName=" + this.firstName + ", lastName=" + this.lastName + ", directedId=" + this.directedId + ", sessionId=" + this.sessionId + ", musicTerritory=" + this.musicTerritory + ')';
    }

    public CustomerMetadata(String firstName, String lastName, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(firstName, "firstName");
        Intrinsics.checkNotNullParameter(lastName, "lastName");
        this.firstName = firstName;
        this.lastName = lastName;
        this.directedId = str;
        this.sessionId = str2;
        this.musicTerritory = str3;
        PlatformProviderUtilKt.freeze(this);
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getMusicTerritory() {
        return this.musicTerritory;
    }
}
