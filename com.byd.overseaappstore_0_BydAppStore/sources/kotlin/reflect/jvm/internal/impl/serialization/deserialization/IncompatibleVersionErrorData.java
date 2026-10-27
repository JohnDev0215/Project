package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.e;
import spi.xi;

/* JADX INFO: loaded from: classes2.dex */
public final class IncompatibleVersionErrorData<T> {
    private final T actualVersion;

    @NotNull
    private final ClassId classId;
    private final T expectedVersion;

    @NotNull
    private final String filePath;

    public IncompatibleVersionErrorData(T t, T t2, @NotNull String filePath, @NotNull ClassId classId) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(classId, "classId");
        this.actualVersion = t;
        this.expectedVersion = t2;
        this.filePath = filePath;
        this.classId = classId;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IncompatibleVersionErrorData)) {
            return false;
        }
        IncompatibleVersionErrorData incompatibleVersionErrorData = (IncompatibleVersionErrorData) obj;
        return Intrinsics.areEqual(this.actualVersion, incompatibleVersionErrorData.actualVersion) && Intrinsics.areEqual(this.expectedVersion, incompatibleVersionErrorData.expectedVersion) && Intrinsics.areEqual(this.filePath, incompatibleVersionErrorData.filePath) && Intrinsics.areEqual(this.classId, incompatibleVersionErrorData.classId);
    }

    public int hashCode() {
        T t = this.actualVersion;
        int iHashCode = (t == null ? 0 : t.hashCode()) * 31;
        T t2 = this.expectedVersion;
        return this.classId.hashCode() + xi.b(this.filePath, (iHashCode + (t2 != null ? t2.hashCode() : 0)) * 31, 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sbK = e.k("IncompatibleVersionErrorData(actualVersion=");
        sbK.append(this.actualVersion);
        sbK.append(", expectedVersion=");
        sbK.append(this.expectedVersion);
        sbK.append(", filePath=");
        sbK.append(this.filePath);
        sbK.append(", classId=");
        sbK.append(this.classId);
        sbK.append(')');
        return sbK.toString();
    }
}
