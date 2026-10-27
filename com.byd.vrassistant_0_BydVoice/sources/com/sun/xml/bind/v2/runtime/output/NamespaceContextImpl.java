package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.NamespaceContext2;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class NamespaceContextImpl implements NamespaceContext2 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final NamespacePrefixMapper defaultNamespacePrefixMapper = new NamespacePrefixMapper() { // from class: com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl.1
        @Override // com.sun.xml.bind.marshaller.NamespacePrefixMapper
        public String getPreferredPrefix(String str, String str2, boolean z) {
            return str.equals(WellKnownNamespace.XML_SCHEMA_INSTANCE) ? "xsi" : str.equals(WellKnownNamespace.XML_SCHEMA) ? "xs" : str.equals(WellKnownNamespace.XML_MIME_URI) ? "xmime" : str2;
        }
    };
    public boolean collectionMode;
    private Element current;
    private final XMLSerializer owner;
    private int size;
    private final Element top;
    private String[] prefixes = new String[4];
    private String[] nsUris = new String[4];
    private NamespacePrefixMapper prefixMapper = defaultNamespacePrefixMapper;

    public NamespaceContextImpl(XMLSerializer xMLSerializer) {
        this.owner = xMLSerializer;
        Element element = new Element(this, null);
        this.top = element;
        this.current = element;
        put("http://www.w3.org/XML/1998/namespace", "xml");
    }

    public void setPrefixMapper(NamespacePrefixMapper namespacePrefixMapper) {
        if (namespacePrefixMapper == null) {
            namespacePrefixMapper = defaultNamespacePrefixMapper;
        }
        this.prefixMapper = namespacePrefixMapper;
    }

    public NamespacePrefixMapper getPrefixMapper() {
        return this.prefixMapper;
    }

    public void reset() {
        this.current = this.top;
        this.size = 1;
        this.collectionMode = false;
    }

    public int declareNsUri(String str, String str2, boolean z) {
        String preferredPrefix = this.prefixMapper.getPreferredPrefix(str, str2, z);
        if (str.length() == 0) {
            int i = this.size;
            do {
                i--;
                if (i >= 0) {
                    if (this.nsUris[i].length() == 0) {
                        return i;
                    }
                } else {
                    return put("", "");
                }
            } while (this.prefixes[i].length() != 0);
            String str3 = this.nsUris[i];
            String[] strArr = this.owner.nameList.namespaceURIs;
            if (this.current.baseIndex <= i) {
                this.nsUris[i] = "";
                int iPut = put(str3, null);
                int length = strArr.length - 1;
                while (true) {
                    if (length < 0) {
                        break;
                    }
                    if (strArr[length].equals(str3)) {
                        this.owner.knownUri2prefixIndexMap[length] = iPut;
                        break;
                    }
                    length--;
                }
                if (this.current.elementLocalName != null) {
                    Element element = this.current;
                    element.setTagName(iPut, element.elementLocalName, this.current.getOuterPeer());
                }
                return i;
            }
            int length2 = strArr.length - 1;
            while (true) {
                if (length2 < 0) {
                    break;
                }
                if (strArr[length2].equals(str3)) {
                    this.current.defaultPrefixIndex = i;
                    this.current.oldDefaultNamespaceUriIndex = length2;
                    this.owner.knownUri2prefixIndexMap[length2] = this.size;
                    break;
                }
                length2--;
            }
            if (this.current.elementLocalName != null) {
                Element element2 = this.current;
                element2.setTagName(this.size, element2.elementLocalName, this.current.getOuterPeer());
            }
            put(this.nsUris[i], null);
            return put("", "");
        }
        for (int i2 = this.size - 1; i2 >= 0; i2--) {
            String str4 = this.prefixes[i2];
            if (this.nsUris[i2].equals(str) && (!z || str4.length() > 0)) {
                return i2;
            }
            if (str4.equals(preferredPrefix)) {
                preferredPrefix = null;
            }
        }
        if (preferredPrefix == null && z) {
            preferredPrefix = makeUniquePrefix();
        }
        return put(str, preferredPrefix);
    }

    @Override // com.sun.xml.bind.v2.runtime.NamespaceContext2
    public int force(String str, String str2) {
        int i = this.size - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            if (!this.prefixes[i].equals(str2)) {
                i--;
            } else if (this.nsUris[i].equals(str)) {
                return i;
            }
        }
        return put(str, str2);
    }

    public int put(String str, String str2) {
        int i = this.size;
        String[] strArr = this.nsUris;
        if (i == strArr.length) {
            String[] strArr2 = new String[strArr.length * 2];
            String[] strArr3 = new String[this.prefixes.length * 2];
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            String[] strArr4 = this.prefixes;
            System.arraycopy(strArr4, 0, strArr3, 0, strArr4.length);
            this.nsUris = strArr2;
            this.prefixes = strArr3;
        }
        if (str2 == null) {
            str2 = this.size == 1 ? "" : makeUniquePrefix();
        }
        String[] strArr5 = this.nsUris;
        int i2 = this.size;
        strArr5[i2] = str;
        this.prefixes[i2] = str2;
        this.size = i2 + 1;
        return i2;
    }

    private String makeUniquePrefix() {
        String string = new StringBuilder(5).append("ns").append(this.size).toString();
        while (getNamespaceURI(string) != null) {
            string = string + '_';
        }
        return string;
    }

    public Element getCurrent() {
        return this.current;
    }

    public int getPrefixIndex(String str) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.nsUris[i].equals(str)) {
                return i;
            }
        }
        throw new IllegalStateException();
    }

    public String getPrefix(int i) {
        return this.prefixes[i];
    }

    public String getNamespaceURI(int i) {
        return this.nsUris[i];
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getNamespaceURI(String str) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.prefixes[i].equals(str)) {
                return this.nsUris[i];
            }
        }
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getPrefix(String str) {
        if (this.collectionMode) {
            return declareNamespace(str, null, false);
        }
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.nsUris[i].equals(str)) {
                return this.prefixes[i];
            }
        }
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public Iterator<String> getPrefixes(String str) {
        if (getPrefix(str) == null) {
            return Collections.emptySet().iterator();
        }
        return Collections.singleton(str).iterator();
    }

    @Override // com.sun.xml.bind.v2.runtime.NamespaceContext2
    public String declareNamespace(String str, String str2, boolean z) {
        return getPrefix(declareNsUri(str, str2, z));
    }

    public int count() {
        return this.size;
    }

    public final class Element {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int baseIndex;
        public final NamespaceContextImpl context;
        private int defaultPrefixIndex;
        private final int depth;
        private String elementLocalName;
        private Name elementName;
        private int elementNamePrefix;
        private Object innerPeer;
        private Element next;
        private int oldDefaultNamespaceUriIndex;
        private Object outerPeer;
        private final Element prev;

        private Element(NamespaceContextImpl namespaceContextImpl, Element element) {
            this.context = namespaceContextImpl;
            this.prev = element;
            this.depth = element == null ? 0 : element.depth + 1;
        }

        public boolean isRootElement() {
            return this.depth == 1;
        }

        public Element push() {
            if (this.next == null) {
                this.next = NamespaceContextImpl.this.new Element(this.context, this);
            }
            this.next.onPushed();
            return this.next;
        }

        public Element pop() {
            if (this.oldDefaultNamespaceUriIndex >= 0) {
                this.context.owner.knownUri2prefixIndexMap[this.oldDefaultNamespaceUriIndex] = this.defaultPrefixIndex;
            }
            this.context.size = this.baseIndex;
            this.context.current = this.prev;
            this.innerPeer = null;
            this.outerPeer = null;
            return this.prev;
        }

        private void onPushed() {
            this.defaultPrefixIndex = -1;
            this.oldDefaultNamespaceUriIndex = -1;
            this.baseIndex = this.context.size;
            this.context.current = this;
        }

        public void setTagName(int i, String str, Object obj) {
            this.elementNamePrefix = i;
            this.elementLocalName = str;
            this.elementName = null;
            this.outerPeer = obj;
        }

        public void setTagName(Name name, Object obj) {
            this.elementName = name;
            this.outerPeer = obj;
        }

        public void startElement(XmlOutput xmlOutput, Object obj) throws XMLStreamException, IOException {
            this.innerPeer = obj;
            Name name = this.elementName;
            if (name != null) {
                xmlOutput.beginStartTag(name);
            } else {
                xmlOutput.beginStartTag(this.elementNamePrefix, this.elementLocalName);
            }
        }

        public void endElement(XmlOutput xmlOutput) throws SAXException, XMLStreamException, IOException {
            Name name = this.elementName;
            if (name != null) {
                xmlOutput.endTag(name);
                this.elementName = null;
            } else {
                xmlOutput.endTag(this.elementNamePrefix, this.elementLocalName);
            }
        }

        public final int count() {
            return this.context.size - this.baseIndex;
        }

        public final String getPrefix(int i) {
            return this.context.prefixes[this.baseIndex + i];
        }

        public final String getNsUri(int i) {
            return this.context.nsUris[this.baseIndex + i];
        }

        public int getBase() {
            return this.baseIndex;
        }

        public Object getOuterPeer() {
            return this.outerPeer;
        }

        public Object getInnerPeer() {
            return this.innerPeer;
        }

        public Element getParent() {
            return this.prev;
        }
    }
}
