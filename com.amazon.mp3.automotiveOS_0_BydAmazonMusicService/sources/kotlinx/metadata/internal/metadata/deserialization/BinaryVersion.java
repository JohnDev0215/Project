package kotlinx.metadata.internal.metadata.deserialization;

import androidx.core.os.EnvironmentCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BinaryVersion.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\b\b&\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0013\u0012\n\u0010\r\u001a\u00020\u0002\"\u00020\u000b¢\u0006\u0004\b\u001c\u0010\u001dJ\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0000H\u0004J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0013\u0010\n\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001a\u00020\u000bH\u0016R\u0014\u0010\r\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0015\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/BinaryVersion;", "", "", "toArray", "ourVersion", "", "isCompatibleTo", "", "toString", "other", "equals", "", "hashCode", "numbers", "[I", "major", "I", "getMajor", "()I", "minor", "getMinor", "patch", "getPatch", "", "rest", "Ljava/util/List;", "getRest", "()Ljava/util/List;", "<init>", "([I)V", "Companion", "metadata"}, k = 1, mv = {1, 7, 1})
public abstract class BinaryVersion {
    private final int major;
    private final int minor;
    private final int[] numbers;
    private final int patch;
    private final List<Integer> rest;

    public BinaryVersion(int... numbers) {
        List<Integer> listEmptyList;
        Intrinsics.checkNotNullParameter(numbers, "numbers");
        this.numbers = numbers;
        Integer orNull = ArraysKt___ArraysKt.getOrNull(numbers, 0);
        this.major = orNull != null ? orNull.intValue() : -1;
        Integer orNull2 = ArraysKt___ArraysKt.getOrNull(numbers, 1);
        this.minor = orNull2 != null ? orNull2.intValue() : -1;
        Integer orNull3 = ArraysKt___ArraysKt.getOrNull(numbers, 2);
        this.patch = orNull3 != null ? orNull3.intValue() : -1;
        if (numbers.length > 3) {
            if (numbers.length > 1024) {
                throw new IllegalArgumentException("BinaryVersion with length more than 1024 are not supported. Provided length " + numbers.length + '.');
            }
            listEmptyList = CollectionsKt___CollectionsKt.toList(ArraysKt___ArraysJvmKt.asList(numbers).subList(3, numbers.length));
        } else {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        this.rest = listEmptyList;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    /* JADX INFO: renamed from: toArray, reason: from getter */
    public final int[] getNumbers() {
        return this.numbers;
    }

    protected final boolean isCompatibleTo(BinaryVersion ourVersion) {
        Intrinsics.checkNotNullParameter(ourVersion, "ourVersion");
        int i = this.major;
        if (i == 0) {
            if (ourVersion.major == 0 && this.minor == ourVersion.minor) {
                return true;
            }
        } else if (i == ourVersion.major && this.minor <= ourVersion.minor) {
            return true;
        }
        return false;
    }

    public String toString() {
        int[] numbers = getNumbers();
        ArrayList arrayList = new ArrayList();
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            int i2 = numbers[i];
            if (!(i2 != -1)) {
                break;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList.isEmpty() ? EnvironmentCompat.MEDIA_UNKNOWN : CollectionsKt___CollectionsKt.joinToString$default(arrayList, ".", null, null, 0, null, null, 62, null);
    }

    public boolean equals(Object other) {
        if (other != null && Intrinsics.areEqual(getClass(), other.getClass())) {
            BinaryVersion binaryVersion = (BinaryVersion) other;
            if (this.major == binaryVersion.major && this.minor == binaryVersion.minor && this.patch == binaryVersion.patch && Intrinsics.areEqual(this.rest, binaryVersion.rest)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.major;
        int i2 = i + (i * 31) + this.minor;
        int i3 = i2 + (i2 * 31) + this.patch;
        return i3 + (i3 * 31) + this.rest.hashCode();
    }
}
