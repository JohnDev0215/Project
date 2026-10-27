package net.bytebuddy.utility;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;

/* JADX INFO: loaded from: classes3.dex */
public enum JavaType {
    METHOD_HANDLE("java.lang.invoke.MethodHandle", 1025, Object.class, new Class[0]),
    METHOD_TYPE("java.lang.invoke.MethodType", 17, Object.class, Serializable.class),
    METHOD_HANDLES_LOOKUP("java.lang.invoke.MethodHandles$Lookup", 25, Object.class, new Class[0]),
    CALL_SITE("java.lang.invoke.CallSite", 1025, Object.class, new Class[0]),
    PARAMETER("java.lang.reflect.Parameter", 17, Object.class, AnnotatedElement.class),
    EXECUTABLE("java.lang.reflect.Executable", 1025, AccessibleObject.class, Member.class, GenericDeclaration.class),
    MODULE("java.lang.Module", 17, Object.class, AnnotatedElement.class);

    private final TypeDescription typeDescription;

    JavaType(String str, int i, Class cls, Class... clsArr) {
        TypeDescription latent;
        try {
            latent = new TypeDescription.ForLoadedType(Class.forName(str));
        } catch (Exception unused) {
            latent = new TypeDescription.Latent(str, i, new TypeDescription.Generic.OfNonGenericType.ForLoadedType(cls), new TypeList.Generic.ForLoadedTypes(clsArr));
        }
        this.typeDescription = latent;
    }

    public TypeDescription getTypeStub() {
        return this.typeDescription;
    }

    public Class<?> load() throws ClassNotFoundException {
        return Class.forName(this.typeDescription.getName());
    }
}
