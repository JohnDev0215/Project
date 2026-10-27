package com.spotify.voiceassistants.playermodels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class SpeakeasyDualResponse {

    public static abstract class CanPrepareResponse extends SpeakeasyDualResponse {
        private final String actionNlUri;

        public static final class ActionNlResponse extends CanPrepareResponse {
            private final String action;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ActionNlResponse(String str) {
                super(str, null);
                v03.j(str, "action");
                this.action = str;
            }

            public static /* synthetic */ ActionNlResponse copy$default(ActionNlResponse actionNlResponse, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = actionNlResponse.action;
                }
                return actionNlResponse.copy(str);
            }

            @Override // com.spotify.voiceassistants.playermodels.SpeakeasyDualResponse.CanPrepareResponse
            public FullSuccessResponse complete(CommandPlayResponse commandPlayResponse) {
                v03.j(commandPlayResponse, "commandPlayResponse");
                return new FullSuccessResponse(this.action, null, commandPlayResponse);
            }

            public final String component1() {
                return this.action;
            }

            public final ActionNlResponse copy(String str) {
                v03.j(str, "action");
                return new ActionNlResponse(str);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ActionNlResponse) && v03.b(this.action, ((ActionNlResponse) obj).action);
            }

            public final String getAction() {
                return this.action;
            }

            public int hashCode() {
                return this.action.hashCode();
            }

            public String toString() {
                return hf2.d(new StringBuilder("ActionNlResponse(action="), this.action, ')');
            }
        }

        public static final class PartialResponse extends CanPrepareResponse {
            private final String action;
            private final SearchResponse searchResponse;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PartialResponse(String str, SearchResponse searchResponse) {
                super(str, null);
                v03.j(str, "action");
                v03.j(searchResponse, "searchResponse");
                this.action = str;
                this.searchResponse = searchResponse;
            }

            public static /* synthetic */ PartialResponse copy$default(PartialResponse partialResponse, String str, SearchResponse searchResponse, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = partialResponse.action;
                }
                if ((i & 2) != 0) {
                    searchResponse = partialResponse.searchResponse;
                }
                return partialResponse.copy(str, searchResponse);
            }

            @Override // com.spotify.voiceassistants.playermodels.SpeakeasyDualResponse.CanPrepareResponse
            public FullSuccessResponse complete(CommandPlayResponse commandPlayResponse) {
                v03.j(commandPlayResponse, "commandPlayResponse");
                return new FullSuccessResponse(this.action, this.searchResponse, commandPlayResponse);
            }

            public final String component1() {
                return this.action;
            }

            public final SearchResponse component2() {
                return this.searchResponse;
            }

            public final PartialResponse copy(String str, SearchResponse searchResponse) {
                v03.j(str, "action");
                v03.j(searchResponse, "searchResponse");
                return new PartialResponse(str, searchResponse);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PartialResponse)) {
                    return false;
                }
                PartialResponse partialResponse = (PartialResponse) obj;
                return v03.b(this.action, partialResponse.action) && v03.b(this.searchResponse, partialResponse.searchResponse);
            }

            public final String getAction() {
                return this.action;
            }

            public final SearchResponse getSearchResponse() {
                return this.searchResponse;
            }

            public int hashCode() {
                return this.searchResponse.hashCode() + (this.action.hashCode() * 31);
            }

            public String toString() {
                return "PartialResponse(action=" + this.action + ", searchResponse=" + this.searchResponse + ')';
            }
        }

        public /* synthetic */ CanPrepareResponse(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }

        public abstract FullSuccessResponse complete(CommandPlayResponse commandPlayResponse);

        public final String getActionNlUri() {
            return this.actionNlUri;
        }

        private CanPrepareResponse(String str) {
            super(null);
            this.actionNlUri = str;
        }
    }

    public static final class FailedResponse extends SpeakeasyDualResponse {
        private final String errorMsg;
        private final SearchResponse searchResponse;
        private final String searchResult;

        public /* synthetic */ FailedResponse(String str, SearchResponse searchResponse, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, searchResponse, (i & 4) != 0 ? null : str2);
        }

        public static /* synthetic */ FailedResponse copy$default(FailedResponse failedResponse, String str, SearchResponse searchResponse, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = failedResponse.searchResult;
            }
            if ((i & 2) != 0) {
                searchResponse = failedResponse.searchResponse;
            }
            if ((i & 4) != 0) {
                str2 = failedResponse.errorMsg;
            }
            return failedResponse.copy(str, searchResponse, str2);
        }

        public final String component1() {
            return this.searchResult;
        }

        public final SearchResponse component2() {
            return this.searchResponse;
        }

        public final String component3() {
            return this.errorMsg;
        }

        public final FailedResponse copy(String str, SearchResponse searchResponse, String str2) {
            v03.j(str, "searchResult");
            v03.j(searchResponse, "searchResponse");
            return new FailedResponse(str, searchResponse, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FailedResponse)) {
                return false;
            }
            FailedResponse failedResponse = (FailedResponse) obj;
            return v03.b(this.searchResult, failedResponse.searchResult) && v03.b(this.searchResponse, failedResponse.searchResponse) && v03.b(this.errorMsg, failedResponse.errorMsg);
        }

        public final String getErrorMsg() {
            return this.errorMsg;
        }

        public final SearchResponse getSearchResponse() {
            return this.searchResponse;
        }

        public final String getSearchResult() {
            return this.searchResult;
        }

        public int hashCode() {
            int iHashCode = (this.searchResponse.hashCode() + (this.searchResult.hashCode() * 31)) * 31;
            String str = this.errorMsg;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("FailedResponse(searchResult=");
            sb.append(this.searchResult);
            sb.append(", searchResponse=");
            sb.append(this.searchResponse);
            sb.append(", errorMsg=");
            return hf2.d(sb, this.errorMsg, ')');
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FailedResponse(String str, SearchResponse searchResponse, String str2) {
            super(null);
            v03.j(str, "searchResult");
            v03.j(searchResponse, "searchResponse");
            this.searchResult = str;
            this.searchResponse = searchResponse;
            this.errorMsg = str2;
        }
    }

    public static final class FullSuccessResponse extends SpeakeasyDualResponse {
        private final String action;
        private final CommandPlayResponse commandPlayResponse;
        private final SearchResponse searchResponse;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FullSuccessResponse(String str, SearchResponse searchResponse, CommandPlayResponse commandPlayResponse) {
            super(null);
            v03.j(str, "action");
            v03.j(commandPlayResponse, "commandPlayResponse");
            this.action = str;
            this.searchResponse = searchResponse;
            this.commandPlayResponse = commandPlayResponse;
        }

        public static /* synthetic */ FullSuccessResponse copy$default(FullSuccessResponse fullSuccessResponse, String str, SearchResponse searchResponse, CommandPlayResponse commandPlayResponse, int i, Object obj) {
            if ((i & 1) != 0) {
                str = fullSuccessResponse.action;
            }
            if ((i & 2) != 0) {
                searchResponse = fullSuccessResponse.searchResponse;
            }
            if ((i & 4) != 0) {
                commandPlayResponse = fullSuccessResponse.commandPlayResponse;
            }
            return fullSuccessResponse.copy(str, searchResponse, commandPlayResponse);
        }

        public final String component1() {
            return this.action;
        }

        public final SearchResponse component2() {
            return this.searchResponse;
        }

        public final CommandPlayResponse component3() {
            return this.commandPlayResponse;
        }

        public final FullSuccessResponse copy(String str, SearchResponse searchResponse, CommandPlayResponse commandPlayResponse) {
            v03.j(str, "action");
            v03.j(commandPlayResponse, "commandPlayResponse");
            return new FullSuccessResponse(str, searchResponse, commandPlayResponse);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FullSuccessResponse)) {
                return false;
            }
            FullSuccessResponse fullSuccessResponse = (FullSuccessResponse) obj;
            return v03.b(this.action, fullSuccessResponse.action) && v03.b(this.searchResponse, fullSuccessResponse.searchResponse) && v03.b(this.commandPlayResponse, fullSuccessResponse.commandPlayResponse);
        }

        public final String getAction() {
            return this.action;
        }

        public final CommandPlayResponse getCommandPlayResponse() {
            return this.commandPlayResponse;
        }

        public final SearchResponse getSearchResponse() {
            return this.searchResponse;
        }

        public int hashCode() {
            int iHashCode = this.action.hashCode() * 31;
            SearchResponse searchResponse = this.searchResponse;
            return this.commandPlayResponse.hashCode() + ((iHashCode + (searchResponse == null ? 0 : searchResponse.hashCode())) * 31);
        }

        public String toString() {
            return "FullSuccessResponse(action=" + this.action + ", searchResponse=" + this.searchResponse + ", commandPlayResponse=" + this.commandPlayResponse + ')';
        }
    }

    private SpeakeasyDualResponse() {
    }

    public /* synthetic */ SpeakeasyDualResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
