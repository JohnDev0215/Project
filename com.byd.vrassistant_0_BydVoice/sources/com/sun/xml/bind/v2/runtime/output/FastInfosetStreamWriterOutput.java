package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.marshaller.NoEscapeHandler;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl;
import com.sun.xml.bind.v2.runtime.unmarshaller.Base64Data;
import com.sun.xml.fastinfoset.stax.StAXDocumentSerializer;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.stream.XMLStreamException;
import org.jvnet.fastinfoset.VocabularyApplicationData;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class FastInfosetStreamWriterOutput extends XMLStreamWriterOutput {
    private final StAXDocumentSerializer fiout;
    private final Encoded[] localNames;
    private final TablesPerJAXBContext tables;

    static final class TablesPerJAXBContext {
        final int[] attributeIndexes;
        final int[] elementIndexPrefixes;
        final int[] elementIndexes;
        int indexOffset;
        final int[] localNameIndexes;
        int maxIndex;
        boolean requiresClear;

        TablesPerJAXBContext(JAXBContextImpl jAXBContextImpl, int i) {
            int[] iArr = new int[jAXBContextImpl.getNumberOfElementNames()];
            this.elementIndexes = iArr;
            this.elementIndexPrefixes = new int[jAXBContextImpl.getNumberOfElementNames()];
            int[] iArr2 = new int[jAXBContextImpl.getNumberOfAttributeNames()];
            this.attributeIndexes = iArr2;
            this.localNameIndexes = new int[jAXBContextImpl.getNumberOfLocalNames()];
            this.indexOffset = 1;
            this.maxIndex = i + iArr.length + iArr2.length;
        }

        public void requireClearTables() {
            this.requiresClear = true;
        }

        public void clearOrResetTables(int i) {
            if (this.requiresClear) {
                this.requiresClear = false;
                int i2 = this.indexOffset + this.maxIndex;
                this.indexOffset = i2;
                int length = i + this.elementIndexes.length + this.attributeIndexes.length;
                this.maxIndex = length;
                if (i2 + length < 0) {
                    clearAll();
                    return;
                }
                return;
            }
            int length2 = i + this.elementIndexes.length + this.attributeIndexes.length;
            this.maxIndex = length2;
            if (this.indexOffset + length2 < 0) {
                resetAll();
            }
        }

        private void clearAll() {
            clear(this.elementIndexes);
            clear(this.attributeIndexes);
            clear(this.localNameIndexes);
            this.indexOffset = 1;
        }

        private void clear(int[] iArr) {
            for (int i = 0; i < iArr.length; i++) {
                iArr[i] = 0;
            }
        }

        public void incrementMaxIndexValue() {
            int i = this.maxIndex + 1;
            this.maxIndex = i;
            if (this.indexOffset + i < 0) {
                resetAll();
            }
        }

        private void resetAll() {
            clear(this.elementIndexes);
            clear(this.attributeIndexes);
            clear(this.localNameIndexes);
            this.indexOffset = 1;
        }

        private void reset(int[] iArr) {
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                int i3 = this.indexOffset;
                if (i2 > i3) {
                    iArr[i] = (i2 - i3) + 1;
                } else {
                    iArr[i] = 0;
                }
            }
        }
    }

    static final class AppData implements VocabularyApplicationData {
        final Collection<TablesPerJAXBContext> collectionOfContexts;
        final Map<JAXBContext, TablesPerJAXBContext> contexts;

        AppData() {
            WeakHashMap weakHashMap = new WeakHashMap();
            this.contexts = weakHashMap;
            this.collectionOfContexts = weakHashMap.values();
        }

        public void clear() {
            Iterator<TablesPerJAXBContext> it = this.collectionOfContexts.iterator();
            while (it.hasNext()) {
                it.next().requireClearTables();
            }
        }
    }

    public FastInfosetStreamWriterOutput(StAXDocumentSerializer stAXDocumentSerializer, JAXBContextImpl jAXBContextImpl) {
        AppData appData;
        super(stAXDocumentSerializer, NoEscapeHandler.theInstance);
        this.fiout = stAXDocumentSerializer;
        this.localNames = jAXBContextImpl.getUTF8NameTable();
        VocabularyApplicationData vocabularyApplicationData = stAXDocumentSerializer.getVocabularyApplicationData();
        if (vocabularyApplicationData == null || !(vocabularyApplicationData instanceof AppData)) {
            appData = new AppData();
            stAXDocumentSerializer.setVocabularyApplicationData(appData);
        } else {
            appData = (AppData) vocabularyApplicationData;
        }
        TablesPerJAXBContext tablesPerJAXBContext = appData.contexts.get(jAXBContextImpl);
        if (tablesPerJAXBContext != null) {
            this.tables = tablesPerJAXBContext;
            tablesPerJAXBContext.clearOrResetTables(stAXDocumentSerializer.getLocalNameIndex());
        } else {
            TablesPerJAXBContext tablesPerJAXBContext2 = new TablesPerJAXBContext(jAXBContextImpl, stAXDocumentSerializer.getLocalNameIndex());
            this.tables = tablesPerJAXBContext2;
            appData.contexts.put(jAXBContextImpl, tablesPerJAXBContext2);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException {
        super.startDocument(xMLSerializer, z, iArr, namespaceContextImpl);
        if (z) {
            this.fiout.initiateLowLevelWriting();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
        super.endDocument(z);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(Name name) throws IOException {
        this.fiout.writeLowLevelTerminationAndMark();
        if (this.nsContext.getCurrent().count() == 0) {
            int i = this.tables.elementIndexes[name.qNameIndex] - this.tables.indexOffset;
            int i2 = this.nsUriIndex2prefixIndex[name.nsUriIndex];
            if (i >= 0 && this.tables.elementIndexPrefixes[name.qNameIndex] == i2) {
                this.fiout.writeLowLevelStartElementIndexed(0, i);
                return;
            }
            this.tables.elementIndexes[name.qNameIndex] = this.fiout.getNextElementIndex() + this.tables.indexOffset;
            this.tables.elementIndexPrefixes[name.qNameIndex] = i2;
            writeLiteral(60, name, this.nsContext.getPrefix(i2), this.nsContext.getNamespaceURI(i2));
            return;
        }
        beginStartTagWithNamespaces(name);
    }

    public void beginStartTagWithNamespaces(Name name) throws IOException {
        NamespaceContextImpl.Element current = this.nsContext.getCurrent();
        this.fiout.writeLowLevelStartNamespaces();
        for (int iCount = current.count() - 1; iCount >= 0; iCount--) {
            String nsUri = current.getNsUri(iCount);
            if (nsUri.length() != 0 || current.getBase() != 1) {
                this.fiout.writeLowLevelNamespace(current.getPrefix(iCount), nsUri);
            }
        }
        this.fiout.writeLowLevelEndNamespaces();
        int i = this.tables.elementIndexes[name.qNameIndex] - this.tables.indexOffset;
        int i2 = this.nsUriIndex2prefixIndex[name.nsUriIndex];
        if (i >= 0 && this.tables.elementIndexPrefixes[name.qNameIndex] == i2) {
            this.fiout.writeLowLevelStartElementIndexed(0, i);
            return;
        }
        this.tables.elementIndexes[name.qNameIndex] = this.fiout.getNextElementIndex() + this.tables.indexOffset;
        this.tables.elementIndexPrefixes[name.qNameIndex] = i2;
        writeLiteral(60, name, this.nsContext.getPrefix(i2), this.nsContext.getNamespaceURI(i2));
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(Name name, String str) throws IOException {
        this.fiout.writeLowLevelStartAttributes();
        int i = this.tables.attributeIndexes[name.qNameIndex] - this.tables.indexOffset;
        if (i >= 0) {
            this.fiout.writeLowLevelAttributeIndexed(i);
        } else {
            this.tables.attributeIndexes[name.qNameIndex] = this.fiout.getNextAttributeIndex() + this.tables.indexOffset;
            short s = name.nsUriIndex;
            if (s == -1) {
                writeLiteral(120, name, "", "");
            } else {
                int i2 = this.nsUriIndex2prefixIndex[s];
                writeLiteral(120, name, this.nsContext.getPrefix(i2), this.nsContext.getNamespaceURI(i2));
            }
        }
        this.fiout.writeLowLevelAttributeValue(str);
    }

    private void writeLiteral(int i, Name name, String str, String str2) throws IOException {
        int i2 = this.tables.localNameIndexes[name.localNameIndex] - this.tables.indexOffset;
        if (i2 < 0) {
            this.tables.localNameIndexes[name.localNameIndex] = this.fiout.getNextLocalNameIndex() + this.tables.indexOffset;
            this.fiout.writeLowLevelStartNameLiteral(i, str, this.localNames[name.localNameIndex].buf, str2);
        } else {
            this.fiout.writeLowLevelStartNameLiteral(i, str, i2, str2);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endStartTag() throws IOException {
        this.fiout.writeLowLevelEndStartElement();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(Name name) throws IOException {
        this.fiout.writeLowLevelEndElement();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(int i, String str) throws IOException {
        this.fiout.writeLowLevelEndElement();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(Pcdata pcdata, boolean z) throws IOException {
        if (z) {
            this.fiout.writeLowLevelText(" ");
        }
        if (!(pcdata instanceof Base64Data)) {
            int length = pcdata.length();
            if (length < this.buf.length) {
                pcdata.writeTo(this.buf, 0);
                this.fiout.writeLowLevelText(this.buf, length);
                return;
            } else {
                this.fiout.writeLowLevelText(pcdata.toString());
                return;
            }
        }
        Base64Data base64Data = (Base64Data) pcdata;
        this.fiout.writeLowLevelOctets(base64Data.get(), base64Data.getDataLen());
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(String str, boolean z) throws IOException {
        if (z) {
            this.fiout.writeLowLevelText(" ");
        }
        this.fiout.writeLowLevelText(str);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(int i, String str) throws IOException {
        this.fiout.writeLowLevelTerminationAndMark();
        if (this.nsContext.getCurrent().count() > 0) {
            NamespaceContextImpl.Element current = this.nsContext.getCurrent();
            this.fiout.writeLowLevelStartNamespaces();
            for (int iCount = current.count() - 1; iCount >= 0; iCount--) {
                String nsUri = current.getNsUri(iCount);
                if (nsUri.length() != 0 || current.getBase() != 1) {
                    this.fiout.writeLowLevelNamespace(current.getPrefix(iCount), nsUri);
                }
            }
            this.fiout.writeLowLevelEndNamespaces();
        }
        if (this.fiout.writeLowLevelStartElement(0, this.nsContext.getPrefix(i), str, this.nsContext.getNamespaceURI(i))) {
            return;
        }
        this.tables.incrementMaxIndexValue();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(int i, String str, String str2) throws IOException {
        boolean zWriteLowLevelAttribute;
        this.fiout.writeLowLevelStartAttributes();
        if (i == -1) {
            zWriteLowLevelAttribute = this.fiout.writeLowLevelAttribute("", "", str);
        } else {
            zWriteLowLevelAttribute = this.fiout.writeLowLevelAttribute(this.nsContext.getPrefix(i), this.nsContext.getNamespaceURI(i), str);
        }
        if (!zWriteLowLevelAttribute) {
            this.tables.incrementMaxIndexValue();
        }
        this.fiout.writeLowLevelAttributeValue(str2);
    }
}
