package net.bytebuddy.jar.asm;

import net.bytebuddy.implementation.auxiliary.TypeProxy;

/* JADX INFO: loaded from: classes3.dex */
class Frame {
    static final int ARRAY_OF = 268435456;
    static final int BASE = 16777216;
    static final int BASE_KIND = 267386880;
    static final int BASE_VALUE = 1048575;
    static final int BOOLEAN = 16777225;
    static final int BYTE = 16777226;
    static final int CHAR = 16777227;
    static final int DIM = -268435456;
    static final int DOUBLE = 16777219;
    static final int ELEMENT_OF = -268435456;
    static final int FLOAT = 16777218;
    static final int INTEGER = 16777217;
    static final int KIND = 251658240;
    private static final int LOCAL = 33554432;
    static final int LONG = 16777220;
    static final int NULL = 16777221;
    static final int OBJECT = 24117248;
    static final int SHORT = 16777228;
    static final int[] SIZE;
    private static final int STACK = 50331648;
    static final int TOP = 16777216;
    static final int TOP_IF_LONG_OR_DOUBLE = 8388608;
    static final int UNINITIALIZED = 25165824;
    static final int UNINITIALIZED_THIS = 16777222;
    static final int VALUE = 8388607;
    private int initializationCount;
    private int[] initializations;
    int[] inputLocals;
    int[] inputStack;
    private int[] outputLocals;
    private int[] outputStack;
    int outputStackTop;
    Label owner;

    Frame() {
    }

