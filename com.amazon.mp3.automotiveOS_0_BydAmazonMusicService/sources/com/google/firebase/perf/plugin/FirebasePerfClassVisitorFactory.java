package com.google.firebase.perf.plugin;

import com.android.build.api.instrumentation.AsmClassVisitorFactory;
import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;
import com.android.build.api.instrumentation.FramesComputationMode;
import com.android.build.api.instrumentation.InstrumentationParameters;
import com.android.build.api.instrumentation.InstrumentationScope;
import com.android.build.api.variant.AndroidComponentsExtension;
import com.android.build.api.variant.ApplicationVariant;
import com.google.firebase.perf.plugin.instrumentation.Instrument;
import com.google.firebase.perf.plugin.instrumentation.InstrumentationConfigFactory;
import com.google.firebase.perf.plugin.instrumentation.InstrumentationVisitor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Project;
import org.objectweb.asm.ClassVisitor;

/* JADX INFO: loaded from: classes6.dex */
public abstract class FirebasePerfClassVisitorFactory implements AsmClassVisitorFactory<InstrumentationParameters.None> {
    private static String REMOVED_ANDROID_COMPONENTS_EXTENSION_CLASS = "com.android.build.api.extension.AndroidComponentsExtension";
    private static String REMOVED_VARIANT_SELECTOR_CLASS = "com.android.build.api.extension.VariantSelector";

    @Nonnull
    public ClassVisitor createClassVisitor(@Nonnull ClassContext classContext, @Nonnull ClassVisitor classVisitor) {
        return new InstrumentationVisitor(((Integer) getInstrumentationContext().getApiVersion().get()).intValue(), classVisitor, new InstrumentationConfigFactory().newClassDataBasedInstrumentationConfig(classContext));
    }

    public boolean isInstrumentable(@Nonnull ClassData classData) {
        return Instrument.isClassInstrumentable(classData.getClassName());
    }

    public static void registerForProject(Project project, final InstrumentationFlagState instrumentationFlagState, final boolean z) {
        AndroidComponentsExtension androidComponentsExtension = (AndroidComponentsExtension) project.getExtensions().getByType(AndroidComponentsExtension.class);
        androidComponentsExtension.onVariants(androidComponentsExtension.selector().all(), new Action() { // from class: com.google.firebase.perf.plugin.FirebasePerfClassVisitorFactory$$ExternalSyntheticLambda1
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.gradle.api.GradleException */
    public static void registerForProjectWithDeprecatedVariantApi(Project project, final InstrumentationFlagState instrumentationFlagState) throws GradleException, ClassNotFoundException {
        try {
            Class<?> clsLoadClass = FirebasePerfClassVisitorFactory.class.getClassLoader().loadClass(REMOVED_ANDROID_COMPONENTS_EXTENSION_CLASS);
            Class<?> clsLoadClass2 = FirebasePerfClassVisitorFactory.class.getClassLoader().loadClass(REMOVED_VARIANT_SELECTOR_CLASS);
            Object byType = project.getExtensions().getByType(clsLoadClass);
            Object objInvoke = clsLoadClass.getMethod("selector", new Class[0]).invoke(byType, new Object[0]);
            clsLoadClass.getMethod("onVariants", clsLoadClass2, Function1.class).invoke(byType, objInvoke.getClass().getMethod("all", new Class[0]).invoke(objInvoke, new Object[0]), new Function1() { // from class: com.google.firebase.perf.plugin.FirebasePerfClassVisitorFactory$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return FirebasePerfClassVisitorFactory.lambda$registerForProjectWithDeprecatedVariantApi$1(instrumentationFlagState, (ApplicationVariant) obj);
                }
            });
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new GradleException("perf-plugin failed to register for project with AGP version 4.2.*: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Unit lambda$registerForProjectWithDeprecatedVariantApi$1(InstrumentationFlagState instrumentationFlagState, ApplicationVariant applicationVariant) {
        registerForVariant(applicationVariant, instrumentationFlagState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void registerForVariant(ApplicationVariant applicationVariant, InstrumentationFlagState instrumentationFlagState, boolean z) {
        if (instrumentationFlagState.isEnabledFor(applicationVariant.getName(), applicationVariant.getBuildType(), (List) applicationVariant.getProductFlavors().stream().map(new Function() { // from class: com.google.firebase.perf.plugin.FirebasePerfClassVisitorFactory$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FirebasePerfClassVisitorFactory.lambda$registerForVariant$2((Pair) obj);
            }
        }).collect(Collectors.toList()))) {
            if (z) {
                applicationVariant.getInstrumentation().transformClassesWith(FirebasePerfClassVisitorFactory.class, InstrumentationScope.ALL, new Function1() { // from class: com.google.firebase.perf.plugin.FirebasePerfClassVisitorFactory$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return FirebasePerfClassVisitorFactory.lambda$registerForVariant$3((InstrumentationParameters.None) obj);
                    }
                });
                applicationVariant.getInstrumentation().setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_CLASSES);
            } else {
                applicationVariant.transformClassesWith(FirebasePerfClassVisitorFactory.class, InstrumentationScope.ALL, new Function1() { // from class: com.google.firebase.perf.plugin.FirebasePerfClassVisitorFactory$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return FirebasePerfClassVisitorFactory.lambda$registerForVariant$4((InstrumentationParameters.None) obj);
                    }
                });
                applicationVariant.setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_CLASSES);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$registerForVariant$2(Pair pair) {
        return (String) pair.getSecond();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Unit lambda$registerForVariant$3(InstrumentationParameters.None none) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Unit lambda$registerForVariant$4(InstrumentationParameters.None none) {
        return Unit.INSTANCE;
    }
}
