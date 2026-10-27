package com.android.dx.cf.code;

import com.android.dx.cf.code.BytecodeArray;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Simulator {
    private static final String LOCAL_MISMATCH_ERROR = "This is symptomatic of .class transformation tools that ignore local variable information.";
    private final BytecodeArray code;
    private final LocalVariableList localVariables;
    private final Machine machine;
    private final SimVisitor visitor;

    public Simulator(Machine machine, ConcreteMethod concreteMethod) {
        if (machine == null) {
            throw new NullPointerException("machine == null");
        }
        if (concreteMethod == null) {
            throw new NullPointerException("method == null");
        }
        this.machine = machine;
        this.code = concreteMethod.getCode();
        this.localVariables = concreteMethod.getLocalVariables();
        this.visitor = new SimVisitor();
    }

    public void simulate(ByteBlock byteBlock, Frame frame) {
        int end = byteBlock.getEnd();
        this.visitor.setFrame(frame);
        try {
            int start = byteBlock.getStart();
            while (start < end) {
                int instruction = this.code.parseInstruction(start, this.visitor);
                this.visitor.setPreviousOffset(start);
                start += instruction;
            }
        } catch (SimException e) {
            frame.annotate(e);
            throw e;
        }
    }

    public int simulate(int i, Frame frame) {
        this.visitor.setFrame(frame);
        return this.code.parseInstruction(i, this.visitor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SimException illegalTos() {
        return new SimException("stack mismatch: illegal top-of-stack for opcode");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Type requiredArrayTypeFor(Type type, Type type2) {
        if (type2 == Type.KNOWN_NULL) {
            return type.isReference() ? Type.KNOWN_NULL : type.getArrayType();
        }
        if (type == Type.OBJECT && type2.isArray() && type2.getComponentType().isReference()) {
            return type2;
        }
        if (type == Type.BYTE && type2 == Type.BOOLEAN_ARRAY) {
            return Type.BOOLEAN_ARRAY;
        }
        return type.getArrayType();
    }

    private class SimVisitor implements BytecodeArray.Visitor {
        private Frame frame = null;
        private final Machine machine;
        private int previousOffset;

        public SimVisitor() {
            this.machine = Simulator.this.machine;
        }

        public void setFrame(Frame frame) {
            if (frame == null) {
                throw new NullPointerException("frame == null");
            }
            this.frame = frame;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitInvalid(int i, int i2, int i3) {
            throw new SimException("invalid opcode " + Hex.u1(i));
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:116:0x02be  */
        /* JADX WARN: Removed duplicated region for block: B:139:0x0359  */
        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void visitNoArgs(int r9, int r10, int r11, com.android.dx.rop.type.Type r12) {
            /*
                Method dump skipped, instruction units count: 1010
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dx.cf.code.Simulator.SimVisitor.visitNoArgs(int, int, int, com.android.dx.rop.type.Type):void");
        }

        private void checkReturnType(Type type) {
            Type returnType = this.machine.getPrototype().getReturnType();
            if (!Merger.isPossiblyAssignableFrom(returnType, type)) {
                throw new SimException("return type mismatch: prototype indicates " + returnType.toHuman() + ", but encountered type " + type.toHuman());
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0087  */
        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void visitLocal(int r6, int r7, int r8, int r9, com.android.dx.rop.type.Type r10, int r11) {
            /*
                r5 = this;
                r0 = 54
                if (r6 != r0) goto L7
                int r1 = r7 + r8
                goto L8
            L7:
                r1 = r7
            L8:
                com.android.dx.cf.code.Simulator r2 = com.android.dx.cf.code.Simulator.this
                com.android.dx.cf.code.LocalVariableList r2 = com.android.dx.cf.code.Simulator.access$300(r2)
                com.android.dx.cf.code.LocalVariableList$Item r1 = r2.pcAndIndexToLocal(r1, r9)
                if (r1 == 0) goto L26
                com.android.dx.rop.type.Type r2 = r1.getType()
                int r3 = r2.getBasicFrameType()
                int r4 = r10.getBasicFrameType()
                if (r3 == r4) goto L27
                com.android.dx.cf.code.BaseMachine.throwLocalMismatch(r10, r2)
                return
            L26:
                r2 = r10
            L27:
                r3 = 21
                if (r6 == r3) goto L7a
                r3 = 0
                if (r6 == r0) goto L61
                r0 = 132(0x84, float:1.85E-43)
                if (r6 == r0) goto L3a
                r11 = 169(0xa9, float:2.37E-43)
                if (r6 == r11) goto L7a
                r5.visitInvalid(r6, r7, r8)
                return
            L3a:
                if (r1 != 0) goto L3d
                goto L41
            L3d:
                com.android.dx.rop.code.LocalItem r3 = r1.getLocalItem()
            L41:
                com.android.dx.cf.code.Machine r8 = r5.machine
                com.android.dx.cf.code.Frame r0 = r5.frame
                r8.localArg(r0, r9)
                com.android.dx.cf.code.Machine r8 = r5.machine
                r8.localTarget(r9, r2, r3)
                com.android.dx.cf.code.Machine r8 = r5.machine
                r8.auxType(r10)
                com.android.dx.cf.code.Machine r8 = r5.machine
                r8.auxIntArg(r11)
                com.android.dx.cf.code.Machine r8 = r5.machine
                com.android.dx.rop.cst.CstInteger r9 = com.android.dx.rop.cst.CstInteger.make(r11)
                r8.auxCstArg(r9)
                goto L90
            L61:
                if (r1 != 0) goto L64
                goto L68
            L64:
                com.android.dx.rop.code.LocalItem r3 = r1.getLocalItem()
            L68:
                com.android.dx.cf.code.Machine r8 = r5.machine
                com.android.dx.cf.code.Frame r11 = r5.frame
                r8.popArgs(r11, r10)
                com.android.dx.cf.code.Machine r8 = r5.machine
                r8.auxType(r10)
                com.android.dx.cf.code.Machine r8 = r5.machine
                r8.localTarget(r9, r2, r3)
                goto L90
            L7a:
                com.android.dx.cf.code.Machine r8 = r5.machine
                com.android.dx.cf.code.Frame r11 = r5.frame
                r8.localArg(r11, r9)
                com.android.dx.cf.code.Machine r8 = r5.machine
                if (r1 == 0) goto L87
                r9 = 1
                goto L88
            L87:
                r9 = 0
            L88:
                r8.localInfo(r9)
                com.android.dx.cf.code.Machine r8 = r5.machine
                r8.auxType(r10)
            L90:
                com.android.dx.cf.code.Machine r8 = r5.machine
                com.android.dx.cf.code.Frame r9 = r5.frame
                r8.run(r9, r7, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dx.cf.code.Simulator.SimVisitor.visitLocal(int, int, int, int, com.android.dx.rop.type.Type, int):void");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0060  */
        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void visitConstant(int r4, int r5, int r6, com.android.dx.rop.cst.Constant r7, int r8) {
            /*
                r3 = this;
                r6 = 189(0xbd, float:2.65E-43)
                if (r4 == r6) goto L78
                r6 = 197(0xc5, float:2.76E-43)
                if (r4 == r6) goto L6a
                r6 = 192(0xc0, float:2.69E-43)
                if (r4 == r6) goto L60
                r6 = 193(0xc1, float:2.7E-43)
                if (r4 == r6) goto L60
                switch(r4) {
                    case 179: goto L51;
                    case 180: goto L60;
                    case 181: goto L40;
                    case 182: goto L30;
                    case 183: goto L30;
                    case 184: goto L20;
                    case 185: goto L19;
                    default: goto L13;
                }
            L13:
                com.android.dx.cf.code.Machine r6 = r3.machine
                r6.clearArgs()
                goto L81
            L19:
                com.android.dx.rop.cst.CstInterfaceMethodRef r7 = (com.android.dx.rop.cst.CstInterfaceMethodRef) r7
                com.android.dx.rop.cst.CstMethodRef r7 = r7.toMethodRef()
                goto L30
            L20:
                r6 = r7
                com.android.dx.rop.cst.CstMethodRef r6 = (com.android.dx.rop.cst.CstMethodRef) r6
                r0 = 1
                com.android.dx.rop.type.Prototype r6 = r6.getPrototype(r0)
                com.android.dx.cf.code.Machine r0 = r3.machine
                com.android.dx.cf.code.Frame r1 = r3.frame
                r0.popArgs(r1, r6)
                goto L81
            L30:
                r6 = r7
                com.android.dx.rop.cst.CstMethodRef r6 = (com.android.dx.rop.cst.CstMethodRef) r6
                r0 = 0
                com.android.dx.rop.type.Prototype r6 = r6.getPrototype(r0)
                com.android.dx.cf.code.Machine r0 = r3.machine
                com.android.dx.cf.code.Frame r1 = r3.frame
                r0.popArgs(r1, r6)
                goto L81
            L40:
                r6 = r7
                com.android.dx.rop.cst.CstFieldRef r6 = (com.android.dx.rop.cst.CstFieldRef) r6
                com.android.dx.rop.type.Type r6 = r6.getType()
                com.android.dx.cf.code.Machine r0 = r3.machine
                com.android.dx.cf.code.Frame r1 = r3.frame
                com.android.dx.rop.type.Type r2 = com.android.dx.rop.type.Type.OBJECT
                r0.popArgs(r1, r2, r6)
                goto L81
            L51:
                r6 = r7
                com.android.dx.rop.cst.CstFieldRef r6 = (com.android.dx.rop.cst.CstFieldRef) r6
                com.android.dx.rop.type.Type r6 = r6.getType()
                com.android.dx.cf.code.Machine r0 = r3.machine
                com.android.dx.cf.code.Frame r1 = r3.frame
                r0.popArgs(r1, r6)
                goto L81
            L60:
                com.android.dx.cf.code.Machine r6 = r3.machine
                com.android.dx.cf.code.Frame r0 = r3.frame
                com.android.dx.rop.type.Type r1 = com.android.dx.rop.type.Type.OBJECT
                r6.popArgs(r0, r1)
                goto L81
            L6a:
                com.android.dx.rop.type.Type r6 = com.android.dx.rop.type.Type.VOID
                com.android.dx.rop.type.Prototype r6 = com.android.dx.rop.type.Prototype.internInts(r6, r8)
                com.android.dx.cf.code.Machine r0 = r3.machine
                com.android.dx.cf.code.Frame r1 = r3.frame
                r0.popArgs(r1, r6)
                goto L81
            L78:
                com.android.dx.cf.code.Machine r6 = r3.machine
                com.android.dx.cf.code.Frame r0 = r3.frame
                com.android.dx.rop.type.Type r1 = com.android.dx.rop.type.Type.INT
                r6.popArgs(r0, r1)
            L81:
                com.android.dx.cf.code.Machine r6 = r3.machine
                r6.auxIntArg(r8)
                com.android.dx.cf.code.Machine r6 = r3.machine
                r6.auxCstArg(r7)
                com.android.dx.cf.code.Machine r6 = r3.machine
                com.android.dx.cf.code.Frame r7 = r3.frame
                r6.run(r7, r5, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dx.cf.code.Simulator.SimVisitor.visitConstant(int, int, int, com.android.dx.rop.cst.Constant, int):void");
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitBranch(int i, int i2, int i3, int i4) {
            switch (i) {
                case 153:
                case 154:
                case 155:
                case 156:
                case 157:
                case 158:
                    this.machine.popArgs(this.frame, Type.INT);
                    this.machine.auxTargetArg(i4);
                    this.machine.run(this.frame, i2, i);
                    break;
                case 159:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                    this.machine.popArgs(this.frame, Type.INT, Type.INT);
                    this.machine.auxTargetArg(i4);
                    this.machine.run(this.frame, i2, i);
                    break;
                case 165:
                case 166:
                    this.machine.popArgs(this.frame, Type.OBJECT, Type.OBJECT);
                    this.machine.auxTargetArg(i4);
                    this.machine.run(this.frame, i2, i);
                    break;
                default:
                    switch (i) {
                        case 198:
                        case 199:
                            this.machine.popArgs(this.frame, Type.OBJECT);
                            break;
                        case 200:
                        case 201:
                            break;
                        default:
                            visitInvalid(i, i2, i3);
                            break;
                    }
                    this.machine.auxTargetArg(i4);
                    this.machine.run(this.frame, i2, i);
                case 167:
                case 168:
                    this.machine.clearArgs();
                    this.machine.auxTargetArg(i4);
                    this.machine.run(this.frame, i2, i);
                    break;
            }
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitSwitch(int i, int i2, int i3, SwitchList switchList, int i4) {
            this.machine.popArgs(this.frame, Type.INT);
            this.machine.auxIntArg(i4);
            this.machine.auxSwitchArg(switchList);
            this.machine.run(this.frame, i2, i);
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitNewarray(int i, int i2, CstType cstType, ArrayList<Constant> arrayList) {
            this.machine.popArgs(this.frame, Type.INT);
            this.machine.auxInitValues(arrayList);
            this.machine.auxCstArg(cstType);
            this.machine.run(this.frame, i, 188);
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void setPreviousOffset(int i) {
            this.previousOffset = i;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public int getPreviousOffset() {
            return this.previousOffset;
        }
    }
}
