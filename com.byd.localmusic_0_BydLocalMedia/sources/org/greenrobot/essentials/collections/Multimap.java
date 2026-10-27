package org.greenrobot.essentials.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class Multimap<K, V> extends AbstractMultimap<K, V, List<V>> {
    private final ListType listType;

    public enum ListType {
        REGULAR,
        THREAD_SAFE,
        LINKED
    }

    public static <K, V> Multimap<K, V> create() {
        return create(ListType.REGULAR);
    }

    public static <K, V> Multimap<K, V> create(ListType listType) {
        return new Multimap<>(new HashMap(), listType);
    }

    protected Multimap(Map<K, List<V>> map, ListType listType) {
        super(map);
        this.listType = listType;
        if (listType == null) {
            throw new IllegalArgumentException("List type may not be null");
        }
    }

    /* JADX INFO: renamed from: org.greenrobot.essentials.collections.Multimap$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$greenrobot$essentials$collections$Multimap$ListType;

        static {
            int[] iArr = new int[ListType.values().length];
            $SwitchMap$org$greenrobot$essentials$collections$Multimap$ListType = iArr;
            try {
                iArr[ListType.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$greenrobot$essentials$collections$Multimap$ListType[ListType.THREAD_SAFE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$greenrobot$essentials$collections$Multimap$ListType[ListType.LINKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.essentials.collections.AbstractMultimap
    public List<V> createNewCollection() {
        int i = AnonymousClass1.$SwitchMap$org$greenrobot$essentials$collections$Multimap$ListType[this.listType.ordinal()];
        if (i == 1) {
            return new ArrayList();
        }
        if (i == 2) {
            return new CopyOnWriteArrayList();
        }
        if (i == 3) {
            return new LinkedList();
        }
        throw new IllegalStateException("Unknown list type: " + this.listType);
    }
}
