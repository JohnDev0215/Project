package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.v2.model.annotation.Locatable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBException;

/* JADX INFO: loaded from: classes3.dex */
public class IllegalAnnotationException extends JAXBException {
    private static final long serialVersionUID = 1;
    private final List<List<Location>> pos;

    public IllegalAnnotationException(String str, Locatable locatable) {
        super(str);
        this.pos = build(locatable);
    }

    public IllegalAnnotationException(String str, Annotation annotation) {
        this(str, cast(annotation));
    }

    public IllegalAnnotationException(String str, Locatable locatable, Locatable locatable2) {
        super(str);
        this.pos = build(locatable, locatable2);
    }

    public IllegalAnnotationException(String str, Annotation annotation, Annotation annotation2) {
        this(str, cast(annotation), cast(annotation2));
    }

    public IllegalAnnotationException(String str, Annotation annotation, Locatable locatable) {
        this(str, cast(annotation), locatable);
    }

    public IllegalAnnotationException(String str, Throwable th, Locatable locatable) {
        super(str, th);
        this.pos = build(locatable);
    }

    private static Locatable cast(Annotation annotation) {
        if (annotation instanceof Locatable) {
            return (Locatable) annotation;
        }
        return null;
    }

    private List<List<Location>> build(Locatable... locatableArr) {
        List<Location> listConvert;
        ArrayList arrayList = new ArrayList();
        for (Locatable locatable : locatableArr) {
            if (locatable != null && (listConvert = convert(locatable)) != null && !listConvert.isEmpty()) {
                arrayList.add(listConvert);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private List<Location> convert(Locatable locatable) {
        if (locatable == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (locatable != null) {
            arrayList.add(locatable.getLocation());
            locatable = locatable.getUpstream();
        }
        return Collections.unmodifiableList(arrayList);
    }

    public List<List<Location>> getSourcePos() {
        return this.pos;
    }

    @Override // javax.xml.bind.JAXBException, java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder(getMessage());
        for (List<Location> list : this.pos) {
            sb.append("\n\tthis problem is related to the following location:");
            Iterator<Location> it = list.iterator();
            while (it.hasNext()) {
                sb.append("\n\t\tat ").append(it.next().toString());
            }
        }
        return sb.toString();
    }
}
