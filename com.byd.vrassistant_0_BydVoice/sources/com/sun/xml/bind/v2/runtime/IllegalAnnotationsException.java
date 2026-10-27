package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.v2.model.core.ErrorHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBException;

/* JADX INFO: loaded from: classes3.dex */
public class IllegalAnnotationsException extends JAXBException {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 1;
    private final List<IllegalAnnotationException> errors;

    public IllegalAnnotationsException(List<IllegalAnnotationException> list) {
        super(list.size() + " counts of IllegalAnnotationExceptions");
        this.errors = Collections.unmodifiableList(new ArrayList(list));
    }

    @Override // javax.xml.bind.JAXBException, java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('\n');
        Iterator<IllegalAnnotationException> it = this.errors.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString()).append('\n');
        }
        return sb.toString();
    }

    public List<IllegalAnnotationException> getErrors() {
        return this.errors;
    }

    public static class Builder implements ErrorHandler {
        private final List<IllegalAnnotationException> list = new ArrayList();

        @Override // com.sun.xml.bind.v2.model.core.ErrorHandler
        public void error(IllegalAnnotationException illegalAnnotationException) {
            this.list.add(illegalAnnotationException);
        }

        public void check() throws IllegalAnnotationsException {
            if (!this.list.isEmpty()) {
                throw new IllegalAnnotationsException(this.list);
            }
        }
    }
}
