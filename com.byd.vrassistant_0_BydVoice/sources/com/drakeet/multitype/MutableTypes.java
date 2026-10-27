package com.drakeet.multitype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MutableTypes.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0012\b\u0002\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\u000e\u001a\u00020\u00032\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0016J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0006\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u001c\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0006H\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u001e\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/drakeet/multitype/MutableTypes;", "Lcom/drakeet/multitype/Types;", "initialCapacity", "", "types", "", "Lcom/drakeet/multitype/Type;", "(ILjava/util/List;)V", "getInitialCapacity", "()I", "size", "getSize", "getTypes", "()Ljava/util/List;", "firstIndexOf", "clazz", "Ljava/lang/Class;", "getType", "T", "index", "register", "", "type", "unregister", "", "multitype"}, k = 1, mv = {1, 4, 2})
public class MutableTypes implements Types {
    private final int initialCapacity;
    private final List<Type<?>> types;

    public MutableTypes() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public MutableTypes(int i, List<Type<?>> types) {
        Intrinsics.checkNotNullParameter(types, "types");
        this.initialCapacity = i;
        this.types = types;
    }

    public int getInitialCapacity() {
        return this.initialCapacity;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MutableTypes(int i, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        i = (i2 & 1) != 0 ? 0 : i;
        this(i, (i2 & 2) != 0 ? new ArrayList(i) : arrayList);
    }

    public List<Type<?>> getTypes() {
        return this.types;
    }

    @Override // com.drakeet.multitype.Types
    public int getSize() {
        return getTypes().size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.drakeet.multitype.Types
    public <T> void register(Type<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        getTypes().add(type);
    }

    @Override // com.drakeet.multitype.Types
    public boolean unregister(final Class<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return CollectionsKt.removeAll((List) getTypes(), (Function1) new Function1<Type<?>, Boolean>() { // from class: com.drakeet.multitype.MutableTypes.unregister.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Type<?> type) {
                return Boolean.valueOf(invoke2(type));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(Type<?> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(it.getClazz(), clazz);
            }
        });
    }

    @Override // com.drakeet.multitype.Types
    public <T> Type<T> getType(int index) {
        Object obj = getTypes().get(index);
        if (obj != null) {
            return (Type) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.drakeet.multitype.Type<T>");
    }

    @Override // com.drakeet.multitype.Types
    public int firstIndexOf(Class<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Iterator<Type<?>> it = getTypes().iterator();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            if (Intrinsics.areEqual(it.next().getClazz(), clazz)) {
                break;
            }
            i2++;
        }
        if (i2 != -1) {
            return i2;
        }
        Iterator<Type<?>> it2 = getTypes().iterator();
        while (it2.hasNext()) {
            if (it2.next().getClazz().isAssignableFrom(clazz)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
