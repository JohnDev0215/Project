package com.amazon.music.featuregating.weblabs;

import kotlin.Metadata;
import kotlin.random.Random;

/* JADX INFO: compiled from: WeblabSessionIdGenerator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabSessionIdGenerator;", "", "", "generateSessionId", "<init>", "()V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class WeblabSessionIdGenerator {
    public static final WeblabSessionIdGenerator INSTANCE = new WeblabSessionIdGenerator();

    private WeblabSessionIdGenerator() {
    }

    public final String generateSessionId() {
        Random.Companion companion = Random.INSTANCE;
        String string = "";
        int i = 0;
        while (i < 19) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append((i == 3 || i == 11) ? "-" : String.valueOf(companion.nextInt(10)));
            string = sb.toString();
            i++;
        }
        return string;
    }
}
