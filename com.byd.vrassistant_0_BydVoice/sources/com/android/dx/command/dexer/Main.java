package com.android.dx.command.dexer;

import com.android.dex.Dex;
import com.android.dex.DexException;
import com.android.dex.DexFormat;
import com.android.dex.util.FileUtils;
import com.android.dx.cf.code.SimException;
import com.android.dx.cf.direct.ClassPathOpener;
import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.direct.StdAttributeFactory;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.command.DxConsole;
import com.android.dx.command.UsageException;
import com.android.dx.dex.DexOptions;
import com.android.dx.dex.cf.CfOptions;
import com.android.dx.dex.cf.CfTranslator;
import com.android.dx.dex.cf.CodeStatistics;
import com.android.dx.dex.file.ClassDefItem;
import com.android.dx.dex.file.DexFile;
import com.android.dx.dex.file.EncodedMethod;
import com.android.dx.merge.CollisionPolicy;
import com.android.dx.merge.DexMerger;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.cst.CstString;
import com.android.multidex.ClassPathElement;
import com.byd.mediacenter.api.media.BuildConfig;
import io.reactivex.rxjava3.annotations.SchedulerSupport;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes.dex */
public class Main {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String DEX_EXTENSION = ".dex";
    private static final String DEX_PREFIX = "classes";
    private static final String IN_RE_CORE_CLASSES = "Ill-advised or mistaken usage of a core class (java.* or javax.*)\nwhen not building a core library.\n\nThis is often due to inadvertently including a core library file\nin your application's project, when using an IDE (such as\nEclipse). If you are sure you're not intentionally defining a\ncore class, then this is the most likely explanation of what's\ngoing on.\n\nHowever, you might actually be trying to define a class in a core\nnamespace, the source of which you may have taken, for example,\nfrom a non-Android virtual machine project. This will most\nassuredly not work. At a minimum, it jeopardizes the\ncompatibility of your app with future versions of the platform.\nIt is also often of questionable legality.\n\nIf you really intend to build a core library -- which is only\nappropriate as part of creating a full virtual machine\ndistribution, as opposed to compiling an application -- then use\nthe \"--core-library\" option to suppress this error message.\n\nIf you go ahead and use \"--core-library\" but are in fact\nbuilding an application, then be forewarned that your application\nwill still fail to build or run, at some point. Please be\nprepared for angry customers who find, for example, that your\napplication ceases to function once they upgrade their operating\nsystem. You will be to blame for this problem.\n\nIf you are legitimately using some code that happens to be in a\ncore package, then the easiest safe alternative you have is to\nrepackage that code. That is, move the classes in question into\nyour own package namespace. This means that they will never be in\nconflict with core system classes. JarJar is a tool that may help\nyou in this endeavor. If you find that you cannot do this, then\nthat is an indication that the path you are on will ultimately\nlead to pain, suffering, grief, and lamentation.\n";
    private static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
    private static final int MAX_FIELD_ADDED_DURING_DEX_CREATION = 9;
    private static final int MAX_METHOD_ADDED_DURING_DEX_CREATION = 2;
    private static volatile boolean anyFilesProcessed;
    private static Arguments args;
    private static ExecutorService classDefItemConsumer;
    private static ExecutorService classTranslatorPool;
    private static ExecutorService dexOutPool;
    private static DexFile outputDex;
    private static TreeMap<String, byte[]> outputResources;
    private static final Attributes.Name CREATED_BY = new Attributes.Name("Created-By");
    private static final String[] JAVAX_CORE = {"accessibility", "crypto", "imageio", "management", "naming", "net", "print", "rmi", "security", "sip", "sound", "sql", "swing", "transaction", "xml"};
    private static AtomicInteger errors = new AtomicInteger(0);
    private static final List<byte[]> libraryDexBuffers = new ArrayList();
    private static List<Future<Boolean>> addToDexFutures = new ArrayList();
    private static List<Future<byte[]>> dexOutputFutures = new ArrayList();
    private static Object dexRotationLock = new Object();
    private static int maxMethodIdsInProcess = 0;
    private static int maxFieldIdsInProcess = 0;
    private static long minimumFileAge = 0;
    private static Set<String> classesInMainDex = null;
    private static List<byte[]> dexOutputArrays = new ArrayList();
    private static OutputStreamWriter humanOutWriter = null;

    private Main() {
    }

    public static void main(String[] strArr) throws Throwable {
        Arguments arguments = new Arguments();
        arguments.parse(strArr);
        int iRun = run(arguments);
        if (iRun != 0) {
            System.exit(iRun);
        }
    }

    public static int run(Arguments arguments) throws IOException {
        OutputStream outputStreamOpenOutput;
        errors.set(0);
        libraryDexBuffers.clear();
        args = arguments;
        arguments.makeOptionsObjects();
        if (args.humanOutName != null) {
            outputStreamOpenOutput = openOutput(args.humanOutName);
            humanOutWriter = new OutputStreamWriter(outputStreamOpenOutput);
        } else {
            outputStreamOpenOutput = null;
        }
        try {
            if (args.multiDex) {
                return runMultiDex();
            }
            return runMonoDex();
        } finally {
            closeOutput(outputStreamOpenOutput);
        }
    }

    public static String getTooManyIdsErrorMessage() {
        return args.multiDex ? "The list of classes given in --main-dex-list is too big and does not fit in the main dex." : "You may try using --multi-dex option.";
    }

