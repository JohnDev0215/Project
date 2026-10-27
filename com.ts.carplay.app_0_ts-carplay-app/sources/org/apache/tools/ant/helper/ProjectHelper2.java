package org.apache.tools.ant.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.util.FileUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: classes.dex */
public class ProjectHelper2 extends ProjectHelper {
    private static AntHandler elementHandler = new ElementHandler();
    private static AntHandler targetHandler = new TargetHandler();
    private static AntHandler mainHandler = new MainHandler();
    private static AntHandler projectHandler = new ProjectHandler();
    private static FileUtils fu = FileUtils.newFileUtils();

    public UnknownElement parseUnknownElement(Project project, URL source) throws Throwable {
        Target dummyTarget = new Target();
        dummyTarget.setProject(project);
        AntXMLContext context = new AntXMLContext(project);
        context.addTarget(dummyTarget);
        context.setImplicitTarget(dummyTarget);
        parse(context.getProject(), source, new RootHandler(context, elementHandler));
        Task[] tasks = dummyTarget.getTasks();
        if (tasks.length != 1) {
            throw new BuildException("No tasks defined");
        }
        return (UnknownElement) tasks[0];
    }

    @Override // org.apache.tools.ant.ProjectHelper
    public void parse(Project project, Object source) throws Throwable {
        getImportStack().addElement(source);
        AntXMLContext context = (AntXMLContext) project.getReference("ant.parsing.context");
        if (context == null) {
            context = new AntXMLContext(project);
            project.addReference("ant.parsing.context", context);
            project.addReference("ant.targets", context.getTargets());
        }
        if (getImportStack().size() > 1) {
            context.setIgnoreProjectTag(true);
            Target currentTarget = context.getCurrentTarget();
            Target currentImplicit = context.getImplicitTarget();
            Map currentTargets = context.getCurrentTargets();
            try {
                Target newCurrent = new Target();
                newCurrent.setProject(project);
                newCurrent.setName("");
                context.setCurrentTarget(newCurrent);
                context.setCurrentTargets(new HashMap());
                context.setImplicitTarget(newCurrent);
                parse(project, source, new RootHandler(context, mainHandler));
                newCurrent.execute();
                return;
            } finally {
                context.setCurrentTarget(currentTarget);
                context.setImplicitTarget(currentImplicit);
                context.setCurrentTargets(currentTargets);
            }
        }
        context.setCurrentTargets(new HashMap());
        parse(project, source, new RootHandler(context, mainHandler));
        context.getImplicitTarget().execute();
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01b7 A[Catch: all -> 0x01bb, TryCatch #9 {all -> 0x01bb, blocks: (B:109:0x01a1, B:113:0x01ad, B:114:0x01b6, B:115:0x01b7, B:116:0x01ba), top: B:155:0x01a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01e5 A[Catch: all -> 0x020c, TRY_LEAVE, TryCatch #19 {all -> 0x020c, blocks: (B:123:0x01ca, B:125:0x01e5, B:136:0x0202, B:137:0x020b), top: B:162:0x01ca }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0216 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parse(org.apache.tools.ant.Project r18, java.lang.Object r19, org.apache.tools.ant.helper.ProjectHelper2.RootHandler r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 620
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.helper.ProjectHelper2.parse(org.apache.tools.ant.Project, java.lang.Object, org.apache.tools.ant.helper.ProjectHelper2$RootHandler):void");
    }

    public static class AntHandler {
        public void onStartElement(String uri, String tag, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
        }

        public AntHandler onStartChild(String uri, String tag, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unexpected element \"");
            stringBuffer.append(qname);
            stringBuffer.append(" \"");
            throw new SAXParseException(stringBuffer.toString(), context.getLocator());
        }

        public void onEndChild(String uri, String tag, String qname, AntXMLContext context) throws SAXParseException {
        }

        public void onEndElement(String uri, String tag, AntXMLContext context) {
        }

        public void characters(char[] buf, int start, int count, AntXMLContext context) throws SAXParseException {
            String s = new String(buf, start, count).trim();
            if (s.length() > 0) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unexpected text \"");
                stringBuffer.append(s);
                stringBuffer.append("\"");
                throw new SAXParseException(stringBuffer.toString(), context.getLocator());
            }
        }

        protected void checkNamespace(String uri) {
        }
    }

    public static class RootHandler extends DefaultHandler {
        private Stack antHandlers;
        private AntXMLContext context;
        private AntHandler currentHandler;

        public RootHandler(AntXMLContext context, AntHandler rootHandler) {
            Stack stack = new Stack();
            this.antHandlers = stack;
            this.currentHandler = null;
            this.currentHandler = rootHandler;
            stack.push(rootHandler);
            this.context = context;
        }

        public AntHandler getCurrentAntHandler() {
            return this.currentHandler;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String publicId, String systemId) {
            Project project = this.context.getProject();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("resolving systemId: ");
            stringBuffer.append(systemId);
            project.log(stringBuffer.toString(), 3);
            if (systemId.startsWith("file:")) {
                String path = ProjectHelper2.fu.fromURI(systemId);
                File file = new File(path);
                if (!file.isAbsolute()) {
                    file = ProjectHelper2.fu.resolveFile(this.context.getBuildFileParent(), path);
                }
                try {
                    InputSource inputSource = new InputSource(new FileInputStream(file));
                    inputSource.setSystemId(ProjectHelper2.fu.toURI(file.getAbsolutePath()));
                    return inputSource;
                } catch (FileNotFoundException e) {
                    Project project2 = this.context.getProject();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(file.getAbsolutePath());
                    stringBuffer2.append(" could not be found");
                    project2.log(stringBuffer2.toString(), 1);
                    return null;
                }
            }
            return null;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String uri, String tag, String qname, Attributes attrs) throws SAXParseException {
            AntHandler next = this.currentHandler.onStartChild(uri, tag, qname, attrs, this.context);
            this.antHandlers.push(this.currentHandler);
            this.currentHandler = next;
            next.onStartElement(uri, tag, qname, attrs, this.context);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void setDocumentLocator(Locator locator) {
            this.context.setLocator(locator);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String uri, String name, String qName) throws SAXException {
            this.currentHandler.onEndElement(uri, name, this.context);
            AntHandler prev = (AntHandler) this.antHandlers.pop();
            this.currentHandler = prev;
            if (prev != null) {
                prev.onEndChild(uri, name, qName, this.context);
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] buf, int start, int count) throws SAXParseException {
            this.currentHandler.characters(buf, start, count, this.context);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startPrefixMapping(String prefix, String uri) {
            this.context.startPrefixMapping(prefix, uri);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endPrefixMapping(String prefix) {
            this.context.endPrefixMapping(prefix);
        }
    }

    public static class MainHandler extends AntHandler {
        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public AntHandler onStartChild(String uri, String name, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
            if (name.equals("project") && (uri.equals("") || uri.equals(ProjectHelper.ANT_CORE_URI))) {
                return ProjectHelper2.projectHandler;
            }
            if (name.equals(qname)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unexpected element \"{");
                stringBuffer.append(uri);
                stringBuffer.append("}");
                stringBuffer.append(name);
                stringBuffer.append("\" {");
                stringBuffer.append(ProjectHelper.ANT_CORE_URI);
                stringBuffer.append("}");
                stringBuffer.append(name);
                throw new SAXParseException(stringBuffer.toString(), context.getLocator());
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unexpected element \"");
            stringBuffer2.append(qname);
            stringBuffer2.append("\" ");
            stringBuffer2.append(name);
            throw new SAXParseException(stringBuffer2.toString(), context.getLocator());
        }
    }

    public static class ProjectHandler extends AntHandler {
        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onStartElement(String uri, String tag, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
            String baseDir = null;
            boolean nameAttributeSet = false;
            Project project = context.getProject();
            context.getImplicitTarget().setLocation(new Location(context.getLocator()));
            for (int i = 0; i < attrs.getLength(); i++) {
                String attrUri = attrs.getURI(i);
                if (attrUri != null && !attrUri.equals("")) {
                    if (!attrUri.equals(uri)) {
                        continue;
                    }
                }
                String key = attrs.getLocalName(i);
                String value = attrs.getValue(i);
                if (key.equals("default")) {
                    if (value != null && !value.equals("") && !context.isIgnoringProjectTag()) {
                        project.setDefault(value);
                    }
                } else if (key.equals(FilenameSelector.NAME_KEY)) {
                    if (value != null) {
                        context.setCurrentProjectName(value);
                        nameAttributeSet = true;
                        if (!context.isIgnoringProjectTag()) {
                            project.setName(value);
                            project.addReference(value, project);
                        }
                    }
                } else if (key.equals("id")) {
                    if (value != null && !context.isIgnoringProjectTag()) {
                        project.addReference(value, project);
                    }
                } else {
                    if (!key.equals("basedir")) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Unexpected attribute \"");
                        stringBuffer.append(attrs.getQName(i));
                        stringBuffer.append("\"");
                        throw new SAXParseException(stringBuffer.toString(), context.getLocator());
                    }
                    if (!context.isIgnoringProjectTag()) {
                        baseDir = value;
                    }
                }
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("ant.file.");
            stringBuffer2.append(context.getCurrentProjectName());
            String antFileProp = stringBuffer2.toString();
            String dup = project.getProperty(antFileProp);
            if (dup != null && nameAttributeSet) {
                File dupFile = new File(dup);
                if (context.isIgnoringProjectTag() && !dupFile.equals(context.getBuildFile())) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Duplicated project name in import. Project ");
                    stringBuffer3.append(context.getCurrentProjectName());
                    stringBuffer3.append(" defined first in ");
                    stringBuffer3.append(dup);
                    stringBuffer3.append(" and again in ");
                    stringBuffer3.append(context.getBuildFile());
                    project.log(stringBuffer3.toString(), 1);
                }
            }
            File dupFile2 = context.getBuildFile();
            if (dupFile2 != null) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("ant.file.");
                stringBuffer4.append(context.getCurrentProjectName());
                project.setUserProperty(stringBuffer4.toString(), context.getBuildFile().toString());
            }
            if (context.isIgnoringProjectTag()) {
                return;
            }
            if (project.getProperty("basedir") != null) {
                project.setBasedir(project.getProperty("basedir"));
            } else if (baseDir == null) {
                project.setBasedir(context.getBuildFileParent().getAbsolutePath());
            } else if (!new File(baseDir).isAbsolute()) {
                project.setBaseDir(ProjectHelper2.fu.resolveFile(context.getBuildFileParent(), baseDir));
            } else {
                project.setBasedir(baseDir);
            }
            project.addTarget("", context.getImplicitTarget());
            context.setCurrentTarget(context.getImplicitTarget());
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public AntHandler onStartChild(String uri, String name, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
            return (name.equals("target") && (uri.equals("") || uri.equals(ProjectHelper.ANT_CORE_URI))) ? ProjectHelper2.targetHandler : ProjectHelper2.elementHandler;
        }
    }

    public static class TargetHandler extends AntHandler {
        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onStartElement(String uri, String tag, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
            String name = null;
            String depends = "";
            Project project = context.getProject();
            Target target = new Target();
            target.setProject(project);
            target.setLocation(new Location(context.getLocator()));
            context.addTarget(target);
            for (int i = 0; i < attrs.getLength(); i++) {
                String attrUri = attrs.getURI(i);
                if (attrUri != null && !attrUri.equals("")) {
                    if (!attrUri.equals(uri)) {
                        continue;
                    }
                }
                String key = attrs.getLocalName(i);
                String value = attrs.getValue(i);
                if (key.equals(FilenameSelector.NAME_KEY)) {
                    name = value;
                    if ("".equals(name)) {
                        throw new BuildException("name attribute must not be empty");
                    }
                } else if (key.equals("depends")) {
                    depends = value;
                } else if (key.equals("if")) {
                    target.setIf(value);
                } else if (key.equals("unless")) {
                    target.setUnless(value);
                } else if (key.equals("id")) {
                    if (value != null && !value.equals("")) {
                        context.getProject().addReference(value, target);
                    }
                } else if (key.equals("description")) {
                    target.setDescription(value);
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unexpected attribute \"");
                    stringBuffer.append(key);
                    stringBuffer.append("\"");
                    throw new SAXParseException(stringBuffer.toString(), context.getLocator());
                }
            }
            if (name == null) {
                throw new SAXParseException("target element appears without a name attribute", context.getLocator());
            }
            Hashtable currentTargets = project.getTargets();
            if (currentTargets.containsKey(name)) {
                if (context.getCurrentTargets().get(name) != null) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Duplicate target '");
                    stringBuffer2.append(name);
                    stringBuffer2.append("'");
                    throw new BuildException(stringBuffer2.toString(), target.getLocation());
                }
                if (context.getCurrentProjectName() != null) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append(context.getCurrentProjectName());
                    stringBuffer3.append(".");
                    stringBuffer3.append(name);
                    String newName = stringBuffer3.toString();
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Already defined in main or a previous import, define ");
                    stringBuffer4.append(name);
                    stringBuffer4.append(" as ");
                    stringBuffer4.append(newName);
                    project.log(stringBuffer4.toString(), 3);
                    name = newName;
                } else {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append("Already defined in main or a previous import, ignore ");
                    stringBuffer5.append(name);
                    project.log(stringBuffer5.toString(), 3);
                    name = null;
                }
            }
            if (name != null) {
                target.setName(name);
                context.getCurrentTargets().put(name, target);
                project.addOrReplaceTarget(name, target);
            }
            if (depends.length() > 0) {
                target.setDepends(depends);
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public AntHandler onStartChild(String uri, String name, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
            return ProjectHelper2.elementHandler;
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onEndElement(String uri, String tag, AntXMLContext context) {
            context.setCurrentTarget(context.getImplicitTarget());
        }
    }

    public static class ElementHandler extends AntHandler {
        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onStartElement(String uri, String tag, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
            String str = uri;
            RuntimeConfigurable parentWrapper = context.currentWrapper();
            Object parent = null;
            if (parentWrapper != null) {
                parent = parentWrapper.getProxy();
            }
            UnknownElement task = new UnknownElement(tag);
            task.setProject(context.getProject());
            task.setNamespace(str);
            task.setQName(qname);
            task.setTaskType(ProjectHelper.genComponentName(task.getNamespace(), tag));
            task.setTaskName(qname);
            Location location = new Location(context.getLocator().getSystemId(), context.getLocator().getLineNumber(), context.getLocator().getColumnNumber());
            task.setLocation(location);
            task.setOwningTarget(context.getCurrentTarget());
            context.configureId(task, attrs);
            if (parent != null) {
                ((UnknownElement) parent).addChild(task);
            } else {
                context.getCurrentTarget().addTask(task);
            }
            RuntimeConfigurable wrapper = new RuntimeConfigurable(task, task.getTaskName());
            int i = 0;
            while (i < attrs.getLength()) {
                String name = attrs.getLocalName(i);
                String attrUri = attrs.getURI(i);
                if (attrUri != null && !attrUri.equals("") && !attrUri.equals(str)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(attrUri);
                    stringBuffer.append(":");
                    stringBuffer.append(attrs.getQName(i));
                    name = stringBuffer.toString();
                }
                String value = attrs.getValue(i);
                if (ProjectHelper.ANT_TYPE.equals(name) || (ProjectHelper.ANT_CORE_URI.equals(attrUri) && ProjectHelper.ANT_TYPE.equals(attrs.getLocalName(i)))) {
                    name = ProjectHelper.ANT_TYPE;
                    int index = value.indexOf(":");
                    if (index != -1) {
                        String prefix = value.substring(0, index);
                        String mappedUri = context.getPrefixMapping(prefix);
                        if (mappedUri == null) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("Unable to find XML NS prefix ");
                            stringBuffer2.append(prefix);
                            throw new BuildException(stringBuffer2.toString());
                        }
                        value = ProjectHelper.genComponentName(mappedUri, value.substring(index + 1));
                    }
                }
                wrapper.setAttribute(name, value);
                i++;
                str = uri;
            }
            if (parentWrapper != null) {
                parentWrapper.addChild(wrapper);
            }
            context.pushWrapper(wrapper);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void characters(char[] buf, int start, int count, AntXMLContext context) throws SAXParseException {
            RuntimeConfigurable wrapper = context.currentWrapper();
            wrapper.addText(buf, start, count);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public AntHandler onStartChild(String uri, String tag, String qname, Attributes attrs, AntXMLContext context) throws SAXParseException {
            return ProjectHelper2.elementHandler;
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onEndElement(String uri, String tag, AntXMLContext context) {
            context.popWrapper();
        }
    }
}
