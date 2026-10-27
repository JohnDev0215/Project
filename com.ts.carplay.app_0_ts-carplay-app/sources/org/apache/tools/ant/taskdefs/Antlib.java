package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.helper.ProjectHelper2;

/* JADX INFO: loaded from: classes.dex */
public class Antlib extends Task implements TaskContainer {
    public static final String TAG = "antlib";
    static /* synthetic */ Class class$org$apache$tools$ant$taskdefs$Antlib;
    private ClassLoader classLoader;
    private String uri = "";
    private List tasks = new ArrayList();

    public static Antlib createAntlib(Project project, URL antlibUrl, String uri) {
        try {
            antlibUrl.openConnection().connect();
            ComponentHelper helper = ComponentHelper.getComponentHelper(project);
            helper.enterAntLib(uri);
            try {
                ProjectHelper2 parser = new ProjectHelper2();
                UnknownElement ue = parser.parseUnknownElement(project, antlibUrl);
                if (!ue.getTag().equals(TAG)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unexpected tag ");
                    stringBuffer.append(ue.getTag());
                    stringBuffer.append(" expecting ");
                    stringBuffer.append(TAG);
                    throw new BuildException(stringBuffer.toString(), ue.getLocation());
                }
                Antlib antlib = new Antlib();
                antlib.setProject(project);
                antlib.setLocation(ue.getLocation());
                antlib.setTaskName(TAG);
                antlib.init();
                ue.configure(antlib);
                return antlib;
            } finally {
                helper.exitAntLib();
            }
        } catch (IOException ex) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to find ");
            stringBuffer2.append(antlibUrl);
            throw new BuildException(stringBuffer2.toString(), ex);
        }
    }

    protected void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    protected void setURI(String uri) {
        this.uri = uri;
    }

    private ClassLoader getClassLoader() {
        if (this.classLoader == null) {
            Class clsClass$ = class$org$apache$tools$ant$taskdefs$Antlib;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.tools.ant.taskdefs.Antlib");
                class$org$apache$tools$ant$taskdefs$Antlib = clsClass$;
            }
            this.classLoader = clsClass$.getClassLoader();
        }
        return this.classLoader;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    @Override // org.apache.tools.ant.TaskContainer
    public void addTask(Task nestedTask) {
        this.tasks.add(nestedTask);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        for (UnknownElement ue : this.tasks) {
            setLocation(ue.getLocation());
            ue.maybeConfigure();
            Object configuredObject = ue.getRealThing();
            if (configuredObject != null) {
                if (!(configuredObject instanceof AntlibDefinition)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Invalid task in antlib ");
                    stringBuffer.append(ue.getTag());
                    stringBuffer.append(" ");
                    stringBuffer.append(configuredObject.getClass());
                    stringBuffer.append(" does not ");
                    stringBuffer.append("extend org.apache.tools.ant.taskdefs.AntlibDefinition");
                    throw new BuildException(stringBuffer.toString());
                }
                AntlibDefinition def = (AntlibDefinition) configuredObject;
                def.setURI(this.uri);
                def.setAntlibClassLoader(getClassLoader());
                def.init();
                def.execute();
            }
        }
    }
}
