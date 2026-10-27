package org.apache.tools.ant.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: classes.dex */
public class DOMElementWriter {
    private static String lSep = System.getProperty("line.separator");
    protected String[] knownEntities = {"gt", "amp", "lt", "apos", "quot"};

    public void write(Element root, OutputStream out) throws IOException {
        Writer wri = new OutputStreamWriter(out, "UTF8");
        wri.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        write(root, wri, 0, "  ");
        wri.flush();
    }

    public void write(Element element, Writer out, int indent, String indentWith) throws IOException {
        openElement(element, out, indent, indentWith);
        boolean hasChildren = false;
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            short nodeType = child.getNodeType();
            if (nodeType == 1) {
                if (!hasChildren) {
                    out.write(lSep);
                    hasChildren = true;
                }
                write((Element) child, out, indent + 1, indentWith);
            } else if (nodeType == 3) {
                out.write(encode(child.getNodeValue()));
            } else if (nodeType == 4) {
                out.write("<![CDATA[");
                out.write(encodedata(((Text) child).getData()));
                out.write("]]>");
            } else if (nodeType == 5) {
                out.write(38);
                out.write(child.getNodeName());
                out.write(59);
            } else if (nodeType == 7) {
                out.write("<?");
                out.write(child.getNodeName());
                String data = child.getNodeValue();
                if (data != null && data.length() > 0) {
                    out.write(32);
                    out.write(data);
                }
                out.write("?>");
            } else if (nodeType == 8) {
                out.write("<!--");
                out.write(encode(child.getNodeValue()));
                out.write("-->");
            }
        }
        closeElement(element, out, indent, indentWith, hasChildren);
    }

    public void openElement(Element element, Writer out, int indent, String indentWith) throws IOException {
        for (int i = 0; i < indent; i++) {
            out.write(indentWith);
        }
        out.write("<");
        out.write(element.getTagName());
        NamedNodeMap attrs = element.getAttributes();
        for (int i2 = 0; i2 < attrs.getLength(); i2++) {
            Attr attr = (Attr) attrs.item(i2);
            out.write(" ");
            out.write(attr.getName());
            out.write("=\"");
            out.write(encode(attr.getValue()));
            out.write("\"");
        }
        out.write(">");
    }

    public void closeElement(Element element, Writer out, int indent, String indentWith, boolean hasChildren) throws IOException {
        if (hasChildren) {
            for (int i = 0; i < indent; i++) {
                out.write(indentWith);
            }
        }
        out.write("</");
        out.write(element.getTagName());
        out.write(">");
        out.write(lSep);
        out.flush();
    }

    public String encode(String value) {
        StringBuffer sb = new StringBuffer();
        int len = value.length();
        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            if (c == '\"') {
                sb.append("&quot;");
            } else if (c == '<') {
                sb.append("&lt;");
            } else if (c == '>') {
                sb.append("&gt;");
            } else if (c == '&') {
                int nextSemi = value.indexOf(";", i);
                if (nextSemi < 0 || !isReference(value.substring(i, nextSemi + 1))) {
                    sb.append("&amp;");
                } else {
                    sb.append('&');
                }
            } else if (c == '\'') {
                sb.append("&apos;");
            } else if (isLegalCharacter(c)) {
                sb.append(c);
            }
        }
        return sb.substring(0);
    }

    public String encodedata(String value) {
        StringBuffer sb = new StringBuffer();
        int len = value.length();
        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            if (isLegalCharacter(c)) {
                sb.append(c);
            }
        }
        String result = sb.substring(0);
        int cdEnd = result.indexOf("]]>");
        while (cdEnd != -1) {
            sb.setLength(cdEnd);
            sb.append("&#x5d;&#x5d;&gt;");
            sb.append(result.substring(cdEnd + 3));
            result = sb.substring(0);
            cdEnd = result.indexOf("]]>");
        }
        return result;
    }

    public boolean isReference(String ent) {
        if (ent.charAt(0) != '&' || !ent.endsWith(";")) {
            return false;
        }
        if (ent.charAt(1) != '#') {
            String name = ent.substring(1, ent.length() - 1);
            int i = 0;
            while (true) {
                String[] strArr = this.knownEntities;
                if (i >= strArr.length) {
                    return false;
                }
                if (name.equals(strArr[i])) {
                    return true;
                }
                i++;
            }
        } else {
            if (ent.charAt(2) != 'x') {
                try {
                    Integer.parseInt(ent.substring(2, ent.length() - 1));
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            try {
                Integer.parseInt(ent.substring(3, ent.length() - 1), 16);
                return true;
            } catch (NumberFormatException e2) {
                return false;
            }
        }
    }

    public boolean isLegalCharacter(char c) {
        if (c == '\t' || c == '\n' || c == '\r') {
            return true;
        }
        if (c < ' ') {
            return false;
        }
        if (c <= 55295) {
            return true;
        }
        return c >= 57344 && c <= 65533;
    }
}
