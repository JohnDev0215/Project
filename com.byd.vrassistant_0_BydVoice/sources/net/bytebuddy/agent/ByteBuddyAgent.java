package net.bytebuddy.agent;

import com.android.multidex.ClassPathElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import kotlin.text.Typography;
import net.bytebuddy.agent.VirtualMachine;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public class ByteBuddyAgent {
    private static final String AGENT_CLASS_PROPERTY = "Agent-Class";
    private static final String ATTACHER_FILE_NAME = "byteBuddyAttacher";
    private static final AttachmentTypeEvaluator ATTACHMENT_TYPE_EVALUATOR = (AttachmentTypeEvaluator) AccessController.doPrivileged(AttachmentTypeEvaluator.InstallationAction.INSTANCE);
    private static final ClassLoader BOOTSTRAP_CLASS_LOADER = null;
    private static final int BUFFER_SIZE = 1024;
    private static final String CAN_REDEFINE_CLASSES_PROPERTY = "Can-Redefine-Classes";
    private static final String CAN_RETRANSFORM_CLASSES_PROPERTY = "Can-Retransform-Classes";
    private static final String CAN_SET_NATIVE_METHOD_PREFIX = "Can-Set-Native-Method-Prefix";
    private static final String CLASS_FILE_EXTENSION = ".class";
    private static final String CLASS_PATH_ARGUMENT = "-cp";
    private static final int END_OF_FILE = -1;
    private static final String INSTRUMENTATION_METHOD = "getInstrumentation";
    private static final String JAR_FILE_EXTENSION = ".jar";
    private static final String JAVA_HOME = "java.home";
    private static final String MANIFEST_VERSION_VALUE = "1.0";
    private static final String OS_NAME = "os.name";
    private static final int START_INDEX = 0;
    private static final Object STATIC_MEMBER = null;
    private static final int SUCCESSFUL_ATTACH = 0;
    private static final Instrumentation UNAVAILABLE = null;
    private static final String WITHOUT_ARGUMENT = null;

    private ByteBuddyAgent() {
        throw new UnsupportedOperationException();
    }

    public static Instrumentation getInstrumentation() {
        Instrumentation instrumentationDoGetInstrumentation = doGetInstrumentation();
        if (instrumentationDoGetInstrumentation != null) {
            return instrumentationDoGetInstrumentation;
        }
        throw new IllegalStateException("The Byte Buddy agent is not initialized");
    }

    public static void attach(File file, String str) {
        attach(file, str, WITHOUT_ARGUMENT);
    }

    public static void attach(File file, String str, String str2) {
        attach(file, str, str2, AttachmentProvider.DEFAULT);
    }

    public static void attach(File file, String str, AttachmentProvider attachmentProvider) {
        attach(file, str, WITHOUT_ARGUMENT, attachmentProvider);
    }

    public static void attach(File file, String str, String str2, AttachmentProvider attachmentProvider) {
        install(attachmentProvider, str, str2, new AgentProvider.ForExistingAgent(file));
    }

    public static void attach(File file, ProcessProvider processProvider) {
        attach(file, processProvider, WITHOUT_ARGUMENT);
    }

    public static void attach(File file, ProcessProvider processProvider, String str) {
        attach(file, processProvider, str, AttachmentProvider.DEFAULT);
    }

    public static void attach(File file, ProcessProvider processProvider, AttachmentProvider attachmentProvider) {
        attach(file, processProvider, WITHOUT_ARGUMENT, attachmentProvider);
    }

    public static void attach(File file, ProcessProvider processProvider, String str, AttachmentProvider attachmentProvider) {
        install(attachmentProvider, processProvider.resolve(), str, new AgentProvider.ForExistingAgent(file));
    }

    public static Instrumentation install() {
        return install(AttachmentProvider.DEFAULT);
    }

    public static Instrumentation install(AttachmentProvider attachmentProvider) {
        return install(attachmentProvider, ProcessProvider.ForCurrentVm.INSTANCE);
    }

    public static Instrumentation install(ProcessProvider processProvider) {
        return install(AttachmentProvider.DEFAULT, processProvider);
    }

    public static synchronized Instrumentation install(AttachmentProvider attachmentProvider, ProcessProvider processProvider) {
        Instrumentation instrumentationDoGetInstrumentation = doGetInstrumentation();
        if (instrumentationDoGetInstrumentation != null) {
            return instrumentationDoGetInstrumentation;
        }
        install(attachmentProvider, processProvider.resolve(), WITHOUT_ARGUMENT, AgentProvider.ForByteBuddyAgent.INSTANCE);
        return doGetInstrumentation();
    }

    private static void install(AttachmentProvider attachmentProvider, String str, String str2, AgentProvider agentProvider) {
        AttachmentProvider.Accessor accessorAttempt = attachmentProvider.attempt();
        if (!accessorAttempt.isAvailable()) {
            throw new IllegalStateException("No compatible attachment provider is not available");
        }
        try {
            if (ATTACHMENT_TYPE_EVALUATOR.requiresExternalAttachment(str)) {
                installExternal(accessorAttempt.getExternalAttachment(), str, agentProvider.resolve(), str2);
            } else {
                Attacher.install(accessorAttempt.getVirtualMachineType(), str, agentProvider.resolve().getAbsolutePath(), str2);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("Error during attachment using: " + attachmentProvider, e2);
        }
    }

    private static void installExternal(AttachmentProvider.Accessor.ExternalAttachment externalAttachment, String str, File file, String str2) throws Exception {
        String str3 = "";
        InputStream resourceAsStream = Attacher.class.getResourceAsStream("/" + Attacher.class.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ".class");
        if (resourceAsStream == null) {
            throw new IllegalStateException("Cannot locate class file for Byte Buddy installation process");
        }
        File fileCreateTempFile = null;
        try {
            try {
                fileCreateTempFile = File.createTempFile(ATTACHER_FILE_NAME, JAR_FILE_EXTENSION);
                JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(fileCreateTempFile));
                try {
                    jarOutputStream.putNextEntry(new JarEntry(Attacher.class.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ".class"));
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = resourceAsStream.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            jarOutputStream.write(bArr, 0, i);
                        }
                    }
                    jarOutputStream.closeEntry();
                    resourceAsStream.close();
                    StringBuilder sbAppend = new StringBuilder().append(quote(fileCreateTempFile.getCanonicalPath()));
                    Iterator<File> it = externalAttachment.getClassPath().iterator();
                    while (it.hasNext()) {
                        sbAppend.append(File.pathSeparatorChar).append(quote(it.next().getCanonicalPath()));
                    }
                    String[] strArr = new String[8];
                    strArr[0] = quote(System.getProperty(JAVA_HOME) + File.separatorChar + "bin" + File.separatorChar + (System.getProperty(OS_NAME, "").toLowerCase(Locale.US).contains("windows") ? "java.exe" : "java"));
                    strArr[1] = CLASS_PATH_ARGUMENT;
                    strArr[2] = sbAppend.toString();
                    strArr[3] = Attacher.class.getName();
                    strArr[4] = externalAttachment.getVirtualMachineType();
                    strArr[5] = str;
                    strArr[6] = quote(file.getAbsolutePath());
                    if (str2 != null) {
                        str3 = "=" + str2;
                    }
                    strArr[7] = str3;
                    if (new ProcessBuilder(strArr).start().waitFor() != 0) {
                        throw new IllegalStateException("Could not self-attach to current VM using external process");
                    }
                } finally {
                    jarOutputStream.close();
                }
            } finally {
                if (fileCreateTempFile != null && !fileCreateTempFile.delete()) {
                    fileCreateTempFile.deleteOnExit();
                }
            }
        } catch (Throwable th) {
            resourceAsStream.close();
            throw th;
        }
    }

    private static String quote(String str) {
        return str.contains(" ") ? "\"" + str + Typography.quote : str;
    }

    private static Instrumentation doGetInstrumentation() {
        try {
            return (Instrumentation) ClassLoader.getSystemClassLoader().loadClass(Installer.class.getName()).getMethod(INSTRUMENTATION_METHOD, new Class[0]).invoke(STATIC_MEMBER, new Object[0]);
        } catch (Exception unused) {
            return UNAVAILABLE;
        }
    }

    public interface AttachmentProvider {
        public static final AttachmentProvider DEFAULT = new Compound(ForJigsawVm.INSTANCE, ForJ9Vm.INSTANCE, ForToolsJarVm.JVM_ROOT, ForToolsJarVm.JDK_ROOT, ForToolsJarVm.MACINTOSH, ForUnixHotSpotVm.INSTANCE);

        Accessor attempt();

        public interface Accessor {
            public static final String VIRTUAL_MACHINE_TYPE_NAME = "com.sun.tools.attach.VirtualMachine";
            public static final String VIRTUAL_MACHINE_TYPE_NAME_J9 = "com.ibm.tools.attach.VirtualMachine";

            ExternalAttachment getExternalAttachment();

            Class<?> getVirtualMachineType();

            boolean isAvailable();

            public enum Unavailable implements Accessor {
                INSTANCE;

                @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor
                public boolean isAvailable() {
                    return false;
                }

                @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor
                public Class<?> getVirtualMachineType() {
                    throw new IllegalStateException("Cannot read the virtual machine type for an unavailable accessor");
                }

                @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor
                public ExternalAttachment getExternalAttachment() {
                    throw new IllegalStateException("Cannot read the virtual machine type for an unavailable accessor");
                }
            }

            public static class ExternalAttachment {
                private final List<File> classPath;
                private final String virtualMachineType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ExternalAttachment;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ExternalAttachment)) {
                        return false;
                    }
                    ExternalAttachment externalAttachment = (ExternalAttachment) obj;
                    if (!externalAttachment.canEqual(this)) {
                        return false;
                    }
                    String virtualMachineType = getVirtualMachineType();
                    String virtualMachineType2 = externalAttachment.getVirtualMachineType();
                    if (virtualMachineType != null ? !virtualMachineType.equals(virtualMachineType2) : virtualMachineType2 != null) {
                        return false;
                    }
                    List<File> classPath = getClassPath();
                    List<File> classPath2 = externalAttachment.getClassPath();
                    return classPath != null ? classPath.equals(classPath2) : classPath2 == null;
                }

                public int hashCode() {
                    String virtualMachineType = getVirtualMachineType();
                    int iHashCode = virtualMachineType == null ? 43 : virtualMachineType.hashCode();
                    List<File> classPath = getClassPath();
                    return ((iHashCode + 59) * 59) + (classPath != null ? classPath.hashCode() : 43);
                }

                public ExternalAttachment(String str, List<File> list) {
                    this.virtualMachineType = str;
                    this.classPath = list;
                }

                public String getVirtualMachineType() {
                    return this.virtualMachineType;
                }

                public List<File> getClassPath() {
                    return this.classPath;
                }
            }

            public static abstract class Simple implements Accessor {
                protected final Class<?> virtualMachineType;

                @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor
                public boolean isAvailable() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Simple;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Simple)) {
                        return false;
                    }
                    Simple simple = (Simple) obj;
                    if (!simple.canEqual(this)) {
                        return false;
                    }
                    Class<?> virtualMachineType = getVirtualMachineType();
                    Class<?> virtualMachineType2 = simple.getVirtualMachineType();
                    return virtualMachineType != null ? virtualMachineType.equals(virtualMachineType2) : virtualMachineType2 == null;
                }

                public int hashCode() {
                    Class<?> virtualMachineType = getVirtualMachineType();
                    return 59 + (virtualMachineType == null ? 43 : virtualMachineType.hashCode());
                }

                protected Simple(Class<?> cls) {
                    this.virtualMachineType = cls;
                }

                public static Accessor of(ClassLoader classLoader, File... fileArr) {
                    try {
                        return new WithExternalAttachment(classLoader.loadClass(Accessor.VIRTUAL_MACHINE_TYPE_NAME), Arrays.asList(fileArr));
                    } catch (ClassNotFoundException unused) {
                        return Unavailable.INSTANCE;
                    }
                }

                public static Accessor ofJ9() {
                    try {
                        return new WithExternalAttachment(ClassLoader.getSystemClassLoader().loadClass(Accessor.VIRTUAL_MACHINE_TYPE_NAME_J9), Collections.emptyList());
                    } catch (ClassNotFoundException unused) {
                        return Unavailable.INSTANCE;
                    }
                }

                @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor
                public Class<?> getVirtualMachineType() {
                    return this.virtualMachineType;
                }

                protected static class WithExternalAttachment extends Simple {
                    private final List<File> classPath;

                    @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor.Simple
                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithExternalAttachment;
                    }

                    @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor.Simple
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithExternalAttachment)) {
                            return false;
                        }
                        WithExternalAttachment withExternalAttachment = (WithExternalAttachment) obj;
                        if (!withExternalAttachment.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        List<File> list = this.classPath;
                        List<File> list2 = withExternalAttachment.classPath;
                        return list != null ? list.equals(list2) : list2 == null;
                    }

                    @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor.Simple
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        List<File> list = this.classPath;
                        return (iHashCode * 59) + (list == null ? 43 : list.hashCode());
                    }

                    public WithExternalAttachment(Class<?> cls, List<File> list) {
                        super(cls);
                        this.classPath = list;
                    }

                    @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor
                    public ExternalAttachment getExternalAttachment() {
                        return new ExternalAttachment(this.virtualMachineType.getName(), this.classPath);
                    }
                }

                protected static class WithoutExternalAttachment extends Simple {
                    @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor.Simple
                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithoutExternalAttachment;
                    }

                    @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor.Simple
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        return (obj instanceof WithoutExternalAttachment) && ((WithoutExternalAttachment) obj).canEqual(this) && super.equals(obj);
                    }

                    @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor.Simple
                    public int hashCode() {
                        return 59 + super.hashCode();
                    }

                    public WithoutExternalAttachment(Class<?> cls) {
                        super(cls);
                    }

                    @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor
                    public ExternalAttachment getExternalAttachment() {
                        throw new IllegalStateException("Cannot read the virtual machine type for an unavailable accessor");
                    }
                }
            }
        }

        public enum ForJigsawVm implements AttachmentProvider {
            INSTANCE;

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider
            public Accessor attempt() {
                return Accessor.Simple.of(ClassLoader.getSystemClassLoader(), new File[0]);
            }
        }

        public enum ForJ9Vm implements AttachmentProvider {
            INSTANCE;

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider
            public Accessor attempt() {
                return Accessor.Simple.ofJ9();
            }
        }

        public enum ForToolsJarVm implements AttachmentProvider {
            JVM_ROOT("../lib/tools.jar"),
            JDK_ROOT("lib/tools.jar"),
            MACINTOSH("../Classes/classes.jar");

            private static final String JAVA_HOME_PROPERTY = "java.home";
            private final String toolsJarPath;

            ForToolsJarVm(String str) {
                this.toolsJarPath = str;
            }

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider
            public Accessor attempt() {
                File file = new File(System.getProperty(JAVA_HOME_PROPERTY), this.toolsJarPath);
                try {
                    return (file.isFile() && file.canRead()) ? Accessor.Simple.of(new URLClassLoader(new URL[]{file.toURI().toURL()}, ByteBuddyAgent.BOOTSTRAP_CLASS_LOADER), file) : Accessor.Unavailable.INSTANCE;
                } catch (MalformedURLException unused) {
                    throw new IllegalStateException("Could not represent " + file + " as URL");
                }
            }
        }

        public enum ForUnixHotSpotVm implements AttachmentProvider {
            INSTANCE;

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider
            public Accessor attempt() {
                try {
                    return new Accessor.Simple.WithoutExternalAttachment(VirtualMachine.ForHotSpot.OnUnix.assertAvailability());
                } catch (Throwable unused) {
                    return Accessor.Unavailable.INSTANCE;
                }
            }
        }

        public static class Compound implements AttachmentProvider {
            private final List<AttachmentProvider> attachmentProviders;

            protected boolean canEqual(Object obj) {
                return obj instanceof Compound;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Compound)) {
                    return false;
                }
                Compound compound = (Compound) obj;
                if (!compound.canEqual(this)) {
                    return false;
                }
                List<AttachmentProvider> list = this.attachmentProviders;
                List<AttachmentProvider> list2 = compound.attachmentProviders;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<AttachmentProvider> list = this.attachmentProviders;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            public Compound(AttachmentProvider... attachmentProviderArr) {
                this((List<? extends AttachmentProvider>) Arrays.asList(attachmentProviderArr));
            }

            public Compound(List<? extends AttachmentProvider> list) {
                this.attachmentProviders = new ArrayList();
                for (AttachmentProvider attachmentProvider : list) {
                    if (attachmentProvider instanceof Compound) {
                        this.attachmentProviders.addAll(((Compound) attachmentProvider).attachmentProviders);
                    } else {
                        this.attachmentProviders.add(attachmentProvider);
                    }
                }
            }

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider
            public Accessor attempt() {
                Iterator<AttachmentProvider> it = this.attachmentProviders.iterator();
                while (it.hasNext()) {
                    Accessor accessorAttempt = it.next().attempt();
                    if (accessorAttempt.isAvailable()) {
                        return accessorAttempt;
                    }
                }
                return Accessor.Unavailable.INSTANCE;
            }
        }
    }

    public interface ProcessProvider {
        String resolve();

        public enum ForCurrentVm implements ProcessProvider {
            INSTANCE;

            private final ProcessProvider dispatcher = ForJava9CapableVm.make();

            ForCurrentVm() {
            }

            @Override // net.bytebuddy.agent.ByteBuddyAgent.ProcessProvider
            public String resolve() {
                return this.dispatcher.resolve();
            }

            protected enum ForLegacyVm implements ProcessProvider {
                INSTANCE;

                @Override // net.bytebuddy.agent.ByteBuddyAgent.ProcessProvider
                public String resolve() {
                    String name = ManagementFactory.getRuntimeMXBean().getName();
                    int iIndexOf = name.indexOf(64);
                    if (iIndexOf == -1) {
                        throw new IllegalStateException("Cannot extract process id from runtime management bean");
                    }
                    return name.substring(0, iIndexOf);
                }
            }

            protected static class ForJava9CapableVm implements ProcessProvider {
                private final Method current;
                private final Method pid;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForJava9CapableVm;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForJava9CapableVm)) {
                        return false;
                    }
                    ForJava9CapableVm forJava9CapableVm = (ForJava9CapableVm) obj;
                    if (!forJava9CapableVm.canEqual(this)) {
                        return false;
                    }
                    Method method = this.current;
                    Method method2 = forJava9CapableVm.current;
                    if (method != null ? !method.equals(method2) : method2 != null) {
                        return false;
                    }
                    Method method3 = this.pid;
                    Method method4 = forJava9CapableVm.pid;
                    return method3 != null ? method3.equals(method4) : method4 == null;
                }

                public int hashCode() {
                    Method method = this.current;
                    int iHashCode = method == null ? 43 : method.hashCode();
                    Method method2 = this.pid;
                    return ((iHashCode + 59) * 59) + (method2 != null ? method2.hashCode() : 43);
                }

                protected ForJava9CapableVm(Method method, Method method2) {
                    this.current = method;
                    this.pid = method2;
                }

                public static ProcessProvider make() {
                    try {
                        return new ForJava9CapableVm(Class.forName("java.lang.ProcessHandle").getMethod("current", new Class[0]), Class.forName("java.lang.ProcessHandle").getMethod("pid", new Class[0]));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }

                @Override // net.bytebuddy.agent.ByteBuddyAgent.ProcessProvider
                public String resolve() {
                    try {
                        return this.pid.invoke(this.current.invoke(ByteBuddyAgent.STATIC_MEMBER, new Object[0]), new Object[0]).toString();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access Java 9 process API", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error when accessing Java 9 process API", e2.getCause());
                    }
                }
            }
        }
    }

    protected interface AgentProvider {
        File resolve() throws IOException;

        public enum ForByteBuddyAgent implements AgentProvider {
            INSTANCE;

            private static final String AGENT_FILE_NAME = "byteBuddyAgent";
            private static final String JAR_FILE_EXTENSION = ".jar";

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AgentProvider
            public File resolve() throws IOException {
                InputStream resourceAsStream = Installer.class.getResourceAsStream("/" + Installer.class.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ".class");
                if (resourceAsStream == null) {
                    throw new IllegalStateException("Cannot locate class file for Byte Buddy installer");
                }
                try {
                    File fileCreateTempFile = File.createTempFile(AGENT_FILE_NAME, JAR_FILE_EXTENSION);
                    fileCreateTempFile.deleteOnExit();
                    Manifest manifest = new Manifest();
                    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
                    manifest.getMainAttributes().put(new Attributes.Name(ByteBuddyAgent.AGENT_CLASS_PROPERTY), Installer.class.getName());
                    manifest.getMainAttributes().put(new Attributes.Name(ByteBuddyAgent.CAN_REDEFINE_CLASSES_PROPERTY), Boolean.TRUE.toString());
                    manifest.getMainAttributes().put(new Attributes.Name(ByteBuddyAgent.CAN_RETRANSFORM_CLASSES_PROPERTY), Boolean.TRUE.toString());
                    manifest.getMainAttributes().put(new Attributes.Name(ByteBuddyAgent.CAN_SET_NATIVE_METHOD_PREFIX), Boolean.TRUE.toString());
                    JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(fileCreateTempFile), manifest);
                    try {
                        jarOutputStream.putNextEntry(new JarEntry(Installer.class.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ".class"));
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = resourceAsStream.read(bArr);
                            if (i != -1) {
                                jarOutputStream.write(bArr, 0, i);
                            } else {
                                jarOutputStream.closeEntry();
                                return fileCreateTempFile;
                            }
                        }
                    } finally {
                        jarOutputStream.close();
                    }
                } finally {
                    resourceAsStream.close();
                }
            }
        }

        public static class ForExistingAgent implements AgentProvider {
            private File agent;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForExistingAgent;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForExistingAgent)) {
                    return false;
                }
                ForExistingAgent forExistingAgent = (ForExistingAgent) obj;
                if (!forExistingAgent.canEqual(this)) {
                    return false;
                }
                File file = this.agent;
                File file2 = forExistingAgent.agent;
                return file != null ? file.equals(file2) : file2 == null;
            }

            public int hashCode() {
                File file = this.agent;
                return 59 + (file == null ? 43 : file.hashCode());
            }

            protected ForExistingAgent(File file) {
                this.agent = file;
            }

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AgentProvider
            public File resolve() {
                return this.agent;
            }
        }
    }

    protected interface AttachmentTypeEvaluator {

        public enum Disabled implements AttachmentTypeEvaluator {
            INSTANCE;

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentTypeEvaluator
            public boolean requiresExternalAttachment(String str) {
                return false;
            }
        }

        boolean requiresExternalAttachment(String str);

        public enum InstallationAction implements PrivilegedAction<AttachmentTypeEvaluator> {
            INSTANCE;

            private static final String JDK_ALLOW_SELF_ATTACH = "jdk.attach.allowAttachSelf";

            @Override // java.security.PrivilegedAction
            public AttachmentTypeEvaluator run() {
                try {
                    if (Boolean.getBoolean(JDK_ALLOW_SELF_ATTACH)) {
                        return Disabled.INSTANCE;
                    }
                    return new ForJava9CapableVm(Class.forName("java.lang.ProcessHandle").getMethod("current", new Class[0]), Class.forName("java.lang.ProcessHandle").getMethod("pid", new Class[0]));
                } catch (Exception unused) {
                    return Disabled.INSTANCE;
                }
            }
        }

        public static class ForJava9CapableVm implements AttachmentTypeEvaluator {
            private final Method current;
            private final Method pid;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForJava9CapableVm;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForJava9CapableVm)) {
                    return false;
                }
                ForJava9CapableVm forJava9CapableVm = (ForJava9CapableVm) obj;
                if (!forJava9CapableVm.canEqual(this)) {
                    return false;
                }
                Method method = this.current;
                Method method2 = forJava9CapableVm.current;
                if (method != null ? !method.equals(method2) : method2 != null) {
                    return false;
                }
                Method method3 = this.pid;
                Method method4 = forJava9CapableVm.pid;
                return method3 != null ? method3.equals(method4) : method4 == null;
            }

            public int hashCode() {
                Method method = this.current;
                int iHashCode = method == null ? 43 : method.hashCode();
                Method method2 = this.pid;
                return ((iHashCode + 59) * 59) + (method2 != null ? method2.hashCode() : 43);
            }

            protected ForJava9CapableVm(Method method, Method method2) {
                this.current = method;
                this.pid = method2;
            }

            @Override // net.bytebuddy.agent.ByteBuddyAgent.AttachmentTypeEvaluator
            public boolean requiresExternalAttachment(String str) {
                try {
                    return this.pid.invoke(this.current.invoke(ByteBuddyAgent.STATIC_MEMBER, new Object[0]), new Object[0]).toString().equals(str);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access Java 9 process API", e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Error when accessing Java 9 process API", e2.getCause());
                }
            }
        }
    }
}
