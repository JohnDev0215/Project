package com.loostone.libserver.version2.data.config;

import com.loostone.libserver.version2.bean.MicItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultMicConfig {
    public static final OooO00o Companion = new OooO00o(null);
    private static final String TAG = "DefaultMicConfig";
    private int micPeriod = 240;
    private int micListVersion = 1;
    private List<MicItem> micList = new ArrayList();

    public static final class OooO00o {
        public OooO00o() {
        }

        public /* synthetic */ OooO00o(f fVar) {
            this();
        }
    }

    public final List<MicItem> getMicList() {
        return this.micList;
    }

    public final int getMicListVersion() {
        return this.micListVersion;
    }

    public final int getMicPeriod() {
        return this.micPeriod;
    }

    public final void setMicList(List<MicItem> list) {
        h.d(list, "<set-?>");
        this.micList = list;
    }

    public final void setMicListVersion(int i) {
        this.micListVersion = i;
    }

    public final void setMicPeriod(int i) {
        this.micPeriod = i;
    }
}
