package com.sun.xml.txw2.output;

import java.io.PrintStream;
import kotlin.text.Typography;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class DumpSerializer implements XmlSerializer {
    private final PrintStream out;

    public DumpSerializer(PrintStream printStream) {
        this.out = printStream;
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void beginStartTag(String str, String str2, String str3) {
        this.out.println("<" + str3 + ':' + str2);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeAttribute(String str, String str2, String str3, StringBuilder sb) {
        this.out.println("@" + str3 + ':' + str2 + SignatureVisitor.INSTANCEOF + ((Object) sb));
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeXmlns(String str, String str2) {
        this.out.println("xmlns:" + str + SignatureVisitor.INSTANCEOF + str2);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endStartTag(String str, String str2, String str3) {
        this.out.println(Typography.greater);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endTag() {
        this.out.println("</  >");
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void text(StringBuilder sb) {
        this.out.println(sb);
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void cdata(StringBuilder sb) {
        this.out.println("<![CDATA[");
        this.out.println(sb);
        this.out.println("]]>");
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void comment(StringBuilder sb) {
        this.out.println("<!--");
        this.out.println(sb);
        this.out.println("-->");
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void startDocument() {
        this.out.println("<?xml?>");
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endDocument() {
        this.out.println("done");
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void flush() {
        this.out.println("flush");
    }
}
