package lombok.eclipse.agent;

import android.app.slice.SliceItem;
import android.content.Context;
import android.provider.SettingsStringUtil;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.lang.instrument.Instrumentation;
import java.net.URLClassLoader;
import java.security.ProtectionDomain;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.core.AgentLauncher;
import lombok.patcher.Filter;
import lombok.patcher.Hook;
import lombok.patcher.MethodTarget;
import lombok.patcher.ScriptManager;
import lombok.patcher.StackRequest;
import lombok.patcher.TargetMatcher;
import lombok.patcher.TransplantMapper;
import lombok.patcher.scripts.ScriptBuilder;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/eclipse/agent/EclipsePatcher.SCL.lombok */
public class EclipsePatcher implements AgentLauncher.AgentLaunchable {
    @Override // lombok.core.AgentLauncher.AgentLaunchable
    public void runAgent(String agentArgs, Instrumentation instrumentation, boolean injected, Class<?> launchingContext) throws Exception {
        registerPatchScripts(instrumentation, injected, launchingContext);
    }

    private static void registerPatchScripts(Instrumentation instrumentation, boolean reloadExistingClasses, Class<?> launchingContext) {
        ScriptManager sm = new ScriptManager();
        sm.registerTransformer(instrumentation);
        sm.setFilter(new Filter() { // from class: lombok.eclipse.agent.EclipsePatcher.1
            @Override // lombok.patcher.Filter
            public boolean shouldTransform(ClassLoader loader, String className, Class<?> classBeingDefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                ClassLoader parent;
                if (loader == null || !loader.getClass().getName().startsWith("org.sonar.classloader.")) {
                    return ((loader instanceof URLClassLoader) && (parent = loader.getParent()) != null && parent.getClass().getName().startsWith("org.eclipse.jdt.apt.core.internal.AnnotationProcessorFactoryLoader")) ? false : true;
                }
                return false;
            }
        });
        final boolean forceBaseResourceNames = shouldForceBaseResourceNames();
        sm.setTransplantMapper(new TransplantMapper() { // from class: lombok.eclipse.agent.EclipsePatcher.2
            @Override // lombok.patcher.TransplantMapper
            public String mapResourceName(int classFileFormatVersion, String resourceName) {
                return (classFileFormatVersion < 50 || forceBaseResourceNames) ? resourceName : "Class50/" + resourceName;
            }
        });
        EclipseLoaderPatcher.patchEquinoxLoaders(sm, launchingContext);
        patchCatchReparse(sm);
        patchIdentifierEndReparse(sm);
        patchRetrieveEllipsisStartPosition(sm);
        patchRetrieveRightBraceOrSemiColonPosition(sm);
        patchRetrieveProperRightBracketPosition(sm);
        patchRetrieveStartBlockPosition(sm);
        patchSetGeneratedFlag(sm);
        patchDomAstReparseIssues(sm);
        patchHideGeneratedNodes(sm);
        patchPostCompileHookEclipse(sm);
        patchFixSourceTypeConverter(sm);
        patchDisableLombokForCodeCleanup(sm);
        patchListRewriteHandleGeneratedMethods(sm);
        patchSyntaxAndOccurrencesHighlighting(sm);
        patchSortMembersOperation(sm);
        patchExtractInterface(sm);
        patchAboutDialog(sm);
        patchEclipseDebugPatches(sm);
        patchJavadoc(sm);
        patchPostCompileHookEcj(sm);
        patchAvoidReparsingGeneratedCode(sm);
        patchLombokizeAST(sm);
        patchEcjTransformers(sm);
        patchExtensionMethod(sm);
        patchRenameField(sm);
        patchNullCheck(sm);
        if (reloadExistingClasses) {
            sm.reloadClasses(instrumentation);
        }
    }

    private static boolean shouldForceBaseResourceNames() {
        String shadowOverride = System.getProperty("shadow.override.lombok", "");
        if (shadowOverride == null || shadowOverride.length() == 0) {
            return false;
        }
        for (String part : shadowOverride.split("\\s*" + (File.pathSeparatorChar == ';' ? ";" : SettingsStringUtil.DELIMITER) + "\\s*")) {
            if (part.equalsIgnoreCase("lombok.jar")) {
                return false;
            }
        }
        return true;
    }

