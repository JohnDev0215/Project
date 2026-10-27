package com.spotify.playerlimited.player.moshi;

import androidx.annotation.Keep;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import p.s71;

/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Keep
@s71
@Retention(RetentionPolicy.RUNTIME)
public @interface MoshiRootName {
    String value();
}
