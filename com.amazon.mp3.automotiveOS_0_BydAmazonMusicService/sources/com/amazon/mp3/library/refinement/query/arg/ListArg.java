package com.amazon.mp3.library.refinement.query.arg;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: ListArg.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B!\b\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000eR\u0014\u0010\u0018\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/library/refinement/query/arg/ListArg;", "Lcom/amazon/mp3/library/refinement/query/arg/Arg;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "value", "Ljava/util/List;", "getValue", "()Ljava/util/List;", "Lcom/amazon/mp3/library/refinement/query/arg/ListArgType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/mp3/library/refinement/query/arg/ListArgType;", "getType", "()Lcom/amazon/mp3/library/refinement/query/arg/ListArgType;", "getSelectionArgs", "selectionArgs", "getSelectionString", "()Ljava/lang/String;", "selectionString", "<init>", "(Ljava/util/List;Lcom/amazon/mp3/library/refinement/query/arg/ListArgType;)V", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ListArg implements Arg {
    private final ListArgType type;
    private final List<String> value;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ListArg)) {
            return false;
        }
        ListArg listArg = (ListArg) other;
        return Intrinsics.areEqual(this.value, listArg.value) && this.type == listArg.type;
    }

    public int hashCode() {
        return (this.value.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "ListArg(value=" + this.value + ", type=" + this.type + ')';
    }

    @JvmOverloads
    public ListArg(List<String> value, ListArgType type) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(type, "type");
        this.value = value;
        this.type = type;
    }

    public /* synthetic */ ListArg(List list, ListArgType listArgType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? ListArgType.IN : listArgType);
    }

    @Override // com.amazon.mp3.library.refinement.query.arg.Arg
    public List<String> getSelectionArgs() {
        return this.value;
    }

    @Override // com.amazon.mp3.library.refinement.query.arg.Arg
    public String getSelectionString() {
        return this.type.getValue() + " (" + CollectionsKt___CollectionsKt.joinToString$default(new IntRange(0, this.value.size()), ",", null, null, 0, null, new Function1<Integer, CharSequence>() { // from class: com.amazon.mp3.library.refinement.query.arg.ListArg$selectionString$1
            public final CharSequence invoke(int i) {
                return "?";
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Integer num) {
                return invoke(num.intValue());
            }
        }, 30, null) + ')';
    }
}
