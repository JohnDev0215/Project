package org.apache.tools.zip;

import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class ExtraFieldUtils {
    static /* synthetic */ Class class$org$apache$tools$zip$AsiExtraField;
    static /* synthetic */ Class class$org$apache$tools$zip$JarMarker;
    private static Hashtable implementations = new Hashtable();

    static {
        Class clsClass$ = class$org$apache$tools$zip$AsiExtraField;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.zip.AsiExtraField");
            class$org$apache$tools$zip$AsiExtraField = clsClass$;
        }
        register(clsClass$);
        Class clsClass$2 = class$org$apache$tools$zip$JarMarker;
        if (clsClass$2 == null) {
            clsClass$2 = class$("org.apache.tools.zip.JarMarker");
            class$org$apache$tools$zip$JarMarker = clsClass$2;
        }
        register(clsClass$2);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public static void register(Class c) {
        try {
            try {
                ZipExtraField ze = (ZipExtraField) c.newInstance();
                try {
                    implementations.put(ze.getHeaderId(), c);
                } catch (IllegalAccessException e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(c);
                    stringBuffer.append("'s no-arg constructor is not public");
                    throw new RuntimeException(stringBuffer.toString());
                } catch (InstantiationException e2) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(c);
                    stringBuffer2.append(" is not a concrete class");
                    throw new RuntimeException(stringBuffer2.toString());
                }
            } catch (IllegalAccessException e3) {
            } catch (InstantiationException e4) {
            }
        } catch (ClassCastException e5) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(c);
            stringBuffer3.append(" doesn't implement ZipExtraField");
            throw new RuntimeException(stringBuffer3.toString());
        }
    }

    public static ZipExtraField createExtraField(ZipShort headerId) throws IllegalAccessException, InstantiationException {
        Class c = (Class) implementations.get(headerId);
        if (c != null) {
            return (ZipExtraField) c.newInstance();
        }
        UnrecognizedExtraField u = new UnrecognizedExtraField();
        u.setHeaderId(headerId);
        return u;
    }

    public static ZipExtraField[] parse(byte[] data) throws ZipException {
        Vector v = new Vector();
        int start = 0;
        while (start <= data.length - 4) {
            ZipShort headerId = new ZipShort(data, start);
            int length = new ZipShort(data, start + 2).getValue();
            if (start + 4 + length > data.length) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("data starting at ");
                stringBuffer.append(start);
                stringBuffer.append(" is in unknown format");
                throw new ZipException(stringBuffer.toString());
            }
            try {
                ZipExtraField ze = createExtraField(headerId);
                ze.parseFromLocalFileData(data, start + 4, length);
                v.addElement(ze);
                start += length + 4;
            } catch (IllegalAccessException iae) {
                throw new ZipException(iae.getMessage());
            } catch (InstantiationException ie) {
                throw new ZipException(ie.getMessage());
            }
        }
        if (start != data.length) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("data starting at ");
            stringBuffer2.append(start);
            stringBuffer2.append(" is in unknown format");
            throw new ZipException(stringBuffer2.toString());
        }
        ZipExtraField[] result = new ZipExtraField[v.size()];
        v.copyInto(result);
        return result;
    }

    public static byte[] mergeLocalFileDataData(ZipExtraField[] data) {
        int sum = data.length * 4;
        for (ZipExtraField zipExtraField : data) {
            sum += zipExtraField.getLocalFileDataLength().getValue();
        }
        byte[] result = new byte[sum];
        int start = 0;
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
            System.arraycopy(data[i].getLocalFileDataLength().getBytes(), 0, result, start + 2, 2);
            byte[] local = data[i].getLocalFileDataData();
            System.arraycopy(local, 0, result, start + 4, local.length);
            start += local.length + 4;
        }
        return result;
    }

    public static byte[] mergeCentralDirectoryData(ZipExtraField[] data) {
        int sum = data.length * 4;
        for (ZipExtraField zipExtraField : data) {
            sum += zipExtraField.getCentralDirectoryLength().getValue();
        }
        byte[] result = new byte[sum];
        int start = 0;
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
            System.arraycopy(data[i].getCentralDirectoryLength().getBytes(), 0, result, start + 2, 2);
            byte[] local = data[i].getCentralDirectoryData();
            System.arraycopy(local, 0, result, start + 4, local.length);
            start += local.length + 4;
        }
        return result;
    }
}
