package org.mifos.processor.bulk.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

/**
 * Everything under {@code operations-app}.
 *
 * <p>
 * The property names are exactly the ones that were on the {@code @Value} annotations before, because the operator sets
 * {@code OPERATIONS_APP_CONTACTPOINT} and {@code OPERATIONS_APP_ENDPOINTS_BATCH_TRANSACTION} as environment variables.
 * Renaming one would silently break a deployment.
 */
@Validated
@ConfigurationProperties(prefix = "operations-app")
public record OperationsAppProperties(@NotBlank(message = "operations-app.contactpoint must be set") String contactpoint, String username,
        String password, @Valid @DefaultValue Endpoints endpoints) {

    public record Endpoints(@NotBlank(message = "operations-app.endpoints.batch-transaction must be set") String batchTransaction,
            @NotBlank(message = "operations-app.endpoints.batch-summary must be set") String batchSummary,
            @NotBlank(message = "operations-app.endpoints.batch-aggregate must be set") String batchAggregate,
            @NotBlank(message = "operations-app.endpoints.auth must be set") String auth) {
    }

    public String batchTransactionUrl() {
        return contactpoint + endpoints.batchTransaction();
    }

    public String batchSummaryUrl() {
        return contactpoint + endpoints.batchSummary();
    }

    public String batchAggregateUrl() {
        return contactpoint + endpoints.batchAggregate();
    }

    public String authUrl() {
        return contactpoint + endpoints.auth();
    }
}
