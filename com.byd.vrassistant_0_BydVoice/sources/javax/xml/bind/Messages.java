package javax.xml.bind;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
class Messages {
    static final String CANT_FIND_PROPERTIES_FILE = "ContextFinder.CantFindPropertiesFile";
    static final String CANT_MIX_PROVIDERS = "ContextFinder.CantMixProviders";
    static final String CONVERTER_MUST_NOT_BE_NULL = "DatatypeConverter.ConverterMustNotBeNull";
    static final String COULD_NOT_INSTANTIATE = "ContextFinder.CouldNotInstantiate";
    static final String DEFAULT_PROVIDER_NOT_FOUND = "ContextFinder.DefaultProviderNotFound";
    static final String ERROR_LOAD_CLASS = "ContextFinder.ErrorLoadClass";
    static final String ILLEGAL_CAST = "JAXBContext.IllegalCast";
    static final String JAXB_CLASSES_NOT_OPEN = "JAXBClasses.notOpen";
    static final String MISSING_PROPERTY = "ContextFinder.MissingProperty";
    static final String NAME_VALUE = "PropertyException.NameValue";
    static final String NO_PACKAGE_IN_CONTEXTPATH = "ContextFinder.NoPackageInContextPath";
    static final String PROVIDER_NOT_FOUND = "ContextFinder.ProviderNotFound";

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
