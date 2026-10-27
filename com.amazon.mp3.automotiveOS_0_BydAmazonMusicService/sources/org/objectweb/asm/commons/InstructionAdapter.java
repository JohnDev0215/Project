package org.objectweb.asm.commons;

import com.visualon.OSMPUtils.voOSType;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

/* JADX INFO: loaded from: classes6.dex */
public class InstructionAdapter extends MethodVisitor {
    public static final Type OBJECT_TYPE = Type.getType("Ljava/lang/Object;");

    static void cast(MethodVisitor methodVisitor, Type type, Type type2) {
        if (type != type2) {
            Type type3 = Type.DOUBLE_TYPE;
            if (type == type3) {
                if (type2 == Type.FLOAT_TYPE) {
                    methodVisitor.visitInsn(voOSType.VOOSMP_PID_ANALYTICS_AGENT);
                    return;
                } else if (type2 == Type.LONG_TYPE) {
                    methodVisitor.visitInsn(voOSType.VOOSMP_PID_SET_I_FRAME_ONLY_MODE);
                    return;
                } else {
                    methodVisitor.visitInsn(142);
                    cast(methodVisitor, Type.INT_TYPE, type2);
                    return;
                }
            }
            Type type4 = Type.FLOAT_TYPE;
            if (type == type4) {
                if (type2 == type3) {
                    methodVisitor.visitInsn(voOSType.VOOSMP_PID_ANALYTICS_EXPORT_LISTENER);
                    return;
                } else if (type2 == Type.LONG_TYPE) {
                    methodVisitor.visitInsn(voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET);
                    return;
                } else {
                    methodVisitor.visitInsn(voOSType.VOOSMP_PID_ANALYTICS_EXPORT);
                    cast(methodVisitor, Type.INT_TYPE, type2);
                    return;
                }
            }
            Type type5 = Type.LONG_TYPE;
            if (type == type5) {
                if (type2 == type3) {
                    methodVisitor.visitInsn(138);
                    return;
                } else if (type2 == type4) {
                    methodVisitor.visitInsn(voOSType.VOOSMP_PID_PREFERENCE);
                    return;
                } else {
                    methodVisitor.visitInsn(136);
                    cast(methodVisitor, Type.INT_TYPE, type2);
                    return;
                }
            }
            if (type2 == Type.BYTE_TYPE) {
                methodVisitor.visitInsn(voOSType.VOOSMP_PID_ANALYTICS_AGENT_CUID);
                return;
            }
            if (type2 == Type.CHAR_TYPE) {
                methodVisitor.visitInsn(voOSType.VOOSMP_PID_ANALYTICS_AGENT_APPID);
                return;
            }
            if (type2 == type3) {
                methodVisitor.visitInsn(135);
                return;
            }
            if (type2 == type4) {
                methodVisitor.visitInsn(134);
            } else if (type2 == type5) {
                methodVisitor.visitInsn(133);
            } else if (type2 == Type.SHORT_TYPE) {
                methodVisitor.visitInsn(voOSType.VOOSMP_PID_ANALYTICS_INFO);
            }
        }
    }

    static void newarray(MethodVisitor methodVisitor, Type type) {
        int i;
        switch (type.getSort()) {
            case 1:
                i = 4;
                break;
            case 2:
                i = 5;
                break;
            case 3:
                i = 8;
                break;
            case 4:
                i = 9;
                break;
            case 5:
                i = 10;
                break;
            case 6:
                i = 6;
                break;
            case 7:
                i = 11;
                break;
            case 8:
                i = 7;
                break;
            default:
                methodVisitor.visitTypeInsn(189, type.getInternalName());
                return;
        }
        methodVisitor.visitIntInsn(188, i);
    }
}