    static {
        int[] iArr = new int[202];
        for (int i = 0; i < 202; i++) {
            iArr[i] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i) - 'E';
        }
        SIZE = iArr;
    }

    final void set(ClassWriter classWriter, int i, Object[] objArr, int i2, Object[] objArr2) {
        for (int iConvert = convert(classWriter, i, objArr, this.inputLocals); iConvert < objArr.length; iConvert++) {
            this.inputLocals[iConvert] = 16777216;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (objArr2[i4] == Opcodes.LONG || objArr2[i4] == Opcodes.DOUBLE) {
                i3++;
            }
        }
        int[] iArr = new int[i3 + i2];
        this.inputStack = iArr;
        convert(classWriter, i2, objArr2, iArr);
        this.outputStackTop = 0;
        this.initializationCount = 0;
    }

    private static int convert(ClassWriter classWriter, int i, Object[] objArr, int[] iArr) {
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            Object obj = objArr[i4];
            if (obj instanceof Integer) {
                i2 = i3 + 1;
                iArr[i3] = ((Integer) obj).intValue() | 16777216;
                if (objArr[i4] == Opcodes.LONG || objArr[i4] == Opcodes.DOUBLE) {
                    i3 = i2 + 1;
                    iArr[i2] = 16777216;
                }
            } else if (obj instanceof String) {
                i2 = i3 + 1;
                iArr[i3] = type(classWriter, Type.getObjectType((String) obj).getDescriptor());
            } else {
                i2 = i3 + 1;
                iArr[i3] = classWriter.addUninitializedType("", ((Label) obj).position) | UNINITIALIZED;
            }
            i3 = i2;
        }
        return i3;
    }

    final void set(Frame frame) {
        this.inputLocals = frame.inputLocals;
        this.inputStack = frame.inputStack;
        this.outputLocals = frame.outputLocals;
        this.outputStack = frame.outputStack;
        this.outputStackTop = frame.outputStackTop;
        this.initializationCount = frame.initializationCount;
        this.initializations = frame.initializations;
    }

    private int get(int i) {
        int[] iArr = this.outputLocals;
        if (iArr == null || i >= iArr.length) {
            return i | 33554432;
        }
        int i2 = iArr[i];
        if (i2 != 0) {
            return i2;
        }
        int i3 = i | 33554432;
        iArr[i] = i3;
        return i3;
    }

    private void set(int i, int i2) {
        if (this.outputLocals == null) {
            this.outputLocals = new int[10];
        }
        int length = this.outputLocals.length;
        if (i >= length) {
            int[] iArr = new int[Math.max(i + 1, length * 2)];
            System.arraycopy(this.outputLocals, 0, iArr, 0, length);
            this.outputLocals = iArr;
        }
        this.outputLocals[i] = i2;
    }

    private void push(int i) {
        if (this.outputStack == null) {
            this.outputStack = new int[10];
        }
        int length = this.outputStack.length;
        int i2 = this.outputStackTop;
        if (i2 >= length) {
            int[] iArr = new int[Math.max(i2 + 1, length * 2)];
            System.arraycopy(this.outputStack, 0, iArr, 0, length);
            this.outputStack = iArr;
        }
        int[] iArr2 = this.outputStack;
        int i3 = this.outputStackTop;
        this.outputStackTop = i3 + 1;
        iArr2[i3] = i;
        int i4 = this.owner.inputStackTop + this.outputStackTop;
        if (i4 > this.owner.outputStackMax) {
            this.owner.outputStackMax = i4;
        }
    }

    private void push(ClassWriter classWriter, String str) {
        int iType = type(classWriter, str);
        if (iType != 0) {
            push(iType);
            if (iType == LONG || iType == DOUBLE) {
                push(16777216);
            }
        }
    }

    static int type(ClassWriter classWriter, String str) {
        int iIndexOf = str.charAt(0) == '(' ? str.indexOf(41) + 1 : 0;
        char cCharAt = str.charAt(iIndexOf);
        int iAddType = FLOAT;
        if (cCharAt == 'F') {
            return FLOAT;
        }
        if (cCharAt == 'L') {
            return classWriter.addType(str.substring(iIndexOf + 1, str.length() - 1)) | OBJECT;
        }
        if (cCharAt != 'S') {
            if (cCharAt == 'V') {
                return 0;
            }
            if (cCharAt != 'Z' && cCharAt != 'I') {
                if (cCharAt == 'J') {
                    return LONG;
                }
                switch (cCharAt) {
                    case 'B':
                    case 'C':
                        break;
                    case 'D':
                        return DOUBLE;
                    default:
                        int i = iIndexOf + 1;
                        while (str.charAt(i) == '[') {
                            i++;
                        }
                        char cCharAt2 = str.charAt(i);
                        if (cCharAt2 != 'F') {
                            if (cCharAt2 == 'S') {
                                iAddType = SHORT;
                            } else if (cCharAt2 == 'Z') {
                                iAddType = BOOLEAN;
                            } else if (cCharAt2 == 'I') {
                                iAddType = INTEGER;
                            } else if (cCharAt2 != 'J') {
                                switch (cCharAt2) {
                                    case 'B':
                                        iAddType = BYTE;
                                        break;
                                    case 'C':
                                        iAddType = CHAR;
                                        break;
                                    case 'D':
                                        iAddType = DOUBLE;
                                        break;
                                    default:
                                        iAddType = classWriter.addType(str.substring(i + 1, str.length() - 1)) | OBJECT;
                                        break;
                                }
                            } else {
                                iAddType = LONG;
                            }
                        }
                        return ((i - iIndexOf) << 28) | iAddType;
                }
            }
        }
        return INTEGER;
    }

    private int pop() {
        int i = this.outputStackTop;
        if (i > 0) {
            int[] iArr = this.outputStack;
            int i2 = i - 1;
            this.outputStackTop = i2;
            return iArr[i2];
        }
        Label label = this.owner;
        int i3 = label.inputStackTop - 1;
        label.inputStackTop = i3;
        return (-i3) | STACK;
    }

    private void pop(int i) {
        int i2 = this.outputStackTop;
        if (i2 >= i) {
            this.outputStackTop = i2 - i;
            return;
        }
        this.owner.inputStackTop -= i - this.outputStackTop;
        this.outputStackTop = 0;
    }

    private void pop(String str) {
        char cCharAt = str.charAt(0);
        if (cCharAt == '(') {
            pop((Type.getArgumentsAndReturnSizes(str) >> 2) - 1);
        } else if (cCharAt == 'J' || cCharAt == 'D') {
            pop(2);
        } else {
            pop(1);
        }
    }

    private void init(int i) {
        if (this.initializations == null) {
            this.initializations = new int[2];
        }
        int length = this.initializations.length;
        int i2 = this.initializationCount;
        if (i2 >= length) {
            int[] iArr = new int[Math.max(i2 + 1, length * 2)];
            System.arraycopy(this.initializations, 0, iArr, 0, length);
            this.initializations = iArr;
        }
        int[] iArr2 = this.initializations;
        int i3 = this.initializationCount;
        this.initializationCount = i3 + 1;
        iArr2[i3] = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0051 A[LOOP:0: B:10:0x0026->B:21:0x0051, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0050 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int init(net.bytebuddy.jar.asm.ClassWriter r7, int r8) {
        /*
            r6 = this;
            r0 = 16777222(0x1000006, float:2.3509904E-38)
            r1 = 24117248(0x1700000, float:4.4081038E-38)
            if (r8 != r0) goto Lf
            java.lang.String r0 = r7.thisName
            int r7 = r7.addType(r0)
        Ld:
            r7 = r7 | r1
            goto L25
        Lf:
            r0 = -1048576(0xfffffffffff00000, float:NaN)
            r0 = r0 & r8
            r2 = 25165824(0x1800000, float:4.7019774E-38)
            if (r0 != r2) goto L54
            net.bytebuddy.jar.asm.Item[] r0 = r7.typeTable
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r8
            r0 = r0[r2]
            java.lang.String r0 = r0.strVal1
            int r7 = r7.addType(r0)
            goto Ld
        L25:
            r0 = 0
        L26:
            int r1 = r6.initializationCount
            if (r0 >= r1) goto L54
            int[] r1 = r6.initializations
            r1 = r1[r0]
            r2 = -268435456(0xfffffffff0000000, float:-1.5845633E29)
            r2 = r2 & r1
            r3 = 251658240(0xf000000, float:6.3108872E-30)
            r3 = r3 & r1
            r4 = 33554432(0x2000000, float:9.403955E-38)
            r5 = 8388607(0x7fffff, float:1.1754942E-38)
            if (r3 != r4) goto L42
            int[] r3 = r6.inputLocals
            r1 = r1 & r5
            r1 = r3[r1]
        L40:
            int r1 = r1 + r2
            goto L4e
        L42:
            r4 = 50331648(0x3000000, float:3.761582E-37)
            if (r3 != r4) goto L4e
            int[] r3 = r6.inputStack
            int r4 = r3.length
            r1 = r1 & r5
            int r4 = r4 - r1
            r1 = r3[r4]
            goto L40
        L4e:
            if (r8 != r1) goto L51
            return r7
        L51:
            int r0 = r0 + 1
            goto L26
        L54:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.Frame.init(net.bytebuddy.jar.asm.ClassWriter, int):int");
    }

    final void initInputFrame(ClassWriter classWriter, int i, Type[] typeArr, int i2) {
        int i3;
        int[] iArr = new int[i2];
        this.inputLocals = iArr;
        this.inputStack = new int[0];
        if ((i & 8) == 0) {
            i3 = 1;
            if ((i & 524288) == 0) {
                iArr[0] = classWriter.addType(classWriter.thisName) | OBJECT;
            } else {
                iArr[0] = UNINITIALIZED_THIS;
            }
        } else {
            i3 = 0;
        }
        for (Type type : typeArr) {
            int iType = type(classWriter, type.getDescriptor());
            int[] iArr2 = this.inputLocals;
            int i4 = i3 + 1;
            iArr2[i3] = iType;
            if (iType == LONG || iType == DOUBLE) {
                iArr2[i4] = 16777216;
                i3 = i4 + 1;
            } else {
                i3 = i4;
            }
        }
        while (i3 < i2) {
            this.inputLocals[i3] = 16777216;
            i3++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void execute(int r17, int r18, net.bytebuddy.jar.asm.ClassWriter r19, net.bytebuddy.jar.asm.Item r20) {
        /*
            Method dump skipped, instruction units count: 1206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.Frame.execute(int, int, net.bytebuddy.jar.asm.ClassWriter, net.bytebuddy.jar.asm.Item):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0109 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final boolean merge(net.bytebuddy.jar.asm.ClassWriter r19, net.bytebuddy.jar.asm.Frame r20, int r21) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.Frame.merge(net.bytebuddy.jar.asm.ClassWriter, net.bytebuddy.jar.asm.Frame, int):boolean");
    }

    private static boolean merge(ClassWriter classWriter, int i, int[] iArr, int i2) {
        int iMin;
        int iAddType;
        int i3 = iArr[i2];
        if (i3 == i) {
            return false;
        }
        if ((268435455 & i) == NULL) {
            if (i3 == NULL) {
                return false;
            }
            i = NULL;
        }
        if (i3 == 0) {
            iArr[i2] = i;
            return true;
        }
        int i4 = i3 & BASE_KIND;
        int mergedType = 16777216;
        if (i4 == OBJECT || (i3 & (-268435456)) != 0) {
            if (i == NULL) {
                return false;
            }
            if ((i & (-1048576)) != ((-1048576) & i3)) {
                int i5 = i & BASE_KIND;
                if (i5 == OBJECT || (i & (-268435456)) != 0) {
                    int i6 = i & (-268435456);
                    int i7 = ((i6 == 0 || i5 == OBJECT) ? 0 : -268435456) + i6;
                    int i8 = i3 & (-268435456);
                    iMin = Math.min(i7, ((i8 == 0 || i4 == OBJECT) ? 0 : -268435456) + i8) | OBJECT;
                    iAddType = classWriter.addType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                    mergedType = iMin | iAddType;
                }
            } else if (i4 == OBJECT) {
                mergedType = (i & (-268435456)) | OBJECT | classWriter.getMergedType(i & BASE_VALUE, BASE_VALUE & i3);
            } else {
                iMin = ((i3 & (-268435456)) - 268435456) | OBJECT;
                iAddType = classWriter.addType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                mergedType = iMin | iAddType;
            }
        } else if (i3 == NULL) {
            if ((i & BASE_KIND) != OBJECT && (i & (-268435456)) == 0) {
                i = 16777216;
            }
            mergedType = i;
        }
        if (i3 == mergedType) {
            return false;
        }
        iArr[i2] = mergedType;
        return true;
    }
}
