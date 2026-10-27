package net.bytebuddy.jar.asm.commons;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.signature.SignatureReader;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.jar.asm.signature.SignatureWriter;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Remapper {
    public String map(String str) {
        return str;
    }

    public String mapFieldName(String str, String str2, String str3) {
        return str2;
    }

    public String mapInvokeDynamicMethodName(String str, String str2) {
        return str;
    }

    public String mapMethodName(String str, String str2, String str3) {
        return str2;
    }

    public String mapModuleName(String str) {
        return str;
    }

    public String mapDesc(String str) {
        String map;
        Type type = Type.getType(str);
        int sort = type.getSort();
        if (sort != 9) {
            return (sort == 10 && (map = map(type.getInternalName())) != null) ? TemperatureUnitEnum.L + map + TypePool.Default.LazyTypeDescription.GenericTypeToken.INDEXED_TYPE_DELIMITER : str;
        }
        String strMapDesc = mapDesc(type.getElementType().getDescriptor());
        for (int i = 0; i < type.getDimensions(); i++) {
            strMapDesc = "[" + strMapDesc;
        }
        return strMapDesc;
    }

    private Type mapType(Type type) {
        switch (type.getSort()) {
            case 9:
                String strMapDesc = mapDesc(type.getElementType().getDescriptor());
                for (int i = 0; i < type.getDimensions(); i++) {
                    strMapDesc = "[" + strMapDesc;
                }
                return Type.getType(strMapDesc);
            case 10:
                String map = map(type.getInternalName());
                return map != null ? Type.getObjectType(map) : type;
            case 11:
                return Type.getMethodType(mapMethodDesc(type.getDescriptor()));
            default:
                return type;
        }
    }

    public String mapType(String str) {
        if (str == null) {
            return null;
        }
        return mapType(Type.getObjectType(str)).getInternalName();
    }

    public String[] mapTypes(String[] strArr) {
        String[] strArr2 = null;
        boolean z = false;
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            String map = map(str);
            if (map != null && strArr2 == null) {
                strArr2 = new String[strArr.length];
                if (i > 0) {
                    System.arraycopy(strArr, 0, strArr2, 0, i);
                }
                z = true;
            }
            if (z) {
                if (map != null) {
                    str = map;
                }
                strArr2[i] = str;
            }
        }
        return z ? strArr2 : strArr;
    }

    public String mapMethodDesc(String str) {
        if ("()V".equals(str)) {
            return str;
        }
        Type[] argumentTypes = Type.getArgumentTypes(str);
        StringBuilder sb = new StringBuilder("(");
        for (Type type : argumentTypes) {
            sb.append(mapDesc(type.getDescriptor()));
        }
        Type returnType = Type.getReturnType(str);
        if (returnType == Type.VOID_TYPE) {
            sb.append(")V");
            return sb.toString();
        }
        sb.append(')').append(mapDesc(returnType.getDescriptor()));
        return sb.toString();
    }

    public Object mapValue(Object obj) {
        if (obj instanceof Type) {
            return mapType((Type) obj);
        }
        if (!(obj instanceof Handle)) {
            return obj;
        }
        Handle handle = (Handle) obj;
        return new Handle(handle.getTag(), mapType(handle.getOwner()), mapMethodName(handle.getOwner(), handle.getName(), handle.getDesc()), mapMethodDesc(handle.getDesc()), handle.isInterface());
    }

    public String mapSignature(String str, boolean z) {
        if (str == null) {
            return null;
        }
        SignatureReader signatureReader = new SignatureReader(str);
        SignatureWriter signatureWriter = new SignatureWriter();
        SignatureVisitor signatureVisitorCreateSignatureRemapper = createSignatureRemapper(signatureWriter);
        if (z) {
            signatureReader.acceptType(signatureVisitorCreateSignatureRemapper);
        } else {
            signatureReader.accept(signatureVisitorCreateSignatureRemapper);
        }
        return signatureWriter.toString();
    }

    @Deprecated
    protected SignatureVisitor createRemappingSignatureAdapter(SignatureVisitor signatureVisitor) {
        return new SignatureRemapper(signatureVisitor, this);
    }

    protected SignatureVisitor createSignatureRemapper(SignatureVisitor signatureVisitor) {
        return createRemappingSignatureAdapter(signatureVisitor);
    }

    public String mapPackageName(String str) {
        int iLastIndexOf;
        String map = map(str + ".FakeClassName");
        return (map == null || (iLastIndexOf = map.lastIndexOf(46)) == -1) ? str : map.substring(0, iLastIndexOf);
    }
}
