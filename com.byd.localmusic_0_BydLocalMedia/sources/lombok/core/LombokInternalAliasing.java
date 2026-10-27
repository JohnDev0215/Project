package lombok.core;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/LombokInternalAliasing.SCL.lombok */
public class LombokInternalAliasing {
    public static final Map<String, String> ALIASES = null;
    public static final Map<String, Collection<String>> REVERSE_ALIASES = null;

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public LombokInternalAliasing(int r3) {
        /*
            r2 = this;
            r0 = r2
            super/*android.app.Activity*/.isChangingConfigurations()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokInternalAliasing.newArray(int):android.view.DisplayAddress$Network[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.animation.ObjectAnimator, android.animation.TypeConverter, android.graphics.Path, android.util.Property, java.util.Map<java.lang.String, java.lang.String>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object[], java.lang.String] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:358)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v4 boolean
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
        */
    public static java.lang.Object[] newArray(int r3) {
        /*
            r0 = r3
            if (r0 != 0) goto L6
            r0 = 0
            return r0
        L6:
            java.util.Map<java.lang.String, java.lang.String> r0 = lombok.core.LombokInternalAliasing.ALIASES
            r1 = r3
            android.animation.ObjectAnimator r0 = r0.ofObject(r1, r0, r0, r0)
            java.lang.String r0 = (java.lang.String) r0
            r4 = r0
            r0 = r4
            if (r0 != 0) goto L1b
            r0 = r3
            goto L1c
        L1b:
            r0 = r4
        L1c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokInternalAliasing.newArray(int):java.lang.Object[]");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v46 ??, still in use, count: 1, list:
          (r0v46 ?? I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) from 0x00a4: IF  (r0v46 ?? I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) != (r1v16 ?? I:??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:23:0x00d0
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1123)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:110)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    static java.lang.Object createFromParcel(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v46 ??, still in use, count: 1, list:
          (r0v46 ?? I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) from 0x00a4: IF  (r0v46 ?? I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) != (r1v16 ?? I:??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:23:0x00d0
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1123)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:110)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
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
}
