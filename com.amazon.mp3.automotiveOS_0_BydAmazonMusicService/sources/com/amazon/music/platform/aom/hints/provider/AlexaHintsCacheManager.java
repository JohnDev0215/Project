package com.amazon.music.platform.aom.hints.provider;

import com.amazon.music.platform.aom.hints.AlexaHints;
import com.amazon.music.platform.aom.hints.dae.DAE;
import kotlin.Metadata;

/* JADX INFO: compiled from: AlexaHintsCacheManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004H&J\b\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lcom/amazon/music/platform/aom/hints/provider/AlexaHintsCacheManager;", "", "Lcom/amazon/music/platform/aom/hints/dae/DAE;", "dae", "Lcom/amazon/music/platform/aom/hints/AlexaHints;", "getHints", "alexaHints", "", "updateHints", "removeAllHints", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public interface AlexaHintsCacheManager {
    AlexaHints getHints(DAE dae);

    void removeAllHints();

    void updateHints(DAE dae, AlexaHints alexaHints);
}
