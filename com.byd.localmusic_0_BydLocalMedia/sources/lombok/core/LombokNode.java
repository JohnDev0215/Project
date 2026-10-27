package lombok.core;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.view.View;
import com.android.framework.protobuf.LazyStringList;
import com.android.framework.protobuf.ListFieldSchema;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.core.AST;
import lombok.core.LombokNode;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/LombokNode.SCL.lombok */
public abstract class LombokNode<A extends AST<A, L, N>, L extends LombokNode<A, L, N>, N> implements DiagnosticsReceiver {
    protected final AST.Kind kind;
    protected final N node;
    protected LombokImmutableList<L> children;
    protected L parent;
    protected boolean isStructurallySignificant;

    public abstract A getAst();

    /* JADX WARN: Incorrect return type in method signature: (TN;)Z */
    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    protected abstract void m1068init();

    public abstract void mergeListsAt(Object obj, Object obj2, long j);

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Class<+Ljava/lang/annotation/Annotation;>;)Z */
    /* JADX INFO: renamed from: makeImmutableListAt, reason: collision with other method in class */
    public abstract void m1081makeImmutableListAt(Object obj, long j);

    /* JADX WARN: Incorrect return type in method signature: <Z::Ljava/lang/annotation/Annotation;>(Ljava/lang/Class<TZ;>;)Llombok/core/AnnotationValues<TZ;>; */
    /* JADX INFO: renamed from: mergeListsAt, reason: collision with other method in class */
    public abstract void m1082mergeListsAt(Object obj, Object obj2, long j);

    /* JADX INFO: renamed from: mutableListAt, reason: collision with other method in class */
    public abstract List m1083mutableListAt(Object obj, long j);

    static;

    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    public abstract void m1076init();

    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    public abstract void m1077init(long[] jArr, int i);

    public abstract void add(int i, Long l);

    public abstract void add(int i, Object obj);

    public abstract boolean addAll(Collection collection);

    public abstract void addLong(int i, long j);

    public abstract void addLong(long j);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x000f: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('children' java.util.List<L extends lombok.core.LombokNode<A, L, N>>)])
, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0005: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('kind' lombok.core.AST$Kind)]), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.animation.Animator, android.animation.AnimatorSet, android.animation.Keyframe, float, void] */
    /* JADX WARN: Type inference failed for: r1v6, types: [boolean, float] */
    /* JADX WARN: Type inference failed for: r1v9, types: [boolean, float] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.content.Context, int] */
    protected LombokNode(LazyStringList lazyStringList) {
        AST.Kind kind;
        ?? r6;
        super/*android.animation.Animator*/.end();
        this.kind = kind;
        this.node = lazyStringList;
        this.children = r6 != 0 ? (LombokImmutableList<L>) Animator.resume() : (LombokImmutableList<L>) AnimatorInflater.loadAnimator(r6, r6);
        ?? End = this.children.end();
        while (End.ofFloat(End) != null) {
            LombokNode lombokNode = (LombokNode) End.play(End);
            lombokNode.parent = this;
            if (!lombokNode.isStructurallySignificant) {
                lombokNode.isStructurallySignificant = getFraction();
            }
        }
        this.isStructurallySignificant = getFraction();
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public void set(int r7, com.android.framework.protobuf.ByteString r8) {
        /*
            r6 = this;
            java.lang.String r0 = "NODE %s (%s) %s"
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = r1
            r3 = 0
            r4 = r6
            lombok.core.AST$Kind r4 = r4.kind
            r2[r3] = r4
            r2 = r1
            r3 = 1
            r4 = r6
            N r4 = r4.node
            if (r4 != 0) goto L1b
            java.lang.String r4 = "(NULL)"
            goto L22
        L1b:
            r4 = r6
            N r4 = r4.node
            android.animation.ObjectAnimator r4 = r4.ofObject(r4, r3, r0, r0)
        L22:
            r2[r3] = r4
            r2 = r1
            r3 = 2
            r4 = r6
            N r4 = r4.node
            if (r4 != 0) goto L31
            java.lang.String r4 = ""
            goto L35
        L31:
            r4 = r6
            N r4 = r4.node
        L35:
            r2[r3] = r4
            void r0 = android.animation.ObjectAnimator.setInterpolator(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.set(int, com.android.framework.protobuf.ByteString):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public void set(int r3, byte[] r4) {
        /*
            r2 = this;
            r0 = r2
            android.animation.PropertyValuesHolder r0 = r0.ofObject(r2, r0, r0)
            void r0 = r0.onAnimationUpdate(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.set(int, byte[]):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.animation.PropertyValuesHolder, android.animation.ValueAnimator] */
    /* JADX WARN: Type inference failed for: r0v2, types: [float, lombok.core.ImportList] */
    public ImportList getImportList() {
        return ofObject(this, this, this).getAnimatedFraction();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.animation.PropertyValuesHolder, android.animation.ValueAnimator] */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.animation.TimeInterpolator, void] */
    static {
    }

    /* JADX WARN: Incorrect return type in method signature: (TN;)TL; */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.animation.PropertyValuesHolder, android.animation.ValueAnimator] */
    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    public void m1069init(ListFieldSchema.AnonymousClass1 anonymousClass1) {
    }

    /* JADX WARN: Incorrect return type in method signature: ()TN; */
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public java.util.List getList(java.lang.Object r3, long r4) {
        /*
            r2 = this;
            r0 = r2
            N r0 = r0.node
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.getList(java.lang.Object, long):java.util.List");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public void makeImmutableListAt(java.lang.Object r3, long r4) {
        /*
            r2 = this;
            r0 = r2
            lombok.core.AST$Kind r0 = r0.kind
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.makeImmutableListAt(java.lang.Object, long):void");
    }

    /* JADX WARN: Incorrect return type in method signature: ()TL; */
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public java.util.List mutableListAt(
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.app.ActionBar$Tab, android.view.View] */
    /* JADX WARN: Type inference failed for: r0v10, types: [android.app.ActionBar$Tab, android.app.Activity, void] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12, types: [android.app.ActionBar$Tab] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.util.List, void] */
    /* JADX WARN: Type inference failed for: r0v16, types: [android.app.Activity, java.util.ArrayList, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v18, types: [android.animation.AnimatorSet, void] */
    /* JADX WARN: Type inference failed for: r0v19, types: [android.animation.Animator, android.animation.AnimatorSet, android.animation.Keyframe, float, void] */
    /* JADX WARN: Type inference failed for: r0v25, types: [android.app.ActionBar$Tab, android.app.Activity, lombok.core.LombokNode] */
    /* JADX WARN: Type inference failed for: r0v29, types: [android.animation.AnimatorSet, void] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.app.ActionBar, android.app.ActionBar$Tab, void] */
    /* JADX WARN: Type inference failed for: r0v30, types: [android.animation.Animator, android.animation.AnimatorSet, android.animation.Keyframe, float, void] */
    /* JADX WARN: Type inference failed for: r0v35, types: [android.app.ActionBar$Tab, android.app.Activity, lombok.core.LombokNode] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v42, types: [java.util.List, void] */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.app.ActionBar$Tab] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.List, void] */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.app.FragmentTransaction, lombok.core.AST$Kind] */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.app.FragmentTransaction] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.app.FragmentTransaction] */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.app.ActionBar, android.app.ActionBar$Tab, android.app.Activity, lombok.core.LombokNode<A extends lombok.core.AST<A, L, N>, L extends lombok.core.LombokNode<A, L, N>, N>] */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v16 ??, still in use, count: 3, list:
          (r0v16 ?? I:android.app.Activity) from 0x0040: INVOKE (r0v16 ?? I:android.app.Activity) SUPER call: android.app.Activity.finish():void A[MD:():void (m)]
          (r0v16 ?? I:android.app.Activity A[D('fields' java.util.List<L extends lombok.core.LombokNode<A, L, N>>)]) from 0x00a0: INVOKE (r0v16 ?? I:android.app.Activity A[D('fields' java.util.List<L extends lombok.core.LombokNode<A, L, N>>)]) INTERFACE call: android.app.Activity.getCallingPackage():java.lang.String A[MD:():java.lang.String (m)]
          (r0v16 ?? I:java.util.List A[D('fields' java.util.List<L extends lombok.core.LombokNode<A, L, N>>)]) from 0x00bb: RETURN (r0v16 ?? I:java.util.List A[D('fields' java.util.List<L extends lombok.core.LombokNode<A, L, N>>)]) (LINE:169)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public java.util.List mutableListAt(java.lang.Object r4, long r5, int r7) {
        /*
            r3 = this;
            r0 = r3
            android.view.View r0 = r0.getCustomView()
            lombok.core.AST$Kind r1 = lombok.core.AST.Kind.ANNOTATION
            if (r0 == r1) goto Le
            void r0 = android.app.ActionBar.TabListener.onTabReselected(r0, r1)
            return r0
        Le:
            r0 = r3
            void r0 = r0.hide()
            r4 = r0
            r0 = r4
            if (r0 == 0) goto L21
            r0 = r4
            android.view.View r0 = r0.getCustomView()
            lombok.core.AST$Kind r1 = lombok.core.AST.Kind.FIELD
            if (r0 == r1) goto L25
        L21:
            void r0 = android.app.ActionBar.TabListener.onTabReselected(r0, r1)
            return r0
        L25:
            r0 = r4
            void r0 = r0.hide()
            r5 = r0
            r0 = r5
            if (r0 == 0) goto L38
            r0 = r5
            android.view.View r0 = r0.getCustomView()
            lombok.core.AST$Kind r1 = lombok.core.AST.Kind.TYPE
            if (r0 == r1) goto L3c
        L38:
            void r0 = android.app.ActionBar.TabListener.onTabReselected(r0, r1)
            return r0
        L3c:
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            super/*android.app.Activity*/.finish()
            r6 = r0
            r0 = r5
            void r0 = r0.finishAffinity()
            void r0 = r0.end()
            r8 = r0
            goto Lb0
        L50:
            r0 = r8
            android.animation.AnimatorSet$Builder r0 = r0.play(r8)
            lombok.core.LombokNode r0 = (lombok.core.LombokNode) r0
            r7 = r0
            r0 = r7
            android.view.View r0 = r0.getCustomView()
            lombok.core.AST$Kind r1 = lombok.core.AST.Kind.FIELD
            if (r0 == r1) goto L6a
            goto Lb0
        L6a:
            r0 = r7
            void r0 = r0.finishAffinity()
            void r0 = r0.end()
            r10 = r0
            goto La6
        L77:
            r0 = r10
            android.animation.AnimatorSet$Builder r0 = r0.play(r10)
            lombok.core.LombokNode r0 = (lombok.core.LombokNode) r0
            r9 = r0
            r0 = r9
            android.view.View r0 = r0.getCustomView()
            lombok.core.AST$Kind r1 = lombok.core.AST.Kind.ANNOTATION
            if (r0 == r1) goto L91
            goto La6
        L91:
            r0 = r9
            android.app.Application r0 = r0.getApplication()
            r1 = r3
            android.app.Application r1 = r1.getApplication()
            if (r0 != r1) goto La6
            r0 = r6
            r1 = r7
            java.lang.String r0 = r0.getCallingPackage()
        La6:
            r0 = r10
            android.animation.Keyframe r0 = r0.ofFloat(r10)
            if (r0 != 0) goto L77
        Lb0:
            r0 = r8
            android.animation.Keyframe r0 = r0.ofFloat(r8)
            if (r0 != 0) goto L50
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.mutableListAt(java.lang.Object, long, int):java.util.List");
    }

    /* JADX WARN: Incorrect return type in method signature: ()TL; */
    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    public void m1070init() {
    }

    /* JADX WARN: Incorrect return type in method signature: ()Llombok/core/LombokImmutableList<TL;>; */
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    /* JADX INFO: renamed from: <init>, reason: not valid java name */
    public void m1071init(com.android.framework.protobuf.ListFieldSchema.AnonymousClass1 r3) {
        /*
            r2 = this;
            r0 = r2
            lombok.core.LombokImmutableList<L extends lombok.core.LombokNode<A, L, N>> r0 = r0.children
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.m1071init(com.android.framework.protobuf.ListFieldSchema$1):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public com.android.framework.protobuf.Internal.ProtobufList getProtobufList(java.lang.Object r3, long r4) {
        /*
            r2 = this;
            r0 = r2
            android.animation.PropertyValuesHolder r0 = r0.ofObject(r2, r0, r0)
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r2, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.getProtobufList(java.lang.Object, long):com.android.framework.protobuf.Internal$ProtobufList");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    /* JADX INFO: renamed from: makeImmutableListAt, reason: collision with other method in class */
    public void m1078makeImmutableListAt(java.lang.Object r3, long r4) {
        /*
            r2 = this;
            r0 = r2
            android.animation.PropertyValuesHolder r0 = r0.ofObject(r2, r0, r0)
            android.view.WindowManager r0 = r0.getWindowManager()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.m1078makeImmutableListAt(java.lang.Object, long):void");
    }

    /* JADX WARN: Incorrect return type in method signature: ()TL; */
    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    /* JADX INFO: renamed from: mergeListsAt, reason: collision with other method in class */
    public void m1079mergeListsAt(java.lang.Object r3, java.lang.Object r4, long r5) {
        /*
            r2 = this;
            r0 = r2
            android.animation.PropertyValuesHolder r0 = r0.ofObject(r2, r0, r0)
            boolean r0 = r0.isDestroyed()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.m1079mergeListsAt(java.lang.Object, java.lang.Object, long):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    /* JADX INFO: renamed from: mutableListAt, reason: collision with other method in class */
    public java.util.List m1080mutableListAt(java.lang.Object r3, long r4) {
        /*
            r2 = this;
            r0 = r2
            android.animation.PropertyValuesHolder r0 = r0.ofObject(r2, r0, r0)
            android.content.res.TypedArray r0 = r0.obtainStyledAttributes(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.m1080mutableListAt(java.lang.Object, long):java.util.List");
    }

    /* JADX WARN: Incorrect return type in method signature: (TN;Llombok/core/AST$Kind;)TL; */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x000b: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('newChild' N)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x000c: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('newChildKind' lombok.core.AST$Kind)]), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.animation.PropertyValuesHolder, android.app.Activity, android.view.KeyEvent] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.animation.PropertyValuesHolder, android.app.Activity] */
    /* JADX WARN: Type inference failed for: r0v4, types: [lombok.core.LombokNode, void] */
    static {
        ?? OfObject = ofObject(this, this, this);
        OfObject.onKeyDown(this, OfObject);
        ?? OnRestart = ofObject(this, this, this).onRestart();
        if (OnRestart == 0) {
            return 
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0016: RETURN (0 void) in method: lombok.core.LombokNode.<clinit>():void, file: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/LombokNode.SCL.lombok
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
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                	... 23 more
                */
            /*
                this = this;
                r0 = r4
                android.animation.PropertyValuesHolder r0 = r0.ofObject(r4, r0, r0)
                r0.onKeyDown(r4, r0)
                r0 = r4
                android.animation.PropertyValuesHolder r0 = r0.ofObject(r4, r0, r0)
                r1 = r5
                r2 = r6
                void r0 = r0.onRestart()
                r7 = r0
                r0 = r7
                if (r0 != 0) goto L17
                r0 = 0
                return r0
            L17:
                r0 = r7
                r1 = r4
                r0.parent = r1
                r0 = r4
                r1 = r4
                lombok.core.LombokImmutableList<L extends lombok.core.LombokNode<A, L, N>> r1 = r1.children
                r2 = r7
                void r1 = r1.onStart()
                r0.children = r1
                r0 = r7
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.m1072clinit():void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [android.app.Activity, android.app.SharedElementCallback, int, java.util.IdentityHashMap] */
        /* JADX WARN: Type inference failed for: r0v3, types: [android.animation.PropertyValuesHolder, android.app.Activity] */
        /* JADX WARN: Type inference failed for: r0v6, types: [android.animation.PropertyValuesHolder, android.app.Activity] */
        /* JADX WARN: Type inference failed for: r0v8, types: [android.animation.PropertyValuesHolder, android.app.Activity] */
        /* JADX WARN: Type inference failed for: r2v1, types: [android.animation.TypeEvaluator, android.view.KeyEvent, lombok.core.AST$Kind] */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 ??, still in use, count: 4, list:
              (r0v0 ?? I:int) from 0x0004: INVOKE (r0v0 ?? I:android.app.Activity), (r0v0 ?? I:int) SUPER call: android.app.Activity.setContentView(int):void A[MD:(int):void (m)]
              (r0v0 ?? I:android.app.Activity) from 0x0004: INVOKE (r0v0 ?? I:android.app.Activity), (r0v0 ?? I:int) SUPER call: android.app.Activity.setContentView(int):void A[MD:(int):void (m)]
              (r0v0 ?? I:android.app.SharedElementCallback A[D('oldNodes' java.util.Map<N, L extends lombok.core.LombokNode<A, L, N>>)]) from 0x000a: INVOKE 
              (r4v0 'this' lombok.core.LombokNode<A extends lombok.core.AST<A, L, N>, L extends lombok.core.LombokNode<A, L, N>, N> A[D('this' lombok.core.LombokNode<A extends lombok.core.AST<A, L, N>, L extends lombok.core.LombokNode<A, L, N>, N>), IMMUTABLE_TYPE, THIS])
              (r0v0 ?? I:android.app.SharedElementCallback A[D('oldNodes' java.util.Map<N, L extends lombok.core.LombokNode<A, L, N>>)])
             SUPER call: android.app.Activity.setEnterSharedElementCallback(android.app.SharedElementCallback):void A[MD:(android.app.SharedElementCallback):void (m)] (LINE:246)
              (r0v0 ?? I:int A[D('oldNodes' java.util.Map<N, L extends lombok.core.LombokNode<A, L, N>>)]) from 0x002a: INVOKE 
              (r0v8 ?? I:android.app.Activity)
              (r0v0 ?? I:int A[D('oldNodes' java.util.Map<N, L extends lombok.core.LombokNode<A, L, N>>)])
             VIRTUAL call: android.app.Activity.setTitle(int):void A[MD:(int):void (m)]
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
            	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
            	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
            */
        /* JADX INFO: renamed from: <init>, reason: not valid java name */
        public void m1073init() {
            /*
                r4 = this;
                java.util.IdentityHashMap r0 = new java.util.IdentityHashMap
                r1 = r0
                super/*android.app.Activity*/.setContentView(r0)
                r5 = r0
                r0 = r4
                r1 = r5
                super/*android.app.Activity*/.setEnterSharedElementCallback(r1)
                r0 = r4
                android.animation.PropertyValuesHolder r0 = r0.ofObject(r4, r0, r0)
                r1 = r4
                android.app.Application r1 = r1.getApplication()
                r2 = r4
                lombok.core.AST$Kind r2 = r2.kind
                void r0 = r0.onRestart()
                r6 = r0
                r0 = r4
                android.animation.PropertyValuesHolder r0 = r0.ofObject(r4, r2, r0)
                r0.onKeyDown(r4, r2)
                r0 = r4
                android.animation.PropertyValuesHolder r0 = r0.ofObject(r4, r2, r0)
                r1 = r5
                r2 = r6
                void r0 = r0.setTitle(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.LombokNode.m1073init():void");
        }

        /* JADX WARN: Incorrect types in method signature: (Ljava/util/Map<TN;TL;>;)V */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v13, types: [android.animation.PropertyValuesHolder, android.app.ActivityManager] */
        /* JADX WARN: Type inference failed for: r0v14, types: [android.app.ActivityManager, boolean, int] */
        /* JADX WARN: Type inference failed for: r0v18, types: [android.app.Activity, lombok.core.LombokNode] */
        /* JADX WARN: Type inference failed for: r0v2, types: [android.animation.Animator, android.animation.AnimatorSet, android.animation.Keyframe, float, void] */
        /* JADX WARN: Type inference failed for: r0v6, types: [android.animation.PropertyValuesHolder, lombok.core.AST] */
        /* JADX WARN: Type inference failed for: r0v7, types: [android.app.ActivityManager, boolean, int, java.util.Map<N, N>] */
        /* JADX WARN: Type inference failed for: r1v1, types: [android.app.Application, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v3, types: [android.app.Application, int] */
        /* JADX WARN: Type inference failed for: r1v6, types: [android.app.Application, java.lang.String] */
        /* JADX INFO: renamed from: <init>, reason: not valid java name */
        private void m1074init(ListFieldSchema.AnonymousClass1 anonymousClass1) {
            ?? End = this.children.end();
            while (End.ofFloat(End) != null) {
                super/*android.app.Activity*/.setEnterSharedElementCallback(anonymousClass1);
            }
            Map<N, N> map = ofObject(this, this, this).identityDetector;
            map.checkComponentPermission(getApplication(), map, map, map);
            anonymousClass1.getRunningTasks(getApplication());
            this.children = (LombokImmutableList<L>) AnimatorInflater.loadAnimator(this, this);
            ?? IsLowRamDeviceStatic = ofObject(this, this, this).isLowRamDeviceStatic();
            IsLowRamDeviceStatic.checkComponentPermission(getApplication(), this, IsLowRamDeviceStatic, IsLowRamDeviceStatic);
        }

        /* JADX WARN: Incorrect return type in method signature: (TL;)V */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 5, insn: 0x000c: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('child' L extends lombok.core.LombokNode<A, L, N>)])
, block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v1, types: [android.animation.PropertyValuesHolder, android.app.Activity, android.view.KeyEvent] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public ListFieldSchema full() {
            View view;
            ?? OfObject = ofObject(this, this, this);
            OfObject.onKeyDown(this, OfObject);
            this.children = (LombokImmutableList<L>) this.children.makeScaleUpAnimation(view, this, this, this, this);
            return;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [boolean, com.android.framework.protobuf.ListFieldSchema] */
        public ListFieldSchema lite() {
            return this.isStructurallySignificant;
        }
    }
