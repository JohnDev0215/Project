package com.google.firebase.perf.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.internal.dsl.BuildType;
import com.android.builder.model.ProductFlavor;
import com.google.common.base.Ascii;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes6.dex */
public final class InstrumentationFlagState implements Serializable {
    private static final boolean INSTRUMENTATION_ENABLED_DEFAULT = true;
    private static final Logger logger = FirebasePerfPlugin.getLogger();
    private final transient AppExtension androidExt;
    private final Optional<Boolean> parsedProjectPropertyValue;
    private final Map<String, Boolean> variantToInstrumentationEnabledMap = new HashMap();

    public InstrumentationFlagState(Project project) {
        AppExtension appExtension = (AppExtension) project.getExtensions().getByType(AppExtension.class);
        this.androidExt = appExtension;
        this.parsedProjectPropertyValue = fetchProjectPropertyValue(project);
        appExtension.getApplicationVariants().all(new Action() { // from class: com.google.firebase.perf.plugin.InstrumentationFlagState$$ExternalSyntheticLambda0
        });
    }

    private static Optional<Boolean> fetchProjectPropertyValue(Project project) {
        if (!project.hasProperty(FirebasePerfPlugin.FIREBASE_PERF_INSTRUMENTATION_ENABLED_KEY) || project.property(FirebasePerfPlugin.FIREBASE_PERF_INSTRUMENTATION_ENABLED_KEY) == null) {
            return Optional.empty();
        }
        Object objProperty = project.property(FirebasePerfPlugin.FIREBASE_PERF_INSTRUMENTATION_ENABLED_KEY);
        Objects.requireNonNull(objProperty);
        String string = objProperty.toString();
        Optional<Boolean> optional = parseBoolean(string);
        if (optional.isPresent()) {
            return optional;
        }
        throw new IllegalStateException(String.format("Could not get unknown value '%s' for the project property '%s' defined in the 'gradle.properties' file. Correct format is either '%s=false' or '%s=true'.", string, FirebasePerfPlugin.FIREBASE_PERF_INSTRUMENTATION_ENABLED_KEY, FirebasePerfPlugin.FIREBASE_PERF_INSTRUMENTATION_ENABLED_KEY, FirebasePerfPlugin.FIREBASE_PERF_INSTRUMENTATION_ENABLED_KEY));
    }

    private static Optional<Boolean> parseBoolean(String str) {
        if (str != null && (Ascii.equalsIgnoreCase(str, "true") || Ascii.equalsIgnoreCase(str, "false"))) {
            return Optional.of(Boolean.valueOf(Boolean.parseBoolean(str)));
        }
        return Optional.empty();
    }

    private void updateInstrumentationEnabledFor(ApplicationVariant applicationVariant) {
        String name = applicationVariant.getName();
        ArrayList arrayList = new ArrayList();
        Iterator it = applicationVariant.getProductFlavors().iterator();
        while (it.hasNext()) {
            arrayList.add(((ProductFlavor) it.next()).getName());
        }
        this.variantToInstrumentationEnabledMap.put(name, Boolean.valueOf(instrumentationEnabledFor(name, applicationVariant.getBuildType().getName(), arrayList)));
    }

    private boolean instrumentationEnabledFor(String str, String str2, List<String> list) {
        if (this.parsedProjectPropertyValue.isPresent()) {
            Logger logger2 = logger;
            Object[] objArr = new Object[1];
            objArr[0] = this.parsedProjectPropertyValue.get().booleanValue() ? "enabled" : "disabled";
            logger2.info(String.format("Firebase Performance Instrumentation is %s per the Project Property specified in the 'gradle.properties' file.", objArr));
            return this.parsedProjectPropertyValue.get().booleanValue();
        }
        Optional<Boolean> buildTypeExtensionValue = getBuildTypeExtensionValue(str2);
        if (buildTypeExtensionValue.isPresent()) {
            Logger logger3 = logger;
            Object[] objArr2 = new Object[3];
            objArr2[0] = buildTypeExtensionValue.get().booleanValue() ? "enabled" : "disabled";
            objArr2[1] = str;
            objArr2[2] = str2;
            logger3.info(String.format("Firebase Performance Instrumentation is %s for %s variant per the Extension Property specified (for buildType=%s) in the 'build.gradle' file.", objArr2));
            return buildTypeExtensionValue.get().booleanValue();
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Optional<Boolean> productFlavorExtensionValue = getProductFlavorExtensionValue(it.next());
            if (productFlavorExtensionValue.isPresent()) {
                Logger logger4 = logger;
                Object[] objArr3 = new Object[3];
                objArr3[0] = productFlavorExtensionValue.get().booleanValue() ? "enabled" : "disabled";
                objArr3[1] = str;
                objArr3[2] = list;
                logger4.info(String.format("Firebase Performance Instrumentation is %s for %s variant per the Extension Property specified (for flavors=%s) in the 'build.gradle' file.", objArr3));
                return productFlavorExtensionValue.get().booleanValue();
            }
        }
        logger.info(String.format("Firebase Performance Instrumentation is %s by default for %s variant.", "enabled", str));
        return true;
    }

    private Optional<Boolean> getBuildTypeExtensionValue(String str) {
        FirebasePerfExtension firebasePerfExtension = (FirebasePerfExtension) ((BuildType) this.androidExt.getBuildTypes().getByName(str)).getExtensions().getByType(FirebasePerfExtension.class);
        return firebasePerfExtension != null ? firebasePerfExtension.isInstrumentationEnabled() : Optional.empty();
    }

    private Optional<Boolean> getProductFlavorExtensionValue(String str) {
        FirebasePerfExtension firebasePerfExtension = (FirebasePerfExtension) ((com.android.build.gradle.internal.dsl.ProductFlavor) this.androidExt.getProductFlavors().getByName(str)).getExtensions().getByType(FirebasePerfExtension.class);
        return firebasePerfExtension != null ? firebasePerfExtension.isInstrumentationEnabled() : Optional.empty();
    }

    public Optional<Boolean> getProjectPropertyValue() {
        return this.parsedProjectPropertyValue;
    }

    public boolean isEnabledFor(String str) {
        return this.variantToInstrumentationEnabledMap.getOrDefault(str, Boolean.TRUE).booleanValue();
    }

    public boolean isEnabledFor(String str, String str2, List<String> list) {
        return instrumentationEnabledFor(str, str2, list);
    }

    public Map<String, Boolean> getVariantToInstrumentationEnabledMap() {
        return this.variantToInstrumentationEnabledMap;
    }
}
