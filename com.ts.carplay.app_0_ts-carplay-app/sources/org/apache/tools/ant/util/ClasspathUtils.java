package org.apache.tools.ant.util;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* JADX INFO: loaded from: classes.dex */
public class ClasspathUtils {
    private static final String LOADER_ID_PREFIX = "ant.loader.";
    public static final String REUSE_LOADER_REF = "ant.reuse.loader";

    public static ClassLoader getClassLoaderForPath(Project p, Reference ref) {
        return getClassLoaderForPath(p, ref, false);
    }

    public static ClassLoader getClassLoaderForPath(Project p, Reference ref, boolean reverseLoader) {
        String pathId = ref.getRefId();
        Object path = p.getReference(pathId);
        if (!(path instanceof Path)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The specified classpathref ");
            stringBuffer.append(pathId);
            stringBuffer.append(" does not reference a Path.");
            throw new BuildException(stringBuffer.toString());
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(LOADER_ID_PREFIX);
        stringBuffer2.append(pathId);
        String loaderId = stringBuffer2.toString();
        return getClassLoaderForPath(p, (Path) path, loaderId, reverseLoader);
    }

    public static ClassLoader getClassLoaderForPath(Project p, Path path, String loaderId) {
        return getClassLoaderForPath(p, path, loaderId, false);
    }

    public static ClassLoader getClassLoaderForPath(Project p, Path path, String loaderId, boolean reverseLoader) {
        return getClassLoaderForPath(p, path, loaderId, reverseLoader, isMagicPropertySet(p));
    }

    public static ClassLoader getClassLoaderForPath(Project p, Path path, String loaderId, boolean reverseLoader, boolean reuseLoader) {
        ClassLoader cl = null;
        if (loaderId != null && reuseLoader) {
            Object reusedLoader = p.getReference(loaderId);
            if (reusedLoader != null && !(reusedLoader instanceof ClassLoader)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The specified loader id ");
                stringBuffer.append(loaderId);
                stringBuffer.append(" does not reference a class loader");
                throw new BuildException(stringBuffer.toString());
            }
            cl = (ClassLoader) reusedLoader;
        }
        if (cl == null) {
            cl = getUniqueClassLoaderForPath(p, path, reverseLoader);
            if (loaderId != null && reuseLoader) {
                p.addReference(loaderId, cl);
            }
        }
        return cl;
    }

    public static ClassLoader getUniqueClassLoaderForPath(Project p, Path path, boolean reverseLoader) {
        AntClassLoader acl = p.createClassLoader(path);
        if (reverseLoader) {
            acl.setParentFirst(false);
            acl.addJavaLibraries();
        }
        return acl;
    }

    public static Object newInstance(String className, ClassLoader userDefinedLoader) {
        InstantiationException e;
        try {
            try {
            } catch (IllegalAccessException e2) {
                e = e2;
            } catch (InstantiationException e3) {
                e = e3;
            }
            try {
                Object o = userDefinedLoader.loadClass(className).newInstance();
                return o;
            } catch (IllegalAccessException e4) {
                e = e4;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not instantiate ");
                stringBuffer.append(className);
                stringBuffer.append(". Specified class should have a ");
                stringBuffer.append("public constructor.");
                throw new BuildException(stringBuffer.toString(), e);
            } catch (InstantiationException e5) {
                e = e5;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Could not instantiate ");
                stringBuffer2.append(className);
                stringBuffer2.append(". Specified class should have a no ");
                stringBuffer2.append("argument constructor.");
                throw new BuildException(stringBuffer2.toString(), e);
            }
        } catch (ClassNotFoundException e6) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Class ");
            stringBuffer3.append(className);
            stringBuffer3.append(" not found by the specific classLoader.");
            throw new BuildException(stringBuffer3.toString(), e6);
        }
    }

    public static Delegate getDelegate(ProjectComponent component) {
        return new Delegate(component);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMagicPropertySet(Project p) {
        return p.getProperty(REUSE_LOADER_REF) != null;
    }

    public static class Delegate {
        private String className;
        private Path classpath;
        private String classpathId;
        private final ProjectComponent component;
        private String loaderId;
        private boolean reverseLoader = false;

        Delegate(ProjectComponent component) {
            this.component = component;
        }

        public void setClasspath(Path classpath) {
            Path path = this.classpath;
            if (path == null) {
                this.classpath = classpath;
            } else {
                path.append(classpath);
            }
        }

        public Path createClasspath() {
            if (this.classpath == null) {
                this.classpath = new Path(this.component.getProject());
            }
            return this.classpath.createPath();
        }

        public void setClassname(String fcqn) {
            this.className = fcqn;
        }

        public void setClasspathref(Reference r) {
            this.classpathId = r.getRefId();
            createClasspath().setRefid(r);
        }

        public void setReverseLoader(boolean reverseLoader) {
            this.reverseLoader = reverseLoader;
        }

        public void setLoaderRef(Reference r) {
            this.loaderId = r.getRefId();
        }

        public ClassLoader getClassLoader() {
            ClassLoader cl = ClasspathUtils.getClassLoaderForPath(getContextProject(), this.classpath, getClassLoadId(), this.reverseLoader, this.loaderId != null || ClasspathUtils.isMagicPropertySet(getContextProject()));
            return cl;
        }

        private Project getContextProject() {
            return this.component.getProject();
        }

        public String getClassLoadId() {
            if (this.loaderId == null && this.classpathId != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(ClasspathUtils.LOADER_ID_PREFIX);
                stringBuffer.append(this.classpathId);
                return stringBuffer.toString();
            }
            return this.loaderId;
        }

        public Object newInstance() {
            ClassLoader cl = getClassLoader();
            return ClasspathUtils.newInstance(this.className, cl);
        }

        public Path getClasspath() {
            return this.classpath;
        }

        public boolean isReverseLoader() {
            return this.reverseLoader;
        }
    }
}
