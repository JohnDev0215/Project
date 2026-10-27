package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* JADX INFO: loaded from: classes.dex */
public class AntStructure extends Task {
    private static final String BOOLEAN = "%boolean;";
    private static final String TASKS = "%tasks;";
    private static final String TYPES = "%types;";
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$org$apache$tools$ant$TaskContainer;
    static /* synthetic */ Class class$org$apache$tools$ant$types$EnumeratedAttribute;
    static /* synthetic */ Class class$org$apache$tools$ant$types$Reference;
    private File output;
    private final String lSep = System.getProperty("line.separator");
    private Hashtable visited = new Hashtable();

    public void setOutput(File output) {
        this.output = output;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00d4  */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.AntStructure.execute():void");
    }

    private void printHead(PrintWriter out, Enumeration tasks, Enumeration types) {
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        out.println("<!ENTITY % boolean \"(true|false|on|off|yes|no)\">");
        out.print("<!ENTITY % tasks \"");
        boolean first = true;
        while (tasks.hasMoreElements()) {
            String taskName = (String) tasks.nextElement();
            if (!first) {
                out.print(" | ");
            } else {
                first = false;
            }
            out.print(taskName);
        }
        out.println("\">");
        out.print("<!ENTITY % types \"");
        boolean first2 = true;
        while (types.hasMoreElements()) {
            String typeName = (String) types.nextElement();
            if (!first2) {
                out.print(" | ");
            } else {
                first2 = false;
            }
            out.print(typeName);
        }
        out.println("\">");
        out.println("");
        out.print("<!ELEMENT project (target | ");
        out.print(TASKS);
        out.print(" | ");
        out.print(TYPES);
        out.println(")*>");
        out.println("<!ATTLIST project");
        out.println("          name    CDATA #IMPLIED");
        out.println("          default CDATA #IMPLIED");
        out.println("          basedir CDATA #IMPLIED>");
        out.println("");
    }

