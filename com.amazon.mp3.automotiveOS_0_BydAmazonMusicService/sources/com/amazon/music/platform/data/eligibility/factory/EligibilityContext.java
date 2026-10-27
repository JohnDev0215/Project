package com.amazon.music.platform.data.eligibility.factory;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.AtomicMapKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: EligibilityContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u000eJ)\u0010\u000f\u001a\u0004\u0018\u0001H\u0010\"\b\b\u0000\u0010\u0010*\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\bH\u0000¢\u0006\u0004\b\u0012\u0010\u0013R!\u0010\u0006\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/factory/EligibilityContext;", "", "_inputs", "", "Lcom/amazon/music/platform/data/eligibility/factory/EligibilityInput;", "(Ljava/util/List;)V", "inputs", "Lcom/amazon/music/platform/AtomicMap;", "Lkotlin/reflect/KClass;", "getInputs", "()Lcom/amazon/music/platform/AtomicMap;", "add", "", "eligibilityInput", "add$MusicPlatformCore_release", "get", "T", "kClass", "get$MusicPlatformCore_release", "(Lkotlin/reflect/KClass;)Lcom/amazon/music/platform/data/eligibility/factory/EligibilityInput;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class EligibilityContext {
    private final AtomicMap<KClass<?>, EligibilityInput> inputs;

    public EligibilityContext(List<? extends EligibilityInput> _inputs) {
        Intrinsics.checkNotNullParameter(_inputs, "_inputs");
        AtomicMap<KClass<?>, EligibilityInput> atomicMapAtomicMapOf = AtomicMapKt.atomicMapOf();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(_inputs, 10));
        for (EligibilityInput eligibilityInput : _inputs) {
            arrayList.add(TuplesKt.to(Reflection.getOrCreateKotlinClass(eligibilityInput.getClass()), eligibilityInput));
        }
        atomicMapAtomicMapOf.putAll(arrayList);
        this.inputs = atomicMapAtomicMapOf;
    }

    public final AtomicMap<KClass<?>, EligibilityInput> getInputs() {
        return this.inputs;
    }

    public final void add$MusicPlatformCore_release(EligibilityInput eligibilityInput) {
        Intrinsics.checkNotNullParameter(eligibilityInput, "eligibilityInput");
        this.inputs.put(Reflection.getOrCreateKotlinClass(eligibilityInput.getClass()), eligibilityInput);
    }

    public final <T extends EligibilityInput> T get$MusicPlatformCore_release(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        EligibilityInput eligibilityInput = this.inputs.get(kClass);
        if (eligibilityInput instanceof EligibilityInput) {
            return (T) eligibilityInput;
        }
        return null;
    }
}
