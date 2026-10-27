package com.sun.xml.bind.unmarshaller;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
public class Messages {
    public static final String ILLEGAL_READER_STATE = "Unmarshaller.IllegalReaderState";
    public static final String NULL_READER = "Unmarshaller.NullReader";
    public static final String UNDEFINED_PREFIX = "Util.UndefinedPrefix";
    public static final String UNEXPECTED_ENTER_ATTRIBUTE = "ContentHandlerEx.UnexpectedEnterAttribute";
    public static final String UNEXPECTED_ENTER_ELEMENT = "ContentHandlerEx.UnexpectedEnterElement";
    public static final String UNEXPECTED_LEAVE_ATTRIBUTE = "ContentHandlerEx.UnexpectedLeaveAttribute";
    public static final String UNEXPECTED_LEAVE_CHILD = "ContentHandlerEx.UnexpectedLeaveChild";
    public static final String UNEXPECTED_LEAVE_ELEMENT = "ContentHandlerEx.UnexpectedLeaveElement";
    public static final String UNEXPECTED_ROOT_ELEMENT = "SAXUnmarshallerHandlerImpl.UnexpectedRootElement";
    public static final String UNEXPECTED_TEXT = "ContentHandlerEx.UnexpectedText";

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

    public static String format(String str, Object[] objArr) {
        return MessageFormat.format(ResourceBundle.getBundle(Messages.class.getName()).getString(str), objArr);
    }
}
