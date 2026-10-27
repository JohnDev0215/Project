package net.bytebuddy.asm;

import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public class MemberRemoval extends AsmVisitorWrapper.AbstractBase {
    private final ElementMatcher.Junction<FieldDescription.InDefinedShape> fieldMatcher;
    private final ElementMatcher.Junction<MethodDescription> methodMatcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof MemberRemoval;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MemberRemoval)) {
            return false;
        }
        MemberRemoval memberRemoval = (MemberRemoval) obj;
        if (!memberRemoval.canEqual(this)) {
            return false;
        }
        ElementMatcher.Junction<FieldDescription.InDefinedShape> junction = this.fieldMatcher;
        ElementMatcher.Junction<FieldDescription.InDefinedShape> junction2 = memberRemoval.fieldMatcher;
        if (junction != null ? !junction.equals(junction2) : junction2 != null) {
            return false;
        }
        ElementMatcher.Junction<MethodDescription> junction3 = this.methodMatcher;
        ElementMatcher.Junction<MethodDescription> junction4 = memberRemoval.methodMatcher;
        return junction3 != null ? junction3.equals(junction4) : junction4 == null;
    }

    public int hashCode() {
        ElementMatcher.Junction<FieldDescription.InDefinedShape> junction = this.fieldMatcher;
        int iHashCode = junction == null ? 43 : junction.hashCode();
        ElementMatcher.Junction<MethodDescription> junction2 = this.methodMatcher;
        return ((iHashCode + 59) * 59) + (junction2 != null ? junction2.hashCode() : 43);
    }

    public MemberRemoval() {
        this(ElementMatchers.none(), ElementMatchers.none());
    }

    protected MemberRemoval(ElementMatcher.Junction<FieldDescription.InDefinedShape> junction, ElementMatcher.Junction<MethodDescription> junction2) {
        this.fieldMatcher = junction;
        this.methodMatcher = junction2;
    }

    public MemberRemoval stripFields(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new MemberRemoval(this.fieldMatcher.or(elementMatcher), this.methodMatcher);
    }

    public MemberRemoval stripMethods(ElementMatcher<? super MethodDescription> elementMatcher) {
        return stripInvokables(ElementMatchers.isMethod().and(elementMatcher));
    }

    public MemberRemoval stripConstructors(ElementMatcher<? super MethodDescription> elementMatcher) {
        return stripInvokables(ElementMatchers.isConstructor().and(elementMatcher));
    }

    public MemberRemoval stripInvokables(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new MemberRemoval(this.fieldMatcher, this.methodMatcher.or(elementMatcher));
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
        HashMap map = new HashMap();
        for (FieldDescription.InDefinedShape inDefinedShape : fieldList) {
            map.put(inDefinedShape.getInternalName() + inDefinedShape.getDescriptor(), inDefinedShape);
        }
        HashMap map2 = new HashMap();
        for (MethodDescription methodDescription : CompoundList.of(methodList, new MethodDescription.Latent.TypeInitializer(typeDescription))) {
            map2.put(methodDescription.getInternalName() + methodDescription.getDescriptor(), methodDescription);
        }
        return new MemberRemovingClassVisitor(classVisitor, this.fieldMatcher, this.methodMatcher, map, map2);
    }

    protected static class MemberRemovingClassVisitor extends ClassVisitor {
        private static final FieldVisitor REMOVE_FIELD = null;
        private static final MethodVisitor REMOVE_METHOD = null;
        private final ElementMatcher.Junction<FieldDescription.InDefinedShape> fieldMatcher;
        private final Map<String, FieldDescription.InDefinedShape> fields;
        private final ElementMatcher.Junction<MethodDescription> methodMatcher;
        private final Map<String, MethodDescription> methods;

        protected MemberRemovingClassVisitor(ClassVisitor classVisitor, ElementMatcher.Junction<FieldDescription.InDefinedShape> junction, ElementMatcher.Junction<MethodDescription> junction2, Map<String, FieldDescription.InDefinedShape> map, Map<String, MethodDescription> map2) {
            super(Opcodes.ASM6, classVisitor);
            this.fieldMatcher = junction;
            this.methodMatcher = junction2;
            this.fields = map;
            this.methods = map2;
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
            FieldDescription.InDefinedShape inDefinedShape = this.fields.get(str + str2);
            return (inDefinedShape == null || !this.fieldMatcher.matches(inDefinedShape)) ? super.visitField(i, str, str2, str3, obj) : REMOVE_FIELD;
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
            MethodDescription methodDescription = this.methods.get(str + str2);
            return (methodDescription == null || !this.methodMatcher.matches(methodDescription)) ? super.visitMethod(i, str, str2, str3, strArr) : REMOVE_METHOD;
        }
    }
}
