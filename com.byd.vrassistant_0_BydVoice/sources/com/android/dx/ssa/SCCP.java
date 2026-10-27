package com.android.dx.ssa;

import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.TypedConstant;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class SCCP {
    private static final int CONSTANT = 1;
    private static final int TOP = 0;
    private static final int VARYING = 2;
    private ArrayList<SsaInsn> branchWorklist;
    private ArrayList<SsaBasicBlock> cfgPhiWorklist;
    private ArrayList<SsaBasicBlock> cfgWorklist;
    private BitSet executableBlocks;
    private Constant[] latticeConstants;
    private int[] latticeValues;
    private int regCount;
    private SsaMethod ssaMeth;
    private ArrayList<SsaInsn> ssaWorklist;
    private ArrayList<SsaInsn> varyingWorklist;

    private static String latticeValName(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "UNKNOWN" : "VARYING" : "CONSTANT" : "TOP";
    }

    private SCCP(SsaMethod ssaMethod) {
        this.ssaMeth = ssaMethod;
        int regCount = ssaMethod.getRegCount();
        this.regCount = regCount;
        this.latticeValues = new int[regCount];
        this.latticeConstants = new Constant[regCount];
        this.cfgWorklist = new ArrayList<>();
        this.cfgPhiWorklist = new ArrayList<>();
        this.executableBlocks = new BitSet(ssaMethod.getBlocks().size());
        this.ssaWorklist = new ArrayList<>();
        this.varyingWorklist = new ArrayList<>();
        this.branchWorklist = new ArrayList<>();
        for (int i = 0; i < this.regCount; i++) {
            this.latticeValues[i] = 0;
            this.latticeConstants[i] = null;
        }
    }

    public static void process(SsaMethod ssaMethod) {
        new SCCP(ssaMethod).run();
    }

    private void addBlockToWorklist(SsaBasicBlock ssaBasicBlock) {
        if (!this.executableBlocks.get(ssaBasicBlock.getIndex())) {
            this.cfgWorklist.add(ssaBasicBlock);
            this.executableBlocks.set(ssaBasicBlock.getIndex());
        } else {
            this.cfgPhiWorklist.add(ssaBasicBlock);
        }
    }

    private void addUsersToWorklist(int i, int i2) {
        if (i2 == 2) {
            Iterator<SsaInsn> it = this.ssaMeth.getUseListForRegister(i).iterator();
            while (it.hasNext()) {
                this.varyingWorklist.add(it.next());
            }
            return;
        }
        Iterator<SsaInsn> it2 = this.ssaMeth.getUseListForRegister(i).iterator();
        while (it2.hasNext()) {
            this.ssaWorklist.add(it2.next());
        }
    }

    private boolean setLatticeValueTo(int i, int i2, Constant constant) {
        if (i2 != 1) {
            int[] iArr = this.latticeValues;
            if (iArr[i] == i2) {
                return false;
            }
            iArr[i] = i2;
            return true;
        }
        if (this.latticeValues[i] == i2 && this.latticeConstants[i].equals(constant)) {
            return false;
        }
        this.latticeValues[i] = i2;
        this.latticeConstants[i] = constant;
        return true;
    }

    private void simulatePhi(PhiInsn phiInsn) {
        int reg = phiInsn.getResult().getReg();
        int i = 2;
        if (this.latticeValues[reg] == 2) {
            return;
        }
        RegisterSpecList sources = phiInsn.getSources();
        int size = sources.size();
        int i2 = 0;
        Constant constant = null;
        int i3 = 0;
        while (true) {
            if (i2 >= size) {
                i = i3;
                break;
            }
            int iPredBlockIndexForSourcesIndex = phiInsn.predBlockIndexForSourcesIndex(i2);
            int reg2 = sources.get(i2).getReg();
            int i4 = this.latticeValues[reg2];
            if (this.executableBlocks.get(iPredBlockIndexForSourcesIndex)) {
                if (i4 != 1) {
                    i = i4;
                    break;
                } else if (constant == null) {
                    constant = this.latticeConstants[reg2];
                    i3 = 1;
                } else if (!this.latticeConstants[reg2].equals(constant)) {
                    break;
                }
            }
            i2++;
        }
        if (setLatticeValueTo(reg, i, constant)) {
            addUsersToWorklist(reg, i);
        }
    }

    private void simulateBlock(SsaBasicBlock ssaBasicBlock) {
        for (SsaInsn ssaInsn : ssaBasicBlock.getInsns()) {
            if (ssaInsn instanceof PhiInsn) {
                simulatePhi((PhiInsn) ssaInsn);
            } else {
                simulateStmt(ssaInsn);
            }
        }
    }

    private void simulatePhiBlock(SsaBasicBlock ssaBasicBlock) {
        for (SsaInsn ssaInsn : ssaBasicBlock.getInsns()) {
            if (!(ssaInsn instanceof PhiInsn)) {
                return;
            } else {
                simulatePhi((PhiInsn) ssaInsn);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void simulateBranch(com.android.dx.ssa.SsaInsn r10) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dx.ssa.SCCP.simulateBranch(com.android.dx.ssa.SsaInsn):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.android.dx.rop.cst.Constant simulateMath(com.android.dx.ssa.SsaInsn r8, int r9) {
        /*
            r7 = this;
            com.android.dx.rop.code.Insn r0 = r8.getOriginalRopInsn()
            com.android.dx.rop.code.Rop r1 = r8.getOpcode()
            int r1 = r1.getOpcode()
            com.android.dx.rop.code.RegisterSpecList r8 = r8.getSources()
            r2 = 0
            com.android.dx.rop.code.RegisterSpec r3 = r8.get(r2)
            int r3 = r3.getReg()
            int[] r4 = r7.latticeValues
            r4 = r4[r3]
            r5 = 0
            r6 = 1
            if (r4 == r6) goto L23
            r3 = r5
            goto L27
        L23:
            com.android.dx.rop.cst.Constant[] r4 = r7.latticeConstants
            r3 = r4[r3]
        L27:
            int r4 = r8.size()
            if (r4 != r6) goto L34
            com.android.dx.rop.code.CstInsn r0 = (com.android.dx.rop.code.CstInsn) r0
            com.android.dx.rop.cst.Constant r0 = r0.getConstant()
            goto L48
        L34:
            com.android.dx.rop.code.RegisterSpec r0 = r8.get(r6)
            int r0 = r0.getReg()
            int[] r4 = r7.latticeValues
            r4 = r4[r0]
            if (r4 == r6) goto L44
            r0 = r5
            goto L48
        L44:
            com.android.dx.rop.cst.Constant[] r4 = r7.latticeConstants
            r0 = r4[r0]
        L48:
            if (r3 == 0) goto L9c
            if (r0 != 0) goto L4d
            goto L9c
        L4d:
            r4 = 6
            if (r9 == r4) goto L51
            return r5
        L51:
            com.android.dx.rop.cst.CstInteger r3 = (com.android.dx.rop.cst.CstInteger) r3
            int r9 = r3.getValue()
            com.android.dx.rop.cst.CstInteger r0 = (com.android.dx.rop.cst.CstInteger) r0
            int r0 = r0.getValue()
            switch(r1) {
                case 14: goto L93;
                case 15: goto L88;
                case 16: goto L86;
                case 17: goto L7f;
                case 18: goto L7a;
                case 19: goto L60;
                case 20: goto L77;
                case 21: goto L74;
                case 22: goto L71;
                case 23: goto L6e;
                case 24: goto L6b;
                case 25: goto L68;
                default: goto L60;
            }
        L60:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r9 = "Unexpected op"
            r8.<init>(r9)
            throw r8
        L68:
            int r8 = r9 >>> r0
            goto L95
        L6b:
            int r8 = r9 >> r0
            goto L95
        L6e:
            int r8 = r9 << r0
            goto L95
        L71:
            r8 = r9 ^ r0
            goto L95
        L74:
            r8 = r9 | r0
            goto L95
        L77:
            r8 = r9 & r0
            goto L95
        L7a:
            if (r0 != 0) goto L7d
            goto L81
        L7d:
            int r9 = r9 % r0
            goto L94
        L7f:
            if (r0 != 0) goto L84
        L81:
            r8 = r2
            r2 = r6
            goto L95
        L84:
            int r9 = r9 / r0
            goto L94
        L86:
            int r9 = r9 * r0
            goto L94
        L88:
            int r8 = r8.size()
            if (r8 != r6) goto L91
            int r0 = r0 - r9
            r8 = r0
            goto L95
        L91:
            int r9 = r9 - r0
            goto L94
        L93:
            int r9 = r9 + r0
        L94:
            r8 = r9
        L95:
            if (r2 == 0) goto L98
            goto L9c
        L98:
            com.android.dx.rop.cst.CstInteger r5 = com.android.dx.rop.cst.CstInteger.make(r8)
        L9c:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dx.ssa.SCCP.simulateMath(com.android.dx.ssa.SsaInsn, int):com.android.dx.rop.cst.Constant");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void simulateStmt(com.android.dx.ssa.SsaInsn r8) {
        /*
            r7 = this;
            com.android.dx.rop.code.Insn r0 = r8.getOriginalRopInsn()
            com.android.dx.rop.code.Rop r1 = r0.getOpcode()
            int r1 = r1.getBranchingness()
            r2 = 1
            if (r1 != r2) goto L19
            com.android.dx.rop.code.Rop r1 = r0.getOpcode()
            boolean r1 = r1.isCallLike()
            if (r1 == 0) goto L1c
        L19:
            r7.simulateBranch(r8)
        L1c:
            com.android.dx.rop.code.Rop r1 = r8.getOpcode()
            int r1 = r1.getOpcode()
            com.android.dx.rop.code.RegisterSpec r3 = r8.getResult()
            r4 = 0
            if (r3 != 0) goto L4b
            r3 = 17
            if (r1 == r3) goto L35
            r3 = 18
            if (r1 != r3) goto L34
            goto L35
        L34:
            return
        L35:
            com.android.dx.ssa.SsaBasicBlock r3 = r8.getBlock()
            com.android.dx.ssa.SsaBasicBlock r3 = r3.getPrimarySuccessor()
            java.util.ArrayList r3 = r3.getInsns()
            java.lang.Object r3 = r3.get(r4)
            com.android.dx.ssa.SsaInsn r3 = (com.android.dx.ssa.SsaInsn) r3
            com.android.dx.rop.code.RegisterSpec r3 = r3.getResult()
        L4b:
            int r5 = r3.getReg()
            r6 = 2
            if (r1 == r6) goto L7f
            r4 = 5
            if (r1 == r4) goto L78
            r0 = 56
            if (r1 == r0) goto L6b
            switch(r1) {
                case 14: goto L60;
                case 15: goto L60;
                case 16: goto L60;
                case 17: goto L60;
                case 18: goto L60;
                default: goto L5c;
            }
        L5c:
            switch(r1) {
                case 20: goto L60;
                case 21: goto L60;
                case 22: goto L60;
                case 23: goto L60;
                case 24: goto L60;
                case 25: goto L60;
                default: goto L5f;
            }
        L5f:
            goto L9e
        L60:
            int r0 = r3.getBasicType()
            com.android.dx.rop.cst.Constant r8 = r7.simulateMath(r8, r0)
            if (r8 == 0) goto L9f
            goto La0
        L6b:
            int[] r8 = r7.latticeValues
            r8 = r8[r5]
            if (r8 != r2) goto L9e
            com.android.dx.rop.cst.Constant[] r0 = r7.latticeConstants
            r0 = r0[r5]
            r2 = r8
            r8 = r0
            goto La0
        L78:
            com.android.dx.rop.code.CstInsn r0 = (com.android.dx.rop.code.CstInsn) r0
            com.android.dx.rop.cst.Constant r8 = r0.getConstant()
            goto La0
        L7f:
            com.android.dx.rop.code.RegisterSpecList r0 = r8.getSources()
            int r0 = r0.size()
            if (r0 != r2) goto L9e
            com.android.dx.rop.code.RegisterSpecList r8 = r8.getSources()
            com.android.dx.rop.code.RegisterSpec r8 = r8.get(r4)
            int r8 = r8.getReg()
            int[] r0 = r7.latticeValues
            r2 = r0[r8]
            com.android.dx.rop.cst.Constant[] r0 = r7.latticeConstants
            r8 = r0[r8]
            goto La0
        L9e:
            r8 = 0
        L9f:
            r2 = r6
        La0:
            boolean r8 = r7.setLatticeValueTo(r5, r2, r8)
            if (r8 == 0) goto La9
            r7.addUsersToWorklist(r5, r2)
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dx.ssa.SCCP.simulateStmt(com.android.dx.ssa.SsaInsn):void");
    }

    private void run() {
        addBlockToWorklist(this.ssaMeth.getEntryBlock());
        while (true) {
            if (!this.cfgWorklist.isEmpty() || !this.cfgPhiWorklist.isEmpty() || !this.ssaWorklist.isEmpty() || !this.varyingWorklist.isEmpty()) {
                while (!this.cfgWorklist.isEmpty()) {
                    simulateBlock(this.cfgWorklist.remove(this.cfgWorklist.size() - 1));
                }
                while (!this.cfgPhiWorklist.isEmpty()) {
                    simulatePhiBlock(this.cfgPhiWorklist.remove(this.cfgPhiWorklist.size() - 1));
                }
                while (!this.varyingWorklist.isEmpty()) {
                    SsaInsn ssaInsnRemove = this.varyingWorklist.remove(this.varyingWorklist.size() - 1);
                    if (this.executableBlocks.get(ssaInsnRemove.getBlock().getIndex())) {
                        if (ssaInsnRemove instanceof PhiInsn) {
                            simulatePhi((PhiInsn) ssaInsnRemove);
                        } else {
                            simulateStmt(ssaInsnRemove);
                        }
                    }
                }
                while (!this.ssaWorklist.isEmpty()) {
                    SsaInsn ssaInsnRemove2 = this.ssaWorklist.remove(this.ssaWorklist.size() - 1);
                    if (this.executableBlocks.get(ssaInsnRemove2.getBlock().getIndex())) {
                        if (ssaInsnRemove2 instanceof PhiInsn) {
                            simulatePhi((PhiInsn) ssaInsnRemove2);
                        } else {
                            simulateStmt(ssaInsnRemove2);
                        }
                    }
                }
            } else {
                replaceConstants();
                replaceBranches();
                return;
            }
        }
    }

    private void replaceConstants() {
        for (int i = 0; i < this.regCount; i++) {
            if (this.latticeValues[i] == 1 && (this.latticeConstants[i] instanceof TypedConstant)) {
                SsaInsn definitionForRegister = this.ssaMeth.getDefinitionForRegister(i);
                if (!definitionForRegister.getResult().getTypeBearer().isConstant()) {
                    definitionForRegister.setResult(definitionForRegister.getResult().withType((TypedConstant) this.latticeConstants[i]));
                    for (SsaInsn ssaInsn : this.ssaMeth.getUseListForRegister(i)) {
                        if (!ssaInsn.isPhiOrMove()) {
                            NormalSsaInsn normalSsaInsn = (NormalSsaInsn) ssaInsn;
                            RegisterSpecList sources = ssaInsn.getSources();
                            int iIndexOfRegister = sources.indexOfRegister(i);
                            normalSsaInsn.changeOneSource(iIndexOfRegister, sources.get(iIndexOfRegister).withType((TypedConstant) this.latticeConstants[i]));
                        }
                    }
                }
            }
        }
    }

    private void replaceBranches() {
        for (SsaInsn ssaInsn : this.branchWorklist) {
            SsaBasicBlock block = ssaInsn.getBlock();
            int size = block.getSuccessorList().size();
            int i = -1;
            for (int i2 = 0; i2 < size; i2++) {
                int i3 = block.getSuccessorList().get(i2);
                if (!this.executableBlocks.get(i3)) {
                    i = i3;
                }
            }
            if (size == 2 && i != -1) {
                block.replaceLastInsn(new PlainInsn(Rops.GOTO, ssaInsn.getOriginalRopInsn().getPosition(), (RegisterSpec) null, RegisterSpecList.EMPTY));
                block.removeSuccessor(i);
            }
        }
    }
}
