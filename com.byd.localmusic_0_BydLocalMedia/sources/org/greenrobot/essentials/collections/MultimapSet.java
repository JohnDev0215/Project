package org.greenrobot.essentials.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes5.dex */
public class MultimapSet<K, V> extends AbstractMultimap<K, V, Set<V>> {
    private final SetType setType;

    public enum SetType {
        REGULAR,
        THREAD_SAFE
    }

    public static <K, V> MultimapSet<K, V> create() {
        return create(SetType.REGULAR);
    }

    public static <K, V> MultimapSet<K, V> create(SetType setType) {
        return new MultimapSet<>(new HashMap(), setType);
    }

    protected MultimapSet(Map<K, Set<V>> map, SetType setType) {
        super(map);
        this.setType = setType;
    }

    /* JADX INFO: renamed from: org.greenrobot.essentials.collections.MultimapSet$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$greenrobot$essentials$collections$MultimapSet$SetType;

        static {
            int[] iArr = new int[SetType.values().length];
            $SwitchMap$org$greenrobot$essentials$collections$MultimapSet$SetType = iArr;
            try {
                iArr[SetType.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$greenrobot$essentials$collections$MultimapSet$SetType[SetType.THREAD_SAFE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.essentials.collections.AbstractMultimap
    public Set<V> createNewCollection() {
        int i = AnonymousClass1.$SwitchMap$org$greenrobot$essentials$collections$MultimapSet$SetType[this.setType.ordinal()];
        if (i == 1) {
            return new HashSet();
        }
        if (i == 2) {
            return new CopyOnWriteArraySet();
        }
        throw new IllegalStateException("Unknown set type: " + this.setType);
    }
}
