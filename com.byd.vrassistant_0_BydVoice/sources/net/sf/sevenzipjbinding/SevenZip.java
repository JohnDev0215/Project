package net.sf.sevenzipjbinding;

import androidx.core.os.EnvironmentCompat;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import net.sf.sevenzipjbinding.impl.OutArchiveImpl;

/* JADX INFO: loaded from: classes3.dex */
public class SevenZip {
    private static final String PROPERTY_BUILD_REF = "build.ref";
    private static final String PROPERTY_SEVENZIPJBINDING_LIB_HASH = "lib.%s.hash";
    private static final String PROPERTY_SEVENZIPJBINDING_LIB_NAME = "lib.%s.name";
    private static final String SEVENZIPJBINDING_LIB_PROPERTIES = "build.ref=000000000000\n\nlib.1.name=lib7-Zip-JBinding.so\nlib.1.hash=0000000000000000000000000000000000000000\n";
    private static final String SEVENZIPJBINDING_LIB_PROPERTIES_FILENAME = "sevenzipjbinding-lib.properties";
    public static final String SEVENZIPJBINDING_MANIFEST_MF = "Manifest-Version: 1.0\nImplementation-Vendor: sevenzipjbind.sf.net\nImplementation-Title: 7-Zip-JBinding native lib (Linux-arm)\nImplementation-Version: 16.02-2.01\nBuilt-By: Boris Brodski\nCMake: 3.3.2\nCreated-By: 1.6.0_33-b03 (Sun Microsystems Inc.)\nBuilt-Date: 2020-01-20 21:44:51Z\n";
    private static final String SEVENZIPJBINDING_PLATFORMS_PROPRETIES_FILENAME = "/sevenzipjbinding-platforms.properties";
    private static final String SEVENZIPJBINDING_PLATFORM_PROPERTIES = "platform.1=Linux-arm";
    private static final String SEVENZIPJBINDING_VERSION = "16.02-2.01";
    private static final String SYSTEM_PROPERTY_SEVEN_ZIP_NO_DO_PRIVILEGED_INITIALIZATION = "sevenzip.no_doprivileged_initialization";
    private static final String SYSTEM_PROPERTY_TMP = "java.io.tmpdir";
    private static boolean autoInitializationWillOccur = true;
    private static List<String> availablePlatforms = null;
    private static boolean initializationSuccessful = false;
    private static SevenZipNativeInitializationException lastInitializationException;
    private static File[] temporaryArtifacts;
    private static String usedPlatform;

    public static class Version {
        public int build;
        public String copyright;
        public String date;
        public int major;
        public int minor;
        public String version;
    }

    public static String getSevenZipJBindingVersion() {
        return "16.02-2.01";
    }

    private static native void nativeCreateArchive(OutArchiveImpl<?> outArchiveImpl, ArchiveFormat archiveFormat) throws SevenZipException;

    private static native int nativeGetVersionBuild();

    private static native String nativeGetVersionCopyright();

    private static native String nativeGetVersionDate();

    private static native int nativeGetVersionMajor();

    private static native int nativeGetVersionMinor();

    private static native String nativeGetVersionVersion();

    /* JADX INFO: Access modifiers changed from: private */
    public static native String nativeInitSevenZipLibrary() throws SevenZipNativeInitializationException;

    private static native IInArchive nativeOpenArchive(ArchiveFormat archiveFormat, IInStream iInStream, IArchiveOpenCallback iArchiveOpenCallback) throws SevenZipException;

    private SevenZip() {
    }

    public static synchronized boolean isInitializedSuccessfully() {
        return initializationSuccessful;
    }

    public static synchronized Throwable getLastInitializationException() {
        return lastInitializationException;
    }

    public static synchronized boolean isAutoInitializationWillOccur() {
        return autoInitializationWillOccur;
    }

    public static synchronized String getUsedPlatform() {
        return usedPlatform;
    }

