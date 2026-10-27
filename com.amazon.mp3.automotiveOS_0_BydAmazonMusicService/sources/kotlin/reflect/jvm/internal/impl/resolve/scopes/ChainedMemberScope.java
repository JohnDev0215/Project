package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

/* JADX INFO: compiled from: ChainedMemberScope.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class ChainedMemberScope implements MemberScope {
    public static final Companion Companion = new Companion(null);
    private final String debugName;
    private final MemberScope[] scopes;

    public /* synthetic */ ChainedMemberScope(String str, MemberScope[] memberScopeArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, memberScopeArr);
    }

    private ChainedMemberScope(String str, MemberScope[] memberScopeArr) {
        this.debugName = str;
        this.scopes = memberScopeArr;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    /* JADX INFO: renamed from: getContributedClassifier */
    public ClassifierDescriptor mo4086getContributedClassifier(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        MemberScope[] memberScopeArr = this.scopes;
        int length = memberScopeArr.length;
        ClassifierDescriptor classifierDescriptor = null;
        int i = 0;
        while (i < length) {
            MemberScope memberScope = memberScopeArr[i];
            i++;
            ClassifierDescriptor classifierDescriptorMo4086getContributedClassifier = memberScope.mo4086getContributedClassifier(name, location);
            if (classifierDescriptorMo4086getContributedClassifier != null) {
                if (!(classifierDescriptorMo4086getContributedClassifier instanceof ClassifierDescriptorWithTypeParameters) || !((ClassifierDescriptorWithTypeParameters) classifierDescriptorMo4086getContributedClassifier).isExpect()) {
                    return classifierDescriptorMo4086getContributedClassifier;
                }
                if (classifierDescriptor == null) {
                    classifierDescriptor = classifierDescriptorMo4086getContributedClassifier;
                }
            }
        }
        return classifierDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection<PropertyDescriptor> getContributedVariables(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        MemberScope[] memberScopeArr = this.scopes;
        int length = memberScopeArr.length;
        if (length == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        int i = 0;
        if (length == 1) {
            return memberScopeArr[0].getContributedVariables(name, location);
        }
        Collection<PropertyDescriptor> collectionConcat = null;
        int length2 = memberScopeArr.length;
        while (i < length2) {
            MemberScope memberScope = memberScopeArr[i];
            i++;
            collectionConcat = ScopeUtilsKt.concat(collectionConcat, memberScope.getContributedVariables(name, location));
        }
        return collectionConcat == null ? SetsKt__SetsKt.emptySet() : collectionConcat;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        MemberScope[] memberScopeArr = this.scopes;
        int length = memberScopeArr.length;
        if (length == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        int i = 0;
        if (length == 1) {
            return memberScopeArr[0].getContributedFunctions(name, location);
        }
        Collection<SimpleFunctionDescriptor> collectionConcat = null;
        int length2 = memberScopeArr.length;
        while (i < length2) {
            MemberScope memberScope = memberScopeArr[i];
            i++;
            collectionConcat = ScopeUtilsKt.concat(collectionConcat, memberScope.getContributedFunctions(name, location));
        }
        return collectionConcat == null ? SetsKt__SetsKt.emptySet() : collectionConcat;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter kindFilter, Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        MemberScope[] memberScopeArr = this.scopes;
        int length = memberScopeArr.length;
        if (length == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        int i = 0;
        if (length == 1) {
            return memberScopeArr[0].getContributedDescriptors(kindFilter, nameFilter);
        }
        Collection<DeclarationDescriptor> collectionConcat = null;
        int length2 = memberScopeArr.length;
        while (i < length2) {
            MemberScope memberScope = memberScopeArr[i];
            i++;
            collectionConcat = ScopeUtilsKt.concat(collectionConcat, memberScope.getContributedDescriptors(kindFilter, nameFilter));
        }
        return collectionConcat == null ? SetsKt__SetsKt.emptySet() : collectionConcat;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set<Name> getFunctionNames() {
        MemberScope[] memberScopeArr = this.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int length = memberScopeArr.length;
        int i = 0;
        while (i < length) {
            MemberScope memberScope = memberScopeArr[i];
            i++;
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, memberScope.getFunctionNames());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set<Name> getVariableNames() {
        MemberScope[] memberScopeArr = this.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int length = memberScopeArr.length;
        int i = 0;
        while (i < length) {
            MemberScope memberScope = memberScopeArr[i];
            i++;
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, memberScope.getVariableNames());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set<Name> getClassifierNames() {
        return MemberScopeKt.flatMapClassifierNamesOrNull(ArraysKt___ArraysKt.asIterable(this.scopes));
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        MemberScope[] memberScopeArr = this.scopes;
        int length = memberScopeArr.length;
        int i = 0;
        while (i < length) {
            MemberScope memberScope = memberScopeArr[i];
            i++;
            memberScope.recordLookup(name, location);
        }
    }

    public String toString() {
        return this.debugName;
    }

    /* JADX INFO: compiled from: ChainedMemberScope.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MemberScope create(String debugName, Iterable<? extends MemberScope> scopes) {
            Intrinsics.checkNotNullParameter(debugName, "debugName");
            Intrinsics.checkNotNullParameter(scopes, "scopes");
            SmartList smartList = new SmartList();
            for (MemberScope memberScope : scopes) {
                if (memberScope != MemberScope.Empty.INSTANCE) {
                    if (memberScope instanceof ChainedMemberScope) {
                        CollectionsKt__MutableCollectionsKt.addAll(smartList, ((ChainedMemberScope) memberScope).scopes);
                    } else {
                        smartList.add(memberScope);
                    }
                }
            }
            return createOrSingle$descriptors(debugName, smartList);
        }

        public final MemberScope createOrSingle$descriptors(String debugName, List<? extends MemberScope> scopes) {
            Intrinsics.checkNotNullParameter(debugName, "debugName");
            Intrinsics.checkNotNullParameter(scopes, "scopes");
            int size = scopes.size();
            if (size == 0) {
                return MemberScope.Empty.INSTANCE;
            }
            if (size != 1) {
                Object[] array = scopes.toArray(new MemberScope[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                return new ChainedMemberScope(debugName, (MemberScope[]) array, null);
            }
            return scopes.get(0);
        }
    }
}
