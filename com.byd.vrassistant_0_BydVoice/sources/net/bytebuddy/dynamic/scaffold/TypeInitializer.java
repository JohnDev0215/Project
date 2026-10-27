package net.bytebuddy.dynamic.scaffold;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeInitializer extends ByteCodeAppender {
    TypeInitializer expandWith(ByteCodeAppender byteCodeAppender);

    boolean isDefined();

    TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record);

    public interface Drain {
        void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context);

        public static class Default implements Drain {
            protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
            protected final TypeDescription instrumentedType;
            protected final TypeWriter.MethodPool methodPool;

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
                TypeWriter.MethodPool methodPool = this.methodPool;
                TypeWriter.MethodPool methodPool2 = r5.methodPool;
                if (methodPool != null ? !methodPool.equals(methodPool2) : methodPool2 != null) {
                    return false;
                }
                AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
                AnnotationValueFilter.Factory factory2 = r5.annotationValueFilterFactory;
                return factory != null ? factory.equals(factory2) : factory2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.instrumentedType;
                int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                TypeWriter.MethodPool methodPool = this.methodPool;
                int iHashCode2 = ((iHashCode + 59) * 59) + (methodPool == null ? 43 : methodPool.hashCode());
                AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
                return (iHashCode2 * 59) + (factory != null ? factory.hashCode() : 43);
            }

            public Default(TypeDescription typeDescription, TypeWriter.MethodPool methodPool, AnnotationValueFilter.Factory factory) {
                this.instrumentedType = typeDescription;
                this.methodPool = methodPool;
                this.annotationValueFilterFactory = factory;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer.Drain
            public void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context) {
                typeInitializer.wrap(this.methodPool.target(new MethodDescription.Latent.TypeInitializer(this.instrumentedType))).apply(classVisitor, context, this.annotationValueFilterFactory);
            }
        }
    }

    public enum None implements TypeInitializer {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer
        public boolean isDefined() {
            return false;
        }

        @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer
        public TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record) {
            return record;
        }

        @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer
        public TypeInitializer expandWith(ByteCodeAppender byteCodeAppender) {
            return new Simple(byteCodeAppender);
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return new ByteCodeAppender.Size(0, 0);
        }
    }

    public static class Simple implements TypeInitializer {
        private final ByteCodeAppender byteCodeAppender;

        @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer
        public boolean isDefined() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Simple;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Simple)) {
                return false;
            }
            Simple simple = (Simple) obj;
            if (!simple.canEqual(this)) {
                return false;
            }
            ByteCodeAppender byteCodeAppender = this.byteCodeAppender;
            ByteCodeAppender byteCodeAppender2 = simple.byteCodeAppender;
            return byteCodeAppender != null ? byteCodeAppender.equals(byteCodeAppender2) : byteCodeAppender2 == null;
        }

        public int hashCode() {
            ByteCodeAppender byteCodeAppender = this.byteCodeAppender;
            return 59 + (byteCodeAppender == null ? 43 : byteCodeAppender.hashCode());
        }

        public Simple(ByteCodeAppender byteCodeAppender) {
            this.byteCodeAppender = byteCodeAppender;
        }

        @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer
        public TypeInitializer expandWith(ByteCodeAppender byteCodeAppender) {
            return new Simple(new ByteCodeAppender.Compound(this.byteCodeAppender, byteCodeAppender));
        }

        @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer
        public TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record) {
            return record.prepend(this.byteCodeAppender);
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return this.byteCodeAppender.apply(methodVisitor, context, methodDescription);
        }
    }
}
