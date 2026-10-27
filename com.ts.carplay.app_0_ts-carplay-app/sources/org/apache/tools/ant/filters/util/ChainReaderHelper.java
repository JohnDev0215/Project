package org.apache.tools.ant.filters.util;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.filters.BaseFilterReader;
import org.apache.tools.ant.filters.ChainableReader;
import org.apache.tools.ant.types.AntFilterReader;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.Parameterizable;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public final class ChainReaderHelper {
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    static /* synthetic */ Class class$java$io$FilterReader;
    static /* synthetic */ Class class$java$io$Reader;
    static /* synthetic */ Class class$org$apache$tools$ant$types$Parameterizable;
    public Reader primaryReader;
    public int bufferSize = 8192;
    public Vector filterChains = new Vector();
    private Project project = null;

    public final void setPrimaryReader(Reader rdr) {
        this.primaryReader = rdr;
    }

    public final void setProject(Project project) {
        this.project = project;
    }

    public final Project getProject() {
        return this.project;
    }

    public final void setBufferSize(int size) {
        this.bufferSize = size;
    }

    public final void setFilterChains(Vector fchain) {
        this.filterChains = fchain;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5 */
    public final Reader getAssembledReader() throws BuildException {
        Class<?> cls;
        Class clsClass$;
        boolean z;
        Class clsClass$2;
        Class<?> clsClass$3;
        Reader readerChain = this.primaryReader;
        if (readerChain == 0) {
            throw new BuildException("primaryReader must not be null.");
        }
        int size = this.filterChains.size();
        Vector vector = new Vector();
        for (int i = 0; i < size; i++) {
            Vector filterReaders = ((FilterChain) this.filterChains.elementAt(i)).getFilterReaders();
            int size2 = filterReaders.size();
            for (int i2 = 0; i2 < size2; i2++) {
                vector.addElement(filterReaders.elementAt(i2));
            }
        }
        int size3 = vector.size();
        if (size3 > 0) {
            int i3 = 0;
            readerChain = readerChain;
            while (i3 < size3) {
                Object objElementAt = vector.elementAt(i3);
                if (objElementAt instanceof AntFilterReader) {
                    AntFilterReader antFilterReader = (AntFilterReader) vector.elementAt(i3);
                    String className = antFilterReader.getClassName();
                    Path classpath = antFilterReader.getClasspath();
                    Project project = antFilterReader.getProject();
                    if (className == null) {
                        continue;
                    } else {
                        if (classpath == null) {
                            try {
                                cls = Class.forName(className);
                            } catch (ClassNotFoundException e) {
                                throw new BuildException(e);
                            } catch (IllegalAccessException e2) {
                                throw new BuildException(e2);
                            } catch (InstantiationException e3) {
                                throw new BuildException(e3);
                            } catch (InvocationTargetException e4) {
                                throw new BuildException(e4);
                            }
                        } else {
                            cls = Class.forName(className, true, project.createClassLoader(classpath));
                        }
                        if (cls == null) {
                            continue;
                        } else {
                            if (class$java$io$FilterReader == null) {
                                clsClass$ = class$("java.io.FilterReader");
                                class$java$io$FilterReader = clsClass$;
                            } else {
                                clsClass$ = class$java$io$FilterReader;
                            }
                            if (!clsClass$.isAssignableFrom(cls)) {
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append(className);
                                stringBuffer.append(" does not extend java.io.FilterReader");
                                throw new BuildException(stringBuffer.toString());
                            }
                            Constructor<?>[] constructors = cls.getConstructors();
                            int i4 = 0;
                            while (true) {
                                if (i4 >= constructors.length) {
                                    z = false;
                                    break;
                                }
                                Class<?>[] parameterTypes = constructors[i4].getParameterTypes();
                                if (parameterTypes.length == 1) {
                                    Class<?> cls2 = parameterTypes[0];
                                    if (class$java$io$Reader == null) {
                                        clsClass$3 = class$("java.io.Reader");
                                        class$java$io$Reader = clsClass$3;
                                    } else {
                                        clsClass$3 = class$java$io$Reader;
                                    }
                                    if (cls2.isAssignableFrom(clsClass$3)) {
                                        z = true;
                                        break;
                                    }
                                }
                                i4++;
                            }
                            if (!z) {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append(className);
                                stringBuffer2.append(" does not define a public constructor");
                                stringBuffer2.append(" that takes in a Reader as its ");
                                stringBuffer2.append("single argument.");
                                throw new BuildException(stringBuffer2.toString());
                            }
                            readerChain = (Reader) constructors[i4].newInstance(readerChain);
                            setProjectOnObject(readerChain);
                            if (class$org$apache$tools$ant$types$Parameterizable == null) {
                                clsClass$2 = class$("org.apache.tools.ant.types.Parameterizable");
                                class$org$apache$tools$ant$types$Parameterizable = clsClass$2;
                            } else {
                                clsClass$2 = class$org$apache$tools$ant$types$Parameterizable;
                            }
                            if (clsClass$2.isAssignableFrom(cls)) {
                                ((Parameterizable) readerChain).setParameters(antFilterReader.getParams());
                            }
                        }
                    }
                } else if (objElementAt instanceof ChainableReader) {
                    setProjectOnObject(objElementAt);
                    readerChain = ((ChainableReader) objElementAt).chain(readerChain);
                    setProjectOnObject(readerChain);
                }
                i3++;
                readerChain = readerChain;
            }
        }
        return readerChain;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private void setProjectOnObject(Object obj) {
        Project project = this.project;
        if (project == null) {
            return;
        }
        if (obj instanceof BaseFilterReader) {
            ((BaseFilterReader) obj).setProject(project);
        } else {
            project.setProjectReference(obj);
        }
    }

    public final String readFully(Reader rdr) throws IOException {
        return FileUtils.readFully(rdr, this.bufferSize);
    }
}