    private static void patchRenameField(ScriptManager sm) {
        sm.addScript(ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.rename.RenameFieldProcessor", "checkAccessorDeclarations", "org.eclipse.ltk.core.refactoring.RefactoringStatus", "org.eclipse.core.runtime.IProgressMonitor", "org.eclipse.jdt.core.IMethod")).methodToWrap(new Hook("org.eclipse.jdt.internal.corext.refactoring.RefactoringSearchEngine", "search", "org.eclipse.jdt.internal.corext.refactoring.SearchResultGroup[]", "org.eclipse.jdt.core.search.SearchPattern", "org.eclipse.jdt.core.search.IJavaSearchScope", "org.eclipse.core.runtime.IProgressMonitor", "org.eclipse.ltk.core.refactoring.RefactoringStatus")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "createFakeSearchResult", "org.eclipse.jdt.internal.corext.refactoring.SearchResultGroup[]", "org.eclipse.jdt.internal.corext.refactoring.SearchResultGroup[]", "java.lang.Object")).requestExtra(StackRequest.THIS).transplant().build());
        sm.addScript(ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.rename.RenameFieldProcessor", "addAccessorOccurrences", "void", "org.eclipse.core.runtime.IProgressMonitor", "org.eclipse.jdt.core.IMethod", "java.lang.String", "java.lang.String", "org.eclipse.ltk.core.refactoring.RefactoringStatus")).methodToWrap(new Hook("org.eclipse.jdt.internal.corext.refactoring.SearchResultGroup", "getSearchResults", "org.eclipse.jdt.core.search.SearchMatch[]", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGenerated", "org.eclipse.jdt.core.search.SearchMatch[]", "org.eclipse.jdt.core.search.SearchMatch[]")).transplant().build());
    }

    private static void patchExtractInterface(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.SourceElementNotifier", "notifySourceElementRequestor", "void", "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration", "org.eclipse.jdt.internal.compiler.ast.TypeDeclaration", "org.eclipse.jdt.internal.compiler.ast.ImportReference")).methodToWrap(new Hook("org.eclipse.jdt.internal.compiler.util.HashtableOfObjectToInt", "get", SliceItem.FORMAT_INT, "java.lang.Object")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "getSourceEndFixed", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.ASTNode")).requestExtra(StackRequest.PARAM1).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.structure.ExtractInterfaceProcessor", "createMethodDeclaration", "void", "org.eclipse.jdt.internal.corext.refactoring.structure.CompilationUnitRewrite", "org.eclipse.jdt.core.dom.rewrite.ASTRewrite", "org.eclipse.jdt.core.dom.AbstractTypeDeclaration", "org.eclipse.jdt.core.dom.MethodDeclaration")).methodToWrap(new Hook("org.eclipse.jface.text.IDocument", "get", "java.lang.String", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "getRealMethodDeclarationSource", "java.lang.String", "java.lang.String", "java.lang.Object", "org.eclipse.jdt.core.dom.MethodDeclaration")).requestExtra(StackRequest.THIS, StackRequest.PARAM4).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.structure.ExtractInterfaceProcessor", "createMemberDeclarations")).target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.structure.ExtractInterfaceProcessor", "createMethodComments")).methodToReplace(new Hook("org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil", "getMethodDeclarationNode", "org.eclipse.jdt.core.dom.MethodDeclaration", "org.eclipse.jdt.core.IMethod", "org.eclipse.jdt.core.dom.CompilationUnit")).replacementMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "getRealMethodDeclarationNode", "org.eclipse.jdt.core.dom.MethodDeclaration", "org.eclipse.jdt.core.IMethod", "org.eclipse.jdt.core.dom.CompilationUnit")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.core.dom.rewrite.ListRewrite", "insertFirst")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "isListRewriteOnGeneratedNode", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.rewrite.ListRewrite")).request(StackRequest.THIS).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.structure.ExtractInterfaceProcessor", "createMethodComment")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "isGenerated", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.ASTNode")).request(StackRequest.PARAM2).transplant().build());
    }

    private static void patchAboutDialog(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.core.internal.runtime.Product", "getProperty", "java.lang.String", "java.lang.String")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$LombokDeps", "addLombokNotesToEclipseAboutDialog", "java.lang.String", "java.lang.String", "java.lang.String")).request(StackRequest.RETURN_VALUE, StackRequest.PARAM1).transplant().build());
    }

    private static void patchSyntaxAndOccurrencesHighlighting(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.ui.search.OccurrencesFinder", "addUsage")).target(new MethodTarget("org.eclipse.jdt.internal.ui.search.OccurrencesFinder", "addWrite")).target(new MethodTarget("org.eclipse.jdt.internal.ui.javaeditor.SemanticHighlightingReconciler$PositionCollector", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.SimpleName")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "isGenerated", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.ASTNode")).valueMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "returnFalse", TypedValues.Custom.S_BOOLEAN, "java.lang.Object")).request(StackRequest.PARAM1).build());
    }

    private static void patchDisableLombokForCodeCleanup(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTNode", "accept", "void", "org.eclipse.jdt.core.dom.ASTVisitor")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "isRefactoringVisitorAndGenerated", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.core.dom.ASTVisitor")).request(StackRequest.THIS, StackRequest.PARAM1).build());
    }

    private static void patchListRewriteHandleGeneratedMethods(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.dom.rewrite.ASTRewriteAnalyzer$ListRewriter", "rewriteList")).methodToReplace(new Hook("org.eclipse.jdt.internal.core.dom.rewrite.RewriteEvent", "getChildren", "org.eclipse.jdt.internal.core.dom.rewrite.RewriteEvent[]", new String[0])).replacementMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "listRewriteHandleGeneratedMethods", "org.eclipse.jdt.internal.core.dom.rewrite.RewriteEvent[]", "org.eclipse.jdt.internal.core.dom.rewrite.RewriteEvent")).build());
    }

    private static void patchSortMembersOperation(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.SortElementsOperation$2", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.CompilationUnit")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.CompilationUnit", "types", "java.util.List", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGeneratedNodes", "java.util.List", "java.util.List")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.SortElementsOperation$2", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.AnnotationTypeDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.AnnotationTypeDeclaration", "bodyDeclarations", "java.util.List", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGeneratedNodes", "java.util.List", "java.util.List")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.SortElementsOperation$2", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.AnonymousClassDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.AnonymousClassDeclaration", "bodyDeclarations", "java.util.List", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGeneratedNodes", "java.util.List", "java.util.List")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.SortElementsOperation$2", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.TypeDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.TypeDeclaration", "bodyDeclarations", "java.util.List", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGeneratedNodes", "java.util.List", "java.util.List")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.SortElementsOperation$2", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.EnumDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.EnumDeclaration", "bodyDeclarations", "java.util.List", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGeneratedNodes", "java.util.List", "java.util.List")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.SortElementsOperation$2", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.EnumDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.EnumDeclaration", "enumConstants", "java.util.List", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGeneratedNodes", "java.util.List", "java.util.List")).transplant().build());
    }

    private static void patchDomAstReparseIssues(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.dom.rewrite.ASTRewriteAnalyzer", "visit")).methodToReplace(new Hook("org.eclipse.jdt.internal.core.dom.rewrite.TokenScanner", "getTokenEndOffset", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).replacementMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "getTokenEndOffsetFixed", SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.core.dom.rewrite.TokenScanner", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, "java.lang.Object")).requestExtra(StackRequest.PARAM1).transplant().build());
    }

    private static void patchPostCompileHookEclipse(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.builder.IncrementalImageBuilder", "writeClassFileContents")).target(new MethodTarget("org.eclipse.jdt.internal.core.builder.AbstractImageBuilder", "writeClassFileContents")).methodToWrap(new Hook("org.eclipse.jdt.internal.compiler.ClassFile", "getBytes", "byte[]", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$LombokDeps", "runPostCompiler", "byte[]", "byte[]", "java.lang.String")).requestExtra(StackRequest.PARAM3).build());
    }

    private static void patchPostCompileHookEcj(ScriptManager sm) {
        sm.addScriptIfNotWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.tool.EclipseCompilerImpl", "outputClassFiles")).methodToWrap(new Hook("javax.tools.JavaFileObject", "openOutputStream", "java.io.OutputStream", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$LombokDeps", "runPostCompiler", "java.io.OutputStream", "java.io.OutputStream")).transplant().build());
        sm.addScriptIfNotWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.util.Util", "writeToDisk")).methodToWrap(new Hook("java.io.BufferedOutputStream", "<init>", "void", "java.io.OutputStream", SliceItem.FORMAT_INT)).wrapMethod(new Hook("lombok.launch.PatchFixesHider$LombokDeps", "runPostCompiler", "java.io.BufferedOutputStream", "java.io.BufferedOutputStream", "java.lang.String", "java.lang.String")).requestExtra(StackRequest.PARAM2, StackRequest.PARAM3).transplant().build());
    }

    private static void patchHideGeneratedNodes(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.internal.corext.dom.LinkedNodeFinder", "findByNode")).target(new MethodTarget("org.eclipse.jdt.internal.corext.dom.LinkedNodeFinder", "findByBinding")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGeneratedSimpleNames", "org.eclipse.jdt.core.dom.SimpleName[]", "org.eclipse.jdt.core.dom.SimpleName[]")).request(StackRequest.RETURN_VALUE).build());
        patchRefactorScripts(sm);
        patchFormatters(sm);
    }

    private static void patchFormatters(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.setSymbolDuringMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.formatter.DefaultCodeFormatter", "formatCompilationUnit")).callToWrap(new Hook("org.eclipse.jdt.internal.core.util.CodeSnippetParsingUtil", "parseCompilationUnit", "org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration", "char[]", "java.util.Map", TypedValues.Custom.S_BOOLEAN)).symbol("lombok.disable").build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.setSymbolDuringMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.formatter.DefaultCodeFormatter", "parseSourceCode")).callToWrap(new Hook("org.eclipse.jdt.core.dom.ASTParser", "createAST", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.core.runtime.IProgressMonitor")).symbol("lombok.disable").build());
    }

    private static void patchRefactorScripts(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.core.dom.rewrite.ASTRewrite", "replace")).target(new MethodTarget("org.eclipse.jdt.core.dom.rewrite.ASTRewrite", "remove")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "skipRewritingGeneratedNodes", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.ASTNode")).transplant().request(StackRequest.PARAM1).build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.rename.RenameTypeProcessor", "addConstructorRenames")).methodToWrap(new Hook("org.eclipse.jdt.core.IType", "getMethods", "org.eclipse.jdt.core.IMethod[]", new String[0])).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "removeGeneratedMethods", "org.eclipse.jdt.core.IMethod[]", "org.eclipse.jdt.core.IMethod[]")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.rename.TempOccurrenceAnalyzer", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.SimpleName")).target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.rename.RenameAnalyzeUtil$ProblemNodeFinder$NameNodeVisitor", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.SimpleName")).target(new MethodTarget("org.eclipse.jdt.internal.corext.refactoring.rename.RenameTypeParameterProcessor$RenameTypeParameterVisitor", "visit", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.SimpleName")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "isGenerated", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.core.dom.ASTNode")).valueMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "returnTrue", TypedValues.Custom.S_BOOLEAN, "java.lang.Object")).request(StackRequest.PARAM1).transplant().build());
    }

    private static void patchCatchReparse(ScriptManager sm) {
        sm.addScript(ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "retrieveStartingCatchPosition")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveStartingCatchPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).transplant().request(StackRequest.RETURN_VALUE, StackRequest.PARAM1).build());
    }

    private static void patchIdentifierEndReparse(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "retrieveIdentifierEndPosition")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveIdentifierEndPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).transplant().request(StackRequest.RETURN_VALUE, StackRequest.PARAM1, StackRequest.PARAM2).build());
    }

    private static void patchRetrieveEllipsisStartPosition(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "retrieveEllipsisStartPosition")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveEllipsisStartPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).transplant().request(StackRequest.RETURN_VALUE, StackRequest.PARAM2).build());
    }

    private static void patchRetrieveStartBlockPosition(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "retrieveStartBlockPosition")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveStartBlockPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).transplant().request(StackRequest.RETURN_VALUE, StackRequest.PARAM2).build());
    }

    private static void patchRetrieveRightBraceOrSemiColonPosition(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convert", "org.eclipse.jdt.core.dom.ASTNode", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.ASTConverter", "retrieveRightBraceOrSemiColonPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveRightBraceOrSemiColonPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration")).requestExtra(StackRequest.PARAM2).transplant().build());
        sm.addScript(ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convert", "org.eclipse.jdt.core.dom.ASTNode", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.ASTConverter", "retrieveRightBrace", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveRightBraceOrSemiColonPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration")).requestExtra(StackRequest.PARAM2).transplant().build());
        sm.addScript(ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convert", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.internal.compiler.ast.FieldDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.ASTConverter", "retrieveRightBrace", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveRightBraceOrSemiColonPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.FieldDeclaration")).requestExtra(StackRequest.PARAM1).transplant().build());
    }

    private static void patchRetrieveProperRightBracketPosition(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "extractSubArrayType", "org.eclipse.jdt.core.dom.ArrayType", "org.eclipse.jdt.core.dom.ArrayType", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).methodToWrap(new Hook("org.eclipse.jdt.core.dom.ASTConverter", "retrieveProperRightBracketPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveProperRightBracketPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, "org.eclipse.jdt.core.dom.Type")).requestExtra(StackRequest.PARAM1).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertToArray", "org.eclipse.jdt.core.dom.ArrayType", "org.eclipse.jdt.core.dom.Type", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.Annotation[][]")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.ASTConverter", "retrieveProperRightBracketPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "fixRetrieveProperRightBracketPosition", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, "org.eclipse.jdt.core.dom.Type")).requestExtra(StackRequest.PARAM1).transplant().build());
    }

    private static void patchSetGeneratedFlag(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.addField().targetClass("org.eclipse.jdt.internal.compiler.ast.ASTNode").fieldName("$generatedBy").fieldType("Lorg/eclipse/jdt/internal/compiler/ast/ASTNode;").setPublic().setTransient().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.addField().targetClass("org.eclipse.jdt.core.dom.ASTNode").fieldName("$isGenerated").fieldType("Z").setPublic().setTransient().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new TargetMatcher() { // from class: lombok.eclipse.agent.EclipsePatcher.3
            @Override // lombok.patcher.TargetMatcher
            public boolean matches(String classSpec, String methodName, String descriptor) {
                if (!"convert".equals(methodName)) {
                    return false;
                }
                List<String> fullDesc = MethodTarget.decomposeFullDesc(descriptor);
                return !ExifInterface.GPS_MEASUREMENT_INTERRUPTED.equals(fullDesc.get(0)) && fullDesc.size() >= 2 && fullDesc.get(1).startsWith("Lorg/eclipse/jdt/internal/compiler/ast/");
            }

            @Override // lombok.patcher.TargetMatcher
            public String describe() {
                return "ASTConverter:[all relevant]";
            }

            @Override // lombok.patcher.TargetMatcher
            public Collection<String> getAffectedClasses() {
                return Collections.singleton("org.eclipse.jdt.core.dom.ASTConverter");
            }
        }).request(StackRequest.PARAM1, StackRequest.RETURN_VALUE).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlag", "void", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convert", "org.eclipse.jdt.core.dom.ASTNode", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration")).request(StackRequest.PARAM2, StackRequest.RETURN_VALUE).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlag", "void", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertToFieldDeclaration", "org.eclipse.jdt.core.dom.FieldDeclaration", "org.eclipse.jdt.internal.compiler.ast.FieldDeclaration")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertToType", "org.eclipse.jdt.core.dom.Type", "org.eclipse.jdt.internal.compiler.ast.NameReference")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertType", "org.eclipse.jdt.core.dom.Type", "org.eclipse.jdt.internal.compiler.ast.TypeReference")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertToVariableDeclarationExpression", "org.eclipse.jdt.core.dom.VariableDeclarationExpression", "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertToSingleVariableDeclaration", "org.eclipse.jdt.core.dom.SingleVariableDeclaration", "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertToVariableDeclarationFragment", "org.eclipse.jdt.core.dom.VariableDeclarationFragment", "org.eclipse.jdt.internal.compiler.ast.FieldDeclaration")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertToVariableDeclarationFragment", "org.eclipse.jdt.core.dom.VariableDeclarationFragment", "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertToVariableDeclarationStatement", "org.eclipse.jdt.core.dom.VariableDeclarationStatement", "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "createBaseType", "org.eclipse.jdt.core.dom.Type", "org.eclipse.jdt.internal.compiler.ast.TypeReference", "long[]", "org.eclipse.jdt.internal.compiler.ast.Annotation[][]", "char[][]", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT, TypedValues.Custom.S_BOOLEAN)).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "createQualifiedType", "org.eclipse.jdt.core.dom.QualifiedType", "org.eclipse.jdt.internal.compiler.ast.TypeReference", "long[]", "org.eclipse.jdt.internal.compiler.ast.Annotation[][]", "char[][]", SliceItem.FORMAT_INT, "org.eclipse.jdt.core.dom.Type")).request(StackRequest.PARAM1, StackRequest.RETURN_VALUE).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlag", "void", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new TargetMatcher() { // from class: lombok.eclipse.agent.EclipsePatcher.4
            @Override // lombok.patcher.TargetMatcher
            public boolean matches(String classSpec, String methodName, String descriptor) {
                if (!methodName.startsWith("convert")) {
                    return false;
                }
                List<String> fullDesc = MethodTarget.decomposeFullDesc(descriptor);
                return fullDesc.size() >= 2 && fullDesc.get(1).startsWith("Lorg/eclipse/jdt/internal/compiler/ast/");
            }

            @Override // lombok.patcher.TargetMatcher
            public String describe() {
                return "ASTConverter::(all relevant)";
            }

            @Override // lombok.patcher.TargetMatcher
            public Collection<String> getAffectedClasses() {
                return Collections.singleton("org.eclipse.jdt.core.dom.ASTConverter");
            }
        }).methodToWrap(new Hook("org.eclipse.jdt.core.dom.SimpleName", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM1).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlagForName", "void", "org.eclipse.jdt.core.dom.Name", "java.lang.Object")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convert", "org.eclipse.jdt.core.dom.ASTNode", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.SimpleName", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM2).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlagForName", "void", "org.eclipse.jdt.core.dom.Name", "java.lang.Object")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertType", "org.eclipse.jdt.core.dom.Type", "org.eclipse.jdt.internal.compiler.ast.TypeReference")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.PrimitiveType", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM1).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlag", "void", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertType", "org.eclipse.jdt.core.dom.Type", "org.eclipse.jdt.internal.compiler.ast.TypeReference")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.SimpleType", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM1).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlag", "void", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertType", "org.eclipse.jdt.core.dom.Type", "org.eclipse.jdt.internal.compiler.ast.TypeReference")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.ParameterizedType", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM1).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlag", "void", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "convertType", "org.eclipse.jdt.core.dom.Type", "org.eclipse.jdt.internal.compiler.ast.TypeReference")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.QualifiedType", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM1).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlag", "void", "org.eclipse.jdt.core.dom.ASTNode", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setQualifiedNameNameAndSourceRanges", "org.eclipse.jdt.core.dom.QualifiedName", "char[][]", "long[]", SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.ASTNode")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setQualifiedNameNameAndSourceRanges", "org.eclipse.jdt.core.dom.QualifiedName", "char[][]", "long[]", SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.TypeReference")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.SimpleName", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM4).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlagForName", "void", "org.eclipse.jdt.core.dom.Name", "java.lang.Object")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setQualifiedNameNameAndSourceRanges", "org.eclipse.jdt.core.dom.QualifiedName", "char[][]", "long[]", SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.ASTNode")).target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setQualifiedNameNameAndSourceRanges", "org.eclipse.jdt.core.dom.QualifiedName", "char[][]", "long[]", SliceItem.FORMAT_INT, "org.eclipse.jdt.internal.compiler.ast.TypeReference")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.QualifiedName", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM4).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlagForName", "void", "org.eclipse.jdt.core.dom.Name", "java.lang.Object")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setQualifiedNameNameAndSourceRanges", "org.eclipse.jdt.core.dom.QualifiedName", "char[][]", "long[]", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.SimpleName", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM3).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlagForName", "void", "org.eclipse.jdt.core.dom.Name", "java.lang.Object")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setQualifiedNameNameAndSourceRanges", "org.eclipse.jdt.core.dom.QualifiedName", "char[][]", "long[]", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.QualifiedName", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM3).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlagForName", "void", "org.eclipse.jdt.core.dom.Name", "java.lang.Object")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setTypeNameForAnnotation", "void", "org.eclipse.jdt.internal.compiler.ast.Annotation", "org.eclipse.jdt.core.dom.Annotation")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.SimpleName", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM1).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlagForName", "void", "org.eclipse.jdt.core.dom.Name", "java.lang.Object")).transplant().build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setTypeNameForAnnotation", "void", "org.eclipse.jdt.internal.compiler.ast.Annotation", "org.eclipse.jdt.core.dom.Annotation")).methodToWrap(new Hook("org.eclipse.jdt.core.dom.QualifiedName", "<init>", "void", "org.eclipse.jdt.core.dom.AST")).requestExtra(StackRequest.PARAM1).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "setIsGeneratedFlagForName", "void", "org.eclipse.jdt.core.dom.Name", "java.lang.Object")).transplant().build());
    }

    private static void patchAvoidReparsingGeneratedCode(ScriptManager sm) {
        sm.addScript(ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.compiler.parser.Parser", "parse", "void", "org.eclipse.jdt.internal.compiler.ast.MethodDeclaration", "org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "checkBit24", TypedValues.Custom.S_BOOLEAN, "java.lang.Object")).transplant().request(StackRequest.PARAM1).build());
        sm.addScript(ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.compiler.parser.Parser", "parse", "void", "org.eclipse.jdt.internal.compiler.ast.ConstructorDeclaration", "org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration", TypedValues.Custom.S_BOOLEAN)).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "checkBit24", TypedValues.Custom.S_BOOLEAN, "java.lang.Object")).transplant().request(StackRequest.PARAM1).build());
        sm.addScript(ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.compiler.parser.Parser", "parse", "void", "org.eclipse.jdt.internal.compiler.ast.Initializer", "org.eclipse.jdt.internal.compiler.ast.TypeDeclaration", "org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "checkBit24", TypedValues.Custom.S_BOOLEAN, "java.lang.Object")).transplant().request(StackRequest.PARAM1).build());
    }

    private static void patchLombokizeAST(ScriptManager sm) {
        sm.addScript(ScriptBuilder.addField().targetClass("org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration").fieldName("$lombokAST").fieldType("Ljava/lang/Object;").setPublic().setTransient().build());
        sm.addScript(ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.internal.compiler.parser.Parser", "getMethodBodies", "void", "org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$Transform", "transform", "void", "java.lang.Object", "java.lang.Object")).request(StackRequest.THIS, StackRequest.PARAM1).build());
        sm.addScript(ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.internal.compiler.parser.Parser", "endParse", "org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration", SliceItem.FORMAT_INT)).wrapMethod(new Hook("lombok.launch.PatchFixesHider$Transform", "transform_swapped", "void", "java.lang.Object", "java.lang.Object")).request(StackRequest.THIS, StackRequest.RETURN_VALUE).build());
    }

    private static void patchEcjTransformers(ScriptManager sm) {
        addPatchesForDelegate(sm);
        addPatchesForVal(sm);
        addPatchesForValEclipse(sm);
    }

    private static void addPatchesForDelegate(ScriptManager sm) {
        sm.addScript(ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.compiler.lookup.ClassScope", "buildFieldsAndMethods", "void", new String[0])).request(StackRequest.THIS).decisionMethod(new Hook("lombok.launch.PatchFixesHider$Delegate", "handleDelegateForType", TypedValues.Custom.S_BOOLEAN, "java.lang.Object")).build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.setSymbolDuringMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.core.SelectionRequestor", "acceptSourceMethod")).callToWrap(new Hook("org.eclipse.jdt.core.IType", "getMethods", "org.eclipse.jdt.core.IMethod[]", new String[0])).symbol("lombok.skipdelegates").build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.addField().fieldName("$delegateMethods").fieldType("Ljava/util/Map;").setPublic().setTransient().targetClass("org.eclipse.jdt.internal.core.CompilationUnit").build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.internal.core.SourceTypeElementInfo", "getChildren", "org.eclipse.jdt.core.IJavaElement[]", new String[0])).request(StackRequest.RETURN_VALUE, StackRequest.THIS).wrapMethod(new Hook("lombok.launch.PatchFixesHider$Delegate", "addGeneratedDelegateMethods", "java.lang.Object[]", "java.lang.Object", "java.lang.Object")).build());
    }

    private static void addPatchesForValEclipse(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.addField().fieldName("$initCopy").fieldType("Lorg/eclipse/jdt/internal/compiler/ast/ASTNode;").setPublic().setTransient().targetClass("org.eclipse.jdt.internal.compiler.ast.LocalDeclaration").build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.addField().fieldName("$iterableCopy").fieldType("Lorg/eclipse/jdt/internal/compiler/ast/ASTNode;").setPublic().setTransient().targetClass("org.eclipse.jdt.internal.compiler.ast.LocalDeclaration").build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.internal.compiler.parser.Parser", "consumeExitVariableWithInitialization", "void", new String[0])).request(StackRequest.THIS).wrapMethod(new Hook("lombok.launch.PatchFixesHider$ValPortal", "copyInitializationOfLocalDeclaration", "void", "java.lang.Object")).build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.internal.compiler.parser.Parser", "consumeEnhancedForStatementHeader", "void", new String[0])).request(StackRequest.THIS).wrapMethod(new Hook("lombok.launch.PatchFixesHider$ValPortal", "copyInitializationOfForEachIterable", "void", "java.lang.Object")).build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setModifiers", "void", "org.eclipse.jdt.core.dom.VariableDeclarationStatement", "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$ValPortal", "addFinalAndValAnnotationToVariableDeclarationStatement", "void", "java.lang.Object", "java.lang.Object", "java.lang.Object")).request(StackRequest.THIS, StackRequest.PARAM1, StackRequest.PARAM2).build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTConverter", "setModifiers", "void", "org.eclipse.jdt.core.dom.SingleVariableDeclaration", "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$ValPortal", "addFinalAndValAnnotationToSingleVariableDeclaration", "void", "java.lang.Object", "java.lang.Object", "java.lang.Object")).request(StackRequest.THIS, StackRequest.PARAM1, StackRequest.PARAM2).build());
    }

    private static void addPatchesForVal(ScriptManager sm) {
        sm.addScript(ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.LocalDeclaration", "resolve", "void", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).request(StackRequest.THIS, StackRequest.PARAM1).decisionMethod(new Hook("lombok.launch.PatchFixesHider$Val", "handleValForLocalDeclaration", TypedValues.Custom.S_BOOLEAN, "java.lang.Object", "java.lang.Object")).build());
        sm.addScript(ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.LocalDeclaration", "resolve", "void", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).methodToReplace(new Hook("org.eclipse.jdt.internal.compiler.ast.Expression", "resolveType", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).requestExtra(StackRequest.THIS).replacementMethod(new Hook("lombok.launch.PatchFixesHider$Val", "skipResolveInitializerIfAlreadyCalled2", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.ast.Expression", "org.eclipse.jdt.internal.compiler.lookup.BlockScope", "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration")).transplant().build());
        sm.addScript(ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.ForeachStatement", "resolve", "void", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).methodToReplace(new Hook("org.eclipse.jdt.internal.compiler.ast.Expression", "resolveType", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).replacementMethod(new Hook("lombok.launch.PatchFixesHider$Val", "skipResolveInitializerIfAlreadyCalled", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.ast.Expression", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).transplant().build());
        sm.addScript(ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.ForeachStatement", "resolve", "void", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).request(StackRequest.THIS, StackRequest.PARAM1).decisionMethod(new Hook("lombok.launch.PatchFixesHider$Val", "handleValForForEach", TypedValues.Custom.S_BOOLEAN, "java.lang.Object", "java.lang.Object")).build());
    }

    private static void patchFixSourceTypeConverter(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.internal.compiler.parser.SourceTypeConverter", "convertAnnotations", "org.eclipse.jdt.internal.compiler.ast.Annotation[]", "org.eclipse.jdt.core.IAnnotatable")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "convertAnnotations", "org.eclipse.jdt.internal.compiler.ast.Annotation[]", "org.eclipse.jdt.internal.compiler.ast.Annotation[]", "org.eclipse.jdt.core.IAnnotatable")).request(StackRequest.PARAM1, StackRequest.RETURN_VALUE).build());
    }

    private static void patchEclipseDebugPatches(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.core.dom.ASTNode", "setSourceRange", "void", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).request(StackRequest.THIS).request(StackRequest.PARAM1).request(StackRequest.PARAM2).decisionMethod(new Hook("lombok.eclipse.agent.PatchDiagnostics", "setSourceRangeCheck", TypedValues.Custom.S_BOOLEAN, "java.lang.Object", SliceItem.FORMAT_INT, SliceItem.FORMAT_INT)).build());
    }

    private static void patchExtensionMethod(ScriptManager sm) {
        sm.addScript(ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.MessageSend", "resolveType", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).request(StackRequest.RETURN_VALUE).request(StackRequest.THIS).request(StackRequest.PARAM1).wrapMethod(new Hook("lombok.launch.PatchFixesHider$ExtensionMethod", "resolveType", "java.lang.Object", "java.lang.Object", "java.lang.Object", "java.lang.Object")).cast().build());
        sm.addScript(ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.MessageSend", "resolveType", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).methodToReplace(new Hook("org.eclipse.jdt.internal.compiler.problem.ProblemReporter", "errorNoMethodFor", "void", "org.eclipse.jdt.internal.compiler.ast.MessageSend", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding[]")).replacementMethod(new Hook("lombok.launch.PatchFixesHider$ExtensionMethod", "errorNoMethodFor", "void", "java.lang.Object", "java.lang.Object", "java.lang.Object", "java.lang.Object")).build());
        sm.addScript(ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.MessageSend", "resolveType", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).methodToReplace(new Hook("org.eclipse.jdt.internal.compiler.problem.ProblemReporter", "invalidMethod", "void", "org.eclipse.jdt.internal.compiler.ast.MessageSend", "org.eclipse.jdt.internal.compiler.lookup.MethodBinding")).replacementMethod(new Hook("lombok.launch.PatchFixesHider$ExtensionMethod", "invalidMethod", "void", "java.lang.Object", "java.lang.Object", "java.lang.Object")).build());
        sm.addScript(ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.MessageSend", "resolveType", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).methodToReplace(new Hook("org.eclipse.jdt.internal.compiler.problem.ProblemReporter", "invalidMethod", "void", "org.eclipse.jdt.internal.compiler.ast.MessageSend", "org.eclipse.jdt.internal.compiler.lookup.MethodBinding", "org.eclipse.jdt.internal.compiler.lookup.Scope")).replacementMethod(new Hook("lombok.launch.PatchFixesHider$ExtensionMethod", "invalidMethod", "void", "java.lang.Object", "java.lang.Object", "java.lang.Object", "java.lang.Object")).build());
        sm.addScript(ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.MessageSend", "resolveType", "org.eclipse.jdt.internal.compiler.lookup.TypeBinding", "org.eclipse.jdt.internal.compiler.lookup.BlockScope")).methodToReplace(new Hook("org.eclipse.jdt.internal.compiler.problem.ProblemReporter", "nonStaticAccessToStaticMethod", "void", "org.eclipse.jdt.internal.compiler.ast.ASTNode", "org.eclipse.jdt.internal.compiler.lookup.MethodBinding")).replacementMethod(new Hook("lombok.launch.PatchFixesHider$ExtensionMethod", "nonStaticAccessToStaticMethod", "void", "java.lang.Object", "java.lang.Object", "java.lang.Object", "java.lang.Object")).requestExtra(StackRequest.THIS).build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapReturnValue().target(new MethodTarget("org.eclipse.jdt.ui.text.java.CompletionProposalCollector", "getJavaCompletionProposals", "org.eclipse.jdt.ui.text.java.IJavaCompletionProposal[]", new String[0])).request(StackRequest.RETURN_VALUE).request(StackRequest.THIS).wrapMethod(new Hook("lombok.eclipse.agent.PatchExtensionMethodCompletionProposalPortal", "getJavaCompletionProposals", "org.eclipse.jdt.ui.text.java.IJavaCompletionProposal[]", "java.lang.Object[]", "java.lang.Object")).build());
    }

    private static void patchNullCheck(ScriptManager sm) {
        sm.addScript(ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.compiler.problem.ProblemReporter", "expressionNonNullComparison", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.internal.compiler.ast.Expression", TypedValues.Custom.S_BOOLEAN)).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "isGenerated", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.internal.compiler.ast.ASTNode")).valueMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "returnTrue", TypedValues.Custom.S_BOOLEAN, "java.lang.Object")).request(StackRequest.PARAM1).transplant().build());
        sm.addScript(ScriptBuilder.exitEarly().target(new MethodTarget("org.eclipse.jdt.internal.compiler.problem.ProblemReporter", "fakeReachable", "void", "org.eclipse.jdt.internal.compiler.ast.ASTNode")).decisionMethod(new Hook("lombok.launch.PatchFixesHider$PatchFixes", "isGenerated", TypedValues.Custom.S_BOOLEAN, "org.eclipse.jdt.internal.compiler.ast.ASTNode")).request(StackRequest.PARAM1).transplant().build());
    }

    private static void patchJavadoc(ScriptManager sm) {
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.ui.text.javadoc.JavadocContentAccess2", "getHTMLContent", "java.lang.String", "org.eclipse.jdt.core.IJavaElement", TypedValues.Custom.S_BOOLEAN)).methodToWrap(new Hook("org.eclipse.jdt.internal.ui.text.javadoc.JavadocContentAccess2", "getHTMLContentFromSource", "java.lang.String", "org.eclipse.jdt.core.IJavaElement")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$Javadoc", "getHTMLContentFromSource", "java.lang.String", "java.lang.String", "org.eclipse.jdt.core.IJavaElement")).requestExtra(StackRequest.PARAM1).build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.wrapMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.ui.text.javadoc.JavadocContentAccess2", "getHTMLContent", "java.lang.String", "org.eclipse.jdt.core.IMember", TypedValues.Custom.S_BOOLEAN)).methodToWrap(new Hook("org.eclipse.jdt.internal.ui.text.javadoc.JavadocContentAccess2", "getHTMLContentFromSource", "java.lang.String", "org.eclipse.jdt.core.IMember")).wrapMethod(new Hook("lombok.launch.PatchFixesHider$Javadoc", "getHTMLContentFromSource", "java.lang.String", "java.lang.String", "org.eclipse.jdt.core.IJavaElement")).requestExtra(StackRequest.PARAM1).build());
        sm.addScript(ScriptBuilder.replaceMethodCall().target(new MethodTarget("org.eclipse.jdt.internal.compiler.ast.TypeDeclaration", "printBody", "java.lang.StringBuffer", SliceItem.FORMAT_INT, "java.lang.StringBuffer")).methodToReplace(new Hook("org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration", Context.PRINT_SERVICE, "java.lang.StringBuffer", SliceItem.FORMAT_INT, "java.lang.StringBuffer")).replacementMethod(new Hook("lombok.launch.PatchFixesHider$Javadoc", "printMethod", "java.lang.StringBuffer", "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration", SliceItem.FORMAT_INT, "java.lang.StringBuffer", "org.eclipse.jdt.internal.compiler.ast.TypeDeclaration")).requestExtra(StackRequest.THIS).build());
        sm.addScriptIfWitness(EclipseLoaderPatcher.OSGI_TYPES, ScriptBuilder.addField().fieldName("$javadoc").fieldType("Ljava/util/Map;").setPublic().setTransient().targetClass("org.eclipse.jdt.internal.core.CompilationUnit").build());
    }
}
