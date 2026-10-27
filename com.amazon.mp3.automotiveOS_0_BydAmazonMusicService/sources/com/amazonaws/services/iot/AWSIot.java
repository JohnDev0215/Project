package com.amazonaws.services.iot;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
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

/* JADX INFO: loaded from: classes5.dex */
public interface AWSIot {
    void acceptCertificateTransfer(AcceptCertificateTransferRequest acceptCertificateTransferRequest) throws AmazonClientException;

    AddThingToBillingGroupResult addThingToBillingGroup(AddThingToBillingGroupRequest addThingToBillingGroupRequest) throws AmazonClientException;

    AddThingToThingGroupResult addThingToThingGroup(AddThingToThingGroupRequest addThingToThingGroupRequest) throws AmazonClientException;

    AssociateTargetsWithJobResult associateTargetsWithJob(AssociateTargetsWithJobRequest associateTargetsWithJobRequest) throws AmazonClientException;

    void attachPolicy(AttachPolicyRequest attachPolicyRequest) throws AmazonClientException;

    @Deprecated
    void attachPrincipalPolicy(AttachPrincipalPolicyRequest attachPrincipalPolicyRequest) throws AmazonClientException;

    AttachSecurityProfileResult attachSecurityProfile(AttachSecurityProfileRequest attachSecurityProfileRequest) throws AmazonClientException;

    AttachThingPrincipalResult attachThingPrincipal(AttachThingPrincipalRequest attachThingPrincipalRequest) throws AmazonClientException;

    CancelAuditMitigationActionsTaskResult cancelAuditMitigationActionsTask(CancelAuditMitigationActionsTaskRequest cancelAuditMitigationActionsTaskRequest) throws AmazonClientException;

    CancelAuditTaskResult cancelAuditTask(CancelAuditTaskRequest cancelAuditTaskRequest) throws AmazonClientException;

    void cancelCertificateTransfer(CancelCertificateTransferRequest cancelCertificateTransferRequest) throws AmazonClientException;

    CancelDetectMitigationActionsTaskResult cancelDetectMitigationActionsTask(CancelDetectMitigationActionsTaskRequest cancelDetectMitigationActionsTaskRequest) throws AmazonClientException;

    CancelJobResult cancelJob(CancelJobRequest cancelJobRequest) throws AmazonClientException;

    void cancelJobExecution(CancelJobExecutionRequest cancelJobExecutionRequest) throws AmazonClientException;

    ClearDefaultAuthorizerResult clearDefaultAuthorizer(ClearDefaultAuthorizerRequest clearDefaultAuthorizerRequest) throws AmazonClientException;

    ConfirmTopicRuleDestinationResult confirmTopicRuleDestination(ConfirmTopicRuleDestinationRequest confirmTopicRuleDestinationRequest) throws AmazonClientException;

    CreateAuditSuppressionResult createAuditSuppression(CreateAuditSuppressionRequest createAuditSuppressionRequest) throws AmazonClientException;

    CreateAuthorizerResult createAuthorizer(CreateAuthorizerRequest createAuthorizerRequest) throws AmazonClientException;

    CreateBillingGroupResult createBillingGroup(CreateBillingGroupRequest createBillingGroupRequest) throws AmazonClientException;

    CreateCertificateFromCsrResult createCertificateFromCsr(CreateCertificateFromCsrRequest createCertificateFromCsrRequest) throws AmazonClientException;

    CreateCustomMetricResult createCustomMetric(CreateCustomMetricRequest createCustomMetricRequest) throws AmazonClientException;

    CreateDimensionResult createDimension(CreateDimensionRequest createDimensionRequest) throws AmazonClientException;

    CreateDomainConfigurationResult createDomainConfiguration(CreateDomainConfigurationRequest createDomainConfigurationRequest) throws AmazonClientException;

    CreateDynamicThingGroupResult createDynamicThingGroup(CreateDynamicThingGroupRequest createDynamicThingGroupRequest) throws AmazonClientException;

    CreateFleetMetricResult createFleetMetric(CreateFleetMetricRequest createFleetMetricRequest) throws AmazonClientException;

    CreateJobResult createJob(CreateJobRequest createJobRequest) throws AmazonClientException;

    CreateJobTemplateResult createJobTemplate(CreateJobTemplateRequest createJobTemplateRequest) throws AmazonClientException;

    CreateKeysAndCertificateResult createKeysAndCertificate(CreateKeysAndCertificateRequest createKeysAndCertificateRequest) throws AmazonClientException;

