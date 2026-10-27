package com.amazon.mp3.library.refinement.query.arg;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SingleArg.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/library/refinement/query/arg/SingleArg;", "Lcom/amazon/mp3/library/refinement/query/arg/Arg;", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "Lcom/amazon/mp3/library/refinement/query/arg/SingleArgType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/mp3/library/refinement/query/arg/SingleArgType;", "getType", "()Lcom/amazon/mp3/library/refinement/query/arg/SingleArgType;", "", "getSelectionArgs", "()Ljava/util/List;", "selectionArgs", "getSelectionString", "selectionString", "<init>", "(Ljava/lang/String;Lcom/amazon/mp3/library/refinement/query/arg/SingleArgType;)V", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class SingleArg implements Arg {
    private final SingleArgType type;
    private final String value;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SingleArg)) {
            return false;
        }
        SingleArg singleArg = (SingleArg) other;
        return Intrinsics.areEqual(this.value, singleArg.value) && this.type == singleArg.type;
    }

    public int hashCode() {
        return (this.value.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "SingleArg(value=" + this.value + ", type=" + this.type + ')';
    }

    @JvmOverloads
    public SingleArg(String value, SingleArgType type) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(type, "type");
        this.value = value;
        this.type = type;
    }

    public /* synthetic */ SingleArg(String str, SingleArgType singleArgType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? SingleArgType.EQUALS : singleArgType);
    }

    @Override // com.amazon.mp3.library.refinement.query.arg.Arg
    public List<String> getSelectionArgs() {
        return CollectionsKt__CollectionsJVMKt.listOf(this.value);
    }

    @Override // com.amazon.mp3.library.refinement.query.arg.Arg
    public String getSelectionString() {
        return Intrinsics.stringPlus(this.type.getValue(), " ?");
    }
}
