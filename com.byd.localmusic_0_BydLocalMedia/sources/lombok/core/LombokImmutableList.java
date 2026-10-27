package lombok.core;

import android.app.NotificationManager;
import android.content.ContentProvider;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import java.util.Iterator;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/LombokImmutableList.SCL.lombok */
public final class LombokImmutableList<T> implements Iterable<T> {
    private Object[] content;
    private static final LombokImmutableList<?> EMPTY = null;

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    static java.lang.String getString(int r4, java.lang.String r5) {
        /*
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            super/*android.app.ActivityManager*/.isHighEndGfx()
            lombok.core.LombokImmutableList.EMPTY = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.getString(int, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>()Llombok/core/LombokImmutableList<TT;>; */
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 2
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    public static android.media.MediaMetadataEditor putBitmap(int r2, android.graphics.Bitmap r3) {
        /*
            lombok.core.LombokImmutableList<?> r0 = lombok.core.LombokImmutableList.EMPTY
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.putBitmap(int, android.graphics.Bitmap):android.media.MediaMetadataEditor");
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;)Llombok/core/LombokImmutableList<TT;>; */
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 8 out of bounds for length 8
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    public static android.media.MediaMetadataEditor putLong(
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 8 out of bounds for length 8
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r7v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;TT;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.app.ActivityManager, android.media.MediaMetadataEditor, lombok.core.LombokImmutableList] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 ??, still in use, count: 2, list:
          (r0v0 ?? I:android.media.MediaMetadataEditor) from 0x0013: RETURN (r0v0 ?? I:android.media.MediaMetadataEditor)
          (r0v0 ?? I:android.app.ActivityManager) from 0x0010: INVOKE (r0v0 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static android.media.MediaMetadataEditor putObject(int r7, java.lang.Object r8) {
        /*
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = r2
            r4 = 0
            r5 = r7
            r3[r4] = r5
            r3 = r2
            r4 = 1
            r5 = r8
            r3[r4] = r5
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.putObject(int, java.lang.Object):android.media.MediaMetadataEditor");
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;TT;TT;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0012: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('c' T)]), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.app.ActivityManager, android.media.MediaMetadataEditor, lombok.core.LombokImmutableList] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 ??, still in use, count: 2, list:
          (r0v0 ?? I:android.media.MediaMetadataEditor) from 0x0017: RETURN (r0v0 ?? I:android.media.MediaMetadataEditor)
          (r0v0 ?? I:android.app.ActivityManager) from 0x0014: INVOKE (r0v0 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static android.media.MediaMetadataEditor putString(int r7, java.lang.String r8) {
        /*
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = r2
            r4 = 0
            r5 = r7
            r3[r4] = r5
            r3 = r2
            r4 = 1
            r5 = r8
            r3[r4] = r5
            r3 = r2
            r4 = 2
            r5 = r9
            r3[r4] = r5
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.putString(int, java.lang.String):android.media.MediaMetadataEditor");
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;TT;TT;TT;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0016: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('d' T)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x000a: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('a' T)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x000e: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r8 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('b' T)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0012: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('c' T)]), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.app.ActivityManager, lombok.core.LombokImmutableList, void] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 ??, still in use, count: 2, list:
          (r0v0 ?? I:void) from 0x001b: RETURN (r0v0 ?? I:void)
          (r0v0 ?? I:android.app.ActivityManager) from 0x0018: INVOKE (r0v0 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static void removeEditableKeys() {
        /*
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = r2
            r4 = 0
            r5 = r7
            r3[r4] = r5
            r3 = r2
            r4 = 1
            r5 = r8
            r3[r4] = r5
            r3 = r2
            r4 = 2
            r5 = r9
            r3[r4] = r5
            r3 = r2
            r4 = 3
            r5 = r10
            r3[r4] = r5
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.removeEditableKeys():void");
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;TT;TT;TT;TT;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0016: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('d' T)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x001a: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('e' T)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x000a: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('a' T)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x000e: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r8 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('b' T)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0012: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('c' T)]), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.app.ActivityManager, lombok.core.LombokImmutableList, void] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 ??, still in use, count: 2, list:
          (r0v0 ?? I:void) from 0x0020: RETURN (r0v0 ?? I:void)
          (r0v0 ?? I:android.app.ActivityManager) from 0x001d: INVOKE (r0v0 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    public static void m1065init() {
        /*
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = 5
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = r2
            r4 = 0
            r5 = r7
            r3[r4] = r5
            r3 = r2
            r4 = 1
            r5 = r8
            r3[r4] = r5
            r3 = r2
            r4 = 2
            r5 = r9
            r3[r4] = r5
            r3 = r2
            r4 = 3
            r5 = r10
            r3[r4] = r5
            r3 = r2
            r4 = 4
            r5 = r11
            r3[r4] = r5
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.m1065init():void");
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;TT;TT;TT;TT;TT;[TT;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x003f: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('e' T)]), block:B:6:0x000e */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0045: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('f' T)]), block:B:6:0x000e */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x0000: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('g' java.lang.Object[])]) (LINE:61), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x002b: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('a' T)]), block:B:6:0x000e */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0030: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('b' T)]), block:B:6:0x000e */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x0035: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r8 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('c' T)]), block:B:6:0x000e */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x003a: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('d' T)]), block:B:6:0x000e */
    /* JADX WARN: Type inference failed for: r0v14, types: [android.app.ActivityManager, android.graphics.Bitmap$Config, lombok.core.LombokImmutableList] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v14 ??, still in use, count: 2, list:
          (r0v14 ?? I:android.graphics.Bitmap$Config) from 0x0051: RETURN (r0v14 ?? I:android.graphics.Bitmap$Config)
          (r0v14 ?? I:android.app.ActivityManager) from 0x004e: INVOKE (r0v14 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static android.graphics.Bitmap.Config getActualConfig() {
        /*
            r0 = r12
            if (r0 != 0) goto Lc
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            goto Le
        Lc:
            r0 = r12
        Le:
            r13 = r0
            r0 = r13
            int r0 = r0.length
            r1 = 6
            int r0 = r0 + r1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r14 = r0
            r0 = r13
            r1 = 0
            r2 = r14
            r3 = 6
            r4 = r13
            int r4 = r4.length
            android.app.AppOpsManager.isListeningForOpNoted()
            r0 = r14
            r1 = 0
            r2 = r6
            r0[r1] = r2
            r0 = r14
            r1 = 1
            r2 = r7
            r0[r1] = r2
            r0 = r14
            r1 = 2
            r2 = r8
            r0[r1] = r2
            r0 = r14
            r1 = 3
            r2 = r9
            r0[r1] = r2
            r0 = r14
            r1 = 4
            r2 = r10
            r0[r1] = r2
            r0 = r14
            r1 = 5
            r2 = r11
            r0[r1] = r2
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = r14
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.getActualConfig():android.graphics.Bitmap$Config");
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(Ljava/util/Collection<+TT;>;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0004: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('list' java.util.Collection<? extends T>)]), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.app.ActivityManager, android.graphics.Bitmap$Config, lombok.core.LombokImmutableList] */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.app.Application, android.app.Application$ActivityLifecycleCallbacks] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 ??, still in use, count: 2, list:
          (r0v0 ?? I:android.graphics.Bitmap$Config) from 0x000d: RETURN (r0v0 ?? I:android.graphics.Bitmap$Config)
          (r0v0 ?? I:android.app.ActivityManager) from 0x000a: INVOKE (r0v0 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static android.graphics.Bitmap.Config getPreferredConfig() {
        /*
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = r4
            void r2 = r2.unregisterActivityLifecycleCallbacks(r4)
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.getPreferredConfig():android.graphics.Bitmap$Config");
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(Ljava/lang/Iterable<+TT;>;)Llombok/core/LombokImmutableList<TT;>; */
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v2 ??, still in use, count: 3, list:
          (r0v2 ?? I:android.view.View) from 0x0025: INVOKE (r0v4 ?? I:void) = (r0v2 ?? I:android.app.Fragment), (r0v2 ?? I:android.view.View), (r0v2 ?? I:android.os.Bundle) INTERFACE call: android.app.Fragment.onViewCreated(android.view.View, android.os.Bundle):void A[MD:(android.view.View, android.os.Bundle):void (m)]
          (r0v2 ?? I:android.app.Fragment) from 0x0025: INVOKE (r0v4 ?? I:void) = (r0v2 ?? I:android.app.Fragment), (r0v2 ?? I:android.view.View), (r0v2 ?? I:android.os.Bundle) INTERFACE call: android.app.Fragment.onViewCreated(android.view.View, android.os.Bundle):void A[MD:(android.view.View, android.os.Bundle):void (m)]
          (r0v2 ?? I:android.os.Bundle) from 0x0025: INVOKE (r0v4 ?? I:void) = (r0v2 ?? I:android.app.Fragment), (r0v2 ?? I:android.view.View), (r0v2 ?? I:android.os.Bundle) INTERFACE call: android.app.Fragment.onViewCreated(android.view.View, android.os.Bundle):void A[MD:(android.view.View, android.os.Bundle):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1123)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1134)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1172)
        	at jadx.core.dex.visitors.ConstructorVisitor.removeAssignChain(ConstructorVisitor.java:243)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:93)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static void setPreferredConfig(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v2 ??, still in use, count: 3, list:
          (r0v2 ?? I:android.view.View) from 0x0025: INVOKE (r0v4 ?? I:void) = (r0v2 ?? I:android.app.Fragment), (r0v2 ?? I:android.view.View), (r0v2 ?? I:android.os.Bundle) INTERFACE call: android.app.Fragment.onViewCreated(android.view.View, android.os.Bundle):void A[MD:(android.view.View, android.os.Bundle):void (m)]
          (r0v2 ?? I:android.app.Fragment) from 0x0025: INVOKE (r0v4 ?? I:void) = (r0v2 ?? I:android.app.Fragment), (r0v2 ?? I:android.view.View), (r0v2 ?? I:android.os.Bundle) INTERFACE call: android.app.Fragment.onViewCreated(android.view.View, android.os.Bundle):void A[MD:(android.view.View, android.os.Bundle):void (m)]
          (r0v2 ?? I:android.os.Bundle) from 0x0025: INVOKE (r0v4 ?? I:void) = (r0v2 ?? I:android.app.Fragment), (r0v2 ?? I:android.view.View), (r0v2 ?? I:android.os.Bundle) INTERFACE call: android.app.Fragment.onViewCreated(android.view.View, android.os.Bundle):void A[MD:(android.view.View, android.os.Bundle):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1123)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1134)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1172)
        	at jadx.core.dex.visitors.ConstructorVisitor.removeAssignChain(ConstructorVisitor.java:243)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:93)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r3v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>([TT;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0000: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('array' java.lang.Object[])]) (LINE:84), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.app.ActivityManager, lombok.core.LombokImmutableList, void] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v4 ??, still in use, count: 2, list:
          (r0v4 ?? I:void) from 0x0017: RETURN (r0v4 ?? I:void)
          (r0v4 ?? I:android.app.ActivityManager) from 0x0014: INVOKE (r0v4 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    static {
        /*
            r0 = r6
            int r0 = r0.length
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r7 = r0
            r0 = r6
            r1 = 0
            r2 = r7
            r3 = 0
            r4 = r6
            int r4 = r4.length
            android.app.AppOpsManager.isListeningForOpNoted()
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = r7
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.m1066clinit():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0005: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('content' java.lang.Object[])]), block:B:2:0x0000 */
    private LombokImmutableList() {
        Object[] content;
        super/*android.app.FragmentTransaction*/.setBreadCrumbTitle(this);
        this.content = content;
    }

    /* JADX WARN: Incorrect return type in method signature: (ITT;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.app.IActivityManager, int, java.lang.Object[], java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v2, types: [void] */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.app.ActivityManager, java.util.List, lombok.core.LombokImmutableList] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v5 ??, still in use, count: 2, list:
          (r0v5 ?? I:java.util.List) from 0x0017: RETURN (r0v5 ?? I:java.util.List)
          (r0v5 ?? I:android.app.ActivityManager) from 0x0014: INVOKE (r0v5 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public java.util.List _getFrameAtIndex(int r5, int r6, android.media.MediaMetadataRetriever.BitmapParams r7) {
        /*
            r4 = this;
            r0 = r4
            java.lang.Object[] r0 = r0.content
            void r0 = r0.requestBugReportWithDescription(r0, r0, r0)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r7 = r0
            r0 = r7
            r1 = r5
            r2 = r6
            r0[r1] = r2
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = r7
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList._getFrameAtIndex(int, int, android.media.MediaMetadataRetriever$BitmapParams):java.util.List");
    }

    /* JADX WARN: Incorrect return type in method signature: (TT;)Llombok/core/LombokImmutableList<TT;>; */
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 10
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    public android.graphics.Bitmap _getFrameAtTime(
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 10
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r7v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    /* JADX WARN: Incorrect return type in method signature: (TT;)Llombok/core/LombokImmutableList<TT;>; */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9, types: [android.app.ActivityManager, android.graphics.Bitmap, lombok.core.LombokImmutableList] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v9 ??, still in use, count: 2, list:
          (r0v9 ?? I:android.graphics.Bitmap) from 0x0024: RETURN (r0v9 ?? I:android.graphics.Bitmap)
          (r0v9 ?? I:android.app.ActivityManager) from 0x0021: INVOKE (r0v9 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public android.graphics.Bitmap _getImageAtIndex(int r7, android.media.MediaMetadataRetriever.BitmapParams r8) {
        /*
            r6 = this;
            r0 = r6
            java.lang.Object[] r0 = r0.content
            int r0 = r0.length
            r8 = r0
            r0 = r8
            r1 = 1
            int r0 = r0 + r1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r9 = r0
            r0 = r6
            java.lang.Object[] r0 = r0.content
            r1 = 0
            r2 = r9
            r3 = 1
            r4 = r8
            android.app.AppOpsManager.isListeningForOpNoted()
            r0 = r9
            r1 = 0
            r2 = r7
            r0[r1] = r2
            lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
            r1 = r0
            r2 = r9
            super/*android.app.ActivityManager*/.isHighEndGfx()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList._getImageAtIndex(int, android.media.MediaMetadataRetriever$BitmapParams):android.graphics.Bitmap");
    }

    /* JADX WARN: Incorrect return type in method signature: (TT;)I */
    /* JADX WARN: Multi-variable type inference failed */
    public void _setDataSource(MediaDataSource mediaDataSource) {
        int length = this.content.length;
        if (mediaDataSource == 0) {
            int i = 0;
            while (i < length) {
                if (this.content[i] == null) {
                    return i == true ? 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: RETURN (wrap:void:?: TERNARY null = ((r7v4 'i' int A[D('i' int)]) == true) ? (1 void) : (0 void)) in method: lombok.core.LombokImmutableList._setDataSource(android.media.MediaDataSource):void, file: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/LombokImmutableList.SCL.lombok
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:226)
                        	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Unknown type in literalToString: void
                        	at jadx.core.codegen.TypeGen.literalToString(TypeGen.java:97)
                        	at jadx.core.codegen.TypeGen.literalToString(TypeGen.java:37)
                        	at jadx.core.codegen.InsnGen.lit(InsnGen.java:183)
                        	at jadx.core.codegen.InsnGen.addLiteralArg(InsnGen.java:130)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:119)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:1187)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:536)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                        	... 35 more
                        */
                    /*
                        this = this;
                        r0 = r4
                        java.lang.Object[] r0 = r0.content
                        int r0 = r0.length
                        r6 = r0
                        r0 = r5
                        if (r0 != 0) goto L24
                        r0 = 0
                        r7 = r0
                        goto L1d
                    Lf:
                        r0 = r4
                        java.lang.Object[] r0 = r0.content
                        r1 = r7
                        r0 = r0[r1]
                        if (r0 != 0) goto L1a
                        r0 = r7
                        return r0
                    L1a:
                        int r7 = r7 + 1
                    L1d:
                        r0 = r7
                        r1 = r6
                        if (r0 < r1) goto Lf
                        r0 = -1
                        return r0
                    L24:
                        r0 = 0
                        r7 = r0
                        goto L3b
                    L29:
                        r0 = r5
                        r1 = r4
                        java.lang.Object[] r1 = r1.content
                        r2 = r7
                        r1 = r1[r2]
                        android.app.NotificationManager$Policy r0 = r0.getConsolidatedNotificationPolicy()
                        if (r0 == 0) goto L38
                        r0 = r7
                        return r0
                    L38:
                        int r7 = r7 + 1
                    L3b:
                        r0 = r7
                        r1 = r6
                        if (r0 < r1) goto L29
                        r0 = -1
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList._setDataSource(android.media.MediaDataSource):void");
                }

                /* JADX WARN: Incorrect return type in method signature: (TT;)Llombok/core/LombokImmutableList<TT;>; */
                /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                    java.lang.ArrayIndexOutOfBoundsException: Index 6 out of bounds for length 6
                    	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
                    	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
                    	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
                    	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
                    	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
                    */
                public void _setDataSource(
                /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                    java.lang.ArrayIndexOutOfBoundsException: Index 6 out of bounds for length 6
                    	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
                    	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
                    	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
                    	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
                    */
                /*  JADX ERROR: Method generation error
                    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v0 ??
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                    	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                    */

                /* JADX WARN: Incorrect return type in method signature: (I)Llombok/core/LombokImmutableList<TT;>; */
                /* JADX WARN: Not initialized variable reg: 8, insn: 0x000d: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r8 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('idx' int)]) (LINE:134), block:B:2:0x0000 */
                /* JADX WARN: Type inference failed for: r0v8, types: [android.app.ActivityManager, lombok.core.LombokImmutableList, void] */
                /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
                    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v8 ??, still in use, count: 2, list:
                      (r0v8 ?? I:void) from 0x003c: RETURN (r0v8 ?? I:void)
                      (r0v8 ?? I:android.app.ActivityManager) from 0x0039: INVOKE (r0v8 ?? I:android.app.ActivityManager) SUPER call: android.app.ActivityManager.isHighEndGfx():boolean A[MD:():boolean (m)]
                    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
                    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
                    	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
                    	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
                    	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
                    	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
                    */
                public void close() {
                    /*
                        r7 = this;
                        r0 = r7
                        java.lang.Object[] r0 = r0.content
                        int r0 = r0.length
                        r9 = r0
                        r0 = r9
                        r1 = 1
                        int r0 = r0 - r1
                        java.lang.Object[] r0 = new java.lang.Object[r0]
                        r10 = r0
                        r0 = r8
                        if (r0 <= 0) goto L1c
                        r0 = r7
                        java.lang.Object[] r0 = r0.content
                        r1 = 0
                        r2 = r10
                        r3 = 0
                        r4 = r8
                        android.app.AppOpsManager.isListeningForOpNoted()
                    L1c:
                        r0 = r8
                        r1 = r9
                        r2 = 1
                        int r1 = r1 - r2
                        if (r0 >= r1) goto L34
                        r0 = r7
                        java.lang.Object[] r0 = r0.content
                        r1 = r8
                        r2 = 1
                        int r1 = r1 + r2
                        r2 = r10
                        r3 = r8
                        r4 = r9
                        r5 = r8
                        int r4 = r4 - r5
                        r5 = 1
                        int r4 = r4 - r5
                        android.app.AppOpsManager.isListeningForOpNoted()
                    L34:
                        lombok.core.LombokImmutableList r0 = new lombok.core.LombokImmutableList
                        r1 = r0
                        r2 = r10
                        super/*android.app.ActivityManager*/.isHighEndGfx()
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.close():void");
                }

                /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                    java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
                    	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
                    	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
                    	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
                    	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
                    	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
                    */
                public java.lang.String convertGenreTag(java.lang.String r3) {
                    /*
                        r2 = this;
                        r0 = r2
                        java.lang.Object[] r0 = r0.content
                        int r0 = r0.length
                        if (r0 != 0) goto La
                        r0 = 1
                        return r0
                    La:
                        r0 = 0
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.convertGenreTag(java.lang.String):java.lang.String");
                }

                /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                    java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
                    	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
                    	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
                    	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
                    	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
                    	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
                    */
                public java.lang.String extractMetadata(int r3) {
                    /*
                        r2 = this;
                        r0 = r2
                        java.lang.Object[] r0 = r0.content
                        int r0 = r0.length
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.extractMetadata(int):java.lang.String");
                }

                /* JADX WARN: Incorrect return type in method signature: (I)TT; */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Not initialized variable reg: 4, insn: 0x0004: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('idx' int)]), block:B:2:0x0000 */
                /* JADX WARN: Type inference failed for: r0v2, types: [void] */
                public void finalize() {
                }

                /* JADX WARN: Incorrect return type in method signature: (TT;)Z */
                /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('in' T)]) (LINE:153), block:B:2:0x0000 */
                public byte[] getEmbeddedPicture() {
                    NotificationManager notificationManager;
                    if (notificationManager == null) {
                        for (Object e : this.content) {
                            if (e == null) {
                                return 1;
                            }
                        }
                        return null;
                    }
                    for (Object obj : this.content) {
                        if (notificationManager.getConsolidatedNotificationPolicy() != null) {
                            return 1;
                        }
                    }
                    return null;
                }

                /* JADX INFO: renamed from: lombok.core.LombokImmutableList$1, reason: invalid class name */
                /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/LombokImmutableList$1.SCL.lombok */
                class AnonymousClass1 implements Iterator<T> {
                    private int idx = 0;
                    final /* synthetic */ LombokImmutableList this$0;

                    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
                    AnonymousClass1() {
                        LombokImmutableList lombokImmutableList;
                        this.this$0 = lombokImmutableList;
                        return;
                    }

                    /*  JADX ERROR: IllegalArgumentException in pass: ConstructorVisitor
                        java.lang.IllegalArgumentException: Illegal Capacity: -1
                        	at java.base/java.util.ArrayList.<init>(ArrayList.java:160)
                        	at jadx.core.dex.nodes.InsnNode.<init>(InsnNode.java:36)
                        	at jadx.core.dex.instructions.BaseInvokeNode.<init>(BaseInvokeNode.java:11)
                        	at jadx.core.dex.instructions.mods.ConstructorInsn.<init>(ConstructorInsn.java:32)
                        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:76)
                        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
                        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
                        */
                    public void cleanup() {
                        /*
                            r3 = this;
                            r0 = r3
                            int r0 = r0.idx
                            r1 = r3
                            lombok.core.LombokImmutableList r1 = r1.this$0
                            void r1 = android.R.<init>()
                            int r1 = r1.length
                            if (r0 >= r1) goto L11
                            r0 = 1
                            return r0
                        L11:
                            r0 = 0
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.AnonymousClass1.cleanup():void");
                    }

                    /* JADX WARN: Incorrect return type in method signature: ()TT; */
                    /*  JADX ERROR: IllegalArgumentException in pass: ConstructorVisitor
                        java.lang.IllegalArgumentException: Illegal Capacity: -1
                        	at java.base/java.util.ArrayList.<init>(ArrayList.java:160)
                        	at jadx.core.dex.nodes.InsnNode.<init>(InsnNode.java:36)
                        	at jadx.core.dex.instructions.BaseInvokeNode.<init>(BaseInvokeNode.java:11)
                        	at jadx.core.dex.instructions.mods.ConstructorInsn.<init>(ConstructorInsn.java:32)
                        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:76)
                        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
                        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
                        */
                    public void dexoptNextPackage() {
                        /*
                            r6 = this;
                            r0 = r6
                            int r0 = r0.idx
                            r1 = r6
                            lombok.core.LombokImmutableList r1 = r1.this$0
                            void r1 = android.R.<init>()
                            int r1 = r1.length
                            if (r0 >= r1) goto L23
                            r0 = r6
                            lombok.core.LombokImmutableList r0 = r0.this$0
                            void r0 = android.R.<init>()
                            r1 = r6
                            r2 = r1
                            int r2 = r2.idx
                            r3 = r2; r2 = r1; r1 = r3; 
                            r4 = 1
                            int r3 = r3 + r4
                            r2.idx = r3
                            r0 = r0[r1]
                            return r0
                        L23:
                            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
                            r1 = r0
                            super/*android.accessibilityservice.-$$Lambda$AccessibilityService$q1syDPtLKDyMLkI7cIJLVQqmK_Y*/.onResult(r0)
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.AnonymousClass1.dexoptNextPackage():void");
                    }

                    /* JADX WARN: Type inference failed for: r0v0, types: [android.accessibilityservice.AccessibilityButtonController$AccessibilityButtonCallback, boolean, java.lang.Throwable, java.lang.UnsupportedOperationException] */
                    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
                        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 ??, still in use, count: 3, list:
                          (r0v0 ?? I:boolean) from 0x0006: INVOKE 
                          (r0v0 ?? I:android.accessibilityservice.AccessibilityButtonController$AccessibilityButtonCallback)
                          ("List is immutable")
                          (r0v0 ?? I:boolean)
                         SUPER call: android.accessibilityservice.AccessibilityButtonController.AccessibilityButtonCallback.onAvailabilityChanged(android.accessibilityservice.AccessibilityButtonController, boolean):void A[MD:(android.accessibilityservice.AccessibilityButtonController, boolean):void (m)]
                          (r0v0 ?? I:java.lang.Throwable) from 0x0009: THROW (r0v0 ?? I:java.lang.Throwable)
                          (r0v0 ?? I:android.accessibilityservice.AccessibilityButtonController$AccessibilityButtonCallback) from 0x0006: INVOKE 
                          (r0v0 ?? I:android.accessibilityservice.AccessibilityButtonController$AccessibilityButtonCallback)
                          ("List is immutable")
                          (r0v0 ?? I:boolean)
                         SUPER call: android.accessibilityservice.AccessibilityButtonController.AccessibilityButtonCallback.onAvailabilityChanged(android.accessibilityservice.AccessibilityButtonController, boolean):void A[MD:(android.accessibilityservice.AccessibilityButtonController, boolean):void (m)]
                        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
                        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
                        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
                        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
                        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
                        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
                        */
                    public java.lang.String getInterfaceDescriptor() {
                        /*
                            r4 = this;
                            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
                            r1 = r0
                            java.lang.String r2 = "List is immutable"
                            super/*android.accessibilityservice.AccessibilityButtonController.AccessibilityButtonCallback*/.onAvailabilityChanged(r2, r0)
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.AnonymousClass1.getInterfaceDescriptor():java.lang.String");
                    }
                }

                /* JADX WARN: Incorrect return type in method signature: ()Ljava/util/Iterator<TT;>; */
                /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                    java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
                    	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
                    	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
                    	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
                    	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
                    	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
                    */
                public byte[] getEmbeddedPicture(int r5) {
                    /*
                        r4 = this;
                        lombok.core.LombokImmutableList$1 r0 = new lombok.core.LombokImmutableList$1
                        r1 = r0
                        r2 = r4
                        r1.<init>()
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.getEmbeddedPicture(int):byte[]");
                }

                /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                    java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
                    	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
                    	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
                    	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
                    	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
                    	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
                    */
                public android.graphics.Bitmap getFrameAtIndex(int r3) {
                    /*
                        r2 = this;
                        r0 = r2
                        java.lang.Object[] r0 = r0.content
                        java.lang.String r0 = android.content.ComponentName.flattenToShortString()
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokImmutableList.getFrameAtIndex(int):android.graphics.Bitmap");
                }

                /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                    java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
                    	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
                    	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
                    	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
                    	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
                    	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
                    */
                public android.graphics.Bitmap getFrameAtIndex(
                /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
                    java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
                    	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
                    	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
                    	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
                    	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
                    */
                /*  JADX ERROR: Method generation error
                    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v0 ??
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                    	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                    */

                /* JADX WARN: Type inference failed for: r0v1, types: [android.net.Uri, java.lang.Object[]] */
                /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.Bitmap, android.net.Uri] */
                public Bitmap getFrameAtTime() {
                    return ContentProvider.getUriWithoutUserId(this.content);
                }
            }
