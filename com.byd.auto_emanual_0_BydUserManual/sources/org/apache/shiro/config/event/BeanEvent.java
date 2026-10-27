package org.apache.shiro.config.event;

import java.util.Map;
import org.apache.shiro.event.Event;

/* JADX INFO: loaded from: classes.dex */
public abstract class BeanEvent extends Event {
    private Object bean;
    private final Map<String, Object> beanContext;
    private String beanName;

    public BeanEvent(String str, Object obj, Map<String, Object> map) {
        super(obj);
        this.beanName = str;
        this.bean = obj;
        this.beanContext = map;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public Object getBean() {
        return this.bean;
    }

    public Map<String, Object> getBeanContext() {
        return this.beanContext;
    }
}
