package org.apache.tools.ant.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.taskdefs.ManifestException;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class AntClassLoader2 extends AntClassLoader {
    static /* synthetic */ Class class$org$apache$tools$ant$Project;
    private static Map pathMap = Collections.synchronizedMap(new HashMap());
    private FileUtils fileUtils = FileUtils.newFileUtils();

    @Override // org.apache.tools.ant.AntClassLoader
    protected Class defineClassFromData(File container, byte[] classData, String className) throws IOException {
        definePackage(container, className);
        int length = classData.length;
        Class clsClass$ = class$org$apache$tools$ant$Project;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.Project");
            class$org$apache$tools$ant$Project = clsClass$;
        }
        return defineClass(className, classData, 0, length, clsClass$.getProtectionDomain());
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private Manifest getJarManifest(File container) throws IOException {
        if (container.isDirectory()) {
            return null;
        }
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(container);
            Manifest manifest = jarFile.getManifest();
            jarFile.close();
            return manifest;
        } catch (Throwable th) {
            if (jarFile != null) {
                jarFile.close();
            }
            throw th;
        }
    }

    protected void definePackage(File container, String className) throws IOException {
        int classIndex = className.lastIndexOf(46);
        if (classIndex == -1) {
            return;
        }
        String packageName = className.substring(0, classIndex);
        if (getPackage(packageName) != null) {
            return;
        }
        Manifest manifest = getJarManifest(container);
        if (manifest == null) {
            definePackage(packageName, null, null, null, null, null, null, null);
        } else {
            definePackage(container, packageName, manifest);
        }
    }

    protected void definePackage(File container, String packageName, Manifest manifest) {
        String specificationVendor;
        String specificationVersion;
        String implementationTitle;
        String implementationVendor;
        String implementationVersion;
        String sealedString;
        String specificationVendor2;
        URL sealBase;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(packageName.replace('.', '/'));
        stringBuffer.append("/");
        String sectionName = stringBuffer.toString();
        String specificationTitle = null;
        String specificationVendor3 = null;
        String specificationVersion2 = null;
        String implementationTitle2 = null;
        String implementationVendor2 = null;
        String implementationVersion2 = null;
        String sealedString2 = null;
        Attributes sectionAttributes = manifest.getAttributes(sectionName);
        if (sectionAttributes != null) {
            specificationTitle = sectionAttributes.getValue(Attributes.Name.SPECIFICATION_TITLE);
            specificationVendor3 = sectionAttributes.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            specificationVersion2 = sectionAttributes.getValue(Attributes.Name.SPECIFICATION_VERSION);
            implementationTitle2 = sectionAttributes.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            implementationVendor2 = sectionAttributes.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            implementationVersion2 = sectionAttributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            sealedString2 = sectionAttributes.getValue(Attributes.Name.SEALED);
        }
        Attributes mainAttributes = manifest.getMainAttributes();
        if (mainAttributes == null) {
            specificationVendor = specificationVendor3;
            specificationVersion = specificationVersion2;
            implementationTitle = implementationTitle2;
            implementationVendor = implementationVendor2;
            implementationVersion = implementationVersion2;
            sealedString = sealedString2;
            specificationVendor2 = specificationTitle;
        } else {
            if (specificationTitle == null) {
                specificationTitle = mainAttributes.getValue(Attributes.Name.SPECIFICATION_TITLE);
            }
            if (specificationVendor3 == null) {
                specificationVendor3 = mainAttributes.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            }
            if (specificationVersion2 == null) {
                specificationVersion2 = mainAttributes.getValue(Attributes.Name.SPECIFICATION_VERSION);
            }
            if (implementationTitle2 == null) {
                implementationTitle2 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            }
            if (implementationVendor2 == null) {
                implementationVendor2 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            }
            if (implementationVersion2 == null) {
                implementationVersion2 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            }
            if (sealedString2 != null) {
                specificationVendor = specificationVendor3;
                specificationVersion = specificationVersion2;
                implementationTitle = implementationTitle2;
                implementationVendor = implementationVendor2;
                implementationVersion = implementationVersion2;
                sealedString = sealedString2;
                specificationVendor2 = specificationTitle;
            } else {
                String sealedString3 = mainAttributes.getValue(Attributes.Name.SEALED);
                specificationVendor = specificationVendor3;
                specificationVersion = specificationVersion2;
                implementationTitle = implementationTitle2;
                implementationVendor = implementationVendor2;
                implementationVersion = implementationVersion2;
                sealedString = sealedString3;
                specificationVendor2 = specificationTitle;
            }
        }
        if (sealedString != null && sealedString.equalsIgnoreCase("true")) {
            try {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("file:");
                stringBuffer2.append(container.getPath());
                sealBase = new URL(stringBuffer2.toString());
            } catch (MalformedURLException e) {
                sealBase = null;
            }
        } else {
            sealBase = null;
        }
        definePackage(packageName, specificationVendor2, specificationVersion, specificationVendor, implementationTitle, implementationVersion, implementationVendor, sealBase);
    }

    @Override // org.apache.tools.ant.AntClassLoader
    protected void addPathFile(File pathComponent) throws IOException {
        super.addPathFile(pathComponent);
        if (pathComponent.isDirectory()) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(pathComponent.getAbsolutePath());
        stringBuffer.append(pathComponent.lastModified());
        stringBuffer.append("-");
        stringBuffer.append(pathComponent.length());
        String absPathPlusTimeAndLength = stringBuffer.toString();
        String classpath = (String) pathMap.get(absPathPlusTimeAndLength);
        if (classpath == null) {
            ZipFile jarFile = null;
            InputStream manifestStream = null;
            try {
                jarFile = new ZipFile(pathComponent);
                manifestStream = jarFile.getInputStream(new ZipEntry("META-INF/MANIFEST.MF"));
                if (manifestStream == null) {
                    if (manifestStream != null) {
                        manifestStream.close();
                    }
                    jarFile.close();
                    return;
                } else {
                    Reader manifestReader = new InputStreamReader(manifestStream, "UTF-8");
                    org.apache.tools.ant.taskdefs.Manifest manifest = new org.apache.tools.ant.taskdefs.Manifest(manifestReader);
                    classpath = manifest.getMainSection().getAttributeValue(org.apache.tools.ant.taskdefs.Manifest.ATTRIBUTE_CLASSPATH);
                    if (manifestStream != null) {
                        manifestStream.close();
                    }
                    jarFile.close();
                }
            } catch (ManifestException e) {
                if (manifestStream != null) {
                    manifestStream.close();
                }
                if (jarFile != null) {
                    jarFile.close();
                }
            } catch (Throwable th) {
                if (manifestStream != null) {
                    manifestStream.close();
                }
                if (jarFile != null) {
                    jarFile.close();
                }
                throw th;
            }
            if (classpath == null) {
                classpath = "";
            }
            pathMap.put(absPathPlusTimeAndLength, classpath);
        }
        if ("".equals(classpath)) {
            return;
        }
        URL baseURL = this.fileUtils.getFileURL(pathComponent);
        StringTokenizer st = new StringTokenizer(classpath);
        while (st.hasMoreTokens()) {
            String classpathElement = st.nextToken();
            URL libraryURL = new URL(baseURL, classpathElement);
            if (libraryURL.getProtocol().equals(TypeSelector.FileType.FILE)) {
                File libraryFile = new File(libraryURL.getFile());
                if (libraryFile.exists() && !isInPath(libraryFile)) {
                    addPathFile(libraryFile);
                }
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Skipping jar library ");
                stringBuffer2.append(classpathElement);
                stringBuffer2.append(" since only relative URLs are supported by this");
                stringBuffer2.append(" loader");
                log(stringBuffer2.toString(), 3);
            }
        }
    }
}
