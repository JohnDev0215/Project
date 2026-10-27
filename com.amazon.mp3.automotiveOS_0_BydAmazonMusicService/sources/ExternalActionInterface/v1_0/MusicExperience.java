package ExternalActionInterface.v1_0;

import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableMusicExperience.class)
@JsonSerialize(as = ImmutableMusicExperience.class)
@Value.Immutable
public abstract class MusicExperience extends PlayableContent {
    @Nullable
    public abstract String artistArtwork();

    @Nullable
    public abstract String artistAsin();

    @Nullable
    public abstract String artistName();

    @Nullable
    public abstract String experienceId();

    @Nullable
    public abstract PlayableContent featuredContent();

    @Nullable
    public abstract Boolean isExpired();

    @Nullable
    public abstract String musicExperienceArtwork();

    @Nullable
    public abstract String musicExperiencePlaybackContent();

    @Nullable
    public abstract MusicExperienceType musicExperienceType();
}
