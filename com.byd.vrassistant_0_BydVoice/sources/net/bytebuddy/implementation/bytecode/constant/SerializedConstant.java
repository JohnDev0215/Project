package net.bytebuddy.implementation.bytecode.constant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class SerializedConstant implements StackManipulation {
    private static final String CHARSET = "ISO-8859-1";
    private final String serialization;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SerializedConstant;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SerializedConstant)) {
            return false;
        }
        SerializedConstant serializedConstant = (SerializedConstant) obj;
        if (!serializedConstant.canEqual(this)) {
            return false;
        }
        String str = this.serialization;
        String str2 = serializedConstant.serialization;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        String str = this.serialization;
        return 59 + (str == null ? 43 : str.hashCode());
    }

    protected SerializedConstant(String str) {
        this.serialization = str;
    }

    public static StackManipulation of(Serializable serializable) {
        if (serializable == null) {
            return NullConstant.INSTANCE;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(serializable);
                objectOutputStream.close();
                return new SerializedConstant(byteArrayOutputStream.toString("ISO-8859-1"));
            } catch (Throwable th) {
                objectOutputStream.close();
                throw th;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot serialize " + serializable, e);
        }
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        try {
            return new StackManipulation.Compound(TypeCreation.of(new TypeDescription.ForLoadedType(ObjectInputStream.class)), Duplication.SINGLE, TypeCreation.of(new TypeDescription.ForLoadedType(ByteArrayInputStream.class)), Duplication.SINGLE, new TextConstant(this.serialization), new TextConstant("ISO-8859-1"), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(String.class.getMethod("getBytes", String.class))), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(ByteArrayInputStream.class.getConstructor(byte[].class))), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(ObjectInputStream.class.getConstructor(InputStream.class))), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(ObjectInputStream.class.getMethod("readObject", new Class[0])))).apply(methodVisitor, context);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate Java API method", e);
        }
    }
}
