package org.slf4j.helpers;

import org.slf4j.Logger;

/* JADX INFO: loaded from: classes6.dex */
public class SubstituteLogger implements Logger {
    private volatile Logger _delegate;
    private final String name;

    public SubstituteLogger(String str) {
        this.name = str;
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.name;
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        delegate().trace(str);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        delegate().trace(str, obj);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        delegate().trace(str, th);
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        delegate().debug(str);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        delegate().debug(str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        delegate().debug(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        delegate().debug(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        delegate().debug(str, th);
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        delegate().info(str);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        delegate().info(str, obj);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        delegate().info(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        delegate().info(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        delegate().info(str, th);
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        delegate().warn(str);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        delegate().warn(str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        delegate().warn(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        delegate().warn(str, th);
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        delegate().error(str);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        delegate().error(str, obj);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        delegate().error(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        delegate().error(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        delegate().error(str, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.name.equals(((SubstituteLogger) obj).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    Logger delegate() {
        return this._delegate != null ? this._delegate : NOPLogger.NOP_LOGGER;
    }

    public void setDelegate(Logger logger) {
        this._delegate = logger;
    }
}