    private static int runMonoDex() throws IOException {
        File file;
        byte[] bArrWriteDex;
        if (!args.incremental) {
            file = null;
        } else {
            if (args.outName == null) {
                System.err.println("error: no incremental output name specified");
                return -1;
            }
            file = new File(args.outName);
            if (file.exists()) {
                minimumFileAge = file.lastModified();
            }
        }
        if (!processAllFiles()) {
            return 1;
        }
        if (args.incremental && !anyFilesProcessed) {
            return 0;
        }
        if (outputDex.isEmpty() && args.humanOutName == null) {
            bArrWriteDex = null;
        } else {
            bArrWriteDex = writeDex(outputDex);
            if (bArrWriteDex == null) {
                return 2;
            }
        }
        if (args.incremental) {
            bArrWriteDex = mergeIncremental(bArrWriteDex, file);
        }
        byte[] bArrMergeLibraryDexBuffers = mergeLibraryDexBuffers(bArrWriteDex);
        if (args.jarOutput) {
            outputDex = null;
            if (bArrMergeLibraryDexBuffers != null) {
                outputResources.put(DexFormat.DEX_IN_JAR_NAME, bArrMergeLibraryDexBuffers);
            }
            if (!createJar(args.outName)) {
                return 3;
            }
        } else if (bArrMergeLibraryDexBuffers != null && args.outName != null) {
            OutputStream outputStreamOpenOutput = openOutput(args.outName);
            outputStreamOpenOutput.write(bArrMergeLibraryDexBuffers);
            closeOutput(outputStreamOpenOutput);
        }
        return 0;
    }

