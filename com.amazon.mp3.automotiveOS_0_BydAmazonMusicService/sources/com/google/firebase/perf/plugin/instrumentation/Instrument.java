package com.google.firebase.perf.plugin.instrumentation;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.firebase.perf.plugin.FirebasePerfPlugin;
import com.google.firebase.perf.plugin.instrumentation.exceptions.AlreadyPerfInstrumentedException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes6.dex */
public class Instrument {
    protected static final int ASM_API_VERSION = 589824;
    private static final Logger logger = FirebasePerfPlugin.getLogger();
    private final ClassLoader classLoader;
    private final InstrumentationConfig instrumentationConfig;

    public Instrument(ClassLoader classLoader) {
        this.instrumentationConfig = new InstrumentationConfigFactory().newClassLoaderBasedInstrumentationConfig(classLoader);
        this.classLoader = classLoader;
    }

    public void instrumentClassFile(File file, File file2) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    logger.debug("instrumentClassFile() >> inputFile: '{}', outputFile: '{}'", file, file2);
                    fileOutputStream.write(instrument(ByteStreams.toByteArray(fileInputStream)));
                    fileOutputStream.close();
                    fileInputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (AlreadyPerfInstrumentedException e) {
            logger.error("Already instrumented class - {}", e.getMessage());
        } catch (Exception e2) {
            logger.error("Can't instrument because of {}. Copying as is.", e2.getMessage());
            Files.copy(file, file2);
        }
    }

    public void instrumentClassesInJar(File file, File file2) throws IOException {
        ZipOutputStream zipOutputStream;
        ZipFile zipFile = new ZipFile(file);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        zipOutputStream = new ZipOutputStream(fileOutputStream);
                    } finally {
                    }
                    while (true) {
                        try {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry != null) {
                                String name = nextEntry.getName();
                                if (!nextEntry.isDirectory()) {
                                    BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(nextEntry));
                                    try {
                                        byte[] byteArray = ByteStreams.toByteArray(bufferedInputStream);
                                        boolean zEndsWith = name.endsWith(".class");
                                        boolean zIsInstrumentable = isInstrumentable(name);
                                        Logger logger2 = logger;
                                        logger2.debug("instrumentClassesInJar() >> entryName: {}, isClassFile: {}, isInstrumentable: {}", name, Boolean.valueOf(zEndsWith), Boolean.valueOf(zIsInstrumentable));
                                        if (zEndsWith && zIsInstrumentable) {
                                            try {
                                                byteArray = instrument(byteArray);
                                            } catch (AlreadyPerfInstrumentedException e) {
                                                logger.error("Already instrumented class - {}", e.getMessage());
                                                bufferedInputStream.close();
                                            } catch (Exception e2) {
                                                logger.error("Can't instrument because of {}. Copying as is.", e2.getMessage());
                                            }
                                        } else {
                                            logger2.debug("Copying '{}' without instrumenting", name);
                                        }
                                        zipOutputStream.putNextEntry(new ZipEntry(name));
                                        zipOutputStream.write(byteArray);
                                        zipOutputStream.closeEntry();
                                        bufferedInputStream.close();
                                    } finally {
                                    }
                                }
                            } else {
                                zipOutputStream.close();
                                fileOutputStream.close();
                                zipInputStream.close();
                                fileInputStream.close();
                                zipFile.close();
                                return;
                            }
                        } finally {
                        }
                    }
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static boolean isInstrumentable(String str) {
        if (FirebasePerfPlugin.getInstrumentFireperfE2ETest() && str.startsWith("com/google/firebase/testing/fireperf")) {
            return true;
        }
        return (str.equals("android") || str.startsWith("android/") || str.startsWith("firebase/perf/") || str.startsWith("com/google/firebase/") || str.startsWith("com/google/firebase/perf/") || str.startsWith("com/google/protobuf/") || str.startsWith("com/google/android/apps/common/proguard/") || str.startsWith("com/google/android/datatransport/") || str.startsWith("com/google/android/gms/") || str.startsWith("com/google/common") || str.startsWith("okhttp3/") || str.startsWith("okio/") || str.toLowerCase(Locale.US).startsWith("meta-inf/")) ? false : true;
    }

    public static boolean isClassInstrumentable(String str) {
        return isInstrumentable(str.replace('.', JsonPointer.SEPARATOR));
    }

    public byte[] instrument(byte[] bArr) {
        ClassReader classReader = new ClassReader(bArr);
        FirebasePerfClassWriter firebasePerfClassWriter = new FirebasePerfClassWriter(3);
        classReader.accept(new InstrumentationVisitor(ASM_API_VERSION, firebasePerfClassWriter, this.instrumentationConfig), new Attribute[]{new PerfInstrumentedAttribute("")}, 4);
        return firebasePerfClassWriter.toByteArray();
    }

    private class FirebasePerfClassWriter extends ClassWriter {
        public FirebasePerfClassWriter(int i) {
            super(i);
        }

        public FirebasePerfClassWriter(ClassReader classReader, int i) {
            super(classReader, i);
        }

        @Override // org.objectweb.asm.ClassWriter
        protected String getCommonSuperClass(String str, String str2) {
            try {
                Class<?> cls = Class.forName(str.replace(JsonPointer.SEPARATOR, '.'), false, Instrument.this.classLoader);
                Class<?> cls2 = Class.forName(str2.replace(JsonPointer.SEPARATOR, '.'), false, Instrument.this.classLoader);
                if (cls.isAssignableFrom(cls2)) {
                    return str;
                }
                if (cls2.isAssignableFrom(cls)) {
                    return str2;
                }
                if (cls.isInterface() || cls2.isInterface()) {
                    return "java/lang/Object";
                }
                do {
                    cls = cls.getSuperclass();
                } while (!cls.isAssignableFrom(cls2));
                return cls.getName().replace('.', JsonPointer.SEPARATOR);
            } catch (Throwable th) {
                Instrument.logger.warn(th.toString());
                return "java/lang/Object";
            }
        }
    }
}
