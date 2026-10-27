package org.apache.shiro.util;

import java.util.Collection;
import java.util.Iterator;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class LifecycleUtils {
    private static final Logger log = LoggerFactory.getLogger(LifecycleUtils.class);

    public static void init(Object obj) throws ShiroException {
        if (obj instanceof Initializable) {
            init((Initializable) obj);
        }
    }

    public static void init(Initializable initializable) throws ShiroException {
        initializable.init();
    }

    public static void init(Collection collection) throws ShiroException {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            init(it.next());
        }
    }

    public static void destroy(Object obj) {
        if (obj instanceof Destroyable) {
            destroy((Destroyable) obj);
        } else if (obj instanceof Collection) {
            destroy((Collection) obj);
        }
    }

    public static void destroy(Destroyable destroyable) {
        if (destroyable != null) {
            try {
                destroyable.destroy();
            } catch (Throwable th) {
                if (log.isDebugEnabled()) {
                    log.debug("Unable to cleanly destroy instance [" + destroyable + "] of type [" + destroyable.getClass().getName() + "].", th);
                }
            }
        }
    }

    public static void destroy(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            destroy(it.next());
        }
    }
}
