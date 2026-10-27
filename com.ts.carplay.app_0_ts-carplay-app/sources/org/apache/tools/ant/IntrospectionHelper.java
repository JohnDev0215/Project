package org.apache.tools.ant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.tools.ant.taskdefs.PreSetDef;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* JADX INFO: loaded from: classes.dex */
public final class IntrospectionHelper implements BuildListener {
    static /* synthetic */ Class class$java$io$File;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$tools$ant$DynamicElement;
    static /* synthetic */ Class class$org$apache$tools$ant$DynamicElementNS;
    static /* synthetic */ Class class$org$apache$tools$ant$Location;
    static /* synthetic */ Class class$org$apache$tools$ant$Project;
    static /* synthetic */ Class class$org$apache$tools$ant$Task;
    static /* synthetic */ Class class$org$apache$tools$ant$TaskContainer;
    static /* synthetic */ Class class$org$apache$tools$ant$types$EnumeratedAttribute;
    private Method addText;
    private Class bean;
    private static final Map EMPTY_MAP = Collections.unmodifiableMap(new HashMap(0));
    private static Hashtable helpers = new Hashtable();
    private static final Hashtable PRIMITIVE_TYPE_MAP = new Hashtable(8);
    private Hashtable attributeTypes = new Hashtable();
    private Hashtable attributeSetters = new Hashtable();
    private Hashtable nestedTypes = new Hashtable();
    private Hashtable nestedCreators = new Hashtable();
    private List addTypeMethods = new ArrayList();

