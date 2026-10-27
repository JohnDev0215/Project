package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.amazon.identity.auth.accounts.MultipleAccountsCommunication;
import com.squareup.javapoet.TypeName;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KspTypeMapper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspTypeMapper;", "", "()V", "javaPrimitiveQNames", "", "", "kotlinTypeToJavaPrimitiveMapping", "", "Lcom/squareup/javapoet/TypeName;", "kotlin.jvm.PlatformType", MultipleAccountsCommunication.DoesAccountHaveMappingAction.KEY_ACCOUNT_MAPPING, "", "getPrimitiveJavaTypeName", "kotlinType", "isJavaPrimitiveType", "", "qName", "swapWithKotlinType", "javaType", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class KspTypeMapper {
    private static final Set<String> javaPrimitiveQNames;
    private static final Map<String, TypeName> kotlinTypeToJavaPrimitiveMapping;
    public static final KspTypeMapper INSTANCE = new KspTypeMapper();
    private static final Map<String, String> mapping = new LinkedHashMap();

    private KspTypeMapper() {
    }

    static {
        Map<String, TypeName> mapMapOf = MapsKt__MapsKt.mapOf(TuplesKt.to("kotlin.Byte", TypeName.BYTE), TuplesKt.to("kotlin.Short", TypeName.SHORT), TuplesKt.to("kotlin.Int", TypeName.INT), TuplesKt.to("kotlin.Long", TypeName.LONG), TuplesKt.to("kotlin.Char", TypeName.CHAR), TuplesKt.to("kotlin.Float", TypeName.FLOAT), TuplesKt.to("kotlin.Double", TypeName.DOUBLE), TuplesKt.to("kotlin.Boolean", TypeName.BOOLEAN));
        kotlinTypeToJavaPrimitiveMapping = mapMapOf;
        Collection<TypeName> collectionValues = mapMapOf.values();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((TypeName) it.next()).toString());
        }
        javaPrimitiveQNames = linkedHashSet;
        for (Map.Entry<String, TypeName> entry : kotlinTypeToJavaPrimitiveMapping.entrySet()) {
            Map<String, String> map = mapping;
            String string = entry.getValue().toString();
            Intrinsics.checkNotNullExpressionValue(string, "it.value.toString()");
            map.put(string, entry.getKey());
        }
        Map<String, String> map2 = mapping;
        map2.put("java.lang.Object", "kotlin.Any");
        map2.put("java.lang.Cloneable", "kotlin.Cloneable");
        map2.put("java.lang.Comparable", "kotlin.Comparable");
        map2.put("java.lang.Enum", "kotlin.Enum");
        map2.put("java.lang.Annotation", "kotlin.Annotation");
        map2.put("java.lang.CharSequence", "kotlin.CharSequence");
        map2.put("java.lang.String", "kotlin.String");
        map2.put("java.lang.Number", "kotlin.Number");
        map2.put("java.lang.Throwable", "kotlin.Throwable");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Double", "kotlin.Double");
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.util.Iterator", "kotlin.collections.MutableIterator");
        map2.put("java.lang.Iterable", "kotlin.collections.Iterable");
        map2.put("java.util.Collection", "kotlin.collections.MutableCollection");
        map2.put("java.util.Set", "kotlin.collections.MutableSet");
        map2.put("java.util.List", "kotlin.collections.MutableList");
        map2.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        map2.put("java.util.Map", "kotlin.collections.MutableMap");
        map2.put("java.util.Map.Entry", "kotlin.collections.MutableEntry");
    }

    public final String swapWithKotlinType(String javaType) {
        Intrinsics.checkNotNullParameter(javaType, "javaType");
        String str = mapping.get(javaType);
        return str == null ? javaType : str;
    }

    public final TypeName getPrimitiveJavaTypeName(String kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
        return kotlinTypeToJavaPrimitiveMapping.get(kotlinType);
    }

    public final boolean isJavaPrimitiveType(String qName) {
        Intrinsics.checkNotNullParameter(qName, "qName");
        return javaPrimitiveQNames.contains(qName);
    }
}
