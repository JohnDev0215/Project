package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class AnnotationIntrospector implements Serializable {
    public abstract Object a(AnnotatedMember annotatedMember);
}
