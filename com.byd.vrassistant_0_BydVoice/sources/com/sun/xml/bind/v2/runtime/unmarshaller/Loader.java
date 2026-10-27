package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.util.Collection;
import java.util.Collections;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.namespace.QName;
import kotlin.text.Typography;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Loader {
    protected boolean expectText;

    public void leaveElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
    }

    public void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
    }

    protected Loader(boolean z) {
        this.expectText = z;
    }

    protected Loader() {
    }

    public void childElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
        reportUnexpectedChildElement(tagName, true);
        state.setLoader(Discarder.INSTANCE);
        state.setReceiver(null);
    }

    protected final void reportUnexpectedChildElement(TagName tagName, boolean z) throws SAXException {
        if (z) {
            UnmarshallingContext unmarshallingContext = UnmarshallingContext.getInstance();
            if (!unmarshallingContext.parent.hasEventHandler() || !unmarshallingContext.shouldErrorBeReported()) {
                return;
            }
        }
        if (tagName.uri == tagName.uri.intern() && tagName.local == tagName.local.intern()) {
            reportError(Messages.UNEXPECTED_ELEMENT.format(tagName.uri, tagName.local, computeExpectedElements()), z);
        } else {
            reportError(Messages.UNINTERNED_STRINGS.format(new Object[0]), z);
        }
    }

    public Collection<QName> getExpectedChildElements() {
        return Collections.emptyList();
    }

    public Collection<QName> getExpectedAttributes() {
        return Collections.emptyList();
    }

    public void text(UnmarshallingContext.State state, CharSequence charSequence) throws SAXException {
        reportError(Messages.UNEXPECTED_TEXT.format(charSequence.toString().replace('\r', ' ').replace('\n', ' ').replace('\t', ' ').trim()), true);
    }

    public final boolean expectText() {
        return this.expectText;
    }

    private String computeExpectedElements() {
        StringBuilder sb = new StringBuilder();
        for (QName qName : getExpectedChildElements()) {
            if (sb.length() != 0) {
                sb.append(',');
            }
            sb.append("<{").append(qName.getNamespaceURI()).append('}').append(qName.getLocalPart()).append(Typography.greater);
        }
        return sb.length() == 0 ? "(none)" : sb.toString();
    }

    protected final void fireBeforeUnmarshal(JaxBeanInfo jaxBeanInfo, Object obj, UnmarshallingContext.State state) throws SAXException {
        if (jaxBeanInfo.lookForLifecycleMethods()) {
            UnmarshallingContext context = state.getContext();
            Unmarshaller.Listener listener = context.parent.getListener();
            if (jaxBeanInfo.hasBeforeUnmarshalMethod()) {
                jaxBeanInfo.invokeBeforeUnmarshalMethod(context.parent, obj, state.getPrev().getTarget());
            }
            if (listener != null) {
                listener.beforeUnmarshal(obj, state.getPrev().getTarget());
            }
        }
    }

    protected final void fireAfterUnmarshal(JaxBeanInfo jaxBeanInfo, Object obj, UnmarshallingContext.State state) throws SAXException {
        if (jaxBeanInfo.lookForLifecycleMethods()) {
            UnmarshallingContext context = state.getContext();
            Unmarshaller.Listener listener = context.parent.getListener();
            if (jaxBeanInfo.hasAfterUnmarshalMethod()) {
                jaxBeanInfo.invokeAfterUnmarshalMethod(context.parent, obj, state.getTarget());
            }
            if (listener != null) {
                listener.afterUnmarshal(obj, state.getTarget());
            }
        }
    }

    protected static void handleGenericException(Exception exc) throws SAXException {
        handleGenericException(exc, false);
    }

    public static void handleGenericException(Exception exc, boolean z) throws SAXException {
        reportError(exc.getMessage(), exc, z);
    }

    public static void handleGenericError(Error error) throws SAXException {
        reportError(error.getMessage(), false);
    }

    protected static void reportError(String str, boolean z) throws SAXException {
        reportError(str, null, z);
    }

    public static void reportError(String str, Exception exc, boolean z) throws SAXException {
        UnmarshallingContext unmarshallingContext = UnmarshallingContext.getInstance();
        unmarshallingContext.handleEvent(new ValidationEventImpl(z ? 1 : 2, str, unmarshallingContext.getLocator().getLocation(), exc), z);
    }

    protected static void handleParseConversionException(UnmarshallingContext.State state, Exception exc) throws SAXException {
        state.getContext().handleError(exc);
    }
}