    private static int runMultiDex() throws Throwable {
        if (args.mainDexListFile != null) {
            classesInMainDex = new HashSet();
            readPathsFromFile(args.mainDexListFile, classesInMainDex);
        }
        dexOutPool = Executors.newFixedThreadPool(args.numThreads);
        if (!processAllFiles()) {
            return 1;
        }
        if (!libraryDexBuffers.isEmpty()) {
            throw new DexException("Library dex files are not supported in multi-dex mode");
        }
        if (outputDex != null) {
            dexOutputFutures.add(dexOutPool.submit(new DexWriter(outputDex)));
            outputDex = null;
        }
        try {
            dexOutPool.shutdown();
            if (!dexOutPool.awaitTermination(600L, TimeUnit.SECONDS)) {
                throw new RuntimeException("Timed out waiting for dex writer threads.");
            }
            Iterator<Future<byte[]>> it = dexOutputFutures.iterator();
            while (it.hasNext()) {
                dexOutputArrays.add(it.next().get());
            }
            if (args.jarOutput) {
                for (int i = 0; i < dexOutputArrays.size(); i++) {
                    outputResources.put(getDexFileName(i), dexOutputArrays.get(i));
                }
                if (!createJar(args.outName)) {
                    return 3;
                }
            } else if (args.outName != null) {
                File file = new File(args.outName);
                for (int i2 = 0; i2 < dexOutputArrays.size(); i2++) {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(file, getDexFileName(i2)));
                    try {
                        fileOutputStream.write(dexOutputArrays.get(i2));
                        closeOutput(fileOutputStream);
                    } catch (Throwable th) {
                        closeOutput(fileOutputStream);
                        throw th;
                    }
                }
            }
            return 0;
        } catch (InterruptedException unused) {
            dexOutPool.shutdownNow();
            throw new RuntimeException("A dex writer thread has been interrupted.");
        } catch (Exception unused2) {
            dexOutPool.shutdownNow();
            throw new RuntimeException("Unexpected exception in dex writer thread");
        }
    }

    private static String getDexFileName(int i) {
        return i == 0 ? DexFormat.DEX_IN_JAR_NAME : DEX_PREFIX + (i + 1) + DEX_EXTENSION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readPathsFromFile(String str, Collection<String> collection) throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
            while (true) {
                try {
                    String line = bufferedReader2.readLine();
                    if (line != null) {
                        collection.add(fixPath(line));
                    } else {
                        bufferedReader2.close();
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static byte[] mergeIncremental(byte[] bArr, File file) throws IOException {
        Dex dex = bArr != null ? new Dex(bArr) : null;
        Dex dex2 = file.exists() ? new Dex(file) : null;
        if (dex == null && dex2 == null) {
            return null;
        }
        if (dex == null) {
            dex = dex2;
        } else if (dex2 != null) {
            dex = new DexMerger(new Dex[]{dex, dex2}, CollisionPolicy.KEEP_FIRST).merge();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        dex.writeTo(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] mergeLibraryDexBuffers(byte[] bArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (bArr != null) {
            arrayList.add(new Dex(bArr));
        }
        Iterator<byte[]> it = libraryDexBuffers.iterator();
        while (it.hasNext()) {
            arrayList.add(new Dex(it.next()));
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new DexMerger((Dex[]) arrayList.toArray(new Dex[arrayList.size()]), CollisionPolicy.FAIL).merge().getBytes();
    }

    private static boolean processAllFiles() {
        createDexFile();
        if (args.jarOutput) {
            outputResources = new TreeMap<>();
        }
        anyFilesProcessed = false;
        String[] strArr = args.fileNames;
        Arrays.sort(strArr);
        classTranslatorPool = new ThreadPoolExecutor(args.numThreads, args.numThreads, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue(args.numThreads * 2, true), new ThreadPoolExecutor.CallerRunsPolicy());
        classDefItemConsumer = Executors.newSingleThreadExecutor();
        try {
            if (args.mainDexListFile != null) {
                ClassPathOpener.FileNameFilter mainDexListFilter = args.strictNameCheck ? new MainDexListFilter() : new BestEffortMainDexListFilter();
                for (String str : strArr) {
                    processOne(str, mainDexListFilter);
                }
                if (dexOutputFutures.size() > 0) {
                    throw new DexException("Too many classes in --main-dex-list, main dex capacity exceeded");
                }
                if (args.minimalMainDex) {
                    synchronized (dexRotationLock) {
                        while (true) {
                            if (maxMethodIdsInProcess <= 0 && maxFieldIdsInProcess <= 0) {
                                break;
                            }
                            try {
                                dexRotationLock.wait();
                            } catch (InterruptedException unused) {
                            }
                        }
                    }
                    rotateDexFile();
                }
                for (String str2 : strArr) {
                    processOne(str2, new NotFilter(mainDexListFilter));
                }
            } else {
                for (String str3 : strArr) {
                    processOne(str3, ClassPathOpener.acceptAll);
                }
            }
        } catch (StopProcessing unused2) {
        }
        try {
            classTranslatorPool.shutdown();
            classTranslatorPool.awaitTermination(600L, TimeUnit.SECONDS);
            classDefItemConsumer.shutdown();
            classDefItemConsumer.awaitTermination(600L, TimeUnit.SECONDS);
            Iterator<Future<Boolean>> it = addToDexFutures.iterator();
            while (it.hasNext()) {
                try {
                    it.next().get();
                } catch (ExecutionException e) {
                    if (errors.incrementAndGet() < 10) {
                        if (args.debug) {
                            DxConsole.err.println("Uncaught translation error:");
                            e.getCause().printStackTrace(DxConsole.err);
                        } else {
                            DxConsole.err.println("Uncaught translation error: " + e.getCause());
                        }
                    } else {
                        throw new InterruptedException("Too many errors");
                    }
                }
            }
            int i = errors.get();
            if (i != 0) {
                DxConsole.err.println(i + " error" + (i == 1 ? "" : "s") + "; aborting");
                return false;
            }
            if (args.incremental && !anyFilesProcessed) {
                return true;
            }
            if (!anyFilesProcessed && !args.emptyOk) {
                DxConsole.err.println("no classfiles specified");
                return false;
            }
            if (args.optimize && args.statistics) {
                CodeStatistics.dumpStatistics(DxConsole.out);
            }
            return true;
        } catch (InterruptedException e2) {
            classTranslatorPool.shutdownNow();
            classDefItemConsumer.shutdownNow();
            throw new RuntimeException("Translation has been interrupted", e2);
        } catch (Exception e3) {
            classTranslatorPool.shutdownNow();
            classDefItemConsumer.shutdownNow();
            e3.printStackTrace(System.out);
            throw new RuntimeException("Unexpected exception in translator thread.", e3);
        }
    }

    private static void createDexFile() {
        outputDex = new DexFile(args.dexOptions);
        if (args.dumpWidth != 0) {
            outputDex.setDumpWidth(args.dumpWidth);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void rotateDexFile() {
        DexFile dexFile = outputDex;
        if (dexFile != null) {
            ExecutorService executorService = dexOutPool;
            if (executorService != null) {
                dexOutputFutures.add(executorService.submit(new DexWriter(outputDex)));
            } else {
                dexOutputArrays.add(writeDex(dexFile));
            }
        }
        createDexFile();
    }

    private static void processOne(String str, ClassPathOpener.FileNameFilter fileNameFilter) {
        if (new ClassPathOpener(str, true, fileNameFilter, new FileBytesConsumer()).process()) {
            updateStatus(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateStatus(boolean z) {
        anyFilesProcessed = z | anyFilesProcessed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean processFileBytes(String str, long j, byte[] bArr) {
        boolean zEndsWith = str.endsWith(ClassFileLocator.CLASS_FILE_EXTENSION);
        boolean zEquals = str.equals(DexFormat.DEX_IN_JAR_NAME);
        boolean z = outputResources != null;
        if (!zEndsWith && !zEquals && !z) {
            if (args.verbose) {
                DxConsole.out.println("ignored resource " + str);
            }
            return false;
        }
        if (args.verbose) {
            DxConsole.out.println("processing " + str + "...");
        }
        String strFixPath = fixPath(str);
        if (zEndsWith) {
            if (z && args.keepClassesInJar) {
                synchronized (outputResources) {
                    outputResources.put(strFixPath, bArr);
                }
            }
            if (j < minimumFileAge) {
                return true;
            }
            processClass(strFixPath, bArr);
            return false;
        }
        if (zEquals) {
            List<byte[]> list = libraryDexBuffers;
            synchronized (list) {
                list.add(bArr);
            }
            return true;
        }
        synchronized (outputResources) {
            outputResources.put(strFixPath, bArr);
        }
        return true;
    }

    private static boolean processClass(String str, byte[] bArr) {
        if (!args.coreLibrary) {
            checkClassName(str);
        }
        try {
            new DirectClassFileConsumer(str, bArr, null).call(new ClassParserTask(str, bArr).call());
            return true;
        } catch (ParseException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Exception parsing classes", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DirectClassFile parseClass(String str, byte[] bArr) {
        DirectClassFile directClassFile = new DirectClassFile(bArr, str, args.cfOptions.strictNameCheck);
        directClassFile.setAttributeFactory(StdAttributeFactory.THE_ONE);
        directClassFile.getMagic();
        return directClassFile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ClassDefItem translateClass(byte[] bArr, DirectClassFile directClassFile) {
        try {
            return CfTranslator.translate(directClassFile, bArr, args.cfOptions, args.dexOptions, outputDex);
        } catch (ParseException e) {
            DxConsole.err.println("\ntrouble processing:");
            if (args.debug) {
                e.printStackTrace(DxConsole.err);
            } else {
                e.printContext(DxConsole.err);
            }
            errors.incrementAndGet();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean addClassToDex(ClassDefItem classDefItem) {
        synchronized (outputDex) {
            outputDex.add(classDefItem);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void checkClassName(java.lang.String r5) {
        /*
            java.lang.String r0 = "java/"
            boolean r0 = r5.startsWith(r0)
            r1 = 1
            if (r0 == 0) goto La
            goto L2c
        La:
            java.lang.String r0 = "javax/"
            boolean r0 = r5.startsWith(r0)
            r2 = 0
            if (r0 == 0) goto L2b
            r0 = 47
            r3 = 6
            int r0 = r5.indexOf(r0, r3)
            r4 = -1
            if (r0 != r4) goto L1e
            goto L2c
        L1e:
            java.lang.String r0 = r5.substring(r3, r0)
            java.lang.String[] r3 = com.android.dx.command.dexer.Main.JAVAX_CORE
            int r0 = java.util.Arrays.binarySearch(r3, r0)
            if (r0 < 0) goto L2b
            goto L2c
        L2b:
            r1 = r2
        L2c:
            if (r1 != 0) goto L2f
            return
        L2f:
            java.io.PrintStream r0 = com.android.dx.command.DxConsole.err
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\ntrouble processing \""
            r1.<init>(r2)
            java.lang.StringBuilder r5 = r1.append(r5)
            java.lang.String r1 = "\":\n\nIll-advised or mistaken usage of a core class (java.* or javax.*)\nwhen not building a core library.\n\nThis is often due to inadvertently including a core library file\nin your application's project, when using an IDE (such as\nEclipse). If you are sure you're not intentionally defining a\ncore class, then this is the most likely explanation of what's\ngoing on.\n\nHowever, you might actually be trying to define a class in a core\nnamespace, the source of which you may have taken, for example,\nfrom a non-Android virtual machine project. This will most\nassuredly not work. At a minimum, it jeopardizes the\ncompatibility of your app with future versions of the platform.\nIt is also often of questionable legality.\n\nIf you really intend to build a core library -- which is only\nappropriate as part of creating a full virtual machine\ndistribution, as opposed to compiling an application -- then use\nthe \"--core-library\" option to suppress this error message.\n\nIf you go ahead and use \"--core-library\" but are in fact\nbuilding an application, then be forewarned that your application\nwill still fail to build or run, at some point. Please be\nprepared for angry customers who find, for example, that your\napplication ceases to function once they upgrade their operating\nsystem. You will be to blame for this problem.\n\nIf you are legitimately using some code that happens to be in a\ncore package, then the easiest safe alternative you have is to\nrepackage that code. That is, move the classes in question into\nyour own package namespace. This means that they will never be in\nconflict with core system classes. JarJar is a tool that may help\nyou in this endeavor. If you find that you cannot do this, then\nthat is an indication that the path you are on will ultimately\nlead to pain, suffering, grief, and lamentation.\n"
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.String r5 = r5.toString()
            r0.println(r5)
            java.util.concurrent.atomic.AtomicInteger r5 = com.android.dx.command.dexer.Main.errors
            r5.incrementAndGet()
            com.android.dx.command.dexer.Main$StopProcessing r5 = new com.android.dx.command.dexer.Main$StopProcessing
            r0 = 0
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dx.command.dexer.Main.checkClassName(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] writeDex(DexFile dexFile) {
        byte[] dex;
        try {
            try {
                if (args.methodToDump != null) {
                    dexFile.toDex(null, false);
                    dumpMethod(dexFile, args.methodToDump, humanOutWriter);
                    dex = null;
                } else {
                    dex = dexFile.toDex(humanOutWriter, args.verboseDump);
                }
                if (args.statistics) {
                    DxConsole.out.println(dexFile.getStatistics().toHuman());
                }
                return dex;
            } finally {
                OutputStreamWriter outputStreamWriter = humanOutWriter;
                if (outputStreamWriter != null) {
                    outputStreamWriter.flush();
                }
            }
        } catch (Exception e) {
            if (args.debug) {
                DxConsole.err.println("\ntrouble writing output:");
                e.printStackTrace(DxConsole.err);
            } else {
                DxConsole.err.println("\ntrouble writing output: " + e.getMessage());
            }
            return null;
        }
    }

    private static boolean createJar(String str) {
        try {
            Manifest manifestMakeManifest = makeManifest();
            OutputStream outputStreamOpenOutput = openOutput(str);
            JarOutputStream jarOutputStream = new JarOutputStream(outputStreamOpenOutput, manifestMakeManifest);
            try {
                for (Map.Entry<String, byte[]> entry : outputResources.entrySet()) {
                    String key = entry.getKey();
                    byte[] value = entry.getValue();
                    JarEntry jarEntry = new JarEntry(key);
                    int length = value.length;
                    if (args.verbose) {
                        DxConsole.out.println("writing " + key + "; size " + length + "...");
                    }
                    jarEntry.setSize(length);
                    jarOutputStream.putNextEntry(jarEntry);
                    jarOutputStream.write(value);
                    jarOutputStream.closeEntry();
                }
                jarOutputStream.finish();
                jarOutputStream.flush();
                closeOutput(outputStreamOpenOutput);
                return true;
            } catch (Throwable th) {
                jarOutputStream.finish();
                jarOutputStream.flush();
                closeOutput(outputStreamOpenOutput);
                throw th;
            }
        } catch (Exception e) {
            if (args.debug) {
                DxConsole.err.println("\ntrouble writing output:");
                e.printStackTrace(DxConsole.err);
                return false;
            }
            DxConsole.err.println("\ntrouble writing output: " + e.getMessage());
            return false;
        }
    }

    private static Manifest makeManifest() throws IOException {
        Attributes mainAttributes;
        Manifest manifest;
        byte[] bArr = outputResources.get(MANIFEST_NAME);
        if (bArr == null) {
            manifest = new Manifest();
            mainAttributes = manifest.getMainAttributes();
            mainAttributes.put(Attributes.Name.MANIFEST_VERSION, BuildConfig.VERSION_NAME);
        } else {
            Manifest manifest2 = new Manifest(new ByteArrayInputStream(bArr));
            Attributes mainAttributes2 = manifest2.getMainAttributes();
            outputResources.remove(MANIFEST_NAME);
            mainAttributes = mainAttributes2;
            manifest = manifest2;
        }
        Attributes.Name name = CREATED_BY;
        String value = mainAttributes.getValue(name);
        mainAttributes.put(name, (value == null ? "" : value + " + ") + "dx 1.12");
        mainAttributes.putValue("Dex-Location", DexFormat.DEX_IN_JAR_NAME);
        return manifest;
    }

    private static OutputStream openOutput(String str) throws IOException {
        if (str.equals("-") || str.startsWith("-.")) {
            return System.out;
        }
        return new FileOutputStream(str);
    }

    private static void closeOutput(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            return;
        }
        outputStream.flush();
        if (outputStream != System.out) {
            outputStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String fixPath(String str) {
        if (File.separatorChar == '\\') {
            str = str.replace('\\', ClassPathElement.SEPARATOR_CHAR);
        }
        int iLastIndexOf = str.lastIndexOf("/./");
        if (iLastIndexOf != -1) {
            return str.substring(iLastIndexOf + 3);
        }
        return str.startsWith("./") ? str.substring(2) : str;
    }

    private static void dumpMethod(DexFile dexFile, String str, OutputStreamWriter outputStreamWriter) {
        boolean zEndsWith = str.endsWith("*");
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf <= 0 || iLastIndexOf == str.length() - 1) {
            DxConsole.err.println("bogus fully-qualified method name: " + str);
            return;
        }
        String strReplace = str.substring(0, iLastIndexOf).replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
        String strSubstring = str.substring(iLastIndexOf + 1);
        ClassDefItem classOrNull = dexFile.getClassOrNull(strReplace);
        if (classOrNull == null) {
            DxConsole.err.println("no such class: " + strReplace);
            return;
        }
        if (zEndsWith) {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        ArrayList<EncodedMethod> methods = classOrNull.getMethods();
        TreeMap treeMap = new TreeMap();
        for (EncodedMethod encodedMethod : methods) {
            String string = encodedMethod.getName().getString();
            if ((zEndsWith && string.startsWith(strSubstring)) || (!zEndsWith && string.equals(strSubstring))) {
                treeMap.put(encodedMethod.getRef().getNat(), encodedMethod);
            }
        }
        if (treeMap.size() == 0) {
            DxConsole.err.println("no such method: " + str);
            return;
        }
        PrintWriter printWriter = new PrintWriter(outputStreamWriter);
        for (EncodedMethod encodedMethod2 : treeMap.values()) {
            encodedMethod2.debugPrint(printWriter, args.verboseDump);
            CstString sourceFile = classOrNull.getSourceFile();
            if (sourceFile != null) {
                printWriter.println("  source file: " + sourceFile.toQuoted());
            }
            Annotations methodAnnotations = classOrNull.getMethodAnnotations(encodedMethod2.getRef());
            AnnotationsList parameterAnnotations = classOrNull.getParameterAnnotations(encodedMethod2.getRef());
            if (methodAnnotations != null) {
                printWriter.println("  method annotations:");
                Iterator<Annotation> it = methodAnnotations.getAnnotations().iterator();
                while (it.hasNext()) {
                    printWriter.println("    " + it.next());
                }
            }
            if (parameterAnnotations != null) {
                printWriter.println("  parameter annotations:");
                int size = parameterAnnotations.size();
                for (int i = 0; i < size; i++) {
                    printWriter.println("    parameter " + i);
                    Iterator<Annotation> it2 = parameterAnnotations.get(i).getAnnotations().iterator();
                    while (it2.hasNext()) {
                        printWriter.println("      " + it2.next());
                    }
                }
            }
        }
        printWriter.flush();
    }

    private static class NotFilter implements ClassPathOpener.FileNameFilter {
        private final ClassPathOpener.FileNameFilter filter;

        private NotFilter(ClassPathOpener.FileNameFilter fileNameFilter) {
            this.filter = fileNameFilter;
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.FileNameFilter
        public boolean accept(String str) {
            return !this.filter.accept(str);
        }
    }

    private static class MainDexListFilter implements ClassPathOpener.FileNameFilter {
        private MainDexListFilter() {
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.FileNameFilter
        public boolean accept(String str) {
            if (!str.endsWith(ClassFileLocator.CLASS_FILE_EXTENSION)) {
                return true;
            }
            return Main.classesInMainDex.contains(Main.fixPath(str));
        }
    }

    private static class BestEffortMainDexListFilter implements ClassPathOpener.FileNameFilter {
        Map<String, List<String>> map = new HashMap();

        public BestEffortMainDexListFilter() {
            Iterator it = Main.classesInMainDex.iterator();
            while (it.hasNext()) {
                String strFixPath = Main.fixPath((String) it.next());
                String simpleName = getSimpleName(strFixPath);
                List<String> arrayList = this.map.get(simpleName);
                if (arrayList == null) {
                    arrayList = new ArrayList<>(1);
                    this.map.put(simpleName, arrayList);
                }
                arrayList.add(strFixPath);
            }
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.FileNameFilter
        public boolean accept(String str) {
            if (!str.endsWith(ClassFileLocator.CLASS_FILE_EXTENSION)) {
                return true;
            }
            String strFixPath = Main.fixPath(str);
            List<String> list = this.map.get(getSimpleName(strFixPath));
            if (list == null) {
                return false;
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (strFixPath.endsWith(it.next())) {
                    return true;
                }
            }
            return false;
        }

        private static String getSimpleName(String str) {
            int iLastIndexOf = str.lastIndexOf(47);
            return iLastIndexOf >= 0 ? str.substring(iLastIndexOf + 1) : str;
        }
    }

    private static class StopProcessing extends RuntimeException {
        private StopProcessing() {
        }
    }

    public static class Arguments {
        private static final String INCREMENTAL_OPTION = "--incremental";
        private static final String INPUT_LIST_OPTION = "--input-list";
        private static final String MAIN_DEX_LIST_OPTION = "--main-dex-list";
        private static final String MINIMAL_MAIN_DEX_OPTION = "--minimal-main-dex";
        private static final String MULTI_DEX_OPTION = "--multi-dex";
        private static final String NUM_THREADS_OPTION = "--num-threads";
        public CfOptions cfOptions;
        public DexOptions dexOptions;
        public String[] fileNames;
        public boolean statistics;
        public boolean debug = false;
        public boolean warnings = true;
        public boolean verbose = false;
        public boolean verboseDump = false;
        public boolean coreLibrary = false;
        public String methodToDump = null;
        public int dumpWidth = 0;
        public String outName = null;
        public String humanOutName = null;
        public boolean strictNameCheck = true;
        public boolean emptyOk = false;
        public boolean jarOutput = false;
        public boolean keepClassesInJar = false;
        public int positionInfo = 2;
        public boolean localInfo = true;
        public boolean incremental = false;
        public boolean forceJumbo = false;
        public boolean optimize = true;
        public String optimizeListFile = null;
        public String dontOptimizeListFile = null;
        public int numThreads = 1;
        public boolean multiDex = false;
        public String mainDexListFile = null;
        public boolean minimalMainDex = false;
        private List<String> inputList = null;
        private int maxNumberOfIdxPerDex = 65536;

        private static class ArgumentsParser {
            private final String[] arguments;
            private String current;
            private int index = 0;
            private String lastValue;

            public ArgumentsParser(String[] strArr) {
                this.arguments = strArr;
            }

            public String getCurrent() {
                return this.current;
            }

            public String getLastValue() {
                return this.lastValue;
            }

            public boolean getNext() {
                int i = this.index;
                String[] strArr = this.arguments;
                if (i >= strArr.length) {
                    return false;
                }
                String str = strArr[i];
                this.current = str;
                if (str.equals("--") || !this.current.startsWith("--")) {
                    return false;
                }
                this.index++;
                return true;
            }

            private boolean getNextValue() {
                int i = this.index;
                String[] strArr = this.arguments;
                if (i >= strArr.length) {
                    return false;
                }
                this.current = strArr[i];
                this.index = i + 1;
                return true;
            }

            public String[] getRemaining() {
                String[] strArr = this.arguments;
                int length = strArr.length;
                int i = this.index;
                int i2 = length - i;
                String[] strArr2 = new String[i2];
                if (i2 > 0) {
                    System.arraycopy(strArr, i, strArr2, 0, i2);
                }
                return strArr2;
            }

            public boolean isArg(String str) {
                int length = str.length();
                if (length > 0) {
                    int i = length - 1;
                    if (str.charAt(i) == '=') {
                        if (this.current.startsWith(str)) {
                            this.lastValue = this.current.substring(length);
                            return true;
                        }
                        String strSubstring = str.substring(0, i);
                        if (!this.current.equals(strSubstring)) {
                            return false;
                        }
                        if (getNextValue()) {
                            this.lastValue = this.current;
                            return true;
                        }
                        System.err.println("Missing value after parameter " + strSubstring);
                        throw new UsageException();
                    }
                }
                return this.current.equals(str);
            }
        }

        public void parse(String[] strArr) throws Throwable {
            ArgumentsParser argumentsParser = new ArgumentsParser(strArr);
            boolean z = false;
            boolean z2 = false;
            while (argumentsParser.getNext()) {
                if (argumentsParser.isArg("--debug")) {
                    this.debug = true;
                } else if (argumentsParser.isArg("--no-warning")) {
                    this.warnings = false;
                } else if (argumentsParser.isArg("--verbose")) {
                    this.verbose = true;
                } else if (argumentsParser.isArg("--verbose-dump")) {
                    this.verboseDump = true;
                } else if (argumentsParser.isArg("--no-files")) {
                    this.emptyOk = true;
                } else if (argumentsParser.isArg("--no-optimize")) {
                    this.optimize = false;
                } else if (argumentsParser.isArg("--no-strict")) {
                    this.strictNameCheck = false;
                } else if (argumentsParser.isArg("--core-library")) {
                    this.coreLibrary = true;
                } else if (argumentsParser.isArg("--statistics")) {
                    this.statistics = true;
                } else if (argumentsParser.isArg("--optimize-list=")) {
                    if (this.dontOptimizeListFile != null) {
                        System.err.println("--optimize-list and --no-optimize-list are incompatible.");
                        throw new UsageException();
                    }
                    this.optimize = true;
                    this.optimizeListFile = argumentsParser.getLastValue();
                } else if (argumentsParser.isArg("--no-optimize-list=")) {
                    if (this.dontOptimizeListFile != null) {
                        System.err.println("--optimize-list and --no-optimize-list are incompatible.");
                        throw new UsageException();
                    }
                    this.optimize = true;
                    this.dontOptimizeListFile = argumentsParser.getLastValue();
                } else if (argumentsParser.isArg("--keep-classes")) {
                    this.keepClassesInJar = true;
                } else if (argumentsParser.isArg("--output=")) {
                    this.outName = argumentsParser.getLastValue();
                    if (new File(this.outName).isDirectory()) {
                        this.jarOutput = false;
                        z2 = true;
                    } else if (FileUtils.hasArchiveSuffix(this.outName)) {
                        this.jarOutput = true;
                    } else if (this.outName.endsWith(Main.DEX_EXTENSION) || this.outName.equals("-")) {
                        this.jarOutput = false;
                        z = true;
                    } else {
                        System.err.println("unknown output extension: " + this.outName);
                        throw new UsageException();
                    }
                } else if (argumentsParser.isArg("--dump-to=")) {
                    this.humanOutName = argumentsParser.getLastValue();
                } else if (argumentsParser.isArg("--dump-width=")) {
                    this.dumpWidth = Integer.parseInt(argumentsParser.getLastValue());
                } else if (argumentsParser.isArg("--dump-method=")) {
                    this.methodToDump = argumentsParser.getLastValue();
                    this.jarOutput = false;
                } else if (argumentsParser.isArg("--positions=")) {
                    String strIntern = argumentsParser.getLastValue().intern();
                    if (strIntern == SchedulerSupport.NONE) {
                        this.positionInfo = 1;
                    } else if (strIntern == "important") {
                        this.positionInfo = 3;
                    } else if (strIntern == "lines") {
                        this.positionInfo = 2;
                    } else {
                        System.err.println("unknown positions option: " + strIntern);
                        throw new UsageException();
                    }
                } else if (argumentsParser.isArg("--no-locals")) {
                    this.localInfo = false;
                } else if (argumentsParser.isArg("--num-threads=")) {
                    this.numThreads = Integer.parseInt(argumentsParser.getLastValue());
                } else if (argumentsParser.isArg(INCREMENTAL_OPTION)) {
                    this.incremental = true;
                } else if (argumentsParser.isArg("--force-jumbo")) {
                    this.forceJumbo = true;
                } else if (argumentsParser.isArg(MULTI_DEX_OPTION)) {
                    this.multiDex = true;
                } else if (argumentsParser.isArg("--main-dex-list=")) {
                    this.mainDexListFile = argumentsParser.getLastValue();
                } else if (argumentsParser.isArg(MINIMAL_MAIN_DEX_OPTION)) {
                    this.minimalMainDex = true;
                } else if (argumentsParser.isArg("--set-max-idx-number=")) {
                    this.maxNumberOfIdxPerDex = Integer.parseInt(argumentsParser.getLastValue());
                } else if (argumentsParser.isArg("--input-list=")) {
                    File file = new File(argumentsParser.getLastValue());
                    try {
                        this.inputList = new ArrayList();
                        Main.readPathsFromFile(file.getAbsolutePath(), this.inputList);
                    } catch (IOException unused) {
                        System.err.println("Unable to read input list file: " + file.getName());
                        throw new UsageException();
                    }
                } else {
                    System.err.println("unknown option: " + argumentsParser.getCurrent());
                    throw new UsageException();
                }
            }
            this.fileNames = argumentsParser.getRemaining();
            List<String> list = this.inputList;
            if (list != null && !list.isEmpty()) {
                this.inputList.addAll(Arrays.asList(this.fileNames));
                List<String> list2 = this.inputList;
                this.fileNames = (String[]) list2.toArray(new String[list2.size()]);
            }
            if (this.fileNames.length == 0) {
                if (!this.emptyOk) {
                    System.err.println("no input files specified");
                    throw new UsageException();
                }
            } else if (this.emptyOk) {
                System.out.println("ignoring input files");
            }
            if (this.humanOutName == null && this.methodToDump != null) {
                this.humanOutName = "-";
            }
            String str = this.mainDexListFile;
            if (str != null && !this.multiDex) {
                System.err.println("--main-dex-list is only supported in combination with --multi-dex");
                throw new UsageException();
            }
            if (this.minimalMainDex && (str == null || !this.multiDex)) {
                System.err.println("--minimal-main-dex is only supported in combination with --multi-dex and --main-dex-list");
                throw new UsageException();
            }
            boolean z3 = this.multiDex;
            if (z3 && this.incremental) {
                System.err.println("--incremental is not supported with --multi-dex");
                throw new UsageException();
            }
            if (z3 && z) {
                System.err.println("Unsupported output \"" + this.outName + "\". --multi-dex supports only archive or directory output");
                throw new UsageException();
            }
            if (z2 && !z3) {
                this.outName = new File(this.outName, DexFormat.DEX_IN_JAR_NAME).getPath();
            }
            makeOptionsObjects();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void makeOptionsObjects() {
            CfOptions cfOptions = new CfOptions();
            this.cfOptions = cfOptions;
            cfOptions.positionInfo = this.positionInfo;
            this.cfOptions.localInfo = this.localInfo;
            this.cfOptions.strictNameCheck = this.strictNameCheck;
            this.cfOptions.optimize = this.optimize;
            this.cfOptions.optimizeListFile = this.optimizeListFile;
            this.cfOptions.dontOptimizeListFile = this.dontOptimizeListFile;
            this.cfOptions.statistics = this.statistics;
            if (this.warnings) {
                this.cfOptions.warn = DxConsole.err;
            } else {
                this.cfOptions.warn = DxConsole.noop;
            }
            DexOptions dexOptions = new DexOptions();
            this.dexOptions = dexOptions;
            dexOptions.forceJumbo = this.forceJumbo;
        }
    }

    private static class FileBytesConsumer implements ClassPathOpener.Consumer {
        private FileBytesConsumer() {
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public boolean processFileBytes(String str, long j, byte[] bArr) {
            return Main.processFileBytes(str, j, bArr);
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public void onException(Exception exc) {
            if (exc instanceof StopProcessing) {
                throw ((StopProcessing) exc);
            }
            if (exc instanceof SimException) {
                DxConsole.err.println("\nEXCEPTION FROM SIMULATION:");
                DxConsole.err.println(exc.getMessage() + "\n");
                DxConsole.err.println(((SimException) exc).getContext());
            } else if (exc instanceof ParseException) {
                DxConsole.err.println("\nPARSE ERROR:");
                ParseException parseException = (ParseException) exc;
                if (Main.args.debug) {
                    parseException.printStackTrace(DxConsole.err);
                } else {
                    parseException.printContext(DxConsole.err);
                }
            } else {
                DxConsole.err.println("\nUNEXPECTED TOP-LEVEL EXCEPTION:");
                exc.printStackTrace(DxConsole.err);
            }
            Main.errors.incrementAndGet();
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.Consumer
        public void onProcessArchiveStart(File file) {
            if (Main.args.verbose) {
                DxConsole.out.println("processing archive " + file + "...");
            }
        }
    }

    private static class ClassParserTask implements Callable<DirectClassFile> {
        byte[] bytes;
        String name;

        private ClassParserTask(String str, byte[] bArr) {
            this.name = str;
            this.bytes = bArr;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public DirectClassFile call() throws Exception {
            return Main.parseClass(this.name, this.bytes);
        }
    }

    private static class DirectClassFileConsumer implements Callable<Boolean> {
        byte[] bytes;
        Future<DirectClassFile> dcff;
        String name;

        private DirectClassFileConsumer(String str, byte[] bArr, Future<DirectClassFile> future) {
            this.name = str;
            this.bytes = bArr;
            this.dcff = future;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            return call(this.dcff.get());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Boolean call(DirectClassFile directClassFile) {
            int i;
            int i2;
            int size;
            int size2;
            if (Main.args.multiDex) {
                int size3 = directClassFile.getConstantPool().size();
                int size4 = directClassFile.getMethods().size() + size3 + 2;
                int size5 = size3 + directClassFile.getFields().size() + 9;
                synchronized (Main.dexRotationLock) {
                    synchronized (Main.outputDex) {
                        size = Main.outputDex.getMethodIds().items().size();
                        size2 = Main.outputDex.getFieldIds().items().size();
                    }
                    while (true) {
                        if (size + size4 + Main.maxMethodIdsInProcess <= Main.args.maxNumberOfIdxPerDex && size2 + size5 + Main.maxFieldIdsInProcess <= Main.args.maxNumberOfIdxPerDex) {
                            break;
                        }
                        if (Main.maxMethodIdsInProcess > 0 || Main.maxFieldIdsInProcess > 0) {
                            try {
                                Main.dexRotationLock.wait();
                            } catch (InterruptedException unused) {
                            }
                        } else {
                            if (Main.outputDex.getClassDefs().items().size() <= 0) {
                                break;
                            }
                            Main.rotateDexFile();
                        }
                        synchronized (Main.outputDex) {
                            size = Main.outputDex.getMethodIds().items().size();
                            size2 = Main.outputDex.getFieldIds().items().size();
                        }
                    }
                    Main.maxMethodIdsInProcess += size4;
                    Main.maxFieldIdsInProcess += size5;
                }
                i2 = size5;
                i = size4;
            } else {
                i = 0;
                i2 = 0;
            }
            Main.addToDexFutures.add(Main.classDefItemConsumer.submit(new ClassDefItemConsumer(this.name, Main.classTranslatorPool.submit(new ClassTranslatorTask(this.name, this.bytes, directClassFile)), i, i2)));
            return true;
        }
    }

    private static class ClassTranslatorTask implements Callable<ClassDefItem> {
        byte[] bytes;
        DirectClassFile classFile;
        String name;

        private ClassTranslatorTask(String str, byte[] bArr, DirectClassFile directClassFile) {
            this.name = str;
            this.bytes = bArr;
            this.classFile = directClassFile;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ClassDefItem call() {
            return Main.translateClass(this.bytes, this.classFile);
        }
    }

    private static class ClassDefItemConsumer implements Callable<Boolean> {
        Future<ClassDefItem> futureClazz;
        int maxFieldIdsInClass;
        int maxMethodIdsInClass;
        String name;

        private ClassDefItemConsumer(String str, Future<ClassDefItem> future, int i, int i2) {
            this.name = str;
            this.futureClazz = future;
            this.maxMethodIdsInClass = i;
            this.maxFieldIdsInClass = i2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            try {
                try {
                    ClassDefItem classDefItem = this.futureClazz.get();
                    if (classDefItem != null) {
                        Main.addClassToDex(classDefItem);
                        Main.updateStatus(true);
                    }
                    if (Main.args.multiDex) {
                        synchronized (Main.dexRotationLock) {
                            Main.maxMethodIdsInProcess -= this.maxMethodIdsInClass;
                            Main.maxFieldIdsInProcess -= this.maxFieldIdsInClass;
                            Main.dexRotationLock.notifyAll();
                        }
                    }
                    return true;
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof Exception) {
                        throw ((Exception) cause);
                    }
                    throw e;
                }
            } catch (Throwable th) {
                if (Main.args.multiDex) {
                    synchronized (Main.dexRotationLock) {
                        Main.maxMethodIdsInProcess -= this.maxMethodIdsInClass;
                        Main.maxFieldIdsInProcess -= this.maxFieldIdsInClass;
                        Main.dexRotationLock.notifyAll();
                    }
                }
                throw th;
            }
        }
    }

    private static class DexWriter implements Callable<byte[]> {
        private DexFile dexFile;

        private DexWriter(DexFile dexFile) {
            this.dexFile = dexFile;
        }

        @Override // java.util.concurrent.Callable
        public byte[] call() throws IOException {
            return Main.writeDex(this.dexFile);
        }
    }
}
