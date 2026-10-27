package com.google.firebase.perf.plugin;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Status;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.api.variant.VariantInfo;
import com.android.utils.FileUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Files;
import com.google.firebase.perf.plugin.instrumentation.Instrument;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.gradle.api.provider.Provider;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes6.dex */
public class FirebasePerfTransform extends Transform {
    private static final Logger logger = FirebasePerfPlugin.getLogger();
    private final Provider<List<File>> bootClasspathProvider;
    private Instrument firebasePerfInstrumentation;
    private final InstrumentationFlagState instrumentationFlagState;
    private boolean applyToVariantUsed = false;
    private final Set<QualifiedContent.ContentType> typeClasses = ImmutableSet.of(QualifiedContent.DefaultContentType.CLASSES);
    private final Set<QualifiedContent.Scope> scopes = ImmutableSet.of(QualifiedContent.Scope.EXTERNAL_LIBRARIES, QualifiedContent.Scope.PROJECT, QualifiedContent.Scope.SUB_PROJECTS);

    public String getName() {
        return FirebasePerfPlugin.FIREBASE_PERF_TAG;
    }

    public boolean isIncremental() {
        return true;
    }

    public FirebasePerfTransform(InstrumentationFlagState instrumentationFlagState, Provider<List<File>> provider) {
        this.instrumentationFlagState = instrumentationFlagState;
        this.bootClasspathProvider = provider;
    }

    public Set<QualifiedContent.ContentType> getInputTypes() {
        return this.typeClasses;
    }

    public Set<QualifiedContent.Scope> getScopes() {
        return this.scopes;
    }

    public boolean applyToVariant(VariantInfo variantInfo) {
        this.applyToVariantUsed = true;
        boolean zIsEnabledFor = this.instrumentationFlagState.isEnabledFor(variantInfo.getFullVariantName(), variantInfo.getBuildTypeName(), variantInfo.getFlavorNames());
        logger.info(String.format("applyToVariant(%s): %s", variantInfo, Boolean.valueOf(zIsEnabledFor)));
        return zIsEnabledFor;
    }

    public Map<String, Object> getParameterInputs() {
        return ImmutableMap.copyOf((Map) this.instrumentationFlagState.getVariantToInstrumentationEnabledMap());
    }

