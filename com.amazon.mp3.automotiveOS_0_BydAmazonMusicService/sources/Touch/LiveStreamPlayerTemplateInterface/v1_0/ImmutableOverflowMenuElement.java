package Touch.LiveStreamPlayerTemplateInterface.v1_0;

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
@Generated(from = "OverflowMenuElement", generator = "Immutables")
public final class ImmutableOverflowMenuElement extends OverflowMenuElement {
    private final List<ActionButtonElement> actions;
    private final List<Method> onItemSelected;

    private ImmutableOverflowMenuElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.actions = createUnmodifiableList(true, builder.actions);
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableOverflowMenuElement(List<ActionButtonElement> list, List<Method> list2) {
        this.actions = list;
        this.onItemSelected = list2;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.OverflowMenuElement
    @JsonProperty("actions")
    public List<ActionButtonElement> actions() {
        return this.actions;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.OverflowMenuElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableOverflowMenuElement withActions(ActionButtonElement... actionButtonElementArr) {
        return new ImmutableOverflowMenuElement((List<ActionButtonElement>) createUnmodifiableList(false, createSafeList(Arrays.asList(actionButtonElementArr), true, false)), this.onItemSelected);
    }

    public final ImmutableOverflowMenuElement withActions(Iterable<? extends ActionButtonElement> iterable) {
        return this.actions == iterable ? this : new ImmutableOverflowMenuElement((List<ActionButtonElement>) createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onItemSelected);
    }

    public final ImmutableOverflowMenuElement withOnItemSelected(Method... methodArr) {
        return new ImmutableOverflowMenuElement(this.actions, (List<Method>) createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableOverflowMenuElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableOverflowMenuElement(this.actions, (List<Method>) createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableOverflowMenuElement) && equalTo(0, (ImmutableOverflowMenuElement) obj);
    }

    private boolean equalTo(int i, ImmutableOverflowMenuElement immutableOverflowMenuElement) {
        return this.actions.equals(immutableOverflowMenuElement.actions) && this.onItemSelected.equals(immutableOverflowMenuElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.actions.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "OverflowMenuElement{actions=" + this.actions + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "OverflowMenuElement", generator = "Immutables")
    static final class Json extends OverflowMenuElement {

        @Nullable
        List<ActionButtonElement> actions = Collections.emptyList();

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();
        boolean onItemSelectedIsSet;

        Json() {
        }

        @JsonProperty("actions")
        public void setActions(List<ActionButtonElement> list) {
            this.actions = list;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.OverflowMenuElement
        public List<ActionButtonElement> actions() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.OverflowMenuElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableOverflowMenuElement fromJson(Json json) {
        Builder builder = builder();
        List<ActionButtonElement> list = json.actions;
        if (list != null) {
            builder.addAllActions(list);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableOverflowMenuElement copyOf(OverflowMenuElement overflowMenuElement) {
        if (overflowMenuElement instanceof ImmutableOverflowMenuElement) {
            return (ImmutableOverflowMenuElement) overflowMenuElement;
        }
        return builder().from(overflowMenuElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "OverflowMenuElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;
        private List<ActionButtonElement> actions;
        private List<Method> onItemSelected;
        private long optBits;

        private Builder() {
            this.actions = new ArrayList();
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(OverflowMenuElement overflowMenuElement) {
            ImmutableOverflowMenuElement.requireNonNull(overflowMenuElement, "instance");
            addAllActions(overflowMenuElement.actions());
            addAllOnItemSelected(overflowMenuElement.onItemSelected());
            return this;
        }

        public final Builder addActions(ActionButtonElement actionButtonElement) {
            this.actions.add((ActionButtonElement) ImmutableOverflowMenuElement.requireNonNull(actionButtonElement, "actions element"));
            return this;
        }

        public final Builder addActions(ActionButtonElement... actionButtonElementArr) {
            for (ActionButtonElement actionButtonElement : actionButtonElementArr) {
                this.actions.add((ActionButtonElement) ImmutableOverflowMenuElement.requireNonNull(actionButtonElement, "actions element"));
            }
            return this;
        }

        @JsonProperty("actions")
        public final Builder actions(Iterable<? extends ActionButtonElement> iterable) {
            this.actions.clear();
            return addAllActions(iterable);
        }

        public final Builder addAllActions(Iterable<? extends ActionButtonElement> iterable) {
            Iterator<? extends ActionButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.actions.add((ActionButtonElement) ImmutableOverflowMenuElement.requireNonNull(it.next(), "actions element"));
            }
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableOverflowMenuElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableOverflowMenuElement.requireNonNull(method, "onItemSelected element"));
            }
            this.optBits |= 1;
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
                this.onItemSelected.add((Method) ImmutableOverflowMenuElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableOverflowMenuElement build() {
            return new ImmutableOverflowMenuElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 1) != 0;
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

    private static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
