package com.sun.xml.txw2.output;

import com.android.multidex.ClassPathElement;
import com.sun.xml.txw2.TxwException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.compress.utils.CharsetNames;

/* JADX INFO: loaded from: classes3.dex */
public class StreamSerializer implements XmlSerializer {
    private final SaxSerializer serializer;
    private final XMLWriter writer;

    public StreamSerializer(OutputStream outputStream) {
        this(createWriter(outputStream));
    }

    public StreamSerializer(OutputStream outputStream, String str) throws UnsupportedEncodingException {
        this(createWriter(outputStream, str));
    }

    public StreamSerializer(Writer writer) {
        this(new StreamResult(writer));
    }

    public StreamSerializer(StreamResult streamResult) {
        final OutputStream[] outputStreamArr = new OutputStream[1];
        if (streamResult.getWriter() != null) {
            this.writer = createWriter(streamResult.getWriter());
        } else if (streamResult.getOutputStream() != null) {
            this.writer = createWriter(streamResult.getOutputStream());
        } else if (streamResult.getSystemId() != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(convertURL(streamResult.getSystemId()));
                outputStreamArr[0] = fileOutputStream;
                this.writer = createWriter(fileOutputStream);
            } catch (IOException e) {
                throw new TxwException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }
        XMLWriter xMLWriter = this.writer;
        this.serializer = new SaxSerializer(xMLWriter, xMLWriter, false) { // from class: com.sun.xml.txw2.output.StreamSerializer.1
            @Override // com.sun.xml.txw2.output.SaxSerializer, com.sun.xml.txw2.output.XmlSerializer
            public void endDocument() {
                super.endDocument();
                OutputStream outputStream = outputStreamArr[0];
                if (outputStream != null) {
                    try {
                        outputStream.close();
                        outputStreamArr[0] = null;
                    } catch (IOException e2) {
                        throw new TxwException(e2);
                    }
                }
            }
        };
    }

    private StreamSerializer(XMLWriter xMLWriter) {
        this.writer = xMLWriter;
        this.serializer = new SaxSerializer(xMLWriter, xMLWriter, false);
    }

    private String convertURL(String str) {
        String strReplaceAll = str.replace('\\', ClassPathElement.SEPARATOR_CHAR).replaceAll("//", "/").replaceAll("//", "/");
        if (!strReplaceAll.startsWith("file:/")) {
            return strReplaceAll;
        }
        if (strReplaceAll.substring(6).indexOf(":") > 0) {
            return strReplaceAll.substring(6);
        }
        return strReplaceAll.substring(5);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void startDocument() {
        this.serializer.startDocument();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void beginStartTag(String str, String str2, String str3) {
        this.serializer.beginStartTag(str, str2, str3);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeAttribute(String str, String str2, String str3, StringBuilder sb) {
        this.serializer.writeAttribute(str, str2, str3, sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeXmlns(String str, String str2) {
        this.serializer.writeXmlns(str, str2);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endStartTag(String str, String str2, String str3) {
        this.serializer.endStartTag(str, str2, str3);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endTag() {
        this.serializer.endTag();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void text(StringBuilder sb) {
        this.serializer.text(sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void cdata(StringBuilder sb) {
        this.serializer.cdata(sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void comment(StringBuilder sb) {
        this.serializer.comment(sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endDocument() {
        this.serializer.endDocument();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void flush() {
        this.serializer.flush();
        try {
            this.writer.flush();
        } catch (IOException e) {
            throw new TxwException(e);
        }
    }

    private static XMLWriter createWriter(Writer writer) {
        DataWriter dataWriter = new DataWriter(new BufferedWriter(writer));
        dataWriter.setIndentStep("  ");
        return dataWriter;
    }

    private static XMLWriter createWriter(OutputStream outputStream, String str) throws UnsupportedEncodingException {
        XMLWriter xMLWriterCreateWriter = createWriter(new OutputStreamWriter(outputStream, str));
        xMLWriterCreateWriter.setEncoding(str);
        return xMLWriterCreateWriter;
    }

    private static XMLWriter createWriter(OutputStream outputStream) {
        try {
            return createWriter(outputStream, CharsetNames.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }
}
