package lombok.eclipse.handlers;

import java.lang.annotation.Annotation;
import lombok.AccessLevel;
import lombok.core.AST;
import lombok.core.HandlerPriority;
import lombok.eclipse.EclipseASTAdapter;
import lombok.eclipse.EclipseNode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.experimental.PackagePrivate;
import org.eclipse.jdt.internal.compiler.ast.ASTNode;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/eclipse/handlers/HandleFieldDefaults.SCL.lombok */
@HandlerPriority(-2048)
public class HandleFieldDefaults extends EclipseASTAdapter {
    private static final char[] FIELD_DEFAULTS = "FieldDefaults".toCharArray();

    public boolean generateFieldDefaultsForType(EclipseNode typeNode, EclipseNode pos, AccessLevel level, boolean makeFinal, boolean checkForTypeLevelFieldDefaults) {
        if (checkForTypeLevelFieldDefaults && EclipseHandlerUtil.hasAnnotation((Class<? extends Annotation>) FieldDefaults.class, typeNode)) {
            return true;
        }
        if (!EclipseHandlerUtil.isClassOrEnum(typeNode)) {
            pos.addError("@FieldDefaults is only supported on a class or an enum.");
            return false;
        }
        for (EclipseNode field : typeNode.down()) {
            if (field.getKind() == AST.Kind.FIELD) {
                FieldDeclaration fieldDecl = (FieldDeclaration) field.get();
                if (EclipseHandlerUtil.filterField(fieldDecl, false)) {
                    Class<?> t = ((ASTNode) field.get()).getClass();
                    if (t == FieldDeclaration.class) {
                        setFieldDefaultsForField(field, (ASTNode) pos.get(), level, makeFinal);
                    }
                }
            }
        }
        return true;
    }

    public void setFieldDefaultsForField(EclipseNode fieldNode, ASTNode pos, AccessLevel level, boolean makeFinal) {
        FieldDeclaration field = (FieldDeclaration) fieldNode.get();
        if (level != null && level != AccessLevel.NONE && (field.modifiers & 7) == 0 && !EclipseHandlerUtil.hasAnnotation((Class<? extends Annotation>) PackagePrivate.class, fieldNode) && (field.modifiers & 8) == 0) {
            field.modifiers |= EclipseHandlerUtil.toEclipseModifier(level);
        }
        if (makeFinal && (field.modifiers & 16) == 0 && !EclipseHandlerUtil.hasAnnotation((Class<? extends Annotation>) NonFinal.class, fieldNode) && (field.modifiers & 8) == 0) {
            field.modifiers |= 16;
        }
        fieldNode.rebuild();
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x009b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00fa A[SYNTHETIC] */
    @Override // lombok.eclipse.EclipseASTAdapter, lombok.eclipse.EclipseASTVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void visitType(lombok.eclipse.EclipseNode r8, org.eclipse.jdt.internal.compiler.ast.TypeDeclaration r9) {
        /*
            Method dump skipped, instruction units count: 416
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.eclipse.handlers.HandleFieldDefaults.visitType(lombok.eclipse.EclipseNode, org.eclipse.jdt.internal.compiler.ast.TypeDeclaration):void");
    }
}
