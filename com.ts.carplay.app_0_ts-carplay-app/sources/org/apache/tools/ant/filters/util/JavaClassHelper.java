package org.apache.tools.ant.filters.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ConstantValue;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;

/* JADX INFO: loaded from: classes.dex */
public final class JavaClassHelper {
    private static final String LS = System.getProperty("line.separator");

    public static final StringBuffer getConstants(byte[] bytes) throws IOException {
        ConstantValue cv;
        StringBuffer sb = new StringBuffer();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ClassParser parser = new ClassParser(bis, "");
        JavaClass javaClass = parser.parse();
        Field[] fields = javaClass.getFields();
        for (Field field : fields) {
            if (field != null && (cv = field.getConstantValue()) != null) {
                String cvs = cv.toString();
                if (cvs.startsWith("\"") && cvs.endsWith("\"")) {
                    cvs = cvs.substring(1, cvs.length() - 1);
                }
                sb.append(field.getName());
                sb.append('=');
                sb.append(cvs);
                sb.append(LS);
            }
        }
        return sb;
    }
}
