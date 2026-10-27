package com.amazon.video.sdk.avod.playbackclient.subtitle.views.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.playbackclient.subtitle.SubtitlePresetSource;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderer;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPreset;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPresets;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitleDefaults;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePreset;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePresets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleRenderPresetsAivFactory {
    public static final ImmutableMap<SubtitlePreset.Edge, EdgeRenderer.EdgeType> EDGE_TYPE_MAP = (ImmutableMap) Preconditions2.checkFullKeyMapping(SubtitlePreset.Edge.class, ImmutableMap.builder().put(SubtitlePreset.Edge.NONE, EdgeRenderer.EdgeType.NONE).put(SubtitlePreset.Edge.RAISED, EdgeRenderer.EdgeType.RAISED).put(SubtitlePreset.Edge.DEPRESSED, EdgeRenderer.EdgeType.DEPRESSED).put(SubtitlePreset.Edge.UNIFORM, EdgeRenderer.EdgeType.UNIFORM).put(SubtitlePreset.Edge.DROP_SHADOW, EdgeRenderer.EdgeType.RIGHT_DROP_SHADOW).build());
    private final Context mContext;
    private final PresetIconRetriever mIconRetriever;
    private final TypefaceFactory mTypefaceFactory;

    public SubtitleRenderPresetsAivFactory(@Nonnull Context context) {
        this(context, new PresetIconRetriever(), TypefaceFactory.getInstance());
    }

    SubtitleRenderPresetsAivFactory(@Nonnull Context context, @Nonnull PresetIconRetriever presetIconRetriever, @Nonnull TypefaceFactory typefaceFactory) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context");
        this.mIconRetriever = (PresetIconRetriever) Preconditions.checkNotNull(presetIconRetriever, "PresetIconRetriever");
        this.mTypefaceFactory = (TypefaceFactory) Preconditions.checkNotNull(typefaceFactory, "TypefaceFactory");
    }

    @Nonnull
    public SubtitleRenderPresets create(@Nonnull SubtitlePresets subtitlePresets) {
        Preconditions.checkNotNull(subtitlePresets, "SubtitlePresets");
        ImmutableList.Builder builder = ImmutableList.builder();
        UnmodifiableIterator<SubtitlePreset> it = subtitlePresets.getPresets().iterator();
        while (it.hasNext()) {
            builder.add(create(it.next()));
        }
        return new SubtitleRenderPresets(SubtitlePresetSource.AIV_SERVICE, builder.build());
    }

    @Nonnull
    private SubtitleRenderPreset create(@Nonnull SubtitlePreset subtitlePreset) {
        Preconditions.checkNotNull(subtitlePreset);
        return new SubtitleRenderPreset(subtitlePreset.getPresetName(), this.mIconRetriever.retrieveIcon(this.mContext, subtitlePreset), this.mTypefaceFactory.getTypefaceFromFamily(this.mContext, subtitlePreset.getTextFont()), this.mTypefaceFactory.getTypefaceTransformation(subtitlePreset.getTextFont()), EDGE_TYPE_MAP.get(subtitlePreset.getTextEdge()), subtitlePreset.getTextSize(), SubtitleDefaults.toArgbColor(subtitlePreset.getTextColor(), subtitlePreset.getTextOpacity()), SubtitleDefaults.toArgbColor(subtitlePreset.getBackgroundColor(), subtitlePreset.getBackgroundOpacity()), SubtitleDefaults.toArgbColor(subtitlePreset.getWindowColor(), subtitlePreset.getWindowOpacity()));
    }

    static class PresetIconRetriever {
        private final StorageHelper mStorageHelper;
        private final SubtitleConfig mSubtitleConfig;

        public PresetIconRetriever() {
            this(StorageHelper.getInstance(), SubtitleConfig.getInstance());
        }

        PresetIconRetriever(StorageHelper storageHelper, SubtitleConfig subtitleConfig) {
            this.mStorageHelper = storageHelper;
            this.mSubtitleConfig = subtitleConfig;
        }

        @Nonnull
        public Drawable retrieveIcon(@Nonnull Context context, @Nonnull SubtitlePreset subtitlePreset) {
            if (subtitlePreset.getIconUrl() == null) {
                return getPlaceholder(context, subtitlePreset);
            }
            File file = new File(new File(this.mStorageHelper.getGeneralFileDir(), this.mSubtitleConfig.getRelativePath()), subtitlePreset.getNormalizedIconFilename());
            if (!file.exists()) {
                return getPlaceholder(context, subtitlePreset);
            }
            Drawable drawableCreateFromPath = Drawable.createFromPath(file.getAbsolutePath());
            return drawableCreateFromPath != null ? drawableCreateFromPath : getPlaceholder(context, subtitlePreset);
        }

        private Drawable getPlaceholder(Context context, SubtitlePreset subtitlePreset) {
            DLog.logf("Could not retrieve subtitle preset icon for preset %s, using placeholder", subtitlePreset.getPresetName());
            return context.getResources().getDrawable(subtitlePreset.getPlaceholderResId());
        }
    }
}
