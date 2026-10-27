package com.sun.xml.bind.v2.bytecode;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import com.android.multidex.ClassPathElement;
import com.sun.xml.bind.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public final class ClassTailor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger logger = Util.getClassLogger();

    private ClassTailor() {
    }

    public static String toVMClassName(Class cls) {
        if (cls.isArray()) {
            return toVMTypeName(cls);
        }
        return cls.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
    }

    public static String toVMTypeName(Class cls) {
        if (cls.isArray()) {
            return "[" + toVMTypeName(cls.getComponentType());
        }
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                return "Z";
            }
            if (cls == Character.TYPE) {
                return TemperatureUnitEnum.C;
            }
            if (cls == Byte.TYPE) {
                return "B";
            }
            if (cls == Double.TYPE) {
                return "D";
            }
            if (cls == Float.TYPE) {
                return TemperatureUnitEnum.F;
            }
            if (cls == Integer.TYPE) {
                return "I";
            }
            if (cls == Long.TYPE) {
                return "J";
            }
            if (cls == Short.TYPE) {
                return "S";
            }
            throw new IllegalArgumentException(cls.getName());
        }
        return TemperatureUnitEnum.L + cls.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + TypePool.Default.LazyTypeDescription.GenericTypeToken.INDEXED_TYPE_DELIMITER;
    }

    public static byte[] tailor(Class cls, String str, String... strArr) {
        String vMClassName = toVMClassName(cls);
        return tailor(SecureLoader.getClassClassLoader(cls).getResourceAsStream(vMClassName + ClassFileLocator.CLASS_FILE_EXTENSION), vMClassName, str, strArr);
    }

    public static byte[] tailor(InputStream inputStream, String str, String str2, String... strArr) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeLong(dataInputStream.readLong());
            short s = dataInputStream.readShort();
            dataOutputStream.writeShort(s);
            int i = 0;
            while (i < s) {
                byte b = dataInputStream.readByte();
                dataOutputStream.writeByte(b);
                switch (b) {
                    case 0:
                        break;
                    case 1:
                        String utf = dataInputStream.readUTF();
                        if (utf.equals(str)) {
                            utf = str2;
                        } else {
                            int i2 = 0;
                            while (true) {
                                if (i2 < strArr.length) {
                                    if (utf.equals(strArr[i2])) {
                                        utf = strArr[i2 + 1];
                                    } else {
                                        i2 += 2;
                                    }
                                }
                            }
                        }
                        dataOutputStream.writeUTF(utf);
                        break;
                    case 2:
                    default:
                        throw new IllegalArgumentException("Unknown constant type " + ((int) b));
                    case 3:
                    case 4:
                        dataOutputStream.writeInt(dataInputStream.readInt());
                        break;
                    case 5:
                    case 6:
                        i++;
                        dataOutputStream.writeLong(dataInputStream.readLong());
                        break;
                    case 7:
                    case 8:
                        dataOutputStream.writeShort(dataInputStream.readShort());
                        break;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        dataOutputStream.writeInt(dataInputStream.readInt());
                        break;
                }
                i++;
            }
            byte[] bArr = new byte[512];
            while (true) {
                int i3 = dataInputStream.read(bArr);
                if (i3 > 0) {
                    dataOutputStream.write(bArr, 0, i3);
                } else {
                    dataInputStream.close();
                    dataOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "failed to tailor", (Throwable) e);
            return null;
        }
    }
}
