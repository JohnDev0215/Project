package org.apache.tools.ant.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class StringUtils {
    public static final String LINE_SEP = System.getProperty("line.separator");

    public static Vector lineSplit(String data) {
        return split(data, 10);
    }

    public static Vector split(String data, int ch) {
        Vector elems = new Vector();
        int i = 0;
        while (true) {
            int pos = data.indexOf(ch, i);
            if (pos != -1) {
                String elem = data.substring(i, pos);
                elems.addElement(elem);
                i = pos + 1;
            } else {
                elems.addElement(data.substring(i));
                return elems;
            }
        }
    }

    public static String replace(String data, String from, String to) {
        StringBuffer buf = new StringBuffer(data.length());
        int i = 0;
        while (true) {
            int pos = data.indexOf(from, i);
            if (pos != -1) {
                buf.append(data.substring(i, pos));
                buf.append(to);
                i = pos + from.length();
            } else {
                buf.append(data.substring(i));
                return buf.toString();
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter((Writer) sw, true);
        t.printStackTrace(pw);
        pw.flush();
        pw.close();
        return sw.toString();
    }
}
