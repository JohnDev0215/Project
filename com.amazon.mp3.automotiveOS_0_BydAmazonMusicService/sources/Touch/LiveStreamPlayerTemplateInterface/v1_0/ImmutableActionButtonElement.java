package Touch.LiveStreamPlayerTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
@Generated(from = "ActionButtonElement", generator = "Immutables")
public final class ImmutableActionButtonElement extends ActionButtonElement {
    private final List<Method> onItemSelected;
    private final String text;
    private final ActionButtonType type;

    private ImmutableActionButtonElement(ActionButtonType actionButtonType, String str, List<Method> list) {
        this.type = actionButtonType;
        this.text = str;
        this.onItemSelected = list;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.ActionButtonElement
    @JsonProperty(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)
    public ActionButtonType type() {
        return this.type;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.ActionButtonElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.ActionButtonElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableActionButtonElement withType(ActionButtonType actionButtonType) {
        ActionButtonType actionButtonType2 = (ActionButtonType) requireNonNull(actionButtonType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        return this.type == actionButtonType2 ? this : new ImmutableActionButtonElement(actionButtonType2, this.text, this.onItemSelected);
    }

    public final ImmutableActionButtonElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableActionButtonElement(this.type, str2, this.onItemSelected);
    }

    public final ImmutableActionButtonElement withOnItemSelected(Method... methodArr) {
        return new ImmutableActionButtonElement(this.type, this.text, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableActionButtonElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableActionButtonElement(this.type, this.text, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableActionButtonElement) && equalTo(0, (ImmutableActionButtonElement) obj);
    }

    private boolean equalTo(int i, ImmutableActionButtonElement immutableActionButtonElement) {
        return this.type.equals(immutableActionButtonElement.type) && this.text.equals(immutableActionButtonElement.text) && this.onItemSelected.equals(immutableActionButtonElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.type.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.text.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "ActionButtonElement{type=" + this.type + ", text=" + this.text + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ActionButtonElement", generator = "Immutables")
    static final class Json extends ActionButtonElement {

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        String text;

        @Nullable
        ActionButtonType type;

        Json() {
        }

        @JsonProperty(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)
        public void setType(ActionButtonType actionButtonType) {
            this.type = actionButtonType;
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.ActionButtonElement
        public ActionButtonType type() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.ActionButtonElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.ActionButtonElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableActionButtonElement fromJson(Json json) {
        Builder builder = builder();
        ActionButtonType actionButtonType = json.type;
        if (actionButtonType != null) {
            builder.type(actionButtonType);
        }
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        return builder.build();
    }

    public static ImmutableActionButtonElement copyOf(ActionButtonElement actionButtonElement) {
        if (actionButtonElement instanceof ImmutableActionButtonElement) {
            return (ImmutableActionButtonElement) actionButtonElement;
        }
        return builder().from(actionButtonElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ActionButtonElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 2;
        private static final long INIT_BIT_TYPE = 1;
        private long initBits;
        private List<Method> onItemSelected;

        @Nullable
        private String text;

        @Nullable
        private ActionButtonType type;

        private Builder() {
            this.initBits = 3L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(ActionButtonElement actionButtonElement) {
            ImmutableActionButtonElement.requireNonNull(actionButtonElement, "instance");
            type(actionButtonElement.type());
            text(actionButtonElement.text());
            addAllOnItemSelected(actionButtonElement.onItemSelected());
            return this;
        }

        @JsonProperty(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)
        public final Builder type(ActionButtonType actionButtonType) {
            this.type = (ActionButtonType) ImmutableActionButtonElement.requireNonNull(actionButtonType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableActionButtonElement.requireNonNull(str, "text");
            this.initBits &= -3;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableActionButtonElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableActionButtonElement.requireNonNull(method, "onItemSelected element"));
            }
            return this;
        }

        @JsonProperty("onItemSelected")
        public final Builder onItemSelected(Iterable<? extends Method> iterable) {
            this.onItemSelected.clear();
            return addAllOnItemSelected(iterable);
        }

        public final Builder addAllOnItemSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onItemSelected.add((Method) ImmutableActionButtonElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public ImmutableActionButtonElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableActionButtonElement(this.type, this.text, ImmutableActionButtonElement.createUnmodifiableList(true, this.onItemSelected));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("text");
            }
            return "Cannot build ActionButtonElement, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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
