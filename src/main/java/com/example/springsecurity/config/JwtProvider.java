package com.example.springsecurity.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtProvider {

    @JsonProperty("issuer")
    String issuer;
    @JsonProperty("authorization_endpoint")
    String authorization_endpoint;
    @JsonProperty("token_endpoint")
    String token_endpoint;
    @JsonProperty("revocation_endpoint")
    String revocation_endpoint;
    @JsonProperty("userinfo_endpoint")
    String userinfo_endpoint;
    @JsonProperty("introspection_endpoint")
    String introspection_endpoint;
    @JsonProperty("jwks_uri")
    String jwks_uri;
    @JsonProperty("registration_endpoint")
    String registration_endpoint;
    @JsonProperty("ping_revoked_sris_endpoint")
    String ping_revoked_sris_endpoint;
    @JsonProperty("ping_end_session_endpoint")
    String ping_end_session_endpoint;

    @JsonProperty("scopes_supported")
    List<String> scopes_supported;
    @JsonProperty("claims_supported")
    List<String> claims_supported;
    @JsonProperty("response_types_supported")
    List<String> response_types_supported;
    @JsonProperty("response_modes_supported")
    List<String> response_modes_supported;
    @JsonProperty("grant_types_supported")
    List<String> grant_types_supported;
    @JsonProperty("subject_types_supported")
    List<String> subject_types_supported;
    @JsonProperty("id_token_signing_alg_values_supported")
    List<String> id_token_signing_alg_values_supported;
    @JsonProperty("token_endpoint_auth_methods_supported")
    List<String> token_endpoint_auth_methods_supported;
    @JsonProperty("token_endpoint_auth_signing_alg_values_supported")
    List<String> token_endpoint_auth_signing_alg_values_supported;
    @JsonProperty("claim_types_supported")
    List<String> claim_types_supported;
    @JsonProperty("claims_parameter_supported")
    Boolean claims_parameter_supported;
    @JsonProperty("request_parameter_supported")
    Boolean request_parameter_supported;
    @JsonProperty("request_uri_parameter_supported")
    Boolean request_uri_parameter_supported;
    @JsonProperty("request_object_signing_alg_values_supported")
    List<String> request_object_signing_alg_values_supported;
    @JsonProperty("request_object_encryption_alg_values_supported")
    List<String> request_object_encryption_alg_values_supported;
    @JsonProperty("request_object_encryption_enc_values_supported")
    List<String> request_object_encryption_enc_values_supported;

    public JwtProvider() {
    }


    public String getIssuer() {
        return issuer;
    }

    public String getAuthorization_endpoint() {
        return authorization_endpoint;
    }

    public String getToken_endpoint() {
        return token_endpoint;
    }

    public String getRevocation_endpoint() {
        return revocation_endpoint;
    }

    public String getUserinfo_endpoint() {
        return userinfo_endpoint;
    }

    public String getIntrospection_endpoint() {
        return introspection_endpoint;
    }

    public String getJwks_uri() {
        return jwks_uri;
    }

    public String getRegistration_endpoint() {
        return registration_endpoint;
    }

    public String getPing_revoked_sris_endpoint() {
        return ping_revoked_sris_endpoint;
    }

    public String getPing_end_session_endpoint() {
        return ping_end_session_endpoint;
    }

    public List<String> getScopes_supported() {
        return scopes_supported;
    }

    public List<String> getClaims_supported() {
        return claims_supported;
    }

    public List<String> getResponse_types_supported() {
        return response_types_supported;
    }

    public List<String> getResponse_modes_supported() {
        return response_modes_supported;
    }

    public List<String> getGrant_types_supported() {
        return grant_types_supported;
    }

    public List<String> getSubject_types_supported() {
        return subject_types_supported;
    }

    public List<String> getId_token_signing_alg_values_supported() {
        return id_token_signing_alg_values_supported;
    }

    public List<String> getToken_endpoint_auth_methods_supported() {
        return token_endpoint_auth_methods_supported;
    }

    public List<String> getToken_endpoint_auth_signing_alg_values_supported() {
        return token_endpoint_auth_signing_alg_values_supported;
    }

    public List<String> getClaim_types_supported() {
        return claim_types_supported;
    }

    public Boolean getClaims_parameter_supported() {
        return claims_parameter_supported;
    }

    public Boolean getRequest_parameter_supported() {
        return request_parameter_supported;
    }

    public Boolean getRequest_uri_parameter_supported() {
        return request_uri_parameter_supported;
    }

    public List<String> getRequest_object_signing_alg_values_supported() {
        return request_object_signing_alg_values_supported;
    }

    public List<String> getRequest_object_encryption_alg_values_supported() {
        return request_object_encryption_alg_values_supported;
    }

    public List<String> getRequest_object_encryption_enc_values_supported() {
        return request_object_encryption_enc_values_supported;
    }
}