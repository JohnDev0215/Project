package com.amazon.music.curate.skyfire.views.galleryTemplate;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.EntityIdType;
import ExternalActionInterface.v1_0.ExternalAction;
import ExternalActionInterface.v1_0.PlayMode;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.content.Context;
import com.amazon.music.curate.R;
import com.amazon.music.curate.provider.EntityType;
import com.amazon.music.curate.provider.MusicItem;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.local.InvokeLocalSkillMethod;
import com.amazon.music.curate.skyfire.local.LocalSkillContext;
import com.amazon.music.curate.skyfire.local.PlayAlbumSkillContext;
import com.amazon.music.curate.skyfire.local.PlayArtistSkillContext;
import com.amazon.music.curate.skyfire.local.PlayCatalogPlaylistSkillContext;
import com.amazon.music.curate.skyfire.local.PlayLibraryPlaylistSkillContext;
import com.amazon.music.curate.skyfire.local.PlayStationSkillContext;
import com.amazon.music.curate.skyfire.local.ShowCatalogPlaylistSkillContext;
import com.amazon.music.curate.skyfire.local.ShowLibraryPlaylistSkillContext;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class VerticalItemEntity {
    private final Context context;
    private final CurateBootstrapProviders curateBootstrapProviders;
    private final PlaybackProvider playbackProvider;

    public VerticalItemEntity(final Context context, final CurateBootstrapProviders curateBootstrapProviders) {
        this.context = context;
        this.curateBootstrapProviders = curateBootstrapProviders;
        this.playbackProvider = curateBootstrapProviders.getPlaybackProvider();
    }

    public int getPlaceholderId(final VerticalItemElement element) {
        MusicItem musicItem;
        if (element.onItemSelected().isEmpty()) {
            musicItem = null;
        } else {
            musicItem = null;
            for (Method method : element.onItemSelected()) {
                if (method instanceof InvokeLocalSkillMethod) {
                    musicItem = getMusicItem(((InvokeLocalSkillMethod) method).getSkillContext());
                } else if (method instanceof TriggerExternalActionMethod) {
                    musicItem = getMusicItem((TriggerExternalActionMethod) method);
                }
            }
        }
        if (musicItem != null) {
            return getPlaceholderIdByContentType(musicItem.getEntityType());
        }
        return getPlaceholderIdByContentType(null);
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemEntity$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$curate$provider$EntityType;

        static {
            int[] iArr = new int[EntityType.values().length];
            $SwitchMap$com$amazon$music$curate$provider$EntityType = iArr;
            try {
                iArr[EntityType.ALBUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$provider$EntityType[EntityType.ARTIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$provider$EntityType[EntityType.USER_PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$provider$EntityType[EntityType.CATALOG_PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$provider$EntityType[EntityType.STATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public int getPlaceholderIdByContentType(final EntityType entityType) {
        if (entityType == null) {
            return R.drawable.placeholder;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$curate$provider$EntityType[entityType.ordinal()];
        if (i == 1) {
            return R.drawable.album_placeholder;
        }
        if (i == 2) {
            return R.drawable.artist_placeholder;
        }
        if (i == 3 || i == 4) {
            return R.drawable.playlist_placeholder;
        }
        if (i == 5) {
            return R.drawable.station_placeholder;
        }
        return R.drawable.placeholder;
    }

    public MusicItem getMusicItem(final LocalSkillContext skillContext) {
        Map<String, String> contextMap;
        EntityType entityType;
        EntityIdType entityIdType;
        String entityId;
        if (skillContext == null || (contextMap = skillContext.getContextMap()) == null || contextMap.isEmpty()) {
            return null;
        }
        if (skillContext instanceof PlayStationSkillContext) {
            entityType = EntityType.STATION;
            entityIdType = EntityIdType.STATION_KEY;
            entityId = getEntityId(contextMap, entityIdType);
            if (StringUtils.isBlank(entityId)) {
                entityIdType = EntityIdType.STATION_SEED_ID;
                entityId = getEntityId(contextMap, entityIdType);
            }
        } else if (skillContext instanceof PlayArtistSkillContext) {
            entityType = EntityType.ARTIST;
            entityIdType = EntityIdType.ASIN;
            entityId = getEntityId(contextMap, entityIdType);
        } else if (skillContext instanceof PlayAlbumSkillContext) {
            entityType = EntityType.ALBUM;
            entityIdType = EntityIdType.ASIN;
            entityId = getEntityId(contextMap, entityIdType);
        } else if ((skillContext instanceof ShowCatalogPlaylistSkillContext) || (skillContext instanceof PlayCatalogPlaylistSkillContext)) {
            entityType = EntityType.CATALOG_PLAYLIST;
            entityIdType = EntityIdType.ASIN;
            entityId = getEntityId(contextMap, entityIdType);
        } else if ((skillContext instanceof ShowLibraryPlaylistSkillContext) || (skillContext instanceof PlayLibraryPlaylistSkillContext)) {
            entityType = EntityType.USER_PLAYLIST;
            entityIdType = EntityIdType.OBJECT_ID;
            entityId = getEntityId(contextMap, entityIdType);
        } else {
            entityType = null;
            entityIdType = null;
            entityId = null;
        }
        if (entityIdType == null || !StringUtils.isNotBlank(entityId)) {
            return null;
        }
        return new MusicItem(entityType, entityIdType, entityId);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a1 A[PHI: r1 r2 r3
  0x00a1: PHI (r1v6 com.amazon.music.curate.provider.EntityType) = (r1v2 com.amazon.music.curate.provider.EntityType), (r1v10 com.amazon.music.curate.provider.EntityType) binds: [B:42:0x00e5, B:30:0x0098] A[DONT_GENERATE, DONT_INLINE]
  0x00a1: PHI (r2v22 ExternalActionInterface.v1_0.EntityIdType) = (r2v18 ExternalActionInterface.v1_0.EntityIdType), (r2v24 ExternalActionInterface.v1_0.EntityIdType) binds: [B:42:0x00e5, B:30:0x0098] A[DONT_GENERATE, DONT_INLINE]
  0x00a1: PHI (r3v1 java.lang.String) = (r3v0 java.lang.String), (r3v2 java.lang.String) binds: [B:42:0x00e5, B:30:0x0098] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.music.curate.provider.MusicItem getMusicItem(final ExternalActionInterface.v1_0.TriggerExternalActionMethod r6) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemEntity.getMusicItem(ExternalActionInterface.v1_0.TriggerExternalActionMethod):com.amazon.music.curate.provider.MusicItem");
    }

    public EntityType getPlayableEntityType(final LocalSkillContext skillContext) {
        Map<String, String> contextMap;
        if (skillContext == null || (contextMap = skillContext.getContextMap()) == null || contextMap.isEmpty()) {
            return null;
        }
        if ((skillContext instanceof PlayStationSkillContext) || (skillContext instanceof PlayArtistSkillContext) || (skillContext instanceof PlayAlbumSkillContext) || (skillContext instanceof PlayCatalogPlaylistSkillContext) || (skillContext instanceof PlayLibraryPlaylistSkillContext)) {
            return getMusicItem(skillContext).getEntityType();
        }
        return null;
    }

    public EntityType getPlayableEntityType(final TriggerExternalActionMethod method) {
        if (method == null) {
            return null;
        }
        String strName = method.name();
        Map<String, String> mapContext = method.context();
        if (mapContext == null || mapContext.isEmpty()) {
            return null;
        }
        if (ExternalAction.PLAY_ALBUM.name().equals(strName) || ExternalAction.PLAY_ARTIST.name().equals(strName) || ExternalAction.PLAY_STATION.name().equals(strName) || ExternalAction.PLAY_PLAYLIST.name().equals(strName)) {
            return getMusicItem(method).getEntityType();
        }
        return null;
    }

    public int getPlayIcon(final EntityType entityType, final VerticalItemElement element) {
        boolean zShowShuffleIcon;
        boolean zShowStationIcon;
        Map<String, String> mapContext;
        if (element.onItemSelected().isEmpty()) {
            zShowShuffleIcon = false;
            zShowStationIcon = false;
        } else {
            zShowShuffleIcon = false;
            zShowStationIcon = false;
            for (Method method : element.onItemSelected()) {
                if (method instanceof InvokeLocalSkillMethod) {
                    Map<String, String> contextMap = ((InvokeLocalSkillMethod) method).getSkillContext().getContextMap();
                    if (contextMap != null && !contextMap.isEmpty()) {
                        zShowShuffleIcon = showShuffleIcon(contextMap);
                        zShowStationIcon = showStationIcon(entityType, contextMap);
                    }
                } else if ((method instanceof TriggerExternalActionMethod) && (mapContext = ((TriggerExternalActionMethod) method).context()) != null && !mapContext.isEmpty()) {
                    zShowShuffleIcon = showShuffleIcon(mapContext);
                    zShowStationIcon = showStationIcon(entityType, mapContext);
                }
            }
        }
        if (zShowShuffleIcon) {
            return 2;
        }
        return (zShowStationIcon || AnonymousClass1.$SwitchMap$com$amazon$music$curate$provider$EntityType[entityType.ordinal()] == 5) ? 1 : 0;
    }

    private boolean showShuffleIcon(final Map<String, String> contextMap) {
        String str = contextMap.get(PlayMode.PLAY_MODE);
        if (contextMap.isEmpty()) {
            return false;
        }
        return PlayMode.AUGMENTED_SHUFFLE.name().equalsIgnoreCase(str) || PlayMode.ON_DEMAND.name().equalsIgnoreCase(str);
    }

    private boolean showStationIcon(final EntityType entityType, final Map<String, String> contextMap) {
        return (contextMap == null || contextMap.isEmpty() || !PlayMode.FREE_STATION.name().equalsIgnoreCase(contextMap.get(PlayMode.PLAY_MODE))) ? false : true;
    }

    private String getEntityId(final Map<String, String> idMap, final EntityIdType entityIdType) {
        if (idMap == null || idMap.isEmpty() || entityIdType == null) {
            return null;
        }
        return idMap.get(entityIdType.name());
    }
}