    public static synchronized List<String> getPlatformList() throws SevenZipNativeInitializationException {
        InputStream resourceAsStream;
        List<String> list = availablePlatforms;
        if (list != null) {
            return list;
        }
        if (System.getProperty("java.vendor", EnvironmentCompat.MEDIA_UNKNOWN).equals("The Android Project")) {
            resourceAsStream = new ByteArrayInputStream(SEVENZIPJBINDING_PLATFORM_PROPERTIES.getBytes());
        } else {
            resourceAsStream = SevenZip.class.getResourceAsStream(SEVENZIPJBINDING_PLATFORMS_PROPRETIES_FILENAME);
        }
        if (resourceAsStream == null) {
            throw new SevenZipNativeInitializationException("Can not find 7-Zip-JBinding platform property file /sevenzipjbinding-platforms.properties. Make sure the 'sevenzipjbinding-<Platform>.jar' file is on the class path or consider initializing SevenZipJBinding manualy using one of the offered initialization methods: 'net.sf.sevenzipjbinding.SevenZip.init*()'");
        }
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throwInitException(e, "Error loading existing property file /sevenzipjbinding-platforms.properties");
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (true) {
            String property = properties.getProperty("platform." + i);
            if (property != null) {
                arrayList.add(property);
                i++;
            } else {
                availablePlatforms = arrayList;
                return arrayList;
            }
        }
    }

    public static synchronized File[] getTemporaryArtifacts() {
        return temporaryArtifacts;
    }

    public static void initSevenZipFromPlatformJAR() throws SevenZipNativeInitializationException {
        initSevenZipFromPlatformJARIntern(null, null);
    }

    public static void initSevenZipFromPlatformJAR(File file) throws SevenZipNativeInitializationException {
        initSevenZipFromPlatformJARIntern(null, file);
    }

    public static void initSevenZipFromPlatformJAR(String str, File file) throws SevenZipNativeInitializationException {
        initSevenZipFromPlatformJARIntern(str, file);
    }

    public static void initSevenZipFromPlatformJAR(String str) throws SevenZipNativeInitializationException {
        initSevenZipFromPlatformJARIntern(str, null);
    }

    private static synchronized void initSevenZipFromPlatformJARIntern(String str, File file) throws SevenZipNativeInitializationException {
        try {
            autoInitializationWillOccur = false;
            if (initializationSuccessful) {
                return;
            }
            determineAndSetUsedPlatform(str);
            Properties propertiesLoadSevenZipJBindingLibProperties = loadSevenZipJBindingLibProperties();
            loadNativeLibraries(copyOrSkipLibraries(propertiesLoadSevenZipJBindingLibProperties, getOrCreateSevenZipJBindingTmpDir(createOrVerifyTmpDir(file), propertiesLoadSevenZipJBindingLibProperties)));
            nativeInitialization();
        } catch (SevenZipNativeInitializationException e) {
            lastInitializationException = e;
            throw e;
        }
    }

    private static void determineAndSetUsedPlatform(String str) throws SevenZipNativeInitializationException {
        if (str == null) {
            usedPlatform = getPlatformBestMatch();
        } else {
            usedPlatform = str;
        }
    }

