package Touch.SyncInterface.v1_0;

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
@Generated(from = "WidgetSyncElement", generator = "Immutables")
public final class ImmutableWidgetSyncElement extends WidgetSyncElement {
    private final List<WidgetItemSyncElement> items;

    @Nullable
    private final String primaryHeader;

    @Nullable
    private final String secondaryHeader;
    private final WidgetContext widgetContext;

    private ImmutableWidgetSyncElement(WidgetContext widgetContext, List<WidgetItemSyncElement> list, @Nullable String str, @Nullable String str2) {
        this.widgetContext = widgetContext;
        this.items = list;
        this.primaryHeader = str;
        this.secondaryHeader = str2;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetSyncElement
    @JsonProperty(ContextMappingConstants.WIDGET_CONTEXT)
    public WidgetContext widgetContext() {
        return this.widgetContext;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetSyncElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<WidgetItemSyncElement> items() {
        return this.items;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetSyncElement
    @JsonProperty("primaryHeader")
    @Nullable
    public String primaryHeader() {
        return this.primaryHeader;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetSyncElement
    @JsonProperty("secondaryHeader")
    @Nullable
    public String secondaryHeader() {
        return this.secondaryHeader;
    }

    public final ImmutableWidgetSyncElement withWidgetContext(WidgetContext widgetContext) {
        WidgetContext widgetContext2 = (WidgetContext) requireNonNull(widgetContext, ContextMappingConstants.WIDGET_CONTEXT);
        return this.widgetContext == widgetContext2 ? this : new ImmutableWidgetSyncElement(widgetContext2, this.items, this.primaryHeader, this.secondaryHeader);
    }

    public final ImmutableWidgetSyncElement withItems(WidgetItemSyncElement... widgetItemSyncElementArr) {
        return new ImmutableWidgetSyncElement(this.widgetContext, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetItemSyncElementArr), true, false)), this.primaryHeader, this.secondaryHeader);
    }

    public final ImmutableWidgetSyncElement withItems(Iterable<? extends WidgetItemSyncElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableWidgetSyncElement(this.widgetContext, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryHeader, this.secondaryHeader);
    }

    public final ImmutableWidgetSyncElement withPrimaryHeader(@Nullable String str) {
        return equals(this.primaryHeader, str) ? this : new ImmutableWidgetSyncElement(this.widgetContext, this.items, str, this.secondaryHeader);
    }

    public final ImmutableWidgetSyncElement withSecondaryHeader(@Nullable String str) {
        return equals(this.secondaryHeader, str) ? this : new ImmutableWidgetSyncElement(this.widgetContext, this.items, this.primaryHeader, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableWidgetSyncElement) && equalTo(0, (ImmutableWidgetSyncElement) obj);
    }

    private boolean equalTo(int i, ImmutableWidgetSyncElement immutableWidgetSyncElement) {
        return this.widgetContext.equals(immutableWidgetSyncElement.widgetContext) && this.items.equals(immutableWidgetSyncElement.items) && equals(this.primaryHeader, immutableWidgetSyncElement.primaryHeader) && equals(this.secondaryHeader, immutableWidgetSyncElement.secondaryHeader);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetContext.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.items.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.primaryHeader);
        return iHashCode3 + (iHashCode3 << 5) + hashCode(this.secondaryHeader);
    }

    public String toString() {
        return "WidgetSyncElement{widgetContext=" + this.widgetContext + ", items=" + this.items + ", primaryHeader=" + this.primaryHeader + ", secondaryHeader=" + this.secondaryHeader + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "WidgetSyncElement", generator = "Immutables")
    static final class Json extends WidgetSyncElement {

        @Nullable
        List<WidgetItemSyncElement> items = Collections.emptyList();

        @Nullable
        String primaryHeader;

        @Nullable
        String secondaryHeader;

        @Nullable
        WidgetContext widgetContext;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.WIDGET_CONTEXT)
        public void setWidgetContext(WidgetContext widgetContext) {
            this.widgetContext = widgetContext;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<WidgetItemSyncElement> list) {
            this.items = list;
        }

