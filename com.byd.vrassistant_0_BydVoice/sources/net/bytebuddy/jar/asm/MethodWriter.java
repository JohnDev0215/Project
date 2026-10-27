package net.bytebuddy.jar.asm;

import com.android.dx.cf.attrib.AttSynthetic;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
class MethodWriter extends MethodVisitor {
    static final int ACC_CONSTRUCTOR = 524288;
    static final int APPEND_FRAME = 252;
    static final int CHOP_FRAME = 248;
    static final int FRAMES = 0;
    static final int FULL_FRAME = 255;
    static final int INSERTED_FRAMES = 1;
    static final int MAXS = 2;
    static final int NOTHING = 3;
    static final int RESERVED = 128;
    static final int SAME_FRAME = 0;
    static final int SAME_FRAME_EXTENDED = 251;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
    private int access;
    private ByteVector annd;
    private AnnotationWriter anns;
    private Attribute attrs;
    private Attribute cattrs;
    int classReaderLength;
    int classReaderOffset;
    private ByteVector code;
    private final int compute;
    private AnnotationWriter ctanns;
    private Label currentBlock;
    private int currentLocals;
    final ClassWriter cw;
    private final int desc;
    private final String descriptor;
    int exceptionCount;
    int[] exceptions;
    private Handler firstHandler;
    private int[] frame;
    int frameCount;
    private int handlerCount;
    private AnnotationWriter ianns;
    private AnnotationWriter ictanns;
    private AnnotationWriter[] ipanns;
    private AnnotationWriter itanns;
    private Label labels;
    private int lastCodeOffset;
    private Handler lastHandler;
    private ByteVector lineNumber;
    private int lineNumberCount;
    private ByteVector localVar;
    private int localVarCount;
    private ByteVector localVarType;
    private int localVarTypeCount;
    private int maxLocals;
    private int maxStack;
    private int maxStackSize;
    private ByteVector methodParameters;
    private int methodParametersCount;
    private final int name;
    private AnnotationWriter[] panns;
    private Label previousBlock;
    private int[] previousFrame;
    private int previousFrameOffset;
    String signature;
    private ByteVector stackMap;
    private int stackSize;
    private int subroutines;
    private int synthetics;
    private AnnotationWriter tanns;

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitCode() {
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitEnd() {
    }

    MethodWriter(ClassWriter classWriter, int i, String str, String str2, String str3, String[] strArr, int i2) {
        super(Opcodes.ASM6);
        this.code = new ByteVector();
        if (classWriter.firstMethod == null) {
            classWriter.firstMethod = this;
        } else {
            classWriter.lastMethod.mv = this;
        }
        classWriter.lastMethod = this;
        this.cw = classWriter;
        this.access = i;
        if (MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(str)) {
            this.access |= 524288;
        }
        this.name = classWriter.newUTF8(str);
        this.desc = classWriter.newUTF8(str2);
        this.descriptor = str2;
        this.signature = str3;
        if (strArr != null && strArr.length > 0) {
            int length = strArr.length;
            this.exceptionCount = length;
            this.exceptions = new int[length];
            for (int i3 = 0; i3 < this.exceptionCount; i3++) {
                this.exceptions[i3] = classWriter.newClass(strArr[i3]);
            }
        }
        this.compute = i2;
        if (i2 != 3) {
            int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(this.descriptor) >> 2;
            argumentsAndReturnSizes = (i & 8) != 0 ? argumentsAndReturnSizes - 1 : argumentsAndReturnSizes;
            this.maxLocals = argumentsAndReturnSizes;
            this.currentLocals = argumentsAndReturnSizes;
            Label label = new Label();
            this.labels = label;
            label.status |= 8;
            visitLabel(this.labels);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitParameter(String str, int i) {
        if (this.methodParameters == null) {
            this.methodParameters = new ByteVector();
        }
        this.methodParametersCount++;
        this.methodParameters.putShort(str == null ? 0 : this.cw.newUTF8(str)).putShort(i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitAnnotationDefault() {
        this.annd = new ByteVector();
        return new AnnotationWriter(this.cw, false, this.annd, null, 0);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.cw.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.next = this.anns;
            this.anns = annotationWriter;
        } else {
            annotationWriter.next = this.ianns;
            this.ianns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.putTarget(i, typePath, byteVector);
        byteVector.putShort(this.cw.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (z) {
            annotationWriter.next = this.tanns;
            this.tanns = annotationWriter;
        } else {
            annotationWriter.next = this.itanns;
            this.itanns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        if ("Ljava/lang/Synthetic;".equals(str)) {
            this.synthetics = Math.max(this.synthetics, i + 1);
            return new AnnotationWriter(this.cw, false, byteVector, null, 0);
        }
        byteVector.putShort(this.cw.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, 2);
        if (z) {
            if (this.panns == null) {
                this.panns = new AnnotationWriter[Type.getArgumentTypes(this.descriptor).length];
            }
            annotationWriter.next = this.panns[i];
            this.panns[i] = annotationWriter;
        } else {
            if (this.ipanns == null) {
                this.ipanns = new AnnotationWriter[Type.getArgumentTypes(this.descriptor).length];
            }
            annotationWriter.next = this.ipanns[i];
            this.ipanns[i] = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitAttribute(Attribute attribute) {
        if (attribute.isCodeAttribute()) {
            attribute.next = this.cattrs;
            this.cattrs = attribute;
        } else {
            attribute.next = this.attrs;
            this.attrs = attribute;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        int i4;
        int i5;
        int i6;
        int i7 = this.compute;
        if (i7 == 0) {
            return;
        }
        if (i7 != 1) {
            int i8 = 0;
            if (i == -1) {
                if (this.previousFrame == null) {
                    visitImplicitFirstFrame();
                }
                this.currentLocals = i2;
                int iStartFrame = startFrame(this.code.length, i2, i3);
                for (int i9 = 0; i9 < i2; i9++) {
                    Object obj = objArr[i9];
                    if (obj instanceof String) {
                        i6 = iStartFrame + 1;
                        this.frame[iStartFrame] = Frame.type(this.cw, Type.getObjectType((String) obj).getDescriptor());
                    } else if (obj instanceof Integer) {
                        i6 = iStartFrame + 1;
                        this.frame[iStartFrame] = 16777216 | ((Integer) obj).intValue();
                    } else {
                        this.frame[iStartFrame] = this.cw.addUninitializedType("", ((Label) obj).position) | 25165824;
                        iStartFrame++;
                    }
                    iStartFrame = i6;
                }
                while (i8 < i3) {
                    Object obj2 = objArr2[i8];
                    if (obj2 instanceof String) {
                        i5 = iStartFrame + 1;
                        this.frame[iStartFrame] = Frame.type(this.cw, Type.getObjectType((String) obj2).getDescriptor());
                    } else if (obj2 instanceof Integer) {
                        i5 = iStartFrame + 1;
                        this.frame[iStartFrame] = ((Integer) obj2).intValue() | 16777216;
                    } else {
                        i5 = iStartFrame + 1;
                        this.frame[iStartFrame] = this.cw.addUninitializedType("", ((Label) obj2).position) | 25165824;
                    }
                    iStartFrame = i5;
                    i8++;
                }
                endFrame();
            } else {
                if (this.stackMap == null) {
                    this.stackMap = new ByteVector();
                    i4 = this.code.length;
                } else {
                    i4 = (this.code.length - this.previousFrameOffset) - 1;
                    if (i4 < 0) {
                        if (i != 3) {
                            throw new IllegalStateException();
                        }
                        return;
                    }
                }
                if (i == 0) {
                    this.currentLocals = i2;
                    this.stackMap.putByte(255).putShort(i4).putShort(i2);
                    for (int i10 = 0; i10 < i2; i10++) {
                        writeFrameType(objArr[i10]);
                    }
                    this.stackMap.putShort(i3);
                    while (i8 < i3) {
                        writeFrameType(objArr2[i8]);
                        i8++;
                    }
                } else if (i == 1) {
                    this.currentLocals += i2;
                    this.stackMap.putByte(i2 + 251).putShort(i4);
                    while (i8 < i2) {
                        writeFrameType(objArr[i8]);
                        i8++;
                    }
                } else if (i == 2) {
                    this.currentLocals -= i2;
                    this.stackMap.putByte(251 - i2).putShort(i4);
                } else if (i != 3) {
                    if (i == 4) {
                        if (i4 < 64) {
                            this.stackMap.putByte(i4 + 64);
                        } else {
                            this.stackMap.putByte(247).putShort(i4);
                        }
                        writeFrameType(objArr2[0]);
                    }
                } else if (i4 < 64) {
                    this.stackMap.putByte(i4);
                } else {
                    this.stackMap.putByte(251).putShort(i4);
                }
                this.previousFrameOffset = this.code.length;
                this.frameCount++;
            }
        } else if (this.currentBlock.frame == null) {
            this.currentBlock.frame = new CurrentFrame();
            this.currentBlock.frame.owner = this.currentBlock;
            this.currentBlock.frame.initInputFrame(this.cw, this.access, Type.getArgumentTypes(this.descriptor), i2);
            visitImplicitFirstFrame();
        } else {
            if (i == -1) {
                this.currentBlock.frame.set(this.cw, i2, objArr, i3, objArr2);
            }
            visitFrame(this.currentBlock.frame);
        }
        this.maxStack = Math.max(this.maxStack, i3);
        this.maxLocals = Math.max(this.maxLocals, this.currentLocals);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInsn(int i) {
        this.lastCodeOffset = this.code.length;
        this.code.putByte(i);
        Label label = this.currentBlock;
        if (label != null) {
            int i2 = this.compute;
            if (i2 == 0 || i2 == 1) {
                label.frame.execute(i, 0, null, null);
            } else {
                int i3 = this.stackSize + Frame.SIZE[i];
                if (i3 > this.maxStackSize) {
                    this.maxStackSize = i3;
                }
                this.stackSize = i3;
            }
            if ((i < 172 || i > 177) && i != 191) {
                return;
            }
            noSuccessor();
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        this.lastCodeOffset = this.code.length;
        Label label = this.currentBlock;
        if (label != null) {
            int i3 = this.compute;
            if (i3 == 0 || i3 == 1) {
                label.frame.execute(i, i2, null, null);
            } else if (i != 188) {
                int i4 = this.stackSize + 1;
                if (i4 > this.maxStackSize) {
                    this.maxStackSize = i4;
                }
                this.stackSize = i4;
            }
        }
        if (i == 17) {
            this.code.put12(i, i2);
        } else {
            this.code.put11(i, i2);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        this.lastCodeOffset = this.code.length;
        Label label = this.currentBlock;
        if (label != null) {
            int i3 = this.compute;
            if (i3 == 0 || i3 == 1) {
                label.frame.execute(i, i2, null, null);
            } else if (i == 169) {
                label.status |= 256;
                this.currentBlock.inputStackTop = this.stackSize;
                noSuccessor();
            } else {
                int i4 = this.stackSize + Frame.SIZE[i];
                if (i4 > this.maxStackSize) {
                    this.maxStackSize = i4;
                }
                this.stackSize = i4;
            }
        }
        if (this.compute != 3) {
            int i5 = (i == 22 || i == 24 || i == 55 || i == 57) ? i2 + 2 : i2 + 1;
            if (i5 > this.maxLocals) {
                this.maxLocals = i5;
            }
        }
        if (i2 < 4 && i != 169) {
            this.code.putByte((i < 54 ? ((i - 21) << 2) + 26 : ((i - 54) << 2) + 59) + i2);
        } else if (i2 >= 256) {
            this.code.putByte(196).put12(i, i2);
        } else {
            this.code.put11(i, i2);
        }
        if (i < 54 || this.compute != 0 || this.handlerCount <= 0) {
            return;
        }
        visitLabel(new Label());
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        this.lastCodeOffset = this.code.length;
        Item itemNewStringishItem = this.cw.newStringishItem(7, str);
        Label label = this.currentBlock;
        if (label != null) {
            int i2 = this.compute;
            if (i2 == 0 || i2 == 1) {
                label.frame.execute(i, this.code.length, this.cw, itemNewStringishItem);
            } else if (i == 187) {
                int i3 = this.stackSize + 1;
                if (i3 > this.maxStackSize) {
                    this.maxStackSize = i3;
                }
                this.stackSize = i3;
            }
        }
        this.code.put12(i, itemNewStringishItem.index);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0053  */
    @Override // net.bytebuddy.jar.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitFieldInsn(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r4 = this;
            net.bytebuddy.jar.asm.ByteVector r0 = r4.code
            int r0 = r0.length
            r4.lastCodeOffset = r0
            net.bytebuddy.jar.asm.ClassWriter r0 = r4.cw
            net.bytebuddy.jar.asm.Item r6 = r0.newFieldItem(r6, r7, r8)
            net.bytebuddy.jar.asm.Label r7 = r4.currentBlock
            if (r7 == 0) goto L5a
            int r0 = r4.compute
            r1 = 0
            if (r0 == 0) goto L53
            r2 = 1
            if (r0 != r2) goto L19
            goto L53
        L19:
            char r7 = r8.charAt(r1)
            r8 = -2
            r0 = 74
            r3 = 68
            switch(r5) {
                case 178: goto L3f;
                case 179: goto L35;
                case 180: goto L2c;
                default: goto L25;
            }
        L25:
            int r1 = r4.stackSize
            if (r7 == r3) goto L48
            if (r7 != r0) goto L3d
            goto L48
        L2c:
            int r8 = r4.stackSize
            if (r7 == r3) goto L32
            if (r7 != r0) goto L33
        L32:
            r1 = r2
        L33:
            int r8 = r8 + r1
            goto L4a
        L35:
            int r1 = r4.stackSize
            if (r7 == r3) goto L3d
            if (r7 != r0) goto L3c
            goto L3d
        L3c:
            r8 = -1
        L3d:
            int r8 = r8 + r1
            goto L4a
        L3f:
            int r8 = r4.stackSize
            if (r7 == r3) goto L45
            if (r7 != r0) goto L46
        L45:
            r2 = 2
        L46:
            int r8 = r8 + r2
            goto L4a
        L48:
            r8 = -3
            goto L3d
        L4a:
            int r7 = r4.maxStackSize
            if (r8 <= r7) goto L50
            r4.maxStackSize = r8
        L50:
            r4.stackSize = r8
            goto L5a
        L53:
            net.bytebuddy.jar.asm.Frame r7 = r7.frame
            net.bytebuddy.jar.asm.ClassWriter r8 = r4.cw
            r7.execute(r5, r1, r8, r6)
        L5a:
            net.bytebuddy.jar.asm.ByteVector r7 = r4.code
            int r6 = r6.index
            r7.put12(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.MethodWriter.visitFieldInsn(int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        int i2;
        this.lastCodeOffset = this.code.length;
        Item itemNewMethodItem = this.cw.newMethodItem(str, str2, str3, z);
        int argumentsAndReturnSizes = itemNewMethodItem.intVal;
        Label label = this.currentBlock;
        if (label != null) {
            int i3 = this.compute;
            if (i3 == 0 || i3 == 1) {
                label.frame.execute(i, 0, this.cw, itemNewMethodItem);
            } else {
                if (argumentsAndReturnSizes == 0) {
                    argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str3);
                    itemNewMethodItem.intVal = argumentsAndReturnSizes;
                }
                if (i == 184) {
                    i2 = (this.stackSize - (argumentsAndReturnSizes >> 2)) + (argumentsAndReturnSizes & 3) + 1;
                } else {
                    i2 = (this.stackSize - (argumentsAndReturnSizes >> 2)) + (argumentsAndReturnSizes & 3);
                }
                if (i2 > this.maxStackSize) {
                    this.maxStackSize = i2;
                }
                this.stackSize = i2;
            }
        }
        if (i == 185) {
            if (argumentsAndReturnSizes == 0) {
                argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str3);
                itemNewMethodItem.intVal = argumentsAndReturnSizes;
            }
            this.code.put12(185, itemNewMethodItem.index).put11(argumentsAndReturnSizes >> 2, 0);
            return;
        }
        this.code.put12(i, itemNewMethodItem.index);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        this.lastCodeOffset = this.code.length;
        Item itemNewInvokeDynamicItem = this.cw.newInvokeDynamicItem(str, str2, handle, objArr);
        int argumentsAndReturnSizes = itemNewInvokeDynamicItem.intVal;
        Label label = this.currentBlock;
        if (label != null) {
            int i = this.compute;
            if (i == 0 || i == 1) {
                label.frame.execute(186, 0, this.cw, itemNewInvokeDynamicItem);
            } else {
                if (argumentsAndReturnSizes == 0) {
                    argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str2);
                    itemNewInvokeDynamicItem.intVal = argumentsAndReturnSizes;
                }
                int i2 = (this.stackSize - (argumentsAndReturnSizes >> 2)) + (argumentsAndReturnSizes & 3) + 1;
                if (i2 > this.maxStackSize) {
                    this.maxStackSize = i2;
                }
                this.stackSize = i2;
            }
        }
        this.code.put12(186, itemNewInvokeDynamicItem.index);
        this.code.putShort(0);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitJumpInsn(int i, Label label) {
        boolean z = i >= 200;
        if (z) {
            i -= 33;
        }
        this.lastCodeOffset = this.code.length;
        Label label2 = this.currentBlock;
        Label label3 = null;
        if (label2 != null) {
            int i2 = this.compute;
            if (i2 == 0) {
                label2.frame.execute(i, 0, null, null);
                label.getFirst().status |= 16;
                addSuccessor(0, label);
                if (i != 167) {
                    label3 = new Label();
                }
            } else if (i2 == 1) {
                label2.frame.execute(i, 0, null, null);
            } else if (i == 168) {
                if ((label.status & 512) == 0) {
                    label.status |= 512;
                    this.subroutines++;
                }
                this.currentBlock.status |= 128;
                addSuccessor(this.stackSize + 1, label);
                label3 = new Label();
            } else {
                int i3 = this.stackSize + Frame.SIZE[i];
                this.stackSize = i3;
                addSuccessor(i3, label);
            }
        }
        if ((label.status & 2) != 0 && label.position - this.code.length < -32768) {
            if (i == 167) {
                this.code.putByte(200);
            } else if (i == 168) {
                this.code.putByte(201);
            } else {
                if (label3 != null) {
                    label3.status |= 16;
                }
                this.code.putByte(i <= 166 ? ((i + 1) ^ 1) - 1 : i ^ 1);
                this.code.putShort(8);
                this.code.putByte(220);
                this.cw.hasAsmInsns = true;
            }
            ByteVector byteVector = this.code;
            label.put(this, byteVector, byteVector.length - 1, true);
        } else if (z) {
            this.code.putByte(i + 33);
            ByteVector byteVector2 = this.code;
            label.put(this, byteVector2, byteVector2.length - 1, true);
        } else {
            this.code.putByte(i);
            ByteVector byteVector3 = this.code;
            label.put(this, byteVector3, byteVector3.length - 1, false);
        }
        if (this.currentBlock != null) {
            if (label3 != null) {
                visitLabel(label3);
            }
            if (i == 167) {
                noSuccessor();
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLabel(Label label) {
        this.cw.hasAsmInsns |= label.resolve(this, this.code.length, this.code.data);
        if ((label.status & 1) != 0) {
            return;
        }
        int i = this.compute;
        if (i == 0) {
            if (this.currentBlock != null) {
                if (label.position == this.currentBlock.position) {
                    this.currentBlock.status |= label.status & 16;
                    label.frame = this.currentBlock.frame;
                    return;
                }
                addSuccessor(0, label);
            }
            this.currentBlock = label;
            if (label.frame == null) {
                label.frame = new Frame();
                label.frame.owner = label;
            }
            if (this.previousBlock != null) {
                if (label.position == this.previousBlock.position) {
                    this.previousBlock.status |= label.status & 16;
                    label.frame = this.previousBlock.frame;
                    this.currentBlock = this.previousBlock;
                    return;
                }
                this.previousBlock.successor = label;
            }
            this.previousBlock = label;
            return;
        }
        if (i == 1) {
            Label label2 = this.currentBlock;
            if (label2 == null) {
                this.currentBlock = label;
                return;
            } else {
                label2.frame.owner = label;
                return;
            }
        }
        if (i == 2) {
            Label label3 = this.currentBlock;
            if (label3 != null) {
                label3.outputStackMax = this.maxStackSize;
                addSuccessor(this.stackSize, label);
            }
            this.currentBlock = label;
            this.stackSize = 0;
            this.maxStackSize = 0;
            Label label4 = this.previousBlock;
            if (label4 != null) {
                label4.successor = label;
            }
            this.previousBlock = label;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        int i;
        this.lastCodeOffset = this.code.length;
        Item itemNewConstItem = this.cw.newConstItem(obj);
        Label label = this.currentBlock;
        if (label != null) {
            int i2 = this.compute;
            if (i2 == 0 || i2 == 1) {
                label.frame.execute(18, 0, this.cw, itemNewConstItem);
            } else {
                if (itemNewConstItem.type == 5 || itemNewConstItem.type == 6) {
                    i = this.stackSize + 2;
                } else {
                    i = this.stackSize + 1;
                }
                if (i > this.maxStackSize) {
                    this.maxStackSize = i;
                }
                this.stackSize = i;
            }
        }
        int i3 = itemNewConstItem.index;
        if (itemNewConstItem.type == 5 || itemNewConstItem.type == 6) {
            this.code.put12(20, i3);
        } else if (i3 >= 256) {
            this.code.put12(19, i3);
        } else {
            this.code.put11(18, i3);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIincInsn(int i, int i2) {
        int i3;
        int i4;
        this.lastCodeOffset = this.code.length;
        Label label = this.currentBlock;
        if (label != null && ((i4 = this.compute) == 0 || i4 == 1)) {
            label.frame.execute(132, i, null, null);
        }
        if (this.compute != 3 && (i3 = i + 1) > this.maxLocals) {
            this.maxLocals = i3;
        }
        if (i > 255 || i2 > 127 || i2 < -128) {
            this.code.putByte(196).put12(132, i).putShort(i2);
        } else {
            this.code.putByte(132).put11(i, i2);
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        this.lastCodeOffset = this.code.length;
        int i3 = this.code.length;
        this.code.putByte(170);
        ByteVector byteVector = this.code;
        byteVector.putByteArray(null, 0, (4 - (byteVector.length % 4)) % 4);
        label.put(this, this.code, i3, true);
        this.code.putInt(i).putInt(i2);
        for (Label label2 : labelArr) {
            label2.put(this, this.code, i3, true);
        }
        visitSwitchInsn(label, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.lastCodeOffset = this.code.length;
        int i = this.code.length;
        this.code.putByte(171);
        ByteVector byteVector = this.code;
        byteVector.putByteArray(null, 0, (4 - (byteVector.length % 4)) % 4);
        label.put(this, this.code, i, true);
        this.code.putInt(labelArr.length);
        for (int i2 = 0; i2 < labelArr.length; i2++) {
            this.code.putInt(iArr[i2]);
            labelArr[i2].put(this, this.code, i, true);
        }
        visitSwitchInsn(label, labelArr);
    }

    private void visitSwitchInsn(Label label, Label[] labelArr) {
        Label label2 = this.currentBlock;
        if (label2 != null) {
            if (this.compute == 0) {
                label2.frame.execute(171, 0, null, null);
                addSuccessor(0, label);
                label.getFirst().status |= 16;
                for (int i = 0; i < labelArr.length; i++) {
                    addSuccessor(0, labelArr[i]);
                    labelArr[i].getFirst().status |= 16;
                }
            } else {
                int i2 = this.stackSize - 1;
                this.stackSize = i2;
                addSuccessor(i2, label);
                for (Label label3 : labelArr) {
                    addSuccessor(this.stackSize, label3);
                }
            }
            noSuccessor();
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        this.lastCodeOffset = this.code.length;
        Item itemNewStringishItem = this.cw.newStringishItem(7, str);
        Label label = this.currentBlock;
        if (label != null) {
            int i2 = this.compute;
            if (i2 == 0 || i2 == 1) {
                label.frame.execute(197, i, this.cw, itemNewStringishItem);
            } else {
                this.stackSize += 1 - i;
            }
        }
        this.code.put12(197, itemNewStringishItem.index).putByte(i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitInsnAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.putTarget((i & (-16776961)) | (this.lastCodeOffset << 8), typePath, byteVector);
        byteVector.putShort(this.cw.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (z) {
            annotationWriter.next = this.ctanns;
            this.ctanns = annotationWriter;
        } else {
            annotationWriter.next = this.ictanns;
            this.ictanns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        this.handlerCount++;
        Handler handler = new Handler();
        handler.start = label;
        handler.end = label2;
        handler.handler = label3;
        handler.desc = str;
        handler.type = str != null ? this.cw.newClass(str) : 0;
        Handler handler2 = this.lastHandler;
        if (handler2 == null) {
            this.firstHandler = handler;
        } else {
            handler2.next = handler;
        }
        this.lastHandler = handler;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.putTarget(i, typePath, byteVector);
        byteVector.putShort(this.cw.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (z) {
            annotationWriter.next = this.ctanns;
            this.ctanns = annotationWriter;
        } else {
            annotationWriter.next = this.ictanns;
            this.ictanns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        if (str3 != null) {
            if (this.localVarType == null) {
                this.localVarType = new ByteVector();
            }
            this.localVarTypeCount++;
            this.localVarType.putShort(label.position).putShort(label2.position - label.position).putShort(this.cw.newUTF8(str)).putShort(this.cw.newUTF8(str3)).putShort(i);
        }
        if (this.localVar == null) {
            this.localVar = new ByteVector();
        }
        this.localVarCount++;
        this.localVar.putShort(label.position).putShort(label2.position - label.position).putShort(this.cw.newUTF8(str)).putShort(this.cw.newUTF8(str2)).putShort(i);
        if (this.compute != 3) {
            char cCharAt = str2.charAt(0);
            int i2 = i + ((cCharAt == 'J' || cCharAt == 'D') ? 2 : 1);
            if (i2 > this.maxLocals) {
                this.maxLocals = i2;
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(i >>> 24).putShort(labelArr.length);
        for (int i2 = 0; i2 < labelArr.length; i2++) {
            byteVector.putShort(labelArr[i2].position).putShort(labelArr2[i2].position - labelArr[i2].position).putShort(iArr[i2]);
        }
        if (typePath == null) {
            byteVector.putByte(0);
        } else {
            byteVector.putByteArray(typePath.b, typePath.offset, (typePath.b[typePath.offset] * 2) + 1);
        }
        byteVector.putShort(this.cw.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (z) {
            annotationWriter.next = this.ctanns;
            this.ctanns = annotationWriter;
        } else {
            annotationWriter.next = this.ictanns;
            this.ictanns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLineNumber(int i, Label label) {
        if (this.lineNumber == null) {
            this.lineNumber = new ByteVector();
        }
        this.lineNumberCount++;
        this.lineNumber.putShort(label.position);
        this.lineNumber.putShort(i);
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMaxs(int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 501
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.MethodWriter.visitMaxs(int, int):void");
    }

    private void addSuccessor(int i, Label label) {
        Edge edge = new Edge();
        edge.info = i;
        edge.successor = label;
        edge.next = this.currentBlock.successors;
        this.currentBlock.successors = edge;
    }

    private void noSuccessor() {
        if (this.compute == 0) {
            Label label = new Label();
            label.frame = new Frame();
            label.frame.owner = label;
            label.resolve(this, this.code.length, this.code.data);
            this.previousBlock.successor = label;
            this.previousBlock = label;
        } else {
            this.currentBlock.outputStackMax = this.maxStackSize;
        }
        if (this.compute != 1) {
            this.currentBlock = null;
        }
    }

    private void visitFrame(Frame frame) {
        int[] iArr = frame.inputLocals;
        int[] iArr2 = frame.inputStack;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < iArr.length) {
            int i5 = iArr[i2];
            if (i5 == 16777216) {
                i4++;
            } else {
                i3 += i4 + 1;
                i4 = 0;
            }
            if (i5 == 16777220 || i5 == 16777219) {
                i2++;
            }
            i2++;
        }
        int i6 = 0;
        int i7 = 0;
        while (i6 < iArr2.length) {
            int i8 = iArr2[i6];
            i7++;
            if (i8 == 16777220 || i8 == 16777219) {
                i6++;
            }
            i6++;
        }
        int iStartFrame = startFrame(frame.owner.position, i3, i7);
        int i9 = 0;
        while (i3 > 0) {
            int i10 = iArr[i9];
            int i11 = iStartFrame + 1;
            this.frame[iStartFrame] = i10;
            if (i10 == 16777220 || i10 == 16777219) {
                i9++;
            }
            i9++;
            i3--;
            iStartFrame = i11;
        }
        while (i < iArr2.length) {
            int i12 = iArr2[i];
            int i13 = iStartFrame + 1;
            this.frame[iStartFrame] = i12;
            if (i12 == 16777220 || i12 == 16777219) {
                i++;
            }
            i++;
            iStartFrame = i13;
        }
        endFrame();
    }

    private void visitImplicitFirstFrame() {
        int i;
        int i2;
        int iStartFrame = startFrame(0, this.descriptor.length() + 1, 0);
        int i3 = this.access;
        if ((i3 & 8) == 0) {
            if ((i3 & 524288) == 0) {
                int[] iArr = this.frame;
                i2 = iStartFrame + 1;
                ClassWriter classWriter = this.cw;
                iArr[iStartFrame] = classWriter.addType(classWriter.thisName) | 24117248;
            } else {
                i2 = iStartFrame + 1;
                this.frame[iStartFrame] = 16777222;
            }
            iStartFrame = i2;
        }
        int i4 = 1;
        while (true) {
            int i5 = i4 + 1;
            char cCharAt = this.descriptor.charAt(i4);
            if (cCharAt == 'F') {
                i = iStartFrame + 1;
                this.frame[iStartFrame] = 16777218;
            } else if (cCharAt != 'L') {
                if (cCharAt != 'S' && cCharAt != 'I') {
                    if (cCharAt == 'J') {
                        i = iStartFrame + 1;
                        this.frame[iStartFrame] = 16777220;
                    } else if (cCharAt != 'Z') {
                        if (cCharAt != '[') {
                            switch (cCharAt) {
                                case 'B':
                                case 'C':
                                    break;
                                case 'D':
                                    i = iStartFrame + 1;
                                    this.frame[iStartFrame] = 16777219;
                                    break;
                                default:
                                    this.frame[1] = iStartFrame - 3;
                                    endFrame();
                                    return;
                            }
                        } else {
                            while (this.descriptor.charAt(i5) == '[') {
                                i5++;
                            }
                            if (this.descriptor.charAt(i5) == 'L') {
                                do {
                                    i5++;
                                } while (this.descriptor.charAt(i5) != ';');
                            }
                            int i6 = i5 + 1;
                            this.frame[iStartFrame] = Frame.type(this.cw, this.descriptor.substring(i4, i6));
                            i4 = i6;
                            iStartFrame++;
                        }
                    }
                }
                i = iStartFrame + 1;
                this.frame[iStartFrame] = 16777217;
            } else {
                int i7 = i5;
                while (this.descriptor.charAt(i7) != ';') {
                    i7++;
                }
                this.frame[iStartFrame] = this.cw.addType(this.descriptor.substring(i5, i7)) | 24117248;
                iStartFrame++;
                i4 = i7 + 1;
            }
            iStartFrame = i;
            i4 = i5;
        }
    }

    private int startFrame(int i, int i2, int i3) {
        int i4 = i2 + 3 + i3;
        int[] iArr = this.frame;
        if (iArr == null || iArr.length < i4) {
            this.frame = new int[i4];
        }
        int[] iArr2 = this.frame;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
        return 3;
    }

    private void endFrame() {
        if (this.previousFrame != null) {
            if (this.stackMap == null) {
                this.stackMap = new ByteVector();
            }
            writeFrame();
            this.frameCount++;
        }
        this.previousFrame = this.frame;
        this.frame = null;
    }

    private void writeFrame() {
        int i;
        char c;
        int[] iArr = this.frame;
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = 0;
        if ((this.cw.version & 65535) < 50) {
            this.stackMap.putShort(this.frame[0]).putShort(i2);
            int i5 = i2 + 3;
            writeFrameTypes(3, i5);
            this.stackMap.putShort(i3);
            writeFrameTypes(i5, i3 + i5);
            return;
        }
        int[] iArr2 = this.previousFrame;
        int i6 = iArr2[1];
        int i7 = this.frameCount == 0 ? this.frame[0] : (this.frame[0] - iArr2[0]) - 1;
        if (i3 == 0) {
            i = i2 - i6;
            switch (i) {
                case -3:
                case -2:
                case -1:
                    i6 = i2;
                    c = 248;
                    break;
                case 0:
                    c = i7 >= 64 ? (char) 251 : (char) 0;
                    break;
                case 1:
                case 2:
                case 3:
                    c = 252;
                    break;
                default:
                    c = 255;
                    break;
            }
        } else if (i2 == i6 && i3 == 1) {
            c = i7 < 63 ? '@' : (char) 247;
            i = 0;
        } else {
            i = 0;
            c = 255;
        }
        if (c != 255) {
            int i8 = 3;
            while (true) {
                if (i4 < i6) {
                    if (this.frame[i8] != this.previousFrame[i8]) {
                        c = 255;
                    } else {
                        i8++;
                        i4++;
                    }
                }
            }
        }
        if (c == 0) {
            this.stackMap.putByte(i7);
            return;
        }
        if (c == '@') {
            this.stackMap.putByte(i7 + 64);
            writeFrameTypes(i2 + 3, i2 + 4);
            return;
        }
        if (c == 247) {
            this.stackMap.putByte(247).putShort(i7);
            writeFrameTypes(i2 + 3, i2 + 4);
            return;
        }
        if (c == 248) {
            this.stackMap.putByte(i + 251).putShort(i7);
            return;
        }
        if (c == 251) {
            this.stackMap.putByte(251).putShort(i7);
            return;
        }
        if (c == 252) {
            this.stackMap.putByte(i + 251).putShort(i7);
            writeFrameTypes(i6 + 3, i2 + 3);
            return;
        }
        this.stackMap.putByte(255).putShort(i7).putShort(i2);
        int i9 = i2 + 3;
        writeFrameTypes(3, i9);
        this.stackMap.putShort(i3);
        writeFrameTypes(i9, i3 + i9);
    }

    private void writeFrameTypes(int i, int i2) {
        while (i < i2) {
            int i3 = this.frame[i];
            int i4 = (-268435456) & i3;
            if (i4 == 0) {
                int i5 = i3 & 1048575;
                int i6 = i3 & 267386880;
                if (i6 == 24117248) {
                    ByteVector byteVectorPutByte = this.stackMap.putByte(7);
                    ClassWriter classWriter = this.cw;
                    byteVectorPutByte.putShort(classWriter.newClass(classWriter.typeTable[i5].strVal1));
                } else if (i6 == 25165824) {
                    this.stackMap.putByte(8).putShort(this.cw.typeTable[i5].intVal);
                } else {
                    this.stackMap.putByte(i5);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                int i7 = i4 >> 28;
                while (true) {
                    int i8 = i7 - 1;
                    if (i7 > 0) {
                        sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
                        i7 = i8;
                    } else {
                        if ((i3 & 267386880) == 24117248) {
                            sb.append('L');
                            sb.append(this.cw.typeTable[i3 & 1048575].strVal1);
                            sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INDEXED_TYPE_DELIMITER);
                        } else {
                            int i9 = i3 & 15;
                            if (i9 == 1) {
                                sb.append('I');
                            } else if (i9 == 2) {
                                sb.append('F');
                            } else if (i9 == 3) {
                                sb.append('D');
                            } else {
                                switch (i9) {
                                    case 9:
                                        sb.append('Z');
                                        break;
                                    case 10:
                                        sb.append('B');
                                        break;
                                    case 11:
                                        sb.append('C');
                                        break;
                                    case 12:
                                        sb.append('S');
                                        break;
                                    default:
                                        sb.append('J');
                                        break;
                                }
                            }
                        }
                        this.stackMap.putByte(7).putShort(this.cw.newClass(sb.toString()));
                    }
                }
            }
            i++;
        }
    }

    private void writeFrameType(Object obj) {
        if (obj instanceof String) {
            this.stackMap.putByte(7).putShort(this.cw.newClass((String) obj));
        } else if (obj instanceof Integer) {
            this.stackMap.putByte(((Integer) obj).intValue());
        } else {
            this.stackMap.putByte(8).putShort(((Label) obj).position);
        }
    }

    final int getSize() {
        int length;
        if (this.classReaderOffset != 0) {
            return this.classReaderLength + 6;
        }
        if (this.code.length <= 0) {
            length = 8;
        } else {
            if (this.code.length > 65535) {
                throw new RuntimeException("Method code too large!");
            }
            this.cw.newUTF8("Code");
            length = this.code.length + 18 + (this.handlerCount * 8) + 8;
            if (this.localVar != null) {
                this.cw.newUTF8("LocalVariableTable");
                length += this.localVar.length + 8;
            }
            if (this.localVarType != null) {
                this.cw.newUTF8("LocalVariableTypeTable");
                length += this.localVarType.length + 8;
            }
            if (this.lineNumber != null) {
                this.cw.newUTF8("LineNumberTable");
                length += this.lineNumber.length + 8;
            }
            if (this.stackMap != null) {
                this.cw.newUTF8((this.cw.version & 65535) >= 50 ? "StackMapTable" : "StackMap");
                length += this.stackMap.length + 8;
            }
            if (this.ctanns != null) {
                this.cw.newUTF8("RuntimeVisibleTypeAnnotations");
                length += this.ctanns.getSize() + 8;
            }
            if (this.ictanns != null) {
                this.cw.newUTF8("RuntimeInvisibleTypeAnnotations");
                length += this.ictanns.getSize() + 8;
            }
            Attribute attribute = this.cattrs;
            if (attribute != null) {
                length += attribute.getSize(this.cw, this.code.data, this.code.length, this.maxStack, this.maxLocals);
            }
        }
        if (this.exceptionCount > 0) {
            this.cw.newUTF8("Exceptions");
            length += (this.exceptionCount * 2) + 8;
        }
        if ((this.access & 4096) != 0 && ((65535 & this.cw.version) < 49 || (this.access & 262144) != 0)) {
            this.cw.newUTF8(AttSynthetic.ATTRIBUTE_NAME);
            length += 6;
        }
        if ((this.access & 131072) != 0) {
            this.cw.newUTF8("Deprecated");
            length += 6;
        }
        if (this.signature != null) {
            this.cw.newUTF8("Signature");
            this.cw.newUTF8(this.signature);
            length += 8;
        }
        if (this.methodParameters != null) {
            this.cw.newUTF8("MethodParameters");
            length += this.methodParameters.length + 7;
        }
        if (this.annd != null) {
            this.cw.newUTF8("AnnotationDefault");
            length += this.annd.length + 6;
        }
        if (this.anns != null) {
            this.cw.newUTF8("RuntimeVisibleAnnotations");
            length += this.anns.getSize() + 8;
        }
        if (this.ianns != null) {
            this.cw.newUTF8("RuntimeInvisibleAnnotations");
            length += this.ianns.getSize() + 8;
        }
        if (this.tanns != null) {
            this.cw.newUTF8("RuntimeVisibleTypeAnnotations");
            length += this.tanns.getSize() + 8;
        }
        if (this.itanns != null) {
            this.cw.newUTF8("RuntimeInvisibleTypeAnnotations");
            length += this.itanns.getSize() + 8;
        }
        if (this.panns != null) {
            this.cw.newUTF8("RuntimeVisibleParameterAnnotations");
            AnnotationWriter[] annotationWriterArr = this.panns;
            length += ((annotationWriterArr.length - this.synthetics) * 2) + 7;
            for (int length2 = annotationWriterArr.length - 1; length2 >= this.synthetics; length2--) {
                AnnotationWriter annotationWriter = this.panns[length2];
                length += annotationWriter == null ? 0 : annotationWriter.getSize();
            }
        }
        if (this.ipanns != null) {
            this.cw.newUTF8("RuntimeInvisibleParameterAnnotations");
            AnnotationWriter[] annotationWriterArr2 = this.ipanns;
            length += ((annotationWriterArr2.length - this.synthetics) * 2) + 7;
            for (int length3 = annotationWriterArr2.length - 1; length3 >= this.synthetics; length3--) {
                AnnotationWriter annotationWriter2 = this.ipanns[length3];
                length += annotationWriter2 == null ? 0 : annotationWriter2.getSize();
            }
        }
        Attribute attribute2 = this.attrs;
        return attribute2 != null ? length + attribute2.getSize(this.cw, null, 0, -1, -1) : length;
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x0294  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void put(net.bytebuddy.jar.asm.ByteVector r23) {
        /*
            Method dump skipped, instruction units count: 1001
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.MethodWriter.put(net.bytebuddy.jar.asm.ByteVector):void");
    }
}
