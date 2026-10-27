package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.CollectionUtils;

/* JADX INFO: loaded from: classes.dex */
public class Manifest {
    public static final String ATTRIBUTE_CLASSPATH = "Class-Path";
    public static final String ATTRIBUTE_FROM = "From";
    public static final String ATTRIBUTE_MANIFEST_VERSION = "Manifest-Version";
    public static final String ATTRIBUTE_NAME = "Name";
    public static final String ATTRIBUTE_SIGNATURE_VERSION = "Signature-Version";
    public static final String DEFAULT_MANIFEST_VERSION = "1.0";
    public static final String EOL = "\r\n";
    public static final int MAX_LINE_LENGTH = 72;
    public static final int MAX_SECTION_LENGTH = 70;
    static /* synthetic */ Class class$org$apache$tools$ant$taskdefs$Manifest;
    private String manifestVersion;
    private Section mainSection = new Section();
    private Hashtable sections = new Hashtable();
    private Vector sectionIndex = new Vector();

    public static class Attribute {
        private int currentIndex;
        private String name;
        private Vector values;

        public Attribute() {
            this.name = null;
            this.values = new Vector();
            this.currentIndex = 0;
        }

        public Attribute(String line) throws ManifestException {
            this.name = null;
            this.values = new Vector();
            this.currentIndex = 0;
            parse(line);
        }

        public Attribute(String name, String value) {
            this.name = null;
            this.values = new Vector();
            this.currentIndex = 0;
            this.name = name;
            setValue(value);
        }

        public int hashCode() {
            String str = this.name;
            int hashCode = str != null ? 0 + str.hashCode() : 0;
            return hashCode + this.values.hashCode();
        }

        public boolean equals(Object rhs) {
            if (rhs == null || rhs.getClass() != getClass()) {
                return false;
            }
            if (rhs == this) {
                return true;
            }
            Attribute rhsAttribute = (Attribute) rhs;
            String lhsKey = getKey();
            String rhsKey = rhsAttribute.getKey();
            if ((lhsKey == null && rhsKey != null) || ((lhsKey != null && rhsKey == null) || !lhsKey.equals(rhsKey))) {
                return false;
            }
            return CollectionUtils.equals(this.values, rhsAttribute.values);
        }

        public void parse(String line) throws ManifestException {
            int index = line.indexOf(": ");
            if (index == -1) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Manifest line \"");
                stringBuffer.append(line);
                stringBuffer.append("\" is not valid as it does not ");
                stringBuffer.append("contain a name and a value separated by ': ' ");
                throw new ManifestException(stringBuffer.toString());
            }
            this.name = line.substring(0, index);
            setValue(line.substring(index + 2));
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String getKey() {
            String str = this.name;
            if (str == null) {
                return null;
            }
            return str.toLowerCase();
        }

        public void setValue(String value) {
            if (this.currentIndex >= this.values.size()) {
                this.values.addElement(value);
                this.currentIndex = this.values.size() - 1;
            } else {
                this.values.setElementAt(value, this.currentIndex);
            }
        }

        public String getValue() {
            if (this.values.size() == 0) {
                return null;
            }
            String fullValue = "";
            Enumeration e = getValues();
            while (e.hasMoreElements()) {
                String value = (String) e.nextElement();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(fullValue);
                stringBuffer.append(value);
                stringBuffer.append(" ");
                fullValue = stringBuffer.toString();
            }
            return fullValue.trim();
        }

        public void addValue(String value) {
            this.currentIndex++;
            setValue(value);
        }

        public Enumeration getValues() {
            return this.values.elements();
        }

        public void addContinuation(String line) {
            String currentValue = (String) this.values.elementAt(this.currentIndex);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(currentValue);
            stringBuffer.append(line.substring(1));
            setValue(stringBuffer.toString());
        }

        public void write(PrintWriter writer) throws IOException {
            Enumeration e = getValues();
            while (e.hasMoreElements()) {
                writeValue(writer, (String) e.nextElement());
            }
        }