    CreateMitigationActionResult createMitigationAction(CreateMitigationActionRequest createMitigationActionRequest) throws AmazonClientException;

    CreateOTAUpdateResult createOTAUpdate(CreateOTAUpdateRequest createOTAUpdateRequest) throws AmazonClientException;

    CreatePolicyResult createPolicy(CreatePolicyRequest createPolicyRequest) throws AmazonClientException;

    CreatePolicyVersionResult createPolicyVersion(CreatePolicyVersionRequest createPolicyVersionRequest) throws AmazonClientException;

    CreateProvisioningClaimResult createProvisioningClaim(CreateProvisioningClaimRequest createProvisioningClaimRequest) throws AmazonClientException;

    CreateProvisioningTemplateResult createProvisioningTemplate(CreateProvisioningTemplateRequest createProvisioningTemplateRequest) throws AmazonClientException;

    CreateProvisioningTemplateVersionResult createProvisioningTemplateVersion(CreateProvisioningTemplateVersionRequest createProvisioningTemplateVersionRequest) throws AmazonClientException;

    CreateRoleAliasResult createRoleAlias(CreateRoleAliasRequest createRoleAliasRequest) throws AmazonClientException;

    CreateScheduledAuditResult createScheduledAudit(CreateScheduledAuditRequest createScheduledAuditRequest) throws AmazonClientException;

    CreateSecurityProfileResult createSecurityProfile(CreateSecurityProfileRequest createSecurityProfileRequest) throws AmazonClientException;

    CreateStreamResult createStream(CreateStreamRequest createStreamRequest) throws AmazonClientException;

    CreateThingResult createThing(CreateThingRequest createThingRequest) throws AmazonClientException;

    CreateThingGroupResult createThingGroup(CreateThingGroupRequest createThingGroupRequest) throws AmazonClientException;

    CreateThingTypeResult createThingType(CreateThingTypeRequest createThingTypeRequest) throws AmazonClientException;

    void createTopicRule(CreateTopicRuleRequest createTopicRuleRequest) throws AmazonClientException;

    CreateTopicRuleDestinationResult createTopicRuleDestination(CreateTopicRuleDestinationRequest createTopicRuleDestinationRequest) throws AmazonClientException;

    DeleteAccountAuditConfigurationResult deleteAccountAuditConfiguration(DeleteAccountAuditConfigurationRequest deleteAccountAuditConfigurationRequest) throws AmazonClientException;

    DeleteAuditSuppressionResult deleteAuditSuppression(DeleteAuditSuppressionRequest deleteAuditSuppressionRequest) throws AmazonClientException;

    DeleteAuthorizerResult deleteAuthorizer(DeleteAuthorizerRequest deleteAuthorizerRequest) throws AmazonClientException;

    DeleteBillingGroupResult deleteBillingGroup(DeleteBillingGroupRequest deleteBillingGroupRequest) throws AmazonClientException;

    DeleteCACertificateResult deleteCACertificate(DeleteCACertificateRequest deleteCACertificateRequest) throws AmazonClientException;

    void deleteCertificate(DeleteCertificateRequest deleteCertificateRequest) throws AmazonClientException;

    DeleteCustomMetricResult deleteCustomMetric(DeleteCustomMetricRequest deleteCustomMetricRequest) throws AmazonClientException;

    DeleteDimensionResult deleteDimension(DeleteDimensionRequest deleteDimensionRequest) throws AmazonClientException;

    DeleteDomainConfigurationResult deleteDomainConfiguration(DeleteDomainConfigurationRequest deleteDomainConfigurationRequest) throws AmazonClientException;

    DeleteDynamicThingGroupResult deleteDynamicThingGroup(DeleteDynamicThingGroupRequest deleteDynamicThingGroupRequest) throws AmazonClientException;

    void deleteFleetMetric(DeleteFleetMetricRequest deleteFleetMetricRequest) throws AmazonClientException;

    void deleteJob(DeleteJobRequest deleteJobRequest) throws AmazonClientException;

    void deleteJobExecution(DeleteJobExecutionRequest deleteJobExecutionRequest) throws AmazonClientException;

    void deleteJobTemplate(DeleteJobTemplateRequest deleteJobTemplateRequest) throws AmazonClientException;

    DeleteMitigationActionResult deleteMitigationAction(DeleteMitigationActionRequest deleteMitigationActionRequest) throws AmazonClientException;

