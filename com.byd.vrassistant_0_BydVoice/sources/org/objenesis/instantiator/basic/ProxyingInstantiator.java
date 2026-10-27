package org.objenesis.instantiator.basic;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX INFO: loaded from: classes4.dex */
@Instantiator(Typology.STANDARD)
public class ProxyingInstantiator<T> implements ObjectInstantiator<T> {
    private static final byte[] CODE;
    private static final int CODE_ATTRIBUTE_LENGTH;
    private static int CONSTANT_POOL_COUNT = 9;
    private static final String CONSTRUCTOR_DESC = "()V";
    private static final String CONSTRUCTOR_NAME = "<init>";
    private static final int INDEX_CLASS_SUPERCLASS = 2;
    private static final int INDEX_CLASS_THIS = 1;
    private static final int INDEX_UTF8_CLASS = 7;
    private static final int INDEX_UTF8_CODE_ATTRIBUTE = 5;
    private static final int INDEX_UTF8_CONSTRUCTOR_DESC = 4;
    private static final int INDEX_UTF8_CONSTRUCTOR_NAME = 3;
    private static final int INDEX_UTF8_SUPERCLASS = 8;
    private static final String SUFFIX = "$$$Objenesis";
    private final Class<?> newType;

    static {
        byte[] bArr = {ClassDefinitionUtils.OPS_aload_0, ClassDefinitionUtils.OPS_return};
        CODE = bArr;
        CODE_ATTRIBUTE_LENGTH = bArr.length + 12;
    }

    public ProxyingInstantiator(Class<T> cls) throws Throwable {
        try {
            this.newType = ClassDefinitionUtils.defineClass(cls.getName() + SUFFIX, writeExtendingClass(cls, SUFFIX), cls.getClassLoader());
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return (T) this.newType.newInstance();
        } catch (IllegalAccessException e) {
            throw new ObjenesisException(e);
        } catch (InstantiationException e2) {
            throw new ObjenesisException(e2);
        }
    }

    private static byte[] writeExtendingClass(Class<?> cls, String str) throws Throwable {
        String strClassNameToInternalClassName = ClassDefinitionUtils.classNameToInternalClassName(cls.getName());
        String str2 = strClassNameToInternalClassName + str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
        DataOutputStream dataOutputStream = null;
        try {
            try {
                DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream2.write(ClassDefinitionUtils.MAGIC);
                    dataOutputStream2.write(ClassDefinitionUtils.VERSION);
                    dataOutputStream2.writeShort(CONSTANT_POOL_COUNT);
                    dataOutputStream2.writeByte(7);
                    dataOutputStream2.writeShort(7);
                    dataOutputStream2.writeByte(7);
                    dataOutputStream2.writeShort(8);
                    dataOutputStream2.writeByte(1);
                    dataOutputStream2.writeUTF("<init>");
                    dataOutputStream2.writeByte(1);
                    dataOutputStream2.writeUTF(CONSTRUCTOR_DESC);
                    dataOutputStream2.writeByte(1);
                    dataOutputStream2.writeUTF("Code");
                    dataOutputStream2.writeByte(1);
                    dataOutputStream2.writeUTF(TemperatureUnitEnum.L + str2 + ";");
                    dataOutputStream2.writeByte(1);
                    dataOutputStream2.writeUTF(str2);
                    dataOutputStream2.writeByte(1);
                    dataOutputStream2.writeUTF(strClassNameToInternalClassName);
                    dataOutputStream2.writeShort(33);
                    dataOutputStream2.writeShort(1);
                    dataOutputStream2.writeShort(2);
                    dataOutputStream2.writeShort(0);
                    dataOutputStream2.writeShort(0);
                    dataOutputStream2.writeShort(1);
                    dataOutputStream2.writeShort(1);
                    dataOutputStream2.writeShort(3);
                    dataOutputStream2.writeShort(4);
                    dataOutputStream2.writeShort(1);
                    dataOutputStream2.writeShort(5);
                    dataOutputStream2.writeInt(CODE_ATTRIBUTE_LENGTH);
                    dataOutputStream2.writeShort(1);
                    dataOutputStream2.writeShort(1);
                    byte[] bArr = CODE;
                    dataOutputStream2.writeInt(bArr.length);
                    dataOutputStream2.write(bArr);
                    dataOutputStream2.writeShort(0);
                    dataOutputStream2.writeShort(0);
                    dataOutputStream2.writeShort(0);
                    try {
                        dataOutputStream2.close();
                        return byteArrayOutputStream.toByteArray();
                    } catch (IOException e) {
                        throw new ObjenesisException(e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    dataOutputStream = dataOutputStream2;
                    throw new ObjenesisException(e);
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e3) {
                            throw new ObjenesisException(e3);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e = e4;
        }
    }
}
