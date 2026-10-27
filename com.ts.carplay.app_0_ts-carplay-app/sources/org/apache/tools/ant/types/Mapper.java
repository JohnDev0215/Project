package org.apache.tools.ant.types;

import java.util.Properties;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.CompositeMapper;
import org.apache.tools.ant.util.ContainerMapper;
import org.apache.tools.ant.util.FileNameMapper;

/* JADX INFO: loaded from: classes.dex */
public class Mapper extends DataType implements Cloneable {
    protected MapperType type = null;
    protected String classname = null;
    protected Path classpath = null;
    protected String from = null;
    protected String to = null;
    private ContainerMapper container = null;

    public Mapper(Project p) {
        setProject(p);
    }

    public void setType(MapperType type) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.type = type;
    }

    public void add(FileNameMapper fileNameMapper) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.container == null) {
            if (this.type == null && this.classname == null) {
                this.container = new CompositeMapper();
            } else {
                FileNameMapper m = getImplementation();
                if (m instanceof ContainerMapper) {
                    this.container = (ContainerMapper) m;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(String.valueOf(m));
                    stringBuffer.append(" mapper implementation does not support nested mappers!");
                    throw new BuildException(stringBuffer.toString());
                }
            }
        }
        this.container.add(fileNameMapper);
    }

    public void addConfiguredMapper(Mapper mapper) {
        add(mapper.getImplementation());
    }

    public void setClassname(String classname) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.classname = classname;
    }

    public void setClasspath(Path classpath) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        Path path = this.classpath;
        if (path == null) {
            this.classpath = classpath;
        } else {
            path.append(classpath);
        }
    }

    public Path createClasspath() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference r) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createClasspath().setRefid(r);
    }

    public void setFrom(String from) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.from = from;
    }

    public void setTo(String to) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.to = to;
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (this.type != null || this.from != null || this.to != null) {
            throw tooManyAttributes();
        }
        super.setRefid(r);
    }

    public FileNameMapper getImplementation() throws BuildException {
        if (isReference()) {
            return getRef().getImplementation();
        }
        if (this.type == null && this.classname == null && this.container == null) {
            throw new BuildException("nested mapper or one of the attributes type or classname is required");
        }
        ContainerMapper containerMapper = this.container;
        if (containerMapper != null) {
            return containerMapper;
        }
        if (this.type != null && this.classname != null) {
            throw new BuildException("must not specify both type and classname attribute");
        }
        try {
            FileNameMapper m = (FileNameMapper) getImplementationClass().newInstance();
            Project project = getProject();
            if (project != null) {
                project.setProjectReference(m);
            }
            m.setFrom(this.from);
            m.setTo(this.to);
            return m;
        } catch (BuildException be) {
            throw be;
        } catch (Throwable t) {
            throw new BuildException(t);
        }
    }

    protected Class getImplementationClass() throws ClassNotFoundException {
        String classname = this.classname;
        MapperType mapperType = this.type;
        if (mapperType != null) {
            classname = mapperType.getImplementation();
        }
        ClassLoader loader = this.classpath == null ? getClass().getClassLoader() : getProject().createClassLoader(this.classpath);
        return Class.forName(classname, true, loader);
    }

    protected Mapper getRef() {
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, getProject());
        }
        Object o = getRefid().getReferencedObject(getProject());
        if (!(o instanceof Mapper)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getRefid().getRefId());
            stringBuffer.append(" doesn't denote a mapper");
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
        return (Mapper) o;
    }

    public static class MapperType extends EnumeratedAttribute {
        private Properties implementations;

        public MapperType() {
            Properties properties = new Properties();
            this.implementations = properties;
            properties.put("identity", "org.apache.tools.ant.util.IdentityMapper");
            this.implementations.put("flatten", "org.apache.tools.ant.util.FlatFileNameMapper");
            this.implementations.put("glob", "org.apache.tools.ant.util.GlobPatternMapper");
            this.implementations.put("merge", "org.apache.tools.ant.util.MergingMapper");
            this.implementations.put(RegularExpression.DATA_TYPE_NAME, "org.apache.tools.ant.util.RegexpPatternMapper");
            this.implementations.put("package", "org.apache.tools.ant.util.PackageNameMapper");
            this.implementations.put("unpackage", "org.apache.tools.ant.util.UnPackageNameMapper");
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"identity", "flatten", "glob", "merge", RegularExpression.DATA_TYPE_NAME, "package", "unpackage"};
        }

        public String getImplementation() {
            return this.implementations.getProperty(getValue());
        }
    }
}
