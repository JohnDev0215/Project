package com.amazon.hermes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
class CoralExceptionDeserializer {
    private String message;
    private Class type;

    CoralExceptionDeserializer() {
    }

    public void set__type(String str) throws ClassNotFoundException {
        this.type = Class.forName(StringUtils.replace(str, "#", "."));
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Throwable get() {
        try {
            return (Throwable) this.type.getDeclaredConstructor(String.class).newInstance(this.message);
        } catch (IllegalAccessException e) {
            return new Exception(this.type + ": " + this.message, e);
        } catch (InstantiationException e2) {
            return new Exception(this.type + ": " + this.message, e2);
        } catch (NoSuchMethodException e3) {
            return new Exception(this.type + ": " + this.message, e3);
        } catch (InvocationTargetException e4) {
            return new Exception(this.type + ": " + this.message, e4);
        }
    }
}
