package com.sun.xml.bind.marshaller;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
public class Messages {
    public static final String ASSERT_FAILED = "SAXMarshaller.AssertFailed";
    public static final String DOM_IMPL_DOESNT_SUPPORT_CREATELEMENTNS = "SAX2DOMEx.DomImplDoesntSupportCreateElementNs";
    public static final String ERR_DANGLING_IDREF = "SAXMarshaller.DanglingIDREF";
    public static final String ERR_MISSING_OBJECT = "SAXMarshaller.MissingObject";
    public static final String ERR_NOT_IDENTIFIABLE = "SAXMarshaller.NotIdentifiable";
    public static final String NOT_MARSHALLABLE = "MarshallerImpl.NotMarshallable";
    public static final String NULL_WRITER = "MarshallerImpl.NullWriterParam";
    public static final String UNSUPPORTED_ENCODING = "MarshallerImpl.UnsupportedEncoding";
    public static final String UNSUPPORTED_RESULT = "MarshallerImpl.UnsupportedResult";

    public static String format(String str) {
        return format(str, (Object[]) null);
    }

    public static String format(String str, Object obj) {
        return format(str, new Object[]{obj});
    }

    public static String format(String str, Object obj, Object obj2) {
        return format(str, new Object[]{obj, obj2});
    }

    public static String format(String str, Object obj, Object obj2, Object obj3) {
        return format(str, new Object[]{obj, obj2, obj3});
    }

    static String format(String str, Object[] objArr) {
        return MessageFormat.format(ResourceBundle.getBundle(Messages.class.getName()).getString(str), objArr);
    }
}
