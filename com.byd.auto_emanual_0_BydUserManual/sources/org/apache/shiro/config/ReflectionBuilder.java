package org.apache.shiro.config;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.event.ConfiguredBeanEvent;
import org.apache.shiro.config.event.DestroyedBeanEvent;
import org.apache.shiro.config.event.InitializedBeanEvent;
import org.apache.shiro.config.event.InstantiatedBeanEvent;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.EventBusAware;
import org.apache.shiro.event.Subscribe;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.util.Assert;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.LifecycleUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class ReflectionBuilder {
    private static final String EMPTY_STRING_VALUE_TOKEN = "\"\"";
    private static final String ESCAPED_OBJECT_REFERENCE_BEGIN_TOKEN = "\\$";
    private static final String EVENT_BUS_NAME = "eventBus";
    private static final String GLOBAL_PROPERTY_PREFIX = "shiro";
    private static final String HEX_BEGIN_TOKEN = "0x";
    private static final char MAP_KEY_VALUE_DELIMITER = ':';
    private static final char MAP_PROPERTY_BEGIN_TOKEN = '[';
    private static final char MAP_PROPERTY_END_TOKEN = ']';
    private static final String NULL_VALUE_TOKEN = "null";
    private static final String OBJECT_REFERENCE_BEGIN_TOKEN = "$";
    private static final char STRING_VALUE_DELIMETER = '\"';
    private static final Logger log = LoggerFactory.getLogger(ReflectionBuilder.class);
    private final BeanUtilsBean beanUtilsBean;
    private EventBus eventBus;
    private Interpolator interpolator;
    private final Map<String, Object> objects;
    private final Map<String, Object> registeredEventSubscribers;

    private Map<String, Object> createDefaultObjectMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(EVENT_BUS_NAME, new DefaultEventBus());
        return linkedHashMap;
    }

    public ReflectionBuilder() {
        this(null);
    }

    public ReflectionBuilder(Map<String, ?> map) {
        BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean() { // from class: org.apache.shiro.config.ReflectionBuilder.1
            public Object convert(String str, Class cls) {
                if (cls.isEnum()) {
                    return Enum.valueOf(cls, str);
                }
                return super.convert(str, cls);
            }
        });
        this.beanUtilsBean = beanUtilsBean;
        beanUtilsBean.getPropertyUtils().addBeanIntrospector(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
        this.interpolator = createInterpolator();
        this.objects = createDefaultObjectMap();
        this.registeredEventSubscribers = new LinkedHashMap();
        apply(map);
    }

    private void apply(Map<String, ?> map) {
        if (!isEmpty(map)) {
            this.objects.putAll(map);
        }
        EventBus eventBusFindEventBus = findEventBus(this.objects);
        Assert.notNull(eventBusFindEventBus, "An " + EventBus.class.getName() + " instance must be present in the object defaults");
        enableEvents(eventBusFindEventBus);
    }

    public Map<String, ?> getObjects() {
        return this.objects;
    }

    public void setObjects(Map<String, ?> map) {
        this.objects.clear();
        this.objects.putAll(createDefaultObjectMap());
        apply(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableEvents(EventBus eventBus) {
        Assert.notNull(eventBus, "EventBus argument cannot be null.");
        Iterator<Object> it = this.registeredEventSubscribers.values().iterator();
        while (it.hasNext()) {
            this.eventBus.unregister(it.next());
        }
        this.registeredEventSubscribers.clear();
        this.eventBus = eventBus;
        for (Map.Entry<String, Object> entry : this.objects.entrySet()) {
            enableEventsIfNecessary(entry.getValue(), entry.getKey());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableEventsIfNecessary(Object obj, String str) {
        if (applyEventBusIfNecessary(obj) || !isEventSubscriber(obj, str)) {
            return;
        }
        this.eventBus.register(obj);
        this.registeredEventSubscribers.put(str, obj);
    }

    private boolean isEventSubscriber(Object obj, String str) {
        return !isEmpty(ClassUtils.getAnnotatedMethods(obj.getClass(), Subscribe.class));
    }

    protected EventBus findEventBus(Map<String, ?> map) {
        if (isEmpty(map)) {
            return null;
        }
        Object obj = map.get(EVENT_BUS_NAME);
        if (obj != null && (obj instanceof EventBus)) {
            return (EventBus) obj;
        }
        for (Object obj2 : map.values()) {
            if (obj2 instanceof EventBus) {
                return (EventBus) obj2;
            }
        }
        return null;
    }

    private boolean applyEventBusIfNecessary(Object obj) {
        if (!(obj instanceof EventBusAware)) {
            return false;
        }
        ((EventBusAware) obj).setEventBus(this.eventBus);
        return true;
    }

    public Object getBean(String str) {
        return this.objects.get(str);
    }

    public <T> T getBean(String str, Class<T> cls) {
        Objects.requireNonNull(cls, "requiredType argument cannot be null.");
        T t = (T) getBean(str);
        if (t == null) {
            return null;
        }
        Assert.state(cls.isAssignableFrom(t.getClass()), "Bean with id [" + str + "] is not of the required type [" + cls.getName() + "].");
        return t;
    }

    private String parseBeanId(String str) {
        Assert.notNull(str);
        if (str.indexOf(46) < 0) {
            return str;
        }
        int iIndexOf = str.indexOf(".class");
        if (iIndexOf >= 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public Map<String, ?> buildObjects(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            BeanConfigurationProcessor beanConfigurationProcessor = new BeanConfigurationProcessor();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String strInterpolate = this.interpolator.interpolate(entry.getValue());
                String beanId = parseBeanId(key);
                if (beanId != null) {
                    beanConfigurationProcessor.add(new InstantiationStatement(beanId, strInterpolate));
                } else {
                    beanConfigurationProcessor.add(new AssignmentStatement(key, strInterpolate));
                }
            }
            beanConfigurationProcessor.execute();
        }
        LifecycleUtils.init((Collection) this.objects.values());
        return this.objects;
    }

    public void destroy() {
        Map mapUnmodifiableMap = Collections.unmodifiableMap(this.objects);
        ArrayList<Map.Entry> arrayList = new ArrayList(this.objects.entrySet());
        Collections.reverse(arrayList);
        for (Map.Entry entry : arrayList) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value != this.eventBus) {
                LifecycleUtils.destroy(value);
                this.eventBus.publish(new DestroyedBeanEvent(str, value, mapUnmodifiableMap));
                this.eventBus.unregister(value);
            }
        }
        LifecycleUtils.destroy(this.eventBus);
    }

    protected void createNewInstance(Map<String, Object> map, String str, String str2) {
        if (map.get(str) != null) {
            log.info("An instance with name '{}' already exists.  Redefining this object as a new instance of type {}", str, str2);
        }
        try {
            Object objNewInstance = ClassUtils.newInstance(str2);
            if (objNewInstance instanceof Nameable) {
                ((Nameable) objNewInstance).setName(str);
            }
            map.put(str, objNewInstance);
        } catch (Exception e) {
            throw new ConfigurationException("Unable to instantiate class [" + str2 + "] for object named '" + str + "'.  Please ensure you've specified the fully qualified class name correctly.", e);
        }
    }

    protected void applyProperty(String str, String str2, Map map) {
        int iIndexOf = str.indexOf(46);
        if (iIndexOf >= 0) {
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1, str.length());
            if (GLOBAL_PROPERTY_PREFIX.equalsIgnoreCase(strSubstring)) {
                applyGlobalProperty(map, strSubstring2, str2);
                return;
            } else {
                applySingleProperty(map, strSubstring, strSubstring2, str2);
                return;
            }
        }
        throw new IllegalArgumentException("All property keys must contain a '.' character. (e.g. myBean.property = value)  These should already be separated out by buildObjects().");
    }

    protected void applyGlobalProperty(Map map, String str, String str2) {
        for (Object obj : map.values()) {
            try {
                if (this.beanUtilsBean.getPropertyUtils().getPropertyDescriptor(obj, str) != null) {
                    applyProperty(obj, str, str2);
                }
            } catch (Exception e) {
                throw new ConfigurationException("Error retrieving property descriptor for instance of type [" + obj.getClass().getName() + "] while setting property [" + str + Ini.SECTION_SUFFIX, e);
            }
        }
    }

    protected void applySingleProperty(Map map, String str, String str2, String str3) {
        Object obj = map.get(str);
        if (str2.equals("class")) {
            throw new IllegalArgumentException("Property keys should not contain 'class' properties since these should already be separated out by buildObjects().");
        }
        if (obj == null) {
            throw new IllegalArgumentException("Configuration error.  Specified object [" + str + "] with property [" + str2 + "] without first defining that object's class.  Please first specify the class property first, e.g. myObject = fully_qualified_class_name and then define additional properties.");
        }
        applyProperty(obj, str2, str3);
    }

    protected boolean isReference(String str) {
        return str != null && str.startsWith("$");
    }

    protected String getId(String str) {
        return str.substring(1);
    }

    protected Object getReferencedObject(String str) {
        Map<String, Object> map = this.objects;
        Object obj = (map == null || map.isEmpty()) ? null : this.objects.get(str);
        if (obj != null) {
            return obj;
        }
        throw new UnresolveableReferenceException("The object with id [" + str + "] has not yet been defined and therefore cannot be referenced.  Please ensure objects are defined in the order in which they should be created and made available for future reference.");
    }

    protected String unescapeIfNecessary(String str) {
        return (str == null || !str.startsWith(ESCAPED_OBJECT_REFERENCE_BEGIN_TOKEN)) ? str : str.substring(1);
    }

    protected Object resolveReference(String str) {
        String id = getId(str);
        log.debug("Encountered object reference '{}'.  Looking up object with id '{}'", str, id);
        Object referencedObject = getReferencedObject(id);
        return referencedObject instanceof Factory ? ((Factory) referencedObject).getInstance() : referencedObject;
    }

    protected boolean isTypedProperty(Object obj, String str, Class cls) {
        Objects.requireNonNull(cls, "type (class) argument cannot be null.");
        try {
            PropertyDescriptor propertyDescriptor = this.beanUtilsBean.getPropertyUtils().getPropertyDescriptor(obj, str);
            if (propertyDescriptor == null) {
                throw new ConfigurationException("Property '" + str + "' does not exist for object of type " + obj.getClass().getName() + ".");
            }
            return cls.isAssignableFrom(propertyDescriptor.getPropertyType());
        } catch (ConfigurationException e) {
            throw e;
        } catch (Exception e2) {
            throw new ConfigurationException("Unable to determine if property [" + str + "] represents a " + cls.getName(), e2);
        }
    }

    protected Set<?> toSet(String str) {
        String[] strArrSplit = StringUtils.split(str);
        if (strArrSplit == null || strArrSplit.length <= 0) {
            return null;
        }
        if (strArrSplit.length == 1 && isReference(strArrSplit[0])) {
            Object objResolveReference = resolveReference(strArrSplit[0]);
            if (objResolveReference instanceof Set) {
                return (Set) objResolveReference;
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(strArrSplit));
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(linkedHashSet.size());
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            linkedHashSet2.add(resolveValue((String) it.next()));
        }
        return linkedHashSet2;
    }

    protected Map<?, ?> toMap(String str) {
        String[] strArrSplit = StringUtils.split(str, StringUtils.DEFAULT_DELIMITER_CHAR, '\"', '\"', true, true);
        if (strArrSplit == null || strArrSplit.length <= 0) {
            return null;
        }
        if (strArrSplit.length == 1 && isReference(strArrSplit[0])) {
            Object objResolveReference = resolveReference(strArrSplit[0]);
            if (objResolveReference instanceof Map) {
                return (Map) objResolveReference;
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(strArrSplit.length);
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = StringUtils.split(str2, MAP_KEY_VALUE_DELIMITER);
            if (strArrSplit2 == null || strArrSplit2.length != 2) {
                throw new ConfigurationException("Map property value [" + str + "] contained key-value pair token [" + str2 + "] that does not properly split to a single key and pair.  This must be the case for all map entries.");
            }
            linkedHashMap.put(strArrSplit2[0], strArrSplit2[1]);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            linkedHashMap2.put(resolveValue((String) entry.getKey()), resolveValue((String) entry.getValue()));
        }
        return linkedHashMap2;
    }

    protected Collection<?> toCollection(String str) {
        String[] strArrSplit = StringUtils.split(str);
        if (strArrSplit == null || strArrSplit.length <= 0) {
            return null;
        }
        if (strArrSplit.length == 1 && isReference(strArrSplit[0])) {
            Object objResolveReference = resolveReference(strArrSplit[0]);
            if (objResolveReference instanceof Collection) {
                return (Collection) objResolveReference;
            }
        }
        ArrayList arrayList = new ArrayList(strArrSplit.length);
        for (String str2 : strArrSplit) {
            arrayList.add(resolveValue(str2));
        }
        return arrayList;
    }

    protected List<?> toList(String str) {
        String[] strArrSplit = StringUtils.split(str);
        if (strArrSplit == null || strArrSplit.length <= 0) {
            return null;
        }
        if (strArrSplit.length == 1 && isReference(strArrSplit[0])) {
            Object objResolveReference = resolveReference(strArrSplit[0]);
            if (objResolveReference instanceof List) {
                return (List) objResolveReference;
            }
        }
        ArrayList arrayList = new ArrayList(strArrSplit.length);
        for (String str2 : strArrSplit) {
            arrayList.add(resolveValue(str2));
        }
        return arrayList;
    }

    protected byte[] toBytes(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(HEX_BEGIN_TOKEN)) {
            return Hex.decode(str.substring(2));
        }
        return Base64.decode(str);
    }

    protected Object resolveValue(String str) {
        if (isReference(str)) {
            return resolveReference(str);
        }
        return unescapeIfNecessary(str);
    }

    protected String checkForNullOrEmptyLiteral(String str) {
        if (str == null) {
            return null;
        }
        return str.equals("\"null\"") ? NULL_VALUE_TOKEN : str.equals("\"\"\"\"") ? EMPTY_STRING_VALUE_TOKEN : str;
    }

    protected void applyProperty(Object obj, String str, Object obj2) {
        String strSubstring;
        String strSubstring2;
        Object indexedProperty;
        int iIndexOf = str.indexOf(91);
        String strSubstring3 = null;
        if (iIndexOf >= 0) {
            strSubstring2 = str.substring(0, iIndexOf);
            int iIndexOf2 = str.indexOf(93, iIndexOf);
            strSubstring = str.substring(iIndexOf + 1, iIndexOf2);
            int i = iIndexOf2 + 1;
            if (str.length() > i) {
                strSubstring3 = str.substring(i);
                if (strSubstring3.startsWith(".")) {
                    strSubstring3 = StringUtils.clean(strSubstring3.substring(1));
                }
            }
        } else {
            strSubstring = null;
            strSubstring2 = null;
        }
        if (strSubstring3 != null) {
            if (isTypedProperty(obj, strSubstring2, Map.class)) {
                indexedProperty = ((Map) getProperty(obj, strSubstring2)).get(resolveValue(strSubstring));
            } else {
                indexedProperty = getIndexedProperty(obj, strSubstring2, Integer.valueOf(strSubstring).intValue());
            }
            if (indexedProperty == null) {
                throw new ConfigurationException("Referenced map/array value '" + strSubstring2 + Ini.SECTION_PREFIX + strSubstring + "]' does not exist.");
            }
            applyProperty(indexedProperty, strSubstring3, obj2);
            return;
        }
        if (strSubstring == null) {
            setProperty(obj, str, obj2);
        } else if (isTypedProperty(obj, strSubstring2, Map.class)) {
            ((Map) getProperty(obj, strSubstring2)).put(resolveValue(strSubstring), obj2);
        } else {
            setIndexedProperty(obj, strSubstring2, Integer.valueOf(strSubstring).intValue(), obj2);
        }
    }

    private void setProperty(Object obj, String str, Object obj2) {
        try {
            Logger logger = log;
            if (logger.isTraceEnabled()) {
                logger.trace("Applying property [{}] value [{}] on object of type [{}]", new Object[]{str, obj2, obj.getClass().getName()});
            }
            this.beanUtilsBean.setProperty(obj, str, obj2);
        } catch (Exception e) {
            throw new ConfigurationException("Unable to set property '" + str + "' with value [" + obj2 + "] on object of type " + (obj != null ? obj.getClass().getName() : null) + ".  If '" + obj2 + "' is a reference to another (previously defined) object, prefix it with '$' to indicate that the referenced object should be used as the actual value.  For example, $" + obj2, e);
        }
    }

    private Object getProperty(Object obj, String str) {
        try {
            return this.beanUtilsBean.getPropertyUtils().getProperty(obj, str);
        } catch (Exception e) {
            throw new ConfigurationException("Unable to access property '" + str + "'", e);
        }
    }

    private void setIndexedProperty(Object obj, String str, int i, Object obj2) {
        try {
            this.beanUtilsBean.getPropertyUtils().setIndexedProperty(obj, str, i, obj2);
        } catch (Exception e) {
            throw new ConfigurationException("Unable to set array property '" + str + "'", e);
        }
    }

    private Object getIndexedProperty(Object obj, String str, int i) {
        try {
            return this.beanUtilsBean.getPropertyUtils().getIndexedProperty(obj, str, i);
        } catch (Exception e) {
            throw new ConfigurationException("Unable to acquire array property '" + str + "'", e);
        }
    }

    protected boolean isIndexedPropertyAssignment(String str) {
        return str.endsWith(Ini.SECTION_SUFFIX);
    }

    protected void applyProperty(Object obj, String str, String str2) {
        Object objResolveValue;
        if (NULL_VALUE_TOKEN.equals(str2)) {
            objResolveValue = null;
        } else if (EMPTY_STRING_VALUE_TOKEN.equals(str2)) {
            objResolveValue = "";
        } else if (isIndexedPropertyAssignment(str)) {
            objResolveValue = resolveValue(checkForNullOrEmptyLiteral(str2));
        } else if (isTypedProperty(obj, str, Set.class)) {
            objResolveValue = toSet(str2);
        } else if (isTypedProperty(obj, str, Map.class)) {
            objResolveValue = toMap(str2);
        } else if (isTypedProperty(obj, str, List.class)) {
            objResolveValue = toList(str2);
        } else if (isTypedProperty(obj, str, Collection.class)) {
            objResolveValue = toCollection(str2);
        } else if (isTypedProperty(obj, str, byte[].class)) {
            objResolveValue = toBytes(str2);
        } else if (isTypedProperty(obj, str, ByteSource.class)) {
            objResolveValue = ByteSource.Util.bytes(toBytes(str2));
        } else {
            objResolveValue = resolveValue(checkForNullOrEmptyLiteral(str2));
        }
        applyProperty(obj, str, objResolveValue);
    }

    private Interpolator createInterpolator() {
        if (ClassUtils.isAvailable("org.apache.commons.configuration2.interpol.ConfigurationInterpolator")) {
            return new CommonsInterpolator();
        }
        return new DefaultInterpolator();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    private class BeanConfigurationProcessor {
        private final List<BeanConfiguration> beanConfigurations;
        private final List<Statement> statements;

        private BeanConfigurationProcessor() {
            this.statements = new ArrayList();
            this.beanConfigurations = new ArrayList();
        }

        public void add(Statement statement) {
            this.statements.add(statement);
            if (statement instanceof InstantiationStatement) {
                this.beanConfigurations.add(new BeanConfiguration((InstantiationStatement) statement));
                return;
            }
            AssignmentStatement assignmentStatement = (AssignmentStatement) statement;
            boolean z = false;
            String rootBeanName = assignmentStatement.getRootBeanName();
            int size = this.beanConfigurations.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                BeanConfiguration beanConfiguration = this.beanConfigurations.get(size);
                if (rootBeanName.equals(beanConfiguration.getBeanName())) {
                    beanConfiguration.add(assignmentStatement);
                    z = true;
                    break;
                }
                size--;
            }
            if (z) {
                return;
            }
            this.beanConfigurations.add(new BeanConfiguration(assignmentStatement));
        }

        public void execute() {
            for (Statement statement : this.statements) {
                statement.execute();
                BeanConfiguration beanConfiguration = statement.getBeanConfiguration();
                if (beanConfiguration.isExecuted()) {
                    if (beanConfiguration.getBeanName().equals(ReflectionBuilder.EVENT_BUS_NAME)) {
                        ReflectionBuilder.this.enableEvents((EventBus) beanConfiguration.getBean());
                    }
                    if (!beanConfiguration.isGlobalConfig()) {
                        ReflectionBuilder.this.eventBus.publish(new ConfiguredBeanEvent(beanConfiguration.getBeanName(), beanConfiguration.getBean(), Collections.unmodifiableMap(ReflectionBuilder.this.objects)));
                    }
                    LifecycleUtils.init(beanConfiguration.getBean());
                    if (!beanConfiguration.isGlobalConfig()) {
                        ReflectionBuilder.this.eventBus.publish(new InitializedBeanEvent(beanConfiguration.getBeanName(), beanConfiguration.getBean(), Collections.unmodifiableMap(ReflectionBuilder.this.objects)));
                    }
                }
            }
        }
    }

    private class BeanConfiguration {
        private final List<AssignmentStatement> assignments;
        private Object bean;
        private final String beanName;
        private final InstantiationStatement instantiationStatement;

        private BeanConfiguration(InstantiationStatement instantiationStatement) {
            this.assignments = new ArrayList();
            instantiationStatement.setBeanConfiguration(this);
            this.instantiationStatement = instantiationStatement;
            this.beanName = instantiationStatement.lhs;
        }

        private BeanConfiguration(AssignmentStatement assignmentStatement) {
            this.assignments = new ArrayList();
            this.instantiationStatement = null;
            this.beanName = assignmentStatement.getRootBeanName();
            add(assignmentStatement);
        }

        public String getBeanName() {
            return this.beanName;
        }

        public boolean isGlobalConfig() {
            return ReflectionBuilder.GLOBAL_PROPERTY_PREFIX.equals(getBeanName());
        }

        public void add(AssignmentStatement assignmentStatement) {
            assignmentStatement.setBeanConfiguration(this);
            this.assignments.add(assignmentStatement);
        }

        public void setBean(Object obj) {
            this.bean = obj;
        }

        public Object getBean() {
            return this.bean;
        }

        public boolean isExecuted() {
            InstantiationStatement instantiationStatement = this.instantiationStatement;
            if (instantiationStatement != null && !instantiationStatement.isExecuted()) {
                return false;
            }
            Iterator<AssignmentStatement> it = this.assignments.iterator();
            while (it.hasNext()) {
                if (!it.next().isExecuted()) {
                    return false;
                }
            }
            return true;
        }
    }

    private abstract class Statement {
        protected Object bean;
        private BeanConfiguration beanConfiguration;
        private boolean executed;
        protected final String lhs;
        private Object result;
        protected final String rhs;

        protected abstract Object doExecute();

        private Statement(String str, String str2) {
            this.lhs = str;
            this.rhs = str2;
            this.executed = false;
        }

        public void setBeanConfiguration(BeanConfiguration beanConfiguration) {
            this.beanConfiguration = beanConfiguration;
        }

        public BeanConfiguration getBeanConfiguration() {
            return this.beanConfiguration;
        }

        public Object execute() {
            if (!isExecuted()) {
                this.result = doExecute();
                this.executed = true;
            }
            if (!getBeanConfiguration().isGlobalConfig()) {
                Assert.notNull(this.bean, "Implementation must set the root bean for which it executed.");
            }
            return this.result;
        }

        public Object getBean() {
            return this.bean;
        }

        protected void setBean(Object obj) {
            this.bean = obj;
            if (this.beanConfiguration.getBean() == null) {
                this.beanConfiguration.setBean(obj);
            }
        }

        public Object getResult() {
            return this.result;
        }

        public boolean isExecuted() {
            return this.executed;
        }
    }

    private class InstantiationStatement extends Statement {
        private InstantiationStatement(String str, String str2) {
            super(str, str2);
        }

        @Override // org.apache.shiro.config.ReflectionBuilder.Statement
        protected Object doExecute() {
            String str = this.lhs;
            ReflectionBuilder reflectionBuilder = ReflectionBuilder.this;
            reflectionBuilder.createNewInstance(reflectionBuilder.objects, str, this.rhs);
            Object obj = ReflectionBuilder.this.objects.get(str);
            setBean(obj);
            ReflectionBuilder.this.enableEventsIfNecessary(obj, str);
            ReflectionBuilder.this.eventBus.publish(new InstantiatedBeanEvent(str, obj, Collections.unmodifiableMap(ReflectionBuilder.this.objects)));
            return obj;
        }
    }

    private class AssignmentStatement extends Statement {
        private final String rootBeanName;

        private AssignmentStatement(String str, String str2) {
            super(str, str2);
            this.rootBeanName = str.substring(0, str.indexOf(46));
        }

        @Override // org.apache.shiro.config.ReflectionBuilder.Statement
        protected Object doExecute() {
            ReflectionBuilder.this.applyProperty(this.lhs, this.rhs, ReflectionBuilder.this.objects);
            setBean(ReflectionBuilder.this.objects.get(this.rootBeanName));
            return null;
        }

        public String getRootBeanName() {
            return this.rootBeanName;
        }
    }

    private static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    private static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
