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
@Generated(from = "FullScreenButtonWidgetElement", generator = "Immutables")
public final class ImmutableFullScreenButtonWidgetElement extends FullScreenButtonWidgetElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final ButtonElement button;
    private volatile transient InitShim initShim;
    private final List<Method> onViewed;
    private final String widgetId;

    private ImmutableFullScreenButtonWidgetElement(Builder builder) {
        this.initShim = new InitShim();
        this.button = builder.button;
        if (builder.widgetId != null) {
            this.initShim.widgetId(builder.widgetId);
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        this.widgetId = this.initShim.widgetId();
        this.onViewed = this.initShim.onViewed();
        this.initShim = null;
    }

    private ImmutableFullScreenButtonWidgetElement(String str, List<Method> list, ButtonElement buttonElement) {
        this.initShim = new InitShim();
        this.widgetId = str;
        this.onViewed = list;
        this.button = buttonElement;
        this.initShim = null;
    }

    @Generated(from = "FullScreenButtonWidgetElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private String widgetId;
        private byte widgetIdBuildStage;

        private InitShim() {
            this.widgetIdBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
        }

        String widgetId() {
            byte b = this.widgetIdBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetIdBuildStage = (byte) -1;
                this.widgetId = (String) ImmutableFullScreenButtonWidgetElement.requireNonNull(ImmutableFullScreenButtonWidgetElement.super.widgetId(), "widgetId");
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
                this.onViewed = ImmutableFullScreenButtonWidgetElement.createUnmodifiableList(false, ImmutableFullScreenButtonWidgetElement.createSafeList(ImmutableFullScreenButtonWidgetElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.widgetIdBuildStage == -1) {
                arrayList.add("widgetId");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            return "Cannot build FullScreenButtonWidgetElement, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.WidgetsInterface.v1_0.FullScreenButtonWidgetElement
    @JsonProperty("button")
    public ButtonElement button() {
        return this.button;
    }

    public final ImmutableFullScreenButtonWidgetElement withWidgetId(String str) {
        String str2 = (String) requireNonNull(str, "widgetId");
        return this.widgetId.equals(str2) ? this : new ImmutableFullScreenButtonWidgetElement(str2, this.onViewed, this.button);
    }

    public final ImmutableFullScreenButtonWidgetElement withOnViewed(Method... methodArr) {
        return new ImmutableFullScreenButtonWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.button);
    }

    public final ImmutableFullScreenButtonWidgetElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableFullScreenButtonWidgetElement(this.widgetId, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.button);
    }

    public final ImmutableFullScreenButtonWidgetElement withButton(ButtonElement buttonElement) {
        if (this.button == buttonElement) {
            return this;
        }
        return new ImmutableFullScreenButtonWidgetElement(this.widgetId, this.onViewed, (ButtonElement) requireNonNull(buttonElement, "button"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableFullScreenButtonWidgetElement) && equalTo(0, (ImmutableFullScreenButtonWidgetElement) obj);
    }

    private boolean equalTo(int i, ImmutableFullScreenButtonWidgetElement immutableFullScreenButtonWidgetElement) {
        return this.widgetId.equals(immutableFullScreenButtonWidgetElement.widgetId) && this.onViewed.equals(immutableFullScreenButtonWidgetElement.onViewed) && this.button.equals(immutableFullScreenButtonWidgetElement.button);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onViewed.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.button.hashCode();
    }

    public String toString() {
        return "FullScreenButtonWidgetElement{widgetId=" + this.widgetId + ", onViewed=" + this.onViewed + ", button=" + this.button + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "FullScreenButtonWidgetElement", generator = "Immutables")
    static final class Json extends FullScreenButtonWidgetElement {

        @Nullable
        ButtonElement button;

        @Nullable
        List<Method> onViewed = Collections.emptyList();
        boolean onViewedIsSet;

        @Nullable
        String widgetId;

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

        @JsonProperty("button")
        public void setButton(ButtonElement buttonElement) {
            this.button = buttonElement;
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.WidgetElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.FullScreenButtonWidgetElement
        public ButtonElement button() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableFullScreenButtonWidgetElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        ButtonElement buttonElement = json.button;
        if (buttonElement != null) {
            builder.button(buttonElement);
        }
        return builder.build();
    }

    public static ImmutableFullScreenButtonWidgetElement copyOf(FullScreenButtonWidgetElement fullScreenButtonWidgetElement) {
        if (fullScreenButtonWidgetElement instanceof ImmutableFullScreenButtonWidgetElement) {
            return (ImmutableFullScreenButtonWidgetElement) fullScreenButtonWidgetElement;
        }
        return builder().from(fullScreenButtonWidgetElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "FullScreenButtonWidgetElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_BUTTON = 1;
        private static final long OPT_BIT_ON_VIEWED = 1;

        @Nullable
        private ButtonElement button;
        private long initBits;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
            this.onViewed = new ArrayList();
        }

        public final Builder from(FullScreenButtonWidgetElement fullScreenButtonWidgetElement) {
            ImmutableFullScreenButtonWidgetElement.requireNonNull(fullScreenButtonWidgetElement, "instance");
            from((Object) fullScreenButtonWidgetElement);
            return this;
        }

        public final Builder from(WidgetElement widgetElement) {
            ImmutableFullScreenButtonWidgetElement.requireNonNull(widgetElement, "instance");
            from((Object) widgetElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof FullScreenButtonWidgetElement) {
                FullScreenButtonWidgetElement fullScreenButtonWidgetElement = (FullScreenButtonWidgetElement) obj;
                button(fullScreenButtonWidgetElement.button());
                widgetId(fullScreenButtonWidgetElement.widgetId());
                addAllOnViewed(fullScreenButtonWidgetElement.onViewed());
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
            this.widgetId = (String) ImmutableFullScreenButtonWidgetElement.requireNonNull(str, "widgetId");
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableFullScreenButtonWidgetElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableFullScreenButtonWidgetElement.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableFullScreenButtonWidgetElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("button")
        public final Builder button(ButtonElement buttonElement) {
            this.button = (ButtonElement) ImmutableFullScreenButtonWidgetElement.requireNonNull(buttonElement, "button");
            this.initBits &= -2;
            return this;
        }

        public ImmutableFullScreenButtonWidgetElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableFullScreenButtonWidgetElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("button");
            }
            return "Cannot build FullScreenButtonWidgetElement, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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
