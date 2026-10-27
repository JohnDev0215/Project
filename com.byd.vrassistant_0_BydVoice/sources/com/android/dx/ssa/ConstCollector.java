package com.android.dx.ssa;

import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.PlainCstInsn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.code.ThrowingCstInsn;
import com.android.dx.rop.cst.TypedConstant;
import com.android.dx.rop.type.StdTypeList;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ConstCollector {
    private static boolean COLLECT_ONE_LOCAL = false;
    private static boolean COLLECT_STRINGS = false;
    private static final int MAX_COLLECTED_CONSTANTS = 5;
    private final SsaMethod ssaMeth;

    public static void process(SsaMethod ssaMethod) {
        new ConstCollector(ssaMethod).run();
    }

    private ConstCollector(SsaMethod ssaMethod) {
        this.ssaMeth = ssaMethod;
    }

    private void run() {
        ArrayList<TypedConstant> arrayList;
        int i;
        int regCount = this.ssaMeth.getRegCount();
        ArrayList<TypedConstant> constsSortedByCountUse = getConstsSortedByCountUse();
        int iMin = Math.min(constsSortedByCountUse.size(), 5);
        SsaBasicBlock entryBlock = this.ssaMeth.getEntryBlock();
        HashMap<TypedConstant, RegisterSpec> map = new HashMap<>(iMin);
        int i2 = 0;
        while (i2 < iMin) {
            TypedConstant typedConstant = constsSortedByCountUse.get(i2);
            RegisterSpec registerSpecMake = RegisterSpec.make(this.ssaMeth.makeNewSsaReg(), typedConstant);
            Rop ropOpConst = Rops.opConst(typedConstant);
            if (ropOpConst.getBranchingness() == 1) {
                entryBlock.addInsnToHead(new PlainCstInsn(Rops.opConst(typedConstant), SourcePosition.NO_INFO, registerSpecMake, RegisterSpecList.EMPTY, typedConstant));
                arrayList = constsSortedByCountUse;
                i = iMin;
            } else {
                SsaBasicBlock entryBlock2 = this.ssaMeth.getEntryBlock();
                SsaBasicBlock primarySuccessor = entryBlock2.getPrimarySuccessor();
                SsaBasicBlock ssaBasicBlockInsertNewSuccessor = entryBlock2.insertNewSuccessor(primarySuccessor);
                arrayList = constsSortedByCountUse;
                i = iMin;
                ssaBasicBlockInsertNewSuccessor.replaceLastInsn(new ThrowingCstInsn(ropOpConst, SourcePosition.NO_INFO, RegisterSpecList.EMPTY, StdTypeList.EMPTY, typedConstant));
                ssaBasicBlockInsertNewSuccessor.insertNewSuccessor(primarySuccessor).addInsnToHead(new PlainInsn(Rops.opMoveResultPseudo(registerSpecMake.getTypeBearer()), SourcePosition.NO_INFO, registerSpecMake, RegisterSpecList.EMPTY));
            }
            map.put(typedConstant, registerSpecMake);
            i2++;
            iMin = i;
            constsSortedByCountUse = arrayList;
        }
        updateConstUses(map, regCount);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.ArrayList<com.android.dx.rop.cst.TypedConstant> getConstsSortedByCountUse() {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dx.ssa.ConstCollector.getConstsSortedByCountUse():java.util.ArrayList");
    }

    private void fixLocalAssignment(RegisterSpec registerSpec, RegisterSpec registerSpec2) {
        for (SsaInsn ssaInsn : this.ssaMeth.getUseListForRegister(registerSpec.getReg())) {
            RegisterSpec localAssignment = ssaInsn.getLocalAssignment();
            if (localAssignment != null && ssaInsn.getResult() != null) {
                LocalItem localItem = localAssignment.getLocalItem();
                ssaInsn.setResultLocal(null);
                registerSpec2 = registerSpec2.withLocalItem(localItem);
                SsaInsn ssaInsnMakeFromRop = SsaInsn.makeFromRop(new PlainInsn(Rops.opMarkLocal(registerSpec2), SourcePosition.NO_INFO, (RegisterSpec) null, RegisterSpecList.make(registerSpec2)), ssaInsn.getBlock());
                ArrayList<SsaInsn> insns = ssaInsn.getBlock().getInsns();
                insns.add(insns.indexOf(ssaInsn) + 1, ssaInsnMakeFromRop);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateConstUses(java.util.HashMap<com.android.dx.rop.cst.TypedConstant, com.android.dx.rop.code.RegisterSpec> r9, int r10) {
        /*
            r8 = this;
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            com.android.dx.ssa.SsaMethod r1 = r8.ssaMeth
            java.util.ArrayList[] r1 = r1.getUseListCopy()
            r2 = 0
        Lc:
            if (r2 >= r10) goto L8f
            com.android.dx.ssa.SsaMethod r3 = r8.ssaMeth
            com.android.dx.ssa.SsaInsn r3 = r3.getDefinitionForRegister(r2)
            if (r3 != 0) goto L18
            goto L8b
        L18:
            com.android.dx.rop.code.RegisterSpec r4 = r3.getResult()
            com.android.dx.rop.code.RegisterSpec r3 = r3.getResult()
            com.android.dx.rop.type.TypeBearer r3 = r3.getTypeBearer()
            boolean r5 = r3.isConstant()
            if (r5 != 0) goto L2b
            goto L8b
        L2b:
            com.android.dx.rop.cst.TypedConstant r3 = (com.android.dx.rop.cst.TypedConstant) r3
            java.lang.Object r5 = r9.get(r3)
            com.android.dx.rop.code.RegisterSpec r5 = (com.android.dx.rop.code.RegisterSpec) r5
            if (r5 != 0) goto L36
            goto L8b
        L36:
            com.android.dx.ssa.SsaMethod r6 = r8.ssaMeth
            boolean r6 = r6.isRegALocal(r4)
            if (r6 == 0) goto L56
            boolean r6 = com.android.dx.ssa.ConstCollector.COLLECT_ONE_LOCAL
            if (r6 != 0) goto L43
            goto L8b
        L43:
            boolean r6 = r0.contains(r3)
            if (r6 == 0) goto L4a
            goto L8b
        L4a:
            r0.add(r3)
            java.lang.Object r3 = r9.get(r3)
            com.android.dx.rop.code.RegisterSpec r3 = (com.android.dx.rop.code.RegisterSpec) r3
            r8.fixLocalAssignment(r4, r3)
        L56:
            com.android.dx.ssa.ConstCollector$2 r3 = new com.android.dx.ssa.ConstCollector$2
            r3.<init>()
            int r4 = r4.getReg()
            r4 = r1[r4]
            java.util.Iterator r4 = r4.iterator()
        L65:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L8b
            java.lang.Object r5 = r4.next()
            com.android.dx.ssa.SsaInsn r5 = (com.android.dx.ssa.SsaInsn) r5
            boolean r6 = r5.canThrow()
            if (r6 == 0) goto L87
            com.android.dx.ssa.SsaBasicBlock r6 = r5.getBlock()
            java.util.BitSet r6 = r6.getSuccessors()
            int r6 = r6.cardinality()
            r7 = 1
            if (r6 <= r7) goto L87
            goto L65
        L87:
            r5.mapSourceRegisters(r3)
            goto L65
        L8b:
            int r2 = r2 + 1
            goto Lc
        L8f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dx.ssa.ConstCollector.updateConstUses(java.util.HashMap, int):void");
    }
}
