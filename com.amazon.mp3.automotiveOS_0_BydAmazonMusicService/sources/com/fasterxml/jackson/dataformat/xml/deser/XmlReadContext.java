package com.fasterxml.jackson.dataformat.xml.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.io.CharTypes;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public final class XmlReadContext extends JsonStreamContext {
    protected XmlReadContext _child = null;
    protected int _columnNr;
    protected String _currentName;
    protected Object _currentValue;
    protected int _lineNr;
    protected Set<String> _namesToWrap;
    protected final XmlReadContext _parent;
    protected String _wrappedName;

    public XmlReadContext(XmlReadContext xmlReadContext, int i, int i2, int i3) {
        this._type = i;
        this._parent = xmlReadContext;
        this._lineNr = i2;
        this._columnNr = i3;
        this._index = -1;
    }

    protected final void reset(int i, int i2, int i3) {
        this._type = i;
        this._index = -1;
        this._lineNr = i2;
        this._columnNr = i3;
        this._currentName = null;
        this._currentValue = null;
        this._namesToWrap = null;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public Object getCurrentValue() {
        return this._currentValue;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public void setCurrentValue(Object obj) {
        this._currentValue = obj;
    }

    public static XmlReadContext createRootContext(int i, int i2) {
        return new XmlReadContext(null, 0, i, i2);
    }

    public static XmlReadContext createRootContext() {
        return new XmlReadContext(null, 0, 1, 0);
    }

    public final XmlReadContext createChildArrayContext(int i, int i2) {
        this._index++;
        XmlReadContext xmlReadContext = this._child;
        if (xmlReadContext == null) {
            XmlReadContext xmlReadContext2 = new XmlReadContext(this, 1, i, i2);
            this._child = xmlReadContext2;
            return xmlReadContext2;
        }
        xmlReadContext.reset(1, i, i2);
        return xmlReadContext;
    }

    public final XmlReadContext createChildObjectContext(int i, int i2) {
        this._index++;
        XmlReadContext xmlReadContext = this._child;
        if (xmlReadContext == null) {
            XmlReadContext xmlReadContext2 = new XmlReadContext(this, 2, i, i2);
            this._child = xmlReadContext2;
            return xmlReadContext2;
        }
        xmlReadContext.reset(2, i, i2);
        return xmlReadContext;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public final String getCurrentName() {
        return this._currentName;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public boolean hasCurrentName() {
        return this._currentName != null;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public final XmlReadContext getParent() {
        return this._parent;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public final JsonLocation getStartLocation(Object obj) {
        return new JsonLocation(obj, -1L, this._lineNr, this._columnNr);
    }

    public final void valueStarted() {
        this._index++;
    }

    public void setCurrentName(String str) {
        this._currentName = str;
    }

    public void setNamesToWrap(Set<String> set) {
        this._namesToWrap = set;
    }

    public boolean shouldWrap(String str) {
        Set<String> set = this._namesToWrap;
        return set != null && set.contains(str);
    }

    protected void convertToArray() {
        this._type = 1;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        int i = this._type;
        if (i == 0) {
            sb.append("/");
        } else if (i == 1) {
            sb.append('[');
            sb.append(getCurrentIndex());
            sb.append(']');
        } else if (i == 2) {
            sb.append('{');
            if (this._currentName != null) {
                sb.append('\"');
                CharTypes.appendQuoted(sb, this._currentName);
                sb.append('\"');
            } else {
                sb.append('?');
            }
            sb.append('}');
        }
        return sb.toString();
    }
}
