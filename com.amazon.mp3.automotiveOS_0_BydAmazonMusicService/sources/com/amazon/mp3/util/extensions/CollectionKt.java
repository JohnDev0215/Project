package com.amazon.mp3.util.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: Collection.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a%\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\t\u001a \u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00032\u0006\u0010\b\u001a\u00020\u0006¨\u0006\n"}, d2 = {"deepEquals", "", "T", "", "other", "toString", "", "", "separator", "([Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class CollectionKt {
    public static final <T> String toString(T[] tArr, String separator) {
        String strJoinToString$default;
        Intrinsics.checkNotNullParameter(separator, "separator");
        return (tArr == null || (strJoinToString$default = ArraysKt___ArraysKt.joinToString$default(tArr, separator, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)) == null) ? "" : strJoinToString$default;
    }

    public static final <T> String toString(List<? extends T> list, String separator) {
        String strJoinToString$default;
        Intrinsics.checkNotNullParameter(separator, "separator");
        return (list == null || (strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(list, separator, null, null, 0, null, null, 62, null)) == null) ? "" : strJoinToString$default;
    }

    public static final <T> boolean deepEquals(List<? extends T> list, List<? extends T> other) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (list.size() != other.size()) {
            return false;
        }
        return list == other || !SequencesKt___SequencesKt.contains(SequencesKt___SequencesKt.map(SequencesKt___SequencesKt.zip(CollectionsKt___CollectionsKt.asSequence(list), CollectionsKt___CollectionsKt.asSequence(other)), new Function1<Pair<? extends T, ? extends T>, Boolean>() { // from class: com.amazon.mp3.util.extensions.CollectionKt.deepEquals.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Pair<? extends T, ? extends T> dstr$a$b) {
                Intrinsics.checkNotNullParameter(dstr$a$b, "$dstr$a$b");
                return Boolean.valueOf(Intrinsics.areEqual(dstr$a$b.component1(), dstr$a$b.component2()));
            }
        }), Boolean.FALSE);
    }
}