    DeleteOTAUpdateResult deleteOTAUpdate(DeleteOTAUpdateRequest deleteOTAUpdateRequest) throws AmazonClientException;

    void deletePolicy(DeletePolicyRequest deletePolicyRequest) throws AmazonClientException;

    void deletePolicyVersion(DeletePolicyVersionRequest deletePolicyVersionRequest) throws AmazonClientException;

    DeleteProvisioningTemplateResult deleteProvisioningTemplate(DeleteProvisioningTemplateRequest deleteProvisioningTemplateRequest) throws AmazonClientException;

    DeleteProvisioningTemplateVersionResult deleteProvisioningTemplateVersion(DeleteProvisioningTemplateVersionRequest deleteProvisioningTemplateVersionRequest) throws AmazonClientException;

    DeleteRegistrationCodeResult deleteRegistrationCode(DeleteRegistrationCodeRequest deleteRegistrationCodeRequest) throws AmazonClientException;

    DeleteRoleAliasResult deleteRoleAlias(DeleteRoleAliasRequest deleteRoleAliasRequest) throws AmazonClientException;

    DeleteScheduledAuditResult deleteScheduledAudit(DeleteScheduledAuditRequest deleteScheduledAuditRequest) throws AmazonClientException;

    DeleteSecurityProfileResult deleteSecurityProfile(DeleteSecurityProfileRequest deleteSecurityProfileRequest) throws AmazonClientException;

    DeleteStreamResult deleteStream(DeleteStreamRequest deleteStreamRequest) throws AmazonClientException;

    DeleteThingResult deleteThing(DeleteThingRequest deleteThingRequest) throws AmazonClientException;

    DeleteThingGroupResult deleteThingGroup(DeleteThingGroupRequest deleteThingGroupRequest) throws AmazonClientException;

    DeleteThingTypeResult deleteThingType(DeleteThingTypeRequest deleteThingTypeRequest) throws AmazonClientException;

    void deleteTopicRule(DeleteTopicRuleRequest deleteTopicRuleRequest) throws AmazonClientException;

    DeleteTopicRuleDestinationResult deleteTopicRuleDestination(DeleteTopicRuleDestinationRequest deleteTopicRuleDestinationRequest) throws AmazonClientException;

    void deleteV2LoggingLevel(DeleteV2LoggingLevelRequest deleteV2LoggingLevelRequest) throws AmazonClientException;

    DeprecateThingTypeResult deprecateThingType(DeprecateThingTypeRequest deprecateThingTypeRequest) throws AmazonClientException;

    DescribeAccountAuditConfigurationResult describeAccountAuditConfiguration(DescribeAccountAuditConfigurationRequest describeAccountAuditConfigurationRequest) throws AmazonClientException;

    DescribeAuditFindingResult describeAuditFinding(DescribeAuditFindingRequest describeAuditFindingRequest) throws AmazonClientException;

    DescribeAuditMitigationActionsTaskResult describeAuditMitigationActionsTask(DescribeAuditMitigationActionsTaskRequest describeAuditMitigationActionsTaskRequest) throws AmazonClientException;

    DescribeAuditSuppressionResult describeAuditSuppression(DescribeAuditSuppressionRequest describeAuditSuppressionRequest) throws AmazonClientException;

    DescribeAuditTaskResult describeAuditTask(DescribeAuditTaskRequest describeAuditTaskRequest) throws AmazonClientException;

    DescribeAuthorizerResult describeAuthorizer(DescribeAuthorizerRequest describeAuthorizerRequest) throws AmazonClientException;

    DescribeBillingGroupResult describeBillingGroup(DescribeBillingGroupRequest describeBillingGroupRequest) throws AmazonClientException;

    DescribeCACertificateResult describeCACertificate(DescribeCACertificateRequest describeCACertificateRequest) throws AmazonClientException;

    DescribeCertificateResult describeCertificate(DescribeCertificateRequest describeCertificateRequest) throws AmazonClientException;

    DescribeCustomMetricResult describeCustomMetric(DescribeCustomMetricRequest describeCustomMetricRequest) throws AmazonClientException;

    DescribeDefaultAuthorizerResult describeDefaultAuthorizer(DescribeDefaultAuthorizerRequest describeDefaultAuthorizerRequest) throws AmazonClientException;

    DescribeDetectMitigationActionsTaskResult describeDetectMitigationActionsTask(DescribeDetectMitigationActionsTaskRequest describeDetectMitigationActionsTaskRequest) throws AmazonClientException;

