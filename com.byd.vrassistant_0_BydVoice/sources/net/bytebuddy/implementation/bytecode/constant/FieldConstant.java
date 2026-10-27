package net.bytebuddy.implementation.bytecode.constant;

import java.lang.reflect.Field;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class FieldConstant implements StackManipulation {
    private final FieldDescription.InDefinedShape fieldDescription;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FieldConstant;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldConstant)) {
            return false;
        }
        FieldConstant fieldConstant = (FieldConstant) obj;
        if (!fieldConstant.canEqual(this)) {
            return false;
        }
        FieldDescription.InDefinedShape inDefinedShape = this.fieldDescription;
        FieldDescription.InDefinedShape inDefinedShape2 = fieldConstant.fieldDescription;
        return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
    }

    public int hashCode() {
        FieldDescription.InDefinedShape inDefinedShape = this.fieldDescription;
        return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
    }

    public FieldConstant(FieldDescription.InDefinedShape inDefinedShape) {
        this.fieldDescription = inDefinedShape;
    }

    public StackManipulation cached() {
        return new Cached(this);
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        try {
            return new StackManipulation.Compound(ClassConstant.of(this.fieldDescription.getDeclaringType()), new TextConstant(this.fieldDescription.getInternalName()), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Class.class.getMethod("getDeclaredField", String.class)))).apply(methodVisitor, context);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Cannot locate Class::getDeclaredField", e);
        }
    }

    protected static class Cached implements StackManipulation {
        private final StackManipulation fieldConstant;

        protected boolean canEqual(Object obj) {
            return obj instanceof Cached;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Cached)) {
                return false;
            }
            Cached cached = (Cached) obj;
            if (!cached.canEqual(this)) {
                return false;
            }
            StackManipulation stackManipulation = this.fieldConstant;
            StackManipulation stackManipulation2 = cached.fieldConstant;
            return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
        }

        public int hashCode() {
            StackManipulation stackManipulation = this.fieldConstant;
            return 59 + (stackManipulation == null ? 43 : stackManipulation.hashCode());
        }

        public Cached(StackManipulation stackManipulation) {
            this.fieldConstant = stackManipulation;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.fieldConstant.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return FieldAccess.forField(context.cache(this.fieldConstant, new TypeDescription.ForLoadedType(Field.class))).read().apply(methodVisitor, context);
        }
    }
}
