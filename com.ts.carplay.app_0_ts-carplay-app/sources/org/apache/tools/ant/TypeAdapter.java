package org.apache.tools.ant;

/* JADX INFO: loaded from: classes.dex */
public interface TypeAdapter {
    void checkProxyClass(Class cls);

    Project getProject();

    Object getProxy();

    void setProject(Project project);

    void setProxy(Object obj);
}
