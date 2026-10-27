package com.squareup.kotlinpoet;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.KModifier;
import com.squareup.kotlinpoet.OriginatingElementsHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: PropertySpec.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 U2\u00020\u00012\u00020\u0002:\u0002VUB%\b\u0002\u0012\u0006\u0010R\u001a\u00020\u001f\u0012\b\b\u0002\u0010\"\u001a\u00020!\u0012\b\b\u0002\u0010$\u001a\u00020\u0002¢\u0006\u0004\bS\u0010TJM\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010\u001c\u001a\u00020\u001a2\b\b\u0002\u0010\u001e\u001a\u00020\u001dH\u0007R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0017\u0010&\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\u001c\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b\u001c\u0010*\u001a\u0004\b+\u0010,R\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010-\u001a\u0004\b.\u0010/R\u0017\u00101\u001a\u0002008\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u001d\u00107\u001a\b\u0012\u0004\u0012\u000206058\u0006¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R\u001d\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u001d\u0010@\u001a\b\u0012\u0004\u0012\u00020?058\u0006¢\u0006\f\n\u0004\b@\u00108\u001a\u0004\bA\u0010:R\u0019\u0010B\u001a\u0004\u0018\u0001008\u0006¢\u0006\f\n\u0004\bB\u00102\u001a\u0004\bC\u00104R\u0017\u0010D\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\bD\u0010'\u001a\u0004\bE\u0010)R\u0019\u0010G\u001a\u0004\u0018\u00010F8\u0006¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u0019\u0010K\u001a\u0004\u0018\u00010F8\u0006¢\u0006\f\n\u0004\bK\u0010H\u001a\u0004\bL\u0010JR\u0019\u0010M\u001a\u0004\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\bM\u0010-\u001a\u0004\bN\u0010/R\u001a\u0010Q\u001a\b\u0012\u0004\u0012\u00020O058\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bP\u0010:¨\u0006W"}, d2 = {"Lcom/squareup/kotlinpoet/PropertySpec;", "", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "Lcom/squareup/kotlinpoet/CodeWriter;", "codeWriter", "", "Lcom/squareup/kotlinpoet/KModifier;", "implicitModifiers", "", "withInitializer", "emitKdoc", "inline", "inlineAnnotations", "", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;Ljava/util/Set;ZZZZ)V", "emit", "Lcom/squareup/kotlinpoet/ParameterSpec;", "parameter", "fromPrimaryConstructorParameter$kotlinpoet", "(Lcom/squareup/kotlinpoet/ParameterSpec;)Lcom/squareup/kotlinpoet/PropertySpec;", "fromPrimaryConstructorParameter", "other", "equals", "", "hashCode", "", "toString", "name", "Lcom/squareup/kotlinpoet/TypeName;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "toBuilder", "Lcom/squareup/kotlinpoet/TagMap;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "delegateOriginatingElementsHolder", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "mutable", "Z", "getMutable", "()Z", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lcom/squareup/kotlinpoet/TypeName;", "getType", "()Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/CodeBlock;", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock;", "getKdoc", "()Lcom/squareup/kotlinpoet/CodeBlock;", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "Ljava/util/List;", "getAnnotations", "()Ljava/util/List;", "modifiers", "Ljava/util/Set;", "getModifiers", "()Ljava/util/Set;", "Lcom/squareup/kotlinpoet/TypeVariableName;", "typeVariables", "getTypeVariables", "initializer", "getInitializer", "delegated", "getDelegated", "Lcom/squareup/kotlinpoet/FunSpec;", "getter", "Lcom/squareup/kotlinpoet/FunSpec;", "getGetter", "()Lcom/squareup/kotlinpoet/FunSpec;", "setter", "getSetter", "receiverType", "getReceiverType", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "originatingElements", "builder", "<init>", "(Lcom/squareup/kotlinpoet/PropertySpec$Builder;Lcom/squareup/kotlinpoet/TagMap;Lcom/squareup/kotlinpoet/OriginatingElementsHolder;)V", "Companion", "Builder", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class PropertySpec implements OriginatingElementsHolder {
    private final List<AnnotationSpec> annotations;
    private final OriginatingElementsHolder delegateOriginatingElementsHolder;
    private final boolean delegated;
    private final FunSpec getter;
    private final CodeBlock initializer;
    private final CodeBlock kdoc;
    private final Set<KModifier> modifiers;
    private final boolean mutable;
    private final String name;
    private final TypeName receiverType;
    private final FunSpec setter;
    private final TagMap tagMap;
    private final TypeName type;
    private final List<TypeVariableName> typeVariables;

    @Override // com.squareup.kotlinpoet.OriginatingElementsHolder
    public List<Element> getOriginatingElements() {
        return this.delegateOriginatingElementsHolder.getOriginatingElements();
    }

    private PropertySpec(Builder builder, TagMap tagMap, OriginatingElementsHolder originatingElementsHolder) {
        boolean z;
        FunSpec funSpec;
        FunSpec funSpec2;
        this.tagMap = tagMap;
        this.delegateOriginatingElementsHolder = originatingElementsHolder;
        this.mutable = builder.getMutable();
        this.name = builder.getName();
        this.type = builder.getType();
        this.kdoc = builder.getKdoc().build();
        this.annotations = UtilKt.toImmutableList(builder.getAnnotations());
        this.modifiers = UtilKt.toImmutableSet(builder.getModifiers());
        List<TypeVariableName> immutableList = UtilKt.toImmutableList(builder.getTypeVariables());
        this.typeVariables = immutableList;
        this.initializer = builder.getInitializer();
        this.delegated = builder.getDelegated();
        this.getter = builder.getGetter();
        this.setter = builder.getSetter();
        this.receiverType = builder.getReceiverType();
        if ((immutableList instanceof Collection) && immutableList.isEmpty()) {
            z = true;
        } else {
            Iterator<T> it = immutableList.iterator();
            while (it.hasNext()) {
                if (((TypeVariableName) it.next()).getIsReified()) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        if (!(z || (!((funSpec = this.getter) == null && this.setter == null) && ((funSpec == null || funSpec.getModifiers().contains(KModifier.INLINE)) && ((funSpec2 = this.setter) == null || funSpec2.getModifiers().contains(KModifier.INLINE)))))) {
            throw new IllegalArgumentException("only type parameters of properties with inline getters and/or setters can be reified!".toString());
        }
        if (!(this.mutable || this.setter == null)) {
            throw new IllegalArgumentException("only a mutable property can have a setter".toString());
        }
    }

    /* synthetic */ PropertySpec(Builder builder, TagMap tagMap, OriginatingElementsHolder originatingElementsHolder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder, (i & 2) != 0 ? TaggableKt.buildTagMap(builder) : tagMap, (i & 4) != 0 ? OriginatingElementsHolderKt.buildOriginatingElements(builder) : originatingElementsHolder);
    }

    public final String getName() {
        return this.name;
    }

    public final TypeName getType() {
        return this.type;
    }

    public final CodeBlock getKdoc() {
        return this.kdoc;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CodeWriter codeWriter = new CodeWriter(sb, null, null, null, null, Integer.MAX_VALUE, 30, null);
        try {
            emit$kotlinpoet$default(this, codeWriter, SetsKt__SetsKt.emptySet(), false, false, false, false, 60, null);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(codeWriter, null);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
            return string;
        } finally {
        }
    }

    public final CodeBlock getInitializer() {
        return this.initializer;
    }

    public final FunSpec getGetter() {
        return this.getter;
    }

    public final FunSpec getSetter() {
        return this.setter;
    }

    public static /* synthetic */ void emit$kotlinpoet$default(PropertySpec propertySpec, CodeWriter codeWriter, Set set, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        boolean z5 = (i & 4) != 0 ? true : z;
        boolean z6 = (i & 8) != 0 ? true : z2;
        boolean z7 = (i & 16) != 0 ? false : z3;
        propertySpec.emit$kotlinpoet(codeWriter, set, z5, z6, z7, (i & 32) != 0 ? z7 : z4);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void emit$kotlinpoet(com.squareup.kotlinpoet.CodeWriter r14, java.util.Set<? extends com.squareup.kotlinpoet.KModifier> r15, boolean r16, boolean r17, boolean r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.PropertySpec.emit$kotlinpoet(com.squareup.kotlinpoet.CodeWriter, java.util.Set, boolean, boolean, boolean, boolean):void");
    }

    public final PropertySpec fromPrimaryConstructorParameter$kotlinpoet(ParameterSpec parameter) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        Builder builderAddAnnotations = toBuilder$default(this, null, null, 3, null).addAnnotations(parameter.getAnnotations());
        builderAddAnnotations.setPrimaryConstructorParameter$kotlinpoet(true);
        CollectionsKt__MutableCollectionsKt.addAll(builderAddAnnotations.getModifiers(), parameter.getModifiers());
        if (builderAddAnnotations.getKdoc().isEmpty()) {
            builderAddAnnotations.addKdoc(parameter.getKdoc());
        }
        return builderAddAnnotations.build();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(PropertySpec.class, other.getClass())) {
            return Intrinsics.areEqual(toString(), other.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public static /* synthetic */ Builder toBuilder$default(PropertySpec propertySpec, String str, TypeName typeName, int i, Object obj) {
        if ((i & 1) != 0) {
            str = propertySpec.name;
        }
        if ((i & 2) != 0) {
            typeName = propertySpec.type;
        }
        return propertySpec.toBuilder(str, typeName);
    }

    @JvmOverloads
    public final Builder toBuilder(String name, TypeName type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        Builder builder = new Builder(name, type);
        builder.setMutable$kotlinpoet(this.mutable);
        builder.getKdoc().add(this.kdoc);
        CollectionsKt__MutableCollectionsKt.addAll(builder.getAnnotations(), this.annotations);
        CollectionsKt__MutableCollectionsKt.addAll(builder.getModifiers(), this.modifiers);
        CollectionsKt__MutableCollectionsKt.addAll(builder.getTypeVariables(), this.typeVariables);
        builder.setInitializer$kotlinpoet(this.initializer);
        builder.setDelegated$kotlinpoet(this.delegated);
        builder.setSetter$kotlinpoet(this.setter);
        builder.setGetter$kotlinpoet(this.getter);
        builder.setReceiverType$kotlinpoet(this.receiverType);
        builder.getTags().putAll(this.tagMap.getTags());
        CollectionsKt__MutableCollectionsKt.addAll(builder.getOriginatingElements(), getOriginatingElements());
        return builder;
    }

    /* JADX INFO: compiled from: PropertySpec.kt */
    @Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0019\b\u0000\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\bQ\u0010RJ\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u0014\u0010\t\u001a\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0006\u0010\u000b\u001a\u00020\nR\u001a\u0010\r\u001a\u00020\f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0017\u001a\u00020\u00168\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\u00020\u00168\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u001a\u0010!\u001a\u00020 8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u00038\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010+\u001a\u00020\u00168\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b+\u0010\u0018\u001a\u0004\b,\u0010\u001a\"\u0004\b-\u0010\u001cR$\u0010/\u001a\u0004\u0018\u00010.8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u00105\u001a\u0004\u0018\u00010.8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b5\u00100\u001a\u0004\b6\u00102\"\u0004\b7\u00104R$\u00108\u001a\u0004\u0018\u00010\u00118\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b8\u0010\u0013\u001a\u0004\b9\u0010\u0015\"\u0004\b:\u0010;R\u001d\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00070<8\u0006¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u001d\u0010B\u001a\b\u0012\u0004\u0012\u00020A0<8\u0006¢\u0006\f\n\u0004\bB\u0010>\u001a\u0004\bC\u0010@R\u001d\u0010E\u001a\b\u0012\u0004\u0012\u00020D0<8\u0006¢\u0006\f\n\u0004\bE\u0010>\u001a\u0004\bF\u0010@R*\u0010J\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030H\u0012\u0004\u0012\u00020I0G8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010MR \u0010O\u001a\b\u0012\u0004\u0012\u00020N0<8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bO\u0010>\u001a\u0004\bP\u0010@¨\u0006S"}, d2 = {"Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;", "Lcom/squareup/kotlinpoet/CodeBlock;", "block", "addKdoc", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotationSpecs", "addAnnotations", "Lcom/squareup/kotlinpoet/PropertySpec;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "name", "Ljava/lang/String;", "getName$kotlinpoet", "()Ljava/lang/String;", "Lcom/squareup/kotlinpoet/TypeName;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/squareup/kotlinpoet/TypeName;", "getType$kotlinpoet", "()Lcom/squareup/kotlinpoet/TypeName;", "", "isPrimaryConstructorParameter", "Z", "isPrimaryConstructorParameter$kotlinpoet", "()Z", "setPrimaryConstructorParameter$kotlinpoet", "(Z)V", "mutable", "getMutable$kotlinpoet", "setMutable$kotlinpoet", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "getKdoc$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "initializer", "Lcom/squareup/kotlinpoet/CodeBlock;", "getInitializer$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock;", "setInitializer$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeBlock;)V", "delegated", "getDelegated$kotlinpoet", "setDelegated$kotlinpoet", "Lcom/squareup/kotlinpoet/FunSpec;", "getter", "Lcom/squareup/kotlinpoet/FunSpec;", "getGetter$kotlinpoet", "()Lcom/squareup/kotlinpoet/FunSpec;", "setGetter$kotlinpoet", "(Lcom/squareup/kotlinpoet/FunSpec;)V", "setter", "getSetter$kotlinpoet", "setSetter$kotlinpoet", "receiverType", "getReceiverType$kotlinpoet", "setReceiverType$kotlinpoet", "(Lcom/squareup/kotlinpoet/TypeName;)V", "", "annotations", "Ljava/util/List;", "getAnnotations", "()Ljava/util/List;", "Lcom/squareup/kotlinpoet/KModifier;", "modifiers", "getModifiers", "Lcom/squareup/kotlinpoet/TypeVariableName;", "typeVariables", "getTypeVariables", "", "Lkotlin/reflect/KClass;", "", "tags", "Ljava/util/Map;", "getTags", "()Ljava/util/Map;", "Ljavax/lang/model/element/Element;", "originatingElements", "getOriginatingElements", "<init>", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;)V", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
    public static final class Builder implements Taggable$Builder<Builder>, OriginatingElementsHolder.Builder<Builder> {
        private final List<AnnotationSpec> annotations;
        private boolean delegated;
        private FunSpec getter;
        private CodeBlock initializer;
        private boolean isPrimaryConstructorParameter;
        private final CodeBlock.Builder kdoc;
        private final List<KModifier> modifiers;
        private boolean mutable;
        private final String name;
        private final List<Element> originatingElements;
        private TypeName receiverType;
        private FunSpec setter;
        private final Map<KClass<?>, Object> tags;
        private final TypeName type;
        private final List<TypeVariableName> typeVariables;

        public Builder(String name, TypeName type) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            this.name = name;
            this.type = type;
            this.kdoc = CodeBlock.INSTANCE.builder();
            this.annotations = new ArrayList();
            this.modifiers = new ArrayList();
            this.typeVariables = new ArrayList();
            this.tags = new LinkedHashMap();
            this.originatingElements = new ArrayList();
        }

        /* JADX INFO: renamed from: getName$kotlinpoet, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: getType$kotlinpoet, reason: from getter */
        public final TypeName getType() {
            return this.type;
        }

        public final void setPrimaryConstructorParameter$kotlinpoet(boolean z) {
            this.isPrimaryConstructorParameter = z;
        }

        /* JADX INFO: renamed from: getMutable$kotlinpoet, reason: from getter */
        public final boolean getMutable() {
            return this.mutable;
        }

        public final void setMutable$kotlinpoet(boolean z) {
            this.mutable = z;
        }

        /* JADX INFO: renamed from: getKdoc$kotlinpoet, reason: from getter */
        public final CodeBlock.Builder getKdoc() {
            return this.kdoc;
        }

        /* JADX INFO: renamed from: getInitializer$kotlinpoet, reason: from getter */
        public final CodeBlock getInitializer() {
            return this.initializer;
        }

        public final void setInitializer$kotlinpoet(CodeBlock codeBlock) {
            this.initializer = codeBlock;
        }

        /* JADX INFO: renamed from: getDelegated$kotlinpoet, reason: from getter */
        public final boolean getDelegated() {
            return this.delegated;
        }

        public final void setDelegated$kotlinpoet(boolean z) {
            this.delegated = z;
        }

        /* JADX INFO: renamed from: getGetter$kotlinpoet, reason: from getter */
        public final FunSpec getGetter() {
            return this.getter;
        }

        public final void setGetter$kotlinpoet(FunSpec funSpec) {
            this.getter = funSpec;
        }

        /* JADX INFO: renamed from: getSetter$kotlinpoet, reason: from getter */
        public final FunSpec getSetter() {
            return this.setter;
        }

        public final void setSetter$kotlinpoet(FunSpec funSpec) {
            this.setter = funSpec;
        }

        /* JADX INFO: renamed from: getReceiverType$kotlinpoet, reason: from getter */
        public final TypeName getReceiverType() {
            return this.receiverType;
        }

        public final void setReceiverType$kotlinpoet(TypeName typeName) {
            this.receiverType = typeName;
        }

        public final List<AnnotationSpec> getAnnotations() {
            return this.annotations;
        }

        public final List<KModifier> getModifiers() {
            return this.modifiers;
        }

        public final List<TypeVariableName> getTypeVariables() {
            return this.typeVariables;
        }

        @Override // com.squareup.kotlinpoet.Taggable$Builder
        public Map<KClass<?>, Object> getTags() {
            return this.tags;
        }

        @Override // com.squareup.kotlinpoet.OriginatingElementsHolder.Builder
        public List<Element> getOriginatingElements() {
            return this.originatingElements;
        }

        public final Builder addKdoc(CodeBlock block) {
            Intrinsics.checkNotNullParameter(block, "block");
            getKdoc().add(block);
            return this;
        }

        public final Builder addAnnotations(Iterable<AnnotationSpec> annotationSpecs) {
            Intrinsics.checkNotNullParameter(annotationSpecs, "annotationSpecs");
            CollectionsKt__MutableCollectionsKt.addAll(getAnnotations(), annotationSpecs);
            return this;
        }

        public final PropertySpec build() {
            if (this.modifiers.contains(KModifier.INLINE)) {
                throw new IllegalArgumentException("KotlinPoet doesn't allow setting the inline modifier on properties. You should mark either the getter, the setter, or both inline.");
            }
            for (KModifier kModifier : this.modifiers) {
                if (!this.isPrimaryConstructorParameter) {
                    kModifier.checkTarget$kotlinpoet(KModifier.Target.PROPERTY);
                }
            }
            return new PropertySpec(this, null, null, 6, null);
        }
    }
}
