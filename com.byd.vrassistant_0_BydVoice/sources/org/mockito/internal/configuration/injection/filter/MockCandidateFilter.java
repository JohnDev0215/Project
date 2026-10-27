package org.mockito.internal.configuration.injection.filter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface MockCandidateFilter {
    OngoingInjector filterCandidate(Collection<Object> collection, Field field, List<Field> list, Object obj);
}
