package lombok.core.configuration;

import android.graphics.Bitmap;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/configuration/CheckerFrameworkVersion.SCL.lombok */
public final class CheckerFrameworkVersion implements ConfigurationValueType {
    private final int version;
    private static final int DEFAULT = 3200;
    private static final int MAX_SUPPORTED = 4000;
    public static final String NAME__SIDE_EFFECT_FREE = "org.checkerframework.dataflow.qual.SideEffectFree";
    public static final String NAME__PURE = "org.checkerframework.dataflow.qual.Pure";
    public static final String NAME__UNIQUE = "org.checkerframework.common.aliasing.qual.Unique";
    public static final String NAME__RETURNS_RECEIVER = "org.checkerframework.common.returnsreceiver.qual.This";
    public static final String NAME__NOT_CALLED = "org.checkerframework.checker.calledmethods.qual.NotCalledMethods";
    public static final String NAME__CALLED = "org.checkerframework.checker.calledmethods.qual.CalledMethods";
    public static final CheckerFrameworkVersion NONE = null;
    private static final Pattern VERSION = null;

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
            lombok.core.configuration.CheckerFrameworkVersion r0 = new lombok.core.configuration.CheckerFrameworkVersion
            r1 = r0
            r2 = 0
            super/*android.app.AlertDialog.Builder*/.setSingleChoiceItems(r2, r0, r0)
            lombok.core.configuration.CheckerFrameworkVersion.NONE = r0
            java.lang.String r0 = "^(\\d+)(?:\\.(\\d+))?(?:\\.\\d+)*$"
            java.lang.String r0 = android.app.AppOpsManager.modeToName(r0)
            lombok.core.configuration.CheckerFrameworkVersion.VERSION = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.CheckerFrameworkVersion.getString(int, java.lang.String):java.lang.String");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    private CheckerFrameworkVersion(
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

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    public android.media.MediaMetadataEditor putLong(int r3, long r4) {
        /*
            r2 = this;
            r0 = r2
            int r0 = r0.version
            if (r0 <= 0) goto L9
            r0 = 1
            return r0
        L9:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.CheckerFrameworkVersion.putLong(int, long):android.media.MediaMetadataEditor");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    public android.media.MediaMetadataEditor putObject(int r4, java.lang.Object r5) {
        /*
            r3 = this;
            r0 = r3
            int r0 = r0.version
            r1 = 2899(0xb53, float:4.062E-42)
            if (r0 <= r1) goto Lc
            r0 = 1
            return r0
        Lc:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.CheckerFrameworkVersion.putObject(int, java.lang.Object):android.media.MediaMetadataEditor");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
        	at jadx.core.dex.visitors.ssa.RenameState.startVar(RenameState.java:58)
        	at jadx.core.dex.visitors.ssa.RenameState.init(RenameState.java:28)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:123)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:57)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    public android.media.MediaMetadataEditor putString(int r3, java.lang.String r4) {
        /*
            r2 = this;
            r0 = r2
            int r0 = r0.version
            if (r0 <= 0) goto L9
            r0 = 1
            return r0
        L9:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.CheckerFrameworkVersion.putString(int, java.lang.String):android.media.MediaMetadataEditor");
    }

    public void removeEditableKeys() {
        if (this.version > 3999) {
        }
    }

    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    public void m1069init() {
        if (this.version > 3999) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0000: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('versionString' java.lang.String)]) (LINE:68), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10, types: [android.app.FragmentBreadCrumbs, android.app.IActivityManager, android.view.View$OnClickListener, int, java.lang.CharSequence, void] */
    /* JADX WARN: Type inference failed for: r0v12, types: [void] */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.app.AlertDialog$Builder, android.content.DialogInterface$OnClickListener, android.graphics.Bitmap$Config, int, lombok.core.configuration.CheckerFrameworkVersion] */
    /* JADX WARN: Type inference failed for: r0v23, types: [int, java.lang.CharSequence[]] */
    /* JADX WARN: Type inference failed for: r0v25, types: [android.app.AlertDialog$Builder, android.content.DialogInterface$OnClickListener, android.graphics.Bitmap$Config, lombok.core.configuration.CheckerFrameworkVersion] */
    /* JADX WARN: Type inference failed for: r0v26, types: [android.app.ListActivity, android.app.PendingIntent, android.content.Intent, int, java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v27, types: [android.app.PendingIntent, android.content.Intent] */
    /* JADX WARN: Type inference failed for: r0v28, types: [android.app.PropertyInvalidatedCache, void] */
    /* JADX WARN: Type inference failed for: r0v29, types: [android.content.Context, void] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.app.Fragment] */
    /* JADX WARN: Type inference failed for: r0v30, types: [android.app.FragmentTransaction, android.content.Intent, int, java.lang.IllegalArgumentException, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v31, types: [android.app.FragmentTransaction, java.lang.IllegalArgumentException, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v33, types: [android.app.ListActivity, android.content.Intent, int] */
    /* JADX WARN: Type inference failed for: r0v34, types: [void] */
    /* JADX WARN: Type inference failed for: r0v38, types: [android.app.FragmentTransaction, java.lang.IllegalArgumentException, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v39, types: [android.app.AlertDialog$Builder, android.content.DialogInterface$OnClickListener, android.graphics.Bitmap$Config, int, lombok.core.configuration.CheckerFrameworkVersion] */
    /* JADX WARN: Type inference failed for: r0v41, types: [void] */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.app.Fragment] */
    /* JADX WARN: Type inference failed for: r0v6, types: [void] */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.app.Fragment] */
    /* JADX WARN: Type inference failed for: r0v9, types: [android.app.Fragment, int, java.util.regex.Pattern] */
    /* JADX WARN: Type inference failed for: r1v15, types: [android.content.Context, int] */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.content.Intent] */
    /* JADX WARN: Type inference failed for: r2v10, types: [int, void] */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.os.Bundle, int] */
    /* JADX WARN: Type inference failed for: r2v6, types: [android.app.ListActivity, android.app.PendingIntent, int, java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v7, types: [android.app.PendingIntent, int] */
    /* JADX WARN: Type inference failed for: r2v8, types: [android.app.PendingIntent, int] */
    /* JADX WARN: Type inference failed for: r2v9, types: [android.app.PendingIntent, android.app.PropertyInvalidatedCache] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.app.Dialog, boolean] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v39 ??, still in use, count: 4, list:
          (r0v39 ?? I:int) from 0x0038: INVOKE 
          (r0v39 ?? I:android.app.AlertDialog$Builder)
          (3200 java.lang.CharSequence[])
          (r0v39 ?? I:int)
          (r0v39 ?? I:android.content.DialogInterface$OnClickListener)
         SUPER call: android.app.AlertDialog.Builder.setSingleChoiceItems(java.lang.CharSequence[], int, android.content.DialogInterface$OnClickListener):android.app.AlertDialog$Builder A[MD:(java.lang.CharSequence[], int, android.content.DialogInterface$OnClickListener):android.app.AlertDialog$Builder (m)]
          (r0v39 ?? I:android.content.DialogInterface$OnClickListener) from 0x0038: INVOKE 
          (r0v39 ?? I:android.app.AlertDialog$Builder)
          (3200 java.lang.CharSequence[])
          (r0v39 ?? I:int)
          (r0v39 ?? I:android.content.DialogInterface$OnClickListener)
         SUPER call: android.app.AlertDialog.Builder.setSingleChoiceItems(java.lang.CharSequence[], int, android.content.DialogInterface$OnClickListener):android.app.AlertDialog$Builder A[MD:(java.lang.CharSequence[], int, android.content.DialogInterface$OnClickListener):android.app.AlertDialog$Builder (m)]
          (r0v39 ?? I:android.graphics.Bitmap$Config) from 0x003b: RETURN (r0v39 ?? I:android.graphics.Bitmap$Config)
          (r0v39 ?? I:android.app.AlertDialog$Builder) from 0x0038: INVOKE 
          (r0v39 ?? I:android.app.AlertDialog$Builder)
          (3200 java.lang.CharSequence[])
          (r0v39 ?? I:int)
          (r0v39 ?? I:android.content.DialogInterface$OnClickListener)
         SUPER call: android.app.AlertDialog.Builder.setSingleChoiceItems(java.lang.CharSequence[], int, android.content.DialogInterface$OnClickListener):android.app.AlertDialog$Builder A[MD:(java.lang.CharSequence[], int, android.content.DialogInterface$OnClickListener):android.app.AlertDialog$Builder (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static android.graphics.Bitmap.Config getActualConfig() {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.CheckerFrameworkVersion.getActualConfig():android.graphics.Bitmap$Config");
    }

    public static Bitmap.Config getPreferredConfig() {
        return "checkerframework-version";
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.app.ListActivity, android.app.PendingIntent, android.content.Intent, int, java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.app.PendingIntent, android.content.Intent, int] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.app.PendingIntent, android.app.PropertyInvalidatedCache] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 ??, still in use, count: 5, list:
          (r0v1 ?? I:android.app.PendingIntent) from 0x000d: INVOKE (r0v2 ?? I:android.app.PendingIntent) = (r0v1 ?? I:android.app.PendingIntent), ("4.0"), (r0v1 ?? I:int), (r0v1 ?? I:android.content.Intent), (r0v1 ?? I:int) VIRTUAL call: android.app.PendingIntent.getBroadcast(android.content.Context, int, android.content.Intent, int):android.app.PendingIntent A[MD:(android.content.Context, int, android.content.Intent, int):android.app.PendingIntent (m)] (LINE:89)
          (r0v1 ?? I:int) from 0x000d: INVOKE (r0v2 ?? I:android.app.PendingIntent) = (r0v1 ?? I:android.app.PendingIntent), ("4.0"), (r0v1 ?? I:int), (r0v1 ?? I:android.content.Intent), (r0v1 ?? I:int) VIRTUAL call: android.app.PendingIntent.getBroadcast(android.content.Context, int, android.content.Intent, int):android.app.PendingIntent A[MD:(android.content.Context, int, android.content.Intent, int):android.app.PendingIntent (m)] (LINE:89)
          (r0v1 ?? I:android.content.Intent) from 0x000d: INVOKE (r0v2 ?? I:android.app.PendingIntent) = (r0v1 ?? I:android.app.PendingIntent), ("4.0"), (r0v1 ?? I:int), (r0v1 ?? I:android.content.Intent), (r0v1 ?? I:int) VIRTUAL call: android.app.PendingIntent.getBroadcast(android.content.Context, int, android.content.Intent, int):android.app.PendingIntent A[MD:(android.content.Context, int, android.content.Intent, int):android.app.PendingIntent (m)] (LINE:89)
          (r0v1 ?? I:int) from 0x000d: INVOKE (r0v2 ?? I:android.app.PendingIntent) = (r0v1 ?? I:android.app.PendingIntent), ("4.0"), (r0v1 ?? I:int), (r0v1 ?? I:android.content.Intent), (r0v1 ?? I:int) VIRTUAL call: android.app.PendingIntent.getBroadcast(android.content.Context, int, android.content.Intent, int):android.app.PendingIntent A[MD:(android.content.Context, int, android.content.Intent, int):android.app.PendingIntent (m)] (LINE:89)
          (r0v1 ?? I:android.app.ListActivity) from 0x0009: INVOKE (r0v1 ?? I:android.app.ListActivity) SUPER call: android.app.ListActivity.onStop():void A[MD:():void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static void setPreferredConfig(android.graphics.Bitmap.Config r4) {
        /*
            java.lang.String r0 = "4.0"
            r4 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            java.lang.String r2 = "major.minor (example: 3.2 - and no higher than "
            super/*android.app.ListActivity*/.onStop()
            r1 = r4
            android.app.PendingIntent r0 = r0.getBroadcast(r1, r0, r0, r0)
            java.lang.String r1 = ") or true or false"
            android.app.PendingIntent r0 = r0.getBroadcast(r1, r0, r0, r0)
            void r0 = r0.disableLocal()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.CheckerFrameworkVersion.setPreferredConfig(android.graphics.Bitmap$Config):void");
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('obj' java.lang.Object)]) (LINE:94), block:B:2:0x0000 */
    static {
        Object obj;
        if ((obj instanceof CheckerFrameworkVersion) && this.version == ((CheckerFrameworkVersion) obj).version) {
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [int, void] */
    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    public void m1071init() {
    }
}