        @JsonProperty("primaryHeader")
        public void setPrimaryHeader(@Nullable String str) {
            this.primaryHeader = str;
        }

        @JsonProperty("secondaryHeader")
        public void setSecondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
        }

        @Override // Touch.SyncInterface.v1_0.WidgetSyncElement
        public WidgetContext widgetContext() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetSyncElement
        public List<WidgetItemSyncElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetSyncElement
        public String primaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetSyncElement
        public String secondaryHeader() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableWidgetSyncElement fromJson(Json json) {
        Builder builder = builder();
        WidgetContext widgetContext = json.widgetContext;
        if (widgetContext != null) {
            builder.widgetContext(widgetContext);
        }
        List<WidgetItemSyncElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        String str = json.primaryHeader;
        if (str != null) {
            builder.primaryHeader(str);
        }
        String str2 = json.secondaryHeader;
        if (str2 != null) {
            builder.secondaryHeader(str2);
        }
        return builder.build();
    }

    public static ImmutableWidgetSyncElement copyOf(WidgetSyncElement widgetSyncElement) {
        if (widgetSyncElement instanceof ImmutableWidgetSyncElement) {
            return (ImmutableWidgetSyncElement) widgetSyncElement;
        }
        return builder().from(widgetSyncElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "WidgetSyncElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_WIDGET_CONTEXT = 1;
        private long initBits;
        private List<WidgetItemSyncElement> items;

        @Nullable
        private String primaryHeader;

        @Nullable
        private String secondaryHeader;

        @Nullable
        private WidgetContext widgetContext;

        private Builder() {
            this.initBits = 1L;
            this.items = new ArrayList();
        }

        public final Builder from(WidgetSyncElement widgetSyncElement) {
            ImmutableWidgetSyncElement.requireNonNull(widgetSyncElement, "instance");
            widgetContext(widgetSyncElement.widgetContext());
            addAllItems(widgetSyncElement.items());
            String strPrimaryHeader = widgetSyncElement.primaryHeader();
            if (strPrimaryHeader != null) {
                primaryHeader(strPrimaryHeader);
            }
            String strSecondaryHeader = widgetSyncElement.secondaryHeader();
            if (strSecondaryHeader != null) {
                secondaryHeader(strSecondaryHeader);
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.WIDGET_CONTEXT)
        public final Builder widgetContext(WidgetContext widgetContext) {
            this.widgetContext = (WidgetContext) ImmutableWidgetSyncElement.requireNonNull(widgetContext, ContextMappingConstants.WIDGET_CONTEXT);
            this.initBits &= -2;
            return this;
        }

        public final Builder addItems(WidgetItemSyncElement widgetItemSyncElement) {
            this.items.add((WidgetItemSyncElement) ImmutableWidgetSyncElement.requireNonNull(widgetItemSyncElement, "items element"));
            return this;
        }

        public final Builder addItems(WidgetItemSyncElement... widgetItemSyncElementArr) {
            for (WidgetItemSyncElement widgetItemSyncElement : widgetItemSyncElementArr) {
                this.items.add((WidgetItemSyncElement) ImmutableWidgetSyncElement.requireNonNull(widgetItemSyncElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends WidgetItemSyncElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends WidgetItemSyncElement> iterable) {
            Iterator<? extends WidgetItemSyncElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((WidgetItemSyncElement) ImmutableWidgetSyncElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        @JsonProperty("primaryHeader")
        public final Builder primaryHeader(@Nullable String str) {
            this.primaryHeader = str;
            return this;
        }

        @JsonProperty("secondaryHeader")
        public final Builder secondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
            return this;
        }

        public ImmutableWidgetSyncElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableWidgetSyncElement(this.widgetContext, ImmutableWidgetSyncElement.createUnmodifiableList(true, this.items), this.primaryHeader, this.secondaryHeader);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.WIDGET_CONTEXT);
            }
            return "Cannot build WidgetSyncElement, some of required attributes are not set " + arrayList;
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

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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
