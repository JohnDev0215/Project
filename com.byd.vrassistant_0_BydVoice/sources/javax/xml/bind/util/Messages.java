package javax.xml.bind.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
class Messages {
    static final String RESULT_NULL_CONTEXT = "JAXBResult.NullContext";
    static final String RESULT_NULL_UNMARSHALLER = "JAXBResult.NullUnmarshaller";
    static final String SOURCE_NULL_CONTENT = "JAXBSource.NullContent";
    static final String SOURCE_NULL_CONTEXT = "JAXBSource.NullContext";
    static final String SOURCE_NULL_MARSHALLER = "JAXBSource.NullMarshaller";
    static final String UNRECOGNIZED_SEVERITY = "ValidationEventCollector.UnrecognizedSeverity";

    Messages() {
    }

    static String format(String str) {
        return format(str, (Object[]) null);
    }

    static String format(String str, Object obj) {
        return format(str, new Object[]{obj});
    }

    static String format(String str, Object obj, Object obj2) {
        return format(str, new Object[]{obj, obj2});
    }

    static String format(String str, Object obj, Object obj2, Object obj3) {
        return format(str, new Object[]{obj, obj2, obj3});
    }

    static String format(String str, Object[] objArr) {
        return MessageFormat.format(ResourceBundle.getBundle(Messages.class.getName()).getString(str), objArr);
    }
}
