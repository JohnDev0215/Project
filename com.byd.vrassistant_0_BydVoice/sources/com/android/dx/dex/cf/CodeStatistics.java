package com.android.dx.dex.cf;

import com.android.dx.dex.code.DalvCode;
import com.android.dx.rop.code.RopMethod;
import java.io.PrintStream;

/* JADX INFO: loaded from: classes.dex */
public final class CodeStatistics {
    private static final boolean DEBUG = false;
    public static int dexRunningDeltaInsns;
    public static int dexRunningDeltaRegisters;
    public static int dexRunningTotalInsns;
    public static int runningDeltaInsns;
    public static int runningDeltaRegisters;
    public static int runningOriginalBytes;
    public static int runningTotalInsns;

    private CodeStatistics() {
    }

    public static void updateOriginalByteCount(int i) {
        runningOriginalBytes += i;
    }

    public static void updateDexStatistics(DalvCode dalvCode, DalvCode dalvCode2) {
        dexRunningDeltaInsns += dalvCode2.getInsns().codeSize() - dalvCode.getInsns().codeSize();
        dexRunningDeltaRegisters += dalvCode2.getInsns().getRegistersSize() - dalvCode.getInsns().getRegistersSize();
        dexRunningTotalInsns += dalvCode2.getInsns().codeSize();
    }

    public static void updateRopStatistics(RopMethod ropMethod, RopMethod ropMethod2) {
        int effectiveInstructionCount = ropMethod.getBlocks().getEffectiveInstructionCount();
        int regCount = ropMethod.getBlocks().getRegCount();
        int effectiveInstructionCount2 = ropMethod2.getBlocks().getEffectiveInstructionCount();
        runningDeltaInsns += effectiveInstructionCount2 - effectiveInstructionCount;
        runningDeltaRegisters += ropMethod2.getBlocks().getRegCount() - regCount;
        runningTotalInsns += effectiveInstructionCount2;
    }

    public static void dumpStatistics(PrintStream printStream) {
        printStream.printf("Optimizer Delta Rop Insns: %d total: %d (%.2f%%) Delta Registers: %d\n", Integer.valueOf(runningDeltaInsns), Integer.valueOf(runningTotalInsns), Double.valueOf(((double) (runningDeltaInsns / (runningTotalInsns + Math.abs(r2)))) * 100.0d), Integer.valueOf(runningDeltaRegisters));
        printStream.printf("Optimizer Delta Dex Insns: Insns: %d total: %d (%.2f%%) Delta Registers: %d\n", Integer.valueOf(dexRunningDeltaInsns), Integer.valueOf(dexRunningTotalInsns), Double.valueOf(((double) (dexRunningDeltaInsns / (dexRunningTotalInsns + Math.abs(r1)))) * 100.0d), Integer.valueOf(dexRunningDeltaRegisters));
        printStream.printf("Original bytecode byte count: %d\n", Integer.valueOf(runningOriginalBytes));
    }
}
