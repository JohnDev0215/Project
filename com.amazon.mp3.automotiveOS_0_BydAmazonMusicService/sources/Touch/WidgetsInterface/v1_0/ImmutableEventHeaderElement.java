package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
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
import java.util.Date;
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
@Generated(from = "EventHeaderElement", generator = "Immutables")
public final class ImmutableEventHeaderElement extends EventHeaderElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<ButtonElement> buttons;

    @Nullable
    private final ButtonElement callToActionButton;

    @Nullable
    private final String daysText;

    @Nullable
    private final Date endTime;

    @Nullable
    private final String hoursText;

    @Nullable
    private final String image;
    private volatile transient InitShim initShim;

    @Nullable
    private final String label;

    @Nullable
    private final String minutesText;
    private final List<Method> onViewed;

    @Nullable
    private final ButtonElement primaryButton;
    private final String primaryText;

    @Nullable
    private final ButtonElement secondaryButton;

    @Nullable
    private final String secondaryText;

    @Nullable
    private final String secondsText;

    @Nullable
    private final Date startTime;

    @Nullable
    private final String tertiaryText;

    @Nullable
    private final String timerText;
    private final String widgetId;

    private ImmutableEventHeaderElement(Builder builder) {
        this.initShim = new InitShim();
        this.primaryText = builder.primaryText;
        this.secondaryText = builder.secondaryText;
        this.tertiaryText = builder.tertiaryText;
        this.label = builder.label;
        this.image = builder.image;
        this.callToActionButton = builder.callToActionButton;
        this.primaryButton = builder.primaryButton;
        this.secondaryButton = builder.secondaryButton;
        this.timerText = builder.timerText;
        this.daysText = builder.daysText;
        this.hoursText = builder.hoursText;
        this.minutesText = builder.minutesText;
        this.secondsText = builder.secondsText;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.buttonsIsSet()) {
            this.initShim.buttons(createUnmodifiableList(true, builder.buttons));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.buttons = this.initShim.buttons();
        this.initShim = null;
    }

    private ImmutableEventHeaderElement(String str, List<Method> list, String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable ButtonElement buttonElement, List<ButtonElement> list2, @Nullable ButtonElement buttonElement2, @Nullable ButtonElement buttonElement3, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable Date date, @Nullable Date date2) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.primaryText = str2;
        this.secondaryText = str3;
        this.tertiaryText = str4;
        this.label = str5;
        this.image = str6;
        this.callToActionButton = buttonElement;
        this.buttons = list2;
        this.primaryButton = buttonElement2;
        this.secondaryButton = buttonElement3;
        this.timerText = str7;
        this.daysText = str8;
        this.hoursText = str9;
        this.minutesText = str10;
        this.secondsText = str11;
        this.startTime = date;
        this.endTime = date2;
        this.initShim = null;
    }

    @Generated(from = "EventHeaderElement", generator = "Immutables")
    private final class InitShim {
        private List<ButtonElement> buttons;
        private byte buttonsBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.buttonsBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableEventHeaderElement.requireNonNull(ImmutableEventHeaderElement.super.widgetId(), "widgetId");
                this.widgetIdBuildStage = (byte) 1;
            }
            return this.widgetId;
        }

        void widgetId(String str) {
            this.widgetId = str;
            this.widgetIdBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutableEventHeaderElement.createUnmodifiableList(false, ImmutableEventHeaderElement.createSafeList(ImmutableEventHeaderElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<ButtonElement> buttons() {
            byte b = this.buttonsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.buttonsBuildStage = (byte) -1;
                this.buttons = ImmutableEventHeaderElement.createUnmodifiableList(false, ImmutableEventHeaderElement.createSafeList(ImmutableEventHeaderElement.super.buttons(), true, false));
                this.buttonsBuildStage = (byte) 1;
            }
            return this.buttons;
        }

        void buttons(List<ButtonElement> list) {
            this.buttons = list;
            this.buttonsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.buttonsBuildStage == -1) {
                arrayList.add("buttons");
            }
            return "Cannot build EventHeaderElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.WidgetsInterface.v1_0.WidgetElement
    @JsonProperty("widgetId")
    public String widgetId() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.widgetId();
        }
        return this.widgetId;
    }

    @Override // Touch.WidgetsInterface.v1_0.WidgetElement
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
    @Nullable
    public String tertiaryText() {
        return this.tertiaryText;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.LABEL)
    @Nullable
    public String label() {
        return this.label;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public String image() {
        return this.image;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty("callToActionButton")
    @Nullable
    public ButtonElement callToActionButton() {
        return this.callToActionButton;
    }

    @Override // Touch.WidgetsInterface.v1_0.HeaderElement
    @JsonProperty("buttons")
    public List<ButtonElement> buttons() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.buttons();
        }
        return this.buttons;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("primaryButton")
    @Nullable
    public ButtonElement primaryButton() {
        return this.primaryButton;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("secondaryButton")
    @Nullable
    public ButtonElement secondaryButton() {
        return this.secondaryButton;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("timerText")
    @Nullable
    @Deprecated
    public String timerText() {
        return this.timerText;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("daysText")
    @Nullable
    public String daysText() {
        return this.daysText;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("hoursText")
    @Nullable
    public String hoursText() {
        return this.hoursText;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("minutesText")
    @Nullable
    public String minutesText() {
        return this.minutesText;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("secondsText")
    @Nullable
    public String secondsText() {
        return this.secondsText;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("startTime")
    @Nullable
    public Date startTime() {
        return this.startTime;
    }

    @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
    @JsonProperty("endTime")
    @Nullable
    public Date endTime() {
        return this.endTime;
    }

    public final ImmutableEventHeaderElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableEventHeaderElement(str2, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withOnViewed(Method... methodArr) {
        return new ImmutableEventHeaderElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableEventHeaderElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withPrimaryText(String str) {
        String str2 = (String) requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
        return this.primaryText.equals(str2) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, str2, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, str, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, str, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withLabel(@Nullable String str) {
        return equals(this.label, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, str, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, str, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withCallToActionButton(@Nullable ButtonElement buttonElement) {
        return this.callToActionButton == buttonElement ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, buttonElement, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withButtons(ButtonElement... buttonElementArr) {
        return new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, createUnmodifiableList(false, createSafeList(Arrays.asList(buttonElementArr), true, false)), this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withButtons(Iterable<? extends ButtonElement> iterable) {
        if (this.buttons == iterable) {
            return this;
        }
        return new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withPrimaryButton(@Nullable ButtonElement buttonElement) {
        return this.primaryButton == buttonElement ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, buttonElement, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withSecondaryButton(@Nullable ButtonElement buttonElement) {
        return this.secondaryButton == buttonElement ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, buttonElement, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    @Deprecated
    public final ImmutableEventHeaderElement withTimerText(@Nullable String str) {
        return equals(this.timerText, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, str, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withDaysText(@Nullable String str) {
        return equals(this.daysText, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, str, this.hoursText, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withHoursText(@Nullable String str) {
        return equals(this.hoursText, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, str, this.minutesText, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withMinutesText(@Nullable String str) {
        return equals(this.minutesText, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, str, this.secondsText, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withSecondsText(@Nullable String str) {
        return equals(this.secondsText, str) ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, str, this.startTime, this.endTime);
    }

    public final ImmutableEventHeaderElement withStartTime(@Nullable Date date) {
        return this.startTime == date ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, date, this.endTime);
    }

    public final ImmutableEventHeaderElement withEndTime(@Nullable Date date) {
        return this.endTime == date ? this : new ImmutableEventHeaderElement(this.widgetId, this.onViewed, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.callToActionButton, this.buttons, this.primaryButton, this.secondaryButton, this.timerText, this.daysText, this.hoursText, this.minutesText, this.secondsText, this.startTime, date);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableEventHeaderElement) && equalTo(0, (ImmutableEventHeaderElement) obj);
    }

    private boolean equalTo(int i, ImmutableEventHeaderElement immutableEventHeaderElement) {
        return this.widgetId.equals(immutableEventHeaderElement.widgetId) && this.onViewed.equals(immutableEventHeaderElement.onViewed) && this.primaryText.equals(immutableEventHeaderElement.primaryText) && equals(this.secondaryText, immutableEventHeaderElement.secondaryText) && equals(this.tertiaryText, immutableEventHeaderElement.tertiaryText) && equals(this.label, immutableEventHeaderElement.label) && equals(this.image, immutableEventHeaderElement.image) && equals(this.callToActionButton, immutableEventHeaderElement.callToActionButton) && this.buttons.equals(immutableEventHeaderElement.buttons) && equals(this.primaryButton, immutableEventHeaderElement.primaryButton) && equals(this.secondaryButton, immutableEventHeaderElement.secondaryButton) && equals(this.timerText, immutableEventHeaderElement.timerText) && equals(this.daysText, immutableEventHeaderElement.daysText) && equals(this.hoursText, immutableEventHeaderElement.hoursText) && equals(this.minutesText, immutableEventHeaderElement.minutesText) && equals(this.secondsText, immutableEventHeaderElement.secondsText) && equals(this.startTime, immutableEventHeaderElement.startTime) && equals(this.endTime, immutableEventHeaderElement.endTime);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.primaryText.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.secondaryText);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.tertiaryText);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.label);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.image);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.callToActionButton);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + this.buttons.hashCode();
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.primaryButton);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.secondaryButton);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.timerText);
        int iHashCode13 = iHashCode12 + (iHashCode12 << 5) + hashCode(this.daysText);
        int iHashCode14 = iHashCode13 + (iHashCode13 << 5) + hashCode(this.hoursText);
        int iHashCode15 = iHashCode14 + (iHashCode14 << 5) + hashCode(this.minutesText);
        int iHashCode16 = iHashCode15 + (iHashCode15 << 5) + hashCode(this.secondsText);
        int iHashCode17 = iHashCode16 + (iHashCode16 << 5) + hashCode(this.startTime);
        return iHashCode17 + (iHashCode17 << 5) + hashCode(this.endTime);
    }

    public String toString() {
        return "EventHeaderElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", label=" + this.label + ", image=" + this.image + ", callToActionButton=" + this.callToActionButton + ", buttons=" + this.buttons + ", primaryButton=" + this.primaryButton + ", secondaryButton=" + this.secondaryButton + ", timerText=" + this.timerText + ", daysText=" + this.daysText + ", hoursText=" + this.hoursText + ", minutesText=" + this.minutesText + ", secondsText=" + this.secondsText + ", startTime=" + this.startTime + ", endTime=" + this.endTime + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "EventHeaderElement", generator = "Immutables")
    static final class Json extends EventHeaderElement {
        boolean buttonsIsSet;

        @Nullable
        ButtonElement callToActionButton;

        @Nullable
        String daysText;

        @Nullable
        Date endTime;

        @Nullable
        String hoursText;

        @Nullable
        String image;

        @Nullable
        String label;

        @Nullable
        String minutesText;
        boolean onViewedIsSet;

        @Nullable
        ButtonElement primaryButton;

        @Nullable
        String primaryText;

        @Nullable
        ButtonElement secondaryButton;

        @Nullable
        String secondaryText;

        @Nullable
        String secondsText;

        @Nullable
        Date startTime;

        @Nullable
        String tertiaryText;

        @Nullable
        String timerText;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<ButtonElement> buttons = Collections.emptyList();

        Json() {
        }

        @JsonProperty("widgetId")
        public void setWidgetId(String str) {
            this.widgetId = str;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
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

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(@Nullable String str) {
            this.label = str;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable String str) {
            this.image = str;
        }

        @JsonProperty("callToActionButton")
        public void setCallToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
        }

        @JsonProperty("buttons")
        public void setButtons(List<ButtonElement> list) {
            this.buttons = list;
            this.buttonsIsSet = true;
        }

        @JsonProperty("primaryButton")
        public void setPrimaryButton(@Nullable ButtonElement buttonElement) {
            this.primaryButton = buttonElement;
        }

        @JsonProperty("secondaryButton")
        public void setSecondaryButton(@Nullable ButtonElement buttonElement) {
            this.secondaryButton = buttonElement;
        }

        @JsonProperty("timerText")
        public void setTimerText(@Nullable String str) {
            this.timerText = str;
        }

        @JsonProperty("daysText")
        public void setDaysText(@Nullable String str) {
            this.daysText = str;
        }

        @JsonProperty("hoursText")
        public void setHoursText(@Nullable String str) {
            this.hoursText = str;
        }

        @JsonProperty("minutesText")
        public void setMinutesText(@Nullable String str) {
            this.minutesText = str;
        }

        @JsonProperty("secondsText")
        public void setSecondsText(@Nullable String str) {
            this.secondsText = str;
        }

        @JsonProperty("startTime")
        public void setStartTime(@Nullable Date date) {
            this.startTime = date;
        }

        @JsonProperty("endTime")
        public void setEndTime(@Nullable Date date) {
            this.endTime = date;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String tertiaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public ButtonElement callToActionButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.HeaderElement
        public List<ButtonElement> buttons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public ButtonElement primaryButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public ButtonElement secondaryButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public String timerText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public String daysText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public String hoursText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public String minutesText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public String secondsText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public Date startTime() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.EventHeaderElement
        public Date endTime() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableEventHeaderElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.primaryText;
        if (str2 != null) {
            builder.primaryText(str2);
        }
        String str3 = json.secondaryText;
        if (str3 != null) {
            builder.secondaryText(str3);
        }
        String str4 = json.tertiaryText;
        if (str4 != null) {
            builder.tertiaryText(str4);
        }
        String str5 = json.label;
        if (str5 != null) {
            builder.label(str5);
        }
        String str6 = json.image;
        if (str6 != null) {
            builder.image(str6);
        }
        ButtonElement buttonElement = json.callToActionButton;
        if (buttonElement != null) {
            builder.callToActionButton(buttonElement);
        }
        if (json.buttonsIsSet) {
            builder.addAllButtons(json.buttons);
        }
        ButtonElement buttonElement2 = json.primaryButton;
        if (buttonElement2 != null) {
            builder.primaryButton(buttonElement2);
        }
        ButtonElement buttonElement3 = json.secondaryButton;
        if (buttonElement3 != null) {
            builder.secondaryButton(buttonElement3);
        }
        String str7 = json.timerText;
        if (str7 != null) {
            builder.timerText(str7);
        }
        String str8 = json.daysText;
        if (str8 != null) {
            builder.daysText(str8);
        }
        String str9 = json.hoursText;
        if (str9 != null) {
            builder.hoursText(str9);
        }
        String str10 = json.minutesText;
        if (str10 != null) {
            builder.minutesText(str10);
        }
        String str11 = json.secondsText;
        if (str11 != null) {
            builder.secondsText(str11);
        }
        Date date = json.startTime;
        if (date != null) {
            builder.startTime(date);
        }
        Date date2 = json.endTime;
        if (date2 != null) {
            builder.endTime(date2);
        }
        return builder.build();
    }

    public static ImmutableEventHeaderElement copyOf(EventHeaderElement eventHeaderElement) {
        if (eventHeaderElement instanceof ImmutableEventHeaderElement) {
            return (ImmutableEventHeaderElement) eventHeaderElement;
        }
        return builder().from(eventHeaderElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "EventHeaderElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_TEXT = 1;
        private static final long OPT_BIT_BUTTONS = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private List<ButtonElement> buttons;

        @Nullable
        private ButtonElement callToActionButton;

        @Nullable
        private String daysText;

        @Nullable
        private Date endTime;

        @Nullable
        private String hoursText;

        @Nullable
        private String image;
        private long initBits;

        @Nullable
        private String label;

        @Nullable
        private String minutesText;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private ButtonElement primaryButton;

        @Nullable
        private String primaryText;

        @Nullable
        private ButtonElement secondaryButton;

        @Nullable
        private String secondaryText;

        @Nullable
        private String secondsText;

        @Nullable
        private Date startTime;

        @Nullable
        private String tertiaryText;

        @Nullable
        private String timerText;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
            this.buttons = new ArrayList();
        }

        public final Builder from(EventHeaderElement eventHeaderElement) {
            ImmutableEventHeaderElement.requireNonNull(eventHeaderElement, "instance");
            from((Object) eventHeaderElement);
            return this;
        }

        public final Builder from(HeaderElement headerElement) {
            ImmutableEventHeaderElement.requireNonNull(headerElement, "instance");
            from((Object) headerElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableEventHeaderElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof EventHeaderElement) {
                EventHeaderElement eventHeaderElement = (EventHeaderElement) obj;
                String strImage = eventHeaderElement.image();
                if (strImage != null) {
                    image(strImage);
                }
                addAllButtons(eventHeaderElement.buttons());
                ButtonElement buttonElementSecondaryButton = eventHeaderElement.secondaryButton();
                if (buttonElementSecondaryButton != null) {
                    secondaryButton(buttonElementSecondaryButton);
                }
                widgetId(eventHeaderElement.widgetId());
                primaryText(eventHeaderElement.primaryText());
                addAllOnViewed(eventHeaderElement.onViewed());
                String strLabel = eventHeaderElement.label();
                if (strLabel != null) {
                    label(strLabel);
                }
                String strDaysText = eventHeaderElement.daysText();
                if (strDaysText != null) {
                    daysText(strDaysText);
                }
                String strSecondaryText = eventHeaderElement.secondaryText();
                if (strSecondaryText != null) {
                    secondaryText(strSecondaryText);
                }
                String strHoursText = eventHeaderElement.hoursText();
                if (strHoursText != null) {
                    hoursText(strHoursText);
                }
                String strSecondsText = eventHeaderElement.secondsText();
                if (strSecondsText != null) {
                    secondsText(strSecondsText);
                }
                ButtonElement buttonElementCallToActionButton = eventHeaderElement.callToActionButton();
                if (buttonElementCallToActionButton != null) {
                    callToActionButton(buttonElementCallToActionButton);
                }
                String strMinutesText = eventHeaderElement.minutesText();
                if (strMinutesText != null) {
                    minutesText(strMinutesText);
                }
                ButtonElement buttonElementPrimaryButton = eventHeaderElement.primaryButton();
                if (buttonElementPrimaryButton != null) {
                    primaryButton(buttonElementPrimaryButton);
                }
                Date dateStartTime = eventHeaderElement.startTime();
                if (dateStartTime != null) {
                    startTime(dateStartTime);
                }
                Date dateEndTime = eventHeaderElement.endTime();
                if (dateEndTime != null) {
                    endTime(dateEndTime);
                }
                String strTertiaryText = eventHeaderElement.tertiaryText();
                if (strTertiaryText != null) {
                    tertiaryText(strTertiaryText);
                }
                j = 511;
                String strTimerText = eventHeaderElement.timerText();
                if (strTimerText != null) {
                    timerText(strTimerText);
                }
            } else {
                j = 0;
            }
            if (obj instanceof HeaderElement) {
                HeaderElement headerElement = (HeaderElement) obj;
                if ((j & 1) == 0) {
                    String strImage2 = headerElement.image();
                    if (strImage2 != null) {
                        image(strImage2);
                    }
                    j |= 1;
                }
                if ((j & 128) == 0) {
                    ButtonElement buttonElementCallToActionButton2 = headerElement.callToActionButton();
                    if (buttonElementCallToActionButton2 != null) {
                        callToActionButton(buttonElementCallToActionButton2);
                    }
                    j |= 128;
                }
                if ((j & 2) == 0) {
                    addAllButtons(headerElement.buttons());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    widgetId(headerElement.widgetId());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    primaryText(headerElement.primaryText());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    addAllOnViewed(headerElement.onViewed());
                    j |= 16;
                }
                if ((j & 32) == 0) {
                    String strLabel2 = headerElement.label();
                    if (strLabel2 != null) {
                        label(strLabel2);
                    }
                    j |= 32;
                }
                if ((j & 256) == 0) {
                    String strTertiaryText2 = headerElement.tertiaryText();
                    if (strTertiaryText2 != null) {
                        tertiaryText(strTertiaryText2);
                    }
                    j |= 256;
                }
                if ((j & 64) == 0) {
                    String strSecondaryText2 = headerElement.secondaryText();
                    if (strSecondaryText2 != null) {
                        secondaryText(strSecondaryText2);
                    }
                    j |= 64;
                }
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 4) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 4;
                }
                if ((j & 16) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableEventHeaderElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableEventHeaderElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableEventHeaderElement.requireNonNull(method, "onViewed element"));
            }
            this.optBits |= 1;
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
                this.onViewed.add((Method) ImmutableEventHeaderElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(String str) {
            this.primaryText = (String) ImmutableEventHeaderElement.requireNonNull(str, ContextMappingConstants.PRIMARY_TEXT);
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

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(@Nullable String str) {
            this.label = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        @JsonProperty("callToActionButton")
        public final Builder callToActionButton(@Nullable ButtonElement buttonElement) {
            this.callToActionButton = buttonElement;
            return this;
        }

        public final Builder addButtons(ButtonElement buttonElement) {
            this.buttons.add((ButtonElement) ImmutableEventHeaderElement.requireNonNull(buttonElement, "buttons element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addButtons(ButtonElement... buttonElementArr) {
            for (ButtonElement buttonElement : buttonElementArr) {
                this.buttons.add((ButtonElement) ImmutableEventHeaderElement.requireNonNull(buttonElement, "buttons element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("buttons")
        public final Builder buttons(Iterable<? extends ButtonElement> iterable) {
            this.buttons.clear();
            return addAllButtons(iterable);
        }

        public final Builder addAllButtons(Iterable<? extends ButtonElement> iterable) {
            Iterator<? extends ButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.buttons.add((ButtonElement) ImmutableEventHeaderElement.requireNonNull(it.next(), "buttons element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("primaryButton")
        public final Builder primaryButton(@Nullable ButtonElement buttonElement) {
            this.primaryButton = buttonElement;
            return this;
        }

        @JsonProperty("secondaryButton")
        public final Builder secondaryButton(@Nullable ButtonElement buttonElement) {
            this.secondaryButton = buttonElement;
            return this;
        }

        @JsonProperty("timerText")
        @Deprecated
        public final Builder timerText(@Nullable String str) {
            this.timerText = str;
            return this;
        }

        @JsonProperty("daysText")
        public final Builder daysText(@Nullable String str) {
            this.daysText = str;
            return this;
        }

        @JsonProperty("hoursText")
        public final Builder hoursText(@Nullable String str) {
            this.hoursText = str;
            return this;
        }

        @JsonProperty("minutesText")
        public final Builder minutesText(@Nullable String str) {
            this.minutesText = str;
            return this;
        }

        @JsonProperty("secondsText")
        public final Builder secondsText(@Nullable String str) {
            this.secondsText = str;
            return this;
        }

        @JsonProperty("startTime")
        public final Builder startTime(@Nullable Date date) {
            this.startTime = date;
            return this;
        }

        @JsonProperty("endTime")
        public final Builder endTime(@Nullable Date date) {
            this.endTime = date;
            return this;
        }

        public ImmutableEventHeaderElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableEventHeaderElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean buttonsIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.PRIMARY_TEXT);
            }
            return "Cannot build EventHeaderElement, some of required attributes are not set " + arrayList;
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
