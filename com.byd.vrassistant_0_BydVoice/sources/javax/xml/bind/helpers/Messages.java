package javax.xml.bind.helpers;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
class Messages {
    static final String ERROR = "DefaultValidationEventHandler.Error";
    static final String FATAL_ERROR = "DefaultValidationEventHandler.FatalError";
    static final String ILLEGAL_SEVERITY = "ValidationEventImpl.IllegalSeverity";
    static final String INPUTSTREAM_NOT_NULL = "AbstractUnmarshallerImpl.ISNotNull";
    static final String LOCATION_UNAVAILABLE = "DefaultValidationEventHandler.LocationUnavailable";
    static final String MUST_BE_BOOLEAN = "AbstractMarshallerImpl.MustBeBoolean";
    static final String MUST_BE_STRING = "AbstractMarshallerImpl.MustBeString";
    static final String MUST_NOT_BE_NULL = "Shared.MustNotBeNull";
    static final String SEVERITY_MESSAGE = "DefaultValidationEventHandler.SeverityMessage";
    static final String UNRECOGNIZED_SEVERITY = "DefaultValidationEventHandler.UnrecognizedSeverity";
    static final String WARNING = "DefaultValidationEventHandler.Warning";

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
