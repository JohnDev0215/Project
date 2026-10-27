package com.amazon.mp3.library.util;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.text.Regex;

/* JADX INFO: compiled from: EntityValidatorUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006R\u001b\u0010\f\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\b\u001a\u0004\b\r\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/library/util/EntityValidatorUtil;", "", "()V", "personalizedPlaylistRegex", "Lkotlin/text/Regex;", "getPersonalizedPlaylistRegex", "()Lkotlin/text/Regex;", "personalizedPlaylistRegex$delegate", "Lkotlin/Lazy;", "strictAsinRegex", "getStrictAsinRegex", "strictAsinRegex$delegate", "uuidRegex", "getUuidRegex", "uuidRegex$delegate", "isPersonalizedPlaylistId", "", "input", "", "isStrictAsin", "isUUID", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityValidatorUtil {
    public static final EntityValidatorUtil INSTANCE = new EntityValidatorUtil();

    /* JADX INFO: renamed from: strictAsinRegex$delegate, reason: from kotlin metadata */
    private static final Lazy strictAsinRegex = LazyKt__LazyJVMKt.lazy(new Function0<Regex>() { // from class: com.amazon.mp3.library.util.EntityValidatorUtil$strictAsinRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return new Regex("^[A-Za-z0-9]{10}$");
        }
    });

    /* JADX INFO: renamed from: uuidRegex$delegate, reason: from kotlin metadata */
    private static final Lazy uuidRegex = LazyKt__LazyJVMKt.lazy(new Function0<Regex>() { // from class: com.amazon.mp3.library.util.EntityValidatorUtil$uuidRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return new Regex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$");
        }
    });

    /* JADX INFO: renamed from: personalizedPlaylistRegex$delegate, reason: from kotlin metadata */
    private static final Lazy personalizedPlaylistRegex = LazyKt__LazyJVMKt.lazy(new Function0<Regex>() { // from class: com.amazon.mp3.library.util.EntityValidatorUtil$personalizedPlaylistRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return new Regex("^PP:?([A-Z0-9]){8}-?([A-Z0-9]){4}-?([A-Z0-9]){4}-?([A-Z0-9]){4}-?([A-Z0-9]){12}$");
        }
    });

    private EntityValidatorUtil() {
    }

    private final Regex getStrictAsinRegex() {
        return (Regex) strictAsinRegex.getValue();
    }

    private final Regex getUuidRegex() {
        return (Regex) uuidRegex.getValue();
    }

    private final Regex getPersonalizedPlaylistRegex() {
        return (Regex) personalizedPlaylistRegex.getValue();
    }

    @JvmStatic
    public static final boolean isStrictAsin(String input) {
        return input != null && INSTANCE.getStrictAsinRegex().matches(input);
    }

    @JvmStatic
    public static final boolean isUUID(String input) {
        return input != null && INSTANCE.getUuidRegex().matches(input);
    }

    @JvmStatic
    public static final boolean isPersonalizedPlaylistId(String input) {
        return input != null && INSTANCE.getPersonalizedPlaylistRegex().matches(input);
    }
}
