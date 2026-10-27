package org.apache.tools.ant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.util.CollectionUtils;
import org.xml.sax.AttributeList;
import org.xml.sax.helpers.AttributeListImpl;

/* JADX INFO: loaded from: classes.dex */
public class RuntimeConfigurable implements Serializable {
    private static final String ANT_TYPE = "ant-type";
    private transient AttributeList attributes;
    private transient IntrospectionHelper.Creator creator;
    private String elementTag;
    private boolean proxyConfigured;
    private transient Object wrappedObject;
    private List children = null;
    private List attributeNames = null;
    private Map attributeMap = null;
    private StringBuffer characters = null;
    private String polyType = null;

    public RuntimeConfigurable(Object proxy, String elementTag) {
        this.elementTag = null;
        this.wrappedObject = null;
        this.proxyConfigured = false;
        this.wrappedObject = proxy;
        this.elementTag = elementTag;
        this.proxyConfigured = false;
        if (proxy instanceof Task) {
            ((Task) proxy).setRuntimeConfigurableWrapper(this);
        }
    }

    public void setProxy(Object proxy) {
        this.wrappedObject = proxy;
        this.proxyConfigured = false;
    }

    void setCreator(IntrospectionHelper.Creator creator) {
        this.creator = creator;
    }

    public Object getProxy() {
        return this.wrappedObject;
    }

    public String getPolyType() {
        return this.polyType;
    }

    public void setPolyType(String polyType) {
        this.polyType = polyType;
    }

    public void setAttributes(AttributeList attributes) {
        this.attributes = new AttributeListImpl(attributes);
        for (int i = 0; i < attributes.getLength(); i++) {
            setAttribute(attributes.getName(i), attributes.getValue(i));
        }
    }

    public void setAttribute(String name, String value) {
        if (name.equalsIgnoreCase("ant-type")) {
            this.polyType = value;
            return;
        }
        if (this.attributeNames == null) {
            this.attributeNames = new ArrayList();
            this.attributeMap = new HashMap();
        }
        this.attributeNames.add(name);
        this.attributeMap.put(name, value);
    }

    public Hashtable getAttributeMap() {
        if (this.attributeMap != null) {
            return new Hashtable(this.attributeMap);
        }
        return new Hashtable(1);
    }

    public AttributeList getAttributes() {
        return this.attributes;
    }

    public void addChild(RuntimeConfigurable child) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(child);
    }

    RuntimeConfigurable getChild(int index) {
        return (RuntimeConfigurable) this.children.get(index);
    }

    public Enumeration getChildren() {
        List list = this.children;
        if (list != null) {
            return Collections.enumeration(list);
        }
        return new CollectionUtils.EmptyEnumeration();
    }

    public void addText(String data) {
        if (data.length() == 0) {
            return;
        }
        StringBuffer stringBuffer = this.characters;
        if (stringBuffer != null) {
            stringBuffer.append(data);
        } else {
            this.characters = new StringBuffer(data);
        }
    }

    public void addText(char[] buf, int start, int count) {
        if (count == 0) {
            return;
        }
        if (this.characters == null) {
            this.characters = new StringBuffer(count);
        }
        this.characters.append(buf, start, count);
    }

    public StringBuffer getText() {
        StringBuffer stringBuffer = this.characters;
        if (stringBuffer != null) {
            return stringBuffer;
        }
        return new StringBuffer(0);
    }

    public String getElementTag() {
        return this.elementTag;
    }

    public void maybeConfigure(Project p) throws BuildException {
        maybeConfigure(p, true);
    }

    public void maybeConfigure(Project p, boolean configureChildren) throws BuildException {
        String id = null;
        if (this.proxyConfigured) {
            return;
        }
        Object target = this.wrappedObject;
        if (target instanceof TypeAdapter) {
            target = ((TypeAdapter) target).getProxy();
        }
        IntrospectionHelper ih = IntrospectionHelper.getHelper(p, target.getClass());
        if (this.attributeNames != null) {
            for (int i = 0; i < this.attributeNames.size(); i++) {
                String name = (String) this.attributeNames.get(i);
                String value = (String) this.attributeMap.get(name);
                try {
                    ih.setAttribute(p, target, name.toLowerCase(Locale.US), p.replaceProperties(value));
                } catch (BuildException be) {
                    if (!name.equals("id")) {
                        throw be;
                    }
                }
            }
            id = (String) this.attributeMap.get("id");
        }
        StringBuffer stringBuffer = this.characters;
        if (stringBuffer != null) {
            ProjectHelper.addText(p, this.wrappedObject, stringBuffer.substring(0));
        }
        Enumeration e = getChildren();
        while (e.hasMoreElements()) {
            RuntimeConfigurable child = (RuntimeConfigurable) e.nextElement();
            Object obj = child.wrappedObject;
            if (obj instanceof Task) {
                Task childTask = (Task) obj;
                childTask.setRuntimeConfigurableWrapper(child);
            }
            if (child.creator != null && configureChildren) {
                child.maybeConfigure(p);
                child.creator.store();
            } else {
                String tag = child.getElementTag().toLowerCase(Locale.US);
                if (configureChildren && ih.supportsNestedElement(tag)) {
                    child.maybeConfigure(p);
                    ProjectHelper.storeChild(p, target, child.wrappedObject, tag);
                }
            }
        }
        if (id != null) {
            p.addReference(id, this.wrappedObject);
        }
        this.proxyConfigured = true;
    }

    public void reconfigure(Project p) {
        this.proxyConfigured = false;
        maybeConfigure(p);
    }

    public void applyPreSet(RuntimeConfigurable r) {
        Map map = r.attributeMap;
        if (map != null) {
            for (String name : map.keySet()) {
                Map map2 = this.attributeMap;
                if (map2 == null || map2.get(name) == null) {
                    setAttribute(name, (String) r.attributeMap.get(name));
                }
            }
        }
        String str = r.polyType;
        if (str != null && this.polyType == null) {
            this.polyType = str;
        }
        if (r.children != null) {
            List newChildren = new ArrayList();
            newChildren.addAll(r.children);
            List list = this.children;
            if (list != null) {
                newChildren.addAll(list);
            }
            this.children = newChildren;
        }
        if (r.characters != null) {
            StringBuffer stringBuffer = this.characters;
            if (stringBuffer == null || stringBuffer.toString().trim().length() == 0) {
                this.characters = new StringBuffer(r.characters.toString());
            }
        }
    }
}
