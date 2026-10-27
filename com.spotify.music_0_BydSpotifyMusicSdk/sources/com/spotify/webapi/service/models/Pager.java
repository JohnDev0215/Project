package com.spotify.webapi.service.models;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.l6;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class Pager<T> {
    public static final Companion Companion = new Companion(null);
    public String href;
    public List<T> items;
    public int limit;
    public String next;
    public int offset;
    public String previous;
    public int total;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T> Pager<T> fromItems(List<T> list) {
            v03.j(list, "items");
            Pager<T> pager = new Pager<>();
            pager.items = list;
            return pager;
        }
    }

    public static final <T> Pager<T> fromItems(List<T> list) {
        return Companion.fromItems(list);
    }

    @z61(name = "href")
    public static /* synthetic */ void getHref$annotations() {
    }

    @z61(name = "items")
    public static /* synthetic */ void getItems$annotations() {
    }

    @z61(name = "limit")
    public static /* synthetic */ void getLimit$annotations() {
    }

    @z61(name = "next")
    public static /* synthetic */ void getNext$annotations() {
    }

    @z61(name = "offset")
    public static /* synthetic */ void getOffset$annotations() {
    }

    @z61(name = "previous")
    public static /* synthetic */ void getPrevious$annotations() {
    }

    @z61(name = "total")
    public static /* synthetic */ void getTotal$annotations() {
    }
}