    public void transform(TransformInvocation transformInvocation) throws IOException {
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        Collection<TransformInput> referencedInputs = transformInvocation.getReferencedInputs();
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        boolean zIsIncremental = transformInvocation.isIncremental();
        boolean z = this.applyToVariantUsed || this.instrumentationFlagState.isEnabledFor(transformInvocation.getContext().getVariantName());
        Logger logger2 = logger;
        logger2.info("Executing transform for buildVariant: {}; instrumentationEnabled: {}, applyToVariantUsed: {}", transformInvocation.getContext().getVariantName(), Boolean.valueOf(z), Boolean.valueOf(this.applyToVariantUsed));
        List<URL> listBuildRuntimeClasspath = buildRuntimeClasspath(inputs, referencedInputs);
        logger2.debug("Effective app classpath at runtime:");
        for (URL url : listBuildRuntimeClasspath) {
            logger.debug("- " + url);
        }
        URLClassLoader uRLClassLoader = new URLClassLoader((URL[]) listBuildRuntimeClasspath.toArray(new URL[0]));
        try {
            logger.info("Transforming with incremental: {}", Boolean.valueOf(zIsIncremental));
            if (!zIsIncremental) {
                outputProvider.deleteAll();
            }
            this.firebasePerfInstrumentation = new Instrument(uRLClassLoader);
            for (TransformInput transformInput : inputs) {
                transformDirectoryInputs(transformInput, outputProvider, zIsIncremental, z);
                transformJarInputs(transformInput, outputProvider, zIsIncremental, z);
            }
            uRLClassLoader.close();
        } catch (Throwable th) {
            try {
                uRLClassLoader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void transformDirectoryInputs(TransformInput transformInput, TransformOutputProvider transformOutputProvider, boolean z, boolean z2) throws IOException {
        for (DirectoryInput directoryInput : transformInput.getDirectoryInputs()) {
            File file = directoryInput.getFile();
            File contentLocation = transformOutputProvider.getContentLocation(directoryInput.getName(), directoryInput.getContentTypes(), directoryInput.getScopes(), Format.DIRECTORY);
            logger.debug("transformDirectoryInputs() >> inputDir: '{}', outputDir: '{}'", file, contentLocation);
            if (z2) {
                performTransformationForDirectoryInput(directoryInput, file, contentLocation, z);
            } else {
                performDummyTransformationForDirectoryInput(file, contentLocation);
            }
        }
    }

    private void performTransformationForDirectoryInput(DirectoryInput directoryInput, File file, File file2, boolean z) throws IOException {
        if (z) {
            for (Map.Entry entry : directoryInput.getChangedFiles().entrySet()) {
                File file3 = (File) entry.getKey();
                Status status = (Status) entry.getValue();
                logger.debug("performTransformationForDirectoryInput() >> inputFile: '{}', incrementalStatus: {}", file3, status);
                int i = AnonymousClass1.$SwitchMap$com$android$build$api$transform$Status[status.ordinal()];
                if (i == 2 || i == 3) {
                    transformFile(file3, file, file2);
                } else if (i == 4) {
                    FileUtils.deleteIfExists(toOutputFile(file2, file, file3));
                }
            }
            return;
        }
        Iterator<E> it = FileUtils.getAllFiles(file).iterator();
        while (it.hasNext()) {
            transformFile((File) it.next(), file, file2);
        }
    }

    /* JADX INFO: renamed from: com.google.firebase.perf.plugin.FirebasePerfTransform$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$build$api$transform$Status;

        static {
            int[] iArr = new int[Status.values().length];
            $SwitchMap$com$android$build$api$transform$Status = iArr;
            try {
                iArr[Status.NOTCHANGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$build$api$transform$Status[Status.ADDED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$build$api$transform$Status[Status.CHANGED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$build$api$transform$Status[Status.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void transformFile(File file, File file2, File file3) throws IOException {
        if (file.isDirectory() || !file.getName().endsWith(".class")) {
            return;
        }
        File outputFile = toOutputFile(file3, file2, file);
        Files.createParentDirs(outputFile);
        this.firebasePerfInstrumentation.instrumentClassFile(file, outputFile);
    }

    private void performDummyTransformationForDirectoryInput(File file, File file2) throws IOException {
        if (file2.mkdirs() || file2.isDirectory()) {
            org.apache.commons.io.FileUtils.deleteDirectory(file2);
            FileUtils.copyDirectory(file, file2);
        }
    }

    private void transformJarInputs(TransformInput transformInput, TransformOutputProvider transformOutputProvider, boolean z, boolean z2) throws IOException {
        for (JarInput jarInput : transformInput.getJarInputs()) {
            File file = jarInput.getFile();
            File contentLocation = transformOutputProvider.getContentLocation(jarInput.getName(), jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR);
            logger.debug("transformJarInputs() >> inputJar: '{}', outputJar: '{}'", file, contentLocation);
            if (z2) {
                performTransformationForJarInput(jarInput, file, contentLocation, z);
            } else {
                performDummyTransformationForJarInput(file, contentLocation);
            }
        }
    }

    private void performTransformationForJarInput(JarInput jarInput, File file, File file2, boolean z) throws IOException {
        if (z) {
            Status status = jarInput.getStatus();
            logger.debug("performTransformationForJarInput() >> inputJar: '{}', incrementalStatus: {}", file, status);
            int i = AnonymousClass1.$SwitchMap$com$android$build$api$transform$Status[status.ordinal()];
            if (i == 2 || i == 3) {
                transformJar(file, file2);
                return;
            } else {
                if (i != 4) {
                    return;
                }
                FileUtils.deleteIfExists(file2);
                return;
            }
        }
        transformJar(file, file2);
    }

    private void transformJar(File file, File file2) throws IOException {
        Files.createParentDirs(file2);
        this.firebasePerfInstrumentation.instrumentClassesInJar(file, file2);
    }

    private void performDummyTransformationForJarInput(File file, File file2) throws IOException {
        Files.createParentDirs(file2);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                IOUtils.copy(fileInputStream, fileOutputStream);
                fileOutputStream.close();
                fileInputStream.close();
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static File toOutputFile(File file, File file2, File file3) {
        return new File(file, FileUtils.relativePossiblyNonExistingPath(file3, file2));
    }

    private List<URL> buildRuntimeClasspath(Collection<TransformInput> collection, Collection<TransformInput> collection2) {
        ArrayList arrayList = new ArrayList((Collection) this.bootClasspathProvider.get());
        Iterator it = Arrays.asList(collection, collection2).iterator();
        while (it.hasNext()) {
            for (TransformInput transformInput : (Collection) it.next()) {
                Iterator it2 = Arrays.asList(transformInput.getDirectoryInputs(), transformInput.getJarInputs()).iterator();
                while (it2.hasNext()) {
                    Iterator it3 = ((Collection) it2.next()).iterator();
                    while (it3.hasNext()) {
                        arrayList.add(((QualifiedContent) it3.next()).getFile());
                    }
                }
            }
        }
        return (List) arrayList.stream().map(new Function() { // from class: com.google.firebase.perf.plugin.FirebasePerfTransform$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FirebasePerfTransform.lambda$buildRuntimeClasspath$0((File) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ URL lambda$buildRuntimeClasspath$0(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            logger.error("Unable to instrument classes due to file '{}'", file);
            throw new RuntimeException(e);
        }
    }
}
