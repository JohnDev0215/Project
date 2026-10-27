package com.squareup.kotlinpoet;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.squareup.kotlinpoet.CodeBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: TypeSpec.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 e2\u00020\u00012\u00020\u0002:\u0002efJ\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0002J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J;\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0014\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u001a\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u0004H\u0016R\u0014\u0010\u001e\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0017\u0010!\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u0019\u0010%\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010)\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u001d\u0010/\u001a\b\u0012\u0004\u0012\u00020.0-8\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u001d\u00103\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0006¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u001d\u00108\u001a\b\u0012\u0004\u0012\u0002070-8\u0006¢\u0006\f\n\u0004\b8\u00100\u001a\u0004\b9\u00102R\u0019\u0010;\u001a\u0004\u0018\u00010:8\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u0017\u0010@\u001a\u00020?8\u0006¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u001d\u0010D\u001a\b\u0012\u0004\u0012\u00020\f0-8\u0006¢\u0006\f\n\u0004\bD\u00100\u001a\u0004\bE\u00102R\u0017\u0010F\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bF\u0010HR\u0017\u0010I\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\bI\u0010G\u001a\u0004\bI\u0010HR%\u0010J\u001a\u0010\u0012\u0004\u0012\u00020?\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00038\u0006¢\u0006\f\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010MR#\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00000\u00038\u0006¢\u0006\f\n\u0004\bN\u0010K\u001a\u0004\bO\u0010MR\u001d\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00050-8\u0006¢\u0006\f\n\u0004\bP\u00100\u001a\u0004\bQ\u00102R\u0017\u0010R\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\bR\u0010*\u001a\u0004\bS\u0010,R\u0017\u0010T\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010WR\u001d\u0010X\u001a\b\u0012\u0004\u0012\u00020:0-8\u0006¢\u0006\f\n\u0004\bX\u00100\u001a\u0004\bY\u00102R\u001d\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00000-8\u0006¢\u0006\f\n\u0004\bZ\u00100\u001a\u0004\b[\u00102R\"\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00118\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\\\u00104\u001a\u0004\b]\u00106R\u0014\u0010_\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b^\u0010HR\u0014\u0010a\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b`\u0010HR\u001a\u0010d\u001a\b\u0012\u0004\u0012\u00020b0-8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bc\u00102¨\u0006g"}, d2 = {"Lcom/squareup/kotlinpoet/TypeSpec;", "", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "", "", "Lcom/squareup/kotlinpoet/PropertySpec;", "constructorProperties", "property", "Lcom/squareup/kotlinpoet/ParameterSpec;", "parameter", "", "isPropertyInitializerConstructorParameter", "Lcom/squareup/kotlinpoet/CodeBlock;", "kdocWithConstructorParameters", "Lcom/squareup/kotlinpoet/CodeWriter;", "codeWriter", "enumName", "", "Lcom/squareup/kotlinpoet/KModifier;", "implicitModifiers", "isNestedExternal", "", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;Ljava/lang/String;Ljava/util/Set;Z)V", "emit", "other", "equals", "", "hashCode", "toString", "delegateOriginatingElements", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "kind", "Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "getKind", "()Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock;", "getKdoc", "()Lcom/squareup/kotlinpoet/CodeBlock;", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotationSpecs", "Ljava/util/List;", "getAnnotationSpecs", "()Ljava/util/List;", "modifiers", "Ljava/util/Set;", "getModifiers", "()Ljava/util/Set;", "Lcom/squareup/kotlinpoet/TypeVariableName;", "typeVariables", "getTypeVariables", "Lcom/squareup/kotlinpoet/FunSpec;", "primaryConstructor", "Lcom/squareup/kotlinpoet/FunSpec;", "getPrimaryConstructor", "()Lcom/squareup/kotlinpoet/FunSpec;", "Lcom/squareup/kotlinpoet/TypeName;", "superclass", "Lcom/squareup/kotlinpoet/TypeName;", "getSuperclass", "()Lcom/squareup/kotlinpoet/TypeName;", "superclassConstructorParameters", "getSuperclassConstructorParameters", "isEnum", "Z", "()Z", "isAnonymousClass", "superinterfaces", "Ljava/util/Map;", "getSuperinterfaces", "()Ljava/util/Map;", "enumConstants", "getEnumConstants", "propertySpecs", "getPropertySpecs", "initializerBlock", "getInitializerBlock", "initializerIndex", "I", "getInitializerIndex", "()I", "funSpecs", "getFunSpecs", "typeSpecs", "getTypeSpecs", "nestedTypesSimpleNames", "getNestedTypesSimpleNames$kotlinpoet", "getHasInitializer", "hasInitializer", "getHasNoBody", "hasNoBody", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "originatingElements", "Companion", "Kind", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class TypeSpec implements OriginatingElementsHolder {
    private final List<AnnotationSpec> annotationSpecs;
    private final OriginatingElementsHolder delegateOriginatingElements;
    private final Map<String, TypeSpec> enumConstants;
    private final List<FunSpec> funSpecs;
    private final CodeBlock initializerBlock;
    private final int initializerIndex;
    private final boolean isAnonymousClass;
    private final boolean isEnum;
    private final CodeBlock kdoc;
    private final Kind kind;
    private final Set<KModifier> modifiers;
    private final String name;
    private final Set<String> nestedTypesSimpleNames;
    private final FunSpec primaryConstructor;
    private final List<PropertySpec> propertySpecs;
    private final TypeName superclass;
    private final List<CodeBlock> superclassConstructorParameters;
    private final Map<TypeName, CodeBlock> superinterfaces;
    private final List<TypeSpec> typeSpecs;
    private final List<TypeVariableName> typeVariables;

    @Override // com.squareup.kotlinpoet.OriginatingElementsHolder
    public List<Element> getOriginatingElements() {
        return this.delegateOriginatingElements.getOriginatingElements();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CodeWriter codeWriter = new CodeWriter(sb, null, null, null, null, Integer.MAX_VALUE, 30, null);
        try {
            emit$kotlinpoet$default(this, codeWriter, null, null, false, 12, null);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(codeWriter, null);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
            return string;
        } finally {
        }
    }

    public final String getName() {
        return this.name;
    }

    public final CodeBlock getKdoc() {
        return this.kdoc;
    }

    public final Set<KModifier> getModifiers() {
        return this.modifiers;
    }

    public final FunSpec getPrimaryConstructor() {
        return this.primaryConstructor;
    }

    public final int getInitializerIndex() {
        return this.initializerIndex;
    }

    public final List<FunSpec> getFunSpecs() {
        return this.funSpecs;
    }

    public final Set<String> getNestedTypesSimpleNames$kotlinpoet() {
        return this.nestedTypesSimpleNames;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void emit$kotlinpoet$default(TypeSpec typeSpec, CodeWriter codeWriter, String str, Set set, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            set = SetsKt__SetsKt.emptySet();
        }
        if ((i & 8) != 0) {
            z = false;
        }
        typeSpec.emit$kotlinpoet(codeWriter, str, set, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x029f A[Catch: all -> 0x055a, TryCatch #0 {all -> 0x055a, blocks: (B:10:0x0041, B:12:0x005c, B:14:0x007f, B:18:0x0089, B:133:0x036e, B:134:0x0386, B:136:0x038c, B:138:0x03a3, B:139:0x03a9, B:140:0x03be, B:142:0x03c2, B:144:0x03c6, B:145:0x03cc, B:147:0x03d6, B:149:0x03df, B:151:0x03e8, B:152:0x03f0, B:153:0x0400, B:155:0x0406, B:157:0x0412, B:158:0x0415, B:161:0x0424, B:163:0x0428, B:164:0x042e, B:166:0x0456, B:168:0x045f, B:170:0x0469, B:171:0x0490, B:172:0x0496, B:174:0x049c, B:177:0x04a9, B:179:0x04ad, B:181:0x04b9, B:182:0x04d3, B:183:0x04de, B:185:0x04e4, B:188:0x04f1, B:190:0x04f5, B:191:0x04fa, B:192:0x0510, B:193:0x0516, B:195:0x051c, B:197:0x0526, B:198:0x052b, B:199:0x053f, B:201:0x054f, B:203:0x0553, B:19:0x0090, B:21:0x0098, B:25:0x00ab, B:27:0x00b5, B:30:0x00e1, B:32:0x00eb, B:33:0x00fe, B:35:0x0104, B:37:0x011e, B:39:0x012a, B:40:0x0146, B:42:0x014c, B:46:0x0159, B:36:0x011a, B:28:0x00cb, B:29:0x00dd, B:47:0x0160, B:49:0x0170, B:51:0x0185, B:53:0x0197, B:54:0x01a1, B:83:0x0228, B:84:0x0237, B:86:0x023d, B:88:0x024e, B:89:0x0252, B:90:0x0261, B:93:0x0269, B:95:0x0275, B:97:0x027d, B:108:0x029f, B:114:0x02d5, B:100:0x0285, B:101:0x0289, B:103:0x028f, B:110:0x02ad, B:112:0x02b9, B:113:0x02ca, B:115:0x02d9, B:116:0x02ee, B:118:0x02f4, B:120:0x0308, B:122:0x0329, B:121:0x0316, B:123:0x0330, B:125:0x033c, B:126:0x0353, B:128:0x035e, B:132:0x036b, B:57:0x01ac, B:61:0x01bc, B:63:0x01c8, B:68:0x01d7, B:72:0x01e7, B:73:0x01f1, B:76:0x01ff, B:78:0x0207, B:81:0x0213, B:82:0x0218, B:50:0x017f), top: B:209:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02ca A[Catch: all -> 0x055a, TryCatch #0 {all -> 0x055a, blocks: (B:10:0x0041, B:12:0x005c, B:14:0x007f, B:18:0x0089, B:133:0x036e, B:134:0x0386, B:136:0x038c, B:138:0x03a3, B:139:0x03a9, B:140:0x03be, B:142:0x03c2, B:144:0x03c6, B:145:0x03cc, B:147:0x03d6, B:149:0x03df, B:151:0x03e8, B:152:0x03f0, B:153:0x0400, B:155:0x0406, B:157:0x0412, B:158:0x0415, B:161:0x0424, B:163:0x0428, B:164:0x042e, B:166:0x0456, B:168:0x045f, B:170:0x0469, B:171:0x0490, B:172:0x0496, B:174:0x049c, B:177:0x04a9, B:179:0x04ad, B:181:0x04b9, B:182:0x04d3, B:183:0x04de, B:185:0x04e4, B:188:0x04f1, B:190:0x04f5, B:191:0x04fa, B:192:0x0510, B:193:0x0516, B:195:0x051c, B:197:0x0526, B:198:0x052b, B:199:0x053f, B:201:0x054f, B:203:0x0553, B:19:0x0090, B:21:0x0098, B:25:0x00ab, B:27:0x00b5, B:30:0x00e1, B:32:0x00eb, B:33:0x00fe, B:35:0x0104, B:37:0x011e, B:39:0x012a, B:40:0x0146, B:42:0x014c, B:46:0x0159, B:36:0x011a, B:28:0x00cb, B:29:0x00dd, B:47:0x0160, B:49:0x0170, B:51:0x0185, B:53:0x0197, B:54:0x01a1, B:83:0x0228, B:84:0x0237, B:86:0x023d, B:88:0x024e, B:89:0x0252, B:90:0x0261, B:93:0x0269, B:95:0x0275, B:97:0x027d, B:108:0x029f, B:114:0x02d5, B:100:0x0285, B:101:0x0289, B:103:0x028f, B:110:0x02ad, B:112:0x02b9, B:113:0x02ca, B:115:0x02d9, B:116:0x02ee, B:118:0x02f4, B:120:0x0308, B:122:0x0329, B:121:0x0316, B:123:0x0330, B:125:0x033c, B:126:0x0353, B:128:0x035e, B:132:0x036b, B:57:0x01ac, B:61:0x01bc, B:63:0x01c8, B:68:0x01d7, B:72:0x01e7, B:73:0x01f1, B:76:0x01ff, B:78:0x0207, B:81:0x0213, B:82:0x0218, B:50:0x017f), top: B:209:0x003f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void emit$kotlinpoet(final com.squareup.kotlinpoet.CodeWriter r28, java.lang.String r29, java.util.Set<? extends com.squareup.kotlinpoet.KModifier> r30, boolean r31) {
        /*
            Method dump skipped, instruction units count: 1375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeSpec.emit$kotlinpoet(com.squareup.kotlinpoet.CodeWriter, java.lang.String, java.util.Set, boolean):void");
    }

    private static final void emit$possiblyEmitInitializer(Ref.BooleanRef booleanRef, boolean z, Ref.BooleanRef booleanRef2, CodeWriter codeWriter, TypeSpec typeSpec) {
        if (booleanRef.element) {
            return;
        }
        booleanRef.element = true;
        if (z) {
            if (!booleanRef2.element) {
                CodeWriter.emit$default(codeWriter, "\n", false, 2, null);
            }
            CodeWriter.emitCode$default(codeWriter, typeSpec.initializerBlock, false, false, 6, null);
            booleanRef2.element = false;
        }
    }

    private final Map<String, PropertySpec> constructorProperties() {
        IntRange indices;
        ParameterSpec parameterSpecParameter$kotlinpoet;
        if (this.primaryConstructor == null) {
            return MapsKt__MapsKt.emptyMap();
        }
        if (getHasInitializer()) {
            indices = RangesKt___RangesKt.until(0, this.initializerIndex);
        } else {
            indices = CollectionsKt__CollectionsKt.getIndices(this.propertySpecs);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int first = indices.getFirst();
        int last = indices.getLast();
        if (first <= last) {
            while (true) {
                int i = first + 1;
                PropertySpec propertySpec = this.propertySpecs.get(first);
                if (propertySpec.getGetter() == null && propertySpec.getSetter() == null && (parameterSpecParameter$kotlinpoet = this.primaryConstructor.parameter$kotlinpoet(propertySpec.getName())) != null && Intrinsics.areEqual(parameterSpecParameter$kotlinpoet.getType(), propertySpec.getType()) && isPropertyInitializerConstructorParameter(propertySpec, parameterSpecParameter$kotlinpoet)) {
                    linkedHashMap.put(propertySpec.getName(), propertySpec.fromPrimaryConstructorParameter$kotlinpoet(parameterSpecParameter$kotlinpoet));
                }
                if (first == last) {
                    break;
                }
                first = i;
            }
        }
        return linkedHashMap;
    }

    private final boolean isPropertyInitializerConstructorParameter(PropertySpec property, ParameterSpec parameter) {
        return Intrinsics.areEqual(CodeBlock.INSTANCE.of("%N", parameter).toString(), UtilKt.escapeIfNecessary(String.valueOf(property.getInitializer()), false));
    }

    private final CodeBlock kdocWithConstructorParameters() {
        FunSpec funSpec = this.primaryConstructor;
        if (funSpec == null || funSpec.getParameters().isEmpty()) {
            return UtilKt.ensureEndsWithNewLine(this.kdoc);
        }
        Map<String, PropertySpec> mapConstructorProperties = constructorProperties();
        List<ParameterSpec> parameters = this.primaryConstructor.getParameters();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = parameters.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ParameterSpec parameterSpec = (ParameterSpec) next;
            PropertySpec propertySpec = mapConstructorProperties.get(parameterSpec.getName());
            CodeBlock kdoc = propertySpec == null ? null : propertySpec.getKdoc();
            if (kdoc == null) {
                kdoc = CodeBlock.INSTANCE.getEMPTY$kotlinpoet();
            }
            if (parameterSpec.getKdoc().isNotEmpty() && kdoc.isNotEmpty() && !Intrinsics.areEqual(parameterSpec.getKdoc(), kdoc)) {
                arrayList.add(next);
            }
        }
        CodeBlock.Builder builder = UtilKt.ensureEndsWithNewLine(this.kdoc).toBuilder();
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            ParameterSpec parameterSpec2 = (ParameterSpec) obj;
            if (i == 0 && getKdoc().isNotEmpty()) {
                builder.add("\n", new Object[0]);
            }
            builder.add("@param %L %L", parameterSpec2.getName(), UtilKt.ensureEndsWithNewLine(parameterSpec2.getKdoc()));
            i = i2;
        }
        return builder.build();
    }

    private final boolean getHasInitializer() {
        return this.initializerIndex != -1 && this.initializerBlock.isNotEmpty();
    }

    private final boolean getHasNoBody() {
        CodeBlock body;
        if (!this.propertySpecs.isEmpty()) {
            Map<String, PropertySpec> mapConstructorProperties = constructorProperties();
            Iterator<PropertySpec> it = this.propertySpecs.iterator();
            while (it.hasNext()) {
                if (!mapConstructorProperties.containsKey(it.next().getName())) {
                    return false;
                }
            }
        }
        if (this.enumConstants.isEmpty() && this.initializerBlock.isEmpty()) {
            FunSpec funSpec = this.primaryConstructor;
            if (((funSpec == null || (body = funSpec.getBody()) == null) ? true : body.isEmpty()) && this.funSpecs.isEmpty() && this.typeSpecs.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(TypeSpec.class, other.getClass())) {
            return Intrinsics.areEqual(toString(), other.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'CLASS' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: TypeSpec.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\tJ#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0002\b\u0012J!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0002\b\u0014J#\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0002\b\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rj\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "", "declarationKeyword", "", "defaultImplicitPropertyModifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "defaultImplicitFunctionModifiers", "defaultImplicitTypeModifiers", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;)V", "getDeclarationKeyword$kotlinpoet", "()Ljava/lang/String;", "getDefaultImplicitFunctionModifiers$kotlinpoet", "()Ljava/util/Set;", "getDefaultImplicitPropertyModifiers$kotlinpoet", "getDefaultImplicitTypeModifiers$kotlinpoet", "implicitFunctionModifiers", "modifiers", "implicitFunctionModifiers$kotlinpoet", "implicitPropertyModifiers", "implicitPropertyModifiers$kotlinpoet", "implicitTypeModifiers", "implicitTypeModifiers$kotlinpoet", "CLASS", "OBJECT", "INTERFACE", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Kind {
        private static final /* synthetic */ Kind[] $VALUES;
        public static final Kind CLASS;
        public static final Kind INTERFACE;
        public static final Kind OBJECT;
        private final String declarationKeyword;
        private final Set<KModifier> defaultImplicitFunctionModifiers;
        private final Set<KModifier> defaultImplicitPropertyModifiers;
        private final Set<KModifier> defaultImplicitTypeModifiers;

        private static final /* synthetic */ Kind[] $values() {
            return new Kind[]{CLASS, OBJECT, INTERFACE};
        }

        public static Kind valueOf(String str) {
            return (Kind) Enum.valueOf(Kind.class, str);
        }

        public static Kind[] values() {
            return (Kind[]) $VALUES.clone();
        }

        private Kind(String str, int i, String str2, Set set, Set set2, Set set3) {
            this.declarationKeyword = str2;
            this.defaultImplicitPropertyModifiers = set;
            this.defaultImplicitFunctionModifiers = set2;
            this.defaultImplicitTypeModifiers = set3;
        }

        /* JADX INFO: renamed from: getDeclarationKeyword$kotlinpoet, reason: from getter */
        public final String getDeclarationKeyword() {
            return this.declarationKeyword;
        }

        public final Set<KModifier> getDefaultImplicitPropertyModifiers$kotlinpoet() {
            return this.defaultImplicitPropertyModifiers;
        }

        public final Set<KModifier> getDefaultImplicitFunctionModifiers$kotlinpoet() {
            return this.defaultImplicitFunctionModifiers;
        }

        public final Set<KModifier> getDefaultImplicitTypeModifiers$kotlinpoet() {
            return this.defaultImplicitTypeModifiers;
        }

        static {
            KModifier kModifier = KModifier.PUBLIC;
            CLASS = new Kind("CLASS", 0, "class", SetsKt__SetsJVMKt.setOf(kModifier), SetsKt__SetsJVMKt.setOf(kModifier), SetsKt__SetsKt.emptySet());
            OBJECT = new Kind("OBJECT", 1, "object", SetsKt__SetsJVMKt.setOf(kModifier), SetsKt__SetsJVMKt.setOf(kModifier), SetsKt__SetsKt.emptySet());
            KModifier kModifier2 = KModifier.ABSTRACT;
            INTERFACE = new Kind("INTERFACE", 2, ContextMappingConstants.INTERFACE, SetsKt__SetsKt.setOf((Object[]) new KModifier[]{kModifier, kModifier2}), SetsKt__SetsKt.setOf((Object[]) new KModifier[]{kModifier, kModifier2}), SetsKt__SetsKt.emptySet());
            $VALUES = $values();
        }

        public final Set<KModifier> implicitPropertyModifiers$kotlinpoet(Set<? extends KModifier> modifiers) {
            Set of;
            Intrinsics.checkNotNullParameter(modifiers, "modifiers");
            Set<KModifier> set = this.defaultImplicitPropertyModifiers;
            if (modifiers.contains(KModifier.ANNOTATION)) {
                of = SetsKt__SetsKt.emptySet();
            } else {
                KModifier kModifier = KModifier.EXPECT;
                if (modifiers.contains(kModifier)) {
                    of = SetsKt__SetsJVMKt.setOf(kModifier);
                } else {
                    KModifier kModifier2 = KModifier.EXTERNAL;
                    of = modifiers.contains(kModifier2) ? SetsKt__SetsJVMKt.setOf(kModifier2) : SetsKt__SetsKt.emptySet();
                }
            }
            return SetsKt___SetsKt.plus((Set) set, (Iterable) of);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Set implicitFunctionModifiers$kotlinpoet$default(Kind kind, Set set, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: implicitFunctionModifiers");
            }
            if ((i & 1) != 0) {
                set = SetsKt__SetsKt.emptySet();
            }
            return kind.implicitFunctionModifiers$kotlinpoet(set);
        }

        public final Set<KModifier> implicitFunctionModifiers$kotlinpoet(Set<? extends KModifier> modifiers) {
            Set of;
            Intrinsics.checkNotNullParameter(modifiers, "modifiers");
            Set<KModifier> set = this.defaultImplicitFunctionModifiers;
            if (modifiers.contains(KModifier.ANNOTATION)) {
                of = SetsKt__SetsJVMKt.setOf(KModifier.ABSTRACT);
            } else {
                KModifier kModifier = KModifier.EXPECT;
                if (modifiers.contains(kModifier)) {
                    of = SetsKt__SetsJVMKt.setOf(kModifier);
                } else {
                    KModifier kModifier2 = KModifier.EXTERNAL;
                    of = modifiers.contains(kModifier2) ? SetsKt__SetsJVMKt.setOf(kModifier2) : SetsKt__SetsKt.emptySet();
                }
            }
            return SetsKt___SetsKt.plus((Set) set, (Iterable) of);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Set implicitTypeModifiers$kotlinpoet$default(Kind kind, Set set, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: implicitTypeModifiers");
            }
            if ((i & 1) != 0) {
                set = SetsKt__SetsKt.emptySet();
            }
            return kind.implicitTypeModifiers$kotlinpoet(set);
        }

        public final Set<KModifier> implicitTypeModifiers$kotlinpoet(Set<? extends KModifier> modifiers) {
            Set of;
            Intrinsics.checkNotNullParameter(modifiers, "modifiers");
            Set<KModifier> set = this.defaultImplicitTypeModifiers;
            KModifier kModifier = KModifier.EXPECT;
            if (modifiers.contains(kModifier)) {
                of = SetsKt__SetsJVMKt.setOf(kModifier);
            } else {
                KModifier kModifier2 = KModifier.EXTERNAL;
                of = modifiers.contains(kModifier2) ? SetsKt__SetsJVMKt.setOf(kModifier2) : SetsKt__SetsKt.emptySet();
            }
            return SetsKt___SetsKt.plus((Set) set, (Iterable) of);
        }
    }
}
