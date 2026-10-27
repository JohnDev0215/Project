package kotlinx.metadata.internal.metadata.jvm.deserialization;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.ProtoBuf$Class;
import kotlinx.metadata.internal.metadata.ProtoBuf$Constructor;
import kotlinx.metadata.internal.metadata.ProtoBuf$Function;
import kotlinx.metadata.internal.metadata.ProtoBuf$Package;
import kotlinx.metadata.internal.metadata.ProtoBuf$Property;
import kotlinx.metadata.internal.metadata.ProtoBuf$Type;
import kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter;
import kotlinx.metadata.internal.metadata.deserialization.NameResolver;
import kotlinx.metadata.internal.metadata.deserialization.ProtoBufUtilKt;
import kotlinx.metadata.internal.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlinx.metadata.internal.metadata.deserialization.TypeTable;
import kotlinx.metadata.internal.metadata.jvm.JvmProtoBuf;
import kotlinx.metadata.internal.metadata.jvm.deserialization.JvmMemberSignature;
import kotlinx.metadata.internal.protobuf.ExtensionRegistryLite;
import kotlinx.metadata.internal.protobuf.GeneratedMessageLite;

/* JADX INFO: compiled from: JvmProtoBufUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b0\u00101J7\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\t\u0010\nJ1\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\t\u0010\rJ7\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e0\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\u000f\u0010\nJ1\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\u000f\u0010\rJ7\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\u0011\u0010\nJ\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J \u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0018J \u0010\u001d\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0017\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0018J*\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010\u0017\u001a\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020\u001fJ%\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\u0001H\u0007¢\u0006\u0004\b&\u0010'J!\u0010)\u001a\u00020\u0007*\u00020(2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002¢\u0006\u0004\b)\u0010*R\u0017\u0010,\u001a\u00020+8\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/¨\u00062"}, d2 = {"Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmProtoBufUtil;", "", "", "", "data", "strings", "Lkotlin/Pair;", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmNameResolver;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Class;", "readClassDataFrom", "([Ljava/lang/String;[Ljava/lang/String;)Lkotlin/Pair;", "", "bytes", "([B[Ljava/lang/String;)Lkotlin/Pair;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Package;", "readPackageDataFrom", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Function;", "readFunctionDataFrom", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Type;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/metadata/internal/metadata/deserialization/NameResolver;", "nameResolver", "mapTypeDefault", "proto", "Lkotlinx/metadata/internal/metadata/deserialization/TypeTable;", "typeTable", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmMemberSignature$Method;", "getJvmMethodSignature", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Constructor;", "getJvmConstructorSignature", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Property;", "", "requireHasFieldFlag", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmMemberSignature$Field;", "getJvmFieldSignature", "Lkotlinx/metadata/internal/protobuf/MessageLite;", "message", "stringTable", "writeData", "(Lorg/jetbrains/kotlin/protobuf/MessageLite;Lorg/jetbrains/kotlin/metadata/jvm/serialization/JvmStringTable;)[Ljava/lang/String;", "Ljava/io/InputStream;", "readNameResolver", "(Ljava/io/InputStream;[Ljava/lang/String;)Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmNameResolver;", "Lkotlinx/metadata/internal/protobuf/ExtensionRegistryLite;", "EXTENSION_REGISTRY", "Lkotlinx/metadata/internal/protobuf/ExtensionRegistryLite;", "getEXTENSION_REGISTRY", "()Lorg/jetbrains/kotlin/protobuf/ExtensionRegistryLite;", "<init>", "()V", "metadata.jvm"}, k = 1, mv = {1, 7, 1})
public final class JvmProtoBufUtil {
    private static final ExtensionRegistryLite EXTENSION_REGISTRY;
    public static final JvmProtoBufUtil INSTANCE = new JvmProtoBufUtil();

    private JvmProtoBufUtil() {
    }

    static {
        ExtensionRegistryLite extensionRegistryLiteNewInstance = ExtensionRegistryLite.newInstance();
        JvmProtoBuf.registerAllExtensions(extensionRegistryLiteNewInstance);
        Intrinsics.checkNotNullExpressionValue(extensionRegistryLiteNewInstance, "newInstance().apply(JvmP…f::registerAllExtensions)");
        EXTENSION_REGISTRY = extensionRegistryLiteNewInstance;
    }

    @JvmStatic
    public static final Pair<JvmNameResolver, ProtoBuf$Class> readClassDataFrom(String[] data, String[] strings) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        byte[] bArrDecodeBytes = BitEncoding.decodeBytes(data);
        Intrinsics.checkNotNullExpressionValue(bArrDecodeBytes, "decodeBytes(data)");
        return readClassDataFrom(bArrDecodeBytes, strings);
    }

    @JvmStatic
    public static final Pair<JvmNameResolver, ProtoBuf$Class> readClassDataFrom(byte[] bytes, String[] strings) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strings), ProtoBuf$Class.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    @JvmStatic
    public static final Pair<JvmNameResolver, ProtoBuf$Package> readPackageDataFrom(String[] data, String[] strings) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        byte[] bArrDecodeBytes = BitEncoding.decodeBytes(data);
        Intrinsics.checkNotNullExpressionValue(bArrDecodeBytes, "decodeBytes(data)");
        return readPackageDataFrom(bArrDecodeBytes, strings);
    }

    @JvmStatic
    public static final Pair<JvmNameResolver, ProtoBuf$Package> readPackageDataFrom(byte[] bytes, String[] strings) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strings), ProtoBuf$Package.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    @JvmStatic
    public static final Pair<JvmNameResolver, ProtoBuf$Function> readFunctionDataFrom(String[] data, String[] strings) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(BitEncoding.decodeBytes(data));
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strings), ProtoBuf$Function.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    private final JvmNameResolver readNameResolver(InputStream inputStream, String[] strArr) throws IOException {
        JvmProtoBuf.StringTableTypes delimitedFrom = JvmProtoBuf.StringTableTypes.parseDelimitedFrom(inputStream, EXTENSION_REGISTRY);
        Intrinsics.checkNotNullExpressionValue(delimitedFrom, "parseDelimitedFrom(this, EXTENSION_REGISTRY)");
        return new JvmNameResolver(delimitedFrom, strArr);
    }

    public final JvmMemberSignature.Method getJvmMethodSignature(ProtoBuf$Function proto, NameResolver nameResolver, TypeTable typeTable) {
        String string;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf$Function, JvmProtoBuf.JvmMethodSignature> methodSignature = JvmProtoBuf.methodSignature;
        Intrinsics.checkNotNullExpressionValue(methodSignature, "methodSignature");
        JvmProtoBuf.JvmMethodSignature jvmMethodSignature = (JvmProtoBuf.JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(proto, methodSignature);
        int name = (jvmMethodSignature == null || !jvmMethodSignature.hasName()) ? proto.getName() : jvmMethodSignature.getName();
        if (jvmMethodSignature != null && jvmMethodSignature.hasDesc()) {
            string = nameResolver.getString(jvmMethodSignature.getDesc());
        } else {
            List listListOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(ProtoTypeTableUtilKt.receiverType(proto, typeTable));
            List<ProtoBuf$ValueParameter> valueParameterList = proto.getValueParameterList();
            Intrinsics.checkNotNullExpressionValue(valueParameterList, "proto.valueParameterList");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(valueParameterList, 10));
            for (ProtoBuf$ValueParameter it : valueParameterList) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                arrayList.add(ProtoTypeTableUtilKt.type(it, typeTable));
            }
            List listPlus = CollectionsKt___CollectionsKt.plus((Collection) listListOfNotNull, (Iterable) arrayList);
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it2 = listPlus.iterator();
            while (it2.hasNext()) {
                String strMapTypeDefault = INSTANCE.mapTypeDefault((ProtoBuf$Type) it2.next(), nameResolver);
                if (strMapTypeDefault == null) {
                    return null;
                }
                arrayList2.add(strMapTypeDefault);
            }
            String strMapTypeDefault2 = mapTypeDefault(ProtoTypeTableUtilKt.returnType(proto, typeTable), nameResolver);
            if (strMapTypeDefault2 == null) {
                return null;
            }
            string = CollectionsKt___CollectionsKt.joinToString$default(arrayList2, "", "(", ")", 0, null, null, 56, null) + strMapTypeDefault2;
        }
        return new JvmMemberSignature.Method(nameResolver.getString(name), string);
    }

    public final JvmMemberSignature.Method getJvmConstructorSignature(ProtoBuf$Constructor proto, NameResolver nameResolver, TypeTable typeTable) {
        String strJoinToString$default;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf$Constructor, JvmProtoBuf.JvmMethodSignature> constructorSignature = JvmProtoBuf.constructorSignature;
        Intrinsics.checkNotNullExpressionValue(constructorSignature, "constructorSignature");
        JvmProtoBuf.JvmMethodSignature jvmMethodSignature = (JvmProtoBuf.JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(proto, constructorSignature);
        String string = (jvmMethodSignature == null || !jvmMethodSignature.hasName()) ? "<init>" : nameResolver.getString(jvmMethodSignature.getName());
        if (jvmMethodSignature != null && jvmMethodSignature.hasDesc()) {
            strJoinToString$default = nameResolver.getString(jvmMethodSignature.getDesc());
        } else {
            List<ProtoBuf$ValueParameter> valueParameterList = proto.getValueParameterList();
            Intrinsics.checkNotNullExpressionValue(valueParameterList, "proto.valueParameterList");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(valueParameterList, 10));
            for (ProtoBuf$ValueParameter it : valueParameterList) {
                JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                String strMapTypeDefault = jvmProtoBufUtil.mapTypeDefault(ProtoTypeTableUtilKt.type(it, typeTable), nameResolver);
                if (strMapTypeDefault == null) {
                    return null;
                }
                arrayList.add(strMapTypeDefault);
            }
            strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, "", "(", ")V", 0, null, null, 56, null);
        }
        return new JvmMemberSignature.Method(string, strJoinToString$default);
    }

    public static /* synthetic */ JvmMemberSignature.Field getJvmFieldSignature$default(JvmProtoBufUtil jvmProtoBufUtil, ProtoBuf$Property protoBuf$Property, NameResolver nameResolver, TypeTable typeTable, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return jvmProtoBufUtil.getJvmFieldSignature(protoBuf$Property, nameResolver, typeTable, z);
    }

    public final JvmMemberSignature.Field getJvmFieldSignature(ProtoBuf$Property proto, NameResolver nameResolver, TypeTable typeTable, boolean requireHasFieldFlag) {
        String strMapTypeDefault;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf$Property, JvmProtoBuf.JvmPropertySignature> propertySignature = JvmProtoBuf.propertySignature;
        Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
        JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(proto, propertySignature);
        if (jvmPropertySignature == null) {
            return null;
        }
        JvmProtoBuf.JvmFieldSignature field = jvmPropertySignature.hasField() ? jvmPropertySignature.getField() : null;
        if (field == null && requireHasFieldFlag) {
            return null;
        }
        int name = (field == null || !field.hasName()) ? proto.getName() : field.getName();
        if (field == null || !field.hasDesc()) {
            strMapTypeDefault = mapTypeDefault(ProtoTypeTableUtilKt.returnType(proto, typeTable), nameResolver);
            if (strMapTypeDefault == null) {
                return null;
            }
        } else {
            strMapTypeDefault = nameResolver.getString(field.getDesc());
        }
        return new JvmMemberSignature.Field(nameResolver.getString(name), strMapTypeDefault);
    }

    private final String mapTypeDefault(ProtoBuf$Type type, NameResolver nameResolver) {
        if (type.hasClassName()) {
            return ClassMapperLite.mapClass(nameResolver.getQualifiedClassName(type.getClassName()));
        }
        return null;
    }
}
