package net.bytebuddy.dynamic;

import net.bytebuddy.description.type.TypeDescription;

/* JADX INFO: loaded from: classes3.dex */
public final class TargetType {
    public static final TypeDescription DESCRIPTION = new TypeDescription.ForLoadedType(TargetType.class);

    public static TypeDescription resolve(TypeDescription typeDescription, TypeDescription typeDescription2) {
        int i = 0;
        TypeDescription componentType = typeDescription;
        while (componentType.isArray()) {
            componentType = componentType.getComponentType();
            i++;
        }
        return componentType.represents(TargetType.class) ? TypeDescription.ArrayProjection.of(typeDescription2, i) : typeDescription;
    }

    private TargetType() {
        throw new UnsupportedOperationException("This class only serves as a marker type");
    }
}
