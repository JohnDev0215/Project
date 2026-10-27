package org.apache.shiro.env;

/* JADX INFO: loaded from: classes.dex */
public interface NamedObjectEnvironment extends Environment {
    <T> T getObject(String str, Class<T> cls) throws RequiredTypeException;
}
