package com.google.firebase.perf.plugin;

import androidx.core.os.EnvironmentCompat;
import com.android.Version;
import com.android.build.gradle.AppExtension;
import com.android.ide.common.repository.GradleVersion;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.Callable;
import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.AppliedPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class FirebasePerfPlugin implements Plugin<Project> {
    public static final String AGP_NEW_INSTRUMENTATION_API_VERSION = "7.2.0";
    public static final String AGP_NEW_VARIANT_API_VERSION = "7.0.0";
    public static final String FIREBASE_PERF_EXTENSION_NAME = "FirebasePerformance";
    public static final String FIREBASE_PERF_INSTRUMENTATION_ENABLED_KEY = "firebasePerformanceInstrumentationEnabled";
    public static final String MINIMUM_SUPPORTED_AGP_VERSION_FOR_NEW_TRANSFORM_API = "4.2.0-rc01";
    private boolean foundApplicationPlugin = false;
    public static final String FIREBASE_PERF_TAG = "FirebasePerformancePlugin";
    private static final Logger logger = LoggerFactory.getLogger(FIREBASE_PERF_TAG);
    private static boolean instrumentFireperfE2ETest = false;

    public void apply(final Project project) {
        instrumentFireperfE2ETest = project.getProperties().containsKey("instrumentFireperfE2ETest") && project.getProperties().get("instrumentFireperfE2ETest").equals("true");
        project.getPluginManager().withPlugin("com.android.application", new Action() { // from class: com.google.firebase.perf.plugin.FirebasePerfPlugin$$ExternalSyntheticLambda1
        });
        project.afterEvaluate(new Action() { // from class: com.google.firebase.perf.plugin.FirebasePerfPlugin$$ExternalSyntheticLambda2
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.gradle.api.GradleException */
    private /* synthetic */ void lambda$apply$0(Project project, AppliedPlugin appliedPlugin) throws GradleException {
        this.foundApplicationPlugin = true;
        try {
            perform(project);
        } catch (ClassNotFoundException e) {
            logger.error("Perform performance plugin failed: class not found " + e.getMessage());
        }
    }

    private /* synthetic */ void lambda$apply$1(Project project) {
        if (!this.foundApplicationPlugin) {
            throw new IllegalStateException("FirebasePerformancePlugin must only be used with Android application projects. Need to apply the 'com.android.application' plugin with this plugin.");
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.gradle.api.GradleException */
    private void perform(Project project) throws GradleException, ClassNotFoundException {
        final AppExtension appExtension = (AppExtension) project.getExtensions().getByType(AppExtension.class);
        registerExtension(appExtension);
        InstrumentationFlagState instrumentationFlagState = new InstrumentationFlagState(project);
        Optional<Boolean> projectPropertyValue = instrumentationFlagState.getProjectPropertyValue();
        if (projectPropertyValue.isPresent() && Boolean.FALSE.equals(projectPropertyValue.get())) {
            logger.info(String.format("%s is disabled globally for the project by specifying '%s=false' flag in the 'gradle.properties' file.", FIREBASE_PERF_TAG, FIREBASE_PERF_INSTRUMENTATION_ENABLED_KEY));
            return;
        }
        if (useNewAgpTransformApi()) {
            if (useNewVariantApi()) {
                FirebasePerfClassVisitorFactory.registerForProject(project, instrumentationFlagState, useInstrumentationApi());
                return;
            } else {
                FirebasePerfClassVisitorFactory.registerForProjectWithDeprecatedVariantApi(project, instrumentationFlagState);
                return;
            }
        }
        Objects.requireNonNull(appExtension);
        appExtension.registerTransform(new FirebasePerfTransform(instrumentationFlagState, project.provider(new Callable() { // from class: com.google.firebase.perf.plugin.FirebasePerfPlugin$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return appExtension.getBootClasspath();
            }
        })), new Object[0]);
    }

    private void registerExtension(AppExtension appExtension) {
        appExtension.getBuildTypes().all(new Action() { // from class: com.google.firebase.perf.plugin.FirebasePerfPlugin$$ExternalSyntheticLambda3
        });
        appExtension.getProductFlavors().all(new Action() { // from class: com.google.firebase.perf.plugin.FirebasePerfPlugin$$ExternalSyntheticLambda3
        });
    }

    public static String getPluginVersion() {
        return getPluginVersion(FirebasePerfPlugin.class.getClassLoader(), "com/google/firebase/perf/plugin/project.properties");
    }

    static String getPluginVersion(ClassLoader classLoader, String str) {
        Properties properties = new Properties();
        if (classLoader != null) {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(classLoader.getResourceAsStream(str));
                try {
                    properties.load(bufferedInputStream);
                    bufferedInputStream.close();
                } finally {
                }
            } catch (IOException e) {
                logger.warn(String.format("Could not load '%s' file.", str), (Throwable) e);
            }
        }
        return properties.getProperty("pluginVersion", EnvironmentCompat.MEDIA_UNKNOWN);
    }

    private boolean useNewAgpTransformApi() {
        try {
            return GradleVersion.parseAndroidGradlePluginVersion(Version.ANDROID_GRADLE_PLUGIN_VERSION).compareTo(GradleVersion.parseAndroidGradlePluginVersion(MINIMUM_SUPPORTED_AGP_VERSION_FOR_NEW_TRANSFORM_API)) >= 0;
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
            return false;
        }
    }

    private boolean useNewVariantApi() {
        try {
            return GradleVersion.parseAndroidGradlePluginVersion(Version.ANDROID_GRADLE_PLUGIN_VERSION).compareTo(GradleVersion.parseAndroidGradlePluginVersion(AGP_NEW_VARIANT_API_VERSION)) >= 0;
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
            return false;
        }
    }

    private boolean useInstrumentationApi() {
        try {
            return GradleVersion.parseAndroidGradlePluginVersion(Version.ANDROID_GRADLE_PLUGIN_VERSION).compareTo(GradleVersion.parseAndroidGradlePluginVersion(AGP_NEW_INSTRUMENTATION_API_VERSION)) >= 0;
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
            return false;
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static boolean getInstrumentFireperfE2ETest() {
        return instrumentFireperfE2ETest;
    }
}
