package org.apache.tools.ant;

import com.ts.lib.settings.common.constant.Constants;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.taskdefs.Typedef;

/* JADX INFO: loaded from: classes.dex */
public class ComponentHelper {
    static /* synthetic */ Class class$org$apache$tools$ant$Task;
    static /* synthetic */ Class class$org$apache$tools$ant$TaskAdapter;
    static /* synthetic */ Class class$org$apache$tools$ant$taskdefs$Property;
    private AntTypeTable antTypeTable;
    private ComponentHelper next;
    private Project project;
    private Hashtable taskClassDefinitions = new Hashtable();
    private boolean rebuildTaskClassDefinitions = true;
    private Hashtable typeClassDefinitions = new Hashtable();
    private boolean rebuildTypeClassDefinitions = true;
    private Set checkedNamespaces = new HashSet();
    private Stack antLibStack = new Stack();
    private String antLibCurrentUri = null;
    private Hashtable createdTasks = new Hashtable();

    public static ComponentHelper getComponentHelper(Project project) {
        ComponentHelper ph = (ComponentHelper) project.getReference("ant.ComponentHelper");
        if (ph != null) {
            return ph;
        }
        ComponentHelper ph2 = new ComponentHelper();
        ph2.setProject(project);
        project.addReference("ant.ComponentHelper", ph2);
        return ph2;
    }

    protected ComponentHelper() {
    }

    public void setNext(ComponentHelper next) {
        this.next = next;
    }

    public ComponentHelper getNext() {
        return this.next;
    }

    public void setProject(Project project) {
        this.project = project;
        this.antTypeTable = new AntTypeTable(project);
    }

    public void initSubProject(ComponentHelper helper) {
        AntTypeTable typeTable = helper.antTypeTable;
        for (AntTypeDefinition def : typeTable.values()) {
            this.antTypeTable.put(def.getName(), def);
        }
        Iterator i = helper.checkedNamespaces.iterator();
        while (i.hasNext()) {
            this.checkedNamespaces.add(i.next());
        }
    }

    public Object createComponent(UnknownElement ue, String ns, String componentType) throws BuildException {
        Object component = createComponent(componentType);
        if (component instanceof Task) {
            Task task = (Task) component;
            task.setLocation(ue.getLocation());
            task.setTaskType(componentType);
            task.setTaskName(ue.getTaskName());
            task.setOwningTarget(ue.getOwningTarget());
            task.init();
            addCreatedTask(componentType, task);
        }
        return component;
    }

    public Object createComponent(String componentName) {
        AntTypeDefinition def = getDefinition(componentName);
        if (def == null) {
            return null;
        }
        return def.create(this.project);
    }

    public Class getComponentClass(String componentName) {
        AntTypeDefinition def = getDefinition(componentName);
        if (def == null) {
            return null;
        }
        return def.getExposedClass(this.project);
    }

    public AntTypeDefinition getDefinition(String componentName) {
        checkNamespace(componentName);
        return this.antTypeTable.getDefinition(componentName);
    }

    public void initDefaultDefinitions() {
        initTasks();
        initTypes();
    }

