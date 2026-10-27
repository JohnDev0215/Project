package Touch.PlaybackTemplateInterface.v1_0;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import ExternalActionInterface.v1_0.PlayableContent;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "PlaybackTemplate", generator = "Immutables")
public final class ImmutablePlaybackTemplate extends PlaybackTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<ButtonElement> actionButtons;

    @Nullable
    private final String backgroundImage;
    private final Long cacheSeconds;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onAudioEnded;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onSwipedUp;
    private final List<Method> onViewed;
    private final PlayableContent playableContent;
    private final String primaryText;
    private final List<Element> secondaryButtons;

    @Nullable
    private final String secondaryText;

    @Nullable
    private final String tertiaryText;

    private ImmutablePlaybackTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.primaryText = builder.primaryText;
        this.secondaryText = builder.secondaryText;
        this.tertiaryText = builder.tertiaryText;
        this.playableContent = builder.playableContent;
        this.backgroundImage = builder.backgroundImage;
        if (builder.onCreatedIsSet()) {
            this.initShim.onCreated(createUnmodifiableList(true, builder.onCreated));
        }
        if (builder.onBoundIsSet()) {
            this.initShim.onBound(createUnmodifiableList(true, builder.onBound));
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.launchMode != null) {
            this.initShim.launchMode(builder.launchMode);
        }
        if (builder.cacheSeconds != null) {
            this.initShim.cacheSeconds(builder.cacheSeconds);
        }
        if (builder.actionButtonsIsSet()) {
            this.initShim.actionButtons(createUnmodifiableList(true, builder.actionButtons));
        }
        if (builder.secondaryButtonsIsSet()) {
            this.initShim.secondaryButtons(createUnmodifiableList(true, builder.secondaryButtons));
        }
        if (builder.onAudioEndedIsSet()) {
            this.initShim.onAudioEnded(createUnmodifiableList(true, builder.onAudioEnded));
        }
        if (builder.onSwipedUpIsSet()) {
            this.initShim.onSwipedUp(createUnmodifiableList(true, builder.onSwipedUp));
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.actionButtons = this.initShim.actionButtons();
        this.secondaryButtons = this.initShim.secondaryButtons();
        this.onAudioEnded = this.initShim.onAudioEnded();
        this.onSwipedUp = this.initShim.onSwipedUp();
        this.initShim = null;
    }

    private ImmutablePlaybackTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, String str, @Nullable String str2, @Nullable String str3, PlayableContent playableContent, @Nullable String str4, List<ButtonElement> list4, List<Element> list5, List<Method> list6, List<Method> list7) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.primaryText = str;
        this.secondaryText = str2;
        this.tertiaryText = str3;
        this.playableContent = playableContent;
        this.backgroundImage = str4;
        this.actionButtons = list4;
        this.secondaryButtons = list5;
        this.onAudioEnded = list6;
        this.onSwipedUp = list7;
        this.initShim = null;
    }

    @Generated(from = "PlaybackTemplate", generator = "Immutables")
    private final class InitShim {
        private List<ButtonElement> actionButtons;
        private byte actionButtonsBuildStage;
        private Long cacheSeconds;
        private byte cacheSecondsBuildStage;
        private TemplateLaunchMode launchMode;
        private byte launchModeBuildStage;
        private List<Method> onAudioEnded;
        private byte onAudioEndedBuildStage;
        private List<Method> onBound;
        private byte onBoundBuildStage;
        private List<Method> onCreated;
        private byte onCreatedBuildStage;
        private List<Method> onSwipedUp;
        private byte onSwipedUpBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private List<Element> secondaryButtons;
        private byte secondaryButtonsBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
            this.actionButtonsBuildStage = (byte) 0;
            this.secondaryButtonsBuildStage = (byte) 0;
            this.onAudioEndedBuildStage = (byte) 0;
            this.onSwipedUpBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutablePlaybackTemplate.createUnmodifiableList(false, ImmutablePlaybackTemplate.createSafeList(ImmutablePlaybackTemplate.super.onCreated(), true, false));
                this.onCreatedBuildStage = (byte) 1;
            }
            return this.onCreated;
        }

        void onCreated(List<Method> list) {
            this.onCreated = list;
            this.onCreatedBuildStage = (byte) 1;
        }

        List<Method> onBound() {
            byte b = this.onBoundBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onBoundBuildStage = (byte) -1;
                this.onBound = ImmutablePlaybackTemplate.createUnmodifiableList(false, ImmutablePlaybackTemplate.createSafeList(ImmutablePlaybackTemplate.super.onBound(), true, false));
                this.onBoundBuildStage = (byte) 1;
            }
            return this.onBound;
        }

        void onBound(List<Method> list) {
            this.onBound = list;
            this.onBoundBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutablePlaybackTemplate.createUnmodifiableList(false, ImmutablePlaybackTemplate.createSafeList(ImmutablePlaybackTemplate.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        TemplateLaunchMode launchMode() {
            byte b = this.launchModeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.launchModeBuildStage = (byte) -1;
                this.launchMode = (TemplateLaunchMode) ImmutablePlaybackTemplate.requireNonNull(ImmutablePlaybackTemplate.super.launchMode(), "launchMode");
                this.launchModeBuildStage = (byte) 1;
            }
            return this.launchMode;
        }

        void launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = templateLaunchMode;
            this.launchModeBuildStage = (byte) 1;
        }

        Long cacheSeconds() {
            byte b = this.cacheSecondsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.cacheSecondsBuildStage = (byte) -1;
                this.cacheSeconds = (Long) ImmutablePlaybackTemplate.requireNonNull(ImmutablePlaybackTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
        }

        List<ButtonElement> actionButtons() {
            byte b = this.actionButtonsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.actionButtonsBuildStage = (byte) -1;
                this.actionButtons = ImmutablePlaybackTemplate.createUnmodifiableList(false, ImmutablePlaybackTemplate.createSafeList(ImmutablePlaybackTemplate.super.actionButtons(), true, false));
                this.actionButtonsBuildStage = (byte) 1;
            }
            return this.actionButtons;
        }

        void actionButtons(List<ButtonElement> list) {
            this.actionButtons = list;
            this.actionButtonsBuildStage = (byte) 1;
        }

        List<Element> secondaryButtons() {
            byte b = this.secondaryButtonsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.secondaryButtonsBuildStage = (byte) -1;
                this.secondaryButtons = ImmutablePlaybackTemplate.createUnmodifiableList(false, ImmutablePlaybackTemplate.createSafeList(ImmutablePlaybackTemplate.super.secondaryButtons(), true, false));
                this.secondaryButtonsBuildStage = (byte) 1;
            }
            return this.secondaryButtons;
        }

        void secondaryButtons(List<Element> list) {
            this.secondaryButtons = list;
            this.secondaryButtonsBuildStage = (byte) 1;
        }

        List<Method> onAudioEnded() {
            byte b = this.onAudioEndedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onAudioEndedBuildStage = (byte) -1;
                this.onAudioEnded = ImmutablePlaybackTemplate.createUnmodifiableList(false, ImmutablePlaybackTemplate.createSafeList(ImmutablePlaybackTemplate.super.onAudioEnded(), true, false));
                this.onAudioEndedBuildStage = (byte) 1;
            }
            return this.onAudioEnded;
        }

        void onAudioEnded(List<Method> list) {
            this.onAudioEnded = list;
            this.onAudioEndedBuildStage = (byte) 1;
        }

        List<Method> onSwipedUp() {
            byte b = this.onSwipedUpBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onSwipedUpBuildStage = (byte) -1;
                this.onSwipedUp = ImmutablePlaybackTemplate.createUnmodifiableList(false, ImmutablePlaybackTemplate.createSafeList(ImmutablePlaybackTemplate.super.onSwipedUp(), true, false));
                this.onSwipedUpBuildStage = (byte) 1;
            }
            return this.onSwipedUp;
        }

        void onSwipedUp(List<Method> list) {
            this.onSwipedUp = list;
            this.onSwipedUpBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.onCreatedBuildStage == -1) {
                arrayList.add("onCreated");
            }
            if (this.onBoundBuildStage == -1) {
                arrayList.add("onBound");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.launchModeBuildStage == -1) {
                arrayList.add("launchMode");
            }
            if (this.cacheSecondsBuildStage == -1) {
                arrayList.add("cacheSeconds");
            }
            if (this.actionButtonsBuildStage == -1) {
                arrayList.add("actionButtons");
            }
            if (this.secondaryButtonsBuildStage == -1) {
                arrayList.add("secondaryButtons");
            }
            if (this.onAudioEndedBuildStage == -1) {
                arrayList.add("onAudioEnded");
            }
            if (this.onSwipedUpBuildStage == -1) {
                arrayList.add("onSwipedUp");
            }
            return "Cannot build PlaybackTemplate, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onCreated")
    public List<Method> onCreated() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onCreated();
        }
        return this.onCreated;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onBound")
    public List<Method> onBound() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onBound();
        }
        return this.onBound;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("launchMode")
    public TemplateLaunchMode launchMode() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.launchMode();
        }
        return this.launchMode;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("cacheSeconds")
    public Long cacheSeconds() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.cacheSeconds();
        }
        return this.cacheSeconds;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
    @Nullable
    public String tertiaryText() {
        return this.tertiaryText;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty("playableContent")
    public PlayableContent playableContent() {
        return this.playableContent;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty("backgroundImage")
    @Nullable
    public String backgroundImage() {
        return this.backgroundImage;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty("actionButtons")
    public List<ButtonElement> actionButtons() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.actionButtons();
        }
        return this.actionButtons;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty("secondaryButtons")
    public List<Element> secondaryButtons() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.secondaryButtons();
        }
        return this.secondaryButtons;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty("onAudioEnded")
    public List<Method> onAudioEnded() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onAudioEnded();
        }
        return this.onAudioEnded;
    }

    @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
    @JsonProperty("onSwipedUp")
    public List<Method> onSwipedUp() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onSwipedUp();
        }
        return this.onSwipedUp;
    }

    public final ImmutablePlaybackTemplate withOnCreated(Method... methodArr) {
        return new ImmutablePlaybackTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutablePlaybackTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withOnBound(Method... methodArr) {
        return new ImmutablePlaybackTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutablePlaybackTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withOnViewed(Method... methodArr) {
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str2, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, str, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, str, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withPlayableContent(PlayableContent playableContent) {
        if (this.playableContent == playableContent) {
            return this;
        }
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, (PlayableContent) requireNonNull(playableContent, "playableContent"), this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withBackgroundImage(@Nullable String str) {
        return equals(this.backgroundImage, str) ? this : new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, str, this.actionButtons, this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withActionButtons(ButtonElement... buttonElementArr) {
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, createUnmodifiableList(false, createSafeList(Arrays.asList(buttonElementArr), true, false)), this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withActionButtons(Iterable<? extends ButtonElement> iterable) {
        if (this.actionButtons == iterable) {
            return this;
        }
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.secondaryButtons, this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withSecondaryButtons(Element... elementArr) {
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, createUnmodifiableList(false, createSafeList(Arrays.asList(elementArr), true, false)), this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withSecondaryButtons(Iterable<? extends Element> iterable) {
        if (this.secondaryButtons == iterable) {
            return this;
        }
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onAudioEnded, this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withOnAudioEnded(Method... methodArr) {
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withOnAudioEnded(Iterable<? extends Method> iterable) {
        if (this.onAudioEnded == iterable) {
            return this;
        }
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onSwipedUp);
    }

    public final ImmutablePlaybackTemplate withOnSwipedUp(Method... methodArr) {
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutablePlaybackTemplate withOnSwipedUp(Iterable<? extends Method> iterable) {
        if (this.onSwipedUp == iterable) {
            return this;
        }
        return new ImmutablePlaybackTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryText, this.secondaryText, this.tertiaryText, this.playableContent, this.backgroundImage, this.actionButtons, this.secondaryButtons, this.onAudioEnded, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlaybackTemplate) && equalTo(0, (ImmutablePlaybackTemplate) obj);
    }

    private boolean equalTo(int i, ImmutablePlaybackTemplate immutablePlaybackTemplate) {
        return this.onCreated.equals(immutablePlaybackTemplate.onCreated) && this.onBound.equals(immutablePlaybackTemplate.onBound) && this.onViewed.equals(immutablePlaybackTemplate.onViewed) && this.launchMode.equals(immutablePlaybackTemplate.launchMode) && this.cacheSeconds.equals(immutablePlaybackTemplate.cacheSeconds) && this.primaryText.equals(immutablePlaybackTemplate.primaryText) && equals(this.secondaryText, immutablePlaybackTemplate.secondaryText) && equals(this.tertiaryText, immutablePlaybackTemplate.tertiaryText) && this.playableContent.equals(immutablePlaybackTemplate.playableContent) && equals(this.backgroundImage, immutablePlaybackTemplate.backgroundImage) && this.actionButtons.equals(immutablePlaybackTemplate.actionButtons) && this.secondaryButtons.equals(immutablePlaybackTemplate.secondaryButtons) && this.onAudioEnded.equals(immutablePlaybackTemplate.onAudioEnded) && this.onSwipedUp.equals(immutablePlaybackTemplate.onSwipedUp);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.primaryText.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.secondaryText);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.tertiaryText);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + this.playableContent.hashCode();
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.backgroundImage);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + this.actionButtons.hashCode();
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + this.secondaryButtons.hashCode();
        int iHashCode13 = iHashCode12 + (iHashCode12 << 5) + this.onAudioEnded.hashCode();
        return iHashCode13 + (iHashCode13 << 5) + this.onSwipedUp.hashCode();
    }

    public String toString() {
        return "PlaybackTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", playableContent=" + this.playableContent + ", backgroundImage=" + this.backgroundImage + ", actionButtons=" + this.actionButtons + ", secondaryButtons=" + this.secondaryButtons + ", onAudioEnded=" + this.onAudioEnded + ", onSwipedUp=" + this.onSwipedUp + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlaybackTemplate", generator = "Immutables")
    static final class Json extends PlaybackTemplate {
        boolean actionButtonsIsSet;

        @Nullable
        String backgroundImage;

        @Nullable
        Long cacheSeconds;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onAudioEndedIsSet;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onSwipedUpIsSet;
        boolean onViewedIsSet;

        @Nullable
        PlayableContent playableContent;

        @Nullable
        String primaryText;
        boolean secondaryButtonsIsSet;

        @Nullable
        String secondaryText;

        @Nullable
        String tertiaryText;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<ButtonElement> actionButtons = Collections.emptyList();

        @Nullable
        List<Element> secondaryButtons = Collections.emptyList();

        @Nullable
        List<Method> onAudioEnded = Collections.emptyList();

        @Nullable
        List<Method> onSwipedUp = Collections.emptyList();

        Json() {
        }

        @JsonProperty("onCreated")
        public void setOnCreated(List<Method> list) {
            this.onCreated = list;
            this.onCreatedIsSet = true;
        }

        @JsonProperty("onBound")
        public void setOnBound(List<Method> list) {
            this.onBound = list;
            this.onBoundIsSet = true;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @JsonProperty("launchMode")
        public void setLaunchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = templateLaunchMode;
        }

        @JsonProperty("cacheSeconds")
        public void setCacheSeconds(Long l) {
            this.cacheSeconds = l;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(@Nullable String str) {
            this.secondaryText = str;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public void setTertiaryText(@Nullable String str) {
            this.tertiaryText = str;
        }

        @JsonProperty("playableContent")
        public void setPlayableContent(PlayableContent playableContent) {
            this.playableContent = playableContent;
        }

        @JsonProperty("backgroundImage")
        public void setBackgroundImage(@Nullable String str) {
            this.backgroundImage = str;
        }

        @JsonProperty("actionButtons")
        public void setActionButtons(List<ButtonElement> list) {
            this.actionButtons = list;
            this.actionButtonsIsSet = true;
        }

        @JsonProperty("secondaryButtons")
        public void setSecondaryButtons(List<Element> list) {
            this.secondaryButtons = list;
            this.secondaryButtonsIsSet = true;
        }

        @JsonProperty("onAudioEnded")
        public void setOnAudioEnded(List<Method> list) {
            this.onAudioEnded = list;
            this.onAudioEndedIsSet = true;
        }

        @JsonProperty("onSwipedUp")
        public void setOnSwipedUp(List<Method> list) {
            this.onSwipedUp = list;
            this.onSwipedUpIsSet = true;
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onCreated() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onBound() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public TemplateLaunchMode launchMode() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public Long cacheSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public String tertiaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public PlayableContent playableContent() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public String backgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public List<ButtonElement> actionButtons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public List<Element> secondaryButtons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public List<Method> onAudioEnded() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate
        public List<Method> onSwipedUp() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlaybackTemplate fromJson(Json json) {
        Builder builder = builder();
        if (json.onCreatedIsSet) {
            builder.addAllOnCreated(json.onCreated);
        }
        if (json.onBoundIsSet) {
            builder.addAllOnBound(json.onBound);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        TemplateLaunchMode templateLaunchMode = json.launchMode;
        if (templateLaunchMode != null) {
            builder.launchMode(templateLaunchMode);
        }
        Long l = json.cacheSeconds;
        if (l != null) {
            builder.cacheSeconds(l);
        }
        String str = json.primaryText;
        if (str != null) {
            builder.primaryText(str);
        }
        String str2 = json.secondaryText;
        if (str2 != null) {
            builder.secondaryText(str2);
        }
        String str3 = json.tertiaryText;
        if (str3 != null) {
            builder.tertiaryText(str3);
        }
        PlayableContent playableContent = json.playableContent;
        if (playableContent != null) {
            builder.playableContent(playableContent);
        }
        String str4 = json.backgroundImage;
        if (str4 != null) {
            builder.backgroundImage(str4);
        }
        if (json.actionButtonsIsSet) {
            builder.addAllActionButtons(json.actionButtons);
        }
        if (json.secondaryButtonsIsSet) {
            builder.addAllSecondaryButtons(json.secondaryButtons);
        }
        if (json.onAudioEndedIsSet) {
            builder.addAllOnAudioEnded(json.onAudioEnded);
        }
        if (json.onSwipedUpIsSet) {
            builder.addAllOnSwipedUp(json.onSwipedUp);
        }
        return builder.build();
    }

    public static ImmutablePlaybackTemplate copyOf(PlaybackTemplate playbackTemplate) {
        if (playbackTemplate instanceof ImmutablePlaybackTemplate) {
            return (ImmutablePlaybackTemplate) playbackTemplate;
        }
        return builder().from(playbackTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlaybackTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PLAYABLE_CONTENT = 2;
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long OPT_BIT_ACTION_BUTTONS = 8;
        private static final long OPT_BIT_ON_AUDIO_ENDED = 32;
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_SWIPED_UP = 64;
        private static final long OPT_BIT_ON_VIEWED = 4;
        private static final long OPT_BIT_SECONDARY_BUTTONS = 16;
        private List<ButtonElement> actionButtons;

        @Nullable
        private String backgroundImage;

        @Nullable
        private Long cacheSeconds;
        private long initBits;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onAudioEnded;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onSwipedUp;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private PlayableContent playableContent;

        @Nullable
        private String primaryText;
        private List<Element> secondaryButtons;

        @Nullable
        private String secondaryText;

        @Nullable
        private String tertiaryText;

        private Builder() {
            this.initBits = 3L;
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.actionButtons = new ArrayList();
            this.secondaryButtons = new ArrayList();
            this.onAudioEnded = new ArrayList();
            this.onSwipedUp = new ArrayList();
        }

        public final Builder from(Template template) {
            ImmutablePlaybackTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        public final Builder from(PlaybackTemplate playbackTemplate) {
            ImmutablePlaybackTemplate.requireNonNull(playbackTemplate, "instance");
            from((Object) playbackTemplate);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Template) {
                Template template = (Template) obj;
                addAllOnBound(template.onBound());
                launchMode(template.launchMode());
                addAllOnViewed(template.onViewed());
                addAllOnCreated(template.onCreated());
                cacheSeconds(template.cacheSeconds());
                j = 31;
            } else {
                j = 0;
            }
            if (obj instanceof PlaybackTemplate) {
                PlaybackTemplate playbackTemplate = (PlaybackTemplate) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(playbackTemplate.onBound());
                    j |= 1;
                }
                addAllSecondaryButtons(playbackTemplate.secondaryButtons());
                primaryText(playbackTemplate.primaryText());
                String strBackgroundImage = playbackTemplate.backgroundImage();
                if (strBackgroundImage != null) {
                    backgroundImage(strBackgroundImage);
                }
                addAllOnAudioEnded(playbackTemplate.onAudioEnded());
                addAllOnSwipedUp(playbackTemplate.onSwipedUp());
                addAllActionButtons(playbackTemplate.actionButtons());
                if ((j & 2) == 0) {
                    addAllOnViewed(playbackTemplate.onViewed());
                    j |= 2;
                }
                String strSecondaryText = playbackTemplate.secondaryText();
                if (strSecondaryText != null) {
                    secondaryText(strSecondaryText);
                }
                if ((j & 4) == 0) {
                    cacheSeconds(playbackTemplate.cacheSeconds());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    launchMode(playbackTemplate.launchMode());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    addAllOnCreated(playbackTemplate.onCreated());
                }
                playableContent(playbackTemplate.playableContent());
                String strTertiaryText = playbackTemplate.tertiaryText();
                if (strTertiaryText != null) {
                    tertiaryText(strTertiaryText);
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onCreated")
        public final Builder onCreated(Iterable<? extends Method> iterable) {
            this.onCreated.clear();
            return addAllOnCreated(iterable);
        }

        public final Builder addAllOnCreated(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onCreated.add((Method) ImmutablePlaybackTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onBound")
        public final Builder onBound(Iterable<? extends Method> iterable) {
            this.onBound.clear();
            return addAllOnBound(iterable);
        }

        public final Builder addAllOnBound(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onBound.add((Method) ImmutablePlaybackTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onViewed")
        public final Builder onViewed(Iterable<? extends Method> iterable) {
            this.onViewed.clear();
            return addAllOnViewed(iterable);
        }

        public final Builder addAllOnViewed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onViewed.add((Method) ImmutablePlaybackTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutablePlaybackTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutablePlaybackTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutablePlaybackTemplate.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(@Nullable String str) {
            this.secondaryText = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public final Builder tertiaryText(@Nullable String str) {
            this.tertiaryText = str;
            return this;
        }

        @JsonProperty("playableContent")
        public final Builder playableContent(PlayableContent playableContent) {
            this.playableContent = (PlayableContent) ImmutablePlaybackTemplate.requireNonNull(playableContent, "playableContent");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("backgroundImage")
        public final Builder backgroundImage(@Nullable String str) {
            this.backgroundImage = str;
            return this;
        }

        public final Builder addActionButtons(ButtonElement buttonElement) {
            this.actionButtons.add((ButtonElement) ImmutablePlaybackTemplate.requireNonNull(buttonElement, "actionButtons element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addActionButtons(ButtonElement... buttonElementArr) {
            for (ButtonElement buttonElement : buttonElementArr) {
                this.actionButtons.add((ButtonElement) ImmutablePlaybackTemplate.requireNonNull(buttonElement, "actionButtons element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("actionButtons")
        public final Builder actionButtons(Iterable<? extends ButtonElement> iterable) {
            this.actionButtons.clear();
            return addAllActionButtons(iterable);
        }

        public final Builder addAllActionButtons(Iterable<? extends ButtonElement> iterable) {
            Iterator<? extends ButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.actionButtons.add((ButtonElement) ImmutablePlaybackTemplate.requireNonNull(it.next(), "actionButtons element"));
            }
            this.optBits |= 8;
            return this;
        }

        public final Builder addSecondaryButtons(Element element) {
            this.secondaryButtons.add((Element) ImmutablePlaybackTemplate.requireNonNull(element, "secondaryButtons element"));
            this.optBits |= 16;
            return this;
        }

        public final Builder addSecondaryButtons(Element... elementArr) {
            for (Element element : elementArr) {
                this.secondaryButtons.add((Element) ImmutablePlaybackTemplate.requireNonNull(element, "secondaryButtons element"));
            }
            this.optBits |= 16;
            return this;
        }

        @JsonProperty("secondaryButtons")
        public final Builder secondaryButtons(Iterable<? extends Element> iterable) {
            this.secondaryButtons.clear();
            return addAllSecondaryButtons(iterable);
        }

        public final Builder addAllSecondaryButtons(Iterable<? extends Element> iterable) {
            Iterator<? extends Element> it = iterable.iterator();
            while (it.hasNext()) {
                this.secondaryButtons.add((Element) ImmutablePlaybackTemplate.requireNonNull(it.next(), "secondaryButtons element"));
            }
            this.optBits |= 16;
            return this;
        }

        public final Builder addOnAudioEnded(Method method) {
            this.onAudioEnded.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onAudioEnded element"));
            this.optBits |= 32;
            return this;
        }

        public final Builder addOnAudioEnded(Method... methodArr) {
            for (Method method : methodArr) {
                this.onAudioEnded.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onAudioEnded element"));
            }
            this.optBits |= 32;
            return this;
        }

        @JsonProperty("onAudioEnded")
        public final Builder onAudioEnded(Iterable<? extends Method> iterable) {
            this.onAudioEnded.clear();
            return addAllOnAudioEnded(iterable);
        }

        public final Builder addAllOnAudioEnded(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onAudioEnded.add((Method) ImmutablePlaybackTemplate.requireNonNull(it.next(), "onAudioEnded element"));
            }
            this.optBits |= 32;
            return this;
        }

        public final Builder addOnSwipedUp(Method method) {
            this.onSwipedUp.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onSwipedUp element"));
            this.optBits |= 64;
            return this;
        }

        public final Builder addOnSwipedUp(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSwipedUp.add((Method) ImmutablePlaybackTemplate.requireNonNull(method, "onSwipedUp element"));
            }
            this.optBits |= 64;
            return this;
        }

        @JsonProperty("onSwipedUp")
        public final Builder onSwipedUp(Iterable<? extends Method> iterable) {
            this.onSwipedUp.clear();
            return addAllOnSwipedUp(iterable);
        }

        public final Builder addAllOnSwipedUp(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onSwipedUp.add((Method) ImmutablePlaybackTemplate.requireNonNull(it.next(), "onSwipedUp element"));
            }
            this.optBits |= 64;
            return this;
        }

        public ImmutablePlaybackTemplate build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlaybackTemplate(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onCreatedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onBoundIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean actionButtonsIsSet() {
            return (this.optBits & 8) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean secondaryButtonsIsSet() {
            return (this.optBits & 16) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onAudioEndedIsSet() {
            return (this.optBits & 32) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onSwipedUpIsSet() {
            return (this.optBits & 64) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("playableContent");
            }
            return "Cannot build PlaybackTemplate, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!z2 || t != null) {
                if (z) {
                    requireNonNull(t, "element");
                }
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
        int size = list.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        if (size == 1) {
            return Collections.singletonList(list.get(0));
        }
        if (z) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
    }
}
