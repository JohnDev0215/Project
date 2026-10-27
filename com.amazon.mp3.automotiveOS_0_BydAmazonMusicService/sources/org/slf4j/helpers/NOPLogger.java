package org.slf4j.helpers;

/* JADX INFO: loaded from: classes6.dex */
public class NOPLogger extends MarkerIgnoringBase {
    public static final NOPLogger NOP_LOGGER = new NOPLogger();

    @Override // org.slf4j.Logger
    public final void debug(String str) {
    }

    @Override // org.slf4j.Logger
    public final void debug(String str, Object obj) {
    }

    @Override // org.slf4j.Logger
    public final void debug(String str, Object obj, Object obj2) {
    }

    @Override // org.slf4j.Logger
    public final void debug(String str, Throwable th) {
    }

    @Override // org.slf4j.Logger
    public final void debug(String str, Object... objArr) {
    }

    @Override // org.slf4j.Logger
    public final void error(String str) {
    }

    @Override // org.slf4j.Logger
    public final void error(String str, Object obj) {
    }

    @Override // org.slf4j.Logger
    public final void error(String str, Object obj, Object obj2) {
    }

    @Override // org.slf4j.Logger
    public final void error(String str, Throwable th) {
    }

    @Override // org.slf4j.Logger
    public final void error(String str, Object... objArr) {
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, org.slf4j.helpers.NamedLoggerBase, org.slf4j.Logger
    public String getName() {
        return "NOP";
    }

    @Override // org.slf4j.Logger
    public final void info(String str) {
    }

    @Override // org.slf4j.Logger
    public final void info(String str, Object obj) {
    }

    @Override // org.slf4j.Logger
    public final void info(String str, Object obj, Object obj2) {
    }

    @Override // org.slf4j.Logger
    public final void info(String str, Throwable th) {
    }

    @Override // org.slf4j.Logger
    public final void info(String str, Object... objArr) {
    }

    @Override // org.slf4j.Logger
    public final void trace(String str) {
    }

    @Override // org.slf4j.Logger
    public final void trace(String str, Object obj) {
    }

    @Override // org.slf4j.Logger
    public final void trace(String str, Throwable th) {
    }

    @Override // org.slf4j.Logger
    public final void warn(String str) {
    }

    @Override // org.slf4j.Logger
    public final void warn(String str, Object obj) {
    }

    @Override // org.slf4j.Logger
    public final void warn(String str, Object obj, Object obj2) {
    }

    @Override // org.slf4j.Logger
    public final void warn(String str, Throwable th) {
    }

    protected NOPLogger() {
    }
}
