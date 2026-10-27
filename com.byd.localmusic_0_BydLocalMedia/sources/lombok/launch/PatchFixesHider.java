package lombok.launch;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import kotlin.text.Typography;
import lombok.eclipse.EcjAugments;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IAnnotatable;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.Expression;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.LocalDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.lookup.BlockScope;
import org.eclipse.jdt.internal.compiler.lookup.TypeBinding;
import org.eclipse.jdt.internal.core.SourceField;
import org.eclipse.jdt.internal.core.dom.rewrite.NodeRewriteEvent;
import org.eclipse.jdt.internal.core.dom.rewrite.RewriteEvent;
import org.eclipse.jdt.internal.core.dom.rewrite.TokenScanner;
import org.eclipse.jdt.internal.corext.refactoring.SearchResultGroup;
import org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil;

/* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider.SCL.lombok
 */
/* JADX INFO: loaded from: classes5.dex */
final class PatchFixesHider {
    PatchFixesHider() {
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$Util.SCL.lombok
 */
    public static final class Util {
        private static ClassLoader shadowLoader;

        public static ClassLoader getShadowLoader() {
            if (shadowLoader == null) {
                try {
                    Class.forName("lombok.core.LombokNode");
                    shadowLoader = Util.class.getClassLoader();
                } catch (ClassNotFoundException unused) {
                    shadowLoader = Main.getShadowClassLoader();
                }
            }
            return shadowLoader;
        }

        public static Class<?> shadowLoadClass(String str) {
            try {
                return Class.forName(str, true, getShadowLoader());
            } catch (ClassNotFoundException e) {
                throw sneakyThrow(e);
            }
        }

        public static Method findMethod(Class<?> cls, String str, Class<?>... clsArr) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                throw sneakyThrow(e);
            }
        }

        public static Method findMethod(Class<?> cls, String str, String... strArr) {
            for (Method method : cls.getDeclaredMethods()) {
                if (str.equals(method.getName()) && sameTypes(method.getParameterTypes(), strArr)) {
                    return method;
                }
            }
            throw sneakyThrow(new NoSuchMethodException(String.valueOf(cls.getName()) + "::" + str));
        }

        public static Method findMethodAnyArgs(Class<?> cls, String str) {
            for (Method method : cls.getDeclaredMethods()) {
                if (str.equals(method.getName())) {
                    return method;
                }
            }
            throw sneakyThrow(new NoSuchMethodException(String.valueOf(cls.getName()) + "::" + str));
        }

        public static Object invokeMethod(Method method, Object... objArr) {
            try {
                return method.invoke(null, objArr);
            } catch (IllegalAccessException e) {
                throw sneakyThrow(e);
            } catch (InvocationTargetException e2) {
                throw sneakyThrow(e2.getCause());
            }
        }

        private static RuntimeException sneakyThrow(Throwable th) throws Throwable {
            if (th == null) {
                throw new NullPointerException("t");
            }
            sneakyThrow0(th);
            return null;
        }

        private static <T extends Throwable> void sneakyThrow0(Throwable th) throws Throwable {
            throw th;
        }

