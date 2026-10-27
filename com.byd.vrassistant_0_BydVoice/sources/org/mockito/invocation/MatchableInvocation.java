package org.mockito.invocation;

import java.util.List;
import org.mockito.ArgumentMatcher;

/* JADX INFO: loaded from: classes4.dex */
public interface MatchableInvocation extends DescribedInvocation {
    void captureArgumentsFrom(Invocation invocation);

    Invocation getInvocation();

    List<ArgumentMatcher> getMatchers();

    boolean hasSameMethod(Invocation invocation);

    boolean hasSimilarMethod(Invocation invocation);

    boolean matches(Invocation invocation);
}
