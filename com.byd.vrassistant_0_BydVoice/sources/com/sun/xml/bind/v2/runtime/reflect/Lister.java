package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.istack.SAXException2;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.TODO;
import com.sun.xml.bind.v2.model.core.Adapter;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx;
import com.sun.xml.bind.v2.runtime.unmarshaller.Patcher;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Lister<BeanT, PropT, ItemT, PackT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Class[] COLLECTION_IMPL_CLASSES;
    private static final ListIterator EMPTY_ITERATOR;
    public static final Lister ERROR;
    private static final Map<Class, WeakReference<Lister>> arrayListerCache = Collections.synchronizedMap(new WeakHashMap());
    static final Map<Class, Lister> primitiveArrayListers = new HashMap();

    public abstract void addToPack(PackT packt, ItemT itemt) throws AccessorException;

    public abstract void endPacking(PackT packt, BeanT beant, Accessor<BeanT, PropT> accessor) throws AccessorException;

    public abstract ListIterator<ItemT> iterator(PropT propt, XMLSerializer xMLSerializer);

    public abstract void reset(BeanT beant, Accessor<BeanT, PropT> accessor) throws AccessorException;

    public abstract PackT startPacking(BeanT beant, Accessor<BeanT, PropT> accessor) throws AccessorException;

    protected Lister() {
    }

    public static <BeanT, PropT, ItemT, PackT> Lister<BeanT, PropT, ItemT, PackT> create(Type type, ID id, Adapter<Type, Class> adapter) {
        Class<?> componentType;
        Lister<BeanT, PropT, ItemT, PackT> collectionLister;
        Class cls = (Class) Utils.REFLECTION_NAVIGATOR.erasure(type);
        if (cls.isArray()) {
            componentType = cls.getComponentType();
            collectionLister = getArrayLister(componentType);
        } else {
            if (!Collection.class.isAssignableFrom(cls)) {
                return null;
            }
            Type baseClass = Utils.REFLECTION_NAVIGATOR.getBaseClass(type, Collection.class);
            if (baseClass instanceof ParameterizedType) {
                componentType = (Class) Utils.REFLECTION_NAVIGATOR.erasure(((ParameterizedType) baseClass).getActualTypeArguments()[0]);
            } else {
                componentType = Object.class;
            }
            collectionLister = new CollectionLister(getImplClass(cls));
        }
        if (id == ID.IDREF) {
            collectionLister = new IDREFS(collectionLister, componentType);
        }
        return adapter != null ? new AdaptedLister(collectionLister, adapter.adapterType) : collectionLister;
    }

    private static Class getImplClass(Class<?> cls) {
        return ClassFactory.inferImplClass(cls, COLLECTION_IMPL_CLASSES);
    }

    static {
        PrimitiveArrayListerBoolean.register();
        PrimitiveArrayListerByte.register();
        PrimitiveArrayListerCharacter.register();
        PrimitiveArrayListerDouble.register();
        PrimitiveArrayListerFloat.register();
        PrimitiveArrayListerInteger.register();
        PrimitiveArrayListerLong.register();
        PrimitiveArrayListerShort.register();
        ERROR = new Lister() { // from class: com.sun.xml.bind.v2.runtime.reflect.Lister.1
            @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
            public void addToPack(Object obj, Object obj2) {
            }

            @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
            public void endPacking(Object obj, Object obj2, Accessor accessor) {
            }

            @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
            public void reset(Object obj, Accessor accessor) {
            }

            @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
            public Object startPacking(Object obj, Accessor accessor) {
                return null;
            }

            @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
            public ListIterator iterator(Object obj, XMLSerializer xMLSerializer) {
                return Lister.EMPTY_ITERATOR;
            }
        };
        EMPTY_ITERATOR = new ListIterator() { // from class: com.sun.xml.bind.v2.runtime.reflect.Lister.2
            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return false;
            }

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Object next() {
                throw new IllegalStateException();
            }
        };
        COLLECTION_IMPL_CLASSES = new Class[]{ArrayList.class, LinkedList.class, HashSet.class, TreeSet.class, Stack.class};
    }

    private static Lister getArrayLister(Class cls) {
        if (cls.isPrimitive()) {
            return primitiveArrayListers.get(cls);
        }
        Map<Class, WeakReference<Lister>> map = arrayListerCache;
        WeakReference<Lister> weakReference = map.get(cls);
        Lister arrayLister = weakReference != null ? weakReference.get() : null;
        if (arrayLister == null) {
            arrayLister = new ArrayLister(cls);
            map.put(cls, new WeakReference<>(arrayLister));
        }
        return arrayLister;
    }

    private static final class ArrayLister<BeanT, ItemT> extends Lister<BeanT, ItemT[], ItemT, Pack<ItemT>> {
        private final Class<ItemT> itemType;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public /* bridge */ /* synthetic */ void endPacking(Object obj, Object obj2, Accessor accessor) throws AccessorException {
            endPacking((Pack) obj, obj2, (Accessor<Object, ItemT[]>) accessor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public /* bridge */ /* synthetic */ Object startPacking(Object obj, Accessor accessor) throws AccessorException {
            return startPacking(obj, (Accessor<Object, ItemT[]>) accessor);
        }

        public ArrayLister(Class<ItemT> cls) {
            this.itemType = cls;
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public ListIterator<ItemT> iterator(final ItemT[] itemtArr, XMLSerializer xMLSerializer) {
            return new ListIterator<ItemT>() { // from class: com.sun.xml.bind.v2.runtime.reflect.Lister.ArrayLister.1
                int idx = 0;

                @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
                public boolean hasNext() {
                    return this.idx < itemtArr.length;
                }

                @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
                public ItemT next() {
                    Object[] objArr = itemtArr;
                    int i = this.idx;
                    this.idx = i + 1;
                    return (ItemT) objArr[i];
                }
            };
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public Pack startPacking(BeanT beant, Accessor<BeanT, ItemT[]> accessor) {
            return new Pack(this.itemType);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public void addToPack(Pack<ItemT> pack, ItemT itemt) {
            pack.add(itemt);
        }

        public void endPacking(Pack<ItemT> pack, BeanT beant, Accessor<BeanT, ItemT[]> accessor) throws AccessorException {
            accessor.set(beant, pack.build());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public void reset(BeanT beant, Accessor<BeanT, ItemT[]> accessor) throws AccessorException {
            accessor.set(beant, (Object[]) Array.newInstance((Class<?>) this.itemType, 0));
        }
    }

    public static final class Pack<ItemT> extends ArrayList<ItemT> {
        private final Class<ItemT> itemType;

        public Pack(Class<ItemT> cls) {
            this.itemType = cls;
        }

        public ItemT[] build() {
            return (ItemT[]) super.toArray((Object[]) Array.newInstance((Class<?>) this.itemType, size()));
        }
    }

    public static final class CollectionLister<BeanT, T extends Collection> extends Lister<BeanT, T, Object, T> {
        private final Class<? extends T> implClass;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public /* bridge */ /* synthetic */ void endPacking(Object obj, Object obj2, Accessor accessor) throws AccessorException {
            endPacking((Collection) obj, obj2, (Accessor<Object, Collection>) accessor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public /* bridge */ /* synthetic */ Object startPacking(Object obj, Accessor accessor) throws AccessorException {
            return startPacking(obj, (Accessor<Object, T>) accessor);
        }

        public CollectionLister(Class<? extends T> cls) {
            this.implClass = cls;
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public ListIterator iterator(T t, XMLSerializer xMLSerializer) {
            final Iterator it = t.iterator();
            return new ListIterator() { // from class: com.sun.xml.bind.v2.runtime.reflect.Lister.CollectionLister.1
                @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
                public Object next() {
                    return it.next();
                }
            };
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public T startPacking(BeanT beant, Accessor<BeanT, T> accessor) throws AccessorException {
            T t = accessor.get(beant);
            if (t == null) {
                t = (T) ClassFactory.create(this.implClass);
                if (!accessor.isAdapted()) {
                    accessor.set(beant, t);
                }
            }
            t.clear();
            return t;
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public void addToPack(T t, Object obj) {
            t.add(obj);
        }

        public void endPacking(T t, BeanT beant, Accessor<BeanT, T> accessor) throws AccessorException {
            try {
                if (accessor.isAdapted()) {
                    accessor.set(beant, t);
                }
            } catch (AccessorException e) {
                if (accessor.isAdapted()) {
                    throw e;
                }
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public void reset(BeanT beant, Accessor<BeanT, T> accessor) throws AccessorException {
            T t = accessor.get(beant);
            if (t == null) {
                return;
            }
            t.clear();
        }
    }

    private static final class IDREFS<BeanT, PropT> extends Lister<BeanT, PropT, String, IDREFS<BeanT, PropT>.Pack> {
        private final Lister<BeanT, PropT, Object, Object> core;
        private final Class itemType;

        public void endPacking(IDREFS<BeanT, PropT>.Pack pack, BeanT beant, Accessor<BeanT, PropT> accessor) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public /* bridge */ /* synthetic */ void endPacking(Object obj, Object obj2, Accessor accessor) throws AccessorException {
            endPacking((IDREFS<Object, PropT>.Pack) obj, obj2, (Accessor<Object, PropT>) accessor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public /* bridge */ /* synthetic */ Object startPacking(Object obj, Accessor accessor) throws AccessorException {
            return startPacking(obj, (Accessor<Object, PropT>) accessor);
        }

        public IDREFS(Lister lister, Class cls) {
            this.core = lister;
            this.itemType = cls;
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public ListIterator<String> iterator(PropT propt, XMLSerializer xMLSerializer) {
            return new IDREFSIterator(this.core.iterator(propt, xMLSerializer), xMLSerializer);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public IDREFS<BeanT, PropT>.Pack startPacking(BeanT beant, Accessor<BeanT, PropT> accessor) {
            return new Pack(beant, accessor);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public void addToPack(IDREFS<BeanT, PropT>.Pack pack, String str) {
            pack.add(str);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
        public void reset(BeanT beant, Accessor<BeanT, PropT> accessor) throws AccessorException {
            this.core.reset(beant, accessor);
        }

        private class Pack implements Patcher {
            private final Accessor<BeanT, PropT> acc;
            private final BeanT bean;
            private final UnmarshallingContext context;
            private final List<String> idrefs = new ArrayList();
            private final LocatorEx location;

            public Pack(BeanT beant, Accessor<BeanT, PropT> accessor) {
                this.bean = beant;
                this.acc = accessor;
                UnmarshallingContext unmarshallingContext = UnmarshallingContext.getInstance();
                this.context = unmarshallingContext;
                this.location = new LocatorEx.Snapshot(unmarshallingContext.getLocator());
                unmarshallingContext.addPatcher(this);
            }

            public void add(String str) {
                this.idrefs.add(str);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Patcher
            public void run() throws SAXException {
                Object objCall;
                try {
                    Object objStartPacking = IDREFS.this.core.startPacking(this.bean, this.acc);
                    for (String str : this.idrefs) {
                        Callable objectFromId = this.context.getObjectFromId(str, IDREFS.this.itemType);
                        if (objectFromId != null) {
                            try {
                                try {
                                    objCall = objectFromId.call();
                                } catch (Exception e) {
                                    throw new SAXException2(e);
                                }
                            } catch (SAXException e2) {
                                throw e2;
                            }
                        } else {
                            objCall = null;
                        }
                        if (objCall == null) {
                            this.context.errorUnresolvedIDREF(this.bean, str, this.location);
                        } else {
                            TODO.prototype();
                            IDREFS.this.core.addToPack(objStartPacking, objCall);
                        }
                    }
                    IDREFS.this.core.endPacking(objStartPacking, this.bean, this.acc);
                } catch (AccessorException e3) {
                    this.context.handleError(e3);
                }
            }
        }
    }

    public static final class IDREFSIterator implements ListIterator<String> {
        private final XMLSerializer context;
        private final ListIterator i;
        private Object last;

        private IDREFSIterator(ListIterator listIterator, XMLSerializer xMLSerializer) {
            this.i = listIterator;
            this.context = xMLSerializer;
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
        public boolean hasNext() {
            return this.i.hasNext();
        }

        public Object last() {
            return this.last;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
        public String next() throws SAXException, JAXBException {
            this.last = this.i.next();
            String id = this.context.grammar.getBeanInfo(this.last, true).getId(this.last, this.context);
            if (id == null) {
                this.context.errorMissingId(this.last);
            }
            return id;
        }
    }

    public static <A, B, C, D> Lister<A, B, C, D> getErrorInstance() {
        return ERROR;
    }
}
