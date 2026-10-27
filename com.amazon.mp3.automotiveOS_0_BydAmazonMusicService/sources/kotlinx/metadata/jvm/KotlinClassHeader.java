package kotlinx.metadata.jvm;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.jvm.deserialization.JvmMetadataVersion;

/* JADX INFO: compiled from: KotlinClassHeader.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0013\u0018\u0000 #2\u00020\u0001:\u0001#Be\b\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b \u0010!B[\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b \u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\f\u0010\t\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\u000bR\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u0017\u0010\u0018\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001bR\u0017\u0010\u001e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006¨\u0006$"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassHeader;", "", "", "kind", "I", "getKind", "()I", "", "metadataVersion", "[I", "getMetadataVersion", "()[I", "bytecodeVersion", "getBytecodeVersion", "getBytecodeVersion$annotations", "()V", "", "", "data1", "[Ljava/lang/String;", "getData1", "()[Ljava/lang/String;", "data2", "getData2", "extraString", "Ljava/lang/String;", "getExtraString", "()Ljava/lang/String;", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "getPackageName", "extraInt", "getExtraInt", "<init>", "(Ljava/lang/Integer;[I[I[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "(Ljava/lang/Integer;[I[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "Companion", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public final class KotlinClassHeader {

    @JvmField
    public static final int[] COMPATIBLE_BYTECODE_VERSION;

    @JvmField
    public static final int[] COMPATIBLE_METADATA_VERSION;
    private final int[] bytecodeVersion;
    private final String[] data1;
    private final String[] data2;
    private final int extraInt;
    private final String extraString;
    private final int kind;
    private final int[] metadataVersion;
    private final String packageName;

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use another constructor, which doesn't take bytecodeVersion as a parameter.", replaceWith = @ReplaceWith(expression = "KotlinClassHeader(kind, metadataVersion, data1, data2, extraString, packageName, extraInt)", imports = {}))
    public KotlinClassHeader(Integer num, int[] iArr, int[] iArr2, String[] strArr, String[] strArr2, String str, String str2, Integer num2) {
        this.kind = num != null ? num.intValue() : 1;
        this.metadataVersion = iArr == null ? new int[0] : iArr;
        this.bytecodeVersion = iArr2 == null ? new int[0] : iArr2;
        this.data1 = strArr == null ? new String[0] : strArr;
        this.data2 = strArr2 == null ? new String[0] : strArr2;
        this.extraString = str == null ? "" : str;
        this.packageName = str2 == null ? "" : str2;
        this.extraInt = num2 != null ? num2.intValue() : 0;
    }

    public KotlinClassHeader(Integer num, int[] iArr, String[] strArr, String[] strArr2, String str, String str2, Integer num2) {
        this(num, iArr, null, strArr, strArr2, str, str2, num2);
    }

    public final int getKind() {
        return this.kind;
    }

    public final int[] getMetadataVersion() {
        return this.metadataVersion;
    }

    public final String[] getData1() {
        return this.data1;
    }

    public final String[] getData2() {
        return this.data2;
    }

    public final int getExtraInt() {
        return this.extraInt;
    }

    static {
        int[] numbers = JvmMetadataVersion.INSTANCE.getNumbers();
        int[] iArrCopyOf = Arrays.copyOf(numbers, numbers.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, size)");
        COMPATIBLE_METADATA_VERSION = iArrCopyOf;
        COMPATIBLE_BYTECODE_VERSION = new int[]{1, 0, 3};
    }
}