    DescribeDimensionResult describeDimension(DescribeDimensionRequest describeDimensionRequest) throws AmazonClientException;

    DescribeDomainConfigurationResult describeDomainConfiguration(DescribeDomainConfigurationRequest describeDomainConfigurationRequest) throws AmazonClientException;

    DescribeEndpointResult describeEndpoint(DescribeEndpointRequest describeEndpointRequest) throws AmazonClientException;

    DescribeEventConfigurationsResult describeEventConfigurations(DescribeEventConfigurationsRequest describeEventConfigurationsRequest) throws AmazonClientException;

    DescribeFleetMetricResult describeFleetMetric(DescribeFleetMetricRequest describeFleetMetricRequest) throws AmazonClientException;

    DescribeIndexResult describeIndex(DescribeIndexRequest describeIndexRequest) throws AmazonClientException;

    DescribeJobResult describeJob(DescribeJobRequest describeJobRequest) throws AmazonClientException;

    DescribeJobExecutionResult describeJobExecution(DescribeJobExecutionRequest describeJobExecutionRequest) throws AmazonClientException;

    DescribeJobTemplateResult describeJobTemplate(DescribeJobTemplateRequest describeJobTemplateRequest) throws AmazonClientException;

    DescribeManagedJobTemplateResult describeManagedJobTemplate(DescribeManagedJobTemplateRequest describeManagedJobTemplateRequest) throws AmazonClientException;

    DescribeMitigationActionResult describeMitigationAction(DescribeMitigationActionRequest describeMitigationActionRequest) throws AmazonClientException;

    DescribeProvisioningTemplateResult describeProvisioningTemplate(DescribeProvisioningTemplateRequest describeProvisioningTemplateRequest) throws AmazonClientException;

    DescribeProvisioningTemplateVersionResult describeProvisioningTemplateVersion(DescribeProvisioningTemplateVersionRequest describeProvisioningTemplateVersionRequest) throws AmazonClientException;

    DescribeRoleAliasResult describeRoleAlias(DescribeRoleAliasRequest describeRoleAliasRequest) throws AmazonClientException;

    DescribeScheduledAuditResult describeScheduledAudit(DescribeScheduledAuditRequest describeScheduledAuditRequest) throws AmazonClientException;

    DescribeSecurityProfileResult describeSecurityProfile(DescribeSecurityProfileRequest describeSecurityProfileRequest) throws AmazonClientException;

    DescribeStreamResult describeStream(DescribeStreamRequest describeStreamRequest) throws AmazonClientException;

    DescribeThingResult describeThing(DescribeThingRequest describeThingRequest) throws AmazonClientException;

    DescribeThingGroupResult describeThingGroup(DescribeThingGroupRequest describeThingGroupRequest) throws AmazonClientException;

    DescribeThingRegistrationTaskResult describeThingRegistrationTask(DescribeThingRegistrationTaskRequest describeThingRegistrationTaskRequest) throws AmazonClientException;

    DescribeThingTypeResult describeThingType(DescribeThingTypeRequest describeThingTypeRequest) throws AmazonClientException;

    void detachPolicy(DetachPolicyRequest detachPolicyRequest) throws AmazonClientException;

    @Deprecated
    void detachPrincipalPolicy(DetachPrincipalPolicyRequest detachPrincipalPolicyRequest) throws AmazonClientException;

    DetachSecurityProfileResult detachSecurityProfile(DetachSecurityProfileRequest detachSecurityProfileRequest) throws AmazonClientException;

    DetachThingPrincipalResult detachThingPrincipal(DetachThingPrincipalRequest detachThingPrincipalRequest) throws AmazonClientException;

    void disableTopicRule(DisableTopicRuleRequest disableTopicRuleRequest) throws AmazonClientException;

    void enableTopicRule(EnableTopicRuleRequest enableTopicRuleRequest) throws AmazonClientException;

    GetBehaviorModelTrainingSummariesResult getBehaviorModelTrainingSummaries(GetBehaviorModelTrainingSummariesRequest getBehaviorModelTrainingSummariesRequest) throws AmazonClientException;

    GetBucketsAggregationResult getBucketsAggregation(GetBucketsAggregationRequest getBucketsAggregationRequest) throws AmazonClientException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetCardinalityResult getCardinality(GetCardinalityRequest getCardinalityRequest) throws AmazonClientException;

    GetEffectivePoliciesResult getEffectivePolicies(GetEffectivePoliciesRequest getEffectivePoliciesRequest) throws AmazonClientException;

