package com.google.firebase.perf.plugin.instrumentation;

import com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodInstrumentationFactory;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public abstract class InstrumentationConfigBase implements InstrumentationConfig {
    protected final List<AnnotatedMethodInstrumentationConfig> annotatedMethodConfigs;
    protected final List<NetworkObjectInstrumentationConfig> networkObjectConfigs;

    public InstrumentationConfigBase(List<AnnotatedMethodInstrumentationConfig> list, List<NetworkObjectInstrumentationConfig> list2) {
        this.annotatedMethodConfigs = Collections.unmodifiableList(list);
        this.networkObjectConfigs = Collections.unmodifiableList(list2);
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.InstrumentationConfig
    @Nullable
    public List<AnnotatedMethodInstrumentationFactory> getAnnotatedMethodInstrumentationFactories(String str) {
        ArrayList arrayList = null;
        for (AnnotatedMethodInstrumentationConfig annotatedMethodInstrumentationConfig : this.annotatedMethodConfigs) {
            if (annotatedMethodInstrumentationConfig.getClassDesc().equals(str)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(annotatedMethodInstrumentationConfig.getFactory());
            }
        }
        return arrayList;
    }
}