        private static boolean sameTypes(Class<?>[] clsArr, String[] strArr) {
            if (clsArr.length != strArr.length) {
                return false;
            }
            for (int i = 0; i < clsArr.length; i++) {
                if (!clsArr[i].getName().equals(strArr[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$LombokDeps.SCL.lombok
 */
    public static final class LombokDeps {
        public static final Method ADD_LOMBOK_NOTES;
        public static final Method POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING;
        public static final Method POST_COMPILER_BYTES_STRING;
        public static final Method POST_COMPILER_OUTPUTSTREAM;

        static {
            Class<?> clsShadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchFixesShadowLoaded");
            ADD_LOMBOK_NOTES = Util.findMethod(clsShadowLoadClass, "addLombokNotesToEclipseAboutDialog", (Class<?>[]) new Class[]{String.class, String.class});
            POST_COMPILER_BYTES_STRING = Util.findMethod(clsShadowLoadClass, "runPostCompiler", (Class<?>[]) new Class[]{byte[].class, String.class});
            POST_COMPILER_OUTPUTSTREAM = Util.findMethod(clsShadowLoadClass, "runPostCompiler", (Class<?>[]) new Class[]{OutputStream.class});
            POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING = Util.findMethod(clsShadowLoadClass, "runPostCompiler", (Class<?>[]) new Class[]{BufferedOutputStream.class, String.class, String.class});
        }

        public static String addLombokNotesToEclipseAboutDialog(String str, String str2) {
            try {
                return (String) Util.invokeMethod(ADD_LOMBOK_NOTES, str, str2);
            } catch (Throwable unused) {
                return str;
            }
        }

        public static byte[] runPostCompiler(byte[] bArr, String str) {
            return (byte[]) Util.invokeMethod(POST_COMPILER_BYTES_STRING, bArr, str);
        }

        public static OutputStream runPostCompiler(OutputStream outputStream) throws IOException {
            return (OutputStream) Util.invokeMethod(POST_COMPILER_OUTPUTSTREAM, outputStream);
        }

        public static BufferedOutputStream runPostCompiler(BufferedOutputStream bufferedOutputStream, String str, String str2) throws IOException {
            return (BufferedOutputStream) Util.invokeMethod(POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING, bufferedOutputStream, str, str2);
        }
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$Transform.SCL.lombok
 */
    public static final class Transform {
        private static Method TRANSFORM;
        private static Method TRANSFORM_SWAPPED;

        private static synchronized void init(ClassLoader classLoader) {
            if (TRANSFORM != null) {
                return;
            }
            Main.prependClassLoader(classLoader);
            Class<?> clsShadowLoadClass = Util.shadowLoadClass("lombok.eclipse.TransformEclipseAST");
            TRANSFORM = Util.findMethodAnyArgs(clsShadowLoadClass, "transform");
            TRANSFORM_SWAPPED = Util.findMethodAnyArgs(clsShadowLoadClass, "transform_swapped");
        }

        public static void transform(Object obj, Object obj2) throws IOException {
            init(obj.getClass().getClassLoader());
            Util.invokeMethod(TRANSFORM, obj, obj2);
        }

        public static void transform_swapped(Object obj, Object obj2) throws IOException {
            init(obj2.getClass().getClassLoader());
            Util.invokeMethod(TRANSFORM_SWAPPED, obj, obj2);
        }
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$Delegate.SCL.lombok
 */
    public static final class Delegate {
        private static final Method ADD_GENERATED_DELEGATE_METHODS;
        private static final Method HANDLE_DELEGATE_FOR_TYPE;

        static {
            Class<?> clsShadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchDelegatePortal");
            HANDLE_DELEGATE_FOR_TYPE = Util.findMethod(clsShadowLoadClass, "handleDelegateForType", (Class<?>[]) new Class[]{Object.class});
            ADD_GENERATED_DELEGATE_METHODS = Util.findMethod(clsShadowLoadClass, "addGeneratedDelegateMethods", (Class<?>[]) new Class[]{Object.class, Object.class});
        }

        public static boolean handleDelegateForType(Object obj) {
            return ((Boolean) Util.invokeMethod(HANDLE_DELEGATE_FOR_TYPE, obj)).booleanValue();
        }

        public static Object[] addGeneratedDelegateMethods(Object obj, Object obj2) {
            return (Object[]) Util.invokeMethod(ADD_GENERATED_DELEGATE_METHODS, obj, obj2);
        }
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$ValPortal.SCL.lombok
 */
    public static final class ValPortal {
        private static final Method ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION;
        private static final Method ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT;
        private static final Method COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE;
        private static final Method COPY_INITIALIZATION_OF_LOCAL_DECLARATION;

        static {
            Class<?> clsShadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchValEclipsePortal");
            COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE = Util.findMethod(clsShadowLoadClass, "copyInitializationOfForEachIterable", (Class<?>[]) new Class[]{Object.class});
            COPY_INITIALIZATION_OF_LOCAL_DECLARATION = Util.findMethod(clsShadowLoadClass, "copyInitializationOfLocalDeclaration", (Class<?>[]) new Class[]{Object.class});
            ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT = Util.findMethod(clsShadowLoadClass, "addFinalAndValAnnotationToVariableDeclarationStatement", (Class<?>[]) new Class[]{Object.class, Object.class, Object.class});
            ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION = Util.findMethod(clsShadowLoadClass, "addFinalAndValAnnotationToSingleVariableDeclaration", (Class<?>[]) new Class[]{Object.class, Object.class, Object.class});
        }

        public static void copyInitializationOfForEachIterable(Object obj) {
            Util.invokeMethod(COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE, obj);
        }

        public static void copyInitializationOfLocalDeclaration(Object obj) {
            Util.invokeMethod(COPY_INITIALIZATION_OF_LOCAL_DECLARATION, obj);
        }

        public static void addFinalAndValAnnotationToVariableDeclarationStatement(Object obj, Object obj2, Object obj3) {
            Util.invokeMethod(ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT, obj, obj2, obj3);
        }

        public static void addFinalAndValAnnotationToSingleVariableDeclaration(Object obj, Object obj2, Object obj3) {
            Util.invokeMethod(ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION, obj, obj2, obj3);
        }
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$Val.SCL.lombok
 */
    public static final class Val {
        private static final String BLOCK_SCOPE_SIG = "org.eclipse.jdt.internal.compiler.lookup.BlockScope";
        private static final String FOREACH_STATEMENT_SIG = "org.eclipse.jdt.internal.compiler.ast.ForeachStatement";
        private static final Method HANDLE_VAL_FOR_FOR_EACH;
        private static final Method HANDLE_VAL_FOR_LOCAL_DECLARATION;
        private static final String LOCAL_DECLARATION_SIG = "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration";

        static {
            Class<?> clsShadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchVal");
            HANDLE_VAL_FOR_LOCAL_DECLARATION = Util.findMethod(clsShadowLoadClass, "handleValForLocalDeclaration", LOCAL_DECLARATION_SIG, BLOCK_SCOPE_SIG);
            HANDLE_VAL_FOR_FOR_EACH = Util.findMethod(clsShadowLoadClass, "handleValForForEach", FOREACH_STATEMENT_SIG, BLOCK_SCOPE_SIG);
        }

        public static boolean handleValForLocalDeclaration(Object obj, Object obj2) {
            return ((Boolean) Util.invokeMethod(HANDLE_VAL_FOR_LOCAL_DECLARATION, obj, obj2)).booleanValue();
        }

        public static boolean handleValForForEach(Object obj, Object obj2) {
            return ((Boolean) Util.invokeMethod(HANDLE_VAL_FOR_FOR_EACH, obj, obj2)).booleanValue();
        }

        public static TypeBinding skipResolveInitializerIfAlreadyCalled(Expression expression, BlockScope blockScope) {
            if (expression.resolvedType != null) {
                return expression.resolvedType;
            }
            try {
                return expression.resolveType(blockScope);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException unused) {
                return null;
            }
        }

        public static TypeBinding skipResolveInitializerIfAlreadyCalled2(Expression expression, BlockScope blockScope, LocalDeclaration localDeclaration) {
            if (localDeclaration != null && LocalDeclaration.class.equals(localDeclaration.getClass()) && expression.resolvedType != null) {
                return expression.resolvedType;
            }
            try {
                return expression.resolveType(blockScope);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException unused) {
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$ExtensionMethod.SCL.lombok
 */
    public static final class ExtensionMethod {
        private static final String AST_NODE_SIG = "org.eclipse.jdt.internal.compiler.ast.ASTNode";
        private static final String BLOCK_SCOPE_SIG = "org.eclipse.jdt.internal.compiler.lookup.BlockScope";
        private static final Method ERROR_NO_METHOD_FOR;
        private static final Method INVALID_METHOD;
        private static final Method INVALID_METHOD2;
        private static final String MESSAGE_SEND_SIG = "org.eclipse.jdt.internal.compiler.ast.MessageSend";
        private static final String METHOD_BINDING_SIG = "org.eclipse.jdt.internal.compiler.lookup.MethodBinding";
        private static final Method NON_STATIC_ACCESS_TO_STATIC_METHOD;
        private static final String PROBLEM_REPORTER_SIG = "org.eclipse.jdt.internal.compiler.problem.ProblemReporter";
        private static final Method RESOLVE_TYPE;
        private static final String SCOPE_SIG = "org.eclipse.jdt.internal.compiler.lookup.Scope";
        private static final String TYPE_BINDINGS_SIG = "[Lorg.eclipse.jdt.internal.compiler.lookup.TypeBinding;";
        private static final String TYPE_BINDING_SIG = "org.eclipse.jdt.internal.compiler.lookup.TypeBinding";

        static {
            Class<?> clsShadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchExtensionMethod");
            RESOLVE_TYPE = Util.findMethod(clsShadowLoadClass, "resolveType", TYPE_BINDING_SIG, MESSAGE_SEND_SIG, BLOCK_SCOPE_SIG);
            ERROR_NO_METHOD_FOR = Util.findMethod(clsShadowLoadClass, "errorNoMethodFor", PROBLEM_REPORTER_SIG, MESSAGE_SEND_SIG, TYPE_BINDING_SIG, TYPE_BINDINGS_SIG);
            INVALID_METHOD = Util.findMethod(clsShadowLoadClass, "invalidMethod", PROBLEM_REPORTER_SIG, MESSAGE_SEND_SIG, METHOD_BINDING_SIG);
            INVALID_METHOD2 = Util.findMethod(clsShadowLoadClass, "invalidMethod", PROBLEM_REPORTER_SIG, MESSAGE_SEND_SIG, METHOD_BINDING_SIG, SCOPE_SIG);
            NON_STATIC_ACCESS_TO_STATIC_METHOD = Util.findMethod(clsShadowLoadClass, "nonStaticAccessToStaticMethod", PROBLEM_REPORTER_SIG, AST_NODE_SIG, METHOD_BINDING_SIG, MESSAGE_SEND_SIG);
        }

        public static Object resolveType(Object obj, Object obj2, Object obj3) {
            return Util.invokeMethod(RESOLVE_TYPE, obj, obj2, obj3);
        }

        public static void errorNoMethodFor(Object obj, Object obj2, Object obj3, Object obj4) {
            Util.invokeMethod(ERROR_NO_METHOD_FOR, obj, obj2, obj3, obj4);
        }

        public static void invalidMethod(Object obj, Object obj2, Object obj3) {
            Util.invokeMethod(INVALID_METHOD, obj, obj2, obj3);
        }

        public static void invalidMethod(Object obj, Object obj2, Object obj3, Object obj4) {
            Util.invokeMethod(INVALID_METHOD2, obj, obj2, obj3, obj4);
        }

        public static void nonStaticAccessToStaticMethod(Object obj, Object obj2, Object obj3, Object obj4) {
            Util.invokeMethod(NON_STATIC_ACCESS_TO_STATIC_METHOD, obj, obj2, obj3, obj4);
        }
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$Javadoc.SCL.lombok
 */
    public static final class Javadoc {
        private static final Method GET_HTML;
        private static final Method PRINT_METHOD;

        static {
            Class<?> clsShadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchJavadoc");
            GET_HTML = Util.findMethod(clsShadowLoadClass, "getHTMLContentFromSource", (Class<?>[]) new Class[]{String.class, Object.class});
            PRINT_METHOD = Util.findMethod(clsShadowLoadClass, "printMethod", (Class<?>[]) new Class[]{AbstractMethodDeclaration.class, Integer.class, StringBuffer.class, TypeDeclaration.class});
        }

        public static String getHTMLContentFromSource(String str, IJavaElement iJavaElement) {
            return (String) Util.invokeMethod(GET_HTML, str, iJavaElement);
        }

        public static StringBuffer printMethod(AbstractMethodDeclaration abstractMethodDeclaration, int i, StringBuffer stringBuffer, TypeDeclaration typeDeclaration) {
            return (StringBuffer) Util.invokeMethod(PRINT_METHOD, abstractMethodDeclaration, Integer.valueOf(i), stringBuffer, typeDeclaration);
        }
    }

    /* JADX WARN: Classes with same name are omitted, all sources:
  classes5.dex
  com.byd.localmusic_0_BydLocalMedia.apk:Class50/lombok/launch/PatchFixesHider$PatchFixes.SCL.lombok
 */
    public static final class PatchFixes {
        public static final int ALREADY_PROCESSED_FLAG = 8388608;

        public static int fixRetrieveEllipsisStartPosition(int i, int i2) {
            return i == -1 ? i2 : i;
        }

        public static int fixRetrieveIdentifierEndPosition(int i, int i2, int i3) {
            return (i != -1 && i >= i2) ? i : i3;
        }

        public static int fixRetrieveRightBraceOrSemiColonPosition(int i, int i2) {
            return i == -1 ? i2 : i;
        }

        public static int fixRetrieveStartBlockPosition(int i, int i2) {
            return i == -1 ? i2 : i;
        }

        public static int fixRetrieveStartingCatchPosition(int i, int i2) {
            return i == -1 ? i2 : i;
        }

        public static boolean returnFalse(Object obj) {
            return false;
        }

        public static boolean returnTrue(Object obj) {
            return true;
        }

        public static boolean isGenerated(ASTNode aSTNode) {
            try {
                boolean zBooleanValue = ((Boolean) aSTNode.getClass().getField("$isGenerated").get(aSTNode)).booleanValue();
                if (zBooleanValue) {
                    return zBooleanValue;
                }
                try {
                    return (aSTNode.getParent() == null || !(aSTNode.getParent() instanceof QualifiedName)) ? zBooleanValue : isGenerated(aSTNode.getParent());
                } catch (Exception unused) {
                    return zBooleanValue;
                }
            } catch (Exception unused2) {
                return false;
            }
        }

        public static boolean isGenerated(org.eclipse.jdt.internal.compiler.ast.ASTNode aSTNode) {
            try {
                return aSTNode.getClass().getField("$generatedBy").get(aSTNode) != null;
            } catch (Exception unused) {
                return false;
            }
        }

        public static boolean isRefactoringVisitorAndGenerated(ASTNode aSTNode, ASTVisitor aSTVisitor) {
            if (aSTVisitor == null) {
                return false;
            }
            String name = aSTVisitor.getClass().getName();
            if (!name.startsWith("org.eclipse.jdt.internal.corext.fix") && !name.startsWith("org.eclipse.jdt.internal.ui.fix")) {
                return false;
            }
            try {
                boolean zBooleanValue = ((Boolean) aSTNode.getClass().getField("$isGenerated").get(aSTNode)).booleanValue();
                return (zBooleanValue || aSTNode.getParent() == null || !(aSTNode.getParent() instanceof QualifiedName)) ? zBooleanValue : isGenerated(aSTNode.getParent());
            } catch (Exception unused) {
                return false;
            }
        }

        public static boolean isListRewriteOnGeneratedNode(ListRewrite listRewrite) {
            return isGenerated(listRewrite.getParent());
        }

        public static List removeGeneratedNodes(List list) {
            try {
                ArrayList arrayList = new ArrayList(list.size());
                for (Object obj : list) {
                    if (!isGenerated((ASTNode) obj)) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            } catch (Exception unused) {
                return list;
            }
        }

        public static String getRealMethodDeclarationSource(String str, Object obj, MethodDeclaration methodDeclaration) throws Exception {
            if (!isGenerated((ASTNode) methodDeclaration)) {
                return str;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : methodDeclaration.modifiers()) {
                if (obj2 instanceof Annotation) {
                    Annotation annotation = (Annotation) obj2;
                    String qualifiedName = annotation.resolveTypeBinding().getQualifiedName();
                    if (!"java.lang.Override".equals(qualifiedName) && !"java.lang.SuppressWarnings".equals(qualifiedName)) {
                        arrayList.add(annotation);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            addAnnotations(arrayList, sb);
            if (((Boolean) obj.getClass().getDeclaredField("fPublic").get(obj)).booleanValue()) {
                sb.append("public ");
            }
            if (((Boolean) obj.getClass().getDeclaredField("fAbstract").get(obj)).booleanValue()) {
                sb.append("abstract ");
            }
            sb.append(methodDeclaration.getReturnType2().toString());
            sb.append(" ");
            sb.append(methodDeclaration.getName().getFullyQualifiedName());
            sb.append("(");
            boolean z = true;
            for (Object obj3 : methodDeclaration.parameters()) {
                if (!z) {
                    sb.append(", ");
                }
                z = false;
                sb.append(obj3);
            }
            sb.append(");");
            return sb.toString();
        }

        public static void addAnnotations(List<Annotation> list, StringBuilder sb) {
            Iterator<Annotation> it = list.iterator();
            while (it.hasNext()) {
                SingleMemberAnnotation singleMemberAnnotation = (Annotation) it.next();
                ArrayList<String> arrayList = new ArrayList();
                if (singleMemberAnnotation.isSingleMemberAnnotation()) {
                    arrayList.add(singleMemberAnnotation.getValue().toString());
                } else if (singleMemberAnnotation.isNormalAnnotation()) {
                    Iterator it2 = ((NormalAnnotation) singleMemberAnnotation).values().iterator();
                    while (it2.hasNext()) {
                        arrayList.add(it2.next().toString());
                    }
                }
                sb.append("@");
                sb.append(singleMemberAnnotation.resolveTypeBinding().getQualifiedName());
                if (!arrayList.isEmpty()) {
                    sb.append("(");
                    boolean z = true;
                    for (String str : arrayList) {
                        if (!z) {
                            sb.append(", ");
                        }
                        z = false;
                        sb.append(Typography.quote);
                        sb.append(str);
                        sb.append(Typography.quote);
                    }
                    sb.append(")");
                }
                sb.append(" ");
            }
        }

        public static MethodDeclaration getRealMethodDeclarationNode(IMethod iMethod, CompilationUnit compilationUnit) throws JavaModelException {
            MethodDeclaration methodDeclarationNode = ASTNodeSearchUtil.getMethodDeclarationNode(iMethod, compilationUnit);
            if (isGenerated((ASTNode) methodDeclarationNode)) {
                Stack stack = new Stack();
                for (IType declaringType = iMethod.getDeclaringType(); declaringType != null; declaringType = declaringType.getDeclaringType()) {
                    stack.push(declaringType);
                }
                AbstractTypeDeclaration abstractTypeDeclarationFindTypeDeclaration = findTypeDeclaration((IType) stack.pop(), compilationUnit.types());
                while (!stack.isEmpty() && abstractTypeDeclarationFindTypeDeclaration != null) {
                    abstractTypeDeclarationFindTypeDeclaration = findTypeDeclaration((IType) stack.pop(), abstractTypeDeclarationFindTypeDeclaration.bodyDeclarations());
                }
                if (stack.isEmpty() && abstractTypeDeclarationFindTypeDeclaration != null) {
                    String elementName = iMethod.getElementName();
                    for (Object obj : abstractTypeDeclarationFindTypeDeclaration.bodyDeclarations()) {
                        if (obj instanceof MethodDeclaration) {
                            MethodDeclaration methodDeclaration = (MethodDeclaration) obj;
                            if (methodDeclaration.getName().toString().equals(elementName)) {
                                return methodDeclaration;
                            }
                        }
                    }
                }
            }
            return methodDeclarationNode;
        }

        public static AbstractTypeDeclaration findTypeDeclaration(IType iType, List<?> list) {
            for (Object obj : list) {
                if (obj instanceof AbstractTypeDeclaration) {
                    AbstractTypeDeclaration abstractTypeDeclaration = (AbstractTypeDeclaration) obj;
                    if (abstractTypeDeclaration.getName().toString().equals(iType.getElementName())) {
                        return abstractTypeDeclaration;
                    }
                }
            }
            return null;
        }

        public static int getSourceEndFixed(int i, org.eclipse.jdt.internal.compiler.ast.ASTNode aSTNode) throws Exception {
            org.eclipse.jdt.internal.compiler.ast.ASTNode aSTNode2;
            return (i != -1 || (aSTNode2 = (org.eclipse.jdt.internal.compiler.ast.ASTNode) aSTNode.getClass().getField("$generatedBy").get(aSTNode)) == null) ? i : aSTNode2.sourceEnd;
        }

        public static int fixRetrieveRightBraceOrSemiColonPosition(int i, AbstractMethodDeclaration abstractMethodDeclaration) {
            if (i != -1 || abstractMethodDeclaration == null) {
                return i;
            }
            if (EcjAugments.ASTNode_generatedBy.get(abstractMethodDeclaration) != null) {
                return abstractMethodDeclaration.declarationSourceEnd;
            }
            return -1;
        }

        public static int fixRetrieveRightBraceOrSemiColonPosition(int i, FieldDeclaration fieldDeclaration) {
            if (i != -1 || fieldDeclaration == null) {
                return i;
            }
            if (EcjAugments.ASTNode_generatedBy.get(fieldDeclaration) != null) {
                return fieldDeclaration.declarationSourceEnd;
            }
            return -1;
        }

        public static int fixRetrieveProperRightBracketPosition(int i, Type type) {
            if (i != -1 || type == null) {
                return i;
            }
            if (isGenerated((ASTNode) type)) {
                return (type.getStartPosition() + type.getLength()) - 1;
            }
            return -1;
        }

        public static boolean checkBit24(Object obj) throws Exception {
            return (((Integer) obj.getClass().getField("bits").get(obj)).intValue() & 8388608) != 0;
        }

        public static boolean skipRewritingGeneratedNodes(ASTNode aSTNode) throws Exception {
            return ((Boolean) aSTNode.getClass().getField("$isGenerated").get(aSTNode)).booleanValue();
        }

        public static void setIsGeneratedFlag(ASTNode aSTNode, org.eclipse.jdt.internal.compiler.ast.ASTNode aSTNode2) throws Exception {
            if (aSTNode2 == null || aSTNode == null) {
                return;
            }
            if (EcjAugments.ASTNode_generatedBy.get(aSTNode2) != null) {
                aSTNode.getClass().getField("$isGenerated").set(aSTNode, true);
            }
        }

        public static void setIsGeneratedFlagForName(Name name, Object obj) throws Exception {
            if (obj instanceof org.eclipse.jdt.internal.compiler.ast.ASTNode) {
                if (EcjAugments.ASTNode_generatedBy.get((org.eclipse.jdt.internal.compiler.ast.ASTNode) obj) != null) {
                    name.getClass().getField("$isGenerated").set(name, true);
                }
            }
        }

        public static RewriteEvent[] listRewriteHandleGeneratedMethods(RewriteEvent rewriteEvent) {
            RewriteEvent[] children = rewriteEvent.getChildren();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (RewriteEvent rewriteEvent2 : children) {
                if (isGenerated((ASTNode) rewriteEvent2.getOriginalValue())) {
                    boolean z = rewriteEvent2.getChangeKind() == 4 || rewriteEvent2.getChangeKind() == 2;
                    boolean z2 = rewriteEvent2.getOriginalValue() instanceof MethodDeclaration;
                    if (z && z2 && rewriteEvent2.getNewValue() != null) {
                        arrayList2.add(new NodeRewriteEvent((Object) null, rewriteEvent2.getNewValue()));
                    }
                } else {
                    arrayList.add(rewriteEvent2);
                }
            }
            arrayList.addAll(arrayList2);
            return (RewriteEvent[]) arrayList.toArray(new RewriteEvent[0]);
        }

        public static int getTokenEndOffsetFixed(TokenScanner tokenScanner, int i, int i2, Object obj) throws CoreException {
            boolean zBooleanValue;
            try {
                zBooleanValue = ((Boolean) obj.getClass().getField("$isGenerated").get(obj)).booleanValue();
            } catch (Exception unused) {
                zBooleanValue = false;
            }
            if (zBooleanValue) {
                return -1;
            }
            return tokenScanner.getTokenEndOffset(i, i2);
        }

        public static IMethod[] removeGeneratedMethods(IMethod[] iMethodArr) throws Exception {
            ArrayList arrayList = new ArrayList();
            for (IMethod iMethod : iMethodArr) {
                if (iMethod.getNameRange().getLength() > 0 && !iMethod.getNameRange().equals(iMethod.getSourceRange())) {
                    arrayList.add(iMethod);
                }
            }
            return arrayList.size() == iMethodArr.length ? iMethodArr : (IMethod[]) arrayList.toArray(new IMethod[0]);
        }

        public static SearchMatch[] removeGenerated(SearchMatch[] searchMatchArr) {
            ArrayList arrayList = new ArrayList();
            for (SearchMatch searchMatch : searchMatchArr) {
                if (!(searchMatch.getElement() instanceof IField) || ((IField) searchMatch.getElement()).getAnnotation("Generated") == null) {
                    arrayList.add(searchMatch);
                }
            }
            return (SearchMatch[]) arrayList.toArray(new SearchMatch[0]);
        }

        public static SearchResultGroup[] createFakeSearchResult(SearchResultGroup[] searchResultGroupArr, Object obj) throws Exception {
            Field declaredField;
            if ((searchResultGroupArr != null && searchResultGroupArr.length != 0) || (declaredField = obj.getClass().getDeclaredField("fField")) == null) {
                return searchResultGroupArr;
            }
            declaredField.setAccessible(true);
            return ((SourceField) declaredField.get(obj)).getDeclaringType().getAnnotation("Data") != null ? new SearchResultGroup[]{new SearchResultGroup((IResource) null, new SearchMatch[1])} : searchResultGroupArr;
        }

        public static SimpleName[] removeGeneratedSimpleNames(SimpleName[] simpleNameArr) throws Exception {
            Field field = SimpleName.class.getField("$isGenerated");
            int i = 0;
            for (int i2 = 0; i2 < simpleNameArr.length; i2++) {
                if (simpleNameArr[i2] == null || !((Boolean) field.get(simpleNameArr[i2])).booleanValue()) {
                    i++;
                }
            }
            if (i == simpleNameArr.length) {
                return simpleNameArr;
            }
            SimpleName[] simpleNameArr2 = new SimpleName[i];
            int i3 = 0;
            for (int i4 = 0; i4 < simpleNameArr.length; i4++) {
                if (simpleNameArr[i4] == null || !((Boolean) field.get(simpleNameArr[i4])).booleanValue()) {
                    simpleNameArr2[i3] = simpleNameArr[i4];
                    i3++;
                }
            }
            return simpleNameArr2;
        }

        public static org.eclipse.jdt.internal.compiler.ast.Annotation[] convertAnnotations(org.eclipse.jdt.internal.compiler.ast.Annotation[] annotationArr, IAnnotatable iAnnotatable) {
            boolean z;
            try {
                IAnnotation[] annotations = iAnnotatable.getAnnotations();
                if (annotationArr == null) {
                    return null;
                }
                int i = 0;
                for (int i2 = 0; i2 < annotationArr.length; i2++) {
                    String str = new String(annotationArr[i2].type.getLastToken());
                    int length = annotations.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            z = false;
                            break;
                        }
                        String elementName = annotations[i3].getElementName();
                        int iLastIndexOf = elementName.lastIndexOf(46);
                        if (iLastIndexOf > -1) {
                            elementName = elementName.substring(iLastIndexOf + 1);
                        }
                        if (elementName.equals(str)) {
                            z = true;
                            break;
                        }
                        i3++;
                    }
                    if (z) {
                        i++;
                    } else {
                        annotationArr[i2] = null;
                    }
                }
                if (i >= annotationArr.length) {
                    return annotationArr;
                }
                org.eclipse.jdt.internal.compiler.ast.Annotation[] annotationArr2 = new org.eclipse.jdt.internal.compiler.ast.Annotation[i];
                int i4 = 0;
                for (int i5 = 0; i5 < annotationArr.length; i5++) {
                    if (annotationArr[i5] != null) {
                        annotationArr2[i4] = annotationArr[i5];
                        i4++;
                    }
                }
                return annotationArr2;
            } catch (Exception unused) {
                return annotationArr;
            }
        }
    }
}