    GetIndexingConfigurationResult getIndexingConfiguration(GetIndexingConfigurationRequest getIndexingConfigurationRequest) throws AmazonClientException;

    GetJobDocumentResult getJobDocument(GetJobDocumentRequest getJobDocumentRequest) throws AmazonClientException;

    GetLoggingOptionsResult getLoggingOptions(GetLoggingOptionsRequest getLoggingOptionsRequest) throws AmazonClientException;

    GetOTAUpdateResult getOTAUpdate(GetOTAUpdateRequest getOTAUpdateRequest) throws AmazonClientException;

    GetPercentilesResult getPercentiles(GetPercentilesRequest getPercentilesRequest) throws AmazonClientException;

    GetPolicyResult getPolicy(GetPolicyRequest getPolicyRequest) throws AmazonClientException;

    GetPolicyVersionResult getPolicyVersion(GetPolicyVersionRequest getPolicyVersionRequest) throws AmazonClientException;

    GetRegistrationCodeResult getRegistrationCode(GetRegistrationCodeRequest getRegistrationCodeRequest) throws AmazonClientException;

    GetStatisticsResult getStatistics(GetStatisticsRequest getStatisticsRequest) throws AmazonClientException;

    GetTopicRuleResult getTopicRule(GetTopicRuleRequest getTopicRuleRequest) throws AmazonClientException;

    GetTopicRuleDestinationResult getTopicRuleDestination(GetTopicRuleDestinationRequest getTopicRuleDestinationRequest) throws AmazonClientException;

    GetV2LoggingOptionsResult getV2LoggingOptions(GetV2LoggingOptionsRequest getV2LoggingOptionsRequest) throws AmazonClientException;

    ListActiveViolationsResult listActiveViolations(ListActiveViolationsRequest listActiveViolationsRequest) throws AmazonClientException;

    ListAttachedPoliciesResult listAttachedPolicies(ListAttachedPoliciesRequest listAttachedPoliciesRequest) throws AmazonClientException;

    ListAuditFindingsResult listAuditFindings(ListAuditFindingsRequest listAuditFindingsRequest) throws AmazonClientException;

    ListAuditMitigationActionsExecutionsResult listAuditMitigationActionsExecutions(ListAuditMitigationActionsExecutionsRequest listAuditMitigationActionsExecutionsRequest) throws AmazonClientException;

    ListAuditMitigationActionsTasksResult listAuditMitigationActionsTasks(ListAuditMitigationActionsTasksRequest listAuditMitigationActionsTasksRequest) throws AmazonClientException;

    ListAuditSuppressionsResult listAuditSuppressions(ListAuditSuppressionsRequest listAuditSuppressionsRequest) throws AmazonClientException;

    ListAuditTasksResult listAuditTasks(ListAuditTasksRequest listAuditTasksRequest) throws AmazonClientException;

    ListAuthorizersResult listAuthorizers(ListAuthorizersRequest listAuthorizersRequest) throws AmazonClientException;

    ListBillingGroupsResult listBillingGroups(ListBillingGroupsRequest listBillingGroupsRequest) throws AmazonClientException;

    ListCACertificatesResult listCACertificates(ListCACertificatesRequest listCACertificatesRequest) throws AmazonClientException;

    ListCertificatesResult listCertificates(ListCertificatesRequest listCertificatesRequest) throws AmazonClientException;

    ListCertificatesByCAResult listCertificatesByCA(ListCertificatesByCARequest listCertificatesByCARequest) throws AmazonClientException;

    ListCustomMetricsResult listCustomMetrics(ListCustomMetricsRequest listCustomMetricsRequest) throws AmazonClientException;

    ListDetectMitigationActionsExecutionsResult listDetectMitigationActionsExecutions(ListDetectMitigationActionsExecutionsRequest listDetectMitigationActionsExecutionsRequest) throws AmazonClientException;

    ListDetectMitigationActionsTasksResult listDetectMitigationActionsTasks(ListDetectMitigationActionsTasksRequest listDetectMitigationActionsTasksRequest) throws AmazonClientException;

    ListDimensionsResult listDimensions(ListDimensionsRequest listDimensionsRequest) throws AmazonClientException;

    ListDomainConfigurationsResult listDomainConfigurations(ListDomainConfigurationsRequest listDomainConfigurationsRequest) throws AmazonClientException;

    ListFleetMetricsResult listFleetMetrics(ListFleetMetricsRequest listFleetMetricsRequest) throws AmazonClientException;

