package net.bytebuddy.asm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.ModifierContributor;
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
public class ModifierAdjustment extends AsmVisitorWrapper.AbstractBase {
    private final List<Adjustment<FieldDescription.InDefinedShape>> fieldAdjustments;
    private final List<Adjustment<MethodDescription>> methodAdjustments;
    private final List<Adjustment<TypeDescription>> typeAdjustments;

    protected boolean canEqual(Object obj) {
        return obj instanceof ModifierAdjustment;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ModifierAdjustment)) {
            return false;
        }
        ModifierAdjustment modifierAdjustment = (ModifierAdjustment) obj;
        if (!modifierAdjustment.canEqual(this)) {
            return false;
        }
        List<Adjustment<TypeDescription>> list = this.typeAdjustments;
        List<Adjustment<TypeDescription>> list2 = modifierAdjustment.typeAdjustments;
        if (list != null ? !list.equals(list2) : list2 != null) {
            return false;
        }
        List<Adjustment<FieldDescription.InDefinedShape>> list3 = this.fieldAdjustments;
        List<Adjustment<FieldDescription.InDefinedShape>> list4 = modifierAdjustment.fieldAdjustments;
        if (list3 != null ? !list3.equals(list4) : list4 != null) {
            return false;
        }
        List<Adjustment<MethodDescription>> list5 = this.methodAdjustments;
        List<Adjustment<MethodDescription>> list6 = modifierAdjustment.methodAdjustments;
        return list5 != null ? list5.equals(list6) : list6 == null;
    }

    public int hashCode() {
        List<Adjustment<TypeDescription>> list = this.typeAdjustments;
        int iHashCode = list == null ? 43 : list.hashCode();
        List<Adjustment<FieldDescription.InDefinedShape>> list2 = this.fieldAdjustments;
        int iHashCode2 = ((iHashCode + 59) * 59) + (list2 == null ? 43 : list2.hashCode());
        List<Adjustment<MethodDescription>> list3 = this.methodAdjustments;
        return (iHashCode2 * 59) + (list3 != null ? list3.hashCode() : 43);
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public /* bridge */ /* synthetic */ ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList fieldList, MethodList methodList, int i, int i2) {
        return wrap(typeDescription, classVisitor, context, typePool, (FieldList<FieldDescription.InDefinedShape>) fieldList, (MethodList<?>) methodList, i, i2);
    }

    public ModifierAdjustment() {
        this(Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    protected ModifierAdjustment(List<Adjustment<TypeDescription>> list, List<Adjustment<FieldDescription.InDefinedShape>> list2, List<Adjustment<MethodDescription>> list3) {
        this.typeAdjustments = list;
        this.fieldAdjustments = list2;
        this.methodAdjustments = list3;
    }

    public ModifierAdjustment withTypeModifiers(ModifierContributor.ForType... forTypeArr) {
        return withTypeModifiers(Arrays.asList(forTypeArr));
    }

    public ModifierAdjustment withTypeModifiers(List<? extends ModifierContributor.ForType> list) {
        return withTypeModifiers(ElementMatchers.any(), list);
    }

    public ModifierAdjustment withTypeModifiers(ElementMatcher<? super TypeDescription> elementMatcher, ModifierContributor.ForType... forTypeArr) {
        return withTypeModifiers(elementMatcher, Arrays.asList(forTypeArr));
    }

    public ModifierAdjustment withTypeModifiers(ElementMatcher<? super TypeDescription> elementMatcher, List<? extends ModifierContributor.ForType> list) {
        return new ModifierAdjustment(CompoundList.of(this.typeAdjustments, new Adjustment(elementMatcher, ModifierContributor.Resolver.of(list))), this.fieldAdjustments, this.methodAdjustments);
    }

    public ModifierAdjustment withFieldModifiers(ModifierContributor.ForField... forFieldArr) {
        return withFieldModifiers(Arrays.asList(forFieldArr));
    }

    public ModifierAdjustment withFieldModifiers(List<? extends ModifierContributor.ForField> list) {
        return withFieldModifiers(ElementMatchers.any(), list);
    }

    public ModifierAdjustment withFieldModifiers(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, ModifierContributor.ForField... forFieldArr) {
        return withFieldModifiers(elementMatcher, Arrays.asList(forFieldArr));
    }

    public ModifierAdjustment withFieldModifiers(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, List<? extends ModifierContributor.ForField> list) {
        return new ModifierAdjustment(this.typeAdjustments, CompoundList.of(this.fieldAdjustments, new Adjustment(elementMatcher, ModifierContributor.Resolver.of(list))), this.methodAdjustments);
    }

    public ModifierAdjustment withMethodModifiers(ModifierContributor.ForMethod... forMethodArr) {
        return withMethodModifiers(Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withMethodModifiers(List<? extends ModifierContributor.ForMethod> list) {
        return withMethodModifiers(ElementMatchers.any(), list);
    }

    public ModifierAdjustment withMethodModifiers(ElementMatcher<? super MethodDescription> elementMatcher, ModifierContributor.ForMethod... forMethodArr) {
        return withMethodModifiers(elementMatcher, Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withMethodModifiers(ElementMatcher<? super MethodDescription> elementMatcher, List<? extends ModifierContributor.ForMethod> list) {
        return withInvokableModifiers(ElementMatchers.isMethod().and(elementMatcher), list);
    }

    public ModifierAdjustment withConstructorModifiers(ModifierContributor.ForMethod... forMethodArr) {
        return withConstructorModifiers(Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withConstructorModifiers(List<? extends ModifierContributor.ForMethod> list) {
        return withConstructorModifiers(ElementMatchers.any(), list);
    }

    public ModifierAdjustment withConstructorModifiers(ElementMatcher<? super MethodDescription> elementMatcher, ModifierContributor.ForMethod... forMethodArr) {
        return withConstructorModifiers(elementMatcher, Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withConstructorModifiers(ElementMatcher<? super MethodDescription> elementMatcher, List<? extends ModifierContributor.ForMethod> list) {
        return withInvokableModifiers(ElementMatchers.isConstructor().and(elementMatcher), list);
    }

    public ModifierAdjustment withInvokableModifiers(ModifierContributor.ForMethod... forMethodArr) {
        return withInvokableModifiers(Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withInvokableModifiers(List<? extends ModifierContributor.ForMethod> list) {
        return withInvokableModifiers(ElementMatchers.any(), list);
    }

    public ModifierAdjustment withInvokableModifiers(ElementMatcher<? super MethodDescription> elementMatcher, ModifierContributor.ForMethod... forMethodArr) {
        return withInvokableModifiers(elementMatcher, Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withInvokableModifiers(ElementMatcher<? super MethodDescription> elementMatcher, List<? extends ModifierContributor.ForMethod> list) {
        return new ModifierAdjustment(this.typeAdjustments, this.fieldAdjustments, CompoundList.of(this.methodAdjustments, new Adjustment(elementMatcher, ModifierContributor.Resolver.of(list))));
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper
    public ModifierAdjustingClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
        HashMap map = new HashMap();
        for (FieldDescription.InDefinedShape inDefinedShape : fieldList) {
            map.put(inDefinedShape.getInternalName() + inDefinedShape.getDescriptor(), inDefinedShape);
        }
        HashMap map2 = new HashMap();
        for (MethodDescription methodDescription : CompoundList.of(methodList, new MethodDescription.Latent.TypeInitializer(typeDescription))) {
            map2.put(methodDescription.getInternalName() + methodDescription.getDescriptor(), methodDescription);
        }
        return new ModifierAdjustingClassVisitor(classVisitor, this.typeAdjustments, this.fieldAdjustments, this.methodAdjustments, typeDescription, map, map2);
    }

    protected static class Adjustment<T> implements ElementMatcher<T> {
        private final ElementMatcher<? super T> matcher;
        private final ModifierContributor.Resolver<?> resolver;

        protected boolean canEqual(Object obj) {
            return obj instanceof Adjustment;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Adjustment)) {
                return false;
            }
            Adjustment adjustment = (Adjustment) obj;
            if (!adjustment.canEqual(this)) {
                return false;
            }
            ElementMatcher<? super T> elementMatcher = this.matcher;
            ElementMatcher<? super T> elementMatcher2 = adjustment.matcher;
            if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                return false;
            }
            ModifierContributor.Resolver<?> resolver = this.resolver;
            ModifierContributor.Resolver<?> resolver2 = adjustment.resolver;
            return resolver != null ? resolver.equals(resolver2) : resolver2 == null;
        }

        public int hashCode() {
            ElementMatcher<? super T> elementMatcher = this.matcher;
            int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
            ModifierContributor.Resolver<?> resolver = this.resolver;
            return ((iHashCode + 59) * 59) + (resolver != null ? resolver.hashCode() : 43);
        }

        protected Adjustment(ElementMatcher<? super T> elementMatcher, ModifierContributor.Resolver<?> resolver) {
            this.matcher = elementMatcher;
            this.resolver = resolver;
        }

        @Override // net.bytebuddy.matcher.ElementMatcher
        public boolean matches(T t) {
            return this.matcher.matches(t);
        }

        protected int resolve(int i) {
            return this.resolver.resolve(i);
        }
    }

    protected static class ModifierAdjustingClassVisitor extends ClassVisitor {
        private final List<Adjustment<FieldDescription.InDefinedShape>> fieldAdjustments;
        private final Map<String, FieldDescription.InDefinedShape> fields;
        private final TypeDescription instrumentedType;
        private final List<Adjustment<MethodDescription>> methodAdjustments;
        private final Map<String, MethodDescription> methods;
        private final List<Adjustment<TypeDescription>> typeAdjustments;

        protected ModifierAdjustingClassVisitor(ClassVisitor classVisitor, List<Adjustment<TypeDescription>> list, List<Adjustment<FieldDescription.InDefinedShape>> list2, List<Adjustment<MethodDescription>> list3, TypeDescription typeDescription, Map<String, FieldDescription.InDefinedShape> map, Map<String, MethodDescription> map2) {
            super(Opcodes.ASM6, classVisitor);
            this.typeAdjustments = list;
            this.fieldAdjustments = list2;
            this.methodAdjustments = list3;
            this.instrumentedType = typeDescription;
            this.fields = map;
            this.methods = map2;
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
            Adjustment<TypeDescription> next;
            Iterator<Adjustment<TypeDescription>> it = this.typeAdjustments.iterator();
            while (true) {
                int i3 = i2;
                while (it.hasNext()) {
                    next = it.next();
                    if (next.matches(this.instrumentedType)) {
                        break;
                    }
                }
                super.visit(i, i3, str, str2, str3, strArr);
                return;
                i2 = next.resolve(i3);
            }
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public void visitInnerClass(String str, String str2, String str3, int i) {
            if (this.instrumentedType.getInternalName().equals(str)) {
                for (Adjustment<TypeDescription> adjustment : this.typeAdjustments) {
                    if (adjustment.matches(this.instrumentedType)) {
                        i = adjustment.resolve(i);
                    }
                }
            }
            super.visitInnerClass(str, str2, str3, i);
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
            FieldDescription.InDefinedShape inDefinedShape = this.fields.get(str + str2);
            if (inDefinedShape != null) {
                for (Adjustment<FieldDescription.InDefinedShape> adjustment : this.fieldAdjustments) {
                    if (adjustment.matches(inDefinedShape)) {
                        i = adjustment.resolve(i);
                    }
                }
            }
            return super.visitField(i, str, str2, str3, obj);
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
            MethodDescription methodDescription = this.methods.get(str + str2);
            if (methodDescription != null) {
                for (Adjustment<MethodDescription> adjustment : this.methodAdjustments) {
                    if (adjustment.matches(methodDescription)) {
                        i = adjustment.resolve(i);
                    }
                }
            }
            return super.visitMethod(i, str, str2, str3, strArr);
        }
    }
}
