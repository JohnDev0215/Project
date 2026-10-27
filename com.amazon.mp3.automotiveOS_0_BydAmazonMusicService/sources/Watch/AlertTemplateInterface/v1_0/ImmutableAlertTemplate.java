package Watch.AlertTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.cast.MediaTrack;
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
@Generated(from = "AlertTemplate", generator = "Immutables")
public final class ImmutableAlertTemplate extends AlertTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Long cacheSeconds;

    @Nullable
    private final String description;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final String message;

    @Nullable
    private final AlertButtonElement negativeButton;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onViewed;

    @Nullable
    private final AlertButtonElement positiveButton;

    @Nullable
    private final String title;

    private ImmutableAlertTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.message = builder.message;
        this.description = builder.description;
        this.positiveButton = builder.positiveButton;
        this.negativeButton = builder.negativeButton;
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
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.initShim = null;
    }

    private ImmutableAlertTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, @Nullable String str, String str2, @Nullable String str3, @Nullable AlertButtonElement alertButtonElement, @Nullable AlertButtonElement alertButtonElement2) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.title = str;
        this.message = str2;
        this.description = str3;
        this.positiveButton = alertButtonElement;
        this.negativeButton = alertButtonElement2;
        this.initShim = null;
    }

    @Generated(from = "AlertTemplate", generator = "Immutables")
    private final class InitShim {
        private Long cacheSeconds;
        private byte cacheSecondsBuildStage;
        private TemplateLaunchMode launchMode;
        private byte launchModeBuildStage;
        private List<Method> onBound;
        private byte onBoundBuildStage;
        private List<Method> onCreated;
        private byte onCreatedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableAlertTemplate.createUnmodifiableList(false, ImmutableAlertTemplate.createSafeList(ImmutableAlertTemplate.super.onCreated(), true, false));
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
                this.onBound = ImmutableAlertTemplate.createUnmodifiableList(false, ImmutableAlertTemplate.createSafeList(ImmutableAlertTemplate.super.onBound(), true, false));
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
                this.onViewed = ImmutableAlertTemplate.createUnmodifiableList(false, ImmutableAlertTemplate.createSafeList(ImmutableAlertTemplate.super.onViewed(), true, false));
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
                this.launchMode = (TemplateLaunchMode) ImmutableAlertTemplate.requireNonNull(ImmutableAlertTemplate.super.launchMode(), "launchMode");
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
                this.cacheSeconds = (Long) ImmutableAlertTemplate.requireNonNull(ImmutableAlertTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
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
            return "Cannot build AlertTemplate, attribute initializers form cycle " + arrayList;
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

    @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
    @JsonProperty("title")
    @Nullable
    public String title() {
        return this.title;
    }

    @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
    @JsonProperty("message")
    public String message() {
        return this.message;
    }

    @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
    @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
    @Nullable
    public String description() {
        return this.description;
    }

    @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
    @JsonProperty("positiveButton")
    @Nullable
    public AlertButtonElement positiveButton() {
        return this.positiveButton;
    }

    @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
    @JsonProperty("negativeButton")
    @Nullable
    public AlertButtonElement negativeButton() {
        return this.negativeButton;
    }

    public final ImmutableAlertTemplate withOnCreated(Method... methodArr) {
        return new ImmutableAlertTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableAlertTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withOnBound(Method... methodArr) {
        return new ImmutableAlertTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableAlertTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withOnViewed(Method... methodArr) {
        return new ImmutableAlertTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.title, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableAlertTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.title, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableAlertTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.title, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableAlertTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.title, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableAlertTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str, this.message, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withMessage(String str) {
        String str2 = (String) requireNonNull(str, "message");
        return this.message.equals(str2) ? this : new ImmutableAlertTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, str2, this.description, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withDescription(@Nullable String str) {
        return equals(this.description, str) ? this : new ImmutableAlertTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.message, str, this.positiveButton, this.negativeButton);
    }

    public final ImmutableAlertTemplate withPositiveButton(@Nullable AlertButtonElement alertButtonElement) {
        return this.positiveButton == alertButtonElement ? this : new ImmutableAlertTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.message, this.description, alertButtonElement, this.negativeButton);
    }

    public final ImmutableAlertTemplate withNegativeButton(@Nullable AlertButtonElement alertButtonElement) {
        return this.negativeButton == alertButtonElement ? this : new ImmutableAlertTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.message, this.description, this.positiveButton, alertButtonElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableAlertTemplate) && equalTo(0, (ImmutableAlertTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableAlertTemplate immutableAlertTemplate) {
        return this.onCreated.equals(immutableAlertTemplate.onCreated) && this.onBound.equals(immutableAlertTemplate.onBound) && this.onViewed.equals(immutableAlertTemplate.onViewed) && this.launchMode.equals(immutableAlertTemplate.launchMode) && this.cacheSeconds.equals(immutableAlertTemplate.cacheSeconds) && equals(this.title, immutableAlertTemplate.title) && this.message.equals(immutableAlertTemplate.message) && equals(this.description, immutableAlertTemplate.description) && equals(this.positiveButton, immutableAlertTemplate.positiveButton) && equals(this.negativeButton, immutableAlertTemplate.negativeButton);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.title);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.message.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.description);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.positiveButton);
        return iHashCode9 + (iHashCode9 << 5) + hashCode(this.negativeButton);
    }

    public String toString() {
        return "AlertTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", title=" + this.title + ", message=" + this.message + ", description=" + this.description + ", positiveButton=" + this.positiveButton + ", negativeButton=" + this.negativeButton + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "AlertTemplate", generator = "Immutables")
    static final class Json extends AlertTemplate {

        @Nullable
        Long cacheSeconds;

        @Nullable
        String description;

        @Nullable
        TemplateLaunchMode launchMode;

        @Nullable
        String message;

        @Nullable
        AlertButtonElement negativeButton;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onViewedIsSet;

        @Nullable
        AlertButtonElement positiveButton;

        @Nullable
        String title;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

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

        @JsonProperty("title")
        public void setTitle(@Nullable String str) {
            this.title = str;
        }

        @JsonProperty("message")
        public void setMessage(String str) {
            this.message = str;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public void setDescription(@Nullable String str) {
            this.description = str;
        }

        @JsonProperty("positiveButton")
        public void setPositiveButton(@Nullable AlertButtonElement alertButtonElement) {
            this.positiveButton = alertButtonElement;
        }

        @JsonProperty("negativeButton")
        public void setNegativeButton(@Nullable AlertButtonElement alertButtonElement) {
            this.negativeButton = alertButtonElement;
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

        @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
        public String message() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
        public String description() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
        public AlertButtonElement positiveButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.AlertTemplateInterface.v1_0.AlertTemplate
        public AlertButtonElement negativeButton() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableAlertTemplate fromJson(Json json) {
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
        String str = json.title;
        if (str != null) {
            builder.title(str);
        }
        String str2 = json.message;
        if (str2 != null) {
            builder.message(str2);
        }
        String str3 = json.description;
        if (str3 != null) {
            builder.description(str3);
        }
        AlertButtonElement alertButtonElement = json.positiveButton;
        if (alertButtonElement != null) {
            builder.positiveButton(alertButtonElement);
        }
        AlertButtonElement alertButtonElement2 = json.negativeButton;
        if (alertButtonElement2 != null) {
            builder.negativeButton(alertButtonElement2);
        }
        return builder.build();
    }

    public static ImmutableAlertTemplate copyOf(AlertTemplate alertTemplate) {
        if (alertTemplate instanceof ImmutableAlertTemplate) {
            return (ImmutableAlertTemplate) alertTemplate;
        }
        return builder().from(alertTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "AlertTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MESSAGE = 1;
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_VIEWED = 4;

        @Nullable
        private Long cacheSeconds;

        @Nullable
        private String description;
        private long initBits;

        @Nullable
        private TemplateLaunchMode launchMode;

        @Nullable
        private String message;

        @Nullable
        private AlertButtonElement negativeButton;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private AlertButtonElement positiveButton;

        @Nullable
        private String title;

        private Builder() {
            this.initBits = 1L;
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
        }

        public final Builder from(Template template) {
            ImmutableAlertTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        public final Builder from(AlertTemplate alertTemplate) {
            ImmutableAlertTemplate.requireNonNull(alertTemplate, "instance");
            from((Object) alertTemplate);
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
            if (obj instanceof AlertTemplate) {
                AlertTemplate alertTemplate = (AlertTemplate) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(alertTemplate.onBound());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    cacheSeconds(alertTemplate.cacheSeconds());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    launchMode(alertTemplate.launchMode());
                    j |= 4;
                }
                AlertButtonElement alertButtonElementNegativeButton = alertTemplate.negativeButton();
                if (alertButtonElementNegativeButton != null) {
                    negativeButton(alertButtonElementNegativeButton);
                }
                String strDescription = alertTemplate.description();
                if (strDescription != null) {
                    description(strDescription);
                }
                AlertButtonElement alertButtonElementPositiveButton = alertTemplate.positiveButton();
                if (alertButtonElementPositiveButton != null) {
                    positiveButton(alertButtonElementPositiveButton);
                }
                if ((j & 8) == 0) {
                    addAllOnViewed(alertTemplate.onViewed());
                    j |= 8;
                }
                String strTitle = alertTemplate.title();
                if (strTitle != null) {
                    title(strTitle);
                }
                message(alertTemplate.message());
                if ((j & 16) == 0) {
                    addAllOnCreated(alertTemplate.onCreated());
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableAlertTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableAlertTemplate.requireNonNull(method, "onCreated element"));
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
                this.onCreated.add((Method) ImmutableAlertTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableAlertTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableAlertTemplate.requireNonNull(method, "onBound element"));
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
                this.onBound.add((Method) ImmutableAlertTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableAlertTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableAlertTemplate.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableAlertTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableAlertTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableAlertTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("title")
        public final Builder title(@Nullable String str) {
            this.title = str;
            return this;
        }

        @JsonProperty("message")
        public final Builder message(String str) {
            this.message = (String) ImmutableAlertTemplate.requireNonNull(str, "message");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_DESCRIPTION)
        public final Builder description(@Nullable String str) {
            this.description = str;
            return this;
        }

        @JsonProperty("positiveButton")
        public final Builder positiveButton(@Nullable AlertButtonElement alertButtonElement) {
            this.positiveButton = alertButtonElement;
            return this;
        }

        @JsonProperty("negativeButton")
        public final Builder negativeButton(@Nullable AlertButtonElement alertButtonElement) {
            this.negativeButton = alertButtonElement;
            return this;
        }

        public ImmutableAlertTemplate build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableAlertTemplate(this);
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

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("message");
            }
            return "Cannot build AlertTemplate, some of required attributes are not set " + arrayList;
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
