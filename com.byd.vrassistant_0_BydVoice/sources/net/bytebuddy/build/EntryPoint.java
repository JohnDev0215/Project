package net.bytebuddy.build;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public interface EntryPoint {
    ByteBuddy getByteBuddy();

    DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer);

    public enum Default implements EntryPoint {
        REBASE(new ByteBuddy()) { // from class: net.bytebuddy.build.EntryPoint.Default.1
            @Override // net.bytebuddy.build.EntryPoint
            public DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                return byteBuddy.rebase(typeDescription, classFileLocator, methodNameTransformer);
            }
        },
        REDEFINE(new ByteBuddy()) { // from class: net.bytebuddy.build.EntryPoint.Default.2
            @Override // net.bytebuddy.build.EntryPoint
            public DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                return byteBuddy.redefine(typeDescription, classFileLocator);
            }
        },
        REDEFINE_LOCAL(new ByteBuddy().with(Implementation.Context.Disabled.Factory.INSTANCE)) { // from class: net.bytebuddy.build.EntryPoint.Default.3
            @Override // net.bytebuddy.build.EntryPoint
            public DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                return byteBuddy.redefine(typeDescription, classFileLocator).ignoreAlso(ElementMatchers.not(ElementMatchers.isDeclaredBy(typeDescription)));
            }
        };

        private final ByteBuddy byteBuddy;

        Default(ByteBuddy byteBuddy) {
            this.byteBuddy = byteBuddy;
        }

        @Override // net.bytebuddy.build.EntryPoint
        public ByteBuddy getByteBuddy() {
            return this.byteBuddy;
        }
    }
}
