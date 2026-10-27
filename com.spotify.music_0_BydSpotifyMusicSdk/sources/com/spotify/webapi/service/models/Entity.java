package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.webapi.service.models.Search;
import com.spotify.webapi.service.models.views.Link;
import com.spotify.webapi.service.models.views.Station;
import com.spotify.webapi.service.models.views.View;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.mw1;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
public abstract class Entity {
    public static final Companion Companion = new Companion(null);
    public String type;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getPolymorphicAdapter$annotations() {
        }

        public final mw1<Entity> getPolymorphicAdapter() {
            mw1<Entity> mw1VarC = mw1.b(Entity.class, RxProductState.Keys.KEY_TYPE).c(View.class, Search.Type.VIEW).c(Link.class, Search.Type.LINK).c(Station.class, Search.Type.STATION).c(PlaylistSimple.class, Search.Type.PLAYLIST).c(Artist.class, Search.Type.ARTIST).c(AlbumSimple.class, Search.Type.ALBUM).c(Track.class, Search.Type.TRACK).c(ShowSimple.class, Search.Type.SHOW).c(Episode.class, Search.Type.EPISODE);
            v03.i(mw1VarC, "of(Entity::class.java, \"…ava, Search.Type.EPISODE)");
            return mw1VarC;
        }

        public final boolean isPresentValidAndPlayable(Entity entity) {
            return entity != null && entity.isPlayable() && entity.isValid();
        }
    }

    public static final mw1<Entity> getPolymorphicAdapter() {
        return Companion.getPolymorphicAdapter();
    }

    @z61(name = RxProductState.Keys.KEY_TYPE)
    public static /* synthetic */ void getType$annotations() {
    }

    public static final boolean isPresentValidAndPlayable(Entity entity) {
        return Companion.isPresentValidAndPlayable(entity);
    }

    public Pager<Entity> getChildren() {
        return null;
    }

    public boolean hasChildren() {
        return false;
    }

    public abstract String href();

    public abstract String id();

    public abstract List<Image> images();

    public boolean isExplicit() {
        return false;
    }

    public boolean isPlayable() {
        return false;
    }

    public final boolean isValid() {
        String strUri;
        String strName = name();
        return (strName == null || strName.length() == 0 || (strUri = uri()) == null || strUri.length() == 0) ? false : true;
    }

    public final boolean isValidAndPlayable() {
        return isPlayable() && isValid();
    }

    public abstract String name();

    public abstract String uri();
}
