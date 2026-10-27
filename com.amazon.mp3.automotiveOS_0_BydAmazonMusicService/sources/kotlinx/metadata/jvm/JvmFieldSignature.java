package kotlinx.metadata.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JvmMemberSignature.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lkotlinx/metadata/jvm/JvmFieldSignature;", "Lkotlinx/metadata/jvm/JvmMemberSignature;", "", "asString", "", "hashCode", "", "other", "", "equals", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "desc", "getDesc", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public final /* data */ class JvmFieldSignature extends JvmMemberSignature {
    private final String desc;
    private final String name;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JvmFieldSignature)) {
            return false;
        }
        JvmFieldSignature jvmFieldSignature = (JvmFieldSignature) other;
        return Intrinsics.areEqual(getName(), jvmFieldSignature.getName()) && Intrinsics.areEqual(getDesc(), jvmFieldSignature.getDesc());
    }

    public int hashCode() {
        return (getName().hashCode() * 31) + getDesc().hashCode();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmFieldSignature(String name, String desc) {
        super(null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desc, "desc");
        this.name = name;
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getName() {
        return this.name;
    }

    @Override // kotlinx.metadata.jvm.JvmMemberSignature
    public String asString() {
        return getName() + ':' + getDesc();
    }
}
