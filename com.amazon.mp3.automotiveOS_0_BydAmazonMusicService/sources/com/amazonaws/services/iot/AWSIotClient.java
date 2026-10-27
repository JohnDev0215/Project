package com.amazonaws.services.iot;

import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.http.JsonResponseHandler;
import com.amazonaws.http.UrlHttpClient;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.iot.model.AcceptCertificateTransferRequest;
import com.amazonaws.services.iot.model.AddThingToBillingGroupRequest;
import com.amazonaws.services.iot.model.AddThingToBillingGroupResult;
import com.amazonaws.services.iot.model.AddThingToThingGroupRequest;
import com.amazonaws.services.iot.model.AddThingToThingGroupResult;
import com.amazonaws.services.iot.model.AssociateTargetsWithJobRequest;
import com.amazonaws.services.iot.model.AssociateTargetsWithJobResult;
import com.amazonaws.services.iot.model.AttachPolicyRequest;
import com.amazonaws.services.iot.model.AttachPrincipalPolicyRequest;
import com.amazonaws.services.iot.model.AttachSecurityProfileRequest;
import com.amazonaws.services.iot.model.AttachSecurityProfileResult;
import com.amazonaws.services.iot.model.AttachThingPrincipalRequest;
import com.amazonaws.services.iot.model.AttachThingPrincipalResult;
import com.amazonaws.services.iot.model.CancelAuditMitigationActionsTaskRequest;
import com.amazonaws.services.iot.model.CancelAuditMitigationActionsTaskResult;
import com.amazonaws.services.iot.model.CancelAuditTaskRequest;
import com.amazonaws.services.iot.model.CancelAuditTaskResult;
import com.amazonaws.services.iot.model.CancelCertificateTransferRequest;
import com.amazonaws.services.iot.model.CancelDetectMitigationActionsTaskRequest;
import com.amazonaws.services.iot.model.CancelDetectMitigationActionsTaskResult;
import com.amazonaws.services.iot.model.CancelJobExecutionRequest;
import com.amazonaws.services.iot.model.CancelJobRequest;
import com.amazonaws.services.iot.model.CancelJobResult;
import com.amazonaws.services.iot.model.ClearDefaultAuthorizerRequest;
import com.amazonaws.services.iot.model.ClearDefaultAuthorizerResult;
import com.amazonaws.services.iot.model.ConfirmTopicRuleDestinationRequest;
import com.amazonaws.services.iot.model.ConfirmTopicRuleDestinationResult;
import com.amazonaws.services.iot.model.CreateAuditSuppressionRequest;
import com.amazonaws.services.iot.model.CreateAuditSuppressionResult;
import com.amazonaws.services.iot.model.CreateAuthorizerRequest;
import com.amazonaws.services.iot.model.CreateAuthorizerResult;
import com.amazonaws.services.iot.model.CreateBillingGroupRequest;
import com.amazonaws.services.iot.model.CreateBillingGroupResult;
import com.amazonaws.services.iot.model.CreateCertificateFromCsrRequest;
import com.amazonaws.services.iot.model.CreateCertificateFromCsrResult;
import com.amazonaws.services.iot.model.CreateCustomMetricRequest;
import com.amazonaws.services.iot.model.CreateCustomMetricResult;
import com.amazonaws.services.iot.model.CreateDimensionRequest;
import com.amazonaws.services.iot.model.CreateDimensionResult;
import com.amazonaws.services.iot.model.CreateDomainConfigurationRequest;
import com.amazonaws.services.iot.model.CreateDomainConfigurationResult;
import com.amazonaws.services.iot.model.CreateDynamicThingGroupRequest;
import com.amazonaws.services.iot.model.CreateDynamicThingGroupResult;
import com.amazonaws.services.iot.model.CreateFleetMetricRequest;
import com.amazonaws.services.iot.model.CreateFleetMetricResult;
import com.amazonaws.services.iot.model.CreateJobRequest;
import com.amazonaws.services.iot.model.CreateJobResult;
import com.amazonaws.services.iot.model.CreateJobTemplateRequest;
import com.amazonaws.services.iot.model.CreateJobTemplateResult;
import com.amazonaws.services.iot.model.CreateKeysAndCertificateRequest;
import com.amazonaws.services.iot.model.CreateKeysAndCertificateResult;
import com.amazonaws.services.iot.model.CreateMitigationActionRequest;
import com.amazonaws.services.iot.model.CreateMitigationActionResult;
import com.amazonaws.services.iot.model.CreateOTAUpdateRequest;
import com.amazonaws.services.iot.model.CreateOTAUpdateResult;
import com.amazonaws.services.iot.model.CreatePolicyRequest;
import com.amazonaws.services.iot.model.CreatePolicyResult;
import com.amazonaws.services.iot.model.CreatePolicyVersionRequest;
import com.amazonaws.services.iot.model.CreatePolicyVersionResult;
import com.amazonaws.services.iot.model.CreateProvisioningClaimRequest;
import com.amazonaws.services.iot.model.CreateProvisioningClaimResult;
import com.amazonaws.services.iot.model.CreateProvisioningTemplateRequest;
import com.amazonaws.services.iot.model.CreateProvisioningTemplateResult;
import com.amazonaws.services.iot.model.CreateProvisioningTemplateVersionRequest;
import com.amazonaws.services.iot.model.CreateProvisioningTemplateVersionResult;
import com.amazonaws.services.iot.model.CreateRoleAliasRequest;
import com.amazonaws.services.iot.model.CreateRoleAliasResult;
import com.amazonaws.services.iot.model.CreateScheduledAuditRequest;
import com.amazonaws.services.iot.model.CreateScheduledAuditResult;
import com.amazonaws.services.iot.model.CreateSecurityProfileRequest;
import com.amazonaws.services.iot.model.CreateSecurityProfileResult;
import com.amazonaws.services.iot.model.CreateStreamRequest;
import com.amazonaws.services.iot.model.CreateStreamResult;
import com.amazonaws.services.iot.model.CreateThingGroupRequest;
import com.amazonaws.services.iot.model.CreateThingGroupResult;
import com.amazonaws.services.iot.model.CreateThingRequest;
import com.amazonaws.services.iot.model.CreateThingResult;
import com.amazonaws.services.iot.model.CreateThingTypeRequest;
import com.amazonaws.services.iot.model.CreateThingTypeResult;
import com.amazonaws.services.iot.model.CreateTopicRuleDestinationRequest;
import com.amazonaws.services.iot.model.CreateTopicRuleDestinationResult;
import com.amazonaws.services.iot.model.CreateTopicRuleRequest;
import com.amazonaws.services.iot.model.DeleteAccountAuditConfigurationRequest;
import com.amazonaws.services.iot.model.DeleteAccountAuditConfigurationResult;
import com.amazonaws.services.iot.model.DeleteAuditSuppressionRequest;
import com.amazonaws.services.iot.model.DeleteAuditSuppressionResult;
import com.amazonaws.services.iot.model.DeleteAuthorizerRequest;
import com.amazonaws.services.iot.model.DeleteAuthorizerResult;
import com.amazonaws.services.iot.model.DeleteBillingGroupRequest;
import com.amazonaws.services.iot.model.DeleteBillingGroupResult;
import com.amazonaws.services.iot.model.DeleteCACertificateRequest;
import com.amazonaws.services.iot.model.DeleteCACertificateResult;
import com.amazonaws.services.iot.model.DeleteCertificateRequest;
import com.amazonaws.services.iot.model.DeleteCustomMetricRequest;
import com.amazonaws.services.iot.model.DeleteCustomMetricResult;
import com.amazonaws.services.iot.model.DeleteDimensionRequest;
import com.amazonaws.services.iot.model.DeleteDimensionResult;
import com.amazonaws.services.iot.model.DeleteDomainConfigurationRequest;
import com.amazonaws.services.iot.model.DeleteDomainConfigurationResult;
import com.amazonaws.services.iot.model.DeleteDynamicThingGroupRequest;
import com.amazonaws.services.iot.model.DeleteDynamicThingGroupResult;
import com.amazonaws.services.iot.model.DeleteFleetMetricRequest;
import com.amazonaws.services.iot.model.DeleteJobExecutionRequest;
import com.amazonaws.services.iot.model.DeleteJobRequest;
import com.amazonaws.services.iot.model.DeleteJobTemplateRequest;
import com.amazonaws.services.iot.model.DeleteMitigationActionRequest;
import com.amazonaws.services.iot.model.DeleteMitigationActionResult;
import com.amazonaws.services.iot.model.DeleteOTAUpdateRequest;
import com.amazonaws.services.iot.model.DeleteOTAUpdateResult;
import com.amazonaws.services.iot.model.DeletePolicyRequest;
import com.amazonaws.services.iot.model.DeletePolicyVersionRequest;
import com.amazonaws.services.iot.model.DeleteProvisioningTemplateRequest;
import com.amazonaws.services.iot.model.DeleteProvisioningTemplateResult;
import com.amazonaws.services.iot.model.DeleteProvisioningTemplateVersionRequest;
import com.amazonaws.services.iot.model.DeleteProvisioningTemplateVersionResult;
import com.amazonaws.services.iot.model.DeleteRegistrationCodeRequest;
import com.amazonaws.services.iot.model.DeleteRegistrationCodeResult;
import com.amazonaws.services.iot.model.DeleteRoleAliasRequest;
import com.amazonaws.services.iot.model.DeleteRoleAliasResult;
import com.amazonaws.services.iot.model.DeleteScheduledAuditRequest;
import com.amazonaws.services.iot.model.DeleteScheduledAuditResult;
import com.amazonaws.services.iot.model.DeleteSecurityProfileRequest;
import com.amazonaws.services.iot.model.DeleteSecurityProfileResult;
import com.amazonaws.services.iot.model.DeleteStreamRequest;
import com.amazonaws.services.iot.model.DeleteStreamResult;
import com.amazonaws.services.iot.model.DeleteThingGroupRequest;
import com.amazonaws.services.iot.model.DeleteThingGroupResult;
import com.amazonaws.services.iot.model.DeleteThingRequest;
import com.amazonaws.services.iot.model.DeleteThingResult;
import com.amazonaws.services.iot.model.DeleteThingTypeRequest;
import com.amazonaws.services.iot.model.DeleteThingTypeResult;
import com.amazonaws.services.iot.model.DeleteTopicRuleDestinationRequest;
import com.amazonaws.services.iot.model.DeleteTopicRuleDestinationResult;
import com.amazonaws.services.iot.model.DeleteTopicRuleRequest;
import com.amazonaws.services.iot.model.DeleteV2LoggingLevelRequest;
import com.amazonaws.services.iot.model.DeprecateThingTypeRequest;
import com.amazonaws.services.iot.model.DeprecateThingTypeResult;
import com.amazonaws.services.iot.model.DescribeAccountAuditConfigurationRequest;
import com.amazonaws.services.iot.model.DescribeAccountAuditConfigurationResult;
import com.amazonaws.services.iot.model.DescribeAuditFindingRequest;
import com.amazonaws.services.iot.model.DescribeAuditFindingResult;
import com.amazonaws.services.iot.model.DescribeAuditMitigationActionsTaskRequest;
import com.amazonaws.services.iot.model.DescribeAuditMitigationActionsTaskResult;
import com.amazonaws.services.iot.model.DescribeAuditSuppressionRequest;
import com.amazonaws.services.iot.model.DescribeAuditSuppressionResult;
import com.amazonaws.services.iot.model.DescribeAuditTaskRequest;
import com.amazonaws.services.iot.model.DescribeAuditTaskResult;
import com.amazonaws.services.iot.model.DescribeAuthorizerRequest;
import com.amazonaws.services.iot.model.DescribeAuthorizerResult;
import com.amazonaws.services.iot.model.DescribeBillingGroupRequest;
import com.amazonaws.services.iot.model.DescribeBillingGroupResult;
import com.amazonaws.services.iot.model.DescribeCACertificateRequest;
import com.amazonaws.services.iot.model.DescribeCACertificateResult;
import com.amazonaws.services.iot.model.DescribeCertificateRequest;
import com.amazonaws.services.iot.model.DescribeCertificateResult;
import com.amazonaws.services.iot.model.DescribeCustomMetricRequest;
import com.amazonaws.services.iot.model.DescribeCustomMetricResult;
import com.amazonaws.services.iot.model.DescribeDefaultAuthorizerRequest;
import com.amazonaws.services.iot.model.DescribeDefaultAuthorizerResult;
import com.amazonaws.services.iot.model.DescribeDetectMitigationActionsTaskRequest;
import com.amazonaws.services.iot.model.DescribeDetectMitigationActionsTaskResult;
import com.amazonaws.services.iot.model.DescribeDimensionRequest;
import com.amazonaws.services.iot.model.DescribeDimensionResult;
import com.amazonaws.services.iot.model.DescribeDomainConfigurationRequest;
import com.amazonaws.services.iot.model.DescribeDomainConfigurationResult;
import com.amazonaws.services.iot.model.DescribeEndpointRequest;
import com.amazonaws.services.iot.model.DescribeEndpointResult;
import com.amazonaws.services.iot.model.DescribeEventConfigurationsRequest;
import com.amazonaws.services.iot.model.DescribeEventConfigurationsResult;
import com.amazonaws.services.iot.model.DescribeFleetMetricRequest;
import com.amazonaws.services.iot.model.DescribeFleetMetricResult;
import com.amazonaws.services.iot.model.DescribeIndexRequest;
import com.amazonaws.services.iot.model.DescribeIndexResult;
import com.amazonaws.services.iot.model.DescribeJobExecutionRequest;
import com.amazonaws.services.iot.model.DescribeJobExecutionResult;
import com.amazonaws.services.iot.model.DescribeJobRequest;
import com.amazonaws.services.iot.model.DescribeJobResult;
import com.amazonaws.services.iot.model.DescribeJobTemplateRequest;
import com.amazonaws.services.iot.model.DescribeJobTemplateResult;
import com.amazonaws.services.iot.model.DescribeManagedJobTemplateRequest;
import com.amazonaws.services.iot.model.DescribeManagedJobTemplateResult;
import com.amazonaws.services.iot.model.DescribeMitigationActionRequest;
import com.amazonaws.services.iot.model.DescribeMitigationActionResult;
import com.amazonaws.services.iot.model.DescribeProvisioningTemplateRequest;
import com.amazonaws.services.iot.model.DescribeProvisioningTemplateResult;
import com.amazonaws.services.iot.model.DescribeProvisioningTemplateVersionRequest;
import com.amazonaws.services.iot.model.DescribeProvisioningTemplateVersionResult;
import com.amazonaws.services.iot.model.DescribeRoleAliasRequest;
import com.amazonaws.services.iot.model.DescribeRoleAliasResult;
import com.amazonaws.services.iot.model.DescribeScheduledAuditRequest;
import com.amazonaws.services.iot.model.DescribeScheduledAuditResult;
import com.amazonaws.services.iot.model.DescribeSecurityProfileRequest;
import com.amazonaws.services.iot.model.DescribeSecurityProfileResult;
import com.amazonaws.services.iot.model.DescribeStreamRequest;
import com.amazonaws.services.iot.model.DescribeStreamResult;
import com.amazonaws.services.iot.model.DescribeThingGroupRequest;
import com.amazonaws.services.iot.model.DescribeThingGroupResult;
import com.amazonaws.services.iot.model.DescribeThingRegistrationTaskRequest;
import com.amazonaws.services.iot.model.DescribeThingRegistrationTaskResult;
import com.amazonaws.services.iot.model.DescribeThingRequest;
import com.amazonaws.services.iot.model.DescribeThingResult;
import com.amazonaws.services.iot.model.DescribeThingTypeRequest;
import com.amazonaws.services.iot.model.DescribeThingTypeResult;
import com.amazonaws.services.iot.model.DetachPolicyRequest;
import com.amazonaws.services.iot.model.DetachPrincipalPolicyRequest;
import com.amazonaws.services.iot.model.DetachSecurityProfileRequest;
import com.amazonaws.services.iot.model.DetachSecurityProfileResult;
import com.amazonaws.services.iot.model.DetachThingPrincipalRequest;
import com.amazonaws.services.iot.model.DetachThingPrincipalResult;
import com.amazonaws.services.iot.model.DisableTopicRuleRequest;
import com.amazonaws.services.iot.model.EnableTopicRuleRequest;
import com.amazonaws.services.iot.model.GetBehaviorModelTrainingSummariesRequest;
import com.amazonaws.services.iot.model.GetBehaviorModelTrainingSummariesResult;
import com.amazonaws.services.iot.model.GetBucketsAggregationRequest;
import com.amazonaws.services.iot.model.GetBucketsAggregationResult;
import com.amazonaws.services.iot.model.GetCardinalityRequest;
import com.amazonaws.services.iot.model.GetCardinalityResult;
import com.amazonaws.services.iot.model.GetEffectivePoliciesRequest;
import com.amazonaws.services.iot.model.GetEffectivePoliciesResult;
import com.amazonaws.services.iot.model.GetIndexingConfigurationRequest;
import com.amazonaws.services.iot.model.GetIndexingConfigurationResult;
import com.amazonaws.services.iot.model.GetJobDocumentRequest;
import com.amazonaws.services.iot.model.GetJobDocumentResult;
import com.amazonaws.services.iot.model.GetLoggingOptionsRequest;
import com.amazonaws.services.iot.model.GetLoggingOptionsResult;
import com.amazonaws.services.iot.model.GetOTAUpdateRequest;
import com.amazonaws.services.iot.model.GetOTAUpdateResult;
import com.amazonaws.services.iot.model.GetPercentilesRequest;
import com.amazonaws.services.iot.model.GetPercentilesResult;
import com.amazonaws.services.iot.model.GetPolicyRequest;
import com.amazonaws.services.iot.model.GetPolicyResult;
import com.amazonaws.services.iot.model.GetPolicyVersionRequest;
import com.amazonaws.services.iot.model.GetPolicyVersionResult;
import com.amazonaws.services.iot.model.GetRegistrationCodeRequest;
import com.amazonaws.services.iot.model.GetRegistrationCodeResult;
import com.amazonaws.services.iot.model.GetStatisticsRequest;
import com.amazonaws.services.iot.model.GetStatisticsResult;
import com.amazonaws.services.iot.model.GetTopicRuleDestinationRequest;
import com.amazonaws.services.iot.model.GetTopicRuleDestinationResult;
import com.amazonaws.services.iot.model.GetTopicRuleRequest;
import com.amazonaws.services.iot.model.GetTopicRuleResult;
import com.amazonaws.services.iot.model.GetV2LoggingOptionsRequest;
import com.amazonaws.services.iot.model.GetV2LoggingOptionsResult;
import com.amazonaws.services.iot.model.ListActiveViolationsRequest;
import com.amazonaws.services.iot.model.ListActiveViolationsResult;
import com.amazonaws.services.iot.model.ListAttachedPoliciesRequest;
import com.amazonaws.services.iot.model.ListAttachedPoliciesResult;
import com.amazonaws.services.iot.model.ListAuditFindingsRequest;
import com.amazonaws.services.iot.model.ListAuditFindingsResult;
import com.amazonaws.services.iot.model.ListAuditMitigationActionsExecutionsRequest;
import com.amazonaws.services.iot.model.ListAuditMitigationActionsExecutionsResult;
import com.amazonaws.services.iot.model.ListAuditMitigationActionsTasksRequest;
import com.amazonaws.services.iot.model.ListAuditMitigationActionsTasksResult;
import com.amazonaws.services.iot.model.ListAuditSuppressionsRequest;
import com.amazonaws.services.iot.model.ListAuditSuppressionsResult;
import com.amazonaws.services.iot.model.ListAuditTasksRequest;
import com.amazonaws.services.iot.model.ListAuditTasksResult;
import com.amazonaws.services.iot.model.ListAuthorizersRequest;
import com.amazonaws.services.iot.model.ListAuthorizersResult;
import com.amazonaws.services.iot.model.ListBillingGroupsRequest;
import com.amazonaws.services.iot.model.ListBillingGroupsResult;
import com.amazonaws.services.iot.model.ListCACertificatesRequest;
import com.amazonaws.services.iot.model.ListCACertificatesResult;
import com.amazonaws.services.iot.model.ListCertificatesByCARequest;
import com.amazonaws.services.iot.model.ListCertificatesByCAResult;
import com.amazonaws.services.iot.model.ListCertificatesRequest;
import com.amazonaws.services.iot.model.ListCertificatesResult;
import com.amazonaws.services.iot.model.ListCustomMetricsRequest;
import com.amazonaws.services.iot.model.ListCustomMetricsResult;
import com.amazonaws.services.iot.model.ListDetectMitigationActionsExecutionsRequest;
import com.amazonaws.services.iot.model.ListDetectMitigationActionsExecutionsResult;
import com.amazonaws.services.iot.model.ListDetectMitigationActionsTasksRequest;
import com.amazonaws.services.iot.model.ListDetectMitigationActionsTasksResult;
import com.amazonaws.services.iot.model.ListDimensionsRequest;
import com.amazonaws.services.iot.model.ListDimensionsResult;
import com.amazonaws.services.iot.model.ListDomainConfigurationsRequest;
import com.amazonaws.services.iot.model.ListDomainConfigurationsResult;
import com.amazonaws.services.iot.model.ListFleetMetricsRequest;
import com.amazonaws.services.iot.model.ListFleetMetricsResult;
import com.amazonaws.services.iot.model.ListIndicesRequest;
import com.amazonaws.services.iot.model.ListIndicesResult;
import com.amazonaws.services.iot.model.ListJobExecutionsForJobRequest;
import com.amazonaws.services.iot.model.ListJobExecutionsForJobResult;
import com.amazonaws.services.iot.model.ListJobExecutionsForThingRequest;
import com.amazonaws.services.iot.model.ListJobExecutionsForThingResult;
import com.amazonaws.services.iot.model.ListJobTemplatesRequest;
import com.amazonaws.services.iot.model.ListJobTemplatesResult;
import com.amazonaws.services.iot.model.ListJobsRequest;
import com.amazonaws.services.iot.model.ListJobsResult;
import com.amazonaws.services.iot.model.ListManagedJobTemplatesRequest;
import com.amazonaws.services.iot.model.ListManagedJobTemplatesResult;
import com.amazonaws.services.iot.model.ListMetricValuesRequest;
import com.amazonaws.services.iot.model.ListMetricValuesResult;
import com.amazonaws.services.iot.model.ListMitigationActionsRequest;
import com.amazonaws.services.iot.model.ListMitigationActionsResult;
import com.amazonaws.services.iot.model.ListOTAUpdatesRequest;
import com.amazonaws.services.iot.model.ListOTAUpdatesResult;
import com.amazonaws.services.iot.model.ListOutgoingCertificatesRequest;
import com.amazonaws.services.iot.model.ListOutgoingCertificatesResult;
import com.amazonaws.services.iot.model.ListPoliciesRequest;
import com.amazonaws.services.iot.model.ListPoliciesResult;
import com.amazonaws.services.iot.model.ListPolicyPrincipalsRequest;
import com.amazonaws.services.iot.model.ListPolicyPrincipalsResult;
import com.amazonaws.services.iot.model.ListPolicyVersionsRequest;
import com.amazonaws.services.iot.model.ListPolicyVersionsResult;
import com.amazonaws.services.iot.model.ListPrincipalPoliciesRequest;
import com.amazonaws.services.iot.model.ListPrincipalPoliciesResult;
import com.amazonaws.services.iot.model.ListPrincipalThingsRequest;
import com.amazonaws.services.iot.model.ListPrincipalThingsResult;
import com.amazonaws.services.iot.model.ListProvisioningTemplateVersionsRequest;
import com.amazonaws.services.iot.model.ListProvisioningTemplateVersionsResult;
import com.amazonaws.services.iot.model.ListProvisioningTemplatesRequest;
import com.amazonaws.services.iot.model.ListProvisioningTemplatesResult;
import com.amazonaws.services.iot.model.ListRelatedResourcesForAuditFindingRequest;
import com.amazonaws.services.iot.model.ListRelatedResourcesForAuditFindingResult;
import com.amazonaws.services.iot.model.ListRoleAliasesRequest;
import com.amazonaws.services.iot.model.ListRoleAliasesResult;
import com.amazonaws.services.iot.model.ListScheduledAuditsRequest;
import com.amazonaws.services.iot.model.ListScheduledAuditsResult;
import com.amazonaws.services.iot.model.ListSecurityProfilesForTargetRequest;
import com.amazonaws.services.iot.model.ListSecurityProfilesForTargetResult;
import com.amazonaws.services.iot.model.ListSecurityProfilesRequest;
import com.amazonaws.services.iot.model.ListSecurityProfilesResult;
import com.amazonaws.services.iot.model.ListStreamsRequest;
import com.amazonaws.services.iot.model.ListStreamsResult;
import com.amazonaws.services.iot.model.ListTagsForResourceRequest;
import com.amazonaws.services.iot.model.ListTagsForResourceResult;
import com.amazonaws.services.iot.model.ListTargetsForPolicyRequest;
import com.amazonaws.services.iot.model.ListTargetsForPolicyResult;
import com.amazonaws.services.iot.model.ListTargetsForSecurityProfileRequest;
import com.amazonaws.services.iot.model.ListTargetsForSecurityProfileResult;
import com.amazonaws.services.iot.model.ListThingGroupsForThingRequest;
import com.amazonaws.services.iot.model.ListThingGroupsForThingResult;
import com.amazonaws.services.iot.model.ListThingGroupsRequest;
import com.amazonaws.services.iot.model.ListThingGroupsResult;
import com.amazonaws.services.iot.model.ListThingPrincipalsRequest;
import com.amazonaws.services.iot.model.ListThingPrincipalsResult;
import com.amazonaws.services.iot.model.ListThingRegistrationTaskReportsRequest;
import com.amazonaws.services.iot.model.ListThingRegistrationTaskReportsResult;
import com.amazonaws.services.iot.model.ListThingRegistrationTasksRequest;
import com.amazonaws.services.iot.model.ListThingRegistrationTasksResult;
import com.amazonaws.services.iot.model.ListThingTypesRequest;
import com.amazonaws.services.iot.model.ListThingTypesResult;
import com.amazonaws.services.iot.model.ListThingsInBillingGroupRequest;
import com.amazonaws.services.iot.model.ListThingsInBillingGroupResult;
import com.amazonaws.services.iot.model.ListThingsInThingGroupRequest;
import com.amazonaws.services.iot.model.ListThingsInThingGroupResult;
import com.amazonaws.services.iot.model.ListThingsRequest;
import com.amazonaws.services.iot.model.ListThingsResult;
import com.amazonaws.services.iot.model.ListTopicRuleDestinationsRequest;
import com.amazonaws.services.iot.model.ListTopicRuleDestinationsResult;
import com.amazonaws.services.iot.model.ListTopicRulesRequest;
import com.amazonaws.services.iot.model.ListTopicRulesResult;
import com.amazonaws.services.iot.model.ListV2LoggingLevelsRequest;
import com.amazonaws.services.iot.model.ListV2LoggingLevelsResult;
import com.amazonaws.services.iot.model.ListViolationEventsRequest;
import com.amazonaws.services.iot.model.ListViolationEventsResult;
import com.amazonaws.services.iot.model.PutVerificationStateOnViolationRequest;
import com.amazonaws.services.iot.model.PutVerificationStateOnViolationResult;
import com.amazonaws.services.iot.model.RegisterCACertificateRequest;
import com.amazonaws.services.iot.model.RegisterCACertificateResult;
import com.amazonaws.services.iot.model.RegisterCertificateRequest;
import com.amazonaws.services.iot.model.RegisterCertificateResult;
import com.amazonaws.services.iot.model.RegisterCertificateWithoutCARequest;
import com.amazonaws.services.iot.model.RegisterCertificateWithoutCAResult;
import com.amazonaws.services.iot.model.RegisterThingRequest;
import com.amazonaws.services.iot.model.RegisterThingResult;
import com.amazonaws.services.iot.model.RejectCertificateTransferRequest;
import com.amazonaws.services.iot.model.RemoveThingFromBillingGroupRequest;
import com.amazonaws.services.iot.model.RemoveThingFromBillingGroupResult;
import com.amazonaws.services.iot.model.RemoveThingFromThingGroupRequest;
import com.amazonaws.services.iot.model.RemoveThingFromThingGroupResult;
import com.amazonaws.services.iot.model.ReplaceTopicRuleRequest;
import com.amazonaws.services.iot.model.SearchIndexRequest;
import com.amazonaws.services.iot.model.SearchIndexResult;
import com.amazonaws.services.iot.model.SetDefaultAuthorizerRequest;
import com.amazonaws.services.iot.model.SetDefaultAuthorizerResult;
import com.amazonaws.services.iot.model.SetDefaultPolicyVersionRequest;
import com.amazonaws.services.iot.model.SetLoggingOptionsRequest;
import com.amazonaws.services.iot.model.SetV2LoggingLevelRequest;
import com.amazonaws.services.iot.model.SetV2LoggingOptionsRequest;
import com.amazonaws.services.iot.model.StartAuditMitigationActionsTaskRequest;
import com.amazonaws.services.iot.model.StartAuditMitigationActionsTaskResult;
import com.amazonaws.services.iot.model.StartDetectMitigationActionsTaskRequest;
import com.amazonaws.services.iot.model.StartDetectMitigationActionsTaskResult;
import com.amazonaws.services.iot.model.StartOnDemandAuditTaskRequest;
import com.amazonaws.services.iot.model.StartOnDemandAuditTaskResult;
import com.amazonaws.services.iot.model.StartThingRegistrationTaskRequest;
import com.amazonaws.services.iot.model.StartThingRegistrationTaskResult;
import com.amazonaws.services.iot.model.StopThingRegistrationTaskRequest;
import com.amazonaws.services.iot.model.StopThingRegistrationTaskResult;
import com.amazonaws.services.iot.model.TagResourceRequest;
import com.amazonaws.services.iot.model.TagResourceResult;
import com.amazonaws.services.iot.model.TestAuthorizationRequest;
import com.amazonaws.services.iot.model.TestAuthorizationResult;
import com.amazonaws.services.iot.model.TestInvokeAuthorizerRequest;
import com.amazonaws.services.iot.model.TestInvokeAuthorizerResult;
import com.amazonaws.services.iot.model.TransferCertificateRequest;
import com.amazonaws.services.iot.model.TransferCertificateResult;
import com.amazonaws.services.iot.model.UntagResourceRequest;
import com.amazonaws.services.iot.model.UntagResourceResult;
import com.amazonaws.services.iot.model.UpdateAccountAuditConfigurationRequest;
import com.amazonaws.services.iot.model.UpdateAccountAuditConfigurationResult;
import com.amazonaws.services.iot.model.UpdateAuditSuppressionRequest;
import com.amazonaws.services.iot.model.UpdateAuditSuppressionResult;
import com.amazonaws.services.iot.model.UpdateAuthorizerRequest;
import com.amazonaws.services.iot.model.UpdateAuthorizerResult;
import com.amazonaws.services.iot.model.UpdateBillingGroupRequest;
import com.amazonaws.services.iot.model.UpdateBillingGroupResult;
import com.amazonaws.services.iot.model.UpdateCACertificateRequest;
import com.amazonaws.services.iot.model.UpdateCertificateRequest;
import com.amazonaws.services.iot.model.UpdateCustomMetricRequest;
import com.amazonaws.services.iot.model.UpdateCustomMetricResult;
import com.amazonaws.services.iot.model.UpdateDimensionRequest;
import com.amazonaws.services.iot.model.UpdateDimensionResult;
import com.amazonaws.services.iot.model.UpdateDomainConfigurationRequest;
import com.amazonaws.services.iot.model.UpdateDomainConfigurationResult;
import com.amazonaws.services.iot.model.UpdateDynamicThingGroupRequest;
import com.amazonaws.services.iot.model.UpdateDynamicThingGroupResult;
import com.amazonaws.services.iot.model.UpdateEventConfigurationsRequest;
import com.amazonaws.services.iot.model.UpdateEventConfigurationsResult;
import com.amazonaws.services.iot.model.UpdateFleetMetricRequest;
import com.amazonaws.services.iot.model.UpdateIndexingConfigurationRequest;
import com.amazonaws.services.iot.model.UpdateIndexingConfigurationResult;
import com.amazonaws.services.iot.model.UpdateJobRequest;
import com.amazonaws.services.iot.model.UpdateMitigationActionRequest;
import com.amazonaws.services.iot.model.UpdateMitigationActionResult;
import com.amazonaws.services.iot.model.UpdateProvisioningTemplateRequest;
import com.amazonaws.services.iot.model.UpdateProvisioningTemplateResult;
import com.amazonaws.services.iot.model.UpdateRoleAliasRequest;
import com.amazonaws.services.iot.model.UpdateRoleAliasResult;
import com.amazonaws.services.iot.model.UpdateScheduledAuditRequest;
import com.amazonaws.services.iot.model.UpdateScheduledAuditResult;
import com.amazonaws.services.iot.model.UpdateSecurityProfileRequest;
import com.amazonaws.services.iot.model.UpdateSecurityProfileResult;
import com.amazonaws.services.iot.model.UpdateStreamRequest;
import com.amazonaws.services.iot.model.UpdateStreamResult;
import com.amazonaws.services.iot.model.UpdateThingGroupRequest;
import com.amazonaws.services.iot.model.UpdateThingGroupResult;
import com.amazonaws.services.iot.model.UpdateThingGroupsForThingRequest;
import com.amazonaws.services.iot.model.UpdateThingGroupsForThingResult;
import com.amazonaws.services.iot.model.UpdateThingRequest;
import com.amazonaws.services.iot.model.UpdateThingResult;
import com.amazonaws.services.iot.model.UpdateTopicRuleDestinationRequest;
import com.amazonaws.services.iot.model.UpdateTopicRuleDestinationResult;
import com.amazonaws.services.iot.model.ValidateSecurityProfileBehaviorsRequest;
import com.amazonaws.services.iot.model.ValidateSecurityProfileBehaviorsResult;
import com.amazonaws.services.iot.model.transform.AcceptCertificateTransferRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AddThingToBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AddThingToBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.AddThingToThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AddThingToThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.AssociateTargetsWithJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AssociateTargetsWithJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.AttachPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AttachPrincipalPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AttachSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AttachSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.AttachThingPrincipalRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AttachThingPrincipalResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CancelAuditMitigationActionsTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelAuditMitigationActionsTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CancelAuditTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelAuditTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CancelCertificateTransferRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelDetectMitigationActionsTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelDetectMitigationActionsTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CancelJobExecutionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CertificateConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.CertificateStateExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.CertificateValidationExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ClearDefaultAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ClearDefaultAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ConfirmTopicRuleDestinationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ConfirmTopicRuleDestinationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ConflictingResourceUpdateExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateAuditSuppressionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateAuditSuppressionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateCertificateFromCsrRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateCertificateFromCsrResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateCustomMetricRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateCustomMetricResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateDimensionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateDimensionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateDomainConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateDomainConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateDynamicThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateDynamicThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateFleetMetricRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateFleetMetricResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateJobTemplateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateJobTemplateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateKeysAndCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateKeysAndCertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateMitigationActionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateMitigationActionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateOTAUpdateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateOTAUpdateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreatePolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreatePolicyResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreatePolicyVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreatePolicyVersionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateProvisioningClaimRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateProvisioningClaimResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateProvisioningTemplateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateProvisioningTemplateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateProvisioningTemplateVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateProvisioningTemplateVersionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateRoleAliasRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateRoleAliasResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateScheduledAuditRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateScheduledAuditResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateStreamRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateStreamResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingTypeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingTypeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateTopicRuleDestinationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateTopicRuleDestinationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAccountAuditConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAccountAuditConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAuditSuppressionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAuditSuppressionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteCACertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteCACertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteCustomMetricRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteCustomMetricResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteDimensionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteDimensionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteDomainConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteDomainConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteDynamicThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteDynamicThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteFleetMetricRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteJobExecutionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteJobTemplateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteMitigationActionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteMitigationActionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteOTAUpdateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteOTAUpdateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeletePolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeletePolicyVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteProvisioningTemplateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteProvisioningTemplateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteProvisioningTemplateVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteProvisioningTemplateVersionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteRegistrationCodeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteRegistrationCodeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteRoleAliasRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteRoleAliasResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteScheduledAuditRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteScheduledAuditResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteStreamRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteStreamResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingTypeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingTypeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteTopicRuleDestinationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteTopicRuleDestinationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteV2LoggingLevelRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeprecateThingTypeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeprecateThingTypeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAccountAuditConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAccountAuditConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditFindingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditFindingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditMitigationActionsTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditMitigationActionsTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditSuppressionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditSuppressionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCACertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCACertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCustomMetricRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCustomMetricResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDefaultAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDefaultAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDetectMitigationActionsTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDetectMitigationActionsTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDimensionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDimensionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDomainConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDomainConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeEndpointRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeEndpointResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeEventConfigurationsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeEventConfigurationsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeFleetMetricRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeFleetMetricResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeIndexRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeIndexResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobExecutionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobExecutionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobTemplateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobTemplateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeManagedJobTemplateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeManagedJobTemplateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeMitigationActionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeMitigationActionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeProvisioningTemplateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeProvisioningTemplateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeProvisioningTemplateVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeProvisioningTemplateVersionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeRoleAliasRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeRoleAliasResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeScheduledAuditRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeScheduledAuditResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeStreamRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeStreamResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingRegistrationTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingRegistrationTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingTypeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingTypeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DetachPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DetachPrincipalPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DetachSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DetachSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DetachThingPrincipalRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DetachThingPrincipalResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DisableTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.EnableTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetBehaviorModelTrainingSummariesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetBehaviorModelTrainingSummariesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetBucketsAggregationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetBucketsAggregationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetCardinalityRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetCardinalityResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetEffectivePoliciesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetEffectivePoliciesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetIndexingConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetIndexingConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetJobDocumentRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetJobDocumentResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetLoggingOptionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetLoggingOptionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetOTAUpdateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetOTAUpdateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetPercentilesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetPercentilesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetPolicyResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetPolicyVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetPolicyVersionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetRegistrationCodeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetRegistrationCodeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetStatisticsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetStatisticsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetTopicRuleDestinationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetTopicRuleDestinationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetTopicRuleResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetV2LoggingOptionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetV2LoggingOptionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.IndexNotReadyExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InternalExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InternalFailureExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InternalServerExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidAggregationExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidQueryExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidRequestExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidResponseExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidStateTransitionExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.LimitExceededExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListActiveViolationsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListActiveViolationsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAttachedPoliciesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAttachedPoliciesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditFindingsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditFindingsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditMitigationActionsExecutionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditMitigationActionsExecutionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditMitigationActionsTasksRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditMitigationActionsTasksResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditSuppressionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditSuppressionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditTasksRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditTasksResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuthorizersRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuthorizersResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListBillingGroupsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListBillingGroupsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListCACertificatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListCACertificatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListCertificatesByCARequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListCertificatesByCAResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListCertificatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListCertificatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListCustomMetricsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListCustomMetricsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListDetectMitigationActionsExecutionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListDetectMitigationActionsExecutionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListDetectMitigationActionsTasksRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListDetectMitigationActionsTasksResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListDimensionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListDimensionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListDomainConfigurationsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListDomainConfigurationsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListFleetMetricsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListFleetMetricsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListIndicesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListIndicesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListJobExecutionsForJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListJobExecutionsForJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListJobExecutionsForThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListJobExecutionsForThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListJobTemplatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListJobTemplatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListJobsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListJobsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListManagedJobTemplatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListManagedJobTemplatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListMetricValuesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListMetricValuesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListMitigationActionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListMitigationActionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListOTAUpdatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListOTAUpdatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListOutgoingCertificatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListOutgoingCertificatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPoliciesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPoliciesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPolicyPrincipalsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPolicyPrincipalsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPolicyVersionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPolicyVersionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPrincipalPoliciesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPrincipalPoliciesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPrincipalThingsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPrincipalThingsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListProvisioningTemplateVersionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListProvisioningTemplateVersionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListProvisioningTemplatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListProvisioningTemplatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListRelatedResourcesForAuditFindingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListRelatedResourcesForAuditFindingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListRoleAliasesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListRoleAliasesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListScheduledAuditsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListScheduledAuditsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListSecurityProfilesForTargetRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListSecurityProfilesForTargetResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListSecurityProfilesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListSecurityProfilesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListStreamsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListStreamsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTagsForResourceRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTagsForResourceResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTargetsForPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTargetsForPolicyResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTargetsForSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTargetsForSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingGroupsForThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingGroupsForThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingGroupsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingGroupsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingPrincipalsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingPrincipalsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingRegistrationTaskReportsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingRegistrationTaskReportsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingRegistrationTasksRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingRegistrationTasksResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingTypesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingTypesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsInBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsInBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsInThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsInThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTopicRuleDestinationsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTopicRuleDestinationsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTopicRulesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTopicRulesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListV2LoggingLevelsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListV2LoggingLevelsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListViolationEventsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListViolationEventsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.MalformedPolicyExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.NotConfiguredExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.PutVerificationStateOnViolationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.PutVerificationStateOnViolationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCACertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCACertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCertificateWithoutCARequestMarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCertificateWithoutCAResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegisterThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RegisterThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegistrationCodeValidationExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.RejectCertificateTransferRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RemoveThingFromBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RemoveThingFromBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RemoveThingFromThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RemoveThingFromThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ReplaceTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ResourceAlreadyExistsExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ResourceRegistrationFailureExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.SearchIndexRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SearchIndexResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ServiceUnavailableExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.SetDefaultAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SetDefaultAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.SetDefaultPolicyVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SetLoggingOptionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SetV2LoggingLevelRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SetV2LoggingOptionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SqlParseExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.StartAuditMitigationActionsTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.StartAuditMitigationActionsTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.StartDetectMitigationActionsTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.StartDetectMitigationActionsTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.StartOnDemandAuditTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.StartOnDemandAuditTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.StartThingRegistrationTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.StartThingRegistrationTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.StopThingRegistrationTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.StopThingRegistrationTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.TagResourceRequestMarshaller;
import com.amazonaws.services.iot.model.transform.TagResourceResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.TaskAlreadyExistsExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.TestAuthorizationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.TestAuthorizationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.TestInvokeAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.TestInvokeAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ThrottlingExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.TransferAlreadyCompletedExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.TransferCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.TransferCertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.TransferConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.UnauthorizedExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.UntagResourceRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UntagResourceResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAccountAuditConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAccountAuditConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAuditSuppressionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAuditSuppressionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateCACertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateCustomMetricRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateCustomMetricResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateDimensionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateDimensionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateDomainConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateDomainConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateDynamicThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateDynamicThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateEventConfigurationsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateEventConfigurationsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateFleetMetricRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateIndexingConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateIndexingConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateMitigationActionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateMitigationActionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateProvisioningTemplateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateProvisioningTemplateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateRoleAliasRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateRoleAliasResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateScheduledAuditRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateScheduledAuditResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateStreamRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateStreamResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingGroupsForThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingGroupsForThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateTopicRuleDestinationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateTopicRuleDestinationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ValidateSecurityProfileBehaviorsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ValidateSecurityProfileBehaviorsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.VersionConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.VersionsLimitExceededExceptionUnmarshaller;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AWSIotClient extends AmazonWebServiceClient implements AWSIot {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    @Deprecated
    public AWSIotClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    @Deprecated
    public AWSIotClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AWSIotClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AWSIotClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AWSIotClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AWSIotClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AWSIotClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AWSIotClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    private void init() {
        ArrayList arrayList = new ArrayList();
        this.jsonErrorUnmarshallers = arrayList;
        arrayList.add(new CertificateConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CertificateStateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CertificateValidationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ConflictingResourceUpdateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DeleteConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new IndexNotReadyExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalFailureExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalServerExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidAggregationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidQueryExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidRequestExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidResponseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidStateTransitionExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new MalformedPolicyExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new NotConfiguredExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new RegistrationCodeValidationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceAlreadyExistsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceRegistrationFailureExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ServiceUnavailableExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new SqlParseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TaskAlreadyExistsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ThrottlingExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TransferAlreadyCompletedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TransferConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new UnauthorizedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new VersionConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new VersionsLimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setServiceNameIntern("execute-api");
        setEndpoint("iot.us-east-1.amazonaws.com");
        this.endpointPrefix = "iot";
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/iot/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/iot/request.handler2s"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AcceptCertificateTransferRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void acceptCertificateTransfer(AcceptCertificateTransferRequest acceptCertificateTransferRequest) throws Throwable {
        Request<AcceptCertificateTransferRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(acceptCertificateTransferRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new AcceptCertificateTransferRequestMarshaller().marshall((AcceptCertificateTransferRequest) acceptCertificateTransferRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                acceptCertificateTransferRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, acceptCertificateTransferRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, acceptCertificateTransferRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AddThingToBillingGroupResult addThingToBillingGroup(AddThingToBillingGroupRequest addThingToBillingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(addThingToBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<AddThingToBillingGroupRequest> requestMarshall = new AddThingToBillingGroupRequestMarshaller().marshall(addThingToBillingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new AddThingToBillingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        AddThingToBillingGroupResult addThingToBillingGroupResult = (AddThingToBillingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return addThingToBillingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = addThingToBillingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AddThingToThingGroupResult addThingToThingGroup(AddThingToThingGroupRequest addThingToThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(addThingToThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<AddThingToThingGroupRequest> requestMarshall = new AddThingToThingGroupRequestMarshaller().marshall(addThingToThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new AddThingToThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        AddThingToThingGroupResult addThingToThingGroupResult = (AddThingToThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return addThingToThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = addThingToThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AssociateTargetsWithJobResult associateTargetsWithJob(AssociateTargetsWithJobRequest associateTargetsWithJobRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(associateTargetsWithJobRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<AssociateTargetsWithJobRequest> requestMarshall = new AssociateTargetsWithJobRequestMarshaller().marshall(associateTargetsWithJobRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new AssociateTargetsWithJobResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        AssociateTargetsWithJobResult associateTargetsWithJobResult = (AssociateTargetsWithJobResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return associateTargetsWithJobResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = associateTargetsWithJobRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AttachPolicyRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void attachPolicy(AttachPolicyRequest attachPolicyRequest) throws Throwable {
        Request<AttachPolicyRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(attachPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new AttachPolicyRequestMarshaller().marshall((AttachPolicyRequest) attachPolicyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                attachPolicyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, attachPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, attachPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AttachPrincipalPolicyRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public void attachPrincipalPolicy(AttachPrincipalPolicyRequest attachPrincipalPolicyRequest) throws Throwable {
        Request<AttachPrincipalPolicyRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(attachPrincipalPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new AttachPrincipalPolicyRequestMarshaller().marshall((AttachPrincipalPolicyRequest) attachPrincipalPolicyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                attachPrincipalPolicyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, attachPrincipalPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, attachPrincipalPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AttachSecurityProfileResult attachSecurityProfile(AttachSecurityProfileRequest attachSecurityProfileRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(attachSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<AttachSecurityProfileRequest> requestMarshall = new AttachSecurityProfileRequestMarshaller().marshall(attachSecurityProfileRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new AttachSecurityProfileResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        AttachSecurityProfileResult attachSecurityProfileResult = (AttachSecurityProfileResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return attachSecurityProfileResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = attachSecurityProfileRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AttachThingPrincipalResult attachThingPrincipal(AttachThingPrincipalRequest attachThingPrincipalRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(attachThingPrincipalRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<AttachThingPrincipalRequest> requestMarshall = new AttachThingPrincipalRequestMarshaller().marshall(attachThingPrincipalRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new AttachThingPrincipalResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        AttachThingPrincipalResult attachThingPrincipalResult = (AttachThingPrincipalResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return attachThingPrincipalResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = attachThingPrincipalRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CancelAuditMitigationActionsTaskResult cancelAuditMitigationActionsTask(CancelAuditMitigationActionsTaskRequest cancelAuditMitigationActionsTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(cancelAuditMitigationActionsTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CancelAuditMitigationActionsTaskRequest> requestMarshall = new CancelAuditMitigationActionsTaskRequestMarshaller().marshall(cancelAuditMitigationActionsTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CancelAuditMitigationActionsTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CancelAuditMitigationActionsTaskResult cancelAuditMitigationActionsTaskResult = (CancelAuditMitigationActionsTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return cancelAuditMitigationActionsTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = cancelAuditMitigationActionsTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CancelAuditTaskResult cancelAuditTask(CancelAuditTaskRequest cancelAuditTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(cancelAuditTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CancelAuditTaskRequest> requestMarshall = new CancelAuditTaskRequestMarshaller().marshall(cancelAuditTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CancelAuditTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CancelAuditTaskResult cancelAuditTaskResult = (CancelAuditTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return cancelAuditTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = cancelAuditTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CancelCertificateTransferRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void cancelCertificateTransfer(CancelCertificateTransferRequest cancelCertificateTransferRequest) throws Throwable {
        Request<CancelCertificateTransferRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(cancelCertificateTransferRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new CancelCertificateTransferRequestMarshaller().marshall((CancelCertificateTransferRequest) cancelCertificateTransferRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cancelCertificateTransferRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, cancelCertificateTransferRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, cancelCertificateTransferRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CancelDetectMitigationActionsTaskResult cancelDetectMitigationActionsTask(CancelDetectMitigationActionsTaskRequest cancelDetectMitigationActionsTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(cancelDetectMitigationActionsTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CancelDetectMitigationActionsTaskRequest> requestMarshall = new CancelDetectMitigationActionsTaskRequestMarshaller().marshall(cancelDetectMitigationActionsTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CancelDetectMitigationActionsTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CancelDetectMitigationActionsTaskResult cancelDetectMitigationActionsTaskResult = (CancelDetectMitigationActionsTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return cancelDetectMitigationActionsTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = cancelDetectMitigationActionsTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CancelJobResult cancelJob(CancelJobRequest cancelJobRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(cancelJobRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CancelJobRequest> requestMarshall = new CancelJobRequestMarshaller().marshall(cancelJobRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CancelJobResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CancelJobResult cancelJobResult = (CancelJobResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return cancelJobResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = cancelJobRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CancelJobExecutionRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void cancelJobExecution(CancelJobExecutionRequest cancelJobExecutionRequest) throws Throwable {
        Request<CancelJobExecutionRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(cancelJobExecutionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new CancelJobExecutionRequestMarshaller().marshall((CancelJobExecutionRequest) cancelJobExecutionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cancelJobExecutionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, cancelJobExecutionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, cancelJobExecutionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ClearDefaultAuthorizerResult clearDefaultAuthorizer(ClearDefaultAuthorizerRequest clearDefaultAuthorizerRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(clearDefaultAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ClearDefaultAuthorizerRequest> requestMarshall = new ClearDefaultAuthorizerRequestMarshaller().marshall(clearDefaultAuthorizerRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ClearDefaultAuthorizerResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ClearDefaultAuthorizerResult clearDefaultAuthorizerResult = (ClearDefaultAuthorizerResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return clearDefaultAuthorizerResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = clearDefaultAuthorizerRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ConfirmTopicRuleDestinationResult confirmTopicRuleDestination(ConfirmTopicRuleDestinationRequest confirmTopicRuleDestinationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(confirmTopicRuleDestinationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ConfirmTopicRuleDestinationRequest> requestMarshall = new ConfirmTopicRuleDestinationRequestMarshaller().marshall(confirmTopicRuleDestinationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ConfirmTopicRuleDestinationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ConfirmTopicRuleDestinationResult confirmTopicRuleDestinationResult = (ConfirmTopicRuleDestinationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return confirmTopicRuleDestinationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = confirmTopicRuleDestinationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateAuditSuppressionResult createAuditSuppression(CreateAuditSuppressionRequest createAuditSuppressionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createAuditSuppressionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateAuditSuppressionRequest> requestMarshall = new CreateAuditSuppressionRequestMarshaller().marshall(createAuditSuppressionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateAuditSuppressionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateAuditSuppressionResult createAuditSuppressionResult = (CreateAuditSuppressionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createAuditSuppressionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createAuditSuppressionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateAuthorizerResult createAuthorizer(CreateAuthorizerRequest createAuthorizerRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateAuthorizerRequest> requestMarshall = new CreateAuthorizerRequestMarshaller().marshall(createAuthorizerRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateAuthorizerResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateAuthorizerResult createAuthorizerResult = (CreateAuthorizerResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createAuthorizerResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createAuthorizerRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateBillingGroupResult createBillingGroup(CreateBillingGroupRequest createBillingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateBillingGroupRequest> requestMarshall = new CreateBillingGroupRequestMarshaller().marshall(createBillingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateBillingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateBillingGroupResult createBillingGroupResult = (CreateBillingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createBillingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createBillingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateCertificateFromCsrResult createCertificateFromCsr(CreateCertificateFromCsrRequest createCertificateFromCsrRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createCertificateFromCsrRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateCertificateFromCsrRequest> requestMarshall = new CreateCertificateFromCsrRequestMarshaller().marshall(createCertificateFromCsrRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateCertificateFromCsrResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateCertificateFromCsrResult createCertificateFromCsrResult = (CreateCertificateFromCsrResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createCertificateFromCsrResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createCertificateFromCsrRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateCustomMetricResult createCustomMetric(CreateCustomMetricRequest createCustomMetricRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createCustomMetricRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateCustomMetricRequest> requestMarshall = new CreateCustomMetricRequestMarshaller().marshall(createCustomMetricRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateCustomMetricResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateCustomMetricResult createCustomMetricResult = (CreateCustomMetricResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createCustomMetricResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createCustomMetricRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateDimensionResult createDimension(CreateDimensionRequest createDimensionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createDimensionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateDimensionRequest> requestMarshall = new CreateDimensionRequestMarshaller().marshall(createDimensionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateDimensionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateDimensionResult createDimensionResult = (CreateDimensionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createDimensionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createDimensionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateDomainConfigurationResult createDomainConfiguration(CreateDomainConfigurationRequest createDomainConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createDomainConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateDomainConfigurationRequest> requestMarshall = new CreateDomainConfigurationRequestMarshaller().marshall(createDomainConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateDomainConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateDomainConfigurationResult createDomainConfigurationResult = (CreateDomainConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createDomainConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createDomainConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateDynamicThingGroupResult createDynamicThingGroup(CreateDynamicThingGroupRequest createDynamicThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createDynamicThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateDynamicThingGroupRequest> requestMarshall = new CreateDynamicThingGroupRequestMarshaller().marshall(createDynamicThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateDynamicThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateDynamicThingGroupResult createDynamicThingGroupResult = (CreateDynamicThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createDynamicThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createDynamicThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateFleetMetricResult createFleetMetric(CreateFleetMetricRequest createFleetMetricRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createFleetMetricRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateFleetMetricRequest> requestMarshall = new CreateFleetMetricRequestMarshaller().marshall(createFleetMetricRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateFleetMetricResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateFleetMetricResult createFleetMetricResult = (CreateFleetMetricResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createFleetMetricResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createFleetMetricRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateJobResult createJob(CreateJobRequest createJobRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createJobRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateJobRequest> requestMarshall = new CreateJobRequestMarshaller().marshall(createJobRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateJobResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateJobResult createJobResult = (CreateJobResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createJobResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createJobRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateJobTemplateResult createJobTemplate(CreateJobTemplateRequest createJobTemplateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createJobTemplateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateJobTemplateRequest> requestMarshall = new CreateJobTemplateRequestMarshaller().marshall(createJobTemplateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateJobTemplateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateJobTemplateResult createJobTemplateResult = (CreateJobTemplateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createJobTemplateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createJobTemplateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateKeysAndCertificateResult createKeysAndCertificate(CreateKeysAndCertificateRequest createKeysAndCertificateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createKeysAndCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateKeysAndCertificateRequest> requestMarshall = new CreateKeysAndCertificateRequestMarshaller().marshall(createKeysAndCertificateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateKeysAndCertificateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateKeysAndCertificateResult createKeysAndCertificateResult = (CreateKeysAndCertificateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createKeysAndCertificateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createKeysAndCertificateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateMitigationActionResult createMitigationAction(CreateMitigationActionRequest createMitigationActionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createMitigationActionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateMitigationActionRequest> requestMarshall = new CreateMitigationActionRequestMarshaller().marshall(createMitigationActionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateMitigationActionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateMitigationActionResult createMitigationActionResult = (CreateMitigationActionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createMitigationActionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createMitigationActionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateOTAUpdateResult createOTAUpdate(CreateOTAUpdateRequest createOTAUpdateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createOTAUpdateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateOTAUpdateRequest> requestMarshall = new CreateOTAUpdateRequestMarshaller().marshall(createOTAUpdateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateOTAUpdateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateOTAUpdateResult createOTAUpdateResult = (CreateOTAUpdateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createOTAUpdateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createOTAUpdateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreatePolicyResult createPolicy(CreatePolicyRequest createPolicyRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreatePolicyRequest> requestMarshall = new CreatePolicyRequestMarshaller().marshall(createPolicyRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreatePolicyResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreatePolicyResult createPolicyResult = (CreatePolicyResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createPolicyResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createPolicyRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreatePolicyVersionResult createPolicyVersion(CreatePolicyVersionRequest createPolicyVersionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createPolicyVersionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreatePolicyVersionRequest> requestMarshall = new CreatePolicyVersionRequestMarshaller().marshall(createPolicyVersionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreatePolicyVersionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreatePolicyVersionResult createPolicyVersionResult = (CreatePolicyVersionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createPolicyVersionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createPolicyVersionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateProvisioningClaimResult createProvisioningClaim(CreateProvisioningClaimRequest createProvisioningClaimRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createProvisioningClaimRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateProvisioningClaimRequest> requestMarshall = new CreateProvisioningClaimRequestMarshaller().marshall(createProvisioningClaimRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateProvisioningClaimResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateProvisioningClaimResult createProvisioningClaimResult = (CreateProvisioningClaimResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createProvisioningClaimResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createProvisioningClaimRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateProvisioningTemplateResult createProvisioningTemplate(CreateProvisioningTemplateRequest createProvisioningTemplateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createProvisioningTemplateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateProvisioningTemplateRequest> requestMarshall = new CreateProvisioningTemplateRequestMarshaller().marshall(createProvisioningTemplateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateProvisioningTemplateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateProvisioningTemplateResult createProvisioningTemplateResult = (CreateProvisioningTemplateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createProvisioningTemplateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createProvisioningTemplateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateProvisioningTemplateVersionResult createProvisioningTemplateVersion(CreateProvisioningTemplateVersionRequest createProvisioningTemplateVersionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createProvisioningTemplateVersionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateProvisioningTemplateVersionRequest> requestMarshall = new CreateProvisioningTemplateVersionRequestMarshaller().marshall(createProvisioningTemplateVersionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateProvisioningTemplateVersionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateProvisioningTemplateVersionResult createProvisioningTemplateVersionResult = (CreateProvisioningTemplateVersionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createProvisioningTemplateVersionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createProvisioningTemplateVersionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateRoleAliasResult createRoleAlias(CreateRoleAliasRequest createRoleAliasRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createRoleAliasRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateRoleAliasRequest> requestMarshall = new CreateRoleAliasRequestMarshaller().marshall(createRoleAliasRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateRoleAliasResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateRoleAliasResult createRoleAliasResult = (CreateRoleAliasResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createRoleAliasResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createRoleAliasRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateScheduledAuditResult createScheduledAudit(CreateScheduledAuditRequest createScheduledAuditRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createScheduledAuditRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateScheduledAuditRequest> requestMarshall = new CreateScheduledAuditRequestMarshaller().marshall(createScheduledAuditRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateScheduledAuditResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateScheduledAuditResult createScheduledAuditResult = (CreateScheduledAuditResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createScheduledAuditResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createScheduledAuditRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateSecurityProfileResult createSecurityProfile(CreateSecurityProfileRequest createSecurityProfileRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateSecurityProfileRequest> requestMarshall = new CreateSecurityProfileRequestMarshaller().marshall(createSecurityProfileRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateSecurityProfileResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateSecurityProfileResult createSecurityProfileResult = (CreateSecurityProfileResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createSecurityProfileResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createSecurityProfileRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateStreamResult createStream(CreateStreamRequest createStreamRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createStreamRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateStreamRequest> requestMarshall = new CreateStreamRequestMarshaller().marshall(createStreamRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateStreamResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateStreamResult createStreamResult = (CreateStreamResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createStreamResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createStreamRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateThingResult createThing(CreateThingRequest createThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateThingRequest> requestMarshall = new CreateThingRequestMarshaller().marshall(createThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateThingResult createThingResult = (CreateThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateThingGroupResult createThingGroup(CreateThingGroupRequest createThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateThingGroupRequest> requestMarshall = new CreateThingGroupRequestMarshaller().marshall(createThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateThingGroupResult createThingGroupResult = (CreateThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateThingTypeResult createThingType(CreateThingTypeRequest createThingTypeRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createThingTypeRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateThingTypeRequest> requestMarshall = new CreateThingTypeRequestMarshaller().marshall(createThingTypeRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateThingTypeResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateThingTypeResult createThingTypeResult = (CreateThingTypeResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createThingTypeResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createThingTypeRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void createTopicRule(CreateTopicRuleRequest createTopicRuleRequest) throws Throwable {
        Request<CreateTopicRuleRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new CreateTopicRuleRequestMarshaller().marshall((CreateTopicRuleRequest) createTopicRuleRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                createTopicRuleRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, createTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateTopicRuleDestinationResult createTopicRuleDestination(CreateTopicRuleDestinationRequest createTopicRuleDestinationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(createTopicRuleDestinationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<CreateTopicRuleDestinationRequest> requestMarshall = new CreateTopicRuleDestinationRequestMarshaller().marshall(createTopicRuleDestinationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new CreateTopicRuleDestinationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        CreateTopicRuleDestinationResult createTopicRuleDestinationResult = (CreateTopicRuleDestinationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return createTopicRuleDestinationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = createTopicRuleDestinationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteAccountAuditConfigurationResult deleteAccountAuditConfiguration(DeleteAccountAuditConfigurationRequest deleteAccountAuditConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteAccountAuditConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteAccountAuditConfigurationRequest> requestMarshall = new DeleteAccountAuditConfigurationRequestMarshaller().marshall(deleteAccountAuditConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteAccountAuditConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteAccountAuditConfigurationResult deleteAccountAuditConfigurationResult = (DeleteAccountAuditConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteAccountAuditConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteAccountAuditConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteAuditSuppressionResult deleteAuditSuppression(DeleteAuditSuppressionRequest deleteAuditSuppressionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteAuditSuppressionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteAuditSuppressionRequest> requestMarshall = new DeleteAuditSuppressionRequestMarshaller().marshall(deleteAuditSuppressionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteAuditSuppressionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteAuditSuppressionResult deleteAuditSuppressionResult = (DeleteAuditSuppressionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteAuditSuppressionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteAuditSuppressionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteAuthorizerResult deleteAuthorizer(DeleteAuthorizerRequest deleteAuthorizerRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteAuthorizerRequest> requestMarshall = new DeleteAuthorizerRequestMarshaller().marshall(deleteAuthorizerRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteAuthorizerResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteAuthorizerResult deleteAuthorizerResult = (DeleteAuthorizerResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteAuthorizerResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteAuthorizerRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteBillingGroupResult deleteBillingGroup(DeleteBillingGroupRequest deleteBillingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteBillingGroupRequest> requestMarshall = new DeleteBillingGroupRequestMarshaller().marshall(deleteBillingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteBillingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteBillingGroupResult deleteBillingGroupResult = (DeleteBillingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteBillingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteBillingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteCACertificateResult deleteCACertificate(DeleteCACertificateRequest deleteCACertificateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteCACertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteCACertificateRequest> requestMarshall = new DeleteCACertificateRequestMarshaller().marshall(deleteCACertificateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteCACertificateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteCACertificateResult deleteCACertificateResult = (DeleteCACertificateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteCACertificateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteCACertificateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteCertificateRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteCertificate(DeleteCertificateRequest deleteCertificateRequest) throws Throwable {
        Request<DeleteCertificateRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeleteCertificateRequestMarshaller().marshall((DeleteCertificateRequest) deleteCertificateRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteCertificateRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteCertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteCertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteCustomMetricResult deleteCustomMetric(DeleteCustomMetricRequest deleteCustomMetricRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteCustomMetricRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteCustomMetricRequest> requestMarshall = new DeleteCustomMetricRequestMarshaller().marshall(deleteCustomMetricRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteCustomMetricResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteCustomMetricResult deleteCustomMetricResult = (DeleteCustomMetricResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteCustomMetricResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteCustomMetricRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteDimensionResult deleteDimension(DeleteDimensionRequest deleteDimensionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteDimensionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteDimensionRequest> requestMarshall = new DeleteDimensionRequestMarshaller().marshall(deleteDimensionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteDimensionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteDimensionResult deleteDimensionResult = (DeleteDimensionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteDimensionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteDimensionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteDomainConfigurationResult deleteDomainConfiguration(DeleteDomainConfigurationRequest deleteDomainConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteDomainConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteDomainConfigurationRequest> requestMarshall = new DeleteDomainConfigurationRequestMarshaller().marshall(deleteDomainConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteDomainConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteDomainConfigurationResult deleteDomainConfigurationResult = (DeleteDomainConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteDomainConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteDomainConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteDynamicThingGroupResult deleteDynamicThingGroup(DeleteDynamicThingGroupRequest deleteDynamicThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteDynamicThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteDynamicThingGroupRequest> requestMarshall = new DeleteDynamicThingGroupRequestMarshaller().marshall(deleteDynamicThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteDynamicThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteDynamicThingGroupResult deleteDynamicThingGroupResult = (DeleteDynamicThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteDynamicThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteDynamicThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteFleetMetricRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteFleetMetric(DeleteFleetMetricRequest deleteFleetMetricRequest) throws Throwable {
        Request<DeleteFleetMetricRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteFleetMetricRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeleteFleetMetricRequestMarshaller().marshall((DeleteFleetMetricRequest) deleteFleetMetricRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteFleetMetricRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteFleetMetricRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteFleetMetricRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteJobRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteJob(DeleteJobRequest deleteJobRequest) throws Throwable {
        Request<DeleteJobRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteJobRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeleteJobRequestMarshaller().marshall((DeleteJobRequest) deleteJobRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteJobRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteJobExecutionRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteJobExecution(DeleteJobExecutionRequest deleteJobExecutionRequest) throws Throwable {
        Request<DeleteJobExecutionRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteJobExecutionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeleteJobExecutionRequestMarshaller().marshall((DeleteJobExecutionRequest) deleteJobExecutionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteJobExecutionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteJobExecutionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteJobExecutionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteJobTemplateRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteJobTemplate(DeleteJobTemplateRequest deleteJobTemplateRequest) throws Throwable {
        Request<DeleteJobTemplateRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteJobTemplateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeleteJobTemplateRequestMarshaller().marshall((DeleteJobTemplateRequest) deleteJobTemplateRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteJobTemplateRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteJobTemplateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteJobTemplateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteMitigationActionResult deleteMitigationAction(DeleteMitigationActionRequest deleteMitigationActionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteMitigationActionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteMitigationActionRequest> requestMarshall = new DeleteMitigationActionRequestMarshaller().marshall(deleteMitigationActionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteMitigationActionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteMitigationActionResult deleteMitigationActionResult = (DeleteMitigationActionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteMitigationActionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteMitigationActionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteOTAUpdateResult deleteOTAUpdate(DeleteOTAUpdateRequest deleteOTAUpdateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteOTAUpdateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteOTAUpdateRequest> requestMarshall = new DeleteOTAUpdateRequestMarshaller().marshall(deleteOTAUpdateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteOTAUpdateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteOTAUpdateResult deleteOTAUpdateResult = (DeleteOTAUpdateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteOTAUpdateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteOTAUpdateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeletePolicyRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deletePolicy(DeletePolicyRequest deletePolicyRequest) throws Throwable {
        Request<DeletePolicyRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deletePolicyRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeletePolicyRequestMarshaller().marshall((DeletePolicyRequest) deletePolicyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deletePolicyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deletePolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deletePolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeletePolicyVersionRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deletePolicyVersion(DeletePolicyVersionRequest deletePolicyVersionRequest) throws Throwable {
        Request<DeletePolicyVersionRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deletePolicyVersionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeletePolicyVersionRequestMarshaller().marshall((DeletePolicyVersionRequest) deletePolicyVersionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deletePolicyVersionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deletePolicyVersionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deletePolicyVersionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteProvisioningTemplateResult deleteProvisioningTemplate(DeleteProvisioningTemplateRequest deleteProvisioningTemplateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteProvisioningTemplateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteProvisioningTemplateRequest> requestMarshall = new DeleteProvisioningTemplateRequestMarshaller().marshall(deleteProvisioningTemplateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteProvisioningTemplateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteProvisioningTemplateResult deleteProvisioningTemplateResult = (DeleteProvisioningTemplateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteProvisioningTemplateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteProvisioningTemplateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteProvisioningTemplateVersionResult deleteProvisioningTemplateVersion(DeleteProvisioningTemplateVersionRequest deleteProvisioningTemplateVersionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteProvisioningTemplateVersionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteProvisioningTemplateVersionRequest> requestMarshall = new DeleteProvisioningTemplateVersionRequestMarshaller().marshall(deleteProvisioningTemplateVersionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteProvisioningTemplateVersionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteProvisioningTemplateVersionResult deleteProvisioningTemplateVersionResult = (DeleteProvisioningTemplateVersionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteProvisioningTemplateVersionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteProvisioningTemplateVersionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteRegistrationCodeResult deleteRegistrationCode(DeleteRegistrationCodeRequest deleteRegistrationCodeRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteRegistrationCodeRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteRegistrationCodeRequest> requestMarshall = new DeleteRegistrationCodeRequestMarshaller().marshall(deleteRegistrationCodeRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteRegistrationCodeResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteRegistrationCodeResult deleteRegistrationCodeResult = (DeleteRegistrationCodeResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteRegistrationCodeResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteRegistrationCodeRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteRoleAliasResult deleteRoleAlias(DeleteRoleAliasRequest deleteRoleAliasRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteRoleAliasRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteRoleAliasRequest> requestMarshall = new DeleteRoleAliasRequestMarshaller().marshall(deleteRoleAliasRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteRoleAliasResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteRoleAliasResult deleteRoleAliasResult = (DeleteRoleAliasResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteRoleAliasResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteRoleAliasRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteScheduledAuditResult deleteScheduledAudit(DeleteScheduledAuditRequest deleteScheduledAuditRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteScheduledAuditRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteScheduledAuditRequest> requestMarshall = new DeleteScheduledAuditRequestMarshaller().marshall(deleteScheduledAuditRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteScheduledAuditResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteScheduledAuditResult deleteScheduledAuditResult = (DeleteScheduledAuditResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteScheduledAuditResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteScheduledAuditRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteSecurityProfileResult deleteSecurityProfile(DeleteSecurityProfileRequest deleteSecurityProfileRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteSecurityProfileRequest> requestMarshall = new DeleteSecurityProfileRequestMarshaller().marshall(deleteSecurityProfileRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteSecurityProfileResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteSecurityProfileResult deleteSecurityProfileResult = (DeleteSecurityProfileResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteSecurityProfileResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteSecurityProfileRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteStreamResult deleteStream(DeleteStreamRequest deleteStreamRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteStreamRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteStreamRequest> requestMarshall = new DeleteStreamRequestMarshaller().marshall(deleteStreamRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteStreamResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteStreamResult deleteStreamResult = (DeleteStreamResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteStreamResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteStreamRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteThingResult deleteThing(DeleteThingRequest deleteThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteThingRequest> requestMarshall = new DeleteThingRequestMarshaller().marshall(deleteThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteThingResult deleteThingResult = (DeleteThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteThingGroupResult deleteThingGroup(DeleteThingGroupRequest deleteThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteThingGroupRequest> requestMarshall = new DeleteThingGroupRequestMarshaller().marshall(deleteThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteThingGroupResult deleteThingGroupResult = (DeleteThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteThingTypeResult deleteThingType(DeleteThingTypeRequest deleteThingTypeRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteThingTypeRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteThingTypeRequest> requestMarshall = new DeleteThingTypeRequestMarshaller().marshall(deleteThingTypeRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteThingTypeResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteThingTypeResult deleteThingTypeResult = (DeleteThingTypeResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteThingTypeResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteThingTypeRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteTopicRule(DeleteTopicRuleRequest deleteTopicRuleRequest) throws Throwable {
        Request<DeleteTopicRuleRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeleteTopicRuleRequestMarshaller().marshall((DeleteTopicRuleRequest) deleteTopicRuleRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteTopicRuleRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteTopicRuleDestinationResult deleteTopicRuleDestination(DeleteTopicRuleDestinationRequest deleteTopicRuleDestinationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteTopicRuleDestinationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteTopicRuleDestinationRequest> requestMarshall = new DeleteTopicRuleDestinationRequestMarshaller().marshall(deleteTopicRuleDestinationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteTopicRuleDestinationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteTopicRuleDestinationResult deleteTopicRuleDestinationResult = (DeleteTopicRuleDestinationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteTopicRuleDestinationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteTopicRuleDestinationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteV2LoggingLevelRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteV2LoggingLevel(DeleteV2LoggingLevelRequest deleteV2LoggingLevelRequest) throws Throwable {
        Request<DeleteV2LoggingLevelRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteV2LoggingLevelRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DeleteV2LoggingLevelRequestMarshaller().marshall((DeleteV2LoggingLevelRequest) deleteV2LoggingLevelRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteV2LoggingLevelRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteV2LoggingLevelRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteV2LoggingLevelRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeprecateThingTypeResult deprecateThingType(DeprecateThingTypeRequest deprecateThingTypeRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deprecateThingTypeRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeprecateThingTypeRequest> requestMarshall = new DeprecateThingTypeRequestMarshaller().marshall(deprecateThingTypeRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeprecateThingTypeResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeprecateThingTypeResult deprecateThingTypeResult = (DeprecateThingTypeResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deprecateThingTypeResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deprecateThingTypeRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAccountAuditConfigurationResult describeAccountAuditConfiguration(DescribeAccountAuditConfigurationRequest describeAccountAuditConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeAccountAuditConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeAccountAuditConfigurationRequest> requestMarshall = new DescribeAccountAuditConfigurationRequestMarshaller().marshall(describeAccountAuditConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeAccountAuditConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeAccountAuditConfigurationResult describeAccountAuditConfigurationResult = (DescribeAccountAuditConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeAccountAuditConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeAccountAuditConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAuditFindingResult describeAuditFinding(DescribeAuditFindingRequest describeAuditFindingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeAuditFindingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeAuditFindingRequest> requestMarshall = new DescribeAuditFindingRequestMarshaller().marshall(describeAuditFindingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeAuditFindingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeAuditFindingResult describeAuditFindingResult = (DescribeAuditFindingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeAuditFindingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeAuditFindingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAuditMitigationActionsTaskResult describeAuditMitigationActionsTask(DescribeAuditMitigationActionsTaskRequest describeAuditMitigationActionsTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeAuditMitigationActionsTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeAuditMitigationActionsTaskRequest> requestMarshall = new DescribeAuditMitigationActionsTaskRequestMarshaller().marshall(describeAuditMitigationActionsTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeAuditMitigationActionsTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeAuditMitigationActionsTaskResult describeAuditMitigationActionsTaskResult = (DescribeAuditMitigationActionsTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeAuditMitigationActionsTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeAuditMitigationActionsTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAuditSuppressionResult describeAuditSuppression(DescribeAuditSuppressionRequest describeAuditSuppressionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeAuditSuppressionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeAuditSuppressionRequest> requestMarshall = new DescribeAuditSuppressionRequestMarshaller().marshall(describeAuditSuppressionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeAuditSuppressionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeAuditSuppressionResult describeAuditSuppressionResult = (DescribeAuditSuppressionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeAuditSuppressionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeAuditSuppressionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAuditTaskResult describeAuditTask(DescribeAuditTaskRequest describeAuditTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeAuditTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeAuditTaskRequest> requestMarshall = new DescribeAuditTaskRequestMarshaller().marshall(describeAuditTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeAuditTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeAuditTaskResult describeAuditTaskResult = (DescribeAuditTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeAuditTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeAuditTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAuthorizerResult describeAuthorizer(DescribeAuthorizerRequest describeAuthorizerRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeAuthorizerRequest> requestMarshall = new DescribeAuthorizerRequestMarshaller().marshall(describeAuthorizerRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeAuthorizerResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeAuthorizerResult describeAuthorizerResult = (DescribeAuthorizerResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeAuthorizerResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeAuthorizerRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeBillingGroupResult describeBillingGroup(DescribeBillingGroupRequest describeBillingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeBillingGroupRequest> requestMarshall = new DescribeBillingGroupRequestMarshaller().marshall(describeBillingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeBillingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeBillingGroupResult describeBillingGroupResult = (DescribeBillingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeBillingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeBillingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeCACertificateResult describeCACertificate(DescribeCACertificateRequest describeCACertificateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeCACertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeCACertificateRequest> requestMarshall = new DescribeCACertificateRequestMarshaller().marshall(describeCACertificateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeCACertificateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeCACertificateResult describeCACertificateResult = (DescribeCACertificateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeCACertificateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeCACertificateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeCertificateResult describeCertificate(DescribeCertificateRequest describeCertificateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeCertificateRequest> requestMarshall = new DescribeCertificateRequestMarshaller().marshall(describeCertificateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeCertificateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeCertificateResult describeCertificateResult = (DescribeCertificateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeCertificateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeCertificateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeCustomMetricResult describeCustomMetric(DescribeCustomMetricRequest describeCustomMetricRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeCustomMetricRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeCustomMetricRequest> requestMarshall = new DescribeCustomMetricRequestMarshaller().marshall(describeCustomMetricRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeCustomMetricResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeCustomMetricResult describeCustomMetricResult = (DescribeCustomMetricResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeCustomMetricResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeCustomMetricRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeDefaultAuthorizerResult describeDefaultAuthorizer(DescribeDefaultAuthorizerRequest describeDefaultAuthorizerRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeDefaultAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeDefaultAuthorizerRequest> requestMarshall = new DescribeDefaultAuthorizerRequestMarshaller().marshall(describeDefaultAuthorizerRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeDefaultAuthorizerResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeDefaultAuthorizerResult describeDefaultAuthorizerResult = (DescribeDefaultAuthorizerResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeDefaultAuthorizerResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeDefaultAuthorizerRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeDetectMitigationActionsTaskResult describeDetectMitigationActionsTask(DescribeDetectMitigationActionsTaskRequest describeDetectMitigationActionsTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeDetectMitigationActionsTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeDetectMitigationActionsTaskRequest> requestMarshall = new DescribeDetectMitigationActionsTaskRequestMarshaller().marshall(describeDetectMitigationActionsTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeDetectMitigationActionsTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeDetectMitigationActionsTaskResult describeDetectMitigationActionsTaskResult = (DescribeDetectMitigationActionsTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeDetectMitigationActionsTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeDetectMitigationActionsTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeDimensionResult describeDimension(DescribeDimensionRequest describeDimensionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeDimensionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeDimensionRequest> requestMarshall = new DescribeDimensionRequestMarshaller().marshall(describeDimensionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeDimensionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeDimensionResult describeDimensionResult = (DescribeDimensionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeDimensionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeDimensionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeDomainConfigurationResult describeDomainConfiguration(DescribeDomainConfigurationRequest describeDomainConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeDomainConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeDomainConfigurationRequest> requestMarshall = new DescribeDomainConfigurationRequestMarshaller().marshall(describeDomainConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeDomainConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeDomainConfigurationResult describeDomainConfigurationResult = (DescribeDomainConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeDomainConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeDomainConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeEndpointResult describeEndpoint(DescribeEndpointRequest describeEndpointRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeEndpointRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeEndpointRequest> requestMarshall = new DescribeEndpointRequestMarshaller().marshall(describeEndpointRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeEndpointResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeEndpointResult describeEndpointResult = (DescribeEndpointResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeEndpointResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeEndpointRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeEventConfigurationsResult describeEventConfigurations(DescribeEventConfigurationsRequest describeEventConfigurationsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeEventConfigurationsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeEventConfigurationsRequest> requestMarshall = new DescribeEventConfigurationsRequestMarshaller().marshall(describeEventConfigurationsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeEventConfigurationsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeEventConfigurationsResult describeEventConfigurationsResult = (DescribeEventConfigurationsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeEventConfigurationsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeEventConfigurationsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeFleetMetricResult describeFleetMetric(DescribeFleetMetricRequest describeFleetMetricRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeFleetMetricRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeFleetMetricRequest> requestMarshall = new DescribeFleetMetricRequestMarshaller().marshall(describeFleetMetricRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeFleetMetricResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeFleetMetricResult describeFleetMetricResult = (DescribeFleetMetricResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeFleetMetricResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeFleetMetricRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeIndexResult describeIndex(DescribeIndexRequest describeIndexRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeIndexRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeIndexRequest> requestMarshall = new DescribeIndexRequestMarshaller().marshall(describeIndexRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeIndexResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeIndexResult describeIndexResult = (DescribeIndexResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeIndexResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeIndexRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeJobResult describeJob(DescribeJobRequest describeJobRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeJobRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeJobRequest> requestMarshall = new DescribeJobRequestMarshaller().marshall(describeJobRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeJobResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeJobResult describeJobResult = (DescribeJobResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeJobResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeJobRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeJobExecutionResult describeJobExecution(DescribeJobExecutionRequest describeJobExecutionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeJobExecutionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeJobExecutionRequest> requestMarshall = new DescribeJobExecutionRequestMarshaller().marshall(describeJobExecutionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeJobExecutionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeJobExecutionResult describeJobExecutionResult = (DescribeJobExecutionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeJobExecutionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeJobExecutionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeJobTemplateResult describeJobTemplate(DescribeJobTemplateRequest describeJobTemplateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeJobTemplateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeJobTemplateRequest> requestMarshall = new DescribeJobTemplateRequestMarshaller().marshall(describeJobTemplateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeJobTemplateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeJobTemplateResult describeJobTemplateResult = (DescribeJobTemplateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeJobTemplateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeJobTemplateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeManagedJobTemplateResult describeManagedJobTemplate(DescribeManagedJobTemplateRequest describeManagedJobTemplateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeManagedJobTemplateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeManagedJobTemplateRequest> requestMarshall = new DescribeManagedJobTemplateRequestMarshaller().marshall(describeManagedJobTemplateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeManagedJobTemplateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeManagedJobTemplateResult describeManagedJobTemplateResult = (DescribeManagedJobTemplateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeManagedJobTemplateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeManagedJobTemplateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeMitigationActionResult describeMitigationAction(DescribeMitigationActionRequest describeMitigationActionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeMitigationActionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeMitigationActionRequest> requestMarshall = new DescribeMitigationActionRequestMarshaller().marshall(describeMitigationActionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeMitigationActionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeMitigationActionResult describeMitigationActionResult = (DescribeMitigationActionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeMitigationActionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeMitigationActionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeProvisioningTemplateResult describeProvisioningTemplate(DescribeProvisioningTemplateRequest describeProvisioningTemplateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeProvisioningTemplateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeProvisioningTemplateRequest> requestMarshall = new DescribeProvisioningTemplateRequestMarshaller().marshall(describeProvisioningTemplateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeProvisioningTemplateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeProvisioningTemplateResult describeProvisioningTemplateResult = (DescribeProvisioningTemplateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeProvisioningTemplateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeProvisioningTemplateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeProvisioningTemplateVersionResult describeProvisioningTemplateVersion(DescribeProvisioningTemplateVersionRequest describeProvisioningTemplateVersionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeProvisioningTemplateVersionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeProvisioningTemplateVersionRequest> requestMarshall = new DescribeProvisioningTemplateVersionRequestMarshaller().marshall(describeProvisioningTemplateVersionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeProvisioningTemplateVersionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeProvisioningTemplateVersionResult describeProvisioningTemplateVersionResult = (DescribeProvisioningTemplateVersionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeProvisioningTemplateVersionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeProvisioningTemplateVersionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeRoleAliasResult describeRoleAlias(DescribeRoleAliasRequest describeRoleAliasRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeRoleAliasRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeRoleAliasRequest> requestMarshall = new DescribeRoleAliasRequestMarshaller().marshall(describeRoleAliasRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeRoleAliasResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeRoleAliasResult describeRoleAliasResult = (DescribeRoleAliasResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeRoleAliasResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeRoleAliasRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeScheduledAuditResult describeScheduledAudit(DescribeScheduledAuditRequest describeScheduledAuditRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeScheduledAuditRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeScheduledAuditRequest> requestMarshall = new DescribeScheduledAuditRequestMarshaller().marshall(describeScheduledAuditRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeScheduledAuditResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeScheduledAuditResult describeScheduledAuditResult = (DescribeScheduledAuditResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeScheduledAuditResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeScheduledAuditRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeSecurityProfileResult describeSecurityProfile(DescribeSecurityProfileRequest describeSecurityProfileRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeSecurityProfileRequest> requestMarshall = new DescribeSecurityProfileRequestMarshaller().marshall(describeSecurityProfileRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeSecurityProfileResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeSecurityProfileResult describeSecurityProfileResult = (DescribeSecurityProfileResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeSecurityProfileResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeSecurityProfileRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeStreamResult describeStream(DescribeStreamRequest describeStreamRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeStreamRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeStreamRequest> requestMarshall = new DescribeStreamRequestMarshaller().marshall(describeStreamRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeStreamResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeStreamResult describeStreamResult = (DescribeStreamResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeStreamResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeStreamRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeThingResult describeThing(DescribeThingRequest describeThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeThingRequest> requestMarshall = new DescribeThingRequestMarshaller().marshall(describeThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeThingResult describeThingResult = (DescribeThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeThingGroupResult describeThingGroup(DescribeThingGroupRequest describeThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeThingGroupRequest> requestMarshall = new DescribeThingGroupRequestMarshaller().marshall(describeThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeThingGroupResult describeThingGroupResult = (DescribeThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeThingRegistrationTaskResult describeThingRegistrationTask(DescribeThingRegistrationTaskRequest describeThingRegistrationTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeThingRegistrationTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeThingRegistrationTaskRequest> requestMarshall = new DescribeThingRegistrationTaskRequestMarshaller().marshall(describeThingRegistrationTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeThingRegistrationTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeThingRegistrationTaskResult describeThingRegistrationTaskResult = (DescribeThingRegistrationTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeThingRegistrationTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeThingRegistrationTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeThingTypeResult describeThingType(DescribeThingTypeRequest describeThingTypeRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(describeThingTypeRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DescribeThingTypeRequest> requestMarshall = new DescribeThingTypeRequestMarshaller().marshall(describeThingTypeRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DescribeThingTypeResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DescribeThingTypeResult describeThingTypeResult = (DescribeThingTypeResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return describeThingTypeResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = describeThingTypeRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DetachPolicyRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void detachPolicy(DetachPolicyRequest detachPolicyRequest) throws Throwable {
        Request<DetachPolicyRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(detachPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DetachPolicyRequestMarshaller().marshall((DetachPolicyRequest) detachPolicyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                detachPolicyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, detachPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, detachPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DetachPrincipalPolicyRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public void detachPrincipalPolicy(DetachPrincipalPolicyRequest detachPrincipalPolicyRequest) throws Throwable {
        Request<DetachPrincipalPolicyRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(detachPrincipalPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DetachPrincipalPolicyRequestMarshaller().marshall((DetachPrincipalPolicyRequest) detachPrincipalPolicyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                detachPrincipalPolicyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, detachPrincipalPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, detachPrincipalPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DetachSecurityProfileResult detachSecurityProfile(DetachSecurityProfileRequest detachSecurityProfileRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(detachSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DetachSecurityProfileRequest> requestMarshall = new DetachSecurityProfileRequestMarshaller().marshall(detachSecurityProfileRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DetachSecurityProfileResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DetachSecurityProfileResult detachSecurityProfileResult = (DetachSecurityProfileResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return detachSecurityProfileResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = detachSecurityProfileRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DetachThingPrincipalResult detachThingPrincipal(DetachThingPrincipalRequest detachThingPrincipalRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(detachThingPrincipalRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DetachThingPrincipalRequest> requestMarshall = new DetachThingPrincipalRequestMarshaller().marshall(detachThingPrincipalRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DetachThingPrincipalResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DetachThingPrincipalResult detachThingPrincipalResult = (DetachThingPrincipalResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return detachThingPrincipalResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = detachThingPrincipalRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DisableTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void disableTopicRule(DisableTopicRuleRequest disableTopicRuleRequest) throws Throwable {
        Request<DisableTopicRuleRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(disableTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new DisableTopicRuleRequestMarshaller().marshall((DisableTopicRuleRequest) disableTopicRuleRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                disableTopicRuleRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, disableTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, disableTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.EnableTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void enableTopicRule(EnableTopicRuleRequest enableTopicRuleRequest) throws Throwable {
        Request<EnableTopicRuleRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(enableTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new EnableTopicRuleRequestMarshaller().marshall((EnableTopicRuleRequest) enableTopicRuleRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                enableTopicRuleRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, enableTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, enableTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetBehaviorModelTrainingSummariesResult getBehaviorModelTrainingSummaries(GetBehaviorModelTrainingSummariesRequest getBehaviorModelTrainingSummariesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getBehaviorModelTrainingSummariesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetBehaviorModelTrainingSummariesRequest> requestMarshall = new GetBehaviorModelTrainingSummariesRequestMarshaller().marshall(getBehaviorModelTrainingSummariesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetBehaviorModelTrainingSummariesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetBehaviorModelTrainingSummariesResult getBehaviorModelTrainingSummariesResult = (GetBehaviorModelTrainingSummariesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getBehaviorModelTrainingSummariesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getBehaviorModelTrainingSummariesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetBucketsAggregationResult getBucketsAggregation(GetBucketsAggregationRequest getBucketsAggregationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getBucketsAggregationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetBucketsAggregationRequest> requestMarshall = new GetBucketsAggregationRequestMarshaller().marshall(getBucketsAggregationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetBucketsAggregationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetBucketsAggregationResult getBucketsAggregationResult = (GetBucketsAggregationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getBucketsAggregationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getBucketsAggregationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetCardinalityResult getCardinality(GetCardinalityRequest getCardinalityRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getCardinalityRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetCardinalityRequest> requestMarshall = new GetCardinalityRequestMarshaller().marshall(getCardinalityRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetCardinalityResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetCardinalityResult getCardinalityResult = (GetCardinalityResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getCardinalityResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getCardinalityRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetEffectivePoliciesResult getEffectivePolicies(GetEffectivePoliciesRequest getEffectivePoliciesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getEffectivePoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetEffectivePoliciesRequest> requestMarshall = new GetEffectivePoliciesRequestMarshaller().marshall(getEffectivePoliciesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetEffectivePoliciesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetEffectivePoliciesResult getEffectivePoliciesResult = (GetEffectivePoliciesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getEffectivePoliciesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getEffectivePoliciesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetIndexingConfigurationResult getIndexingConfiguration(GetIndexingConfigurationRequest getIndexingConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getIndexingConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetIndexingConfigurationRequest> requestMarshall = new GetIndexingConfigurationRequestMarshaller().marshall(getIndexingConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetIndexingConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetIndexingConfigurationResult getIndexingConfigurationResult = (GetIndexingConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getIndexingConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getIndexingConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetJobDocumentResult getJobDocument(GetJobDocumentRequest getJobDocumentRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getJobDocumentRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetJobDocumentRequest> requestMarshall = new GetJobDocumentRequestMarshaller().marshall(getJobDocumentRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetJobDocumentResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetJobDocumentResult getJobDocumentResult = (GetJobDocumentResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getJobDocumentResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getJobDocumentRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetLoggingOptionsResult getLoggingOptions(GetLoggingOptionsRequest getLoggingOptionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getLoggingOptionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetLoggingOptionsRequest> requestMarshall = new GetLoggingOptionsRequestMarshaller().marshall(getLoggingOptionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetLoggingOptionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetLoggingOptionsResult getLoggingOptionsResult = (GetLoggingOptionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getLoggingOptionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getLoggingOptionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetOTAUpdateResult getOTAUpdate(GetOTAUpdateRequest getOTAUpdateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getOTAUpdateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetOTAUpdateRequest> requestMarshall = new GetOTAUpdateRequestMarshaller().marshall(getOTAUpdateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetOTAUpdateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetOTAUpdateResult getOTAUpdateResult = (GetOTAUpdateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getOTAUpdateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getOTAUpdateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetPercentilesResult getPercentiles(GetPercentilesRequest getPercentilesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getPercentilesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetPercentilesRequest> requestMarshall = new GetPercentilesRequestMarshaller().marshall(getPercentilesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetPercentilesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetPercentilesResult getPercentilesResult = (GetPercentilesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getPercentilesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getPercentilesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetPolicyResult getPolicy(GetPolicyRequest getPolicyRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetPolicyRequest> requestMarshall = new GetPolicyRequestMarshaller().marshall(getPolicyRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetPolicyResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetPolicyResult getPolicyResult = (GetPolicyResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getPolicyResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getPolicyRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetPolicyVersionResult getPolicyVersion(GetPolicyVersionRequest getPolicyVersionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getPolicyVersionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetPolicyVersionRequest> requestMarshall = new GetPolicyVersionRequestMarshaller().marshall(getPolicyVersionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetPolicyVersionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetPolicyVersionResult getPolicyVersionResult = (GetPolicyVersionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getPolicyVersionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getPolicyVersionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetRegistrationCodeResult getRegistrationCode(GetRegistrationCodeRequest getRegistrationCodeRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getRegistrationCodeRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetRegistrationCodeRequest> requestMarshall = new GetRegistrationCodeRequestMarshaller().marshall(getRegistrationCodeRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetRegistrationCodeResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetRegistrationCodeResult getRegistrationCodeResult = (GetRegistrationCodeResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getRegistrationCodeResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getRegistrationCodeRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetStatisticsResult getStatistics(GetStatisticsRequest getStatisticsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getStatisticsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetStatisticsRequest> requestMarshall = new GetStatisticsRequestMarshaller().marshall(getStatisticsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetStatisticsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetStatisticsResult getStatisticsResult = (GetStatisticsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getStatisticsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getStatisticsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetTopicRuleResult getTopicRule(GetTopicRuleRequest getTopicRuleRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetTopicRuleRequest> requestMarshall = new GetTopicRuleRequestMarshaller().marshall(getTopicRuleRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetTopicRuleResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetTopicRuleResult getTopicRuleResult = (GetTopicRuleResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getTopicRuleResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getTopicRuleRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetTopicRuleDestinationResult getTopicRuleDestination(GetTopicRuleDestinationRequest getTopicRuleDestinationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getTopicRuleDestinationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetTopicRuleDestinationRequest> requestMarshall = new GetTopicRuleDestinationRequestMarshaller().marshall(getTopicRuleDestinationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetTopicRuleDestinationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetTopicRuleDestinationResult getTopicRuleDestinationResult = (GetTopicRuleDestinationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getTopicRuleDestinationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getTopicRuleDestinationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetV2LoggingOptionsResult getV2LoggingOptions(GetV2LoggingOptionsRequest getV2LoggingOptionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getV2LoggingOptionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetV2LoggingOptionsRequest> requestMarshall = new GetV2LoggingOptionsRequestMarshaller().marshall(getV2LoggingOptionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetV2LoggingOptionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetV2LoggingOptionsResult getV2LoggingOptionsResult = (GetV2LoggingOptionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getV2LoggingOptionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getV2LoggingOptionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListActiveViolationsResult listActiveViolations(ListActiveViolationsRequest listActiveViolationsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listActiveViolationsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListActiveViolationsRequest> requestMarshall = new ListActiveViolationsRequestMarshaller().marshall(listActiveViolationsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListActiveViolationsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListActiveViolationsResult listActiveViolationsResult = (ListActiveViolationsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listActiveViolationsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listActiveViolationsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAttachedPoliciesResult listAttachedPolicies(ListAttachedPoliciesRequest listAttachedPoliciesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listAttachedPoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListAttachedPoliciesRequest> requestMarshall = new ListAttachedPoliciesRequestMarshaller().marshall(listAttachedPoliciesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListAttachedPoliciesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListAttachedPoliciesResult listAttachedPoliciesResult = (ListAttachedPoliciesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listAttachedPoliciesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listAttachedPoliciesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuditFindingsResult listAuditFindings(ListAuditFindingsRequest listAuditFindingsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listAuditFindingsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListAuditFindingsRequest> requestMarshall = new ListAuditFindingsRequestMarshaller().marshall(listAuditFindingsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListAuditFindingsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListAuditFindingsResult listAuditFindingsResult = (ListAuditFindingsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listAuditFindingsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listAuditFindingsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuditMitigationActionsExecutionsResult listAuditMitigationActionsExecutions(ListAuditMitigationActionsExecutionsRequest listAuditMitigationActionsExecutionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listAuditMitigationActionsExecutionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListAuditMitigationActionsExecutionsRequest> requestMarshall = new ListAuditMitigationActionsExecutionsRequestMarshaller().marshall(listAuditMitigationActionsExecutionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListAuditMitigationActionsExecutionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListAuditMitigationActionsExecutionsResult listAuditMitigationActionsExecutionsResult = (ListAuditMitigationActionsExecutionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listAuditMitigationActionsExecutionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listAuditMitigationActionsExecutionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuditMitigationActionsTasksResult listAuditMitigationActionsTasks(ListAuditMitigationActionsTasksRequest listAuditMitigationActionsTasksRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listAuditMitigationActionsTasksRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListAuditMitigationActionsTasksRequest> requestMarshall = new ListAuditMitigationActionsTasksRequestMarshaller().marshall(listAuditMitigationActionsTasksRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListAuditMitigationActionsTasksResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListAuditMitigationActionsTasksResult listAuditMitigationActionsTasksResult = (ListAuditMitigationActionsTasksResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listAuditMitigationActionsTasksResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listAuditMitigationActionsTasksRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuditSuppressionsResult listAuditSuppressions(ListAuditSuppressionsRequest listAuditSuppressionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listAuditSuppressionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListAuditSuppressionsRequest> requestMarshall = new ListAuditSuppressionsRequestMarshaller().marshall(listAuditSuppressionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListAuditSuppressionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListAuditSuppressionsResult listAuditSuppressionsResult = (ListAuditSuppressionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listAuditSuppressionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listAuditSuppressionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuditTasksResult listAuditTasks(ListAuditTasksRequest listAuditTasksRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listAuditTasksRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListAuditTasksRequest> requestMarshall = new ListAuditTasksRequestMarshaller().marshall(listAuditTasksRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListAuditTasksResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListAuditTasksResult listAuditTasksResult = (ListAuditTasksResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listAuditTasksResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listAuditTasksRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuthorizersResult listAuthorizers(ListAuthorizersRequest listAuthorizersRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listAuthorizersRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListAuthorizersRequest> requestMarshall = new ListAuthorizersRequestMarshaller().marshall(listAuthorizersRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListAuthorizersResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListAuthorizersResult listAuthorizersResult = (ListAuthorizersResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listAuthorizersResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listAuthorizersRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListBillingGroupsResult listBillingGroups(ListBillingGroupsRequest listBillingGroupsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listBillingGroupsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListBillingGroupsRequest> requestMarshall = new ListBillingGroupsRequestMarshaller().marshall(listBillingGroupsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListBillingGroupsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListBillingGroupsResult listBillingGroupsResult = (ListBillingGroupsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listBillingGroupsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listBillingGroupsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListCACertificatesResult listCACertificates(ListCACertificatesRequest listCACertificatesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listCACertificatesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListCACertificatesRequest> requestMarshall = new ListCACertificatesRequestMarshaller().marshall(listCACertificatesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListCACertificatesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListCACertificatesResult listCACertificatesResult = (ListCACertificatesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listCACertificatesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listCACertificatesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListCertificatesResult listCertificates(ListCertificatesRequest listCertificatesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listCertificatesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListCertificatesRequest> requestMarshall = new ListCertificatesRequestMarshaller().marshall(listCertificatesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListCertificatesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListCertificatesResult listCertificatesResult = (ListCertificatesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listCertificatesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listCertificatesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListCertificatesByCAResult listCertificatesByCA(ListCertificatesByCARequest listCertificatesByCARequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listCertificatesByCARequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListCertificatesByCARequest> requestMarshall = new ListCertificatesByCARequestMarshaller().marshall(listCertificatesByCARequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListCertificatesByCAResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListCertificatesByCAResult listCertificatesByCAResult = (ListCertificatesByCAResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listCertificatesByCAResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listCertificatesByCARequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListCustomMetricsResult listCustomMetrics(ListCustomMetricsRequest listCustomMetricsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listCustomMetricsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListCustomMetricsRequest> requestMarshall = new ListCustomMetricsRequestMarshaller().marshall(listCustomMetricsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListCustomMetricsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListCustomMetricsResult listCustomMetricsResult = (ListCustomMetricsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listCustomMetricsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listCustomMetricsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListDetectMitigationActionsExecutionsResult listDetectMitigationActionsExecutions(ListDetectMitigationActionsExecutionsRequest listDetectMitigationActionsExecutionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listDetectMitigationActionsExecutionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListDetectMitigationActionsExecutionsRequest> requestMarshall = new ListDetectMitigationActionsExecutionsRequestMarshaller().marshall(listDetectMitigationActionsExecutionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListDetectMitigationActionsExecutionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListDetectMitigationActionsExecutionsResult listDetectMitigationActionsExecutionsResult = (ListDetectMitigationActionsExecutionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listDetectMitigationActionsExecutionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listDetectMitigationActionsExecutionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListDetectMitigationActionsTasksResult listDetectMitigationActionsTasks(ListDetectMitigationActionsTasksRequest listDetectMitigationActionsTasksRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listDetectMitigationActionsTasksRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListDetectMitigationActionsTasksRequest> requestMarshall = new ListDetectMitigationActionsTasksRequestMarshaller().marshall(listDetectMitigationActionsTasksRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListDetectMitigationActionsTasksResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListDetectMitigationActionsTasksResult listDetectMitigationActionsTasksResult = (ListDetectMitigationActionsTasksResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listDetectMitigationActionsTasksResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listDetectMitigationActionsTasksRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListDimensionsResult listDimensions(ListDimensionsRequest listDimensionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listDimensionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListDimensionsRequest> requestMarshall = new ListDimensionsRequestMarshaller().marshall(listDimensionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListDimensionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListDimensionsResult listDimensionsResult = (ListDimensionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listDimensionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listDimensionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListDomainConfigurationsResult listDomainConfigurations(ListDomainConfigurationsRequest listDomainConfigurationsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listDomainConfigurationsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListDomainConfigurationsRequest> requestMarshall = new ListDomainConfigurationsRequestMarshaller().marshall(listDomainConfigurationsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListDomainConfigurationsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListDomainConfigurationsResult listDomainConfigurationsResult = (ListDomainConfigurationsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listDomainConfigurationsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listDomainConfigurationsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListFleetMetricsResult listFleetMetrics(ListFleetMetricsRequest listFleetMetricsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listFleetMetricsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListFleetMetricsRequest> requestMarshall = new ListFleetMetricsRequestMarshaller().marshall(listFleetMetricsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListFleetMetricsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListFleetMetricsResult listFleetMetricsResult = (ListFleetMetricsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listFleetMetricsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listFleetMetricsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListIndicesResult listIndices(ListIndicesRequest listIndicesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listIndicesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListIndicesRequest> requestMarshall = new ListIndicesRequestMarshaller().marshall(listIndicesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListIndicesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListIndicesResult listIndicesResult = (ListIndicesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listIndicesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listIndicesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListJobExecutionsForJobResult listJobExecutionsForJob(ListJobExecutionsForJobRequest listJobExecutionsForJobRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listJobExecutionsForJobRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListJobExecutionsForJobRequest> requestMarshall = new ListJobExecutionsForJobRequestMarshaller().marshall(listJobExecutionsForJobRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListJobExecutionsForJobResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListJobExecutionsForJobResult listJobExecutionsForJobResult = (ListJobExecutionsForJobResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listJobExecutionsForJobResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listJobExecutionsForJobRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListJobExecutionsForThingResult listJobExecutionsForThing(ListJobExecutionsForThingRequest listJobExecutionsForThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listJobExecutionsForThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListJobExecutionsForThingRequest> requestMarshall = new ListJobExecutionsForThingRequestMarshaller().marshall(listJobExecutionsForThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListJobExecutionsForThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListJobExecutionsForThingResult listJobExecutionsForThingResult = (ListJobExecutionsForThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listJobExecutionsForThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listJobExecutionsForThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListJobTemplatesResult listJobTemplates(ListJobTemplatesRequest listJobTemplatesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listJobTemplatesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListJobTemplatesRequest> requestMarshall = new ListJobTemplatesRequestMarshaller().marshall(listJobTemplatesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListJobTemplatesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListJobTemplatesResult listJobTemplatesResult = (ListJobTemplatesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listJobTemplatesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listJobTemplatesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListJobsResult listJobs(ListJobsRequest listJobsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listJobsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListJobsRequest> requestMarshall = new ListJobsRequestMarshaller().marshall(listJobsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListJobsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListJobsResult listJobsResult = (ListJobsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listJobsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listJobsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListManagedJobTemplatesResult listManagedJobTemplates(ListManagedJobTemplatesRequest listManagedJobTemplatesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listManagedJobTemplatesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListManagedJobTemplatesRequest> requestMarshall = new ListManagedJobTemplatesRequestMarshaller().marshall(listManagedJobTemplatesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListManagedJobTemplatesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListManagedJobTemplatesResult listManagedJobTemplatesResult = (ListManagedJobTemplatesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listManagedJobTemplatesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listManagedJobTemplatesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListMetricValuesResult listMetricValues(ListMetricValuesRequest listMetricValuesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listMetricValuesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListMetricValuesRequest> requestMarshall = new ListMetricValuesRequestMarshaller().marshall(listMetricValuesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListMetricValuesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListMetricValuesResult listMetricValuesResult = (ListMetricValuesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listMetricValuesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listMetricValuesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListMitigationActionsResult listMitigationActions(ListMitigationActionsRequest listMitigationActionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listMitigationActionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListMitigationActionsRequest> requestMarshall = new ListMitigationActionsRequestMarshaller().marshall(listMitigationActionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListMitigationActionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListMitigationActionsResult listMitigationActionsResult = (ListMitigationActionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listMitigationActionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listMitigationActionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListOTAUpdatesResult listOTAUpdates(ListOTAUpdatesRequest listOTAUpdatesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listOTAUpdatesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListOTAUpdatesRequest> requestMarshall = new ListOTAUpdatesRequestMarshaller().marshall(listOTAUpdatesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListOTAUpdatesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListOTAUpdatesResult listOTAUpdatesResult = (ListOTAUpdatesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listOTAUpdatesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listOTAUpdatesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListOutgoingCertificatesResult listOutgoingCertificates(ListOutgoingCertificatesRequest listOutgoingCertificatesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listOutgoingCertificatesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListOutgoingCertificatesRequest> requestMarshall = new ListOutgoingCertificatesRequestMarshaller().marshall(listOutgoingCertificatesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListOutgoingCertificatesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListOutgoingCertificatesResult listOutgoingCertificatesResult = (ListOutgoingCertificatesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listOutgoingCertificatesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listOutgoingCertificatesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListPoliciesResult listPolicies(ListPoliciesRequest listPoliciesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listPoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListPoliciesRequest> requestMarshall = new ListPoliciesRequestMarshaller().marshall(listPoliciesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListPoliciesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListPoliciesResult listPoliciesResult = (ListPoliciesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listPoliciesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listPoliciesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public ListPolicyPrincipalsResult listPolicyPrincipals(ListPolicyPrincipalsRequest listPolicyPrincipalsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listPolicyPrincipalsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListPolicyPrincipalsRequest> requestMarshall = new ListPolicyPrincipalsRequestMarshaller().marshall(listPolicyPrincipalsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListPolicyPrincipalsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListPolicyPrincipalsResult listPolicyPrincipalsResult = (ListPolicyPrincipalsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listPolicyPrincipalsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listPolicyPrincipalsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListPolicyVersionsResult listPolicyVersions(ListPolicyVersionsRequest listPolicyVersionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listPolicyVersionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListPolicyVersionsRequest> requestMarshall = new ListPolicyVersionsRequestMarshaller().marshall(listPolicyVersionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListPolicyVersionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListPolicyVersionsResult listPolicyVersionsResult = (ListPolicyVersionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listPolicyVersionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listPolicyVersionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public ListPrincipalPoliciesResult listPrincipalPolicies(ListPrincipalPoliciesRequest listPrincipalPoliciesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listPrincipalPoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListPrincipalPoliciesRequest> requestMarshall = new ListPrincipalPoliciesRequestMarshaller().marshall(listPrincipalPoliciesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListPrincipalPoliciesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListPrincipalPoliciesResult listPrincipalPoliciesResult = (ListPrincipalPoliciesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listPrincipalPoliciesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listPrincipalPoliciesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListPrincipalThingsResult listPrincipalThings(ListPrincipalThingsRequest listPrincipalThingsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listPrincipalThingsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListPrincipalThingsRequest> requestMarshall = new ListPrincipalThingsRequestMarshaller().marshall(listPrincipalThingsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListPrincipalThingsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListPrincipalThingsResult listPrincipalThingsResult = (ListPrincipalThingsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listPrincipalThingsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listPrincipalThingsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListProvisioningTemplateVersionsResult listProvisioningTemplateVersions(ListProvisioningTemplateVersionsRequest listProvisioningTemplateVersionsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listProvisioningTemplateVersionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListProvisioningTemplateVersionsRequest> requestMarshall = new ListProvisioningTemplateVersionsRequestMarshaller().marshall(listProvisioningTemplateVersionsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListProvisioningTemplateVersionsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListProvisioningTemplateVersionsResult listProvisioningTemplateVersionsResult = (ListProvisioningTemplateVersionsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listProvisioningTemplateVersionsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listProvisioningTemplateVersionsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListProvisioningTemplatesResult listProvisioningTemplates(ListProvisioningTemplatesRequest listProvisioningTemplatesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listProvisioningTemplatesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListProvisioningTemplatesRequest> requestMarshall = new ListProvisioningTemplatesRequestMarshaller().marshall(listProvisioningTemplatesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListProvisioningTemplatesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListProvisioningTemplatesResult listProvisioningTemplatesResult = (ListProvisioningTemplatesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listProvisioningTemplatesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listProvisioningTemplatesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListRelatedResourcesForAuditFindingResult listRelatedResourcesForAuditFinding(ListRelatedResourcesForAuditFindingRequest listRelatedResourcesForAuditFindingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listRelatedResourcesForAuditFindingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListRelatedResourcesForAuditFindingRequest> requestMarshall = new ListRelatedResourcesForAuditFindingRequestMarshaller().marshall(listRelatedResourcesForAuditFindingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListRelatedResourcesForAuditFindingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListRelatedResourcesForAuditFindingResult listRelatedResourcesForAuditFindingResult = (ListRelatedResourcesForAuditFindingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listRelatedResourcesForAuditFindingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listRelatedResourcesForAuditFindingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListRoleAliasesResult listRoleAliases(ListRoleAliasesRequest listRoleAliasesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listRoleAliasesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListRoleAliasesRequest> requestMarshall = new ListRoleAliasesRequestMarshaller().marshall(listRoleAliasesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListRoleAliasesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListRoleAliasesResult listRoleAliasesResult = (ListRoleAliasesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listRoleAliasesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listRoleAliasesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListScheduledAuditsResult listScheduledAudits(ListScheduledAuditsRequest listScheduledAuditsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listScheduledAuditsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListScheduledAuditsRequest> requestMarshall = new ListScheduledAuditsRequestMarshaller().marshall(listScheduledAuditsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListScheduledAuditsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListScheduledAuditsResult listScheduledAuditsResult = (ListScheduledAuditsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listScheduledAuditsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listScheduledAuditsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListSecurityProfilesResult listSecurityProfiles(ListSecurityProfilesRequest listSecurityProfilesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listSecurityProfilesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListSecurityProfilesRequest> requestMarshall = new ListSecurityProfilesRequestMarshaller().marshall(listSecurityProfilesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListSecurityProfilesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListSecurityProfilesResult listSecurityProfilesResult = (ListSecurityProfilesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listSecurityProfilesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listSecurityProfilesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListSecurityProfilesForTargetResult listSecurityProfilesForTarget(ListSecurityProfilesForTargetRequest listSecurityProfilesForTargetRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listSecurityProfilesForTargetRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListSecurityProfilesForTargetRequest> requestMarshall = new ListSecurityProfilesForTargetRequestMarshaller().marshall(listSecurityProfilesForTargetRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListSecurityProfilesForTargetResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListSecurityProfilesForTargetResult listSecurityProfilesForTargetResult = (ListSecurityProfilesForTargetResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listSecurityProfilesForTargetResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listSecurityProfilesForTargetRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListStreamsResult listStreams(ListStreamsRequest listStreamsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listStreamsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListStreamsRequest> requestMarshall = new ListStreamsRequestMarshaller().marshall(listStreamsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListStreamsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListStreamsResult listStreamsResult = (ListStreamsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listStreamsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listStreamsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTagsForResourceResult listTagsForResource(ListTagsForResourceRequest listTagsForResourceRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listTagsForResourceRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListTagsForResourceRequest> requestMarshall = new ListTagsForResourceRequestMarshaller().marshall(listTagsForResourceRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListTagsForResourceResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListTagsForResourceResult listTagsForResourceResult = (ListTagsForResourceResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listTagsForResourceResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listTagsForResourceRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTargetsForPolicyResult listTargetsForPolicy(ListTargetsForPolicyRequest listTargetsForPolicyRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listTargetsForPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListTargetsForPolicyRequest> requestMarshall = new ListTargetsForPolicyRequestMarshaller().marshall(listTargetsForPolicyRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListTargetsForPolicyResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListTargetsForPolicyResult listTargetsForPolicyResult = (ListTargetsForPolicyResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listTargetsForPolicyResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listTargetsForPolicyRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTargetsForSecurityProfileResult listTargetsForSecurityProfile(ListTargetsForSecurityProfileRequest listTargetsForSecurityProfileRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listTargetsForSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListTargetsForSecurityProfileRequest> requestMarshall = new ListTargetsForSecurityProfileRequestMarshaller().marshall(listTargetsForSecurityProfileRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListTargetsForSecurityProfileResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListTargetsForSecurityProfileResult listTargetsForSecurityProfileResult = (ListTargetsForSecurityProfileResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listTargetsForSecurityProfileResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listTargetsForSecurityProfileRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingGroupsResult listThingGroups(ListThingGroupsRequest listThingGroupsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingGroupsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingGroupsRequest> requestMarshall = new ListThingGroupsRequestMarshaller().marshall(listThingGroupsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingGroupsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingGroupsResult listThingGroupsResult = (ListThingGroupsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingGroupsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingGroupsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingGroupsForThingResult listThingGroupsForThing(ListThingGroupsForThingRequest listThingGroupsForThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingGroupsForThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingGroupsForThingRequest> requestMarshall = new ListThingGroupsForThingRequestMarshaller().marshall(listThingGroupsForThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingGroupsForThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingGroupsForThingResult listThingGroupsForThingResult = (ListThingGroupsForThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingGroupsForThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingGroupsForThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingPrincipalsResult listThingPrincipals(ListThingPrincipalsRequest listThingPrincipalsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingPrincipalsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingPrincipalsRequest> requestMarshall = new ListThingPrincipalsRequestMarshaller().marshall(listThingPrincipalsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingPrincipalsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingPrincipalsResult listThingPrincipalsResult = (ListThingPrincipalsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingPrincipalsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingPrincipalsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingRegistrationTaskReportsResult listThingRegistrationTaskReports(ListThingRegistrationTaskReportsRequest listThingRegistrationTaskReportsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingRegistrationTaskReportsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingRegistrationTaskReportsRequest> requestMarshall = new ListThingRegistrationTaskReportsRequestMarshaller().marshall(listThingRegistrationTaskReportsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingRegistrationTaskReportsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingRegistrationTaskReportsResult listThingRegistrationTaskReportsResult = (ListThingRegistrationTaskReportsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingRegistrationTaskReportsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingRegistrationTaskReportsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingRegistrationTasksResult listThingRegistrationTasks(ListThingRegistrationTasksRequest listThingRegistrationTasksRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingRegistrationTasksRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingRegistrationTasksRequest> requestMarshall = new ListThingRegistrationTasksRequestMarshaller().marshall(listThingRegistrationTasksRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingRegistrationTasksResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingRegistrationTasksResult listThingRegistrationTasksResult = (ListThingRegistrationTasksResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingRegistrationTasksResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingRegistrationTasksRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingTypesResult listThingTypes(ListThingTypesRequest listThingTypesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingTypesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingTypesRequest> requestMarshall = new ListThingTypesRequestMarshaller().marshall(listThingTypesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingTypesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingTypesResult listThingTypesResult = (ListThingTypesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingTypesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingTypesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingsResult listThings(ListThingsRequest listThingsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingsRequest> requestMarshall = new ListThingsRequestMarshaller().marshall(listThingsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingsResult listThingsResult = (ListThingsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingsInBillingGroupResult listThingsInBillingGroup(ListThingsInBillingGroupRequest listThingsInBillingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingsInBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingsInBillingGroupRequest> requestMarshall = new ListThingsInBillingGroupRequestMarshaller().marshall(listThingsInBillingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingsInBillingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingsInBillingGroupResult listThingsInBillingGroupResult = (ListThingsInBillingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingsInBillingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingsInBillingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingsInThingGroupResult listThingsInThingGroup(ListThingsInThingGroupRequest listThingsInThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listThingsInThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListThingsInThingGroupRequest> requestMarshall = new ListThingsInThingGroupRequestMarshaller().marshall(listThingsInThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListThingsInThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListThingsInThingGroupResult listThingsInThingGroupResult = (ListThingsInThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listThingsInThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listThingsInThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTopicRuleDestinationsResult listTopicRuleDestinations(ListTopicRuleDestinationsRequest listTopicRuleDestinationsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listTopicRuleDestinationsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListTopicRuleDestinationsRequest> requestMarshall = new ListTopicRuleDestinationsRequestMarshaller().marshall(listTopicRuleDestinationsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListTopicRuleDestinationsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListTopicRuleDestinationsResult listTopicRuleDestinationsResult = (ListTopicRuleDestinationsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listTopicRuleDestinationsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listTopicRuleDestinationsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTopicRulesResult listTopicRules(ListTopicRulesRequest listTopicRulesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listTopicRulesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListTopicRulesRequest> requestMarshall = new ListTopicRulesRequestMarshaller().marshall(listTopicRulesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListTopicRulesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListTopicRulesResult listTopicRulesResult = (ListTopicRulesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listTopicRulesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listTopicRulesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListV2LoggingLevelsResult listV2LoggingLevels(ListV2LoggingLevelsRequest listV2LoggingLevelsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listV2LoggingLevelsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListV2LoggingLevelsRequest> requestMarshall = new ListV2LoggingLevelsRequestMarshaller().marshall(listV2LoggingLevelsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListV2LoggingLevelsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListV2LoggingLevelsResult listV2LoggingLevelsResult = (ListV2LoggingLevelsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listV2LoggingLevelsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listV2LoggingLevelsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListViolationEventsResult listViolationEvents(ListViolationEventsRequest listViolationEventsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listViolationEventsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListViolationEventsRequest> requestMarshall = new ListViolationEventsRequestMarshaller().marshall(listViolationEventsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListViolationEventsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListViolationEventsResult listViolationEventsResult = (ListViolationEventsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listViolationEventsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listViolationEventsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public PutVerificationStateOnViolationResult putVerificationStateOnViolation(PutVerificationStateOnViolationRequest putVerificationStateOnViolationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(putVerificationStateOnViolationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<PutVerificationStateOnViolationRequest> requestMarshall = new PutVerificationStateOnViolationRequestMarshaller().marshall(putVerificationStateOnViolationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new PutVerificationStateOnViolationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        PutVerificationStateOnViolationResult putVerificationStateOnViolationResult = (PutVerificationStateOnViolationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return putVerificationStateOnViolationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = putVerificationStateOnViolationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RegisterCACertificateResult registerCACertificate(RegisterCACertificateRequest registerCACertificateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(registerCACertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<RegisterCACertificateRequest> requestMarshall = new RegisterCACertificateRequestMarshaller().marshall(registerCACertificateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new RegisterCACertificateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        RegisterCACertificateResult registerCACertificateResult = (RegisterCACertificateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return registerCACertificateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = registerCACertificateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RegisterCertificateResult registerCertificate(RegisterCertificateRequest registerCertificateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(registerCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<RegisterCertificateRequest> requestMarshall = new RegisterCertificateRequestMarshaller().marshall(registerCertificateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new RegisterCertificateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        RegisterCertificateResult registerCertificateResult = (RegisterCertificateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return registerCertificateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = registerCertificateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RegisterCertificateWithoutCAResult registerCertificateWithoutCA(RegisterCertificateWithoutCARequest registerCertificateWithoutCARequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(registerCertificateWithoutCARequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<RegisterCertificateWithoutCARequest> requestMarshall = new RegisterCertificateWithoutCARequestMarshaller().marshall(registerCertificateWithoutCARequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new RegisterCertificateWithoutCAResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        RegisterCertificateWithoutCAResult registerCertificateWithoutCAResult = (RegisterCertificateWithoutCAResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return registerCertificateWithoutCAResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = registerCertificateWithoutCARequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RegisterThingResult registerThing(RegisterThingRequest registerThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(registerThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<RegisterThingRequest> requestMarshall = new RegisterThingRequestMarshaller().marshall(registerThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new RegisterThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        RegisterThingResult registerThingResult = (RegisterThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return registerThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = registerThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.RejectCertificateTransferRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void rejectCertificateTransfer(RejectCertificateTransferRequest rejectCertificateTransferRequest) throws Throwable {
        Request<RejectCertificateTransferRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(rejectCertificateTransferRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new RejectCertificateTransferRequestMarshaller().marshall((RejectCertificateTransferRequest) rejectCertificateTransferRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                rejectCertificateTransferRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, rejectCertificateTransferRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, rejectCertificateTransferRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RemoveThingFromBillingGroupResult removeThingFromBillingGroup(RemoveThingFromBillingGroupRequest removeThingFromBillingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(removeThingFromBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<RemoveThingFromBillingGroupRequest> requestMarshall = new RemoveThingFromBillingGroupRequestMarshaller().marshall(removeThingFromBillingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new RemoveThingFromBillingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        RemoveThingFromBillingGroupResult removeThingFromBillingGroupResult = (RemoveThingFromBillingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return removeThingFromBillingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = removeThingFromBillingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RemoveThingFromThingGroupResult removeThingFromThingGroup(RemoveThingFromThingGroupRequest removeThingFromThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(removeThingFromThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<RemoveThingFromThingGroupRequest> requestMarshall = new RemoveThingFromThingGroupRequestMarshaller().marshall(removeThingFromThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new RemoveThingFromThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        RemoveThingFromThingGroupResult removeThingFromThingGroupResult = (RemoveThingFromThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return removeThingFromThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = removeThingFromThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ReplaceTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void replaceTopicRule(ReplaceTopicRuleRequest replaceTopicRuleRequest) throws Throwable {
        Request<ReplaceTopicRuleRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(replaceTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new ReplaceTopicRuleRequestMarshaller().marshall((ReplaceTopicRuleRequest) replaceTopicRuleRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                replaceTopicRuleRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, replaceTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, replaceTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public SearchIndexResult searchIndex(SearchIndexRequest searchIndexRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(searchIndexRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<SearchIndexRequest> requestMarshall = new SearchIndexRequestMarshaller().marshall(searchIndexRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new SearchIndexResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        SearchIndexResult searchIndexResult = (SearchIndexResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return searchIndexResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = searchIndexRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public SetDefaultAuthorizerResult setDefaultAuthorizer(SetDefaultAuthorizerRequest setDefaultAuthorizerRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(setDefaultAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<SetDefaultAuthorizerRequest> requestMarshall = new SetDefaultAuthorizerRequestMarshaller().marshall(setDefaultAuthorizerRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new SetDefaultAuthorizerResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        SetDefaultAuthorizerResult setDefaultAuthorizerResult = (SetDefaultAuthorizerResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return setDefaultAuthorizerResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = setDefaultAuthorizerRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SetDefaultPolicyVersionRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void setDefaultPolicyVersion(SetDefaultPolicyVersionRequest setDefaultPolicyVersionRequest) throws Throwable {
        Request<SetDefaultPolicyVersionRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(setDefaultPolicyVersionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new SetDefaultPolicyVersionRequestMarshaller().marshall((SetDefaultPolicyVersionRequest) setDefaultPolicyVersionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                setDefaultPolicyVersionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, setDefaultPolicyVersionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setDefaultPolicyVersionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SetLoggingOptionsRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void setLoggingOptions(SetLoggingOptionsRequest setLoggingOptionsRequest) throws Throwable {
        Request<SetLoggingOptionsRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(setLoggingOptionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new SetLoggingOptionsRequestMarshaller().marshall((SetLoggingOptionsRequest) setLoggingOptionsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                setLoggingOptionsRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, setLoggingOptionsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setLoggingOptionsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SetV2LoggingLevelRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void setV2LoggingLevel(SetV2LoggingLevelRequest setV2LoggingLevelRequest) throws Throwable {
        Request<SetV2LoggingLevelRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(setV2LoggingLevelRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new SetV2LoggingLevelRequestMarshaller().marshall((SetV2LoggingLevelRequest) setV2LoggingLevelRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                setV2LoggingLevelRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, setV2LoggingLevelRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setV2LoggingLevelRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SetV2LoggingOptionsRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void setV2LoggingOptions(SetV2LoggingOptionsRequest setV2LoggingOptionsRequest) throws Throwable {
        Request<SetV2LoggingOptionsRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(setV2LoggingOptionsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new SetV2LoggingOptionsRequestMarshaller().marshall((SetV2LoggingOptionsRequest) setV2LoggingOptionsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                setV2LoggingOptionsRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, setV2LoggingOptionsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setV2LoggingOptionsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public StartAuditMitigationActionsTaskResult startAuditMitigationActionsTask(StartAuditMitigationActionsTaskRequest startAuditMitigationActionsTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(startAuditMitigationActionsTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<StartAuditMitigationActionsTaskRequest> requestMarshall = new StartAuditMitigationActionsTaskRequestMarshaller().marshall(startAuditMitigationActionsTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new StartAuditMitigationActionsTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        StartAuditMitigationActionsTaskResult startAuditMitigationActionsTaskResult = (StartAuditMitigationActionsTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return startAuditMitigationActionsTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = startAuditMitigationActionsTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public StartDetectMitigationActionsTaskResult startDetectMitigationActionsTask(StartDetectMitigationActionsTaskRequest startDetectMitigationActionsTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(startDetectMitigationActionsTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<StartDetectMitigationActionsTaskRequest> requestMarshall = new StartDetectMitigationActionsTaskRequestMarshaller().marshall(startDetectMitigationActionsTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new StartDetectMitigationActionsTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        StartDetectMitigationActionsTaskResult startDetectMitigationActionsTaskResult = (StartDetectMitigationActionsTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return startDetectMitigationActionsTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = startDetectMitigationActionsTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public StartOnDemandAuditTaskResult startOnDemandAuditTask(StartOnDemandAuditTaskRequest startOnDemandAuditTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(startOnDemandAuditTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<StartOnDemandAuditTaskRequest> requestMarshall = new StartOnDemandAuditTaskRequestMarshaller().marshall(startOnDemandAuditTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new StartOnDemandAuditTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        StartOnDemandAuditTaskResult startOnDemandAuditTaskResult = (StartOnDemandAuditTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return startOnDemandAuditTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = startOnDemandAuditTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public StartThingRegistrationTaskResult startThingRegistrationTask(StartThingRegistrationTaskRequest startThingRegistrationTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(startThingRegistrationTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<StartThingRegistrationTaskRequest> requestMarshall = new StartThingRegistrationTaskRequestMarshaller().marshall(startThingRegistrationTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new StartThingRegistrationTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        StartThingRegistrationTaskResult startThingRegistrationTaskResult = (StartThingRegistrationTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return startThingRegistrationTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = startThingRegistrationTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public StopThingRegistrationTaskResult stopThingRegistrationTask(StopThingRegistrationTaskRequest stopThingRegistrationTaskRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(stopThingRegistrationTaskRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<StopThingRegistrationTaskRequest> requestMarshall = new StopThingRegistrationTaskRequestMarshaller().marshall(stopThingRegistrationTaskRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new StopThingRegistrationTaskResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        StopThingRegistrationTaskResult stopThingRegistrationTaskResult = (StopThingRegistrationTaskResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return stopThingRegistrationTaskResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = stopThingRegistrationTaskRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public TagResourceResult tagResource(TagResourceRequest tagResourceRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(tagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<TagResourceRequest> requestMarshall = new TagResourceRequestMarshaller().marshall(tagResourceRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new TagResourceResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        TagResourceResult tagResourceResult = (TagResourceResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return tagResourceResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = tagResourceRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public TestAuthorizationResult testAuthorization(TestAuthorizationRequest testAuthorizationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(testAuthorizationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<TestAuthorizationRequest> requestMarshall = new TestAuthorizationRequestMarshaller().marshall(testAuthorizationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new TestAuthorizationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        TestAuthorizationResult testAuthorizationResult = (TestAuthorizationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return testAuthorizationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = testAuthorizationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public TestInvokeAuthorizerResult testInvokeAuthorizer(TestInvokeAuthorizerRequest testInvokeAuthorizerRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(testInvokeAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<TestInvokeAuthorizerRequest> requestMarshall = new TestInvokeAuthorizerRequestMarshaller().marshall(testInvokeAuthorizerRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new TestInvokeAuthorizerResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        TestInvokeAuthorizerResult testInvokeAuthorizerResult = (TestInvokeAuthorizerResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return testInvokeAuthorizerResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = testInvokeAuthorizerRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public TransferCertificateResult transferCertificate(TransferCertificateRequest transferCertificateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(transferCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<TransferCertificateRequest> requestMarshall = new TransferCertificateRequestMarshaller().marshall(transferCertificateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new TransferCertificateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        TransferCertificateResult transferCertificateResult = (TransferCertificateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return transferCertificateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = transferCertificateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UntagResourceResult untagResource(UntagResourceRequest untagResourceRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(untagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UntagResourceRequest> requestMarshall = new UntagResourceRequestMarshaller().marshall(untagResourceRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UntagResourceResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UntagResourceResult untagResourceResult = (UntagResourceResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return untagResourceResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = untagResourceRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateAccountAuditConfigurationResult updateAccountAuditConfiguration(UpdateAccountAuditConfigurationRequest updateAccountAuditConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateAccountAuditConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateAccountAuditConfigurationRequest> requestMarshall = new UpdateAccountAuditConfigurationRequestMarshaller().marshall(updateAccountAuditConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateAccountAuditConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateAccountAuditConfigurationResult updateAccountAuditConfigurationResult = (UpdateAccountAuditConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateAccountAuditConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateAccountAuditConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateAuditSuppressionResult updateAuditSuppression(UpdateAuditSuppressionRequest updateAuditSuppressionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateAuditSuppressionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateAuditSuppressionRequest> requestMarshall = new UpdateAuditSuppressionRequestMarshaller().marshall(updateAuditSuppressionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateAuditSuppressionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateAuditSuppressionResult updateAuditSuppressionResult = (UpdateAuditSuppressionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateAuditSuppressionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateAuditSuppressionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateAuthorizerResult updateAuthorizer(UpdateAuthorizerRequest updateAuthorizerRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateAuthorizerRequest> requestMarshall = new UpdateAuthorizerRequestMarshaller().marshall(updateAuthorizerRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateAuthorizerResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateAuthorizerResult updateAuthorizerResult = (UpdateAuthorizerResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateAuthorizerResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateAuthorizerRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateBillingGroupResult updateBillingGroup(UpdateBillingGroupRequest updateBillingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateBillingGroupRequest> requestMarshall = new UpdateBillingGroupRequestMarshaller().marshall(updateBillingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateBillingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateBillingGroupResult updateBillingGroupResult = (UpdateBillingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateBillingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateBillingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateCACertificateRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void updateCACertificate(UpdateCACertificateRequest updateCACertificateRequest) throws Throwable {
        Request<UpdateCACertificateRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateCACertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new UpdateCACertificateRequestMarshaller().marshall((UpdateCACertificateRequest) updateCACertificateRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                updateCACertificateRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateCACertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateCACertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateCertificateRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void updateCertificate(UpdateCertificateRequest updateCertificateRequest) throws Throwable {
        Request<UpdateCertificateRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new UpdateCertificateRequestMarshaller().marshall((UpdateCertificateRequest) updateCertificateRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                updateCertificateRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateCertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateCertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateCustomMetricResult updateCustomMetric(UpdateCustomMetricRequest updateCustomMetricRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateCustomMetricRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateCustomMetricRequest> requestMarshall = new UpdateCustomMetricRequestMarshaller().marshall(updateCustomMetricRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateCustomMetricResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateCustomMetricResult updateCustomMetricResult = (UpdateCustomMetricResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateCustomMetricResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateCustomMetricRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateDimensionResult updateDimension(UpdateDimensionRequest updateDimensionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateDimensionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateDimensionRequest> requestMarshall = new UpdateDimensionRequestMarshaller().marshall(updateDimensionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateDimensionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateDimensionResult updateDimensionResult = (UpdateDimensionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateDimensionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateDimensionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateDomainConfigurationResult updateDomainConfiguration(UpdateDomainConfigurationRequest updateDomainConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateDomainConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateDomainConfigurationRequest> requestMarshall = new UpdateDomainConfigurationRequestMarshaller().marshall(updateDomainConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateDomainConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateDomainConfigurationResult updateDomainConfigurationResult = (UpdateDomainConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateDomainConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateDomainConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateDynamicThingGroupResult updateDynamicThingGroup(UpdateDynamicThingGroupRequest updateDynamicThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateDynamicThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateDynamicThingGroupRequest> requestMarshall = new UpdateDynamicThingGroupRequestMarshaller().marshall(updateDynamicThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateDynamicThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateDynamicThingGroupResult updateDynamicThingGroupResult = (UpdateDynamicThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateDynamicThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateDynamicThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateEventConfigurationsResult updateEventConfigurations(UpdateEventConfigurationsRequest updateEventConfigurationsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateEventConfigurationsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateEventConfigurationsRequest> requestMarshall = new UpdateEventConfigurationsRequestMarshaller().marshall(updateEventConfigurationsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateEventConfigurationsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateEventConfigurationsResult updateEventConfigurationsResult = (UpdateEventConfigurationsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateEventConfigurationsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateEventConfigurationsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateFleetMetricRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void updateFleetMetric(UpdateFleetMetricRequest updateFleetMetricRequest) throws Throwable {
        Request<UpdateFleetMetricRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateFleetMetricRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new UpdateFleetMetricRequestMarshaller().marshall((UpdateFleetMetricRequest) updateFleetMetricRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                updateFleetMetricRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateFleetMetricRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateFleetMetricRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateIndexingConfigurationResult updateIndexingConfiguration(UpdateIndexingConfigurationRequest updateIndexingConfigurationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateIndexingConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateIndexingConfigurationRequest> requestMarshall = new UpdateIndexingConfigurationRequestMarshaller().marshall(updateIndexingConfigurationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateIndexingConfigurationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateIndexingConfigurationResult updateIndexingConfigurationResult = (UpdateIndexingConfigurationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateIndexingConfigurationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateIndexingConfigurationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateJobRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void updateJob(UpdateJobRequest updateJobRequest) throws Throwable {
        Request<UpdateJobRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateJobRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new UpdateJobRequestMarshaller().marshall((UpdateJobRequest) updateJobRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                updateJobRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateMitigationActionResult updateMitigationAction(UpdateMitigationActionRequest updateMitigationActionRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateMitigationActionRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateMitigationActionRequest> requestMarshall = new UpdateMitigationActionRequestMarshaller().marshall(updateMitigationActionRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateMitigationActionResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateMitigationActionResult updateMitigationActionResult = (UpdateMitigationActionResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateMitigationActionResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateMitigationActionRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateProvisioningTemplateResult updateProvisioningTemplate(UpdateProvisioningTemplateRequest updateProvisioningTemplateRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateProvisioningTemplateRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateProvisioningTemplateRequest> requestMarshall = new UpdateProvisioningTemplateRequestMarshaller().marshall(updateProvisioningTemplateRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateProvisioningTemplateResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateProvisioningTemplateResult updateProvisioningTemplateResult = (UpdateProvisioningTemplateResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateProvisioningTemplateResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateProvisioningTemplateRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateRoleAliasResult updateRoleAlias(UpdateRoleAliasRequest updateRoleAliasRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateRoleAliasRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateRoleAliasRequest> requestMarshall = new UpdateRoleAliasRequestMarshaller().marshall(updateRoleAliasRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateRoleAliasResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateRoleAliasResult updateRoleAliasResult = (UpdateRoleAliasResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateRoleAliasResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateRoleAliasRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateScheduledAuditResult updateScheduledAudit(UpdateScheduledAuditRequest updateScheduledAuditRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateScheduledAuditRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateScheduledAuditRequest> requestMarshall = new UpdateScheduledAuditRequestMarshaller().marshall(updateScheduledAuditRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateScheduledAuditResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateScheduledAuditResult updateScheduledAuditResult = (UpdateScheduledAuditResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateScheduledAuditResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateScheduledAuditRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateSecurityProfileResult updateSecurityProfile(UpdateSecurityProfileRequest updateSecurityProfileRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateSecurityProfileRequest> requestMarshall = new UpdateSecurityProfileRequestMarshaller().marshall(updateSecurityProfileRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateSecurityProfileResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateSecurityProfileResult updateSecurityProfileResult = (UpdateSecurityProfileResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateSecurityProfileResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateSecurityProfileRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateStreamResult updateStream(UpdateStreamRequest updateStreamRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateStreamRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateStreamRequest> requestMarshall = new UpdateStreamRequestMarshaller().marshall(updateStreamRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateStreamResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateStreamResult updateStreamResult = (UpdateStreamResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateStreamResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateStreamRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateThingResult updateThing(UpdateThingRequest updateThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateThingRequest> requestMarshall = new UpdateThingRequestMarshaller().marshall(updateThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateThingResult updateThingResult = (UpdateThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateThingGroupResult updateThingGroup(UpdateThingGroupRequest updateThingGroupRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateThingGroupRequest> requestMarshall = new UpdateThingGroupRequestMarshaller().marshall(updateThingGroupRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateThingGroupResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateThingGroupResult updateThingGroupResult = (UpdateThingGroupResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateThingGroupResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateThingGroupRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateThingGroupsForThingResult updateThingGroupsForThing(UpdateThingGroupsForThingRequest updateThingGroupsForThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateThingGroupsForThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateThingGroupsForThingRequest> requestMarshall = new UpdateThingGroupsForThingRequestMarshaller().marshall(updateThingGroupsForThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateThingGroupsForThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateThingGroupsForThingResult updateThingGroupsForThingResult = (UpdateThingGroupsForThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateThingGroupsForThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateThingGroupsForThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateTopicRuleDestinationResult updateTopicRuleDestination(UpdateTopicRuleDestinationRequest updateTopicRuleDestinationRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateTopicRuleDestinationRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateTopicRuleDestinationRequest> requestMarshall = new UpdateTopicRuleDestinationRequestMarshaller().marshall(updateTopicRuleDestinationRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateTopicRuleDestinationResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateTopicRuleDestinationResult updateTopicRuleDestinationResult = (UpdateTopicRuleDestinationResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateTopicRuleDestinationResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateTopicRuleDestinationRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iot.AWSIotClient] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviors(ValidateSecurityProfileBehaviorsRequest validateSecurityProfileBehaviorsRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(validateSecurityProfileBehaviorsRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ValidateSecurityProfileBehaviorsRequest> requestMarshall = new ValidateSecurityProfileBehaviorsRequestMarshaller().marshall(validateSecurityProfileBehaviorsRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ValidateSecurityProfileBehaviorsResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviorsResult = (ValidateSecurityProfileBehaviorsResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return validateSecurityProfileBehaviorsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = validateSecurityProfileBehaviorsRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    private <X, Y extends AmazonWebServiceRequest> Response<X> invoke(Request<Y> request, HttpResponseHandler<AmazonWebServiceResponse<X>> httpResponseHandler, ExecutionContext executionContext) {
        request.setEndpoint(this.endpoint);
        request.setTimeOffset(this.timeOffset);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.CredentialsRequestTime;
        awsRequestMetrics.startEvent(field);
        try {
            AWSCredentials credentials = this.awsCredentialsProvider.getCredentials();
            awsRequestMetrics.endEvent(field);
            AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
            if (originalRequest != null && originalRequest.getRequestCredentials() != null) {
                credentials = originalRequest.getRequestCredentials();
            }
            executionContext.setCredentials(credentials);
            return this.client.execute(request, httpResponseHandler, new JsonErrorResponseHandler(this.jsonErrorUnmarshallers), executionContext);
        } catch (Throwable th) {
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
            throw th;
        }
    }
}
