package com.amazon.music.find.skyfire;

import kotlin.Metadata;

/* JADX INFO: compiled from: Contexts.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/skyfire/Contexts;", "", "()V", "OpenAlexa", "OpenFindSeeMore", "OpenSearch", "OpenTarget", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Contexts {
    public static final Contexts INSTANCE = new Contexts();

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/find/skyfire/Contexts$OpenTarget;", "", "()V", "target", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenTarget {
        public static final OpenTarget INSTANCE = new OpenTarget();
        public static final String target = "TARGET";

        private OpenTarget() {
        }
    }

    private Contexts() {
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/find/skyfire/Contexts$OpenFindSeeMore;", "", "()V", "contentId", "", "title", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenFindSeeMore {
        public static final OpenFindSeeMore INSTANCE = new OpenFindSeeMore();
        public static final String contentId = "CONTENT_ID";
        public static final String title = "TITLE";

        private OpenFindSeeMore() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/find/skyfire/Contexts$OpenSearch;", "", "()V", "mode", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenSearch {
        public static final OpenSearch INSTANCE = new OpenSearch();
        public static final String mode = "MODE";

        private OpenSearch() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/find/skyfire/Contexts$OpenAlexa;", "", "()V", "pageType", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenAlexa {
        public static final OpenAlexa INSTANCE = new OpenAlexa();
        public static final String pageType = "PAGE_TYPE";

        private OpenAlexa() {
        }
    }
}
