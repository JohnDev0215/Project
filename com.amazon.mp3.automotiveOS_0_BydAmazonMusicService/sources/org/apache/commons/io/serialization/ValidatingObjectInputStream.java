package org.apache.commons.io.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class ValidatingObjectInputStream extends ObjectInputStream {
    private final List<ClassNameMatcher> acceptMatchers;
    private final List<ClassNameMatcher> rejectMatchers;

    public ValidatingObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        this.acceptMatchers = new ArrayList();
        this.rejectMatchers = new ArrayList();
    }

    private void validateClassName(String str) throws InvalidClassException {
        Iterator<ClassNameMatcher> it = this.rejectMatchers.iterator();
        while (it.hasNext()) {
            if (it.next().matches(str)) {
                invalidClassNameFound(str);
            }
        }
        boolean z = false;
        Iterator<ClassNameMatcher> it2 = this.acceptMatchers.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            } else if (it2.next().matches(str)) {
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        invalidClassNameFound(str);
    }

    protected void invalidClassNameFound(String str) throws InvalidClassException {
        throw new InvalidClassException("Class name not accepted: " + str);
    }

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        validateClassName(objectStreamClass.getName());
        return super.resolveClass(objectStreamClass);
    }

    public ValidatingObjectInputStream accept(String... strArr) {
        for (String str : strArr) {
            this.acceptMatchers.add(new WildcardClassNameMatcher(str));
        }
        return this;
    }
}