    private static Properties loadSevenZipJBindingLibProperties() throws SevenZipNativeInitializationException {
        InputStream resourceAsStream;
        String str = "/" + usedPlatform + "/";
        if (System.getProperty("java.vendor", EnvironmentCompat.MEDIA_UNKNOWN).equals("The Android Project")) {
            resourceAsStream = new ByteArrayInputStream(SEVENZIPJBINDING_LIB_PROPERTIES.getBytes());
        } else {
            resourceAsStream = SevenZip.class.getResourceAsStream(str + SEVENZIPJBINDING_LIB_PROPERTIES_FILENAME);
        }
        if (resourceAsStream == null) {
            throwInitException("error loading property file '" + str + "sevenzipjbinding-lib.properties' from a jar-file 'sevenzipjbinding-<Platform>.jar'. Is the platform jar-file not on the class path?");
        }
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException unused) {
            throwInitException("error loading property file 'sevenzipjbinding-lib.properties' from a jar-file 'sevenzipjbinding-<Platform>.jar'");
        }
        return properties;
    }

    private static File createOrVerifyTmpDir(File file) throws SevenZipNativeInitializationException {
        File file2;
        if (System.getProperty("java.vendor", EnvironmentCompat.MEDIA_UNKNOWN).equals("The Android Project")) {
            return null;
        }
        if (file != null) {
            file2 = file;
        } else {
            String property = System.getProperty(SYSTEM_PROPERTY_TMP);
            if (property == null) {
                throwInitException("can't determinte tmp directory. Use may use -Djava.io.tmpdir=<path to tmp dir> parameter for jvm to fix this.");
            }
            file2 = new File(property);
        }
        if (!file2.exists() || !file2.isDirectory()) {
            throwInitException("invalid tmp directory '" + file + "'");
        }
        if (!file2.canWrite()) {
            throwInitException("can't create files in '" + file2.getAbsolutePath() + "'");
        }
        return file2;
    }

    private static File getOrCreateSevenZipJBindingTmpDir(File file, Properties properties) throws SevenZipNativeInitializationException {
        String orGenerateBuildRef = getOrGenerateBuildRef(properties);
        if (System.getProperty("java.vendor", EnvironmentCompat.MEDIA_UNKNOWN).equals("The Android Project")) {
            return null;
        }
        File file2 = new File(file.getAbsolutePath() + File.separator + "SevenZipJBinding-" + orGenerateBuildRef);
        if (!file2.exists() && !file2.mkdir()) {
            throwInitException("Directory '" + file.getAbsolutePath() + "' couldn't be created");
        }
        return file2;
    }

    private static String getOrGenerateBuildRef(Properties properties) {
        String property = properties.getProperty(PROPERTY_BUILD_REF);
        return property == null ? Integer.toString(new Random().nextInt(10000000)) : property;
    }

    private static List<File> copyOrSkipLibraries(Properties properties, File file) throws Throwable {
        File file2;
        ArrayList arrayList = new ArrayList(5);
        int i = 1;
        while (true) {
            String str = String.format(PROPERTY_SEVENZIPJBINDING_LIB_NAME, Integer.valueOf(i));
            String str2 = String.format(PROPERTY_SEVENZIPJBINDING_LIB_HASH, Integer.valueOf(i));
            String property = properties.getProperty(str);
            String property2 = properties.getProperty(str2);
            if (property == null) {
                if (arrayList.size() == 0) {
                    throwInitException("property file 'sevenzipjbinding-lib.properties' from 'sevenzipjbinding-<Platform>.jar' missing property '" + str + "'");
                } else {
                    applyTemporaryArtifacts(file, arrayList);
                    return arrayList;
                }
            }
            if (property2 == null) {
                throwInitException("property file 'sevenzipjbinding-lib.properties' from 'sevenzipjbinding-<Platform>.jar' missing property " + str2 + " containing the hash for the library '" + property + "'");
            }
            if (System.getProperty("java.vendor", EnvironmentCompat.MEDIA_UNKNOWN).equals("The Android Project")) {
                file2 = new File(property.replaceAll("lib|.so", ""));
            } else {
                file2 = new File(file.getAbsolutePath() + File.separatorChar + property);
                if (!file2.exists() || !hashMatched(file2, property2)) {
                    InputStream resourceAsStream = SevenZip.class.getResourceAsStream("/" + usedPlatform + "/" + property);
                    if (resourceAsStream == null) {
                        throwInitException("error loading native library '" + property + "' from a jar-file 'sevenzipjbinding-<Platform>.jar'.");
                    }
                    copyLibraryToFS(file2, resourceAsStream);
                }
            }
            arrayList.add(file2);
            i++;
        }
    }

    private static boolean hashMatched(File file, String str) throws SevenZipNativeInitializationException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[131072];
                    while (true) {
                        try {
                            int i = fileInputStream.read(bArr);
                            if (i <= 0) {
                                break;
                            }
                            messageDigest.update(bArr, 0, i);
                        } catch (IOException e) {
                            throwInitException(e, "Error reading from library file opened from the temp directory: '" + file.getAbsolutePath() + "'");
                            try {
                                fileInputStream.close();
                            } catch (IOException unused) {
                            }
                            return false;
                        }
                    }
                    boolean zEquals = byteArrayToHex(messageDigest.digest()).equals(str.trim().toLowerCase());
                    try {
                    } catch (IOException e2) {
                        throwInitException(e2, "Error closing library file from the temp directory (opened for reading): '" + file.getAbsolutePath() + "'");
                    }
                    return zEquals;
                } finally {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused2) {
                    }
                }
            } catch (IOException e3) {
                throwInitException(e3, "Error opening library file from the temp directory for reading: '" + file.getAbsolutePath() + "'");
                return false;
            }
        } catch (NoSuchAlgorithmException e4) {
            throwInitException(e4, "Error initializing SHA1 algorithm");
            return false;
        }
    }

    private static String byteArrayToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toHexString(b & 255));
        }
        return sb.toString();
    }

    private static void applyTemporaryArtifacts(File file, List<File> list) {
        File[] fileArr = new File[list.size() + 1];
        temporaryArtifacts = fileArr;
        list.toArray(fileArr);
        temporaryArtifacts[r2.length - 1] = file;
    }

    private static void loadNativeLibraries(List<File> list) throws SevenZipNativeInitializationException {
        int size = list.size();
        while (true) {
            size--;
            if (size == -1) {
                return;
            }
            try {
                if (System.getProperty("java.vendor", EnvironmentCompat.MEDIA_UNKNOWN).equals("The Android Project")) {
                    System.loadLibrary(list.get(size).getName());
                } else {
                    System.load(list.get(size).getAbsolutePath());
                }
            } catch (Throwable th) {
                throw new SevenZipNativeInitializationException("7-Zip-JBinding initialization failed: Error loading native library: '" + ((String) null) + "'", th);
            }
        }
    }

    public static synchronized void initLoadedLibraries() throws SevenZipNativeInitializationException {
        if (initializationSuccessful) {
            return;
        }
        autoInitializationWillOccur = false;
        nativeInitialization();
    }

    private static void nativeInitialization() throws SevenZipNativeInitializationException {
        String property = System.getProperty(SYSTEM_PROPERTY_SEVEN_ZIP_NO_DO_PRIVILEGED_INITIALIZATION);
        final String[] strArr = new String[1];
        final Throwable[] thArr = new Throwable[1];
        if (property == null || property.trim().equals("0")) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() { // from class: net.sf.sevenzipjbinding.SevenZip.1
                @Override // java.security.PrivilegedAction
                public Void run() {
                    try {
                        strArr[0] = SevenZip.nativeInitSevenZipLibrary();
                        return null;
                    } catch (Throwable th) {
                        thArr[0] = th;
                        return null;
                    }
                }
            });
        } else {
            strArr[0] = nativeInitSevenZipLibrary();
        }
        String str = strArr[0];
        if (str != null || thArr[0] != null) {
            if (str == null) {
                str = "No message";
            }
            SevenZipNativeInitializationException sevenZipNativeInitializationException = new SevenZipNativeInitializationException("Error initializing 7-Zip-JBinding: " + str, thArr[0]);
            lastInitializationException = sevenZipNativeInitializationException;
            throw sevenZipNativeInitializationException;
        }
        initializationSuccessful = true;
    }

    public static IInArchive openInArchive(ArchiveFormat archiveFormat, IInStream iInStream, IArchiveOpenCallback iArchiveOpenCallback) throws SevenZipException {
        ensureLibraryIsInitialized();
        if (iArchiveOpenCallback == null) {
            iArchiveOpenCallback = new DummyOpenArchiveCallback();
        }
        if (archiveFormat != null) {
            return callNativeOpenArchive(archiveFormat, iInStream, iArchiveOpenCallback);
        }
        return callNativeOpenArchive(null, iInStream, iArchiveOpenCallback);
    }

    public static IInArchive openInArchive(ArchiveFormat archiveFormat, IInStream iInStream, String str) throws SevenZipException {
        ensureLibraryIsInitialized();
        if (str == null) {
            return openInArchive(archiveFormat, iInStream);
        }
        return callNativeOpenArchive(archiveFormat, iInStream, new ArchiveOpenCryptoCallback(str));
    }

    public static IInArchive openInArchive(ArchiveFormat archiveFormat, IInStream iInStream) throws SevenZipException {
        ensureLibraryIsInitialized();
        return callNativeOpenArchive(archiveFormat, iInStream, new DummyOpenArchiveCallback());
    }

    private static void ensureLibraryIsInitialized() {
        if (autoInitializationWillOccur) {
            autoInitializationWillOccur = false;
            try {
                initSevenZipFromPlatformJAR();
            } catch (SevenZipNativeInitializationException e) {
                lastInitializationException = e;
                throw new RuntimeException("SevenZipJBinding couldn't be initialized automaticly using initialization from platform depended JAR and the default temporary directory. Please, make sure the correct 'sevenzipjbinding-<Platform>.jar' file is on the class path or consider initializing SevenZipJBinding manualy using one of the offered initialization methods: 'net.sf.sevenzipjbinding.SevenZip.init*()'", e);
            }
        }
        if (!initializationSuccessful) {
            throw new RuntimeException("SevenZipJBinding wasn't initialized successfully last time.", lastInitializationException);
        }
    }

    private static void throwInitException(String str) throws SevenZipNativeInitializationException {
        throwInitException(null, str);
    }

    private static void throwInitException(Exception exc, String str) throws SevenZipNativeInitializationException {
        throw new SevenZipNativeInitializationException("Error loading SevenZipJBinding native library into JVM: " + str + " [You may also try different SevenZipJBinding initialization methods 'net.sf.sevenzipjbinding.SevenZip.init*()' in order to solve this problem] ", exc);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void copyLibraryToFS(java.io.File r6, java.io.InputStream r7) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            r0 = 65536(0x10000, float:9.1835E-41)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Exception -> L1c java.lang.Throwable -> L47
        La:
            int r2 = r7.read(r0)     // Catch: java.lang.Exception -> L1c java.lang.Throwable -> L47
            if (r2 <= 0) goto L15
            r3 = 0
            r1.write(r0, r3, r2)     // Catch: java.lang.Exception -> L1c java.lang.Throwable -> L47
            goto La
        L15:
            r7.close()     // Catch: java.io.IOException -> L18
        L18:
            r1.close()     // Catch: java.io.IOException -> L1b
        L1b:
            return
        L1c:
            r0 = move-exception
            goto L24
        L1e:
            r6 = move-exception
            goto L49
        L20:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L24:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L47
            r3.<init>()     // Catch: java.lang.Throwable -> L47
            java.lang.String r4 = "Error initializing SevenZipJBinding native library: can't copy native library out of a resource file to the temporary location: '"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> L47
            java.lang.String r6 = r6.getAbsolutePath()     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r6 = r3.append(r6)     // Catch: java.lang.Throwable -> L47
            java.lang.String r3 = "'"
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch: java.lang.Throwable -> L47
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L47
            r2.<init>(r6, r0)     // Catch: java.lang.Throwable -> L47
            throw r2     // Catch: java.lang.Throwable -> L47
        L47:
            r6 = move-exception
            r0 = r1
        L49:
            r7.close()     // Catch: java.io.IOException -> L4c
        L4c:
            if (r0 == 0) goto L51
            r0.close()     // Catch: java.io.IOException -> L51
        L51:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.sevenzipjbinding.SevenZip.copyLibraryToFS(java.io.File, java.io.InputStream):void");
    }

    public static String getPlatformBestMatch() throws SevenZipNativeInitializationException {
        List<String> platformList = getPlatformList();
        if (platformList.size() == 1) {
            return platformList.get(0);
        }
        String property = System.getProperty("os.arch");
        String str = System.getProperty("os.name").split(" ")[0];
        if (platformList.contains(str + "-" + property)) {
            return str + "-" + property;
        }
        StringBuilder sb = new StringBuilder("Can't find suited platform for os.arch=");
        sb.append(property);
        sb.append(", os.name=");
        sb.append(str);
        sb.append("... Available list of platforms: ");
        Iterator<String> it = platformList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        throwInitException(sb.toString());
        return null;
    }

    private static IInArchive callNativeOpenArchive(ArchiveFormat archiveFormat, IInStream iInStream, IArchiveOpenCallback iArchiveOpenCallback) throws SevenZipException {
        if (iInStream == null) {
            throw new NullPointerException("SevenZip.callNativeOpenArchive(...): inStream parameter is null");
        }
        return nativeOpenArchive(archiveFormat, iInStream, iArchiveOpenCallback);
    }

    public static Version getSevenZipVersion() {
        ensureLibraryIsInitialized();
        Version version = new Version();
        version.major = nativeGetVersionMajor();
        version.minor = nativeGetVersionMinor();
        version.build = nativeGetVersionBuild();
        version.version = nativeGetVersionVersion();
        version.date = nativeGetVersionDate();
        version.copyright = nativeGetVersionCopyright();
        return version;
    }

    public static IOutCreateArchiveZip openOutArchiveZip() throws SevenZipException {
        return (IOutCreateArchiveZip) openOutArchiveIntern(ArchiveFormat.ZIP);
    }

    public static IOutCreateArchive7z openOutArchive7z() throws SevenZipException {
        return (IOutCreateArchive7z) openOutArchiveIntern(ArchiveFormat.SEVEN_ZIP);
    }

    public static IOutCreateArchiveTar openOutArchiveTar() throws SevenZipException {
        return (IOutCreateArchiveTar) openOutArchiveIntern(ArchiveFormat.TAR);
    }

    public static IOutCreateArchiveBZip2 openOutArchiveBZip2() throws SevenZipException {
        return (IOutCreateArchiveBZip2) openOutArchiveIntern(ArchiveFormat.BZIP2);
    }

    public static IOutCreateArchiveGZip openOutArchiveGZip() throws SevenZipException {
        return (IOutCreateArchiveGZip) openOutArchiveIntern(ArchiveFormat.GZIP);
    }

    public static IOutCreateArchive<IOutItemAllFormats> openOutArchive(ArchiveFormat archiveFormat) throws SevenZipException {
        return openOutArchiveIntern(archiveFormat);
    }

    private static OutArchiveImpl<?> openOutArchiveIntern(ArchiveFormat archiveFormat) throws SevenZipException {
        ensureLibraryIsInitialized();
        if (!archiveFormat.isOutArchiveSupported()) {
            throw new IllegalStateException("Archive format '" + archiveFormat + "' doesn't support archive creation.");
        }
        try {
            OutArchiveImpl<?> outArchiveImplNewInstance = archiveFormat.getOutArchiveImplementation().newInstance();
            nativeCreateArchive(outArchiveImplNewInstance, archiveFormat);
            return outArchiveImplNewInstance;
        } catch (Exception unused) {
            throw new IllegalStateException("Internal error: Can't create new instance of the class " + archiveFormat.getOutArchiveImplementation() + " using default constructor.");
        }
    }

    private static class DummyOpenArchiveCallback implements IArchiveOpenCallback, ICryptoGetTextPassword {
        @Override // net.sf.sevenzipjbinding.IArchiveOpenCallback
        public void setCompleted(Long l, Long l2) {
        }

        @Override // net.sf.sevenzipjbinding.IArchiveOpenCallback
        public void setTotal(Long l, Long l2) {
        }

        private DummyOpenArchiveCallback() {
        }

        @Override // net.sf.sevenzipjbinding.ICryptoGetTextPassword
        public String cryptoGetTextPassword() throws SevenZipException {
            throw new SevenZipException("No password was provided for opening protected archive.");
        }
    }

    private static final class ArchiveOpenCryptoCallback implements IArchiveOpenCallback, ICryptoGetTextPassword {
        private final String passwordForOpen;

        @Override // net.sf.sevenzipjbinding.IArchiveOpenCallback
        public void setCompleted(Long l, Long l2) {
        }

        @Override // net.sf.sevenzipjbinding.IArchiveOpenCallback
        public void setTotal(Long l, Long l2) {
        }

        public ArchiveOpenCryptoCallback(String str) {
            this.passwordForOpen = str;
        }

        @Override // net.sf.sevenzipjbinding.ICryptoGetTextPassword
        public String cryptoGetTextPassword() throws SevenZipException {
            return this.passwordForOpen;
        }
    }
}
