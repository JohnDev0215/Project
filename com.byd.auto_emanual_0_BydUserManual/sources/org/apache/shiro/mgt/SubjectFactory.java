package org.apache.shiro.mgt;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;

/* JADX INFO: loaded from: classes.dex */
public interface SubjectFactory {
    Subject createSubject(SubjectContext subjectContext);
}
