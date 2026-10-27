package net.bytebuddy.implementation.bytecode.collection;

import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class ArrayFactory implements CollectionFactory {
    private final ArrayCreator arrayCreator;
    private final TypeDescription.Generic componentType;
    private final StackManipulation.Size sizeDecrease;

    protected boolean canEqual(Object obj) {
        return obj instanceof ArrayFactory;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArrayFactory)) {
            return false;
        }
        ArrayFactory arrayFactory = (ArrayFactory) obj;
        if (!arrayFactory.canEqual(this)) {
            return false;
        }
        TypeDescription.Generic componentType = getComponentType();
        TypeDescription.Generic componentType2 = arrayFactory.getComponentType();
        if (componentType != null ? !componentType.equals(componentType2) : componentType2 != null) {
            return false;
        }
        ArrayCreator arrayCreator = this.arrayCreator;
        ArrayCreator arrayCreator2 = arrayFactory.arrayCreator;
        return arrayCreator != null ? arrayCreator.equals(arrayCreator2) : arrayCreator2 == null;
    }

    public int hashCode() {
        TypeDescription.Generic componentType = getComponentType();
        int iHashCode = componentType == null ? 43 : componentType.hashCode();
        ArrayCreator arrayCreator = this.arrayCreator;
        return ((iHashCode + 59) * 59) + (arrayCreator != null ? arrayCreator.hashCode() : 43);
    }

    protected ArrayFactory(TypeDescription.Generic generic, ArrayCreator arrayCreator) {
        this.componentType = generic;
        this.arrayCreator = arrayCreator;
        this.sizeDecrease = StackSize.DOUBLE.toDecreasingSize().aggregate(generic.getStackSize().toDecreasingSize());
    }

    public static ArrayFactory forType(TypeDescription.Generic generic) {
        return new ArrayFactory(generic, makeArrayCreatorFor(generic));
    }

    private static ArrayCreator makeArrayCreatorFor(TypeDefinition typeDefinition) {
        if (typeDefinition.isPrimitive()) {
            if (typeDefinition.represents(Boolean.TYPE)) {
                return ArrayCreator.ForPrimitiveType.BOOLEAN;
            }
            if (typeDefinition.represents(Byte.TYPE)) {
                return ArrayCreator.ForPrimitiveType.BYTE;
            }
            if (typeDefinition.represents(Short.TYPE)) {
                return ArrayCreator.ForPrimitiveType.SHORT;
            }
            if (typeDefinition.represents(Character.TYPE)) {
                return ArrayCreator.ForPrimitiveType.CHARACTER;
            }
            if (typeDefinition.represents(Integer.TYPE)) {
                return ArrayCreator.ForPrimitiveType.INTEGER;
            }
            if (typeDefinition.represents(Long.TYPE)) {
                return ArrayCreator.ForPrimitiveType.LONG;
            }
            if (typeDefinition.represents(Float.TYPE)) {
                return ArrayCreator.ForPrimitiveType.FLOAT;
            }
            if (typeDefinition.represents(Double.TYPE)) {
                return ArrayCreator.ForPrimitiveType.DOUBLE;
            }
            throw new IllegalArgumentException("Cannot create array of type " + typeDefinition);
        }
        return new ArrayCreator.ForReferenceType(typeDefinition.asErasure());
    }

    @Override // net.bytebuddy.implementation.bytecode.collection.CollectionFactory
    public StackManipulation withValues(List<? extends StackManipulation> list) {
        return new ArrayStackManipulation(list);
    }

    @Override // net.bytebuddy.implementation.bytecode.collection.CollectionFactory
    public TypeDescription.Generic getComponentType() {
        return this.componentType;
    }

    protected interface ArrayCreator extends StackManipulation {
        public static final StackManipulation.Size ARRAY_CREATION_SIZE_CHANGE = StackSize.ZERO.toDecreasingSize();

        int getStorageOpcode();

        public enum ForPrimitiveType implements ArrayCreator {
            BOOLEAN(4, 84),
            BYTE(8, 84),
            SHORT(9, 86),
            CHARACTER(5, 85),
            INTEGER(10, 79),
            LONG(11, 80),
            FLOAT(6, 81),
            DOUBLE(7, 82);

            private final int creationOpcode;
            private final int storageOpcode;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            ForPrimitiveType(int i, int i2) {
                this.creationOpcode = i;
                this.storageOpcode = i2;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitIntInsn(188, this.creationOpcode);
                return ARRAY_CREATION_SIZE_CHANGE;
            }

            @Override // net.bytebuddy.implementation.bytecode.collection.ArrayFactory.ArrayCreator
            public int getStorageOpcode() {
                return this.storageOpcode;
            }
        }

        public static class ForReferenceType implements ArrayCreator {
            private final String internalTypeName;

            @Override // net.bytebuddy.implementation.bytecode.collection.ArrayFactory.ArrayCreator
            public int getStorageOpcode() {
                return 83;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForReferenceType;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForReferenceType)) {
                    return false;
                }
                ForReferenceType forReferenceType = (ForReferenceType) obj;
                if (!forReferenceType.canEqual(this)) {
                    return false;
                }
                String str = this.internalTypeName;
                String str2 = forReferenceType.internalTypeName;
                return str != null ? str.equals(str2) : str2 == null;
            }

            public int hashCode() {
                String str = this.internalTypeName;
                return 59 + (str == null ? 43 : str.hashCode());
            }

            protected ForReferenceType(TypeDescription typeDescription) {
                this.internalTypeName = typeDescription.getInternalName();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitTypeInsn(189, this.internalTypeName);
                return ARRAY_CREATION_SIZE_CHANGE;
            }
        }
    }

    protected class ArrayStackManipulation implements StackManipulation {
        private final List<? extends StackManipulation> stackManipulations;

        protected ArrayStackManipulation(List<? extends StackManipulation> list) {
            this.stackManipulations = list;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            Iterator<? extends StackManipulation> it = this.stackManipulations.iterator();
            while (it.hasNext()) {
                if (!it.next().isValid()) {
                    return false;
                }
            }
            return ArrayFactory.this.arrayCreator.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            StackManipulation.Size sizeAggregate = IntegerConstant.forValue(this.stackManipulations.size()).apply(methodVisitor, context).aggregate(ArrayFactory.this.arrayCreator.apply(methodVisitor, context));
            int i = 0;
            for (StackManipulation stackManipulation : this.stackManipulations) {
                methodVisitor.visitInsn(89);
                StackManipulation.Size sizeAggregate2 = sizeAggregate.aggregate(StackSize.SINGLE.toIncreasingSize()).aggregate(IntegerConstant.forValue(i).apply(methodVisitor, context)).aggregate(stackManipulation.apply(methodVisitor, context));
                methodVisitor.visitInsn(ArrayFactory.this.arrayCreator.getStorageOpcode());
                sizeAggregate = sizeAggregate2.aggregate(ArrayFactory.this.sizeDecrease);
                i++;
            }
            return sizeAggregate;
        }

        private ArrayFactory getArrayFactory() {
            return ArrayFactory.this;
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj != null && getClass() == obj.getClass()) {
                    ArrayStackManipulation arrayStackManipulation = (ArrayStackManipulation) obj;
                    if (!ArrayFactory.this.equals(arrayStackManipulation.getArrayFactory()) || !this.stackManipulations.equals(arrayStackManipulation.stackManipulations)) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.stackManipulations.hashCode();
        }
    }
}
