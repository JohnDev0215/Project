package kotlinx.metadata.jvm.impl;

import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmClassExtensionVisitor;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.KmConstructorExtensionVisitor;
import kotlinx.metadata.KmConstructorVisitor;
import kotlinx.metadata.KmFunctionExtensionVisitor;
import kotlinx.metadata.KmFunctionVisitor;
import kotlinx.metadata.KmPropertyExtensionVisitor;
import kotlinx.metadata.KmPropertyVisitor;
import kotlinx.metadata.KmTypeAliasVisitor;
import kotlinx.metadata.KmTypeExtensionVisitor;
import kotlinx.metadata.KmTypeParameterExtensionVisitor;
import kotlinx.metadata.KmTypeParameterVisitor;
import kotlinx.metadata.KmTypeVisitor;
import kotlinx.metadata.KmValueParameterVisitor;
import kotlinx.metadata.impl.ReadContext;
import kotlinx.metadata.impl.ReadUtilsKt;
import kotlinx.metadata.impl.ReadersKt;
import kotlinx.metadata.impl.extensions.MetadataExtensions;
import kotlinx.metadata.internal.metadata.ProtoBuf$Annotation;
import kotlinx.metadata.internal.metadata.ProtoBuf$Class;
import kotlinx.metadata.internal.metadata.ProtoBuf$Constructor;
import kotlinx.metadata.internal.metadata.ProtoBuf$Function;
import kotlinx.metadata.internal.metadata.ProtoBuf$Property;
import kotlinx.metadata.internal.metadata.ProtoBuf$Type;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeAlias;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter;
import kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter;
import kotlinx.metadata.internal.metadata.deserialization.ProtoBufUtilKt;
import kotlinx.metadata.internal.metadata.jvm.JvmProtoBuf;
import kotlinx.metadata.internal.metadata.jvm.deserialization.JvmMemberSignature;
import kotlinx.metadata.internal.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlinx.metadata.internal.protobuf.GeneratedMessageLite;
import kotlinx.metadata.jvm.JvmClassExtensionVisitor;
import kotlinx.metadata.jvm.JvmConstructorExtensionVisitor;
import kotlinx.metadata.jvm.JvmFunctionExtensionVisitor;
import kotlinx.metadata.jvm.JvmMemberSignatureKt;
import kotlinx.metadata.jvm.JvmMethodSignature;
import kotlinx.metadata.jvm.JvmPropertyExtensionVisitor;
import kotlinx.metadata.jvm.JvmTypeExtensionVisitor;
import kotlinx.metadata.jvm.JvmTypeParameterExtensionVisitor;

