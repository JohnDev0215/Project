package com.neusoft.na.common;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;

/* JADX INFO: compiled from: ApplicationStatus.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001e\u001fB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000bJ$\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\bH\u0016J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\bHÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\bH\u0016R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/neusoft/na/common/ApplicationStatus;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "mStatus", "Lcom/neusoft/na/common/ApplicationStatus$Status;", "mProgress", "", "(Lcom/neusoft/na/common/ApplicationStatus$Status;Ljava/lang/Integer;)V", "getMProgress", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMStatus", "()Lcom/neusoft/na/common/ApplicationStatus$Status;", "component1", "component2", "copy", "(Lcom/neusoft/na/common/ApplicationStatus$Status;Ljava/lang/Integer;)Lcom/neusoft/na/common/ApplicationStatus;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "flags", "CREATOR", "Status", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final /* data */ class ApplicationStatus implements Parcelable {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer mProgress;
    private final Status mStatus;

    /* JADX INFO: compiled from: ApplicationStatus.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/neusoft/na/common/ApplicationStatus$Status;", "", "(Ljava/lang/String;I)V", "UNINITIALIZED", "INITIALIZING", "INITIALIZED", "STARTING", DebugCoroutineInfoImplKt.RUNNING, ResultCodeEnum.ERROR, "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum Status {
        UNINITIALIZED,
        INITIALIZING,
        INITIALIZED,
        STARTING,
        RUNNING,
        ERROR
    }

    public static /* synthetic */ ApplicationStatus copy$default(ApplicationStatus applicationStatus, Status status, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            status = applicationStatus.mStatus;
        }
        if ((i & 2) != 0) {
            num = applicationStatus.mProgress;
        }
        return applicationStatus.copy(status, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Status getMStatus() {
        return this.mStatus;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getMProgress() {
        return this.mProgress;
    }

    public final ApplicationStatus copy(Status mStatus, Integer mProgress) {
        Intrinsics.checkNotNullParameter(mStatus, "mStatus");
        return new ApplicationStatus(mStatus, mProgress);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApplicationStatus)) {
            return false;
        }
        ApplicationStatus applicationStatus = (ApplicationStatus) other;
        return this.mStatus == applicationStatus.mStatus && Intrinsics.areEqual(this.mProgress, applicationStatus.mProgress);
    }

    public int hashCode() {
        int iHashCode = this.mStatus.hashCode() * 31;
        Integer num = this.mProgress;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "ApplicationStatus(mStatus=" + this.mStatus + ", mProgress=" + this.mProgress + ')';
    }

    public ApplicationStatus(Status mStatus, Integer num) {
        Intrinsics.checkNotNullParameter(mStatus, "mStatus");
        this.mStatus = mStatus;
        this.mProgress = num;
    }

    public /* synthetic */ ApplicationStatus(Status status, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(status, (i & 2) != 0 ? null : num);
    }

    public final Status getMStatus() {
        return this.mStatus;
    }

    public final Integer getMProgress() {
        return this.mProgress;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ApplicationStatus(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        Status statusValueOf = Status.valueOf(string == null ? "" : string);
        Object value = parcel.readValue(Integer.TYPE.getClassLoader());
        this(statusValueOf, value instanceof Integer ? (Integer) value : null);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.mStatus.name());
        parcel.writeValue(this.mProgress);
    }

    /* JADX INFO: renamed from: com.neusoft.na.common.ApplicationStatus$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ApplicationStatus.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/neusoft/na/common/ApplicationStatus$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/neusoft/na/common/ApplicationStatus;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/neusoft/na/common/ApplicationStatus;", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion implements Parcelable.Creator<ApplicationStatus> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApplicationStatus createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ApplicationStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApplicationStatus[] newArray(int size) {
            return new ApplicationStatus[size];
        }
    }
}
