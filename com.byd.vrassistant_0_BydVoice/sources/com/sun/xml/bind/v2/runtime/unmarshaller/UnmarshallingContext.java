package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.istack.SAXParseException2;
import com.sun.xml.bind.IDResolver;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.api.ClassResolver;
import com.sun.xml.bind.unmarshaller.InfosetScanner;
import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.runtime.AssociationMap;
import com.sun.xml.bind.v2.runtime.Coordinator;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: classes3.dex */
public final class UnmarshallingContext extends Coordinator implements NamespaceContext, ValidationEventHandler, ErrorHandler, XmlVisitor, XmlVisitor.TextPredictor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Loader DEFAULT_ROOT_LOADER;
    private static final LocatorEx DUMMY_INSTANCE;
    private static final Loader EXPECTED_TYPE_ROOT_LOADER;
    private static volatile int errorsCounter;
    private static final Logger logger = Logger.getLogger(UnmarshallingContext.class.getName());
    private final AssociationMap assoc;
    public ClassLoader classLoader;
    public ClassResolver classResolver;
    private State current;
    private Object currentElement;
    private NamespaceContext environmentNamespaceContext;
    private JaxBeanInfo expectedType;
    private IDResolver idResolver;
    private boolean isInplaceMode;
    public final UnmarshallerImpl parent;
    private Object result;
    private final State root;
    private InfosetScanner scanner;
    private LocatorEx locator = DUMMY_INSTANCE;
    private boolean isUnmarshalInProgress = true;
    private boolean aborted = false;
    private final Map<Class, Factory> factories = new HashMap();
    private Patcher[] patchers = null;
    private int patchersLen = 0;
    private String[] nsBind = new String[16];
    private int nsLen = 0;
    private Scope[] scopes = new Scope[16];
    private int scopeTop = 0;

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public UnmarshallingContext getContext() {
        return this;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    @Deprecated
    public XmlVisitor.TextPredictor getPredictor() {
        return this;
    }

    static {
        LocatorImpl locatorImpl = new LocatorImpl();
        locatorImpl.setPublicId(null);
        locatorImpl.setSystemId(null);
        locatorImpl.setLineNumber(-1);
        locatorImpl.setColumnNumber(-1);
        DUMMY_INSTANCE = new LocatorExWrapper(locatorImpl);
        errorsCounter = 10;
        DEFAULT_ROOT_LOADER = new DefaultRootLoader();
        EXPECTED_TYPE_ROOT_LOADER = new ExpectedTypeRootLoader();
    }

    public final class State {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Object backup;
        private String elementDefaultValue;
        private Intercepter intercepter;
        private Loader loader;
        private boolean mixed;
        private State next;
        private boolean nil;
        private int numNsDecl;
        private State prev;
        private Receiver receiver;
        private Object target;

        public UnmarshallingContext getContext() {
            return UnmarshallingContext.this;
        }

        private State(State state) {
            this.nil = false;
            this.mixed = false;
            this.prev = state;
            if (state != null) {
                state.next = this;
                if (state.mixed) {
                    this.mixed = true;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void push() {
            if (UnmarshallingContext.logger.isLoggable(Level.FINEST)) {
                UnmarshallingContext.logger.log(Level.FINEST, "State.push");
            }
            if (this.next == null) {
                this.next = UnmarshallingContext.this.new State(this);
            }
            this.nil = false;
            State state = this.next;
            state.numNsDecl = UnmarshallingContext.this.nsLen;
            UnmarshallingContext.this.current = state;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void pop() {
            if (UnmarshallingContext.logger.isLoggable(Level.FINEST)) {
                UnmarshallingContext.logger.log(Level.FINEST, "State.pop");
            }
            this.loader = null;
            this.nil = false;
            this.mixed = false;
            this.receiver = null;
            this.intercepter = null;
            this.elementDefaultValue = null;
            this.target = null;
            UnmarshallingContext.this.current = this.prev;
            this.next = null;
        }

        public boolean isMixed() {
            return this.mixed;
        }

        public Object getTarget() {
            return this.target;
        }

        public void setLoader(Loader loader) {
            if (loader instanceof StructureLoader) {
                this.mixed = !((StructureLoader) loader).getBeanInfo().hasElementOnlyContentModel();
            }
            this.loader = loader;
        }

        public void setReceiver(Receiver receiver) {
            this.receiver = receiver;
        }

        public State getPrev() {
            return this.prev;
        }

        public void setIntercepter(Intercepter intercepter) {
            this.intercepter = intercepter;
        }

        public void setBackup(Object obj) {
            this.backup = obj;
        }

        public void setTarget(Object obj) {
            this.target = obj;
        }

        public Object getBackup() {
            return this.backup;
        }

        public boolean isNil() {
            return this.nil;
        }

        public void setNil(boolean z) {
            this.nil = z;
        }

        public Loader getLoader() {
            return this.loader;
        }

        public String getElementDefaultValue() {
            return this.elementDefaultValue;
        }

        public void setElementDefaultValue(String str) {
            this.elementDefaultValue = str;
        }
    }

    private static class Factory {
        private final Object factorInstance;
        private final Method method;

        public Factory(Object obj, Method method) {
            this.factorInstance = obj;
            this.method = method;
        }

        public Object createInstance() throws SAXException {
            try {
                return this.method.invoke(this.factorInstance, new Object[0]);
            } catch (IllegalAccessException e) {
                UnmarshallingContext.getInstance().handleError(e, false);
                return null;
            } catch (InvocationTargetException e2) {
                UnmarshallingContext.getInstance().handleError(e2, false);
                return null;
            }
        }
    }

    public UnmarshallingContext(UnmarshallerImpl unmarshallerImpl, AssociationMap associationMap) {
        int i = 0;
        State state = null;
        byte b = 0;
        while (true) {
            Scope[] scopeArr = this.scopes;
            if (i >= scopeArr.length) {
                this.parent = unmarshallerImpl;
                this.assoc = associationMap;
                State state2 = new State(state);
                this.current = state2;
                this.root = state2;
                return;
            }
            scopeArr[i] = new Scope(this);
            i++;
        }
    }

    public void reset(InfosetScanner infosetScanner, boolean z, JaxBeanInfo jaxBeanInfo, IDResolver iDResolver) {
        this.scanner = infosetScanner;
        this.isInplaceMode = z;
        this.expectedType = jaxBeanInfo;
        this.idResolver = iDResolver;
    }

    public JAXBContextImpl getJAXBContext() {
        return this.parent.context;
    }

    public State getCurrentState() {
        return this.current;
    }

    public Loader selectRootLoader(State state, TagName tagName) throws SAXException {
        Class<?> clsResolveElementName;
        try {
            Loader loaderSelectRootLoader = getJAXBContext().selectRootLoader(state, tagName);
            if (loaderSelectRootLoader != null) {
                return loaderSelectRootLoader;
            }
            ClassResolver classResolver = this.classResolver;
            if (classResolver == null || (clsResolveElementName = classResolver.resolveElementName(tagName.uri, tagName.local)) == null) {
                return null;
            }
            JAXBContextImpl jAXBContextImplCreateAugmented = getJAXBContext().createAugmented(clsResolveElementName);
            return jAXBContextImplCreateAugmented.getBeanInfo((Class) clsResolveElementName).getLoader(jAXBContextImplCreateAugmented, true);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            handleError(e2);
            return null;
        }
    }

    public void clearStates() {
        State state = this.current;
        while (state.next != null) {
            state = state.next;
        }
        while (state.prev != null) {
            state.loader = null;
            state.nil = false;
            state.receiver = null;
            state.intercepter = null;
            state.elementDefaultValue = null;
            state.target = null;
            state = state.prev;
            state.next.prev = null;
            state.next = null;
        }
        this.current = state;
    }

    public void setFactories(Object obj) {
        this.factories.clear();
        if (obj == null) {
            return;
        }
        if (obj instanceof Object[]) {
            for (Object obj2 : (Object[]) obj) {
                addFactory(obj2);
            }
            return;
        }
        addFactory(obj);
    }

    private void addFactory(Object obj) {
        for (Method method : obj.getClass().getMethods()) {
            if (method.getName().startsWith("create") && method.getParameterTypes().length <= 0) {
                this.factories.put(method.getReturnType(), new Factory(obj, method));
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startDocument(LocatorEx locatorEx, NamespaceContext namespaceContext) throws SAXException {
        if (locatorEx != null) {
            this.locator = locatorEx;
        }
        this.environmentNamespaceContext = namespaceContext;
        this.result = null;
        State state = this.root;
        this.current = state;
        this.patchersLen = 0;
        this.aborted = false;
        this.isUnmarshalInProgress = true;
        this.nsLen = 0;
        if (this.expectedType != null) {
            state.loader = EXPECTED_TYPE_ROOT_LOADER;
        } else {
            state.loader = DEFAULT_ROOT_LOADER;
        }
        this.idResolver.startDocument(this);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startElement(TagName tagName) throws SAXException {
        pushCoordinator();
        try {
            _startElement(tagName);
        } finally {
            popCoordinator();
        }
    }

    private void _startElement(TagName tagName) throws SAXException {
        if (this.assoc != null) {
            this.currentElement = this.scanner.getCurrentElement();
        }
        Loader loader = this.current.loader;
        this.current.push();
        loader.childElement(this.current, tagName);
        this.current.loader.startElement(this.current, tagName);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void text(CharSequence charSequence) throws SAXException {
        pushCoordinator();
        try {
            if (this.current.elementDefaultValue != null && charSequence.length() == 0) {
                charSequence = this.current.elementDefaultValue;
            }
            this.current.loader.text(this.current, charSequence);
        } finally {
            popCoordinator();
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public final void endElement(TagName tagName) throws SAXException {
        pushCoordinator();
        try {
            State state = this.current;
            state.loader.leaveElement(state, tagName);
            Object objIntercept = state.target;
            Receiver receiver = state.receiver;
            Intercepter intercepter = state.intercepter;
            state.pop();
            if (intercepter != null) {
                objIntercept = intercepter.intercept(this.current, objIntercept);
            }
            if (receiver != null) {
                receiver.receive(this.current, objIntercept);
            }
        } finally {
            popCoordinator();
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endDocument() throws SAXException {
        runPatchers();
        this.idResolver.endDocument();
        this.isUnmarshalInProgress = false;
        this.currentElement = null;
        this.locator = DUMMY_INSTANCE;
        this.environmentNamespaceContext = null;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor.TextPredictor
    @Deprecated
    public boolean expectText() {
        return this.current.loader.expectText;
    }

    public Object getResult() throws UnmarshalException {
        if (this.isUnmarshalInProgress) {
            throw new IllegalStateException();
        }
        if (!this.aborted) {
            return this.result;
        }
        throw new UnmarshalException((String) null);
    }

    void clearResult() {
        if (this.isUnmarshalInProgress) {
            throw new IllegalStateException();
        }
        this.result = null;
    }

    public Object createInstance(Class<?> cls) throws SAXException {
        Factory factory;
        if (!this.factories.isEmpty() && (factory = this.factories.get(cls)) != null) {
            return factory.createInstance();
        }
        return ClassFactory.create(cls);
    }

    public Object createInstance(JaxBeanInfo jaxBeanInfo) throws SAXException {
        Factory factory;
        if (!this.factories.isEmpty() && (factory = this.factories.get(jaxBeanInfo.jaxbType)) != null) {
            return factory.createInstance();
        }
        try {
            return jaxBeanInfo.createInstance(this);
        } catch (IllegalAccessException e) {
            Loader.reportError("Unable to create an instance of " + jaxBeanInfo.jaxbType.getName(), e, false);
            return null;
        } catch (InstantiationException e2) {
            Loader.reportError("Unable to create an instance of " + jaxBeanInfo.jaxbType.getName(), e2, false);
            return null;
        } catch (InvocationTargetException e3) {
            Loader.reportError("Unable to create an instance of " + jaxBeanInfo.jaxbType.getName(), e3, false);
            return null;
        }
    }

    public void handleEvent(ValidationEvent validationEvent, boolean z) throws SAXException {
        boolean zHandleEvent = this.parent.getEventHandler().handleEvent(validationEvent);
        if (!zHandleEvent) {
            this.aborted = true;
        }
        if (!z || !zHandleEvent) {
            throw new SAXParseException2(validationEvent.getMessage(), this.locator, new UnmarshalException(validationEvent.getMessage(), validationEvent.getLinkedException()));
        }
    }

    @Override // javax.xml.bind.ValidationEventHandler
    public boolean handleEvent(ValidationEvent validationEvent) {
        try {
            boolean zHandleEvent = this.parent.getEventHandler().handleEvent(validationEvent);
            if (!zHandleEvent) {
                this.aborted = true;
            }
            return zHandleEvent;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public void handleError(Exception exc) throws SAXException {
        handleError(exc, true);
    }

    public void handleError(Exception exc, boolean z) throws SAXException {
        handleEvent(new ValidationEventImpl(1, exc.getMessage(), this.locator.getLocation(), exc), z);
    }

    public void handleError(String str) {
        handleEvent(new ValidationEventImpl(1, str, this.locator.getLocation()));
    }

    @Override // com.sun.xml.bind.v2.runtime.Coordinator
    protected ValidationEventLocator getLocation() {
        return this.locator.getLocation();
    }

    public LocatorEx getLocator() {
        return this.locator;
    }

    public void errorUnresolvedIDREF(Object obj, String str, LocatorEx locatorEx) throws SAXException {
        handleEvent(new ValidationEventImpl(1, Messages.UNRESOLVED_IDREF.format(str), locatorEx.getLocation()), true);
    }

    public void addPatcher(Patcher patcher) {
        if (this.patchers == null) {
            this.patchers = new Patcher[32];
        }
        Patcher[] patcherArr = this.patchers;
        int length = patcherArr.length;
        int i = this.patchersLen;
        if (length == i) {
            Patcher[] patcherArr2 = new Patcher[i * 2];
            System.arraycopy(patcherArr, 0, patcherArr2, 0, i);
            this.patchers = patcherArr2;
        }
        Patcher[] patcherArr3 = this.patchers;
        int i2 = this.patchersLen;
        this.patchersLen = i2 + 1;
        patcherArr3[i2] = patcher;
    }

    private void runPatchers() throws SAXException {
        if (this.patchers != null) {
            for (int i = 0; i < this.patchersLen; i++) {
                this.patchers[i].run();
                this.patchers[i] = null;
            }
        }
    }

    public String addToIdTable(String str) throws SAXException {
        Object obj = this.current.target;
        if (obj == null) {
            obj = this.current.prev.target;
        }
        this.idResolver.bind(str, obj);
        return str;
    }

    public Callable getObjectFromId(String str, Class cls) throws SAXException {
        return this.idResolver.resolve(str, cls);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startPrefixMapping(String str, String str2) {
        String[] strArr = this.nsBind;
        int length = strArr.length;
        int i = this.nsLen;
        if (length == i) {
            String[] strArr2 = new String[i * 2];
            System.arraycopy(strArr, 0, strArr2, 0, i);
            this.nsBind = strArr2;
        }
        String[] strArr3 = this.nsBind;
        int i2 = this.nsLen;
        int i3 = i2 + 1;
        strArr3[i2] = str;
        this.nsLen = i3 + 1;
        strArr3[i3] = str2;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endPrefixMapping(String str) {
        this.nsLen -= 2;
    }

    private String resolveNamespacePrefix(String str) {
        if (str.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        for (int i = this.nsLen - 2; i >= 0; i -= 2) {
            if (str.equals(this.nsBind[i])) {
                return this.nsBind[i + 1];
            }
        }
        NamespaceContext namespaceContext = this.environmentNamespaceContext;
        if (namespaceContext != null) {
            return namespaceContext.getNamespaceURI(str.intern());
        }
        if (str.equals("")) {
            return "";
        }
        return null;
    }

    public String[] getNewlyDeclaredPrefixes() {
        return getPrefixList(this.current.prev.numNsDecl);
    }

    public String[] getAllDeclaredPrefixes() {
        return getPrefixList(0);
    }

    private String[] getPrefixList(int i) {
        int i2 = (this.current.numNsDecl - i) / 2;
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr[i3] = this.nsBind[(i3 * 2) + i];
        }
        return strArr;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public Iterator<String> getPrefixes(String str) {
        return Collections.unmodifiableList(getAllPrefixesInList(str)).iterator();
    }

    private List<String> getAllPrefixesInList(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            arrayList.add("xml");
            return arrayList;
        }
        if (str.equals("http://www.w3.org/2000/xmlns/")) {
            arrayList.add("xmlns");
            return arrayList;
        }
        for (int i = this.nsLen - 2; i >= 0; i -= 2) {
            int i2 = i + 1;
            if (str.equals(this.nsBind[i2]) && getNamespaceURI(this.nsBind[i]).equals(this.nsBind[i2])) {
                arrayList.add(this.nsBind[i]);
            }
        }
        return arrayList;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getPrefix(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            return "xml";
        }
        if (str.equals("http://www.w3.org/2000/xmlns/")) {
            return "xmlns";
        }
        for (int i = this.nsLen - 2; i >= 0; i -= 2) {
            int i2 = i + 1;
            if (str.equals(this.nsBind[i2]) && getNamespaceURI(this.nsBind[i]).equals(this.nsBind[i2])) {
                return this.nsBind[i];
            }
        }
        NamespaceContext namespaceContext = this.environmentNamespaceContext;
        if (namespaceContext != null) {
            return namespaceContext.getPrefix(str);
        }
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getNamespaceURI(String str) {
        if (str != null) {
            return str.equals("xmlns") ? "http://www.w3.org/2000/xmlns/" : resolveNamespacePrefix(str);
        }
        throw new IllegalArgumentException();
    }

    public void startScope(int i) {
        int i2 = this.scopeTop + i;
        this.scopeTop = i2;
        Scope[] scopeArr = this.scopes;
        if (i2 >= scopeArr.length) {
            int iMax = Math.max(i2 + 1, scopeArr.length * 2);
            Scope[] scopeArr2 = new Scope[iMax];
            Scope[] scopeArr3 = this.scopes;
            System.arraycopy(scopeArr3, 0, scopeArr2, 0, scopeArr3.length);
            for (int length = this.scopes.length; length < iMax; length++) {
                scopeArr2[length] = new Scope(this);
            }
            this.scopes = scopeArr2;
        }
    }

    public void endScope(int i) throws SAXException {
        while (i > 0) {
            try {
                this.scopes[this.scopeTop].finish();
                i--;
                this.scopeTop--;
            } catch (AccessorException e) {
                handleError(e);
                while (i > 0) {
                    Scope[] scopeArr = this.scopes;
                    int i2 = this.scopeTop;
                    this.scopeTop = i2 - 1;
                    scopeArr[i2] = new Scope(this);
                    i--;
                }
                return;
            }
        }
    }

    public Scope getScope(int i) {
        return this.scopes[this.scopeTop - i];
    }

    private static final class DefaultRootLoader extends Loader implements Receiver {
        private DefaultRootLoader() {
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void childElement(State state, TagName tagName) throws SAXException {
            Loader loaderSelectRootLoader = state.getContext().selectRootLoader(state, tagName);
            if (loaderSelectRootLoader != null) {
                state.loader = loaderSelectRootLoader;
                state.receiver = this;
                return;
            }
            JaxBeanInfo xsiType = XsiTypeLoader.parseXsiType(state, tagName, null);
            if (xsiType != null) {
                state.loader = xsiType.getLoader(null, false);
                state.prev.backup = new JAXBElement(tagName.createQName(), Object.class, null);
                state.receiver = this;
                return;
            }
            reportUnexpectedChildElement(tagName, false);
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public Collection<QName> getExpectedChildElements() {
            return UnmarshallingContext.getInstance().getJAXBContext().getValidRootNames();
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Receiver
        public void receive(State state, Object obj) {
            if (state.backup != null) {
                ((JAXBElement) state.backup).setValue(obj);
                obj = state.backup;
            }
            if (state.nil) {
                ((JAXBElement) obj).setNil(true);
            }
            state.getContext().result = obj;
        }
    }

    private static final class ExpectedTypeRootLoader extends Loader implements Receiver {
        private ExpectedTypeRootLoader() {
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void childElement(State state, TagName tagName) {
            UnmarshallingContext context = state.getContext();
            QName qName = new QName(tagName.uri, tagName.local);
            state.prev.target = new JAXBElement(qName, context.expectedType.jaxbType, null, null);
            state.receiver = this;
            state.loader = new XsiNilLoader(context.expectedType.getLoader(null, true));
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Receiver
        public void receive(State state, Object obj) {
            JAXBElement jAXBElement = (JAXBElement) state.target;
            jAXBElement.setValue(obj);
            state.getContext().recordOuterPeer(jAXBElement);
            state.getContext().result = jAXBElement;
        }
    }

    public void recordInnerPeer(Object obj) {
        AssociationMap associationMap = this.assoc;
        if (associationMap != null) {
            associationMap.addInner(this.currentElement, obj);
        }
    }

    public Object getInnerPeer() {
        AssociationMap associationMap = this.assoc;
        if (associationMap == null || !this.isInplaceMode) {
            return null;
        }
        return associationMap.getInnerPeer(this.currentElement);
    }

    public void recordOuterPeer(Object obj) {
        AssociationMap associationMap = this.assoc;
        if (associationMap != null) {
            associationMap.addOuter(this.currentElement, obj);
        }
    }

    public Object getOuterPeer() {
        AssociationMap associationMap = this.assoc;
        if (associationMap == null || !this.isInplaceMode) {
            return null;
        }
        return associationMap.getOuterPeer(this.currentElement);
    }

    public String getXMIMEContentType() {
        Object obj = this.current.target;
        if (obj == null) {
            return null;
        }
        return getJAXBContext().getXMIMEContentType(obj);
    }

    public static UnmarshallingContext getInstance() {
        return (UnmarshallingContext) Coordinator._getInstance();
    }

    public Collection<QName> getCurrentExpectedElements() {
        pushCoordinator();
        try {
            Loader loader = getCurrentState().loader;
            return loader != null ? loader.getExpectedChildElements() : null;
        } finally {
            popCoordinator();
        }
    }

    public Collection<QName> getCurrentExpectedAttributes() {
        pushCoordinator();
        try {
            Loader loader = getCurrentState().loader;
            return loader != null ? loader.getExpectedAttributes() : null;
        } finally {
            popCoordinator();
        }
    }

    public StructureLoader getStructureLoader() {
        if (this.current.loader instanceof StructureLoader) {
            return (StructureLoader) this.current.loader;
        }
        return null;
    }

    public boolean shouldErrorBeReported() throws SAXException {
        if (logger.isLoggable(Level.FINEST)) {
            return true;
        }
        if (errorsCounter >= 0) {
            errorsCounter--;
            if (errorsCounter == 0) {
                handleEvent(new ValidationEventImpl(0, Messages.ERRORS_LIMIT_EXCEEDED.format(new Object[0]), getLocator().getLocation(), null), true);
            }
        }
        return errorsCounter >= 0;
    }
}
