package net.bytebuddy.matcher;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class CachingMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    protected final ConcurrentMap<? super T, Boolean> map;
    private final ElementMatcher<? super T> matcher;

    public CachingMatcher(ElementMatcher<? super T> elementMatcher, ConcurrentMap<? super T, Boolean> concurrentMap) {
        this.matcher = elementMatcher;
        this.map = concurrentMap;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        Boolean boolValueOf = this.map.get(t);
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(onCacheMiss(t));
        }
        return boolValueOf.booleanValue();
    }

    protected boolean onCacheMiss(T t) {
        boolean zMatches = this.matcher.matches(t);
        this.map.put(t, Boolean.valueOf(zMatches));
        return zMatches;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CachingMatcher) {
            return this.matcher.equals(((CachingMatcher) obj).matcher);
        }
        return false;
    }

    public int hashCode() {
        return this.matcher.hashCode();
    }

    public String toString() {
        return "cached(" + this.matcher + ")";
    }

    public static class WithInlineEviction<S> extends CachingMatcher<S> {
        private final int evictionSize;

        public WithInlineEviction(ElementMatcher<? super S> elementMatcher, ConcurrentMap<? super S, Boolean> concurrentMap, int i) {
            super(elementMatcher, concurrentMap);
            this.evictionSize = i;
        }

        @Override // net.bytebuddy.matcher.CachingMatcher
        protected boolean onCacheMiss(S s) {
            if (this.map.size() >= this.evictionSize) {
                Iterator<Map.Entry<? super T, Boolean>> it = this.map.entrySet().iterator();
                it.next();
                it.remove();
            }
            return super.onCacheMiss(s);
        }
    }
}
