package lombok.eclipse.handlers;

import lombok.ConfigurationKeys;
import lombok.core.AST;
import lombok.core.AnnotationValues;
import lombok.core.HandlerPriority;
import lombok.core.handlers.HandlerUtil;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.experimental.UtilityClass;
import org.eclipse.jdt.internal.compiler.ast.ASTNode;
import org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.AllocationExpression;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.Clinit;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.ast.ConstructorDeclaration;
import org.eclipse.jdt.internal.compiler.ast.ExplicitConstructorCall;
import org.eclipse.jdt.internal.compiler.ast.Expression;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.MethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.QualifiedTypeReference;
import org.eclipse.jdt.internal.compiler.ast.Statement;
import org.eclipse.jdt.internal.compiler.ast.StringLiteral;
import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.lookup.TypeConstants;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/eclipse/handlers/HandleUtilityClass.SCL.lombok */
@HandlerPriority(-4096)
public class HandleUtilityClass extends EclipseAnnotationHandler<UtilityClass> {
    private static final char[][] JAVA_LANG_UNSUPPORTED_OPERATION_EXCEPTION = {TypeConstants.JAVA, TypeConstants.LANG, "UnsupportedOperationException".toCharArray()};
    private static final char[] UNSUPPORTED_MESSAGE = "This is a utility class and cannot be instantiated".toCharArray();
    private static /* synthetic */ int[] $SWITCH_TABLE$lombok$core$AST$Kind;

