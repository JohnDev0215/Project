package com.sun.xml.txw2;

import com.sun.xml.txw2.output.XmlSerializer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class Document {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final char MAGIC = 0;
    private NamespaceDecl activeNamespaces;
    private final XmlSerializer out;
    private boolean started = false;
    private Content current = null;
    private final Map<Class, DatatypeWriter> datatypeWriters = new HashMap();
    private int iota = 1;
    private final NamespaceSupport inscopeNamespace = new NamespaceSupport();
    private final ContentVisitor visitor = new ContentVisitor() { // from class: com.sun.xml.txw2.Document.1
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // com.sun.xml.txw2.ContentVisitor
        public void onStartDocument() {
            throw new IllegalStateException();
        }

        @Override // com.sun.xml.txw2.ContentVisitor
        public void onEndDocument() {
            Document.this.out.endDocument();
        }

        @Override // com.sun.xml.txw2.ContentVisitor
        public void onEndTag() {
            Document.this.out.endTag();
            Document.this.inscopeNamespace.popContext();
            Document.this.activeNamespaces = null;
        }

        @Override // com.sun.xml.txw2.ContentVisitor
        public void onPcdata(StringBuilder sb) {
            if (Document.this.activeNamespaces != null) {
                sb = Document.this.fixPrefix(sb);
            }
            Document.this.out.text(sb);
        }

        @Override // com.sun.xml.txw2.ContentVisitor
        public void onCdata(StringBuilder sb) {
            if (Document.this.activeNamespaces != null) {
                sb = Document.this.fixPrefix(sb);
            }
            Document.this.out.cdata(sb);
        }

        @Override // com.sun.xml.txw2.ContentVisitor
        public void onComment(StringBuilder sb) {
            if (Document.this.activeNamespaces != null) {
                sb = Document.this.fixPrefix(sb);
            }
            Document.this.out.comment(sb);
        }

        @Override // com.sun.xml.txw2.ContentVisitor
        public void onStartTag(String str, String str2, Attribute attribute, NamespaceDecl namespaceDecl) {
            NamespaceSupport namespaceSupport;
            String strNewPrefix;
            String uri;
            Document.this.activeNamespaces = namespaceDecl;
            if (!Document.this.started) {
                Document.this.started = true;
                Document.this.out.startDocument();
            }
            Document.this.inscopeNamespace.pushContext();
            for (NamespaceDecl namespaceDecl2 = namespaceDecl; namespaceDecl2 != null; namespaceDecl2 = namespaceDecl2.next) {
                namespaceDecl2.declared = false;
                if (namespaceDecl2.prefix != null && ((uri = Document.this.inscopeNamespace.getURI(namespaceDecl2.prefix)) == null || !uri.equals(namespaceDecl2.uri))) {
                    Document.this.inscopeNamespace.declarePrefix(namespaceDecl2.prefix, namespaceDecl2.uri);
                    namespaceDecl2.declared = true;
                }
            }
            for (NamespaceDecl namespaceDecl3 = namespaceDecl; namespaceDecl3 != null; namespaceDecl3 = namespaceDecl3.next) {
                if (namespaceDecl3.prefix == null) {
                    if (!Document.this.inscopeNamespace.getURI("").equals(namespaceDecl3.uri)) {
                        String prefix = Document.this.inscopeNamespace.getPrefix(namespaceDecl3.uri);
                        if (prefix == null) {
                            do {
                                namespaceSupport = Document.this.inscopeNamespace;
                                strNewPrefix = Document.this.newPrefix();
                            } while (namespaceSupport.getURI(strNewPrefix) != null);
                            namespaceDecl3.declared = true;
                            Document.this.inscopeNamespace.declarePrefix(strNewPrefix, namespaceDecl3.uri);
                            prefix = strNewPrefix;
                        }
                        namespaceDecl3.prefix = prefix;
                    } else {
                        namespaceDecl3.prefix = "";
                    }
                }
            }
            Document.this.out.beginStartTag(str, str2, namespaceDecl.prefix);
            for (NamespaceDecl namespaceDecl4 = namespaceDecl; namespaceDecl4 != null; namespaceDecl4 = namespaceDecl4.next) {
                if (namespaceDecl4.declared) {
                    Document.this.out.writeXmlns(namespaceDecl4.prefix, namespaceDecl4.uri);
                }
            }
            while (attribute != null) {
                Document.this.out.writeAttribute(attribute.nsUri, attribute.localName, attribute.nsUri.length() == 0 ? "" : Document.this.inscopeNamespace.getPrefix(attribute.nsUri), Document.this.fixPrefix(attribute.value));
                attribute = attribute.next;
            }
            Document.this.out.endStartTag(str, str2, namespaceDecl.prefix);
        }
    };
    private final StringBuilder prefixSeed = new StringBuilder("ns");
    private int prefixIota = 0;

    Document(XmlSerializer xmlSerializer) {
        this.out = xmlSerializer;
        for (DatatypeWriter<?> datatypeWriter : DatatypeWriter.BUILTIN) {
            this.datatypeWriters.put(datatypeWriter.getType(), datatypeWriter);
        }
    }

    void flush() {
        this.out.flush();
    }

    void setFirstContent(Content content) {
        StartDocument startDocument = new StartDocument();
        this.current = startDocument;
        startDocument.setNext(this, content);
    }

    public void addDatatypeWriter(DatatypeWriter<?> datatypeWriter) {
        this.datatypeWriters.put(datatypeWriter.getType(), datatypeWriter);
    }

    void run() {
        while (true) {
            Content next = this.current.getNext();
            if (next == null || !next.isReadyToCommit()) {
                return;
            }
            next.accept(this.visitor);
            next.written();
            this.current = next;
        }
    }

    void writeValue(Object obj, NamespaceResolver namespaceResolver, StringBuilder sb) {
        if (obj == null) {
            throw new IllegalArgumentException("argument contains null");
        }
        if (obj instanceof Object[]) {
            for (Object obj2 : (Object[]) obj) {
                writeValue(obj2, namespaceResolver, sb);
            }
            return;
        }
        if (obj instanceof Iterable) {
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                writeValue(it.next(), namespaceResolver, sb);
            }
            return;
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            DatatypeWriter datatypeWriter = this.datatypeWriters.get(superclass);
            if (datatypeWriter != null) {
                datatypeWriter.print(obj, namespaceResolver, sb);
                return;
            }
        }
        sb.append(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String newPrefix() {
        this.prefixSeed.setLength(2);
        StringBuilder sb = this.prefixSeed;
        int i = this.prefixIota + 1;
        this.prefixIota = i;
        sb.append(i);
        return this.prefixSeed.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StringBuilder fixPrefix(StringBuilder sb) {
        int i;
        int length = sb.length();
        int i2 = 0;
        while (i2 < length && sb.charAt(i2) != 0) {
            i2++;
        }
        if (i2 == length) {
            return sb;
        }
        while (i2 < length) {
            char cCharAt = sb.charAt(i2 + 1);
            NamespaceDecl namespaceDecl = this.activeNamespaces;
            while (namespaceDecl != null && namespaceDecl.uniqueId != cCharAt) {
                namespaceDecl = namespaceDecl.next;
            }
            if (namespaceDecl == null) {
                throw new IllegalStateException("Unexpected use of prefixes " + ((Object) sb));
            }
            String str = namespaceDecl.prefix;
            if (str.length() == 0) {
                int i3 = i2 + 2;
                if (sb.length() <= i3 || sb.charAt(i3) != ':') {
                    throw new IllegalStateException("Unexpected use of prefixes " + ((Object) sb));
                }
                i = 3;
            } else {
                i = 2;
            }
            sb.replace(i2, i2 + i, str);
            length += str.length() - i;
            while (i2 < length && sb.charAt(i2) != 0) {
                i2++;
            }
        }
        return sb;
    }

    char assignNewId() {
        int i = this.iota;
        this.iota = i + 1;
        return (char) i;
    }
}