        private void writeValue(PrintWriter writer, String value) throws IOException {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.name);
            stringBuffer.append(": ");
            stringBuffer.append(value);
            String line = stringBuffer.toString();
            while (line.getBytes().length > 72) {
                int breakIndex = 70;
                String section = line.substring(0, 70);
                while (section.getBytes().length > 70 && breakIndex > 0) {
                    breakIndex--;
                    section = line.substring(0, breakIndex);
                }
                if (breakIndex == 0) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Unable to write manifest line ");
                    stringBuffer2.append(this.name);
                    stringBuffer2.append(": ");
                    stringBuffer2.append(value);
                    throw new IOException(stringBuffer2.toString());
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(section);
                stringBuffer3.append(Manifest.EOL);
                writer.print(stringBuffer3.toString());
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append(" ");
                stringBuffer4.append(line.substring(breakIndex));
                line = stringBuffer4.toString();
            }
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(line);
            stringBuffer5.append(Manifest.EOL);
            writer.print(stringBuffer5.toString());
        }
    }

    public static class Section {
        private Vector warnings = new Vector();
        private String name = null;
        private Hashtable attributes = new Hashtable();
        private Vector attributeIndex = new Vector();

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String read(BufferedReader reader) throws IOException, ManifestException {
            Attribute attribute = null;
            while (true) {
                String line = reader.readLine();
                if (line == null || line.length() == 0) {
                    return null;
                }
                if (line.charAt(0) == ' ') {
                    if (attribute == null) {
                        if (this.name != null) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(this.name);
                            stringBuffer.append(line.substring(1));
                            this.name = stringBuffer.toString();
                        } else {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("Can't start an attribute with a continuation line ");
                            stringBuffer2.append(line);
                            throw new ManifestException(stringBuffer2.toString());
                        }
                    } else {
                        attribute.addContinuation(line);
                    }
                } else {
                    Attribute attribute2 = new Attribute(line);
                    String nameReadAhead = addAttributeAndCheck(attribute2);
                    attribute = getAttribute(attribute2.getKey());
                    if (nameReadAhead != null) {
                        return nameReadAhead;
                    }
                }
            }
        }

        public void merge(Section section) throws ManifestException {
            String str;
            Attribute classpathAttribute;
            if ((this.name == null && section.getName() != null) || ((str = this.name) != null && !str.equalsIgnoreCase(section.getName()))) {
                throw new ManifestException("Unable to merge sections with different names");
            }
            Enumeration e = section.getAttributeKeys();
            Attribute classpathAttribute2 = null;
            while (e.hasMoreElements()) {
                String attributeName = (String) e.nextElement();
                Attribute attribute = section.getAttribute(attributeName);
                if (attributeName.equalsIgnoreCase(Manifest.ATTRIBUTE_CLASSPATH)) {
                    if (classpathAttribute2 != null) {
                        classpathAttribute = classpathAttribute2;
                    } else {
                        Attribute classpathAttribute3 = new Attribute();
                        classpathAttribute3.setName(Manifest.ATTRIBUTE_CLASSPATH);
                        classpathAttribute = classpathAttribute3;
                    }
                    Enumeration cpe = attribute.getValues();
                    while (cpe.hasMoreElements()) {
                        String value = (String) cpe.nextElement();
                        classpathAttribute.addValue(value);
                    }
                    classpathAttribute2 = classpathAttribute;
                } else {
                    storeAttribute(attribute);
                }
            }
            if (classpathAttribute2 != null) {
                storeAttribute(classpathAttribute2);
            }
            Enumeration warnEnum = section.warnings.elements();
            while (warnEnum.hasMoreElements()) {
                this.warnings.addElement(warnEnum.nextElement());
            }
        }

        public void write(PrintWriter writer) throws IOException {
            String str = this.name;
            if (str != null) {
                Attribute nameAttr = new Attribute(Manifest.ATTRIBUTE_NAME, str);
                nameAttr.write(writer);
            }
            Enumeration e = getAttributeKeys();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                Attribute attribute = getAttribute(key);
                attribute.write(writer);
            }
            writer.print(Manifest.EOL);
        }

        public Attribute getAttribute(String attributeName) {
            return (Attribute) this.attributes.get(attributeName.toLowerCase());
        }

        public Enumeration getAttributeKeys() {
            return this.attributeIndex.elements();
        }

        public String getAttributeValue(String attributeName) {
            Attribute attribute = getAttribute(attributeName.toLowerCase());
            if (attribute == null) {
                return null;
            }
            return attribute.getValue();
        }

        public void removeAttribute(String attributeName) {
            String key = attributeName.toLowerCase();
            this.attributes.remove(key);
            this.attributeIndex.removeElement(key);
        }

        public void addConfiguredAttribute(Attribute attribute) throws ManifestException {
            String check = addAttributeAndCheck(attribute);
            if (check != null) {
                throw new BuildException("Specify the section name using the \"name\" attribute of the <section> element rather than using a \"Name\" manifest attribute");
            }
        }

        public String addAttributeAndCheck(Attribute attribute) throws ManifestException {
            if (attribute.getName() == null || attribute.getValue() == null) {
                throw new BuildException("Attributes must have name and value");
            }
            if (attribute.getKey().equalsIgnoreCase(Manifest.ATTRIBUTE_NAME)) {
                Vector vector = this.warnings;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("\"Name\" attributes should not occur in the main section and must be the first element in all other sections: \"");
                stringBuffer.append(attribute.getName());
                stringBuffer.append(": ");
                stringBuffer.append(attribute.getValue());
                stringBuffer.append("\"");
                vector.addElement(stringBuffer.toString());
                return attribute.getValue();
            }
            if (attribute.getKey().startsWith(Manifest.ATTRIBUTE_FROM.toLowerCase())) {
                Vector vector2 = this.warnings;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Manifest attributes should not start with \"From\" in \"");
                stringBuffer2.append(attribute.getName());
                stringBuffer2.append(": ");
                stringBuffer2.append(attribute.getValue());
                stringBuffer2.append("\"");
                vector2.addElement(stringBuffer2.toString());
                return null;
            }
            String attributeKey = attribute.getKey();
            if (attributeKey.equalsIgnoreCase(Manifest.ATTRIBUTE_CLASSPATH)) {
                Attribute classpathAttribute = (Attribute) this.attributes.get(attributeKey);
                if (classpathAttribute == null) {
                    storeAttribute(attribute);
                    return null;
                }
                this.warnings.addElement("Multiple Class-Path attributes are supported but violate the Jar specification and may not be correctly processed in all environments");
                Enumeration e = attribute.getValues();
                while (e.hasMoreElements()) {
                    String value = (String) e.nextElement();
                    classpathAttribute.addValue(value);
                }
                return null;
            }
            if (this.attributes.containsKey(attributeKey)) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("The attribute \"");
                stringBuffer3.append(attribute.getName());
                stringBuffer3.append("\" may not occur more ");
                stringBuffer3.append("than once in the same section");
                throw new ManifestException(stringBuffer3.toString());
            }
            storeAttribute(attribute);
            return null;
        }

        public Object clone() {
            Section cloned = new Section();
            cloned.setName(this.name);
            Enumeration e = getAttributeKeys();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                Attribute attribute = getAttribute(key);
                cloned.storeAttribute(new Attribute(attribute.getName(), attribute.getValue()));
            }
            return cloned;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void storeAttribute(Attribute attribute) {
            if (attribute == null) {
                return;
            }
            String attributeKey = attribute.getKey();
            this.attributes.put(attributeKey, attribute);
            if (!this.attributeIndex.contains(attributeKey)) {
                this.attributeIndex.addElement(attributeKey);
            }
        }

        public Enumeration getWarnings() {
            return this.warnings.elements();
        }

        public int hashCode() {
            String str = this.name;
            int hashCode = str != null ? 0 + str.hashCode() : 0;
            return hashCode + this.attributes.hashCode();
        }

        public boolean equals(Object rhs) {
            if (rhs == null || rhs.getClass() != getClass()) {
                return false;
            }
            if (rhs == this) {
                return true;
            }
            Section rhsSection = (Section) rhs;
            return CollectionUtils.equals(this.attributes, rhsSection.attributes);
        }
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    public static Manifest getDefaultManifest() throws BuildException {
        Class clsClass$;
        try {
            if (class$org$apache$tools$ant$taskdefs$Manifest == null) {
                clsClass$ = class$("org.apache.tools.ant.taskdefs.Manifest");
                class$org$apache$tools$ant$taskdefs$Manifest = clsClass$;
            } else {
                clsClass$ = class$org$apache$tools$ant$taskdefs$Manifest;
            }
            InputStream in = clsClass$.getResourceAsStream("/org/apache/tools/ant/defaultManifest.mf");
            if (in == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not find default manifest: ");
                stringBuffer.append("/org/apache/tools/ant/defaultManifest.mf");
                throw new BuildException(stringBuffer.toString());
            }
            try {
                Manifest defaultManifest = new Manifest(new InputStreamReader(in, "UTF-8"));
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(System.getProperty("java.vm.version"));
                stringBuffer2.append(" (");
                stringBuffer2.append(System.getProperty("java.vm.vendor"));
                stringBuffer2.append(")");
                Attribute createdBy = new Attribute("Created-By", stringBuffer2.toString());
                defaultManifest.getMainSection().storeAttribute(createdBy);
                return defaultManifest;
            } catch (UnsupportedEncodingException e) {
                return new Manifest(new InputStreamReader(in));
            }
        } catch (IOException e2) {
            throw new BuildException("Unable to read default manifest", e2);
        } catch (ManifestException e3) {
            throw new BuildException("Default manifest is invalid !!", e3);
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public Manifest() {
        this.manifestVersion = "1.0";
        this.manifestVersion = null;
    }

    public Manifest(Reader r) throws IOException, ManifestException {
        this.manifestVersion = "1.0";
        BufferedReader reader = new BufferedReader(r);
        String nextSectionName = this.mainSection.read(reader);
        String readManifestVersion = this.mainSection.getAttributeValue(ATTRIBUTE_MANIFEST_VERSION);
        if (readManifestVersion != null) {
            this.manifestVersion = readManifestVersion;
            this.mainSection.removeAttribute(ATTRIBUTE_MANIFEST_VERSION);
        }
        while (true) {
            String line = reader.readLine();
            if (line != null) {
                if (line.length() != 0) {
                    Section section = new Section();
                    if (nextSectionName == null) {
                        Attribute sectionName = new Attribute(line);
                        if (!sectionName.getName().equalsIgnoreCase(ATTRIBUTE_NAME)) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("Manifest sections should start with a \"Name\" attribute and not \"");
                            stringBuffer.append(sectionName.getName());
                            stringBuffer.append("\"");
                            throw new ManifestException(stringBuffer.toString());
                        }
                        nextSectionName = sectionName.getValue();
                    } else {
                        Attribute firstAttribute = new Attribute(line);
                        section.addAttributeAndCheck(firstAttribute);
                    }
                    section.setName(nextSectionName);
                    nextSectionName = section.read(reader);
                    addConfiguredSection(section);
                }
            } else {
                return;
            }
        }
    }

    public void addConfiguredSection(Section section) throws ManifestException {
        String sectionName = section.getName();
        if (sectionName == null) {
            throw new BuildException("Sections must have a name");
        }
        this.sections.put(sectionName, section);
        if (!this.sectionIndex.contains(sectionName)) {
            this.sectionIndex.addElement(sectionName);
        }
    }

    public void addConfiguredAttribute(Attribute attribute) throws ManifestException {
        if (attribute.getKey() == null || attribute.getValue() == null) {
            throw new BuildException("Attributes must have name and value");
        }
        if (attribute.getKey().equalsIgnoreCase(ATTRIBUTE_MANIFEST_VERSION)) {
            this.manifestVersion = attribute.getValue();
        } else {
            this.mainSection.addConfiguredAttribute(attribute);
        }
    }

    public void merge(Manifest other) throws ManifestException {
        merge(other, false);
    }

    public void merge(Manifest other, boolean overwriteMain) throws ManifestException {
        if (other != null) {
            if (overwriteMain) {
                this.mainSection = (Section) other.mainSection.clone();
            } else {
                this.mainSection.merge(other.mainSection);
            }
            String str = other.manifestVersion;
            if (str != null) {
                this.manifestVersion = str;
            }
            Enumeration e = other.getSectionNames();
            while (e.hasMoreElements()) {
                String sectionName = (String) e.nextElement();
                Section ourSection = (Section) this.sections.get(sectionName);
                Section otherSection = (Section) other.sections.get(sectionName);
                if (ourSection == null) {
                    if (otherSection != null) {
                        addConfiguredSection((Section) otherSection.clone());
                    }
                } else {
                    ourSection.merge(otherSection);
                }
            }
        }
    }

    public void write(PrintWriter writer) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Manifest-Version: ");
        stringBuffer.append(this.manifestVersion);
        stringBuffer.append(EOL);
        writer.print(stringBuffer.toString());
        String signatureVersion = this.mainSection.getAttributeValue(ATTRIBUTE_SIGNATURE_VERSION);
        if (signatureVersion != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Signature-Version: ");
            stringBuffer2.append(signatureVersion);
            stringBuffer2.append(EOL);
            writer.print(stringBuffer2.toString());
            this.mainSection.removeAttribute(ATTRIBUTE_SIGNATURE_VERSION);
        }
        this.mainSection.write(writer);
        if (signatureVersion != null) {
            try {
                Attribute svAttr = new Attribute(ATTRIBUTE_SIGNATURE_VERSION, signatureVersion);
                this.mainSection.addConfiguredAttribute(svAttr);
            } catch (ManifestException e) {
            }
        }
        Enumeration e2 = this.sectionIndex.elements();
        while (e2.hasMoreElements()) {
            String sectionName = (String) e2.nextElement();
            Section section = getSection(sectionName);
            section.write(writer);
        }
    }

    public String toString() {
        StringWriter sw = new StringWriter();
        try {
            write(new PrintWriter(sw));
            return sw.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public Enumeration getWarnings() {
        Vector warnings = new Vector();
        Enumeration warnEnum = this.mainSection.getWarnings();
        while (warnEnum.hasMoreElements()) {
            warnings.addElement(warnEnum.nextElement());
        }
        Enumeration e = this.sections.elements();
        while (e.hasMoreElements()) {
            Section section = (Section) e.nextElement();
            Enumeration e2 = section.getWarnings();
            while (e2.hasMoreElements()) {
                warnings.addElement(e2.nextElement());
            }
        }
        return warnings.elements();
    }

    public int hashCode() {
        String str = this.manifestVersion;
        int hashCode = str != null ? 0 + str.hashCode() : 0;
        return hashCode + this.mainSection.hashCode() + this.sections.hashCode();
    }

    public boolean equals(Object rhs) {
        if (rhs == null || rhs.getClass() != getClass()) {
            return false;
        }
        if (rhs == this) {
            return true;
        }
        Manifest rhsManifest = (Manifest) rhs;
        String str = this.manifestVersion;
        if (str == null) {
            if (rhsManifest.manifestVersion != null) {
                return false;
            }
        } else if (!str.equals(rhsManifest.manifestVersion)) {
            return false;
        }
        if (!this.mainSection.equals(rhsManifest.mainSection)) {
            return false;
        }
        return CollectionUtils.equals(this.sections, rhsManifest.sections);
    }

    public String getManifestVersion() {
        return this.manifestVersion;
    }

    public Section getMainSection() {
        return this.mainSection;
    }

    public Section getSection(String name) {
        return (Section) this.sections.get(name);
    }

    public Enumeration getSectionNames() {
        return this.sectionIndex.elements();
    }
}