    static /* synthetic */ int[] $SWITCH_TABLE$lombok$core$AST$Kind() {
        int[] iArr = $SWITCH_TABLE$lombok$core$AST$Kind;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[AST.Kind.valuesCustom().length];
        try {
            iArr2[AST.Kind.ANNOTATION.ordinal()] = 6;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[AST.Kind.ARGUMENT.ordinal()] = 7;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[AST.Kind.COMPILATION_UNIT.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[AST.Kind.FIELD.ordinal()] = 3;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[AST.Kind.INITIALIZER.ordinal()] = 4;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[AST.Kind.LOCAL.ordinal()] = 8;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr2[AST.Kind.METHOD.ordinal()] = 5;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr2[AST.Kind.STATEMENT.ordinal()] = 9;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            iArr2[AST.Kind.TYPE.ordinal()] = 2;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            iArr2[AST.Kind.TYPE_USE.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        $SWITCH_TABLE$lombok$core$AST$Kind = iArr2;
        return iArr2;
    }

    @Override // lombok.eclipse.EclipseAnnotationHandler
    public void handle(AnnotationValues<UtilityClass> annotation, Annotation ast, EclipseNode annotationNode) {
        HandlerUtil.handleFlagUsage(annotationNode, ConfigurationKeys.UTILITY_CLASS_FLAG_USAGE, "@UtilityClass");
        EclipseNode typeNode = (EclipseNode) annotationNode.up();
        if (checkLegality(typeNode, annotationNode)) {
            changeModifiersAndGenerateConstructor((EclipseNode) annotationNode.up(), annotationNode);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:104)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    private static boolean checkLegality(lombok.eclipse.EclipseNode r3, lombok.eclipse.EclipseNode r4) {
        /*
            r0 = r3
            boolean r0 = lombok.eclipse.handlers.EclipseHandlerUtil.isClass(r0)
            if (r0 != 0) goto Lf
            r0 = r4
            java.lang.String r1 = "@UtilityClass is only supported on a class."
            r0.addError(r1)
            r0 = 0
            return r0
        Lf:
            r0 = r3
            r5 = r0
        L11:
            r0 = r5
            lombok.core.LombokNode r0 = r0.up()
            lombok.eclipse.EclipseNode r0 = (lombok.eclipse.EclipseNode) r0
            r5 = r0
            int[] r0 = $SWITCH_TABLE$lombok$core$AST$Kind()
            r1 = r5
            lombok.core.AST$Kind r1 = r1.getKind()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L6a;
                case 2: goto L3c;
                default: goto L6c;
            }
        L3c:
            r0 = r5
            java.lang.Object r0 = r0.get()
            org.eclipse.jdt.internal.compiler.ast.TypeDeclaration r0 = (org.eclipse.jdt.internal.compiler.ast.TypeDeclaration) r0
            int r0 = r0.modifiers
            r1 = 25096(0x6208, float:3.5167E-41)
            r0 = r0 & r1
            if (r0 == 0) goto L50
            goto L11
        L50:
            r0 = r5
            lombok.core.LombokNode r0 = r0.up()
            lombok.eclipse.EclipseNode r0 = (lombok.eclipse.EclipseNode) r0
            lombok.core.AST$Kind r0 = r0.getKind()
            lombok.core.AST$Kind r1 = lombok.core.AST.Kind.COMPILATION_UNIT
            if (r0 != r1) goto L62
            r0 = 1
            return r0
        L62:
            r0 = r4
            java.lang.String r1 = "@UtilityClass automatically makes the class static, however, this class cannot be made static."
            r0.addError(r1)
            r0 = 0
            return r0
        L6a:
            r0 = 1
            return r0
        L6c:
            r0 = r4
            java.lang.String r1 = "@UtilityClass cannot be placed on a method local or anonymous inner class, or any class nested in such a class."
            r0.addError(r1)
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.eclipse.handlers.HandleUtilityClass.checkLegality(lombok.eclipse.EclipseNode, lombok.eclipse.EclipseNode):boolean");
    }

    private void changeModifiersAndGenerateConstructor(EclipseNode typeNode, EclipseNode annotationNode) {
        TypeDeclaration classDecl = (TypeDeclaration) typeNode.get();
        boolean makeConstructor = true;
        classDecl.modifiers |= 16;
        boolean requiresClInit = false;
        boolean alreadyHasClinit = false;
        boolean markStatic = ((EclipseNode) typeNode.up()).getKind() != AST.Kind.COMPILATION_UNIT;
        if (markStatic && ((EclipseNode) typeNode.up()).getKind() == AST.Kind.TYPE) {
            TypeDeclaration typeDecl = (TypeDeclaration) ((EclipseNode) typeNode.up()).get();
            if ((typeDecl.modifiers & 8704) != 0) {
                markStatic = false;
            }
        }
        if (markStatic) {
            classDecl.modifiers |= 8;
        }
        for (EclipseNode element : typeNode.down()) {
            if (element.getKind() == AST.Kind.FIELD) {
                FieldDeclaration fieldDecl = (FieldDeclaration) element.get();
                if ((fieldDecl.modifiers & 8) == 0) {
                    requiresClInit = true;
                    fieldDecl.modifiers |= 8;
                }
            } else if (element.getKind() == AST.Kind.METHOD) {
                AbstractMethodDeclaration amd = (AbstractMethodDeclaration) element.get();
                if (amd instanceof ConstructorDeclaration) {
                    ConstructorDeclaration constrDecl = (ConstructorDeclaration) element.get();
                    if (EclipseHandlerUtil.getGeneratedBy(constrDecl) == null && (constrDecl.bits & 128) == 0) {
                        element.addError("@UtilityClasses cannot have declared constructors.");
                        makeConstructor = false;
                    }
                } else if (amd instanceof MethodDeclaration) {
                    amd.modifiers |= 8;
                } else if (amd instanceof Clinit) {
                    alreadyHasClinit = true;
                }
            } else if (element.getKind() == AST.Kind.TYPE) {
                ((TypeDeclaration) element.get()).modifiers |= 8;
            }
        }
        if (makeConstructor) {
            createPrivateDefaultConstructor(typeNode, annotationNode);
        }
        if (!requiresClInit || alreadyHasClinit) {
            return;
        }
        classDecl.addClinit();
    }

    private void createPrivateDefaultConstructor(EclipseNode typeNode, EclipseNode sourceNode) {
        ASTNode source = (ASTNode) sourceNode.get();
        TypeDeclaration typeDeclaration = (TypeDeclaration) typeNode.get();
        ConstructorDeclaration constructor = new ConstructorDeclaration(((CompilationUnitDeclaration) ((EclipseNode) typeNode.top()).get()).compilationResult);
        constructor.modifiers = 2;
        constructor.selector = typeDeclaration.name;
        constructor.constructorCall = new ExplicitConstructorCall(1);
        constructor.thrownExceptions = null;
        constructor.typeParameters = null;
        constructor.bits |= 8388608;
        constructor.arguments = null;
        long[] ps = new long[JAVA_LANG_UNSUPPORTED_OPERATION_EXCEPTION.length];
        AllocationExpression exception = new AllocationExpression();
        exception.type = new QualifiedTypeReference(JAVA_LANG_UNSUPPORTED_OPERATION_EXCEPTION, ps);
        exception.arguments = new Expression[]{new StringLiteral(UNSUPPORTED_MESSAGE, 0, 0, 0)};
        constructor.statements = new Statement[]{new ThrowStatement(exception, 0, 0)};
        constructor.traverse(new SetGeneratedByVisitor(source), typeDeclaration.scope);
        EclipseHandlerUtil.injectMethod(typeNode, constructor);
    }
}
