package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
class StartTag extends Content implements NamespaceResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    final Document document;
    private Attribute firstAtt;
    private NamespaceDecl firstNs;
    private Attribute lastAtt;
    private NamespaceDecl lastNs;
    private final String localName;
    private ContainerElement owner;
    private String uri;

    @Override // com.sun.xml.txw2.Content
    boolean concludesPendingStartTag() {
        return true;
    }

    public StartTag(ContainerElement containerElement, String str, String str2) {
        this(containerElement.document, str, str2);
        this.owner = containerElement;
    }

    public StartTag(Document document, String str, String str2) {
        this.uri = str;
        this.localName = str2;
        this.document = document;
        addNamespaceDecl(str, null, false);
    }

    public void addAttribute(String str, String str2, Object obj) {
        checkWritable();
        Attribute attribute = this.firstAtt;
        while (attribute != null && !attribute.hasName(str, str2)) {
            attribute = attribute.next;
        }
        if (attribute == null) {
            attribute = new Attribute(str, str2);
            Attribute attribute2 = this.lastAtt;
            if (attribute2 == null) {
                this.lastAtt = attribute;
                this.firstAtt = attribute;
            } else {
                attribute2.next = attribute;
                this.lastAtt = attribute;
            }
            if (str.length() > 0) {
                addNamespaceDecl(str, null, true);
            }
        }
        this.document.writeValue(obj, this, attribute.value);
    }

    public NamespaceDecl addNamespaceDecl(String str, String str2, boolean z) {
        checkWritable();
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (str.length() == 0) {
            if (z) {
                throw new IllegalArgumentException("The empty namespace cannot have a non-empty prefix");
            }
            if (str2 != null && str2.length() > 0) {
                throw new IllegalArgumentException("The empty namespace can be only bound to the empty prefix");
            }
            str2 = "";
        }
        for (NamespaceDecl namespaceDecl = this.firstNs; namespaceDecl != null; namespaceDecl = namespaceDecl.next) {
            if (str.equals(namespaceDecl.uri)) {
                if (str2 == null) {
                    namespaceDecl.requirePrefix |= z;
                    return namespaceDecl;
                }
                if (namespaceDecl.prefix == null) {
                    namespaceDecl.prefix = str2;
                    namespaceDecl.requirePrefix |= z;
                    return namespaceDecl;
                }
                if (str2.equals(namespaceDecl.prefix)) {
                    namespaceDecl.requirePrefix |= z;
                    return namespaceDecl;
                }
            }
            if (str2 != null && namespaceDecl.prefix != null && namespaceDecl.prefix.equals(str2)) {
                throw new IllegalArgumentException("Prefix '" + str2 + "' is already bound to '" + namespaceDecl.uri + '\'');
            }
        }
        NamespaceDecl namespaceDecl2 = new NamespaceDecl(this.document.assignNewId(), str, str2, z);
        NamespaceDecl namespaceDecl3 = this.lastNs;
        if (namespaceDecl3 == null) {
            this.lastNs = namespaceDecl2;
            this.firstNs = namespaceDecl2;
        } else {
            namespaceDecl3.next = namespaceDecl2;
            this.lastNs = namespaceDecl2;
        }
        return namespaceDecl2;
    }

    private void checkWritable() {
        if (isWritten()) {
            throw new IllegalStateException("The start tag of " + this.localName + " has already been written. If you need out of order writing, see the TypedXmlWriter.block method");
        }
    }

    boolean isWritten() {
        return this.uri == null;
    }

    @Override // com.sun.xml.txw2.Content
    boolean isReadyToCommit() {
        ContainerElement containerElement = this.owner;
        if (containerElement != null && containerElement.isBlocked()) {
            return false;
        }
        for (Content next = getNext(); next != null; next = next.getNext()) {
            if (next.concludesPendingStartTag()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.xml.txw2.Content
    public void written() {
        this.lastAtt = null;
        this.firstAtt = null;
        this.uri = null;
        ContainerElement containerElement = this.owner;
        if (containerElement != null) {
            containerElement.startTag = null;
        }
    }

    @Override // com.sun.xml.txw2.Content
    void accept(ContentVisitor contentVisitor) {
        contentVisitor.onStartTag(this.uri, this.localName, this.firstAtt, this.firstNs);
    }

    @Override // com.sun.xml.txw2.NamespaceResolver
    public String getPrefix(String str) {
        NamespaceDecl namespaceDeclAddNamespaceDecl = addNamespaceDecl(str, null, false);
        if (namespaceDeclAddNamespaceDecl.prefix != null) {
            return namespaceDeclAddNamespaceDecl.prefix;
        }
        return namespaceDeclAddNamespaceDecl.dummyPrefix;
    }
}
