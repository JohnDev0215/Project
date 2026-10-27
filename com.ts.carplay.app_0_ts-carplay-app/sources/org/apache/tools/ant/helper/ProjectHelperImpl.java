package org.apache.tools.ant.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.TypeAdapter;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.util.FileUtils;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: classes.dex */
public class ProjectHelperImpl extends ProjectHelper {
    private static FileUtils fu = FileUtils.newFileUtils();
    private File buildFile;
    private File buildFileParent;
    private Target implicitTarget;
    private Locator locator;
    private Parser parser;
    private Project project;

    public ProjectHelperImpl() {
        Target target = new Target();
        this.implicitTarget = target;
        target.setName("");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01b0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0143 A[Catch: all -> 0x0151, TryCatch #20 {all -> 0x0151, blocks: (B:63:0x013f, B:65:0x0143, B:66:0x0146, B:67:0x0147, B:68:0x0150), top: B:114:0x013f }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0147 A[Catch: all -> 0x0151, TryCatch #20 {all -> 0x0151, blocks: (B:63:0x013f, B:65:0x0143, B:66:0x0146, B:67:0x0147, B:68:0x0150), top: B:114:0x013f }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x017c A[Catch: all -> 0x019d, TRY_LEAVE, TryCatch #25 {all -> 0x019d, blocks: (B:74:0x0178, B:76:0x017c, B:84:0x0193, B:85:0x019c), top: B:119:0x0178 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0193 A[Catch: all -> 0x019d, TRY_ENTER, TryCatch #25 {all -> 0x019d, blocks: (B:74:0x0178, B:76:0x017c, B:84:0x0193, B:85:0x019c), top: B:119:0x0178 }] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v35, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v5, types: [org.apache.tools.ant.BuildException] */
    /* JADX WARN: Type inference failed for: r5v10, types: [org.apache.tools.ant.Location] */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v25, types: [org.xml.sax.InputSource] */
    /* JADX WARN: Type inference failed for: r5v26, types: [java.lang.StringBuffer] */
    /* JADX WARN: Type inference failed for: r5v28, types: [org.apache.tools.ant.helper.ProjectHelperImpl$RootHandler, org.xml.sax.DTDHandler, org.xml.sax.DocumentHandler, org.xml.sax.EntityResolver, org.xml.sax.ErrorHandler] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v30, types: [org.xml.sax.Parser] */
    /* JADX WARN: Type inference failed for: r6v31, types: [org.xml.sax.Parser] */
    /* JADX WARN: Type inference failed for: r6v32, types: [org.xml.sax.Parser] */
    /* JADX WARN: Type inference failed for: r6v33, types: [org.xml.sax.Parser] */
    /* JADX WARN: Type inference failed for: r6v34, types: [org.xml.sax.Parser] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.lang.Throwable, org.apache.tools.ant.BuildException] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v32 */
    @Override // org.apache.tools.ant.ProjectHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parse(org.apache.tools.ant.Project r13, java.lang.Object r14) throws org.xml.sax.SAXParseException, org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instruction units count: 447
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.helper.ProjectHelperImpl.parse(org.apache.tools.ant.Project, java.lang.Object):void");
    }

    static class AbstractHandler extends HandlerBase {
        ProjectHelperImpl helperImpl;
        protected DocumentHandler parentHandler;

        public AbstractHandler(ProjectHelperImpl helperImpl, DocumentHandler parentHandler) {
            this.parentHandler = parentHandler;
            this.helperImpl = helperImpl;
            helperImpl.parser.setDocumentHandler(this);
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String tag, AttributeList attrs) throws SAXParseException {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unexpected element \"");
            stringBuffer.append(tag);
            stringBuffer.append("\"");
            throw new SAXParseException(stringBuffer.toString(), this.helperImpl.locator);
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] buf, int start, int count) throws SAXParseException {
            String s = new String(buf, start, count).trim();
            if (s.length() > 0) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unexpected text \"");
                stringBuffer.append(s);
                stringBuffer.append("\"");
                throw new SAXParseException(stringBuffer.toString(), this.helperImpl.locator);
            }
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void endElement(String name) throws SAXException {
            this.helperImpl.parser.setDocumentHandler(this.parentHandler);
        }
    }

    static class RootHandler extends HandlerBase {
        ProjectHelperImpl helperImpl;

        public RootHandler(ProjectHelperImpl helperImpl) {
            this.helperImpl = helperImpl;
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String publicId, String systemId) {
            Project project = this.helperImpl.project;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("resolving systemId: ");
            stringBuffer.append(systemId);
            project.log(stringBuffer.toString(), 3);
            if (systemId.startsWith("file:")) {
                String path = ProjectHelperImpl.fu.fromURI(systemId);
                File file = new File(path);
                if (!file.isAbsolute()) {
                    file = ProjectHelperImpl.fu.resolveFile(this.helperImpl.buildFileParent, path);
                }
                try {
                    InputSource inputSource = new InputSource(new FileInputStream(file));
                    inputSource.setSystemId(ProjectHelperImpl.fu.toURI(file.getAbsolutePath()));
                    return inputSource;
                } catch (FileNotFoundException e) {
                    Project project2 = this.helperImpl.project;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(file.getAbsolutePath());
                    stringBuffer2.append(" could not be found");
                    project2.log(stringBuffer2.toString(), 1);
                    return null;
                }
            }
            return null;
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String tag, AttributeList attrs) throws SAXParseException {
            if (tag.equals("project")) {
                new ProjectHandler(this.helperImpl, this).init(tag, attrs);
                return;
            }
            throw new SAXParseException("Config file is not of expected XML type", this.helperImpl.locator);
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void setDocumentLocator(Locator locator) {
            this.helperImpl.locator = locator;
        }
    }

    static class ProjectHandler extends AbstractHandler {
        public ProjectHandler(ProjectHelperImpl helperImpl, DocumentHandler parentHandler) {
            super(helperImpl, parentHandler);
        }

        public void init(String tag, AttributeList attrs) throws SAXParseException {
            String def = null;
            String name = null;
            String id = null;
            String baseDir = null;
            for (int i = 0; i < attrs.getLength(); i++) {
                String key = attrs.getName(i);
                String value = attrs.getValue(i);
                if (key.equals("default")) {
                    def = value;
                } else if (key.equals(FilenameSelector.NAME_KEY)) {
                    name = value;
                } else if (key.equals("id")) {
                    id = value;
                } else if (key.equals("basedir")) {
                    baseDir = value;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unexpected attribute \"");
                    stringBuffer.append(attrs.getName(i));
                    stringBuffer.append("\"");
                    throw new SAXParseException(stringBuffer.toString(), this.helperImpl.locator);
                }
            }
            if (def != null && !def.equals("")) {
                this.helperImpl.project.setDefaultTarget(def);
                if (name != null) {
                    this.helperImpl.project.setName(name);
                    this.helperImpl.project.addReference(name, this.helperImpl.project);
                }
                if (id != null) {
                    this.helperImpl.project.addReference(id, this.helperImpl.project);
                }
                if (this.helperImpl.project.getProperty("basedir") != null) {
                    this.helperImpl.project.setBasedir(this.helperImpl.project.getProperty("basedir"));
                } else if (baseDir == null) {
                    this.helperImpl.project.setBasedir(this.helperImpl.buildFileParent.getAbsolutePath());
                } else if (new File(baseDir).isAbsolute()) {
                    this.helperImpl.project.setBasedir(baseDir);
                } else {
                    File resolvedBaseDir = this.helperImpl.project.resolveFile(baseDir, this.helperImpl.buildFileParent);
                    this.helperImpl.project.setBaseDir(resolvedBaseDir);
                }
                this.helperImpl.project.addTarget("", this.helperImpl.implicitTarget);
                return;
            }
            throw new BuildException("The default attribute is required");
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String name, AttributeList attrs) throws SAXParseException {
            if (!name.equals("target")) {
                ProjectHelperImpl.handleElement(this.helperImpl, this, this.helperImpl.implicitTarget, name, attrs);
            } else {
                handleTarget(name, attrs);
            }
        }

        private void handleTarget(String tag, AttributeList attrs) throws SAXParseException {
            new TargetHandler(this.helperImpl, this).init(tag, attrs);
        }
    }

    static class TargetHandler extends AbstractHandler {
        private Target target;

        public TargetHandler(ProjectHelperImpl helperImpl, DocumentHandler parentHandler) {
            super(helperImpl, parentHandler);
        }

        public void init(String tag, AttributeList attrs) throws SAXParseException {
            String name = null;
            String depends = "";
            String ifCond = null;
            String unlessCond = null;
            String id = null;
            String description = null;
            for (int i = 0; i < attrs.getLength(); i++) {
                String key = attrs.getName(i);
                String value = attrs.getValue(i);
                if (key.equals(FilenameSelector.NAME_KEY)) {
                    name = value;
                    if (name.equals("")) {
                        throw new BuildException("name attribute must not be empty", new Location(this.helperImpl.locator));
                    }
                } else if (key.equals("depends")) {
                    depends = value;
                } else if (key.equals("if")) {
                    ifCond = value;
                } else if (key.equals("unless")) {
                    unlessCond = value;
                } else if (key.equals("id")) {
                    id = value;
                } else if (key.equals("description")) {
                    description = value;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unexpected attribute \"");
                    stringBuffer.append(key);
                    stringBuffer.append("\"");
                    throw new SAXParseException(stringBuffer.toString(), this.helperImpl.locator);
                }
            }
            if (name == null) {
                throw new SAXParseException("target element appears without a name attribute", this.helperImpl.locator);
            }
            Target target = new Target();
            this.target = target;
            target.addDependency("");
            this.target.setName(name);
            this.target.setIf(ifCond);
            this.target.setUnless(unlessCond);
            this.target.setDescription(description);
            this.helperImpl.project.addTarget(name, this.target);
            if (id != null && !id.equals("")) {
                this.helperImpl.project.addReference(id, this.target);
            }
            if (depends.length() > 0) {
                this.target.setDepends(depends);
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String name, AttributeList attrs) throws SAXParseException {
            ProjectHelperImpl.handleElement(this.helperImpl, this, this.target, name, attrs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleElement(ProjectHelperImpl helperImpl, DocumentHandler parent, Target target, String elementName, AttributeList attrs) throws SAXParseException {
        if (elementName.equals("description")) {
            new DescriptionHandler(helperImpl, parent);
        } else if (helperImpl.project.getDataTypeDefinitions().get(elementName) != null) {
            new DataTypeHandler(helperImpl, parent, target).init(elementName, attrs);
        } else {
            new TaskHandler(helperImpl, parent, target, null, target).init(elementName, attrs);
        }
    }

    static class DescriptionHandler extends AbstractHandler {
        public DescriptionHandler(ProjectHelperImpl helperImpl, DocumentHandler parentHandler) {
            super(helperImpl, parentHandler);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] buf, int start, int count) {
            String text = new String(buf, start, count);
            String currentDescription = this.helperImpl.project.getDescription();
            if (currentDescription == null) {
                this.helperImpl.project.setDescription(text);
                return;
            }
            Project project = this.helperImpl.project;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(currentDescription);
            stringBuffer.append(text);
            project.setDescription(stringBuffer.toString());
        }
    }

    static class TaskHandler extends AbstractHandler {
        private TaskContainer container;
        private RuntimeConfigurable parentWrapper;
        private Target target;
        private Task task;
        private RuntimeConfigurable wrapper;

        public TaskHandler(ProjectHelperImpl helperImpl, DocumentHandler parentHandler, TaskContainer container, RuntimeConfigurable parentWrapper, Target target) {
            super(helperImpl, parentHandler);
            this.wrapper = null;
            this.container = container;
            this.parentWrapper = parentWrapper;
            this.target = target;
        }

        public void init(String tag, AttributeList attrs) throws SAXParseException {
            try {
                this.task = this.helperImpl.project.createTask(tag);
            } catch (BuildException e) {
            }
            if (this.task == null) {
                UnknownElement unknownElement = new UnknownElement(tag);
                this.task = unknownElement;
                unknownElement.setProject(this.helperImpl.project);
                this.task.setTaskName(tag);
            }
            this.task.setLocation(new Location(this.helperImpl.locator));
            this.helperImpl.configureId(this.task, attrs);
            this.task.setOwningTarget(this.target);
            this.container.addTask(this.task);
            this.task.init();
            RuntimeConfigurable runtimeConfigurableWrapper = this.task.getRuntimeConfigurableWrapper();
            this.wrapper = runtimeConfigurableWrapper;
            runtimeConfigurableWrapper.setAttributes(attrs);
            RuntimeConfigurable runtimeConfigurable = this.parentWrapper;
            if (runtimeConfigurable != null) {
                runtimeConfigurable.addChild(this.wrapper);
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] buf, int start, int count) {
            this.wrapper.addText(buf, start, count);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String name, AttributeList attrs) throws SAXParseException {
            if (this.task instanceof TaskContainer) {
                new TaskHandler(this.helperImpl, this, (TaskContainer) this.task, this.wrapper, this.target).init(name, attrs);
            } else {
                new NestedElementHandler(this.helperImpl, this, this.task, this.wrapper, this.target).init(name, attrs);
            }
        }
    }

    static class NestedElementHandler extends AbstractHandler {
        private Object child;
        private RuntimeConfigurable childWrapper;
        private Object parent;
        private RuntimeConfigurable parentWrapper;
        private Target target;

        public NestedElementHandler(ProjectHelperImpl helperImpl, DocumentHandler parentHandler, Object parent, RuntimeConfigurable parentWrapper, Target target) {
            super(helperImpl, parentHandler);
            this.childWrapper = null;
            if (parent instanceof TypeAdapter) {
                this.parent = ((TypeAdapter) parent).getProxy();
            } else {
                this.parent = parent;
            }
            this.parentWrapper = parentWrapper;
            this.target = target;
        }

        public void init(String propType, AttributeList attrs) throws SAXParseException {
            IntrospectionHelper ih = IntrospectionHelper.getHelper(this.parent.getClass());
            try {
                String elementName = propType.toLowerCase(Locale.US);
                if (!(this.parent instanceof UnknownElement)) {
                    this.child = ih.createElement(this.helperImpl.project, this.parent, elementName);
                } else {
                    UnknownElement uc = new UnknownElement(elementName);
                    uc.setProject(this.helperImpl.project);
                    ((UnknownElement) this.parent).addChild(uc);
                    this.child = uc;
                }
                this.helperImpl.configureId(this.child, attrs);
                RuntimeConfigurable runtimeConfigurable = new RuntimeConfigurable(this.child, propType);
                this.childWrapper = runtimeConfigurable;
                runtimeConfigurable.setAttributes(attrs);
                this.parentWrapper.addChild(this.childWrapper);
            } catch (BuildException exc) {
                throw new SAXParseException(exc.getMessage(), this.helperImpl.locator, exc);
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] buf, int start, int count) {
            this.childWrapper.addText(buf, start, count);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String name, AttributeList attrs) throws SAXParseException {
            if (this.child instanceof TaskContainer) {
                new TaskHandler(this.helperImpl, this, (TaskContainer) this.child, this.childWrapper, this.target).init(name, attrs);
            } else {
                new NestedElementHandler(this.helperImpl, this, this.child, this.childWrapper, this.target).init(name, attrs);
            }
        }
    }

    static class DataTypeHandler extends AbstractHandler {
        private Object element;
        private Target target;
        private RuntimeConfigurable wrapper;

        public DataTypeHandler(ProjectHelperImpl helperImpl, DocumentHandler parentHandler, Target target) {
            super(helperImpl, parentHandler);
            this.wrapper = null;
            this.target = target;
        }

        public void init(String propType, AttributeList attrs) throws SAXParseException {
            try {
                Object objCreateDataType = this.helperImpl.project.createDataType(propType);
                this.element = objCreateDataType;
                if (objCreateDataType == null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unknown data type ");
                    stringBuffer.append(propType);
                    throw new BuildException(stringBuffer.toString());
                }
                RuntimeConfigurable runtimeConfigurable = new RuntimeConfigurable(objCreateDataType, propType);
                this.wrapper = runtimeConfigurable;
                runtimeConfigurable.setAttributes(attrs);
                this.target.addDataType(this.wrapper);
            } catch (BuildException exc) {
                throw new SAXParseException(exc.getMessage(), this.helperImpl.locator, exc);
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] buf, int start, int count) {
            this.wrapper.addText(buf, start, count);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String name, AttributeList attrs) throws SAXParseException {
            new NestedElementHandler(this.helperImpl, this, this.element, this.wrapper, this.target).init(name, attrs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configureId(Object target, AttributeList attr) {
        String id = attr.getValue("id");
        if (id != null) {
            this.project.addReference(id, target);
        }
    }
}
