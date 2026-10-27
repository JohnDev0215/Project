package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JacksonException;
import java.io.Closeable;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
public class JsonMappingException extends DatabindException {
    private static final long serialVersionUID = 3;
    protected LinkedList<Reference> _path;

    public static class Reference implements Serializable {
        private static final long serialVersionUID = 2;

        public abstract String toString();
    }

    public JsonMappingException(Closeable closeable, String str) {
        super(str);
        if (closeable instanceof q71) {
            this._location = ((q71) closeable).O0();
        }
    }

    public final String d() {
        String message = super.getMessage();
        if (this._path == null) {
            return message;
        }
        StringBuilder sb = message == null ? new StringBuilder() : new StringBuilder(message);
        sb.append(" (through reference chain: ");
        LinkedList<Reference> linkedList = this._path;
        if (linkedList != null) {
            Iterator<Reference> it = linkedList.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                if (it.hasNext()) {
                    sb.append("->");
                }
            }
        }
        sb.append(')');
        return sb.toString();
    }

    @Override // java.lang.Throwable
    public final String getLocalizedMessage() {
        return d();
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException, java.lang.Throwable
    public String getMessage() {
        return d();
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return getClass().getName() + ": " + getMessage();
    }

    public JsonMappingException(Closeable closeable, String str, Throwable th) {
        super(str, null, th);
        if (th instanceof JacksonException) {
            this._location = ((JacksonException) th).a();
        } else if (closeable instanceof q71) {
            this._location = ((q71) closeable).O0();
        }
    }
}
