package org.mockito.junit;

import org.junit.rules.MethodRule;
import org.mockito.Incubating;
import org.mockito.quality.Strictness;

/* JADX INFO: loaded from: classes4.dex */
public interface MockitoRule extends MethodRule {
    MockitoRule silent();

    @Incubating
    MockitoRule strictness(Strictness strictness);
}
