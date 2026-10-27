package com.sun.xml.bind.v2.runtime;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class AssociationMap<XmlNode> {
    private final Map<XmlNode, Entry<XmlNode>> byElement = new IdentityHashMap();
    private final Map<Object, Entry<XmlNode>> byPeer = new IdentityHashMap();
    private final Set<XmlNode> usedNodes = new HashSet();

    static final class Entry<XmlNode> {
        private XmlNode element;
        private Object inner;
        private Object outer;

        Entry() {
        }

        public XmlNode element() {
            return this.element;
        }

        public Object inner() {
            return this.inner;
        }

        public Object outer() {
            return this.outer;
        }
    }

    public void addInner(XmlNode xmlnode, Object obj) {
        Entry<XmlNode> entry = this.byElement.get(xmlnode);
        if (entry != null) {
            if (((Entry) entry).inner != null) {
                this.byPeer.remove(((Entry) entry).inner);
            }
            ((Entry) entry).inner = obj;
        } else {
            entry = new Entry<>();
            ((Entry) entry).element = xmlnode;
            ((Entry) entry).inner = obj;
        }
        this.byElement.put(xmlnode, entry);
        Entry<XmlNode> entryPut = this.byPeer.put(obj, entry);
        if (entryPut != null) {
            if (((Entry) entryPut).outer != null) {
                this.byPeer.remove(((Entry) entryPut).outer);
            }
            if (((Entry) entryPut).element != null) {
                this.byElement.remove(((Entry) entryPut).element);
            }
        }
    }

    public void addOuter(XmlNode xmlnode, Object obj) {
        Entry<XmlNode> entry = this.byElement.get(xmlnode);
        if (entry != null) {
            if (((Entry) entry).outer != null) {
                this.byPeer.remove(((Entry) entry).outer);
            }
            ((Entry) entry).outer = obj;
        } else {
            entry = new Entry<>();
            ((Entry) entry).element = xmlnode;
            ((Entry) entry).outer = obj;
        }
        this.byElement.put(xmlnode, entry);
        Entry<XmlNode> entryPut = this.byPeer.put(obj, entry);
        if (entryPut != null) {
            ((Entry) entryPut).outer = null;
            if (((Entry) entryPut).inner == null) {
                this.byElement.remove(((Entry) entryPut).element);
            }
        }
    }

    public void addUsed(XmlNode xmlnode) {
        this.usedNodes.add(xmlnode);
    }

    public Entry<XmlNode> byElement(Object obj) {
        return this.byElement.get(obj);
    }

    public Entry<XmlNode> byPeer(Object obj) {
        return this.byPeer.get(obj);
    }

    public Object getInnerPeer(XmlNode xmlnode) {
        Entry<XmlNode> entryByElement = byElement(xmlnode);
        if (entryByElement == null) {
            return null;
        }
        return ((Entry) entryByElement).inner;
    }

    public Object getOuterPeer(XmlNode xmlnode) {
        Entry<XmlNode> entryByElement = byElement(xmlnode);
        if (entryByElement == null) {
            return null;
        }
        return ((Entry) entryByElement).outer;
    }
}
