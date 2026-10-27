package net.bytebuddy.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class MethodSortMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final Sort sort;

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodSortMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodSortMatcher)) {
            return false;
        }
        MethodSortMatcher methodSortMatcher = (MethodSortMatcher) obj;
        if (!methodSortMatcher.canEqual(this)) {
            return false;
        }
        Sort sort = this.sort;
        Sort sort2 = methodSortMatcher.sort;
        return sort != null ? sort.equals(sort2) : sort2 == null;
    }

    public int hashCode() {
        Sort sort = this.sort;
        return 59 + (sort == null ? 43 : sort.hashCode());
    }

    public MethodSortMatcher(Sort sort) {
        this.sort = sort;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.sort.isSort(t);
    }

    public String toString() {
        return this.sort.getDescription();
    }

    public enum Sort {
        METHOD("isMethod()") { // from class: net.bytebuddy.matcher.MethodSortMatcher.Sort.1
            @Override // net.bytebuddy.matcher.MethodSortMatcher.Sort
            protected boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isMethod();
            }
        },
        CONSTRUCTOR("isConstructor()") { // from class: net.bytebuddy.matcher.MethodSortMatcher.Sort.2
            @Override // net.bytebuddy.matcher.MethodSortMatcher.Sort
            protected boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isConstructor();
            }
        },
        TYPE_INITIALIZER("isTypeInitializer()") { // from class: net.bytebuddy.matcher.MethodSortMatcher.Sort.3
            @Override // net.bytebuddy.matcher.MethodSortMatcher.Sort
            protected boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isTypeInitializer();
            }
        },
        VIRTUAL("isVirtual()") { // from class: net.bytebuddy.matcher.MethodSortMatcher.Sort.4
            @Override // net.bytebuddy.matcher.MethodSortMatcher.Sort
            protected boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isVirtual();
            }
        },
        DEFAULT_METHOD("isDefaultMethod()") { // from class: net.bytebuddy.matcher.MethodSortMatcher.Sort.5
            @Override // net.bytebuddy.matcher.MethodSortMatcher.Sort
            protected boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isDefaultMethod();
            }
        };

        private final String description;

        protected abstract boolean isSort(MethodDescription methodDescription);

        Sort(String str) {
            this.description = str;
        }

        protected String getDescription() {
            return this.description;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "MethodSortMatcher.Sort." + name();
        }
    }
}
