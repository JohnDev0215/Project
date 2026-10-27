package lombok.eclipse.handlers.singulars;

import android.telephony.PhoneNumberUtils;
import android.text.format.DateFormat;
import java.util.List;
import lombok.AccessLevel;
import lombok.core.LombokImmutableList;
import lombok.core.configuration.CheckerFrameworkVersion;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.EclipseSingularsRecipes;
import org.eclipse.jdt.internal.compiler.ast.Statement;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/eclipse/handlers/singulars/EclipseJavaUtilSetSingularizer.SCL.lombok */
public class EclipseJavaUtilSetSingularizer extends EclipseJavaUtilListSetSingularizer {
    private static final char[] EMPTY_SORTED_SET = {'e', DateFormat.MINUTE, 'p', 't', 'y', 'S', 'o', 'r', 't', 'e', DateFormat.DATE, 'S', 'e', 't'};
    private static final char[] EMPTY_NAVIGABLE_SET = {'e', DateFormat.MINUTE, 'p', 't', 'y', PhoneNumberUtils.WILD, DateFormat.AM_PM, 'v', 'i', 'g', DateFormat.AM_PM, 'b', 'l', 'e', 'S', 'e', 't'};
    private static final char[] EMPTY_SET = {'e', DateFormat.MINUTE, 'p', 't', 'y', 'S', 'e', 't'};

    @Override // lombok.eclipse.handlers.singulars.EclipseJavaUtilListSetSingularizer, lombok.eclipse.handlers.EclipseSingularsRecipes.EclipseSingularizer
    public /* bridge */ /* synthetic */ List generateFields(EclipseSingularsRecipes.SingularData singularData, EclipseNode eclipseNode) {
        return super.generateFields(singularData, eclipseNode);
    }

    @Override // lombok.eclipse.handlers.singulars.EclipseJavaUtilListSetSingularizer, lombok.eclipse.handlers.EclipseSingularsRecipes.EclipseSingularizer
    public /* bridge */ /* synthetic */ void generateMethods(CheckerFrameworkVersion checkerFrameworkVersion, EclipseSingularsRecipes.SingularData singularData, boolean z, EclipseNode eclipseNode, boolean z2, EclipseSingularsRecipes.TypeReferenceMaker typeReferenceMaker, EclipseSingularsRecipes.StatementMaker statementMaker, AccessLevel accessLevel) {
        super.generateMethods(checkerFrameworkVersion, singularData, z, eclipseNode, z2, typeReferenceMaker, statementMaker, accessLevel);
    }

    @Override // lombok.eclipse.handlers.singulars.EclipseJavaUtilListSetSingularizer, lombok.eclipse.handlers.EclipseSingularsRecipes.EclipseSingularizer
    public /* bridge */ /* synthetic */ List listFieldsToBeGenerated(EclipseSingularsRecipes.SingularData singularData, EclipseNode eclipseNode) {
        return super.listFieldsToBeGenerated(singularData, eclipseNode);
    }

    @Override // lombok.eclipse.handlers.singulars.EclipseJavaUtilListSetSingularizer, lombok.eclipse.handlers.EclipseSingularsRecipes.EclipseSingularizer
    public /* bridge */ /* synthetic */ List listMethodsToBeGenerated(EclipseSingularsRecipes.SingularData singularData, EclipseNode eclipseNode) {
        return super.listMethodsToBeGenerated(singularData, eclipseNode);
    }

    @Override // lombok.eclipse.handlers.EclipseSingularsRecipes.EclipseSingularizer
    public LombokImmutableList<String> getSupportedTypes() {
        return LombokImmutableList.of("java.util.Set", "java.util.SortedSet", "java.util.NavigableSet");
    }

    @Override // lombok.eclipse.handlers.EclipseSingularsRecipes.EclipseSingularizer
    protected char[][] getEmptyMakerReceiver(String targetFqn) {
        return JAVA_UTIL_COLLECTIONS;
    }

    @Override // lombok.eclipse.handlers.EclipseSingularsRecipes.EclipseSingularizer
    protected char[] getEmptyMakerSelector(String targetFqn) {
        return targetFqn.endsWith("SortedSet") ? EMPTY_SORTED_SET : targetFqn.endsWith("NavigableSet") ? EMPTY_NAVIGABLE_SET : EMPTY_SET;
    }

    @Override // lombok.eclipse.handlers.EclipseSingularsRecipes.EclipseSingularizer
    public void appendBuildCode(EclipseSingularsRecipes.SingularData data, EclipseNode builderType, List<Statement> statements, char[] targetVariableName, String builderVariable) {
        if (useGuavaInstead(builderType)) {
            this.guavaListSetSingularizer.appendBuildCode(data, builderType, statements, targetVariableName, builderVariable);
        } else if (data.getTargetFqn().equals("java.util.Set")) {
            statements.addAll(createJavaUtilSetMapInitialCapacitySwitchStatements(data, builderType, false, "emptySet", "singleton", "LinkedHashSet", builderVariable));
        } else {
            statements.addAll(createJavaUtilSimpleCreationAndFillStatements(data, builderType, false, true, false, true, "TreeSet", builderVariable));
        }
    }
}
