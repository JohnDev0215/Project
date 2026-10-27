package net.bytebuddy.dynamic.loading;

import com.android.multidex.ClassPathElement;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public interface PackageDefinitionStrategy {
    Definition define(ClassLoader classLoader, String str, String str2);

    public enum NoOp implements PackageDefinitionStrategy {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public Definition define(ClassLoader classLoader, String str, String str2) {
            return Definition.Undefined.INSTANCE;
        }
    }

    public enum Trivial implements PackageDefinitionStrategy {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public Definition define(ClassLoader classLoader, String str, String str2) {
            return Definition.Trivial.INSTANCE;
        }
    }

    public interface Definition {
        String getImplementationTitle();

        String getImplementationVendor();

        String getImplementationVersion();

        URL getSealBase();

        String getSpecificationTitle();

        String getSpecificationVendor();

        String getSpecificationVersion();

        boolean isCompatibleTo(Package r1);

        boolean isDefined();

        public enum Undefined implements Definition {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isDefined() {
                return false;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationTitle() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationVersion() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationVendor() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationTitle() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVersion() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVendor() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public URL getSealBase() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isCompatibleTo(Package r2) {
                throw new IllegalStateException("Cannot check compatibility to undefined package");
            }
        }

        public enum Trivial implements Definition {
            INSTANCE;

            private static final String NO_VALUE = null;
            private static final URL NOT_SEALED = null;

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isCompatibleTo(Package r1) {
                return true;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isDefined() {
                return true;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationTitle() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationVersion() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationVendor() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationTitle() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVersion() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVendor() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public URL getSealBase() {
                return NOT_SEALED;
            }
        }

        public static class Simple implements Definition {
            private final String implementationTitle;
            private final String implementationVendor;
            private final String implementationVersion;
            protected final URL sealBase;
            private final String specificationTitle;
            private final String specificationVendor;
            private final String specificationVersion;

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isDefined() {
                return true;
            }

            public Simple(String str, String str2, String str3, String str4, String str5, String str6, URL url) {
                this.specificationTitle = str;
                this.specificationVersion = str2;
                this.specificationVendor = str3;
                this.implementationTitle = str4;
                this.implementationVersion = str5;
                this.implementationVendor = str6;
                this.sealBase = url;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationTitle() {
                return this.specificationTitle;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationVersion() {
                return this.specificationVersion;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationVendor() {
                return this.specificationVendor;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationTitle() {
                return this.implementationTitle;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVersion() {
                return this.implementationVersion;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVendor() {
                return this.implementationVendor;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public URL getSealBase() {
                return this.sealBase;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isCompatibleTo(Package r2) {
                URL url = this.sealBase;
                if (url == null) {
                    return !r2.isSealed();
                }
                return r2.isSealed(url);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Simple simple = (Simple) obj;
                String str = this.specificationTitle;
                if (str == null ? simple.specificationTitle == null : str.equals(simple.specificationTitle)) {
                    String str2 = this.specificationVersion;
                    if (str2 == null ? simple.specificationVersion == null : str2.equals(simple.specificationVersion)) {
                        String str3 = this.specificationVendor;
                        if (str3 == null ? simple.specificationVendor == null : str3.equals(simple.specificationVendor)) {
                            String str4 = this.implementationTitle;
                            if (str4 == null ? simple.implementationTitle == null : str4.equals(simple.implementationTitle)) {
                                String str5 = this.implementationVersion;
                                if (str5 == null ? simple.implementationVersion == null : str5.equals(simple.implementationVersion)) {
                                    String str6 = this.implementationVendor;
                                    if (str6 == null ? simple.implementationVendor == null : str6.equals(simple.implementationVendor)) {
                                        URL url = this.sealBase;
                                        if (url != null) {
                                            if (url.equals(simple.sealBase)) {
                                                return true;
                                            }
                                        } else if (simple.sealBase == null) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }

            public int hashCode() {
                String str = this.specificationTitle;
                int iHashCode = (str != null ? str.hashCode() : 0) * 31;
                String str2 = this.specificationVersion;
                int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
                String str3 = this.specificationVendor;
                int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
                String str4 = this.implementationTitle;
                int iHashCode4 = (iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
                String str5 = this.implementationVersion;
                int iHashCode5 = (iHashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
                String str6 = this.implementationVendor;
                int iHashCode6 = (iHashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
                URL url = this.sealBase;
                return iHashCode6 + (url != null ? url.hashCode() : 0);
            }
        }
    }

    public static class ManifestReading implements PackageDefinitionStrategy {
        private static final Attributes.Name[] ATTRIBUTE_NAMES = {Attributes.Name.SPECIFICATION_TITLE, Attributes.Name.SPECIFICATION_VERSION, Attributes.Name.SPECIFICATION_VENDOR, Attributes.Name.IMPLEMENTATION_TITLE, Attributes.Name.IMPLEMENTATION_VERSION, Attributes.Name.IMPLEMENTATION_VENDOR, Attributes.Name.SEALED};
        private static final String MANIFEST_FILE = "/META-INF/MANIFEST.MF";
        private static final URL NOT_SEALED = null;
        private final SealBaseLocator sealBaseLocator;

        protected boolean canEqual(Object obj) {
            return obj instanceof ManifestReading;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ManifestReading)) {
                return false;
            }
            ManifestReading manifestReading = (ManifestReading) obj;
            if (!manifestReading.canEqual(this)) {
                return false;
            }
            SealBaseLocator sealBaseLocator = this.sealBaseLocator;
            SealBaseLocator sealBaseLocator2 = manifestReading.sealBaseLocator;
            return sealBaseLocator != null ? sealBaseLocator.equals(sealBaseLocator2) : sealBaseLocator2 == null;
        }

        public int hashCode() {
            SealBaseLocator sealBaseLocator = this.sealBaseLocator;
            return 59 + (sealBaseLocator == null ? 43 : sealBaseLocator.hashCode());
        }

        public ManifestReading() {
            this(new SealBaseLocator.ForTypeResourceUrl());
        }

        public ManifestReading(SealBaseLocator sealBaseLocator) {
            this.sealBaseLocator = sealBaseLocator;
        }

        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public Definition define(ClassLoader classLoader, String str, String str2) {
            InputStream resourceAsStream = classLoader.getResourceAsStream(MANIFEST_FILE);
            if (resourceAsStream != null) {
                try {
                    try {
                        Manifest manifest = new Manifest(resourceAsStream);
                        HashMap map = new HashMap();
                        Attributes mainAttributes = manifest.getMainAttributes();
                        if (mainAttributes != null) {
                            for (Attributes.Name name : ATTRIBUTE_NAMES) {
                                map.put(name, mainAttributes.getValue(name));
                            }
                        }
                        Attributes attributes = manifest.getAttributes(str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR).concat("/"));
                        if (attributes != null) {
                            for (Attributes.Name name2 : ATTRIBUTE_NAMES) {
                                String value = attributes.getValue(name2);
                                if (value != null) {
                                    map.put(name2, value);
                                }
                            }
                        }
                        return new Definition.Simple((String) map.get(Attributes.Name.SPECIFICATION_TITLE), (String) map.get(Attributes.Name.SPECIFICATION_VERSION), (String) map.get(Attributes.Name.SPECIFICATION_VENDOR), (String) map.get(Attributes.Name.IMPLEMENTATION_TITLE), (String) map.get(Attributes.Name.IMPLEMENTATION_VERSION), (String) map.get(Attributes.Name.IMPLEMENTATION_VENDOR), Boolean.parseBoolean((String) map.get(Attributes.Name.SEALED)) ? this.sealBaseLocator.findSealBase(classLoader, str2) : NOT_SEALED);
                    } finally {
                        resourceAsStream.close();
                    }
                } catch (IOException e) {
                    throw new IllegalStateException("Error while reading manifest file", e);
                }
            }
            return Definition.Trivial.INSTANCE;
        }

        public interface SealBaseLocator {
            URL findSealBase(ClassLoader classLoader, String str);

            public enum NonSealing implements SealBaseLocator {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                public URL findSealBase(ClassLoader classLoader, String str) {
                    return ManifestReading.NOT_SEALED;
                }
            }

            public static class ForFixedValue implements SealBaseLocator {
                private final URL sealBase;

                public ForFixedValue(URL url) {
                    this.sealBase = url;
                }

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                public URL findSealBase(ClassLoader classLoader, String str) {
                    return this.sealBase;
                }

                public boolean equals(Object obj) {
                    return this == obj || (obj != null && getClass() == obj.getClass() && this.sealBase.equals(((ForFixedValue) obj).sealBase));
                }

                public int hashCode() {
                    return this.sealBase.hashCode();
                }
            }

            public static class ForTypeResourceUrl implements SealBaseLocator {
                private static final String CLASS_FILE_EXTENSION = ".class";
                private static final int EXCLUDE_INITIAL_SLASH = 1;
                private static final String FILE_SYSTEM = "file";
                private static final String JAR_FILE = "jar";
                private static final String RUNTIME_IMAGE = "jrt";
                private final SealBaseLocator fallback;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForTypeResourceUrl;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForTypeResourceUrl)) {
                        return false;
                    }
                    ForTypeResourceUrl forTypeResourceUrl = (ForTypeResourceUrl) obj;
                    if (!forTypeResourceUrl.canEqual(this)) {
                        return false;
                    }
                    SealBaseLocator sealBaseLocator = this.fallback;
                    SealBaseLocator sealBaseLocator2 = forTypeResourceUrl.fallback;
                    return sealBaseLocator != null ? sealBaseLocator.equals(sealBaseLocator2) : sealBaseLocator2 == null;
                }

                public int hashCode() {
                    SealBaseLocator sealBaseLocator = this.fallback;
                    return 59 + (sealBaseLocator == null ? 43 : sealBaseLocator.hashCode());
                }

                public ForTypeResourceUrl() {
                    this(NonSealing.INSTANCE);
                }

                public ForTypeResourceUrl(SealBaseLocator sealBaseLocator) {
                    this.fallback = sealBaseLocator;
                }

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                public URL findSealBase(ClassLoader classLoader, String str) {
                    URL resource = classLoader.getResource(str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ".class");
                    if (resource != null) {
                        try {
                            if (resource.getProtocol().equals("jar")) {
                                return new URL(resource.getPath().substring(0, resource.getPath().indexOf(33)));
                            }
                            if (resource.getProtocol().equals(FILE_SYSTEM)) {
                                return resource;
                            }
                            if (resource.getProtocol().equals(RUNTIME_IMAGE)) {
                                String path = resource.getPath();
                                int iIndexOf = path.indexOf(47, 1);
                                return iIndexOf == -1 ? resource : new URL("jrt:" + path.substring(0, iIndexOf));
                            }
                        } catch (MalformedURLException e) {
                            throw new IllegalStateException("Unexpected URL: " + resource, e);
                        }
                    }
                    return this.fallback.findSealBase(classLoader, str);
                }
            }
        }
    }
}
