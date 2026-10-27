package org.apache.shiro.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class ThreadContext {
    private static final Logger log = LoggerFactory.getLogger(ThreadContext.class);
    public static final String SECURITY_MANAGER_KEY = ThreadContext.class.getName() + "_SECURITY_MANAGER_KEY";
    public static final String SUBJECT_KEY = ThreadContext.class.getName() + "_SUBJECT_KEY";
    private static final ThreadLocal<Map<Object, Object>> resources = new InheritableThreadLocalMap();

    protected ThreadContext() {
    }

    public static Map<Object, Object> getResources() {
        ThreadLocal<Map<Object, Object>> threadLocal = resources;
        if (threadLocal.get() == null) {
            return Collections.emptyMap();
        }
        return new HashMap(threadLocal.get());
    }

    public static void setResources(Map<Object, Object> map) {
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        ensureResourcesInitialized();
        ThreadLocal<Map<Object, Object>> threadLocal = resources;
        threadLocal.get().clear();
        threadLocal.get().putAll(map);
    }

    private static Object getValue(Object obj) {
        Map<Object, Object> map = resources.get();
        if (map != null) {
            return map.get(obj);
        }
        return null;
    }

    private static void ensureResourcesInitialized() {
        ThreadLocal<Map<Object, Object>> threadLocal = resources;
        if (threadLocal.get() == null) {
            threadLocal.set(new HashMap());
        }
    }

    public static Object get(Object obj) {
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            logger.trace("get() - in thread [" + Thread.currentThread().getName() + Ini.SECTION_SUFFIX);
        }
        Object value = getValue(obj);
        if (value != null && logger.isTraceEnabled()) {
            logger.trace("Retrieved value of type [" + value.getClass().getName() + "] for key [" + obj + "] bound to thread [" + Thread.currentThread().getName() + Ini.SECTION_SUFFIX);
        }
        return value;
    }

    public static void put(Object obj, Object obj2) {
        if (obj == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (obj2 == null) {
            remove(obj);
            return;
        }
        ensureResourcesInitialized();
        resources.get().put(obj, obj2);
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            logger.trace("Bound value of type [" + obj2.getClass().getName() + "] for key [" + obj + "] to thread [" + Thread.currentThread().getName() + Ini.SECTION_SUFFIX);
        }
    }

    public static Object remove(Object obj) {
        Map<Object, Object> map = resources.get();
        Object objRemove = map != null ? map.remove(obj) : null;
        if (objRemove != null) {
            Logger logger = log;
            if (logger.isTraceEnabled()) {
                logger.trace("Removed value of type [" + objRemove.getClass().getName() + "] for key [" + obj + "]from thread [" + Thread.currentThread().getName() + Ini.SECTION_SUFFIX);
            }
        }
        return objRemove;
    }

    public static void remove() {
        resources.remove();
    }

    public static SecurityManager getSecurityManager() {
        return (SecurityManager) get(SECURITY_MANAGER_KEY);
    }

    public static void bind(SecurityManager securityManager) {
        if (securityManager != null) {
            put(SECURITY_MANAGER_KEY, securityManager);
        }
    }

    public static SecurityManager unbindSecurityManager() {
        return (SecurityManager) remove(SECURITY_MANAGER_KEY);
    }

    public static Subject getSubject() {
        return (Subject) get(SUBJECT_KEY);
    }

    public static void bind(Subject subject) {
        if (subject != null) {
            put(SUBJECT_KEY, subject);
        }
    }

    public static Subject unbindSubject() {
        return (Subject) remove(SUBJECT_KEY);
    }

    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {
        private InheritableThreadLocalMap() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.InheritableThreadLocal
        public Map<Object, Object> childValue(Map<Object, Object> map) {
            if (map != null) {
                return (Map) ((HashMap) map).clone();
            }
            return null;
        }
    }
}