    private void printTargetDecl(PrintWriter out) {
        out.print("<!ELEMENT target (");
        out.print(TASKS);
        out.print(" | ");
        out.print(TYPES);
        out.println(")*>");
        out.println("");
        out.println("<!ATTLIST target");
        out.println("          id          ID    #IMPLIED");
        out.println("          name        CDATA #REQUIRED");
        out.println("          if          CDATA #IMPLIED");
        out.println("          unless      CDATA #IMPLIED");
        out.println("          depends     CDATA #IMPLIED");
        out.println("          description CDATA #IMPLIED>");
        out.println("");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24, types: [org.apache.tools.ant.types.EnumeratedAttribute] */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v37 */
    /* JADX WARN: Type inference failed for: r5v38 */
    /* JADX WARN: Type inference failed for: r5v39 */
    /* JADX WARN: Type inference failed for: r5v40 */
    /* JADX WARN: Type inference failed for: r5v41 */
    /* JADX WARN: Type inference failed for: r5v42 */
    /* JADX WARN: Type inference failed for: r5v43 */
    /* JADX WARN: Type inference failed for: r5v44 */
    private void printElementDecl(PrintWriter printWriter, String str, Class cls) throws BuildException {
        String str2;
        String str3;
        IntrospectionHelper introspectionHelper;
        ?? r5;
        AntStructure antStructure = this;
        PrintWriter printWriter2 = printWriter;
        String str4 = str;
        if (antStructure.visited.containsKey(str4)) {
            return;
        }
        antStructure.visited.put(str4, "");
        try {
            IntrospectionHelper helper = IntrospectionHelper.getHelper(cls);
            StringBuffer stringBuffer = new StringBuffer("<!ELEMENT ");
            stringBuffer.append(str4);
            stringBuffer.append(" ");
            Class clsClass$ = class$org$apache$tools$ant$types$Reference;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.tools.ant.types.Reference");
                class$org$apache$tools$ant$types$Reference = clsClass$;
            }
            if (clsClass$.equals(cls)) {
                stringBuffer.append("EMPTY>");
                stringBuffer.append(antStructure.lSep);
                stringBuffer.append("<!ATTLIST ");
                stringBuffer.append(str4);
                stringBuffer.append(antStructure.lSep);
                stringBuffer.append("          id ID #IMPLIED");
                stringBuffer.append(antStructure.lSep);
                stringBuffer.append("          refid IDREF #IMPLIED");
                stringBuffer.append(">");
                stringBuffer.append(antStructure.lSep);
                printWriter2.println(stringBuffer);
                return;
            }
            Vector vector = new Vector();
            if (helper.supportsCharacters()) {
                vector.addElement("#PCDATA");
            }
            Class clsClass$2 = class$org$apache$tools$ant$TaskContainer;
            if (clsClass$2 == null) {
                clsClass$2 = class$("org.apache.tools.ant.TaskContainer");
                class$org$apache$tools$ant$TaskContainer = clsClass$2;
            }
            if (clsClass$2.isAssignableFrom(cls)) {
                vector.addElement(TASKS);
            }
            Enumeration nestedElements = helper.getNestedElements();
            while (nestedElements.hasMoreElements()) {
                vector.addElement(nestedElements.nextElement());
                antStructure = this;
                printWriter2 = printWriter;
                str4 = str;
            }
            if (vector.isEmpty()) {
                stringBuffer.append("EMPTY");
                str2 = " | ";
            } else {
                stringBuffer.append("(");
                int size = vector.size();
                str2 = " | ";
                int i = 0;
                while (i < size) {
                    int i2 = i;
                    String str5 = str2;
                    IntrospectionHelper introspectionHelper2 = helper;
                    if (i2 != 0) {
                        stringBuffer.append(str5);
                    }
                    stringBuffer.append(vector.elementAt(i2));
                    i = i2 + 1;
                    printWriter2 = printWriter;
                    str4 = str;
                    helper = introspectionHelper2;
                    str2 = str5;
                    antStructure = this;
                }
                stringBuffer.append(")");
                if (size > 1 || !vector.elementAt(0).equals("#PCDATA")) {
                    stringBuffer.append("*");
                }
            }
            stringBuffer.append(">");
            printWriter2.println(stringBuffer);
            StringBuffer stringBuffer2 = new StringBuffer("<!ATTLIST ");
            stringBuffer2.append(str4);
            stringBuffer2.append(antStructure.lSep);
            stringBuffer2.append("          id ID #IMPLIED");
            Enumeration attributes = helper.getAttributes();
            ?? r52 = 0;
            while (attributes.hasMoreElements()) {
                String str6 = (String) attributes.nextElement();
                if (!"id".equals(str6)) {
                    stringBuffer2.append(antStructure.lSep);
                    stringBuffer2.append("          ");
                    stringBuffer2.append(str6);
                    stringBuffer2.append(" ");
                    Class<?> attributeType = helper.getAttributeType(str6);
                    Class clsClass$3 = class$java$lang$Boolean;
                    if (clsClass$3 == null) {
                        clsClass$3 = class$("java.lang.Boolean");
                        class$java$lang$Boolean = clsClass$3;
                    }
                    if (attributeType.equals(clsClass$3) || attributeType.equals(Boolean.TYPE)) {
                        str3 = str2;
                        introspectionHelper = helper;
                        stringBuffer2.append(BOOLEAN);
                        stringBuffer2.append(" ");
                        r52 = r52;
                        stringBuffer2.append("#IMPLIED");
                        printWriter2 = printWriter;
                        helper = introspectionHelper;
                        str2 = str3;
                        antStructure = this;
                    } else {
                        Class clsClass$4 = class$org$apache$tools$ant$types$Reference;
                        if (clsClass$4 == null) {
                            clsClass$4 = class$("org.apache.tools.ant.types.Reference");
                            class$org$apache$tools$ant$types$Reference = clsClass$4;
                        }
                        if (clsClass$4.isAssignableFrom(attributeType)) {
                            stringBuffer2.append("IDREF ");
                            str3 = str2;
                            introspectionHelper = helper;
                            r52 = r52;
                        } else {
                            Class clsClass$5 = class$org$apache$tools$ant$types$EnumeratedAttribute;
                            if (clsClass$5 == null) {
                                clsClass$5 = class$("org.apache.tools.ant.types.EnumeratedAttribute");
                                class$org$apache$tools$ant$types$EnumeratedAttribute = clsClass$5;
                            }
                            if (clsClass$5.isAssignableFrom(attributeType)) {
                                try {
                                    r52 = (EnumeratedAttribute) attributeType.newInstance();
                                    String[] values = r52.getValues();
                                    if (values == null || values.length == 0 || !antStructure.areNmtokens(values)) {
                                        str3 = str2;
                                        introspectionHelper = helper;
                                        try {
                                            stringBuffer2.append("CDATA ");
                                            r52 = r52;
                                        } catch (IllegalAccessException e) {
                                            r5 = r52;
                                            stringBuffer2.append("CDATA ");
                                            r52 = r5;
                                        } catch (InstantiationException e2) {
                                            e = e2;
                                            r52 = e;
                                            stringBuffer2.append("CDATA ");
                                        }
                                    } else {
                                        stringBuffer2.append("(");
                                        int i3 = 0;
                                        while (i3 < values.length) {
                                            if (i3 != 0) {
                                                str3 = str2;
                                                try {
                                                    stringBuffer2.append(str3);
                                                } catch (IllegalAccessException e3) {
                                                    introspectionHelper = helper;
                                                    r5 = r52;
                                                    stringBuffer2.append("CDATA ");
                                                    r52 = r5;
                                                    stringBuffer2.append("#IMPLIED");
                                                    printWriter2 = printWriter;
                                                    helper = introspectionHelper;
                                                    str2 = str3;
                                                    antStructure = this;
                                                } catch (InstantiationException e4) {
                                                    e = e4;
                                                    introspectionHelper = helper;
                                                    r52 = e;
                                                    stringBuffer2.append("CDATA ");
                                                    stringBuffer2.append("#IMPLIED");
                                                    printWriter2 = printWriter;
                                                    helper = introspectionHelper;
                                                    str2 = str3;
                                                    antStructure = this;
                                                }
                                            } else {
                                                str3 = str2;
                                            }
                                            IntrospectionHelper introspectionHelper3 = helper;
                                            stringBuffer2.append(values[i3]);
                                            i3++;
                                            helper = introspectionHelper3;
                                            str2 = str3;
                                        }
                                        try {
                                            stringBuffer2.append(") ");
                                            str3 = str2;
                                            introspectionHelper = helper;
                                            r52 = r52;
                                        } catch (IllegalAccessException e5) {
                                            str3 = str2;
                                            introspectionHelper = helper;
                                            r5 = r52;
                                            stringBuffer2.append("CDATA ");
                                            r52 = r5;
                                        } catch (InstantiationException e6) {
                                            e = e6;
                                            str3 = str2;
                                            introspectionHelper = helper;
                                            r52 = e;
                                            stringBuffer2.append("CDATA ");
                                        }
                                    }
                                } catch (IllegalAccessException e7) {
                                    str3 = str2;
                                    introspectionHelper = helper;
                                    r5 = r52;
                                } catch (InstantiationException e8) {
                                    e = e8;
                                    str3 = str2;
                                    introspectionHelper = helper;
                                }
                            } else {
                                str3 = str2;
                                introspectionHelper = helper;
                                stringBuffer2.append("CDATA ");
                                r52 = r52;
                            }
                        }
                        stringBuffer2.append("#IMPLIED");
                        printWriter2 = printWriter;
                        helper = introspectionHelper;
                        str2 = str3;
                        antStructure = this;
                    }
                }
            }
            stringBuffer2.append(">");
            stringBuffer2.append(antStructure.lSep);
            printWriter2.println(stringBuffer2);
            int size2 = vector.size();
            for (int i4 = 0; i4 < size2; i4++) {
                String str7 = (String) vector.elementAt(i4);
                if (!"#PCDATA".equals(str7) && !TASKS.equals(str7) && !TYPES.equals(str7)) {
                    antStructure.printElementDecl(printWriter2, str7, helper.getElementType(str7));
                }
            }
        } catch (Throwable th) {
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    protected boolean isNmtoken(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '-' && c != '_' && c != ':') {
                return false;
            }
        }
        return true;
    }

    protected boolean areNmtokens(String[] s) {
        for (String str : s) {
            if (!isNmtoken(str)) {
                return false;
            }
        }
        return true;
    }
}