    ListIndicesResult listIndices(ListIndicesRequest listIndicesRequest) throws AmazonClientException;

    ListJobExecutionsForJobResult listJobExecutionsForJob(ListJobExecutionsForJobRequest listJobExecutionsForJobRequest) throws AmazonClientException;

    ListJobExecutionsForThingResult listJobExecutionsForThing(ListJobExecutionsForThingRequest listJobExecutionsForThingRequest) throws AmazonClientException;

    ListJobTemplatesResult listJobTemplates(ListJobTemplatesRequest listJobTemplatesRequest) throws AmazonClientException;

    ListJobsResult listJobs(ListJobsRequest listJobsRequest) throws AmazonClientException;

    ListManagedJobTemplatesResult listManagedJobTemplates(ListManagedJobTemplatesRequest listManagedJobTemplatesRequest) throws AmazonClientException;

    ListMetricValuesResult listMetricValues(ListMetricValuesRequest listMetricValuesRequest) throws AmazonClientException;

    ListMitigationActionsResult listMitigationActions(ListMitigationActionsRequest listMitigationActionsRequest) throws AmazonClientException;

    ListOTAUpdatesResult listOTAUpdates(ListOTAUpdatesRequest listOTAUpdatesRequest) throws AmazonClientException;

    ListOutgoingCertificatesResult listOutgoingCertificates(ListOutgoingCertificatesRequest listOutgoingCertificatesRequest) throws AmazonClientException;

    ListPoliciesResult listPolicies(ListPoliciesRequest listPoliciesRequest) throws AmazonClientException;

    @Deprecated
    ListPolicyPrincipalsResult listPolicyPrincipals(ListPolicyPrincipalsRequest listPolicyPrincipalsRequest) throws AmazonClientException;

    ListPolicyVersionsResult listPolicyVersions(ListPolicyVersionsRequest listPolicyVersionsRequest) throws AmazonClientException;

    @Deprecated
    ListPrincipalPoliciesResult listPrincipalPolicies(ListPrincipalPoliciesRequest listPrincipalPoliciesRequest) throws AmazonClientException;

    ListPrincipalThingsResult listPrincipalThings(ListPrincipalThingsRequest listPrincipalThingsRequest) throws AmazonClientException;

    ListProvisioningTemplateVersionsResult listProvisioningTemplateVersions(ListProvisioningTemplateVersionsRequest listProvisioningTemplateVersionsRequest) throws AmazonClientException;

    ListProvisioningTemplatesResult listProvisioningTemplates(ListProvisioningTemplatesRequest listProvisioningTemplatesRequest) throws AmazonClientException;

    ListRelatedResourcesForAuditFindingResult listRelatedResourcesForAuditFinding(ListRelatedResourcesForAuditFindingRequest listRelatedResourcesForAuditFindingRequest) throws AmazonClientException;

    ListRoleAliasesResult listRoleAliases(ListRoleAliasesRequest listRoleAliasesRequest) throws AmazonClientException;

    ListScheduledAuditsResult listScheduledAudits(ListScheduledAuditsRequest listScheduledAuditsRequest) throws AmazonClientException;

    ListSecurityProfilesResult listSecurityProfiles(ListSecurityProfilesRequest listSecurityProfilesRequest) throws AmazonClientException;

    ListSecurityProfilesForTargetResult listSecurityProfilesForTarget(ListSecurityProfilesForTargetRequest listSecurityProfilesForTargetRequest) throws AmazonClientException;

    ListStreamsResult listStreams(ListStreamsRequest listStreamsRequest) throws AmazonClientException;

    ListTagsForResourceResult listTagsForResource(ListTagsForResourceRequest listTagsForResourceRequest) throws AmazonClientException;

    ListTargetsForPolicyResult listTargetsForPolicy(ListTargetsForPolicyRequest listTargetsForPolicyRequest) throws AmazonClientException;

    ListTargetsForSecurityProfileResult listTargetsForSecurityProfile(ListTargetsForSecurityProfileRequest listTargetsForSecurityProfileRequest) throws AmazonClientException;

    ListThingGroupsResult listThingGroups(ListThingGroupsRequest listThingGroupsRequest) throws AmazonClientException;

    ListThingGroupsForThingResult listThingGroupsForThing(ListThingGroupsForThingRequest listThingGroupsForThingRequest) throws AmazonClientException;

    ListThingPrincipalsResult listThingPrincipals(ListThingPrincipalsRequest listThingPrincipalsRequest) throws AmazonClientException;

