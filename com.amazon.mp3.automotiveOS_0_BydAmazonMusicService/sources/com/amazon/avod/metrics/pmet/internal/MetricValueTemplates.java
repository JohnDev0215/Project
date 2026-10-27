package com.amazon.avod.metrics.pmet.internal;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MetricValueTemplates {
    private final ImmutableList<String> mBaseNames;
    private final ImmutableList<ImmutableList<Class<? extends MetricParameter>>> mRequiredParameterClasses;

    public static Builder defaultBuilder() {
        return new Builder(true);
    }

    public static Builder emptyBuilder() {
        return new Builder(false);
    }

    private MetricValueTemplates(@Nonnull ImmutableList<String> immutableList, @Nonnull ImmutableList<ImmutableList<Class<? extends MetricParameter>>> immutableList2) {
        ImmutableList<String> immutableList3 = (ImmutableList) Preconditions.checkNotNull(immutableList, "baseNames");
        this.mBaseNames = immutableList3;
        ImmutableList<ImmutableList<Class<? extends MetricParameter>>> immutableList4 = (ImmutableList) Preconditions.checkNotNull(immutableList2, "requiredParameterClasses");
        this.mRequiredParameterClasses = immutableList4;
        Preconditions.checkState(immutableList3.size() == immutableList4.size(), "MetricValueTemplates requires a parameter list for each base name provided; got %d names and %d lists", immutableList3.size(), immutableList4.size());
    }

    public ImmutableList<String> format(@Nonnull ImmutableList<ImmutableList<MetricParameter>> immutableList) {
        Preconditions.checkNotNull(immutableList, "allParameters");
        Preconditions2.checkStateWeakly(this.mRequiredParameterClasses.size() == immutableList.size(), "Bases (%s) expect %s parameter lists but was formatted with %s", this.mBaseNames, Integer.valueOf(this.mRequiredParameterClasses.size()), Integer.valueOf(immutableList.size()));
        UnmodifiableIterator<ImmutableList<Class<? extends MetricParameter>>> it = this.mRequiredParameterClasses.iterator();
        UnmodifiableIterator<ImmutableList<MetricParameter>> it2 = immutableList.iterator();
        UnmodifiableIterator<String> it3 = this.mBaseNames.iterator();
        ImmutableList.Builder builder = ImmutableList.builder();
        while (it.hasNext() && it2.hasNext() && it3.hasNext()) {
            String strAppendParametersIfValid = PmetMetricUtils.appendParametersIfValid(it.next(), it2.next(), it3.next());
            if (!Strings.isNullOrEmpty(strAppendParametersIfValid)) {
                builder.add(strAppendParametersIfValid);
            }
        }
        return builder.build();
    }

    public static class Builder {
        private final ImmutableList.Builder<String> mBases;
        private final ImmutableList.Builder<ImmutableList<Class<? extends MetricParameter>>> mParameterTypes;

        private Builder(boolean z) {
            ImmutableList.Builder<String> builder = ImmutableList.builder();
            this.mBases = builder;
            ImmutableList.Builder<ImmutableList<Class<? extends MetricParameter>>> builder2 = ImmutableList.builder();
            this.mParameterTypes = builder2;
            if (z) {
                builder.add("Counter");
                builder2.add(ImmutableList.of());
            }
        }

        public Builder add(@Nonnull String str, @Nonnull Class<? extends MetricParameter> cls) {
            Preconditions.checkNotNull(cls, "parameterType");
            return add(str, ImmutableList.of(cls));
        }

        public Builder add(@Nonnull String str, @Nonnull ImmutableList<Class<? extends MetricParameter>> immutableList) {
            this.mBases.add((String) Preconditions.checkNotNull(str, "baseName"));
            this.mParameterTypes.add((ImmutableList<Class<? extends MetricParameter>>) Preconditions.checkNotNull(immutableList, "parameterTypes"));
            return this;
        }

        public MetricValueTemplates build() {
            return new MetricValueTemplates(this.mBases.build(), this.mParameterTypes.build());
        }
    }
}