/* JADX INFO: compiled from: JvmMetadataExtensions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001f\u0010 J \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u000f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u0012\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u0015\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u0018\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u001b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u001e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006!"}, d2 = {"Lkotlinx/metadata/jvm/impl/JvmMetadataExtensions;", "Lkotlinx/metadata/impl/extensions/MetadataExtensions;", "Lkotlinx/metadata/KmClassVisitor;", "v", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Class;", "proto", "Lkotlinx/metadata/impl/ReadContext;", "c", "", "readClassExtensions", "Lkotlinx/metadata/KmFunctionVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Function;", "readFunctionExtensions", "Lkotlinx/metadata/KmPropertyVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Property;", "readPropertyExtensions", "Lkotlinx/metadata/KmConstructorVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Constructor;", "readConstructorExtensions", "Lkotlinx/metadata/KmTypeParameterVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeParameter;", "readTypeParameterExtensions", "Lkotlinx/metadata/KmTypeVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Type;", "readTypeExtensions", "Lkotlinx/metadata/KmTypeAliasVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeAlias;", "readTypeAliasExtensions", "Lkotlinx/metadata/KmValueParameterVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$ValueParameter;", "readValueParameterExtensions", "<init>", "()V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public final class JvmMetadataExtensions implements MetadataExtensions {
    @Override // kotlinx.metadata.impl.extensions.MetadataExtensions
    public void readTypeAliasExtensions(KmTypeAliasVisitor v, ProtoBuf$TypeAlias proto, ReadContext c) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(c, "c");
    }

    @Override // kotlinx.metadata.impl.extensions.MetadataExtensions
    public void readValueParameterExtensions(KmValueParameterVisitor v, ProtoBuf$ValueParameter proto, ReadContext c) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(c, "c");
    }

    @Override // kotlinx.metadata.impl.extensions.MetadataExtensions
    public void readClassExtensions(KmClassVisitor v, ProtoBuf$Class proto, ReadContext c) {
        String str;
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(c, "c");
        KmClassExtensionVisitor kmClassExtensionVisitorVisitExtensions = v.visitExtensions(JvmClassExtensionVisitor.TYPE);
        JvmClassExtensionVisitor jvmClassExtensionVisitor = kmClassExtensionVisitorVisitExtensions instanceof JvmClassExtensionVisitor ? (JvmClassExtensionVisitor) kmClassExtensionVisitorVisitExtensions : null;
        if (jvmClassExtensionVisitor == null) {
            return;
        }
        GeneratedMessageLite.GeneratedExtension<ProtoBuf$Class, Integer> anonymousObjectOriginName = JvmProtoBuf.anonymousObjectOriginName;
        Intrinsics.checkNotNullExpressionValue(anonymousObjectOriginName, "anonymousObjectOriginName");
        Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull(proto, anonymousObjectOriginName);
        if (num != null) {
            jvmClassExtensionVisitor.visitAnonymousObjectOriginName(c.get(num.intValue()));
        }
        for (ProtoBuf$Property property : (List) proto.getExtension(JvmProtoBuf.classLocalVariable)) {
            int flags = property.getFlags();
            String str2 = c.get(property.getName());
            Intrinsics.checkNotNullExpressionValue(property, "property");
            KmPropertyVisitor kmPropertyVisitorVisitLocalDelegatedProperty = jvmClassExtensionVisitor.visitLocalDelegatedProperty(flags, str2, ReadersKt.getPropertyGetterFlags(property), ReadersKt.getPropertySetterFlags(property));
            if (kmPropertyVisitorVisitLocalDelegatedProperty != null) {
                ReadersKt.accept(property, kmPropertyVisitorVisitLocalDelegatedProperty, c);
            }
        }
        GeneratedMessageLite.GeneratedExtension<ProtoBuf$Class, Integer> classModuleName = JvmProtoBuf.classModuleName;
        Intrinsics.checkNotNullExpressionValue(classModuleName, "classModuleName");
        Integer num2 = (Integer) ProtoBufUtilKt.getExtensionOrNull(proto, classModuleName);
        if (num2 == null || (str = c.get(num2.intValue())) == null) {
            str = MediaTrack.ROLE_MAIN;
        }
        jvmClassExtensionVisitor.visitModuleName(str);
        GeneratedMessageLite.GeneratedExtension<ProtoBuf$Class, Integer> jvmClassFlags = JvmProtoBuf.jvmClassFlags;
        Intrinsics.checkNotNullExpressionValue(jvmClassFlags, "jvmClassFlags");
        Integer num3 = (Integer) ProtoBufUtilKt.getExtensionOrNull(proto, jvmClassFlags);
        if (num3 != null) {
            jvmClassExtensionVisitor.visitJvmFlags(num3.intValue());
        }
        jvmClassExtensionVisitor.visitEnd();
    }

    @Override // kotlinx.metadata.impl.extensions.MetadataExtensions
    public void readFunctionExtensions(KmFunctionVisitor v, ProtoBuf$Function proto, ReadContext c) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(c, "c");
        KmFunctionExtensionVisitor kmFunctionExtensionVisitorVisitExtensions = v.visitExtensions(JvmFunctionExtensionVisitor.TYPE);
        JvmFunctionExtensionVisitor jvmFunctionExtensionVisitor = kmFunctionExtensionVisitorVisitExtensions instanceof JvmFunctionExtensionVisitor ? (JvmFunctionExtensionVisitor) kmFunctionExtensionVisitorVisitExtensions : null;
        if (jvmFunctionExtensionVisitor == null) {
            return;
        }
        JvmMemberSignature.Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature(proto, c.getStrings(), c.getTypes());
        jvmFunctionExtensionVisitor.visit(jvmMethodSignature != null ? JvmMemberSignatureKt.wrapAsPublic(jvmMethodSignature) : null);
        GeneratedMessageLite.GeneratedExtension<ProtoBuf$Function, Integer> lambdaClassOriginName = JvmProtoBuf.lambdaClassOriginName;
        Intrinsics.checkNotNullExpressionValue(lambdaClassOriginName, "lambdaClassOriginName");
        Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull(proto, lambdaClassOriginName);
        if (num != null) {
            jvmFunctionExtensionVisitor.visitLambdaClassOriginName(c.get(num.intValue()));
        }
        jvmFunctionExtensionVisitor.visitEnd();
    }

    @Override // kotlinx.metadata.impl.extensions.MetadataExtensions
    public void readPropertyExtensions(KmPropertyVisitor v, ProtoBuf$Property proto, ReadContext c) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(c, "c");
        KmPropertyExtensionVisitor kmPropertyExtensionVisitorVisitExtensions = v.visitExtensions(JvmPropertyExtensionVisitor.TYPE);
        JvmPropertyExtensionVisitor jvmPropertyExtensionVisitor = kmPropertyExtensionVisitorVisitExtensions instanceof JvmPropertyExtensionVisitor ? (JvmPropertyExtensionVisitor) kmPropertyExtensionVisitorVisitExtensions : null;
        if (jvmPropertyExtensionVisitor == null) {
            return;
        }
        JvmMemberSignature.Field jvmFieldSignature$default = JvmProtoBufUtil.getJvmFieldSignature$default(JvmProtoBufUtil.INSTANCE, proto, c.getStrings(), c.getTypes(), false, 8, null);
        GeneratedMessageLite.GeneratedExtension<ProtoBuf$Property, JvmProtoBuf.JvmPropertySignature> propertySignature = JvmProtoBuf.propertySignature;
        Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
        JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(proto, propertySignature);
        JvmProtoBuf.JvmMethodSignature getter = (jvmPropertySignature == null || !jvmPropertySignature.hasGetter()) ? null : jvmPropertySignature.getGetter();
        JvmProtoBuf.JvmMethodSignature setter = (jvmPropertySignature == null || !jvmPropertySignature.hasSetter()) ? null : jvmPropertySignature.getSetter();
        Object extension = proto.getExtension(JvmProtoBuf.flags);
        Intrinsics.checkNotNullExpressionValue(extension, "proto.getExtension(JvmProtoBuf.flags)");
        jvmPropertyExtensionVisitor.visit(((Number) extension).intValue(), jvmFieldSignature$default != null ? JvmMemberSignatureKt.wrapAsPublic(jvmFieldSignature$default) : null, getter != null ? new JvmMethodSignature(c.get(getter.getName()), c.get(getter.getDesc())) : null, setter != null ? new JvmMethodSignature(c.get(setter.getName()), c.get(setter.getDesc())) : null);
        JvmProtoBuf.JvmMethodSignature syntheticMethod = (jvmPropertySignature == null || !jvmPropertySignature.hasSyntheticMethod()) ? null : jvmPropertySignature.getSyntheticMethod();
        jvmPropertyExtensionVisitor.visitSyntheticMethodForAnnotations(syntheticMethod != null ? new JvmMethodSignature(c.get(syntheticMethod.getName()), c.get(syntheticMethod.getDesc())) : null);
        JvmProtoBuf.JvmMethodSignature delegateMethod = (jvmPropertySignature == null || !jvmPropertySignature.hasDelegateMethod()) ? null : jvmPropertySignature.getDelegateMethod();
        jvmPropertyExtensionVisitor.visitSyntheticMethodForDelegate(delegateMethod != null ? new JvmMethodSignature(c.get(delegateMethod.getName()), c.get(delegateMethod.getDesc())) : null);
        jvmPropertyExtensionVisitor.visitEnd();
    }

    @Override // kotlinx.metadata.impl.extensions.MetadataExtensions
    public void readConstructorExtensions(KmConstructorVisitor v, ProtoBuf$Constructor proto, ReadContext c) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(c, "c");
        KmConstructorExtensionVisitor kmConstructorExtensionVisitorVisitExtensions = v.visitExtensions(JvmConstructorExtensionVisitor.TYPE);
        JvmConstructorExtensionVisitor jvmConstructorExtensionVisitor = kmConstructorExtensionVisitorVisitExtensions instanceof JvmConstructorExtensionVisitor ? (JvmConstructorExtensionVisitor) kmConstructorExtensionVisitorVisitExtensions : null;
        if (jvmConstructorExtensionVisitor == null) {
            return;
        }
        JvmMemberSignature.Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature(proto, c.getStrings(), c.getTypes());
        jvmConstructorExtensionVisitor.visit(jvmConstructorSignature != null ? JvmMemberSignatureKt.wrapAsPublic(jvmConstructorSignature) : null);
    }

    @Override // kotlinx.metadata.impl.extensions.MetadataExtensions
    public void readTypeParameterExtensions(KmTypeParameterVisitor v, ProtoBuf$TypeParameter proto, ReadContext c) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(c, "c");
        KmTypeParameterExtensionVisitor kmTypeParameterExtensionVisitorVisitExtensions = v.visitExtensions(JvmTypeParameterExtensionVisitor.TYPE);
        JvmTypeParameterExtensionVisitor jvmTypeParameterExtensionVisitor = kmTypeParameterExtensionVisitorVisitExtensions instanceof JvmTypeParameterExtensionVisitor ? (JvmTypeParameterExtensionVisitor) kmTypeParameterExtensionVisitorVisitExtensions : null;
        if (jvmTypeParameterExtensionVisitor == null) {
            return;
        }
        for (ProtoBuf$Annotation annotation : (List) proto.getExtension(JvmProtoBuf.typeParameterAnnotation)) {
            Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
            jvmTypeParameterExtensionVisitor.visitAnnotation(ReadUtilsKt.readAnnotation(annotation, c.getStrings()));
        }
        jvmTypeParameterExtensionVisitor.visitEnd();
    }

    @Override // kotlinx.metadata.impl.extensions.MetadataExtensions
    public void readTypeExtensions(KmTypeVisitor v, ProtoBuf$Type proto, ReadContext c) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(c, "c");
        KmTypeExtensionVisitor kmTypeExtensionVisitorVisitExtensions = v.visitExtensions(JvmTypeExtensionVisitor.TYPE);
        JvmTypeExtensionVisitor jvmTypeExtensionVisitor = kmTypeExtensionVisitorVisitExtensions instanceof JvmTypeExtensionVisitor ? (JvmTypeExtensionVisitor) kmTypeExtensionVisitorVisitExtensions : null;
        if (jvmTypeExtensionVisitor == null) {
            return;
        }
        Object extension = proto.getExtension(JvmProtoBuf.isRaw);
        Intrinsics.checkNotNullExpressionValue(extension, "proto.getExtension(JvmProtoBuf.isRaw)");
        jvmTypeExtensionVisitor.visit(((Boolean) extension).booleanValue());
        for (ProtoBuf$Annotation annotation : (List) proto.getExtension(JvmProtoBuf.typeAnnotation)) {
            Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
            jvmTypeExtensionVisitor.visitAnnotation(ReadUtilsKt.readAnnotation(annotation, c.getStrings()));
        }
        jvmTypeExtensionVisitor.visitEnd();
    }
}
