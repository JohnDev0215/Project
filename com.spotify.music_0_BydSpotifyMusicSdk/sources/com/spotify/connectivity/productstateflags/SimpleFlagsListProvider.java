package com.spotify.connectivity.productstateflags;

import com.spotify.connectivity.flags.Flag;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class SimpleFlagsListProvider implements FlagsListProvider {
    public static final Companion Companion = new Companion(null);
    private List<? extends Flag<?>> mAllFlags;
    private List<? extends Flag<?>> mProductStateFlags;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void populateSourceLists(List<? extends FlagsListProvider> list, List<Flag<?>> list2, List<Flag<?>> list3) {
            Iterator<? extends FlagsListProvider> it = list.iterator();
            while (it.hasNext()) {
                Field[] declaredFields = it.next().getClass().getDeclaredFields();
                try {
                    v03.i(declaredFields, "declaredFields");
                    for (Field field : declaredFields) {
                        if (Flag.class.isAssignableFrom(field.getType())) {
                            if (!field.isAccessible()) {
                                field.setAccessible(true);
                            }
                            Object obj = field.get(null);
                            v03.g(obj, "null cannot be cast to non-null type com.spotify.connectivity.flags.Flag<*>");
                            Flag<?> flag = (Flag) obj;
                            if (!v03.b(ProductStateSource.TYPE, flag.getSource().getSourceType())) {
                                throw new IllegalArgumentException("Failed requirement.".toString());
                            }
                            list2.add(flag);
                            list3.add(flag);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                }
            }
        }
    }

    private final synchronized void initializeListsIfNecessary() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Companion.populateSourceLists(getFlagsListProviders(), arrayList, arrayList2);
        this.mProductStateFlags = Collections.unmodifiableList(arrayList);
        this.mAllFlags = Collections.unmodifiableList(arrayList2);
    }

    @Override // com.spotify.connectivity.productstateflags.FlagsListProvider
    public Collection<Flag<?>> getAllFlags() {
        initializeListsIfNecessary();
        List<? extends Flag<?>> list = this.mAllFlags;
        v03.h(list);
        return list;
    }

    public List<FlagsListProvider> getFlagsListProviders() {
        return v03.B(this);
    }

    @Override // com.spotify.connectivity.productstateflags.FlagsListProvider
    public List<Flag<?>> getProductStateFlags() {
        initializeListsIfNecessary();
        List list = this.mProductStateFlags;
        v03.h(list);
        return list;
    }
}