    public void addTaskDefinition(String taskName, Class taskClass) {
        checkTaskClass(taskClass);
        AntTypeDefinition def = new AntTypeDefinition();
        def.setName(taskName);
        def.setClassLoader(taskClass.getClassLoader());
        def.setClass(taskClass);
        Class clsClass$ = class$org$apache$tools$ant$TaskAdapter;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.TaskAdapter");
            class$org$apache$tools$ant$TaskAdapter = clsClass$;
        }
        def.setAdapterClass(clsClass$);
        def.setClassName(taskClass.getName());
        Class clsClass$2 = class$org$apache$tools$ant$Task;
        if (clsClass$2 == null) {
            clsClass$2 = class$("org.apache.tools.ant.Task");
            class$org$apache$tools$ant$Task = clsClass$2;
        }
        def.setAdaptToClass(clsClass$2);
        updateDataTypeDefinition(def);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public void checkTaskClass(Class taskClass) throws BuildException {
        if (!Modifier.isPublic(taskClass.getModifiers())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(taskClass);
            stringBuffer.append(" is not public");
            String message = stringBuffer.toString();
            this.project.log(message, 0);
            throw new BuildException(message);
        }
        if (Modifier.isAbstract(taskClass.getModifiers())) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(taskClass);
            stringBuffer2.append(" is abstract");
            String message2 = stringBuffer2.toString();
            this.project.log(message2, 0);
            throw new BuildException(message2);
        }
        try {
            taskClass.getConstructor((Class[]) null);
            Class clsClass$ = class$org$apache$tools$ant$Task;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.tools.ant.Task");
                class$org$apache$tools$ant$Task = clsClass$;
            }
            if (!clsClass$.isAssignableFrom(taskClass)) {
                TaskAdapter.checkTaskClass(taskClass, this.project);
            }
        } catch (NoSuchMethodException e) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("No public no-arg constructor in ");
            stringBuffer3.append(taskClass);
            String message3 = stringBuffer3.toString();
            this.project.log(message3, 0);
            throw new BuildException(message3);
        }
    }

    public Hashtable getTaskDefinitions() {
        Class clsClass$;
        synchronized (this.taskClassDefinitions) {
            synchronized (this.antTypeTable) {
                if (this.rebuildTaskClassDefinitions) {
                    this.taskClassDefinitions.clear();
                    for (String name : this.antTypeTable.keySet()) {
                        Class<?> exposedClass = this.antTypeTable.getExposedClass(name);
                        if (exposedClass != null) {
                            if (class$org$apache$tools$ant$Task == null) {
                                clsClass$ = class$("org.apache.tools.ant.Task");
                                class$org$apache$tools$ant$Task = clsClass$;
                            } else {
                                clsClass$ = class$org$apache$tools$ant$Task;
                            }
                            if (clsClass$.isAssignableFrom(exposedClass)) {
                                this.taskClassDefinitions.put(name, this.antTypeTable.getTypeClass(name));
                            }
                        }
                    }
                    this.rebuildTaskClassDefinitions = false;
                }
            }
        }
        return this.taskClassDefinitions;
    }

    public Hashtable getDataTypeDefinitions() {
        Class clsClass$;
        synchronized (this.typeClassDefinitions) {
            synchronized (this.antTypeTable) {
                if (this.rebuildTypeClassDefinitions) {
                    this.typeClassDefinitions.clear();
                    for (String name : this.antTypeTable.keySet()) {
                        Class<?> exposedClass = this.antTypeTable.getExposedClass(name);
                        if (exposedClass != null) {
                            if (class$org$apache$tools$ant$Task == null) {
                                clsClass$ = class$("org.apache.tools.ant.Task");
                                class$org$apache$tools$ant$Task = clsClass$;
                            } else {
                                clsClass$ = class$org$apache$tools$ant$Task;
                            }
                            if (!clsClass$.isAssignableFrom(exposedClass)) {
                                this.typeClassDefinitions.put(name, this.antTypeTable.getTypeClass(name));
                            }
                        }
                    }
                    this.rebuildTypeClassDefinitions = false;
                }
            }
        }
        return this.typeClassDefinitions;
    }

    public void addDataTypeDefinition(String typeName, Class typeClass) {
        AntTypeDefinition def = new AntTypeDefinition();
        def.setName(typeName);
        def.setClass(typeClass);
        updateDataTypeDefinition(def);
        Project project = this.project;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" +User datatype: ");
        stringBuffer.append(typeName);
        stringBuffer.append("     ");
        stringBuffer.append(typeClass.getName());
        project.log(stringBuffer.toString(), 4);
    }

    public void addDataTypeDefinition(AntTypeDefinition def) {
        updateDataTypeDefinition(def);
    }

    public Hashtable getAntTypeTable() {
        return this.antTypeTable;
    }

    public Task createTask(String taskType) throws BuildException {
        Task task = createNewTask(taskType);
        if (task == null && taskType.equals(Constants.PROPERTY_SERVICE)) {
            Class clsClass$ = class$org$apache$tools$ant$taskdefs$Property;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.tools.ant.taskdefs.Property");
                class$org$apache$tools$ant$taskdefs$Property = clsClass$;
            }
            addTaskDefinition(Constants.PROPERTY_SERVICE, clsClass$);
            task = createNewTask(taskType);
        }
        if (task != null) {
            addCreatedTask(taskType, task);
        }
        return task;
    }

    private Task createNewTask(String taskType) throws BuildException {
        Task task;
        Class<?> componentClass = getComponentClass(taskType);
        if (componentClass != null) {
            Class clsClass$ = class$org$apache$tools$ant$Task;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.tools.ant.Task");
                class$org$apache$tools$ant$Task = clsClass$;
            }
            if (!clsClass$.isAssignableFrom(componentClass) || (task = (Task) createComponent(taskType)) == null) {
                return null;
            }
            task.setTaskType(taskType);
            task.setTaskName(taskType);
            Project project = this.project;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("   +Task: ");
            stringBuffer.append(taskType);
            project.log(stringBuffer.toString(), 4);
            return task;
        }
        return null;
    }

    private void addCreatedTask(String type, Task task) {
        synchronized (this.createdTasks) {
            Vector v = (Vector) this.createdTasks.get(type);
            if (v == null) {
                v = new Vector();
                this.createdTasks.put(type, v);
            }
            v.addElement(new WeakReference(task));
        }
    }

    private void invalidateCreatedTasks(String type) {
        synchronized (this.createdTasks) {
            Vector v = (Vector) this.createdTasks.get(type);
            if (v != null) {
                Enumeration taskEnum = v.elements();
                while (taskEnum.hasMoreElements()) {
                    WeakReference ref = (WeakReference) taskEnum.nextElement();
                    Task t = (Task) ref.get();
                    if (t != null) {
                        t.markInvalid();
                    }
                }
                v.removeAllElements();
                this.createdTasks.remove(type);
            }
        }
    }

    public Object createDataType(String typeName) throws BuildException {
        return createComponent(typeName);
    }

    public String getElementName(Object element) {
        Class cls = element.getClass();
        for (AntTypeDefinition antTypeDefinition : this.antTypeTable.values()) {
            if (cls == antTypeDefinition.getExposedClass(this.project)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The <");
                stringBuffer.append(antTypeDefinition.getName());
                stringBuffer.append("> type");
                return stringBuffer.toString();
            }
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Class ");
        stringBuffer2.append(cls.getName());
        return stringBuffer2.toString();
    }

    private boolean validDefinition(AntTypeDefinition def) {
        return (def.getTypeClass(this.project) == null || def.getExposedClass(this.project) == null) ? false : true;
    }

    private boolean sameDefinition(AntTypeDefinition def, AntTypeDefinition old) {
        boolean defValid = validDefinition(def);
        boolean sameValidity = defValid == validDefinition(old);
        return sameValidity && (!defValid || def.sameDefinition(old, this.project));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0067 A[Catch: all -> 0x0095, TryCatch #0 {, blocks: (B:5:0x0008, B:7:0x0014, B:9:0x001a, B:11:0x001c, B:13:0x0024, B:15:0x0028, B:17:0x0033, B:21:0x003c, B:25:0x004f, B:28:0x0062, B:30:0x0067, B:16:0x0031, B:31:0x006a, B:32:0x0093), top: B:37:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateDataTypeDefinition(org.apache.tools.ant.AntTypeDefinition r10) {
        /*
            r9 = this;
            java.lang.String r0 = r10.getName()
            org.apache.tools.ant.ComponentHelper$AntTypeTable r1 = r9.antTypeTable
            monitor-enter(r1)
            r2 = 1
            r9.rebuildTaskClassDefinitions = r2     // Catch: java.lang.Throwable -> L95
            r9.rebuildTypeClassDefinitions = r2     // Catch: java.lang.Throwable -> L95
            org.apache.tools.ant.ComponentHelper$AntTypeTable r3 = r9.antTypeTable     // Catch: java.lang.Throwable -> L95
            org.apache.tools.ant.AntTypeDefinition r3 = r3.getDefinition(r0)     // Catch: java.lang.Throwable -> L95
            if (r3 == 0) goto L6a
            boolean r4 = r9.sameDefinition(r10, r3)     // Catch: java.lang.Throwable -> L95
            if (r4 == 0) goto L1c
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L95
            return
        L1c:
            org.apache.tools.ant.ComponentHelper$AntTypeTable r4 = r9.antTypeTable     // Catch: java.lang.Throwable -> L95
            java.lang.Class r4 = r4.getExposedClass(r0)     // Catch: java.lang.Throwable -> L95
            if (r4 == 0) goto L3b
            java.lang.Class r5 = org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$Task     // Catch: java.lang.Throwable -> L95
            if (r5 != 0) goto L31
            java.lang.String r5 = "org.apache.tools.ant.Task"
            java.lang.Class r5 = class$(r5)     // Catch: java.lang.Throwable -> L95
            org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$Task = r5     // Catch: java.lang.Throwable -> L95
            goto L33
        L31:
            java.lang.Class r5 = org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$Task     // Catch: java.lang.Throwable -> L95
        L33:
            boolean r5 = r5.isAssignableFrom(r4)     // Catch: java.lang.Throwable -> L95
            if (r5 == 0) goto L3b
            r5 = r2
            goto L3c
        L3b:
            r5 = 0
        L3c:
            org.apache.tools.ant.Project r6 = r9.project     // Catch: java.lang.Throwable -> L95
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L95
            r7.<init>()     // Catch: java.lang.Throwable -> L95
            java.lang.String r8 = "Trying to override old definition of "
            r7.append(r8)     // Catch: java.lang.Throwable -> L95
            if (r5 == 0) goto L4d
            java.lang.String r8 = "task "
            goto L4f
        L4d:
            java.lang.String r8 = "datatype "
        L4f:
            r7.append(r8)     // Catch: java.lang.Throwable -> L95
            r7.append(r0)     // Catch: java.lang.Throwable -> L95
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L95
            org.apache.tools.ant.Project r8 = r9.project     // Catch: java.lang.Throwable -> L95
            boolean r8 = r10.similarDefinition(r3, r8)     // Catch: java.lang.Throwable -> L95
            if (r8 == 0) goto L62
            r2 = 3
        L62:
            r6.log(r7, r2)     // Catch: java.lang.Throwable -> L95
            if (r5 == 0) goto L6a
            r9.invalidateCreatedTasks(r0)     // Catch: java.lang.Throwable -> L95
        L6a:
            org.apache.tools.ant.Project r2 = r9.project     // Catch: java.lang.Throwable -> L95
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L95
            r4.<init>()     // Catch: java.lang.Throwable -> L95
            java.lang.String r5 = " +Datatype "
            r4.append(r5)     // Catch: java.lang.Throwable -> L95
            r4.append(r0)     // Catch: java.lang.Throwable -> L95
            java.lang.String r5 = " "
            r4.append(r5)     // Catch: java.lang.Throwable -> L95
            java.lang.String r5 = r10.getClassName()     // Catch: java.lang.Throwable -> L95
            r4.append(r5)     // Catch: java.lang.Throwable -> L95
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L95
            r5 = 4
            r2.log(r4, r5)     // Catch: java.lang.Throwable -> L95
            org.apache.tools.ant.ComponentHelper$AntTypeTable r2 = r9.antTypeTable     // Catch: java.lang.Throwable -> L95
            r2.put(r0, r10)     // Catch: java.lang.Throwable -> L95
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L95
            return
        L95:
            r2 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L95
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.ComponentHelper.updateDataTypeDefinition(org.apache.tools.ant.AntTypeDefinition):void");
    }

    public void enterAntLib(String uri) {
        this.antLibCurrentUri = uri;
        this.antLibStack.push(uri);
    }

    public String getCurrentAntlibUri() {
        return this.antLibCurrentUri;
    }

    public void exitAntLib() {
        this.antLibStack.pop();
        this.antLibCurrentUri = this.antLibStack.size() == 0 ? null : (String) this.antLibStack.peek();
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initTasks() throws java.lang.Throwable {
        /*
            r9 = this;
            r0 = 0
            org.apache.tools.ant.Project r1 = r9.project
            java.lang.ClassLoader r1 = r1.getCoreLoader()
            if (r1 == 0) goto L1f
            org.apache.tools.ant.Project r1 = r9.project
            java.lang.String r2 = "build.sysclasspath"
            java.lang.String r1 = r1.getProperty(r2)
            java.lang.String r2 = "only"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L1f
            org.apache.tools.ant.Project r1 = r9.project
            java.lang.ClassLoader r0 = r1.getCoreLoader()
        L1f:
            java.lang.String r1 = "/org/apache/tools/ant/taskdefs/defaults.properties"
            r2 = 0
            r3 = 0
            java.util.Properties r4 = new java.util.Properties     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r4.<init>()     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r3 = r4
            java.lang.Class r4 = r9.getClass()     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.io.InputStream r4 = r4.getResourceAsStream(r1)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r2 = r4
            if (r2 == 0) goto L8f
            r3.load(r2)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.util.Enumeration r4 = r3.propertyNames()     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
        L3c:
            boolean r5 = r4.hasMoreElements()     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            if (r5 != 0) goto L4d
        L43:
            if (r2 == 0) goto L4a
            r2.close()     // Catch: java.lang.Exception -> L49
            goto L4a
        L49:
            r4 = move-exception
        L4a:
            return
        L4d:
            java.lang.Object r5 = r4.nextElement()     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.lang.String r6 = r3.getProperty(r5)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            org.apache.tools.ant.AntTypeDefinition r7 = new org.apache.tools.ant.AntTypeDefinition     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r7.<init>()     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r7.setName(r5)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r7.setClassName(r6)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r7.setClassLoader(r0)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.lang.Class r8 = org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$Task     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            if (r8 != 0) goto L72
            java.lang.String r8 = "org.apache.tools.ant.Task"
            java.lang.Class r8 = class$(r8)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$Task = r8     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            goto L74
        L72:
            java.lang.Class r8 = org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$Task     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
        L74:
            r7.setAdaptToClass(r8)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.lang.Class r8 = org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$TaskAdapter     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            if (r8 != 0) goto L84
            java.lang.String r8 = "org.apache.tools.ant.TaskAdapter"
            java.lang.Class r8 = class$(r8)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$TaskAdapter = r8     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            goto L86
        L84:
            java.lang.Class r8 = org.apache.tools.ant.ComponentHelper.class$org$apache$tools$ant$TaskAdapter     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
        L86:
            r7.setAdapterClass(r8)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            org.apache.tools.ant.ComponentHelper$AntTypeTable r8 = r9.antTypeTable     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            r8.put(r5, r7)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            goto L3c
        L8f:
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.lang.String r5 = "Can't load default task list"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            throw r4     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
        L97:
            r4 = move-exception
            goto La3
        L99:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> La2
            java.lang.String r5 = "Can't load default type list"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> La2
            throw r4     // Catch: java.lang.Throwable -> La2
        La2:
            r4 = move-exception
        La3:
            if (r2 == 0) goto Laa
            r2.close()     // Catch: java.lang.Exception -> La9
            goto Laa
        La9:
            r5 = move-exception
        Laa:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.ComponentHelper.initTasks():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initTypes() throws java.lang.Throwable {
        /*
            r9 = this;
            r0 = 0
            org.apache.tools.ant.Project r1 = r9.project
            java.lang.ClassLoader r1 = r1.getCoreLoader()
            if (r1 == 0) goto L1f
            org.apache.tools.ant.Project r1 = r9.project
            java.lang.String r2 = "build.sysclasspath"
            java.lang.String r1 = r1.getProperty(r2)
            java.lang.String r2 = "only"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L1f
            org.apache.tools.ant.Project r1 = r9.project
            java.lang.ClassLoader r0 = r1.getCoreLoader()
        L1f:
            java.lang.String r1 = "/org/apache/tools/ant/types/defaults.properties"
            r2 = 0
            r3 = 0
            java.util.Properties r4 = new java.util.Properties     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            r4.<init>()     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            r3 = r4
            java.lang.Class r4 = r9.getClass()     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            java.io.InputStream r4 = r4.getResourceAsStream(r1)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            r2 = r4
            if (r2 == 0) goto L6b
            r3.load(r2)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            java.util.Enumeration r4 = r3.propertyNames()     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
        L3c:
            boolean r5 = r4.hasMoreElements()     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            if (r5 != 0) goto L4d
        L43:
            if (r2 == 0) goto L4a
            r2.close()     // Catch: java.lang.Exception -> L49
            goto L4a
        L49:
            r4 = move-exception
        L4a:
            return
        L4d:
            java.lang.Object r5 = r4.nextElement()     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            java.lang.String r6 = r3.getProperty(r5)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            org.apache.tools.ant.AntTypeDefinition r7 = new org.apache.tools.ant.AntTypeDefinition     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            r7.<init>()     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            r7.setName(r5)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            r7.setClassName(r6)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            r7.setClassLoader(r0)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            org.apache.tools.ant.ComponentHelper$AntTypeTable r8 = r9.antTypeTable     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            r8.put(r5, r7)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            goto L3c
        L6b:
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            java.lang.String r5 = "Can't load default datatype list"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            throw r4     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
        L73:
            r4 = move-exception
            goto L7f
        L75:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L7e
            java.lang.String r5 = "Can't load default type list"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L7e
            throw r4     // Catch: java.lang.Throwable -> L7e
        L7e:
            r4 = move-exception
        L7f:
            if (r2 == 0) goto L86
            r2.close()     // Catch: java.lang.Exception -> L85
            goto L86
        L85:
            r5 = move-exception
        L86:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.ComponentHelper.initTypes():void");
    }

    private synchronized void checkNamespace(String componentName) {
        String uri = ProjectHelper.extractUriFromComponentName(componentName);
        if ("".equals(uri)) {
            uri = ProjectHelper.ANT_CORE_URI;
        }
        if (uri.startsWith(ProjectHelper.ANTLIB_URI)) {
            if (this.checkedNamespaces.contains(uri)) {
                return;
            }
            this.checkedNamespaces.add(uri);
            Typedef definer = new Typedef();
            definer.setProject(this.project);
            definer.setURI(uri);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(uri.substring(ProjectHelper.ANTLIB_URI.length()).replace('.', '/'));
            stringBuffer.append("/antlib.xml");
            definer.setResource(stringBuffer.toString());
            definer.setOnError(new Definer.OnError("ignore"));
            definer.init();
            definer.execute();
        }
    }

    private static class AntTypeTable extends Hashtable {
        private Project project;

        public AntTypeTable(Project project) {
            this.project = project;
        }

        public AntTypeDefinition getDefinition(String key) {
            return (AntTypeDefinition) super.get(key);
        }

        @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
        public Object get(Object key) {
            return getTypeClass((String) key);
        }

        public Object create(String name) {
            AntTypeDefinition def = getDefinition(name);
            if (def == null) {
                return null;
            }
            return def.create(this.project);
        }

        public Class getTypeClass(String name) {
            AntTypeDefinition def = getDefinition(name);
            if (def == null) {
                return null;
            }
            return def.getTypeClass(this.project);
        }

        public Class getExposedClass(String name) {
            AntTypeDefinition def = getDefinition(name);
            if (def == null) {
                return null;
            }
            return def.getExposedClass(this.project);
        }

        @Override // java.util.Hashtable
        public boolean contains(Object clazz) {
            boolean found = false;
            if (clazz instanceof Class) {
                Iterator i = values().iterator();
                while (i.hasNext() && !found) {
                    found |= ((AntTypeDefinition) i.next()).getExposedClass(this.project) == clazz;
                }
            }
            return found;
        }

        @Override // java.util.Hashtable, java.util.Map
        public boolean containsValue(Object value) {
            return contains(value);
        }
    }
}
