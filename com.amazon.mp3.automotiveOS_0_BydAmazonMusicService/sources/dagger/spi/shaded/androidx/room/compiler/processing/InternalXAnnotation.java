package dagger.spi.shaded.androidx.room.compiler.processing;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: InternalXAnnotation.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bR'\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/InternalXAnnotation;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XAnnotation;", "", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XAnnotationValue;", "valuesByName$delegate", "Lkotlin/Lazy;", "getValuesByName", "()Ljava/util/Map;", "valuesByName", "<init>", "()V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
@PublishedApi
public abstract class InternalXAnnotation implements XAnnotation {

    /* JADX INFO: renamed from: valuesByName$delegate, reason: from kotlin metadata */
    private final Lazy valuesByName = LazyKt__LazyJVMKt.lazy(new Function0<Map<String, ? extends XAnnotationValue>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.InternalXAnnotation$valuesByName$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Map<String, ? extends XAnnotationValue> invoke() {
            List<XAnnotationValue> annotationValues = this.this$0.getAnnotationValues();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(annotationValues, 10)), 16));
            for (Object obj : annotationValues) {
                linkedHashMap.put(((XAnnotationValue) obj).getName(), obj);
            }
            return linkedHashMap;
        }
    });
}
