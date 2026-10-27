package org.apache.tools.ant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class AntTypeDefinition {
    static /* synthetic */ Class class$org$apache$tools$ant$Project;
    private Class adaptToClass;
    private Class adapterClass;
    private ClassLoader classLoader;
    private String className;
    private Class clazz;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setClass(Class clazz) {
        this.clazz = clazz;
        if (clazz == null) {
            return;
        }
        if (this.classLoader == null) {
            this.classLoader = clazz.getClassLoader();
        }
        if (this.className == null) {
            this.className = clazz.getName();
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

    public void setAdapterClass(Class adapterClass) {
        this.adapterClass = adapterClass;
    }

    public void setAdaptToClass(Class adaptToClass) {
        this.adaptToClass = adaptToClass;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Class getExposedClass(Project project) {
        if (this.adaptToClass != null) {
            Class<?> typeClass = getTypeClass(project);
            if (typeClass == null) {
                return null;
            }
            if (this.adaptToClass.isAssignableFrom(typeClass)) {
                return typeClass;
            }
        }
        Class z = this.adapterClass;
        if (z != null) {
            return z;
        }
        return getTypeClass(project);
    }

    public Class getTypeClass(Project project) {
        Class cls = this.clazz;
        if (cls != null) {
            return cls;
        }
        try {
            if (this.classLoader == null) {
                this.clazz = Class.forName(this.className);
            } else {
                this.clazz = this.classLoader.loadClass(this.className);
            }
        } catch (ClassNotFoundException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not load class (");
            stringBuffer.append(this.className);
            stringBuffer.append(") for type ");
            stringBuffer.append(this.name);
            project.log(stringBuffer.toString(), 4);
        } catch (NoClassDefFoundError ncdfe) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not load a dependent class (");
            stringBuffer2.append(ncdfe.getMessage());
            stringBuffer2.append(") for type ");
            stringBuffer2.append(this.name);
            project.log(stringBuffer2.toString(), 4);
        }
        return this.clazz;
    }

    public Object create(Project project) {
        return icreate(project);
    }

    private Object icreate(Project project) {
        Class c = getTypeClass(project);
        if (c == null) {
            return null;
        }
        Object o = createAndSet(project, c);
        if (o == null || this.adapterClass == null) {
            return o;
        }
        Class cls = this.adaptToClass;
        if (cls != null && cls.isAssignableFrom(o.getClass())) {
            return o;
        }
        TypeAdapter adapterObject = (TypeAdapter) createAndSet(project, this.adapterClass);
        if (adapterObject == null) {
            return null;
        }
        adapterObject.setProxy(o);
        return adapterObject;
    }

    public void checkClass(Project project) {
        if (this.clazz == null) {
            Class typeClass = getTypeClass(project);
            this.clazz = typeClass;
            if (typeClass == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unable to create class for ");
                stringBuffer.append(getName());
                throw new BuildException(stringBuffer.toString());
            }
        }
        if (this.adapterClass != null) {
            boolean needToCheck = true;
            Class cls = this.adaptToClass;
            if (cls != null && cls.isAssignableFrom(this.clazz)) {
                needToCheck = false;
            }
            if (needToCheck) {
                TypeAdapter adapter = (TypeAdapter) createAndSet(project, this.adapterClass);
                if (adapter == null) {
                    throw new BuildException("Unable to create adapter object");
                }
                adapter.checkProxyClass(this.clazz);
            }
        }
    }

    private Object createAndSet(Project project, Class cls) {
        Class<?> clsClass$;
        Constructor constructor;
        boolean z;
        Object objNewInstance;
        try {
            try {
                constructor = cls.getConstructor(new Class[0]);
                z = true;
            } catch (NoClassDefFoundError e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Type ");
                stringBuffer.append(this.name);
                stringBuffer.append(": A class needed by class ");
                stringBuffer.append(cls);
                stringBuffer.append(" cannot be found: ");
                stringBuffer.append(e.getMessage());
                throw new BuildException(stringBuffer.toString(), e);
            } catch (InvocationTargetException e2) {
                Throwable targetException = e2.getTargetException();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Could not create type ");
                stringBuffer2.append(this.name);
                stringBuffer2.append(" due to ");
                stringBuffer2.append(targetException);
                throw new BuildException(stringBuffer2.toString(), targetException);
            } catch (Throwable th) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Could not create type ");
                stringBuffer3.append(this.name);
                stringBuffer3.append(" due to ");
                stringBuffer3.append(th);
                throw new BuildException(stringBuffer3.toString(), th);
            }
        } catch (NoSuchMethodException e3) {
            Class<?>[] clsArr = new Class[1];
            if (class$org$apache$tools$ant$Project == null) {
                clsClass$ = class$("org.apache.tools.ant.Project");
                class$org$apache$tools$ant$Project = clsClass$;
            } else {
                clsClass$ = class$org$apache$tools$ant$Project;
            }
            clsArr[0] = clsClass$;
            constructor = cls.getConstructor(clsArr);
            z = false;
        }
        if (z) {
            objNewInstance = constructor.newInstance(new Object[0]);
        } else {
            objNewInstance = constructor.newInstance(project);
        }
        project.setProjectReference(objNewInstance);
        return objNewInstance;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public boolean sameDefinition(AntTypeDefinition other, Project project) {
        if (other == null || other.getClass() != getClass() || !other.getTypeClass(project).equals(getTypeClass(project)) || !other.getExposedClass(project).equals(getExposedClass(project)) || other.adapterClass != this.adapterClass || other.adaptToClass != this.adaptToClass) {
            return false;
        }
        return true;
    }

    public boolean similarDefinition(AntTypeDefinition other, Project project) {
        if (other == null || getClass() != other.getClass() || !getClassName().equals(other.getClassName()) || !extractClassname(this.adapterClass).equals(extractClassname(other.adapterClass)) || !extractClassname(this.adaptToClass).equals(extractClassname(other.adaptToClass))) {
            return false;
        }
        ClassLoader oldLoader = other.getClassLoader();
        ClassLoader newLoader = getClassLoader();
        if (oldLoader == newLoader) {
            return true;
        }
        if (newLoader == null || oldLoader == null || !(oldLoader instanceof AntClassLoader) || !(newLoader instanceof AntClassLoader) || !((AntClassLoader) oldLoader).getClasspath().equals(((AntClassLoader) newLoader).getClasspath())) {
            return false;
        }
        return true;
    }

    private String extractClassname(Class c) {
        if (c == null) {
            return "<null>";
        }
        return c.getClass().getName();
    }
}
