package io.gsonfire;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class ClassConfig<T> {
    private Class<T> clazz;
    private boolean hooksEnabled;
    private Collection<PostProcessor<T>> postProcessors;
    private Collection<PreProcessor<T>> preProcessors;
    private TypeSelector<? super T> typeSelector;

    public ClassConfig(Class<T> cls) {
        this.clazz = cls;
    }

    public Class<T> getConfiguredClass() {
        return this.clazz;
    }

    public TypeSelector<? super T> getTypeSelector() {
        return this.typeSelector;
    }

    public void setTypeSelector(TypeSelector<? super T> typeSelector) {
        this.typeSelector = typeSelector;
    }

    public Collection<PostProcessor<T>> getPostProcessors() {
        if (this.postProcessors == null) {
            this.postProcessors = new ArrayList();
        }
        return this.postProcessors;
    }

    public Collection<PreProcessor<T>> getPreProcessors() {
        if (this.preProcessors == null) {
            this.preProcessors = new ArrayList();
        }
        return this.preProcessors;
    }

    public boolean isHooksEnabled() {
        return this.hooksEnabled;
    }

    public void setHooksEnabled(boolean z) {
        this.hooksEnabled = z;
    }
}
