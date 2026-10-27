package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.util.StringUtil;

/* JADX INFO: loaded from: classes4.dex */
public class WantedButNotInvoked extends MockitoAssertionError {
    private static final long serialVersionUID = 1;

    public WantedButNotInvoked(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return StringUtil.removeFirstLine(super.toString());
    }
}