    ListThingRegistrationTaskReportsResult listThingRegistrationTaskReports(ListThingRegistrationTaskReportsRequest listThingRegistrationTaskReportsRequest) throws AmazonClientException;

    ListThingRegistrationTasksResult listThingRegistrationTasks(ListThingRegistrationTasksRequest listThingRegistrationTasksRequest) throws AmazonClientException;

    ListThingTypesResult listThingTypes(ListThingTypesRequest listThingTypesRequest) throws AmazonClientException;

    ListThingsResult listThings(ListThingsRequest listThingsRequest) throws AmazonClientException;

    ListThingsInBillingGroupResult listThingsInBillingGroup(ListThingsInBillingGroupRequest listThingsInBillingGroupRequest) throws AmazonClientException;

    ListThingsInThingGroupResult listThingsInThingGroup(ListThingsInThingGroupRequest listThingsInThingGroupRequest) throws AmazonClientException;

    ListTopicRuleDestinationsResult listTopicRuleDestinations(ListTopicRuleDestinationsRequest listTopicRuleDestinationsRequest) throws AmazonClientException;

    ListTopicRulesResult listTopicRules(ListTopicRulesRequest listTopicRulesRequest) throws AmazonClientException;

    ListV2LoggingLevelsResult listV2LoggingLevels(ListV2LoggingLevelsRequest listV2LoggingLevelsRequest) throws AmazonClientException;

    ListViolationEventsResult listViolationEvents(ListViolationEventsRequest listViolationEventsRequest) throws AmazonClientException;

    PutVerificationStateOnViolationResult putVerificationStateOnViolation(PutVerificationStateOnViolationRequest putVerificationStateOnViolationRequest) throws AmazonClientException;

    RegisterCACertificateResult registerCACertificate(RegisterCACertificateRequest registerCACertificateRequest) throws AmazonClientException;

    RegisterCertificateResult registerCertificate(RegisterCertificateRequest registerCertificateRequest) throws AmazonClientException;

    RegisterCertificateWithoutCAResult registerCertificateWithoutCA(RegisterCertificateWithoutCARequest registerCertificateWithoutCARequest) throws AmazonClientException;

    RegisterThingResult registerThing(RegisterThingRequest registerThingRequest) throws AmazonClientException;

    void rejectCertificateTransfer(RejectCertificateTransferRequest rejectCertificateTransferRequest) throws AmazonClientException;

    RemoveThingFromBillingGroupResult removeThingFromBillingGroup(RemoveThingFromBillingGroupRequest removeThingFromBillingGroupRequest) throws AmazonClientException;

    RemoveThingFromThingGroupResult removeThingFromThingGroup(RemoveThingFromThingGroupRequest removeThingFromThingGroupRequest) throws AmazonClientException;

    void replaceTopicRule(ReplaceTopicRuleRequest replaceTopicRuleRequest) throws AmazonClientException;

    SearchIndexResult searchIndex(SearchIndexRequest searchIndexRequest) throws AmazonClientException;

    SetDefaultAuthorizerResult setDefaultAuthorizer(SetDefaultAuthorizerRequest setDefaultAuthorizerRequest) throws AmazonClientException;

    void setDefaultPolicyVersion(SetDefaultPolicyVersionRequest setDefaultPolicyVersionRequest) throws AmazonClientException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setLoggingOptions(SetLoggingOptionsRequest setLoggingOptionsRequest) throws AmazonClientException;

    void setRegion(Region region) throws IllegalArgumentException;

    void setV2LoggingLevel(SetV2LoggingLevelRequest setV2LoggingLevelRequest) throws AmazonClientException;

    void setV2LoggingOptions(SetV2LoggingOptionsRequest setV2LoggingOptionsRequest) throws AmazonClientException;

    void shutdown();

    StartAuditMitigationActionsTaskResult startAuditMitigationActionsTask(StartAuditMitigationActionsTaskRequest startAuditMitigationActionsTaskRequest) throws AmazonClientException;

    StartDetectMitigationActionsTaskResult startDetectMitigationActionsTask(StartDetectMitigationActionsTaskRequest startDetectMitigationActionsTaskRequest) throws AmazonClientException;

    StartOnDemandAuditTaskResult startOnDemandAuditTask(StartOnDemandAuditTaskRequest startOnDemandAuditTaskRequest) throws AmazonClientException;

    StartThingRegistrationTaskResult startThingRegistrationTask(StartThingRegistrationTaskRequest startThingRegistrationTaskRequest) throws AmazonClientException;