    static {
        Class[] primitives = {Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
        Class[] wrappers = new Class[8];
        Class clsClass$ = class$java$lang$Boolean;
        if (clsClass$ == null) {
            clsClass$ = class$("java.lang.Boolean");
            class$java$lang$Boolean = clsClass$;
        }
        wrappers[0] = clsClass$;
        Class clsClass$2 = class$java$lang$Byte;
        if (clsClass$2 == null) {
            clsClass$2 = class$("java.lang.Byte");
            class$java$lang$Byte = clsClass$2;
        }
        wrappers[1] = clsClass$2;
        Class clsClass$3 = class$java$lang$Character;
        if (clsClass$3 == null) {
            clsClass$3 = class$("java.lang.Character");
            class$java$lang$Character = clsClass$3;
        }
        wrappers[2] = clsClass$3;
        Class clsClass$4 = class$java$lang$Short;
        if (clsClass$4 == null) {
            clsClass$4 = class$("java.lang.Short");
            class$java$lang$Short = clsClass$4;
        }
        wrappers[3] = clsClass$4;
        Class clsClass$5 = class$java$lang$Integer;
        if (clsClass$5 == null) {
            clsClass$5 = class$("java.lang.Integer");
            class$java$lang$Integer = clsClass$5;
        }
        wrappers[4] = clsClass$5;
        Class clsClass$6 = class$java$lang$Long;
        if (clsClass$6 == null) {
            clsClass$6 = class$("java.lang.Long");
            class$java$lang$Long = clsClass$6;
        }
        wrappers[5] = clsClass$6;
        Class clsClass$7 = class$java$lang$Float;
        if (clsClass$7 == null) {
            clsClass$7 = class$("java.lang.Float");
            class$java$lang$Float = clsClass$7;
        }
        wrappers[6] = clsClass$7;
        Class clsClass$8 = class$java$lang$Double;
        if (clsClass$8 == null) {
            clsClass$8 = class$("java.lang.Double");
            class$java$lang$Double = clsClass$8;
        }
        wrappers[7] = clsClass$8;
        for (int i = 0; i < primitives.length; i++) {
            PRIMITIVE_TYPE_MAP.put(primitives[i], wrappers[i]);
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private IntrospectionHelper(java.lang.Class r18) throws java.lang.NoSuchMethodException {
        /*
            Method dump skipped, instruction units count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.IntrospectionHelper.<init>(java.lang.Class):void");
    }

    private boolean isHiddenSetMethod(String name, Class type) {
        if ("setLocation".equals(name)) {
            Class clsClass$ = class$org$apache$tools$ant$Location;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.tools.ant.Location");
                class$org$apache$tools$ant$Location = clsClass$;
            }
            if (clsClass$.equals(type)) {
                return true;
            }
        }
        if (!"setTaskType".equals(name)) {
            return false;
        }
        Class clsClass$2 = class$java$lang$String;
        if (clsClass$2 == null) {
            clsClass$2 = class$("java.lang.String");
            class$java$lang$String = clsClass$2;
        }
        return clsClass$2.equals(type);
    }

    public static synchronized IntrospectionHelper getHelper(Class c) {
        IntrospectionHelper ih;
        ih = (IntrospectionHelper) helpers.get(c);
        if (ih == null) {
            ih = new IntrospectionHelper(c);
            helpers.put(c, ih);
        }
        return ih;
    }

    public static IntrospectionHelper getHelper(Project p, Class c) {
        IntrospectionHelper ih = getHelper(c);
        p.addBuildListener(ih);
        return ih;
    }

    public void setAttribute(Project p, Object element, String attributeName, String value) throws BuildException {
        String qName;
        AttributeSetter as = (AttributeSetter) this.attributeSetters.get(attributeName.toLowerCase(Locale.US));
        if (as == null) {
            if (element instanceof DynamicAttributeNS) {
                DynamicAttributeNS dc = (DynamicAttributeNS) element;
                String uriPlusPrefix = ProjectHelper.extractUriFromComponentName(attributeName);
                String uri = ProjectHelper.extractUriFromComponentName(uriPlusPrefix);
                String localName = ProjectHelper.extractNameFromComponentName(attributeName);
                if ("".equals(uri)) {
                    qName = localName;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(uri);
                    stringBuffer.append(":");
                    stringBuffer.append(localName);
                    qName = stringBuffer.toString();
                }
                dc.setDynamicAttribute(uri, localName, qName, value);
                return;
            }
            if (element instanceof DynamicAttribute) {
                DynamicAttribute dc2 = (DynamicAttribute) element;
                dc2.setDynamicAttribute(attributeName.toLowerCase(Locale.US), value);
                return;
            } else {
                if (attributeName.indexOf(58) != -1) {
                    return;
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(getElementName(p, element));
                stringBuffer2.append(" doesn't support the \"");
                stringBuffer2.append(attributeName);
                stringBuffer2.append("\" attribute.");
                String msg = stringBuffer2.toString();
                throw new UnsupportedAttributeException(msg, attributeName);
            }
        }
        try {
            as.set(p, element, value);
        } catch (IllegalAccessException ie) {
            throw new BuildException(ie);
        } catch (InvocationTargetException ite) {
            Throwable t = ite.getTargetException();
            if (t instanceof BuildException) {
                throw ((BuildException) t);
            }
            throw new BuildException(t);
        }
    }

    public void addText(Project project, Object element, String text) throws BuildException {
        Method method = this.addText;
        if (method == null) {
            if (text.trim().length() == 0) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(project.getElementName(element));
            stringBuffer.append(" doesn't support nested text data.");
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
        try {
            method.invoke(element, text);
        } catch (IllegalAccessException ie) {
            throw new BuildException(ie);
        } catch (InvocationTargetException ite) {
            Throwable t = ite.getTargetException();
            if (t instanceof BuildException) {
                throw ((BuildException) t);
            }
            throw new BuildException(t);
        }
    }

    public void throwNotSupported(Project project, Object parent, String elementName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(project.getElementName(parent));
        stringBuffer.append(" doesn't support the nested \"");
        stringBuffer.append(elementName);
        stringBuffer.append("\" element.");
        String msg = stringBuffer.toString();
        throw new UnsupportedElementException(msg, elementName);
    }

    private NestedCreator getNestedCreator(Project project, String parentUri, Object parent, String elementName, UnknownElement child) throws BuildException {
        String uri = ProjectHelper.extractUriFromComponentName(elementName);
        String name = ProjectHelper.extractNameFromComponentName(elementName);
        if (uri.equals(ProjectHelper.ANT_CORE_URI)) {
            uri = "";
        }
        if (parentUri.equals(ProjectHelper.ANT_CORE_URI)) {
            parentUri = "";
        }
        NestedCreator nc = null;
        if (uri.equals(parentUri) || uri.equals("")) {
            nc = (NestedCreator) this.nestedCreators.get(name.toLowerCase(Locale.US));
        }
        if (nc == null) {
            nc = createAddTypeCreator(project, parent, elementName);
        }
        Method method = null;
        if (nc == null && (parent instanceof DynamicElementNS)) {
            DynamicElementNS dc = (DynamicElementNS) parent;
            String qName = child == null ? name : child.getQName();
            final Object nestedElement = dc.createDynamicElement(child != null ? child.getNamespace() : "", name, qName);
            if (nestedElement != null) {
                nc = new NestedCreator(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.1
                    private final /* synthetic */ IntrospectionHelper this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
                    Object create(Project project2, Object parent2, Object ignore) {
                        return nestedElement;
                    }
                };
            }
        }
        if (nc == null && (parent instanceof DynamicElement)) {
            DynamicElement dc2 = (DynamicElement) parent;
            final Object nestedElement2 = dc2.createDynamicElement(name.toLowerCase(Locale.US));
            if (nestedElement2 != null) {
                nc = new NestedCreator(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.2
                    private final /* synthetic */ IntrospectionHelper this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
                    Object create(Project project2, Object parent2, Object ignore) {
                        return nestedElement2;
                    }
                };
            }
        }
        if (nc == null) {
            throwNotSupported(project, parent, elementName);
        }
        return nc;
    }

