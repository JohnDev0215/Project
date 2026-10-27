package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.XMLCatalog;
import org.apache.tools.ant.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes.dex */
public class XmlProperty extends Task {
    private static final String LOCATION = "location";
    private static final String VALUE = "value";
    private File src;
    private static final String ID = "id";
    private static final String REF_ID = "refid";
    private static final String PATH = "path";
    private static final String PATHID = "pathid";
    private static final String[] ATTRIBUTES = {ID, REF_ID, "location", "value", PATH, PATHID};
    private String prefix = "";
    private boolean keepRoot = true;
    private boolean validate = false;
    private boolean collapseAttributes = false;
    private boolean semanticAttributes = false;
    private boolean includeSemanticAttribute = false;
    private File rootDirectory = null;
    private FileUtils fileUtils = FileUtils.newFileUtils();
    private Hashtable addedAttributes = new Hashtable();
    private XMLCatalog xmlCatalog = new XMLCatalog();

    @Override // org.apache.tools.ant.Task
    public void init() {
        super.init();
        this.xmlCatalog.setProject(getProject());
    }

    protected EntityResolver getEntityResolver() {
        return this.xmlCatalog;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        ParserConfigurationException pce;
        IOException ioe;
        if (getFile() == null) {
            throw new BuildException("XmlProperty task requires a file attribute");
        }
        try {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Loading ");
                stringBuffer.append(this.src.getAbsolutePath());
                log(stringBuffer.toString(), 3);
                if (this.src.exists()) {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    try {
                        try {
                            factory.setValidating(this.validate);
                            factory.setNamespaceAware(false);
                            DocumentBuilder builder = factory.newDocumentBuilder();
                            try {
                                builder.setEntityResolver(getEntityResolver());
                                Document document = builder.parse(this.src);
                                Element topElement = document.getDocumentElement();
                                this.addedAttributes = new Hashtable();
                                if (this.keepRoot) {
                                    addNodeRecursively(topElement, this.prefix, null);
                                } else {
                                    NodeList topChildren = topElement.getChildNodes();
                                    int numChildren = topChildren.getLength();
                                    for (int i = 0; i < numChildren; i++) {
                                        addNodeRecursively(topChildren.item(i), this.prefix, null);
                                    }
                                }
                            } catch (IOException e) {
                                ioe = e;
                                throw new BuildException(ioe);
                            }
                        } catch (IOException e2) {
                            ioe = e2;
                        }
                    } catch (ParserConfigurationException e3) {
                        pce = e3;
                        throw new BuildException(pce);
                    }
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Unable to find property file: ");
                    stringBuffer2.append(this.src.getAbsolutePath());
                    log(stringBuffer2.toString(), 3);
                }
            } catch (IOException e4) {
                ioe = e4;
            } catch (ParserConfigurationException e5) {
                pce = e5;
            }
        } catch (SAXException sxe) {
            Exception x = sxe;
            if (sxe.getException() != null) {
                x = sxe.getException();
            }
            throw new BuildException(x);
        }
    }

    private void addNodeRecursively(Node node, String prefix, Object container) {
        String nodePrefix = prefix;
        if (node.getNodeType() != 3) {
            if (prefix.trim().length() > 0) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(nodePrefix);
                stringBuffer.append(".");
                nodePrefix = stringBuffer.toString();
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(nodePrefix);
            stringBuffer2.append(node.getNodeName());
            nodePrefix = stringBuffer2.toString();
        }
        Object nodeObject = processNode(node, nodePrefix, container);
        if (node.hasChildNodes()) {
            NodeList nodeChildren = node.getChildNodes();
            int numChildren = nodeChildren.getLength();
            for (int i = 0; i < numChildren; i++) {
                addNodeRecursively(nodeChildren.item(i), nodePrefix, nodeObject);
            }
        }
    }

    void addNodeRecursively(Node node, String prefix) {
        addNodeRecursively(node, prefix, null);
    }

    public Object processNode(Node node, String prefix, Object container) {
        Object addedPath = null;
        String id = null;
        if (node.hasAttributes()) {
            NamedNodeMap nodeAttributes = node.getAttributes();
            Node idNode = nodeAttributes.getNamedItem(ID);
            String id2 = (!this.semanticAttributes || idNode == null) ? null : idNode.getNodeValue();
            for (int i = 0; i < nodeAttributes.getLength(); i++) {
                Node attributeNode = nodeAttributes.item(i);
                if (!this.semanticAttributes) {
                    String attributeName = getAttributeName(attributeNode);
                    String attributeValue = getAttributeValue(attributeNode);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(prefix);
                    stringBuffer.append(attributeName);
                    addProperty(stringBuffer.toString(), attributeValue, null);
                } else {
                    String nodeName = attributeNode.getNodeName();
                    String attributeValue2 = getAttributeValue(attributeNode);
                    Path containingPath = (container == null || !(container instanceof Path)) ? null : (Path) container;
                    if (nodeName.equals(ID)) {
                        continue;
                    } else if (containingPath != null && nodeName.equals(PATH)) {
                        containingPath.setPath(attributeValue2);
                    } else if ((container instanceof Path) && nodeName.equals(REF_ID)) {
                        containingPath.setPath(attributeValue2);
                    } else if ((container instanceof Path) && nodeName.equals("location")) {
                        containingPath.setLocation(resolveFile(attributeValue2));
                    } else if (nodeName.equals(PATHID)) {
                        if (container != null) {
                            throw new BuildException("XmlProperty does not support nested paths");
                        }
                        addedPath = new Path(getProject());
                        getProject().addReference(attributeValue2, addedPath);
                    } else {
                        String attributeName2 = getAttributeName(attributeNode);
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append(prefix);
                        stringBuffer2.append(attributeName2);
                        addProperty(stringBuffer2.toString(), attributeValue2, id2);
                    }
                }
            }
            id = id2;
        }
        String nodeText = null;
        if (node.getNodeType() == 3) {
            nodeText = getAttributeValue(node);
        } else if (node.getNodeType() == 1 && node.getChildNodes().getLength() == 1 && node.getFirstChild().getNodeType() == 4) {
            nodeText = node.getFirstChild().getNodeValue();
        }
        if (nodeText != null) {
            if (this.semanticAttributes && id == null && (container instanceof String)) {
                id = (String) container;
            }
            if (nodeText.trim().length() != 0) {
                addProperty(prefix, nodeText, id);
            }
        }
        return addedPath != null ? addedPath : id;
    }

    private void addProperty(String name, String value, String id) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name);
        stringBuffer.append(":");
        stringBuffer.append(value);
        String msg = stringBuffer.toString();
        if (id != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(msg);
            stringBuffer2.append("(id=");
            stringBuffer2.append(id);
            stringBuffer2.append(")");
            msg = stringBuffer2.toString();
        }
        log(msg, 4);
        if (this.addedAttributes.containsKey(name)) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append((String) this.addedAttributes.get(name));
            stringBuffer3.append(",");
            stringBuffer3.append(value);
            value = stringBuffer3.toString();
            getProject().setProperty(name, value);
        } else {
            getProject().setNewProperty(name, value);
        }
        this.addedAttributes.put(name, value);
        if (id != null) {
            getProject().addReference(id, value);
        }
    }

    private String getAttributeName(Node attributeNode) {
        String attributeName = attributeNode.getNodeName();
        if (this.semanticAttributes) {
            if (attributeName.equals(REF_ID)) {
                return "";
            }
            if (isSemanticAttribute(attributeName) && !this.includeSemanticAttribute) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(".");
            stringBuffer.append(attributeName);
            return stringBuffer.toString();
        }
        if (this.collapseAttributes) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(".");
            stringBuffer2.append(attributeName);
            return stringBuffer2.toString();
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("(");
        stringBuffer3.append(attributeName);
        stringBuffer3.append(")");
        return stringBuffer3.toString();
    }

    private static boolean isSemanticAttribute(String attributeName) {
        int i = 0;
        while (true) {
            String[] strArr = ATTRIBUTES;
            if (i < strArr.length) {
                if (!attributeName.equals(strArr[i])) {
                    i++;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private String getAttributeValue(Node attributeNode) {
        Object ref;
        String nodeValue = attributeNode.getNodeValue().trim();
        if (this.semanticAttributes) {
            String attributeName = attributeNode.getNodeName();
            nodeValue = getProject().replaceProperties(nodeValue);
            if (attributeName.equals("location")) {
                File f = resolveFile(nodeValue);
                return f.getPath();
            }
            if (attributeName.equals(REF_ID) && (ref = getProject().getReference(nodeValue)) != null) {
                return ref.toString();
            }
        }
        return nodeValue;
    }

    public void setFile(File src) {
        this.src = src;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix.trim();
    }

    public void setKeeproot(boolean keepRoot) {
        this.keepRoot = keepRoot;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public void setCollapseAttributes(boolean collapseAttributes) {
        this.collapseAttributes = collapseAttributes;
    }

    public void setSemanticAttributes(boolean semanticAttributes) {
        this.semanticAttributes = semanticAttributes;
    }

    public void setRootDirectory(File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void setIncludeSemanticAttribute(boolean includeSemanticAttribute) {
        this.includeSemanticAttribute = includeSemanticAttribute;
    }

    public void addConfiguredXMLCatalog(XMLCatalog catalog) {
        this.xmlCatalog.addConfiguredXMLCatalog(catalog);
    }

    protected File getFile() {
        return this.src;
    }

    protected String getPrefix() {
        return this.prefix;
    }

    protected boolean getKeeproot() {
        return this.keepRoot;
    }

    protected boolean getValidate() {
        return this.validate;
    }

    protected boolean getCollapseAttributes() {
        return this.collapseAttributes;
    }

    protected boolean getSemanticAttributes() {
        return this.semanticAttributes;
    }

    protected File getRootDirectory() {
        return this.rootDirectory;
    }

    protected boolean getIncludeSementicAttribute() {
        return this.includeSemanticAttribute;
    }

    private File resolveFile(String fileName) {
        File file = this.rootDirectory;
        if (file == null) {
            return getProject().resolveFile(fileName);
        }
        return this.fileUtils.resolveFile(file, fileName);
    }
}