    StopThingRegistrationTaskResult stopThingRegistrationTask(StopThingRegistrationTaskRequest stopThingRegistrationTaskRequest) throws AmazonClientException;

    TagResourceResult tagResource(TagResourceRequest tagResourceRequest) throws AmazonClientException;

    TestAuthorizationResult testAuthorization(TestAuthorizationRequest testAuthorizationRequest) throws AmazonClientException;

    TestInvokeAuthorizerResult testInvokeAuthorizer(TestInvokeAuthorizerRequest testInvokeAuthorizerRequest) throws AmazonClientException;

    TransferCertificateResult transferCertificate(TransferCertificateRequest transferCertificateRequest) throws AmazonClientException;

    UntagResourceResult untagResource(UntagResourceRequest untagResourceRequest) throws AmazonClientException;

    UpdateAccountAuditConfigurationResult updateAccountAuditConfiguration(UpdateAccountAuditConfigurationRequest updateAccountAuditConfigurationRequest) throws AmazonClientException;

    UpdateAuditSuppressionResult updateAuditSuppression(UpdateAuditSuppressionRequest updateAuditSuppressionRequest) throws AmazonClientException;

    UpdateAuthorizerResult updateAuthorizer(UpdateAuthorizerRequest updateAuthorizerRequest) throws AmazonClientException;

    UpdateBillingGroupResult updateBillingGroup(UpdateBillingGroupRequest updateBillingGroupRequest) throws AmazonClientException;

    void updateCACertificate(UpdateCACertificateRequest updateCACertificateRequest) throws AmazonClientException;

    void updateCertificate(UpdateCertificateRequest updateCertificateRequest) throws AmazonClientException;

    UpdateCustomMetricResult updateCustomMetric(UpdateCustomMetricRequest updateCustomMetricRequest) throws AmazonClientException;

    UpdateDimensionResult updateDimension(UpdateDimensionRequest updateDimensionRequest) throws AmazonClientException;

    UpdateDomainConfigurationResult updateDomainConfiguration(UpdateDomainConfigurationRequest updateDomainConfigurationRequest) throws AmazonClientException;

    UpdateDynamicThingGroupResult updateDynamicThingGroup(UpdateDynamicThingGroupRequest updateDynamicThingGroupRequest) throws AmazonClientException;

    UpdateEventConfigurationsResult updateEventConfigurations(UpdateEventConfigurationsRequest updateEventConfigurationsRequest) throws AmazonClientException;

    void updateFleetMetric(UpdateFleetMetricRequest updateFleetMetricRequest) throws AmazonClientException;

    UpdateIndexingConfigurationResult updateIndexingConfiguration(UpdateIndexingConfigurationRequest updateIndexingConfigurationRequest) throws AmazonClientException;

    void updateJob(UpdateJobRequest updateJobRequest) throws AmazonClientException;

    UpdateMitigationActionResult updateMitigationAction(UpdateMitigationActionRequest updateMitigationActionRequest) throws AmazonClientException;

    UpdateProvisioningTemplateResult updateProvisioningTemplate(UpdateProvisioningTemplateRequest updateProvisioningTemplateRequest) throws AmazonClientException;

    UpdateRoleAliasResult updateRoleAlias(UpdateRoleAliasRequest updateRoleAliasRequest) throws AmazonClientException;

    UpdateScheduledAuditResult updateScheduledAudit(UpdateScheduledAuditRequest updateScheduledAuditRequest) throws AmazonClientException;

    UpdateSecurityProfileResult updateSecurityProfile(UpdateSecurityProfileRequest updateSecurityProfileRequest) throws AmazonClientException;

    UpdateStreamResult updateStream(UpdateStreamRequest updateStreamRequest) throws AmazonClientException;

    UpdateThingResult updateThing(UpdateThingRequest updateThingRequest) throws AmazonClientException;

    UpdateThingGroupResult updateThingGroup(UpdateThingGroupRequest updateThingGroupRequest) throws AmazonClientException;

    UpdateThingGroupsForThingResult updateThingGroupsForThing(UpdateThingGroupsForThingRequest updateThingGroupsForThingRequest) throws AmazonClientException;

    UpdateTopicRuleDestinationResult updateTopicRuleDestination(UpdateTopicRuleDestinationRequest updateTopicRuleDestinationRequest) throws AmazonClientException;

    ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviors(ValidateSecurityProfileBehaviorsRequest validateSecurityProfileBehaviorsRequest) throws AmazonClientException;
}
