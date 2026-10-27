package lombok.javac;

import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Name;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import lombok.core.AST;
import lombok.core.AnnotationValues;
import lombok.core.LombokNode;
import lombok.javac.handlers.JavacHandlerUtil;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/javac/JavacNode.SCL.lombok */
public class JavacNode extends LombokNode<JavacAST, JavacNode, JCTree> {
    private JavacAST ast;
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

    public JavacNode(JavacAST ast, JCTree node, List<JavacNode> children, AST.Kind kind) {
        super(node, children, kind);
        this.ast = ast;
    }

    @Override // lombok.core.LombokNode
    public JavacAST getAst() {
        return this.ast;
    }

    public Element getElement() {
        if (this.node instanceof JCTree.JCClassDecl) {
            return ((JCTree.JCClassDecl) this.node).sym;
        }
        if (this.node instanceof JCTree.JCMethodDecl) {
            return ((JCTree.JCMethodDecl) this.node).sym;
        }
        if (this.node instanceof JCTree.JCVariableDecl) {
            return ((JCTree.JCVariableDecl) this.node).sym;
        }
        return null;
    }

    public int getEndPosition(JCDiagnostic.DiagnosticPosition pos) {
        JCTree.JCCompilationUnit cu = (JCTree.JCCompilationUnit) ((JavacNode) top()).get();
        return Javac.getEndPosition(pos, cu);
    }

    public int getEndPosition() {
        return getEndPosition((JCDiagnostic.DiagnosticPosition) this.node);
    }

    public void traverse(JavacASTVisitor visitor) {
        switch ($SWITCH_TABLE$lombok$core$AST$Kind()[getKind().ordinal()]) {
            case 1:
                visitor.visitCompilationUnit(this, (JCTree.JCCompilationUnit) get());
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitCompilationUnit(this, (JCTree.JCCompilationUnit) get());
                return;
            case 2:
                visitor.visitType(this, (JCTree.JCClassDecl) get());
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitType(this, (JCTree.JCClassDecl) get());
                return;
            case 3:
                visitor.visitField(this, (JCTree.JCVariableDecl) get());
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitField(this, (JCTree.JCVariableDecl) get());
                return;
            case 4:
                visitor.visitInitializer(this, (JCTree.JCBlock) get());
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitInitializer(this, (JCTree.JCBlock) get());
                return;
            case 5:
                visitor.visitMethod(this, (JCTree.JCMethodDecl) get());
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitMethod(this, (JCTree.JCMethodDecl) get());
                return;
            case 6:
                switch ($SWITCH_TABLE$lombok$core$AST$Kind()[((JavacNode) up()).getKind().ordinal()]) {
                    case 2:
                        visitor.visitAnnotationOnType((JCTree.JCClassDecl) ((JavacNode) up()).get(), this, (JCTree.JCAnnotation) get());
                        return;
                    case 3:
                        visitor.visitAnnotationOnField((JCTree.JCVariableDecl) ((JavacNode) up()).get(), this, (JCTree.JCAnnotation) get());
                        return;
                    case 4:
                    case 6:
                    case 9:
                    default:
                        throw new AssertionError("Annotion not expected as child of a " + ((JavacNode) up()).getKind());
                    case 5:
                        visitor.visitAnnotationOnMethod((JCTree.JCMethodDecl) ((JavacNode) up()).get(), this, (JCTree.JCAnnotation) get());
                        return;
                    case 7:
                        JCTree.JCVariableDecl argument = (JCTree.JCVariableDecl) ((JavacNode) up()).get();
                        JCTree.JCMethodDecl method = (JCTree.JCMethodDecl) ((JavacNode) ((JavacNode) up()).up()).get();
                        visitor.visitAnnotationOnMethodArgument(argument, method, this, (JCTree.JCAnnotation) get());
                        return;
                    case 8:
                        visitor.visitAnnotationOnLocal((JCTree.JCVariableDecl) ((JavacNode) up()).get(), this, (JCTree.JCAnnotation) get());
                        return;
                    case 10:
                        visitor.visitAnnotationOnTypeUse((JCTree) ((JavacNode) up()).get(), this, (JCTree.JCAnnotation) get());
                        return;
                }
            case 7:
                JCTree.JCMethodDecl parentMethod = (JCTree.JCMethodDecl) ((JavacNode) up()).get();
                visitor.visitMethodArgument(this, (JCTree.JCVariableDecl) get(), parentMethod);
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitMethodArgument(this, (JCTree.JCVariableDecl) get(), parentMethod);
                return;
            case 8:
                visitor.visitLocal(this, (JCTree.JCVariableDecl) get());
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitLocal(this, (JCTree.JCVariableDecl) get());
                return;
            case 9:
                visitor.visitStatement(this, (JCTree) get());
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitStatement(this, (JCTree) get());
                return;
            case 10:
                visitor.visitTypeUse(this, (JCTree) get());
                this.ast.traverseChildren(visitor, this);
                visitor.endVisitTypeUse(this, (JCTree) get());
                return;
            default:
                throw new AssertionError("Unexpected kind during node traversal: " + getKind());
        }
    }

