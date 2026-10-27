package net.bytebuddy.asm;

import com.android.multidex.ClassPathElement;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public enum TypeConstantAdjustment implements AsmVisitorWrapper {
    INSTANCE;

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public int mergeReader(int i) {
        return i;
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public int mergeWriter(int i) {
        return i;
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
        return new TypeConstantDissolvingClassVisitor(classVisitor);
    }

    protected static class TypeConstantDissolvingClassVisitor extends ClassVisitor {
        private boolean supportsTypeConstants;

        protected TypeConstantDissolvingClassVisitor(ClassVisitor classVisitor) {
            super(Opcodes.ASM6, classVisitor);
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
            this.supportsTypeConstants = ClassFileVersion.ofMinorMajor(i).isAtLeast(ClassFileVersion.JAVA_V5);
            super.visit(i, i2, str, str2, str3, strArr);
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
            MethodVisitor methodVisitorVisitMethod = super.visitMethod(i, str, str2, str3, strArr);
            return (this.supportsTypeConstants || methodVisitorVisitMethod == null) ? methodVisitorVisitMethod : new TypeConstantDissolvingMethodVisitor(methodVisitorVisitMethod);
        }

        protected static class TypeConstantDissolvingMethodVisitor extends MethodVisitor {
            private static final String DESCRIPTOR = "(Ljava/lang/String;)Ljava/lang/Class;";
            private static final String FOR_NAME = "forName";
            private static final String JAVA_LANG_CLASS = "java/lang/Class";

            protected TypeConstantDissolvingMethodVisitor(MethodVisitor methodVisitor) {
                super(Opcodes.ASM6, methodVisitor);
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitLdcInsn(Object obj) {
                Type type;
                int sort;
                if ((obj instanceof Type) && ((sort = (type = (Type) obj).getSort()) == 9 || sort == 10)) {
                    super.visitLdcInsn(type.getInternalName().replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH));
                    super.visitMethodInsn(184, "java/lang/Class", FOR_NAME, DESCRIPTOR, false);
                } else {
                    super.visitLdcInsn(obj);
                }
            }
        }
    }
}
