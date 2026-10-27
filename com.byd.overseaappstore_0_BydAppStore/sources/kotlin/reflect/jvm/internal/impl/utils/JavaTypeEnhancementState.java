package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.d;

/* JADX INFO: loaded from: classes2.dex */
public final class JavaTypeEnhancementState {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final JavaTypeEnhancementState DEFAULT;

    @JvmField
    @NotNull
    public static final ReportLevel DEFAULT_REPORT_LEVEL_FOR_JSPECIFY;

    @JvmField
    @NotNull
    public static final JavaTypeEnhancementState DISABLED_JSR_305;

    @JvmField
    @NotNull
    public static final JavaTypeEnhancementState STRICT;

    @NotNull
    private final Lazy description$delegate;
    private final boolean disabledDefaultAnnotations;
    private final boolean disabledJsr305;
    private final boolean enableCompatqualCheckerFrameworkAnnotations;

    @NotNull
    private final ReportLevel globalJsr305Level;

    @NotNull
    private final ReportLevel jspecifyReportLevel;

    @Nullable
    private final ReportLevel migrationLevelForJsr305;

    @NotNull
    private final Map<String, ReportLevel> userDefinedLevelForSpecificJsr305Annotation;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        ReportLevel reportLevel = ReportLevel.WARN;
        DEFAULT_REPORT_LEVEL_FOR_JSPECIFY = reportLevel;
        DEFAULT = new JavaTypeEnhancementState(reportLevel, null, MapsKt.emptyMap(), false, null, 24, null);
        ReportLevel reportLevel2 = ReportLevel.IGNORE;
        DISABLED_JSR_305 = new JavaTypeEnhancementState(reportLevel2, reportLevel2, MapsKt.emptyMap(), false, null, 24, null);
        ReportLevel reportLevel3 = ReportLevel.STRICT;
        STRICT = new JavaTypeEnhancementState(reportLevel3, reportLevel3, MapsKt.emptyMap(), false, null, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JavaTypeEnhancementState(@NotNull ReportLevel globalJsr305Level, @Nullable ReportLevel reportLevel, @NotNull Map<String, ? extends ReportLevel> userDefinedLevelForSpecificJsr305Annotation, boolean z, @NotNull ReportLevel jspecifyReportLevel) {
        Intrinsics.checkNotNullParameter(globalJsr305Level, "globalJsr305Level");
        Intrinsics.checkNotNullParameter(userDefinedLevelForSpecificJsr305Annotation, "userDefinedLevelForSpecificJsr305Annotation");
        Intrinsics.checkNotNullParameter(jspecifyReportLevel, "jspecifyReportLevel");
        this.globalJsr305Level = globalJsr305Level;
        this.migrationLevelForJsr305 = reportLevel;
        this.userDefinedLevelForSpecificJsr305Annotation = userDefinedLevelForSpecificJsr305Annotation;
        this.enableCompatqualCheckerFrameworkAnnotations = z;
        this.jspecifyReportLevel = jspecifyReportLevel;
        this.description$delegate = LazyKt.lazy(new Function0<String[]>() { // from class: kotlin.reflect.jvm.internal.impl.utils.JavaTypeEnhancementState$description$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final String[] invoke() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.this$0.getGlobalJsr305Level().getDescription());
                ReportLevel migrationLevelForJsr305 = this.this$0.getMigrationLevelForJsr305();
                if (migrationLevelForJsr305 != null) {
                    arrayList.add(Intrinsics.stringPlus("under-migration:", migrationLevelForJsr305.getDescription()));
                }
                for (Map.Entry<String, ReportLevel> entry : this.this$0.getUserDefinedLevelForSpecificJsr305Annotation().entrySet()) {
                    StringBuilder sbI = d.i('@');
                    sbI.append(entry.getKey());
                    sbI.append(':');
                    sbI.append(entry.getValue().getDescription());
                    arrayList.add(sbI.toString());
                }
                Object[] array = arrayList.toArray(new String[0]);
                Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
                return (String[]) array;
            }
        });
        ReportLevel reportLevel2 = ReportLevel.IGNORE;
        boolean z2 = true;
        boolean z3 = globalJsr305Level == reportLevel2 && reportLevel == reportLevel2 && userDefinedLevelForSpecificJsr305Annotation.isEmpty();
        this.disabledJsr305 = z3;
        if (!z3 && jspecifyReportLevel != reportLevel2) {
            z2 = false;
        }
        this.disabledDefaultAnnotations = z2;
    }

    public /* synthetic */ JavaTypeEnhancementState(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, boolean z, ReportLevel reportLevel3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reportLevel, reportLevel2, map, (i & 8) != 0 ? true : z, (i & 16) != 0 ? DEFAULT_REPORT_LEVEL_FOR_JSPECIFY : reportLevel3);
    }

    public final boolean getDisabledDefaultAnnotations() {
        return this.disabledDefaultAnnotations;
    }

    public final boolean getDisabledJsr305() {
        return this.disabledJsr305;
    }

    public final boolean getEnableCompatqualCheckerFrameworkAnnotations() {
        return this.enableCompatqualCheckerFrameworkAnnotations;
    }

    @NotNull
    public final ReportLevel getGlobalJsr305Level() {
        return this.globalJsr305Level;
    }

    @NotNull
    public final ReportLevel getJspecifyReportLevel() {
        return this.jspecifyReportLevel;
    }

    @Nullable
    public final ReportLevel getMigrationLevelForJsr305() {
        return this.migrationLevelForJsr305;
    }

    @NotNull
    public final Map<String, ReportLevel> getUserDefinedLevelForSpecificJsr305Annotation() {
        return this.userDefinedLevelForSpecificJsr305Annotation;
    }
}