    public Object createElement(Project project, Object parent, String elementName) throws BuildException {
        InstantiationException ine;
        NestedCreator nc = getNestedCreator(project, "", parent, elementName, null);
        try {
            try {
                Object nestedElement = nc.create(project, parent, null);
                if (project != null) {
                    try {
                        project.setProjectReference(nestedElement);
                    } catch (InstantiationException e) {
                        ine = e;
                        throw new BuildException(ine);
                    } catch (InvocationTargetException e2) {
                        ite = e2;
                        Throwable t = ite.getTargetException();
                        if (t instanceof BuildException) {
                            throw ((BuildException) t);
                        }
                        throw new BuildException(t);
                    }
                }
                return nestedElement;
            } catch (InstantiationException e3) {
                ine = e3;
            } catch (InvocationTargetException e4) {
                ite = e4;
            }
        } catch (IllegalAccessException ie) {
            throw new BuildException(ie);
        }
    }

    public Creator getElementCreator(Project project, String parentUri, Object parent, String elementName, UnknownElement ue) {
        NestedCreator nc = getNestedCreator(project, parentUri, parent, elementName, ue);
        return new Creator(project, parent, nc);
    }

    public boolean isDynamic() {
        Class clsClass$ = class$org$apache$tools$ant$DynamicElement;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.DynamicElement");
            class$org$apache$tools$ant$DynamicElement = clsClass$;
        }
        if (!clsClass$.isAssignableFrom(this.bean)) {
            Class clsClass$2 = class$org$apache$tools$ant$DynamicElementNS;
            if (clsClass$2 == null) {
                clsClass$2 = class$("org.apache.tools.ant.DynamicElementNS");
                class$org$apache$tools$ant$DynamicElementNS = clsClass$2;
            }
            if (!clsClass$2.isAssignableFrom(this.bean)) {
                return false;
            }
        }
        return true;
    }

    public boolean isContainer() {
        Class clsClass$ = class$org$apache$tools$ant$TaskContainer;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.TaskContainer");
            class$org$apache$tools$ant$TaskContainer = clsClass$;
        }
        return clsClass$.isAssignableFrom(this.bean);
    }

    public boolean supportsNestedElement(String elementName) {
        return this.nestedCreators.containsKey(elementName.toLowerCase(Locale.US)) || isDynamic() || this.addTypeMethods.size() != 0;
    }

    public boolean supportsNestedElement(String parentUri, String elementName) {
        if (parentUri.equals(ProjectHelper.ANT_CORE_URI)) {
            parentUri = "";
        }
        String uri = ProjectHelper.extractUriFromComponentName(elementName);
        if (uri.equals(ProjectHelper.ANT_CORE_URI)) {
            uri = "";
        }
        String name = ProjectHelper.extractNameFromComponentName(elementName);
        return (this.nestedCreators.containsKey(name.toLowerCase(Locale.US)) && (uri.equals(parentUri) || "".equals(uri))) || isDynamic() || this.addTypeMethods.size() != 0;
    }

    public void storeElement(Project project, Object parent, Object child, String elementName) throws BuildException {
        NestedCreator ns;
        if (elementName == null || (ns = (NestedCreator) this.nestedCreators.get(elementName.toLowerCase(Locale.US))) == null) {
            return;
        }
        try {
            ns.store(parent, child);
        } catch (IllegalAccessException ie) {
            throw new BuildException(ie);
        } catch (InstantiationException ine) {
            throw new BuildException(ine);
        } catch (InvocationTargetException ite) {
            Throwable t = ite.getTargetException();
            if (t instanceof BuildException) {
                throw ((BuildException) t);
            }
            throw new BuildException(t);
        }
    }

    public Class getElementType(String elementName) throws BuildException {
        Class nt = (Class) this.nestedTypes.get(elementName);
        if (nt == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Class ");
            stringBuffer.append(this.bean.getName());
            stringBuffer.append(" doesn't support the nested \"");
            stringBuffer.append(elementName);
            stringBuffer.append("\" element.");
            throw new UnsupportedElementException(stringBuffer.toString(), elementName);
        }
        return nt;
    }

    public Class getAttributeType(String attributeName) throws BuildException {
        Class at = (Class) this.attributeTypes.get(attributeName);
        if (at == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Class ");
            stringBuffer.append(this.bean.getName());
            stringBuffer.append(" doesn't support the \"");
            stringBuffer.append(attributeName);
            stringBuffer.append("\" attribute.");
            throw new UnsupportedAttributeException(stringBuffer.toString(), attributeName);
        }
        return at;
    }

    public Method getAddTextMethod() throws BuildException {
        if (!supportsCharacters()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Class ");
            stringBuffer.append(this.bean.getName());
            stringBuffer.append(" doesn't support nested text data.");
            throw new BuildException(stringBuffer.toString());
        }
        return this.addText;
    }

    public Method getElementMethod(String elementName) throws BuildException {
        Object creator = this.nestedCreators.get(elementName);
        if (creator == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Class ");
            stringBuffer.append(this.bean.getName());
            stringBuffer.append(" doesn't support the nested \"");
            stringBuffer.append(elementName);
            stringBuffer.append("\" element.");
            throw new UnsupportedElementException(stringBuffer.toString(), elementName);
        }
        return ((NestedCreator) creator).method;
    }

    public Method getAttributeMethod(String attributeName) throws BuildException {
        Object setter = this.attributeSetters.get(attributeName);
        if (setter == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Class ");
            stringBuffer.append(this.bean.getName());
            stringBuffer.append(" doesn't support the \"");
            stringBuffer.append(attributeName);
            stringBuffer.append("\" attribute.");
            throw new UnsupportedAttributeException(stringBuffer.toString(), attributeName);
        }
        return ((AttributeSetter) setter).method;
    }

    public boolean supportsCharacters() {
        return this.addText != null;
    }

    public Enumeration getAttributes() {
        return this.attributeSetters.keys();
    }

    public Map getAttributeMap() {
        return this.attributeTypes.size() < 1 ? EMPTY_MAP : Collections.unmodifiableMap(this.attributeTypes);
    }

    public Enumeration getNestedElements() {
        return this.nestedTypes.keys();
    }

    public Map getNestedElementMap() {
        return this.nestedTypes.size() < 1 ? EMPTY_MAP : Collections.unmodifiableMap(this.nestedTypes);
    }

    public List getExtensionPoints() {
        return this.addTypeMethods.size() < 1 ? Collections.EMPTY_LIST : Collections.unmodifiableList(this.addTypeMethods);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AttributeSetter createAttributeSetter(final Method method, Class cls, final String str) throws NoSuchMethodException {
        Class<?> clsClass$;
        Constructor constructor;
        boolean z;
        Class<?> clsClass$2;
        Class<?> clsClass$3;
        final Class cls2 = PRIMITIVE_TYPE_MAP.containsKey(cls) ? (Class) PRIMITIVE_TYPE_MAP.get(cls) : cls;
        Class clsClass$4 = class$java$lang$String;
        if (clsClass$4 == null) {
            clsClass$4 = class$("java.lang.String");
            class$java$lang$String = clsClass$4;
        }
        if (clsClass$4.equals(cls2)) {
            return new AttributeSetter(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.3
                private final /* synthetic */ IntrospectionHelper this$0;

                {
                    this.this$0 = this;
                }

                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project p, Object parent, String value) throws IllegalAccessException, InvocationTargetException {
                    method.invoke(parent, value);
                }
            };
        }
        Class clsClass$5 = class$java$lang$Character;
        if (clsClass$5 == null) {
            clsClass$5 = class$("java.lang.Character");
            class$java$lang$Character = clsClass$5;
        }
        if (clsClass$5.equals(cls2)) {
            return new AttributeSetter(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.4
                private final /* synthetic */ IntrospectionHelper this$0;

                {
                    this.this$0 = this;
                }

                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project p, Object parent, String value) throws IllegalAccessException, InvocationTargetException {
                    if (value.length() == 0) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("The value \"\" is not a legal value for attribute \"");
                        stringBuffer.append(str);
                        stringBuffer.append("\"");
                        throw new BuildException(stringBuffer.toString());
                    }
                    method.invoke(parent, new Character(value.charAt(0)));
                }
            };
        }
        Class clsClass$6 = class$java$lang$Boolean;
        if (clsClass$6 == null) {
            clsClass$6 = class$("java.lang.Boolean");
            class$java$lang$Boolean = clsClass$6;
        }
        if (clsClass$6.equals(cls2)) {
            return new AttributeSetter(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.5
                private final /* synthetic */ IntrospectionHelper this$0;

                {
                    this.this$0 = this;
                }

                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project p, Object parent, String value) throws IllegalAccessException, InvocationTargetException {
                    Method method2 = method;
                    Boolean[] boolArr = new Boolean[1];
                    boolArr[0] = Project.toBoolean(value) ? Boolean.TRUE : Boolean.FALSE;
                    method2.invoke(parent, boolArr);
                }
            };
        }
        Class clsClass$7 = class$java$lang$Class;
        if (clsClass$7 == null) {
            clsClass$7 = class$("java.lang.Class");
            class$java$lang$Class = clsClass$7;
        }
        if (clsClass$7.equals(cls2)) {
            return new AttributeSetter(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.6
                private final /* synthetic */ IntrospectionHelper this$0;

                {
                    this.this$0 = this;
                }

                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project p, Object parent, String value) throws IllegalAccessException, BuildException, InvocationTargetException {
                    try {
                        method.invoke(parent, Class.forName(value));
                    } catch (ClassNotFoundException ce) {
                        throw new BuildException(ce);
                    }
                }
            };
        }
        Class clsClass$8 = class$java$io$File;
        if (clsClass$8 == null) {
            clsClass$8 = class$("java.io.File");
            class$java$io$File = clsClass$8;
        }
        if (clsClass$8.equals(cls2)) {
            return new AttributeSetter(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.7
                private final /* synthetic */ IntrospectionHelper this$0;

                {
                    this.this$0 = this;
                }

                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project p, Object parent, String value) throws IllegalAccessException, InvocationTargetException {
                    method.invoke(parent, p.resolveFile(value));
                }
            };
        }
        Class cls3 = class$org$apache$tools$ant$types$EnumeratedAttribute;
        Class cls4 = cls3;
        if (cls3 == null) {
            Class clsClass$9 = class$("org.apache.tools.ant.types.EnumeratedAttribute");
            class$org$apache$tools$ant$types$EnumeratedAttribute = clsClass$9;
            cls4 = clsClass$9;
        }
        if (cls4.isAssignableFrom(cls2)) {
            return new AttributeSetter(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.8
                private final /* synthetic */ IntrospectionHelper this$0;

                {
                    this.this$0 = this;
                }

                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project p, Object parent, String value) throws IllegalAccessException, BuildException, InvocationTargetException {
                    try {
                        EnumeratedAttribute ea = (EnumeratedAttribute) cls2.newInstance();
                        ea.setValue(value);
                        method.invoke(parent, ea);
                    } catch (InstantiationException ie) {
                        throw new BuildException(ie);
                    }
                }
            };
        }
        try {
            Class<?>[] clsArr = new Class[2];
            if (class$org$apache$tools$ant$Project == null) {
                clsClass$2 = class$("org.apache.tools.ant.Project");
                class$org$apache$tools$ant$Project = clsClass$2;
            } else {
                clsClass$2 = class$org$apache$tools$ant$Project;
            }
            clsArr[0] = clsClass$2;
            if (class$java$lang$String == null) {
                clsClass$3 = class$("java.lang.String");
                class$java$lang$String = clsClass$3;
            } else {
                clsClass$3 = class$java$lang$String;
            }
            clsArr[1] = clsClass$3;
            constructor = cls2.getConstructor(clsArr);
            z = true;
        } catch (NoSuchMethodException e) {
            try {
                Class<?>[] clsArr2 = new Class[1];
                if (class$java$lang$String == null) {
                    clsClass$ = class$("java.lang.String");
                    class$java$lang$String = clsClass$;
                } else {
                    clsClass$ = class$java$lang$String;
                }
                clsArr2[0] = clsClass$;
                constructor = cls2.getConstructor(clsArr2);
                z = false;
            } catch (NoSuchMethodException e2) {
                return null;
            }
        }
        final boolean z2 = z;
        final Constructor constructor2 = constructor;
        return new AttributeSetter(this, method) { // from class: org.apache.tools.ant.IntrospectionHelper.9
            private final /* synthetic */ IntrospectionHelper this$0;

            {
                this.this$0 = this;
            }

            @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
            public void set(Project p, Object parent, String value) throws IllegalAccessException, BuildException, InvocationTargetException {
                try {
                    Object[] args = z2 ? new Object[]{p, value} : new Object[]{value};
                    Object attribute = constructor2.newInstance(args);
                    if (p != null) {
                        p.setProjectReference(attribute);
                    }
                    method.invoke(parent, attribute);
                } catch (InstantiationException ie) {
                    throw new BuildException(ie);
                }
            }
        };
    }

    protected String getElementName(Project project, Object element) {
        return project.getElementName(element);
    }

    private String getPropertyName(String methodName, String prefix) {
        return methodName.substring(prefix.length()).toLowerCase(Locale.US);
    }

    public static final class Creator {
        private NestedCreator nestedCreator;
        private Object nestedObject;
        private Object parent;
        private String polyType;
        private Project project;

        private Creator(Project project, Object parent, NestedCreator nestedCreator) {
            this.project = project;
            this.parent = parent;
            this.nestedCreator = nestedCreator;
        }

        public void setPolyType(String polyType) {
            this.polyType = polyType;
        }

        public Object create() {
            if (this.polyType != null) {
                if (!this.nestedCreator.isPolyMorphic()) {
                    throw new BuildException("Not allowed to use the polymorphic form for this element");
                }
                this.nestedCreator.getElementClass();
                ComponentHelper helper = ComponentHelper.getComponentHelper(this.project);
                Object objCreateComponent = helper.createComponent(this.polyType);
                this.nestedObject = objCreateComponent;
                if (objCreateComponent == null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unable to create object of type ");
                    stringBuffer.append(this.polyType);
                    throw new BuildException(stringBuffer.toString());
                }
            }
            try {
                Object objCreate = this.nestedCreator.create(this.project, this.parent, this.nestedObject);
                this.nestedObject = objCreate;
                if (this.project != null) {
                    this.project.setProjectReference(objCreate);
                }
                return this.nestedObject;
            } catch (IllegalAccessException ex) {
                throw new BuildException(ex);
            } catch (IllegalArgumentException ex2) {
                if (this.polyType != null) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Invalid type used ");
                    stringBuffer2.append(this.polyType);
                    throw new BuildException(stringBuffer2.toString());
                }
                throw ex2;
            } catch (InstantiationException ex3) {
                throw new BuildException(ex3);
            } catch (InvocationTargetException ex4) {
                Throwable t = ex4.getTargetException();
                if (t instanceof BuildException) {
                    throw ((BuildException) t);
                }
                throw new BuildException(t);
            }
        }

        public Object getRealObject() {
            return this.nestedCreator.getRealObject();
        }

        public void store() {
            try {
                this.nestedCreator.store(this.parent, this.nestedObject);
            } catch (IllegalAccessException ex) {
                throw new BuildException(ex);
            } catch (IllegalArgumentException ex2) {
                if (this.polyType != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Invalid type used ");
                    stringBuffer.append(this.polyType);
                    throw new BuildException(stringBuffer.toString());
                }
                throw ex2;
            } catch (InstantiationException ex3) {
                throw new BuildException(ex3);
            } catch (InvocationTargetException ex4) {
                Throwable t = ex4.getTargetException();
                if (t instanceof BuildException) {
                    throw ((BuildException) t);
                }
                throw new BuildException(t);
            }
        }
    }

    private static abstract class NestedCreator {
        Method method;

        abstract Object create(Project project, Object obj, Object obj2) throws IllegalAccessException, InstantiationException, InvocationTargetException;

        NestedCreator(Method m) {
            this.method = m;
        }

        boolean isPolyMorphic() {
            return false;
        }

        Class getElementClass() {
            return null;
        }

        Object getRealObject() {
            return null;
        }

        void store(Object parent, Object child) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        }
    }

    private class CreateNestedCreator extends NestedCreator {
        private final /* synthetic */ IntrospectionHelper this$0;

        CreateNestedCreator(IntrospectionHelper this$0, Method m) {
            super(m);
            this.this$0 = this$0;
        }

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        Object create(Project project, Object parent, Object ignore) throws IllegalAccessException, InvocationTargetException {
            return this.method.invoke(parent, new Object[0]);
        }
    }

    private class AddNestedCreator extends NestedCreator {
        static final int ADD = 1;
        static final int ADD_CONFIGURED = 2;
        protected int behavior;
        protected Constructor constructor;
        private final /* synthetic */ IntrospectionHelper this$0;

        AddNestedCreator(IntrospectionHelper this$0, Method m, Constructor c, int behavior) {
            super(m);
            this.this$0 = this$0;
            this.constructor = c;
            this.behavior = behavior;
        }

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        boolean isPolyMorphic() {
            return true;
        }

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        Class getElementClass() {
            return this.constructor.getDeclaringClass();
        }

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        Object create(Project project, Object parent, Object child) throws IllegalAccessException, InstantiationException, InvocationTargetException {
            if (child == null) {
                Constructor constructor = this.constructor;
                child = constructor.newInstance(constructor.getParameterTypes().length == 0 ? new Object[0] : new Object[]{project});
            }
            if (child instanceof PreSetDef.PreSetDefinition) {
                child = ((PreSetDef.PreSetDefinition) child).createObject(project);
            }
            if (this.behavior == 1) {
                istore(parent, child);
            }
            return child;
        }

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        void store(Object parent, Object child) throws IllegalAccessException, InstantiationException, InvocationTargetException {
            if (this.behavior == 2) {
                istore(parent, child);
            }
        }

        private void istore(Object parent, Object child) throws IllegalAccessException, InstantiationException, InvocationTargetException {
            this.method.invoke(parent, child);
        }
    }

    private static abstract class AttributeSetter {
        Method method;

        abstract void set(Project project, Object obj, String str) throws IllegalAccessException, BuildException, InvocationTargetException;

        AttributeSetter(Method m) {
            this.method = m;
        }
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent event) {
        this.attributeTypes.clear();
        this.attributeSetters.clear();
        this.nestedTypes.clear();
        this.nestedCreators.clear();
        this.addText = null;
        helpers.clear();
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent event) {
    }

    private NestedCreator createAddTypeCreator(Project project, Object parent, String elementName) throws BuildException {
        ComponentHelper helper;
        Class clazz;
        Method addMethod;
        final Object addedObject;
        if (this.addTypeMethods.size() == 0 || (clazz = (helper = ComponentHelper.getComponentHelper(project)).getComponentClass(elementName)) == null || (addMethod = findMatchingMethod(clazz, this.addTypeMethods)) == null || (addedObject = helper.createComponent(elementName)) == null) {
            return null;
        }
        Object rObject = addedObject;
        if (addedObject instanceof PreSetDef.PreSetDefinition) {
            rObject = ((PreSetDef.PreSetDefinition) addedObject).createObject(project);
        }
        final Object realObject = rObject;
        return new NestedCreator(this, addMethod) { // from class: org.apache.tools.ant.IntrospectionHelper.10
            private final /* synthetic */ IntrospectionHelper this$0;

            @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
            Object create(Project project2, Object parent2, Object ignore) throws IllegalAccessException, InvocationTargetException {
                if (!this.method.getName().endsWith("Configured")) {
                    this.method.invoke(parent2, realObject);
                }
                return addedObject;
            }

            @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
            Object getRealObject() {
                return realObject;
            }

            {
                this.this$0 = this;
            }

            @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
            void store(Object parent2, Object child) throws IllegalAccessException, InstantiationException, InvocationTargetException {
                if (this.method.getName().endsWith("Configured")) {
                    this.method.invoke(parent2, realObject);
                }
            }
        };
    }

    private void insertAddTypeMethod(Method method) {
        Class<?> cls = method.getParameterTypes()[0];
        for (int c = 0; c < this.addTypeMethods.size(); c++) {
            Method current = (Method) this.addTypeMethods.get(c);
            if (current.getParameterTypes()[0].equals(cls)) {
                return;
            }
            if (current.getParameterTypes()[0].isAssignableFrom(cls)) {
                this.addTypeMethods.add(c, method);
                return;
            }
        }
        this.addTypeMethods.add(method);
    }

    private Method findMatchingMethod(Class paramClass, List methods) {
        Class<?> cls = null;
        Method matchedMethod = null;
        for (int i = 0; i < methods.size(); i++) {
            Method method = (Method) methods.get(i);
            Class<?> cls2 = method.getParameterTypes()[0];
            if (cls2.isAssignableFrom(paramClass)) {
                if (cls == null) {
                    cls = cls2;
                    matchedMethod = method;
                } else if (!cls2.isAssignableFrom(cls)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("ambiguous: types ");
                    stringBuffer.append(cls.getName());
                    stringBuffer.append(" and ");
                    stringBuffer.append(cls2.getName());
                    stringBuffer.append(" match ");
                    stringBuffer.append(paramClass.getName());
                    throw new BuildException(stringBuffer.toString());
                }
            }
        }
        return matchedMethod;
    }
}