    public String getName() {
        Name n;
        if (this.node instanceof JCTree.JCClassDecl) {
            n = ((JCTree.JCClassDecl) this.node).name;
        } else if (this.node instanceof JCTree.JCMethodDecl) {
            n = ((JCTree.JCMethodDecl) this.node).name;
        } else {
            n = this.node instanceof JCTree.JCVariableDecl ? ((JCTree.JCVariableDecl) this.node).name : null;
        }
        if (n == null) {
            return null;
        }
        return n.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean calculateIsStructurallySignificant(JCTree parent) {
        if ((this.node instanceof JCTree.JCClassDecl) || (this.node instanceof JCTree.JCMethodDecl) || (this.node instanceof JCTree.JCVariableDecl) || (this.node instanceof JCTree.JCCompilationUnit)) {
            return true;
        }
        if (this.node instanceof JCTree.JCBlock) {
            return parent instanceof JCTree.JCClassDecl;
        }
        return false;
    }

    public JavacTreeMaker getTreeMaker() {
        return this.ast.getTreeMaker();
    }

    public Symtab getSymbolTable() {
        return this.ast.getSymbolTable();
    }

    public JavacTypes getTypesUtil() {
        return this.ast.getTypesUtil();
    }

    public Context getContext() {
        return this.ast.getContext();
    }

    public boolean shouldDeleteLombokAnnotations() {
        return LombokOptions.shouldDeleteLombokAnnotations(this.ast.getContext());
    }

    public Name toName(String name) {
        return this.ast.toName(name);
    }

    public void removeDeferredErrors() {
        this.ast.removeDeferredErrors(this);
    }

    @Override // lombok.core.DiagnosticsReceiver
    public void addError(String message) {
        this.ast.printMessage(Diagnostic.Kind.ERROR, message, this, null, true);
    }

    public void addError(String message, JCDiagnostic.DiagnosticPosition pos) {
        this.ast.printMessage(Diagnostic.Kind.ERROR, message, null, pos, true);
    }

    @Override // lombok.core.DiagnosticsReceiver
    public void addWarning(String message) {
        this.ast.printMessage(Diagnostic.Kind.WARNING, message, this, null, false);
    }

    public void addWarning(String message, JCDiagnostic.DiagnosticPosition pos) {
        this.ast.printMessage(Diagnostic.Kind.WARNING, message, null, pos, false);
    }

    public boolean hasAnnotation(Class<? extends Annotation> type) {
        return JavacHandlerUtil.hasAnnotationAndDeleteIfNeccessary(type, this);
    }

    public <Z extends Annotation> AnnotationValues<Z> findAnnotation(Class<Z> type) {
        JavacNode annotation = JavacHandlerUtil.findAnnotation(type, this, true);
        if (annotation == null) {
            return null;
        }
        return JavacHandlerUtil.createAnnotation(type, annotation);
    }

    private JCTree.JCModifiers getModifiers() {
        if (this.node instanceof JCTree.JCClassDecl) {
            return ((JCTree.JCClassDecl) this.node).getModifiers();
        }
        if (this.node instanceof JCTree.JCMethodDecl) {
            return ((JCTree.JCMethodDecl) this.node).getModifiers();
        }
        if (this.node instanceof JCTree.JCVariableDecl) {
            return ((JCTree.JCVariableDecl) this.node).getModifiers();
        }
        return null;
    }

    public boolean isStatic() {
        JavacNode directUp;
        if (this.node instanceof JCTree.JCClassDecl) {
            JavacNode directUp2 = (JavacNode) directUp();
            if (directUp2 == null || directUp2.getKind() == AST.Kind.COMPILATION_UNIT) {
                return true;
            }
            if (!(directUp2.get() instanceof JCTree.JCClassDecl)) {
                return false;
            }
            JCTree.JCClassDecl p = (JCTree.JCClassDecl) directUp2.get();
            long f = p.mods.flags;
            if ((512 & f) != 0 || (16384 & f) != 0) {
                return true;
            }
        }
        if ((this.node instanceof JCTree.JCVariableDecl) && (directUp = (JavacNode) directUp()) != null && (directUp.get() instanceof JCTree.JCClassDecl)) {
            JCTree.JCClassDecl p2 = (JCTree.JCClassDecl) directUp.get();
            if ((512 & p2.mods.flags) != 0) {
                return true;
            }
        }
        JCTree.JCModifiers mods = getModifiers();
        return (mods == null || (mods.flags & 8) == 0) ? false : true;
    }

    public boolean isFinal() {
        JavacNode directUp;
        if ((this.node instanceof JCTree.JCVariableDecl) && (directUp = (JavacNode) directUp()) != null && (directUp.get() instanceof JCTree.JCClassDecl)) {
            JCTree.JCClassDecl p = (JCTree.JCClassDecl) directUp.get();
            long f = p.mods.flags;
            if ((16896 & f) != 0) {
                return true;
            }
        }
        JCTree.JCModifiers mods = getModifiers();
        return (mods == null || (16 & mods.flags) == 0) ? false : true;
    }

    public boolean isEnumMember() {
        JCTree.JCModifiers mods;
        return (getKind() != AST.Kind.FIELD || (mods = getModifiers()) == null || (16384 & mods.flags) == 0) ? false : true;
    }

    public boolean isEnumType() {
        JCTree.JCModifiers mods;
        return (getKind() != AST.Kind.TYPE || (mods = getModifiers()) == null || (16384 & mods.flags) == 0) ? false : true;
    }

    public boolean isPrimitive() {
        if ((this.node instanceof JCTree.JCVariableDecl) && !isEnumMember()) {
            return Javac.isPrimitive(((JCTree.JCVariableDecl) this.node).vartype);
        }
        if (this.node instanceof JCTree.JCMethodDecl) {
            return Javac.isPrimitive(((JCTree.JCMethodDecl) this.node).restype);
        }
        return false;
    }

    public String fieldOrMethodBaseType() {
        if ((this.node instanceof JCTree.JCVariableDecl) && !isEnumMember()) {
            return ((JCTree.JCVariableDecl) this.node).vartype.toString();
        }
        if (this.node instanceof JCTree.JCMethodDecl) {
            return ((JCTree.JCMethodDecl) this.node).restype.toString();
        }
        return null;
    }

    public boolean isTransient() {
        JCTree.JCModifiers mods;
        return (getKind() != AST.Kind.FIELD || (mods = getModifiers()) == null || (128 & mods.flags) == 0) ? false : true;
    }

    public int countMethodParameters() {
        com.sun.tools.javac.util.List<JCTree.JCVariableDecl> params;
        if (getKind() == AST.Kind.METHOD && (params = ((JCTree.JCMethodDecl) this.node).params) != null) {
            return params.size();
        }
        return 0;
    }

    public int getStartPos() {
        return ((JCTree) this.node).getPreferredPosition();
    }
}
