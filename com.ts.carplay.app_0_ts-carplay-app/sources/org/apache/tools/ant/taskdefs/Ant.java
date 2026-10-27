package org.apache.tools.ant.taskdefs;

import com.ts.lib.settings.common.constant.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Main;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.PropertySet;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Ant extends Task {
    static /* synthetic */ Class class$org$apache$tools$ant$Project;
    private Project newProject;
    private File dir = null;
    private String antFile = null;
    private String output = null;
    private boolean inheritAll = true;
    private boolean inheritRefs = false;
    private Vector properties = new Vector();
    private Vector references = new Vector();
    private PrintStream out = null;
    private Vector propertySets = new Vector();
    private Vector targets = new Vector();
    private boolean targetAttributeSet = false;

    public void setInheritAll(boolean value) {
        this.inheritAll = value;
    }

    public void setInheritRefs(boolean value) {
        this.inheritRefs = value;
    }

    @Override // org.apache.tools.ant.Task
    public void init() {
        Project project = new Project();
        this.newProject = project;
        project.setDefaultInputStream(getProject().getDefaultInputStream());
        this.newProject.setJavaVersionProperty();
    }

    private void reinit() {
        init();
    }

    private void initializeProject() {
        File outfile;
        this.newProject.setInputHandler(getProject().getInputHandler());
        Iterator iter = getBuildListeners();
        while (iter.hasNext()) {
            this.newProject.addBuildListener((BuildListener) iter.next());
        }
        if (this.output != null) {
            if (this.dir != null) {
                outfile = FileUtils.newFileUtils().resolveFile(this.dir, this.output);
            } else {
                outfile = getProject().resolveFile(this.output);
            }
            try {
                this.out = new PrintStream(new FileOutputStream(outfile));
                DefaultLogger logger = new DefaultLogger();
                logger.setMessageOutputLevel(2);
                logger.setOutputPrintStream(this.out);
                logger.setErrorPrintStream(this.out);
                this.newProject.addBuildListener(logger);
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Ant: Can't set output to ");
                stringBuffer.append(this.output);
                log(stringBuffer.toString());
            }
        }
        getProject().initSubProject(this.newProject);
        getProject().copyUserProperties(this.newProject);
        if (!this.inheritAll) {
            this.newProject.setSystemProperties();
        } else {
            addAlmostAll(getProject().getProperties());
        }
        Enumeration e2 = this.propertySets.elements();
        while (e2.hasMoreElements()) {
            PropertySet ps = (PropertySet) e2.nextElement();
            addAlmostAll(ps.getProperties());
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleOutput(String output) {
        Project project = this.newProject;
        if (project != null) {
            project.demuxOutput(output, false);
        } else {
            super.handleOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public int handleInput(byte[] buffer, int offset, int length) throws IOException {
        Project project = this.newProject;
        if (project != null) {
            return project.demuxInput(buffer, offset, length);
        }
        return super.handleInput(buffer, offset, length);
    }

    @Override // org.apache.tools.ant.Task
    public void handleFlush(String output) {
        Project project = this.newProject;
        if (project != null) {
            project.demuxFlush(output, false);
        } else {
            super.handleFlush(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleErrorOutput(String output) {
        Project project = this.newProject;
        if (project != null) {
            project.demuxOutput(output, true);
        } else {
            super.handleErrorOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleErrorFlush(String output) {
        Project project = this.newProject;
        if (project != null) {
            project.demuxFlush(output, true);
        } else {
            super.handleErrorFlush(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        PrintStream printStream;
        BuildException buildExceptionAddLocationToBuildException;
        String defaultTarget;
        File file = this.dir;
        String str = this.antFile;
        Vector vector = new Vector(this.targets);
        try {
            if (this.newProject == null) {
                reinit();
            }
            if (this.dir == null && this.inheritAll) {
                this.dir = getProject().getBaseDir();
            }
            initializeProject();
            if (this.dir != null) {
                this.newProject.setBaseDir(this.dir);
                if (file != null) {
                    this.newProject.setInheritedProperty("basedir", this.dir.getAbsolutePath());
                }
            } else {
                this.dir = getProject().getBaseDir();
            }
            overrideProperties();
            if (this.antFile == null) {
                this.antFile = Main.DEFAULT_BUILD_FILENAME;
            }
            this.antFile = FileUtils.newFileUtils().resolveFile(this.dir, this.antFile).getAbsolutePath();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("calling target(s) ");
            stringBuffer.append(vector.size() > 0 ? vector.toString() : "[default]");
            stringBuffer.append(" in build file ");
            stringBuffer.append(this.antFile);
            log(stringBuffer.toString(), 3);
            this.newProject.setUserProperty("ant.file", this.antFile);
            String property = getProject().getProperty("ant.file");
            if (property == null || !this.newProject.resolveFile(this.newProject.getProperty("ant.file")).equals(getProject().resolveFile(property)) || getOwningTarget() == null || !getOwningTarget().getName().equals("")) {
                try {
                    ProjectHelper.configureProject(this.newProject, new File(this.antFile));
                    if (vector.size() == 0 && (defaultTarget = this.newProject.getDefaultTarget()) != null) {
                        vector.add(defaultTarget);
                    }
                    if (this.newProject.getProperty("ant.file").equals(getProject().getProperty("ant.file")) && getOwningTarget() != null) {
                        String name = getOwningTarget().getName();
                        if (vector.contains(name)) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append(getTaskName());
                            stringBuffer2.append(" task calling ");
                            stringBuffer2.append("its own parent target.");
                            throw new BuildException(stringBuffer2.toString());
                        }
                        Iterator it = vector.iterator();
                        boolean z = false;
                        while (!z && it.hasNext()) {
                            Target target = (Target) getProject().getTargets().get((String) it.next());
                            z |= target != null && target.dependsOn(name);
                        }
                        if (z) {
                            StringBuffer stringBuffer3 = new StringBuffer();
                            stringBuffer3.append(getTaskName());
                            stringBuffer3.append(" task calling a target");
                            stringBuffer3.append(" that depends on");
                            stringBuffer3.append(" its parent target '");
                            stringBuffer3.append(name);
                            stringBuffer3.append("'.");
                            throw new BuildException(stringBuffer3.toString());
                        }
                    }
                    addReferences();
                    if (vector.size() > 0 && (vector.size() != 1 || vector.get(0) != "")) {
                        try {
                            try {
                                StringBuffer stringBuffer4 = new StringBuffer();
                                stringBuffer4.append("Entering ");
                                stringBuffer4.append(this.antFile);
                                stringBuffer4.append("...");
                                log(stringBuffer4.toString(), 3);
                                this.newProject.fireSubBuildStarted();
                                this.newProject.executeTargets(vector);
                                StringBuffer stringBuffer5 = new StringBuffer();
                                stringBuffer5.append("Exiting ");
                                stringBuffer5.append(this.antFile);
                                stringBuffer5.append(".");
                                log(stringBuffer5.toString(), 3);
                                this.newProject.fireSubBuildFinished(null);
                            } catch (BuildException e) {
                                buildExceptionAddLocationToBuildException = ProjectHelper.addLocationToBuildException(e, getLocation());
                                try {
                                    throw buildExceptionAddLocationToBuildException;
                                } catch (Throwable th) {
                                    th = th;
                                    StringBuffer stringBuffer6 = new StringBuffer();
                                    stringBuffer6.append("Exiting ");
                                    stringBuffer6.append(this.antFile);
                                    stringBuffer6.append(".");
                                    log(stringBuffer6.toString(), 3);
                                    this.newProject.fireSubBuildFinished(buildExceptionAddLocationToBuildException);
                                    throw th;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            buildExceptionAddLocationToBuildException = null;
                            StringBuffer stringBuffer62 = new StringBuffer();
                            stringBuffer62.append("Exiting ");
                            stringBuffer62.append(this.antFile);
                            stringBuffer62.append(".");
                            log(stringBuffer62.toString(), 3);
                            this.newProject.fireSubBuildFinished(buildExceptionAddLocationToBuildException);
                            throw th;
                        }
                    }
                    return;
                } catch (BuildException e2) {
                    throw ProjectHelper.addLocationToBuildException(e2, getLocation());
                }
            }
            if (getTaskName().equals("antcall")) {
                throw new BuildException("antcall must not be used at the top level.");
            }
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append(getTaskName());
            stringBuffer7.append(" task at the");
            stringBuffer7.append(" top level must not invoke");
            stringBuffer7.append(" its own build file.");
            throw new BuildException(stringBuffer7.toString());
        } finally {
            this.newProject = null;
            Enumeration enumerationElements = this.properties.elements();
            while (enumerationElements.hasMoreElements()) {
                ((Property) enumerationElements.nextElement()).setProject(null);
            }
            if (this.output != null && (printStream = this.out) != null) {
                try {
                    printStream.close();
                } catch (Exception e3) {
                }
            }
            this.dir = file;
            this.antFile = str;
        }
    }

    private void overrideProperties() throws Throwable {
        Set set = new HashSet();
        for (int i = this.properties.size() - 1; i >= 0; i--) {
            Property p = (Property) this.properties.get(i);
            if (p.getName() != null && !p.getName().equals("")) {
                if (set.contains(p.getName())) {
                    this.properties.remove(i);
                } else {
                    set.add(p.getName());
                }
            }
        }
        Enumeration e = this.properties.elements();
        while (e.hasMoreElements()) {
            Property p2 = (Property) e.nextElement();
            p2.setProject(this.newProject);
            p2.execute();
        }
        getProject().copyInheritedProperties(this.newProject);
    }

    private void addReferences() throws BuildException {
        Hashtable thisReferences = (Hashtable) getProject().getReferences().clone();
        Hashtable newReferences = this.newProject.getReferences();
        if (this.references.size() > 0) {
            Enumeration e = this.references.elements();
            while (e.hasMoreElements()) {
                Reference ref = (Reference) e.nextElement();
                String refid = ref.getRefId();
                if (refid == null) {
                    throw new BuildException("the refid attribute is required for reference elements");
                }
                if (!thisReferences.containsKey(refid)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Parent project doesn't contain any reference '");
                    stringBuffer.append(refid);
                    stringBuffer.append("'");
                    log(stringBuffer.toString(), 1);
                } else {
                    thisReferences.remove(refid);
                    String toRefid = ref.getToRefid();
                    if (toRefid == null) {
                        toRefid = refid;
                    }
                    copyReference(refid, toRefid);
                }
            }
        }
        if (this.inheritRefs) {
            Enumeration e2 = thisReferences.keys();
            while (e2.hasMoreElements()) {
                String key = (String) e2.nextElement();
                if (!newReferences.containsKey(key)) {
                    copyReference(key, key);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.reflect.Method] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.reflect.Method] */
    private void copyReference(String oldKey, String newKey) {
        Class<?> clsClass$;
        Object orig = getProject().getReference(oldKey);
        if (orig == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No object referenced by ");
            stringBuffer.append(oldKey);
            stringBuffer.append(". Can't copy to ");
            stringBuffer.append(newKey);
            log(stringBuffer.toString(), 1);
            return;
        }
        Class<?> cls = orig.getClass();
        Object copy = orig;
        try {
            e = cls.getMethod("clone", new Class[0]);
            if (e != 0) {
                copy = e.invoke(orig, new Object[0]);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Adding clone of reference ");
                stringBuffer2.append(oldKey);
                log(stringBuffer2.toString(), 4);
            }
        } catch (Exception e) {
            e = e;
        }
        if (!(copy instanceof ProjectComponent)) {
            try {
                Class<?>[] clsArr = new Class[1];
                if (class$org$apache$tools$ant$Project == null) {
                    clsClass$ = class$("org.apache.tools.ant.Project");
                    class$org$apache$tools$ant$Project = clsClass$;
                } else {
                    clsClass$ = class$org$apache$tools$ant$Project;
                }
                clsArr[0] = clsClass$;
                e = cls.getMethod("setProject", clsArr);
                if (e != 0) {
                    e.invoke(copy, this.newProject);
                }
            } catch (NoSuchMethodException e2) {
            } catch (Exception e22) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Error setting new project instance for reference with id ");
                stringBuffer3.append(oldKey);
                String msg = stringBuffer3.toString();
                throw new BuildException(msg, e22, getLocation());
            }
        } else {
            ((ProjectComponent) copy).setProject(this.newProject);
        }
        this.newProject.addReference(newKey, copy);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private void addAlmostAll(Hashtable props) {
        Enumeration e = props.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement().toString();
            if (!"basedir".equals(key) && !"ant.file".equals(key)) {
                String value = props.get(key).toString();
                if (this.newProject.getProperty(key) == null) {
                    this.newProject.setNewProperty(key, value);
                }
            }
        }
    }

    public void setDir(File d) {
        this.dir = d;
    }

    public void setAntfile(String s) {
        this.antFile = s;
    }

    public void setTarget(String s) {
        if (s.equals("")) {
            throw new BuildException("target attribute must not be empty");
        }
        this.targets.add(s);
        this.targetAttributeSet = true;
    }

    public void setOutput(String s) {
        this.output = s;
    }

    public Property createProperty() {
        if (this.newProject == null) {
            reinit();
        }
        Property p = new Property(true, getProject());
        p.setProject(this.newProject);
        p.setTaskName(Constants.PROPERTY_SERVICE);
        this.properties.addElement(p);
        return p;
    }

    public void addReference(Reference r) {
        this.references.addElement(r);
    }

    public void addConfiguredTarget(TargetElement t) {
        if (this.targetAttributeSet) {
            throw new BuildException("nested target is incompatible with the target attribute");
        }
        String name = t.getName();
        if (name.equals("")) {
            throw new BuildException("target name must not be empty");
        }
        this.targets.add(name);
    }

    public void addPropertyset(PropertySet ps) {
        this.propertySets.addElement(ps);
    }

    private Iterator getBuildListeners() {
        return getProject().getBuildListeners().iterator();
    }

    public static class Reference extends org.apache.tools.ant.types.Reference {
        private String targetid = null;

        public void setToRefid(String targetid) {
            this.targetid = targetid;
        }

        public String getToRefid() {
            return this.targetid;
        }
    }

    public static class TargetElement {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
