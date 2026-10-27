package com.android.dx.command.dump;

import com.android.dx.cf.code.ConcreteMethod;
import com.android.dx.cf.code.Ropper;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.Method;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.code.DexTranslationAdvice;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.ssa.Optimizer;
import com.android.dx.ssa.SsaBasicBlock;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.ssa.SsaMethod;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class SsaDumper extends BlockDumper {
    public static void dump(byte[] bArr, PrintStream printStream, String str, Args args) {
        new SsaDumper(bArr, printStream, str, args).dump();
    }

    private SsaDumper(byte[] bArr, PrintStream printStream, String str, Args args) {
        super(bArr, printStream, str, true, args);
    }

    @Override // com.android.dx.command.dump.BlockDumper, com.android.dx.command.dump.BaseDumper, com.android.dx.cf.iface.ParseObserver
    public void endParsingMember(ByteArray byteArray, int i, String str, String str2, Member member) {
        SsaMethod ssaMethodDebugDeadCodeRemover;
        if (!(member instanceof Method) || !shouldDumpMethod(str) || (member.getAccessFlags() & 1280) != 0) {
            return;
        }
        ConcreteMethod concreteMethod = new ConcreteMethod((Method) member, this.classFile, true, true);
        DexTranslationAdvice dexTranslationAdvice = DexTranslationAdvice.THE_ONE;
        RopMethod ropMethodConvert = Ropper.convert(concreteMethod, dexTranslationAdvice, this.classFile.getMethods());
        boolean zIsStatic = AccessFlags.isStatic(concreteMethod.getAccessFlags());
        int iComputeParamWidth = computeParamWidth(concreteMethod, zIsStatic);
        if (this.args.ssaStep == null) {
            ssaMethodDebugDeadCodeRemover = Optimizer.debugNoRegisterAllocation(ropMethodConvert, iComputeParamWidth, zIsStatic, true, dexTranslationAdvice, EnumSet.allOf(Optimizer.OptionalStep.class));
        } else if ("edge-split".equals(this.args.ssaStep)) {
            ssaMethodDebugDeadCodeRemover = Optimizer.debugEdgeSplit(ropMethodConvert, iComputeParamWidth, zIsStatic, true, dexTranslationAdvice);
        } else if ("phi-placement".equals(this.args.ssaStep)) {
            ssaMethodDebugDeadCodeRemover = Optimizer.debugPhiPlacement(ropMethodConvert, iComputeParamWidth, zIsStatic, true, dexTranslationAdvice);
        } else if ("renaming".equals(this.args.ssaStep)) {
            ssaMethodDebugDeadCodeRemover = Optimizer.debugRenaming(ropMethodConvert, iComputeParamWidth, zIsStatic, true, dexTranslationAdvice);
        } else {
            ssaMethodDebugDeadCodeRemover = "dead-code".equals(this.args.ssaStep) ? Optimizer.debugDeadCodeRemover(ropMethodConvert, iComputeParamWidth, zIsStatic, true, dexTranslationAdvice) : null;
        }
        StringBuffer stringBuffer = new StringBuffer(2000);
        stringBuffer.append("first ");
        stringBuffer.append(Hex.u2(ssaMethodDebugDeadCodeRemover.blockIndexToRopLabel(ssaMethodDebugDeadCodeRemover.getEntryBlockIndex())));
        stringBuffer.append('\n');
        ArrayList arrayList = (ArrayList) ssaMethodDebugDeadCodeRemover.getBlocks().clone();
        Collections.sort(arrayList, SsaBasicBlock.LABEL_COMPARATOR);
        Iterator it = arrayList.iterator();
        while (true) {
            if (it.hasNext()) {
                SsaBasicBlock ssaBasicBlock = (SsaBasicBlock) it.next();
                stringBuffer.append("block ").append(Hex.u2(ssaBasicBlock.getRopLabel())).append('\n');
                BitSet predecessors = ssaBasicBlock.getPredecessors();
                for (int iNextSetBit = predecessors.nextSetBit(0); iNextSetBit >= 0; iNextSetBit = predecessors.nextSetBit(iNextSetBit + 1)) {
                    stringBuffer.append("  pred ");
                    stringBuffer.append(Hex.u2(ssaMethodDebugDeadCodeRemover.blockIndexToRopLabel(iNextSetBit)));
                    stringBuffer.append('\n');
                }
                stringBuffer.append("  live in:" + ssaBasicBlock.getLiveInRegs());
                stringBuffer.append("\n");
                for (SsaInsn ssaInsn : ssaBasicBlock.getInsns()) {
                    stringBuffer.append("  ");
                    stringBuffer.append(ssaInsn.toHuman());
                    stringBuffer.append('\n');
                }
                if (ssaBasicBlock.getSuccessors().cardinality() == 0) {
                    stringBuffer.append("  returns\n");
                } else {
                    int primarySuccessorRopLabel = ssaBasicBlock.getPrimarySuccessorRopLabel();
                    IntList ropLabelSuccessorList = ssaBasicBlock.getRopLabelSuccessorList();
                    int size = ropLabelSuccessorList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        stringBuffer.append("  next ");
                        stringBuffer.append(Hex.u2(ropLabelSuccessorList.get(i2)));
                        if (size != 1 && primarySuccessorRopLabel == ropLabelSuccessorList.get(i2)) {
                            stringBuffer.append(" *");
                        }
                        stringBuffer.append('\n');
                    }
                }
                stringBuffer.append("  live out:" + ssaBasicBlock.getLiveOutRegs());
                stringBuffer.append("\n");
            } else {
                this.suppressDump = false;
                setAt(byteArray, 0);
                parsed(byteArray, 0, byteArray.size(), stringBuffer.toString());
                this.suppressDump = true;
                return;
            }
        }
    }
}
