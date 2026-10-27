package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "MessageElement", generator = "Immutables")
public final class ImmutableMessageElement extends MessageElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<TextButtonElement> buttons;
    private volatile transient InitShim initShim;
    private final String message;
    private final List<Method> onViewed;

    @Nullable
    private final String subMessage;
    private final String widgetId;

    private ImmutableMessageElement(Builder builder) {
        this.initShim = new InitShim();
        this.message = builder.message;
        this.subMessage = builder.subMessage;
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

    private ImmutableMessageElement(String str, List<Method> list, String str2, @Nullable String str3, List<TextButtonElement> list2) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.message = str2;
        this.subMessage = str3;
        this.buttons = list2;
        this.initShim = null;
    }

    @Generated(from = "MessageElement", generator = "Immutables")
    private final class InitShim {
        private List<TextButtonElement> buttons;
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
                this.widgetId = (String) ImmutableMessageElement.requireNonNull(ImmutableMessageElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableMessageElement.createUnmodifiableList(false, ImmutableMessageElement.createSafeList(ImmutableMessageElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        List<TextButtonElement> buttons() {
            byte b = this.buttonsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.buttonsBuildStage = (byte) -1;
                this.buttons = ImmutableMessageElement.createUnmodifiableList(false, ImmutableMessageElement.createSafeList(ImmutableMessageElement.super.buttons(), true, false));
                this.buttonsBuildStage = (byte) 1;
            }
            return this.buttons;
        }

        void buttons(List<TextButtonElement> list) {
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
            return "Cannot build MessageElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.MessageElement
    @JsonProperty("message")
    public String message() {
        return this.message;
    }

    @Override // Touch.WidgetsInterface.v1_0.MessageElement
    @JsonProperty("subMessage")
    @Nullable
    public String subMessage() {
        return this.subMessage;
    }

    @Override // Touch.WidgetsInterface.v1_0.MessageElement
    @JsonProperty("buttons")
    public List<TextButtonElement> buttons() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.buttons();
        }
        return this.buttons;
    }

    public final ImmutableMessageElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableMessageElement(str2, this.onViewed, this.message, this.subMessage, this.buttons);
    }

    public final ImmutableMessageElement withOnViewed(Method... methodArr) {
        return new ImmutableMessageElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.message, this.subMessage, this.buttons);
    }

    public final ImmutableMessageElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableMessageElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.message, this.subMessage, this.buttons);
    }

    public final ImmutableMessageElement withMessage(String str) {
        String str2 = (String) requireNonNull(str, "message");
        return this.message.equals(str2) ? this : new ImmutableMessageElement(this.widgetId, this.onViewed, str2, this.subMessage, this.buttons);
    }

    public final ImmutableMessageElement withSubMessage(@Nullable String str) {
        return equals(this.subMessage, str) ? this : new ImmutableMessageElement(this.widgetId, this.onViewed, this.message, str, this.buttons);
    }

    public final ImmutableMessageElement withButtons(TextButtonElement... textButtonElementArr) {
        return new ImmutableMessageElement(this.widgetId, this.onViewed, this.message, this.subMessage, createUnmodifiableList(false, createSafeList(Arrays.asList(textButtonElementArr), true, false)));
    }

    public final ImmutableMessageElement withButtons(Iterable<? extends TextButtonElement> iterable) {
        if (this.buttons == iterable) {
            return this;
        }
        return new ImmutableMessageElement(this.widgetId, this.onViewed, this.message, this.subMessage, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMessageElement) && equalTo(0, (ImmutableMessageElement) obj);
    }

    private boolean equalTo(int i, ImmutableMessageElement immutableMessageElement) {
        return this.widgetId.equals(immutableMessageElement.widgetId) && this.onViewed.equals(immutableMessageElement.onViewed) && this.message.equals(immutableMessageElement.message) && equals(this.subMessage, immutableMessageElement.subMessage) && this.buttons.equals(immutableMessageElement.buttons);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.message.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.subMessage);
        return iHashCode4 + (iHashCode4 << 5) + this.buttons.hashCode();
    }

    public String toString() {
        return "MessageElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", message=" + this.message + ", subMessage=" + this.subMessage + ", buttons=" + this.buttons + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MessageElement", generator = "Immutables")
    static final class Json extends MessageElement {
        boolean buttonsIsSet;

        @Nullable
        String message;
        boolean onViewedIsSet;

        @Nullable
        String subMessage;

        @Nullable
        String widgetId;

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<TextButtonElement> buttons = Collections.emptyList();

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

        @JsonProperty("message")
        public void setMessage(String str) {
            this.message = str;
        }

        @JsonProperty("subMessage")
        public void setSubMessage(@Nullable String str) {
            this.subMessage = str;
        }

        @JsonProperty("buttons")
        public void setButtons(List<TextButtonElement> list) {
            this.buttons = list;
            this.buttonsIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.MessageElement
        public String message() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.MessageElement
        public String subMessage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.MessageElement
        public List<TextButtonElement> buttons() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMessageElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        String str2 = json.message;
        if (str2 != null) {
            builder.message(str2);
        }
        String str3 = json.subMessage;
        if (str3 != null) {
            builder.subMessage(str3);
        }
        if (json.buttonsIsSet) {
            builder.addAllButtons(json.buttons);
        }
        return builder.build();
    }

    public static ImmutableMessageElement copyOf(MessageElement messageElement) {
        if (messageElement instanceof ImmutableMessageElement) {
            return (ImmutableMessageElement) messageElement;
        }
        return builder().from(messageElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MessageElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MESSAGE = 1;
        private static final long OPT_BIT_BUTTONS = 2;
        private static final long OPT_BIT_ON_VIEWED = 1;
        private List<TextButtonElement> buttons;
        private long initBits;

        @Nullable
        private String message;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String subMessage;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
            this.buttons = new ArrayList();
        }

        public final Builder from(MessageElement messageElement) {
            ImmutableMessageElement.requireNonNull(messageElement, "instance");
            from((Object) messageElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableMessageElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof MessageElement) {
                MessageElement messageElement = (MessageElement) obj;
                addAllButtons(messageElement.buttons());
                addAllOnViewed(messageElement.onViewed());
                message(messageElement.message());
                String strSubMessage = messageElement.subMessage();
                if (strSubMessage != null) {
                    subMessage(strSubMessage);
                }
                widgetId(messageElement.widgetId());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof WidgetElement) {
                WidgetElement widgetElement = (WidgetElement) obj;
                if ((j & 1) == 0) {
                    widgetId(widgetElement.widgetId());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    addAllOnViewed(widgetElement.onViewed());
                }
            }
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(String str) {
            this.widgetId = (String) ImmutableMessageElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableMessageElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableMessageElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableMessageElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("message")
        public final Builder message(String str) {
            this.message = (String) ImmutableMessageElement.requireNonNull(str, "message");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("subMessage")
        public final Builder subMessage(@Nullable String str) {
            this.subMessage = str;
            return this;
        }

        public final Builder addButtons(TextButtonElement textButtonElement) {
            this.buttons.add((TextButtonElement) ImmutableMessageElement.requireNonNull(textButtonElement, "buttons element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addButtons(TextButtonElement... textButtonElementArr) {
            for (TextButtonElement textButtonElement : textButtonElementArr) {
                this.buttons.add((TextButtonElement) ImmutableMessageElement.requireNonNull(textButtonElement, "buttons element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("buttons")
        public final Builder buttons(Iterable<? extends TextButtonElement> iterable) {
            this.buttons.clear();
            return addAllButtons(iterable);
        }

        public final Builder addAllButtons(Iterable<? extends TextButtonElement> iterable) {
            Iterator<? extends TextButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.buttons.add((TextButtonElement) ImmutableMessageElement.requireNonNull(it.next(), "buttons element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableMessageElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableMessageElement(this);
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
                arrayList.add("message");
            }
            return "Cannot build MessageElement, some of required attributes are not set " + arrayList;
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
