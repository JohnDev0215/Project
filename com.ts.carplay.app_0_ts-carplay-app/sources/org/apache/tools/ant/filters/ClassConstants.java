package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class ClassConstants extends BaseFilterReader implements ChainableReader {
    private static final String JAVA_CLASS_HELPER = "org.apache.tools.ant.filters.util.JavaClassHelper";
    static /* synthetic */ Class array$B;
    private String queuedData;

    public ClassConstants() {
        this.queuedData = null;
    }

    public ClassConstants(Reader in) {
        super(in);
        this.queuedData = null;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        NoSuchMethodException nsme;
        Class<?>[] clsArr;
        Class<?> clsClass$;
        String str = this.queuedData;
        if (str != null && str.length() == 0) {
            this.queuedData = null;
        }
        String str2 = this.queuedData;
        if (str2 != null) {
            int ch = str2.charAt(0);
            String strSubstring = this.queuedData.substring(1);
            this.queuedData = strSubstring;
            if (strSubstring.length() != 0) {
                return ch;
            }
            this.queuedData = null;
            return ch;
        }
        String clazz = readFully();
        if (clazz == null) {
            return -1;
        }
        byte[] bytes = clazz.getBytes();
        try {
            try {
                Class<?> cls = Class.forName(JAVA_CLASS_HELPER);
                if (cls == null) {
                    return -1;
                }
                try {
                    try {
                        clsArr = new Class[1];
                        if (array$B == null) {
                            clsClass$ = class$("[B");
                            array$B = clsClass$;
                        } else {
                            clsClass$ = array$B;
                        }
                        clsArr[0] = clsClass$;
                    } catch (IllegalAccessException e) {
                        iae = e;
                    } catch (IllegalArgumentException e2) {
                        iarge = e2;
                    } catch (InvocationTargetException e3) {
                        ite = e3;
                        throw new IOException(ite.getMessage());
                    }
                    try {
                        try {
                            Method getConstants = cls.getMethod("getConstants", clsArr);
                            try {
                                try {
                                    Object[] args = {bytes};
                                    try {
                                        StringBuffer sb = (StringBuffer) getConstants.invoke(null, args);
                                        if (sb.length() <= 0) {
                                            return -1;
                                        }
                                        this.queuedData = sb.toString();
                                        return read();
                                    } catch (InvocationTargetException e4) {
                                        ite = e4;
                                        throw new IOException(ite.getMessage());
                                    }
                                } catch (IllegalArgumentException e5) {
                                    iarge = e5;
                                    throw new IOException(iarge.getMessage());
                                }
                            } catch (InvocationTargetException e6) {
                                ite = e6;
                            }
                        } catch (IllegalArgumentException e7) {
                            iarge = e7;
                        } catch (InvocationTargetException e8) {
                            ite = e8;
                        }
                    } catch (IllegalAccessException e9) {
                        iae = e9;
                        throw new IOException(iae.getMessage());
                    }
                } catch (NoSuchMethodException e10) {
                    nsme = e10;
                    throw new IOException(nsme.getMessage());
                }
            } catch (IllegalAccessException e11) {
                iae = e11;
            } catch (IllegalArgumentException e12) {
                iarge = e12;
            } catch (NoSuchMethodException e13) {
                nsme = e13;
            } catch (InvocationTargetException e14) {
                ite = e14;
            }
        } catch (ClassNotFoundException cnfe) {
            throw new IOException(cnfe.getMessage());
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        ClassConstants newFilter = new ClassConstants(rdr);
        return newFilter;
    }
}
