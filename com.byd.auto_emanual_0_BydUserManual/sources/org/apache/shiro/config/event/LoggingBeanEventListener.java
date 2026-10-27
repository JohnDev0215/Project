package org.apache.shiro.config.event;

import org.apache.shiro.event.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class LoggingBeanEventListener {
    private static final Logger logger = LoggerFactory.getLogger(LoggingBeanEventListener.class);
    private static final String SUFFIX = BeanEvent.class.getSimpleName();

    @Subscribe
    public void onEvent(BeanEvent beanEvent) {
        String simpleName = beanEvent.getClass().getSimpleName();
        int iLastIndexOf = simpleName.lastIndexOf(SUFFIX);
        if (iLastIndexOf > 0) {
            simpleName = simpleName.substring(0, iLastIndexOf);
        }
        logger.trace("{} bean '{}' [{}]", new Object[]{simpleName, beanEvent.getBeanName(), beanEvent.getBean()});
    }
}
