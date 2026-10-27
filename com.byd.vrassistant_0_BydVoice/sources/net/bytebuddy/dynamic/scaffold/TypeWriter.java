package net.bytebuddy.dynamic.scaffold;

import com.android.multidex.ClassPathElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver;
import net.bytebuddy.dynamic.scaffold.inline.RebaseImplementationTarget;
import net.bytebuddy.dynamic.scaffold.subclass.SubclassImplementationTarget;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.jar.asm.commons.ClassRemapper;
import net.bytebuddy.jar.asm.commons.SimpleRemapper;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeWriter<T> {
    public static final String DUMP_PROPERTY = "net.bytebuddy.dump";

    DynamicType.Unloaded<T> make(TypeResolutionStrategy.Resolved resolved);

    public interface FieldPool {
        Record target(FieldDescription fieldDescription);

        public interface Record {
            void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory);

            void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory);

            FieldDescription getField();

            FieldAttributeAppender getFieldAppender();

            boolean isImplicit();

            Object resolveDefault(Object obj);

            public static class ForImplicitField implements Record {
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public boolean isImplicit() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForImplicitField;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForImplicitField)) {
                        return false;
                    }
                    ForImplicitField forImplicitField = (ForImplicitField) obj;
                    if (!forImplicitField.canEqual(this)) {
                        return false;
                    }
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = forImplicitField.fieldDescription;
                    return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
                }

                public int hashCode() {
                    FieldDescription fieldDescription = this.fieldDescription;
                    return 59 + (fieldDescription == null ? 43 : fieldDescription.hashCode());
                }

                public ForImplicitField(FieldDescription fieldDescription) {
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public FieldDescription getField() {
                    return this.fieldDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public FieldAttributeAppender getFieldAppender() {
                    throw new IllegalStateException("An implicit field record does not expose a field appender: " + this);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public Object resolveDefault(Object obj) {
                    throw new IllegalStateException("An implicit field record does not expose a default value: " + this);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                    FieldVisitor fieldVisitorVisitField = classVisitor.visitField(this.fieldDescription.getActualModifiers(), this.fieldDescription.getInternalName(), this.fieldDescription.getDescriptor(), this.fieldDescription.getGenericSignature(), FieldDescription.NO_DEFAULT_VALUE);
                    if (fieldVisitorVisitField != null) {
                        FieldAttributeAppender.ForInstrumentedField forInstrumentedField = FieldAttributeAppender.ForInstrumentedField.INSTANCE;
                        FieldDescription fieldDescription = this.fieldDescription;
                        forInstrumentedField.apply(fieldVisitorVisitField, fieldDescription, factory.on(fieldDescription));
                        fieldVisitorVisitField.visitEnd();
                    }
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory) {
                    throw new IllegalStateException("An implicit field record is not intended for partial application: " + this);
                }
            }

            public static class ForExplicitField implements Record {
                private final FieldAttributeAppender attributeAppender;
                private final Object defaultValue;
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public boolean isImplicit() {
                    return false;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForExplicitField;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForExplicitField)) {
                        return false;
                    }
                    ForExplicitField forExplicitField = (ForExplicitField) obj;
                    if (!forExplicitField.canEqual(this)) {
                        return false;
                    }
                    FieldAttributeAppender fieldAttributeAppender = this.attributeAppender;
                    FieldAttributeAppender fieldAttributeAppender2 = forExplicitField.attributeAppender;
                    if (fieldAttributeAppender != null ? !fieldAttributeAppender.equals(fieldAttributeAppender2) : fieldAttributeAppender2 != null) {
                        return false;
                    }
                    Object obj2 = this.defaultValue;
                    Object obj3 = forExplicitField.defaultValue;
                    if (obj2 != null ? !obj2.equals(obj3) : obj3 != null) {
                        return false;
                    }
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = forExplicitField.fieldDescription;
                    return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
                }

                public int hashCode() {
                    FieldAttributeAppender fieldAttributeAppender = this.attributeAppender;
                    int iHashCode = fieldAttributeAppender == null ? 43 : fieldAttributeAppender.hashCode();
                    Object obj = this.defaultValue;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (obj == null ? 43 : obj.hashCode());
                    FieldDescription fieldDescription = this.fieldDescription;
                    return (iHashCode2 * 59) + (fieldDescription != null ? fieldDescription.hashCode() : 43);
                }

                public ForExplicitField(FieldAttributeAppender fieldAttributeAppender, Object obj, FieldDescription fieldDescription) {
                    this.attributeAppender = fieldAttributeAppender;
                    this.defaultValue = obj;
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public FieldDescription getField() {
                    return this.fieldDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public FieldAttributeAppender getFieldAppender() {
                    return this.attributeAppender;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public Object resolveDefault(Object obj) {
                    Object obj2 = this.defaultValue;
                    return obj2 == null ? obj : obj2;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                    FieldVisitor fieldVisitorVisitField = classVisitor.visitField(this.fieldDescription.getActualModifiers(), this.fieldDescription.getInternalName(), this.fieldDescription.getDescriptor(), this.fieldDescription.getGenericSignature(), resolveDefault(FieldDescription.NO_DEFAULT_VALUE));
                    if (fieldVisitorVisitField != null) {
                        FieldAttributeAppender fieldAttributeAppender = this.attributeAppender;
                        FieldDescription fieldDescription = this.fieldDescription;
                        fieldAttributeAppender.apply(fieldVisitorVisitField, fieldDescription, factory.on(fieldDescription));
                        fieldVisitorVisitField.visitEnd();
                    }
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory) {
                    FieldAttributeAppender fieldAttributeAppender = this.attributeAppender;
                    FieldDescription fieldDescription = this.fieldDescription;
                    fieldAttributeAppender.apply(fieldVisitor, fieldDescription, factory.on(fieldDescription));
                }
            }
        }
    }

    public interface MethodPool {
        Record target(MethodDescription methodDescription);

        public interface Record {
            void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory);

            void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory);

            void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory);

            ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context);

            void applyHead(MethodVisitor methodVisitor);

            MethodDescription getMethod();

            Sort getSort();

            Visibility getVisibility();

            Record prepend(ByteCodeAppender byteCodeAppender);

            public enum Sort {
                SKIPPED(false, false),
                DEFINED(true, false),
                IMPLEMENTED(true, true);

                private final boolean define;
                private final boolean implement;

                Sort(boolean z, boolean z2) {
                    this.define = z;
                    this.implement = z2;
                }

                public boolean isDefined() {
                    return this.define;
                }

                public boolean isImplemented() {
                    return this.implement;
                }
            }

            public static class ForNonImplementedMethod implements Record {
                private final MethodDescription methodDescription;

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForNonImplementedMethod;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForNonImplementedMethod)) {
                        return false;
                    }
                    ForNonImplementedMethod forNonImplementedMethod = (ForNonImplementedMethod) obj;
                    if (!forNonImplementedMethod.canEqual(this)) {
                        return false;
                    }
                    MethodDescription methodDescription = this.methodDescription;
                    MethodDescription methodDescription2 = forNonImplementedMethod.methodDescription;
                    return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
                }

                public int hashCode() {
                    MethodDescription methodDescription = this.methodDescription;
                    return 59 + (methodDescription == null ? 43 : methodDescription.hashCode());
                }

                public ForNonImplementedMethod(MethodDescription methodDescription) {
                    this.methodDescription = methodDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    throw new IllegalStateException("Cannot apply body for non-implemented method on " + this.methodDescription);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                    throw new IllegalStateException("Cannot apply code for non-implemented method on " + this.methodDescription);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyHead(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Cannot apply head for non-implemented method on " + this.methodDescription);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public MethodDescription getMethod() {
                    return this.methodDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Visibility getVisibility() {
                    return this.methodDescription.getVisibility();
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Sort getSort() {
                    return Sort.SKIPPED;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Record prepend(ByteCodeAppender byteCodeAppender) {
                    return new ForDefinedMethod.WithBody(this.methodDescription, new ByteCodeAppender.Compound(byteCodeAppender, new ByteCodeAppender.Simple(DefaultValue.of(this.methodDescription.getReturnType()), MethodReturn.of(this.methodDescription.getReturnType()))));
                }
            }

            public static abstract class ForDefinedMethod implements Record {
                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    MethodVisitor methodVisitorVisitMethod = classVisitor.visitMethod(getMethod().getActualModifiers(getSort().isImplemented(), getVisibility()), getMethod().getInternalName(), getMethod().getDescriptor(), getMethod().getGenericSignature(), getMethod().getExceptionTypes().asErasures().toInternalNames());
                    if (methodVisitorVisitMethod != null) {
                        ParameterList<?> parameters = getMethod().getParameters();
                        if (parameters.hasExplicitMetaData()) {
                            Iterator it = parameters.iterator();
                            while (it.hasNext()) {
                                ParameterDescription parameterDescription = (ParameterDescription) it.next();
                                methodVisitorVisitMethod.visitParameter(parameterDescription.getName(), parameterDescription.getModifiers());
                            }
                        }
                        applyHead(methodVisitorVisitMethod);
                        applyBody(methodVisitorVisitMethod, context, factory);
                        methodVisitorVisitMethod.visitEnd();
                    }
                }

                public static class WithBody extends ForDefinedMethod {
                    private final ByteCodeAppender byteCodeAppender;
                    private final MethodAttributeAppender methodAttributeAppender;
                    private final MethodDescription methodDescription;
                    private final Visibility visibility;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithBody;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithBody)) {
                            return false;
                        }
                        WithBody withBody = (WithBody) obj;
                        if (!withBody.canEqual(this)) {
                            return false;
                        }
                        MethodDescription methodDescription = this.methodDescription;
                        MethodDescription methodDescription2 = withBody.methodDescription;
                        if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                            return false;
                        }
                        ByteCodeAppender byteCodeAppender = this.byteCodeAppender;
                        ByteCodeAppender byteCodeAppender2 = withBody.byteCodeAppender;
                        if (byteCodeAppender != null ? !byteCodeAppender.equals(byteCodeAppender2) : byteCodeAppender2 != null) {
                            return false;
                        }
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        MethodAttributeAppender methodAttributeAppender2 = withBody.methodAttributeAppender;
                        if (methodAttributeAppender != null ? !methodAttributeAppender.equals(methodAttributeAppender2) : methodAttributeAppender2 != null) {
                            return false;
                        }
                        Visibility visibility = getVisibility();
                        Visibility visibility2 = withBody.getVisibility();
                        return visibility != null ? visibility.equals(visibility2) : visibility2 == null;
                    }

                    public int hashCode() {
                        MethodDescription methodDescription = this.methodDescription;
                        int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
                        ByteCodeAppender byteCodeAppender = this.byteCodeAppender;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (byteCodeAppender == null ? 43 : byteCodeAppender.hashCode());
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        int iHashCode3 = (iHashCode2 * 59) + (methodAttributeAppender == null ? 43 : methodAttributeAppender.hashCode());
                        Visibility visibility = getVisibility();
                        return (iHashCode3 * 59) + (visibility != null ? visibility.hashCode() : 43);
                    }

                    public WithBody(MethodDescription methodDescription, ByteCodeAppender byteCodeAppender) {
                        this(methodDescription, byteCodeAppender, MethodAttributeAppender.NoOp.INSTANCE, methodDescription.getVisibility());
                    }

                    public WithBody(MethodDescription methodDescription, ByteCodeAppender byteCodeAppender, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                        this.methodDescription = methodDescription;
                        this.byteCodeAppender = byteCodeAppender;
                        this.methodAttributeAppender = methodAttributeAppender;
                        this.visibility = visibility;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Sort getSort() {
                        return Sort.IMPLEMENTED;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Visibility getVisibility() {
                        return this.visibility;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                        methodVisitor.visitCode();
                        ByteCodeAppender.Size sizeApplyCode = applyCode(methodVisitor, context);
                        methodVisitor.visitMaxs(sizeApplyCode.getOperandStackSize(), sizeApplyCode.getLocalVariableSize());
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        MethodDescription methodDescription = this.methodDescription;
                        methodAttributeAppender.apply(methodVisitor, methodDescription, factory.on(methodDescription));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        return this.byteCodeAppender.apply(methodVisitor, context, this.methodDescription);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        return new WithBody(this.methodDescription, new ByteCodeAppender.Compound(byteCodeAppender, this.byteCodeAppender), this.methodAttributeAppender, this.visibility);
                    }
                }

                public static class WithoutBody extends ForDefinedMethod {
                    private final MethodAttributeAppender methodAttributeAppender;
                    private final MethodDescription methodDescription;
                    private final Visibility visibility;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithoutBody;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithoutBody)) {
                            return false;
                        }
                        WithoutBody withoutBody = (WithoutBody) obj;
                        if (!withoutBody.canEqual(this)) {
                            return false;
                        }
                        MethodDescription methodDescription = this.methodDescription;
                        MethodDescription methodDescription2 = withoutBody.methodDescription;
                        if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                            return false;
                        }
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        MethodAttributeAppender methodAttributeAppender2 = withoutBody.methodAttributeAppender;
                        if (methodAttributeAppender != null ? !methodAttributeAppender.equals(methodAttributeAppender2) : methodAttributeAppender2 != null) {
                            return false;
                        }
                        Visibility visibility = getVisibility();
                        Visibility visibility2 = withoutBody.getVisibility();
                        return visibility != null ? visibility.equals(visibility2) : visibility2 == null;
                    }

                    public int hashCode() {
                        MethodDescription methodDescription = this.methodDescription;
                        int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (methodAttributeAppender == null ? 43 : methodAttributeAppender.hashCode());
                        Visibility visibility = getVisibility();
                        return (iHashCode2 * 59) + (visibility != null ? visibility.hashCode() : 43);
                    }

                    public WithoutBody(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                        this.methodDescription = methodDescription;
                        this.methodAttributeAppender = methodAttributeAppender;
                        this.visibility = visibility;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Sort getSort() {
                        return Sort.DEFINED;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Visibility getVisibility() {
                        return this.visibility;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        MethodDescription methodDescription = this.methodDescription;
                        methodAttributeAppender.apply(methodVisitor, methodDescription, factory.on(methodDescription));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        throw new IllegalStateException("Cannot apply code for abstract method on " + this.methodDescription);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        throw new IllegalStateException("Cannot prepend code for abstract method on " + this.methodDescription);
                    }
                }

                public static class WithAnnotationDefaultValue extends ForDefinedMethod {
                    private final AnnotationValue<?, ?> annotationValue;
                    private final MethodAttributeAppender methodAttributeAppender;
                    private final MethodDescription methodDescription;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithAnnotationDefaultValue;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithAnnotationDefaultValue)) {
                            return false;
                        }
                        WithAnnotationDefaultValue withAnnotationDefaultValue = (WithAnnotationDefaultValue) obj;
                        if (!withAnnotationDefaultValue.canEqual(this)) {
                            return false;
                        }
                        MethodDescription methodDescription = this.methodDescription;
                        MethodDescription methodDescription2 = withAnnotationDefaultValue.methodDescription;
                        if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                            return false;
                        }
                        AnnotationValue<?, ?> annotationValue = this.annotationValue;
                        AnnotationValue<?, ?> annotationValue2 = withAnnotationDefaultValue.annotationValue;
                        if (annotationValue != null ? !annotationValue.equals(annotationValue2) : annotationValue2 != null) {
                            return false;
                        }
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        MethodAttributeAppender methodAttributeAppender2 = withAnnotationDefaultValue.methodAttributeAppender;
                        return methodAttributeAppender != null ? methodAttributeAppender.equals(methodAttributeAppender2) : methodAttributeAppender2 == null;
                    }

                    public int hashCode() {
                        MethodDescription methodDescription = this.methodDescription;
                        int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
                        AnnotationValue<?, ?> annotationValue = this.annotationValue;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (annotationValue == null ? 43 : annotationValue.hashCode());
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        return (iHashCode2 * 59) + (methodAttributeAppender != null ? methodAttributeAppender.hashCode() : 43);
                    }

                    public WithAnnotationDefaultValue(MethodDescription methodDescription, AnnotationValue<?, ?> annotationValue, MethodAttributeAppender methodAttributeAppender) {
                        this.methodDescription = methodDescription;
                        this.annotationValue = annotationValue;
                        this.methodAttributeAppender = methodAttributeAppender;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Sort getSort() {
                        return Sort.DEFINED;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Visibility getVisibility() {
                        return this.methodDescription.getVisibility();
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyHead(MethodVisitor methodVisitor) {
                        if (!this.methodDescription.isDefaultValue(this.annotationValue)) {
                            throw new IllegalStateException("Cannot set " + this.annotationValue + " as default for " + this.methodDescription);
                        }
                        AnnotationVisitor annotationVisitorVisitAnnotationDefault = methodVisitor.visitAnnotationDefault();
                        AnnotationAppender.Default.apply(annotationVisitorVisitAnnotationDefault, this.methodDescription.getReturnType().asErasure(), AnnotationAppender.NO_NAME, this.annotationValue.resolve());
                        annotationVisitorVisitAnnotationDefault.visitEnd();
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        MethodAttributeAppender methodAttributeAppender = this.methodAttributeAppender;
                        MethodDescription methodDescription = this.methodDescription;
                        methodAttributeAppender.apply(methodVisitor, methodDescription, factory.on(methodDescription));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        throw new IllegalStateException("Cannot apply attributes for default value on " + this.methodDescription);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        throw new IllegalStateException("Cannot apply code for default value on " + this.methodDescription);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        throw new IllegalStateException("Cannot prepend code for default value on " + this.methodDescription);
                    }
                }

                public static class OfVisibilityBridge extends ForDefinedMethod implements ByteCodeAppender {
                    private final MethodAttributeAppender attributeAppender;
                    private final MethodDescription bridgeTarget;
                    private final TypeDescription bridgeType;
                    private final MethodDescription visibilityBridge;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    protected boolean canEqual(Object obj) {
                        return obj instanceof OfVisibilityBridge;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof OfVisibilityBridge)) {
                            return false;
                        }
                        OfVisibilityBridge ofVisibilityBridge = (OfVisibilityBridge) obj;
                        if (!ofVisibilityBridge.canEqual(this)) {
                            return false;
                        }
                        MethodDescription methodDescription = this.visibilityBridge;
                        MethodDescription methodDescription2 = ofVisibilityBridge.visibilityBridge;
                        if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                            return false;
                        }
                        MethodDescription methodDescription3 = this.bridgeTarget;
                        MethodDescription methodDescription4 = ofVisibilityBridge.bridgeTarget;
                        if (methodDescription3 != null ? !methodDescription3.equals(methodDescription4) : methodDescription4 != null) {
                            return false;
                        }
                        TypeDescription typeDescription = this.bridgeType;
                        TypeDescription typeDescription2 = ofVisibilityBridge.bridgeType;
                        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                            return false;
                        }
                        MethodAttributeAppender methodAttributeAppender = this.attributeAppender;
                        MethodAttributeAppender methodAttributeAppender2 = ofVisibilityBridge.attributeAppender;
                        return methodAttributeAppender != null ? methodAttributeAppender.equals(methodAttributeAppender2) : methodAttributeAppender2 == null;
                    }

                    public int hashCode() {
                        MethodDescription methodDescription = this.visibilityBridge;
                        int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
                        MethodDescription methodDescription2 = this.bridgeTarget;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (methodDescription2 == null ? 43 : methodDescription2.hashCode());
                        TypeDescription typeDescription = this.bridgeType;
                        int iHashCode3 = (iHashCode2 * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                        MethodAttributeAppender methodAttributeAppender = this.attributeAppender;
                        return (iHashCode3 * 59) + (methodAttributeAppender != null ? methodAttributeAppender.hashCode() : 43);
                    }

                    protected OfVisibilityBridge(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, MethodAttributeAppender methodAttributeAppender) {
                        this.visibilityBridge = methodDescription;
                        this.bridgeTarget = methodDescription2;
                        this.bridgeType = typeDescription;
                        this.attributeAppender = methodAttributeAppender;
                    }

                    public static Record of(TypeDescription typeDescription, MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender) {
                        TypeDefinition superClass = null;
                        if (methodDescription.isDefaultMethod()) {
                            TypeDescription typeDescriptionAsErasure = methodDescription.getDeclaringType().asErasure();
                            for (TypeDescription typeDescription2 : typeDescription.getInterfaces().asErasures().filter(ElementMatchers.isSubTypeOf(typeDescriptionAsErasure))) {
                                if (superClass == null || typeDescriptionAsErasure.isAssignableTo(superClass.asErasure())) {
                                    superClass = typeDescription2;
                                }
                            }
                        }
                        if (superClass == null) {
                            superClass = typeDescription.getSuperClass();
                        }
                        return new OfVisibilityBridge(new VisibilityBridge(typeDescription, methodDescription), methodDescription, superClass.asErasure(), methodAttributeAppender);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public MethodDescription getMethod() {
                        return this.visibilityBridge;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Sort getSort() {
                        return Sort.IMPLEMENTED;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Visibility getVisibility() {
                        return this.bridgeTarget.getVisibility();
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        return new WithBody(this.visibilityBridge, new ByteCodeAppender.Compound(this, byteCodeAppender), this.attributeAppender, this.bridgeTarget.getVisibility());
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                        methodVisitor.visitCode();
                        ByteCodeAppender.Size sizeApplyCode = applyCode(methodVisitor, context);
                        methodVisitor.visitMaxs(sizeApplyCode.getOperandStackSize(), sizeApplyCode.getLocalVariableSize());
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        MethodAttributeAppender methodAttributeAppender = this.attributeAppender;
                        MethodDescription methodDescription = this.visibilityBridge;
                        methodAttributeAppender.apply(methodVisitor, methodDescription, factory.on(methodDescription));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        return apply(methodVisitor, context, this.visibilityBridge);
                    }

                    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        return new ByteCodeAppender.Simple(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), MethodInvocation.invoke(this.bridgeTarget).special(this.bridgeType), MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context, methodDescription);
                    }

                    protected static class VisibilityBridge extends MethodDescription.InDefinedShape.AbstractBase {
                        private final MethodDescription bridgeTarget;
                        private final TypeDescription instrumentedType;

                        protected VisibilityBridge(TypeDescription typeDescription, MethodDescription methodDescription) {
                            this.instrumentedType = typeDescription;
                            this.bridgeTarget = methodDescription;
                        }

                        @Override // net.bytebuddy.description.DeclaredByType
                        public TypeDescription getDeclaringType() {
                            return this.instrumentedType;
                        }

                        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                            return new ParameterList.Explicit.ForTypes(this, this.bridgeTarget.getParameters().asTypeList().asRawTypes());
                        }

                        @Override // net.bytebuddy.description.method.MethodDescription
                        public TypeDescription.Generic getReturnType() {
                            return this.bridgeTarget.getReturnType().asRawType();
                        }

                        @Override // net.bytebuddy.description.method.MethodDescription
                        public TypeList.Generic getExceptionTypes() {
                            return this.bridgeTarget.getExceptionTypes().asRawTypes();
                        }

                        @Override // net.bytebuddy.description.method.MethodDescription
                        public AnnotationValue<?, ?> getDefaultValue() {
                            return AnnotationValue.UNDEFINED;
                        }

                        @Override // net.bytebuddy.description.TypeVariableSource
                        public TypeList.Generic getTypeVariables() {
                            return new TypeList.Generic.Empty();
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return this.bridgeTarget.getDeclaredAnnotations();
                        }

                        @Override // net.bytebuddy.description.ModifierReviewable
                        public int getModifiers() {
                            return (this.bridgeTarget.getModifiers() | 4096 | 64) & (-257);
                        }

                        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                        public String getInternalName() {
                            return this.bridgeTarget.getName();
                        }
                    }
                }
            }

            public static class AccessBridgeWrapper implements Record {
                private final MethodAttributeAppender attributeAppender;
                private final MethodDescription bridgeTarget;
                private final Set<MethodDescription.TypeToken> bridgeTypes;
                private final Record delegate;
                private final TypeDescription instrumentedType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof AccessBridgeWrapper;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof AccessBridgeWrapper)) {
                        return false;
                    }
                    AccessBridgeWrapper accessBridgeWrapper = (AccessBridgeWrapper) obj;
                    if (!accessBridgeWrapper.canEqual(this)) {
                        return false;
                    }
                    Record record = this.delegate;
                    Record record2 = accessBridgeWrapper.delegate;
                    if (record != null ? !record.equals(record2) : record2 != null) {
                        return false;
                    }
                    TypeDescription typeDescription = this.instrumentedType;
                    TypeDescription typeDescription2 = accessBridgeWrapper.instrumentedType;
                    if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                        return false;
                    }
                    MethodDescription methodDescription = this.bridgeTarget;
                    MethodDescription methodDescription2 = accessBridgeWrapper.bridgeTarget;
                    if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                        return false;
                    }
                    Set<MethodDescription.TypeToken> set = this.bridgeTypes;
                    Set<MethodDescription.TypeToken> set2 = accessBridgeWrapper.bridgeTypes;
                    if (set != null ? !set.equals(set2) : set2 != null) {
                        return false;
                    }
                    MethodAttributeAppender methodAttributeAppender = this.attributeAppender;
                    MethodAttributeAppender methodAttributeAppender2 = accessBridgeWrapper.attributeAppender;
                    return methodAttributeAppender != null ? methodAttributeAppender.equals(methodAttributeAppender2) : methodAttributeAppender2 == null;
                }

                public int hashCode() {
                    Record record = this.delegate;
                    int iHashCode = record == null ? 43 : record.hashCode();
                    TypeDescription typeDescription = this.instrumentedType;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                    MethodDescription methodDescription = this.bridgeTarget;
                    int iHashCode3 = (iHashCode2 * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
                    Set<MethodDescription.TypeToken> set = this.bridgeTypes;
                    int iHashCode4 = (iHashCode3 * 59) + (set == null ? 43 : set.hashCode());
                    MethodAttributeAppender methodAttributeAppender = this.attributeAppender;
                    return (iHashCode4 * 59) + (methodAttributeAppender != null ? methodAttributeAppender.hashCode() : 43);
                }

                protected AccessBridgeWrapper(Record record, TypeDescription typeDescription, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, MethodAttributeAppender methodAttributeAppender) {
                    this.delegate = record;
                    this.instrumentedType = typeDescription;
                    this.bridgeTarget = methodDescription;
                    this.bridgeTypes = set;
                    this.attributeAppender = methodAttributeAppender;
                }

                public static Record of(Record record, TypeDescription typeDescription, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, MethodAttributeAppender methodAttributeAppender) {
                    HashSet hashSet = new HashSet();
                    for (MethodDescription.TypeToken typeToken : set) {
                        if (methodDescription.isBridgeCompatible(typeToken)) {
                            hashSet.add(typeToken);
                        }
                    }
                    return !hashSet.isEmpty() ? (!typeDescription.isInterface() || record.getSort().isImplemented()) ? new AccessBridgeWrapper(record, typeDescription, methodDescription, hashSet, methodAttributeAppender) : record : record;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Sort getSort() {
                    return this.delegate.getSort();
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public MethodDescription getMethod() {
                    return this.bridgeTarget;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Visibility getVisibility() {
                    return this.delegate.getVisibility();
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Record prepend(ByteCodeAppender byteCodeAppender) {
                    return new AccessBridgeWrapper(this.delegate.prepend(byteCodeAppender), this.instrumentedType, this.bridgeTarget, this.bridgeTypes, this.attributeAppender);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    this.delegate.apply(classVisitor, context, factory);
                    Iterator<MethodDescription.TypeToken> it = this.bridgeTypes.iterator();
                    while (it.hasNext()) {
                        AccessorBridge accessorBridge = new AccessorBridge(this.bridgeTarget, it.next(), this.instrumentedType);
                        BridgeTarget bridgeTarget = new BridgeTarget(this.bridgeTarget, this.instrumentedType);
                        MethodVisitor methodVisitorVisitMethod = classVisitor.visitMethod(accessorBridge.getActualModifiers(true, getVisibility()), accessorBridge.getInternalName(), accessorBridge.getDescriptor(), MethodDescription.NON_GENERIC_SIGNATURE, accessorBridge.getExceptionTypes().asErasures().toInternalNames());
                        if (methodVisitorVisitMethod != null) {
                            this.attributeAppender.apply(methodVisitorVisitMethod, accessorBridge, factory.on(this.instrumentedType));
                            methodVisitorVisitMethod.visitCode();
                            StackManipulation[] stackManipulationArr = new StackManipulation[4];
                            stackManipulationArr[0] = MethodVariableAccess.allArgumentsOf(accessorBridge).asBridgeOf(bridgeTarget).prependThisReference();
                            stackManipulationArr[1] = MethodInvocation.invoke((MethodDescription.InDefinedShape) bridgeTarget).virtual(this.instrumentedType);
                            stackManipulationArr[2] = bridgeTarget.getReturnType().asErasure().isAssignableTo(accessorBridge.getReturnType().asErasure()) ? StackManipulation.Trivial.INSTANCE : TypeCasting.to(accessorBridge.getReturnType().asErasure());
                            stackManipulationArr[3] = MethodReturn.of(accessorBridge.getReturnType());
                            ByteCodeAppender.Size sizeApply = new ByteCodeAppender.Simple(stackManipulationArr).apply(methodVisitorVisitMethod, context, accessorBridge);
                            methodVisitorVisitMethod.visitMaxs(sizeApply.getOperandStackSize(), sizeApply.getLocalVariableSize());
                            methodVisitorVisitMethod.visitEnd();
                        }
                    }
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyHead(MethodVisitor methodVisitor) {
                    this.delegate.applyHead(methodVisitor);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    this.delegate.applyBody(methodVisitor, context, factory);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                    this.delegate.applyAttributes(methodVisitor, factory);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                    return this.delegate.applyCode(methodVisitor, context);
                }

                protected static class AccessorBridge extends MethodDescription.InDefinedShape.AbstractBase {
                    private final MethodDescription bridgeTarget;
                    private final MethodDescription.TypeToken bridgeType;
                    private final TypeDescription instrumentedType;

                    protected AccessorBridge(MethodDescription methodDescription, MethodDescription.TypeToken typeToken, TypeDescription typeDescription) {
                        this.bridgeTarget = methodDescription;
                        this.bridgeType = typeToken;
                        this.instrumentedType = typeDescription;
                    }

                    @Override // net.bytebuddy.description.DeclaredByType
                    public TypeDescription getDeclaringType() {
                        return this.instrumentedType;
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                    public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                        return new ParameterList.Explicit.ForTypes(this, this.bridgeType.getParameterTypes());
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public TypeDescription.Generic getReturnType() {
                        return this.bridgeType.getReturnType().asGenericType();
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public TypeList.Generic getExceptionTypes() {
                        return this.bridgeTarget.getExceptionTypes().accept(TypeDescription.Generic.Visitor.TypeErasing.INSTANCE);
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public AnnotationValue<?, ?> getDefaultValue() {
                        return AnnotationValue.UNDEFINED;
                    }

                    @Override // net.bytebuddy.description.TypeVariableSource
                    public TypeList.Generic getTypeVariables() {
                        return new TypeList.Generic.Empty();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return new AnnotationList.Empty();
                    }

                    @Override // net.bytebuddy.description.ModifierReviewable
                    public int getModifiers() {
                        return (this.bridgeTarget.getModifiers() | 64 | 4096) & (-1281);
                    }

                    @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                    public String getInternalName() {
                        return this.bridgeTarget.getInternalName();
                    }
                }

                protected static class BridgeTarget extends MethodDescription.InDefinedShape.AbstractBase {
                    private final MethodDescription bridgeTarget;
                    private final TypeDescription instrumentedType;

                    protected BridgeTarget(MethodDescription methodDescription, TypeDescription typeDescription) {
                        this.bridgeTarget = methodDescription;
                        this.instrumentedType = typeDescription;
                    }

                    @Override // net.bytebuddy.description.DeclaredByType
                    public TypeDescription getDeclaringType() {
                        return this.instrumentedType;
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                    public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                        return new ParameterList.ForTokens(this, this.bridgeTarget.getParameters().asTokenList(ElementMatchers.is(this.instrumentedType)));
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public TypeDescription.Generic getReturnType() {
                        return this.bridgeTarget.getReturnType();
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public TypeList.Generic getExceptionTypes() {
                        return this.bridgeTarget.getExceptionTypes();
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public AnnotationValue<?, ?> getDefaultValue() {
                        return this.bridgeTarget.getDefaultValue();
                    }

                    @Override // net.bytebuddy.description.TypeVariableSource
                    public TypeList.Generic getTypeVariables() {
                        return this.bridgeTarget.getTypeVariables();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return this.bridgeTarget.getDeclaredAnnotations();
                    }

                    @Override // net.bytebuddy.description.ModifierReviewable
                    public int getModifiers() {
                        return this.bridgeTarget.getModifiers();
                    }

                    @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                    public String getInternalName() {
                        return this.bridgeTarget.getInternalName();
                    }
                }
            }
        }
    }

    public static abstract class Default<S> implements TypeWriter<S> {
        private static final String DUMP_FOLDER;
        protected final AnnotationRetention annotationRetention;
        protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
        protected final AsmVisitorWrapper asmVisitorWrapper;
        protected final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
        protected final List<? extends DynamicType> auxiliaryTypes;
        protected final ClassFileVersion classFileVersion;
        protected final FieldPool fieldPool;
        protected final FieldList<FieldDescription.InDefinedShape> fields;
        protected final Implementation.Context.Factory implementationContextFactory;
        protected final MethodList<?> instrumentedMethods;
        protected final TypeDescription instrumentedType;
        protected final LoadedTypeInitializer loadedTypeInitializer;
        protected final MethodList<?> methods;
        protected final TypeAttributeAppender typeAttributeAppender;
        protected final TypeInitializer typeInitializer;
        protected final TypePool typePool;
        protected final TypeValidation typeValidation;

        protected abstract Default<S>.UnresolvedType create(TypeInitializer typeInitializer);

        protected boolean canEqual(Object obj) {
            return obj instanceof Default;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Default)) {
                return false;
            }
            Default r5 = (Default) obj;
            if (!r5.canEqual(this)) {
                return false;
            }
            TypeDescription typeDescription = this.instrumentedType;
            TypeDescription typeDescription2 = r5.instrumentedType;
            if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                return false;
            }
            ClassFileVersion classFileVersion = this.classFileVersion;
            ClassFileVersion classFileVersion2 = r5.classFileVersion;
            if (classFileVersion != null ? !classFileVersion.equals(classFileVersion2) : classFileVersion2 != null) {
                return false;
            }
            FieldPool fieldPool = this.fieldPool;
            FieldPool fieldPool2 = r5.fieldPool;
            if (fieldPool != null ? !fieldPool.equals(fieldPool2) : fieldPool2 != null) {
                return false;
            }
            List<? extends DynamicType> list = this.auxiliaryTypes;
            List<? extends DynamicType> list2 = r5.auxiliaryTypes;
            if (list != null ? !list.equals(list2) : list2 != null) {
                return false;
            }
            FieldList<FieldDescription.InDefinedShape> fieldList = this.fields;
            FieldList<FieldDescription.InDefinedShape> fieldList2 = r5.fields;
            if (fieldList != null ? !fieldList.equals(fieldList2) : fieldList2 != null) {
                return false;
            }
            MethodList<?> methodList = this.methods;
            MethodList<?> methodList2 = r5.methods;
            if (methodList != null ? !methodList.equals(methodList2) : methodList2 != null) {
                return false;
            }
            MethodList<?> methodList3 = this.instrumentedMethods;
            MethodList<?> methodList4 = r5.instrumentedMethods;
            if (methodList3 != null ? !methodList3.equals(methodList4) : methodList4 != null) {
                return false;
            }
            LoadedTypeInitializer loadedTypeInitializer = this.loadedTypeInitializer;
            LoadedTypeInitializer loadedTypeInitializer2 = r5.loadedTypeInitializer;
            if (loadedTypeInitializer != null ? !loadedTypeInitializer.equals(loadedTypeInitializer2) : loadedTypeInitializer2 != null) {
                return false;
            }
            TypeInitializer typeInitializer = this.typeInitializer;
            TypeInitializer typeInitializer2 = r5.typeInitializer;
            if (typeInitializer != null ? !typeInitializer.equals(typeInitializer2) : typeInitializer2 != null) {
                return false;
            }
            TypeAttributeAppender typeAttributeAppender = this.typeAttributeAppender;
            TypeAttributeAppender typeAttributeAppender2 = r5.typeAttributeAppender;
            if (typeAttributeAppender != null ? !typeAttributeAppender.equals(typeAttributeAppender2) : typeAttributeAppender2 != null) {
                return false;
            }
            AsmVisitorWrapper asmVisitorWrapper = this.asmVisitorWrapper;
            AsmVisitorWrapper asmVisitorWrapper2 = r5.asmVisitorWrapper;
            if (asmVisitorWrapper != null ? !asmVisitorWrapper.equals(asmVisitorWrapper2) : asmVisitorWrapper2 != null) {
                return false;
            }
            AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
            AnnotationValueFilter.Factory factory2 = r5.annotationValueFilterFactory;
            if (factory != null ? !factory.equals(factory2) : factory2 != null) {
                return false;
            }
            AnnotationRetention annotationRetention = this.annotationRetention;
            AnnotationRetention annotationRetention2 = r5.annotationRetention;
            if (annotationRetention != null ? !annotationRetention.equals(annotationRetention2) : annotationRetention2 != null) {
                return false;
            }
            AuxiliaryType.NamingStrategy namingStrategy = this.auxiliaryTypeNamingStrategy;
            AuxiliaryType.NamingStrategy namingStrategy2 = r5.auxiliaryTypeNamingStrategy;
            if (namingStrategy != null ? !namingStrategy.equals(namingStrategy2) : namingStrategy2 != null) {
                return false;
            }
            Implementation.Context.Factory factory3 = this.implementationContextFactory;
            Implementation.Context.Factory factory4 = r5.implementationContextFactory;
            if (factory3 != null ? !factory3.equals(factory4) : factory4 != null) {
                return false;
            }
            TypeValidation typeValidation = this.typeValidation;
            TypeValidation typeValidation2 = r5.typeValidation;
            if (typeValidation != null ? !typeValidation.equals(typeValidation2) : typeValidation2 != null) {
                return false;
            }
            TypePool typePool = this.typePool;
            TypePool typePool2 = r5.typePool;
            return typePool != null ? typePool.equals(typePool2) : typePool2 == null;
        }

        public int hashCode() {
            TypeDescription typeDescription = this.instrumentedType;
            int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
            ClassFileVersion classFileVersion = this.classFileVersion;
            int iHashCode2 = ((iHashCode + 59) * 59) + (classFileVersion == null ? 43 : classFileVersion.hashCode());
            FieldPool fieldPool = this.fieldPool;
            int iHashCode3 = (iHashCode2 * 59) + (fieldPool == null ? 43 : fieldPool.hashCode());
            List<? extends DynamicType> list = this.auxiliaryTypes;
            int iHashCode4 = (iHashCode3 * 59) + (list == null ? 43 : list.hashCode());
            FieldList<FieldDescription.InDefinedShape> fieldList = this.fields;
            int iHashCode5 = (iHashCode4 * 59) + (fieldList == null ? 43 : fieldList.hashCode());
            MethodList<?> methodList = this.methods;
            int iHashCode6 = (iHashCode5 * 59) + (methodList == null ? 43 : methodList.hashCode());
            MethodList<?> methodList2 = this.instrumentedMethods;
            int iHashCode7 = (iHashCode6 * 59) + (methodList2 == null ? 43 : methodList2.hashCode());
            LoadedTypeInitializer loadedTypeInitializer = this.loadedTypeInitializer;
            int iHashCode8 = (iHashCode7 * 59) + (loadedTypeInitializer == null ? 43 : loadedTypeInitializer.hashCode());
            TypeInitializer typeInitializer = this.typeInitializer;
            int iHashCode9 = (iHashCode8 * 59) + (typeInitializer == null ? 43 : typeInitializer.hashCode());
            TypeAttributeAppender typeAttributeAppender = this.typeAttributeAppender;
            int iHashCode10 = (iHashCode9 * 59) + (typeAttributeAppender == null ? 43 : typeAttributeAppender.hashCode());
            AsmVisitorWrapper asmVisitorWrapper = this.asmVisitorWrapper;
            int iHashCode11 = (iHashCode10 * 59) + (asmVisitorWrapper == null ? 43 : asmVisitorWrapper.hashCode());
            AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
            int iHashCode12 = (iHashCode11 * 59) + (factory == null ? 43 : factory.hashCode());
            AnnotationRetention annotationRetention = this.annotationRetention;
            int iHashCode13 = (iHashCode12 * 59) + (annotationRetention == null ? 43 : annotationRetention.hashCode());
            AuxiliaryType.NamingStrategy namingStrategy = this.auxiliaryTypeNamingStrategy;
            int iHashCode14 = (iHashCode13 * 59) + (namingStrategy == null ? 43 : namingStrategy.hashCode());
            Implementation.Context.Factory factory2 = this.implementationContextFactory;
            int iHashCode15 = (iHashCode14 * 59) + (factory2 == null ? 43 : factory2.hashCode());
            TypeValidation typeValidation = this.typeValidation;
            int iHashCode16 = (iHashCode15 * 59) + (typeValidation == null ? 43 : typeValidation.hashCode());
            TypePool typePool = this.typePool;
            return (iHashCode16 * 59) + (typePool != null ? typePool.hashCode() : 43);
        }

        static {
            String str;
            try {
                str = (String) AccessController.doPrivileged(new GetSystemPropertyAction(TypeWriter.DUMP_PROPERTY));
            } catch (RuntimeException unused) {
                str = null;
            }
            DUMP_FOLDER = str;
        }

        protected Default(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, TypePool typePool) {
            this.instrumentedType = typeDescription;
            this.classFileVersion = classFileVersion;
            this.fieldPool = fieldPool;
            this.auxiliaryTypes = list;
            this.fields = fieldList;
            this.methods = methodList;
            this.instrumentedMethods = methodList2;
            this.loadedTypeInitializer = loadedTypeInitializer;
            this.typeInitializer = typeInitializer;
            this.typeAttributeAppender = typeAttributeAppender;
            this.asmVisitorWrapper = asmVisitorWrapper;
            this.auxiliaryTypeNamingStrategy = namingStrategy;
            this.annotationValueFilterFactory = factory;
            this.annotationRetention = annotationRetention;
            this.implementationContextFactory = factory2;
            this.typeValidation = typeValidation;
            this.typePool = typePool;
        }

        public static <U> TypeWriter<U> forCreation(MethodRegistry.Compiled compiled, FieldPool fieldPool, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, TypePool typePool) {
            return new ForCreation(compiled.getInstrumentedType(), classFileVersion, fieldPool, compiled, Collections.emptyList(), compiled.getInstrumentedType().getDeclaredFields(), compiled.getMethods(), compiled.getInstrumentedMethods(), compiled.getLoadedTypeInitializer(), compiled.getTypeInitializer(), typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, typePool);
        }

        public static <U> TypeWriter<U> forRedefinition(MethodRegistry.Prepared prepared, FieldPool fieldPool, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, TypePool typePool, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            return new ForInlining(prepared.getInstrumentedType(), classFileVersion, fieldPool, prepared, SubclassImplementationTarget.Factory.LEVEL_TYPE, Collections.emptyList(), prepared.getInstrumentedType().getDeclaredFields(), prepared.getMethods(), prepared.getInstrumentedMethods(), prepared.getLoadedTypeInitializer(), prepared.getTypeInitializer(), typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, typePool, typeDescription, classFileLocator, MethodRebaseResolver.Disabled.INSTANCE);
        }

        public static <U> TypeWriter<U> forRebasing(MethodRegistry.Prepared prepared, FieldPool fieldPool, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, TypePool typePool, TypeDescription typeDescription, ClassFileLocator classFileLocator, MethodRebaseResolver methodRebaseResolver) {
            return new ForInlining(prepared.getInstrumentedType(), classFileVersion, fieldPool, prepared, new RebaseImplementationTarget.Factory(methodRebaseResolver), methodRebaseResolver.getAuxiliaryTypes(), prepared.getInstrumentedType().getDeclaredFields(), prepared.getMethods(), prepared.getInstrumentedMethods(), prepared.getLoadedTypeInitializer(), prepared.getTypeInitializer(), typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, typePool, typeDescription, classFileLocator, methodRebaseResolver);
        }

        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter
        public DynamicType.Unloaded<S> make(TypeResolutionStrategy.Resolved resolved) {
            Default<S>.UnresolvedType unresolvedTypeCreate = create(resolved.injectedInto(this.typeInitializer));
            String str = DUMP_FOLDER;
            if (str != null) {
                try {
                    AccessController.doPrivileged(new ClassDumpAction(str, this.instrumentedType, unresolvedTypeCreate.getBinaryRepresentation()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return unresolvedTypeCreate.toDynamicType(resolved);
        }

        protected class UnresolvedType {
            private final List<? extends DynamicType> auxiliaryTypes;
            private final byte[] binaryRepresentation;

            protected UnresolvedType(byte[] bArr, List<? extends DynamicType> list) {
                this.binaryRepresentation = bArr;
                this.auxiliaryTypes = list;
            }

            protected DynamicType.Unloaded<S> toDynamicType(TypeResolutionStrategy.Resolved resolved) {
                return new DynamicType.Default.Unloaded(Default.this.instrumentedType, this.binaryRepresentation, Default.this.loadedTypeInitializer, CompoundList.of((List) Default.this.auxiliaryTypes, (List) this.auxiliaryTypes), resolved);
            }

            protected byte[] getBinaryRepresentation() {
                return this.binaryRepresentation;
            }

            private Default getOuter() {
                return Default.this;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                UnresolvedType unresolvedType = (UnresolvedType) obj;
                return Arrays.equals(this.binaryRepresentation, unresolvedType.binaryRepresentation) && Default.this.equals(unresolvedType.getOuter()) && this.auxiliaryTypes.equals(unresolvedType.auxiliaryTypes);
            }

            public int hashCode() {
                return (((Arrays.hashCode(this.binaryRepresentation) * 31) + this.auxiliaryTypes.hashCode()) * 31) + Default.this.hashCode();
            }
        }

        protected static class ValidatingClassVisitor extends ClassVisitor {
            private static final FieldVisitor IGNORE_FIELD = null;
            private static final MethodVisitor IGNORE_METHOD = null;
            private static final String NO_PARAMETERS = "()";
            private static final String RETURNS_VOID = "V";
            private static final String STRING_DESCRIPTOR = "Ljava/lang/String;";
            private Constraint constraint;

            protected ValidatingClassVisitor(ClassVisitor classVisitor) {
                super(Opcodes.ASM6, classVisitor);
            }

            protected static ClassVisitor of(ClassVisitor classVisitor, TypeValidation typeValidation) {
                return typeValidation.isEnabled() ? new ValidatingClassVisitor(classVisitor) : classVisitor;
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                ClassFileVersion classFileVersionOfMinorMajor = ClassFileVersion.ofMinorMajor(i);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Constraint.ForClassFileVersion(classFileVersionOfMinorMajor));
                if (str.endsWith("/package-info")) {
                    arrayList.add(Constraint.ForPackageType.INSTANCE);
                } else if ((i2 & 8192) != 0) {
                    if (!classFileVersionOfMinorMajor.isAtLeast(ClassFileVersion.JAVA_V5)) {
                        throw new IllegalStateException("Cannot define an annotation type for class file version " + classFileVersionOfMinorMajor);
                    }
                    arrayList.add(classFileVersionOfMinorMajor.isAtLeast(ClassFileVersion.JAVA_V8) ? Constraint.ForAnnotation.JAVA_8 : Constraint.ForAnnotation.CLASSIC);
                } else if ((i2 & 512) != 0) {
                    arrayList.add(classFileVersionOfMinorMajor.isAtLeast(ClassFileVersion.JAVA_V8) ? Constraint.ForInterface.JAVA_8 : Constraint.ForInterface.CLASSIC);
                } else if ((i2 & 1024) != 0) {
                    arrayList.add(Constraint.ForClass.ABSTRACT);
                } else {
                    arrayList.add(Constraint.ForClass.MANIFEST);
                }
                Constraint.Compound compound = new Constraint.Compound(arrayList);
                this.constraint = compound;
                compound.assertType(i2, strArr != null, str2 != null);
                super.visit(i, i2, str, str2, str3, strArr);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public AnnotationVisitor visitAnnotation(String str, boolean z) {
                this.constraint.assertAnnotation();
                return super.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                this.constraint.assertTypeAnnotation();
                return super.visitTypeAnnotation(i, typePath, str, z);
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x0045  */
            @Override // net.bytebuddy.jar.asm.ClassVisitor
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public net.bytebuddy.jar.asm.FieldVisitor visitField(int r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.Object r13) {
                /*
                    Method dump skipped, instruction units count: 256
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.visitField(int, java.lang.String, java.lang.String, java.lang.String, java.lang.Object):net.bytebuddy.jar.asm.FieldVisitor");
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                this.constraint.assertMethod(str, (i & 1024) != 0, (i & 1) != 0, (i & 2) != 0, (i & 8) != 0, (str.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME) || str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME) || (i & 10) != 0) ? false : true, str.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME), !str2.startsWith(NO_PARAMETERS) || str2.endsWith(RETURNS_VOID), str3 != null);
                MethodVisitor methodVisitorVisitMethod = super.visitMethod(i, str, str2, str3, strArr);
                return methodVisitorVisitMethod == null ? IGNORE_METHOD : new ValidatingMethodVisitor(methodVisitorVisitMethod, str);
            }

            protected interface Constraint {
                void assertAnnotation();

                void assertDefaultMethodCall();

                void assertDefaultValue(String str);

                void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4);

                void assertHandleInConstantPool();

                void assertInvokeDynamic();

                void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8);

                void assertMethodTypeInConstantPool();

                void assertSubRoutine();

                void assertType(int i, boolean z, boolean z2);

                void assertTypeAnnotation();

                void assertTypeInConstantPool();

                public enum ForClass implements Constraint {
                    MANIFEST(true),
                    ABSTRACT(false);

                    private final boolean manifestType;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertType(int i, boolean z, boolean z2) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeInConstantPool() {
                    }

                    ForClass(boolean z) {
                        this.manifestType = z;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (z && this.manifestType) {
                            throw new IllegalStateException("Cannot define abstract method '" + str + "' for non-abstract class");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultValue(String str) {
                        throw new IllegalStateException("Cannot define default value for '" + str + "' for non-annotation type");
                    }
                }

                public enum ForPackageType implements Constraint {
                    INSTANCE;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultValue(String str) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        throw new IllegalStateException("Cannot define a field for a package description type");
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        throw new IllegalStateException("Cannot define a method for a package description type");
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertType(int i, boolean z, boolean z2) {
                        if (i != 5632) {
                            throw new IllegalStateException("A package description type must define 5632 as modifier");
                        }
                        if (z) {
                            throw new IllegalStateException("Cannot implement interface for package type");
                        }
                    }
                }

                public enum ForInterface implements Constraint {
                    CLASSIC(true),
                    JAVA_8(false);

                    private final boolean classic;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertType(int i, boolean z, boolean z2) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeInConstantPool() {
                    }

                    ForInterface(boolean z) {
                        this.classic = z;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (!z2 || !z || !z3) {
                            throw new IllegalStateException("Cannot only define public, static, final field '" + str + "' for interface type");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            return;
                        }
                        if (z6) {
                            throw new IllegalStateException("Cannot define constructor for interface type");
                        }
                        boolean z9 = this.classic;
                        if (z9 && !z2) {
                            throw new IllegalStateException("Cannot define non-public method '" + str + "' for interface type");
                        }
                        if (z9 && !z5) {
                            throw new IllegalStateException("Cannot define non-virtual method '" + str + "' for a pre-Java 8 interface type");
                        }
                        if (z9 && !z) {
                            throw new IllegalStateException("Cannot define default method '" + str + "' for pre-Java 8 interface type");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultValue(String str) {
                        throw new IllegalStateException("Cannot define default value for '" + str + "' for non-annotation type");
                    }
                }

                public enum ForAnnotation implements Constraint {
                    CLASSIC(true),
                    JAVA_8(false);

                    private final boolean classic;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultValue(String str) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeInConstantPool() {
                    }

                    ForAnnotation(boolean z) {
                        this.classic = z;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (!z2 || !z || !z3) {
                            throw new IllegalStateException("Cannot only define public, static, final field '" + str + "' for interface type");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            return;
                        }
                        if (z6) {
                            throw new IllegalStateException("Cannot define constructor for interface type");
                        }
                        if (this.classic && !z5) {
                            throw new IllegalStateException("Cannot define non-virtual method '" + str + "' for a pre-Java 8 annotation type");
                        }
                        if (!z4 && z7) {
                            throw new IllegalStateException("Cannot define method '" + str + "' with the given signature as an annotation type method");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertType(int i, boolean z, boolean z2) {
                        if ((i & 512) == 0) {
                            throw new IllegalStateException("Cannot define annotation type without interface modifier");
                        }
                    }
                }

                public static class ForClassFileVersion implements Constraint {
                    private final ClassFileVersion classFileVersion;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultValue(String str) {
                    }

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForClassFileVersion;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForClassFileVersion)) {
                            return false;
                        }
                        ForClassFileVersion forClassFileVersion = (ForClassFileVersion) obj;
                        if (!forClassFileVersion.canEqual(this)) {
                            return false;
                        }
                        ClassFileVersion classFileVersion = this.classFileVersion;
                        ClassFileVersion classFileVersion2 = forClassFileVersion.classFileVersion;
                        return classFileVersion != null ? classFileVersion.equals(classFileVersion2) : classFileVersion2 == null;
                    }

                    public int hashCode() {
                        ClassFileVersion classFileVersion = this.classFileVersion;
                        return 59 + (classFileVersion == null ? 43 : classFileVersion.hashCode());
                    }

                    protected ForClassFileVersion(ClassFileVersion classFileVersion) {
                        this.classFileVersion = classFileVersion;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertType(int i, boolean z, boolean z2) {
                        if ((i & 8192) != 0 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define annotation type for class file version " + this.classFileVersion);
                        }
                        if (z2 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define a generic type for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (z4 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define generic field '" + str + "' for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (z8 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define generic method '" + str + "' for class file version " + this.classFileVersion);
                        }
                        if (!z5 && z) {
                            throw new IllegalStateException("Cannot define static or non-virtual method '" + str + "' to be abstract");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertAnnotation() {
                        if (!this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write annotations for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeAnnotation() {
                        if (!this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write type annotations for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultMethodCall() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V8)) {
                            throw new IllegalStateException("Cannot invoke default method for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeInConstantPool() {
                        if (!this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write type to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethodTypeInConstantPool() {
                        if (!this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write method type to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertHandleInConstantPool() {
                        if (!this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write method handle to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertInvokeDynamic() {
                        if (!this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write invoke dynamic instruction for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertSubRoutine() {
                        if (!this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V6)) {
                            throw new IllegalStateException("Cannot write subroutine for class file version " + this.classFileVersion);
                        }
                    }
                }

                public static class Compound implements Constraint {
                    private final List<Constraint> constraints = new ArrayList();

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Compound;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Compound)) {
                            return false;
                        }
                        Compound compound = (Compound) obj;
                        if (!compound.canEqual(this)) {
                            return false;
                        }
                        List<Constraint> list = this.constraints;
                        List<Constraint> list2 = compound.constraints;
                        return list != null ? list.equals(list2) : list2 == null;
                    }

                    public int hashCode() {
                        List<Constraint> list = this.constraints;
                        return 59 + (list == null ? 43 : list.hashCode());
                    }

                    public Compound(List<? extends Constraint> list) {
                        for (Constraint constraint : list) {
                            if (constraint instanceof Compound) {
                                this.constraints.addAll(((Compound) constraint).constraints);
                            } else {
                                this.constraints.add(constraint);
                            }
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertType(int i, boolean z, boolean z2) {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertType(i, z, z2);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertField(str, z, z2, z3, z4);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertMethod(str, z, z2, z3, z4, z5, z6, z7, z8);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultValue(String str) {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertDefaultValue(str);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultMethodCall() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertDefaultMethodCall();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertAnnotation() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertAnnotation();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeAnnotation() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertTypeAnnotation();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeInConstantPool() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertTypeInConstantPool();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethodTypeInConstantPool() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertMethodTypeInConstantPool();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertHandleInConstantPool() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertHandleInConstantPool();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertInvokeDynamic() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertInvokeDynamic();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertSubRoutine() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertSubRoutine();
                        }
                    }
                }
            }

            protected class ValidatingFieldVisitor extends FieldVisitor {
                protected ValidatingFieldVisitor(FieldVisitor fieldVisitor) {
                    super(Opcodes.ASM6, fieldVisitor);
                }

                @Override // net.bytebuddy.jar.asm.FieldVisitor
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    ValidatingClassVisitor.this.constraint.assertAnnotation();
                    return super.visitAnnotation(str, z);
                }
            }

            protected class ValidatingMethodVisitor extends MethodVisitor {
                private final String name;

                protected ValidatingMethodVisitor(MethodVisitor methodVisitor, String str) {
                    super(Opcodes.ASM6, methodVisitor);
                    this.name = str;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    ValidatingClassVisitor.this.constraint.assertAnnotation();
                    return super.visitAnnotation(str, z);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitAnnotationDefault() {
                    ValidatingClassVisitor.this.constraint.assertDefaultValue(this.name);
                    return super.visitAnnotationDefault();
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitLdcInsn(Object obj) {
                    if (obj instanceof Type) {
                        switch (((Type) obj).getSort()) {
                            case 9:
                            case 10:
                                ValidatingClassVisitor.this.constraint.assertTypeInConstantPool();
                                break;
                            case 11:
                                ValidatingClassVisitor.this.constraint.assertMethodTypeInConstantPool();
                                break;
                        }
                    } else if (obj instanceof Handle) {
                        ValidatingClassVisitor.this.constraint.assertHandleInConstantPool();
                    }
                    super.visitLdcInsn(obj);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
                    if (z && i == 183) {
                        ValidatingClassVisitor.this.constraint.assertDefaultMethodCall();
                    }
                    super.visitMethodInsn(i, str, str2, str3, z);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
                    ValidatingClassVisitor.this.constraint.assertInvokeDynamic();
                    super.visitInvokeDynamicInsn(str, str2, handle, objArr);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitJumpInsn(int i, Label label) {
                    if (i == 168) {
                        ValidatingClassVisitor.this.constraint.assertSubRoutine();
                    }
                    super.visitJumpInsn(i, label);
                }
            }
        }

        protected static class FrameComputingClassWriter extends ClassWriter {
            private final TypePool typePool;

            protected FrameComputingClassWriter(int i, TypePool typePool) {
                super(i);
                this.typePool = typePool;
            }

            protected FrameComputingClassWriter(ClassReader classReader, int i, TypePool typePool) {
                super(classReader, i);
                this.typePool = typePool;
            }

            @Override // net.bytebuddy.jar.asm.ClassWriter
            protected String getCommonSuperClass(String str, String str2) {
                TypeDescription typeDescriptionResolve = this.typePool.describe(str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH)).resolve();
                TypeDescription typeDescriptionResolve2 = this.typePool.describe(str2.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH)).resolve();
                if (typeDescriptionResolve.isAssignableFrom(typeDescriptionResolve2)) {
                    return typeDescriptionResolve.getInternalName();
                }
                if (typeDescriptionResolve.isAssignableTo(typeDescriptionResolve2)) {
                    return typeDescriptionResolve2.getInternalName();
                }
                if (typeDescriptionResolve.isInterface() || typeDescriptionResolve2.isInterface()) {
                    return TypeDescription.OBJECT.getInternalName();
                }
                do {
                    typeDescriptionResolve = typeDescriptionResolve.getSuperClass().asErasure();
                } while (!typeDescriptionResolve.isAssignableFrom(typeDescriptionResolve2));
                return typeDescriptionResolve.getInternalName();
            }
        }

        public static class ForInlining<U> extends Default<U> {
            private static final AnnotationVisitor IGNORE_ANNOTATION = null;
            private static final FieldVisitor IGNORE_FIELD = null;
            private static final MethodVisitor IGNORE_METHOD = null;
            private static final String NO_SUPER_TYPE = null;
            private final ClassFileLocator classFileLocator;
            private final Implementation.Target.Factory implementationTargetFactory;
            private final MethodRebaseResolver methodRebaseResolver;
            private final MethodRegistry.Prepared methodRegistry;
            private final TypeDescription originalType;

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            protected boolean canEqual(Object obj) {
                return obj instanceof ForInlining;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForInlining)) {
                    return false;
                }
                ForInlining forInlining = (ForInlining) obj;
                if (!forInlining.canEqual(this) || !super.equals(obj)) {
                    return false;
                }
                MethodRegistry.Prepared prepared = this.methodRegistry;
                MethodRegistry.Prepared prepared2 = forInlining.methodRegistry;
                if (prepared != null ? !prepared.equals(prepared2) : prepared2 != null) {
                    return false;
                }
                Implementation.Target.Factory factory = this.implementationTargetFactory;
                Implementation.Target.Factory factory2 = forInlining.implementationTargetFactory;
                if (factory != null ? !factory.equals(factory2) : factory2 != null) {
                    return false;
                }
                TypeDescription typeDescription = this.originalType;
                TypeDescription typeDescription2 = forInlining.originalType;
                if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                    return false;
                }
                ClassFileLocator classFileLocator = this.classFileLocator;
                ClassFileLocator classFileLocator2 = forInlining.classFileLocator;
                if (classFileLocator != null ? !classFileLocator.equals(classFileLocator2) : classFileLocator2 != null) {
                    return false;
                }
                MethodRebaseResolver methodRebaseResolver = this.methodRebaseResolver;
                MethodRebaseResolver methodRebaseResolver2 = forInlining.methodRebaseResolver;
                return methodRebaseResolver != null ? methodRebaseResolver.equals(methodRebaseResolver2) : methodRebaseResolver2 == null;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            public int hashCode() {
                int iHashCode = super.hashCode() + 59;
                MethodRegistry.Prepared prepared = this.methodRegistry;
                int iHashCode2 = (iHashCode * 59) + (prepared == null ? 43 : prepared.hashCode());
                Implementation.Target.Factory factory = this.implementationTargetFactory;
                int iHashCode3 = (iHashCode2 * 59) + (factory == null ? 43 : factory.hashCode());
                TypeDescription typeDescription = this.originalType;
                int iHashCode4 = (iHashCode3 * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                ClassFileLocator classFileLocator = this.classFileLocator;
                int iHashCode5 = (iHashCode4 * 59) + (classFileLocator == null ? 43 : classFileLocator.hashCode());
                MethodRebaseResolver methodRebaseResolver = this.methodRebaseResolver;
                return (iHashCode5 * 59) + (methodRebaseResolver != null ? methodRebaseResolver.hashCode() : 43);
            }

            protected ForInlining(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, MethodRegistry.Prepared prepared, Implementation.Target.Factory factory, List<DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory2, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory3, TypeValidation typeValidation, TypePool typePool, TypeDescription typeDescription2, ClassFileLocator classFileLocator, MethodRebaseResolver methodRebaseResolver) {
                super(typeDescription, classFileVersion, fieldPool, list, fieldList, methodList, methodList2, loadedTypeInitializer, typeInitializer, typeAttributeAppender, asmVisitorWrapper, factory2, annotationRetention, namingStrategy, factory3, typeValidation, typePool);
                this.methodRegistry = prepared;
                this.implementationTargetFactory = factory;
                this.originalType = typeDescription2;
                this.classFileLocator = classFileLocator;
                this.methodRebaseResolver = methodRebaseResolver;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            protected Default<U>.UnresolvedType create(TypeInitializer typeInitializer) {
                try {
                    int iMergeWriter = this.asmVisitorWrapper.mergeWriter(0);
                    int iMergeReader = this.asmVisitorWrapper.mergeReader(0);
                    ClassReader classReader = new ClassReader(this.classFileLocator.locate(this.originalType.getName()).resolve());
                    FrameComputingClassWriter frameComputingClassWriter = new FrameComputingClassWriter(classReader, iMergeWriter, this.typePool);
                    ContextRegistry contextRegistry = new ContextRegistry();
                    classReader.accept(writeTo(ValidatingClassVisitor.of(frameComputingClassWriter, this.typeValidation), typeInitializer, contextRegistry, iMergeWriter, iMergeReader), iMergeReader);
                    return new UnresolvedType(frameComputingClassWriter.toByteArray(), contextRegistry.getAuxiliaryTypes());
                } catch (IOException e) {
                    throw new RuntimeException("The class file could not be written", e);
                }
            }

            private ClassVisitor writeTo(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2) {
                RedefinitionClassVisitor redefinitionClassVisitor = new RedefinitionClassVisitor(classVisitor, typeInitializer, contextRegistry, i, i2);
                return this.originalType.getName().equals(this.instrumentedType.getName()) ? redefinitionClassVisitor : new ClassRemapper(redefinitionClassVisitor, new SimpleRemapper(this.originalType.getInternalName(), this.instrumentedType.getInternalName()));
            }

            protected interface InitializationHandler {
                void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView);

                public static class Creating extends TypeInitializer.Drain.Default implements InitializationHandler {
                    protected Creating(TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory) {
                        super(typeDescription, methodPool, factory);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler
                    public void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView) {
                        extractableView.drain(this, classVisitor, this.annotationValueFilterFactory);
                    }
                }

                public static abstract class Appending extends MethodVisitor implements InitializationHandler, TypeInitializer.Drain {
                    protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
                    protected final FrameWriter frameWriter;
                    protected final TypeDescription instrumentedType;
                    protected int localVariableLength;
                    protected final MethodPool.Record record;
                    protected int stackSize;

                    protected abstract void onComplete(Implementation.Context context);

                    protected abstract void onEnd();

                    protected abstract void onStart();

                    protected Appending(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                        super(Opcodes.ASM6, methodVisitor);
                        this.instrumentedType = typeDescription;
                        this.record = record;
                        this.annotationValueFilterFactory = factory;
                        if (!z) {
                            this.frameWriter = FrameWriter.NoOp.INSTANCE;
                        } else if (z2) {
                            this.frameWriter = FrameWriter.Expanding.INSTANCE;
                        } else {
                            this.frameWriter = new FrameWriter.Active();
                        }
                    }

                    protected static InitializationHandler of(boolean z, MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z2, boolean z3) {
                        if (z) {
                            return withDrain(methodVisitor, typeDescription, methodPool, factory, z2, z3);
                        }
                        return withoutDrain(methodVisitor, typeDescription, methodPool, factory, z2, z3);
                    }

                    private static WithDrain withDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                        MethodPool.Record recordTarget = methodPool.target(new MethodDescription.Latent.TypeInitializer(typeDescription));
                        return recordTarget.getSort().isImplemented() ? new WithDrain.WithActiveRecord(methodVisitor, typeDescription, recordTarget, factory, z, z2) : new WithDrain.WithoutActiveRecord(methodVisitor, typeDescription, recordTarget, factory, z, z2);
                    }

                    private static WithoutDrain withoutDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                        MethodPool.Record recordTarget = methodPool.target(new MethodDescription.Latent.TypeInitializer(typeDescription));
                        return recordTarget.getSort().isImplemented() ? new WithoutDrain.WithActiveRecord(methodVisitor, typeDescription, recordTarget, factory, z, z2) : new WithoutDrain.WithoutActiveRecord(methodVisitor, typeDescription, recordTarget, factory);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public void visitCode() {
                        this.record.applyAttributes(this.mv, this.annotationValueFilterFactory);
                        super.visitCode();
                        onStart();
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                        super.visitFrame(i, i2, objArr, i3, objArr2);
                        this.frameWriter.onFrame(i, i2);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public void visitMaxs(int i, int i2) {
                        this.stackSize = i;
                        this.localVariableLength = i2;
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public void visitEnd() {
                        onEnd();
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer.Drain
                    public void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context) {
                        ByteCodeAppender.Size sizeApply = typeInitializer.apply(this.mv, context, new MethodDescription.Latent.TypeInitializer(this.instrumentedType));
                        this.stackSize = Math.max(this.stackSize, sizeApply.getOperandStackSize());
                        this.localVariableLength = Math.max(this.localVariableLength, sizeApply.getLocalVariableSize());
                        onComplete(context);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler
                    public void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView) {
                        extractableView.drain(this, classVisitor, this.annotationValueFilterFactory);
                        this.mv.visitMaxs(this.stackSize, this.localVariableLength);
                        this.mv.visitEnd();
                    }

                    protected interface FrameWriter {
                        public static final Object[] EMPTY = new Object[0];

                        public enum NoOp implements FrameWriter {
                            INSTANCE;

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending.FrameWriter
                            public void emitFrame(MethodVisitor methodVisitor) {
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending.FrameWriter
                            public void onFrame(int i, int i2) {
                            }
                        }

                        void emitFrame(MethodVisitor methodVisitor);

                        void onFrame(int i, int i2);

                        public enum Expanding implements FrameWriter {
                            INSTANCE;

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending.FrameWriter
                            public void onFrame(int i, int i2) {
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending.FrameWriter
                            public void emitFrame(MethodVisitor methodVisitor) {
                                methodVisitor.visitFrame(-1, EMPTY.length, EMPTY, EMPTY.length, EMPTY);
                            }
                        }

                        public static class Active implements FrameWriter {
                            private int currentLocalVariableLength;

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending.FrameWriter
                            public void onFrame(int i, int i2) {
                                if (i == -1 || i == 0) {
                                    this.currentLocalVariableLength = i2;
                                    return;
                                }
                                if (i == 1) {
                                    this.currentLocalVariableLength += i2;
                                } else if (i == 2) {
                                    this.currentLocalVariableLength -= i2;
                                } else if (i != 3 && i != 4) {
                                    throw new IllegalStateException("Unexpected frame type: " + i);
                                }
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending.FrameWriter
                            public void emitFrame(MethodVisitor methodVisitor) {
                                int i = this.currentLocalVariableLength;
                                if (i == 0) {
                                    methodVisitor.visitFrame(3, EMPTY.length, EMPTY, EMPTY.length, EMPTY);
                                } else if (i > 3) {
                                    methodVisitor.visitFrame(0, EMPTY.length, EMPTY, EMPTY.length, EMPTY);
                                } else {
                                    methodVisitor.visitFrame(2, i, EMPTY, EMPTY.length, EMPTY);
                                }
                                this.currentLocalVariableLength = 0;
                            }
                        }
                    }

                    protected static abstract class WithoutDrain extends Appending {
                        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending
                        protected void onEnd() {
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending
                        protected void onStart() {
                        }

                        protected WithoutDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                            super(methodVisitor, typeDescription, record, factory, z, z2);
                        }

                        protected static class WithoutActiveRecord extends WithoutDrain {
                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending
                            protected void onComplete(Implementation.Context context) {
                            }

                            protected WithoutActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory) {
                                super(methodVisitor, typeDescription, record, factory, false, false);
                            }
                        }

                        protected static class WithActiveRecord extends WithoutDrain {
                            private final Label label;

                            protected WithActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                super(methodVisitor, typeDescription, record, factory, z, z2);
                                this.label = new Label();
                            }

                            @Override // net.bytebuddy.jar.asm.MethodVisitor
                            public void visitInsn(int i) {
                                if (i == 177) {
                                    this.mv.visitJumpInsn(167, this.label);
                                } else {
                                    super.visitInsn(i);
                                }
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending
                            protected void onComplete(Implementation.Context context) {
                                this.mv.visitLabel(this.label);
                                this.frameWriter.emitFrame(this.mv);
                                ByteCodeAppender.Size sizeApplyCode = this.record.applyCode(this.mv, context);
                                this.stackSize = Math.max(this.stackSize, sizeApplyCode.getOperandStackSize());
                                this.localVariableLength = Math.max(this.localVariableLength, sizeApplyCode.getLocalVariableSize());
                            }
                        }
                    }

                    protected static abstract class WithDrain extends Appending {
                        protected final Label appended;
                        protected final Label original;

                        protected abstract void afterComplete(Implementation.Context context);

                        protected WithDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                            super(methodVisitor, typeDescription, record, factory, z, z2);
                            this.appended = new Label();
                            this.original = new Label();
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending
                        protected void onStart() {
                            this.mv.visitJumpInsn(167, this.appended);
                            this.mv.visitLabel(this.original);
                            this.frameWriter.emitFrame(this.mv);
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending
                        protected void onEnd() {
                            this.mv.visitLabel(this.appended);
                            this.frameWriter.emitFrame(this.mv);
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending
                        protected void onComplete(Implementation.Context context) {
                            this.mv.visitJumpInsn(167, this.original);
                            afterComplete(context);
                        }

                        protected static class WithoutActiveRecord extends WithDrain {
                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending.WithDrain
                            protected void afterComplete(Implementation.Context context) {
                            }

                            protected WithoutActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                super(methodVisitor, typeDescription, record, factory, z, z2);
                            }
                        }

                        protected static class WithActiveRecord extends WithDrain {
                            private final Label label;

                            protected WithActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                super(methodVisitor, typeDescription, record, factory, z, z2);
                                this.label = new Label();
                            }

                            @Override // net.bytebuddy.jar.asm.MethodVisitor
                            public void visitInsn(int i) {
                                if (i == 177) {
                                    this.mv.visitJumpInsn(167, this.label);
                                } else {
                                    super.visitInsn(i);
                                }
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.InitializationHandler.Appending.WithDrain
                            protected void afterComplete(Implementation.Context context) {
                                this.mv.visitLabel(this.label);
                                this.frameWriter.emitFrame(this.mv);
                                ByteCodeAppender.Size sizeApplyCode = this.record.applyCode(this.mv, context);
                                this.stackSize = Math.max(this.stackSize, sizeApplyCode.getOperandStackSize());
                                this.localVariableLength = Math.max(this.localVariableLength, sizeApplyCode.getLocalVariableSize());
                            }
                        }
                    }
                }
            }

            protected static class ContextRegistry {
                private Implementation.Context.ExtractableView implementationContext;

                protected ContextRegistry() {
                }

                public void setImplementationContext(Implementation.Context.ExtractableView extractableView) {
                    this.implementationContext = extractableView;
                }

                public List<DynamicType> getAuxiliaryTypes() {
                    return this.implementationContext.getAuxiliaryTypes();
                }
            }

            protected class RedefinitionClassVisitor extends ClassVisitor {
                private final ContextRegistry contextRegistry;
                private final LinkedHashMap<String, FieldDescription> declarableFields;
                private final LinkedHashMap<String, MethodDescription> declarableMethods;
                private Implementation.Context.ExtractableView implementationContext;
                private InitializationHandler initializationHandler;
                private MethodPool methodPool;
                private final int readerFlags;
                private final TypeInitializer typeInitializer;
                private final int writerFlags;

                protected RedefinitionClassVisitor(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2) {
                    super(Opcodes.ASM6, classVisitor);
                    this.typeInitializer = typeInitializer;
                    this.contextRegistry = contextRegistry;
                    this.writerFlags = i;
                    this.readerFlags = i2;
                    this.declarableFields = new LinkedHashMap<>();
                    for (FieldDescription fieldDescription : ForInlining.this.fields) {
                        this.declarableFields.put(fieldDescription.getInternalName() + fieldDescription.getDescriptor(), fieldDescription);
                    }
                    this.declarableMethods = new LinkedHashMap<>();
                    Iterator it = ForInlining.this.instrumentedMethods.iterator();
                    while (it.hasNext()) {
                        MethodDescription methodDescription = (MethodDescription) it.next();
                        this.declarableMethods.put(methodDescription.getInternalName() + methodDescription.getDescriptor(), methodDescription);
                    }
                }

                @Override // net.bytebuddy.jar.asm.ClassVisitor
                public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                    String internalName;
                    ClassFileVersion classFileVersionOfMinorMajor = ClassFileVersion.ofMinorMajor(i);
                    this.methodPool = ForInlining.this.methodRegistry.compile(ForInlining.this.implementationTargetFactory, classFileVersionOfMinorMajor);
                    this.initializationHandler = new InitializationHandler.Creating(ForInlining.this.instrumentedType, this.methodPool, ForInlining.this.annotationValueFilterFactory);
                    Implementation.Context.ExtractableView extractableViewMake = ForInlining.this.implementationContextFactory.make(ForInlining.this.instrumentedType, ForInlining.this.auxiliaryTypeNamingStrategy, this.typeInitializer, classFileVersionOfMinorMajor, ForInlining.this.classFileVersion);
                    this.implementationContext = extractableViewMake;
                    this.contextRegistry.setImplementationContext(extractableViewMake);
                    this.cv = ForInlining.this.asmVisitorWrapper.wrap(ForInlining.this.instrumentedType, this.cv, this.implementationContext, ForInlining.this.typePool, ForInlining.this.fields, ForInlining.this.methods, this.writerFlags, this.readerFlags);
                    int i3 = 0;
                    int actualModifiers = ForInlining.this.instrumentedType.getActualModifiers(((i2 & 32) == 0 || ForInlining.this.instrumentedType.isInterface()) ? false : true);
                    if ((i2 & 16) != 0 && ForInlining.this.instrumentedType.isAnonymousClass()) {
                        i3 = 16;
                    }
                    int i4 = actualModifiers | i3;
                    String internalName2 = ForInlining.this.instrumentedType.getInternalName();
                    if (!TypeDescription.AbstractBase.RAW_TYPES) {
                        str2 = ForInlining.this.instrumentedType.getGenericSignature();
                    }
                    String str4 = str2;
                    if (ForInlining.this.instrumentedType.getSuperClass() == null) {
                        internalName = ForInlining.this.instrumentedType.isInterface() ? TypeDescription.OBJECT.getInternalName() : ForInlining.NO_SUPER_TYPE;
                    } else {
                        internalName = ForInlining.this.instrumentedType.getSuperClass().asErasure().getInternalName();
                    }
                    super.visit(i, i4, internalName2, str4, internalName, ForInlining.this.instrumentedType.getInterfaces().asErasures().toInternalNames());
                    ForInlining.this.typeAttributeAppender.apply(this.cv, ForInlining.this.instrumentedType, ForInlining.this.annotationValueFilterFactory.on(ForInlining.this.instrumentedType));
                }

                @Override // net.bytebuddy.jar.asm.ClassVisitor
                public void visitInnerClass(String str, String str2, String str3, int i) {
                    if (str.equals(ForInlining.this.instrumentedType.getInternalName())) {
                        i = ForInlining.this.instrumentedType.getModifiers();
                    }
                    super.visitInnerClass(str, str2, str3, i);
                }

                @Override // net.bytebuddy.jar.asm.ClassVisitor
                public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                    if (!ForInlining.this.annotationRetention.isEnabled()) {
                        return ForInlining.IGNORE_ANNOTATION;
                    }
                    return super.visitTypeAnnotation(i, typePath, str, z);
                }

                @Override // net.bytebuddy.jar.asm.ClassVisitor
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    if (!ForInlining.this.annotationRetention.isEnabled()) {
                        return ForInlining.IGNORE_ANNOTATION;
                    }
                    return super.visitAnnotation(str, z);
                }

                @Override // net.bytebuddy.jar.asm.ClassVisitor
                public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
                    FieldDescription fieldDescriptionRemove = this.declarableFields.remove(str + str2);
                    if (fieldDescriptionRemove != null) {
                        FieldPool.Record recordTarget = ForInlining.this.fieldPool.target(fieldDescriptionRemove);
                        if (!recordTarget.isImplicit()) {
                            return redefine(recordTarget, obj, str3);
                        }
                    }
                    return super.visitField(i, str, str2, str3, obj);
                }

                protected FieldVisitor redefine(FieldPool.Record record, Object obj, String str) {
                    FieldDescription field = record.getField();
                    int actualModifiers = field.getActualModifiers();
                    String internalName = field.getInternalName();
                    String descriptor = field.getDescriptor();
                    if (!TypeDescription.AbstractBase.RAW_TYPES) {
                        str = field.getGenericSignature();
                    }
                    FieldVisitor fieldVisitorVisitField = super.visitField(actualModifiers, internalName, descriptor, str, record.resolveDefault(obj));
                    return fieldVisitorVisitField == null ? ForInlining.IGNORE_FIELD : new AttributeObtainingFieldVisitor(fieldVisitorVisitField, record);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // net.bytebuddy.jar.asm.ClassVisitor
                public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                    if (str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                        MethodVisitor methodVisitorVisitMethod = super.visitMethod(i, str, str2, str3, strArr);
                        if (methodVisitorVisitMethod == null) {
                            return ForInlining.IGNORE_METHOD;
                        }
                        InitializationHandler initializationHandlerOf = InitializationHandler.Appending.of(this.implementationContext.isEnabled(), methodVisitorVisitMethod, ForInlining.this.instrumentedType, this.methodPool, ForInlining.this.annotationValueFilterFactory, (this.writerFlags & 2) == 0 && this.implementationContext.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V6), (this.readerFlags & 8) != 0);
                        this.initializationHandler = initializationHandlerOf;
                        return (MethodVisitor) initializationHandlerOf;
                    }
                    MethodDescription methodDescriptionRemove = this.declarableMethods.remove(str + str2);
                    if (methodDescriptionRemove == null) {
                        return super.visitMethod(i, str, str2, str3, strArr);
                    }
                    return redefine(methodDescriptionRemove, (i & 1024) != 0, str3);
                }

                protected MethodVisitor redefine(MethodDescription methodDescription, boolean z, String str) {
                    MethodPool.Record recordTarget = this.methodPool.target(methodDescription);
                    if (!recordTarget.getSort().isDefined()) {
                        int actualModifiers = methodDescription.getActualModifiers();
                        String internalName = methodDescription.getInternalName();
                        String descriptor = methodDescription.getDescriptor();
                        if (!TypeDescription.AbstractBase.RAW_TYPES) {
                            str = methodDescription.getGenericSignature();
                        }
                        return super.visitMethod(actualModifiers, internalName, descriptor, str, methodDescription.getExceptionTypes().asErasures().toInternalNames());
                    }
                    MethodDescription method = recordTarget.getMethod();
                    MethodVisitor methodVisitorVisitMethod = super.visitMethod(ModifierContributor.Resolver.of(Collections.singleton(recordTarget.getVisibility())).resolve(method.getActualModifiers(recordTarget.getSort().isImplemented())), method.getInternalName(), method.getDescriptor(), TypeDescription.AbstractBase.RAW_TYPES ? str : method.getGenericSignature(), method.getExceptionTypes().asErasures().toInternalNames());
                    if (methodVisitorVisitMethod == null) {
                        return ForInlining.IGNORE_METHOD;
                    }
                    if (z) {
                        return new AttributeObtainingMethodVisitor(methodVisitorVisitMethod, recordTarget);
                    }
                    if (methodDescription.isNative()) {
                        MethodRebaseResolver.Resolution resolutionResolve = ForInlining.this.methodRebaseResolver.resolve(method.asDefined());
                        if (resolutionResolve.isRebased()) {
                            int actualModifiers2 = resolutionResolve.getResolvedMethod().getActualModifiers();
                            String internalName2 = resolutionResolve.getResolvedMethod().getInternalName();
                            String descriptor2 = resolutionResolve.getResolvedMethod().getDescriptor();
                            if (!TypeDescription.AbstractBase.RAW_TYPES) {
                                str = method.getGenericSignature();
                            }
                            MethodVisitor methodVisitorVisitMethod2 = super.visitMethod(actualModifiers2, internalName2, descriptor2, str, resolutionResolve.getResolvedMethod().getExceptionTypes().asErasures().toInternalNames());
                            if (methodVisitorVisitMethod2 != null) {
                                methodVisitorVisitMethod2.visitEnd();
                            }
                        }
                        return new AttributeObtainingMethodVisitor(methodVisitorVisitMethod, recordTarget);
                    }
                    return new CodePreservingMethodVisitor(methodVisitorVisitMethod, recordTarget, ForInlining.this.methodRebaseResolver.resolve(method.asDefined()));
                }

                @Override // net.bytebuddy.jar.asm.ClassVisitor
                public void visitEnd() {
                    Iterator<FieldDescription> it = this.declarableFields.values().iterator();
                    while (it.hasNext()) {
                        ForInlining.this.fieldPool.target(it.next()).apply(this.cv, ForInlining.this.annotationValueFilterFactory);
                    }
                    Iterator<MethodDescription> it2 = this.declarableMethods.values().iterator();
                    while (it2.hasNext()) {
                        this.methodPool.target(it2.next()).apply(this.cv, this.implementationContext, ForInlining.this.annotationValueFilterFactory);
                    }
                    this.initializationHandler.complete(this.cv, this.implementationContext);
                    super.visitEnd();
                }

                protected class AttributeObtainingFieldVisitor extends FieldVisitor {
                    private final FieldPool.Record record;

                    protected AttributeObtainingFieldVisitor(FieldVisitor fieldVisitor, FieldPool.Record record) {
                        super(Opcodes.ASM6, fieldVisitor);
                        this.record = record;
                    }

                    @Override // net.bytebuddy.jar.asm.FieldVisitor
                    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                        if (!ForInlining.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return super.visitTypeAnnotation(i, typePath, str, z);
                    }

                    @Override // net.bytebuddy.jar.asm.FieldVisitor
                    public AnnotationVisitor visitAnnotation(String str, boolean z) {
                        if (!ForInlining.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return super.visitAnnotation(str, z);
                    }

                    @Override // net.bytebuddy.jar.asm.FieldVisitor
                    public void visitEnd() {
                        this.record.apply(this.fv, ForInlining.this.annotationValueFilterFactory);
                        super.visitEnd();
                    }
                }

                protected class CodePreservingMethodVisitor extends MethodVisitor {
                    private final MethodVisitor actualMethodVisitor;
                    private final MethodPool.Record record;
                    private final MethodRebaseResolver.Resolution resolution;

                    protected CodePreservingMethodVisitor(MethodVisitor methodVisitor, MethodPool.Record record, MethodRebaseResolver.Resolution resolution) {
                        super(Opcodes.ASM6, methodVisitor);
                        this.actualMethodVisitor = methodVisitor;
                        this.record = record;
                        this.resolution = resolution;
                        record.applyHead(methodVisitor);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public AnnotationVisitor visitAnnotationDefault() {
                        return ForInlining.IGNORE_ANNOTATION;
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                        if (!ForInlining.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return super.visitTypeAnnotation(i, typePath, str, z);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public AnnotationVisitor visitAnnotation(String str, boolean z) {
                        if (!ForInlining.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return super.visitAnnotation(str, z);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                        if (!ForInlining.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return super.visitParameterAnnotation(i, str, z);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public void visitCode() {
                        MethodVisitor methodVisitorVisitMethod;
                        this.record.applyBody(this.actualMethodVisitor, RedefinitionClassVisitor.this.implementationContext, ForInlining.this.annotationValueFilterFactory);
                        this.actualMethodVisitor.visitEnd();
                        if (!this.resolution.isRebased()) {
                            methodVisitorVisitMethod = ForInlining.IGNORE_METHOD;
                        } else {
                            methodVisitorVisitMethod = RedefinitionClassVisitor.this.cv.visitMethod(this.resolution.getResolvedMethod().getActualModifiers(), this.resolution.getResolvedMethod().getInternalName(), this.resolution.getResolvedMethod().getDescriptor(), this.resolution.getResolvedMethod().getGenericSignature(), this.resolution.getResolvedMethod().getExceptionTypes().asErasures().toInternalNames());
                        }
                        this.mv = methodVisitorVisitMethod;
                        super.visitCode();
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public void visitMaxs(int i, int i2) {
                        super.visitMaxs(i, Math.max(i2, this.resolution.getResolvedMethod().getStackSize()));
                    }
                }

                protected class AttributeObtainingMethodVisitor extends MethodVisitor {
                    private final MethodVisitor actualMethodVisitor;
                    private final MethodPool.Record record;

                    protected AttributeObtainingMethodVisitor(MethodVisitor methodVisitor, MethodPool.Record record) {
                        super(Opcodes.ASM6, methodVisitor);
                        this.actualMethodVisitor = methodVisitor;
                        this.record = record;
                        record.applyHead(methodVisitor);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public AnnotationVisitor visitAnnotationDefault() {
                        return ForInlining.IGNORE_ANNOTATION;
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                        if (!ForInlining.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return super.visitTypeAnnotation(i, typePath, str, z);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public AnnotationVisitor visitAnnotation(String str, boolean z) {
                        if (!ForInlining.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return super.visitAnnotation(str, z);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                        if (!ForInlining.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return super.visitParameterAnnotation(i, str, z);
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public void visitCode() {
                        this.mv = ForInlining.IGNORE_METHOD;
                    }

                    @Override // net.bytebuddy.jar.asm.MethodVisitor
                    public void visitEnd() {
                        this.record.applyBody(this.actualMethodVisitor, RedefinitionClassVisitor.this.implementationContext, ForInlining.this.annotationValueFilterFactory);
                        this.actualMethodVisitor.visitEnd();
                    }
                }
            }
        }

        public static class ForCreation<U> extends Default<U> {
            private final MethodPool methodPool;

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            protected boolean canEqual(Object obj) {
                return obj instanceof ForCreation;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForCreation)) {
                    return false;
                }
                ForCreation forCreation = (ForCreation) obj;
                if (!forCreation.canEqual(this) || !super.equals(obj)) {
                    return false;
                }
                MethodPool methodPool = this.methodPool;
                MethodPool methodPool2 = forCreation.methodPool;
                return methodPool != null ? methodPool.equals(methodPool2) : methodPool2 == null;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            public int hashCode() {
                int iHashCode = super.hashCode() + 59;
                MethodPool methodPool = this.methodPool;
                return (iHashCode * 59) + (methodPool == null ? 43 : methodPool.hashCode());
            }

            protected ForCreation(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, MethodPool methodPool, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, TypePool typePool) {
                super(typeDescription, classFileVersion, fieldPool, list, fieldList, methodList, methodList2, loadedTypeInitializer, typeInitializer, typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, typePool);
                this.methodPool = methodPool;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            protected Default<U>.UnresolvedType create(TypeInitializer typeInitializer) {
                int iMergeWriter = this.asmVisitorWrapper.mergeWriter(0);
                FrameComputingClassWriter frameComputingClassWriter = new FrameComputingClassWriter(iMergeWriter, this.typePool);
                Implementation.Context.ExtractableView extractableViewMake = this.implementationContextFactory.make(this.instrumentedType, this.auxiliaryTypeNamingStrategy, typeInitializer, this.classFileVersion, this.classFileVersion);
                ClassVisitor classVisitorWrap = this.asmVisitorWrapper.wrap(this.instrumentedType, ValidatingClassVisitor.of(frameComputingClassWriter, this.typeValidation), extractableViewMake, this.typePool, this.fields, this.methods, iMergeWriter, this.asmVisitorWrapper.mergeReader(0));
                classVisitorWrap.visit(this.classFileVersion.getMinorMajorVersion(), this.instrumentedType.getActualModifiers(!this.instrumentedType.isInterface()), this.instrumentedType.getInternalName(), this.instrumentedType.getGenericSignature(), (this.instrumentedType.getSuperClass() == null ? TypeDescription.OBJECT : this.instrumentedType.getSuperClass().asErasure()).getInternalName(), this.instrumentedType.getInterfaces().asErasures().toInternalNames());
                this.typeAttributeAppender.apply(classVisitorWrap, this.instrumentedType, this.annotationValueFilterFactory.on(this.instrumentedType));
                Iterator it = this.fields.iterator();
                while (it.hasNext()) {
                    this.fieldPool.target((FieldDescription) it.next()).apply(classVisitorWrap, this.annotationValueFilterFactory);
                }
                Iterator it2 = this.instrumentedMethods.iterator();
                while (it2.hasNext()) {
                    this.methodPool.target((MethodDescription) it2.next()).apply(classVisitorWrap, extractableViewMake, this.annotationValueFilterFactory);
                }
                extractableViewMake.drain(new TypeInitializer.Drain.Default(this.instrumentedType, this.methodPool, this.annotationValueFilterFactory), classVisitorWrap, this.annotationValueFilterFactory);
                classVisitorWrap.visitEnd();
                return new UnresolvedType(frameComputingClassWriter.toByteArray(), extractableViewMake.getAuxiliaryTypes());
            }
        }

        protected static class ClassDumpAction implements PrivilegedExceptionAction<Void> {
            private static final Void NOTHING = null;
            private final byte[] binaryRepresentation;
            private final TypeDescription instrumentedType;
            private final String target;

            protected boolean canEqual(Object obj) {
                return obj instanceof ClassDumpAction;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ClassDumpAction)) {
                    return false;
                }
                ClassDumpAction classDumpAction = (ClassDumpAction) obj;
                if (!classDumpAction.canEqual(this)) {
                    return false;
                }
                String str = this.target;
                String str2 = classDumpAction.target;
                if (str != null ? !str.equals(str2) : str2 != null) {
                    return false;
                }
                TypeDescription typeDescription = this.instrumentedType;
                TypeDescription typeDescription2 = classDumpAction.instrumentedType;
                if (typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null) {
                    return Arrays.equals(this.binaryRepresentation, classDumpAction.binaryRepresentation);
                }
                return false;
            }

            public int hashCode() {
                String str = this.target;
                int iHashCode = str == null ? 43 : str.hashCode();
                TypeDescription typeDescription = this.instrumentedType;
                return ((((iHashCode + 59) * 59) + (typeDescription != null ? typeDescription.hashCode() : 43)) * 59) + Arrays.hashCode(this.binaryRepresentation);
            }

            protected ClassDumpAction(String str, TypeDescription typeDescription, byte[] bArr) {
                this.target = str;
                this.instrumentedType = typeDescription;
                this.binaryRepresentation = bArr;
            }

            @Override // java.security.PrivilegedExceptionAction
            public Void run() throws Exception {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.target, this.instrumentedType.getName() + "." + System.currentTimeMillis()));
                try {
                    fileOutputStream.write(this.binaryRepresentation);
                    return NOTHING;
                } finally {
                    fileOutputStream.close();
                }
            }
        }
    }
}
